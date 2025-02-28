package com.taotao.cloud.message.biz.austin.handler.deduplication.limit;

import cn.hutool.core.collection.CollUtil;
import com.taotao.cloud.message.biz.austin.common.constant.AustinConstant;
import com.taotao.cloud.message.biz.austin.common.domain.TaskInfo;
import com.taotao.cloud.message.biz.austin.handler.deduplication.DeduplicationParam;
import com.taotao.cloud.message.biz.austin.handler.deduplication.service.AbstractDeduplicationService;
import com.taotao.cloud.message.biz.austin.support.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 采用普通的计数去重方法，限制的是每天发送的条数。
 *
 * @author cao
 * @date 2022-04-20 13:41
 */
@Service(value = "SimpleLimitService")
public class SimpleLimitService extends AbstractLimitService {

	private static final String LIMIT_TAG = "SP_";

	@Autowired
	private RedisUtils redisUtils;

	@Override
	public Set<String> limitFilter(AbstractDeduplicationService service, TaskInfo taskInfo, DeduplicationParam param) {
		Set<String> filterReceiver = new HashSet<>(taskInfo.getReceiver().size());
		// 获取redis记录
		Map<String, String> readyPutRedisReceiver = new HashMap<>(taskInfo.getReceiver().size());
		//redis数据隔离
		List<String> keys = deduplicationAllKey(service, taskInfo).stream().map(key -> LIMIT_TAG + key).collect(Collectors.toList());
		Map<String, String> inRedisValue = redisUtils.mGet(keys);

		for (String receiver : taskInfo.getReceiver()) {
			String key = LIMIT_TAG + deduplicationSingleKey(service, taskInfo, receiver);
			String value = inRedisValue.get(key);

			// 符合条件的用户
			if (value != null && Integer.parseInt(value) >= param.getCountNum()) {
				filterReceiver.add(receiver);
			} else {
				readyPutRedisReceiver.put(receiver, key);
			}
		}

		// 不符合条件的用户：需要更新Redis(无记录添加，有记录则累加次数)
		putInRedis(readyPutRedisReceiver, inRedisValue, param.getDeduplicationTime());

		return filterReceiver;
	}


	/**
	 * 存入redis 实现去重
	 *
	 * @param readyPutRedisReceiver
	 */
	private void putInRedis(Map<String, String> readyPutRedisReceiver,
							Map<String, String> inRedisValue, Long deduplicationTime) {
		Map<String, String> keyValues = new HashMap<>(readyPutRedisReceiver.size());
		for (Map.Entry<String, String> entry : readyPutRedisReceiver.entrySet()) {
			String key = entry.getValue();
			if (inRedisValue.get(key) != null) {
				keyValues.put(key, String.valueOf(Integer.valueOf(inRedisValue.get(key)) + 1));
			} else {
				keyValues.put(key, String.valueOf(AustinConstant.TRUE));
			}
		}
		if (CollUtil.isNotEmpty(keyValues)) {
			redisUtils.pipelineSetEx(keyValues, deduplicationTime);
		}
	}

}
