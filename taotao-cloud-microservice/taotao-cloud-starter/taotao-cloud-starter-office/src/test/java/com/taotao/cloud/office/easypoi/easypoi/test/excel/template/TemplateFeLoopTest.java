package com.taotao.cloud.office.easypoi.easypoi.test.excel.template;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.taotao.cloud.office.easypoi.easypoi.test.excel.export.ExcelExportOneToManyHaseNameTest;
import com.taotao.cloud.office.easypoi.easypoi.test.excel.handler.ExcelDictHandlerImpl;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

/**
 * 模板多层循环
 *
 * @author by jueyue on 19-9-26.
 */
public class TemplateFeLoopTest {

	@Test
	public void nestedLoopTest() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", ExcelExportOneToManyHaseNameTest.getList());
			TemplateExportParams params = new TemplateExportParams(
				"doc/nestedloop.xlsx");
			params.setDictHandler(new ExcelDictHandlerImpl());
			Workbook workbook = ExcelExportUtil.exportExcel(params, map);
			FileOutputStream fos = new FileOutputStream(
				"D:/home/excel/TemplateFeLoopTest.nestedLoopTest.xlsx");
			workbook.write(fos);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
