package com.taotao.cloud.workflow.biz.form.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;

import com.taotao.cloud.workflow.biz.engine.service.FlowTaskService;
import com.taotao.cloud.workflow.biz.form.entity.ApplyBanquetEntity;
import com.taotao.cloud.workflow.biz.form.mapper.ApplyBanquetMapper;
import com.taotao.cloud.workflow.biz.form.service.ApplyBanquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 宴请申请
 *
 */
@Service
public class ApplyBanquetServiceImpl extends ServiceImpl<ApplyBanquetMapper, ApplyBanquetEntity> implements ApplyBanquetService {

    @Autowired
    private BillRuleService billRuleService;
    @Autowired
    private FlowTaskService flowTaskService;

    @Override
    public ApplyBanquetEntity getInfo(String id) {
        QueryWrapper<ApplyBanquetEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ApplyBanquetEntity::getId, id);
        return this.getOne(queryWrapper);
    }

    @Override
    @DSTransactional
    public void save(String id, ApplyBanquetEntity entity) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleService.useBillNumber("WF_ApplyBanquetNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        ModelUtil.save(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity);
    }

    @Override
    @DSTransactional
    public void submit(String id, ApplyBanquetEntity entity, Map<String, List<String>> candidateList) throws WorkFlowException {
        //表单信息
        if (id == null) {
            entity.setId(RandomUtil.uuId());
            this.save(entity);
            billRuleService.useBillNumber("WF_ApplyBanquetNo");
        } else {
            entity.setId(id);
            this.updateById(entity);
        }
        //流程信息
        ModelUtil.submit(id, entity.getFlowId(), entity.getId(), entity.getFlowTitle(), entity.getFlowUrgent(), entity.getBillNo(), entity, null, candidateList);
    }

    @Override
    public void data(String id, String data) {
        ApplyBanquetForm applyBanquetForm = JsonUtil.getJsonToBean(data, ApplyBanquetForm.class);
        ApplyBanquetEntity entity = JsonUtil.getJsonToBean(applyBanquetForm, ApplyBanquetEntity.class);
        entity.setId(id);
        this.saveOrUpdate(entity);
    }
}
