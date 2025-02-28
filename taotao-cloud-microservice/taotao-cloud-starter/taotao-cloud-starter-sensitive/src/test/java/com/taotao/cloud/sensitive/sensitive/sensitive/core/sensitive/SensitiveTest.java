package com.taotao.cloud.sensitive.sensitive.sensitive.core.sensitive;

import com.taotao.cloud.sensitive.sensitive.sensitive.api.IStrategy;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.DataPrepareTest;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.SensitiveUtil;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Sensitive 脱敏测试类
 */
public class SensitiveTest {

    /**
     * 单个属性测试
     */
    @Test
    public void singleSensitiveTest() {
        final String email = "123456@qq.com";
        final String exceptResult = "123***@qq.com";
        IStrategy strategy = new StrategyEmail();
        final String emailSensitive = (String) strategy.des(email, null);
        Assertions.assertEquals(exceptResult, emailSensitive);
    }

    /**
     * 普通脱敏测试
     */
    @Test
    public void commonSensitiveTest() {
        final String originalStr = "User{username='脱敏君', idCard='123456190001011234', password='1234567', email='12345@qq.com', phone='18888888888'}";
        final String sensitiveStr = "User{username='脱*君', idCard='123456**********34', password='null', email='123**@qq.com', phone='188****8888'}";

        User user = DataPrepareTest.buildUser();
        Assertions.assertEquals(originalStr, user.toString());

        User sensitiveUser = SensitiveUtil.desCopy(user);
        Assertions.assertEquals(sensitiveStr, sensitiveUser.toString());
        Assertions.assertEquals(originalStr, user.toString());
    }

}
