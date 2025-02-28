///*
// * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.taotao.cloud.oauth2.api.oauth2_server.handler;
//
//import cn.hutool.core.collection.CollUtil;
//import com.taotao.cloud.common.utils.LogUtil;
//import com.taotao.cloud.core.model.SecurityUser;
//import org.springframework.context.ApplicationListener;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
///**
// * 在验证过程中成功会触发此类事件
// *
// * @author shuigedeng
// * @since 2020/4/29 21:23
// * @version 2022.03
// */
//@Component
//public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
//
//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent event) {
//        Authentication authentication = (Authentication) event.getSource();
//        if (CollUtil.isNotEmpty(authentication.getAuthorities())) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof SecurityUser) {
//                // 此处可以异步调用消息系统 发送消息或者邮件
//                LogUtil.info("用户：{0} 登录成功", ((SecurityUser) principal).getUsername());
//            }
//        }
//    }
//}
//
//
