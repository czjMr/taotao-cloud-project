package com.taotao.cloud.office.easypoi.easypoi.test.excel.export;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.taotao.cloud.office.easypoi.test.en.EnumDataEntity;
import com.taotao.cloud.office.easypoi.test.en.Sex;
import com.taotao.cloud.office.easypoi.test.en.StatusEnum;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;

/**
 * @author by jueyue on 18-4-2.
 */
public class ExcelExportEnumTest {

	@Test
	public void test() throws Exception {

		List<EnumDataEntity> list = new ArrayList<EnumDataEntity>();
		for (int i = 0; i < 100; i++) {
			EnumDataEntity client = new EnumDataEntity();
			client.setName("小明" + i);
			client.setSex(Sex.MAN);
			client.setStatus(StatusEnum.Init);
			client.setBaseStatus(StatusEnum.Ready);
			list.add(client);
		}
		Date start = new Date();
		ExportParams params = new ExportParams("枚举测试", "测试", ExcelType.XSSF);
		Workbook workbook = ExcelExportUtil.exportExcel(params, EnumDataEntity.class, list);
		System.out.println(new Date().getTime() - start.getTime());
		FileOutputStream fos = new FileOutputStream("D:/home/excel/EnumDataEntity.xlsx");
		workbook.write(fos);
		fos.close();
	}
}
