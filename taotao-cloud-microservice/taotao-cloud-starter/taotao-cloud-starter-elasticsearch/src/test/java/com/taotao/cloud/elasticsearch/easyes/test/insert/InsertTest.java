package com.taotao.cloud.elasticsearch.easyes.test.insert;

import com.taotao.cloud.elasticsearch.easyes.sample.entity.Document;
import com.taotao.cloud.elasticsearch.easyes.sample.entity.Faq;
import com.taotao.cloud.elasticsearch.easyes.sample.entity.User;
import com.taotao.cloud.elasticsearch.easyes.sample.mapper.DocumentMapper;
import com.taotao.cloud.elasticsearch.easyes.test.TestEasyEsApplication;
import org.elasticsearch.geometry.Point;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 插入测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestEasyEsApplication.class)
public class InsertTest {
    @Resource
    private DocumentMapper documentMapper;

    @Test
    public void testInsert() {
        // 测试插入数据
        Document document = new Document();
        document.setId("3");
        document.setTitle("老汉");
        document.setContent("人才");
        document.setCreator("吃饭");
        document.setLocation("40.171975,116.587105");
        document.setGmtCreate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        document.setCustomField("俄罗斯方块");
        Point point = new Point(13.400544, 52.530286);
        document.setGeoLocation(point.toString());
        document.setStarNum(1);
        List<User> users = new ArrayList<>();
        Set<Faq> faqs = new HashSet<>();
        faqs.add(new Faq("问题1", "回答1"));
        faqs.add(new Faq("问题2", "回答2"));

        Set<Faq> faqs1 = new HashSet<>();
        faqs1.add(new Faq("问题3","回答3"));
        faqs1.add(new Faq("问题4","回答4"));
        users.add(new User("用户1", 18, faqs));
        users.add(new User("用户2", 19, faqs1));
        document.setUsers(users);
        int successCount = documentMapper.insert(document);
        Assert.assertEquals(successCount, 1);
    }

    @Test
    public void testInsertBatch() {
        List<Document> documentList = new ArrayList<>();
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Document document = new Document();
        document.setId("2");
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        document.setCreator("隔壁老王");


        document.setGmtCreate(now);
        document.setCustomField("乌拉巴拉小魔仙");
        document.setLocation("40.17836693398477,116.64002551005981");

        Document document1 = new Document();
        document1.setId("3");
        document1.setTitle("老汉");
        document1.setContent("推*技术过硬");
        document1.setCreator("隔壁老王");
        document1.setGmtCreate(now);
        document1.setCustomField("魔鬼的步伐");
        document1.setLocation("40.19103839805197,116.5624013764374");


        Document document2 = new Document();
        document2.setId("4");
        document2.setTitle("小伙子");
        document2.setContent("推*技术过硬");
        document2.setCreator("大猪蹄子");
        document2.setGmtCreate(now);
        document2.setCustomField("锤子科技");
        document2.setLocation("40.13933715136454,116.63441990026217");

        documentList.add(document);
        documentList.add(document1);
        documentList.add(document2);

        int successCount = documentMapper.insertBatch(documentList);
        System.out.println(successCount);
        // id可以直接从被插入的数据中取出
        documentList.forEach(System.out::println);
    }

    @Test
    public void testInsertWithCustomizeId() {
        // 测试用户自行指定id新增,测试前必须先把注解@TableId中的type指定为IdType.CUSTOMIZE 或在配置文件yml中指定
        String id = "muscle";
        Document document = new Document();
        document.setId(id);
        document.setTitle("测试用户自定义id");
        document.setContent("测试用户自己指定id,如果es中已存在该id就更新该数据,不存在时才新增");
        int successCount = documentMapper.insert(document);
        System.out.println(successCount);

        // id保持不变,只改内容,会自动更新此id对应的数据 此方法是Insert,所以成功条目数返回为0
        document.setTitle("我被更新了1");
        documentMapper.insert(document);
        Document document1 = documentMapper.selectById(id);
        System.out.println("更新后的数据:" + document1);
    }

}
