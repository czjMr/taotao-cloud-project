package com.taotao.cloud.office.easyexecl.core.head;

import com.alibaba.excel.annotation.ExcelProperty;



/**

 */
public class NoHeadData {
    @ExcelProperty("字符串")
    private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
