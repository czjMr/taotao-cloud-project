package com.taotao.cloud.message.biz.austin.web.service;


import com.taotao.cloud.message.biz.austin.support.domain.ChannelAccount;

import java.util.List;

/**
 * 渠道账号接口
 *
 * @author 3y
 */
public interface ChannelAccountService {


	/**
	 * 保存/修改渠道账号信息
	 *
	 * @param channelAccount
	 * @return
	 */
	ChannelAccount save(ChannelAccount channelAccount);

	/**
	 * 根据渠道标识查询账号信息
	 *
	 * @param channelType 渠道标识
	 * @return
	 */
	List<ChannelAccount> queryByChannelType(Integer channelType);


	/**
	 * 列表信息 无条件
	 *
	 * @return
	 */
	List<ChannelAccount> list();

	/**
	 * 软删除(deleted=1)
	 *
	 * @param ids
	 */
	void deleteByIds(List<Long> ids);

}
