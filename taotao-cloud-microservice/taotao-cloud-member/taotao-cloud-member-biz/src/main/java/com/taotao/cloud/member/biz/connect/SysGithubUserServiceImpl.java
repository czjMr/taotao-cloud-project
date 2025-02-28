// package com.taotao.cloud.sys.biz.service.business.impl;
//
//
// import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
// import com.baomidou.mybatisplus.core.toolkit.Wrappers;
// import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
// import com.taotao.cloud.common.enums.ResultEnum;
// import com.taotao.cloud.common.model.PageResult;
// import com.taotao.cloud.data.mybatis.plus.service.impl.SuperServiceImpl;
// import com.taotao.cloud.sys.biz.entity.SysGithubUser;
// import com.taotao.cloud.sys.biz.mapper.SysGithubUserMapper;
// import com.taotao.cloud.sys.biz.service.ISysGithubUserService;
// import org.springframework.stereotype.Service;
//
// import java.util.Collections;
// import java.util.Map;
//
// /**
//  * github用户表
//  */
// 
// @Service
// public class SysGithubUserServiceImpl extends SuperServiceImpl<SysGithubUserMapper, SysGithubUser> implements ISysGithubUserService {
//     /**
//      * 列表
//      *
//      * @param params
//      * @return
//      */
//     @Override
//     public PageResult<SysGithubUser> findList(Map<String, Object> params) {
// //        Page<SysGithubUser> page = new Page<>(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"));
//         Page<SysGithubUser> page = new Page<>(10, 20);
//         LambdaQueryWrapper<SysGithubUser> githubUserLambdaQueryWrapper = Wrappers.<SysGithubUser>lambdaQuery();
//         Page<SysGithubUser> pageResult = baseMapper.selectPage(page, githubUserLambdaQueryWrapper);
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
