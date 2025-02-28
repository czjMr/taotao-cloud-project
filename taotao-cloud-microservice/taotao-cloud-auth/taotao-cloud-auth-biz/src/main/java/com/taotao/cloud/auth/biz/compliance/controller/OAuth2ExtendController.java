
package com.taotao.cloud.auth.biz.compliance.controller;

import cn.herodotus.engine.assistant.core.domain.Result;
import cn.herodotus.engine.oauth2.compliance.service.OAuth2AccountStatusService;
import cn.herodotus.engine.oauth2.compliance.service.OAuth2ComplianceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * <p>Description: OAuth2 扩展 接口 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/7 17:05
 */
@RestController
@RequestMapping("/oauth2")
@Tags({
        @Tag(name = "OAuth2 认证服务接口"),
        @Tag(name = "OAuth2 扩展接口")
})
public class OAuth2ExtendController {

    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2ComplianceService complianceService;
    private final OAuth2AccountStatusService accountLockService;

    @Autowired
    public OAuth2ExtendController(OAuth2AuthorizationService authorizationService, OAuth2ComplianceService complianceService, OAuth2AccountStatusService accountLockService) {
        this.authorizationService = authorizationService;
        this.complianceService = complianceService;
        this.accountLockService = accountLockService;
    }

    @Operation(summary = "注销 OAuth2 应用", description = "根据接收到的 AccessToken，删除后端存储的 Token信息，起到注销效果",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/x-www-form-urlencoded")),
            responses = {@ApiResponse(description = "是否成功", content = @Content(mediaType = "application/json"))})
    @Parameters({
            @Parameter(name = "accessToken", required = true, description = "Access Token"),
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, required = true, description = "Basic Token"),
    })
    @PutMapping("/sign-out")
    public Result<String> signOut(@RequestParam(name = "accessToken") @NotBlank String accessToken, HttpServletRequest request) {
        OAuth2Authorization authorization = authorizationService.findByToken(accessToken, OAuth2TokenType.ACCESS_TOKEN);
        if (ObjectUtils.isNotEmpty(authorization)) {
            authorizationService.remove(authorization);
            complianceService.save(authorization.getPrincipalName(), authorization.getRegisteredClientId(), "退出系统", request);
            accountLockService.releaseFromCache(authorization.getPrincipalName());
        }
        return Result.success("注销成功");
    }
}
