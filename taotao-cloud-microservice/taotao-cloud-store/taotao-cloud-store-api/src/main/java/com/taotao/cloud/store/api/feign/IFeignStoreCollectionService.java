package com.taotao.cloud.store.api.feign;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.common.model.PageParam;
import com.taotao.cloud.store.api.feign.fallback.FeignStoreServiceFallbackImpl;
import com.taotao.cloud.store.api.web.vo.StoreCollectionVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(contextId = "IFeignFreightTemplateService", value = ServiceName.TAOTAO_CLOUD_GOODS, fallbackFactory = FeignStoreServiceFallbackImpl.class)
public interface IFeignStoreCollectionService {

	IPage<StoreCollectionVO> storeCollection(PageParam page);

	Boolean addStoreCollection(Long id);

	Boolean deleteStoreCollection(Long id);

	Boolean isCollection(Long id);
}
