package com.taotao.cloud.office.easypoi.easypoi.test.excel.template;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.taotao.cloud.office.easypoi.test.entity.CourseEntity;
import com.taotao.cloud.office.easypoi.test.entity.TeacherEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class ExcelExportTemplateTest {

	List<CourseEntity> list = new ArrayList<CourseEntity>();
	CourseEntity courseEntity;

	@Test
	public void one() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
			"doc/exportTemp.xls", true);
		params.setHeadingRows(2);
		params.setHeadingStartRow(2);
		params.setStyle(ExcelStyleType.BORDER.getClazz());
		Map<String, Object> map = new HashMap<String, Object>();
		//sheet 1
		map.put("year", "2013");
		map.put("sunCourses", list.size());
		Map<String, Object> obj = new HashMap<String, Object>();
		map.put("obj", obj);
		obj.put("name", list.size());
		// sheet 2
		map.put("month", 10);
		Map<String, Object> temp;
		for (int i = 1; i < 8; i++) {
			temp = new HashMap<String, Object>();
			temp.put("per", i * 10);
			temp.put("mon", i * 1000);
			temp.put("summon", i * 10000);
			map.put("i" + i, temp);
		}
		Workbook book = ExcelExportUtil.exportExcel(params, CourseEntity.class, list, map);
		File savefile = new File("D:/home/excel/");
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream("D:/home/excel/exportTemp.xls");
		book.write(fos);
		fos.close();

	}

	@Before
	public void testBefore() {
		courseEntity = new CourseEntity();
		courseEntity.setId("1131");
		courseEntity.setName("小白");

		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setId("12131231");
		teacherEntity.setName("你们");
		courseEntity.setChineseTeacher(teacherEntity);

		teacherEntity = new TeacherEntity();
		teacherEntity.setId("121312314312421131");
		teacherEntity.setName("老王");
		courseEntity.setMathTeacher(teacherEntity);

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setId("11231");
		studentEntity.setName("撒旦法司法局");
		studentEntity.setBirthday(new Date());
		studentEntity.setSex(1);
		List<StudentEntity> studentList = new ArrayList<StudentEntity>();
		studentList.add(studentEntity);
		studentList.add(studentEntity);
		courseEntity.setStudents(studentList);

		for (int i = 0; i < 3; i++) {
			list.add(courseEntity);
		}
	}

	@Test
	public void two() throws Exception {
		TemplateExportParams params = new TemplateExportParams(
			"doc/exportTemp.xls", 1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", 10);
		Map<String, Object> temp;
		for (int i = 1; i < 8; i++) {
			temp = new HashMap<String, Object>();
			temp.put("per", i * 10);
			temp.put("mon", i * 1000);
			temp.put("summon", i * 10000);
			map.put("i" + i, temp);
		}
		Workbook book = ExcelExportUtil.exportExcel(params, map);
		File savefile = new File("D:/home/excel/");
		if (!savefile.exists()) {
			savefile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream("D:/home/excel/exportTemp2.xls");
		book.write(fos);
		fos.close();

	}
}
