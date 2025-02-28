package com.taotao.cloud.sensitive.sensitive.word.data;

import com.taotao.cloud.common.support.filter.IFilter;
import com.taotao.cloud.common.utils.collection.CollectionUtils;
import com.taotao.cloud.common.utils.common.CharsetUtils;
import com.taotao.cloud.common.utils.io.FileUtils;
import com.taotao.cloud.common.utils.lang.StringUtils;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * 数据初始化
 */
@Ignore
public class DataInitTest {

    @Test
    @Ignore
    public void splitTest() {
        final String targetFile = "D:\\github\\sensitive-word\\src\\main\\resources\\dict4.txt";
        final String sourceFile = "D:\\github\\sensitive-word\\src\\main\\resources\\_minganci.txt";

        String string = FileUtils.getFileContent(sourceFile);

        List<String> words = StringUtils.splitToList(string, "\\|");
        FileUtils.write(targetFile, words);
    }

    @Test
    @Ignore
    public void trimTest() {
        final String source = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
        List<String> lines = FileUtils.readAllLines(source);
        List<String> trimLines = CollectionUtils.distinct(CollectionUtils.trimCollection(lines));

        final String target = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";
    }

    /**
     * 获取对应文件的独一无二内容
     * @param name 名称
     * @return 结果
     */
    private List<String> distinctLines(final String name) {
        final String dir = "D:\\github\\sensitive-word\\src\\main\\resources\\";
        final String path = dir + name;
        List<String> lines = FileUtils.readAllLines(path);
        return CollectionUtils.distinct(lines);
    }

    @Test
    @Ignore
    public void stopWordTest() {
        final String source = "D:\\github\\sensitive-word\\src\\main\\resources\\stopword.txt";
        final String target = "D:\\github\\sensitive-word\\src\\main\\resources\\stopword_chars.txt";

        final List<String> lines = FileUtils.readAllLines(source);

        List<String> resultList = CollectionUtils.distinct(CollectionUtils.filterList(lines, new IFilter<String>() {
            @Override
            public boolean filter(String s) {
                return CharsetUtils.isContainsChinese(s);
            }
        }));
        Collections.sort(resultList);
        FileUtils.write(target, resultList);
    }

    @Test
    @Ignore
    public void dictAllInOneTest() {
        final List<String> allLines = distinctLines("dict.txt");

        allLines.addAll(distinctLines("不正当竞争.txt"));
        allLines.addAll(distinctLines("人名.txt"));
        allLines.addAll(distinctLines("其他.txt"));
        allLines.addAll(distinctLines("广告.txt"));
        allLines.addAll(distinctLines("房产.txt"));
        allLines.addAll(distinctLines("政治类.txt"));
        allLines.addAll(distinctLines("新闻实事.txt"));
        allLines.addAll(distinctLines("暴力.txt"));
        allLines.addAll(distinctLines("毒品.txt"));
        allLines.addAll(distinctLines("网址.txt"));
        allLines.addAll(distinctLines("色情类.txt"));
        allLines.addAll(distinctLines("辱骂.txt"));
        allLines.addAll(distinctLines("邪教.txt"));


        List<String> disctinct = CollectionUtils
                .distinct(CollectionUtils.trimCollection(allLines));

        Collections.sort(disctinct);

        final String target = "D:\\github\\sensitive-word\\src\\main\\resources\\dict.txt";

        FileUtils.write(target, disctinct);
    }

    @Test
    @Ignore
    public void oneWordTest() {
        final String source = "D:\\_github\\sensitive-word\\src\\main\\resources\\dict.txt";

        List<String> lines = FileUtils.readAllLines(source);
        for(int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if(line.trim().length() == 1) {
                System.out.println(i + " === " + line);
            }
        }
    }

}
