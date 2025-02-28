package com.taotao.cloud.sys.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.sys.api.feign.fallback.FeignMenuServiceFallback;
import com.taotao.cloud.sys.api.model.vo.menu.MenuQueryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * 远程调用后台菜单模块
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2022-03-25 14:09:10
 */
@FeignClient(name = ServiceName.TAOTAO_CLOUD_SYS, fallbackFactory = FeignMenuServiceFallback.class)
public interface IFeignMenuService {

	/**
	 * 根据角色code列表获取角色列表
	 *
	 * @param codes 角色code列表
	 * @return 角色列表
	 * @since 2020/10/21 15:24
	 */
	@GetMapping("/menu/info/codes")
	List<MenuQueryVO> findResourceByCodes(@RequestParam(value = "codes") Set<String> codes);

}
