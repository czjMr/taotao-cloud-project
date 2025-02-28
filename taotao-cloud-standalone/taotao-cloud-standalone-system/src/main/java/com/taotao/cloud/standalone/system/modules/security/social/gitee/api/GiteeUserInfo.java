package com.taotao.cloud.standalone.system.modules.security.social.gitee.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname GiteeAdapter
 * @Description Gitee 用户信息
 * @Author shuigedeng
 * @since 2019-07-08 21:49
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiteeUserInfo {
    private int id;
    private String login;
    private String name;
    private String avatarUrl;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String blog;
}
