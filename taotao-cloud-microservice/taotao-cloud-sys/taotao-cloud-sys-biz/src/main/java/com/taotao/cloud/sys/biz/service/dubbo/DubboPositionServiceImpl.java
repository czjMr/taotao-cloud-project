package com.taotao.cloud.sys.biz.service.dubbo;

import com.taotao.cloud.sys.api.dubbo.IDubboPositionService;
import com.taotao.cloud.sys.biz.mapper.IPositionMapper;
import com.taotao.cloud.sys.biz.model.entity.system.Position;
import com.taotao.cloud.sys.biz.repository.cls.JobRepository;
import com.taotao.cloud.sys.biz.repository.inf.IJobRepository;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * 岗位表服务实现类
 *
 * @author shuigedeng
 * @since 2020-10-16 16:23:05
 * @since 1.0
 */
@Service
@DubboService(interfaceClass = IDubboPositionService.class, validation = "true")
public class DubboPositionServiceImpl extends
	BaseSuperServiceImpl<IPositionMapper, Position, JobRepository, IJobRepository, Long>
	implements IDubboPositionService {


}
