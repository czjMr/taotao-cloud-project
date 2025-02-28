// package com.taotao.cloud.sys.biz.service.business.impl;
//
//
// import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
// import com.baomidou.mybatisplus.core.toolkit.Wrappers;
// import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
// import com.taotao.cloud.common.enums.ResultEnum;
// import com.taotao.cloud.common.model.PageResult;
// import com.taotao.cloud.data.mybatis.plus.service.impl.SuperServiceImpl;
// import com.taotao.cloud.sys.biz.entity.SysQqUser;
// import com.taotao.cloud.sys.biz.mapper.SysQqUserMapper;
// import com.taotao.cloud.sys.biz.service.ISysQqUserService;
// import org.springframework.stereotype.Service;
//
// import java.util.Collections;
// import java.util.Map;
//
// /**
//  * qq用户表
//  */
// 
// @Service
// public class SysQqUserServiceImpl extends SuperServiceImpl<SysQqUserMapper, SysQqUser> implements ISysQqUserService {
//     @Override
//     public PageResult<SysQqUser> findList(Map<String, Object> params) {
// //        Page<SysQqUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
//         Page<SysQqUser> page = new Page<>(1, 10);
//         LambdaQueryWrapper<SysQqUser> qqUserLambdaQueryWrapper = Wrappers.<SysQqUser>lambdaQuery();
//         Page<SysQqUser> pageResult = baseMapper.selectPage(page, qqUserLambdaQueryWrapper);
//
//         PageResult result = PageResult.builder().currentPage(page.getCurrent()).total(pageResult.getTotal())
//                 .code(ResultEnum.SUCCESS.getCode())
//                 .pageSize(pageResult.getSize())
//                 .data(Collections.singletonList(pageResult.getRecords()))
//                 .build();
//
//         return result;
//     }
// }
