package com.taotao.cloud.workflow.biz.engine.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.taotao.cloud.workflow.biz.engine.entity.FlowEngineEntity;
import com.taotao.cloud.workflow.biz.engine.entity.FlowEngineVisibleEntity;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.FlowEngineListVO;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.FlowExportModel;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.FlowPagination;
import com.taotao.cloud.workflow.biz.engine.model.flowengine.PaginationFlowEngine;

import java.util.List;

/**
 * 流程引擎
 *
 */
public interface FlowEngineService extends IService<FlowEngineEntity> {

    /**
     * 分页列表
     *
     * @param pagination 分页
     * @return
     */
    List<FlowEngineEntity> getPageList(FlowPagination pagination);

    /**
     * 不分页数据
     *
     * @param pagination 分页
     * @return
     */
    List<FlowEngineEntity> getList(PaginationFlowEngine pagination);

    /**
     * 列表
     *
     * @return
     */
    List<FlowEngineEntity> getList();

    /**
     * 列表
     *
     * @param pagination 分页对象
     * @param isPage     是否分页
     * @return
     */
    List<FlowEngineEntity> getListAll(FlowPagination pagination, boolean isPage);

    /**
     * 列表
     *
     * @return
     */
    List<FlowEngineEntity> getFlowFormList();

    /**
     * 获取发起全部可见的引擎
     * @return
     */
    List<FlowEngineEntity> getFlowFormTypeList();

    /**
     * 查询引擎
     *
     * @param id 主键值
     * @return
     */
    List<FlowEngineEntity> getFlowList(List<String> id);

    /**
     * 信息
     *
     * @param id 主键值
     * @return
     * @throws WorkFlowException 异常
     */
    FlowEngineEntity getInfo(String id) throws WorkFlowException;

    /**
     * 信息
     *
     * @param enCode 流程编码
     * @return
     * @throws WorkFlowException 异常
     */
    FlowEngineEntity getInfoByEnCode(String enCode) throws WorkFlowException;

    /**
     * 验证名称
     *
     * @param fullName 名称
     * @param id       主键值
     * @return
     */
    boolean isExistByFullName(String fullName, String id);

    /**
     * 验证编码
     *
     * @param enCode 编码
     * @param id     主键值
     * @return
     */
    boolean isExistByEnCode(String enCode, String id);

    /**
     * 删除
     *
     * @param entity 实体对象
     */
    void delete(FlowEngineEntity entity) throws WorkFlowException;

    /**
     * 创建
     *
     * @param entity 实体对象
     */
    void create(FlowEngineEntity entity);

    /**
     * 创建
     *
     * @param entity 实体对象
     */
    void copy(FlowEngineEntity entity) throws WorkFlowException;

    /**
     * 更新
     *
     * @param id     主键值
     * @param entity 实体对象
     * @return
     */
    boolean updateVisible(String id, FlowEngineEntity entity) throws WorkFlowException;

    /**
     * 更新
     *
     * @param id     主键值
     * @param entity 实体对象
     */
    void update(String id, FlowEngineEntity entity) throws WorkFlowException;

    /**
     * 上移
     *
     * @param id 主键值
     * @return
     */
    boolean first(String id);

    /**
     * 下移
     *
     * @param id 主键值
     * @return
     */
    boolean next(String id);

    /**
     * 流程设计列表
     *
     * @param pagination
     * @param isList
     * @return
     */
    List<FlowEngineListVO> getTreeList(PaginationFlowEngine pagination, boolean isList);

    /**
     * 导入创建
     *
     * @param id 导出主键
     */
    FlowExportModel exportData(String id) throws WorkFlowException;

    /**
     * 工作流导入
     *
     * @param entity      实体对象
     * @param visibleList 可见
     * @return
     * @throws WorkFlowException
     */
    Result ImportData(FlowEngineEntity entity, List<FlowEngineVisibleEntity> visibleList) throws WorkFlowException;
}
