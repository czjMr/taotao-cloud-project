package com.taotao.cloud.sensitive.sensitive.sensitive.core.sensitive.system;

import com.taotao.cloud.core.sensitive.sensitive.core.DataPrepareTest;
import com.taotao.cloud.core.sensitive.sensitive.core.api.SensitiveUtil;
import com.taotao.cloud.core.sensitive.sensitive.model.sensitive.system.SystemBuiltInAt;
import com.taotao.cloud.core.sensitive.sensitive.model.sensitive.system.SystemBuiltInAtEntry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 系统内置注解测试
 */
public class SystemBuiltInTest {

    /**
     * 普通属性脱敏测试
     */
    @Test
    public void sensitiveTest() {
        final String originalStr = "SystemBuiltInAt{phone='18888888888', password='1234567', name='脱敏君', email='12345@qq.com', cardId='123456190001011234'}";
        final String sensitiveStr = "SystemBuiltInAt{phone='188****8888', password='null', name='脱*君', email='123**@qq.com', cardId='123456**********34'}";

        SystemBuiltInAt systemBuiltInAt = DataPrepareTest.buildSystemBuiltInAt();
        Assertions.assertEquals(originalStr, systemBuiltInAt.toString());

        SystemBuiltInAt sensitive = SensitiveUtil.desCopy(systemBuiltInAt);
        Assertions.assertEquals(sensitiveStr, sensitive.toString());
        Assertions.assertEquals(originalStr, systemBuiltInAt.toString());
    }

    /**
     * 属性包含对象
     */
    @Test
    public void sensitiveEntryTest() {
        final String originalStr = "SystemBuiltInAtEntry{entry=SystemBuiltInAt{phone='18888888888', password='1234567', name='脱敏君', email='12345@qq.com', cardId='123456190001011234'}}";
        final String sensitiveStr = "SystemBuiltInAtEntry{entry=SystemBuiltInAt{phone='188****8888', password='null', name='脱*君', email='123**@qq.com', cardId='123456**********34'}}";

        SystemBuiltInAtEntry entry = DataPrepareTest.buildSystemBuiltInAtEntry();
        Assertions.assertEquals(originalStr, entry.toString());

        SystemBuiltInAtEntry sensitive = SensitiveUtil.desCopy(entry);
        Assertions.assertEquals(sensitiveStr, sensitive.toString());
        Assertions.assertEquals(originalStr, entry.toString());
    }

    /**
     * 普通属性脱敏 JSON 测试
     */
    @Test
    public void sensitiveJsonTest() {
        final String originalStr = "SystemBuiltInAt{phone='18888888888', password='1234567', name='脱敏君', email='12345@qq.com', cardId='123456190001011234'}";
        final String sensitiveJson = "{\"cardId\":\"123456**********34\",\"email\":\"123**@qq.com\",\"name\":\"脱*君\",\"phone\":\"188****8888\"}";

        SystemBuiltInAt systemBuiltInAt = DataPrepareTest.buildSystemBuiltInAt();
        Assertions.assertEquals(sensitiveJson, SensitiveUtil.desJson(systemBuiltInAt));
        Assertions.assertEquals(originalStr, systemBuiltInAt.toString());
    }

    /**
     * 属性包含对象 JSON

     */
    @Test
    public void sensitiveEntryJsonTest() {
        final String originalStr = "SystemBuiltInAtEntry{entry=SystemBuiltInAt{phone='18888888888', password='1234567', name='脱敏君', email='12345@qq.com', cardId='123456190001011234'}}";
        final String sensitiveJson = "{\"entry\":{\"cardId\":\"123456**********34\",\"email\":\"123**@qq.com\",\"name\":\"脱*君\",\"phone\":\"188****8888\"}}";

        SystemBuiltInAtEntry entry = DataPrepareTest.buildSystemBuiltInAtEntry();

        Assertions.assertEquals(sensitiveJson, SensitiveUtil.desJson(entry));
        Assertions.assertEquals(originalStr, entry.toString());
    }

}
