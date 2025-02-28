package com.taotao.cloud.common.generator;

import com.taotao.cloud.common.support.generator.EnglishNameGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class EnglishNameGeneratorTest {

    @Test
    public void testGenerate() {
        String generatedName = EnglishNameGenerator.getInstance().generate();
        Assertions.assertNotNull(generatedName);
        System.err.println(generatedName);
    }

}
