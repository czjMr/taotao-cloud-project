package com.taotao.cloud.stock.biz.infrastructure.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码Mapper
 *
 * @author shuigedeng
 * @date 2021-02-08
 */
@Mapper
public interface SysCaptchaMapper extends BaseSuperMapper<SysCaptchaDO> {
}
