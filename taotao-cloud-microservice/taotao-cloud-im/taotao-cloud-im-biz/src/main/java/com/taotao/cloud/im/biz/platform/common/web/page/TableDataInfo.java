package com.taotao.cloud.im.biz.platform.common.web.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 */
@Data
@Accessors(chain = true) // 链式调用
public class TableDataInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 消息状态码
     */
    private int code;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

}
