package com.taotao.cloud.workflow.biz.engine.model.flowtask.method;

import com.taotao.cloud.workflow.biz.engine.entity.FlowTaskEntity;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.FlowModel;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.shuntjson.nodejson.ChildNodeList;
import lombok.Data;


/**
 *
 *
 */
@Data
public class TaskOperator {
    /**当前节点数据*/
    private ChildNodeList childNode;
    /**引擎实例*/
    private FlowTaskEntity taskEntity;
    /**提交数据*/
    private FlowModel flowModel;
    /**true记录 false不记录*/
    private Boolean details;
    /**经办id*/
    private String id;
}
