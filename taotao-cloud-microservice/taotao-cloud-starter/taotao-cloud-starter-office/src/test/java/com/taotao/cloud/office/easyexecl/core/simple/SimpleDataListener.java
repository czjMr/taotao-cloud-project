package com.taotao.cloud.office.easyexecl.core.simple;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

 */
public class SimpleDataListener extends AnalysisEventListener<SimpleData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleDataListener.class);
    List<SimpleData> list = new ArrayList<SimpleData>();

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        LOGGER.debug("Head is:{}", JSON.toJSONString(headMap));
        Assert.assertEquals(headMap.get(0), "姓名");
    }

    @Override
    public void invoke(SimpleData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Assert.assertEquals(list.size(), 10);
        Assert.assertEquals(list.get(0).getName(), "姓名0");
        Assert.assertEquals((int)(context.readSheetHolder().getSheetNo()), 0);
        Assert.assertEquals(
            context.readSheetHolder().getExcelReadHeadProperty().getHeadMap().get(0).getHeadNameList().get(0), "姓名");
        LOGGER.debug("First row:{}", JSON.toJSONString(list.get(0)));
    }
}
