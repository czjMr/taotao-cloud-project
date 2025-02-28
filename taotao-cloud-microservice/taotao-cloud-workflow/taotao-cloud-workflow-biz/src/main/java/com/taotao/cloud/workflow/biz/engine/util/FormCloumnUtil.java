package com.taotao.cloud.workflow.biz.engine.util;

import com.taotao.cloud.workflow.biz.model.FormAllModel;
import com.taotao.cloud.workflow.biz.model.FormColumnModel;
import com.taotao.cloud.workflow.biz.model.FormColumnTableModel;
import com.taotao.cloud.workflow.biz.model.FormEnum;
import com.taotao.cloud.workflow.biz.model.FormMastTableModel;
import com.taotao.cloud.workflow.biz.model.FormModel;
import com.taotao.cloud.workflow.biz.model.RecursionForm;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 在线工作流开发
 */
public class FormCloumnUtil {

    /**
     * 引擎递归
     **/
    public static void recursionForm(RecursionForm recursionForm, List<FormAllModel> formAllModel) {
        List<TableModel> tableModelList = recursionForm.getTableModelList();
        List<FieLdsModel> list = recursionForm.getList();
        for (FieLdsModel fieLdsModel : list) {
            FormAllModel start = new FormAllModel();
            FormAllModel end = new FormAllModel();
            ConfigModel config = fieLdsModel.getConfig();
            String jnpfkey = config.getJnpfKey();
            List<FieLdsModel> childrenList = config.getChildren();
            boolean isJnpfKey = StringUtil.isEmpty(jnpfkey);
            boolean isName = StringUtil.isNotEmpty(fieLdsModel.getName());
            if (FormEnum.row.getMessage().equals(jnpfkey) || FormEnum.card.getMessage().equals(jnpfkey)
                    || FormEnum.tab.getMessage().equals(jnpfkey) || FormEnum.collapse.getMessage().equals(jnpfkey)
                    || isJnpfKey) {
                String key = isJnpfKey ? isName ? FormEnum.collapse.getMessage() : FormEnum.tab.getMessage() : jnpfkey;
                //布局属性
                FormModel formModel = JsonUtil.getJsonToBean(fieLdsModel, FormModel.class);
                formModel.setSpan(config.getSpan());
                formModel.setActive(config.getActive());
                formModel.setChildNum(config.getChildNum());
                formModel.setModel(config.getModel());
                String outermost = !isJnpfKey ? "0" : "1";
                if (FormEnum.tab.getMessage().equals(key) || FormEnum.collapse.getMessage().equals(key)) {
                    if (!isJnpfKey) {
                        String chidModel = "active" + RandomUtil.enUuid();
                        formModel.setModel(chidModel);
                        for (int i = 0; i < childrenList.size(); i++) {
                            FieLdsModel childModel = childrenList.get(i);
                            ConfigModel childConfig = childModel.getConfig();
                            childConfig.setModel(chidModel);
                            childConfig.setChildNum(i);
                            childModel.setConfig(childConfig);
                        }
                        formModel.setChildren(childrenList);
                    }
                    formModel.setOutermost(outermost);
                }
                start.setJnpfKey(key);
                start.setFormModel(formModel);
                formAllModel.add(start);
                RecursionForm recursion = new RecursionForm(childrenList, tableModelList);
                recursionForm(recursion, formAllModel);
                end.setIsEnd("1");
                end.setJnpfKey(key);
                //折叠、标签的判断里层还是外层
                FormModel endFormModel = new FormModel();
                endFormModel.setOutermost(outermost);
                end.setFormModel(endFormModel);
                formAllModel.add(end);
            } else if (FormEnum.table.getMessage().equals(jnpfkey)) {
                tableModel(fieLdsModel, formAllModel);
            } else if (FormEnum.isModel(jnpfkey)) {
                FormModel formModel = JsonUtil.getJsonToBean(fieLdsModel, FormModel.class);
                start.setJnpfKey(jnpfkey);
                start.setFormModel(formModel);
                formAllModel.add(start);
            } else {
                model(fieLdsModel, formAllModel, tableModelList);
            }
        }
        for (FormAllModel formModel : formAllModel) {
            if (FormEnum.mast.getMessage().equals(formModel.getJnpfKey())) {
                String jnpfkey = formModel.getFormColumnModel().getFieLdsModel().getConfig().getJnpfKey();
                if (FormEnum.relationFormAttr.getMessage().equals(jnpfkey) || FormEnum.popupAttr.getMessage().equals(jnpfkey)) {
                    List<FieLdsModel> partenList = new ArrayList<>();
                    partenList.addAll(formAllModel.stream().filter(t -> t.getFormColumnModel() != null).map(t -> t.getFormColumnModel().getFieLdsModel()).collect(Collectors.toList()));
                    partenList.addAll(formAllModel.stream().filter(t -> t.getFormMastTableModel() != null).map(t -> t.getFormMastTableModel().getMastTable().getFieLdsModel()).collect(Collectors.toList()));
                    String relationField = formModel.getFormColumnModel().getFieLdsModel().getRelationField().split("_jnpfTable_")[0];
                    FieLdsModel parten = partenList.stream().filter(t -> relationField.equals(t.getVModel())).findFirst().orElse(null);
                    if (parten != null) {
                        formModel.getFormColumnModel().getFieLdsModel().setInterfaceId(parten.getInterfaceId());
                        formModel.getFormColumnModel().getFieLdsModel().setModelId(parten.getModelId());
                        formModel.getFormColumnModel().getFieLdsModel().setPropsValue(parten.getPropsValue());
                        formModel.getFormColumnModel().getFieLdsModel().setRelationField(parten.getVModel());
                    }
                }
            }
        }
    }

    /**
     * 主表属性添加
     **/
    private static void model(FieLdsModel fieLdsModel, List<FormAllModel> formAllModel, List<TableModel> tableModelList) {
        FormColumnModel mastModel = formModel(fieLdsModel);
        FormAllModel formModel = new FormAllModel();
        formModel.setJnpfKey(FormEnum.mast.getMessage());
        formModel.setFormColumnModel(mastModel);
        if (tableModelList.size() > 0) {
            TableModel tableModel = tableModelList.stream().filter(t -> t.getTable().equals(fieLdsModel.getConfig().getTableName())).findFirst().orElse(null);
            if (tableModel == null) {
                Optional<TableModel> first = tableModelList.stream().filter(t -> "1".equals(t.getTypeId())).findFirst();
                if (first.isPresent()) {
                    tableModel = first.get();
                }
            }
            String type = tableModel.getTypeId();
            if ("1".equals(type)) {
                mastModel.getFieLdsModel().getConfig().setTableName(tableModel.getTable());
                formModel.setFormColumnModel(mastModel);
                formAllModel.add(formModel);
            } else {
                mastTable(tableModel, fieLdsModel, formAllModel);
            }
        } else {
            formAllModel.add(formModel);
        }
    }

    /**
     * 主表的属性是子表字段
     */
    private static void mastTable(TableModel tableModel, FieLdsModel fieLdsModel, List<FormAllModel> formAllModel) {
        FormMastTableModel childModel = new FormMastTableModel();
        String vModel = fieLdsModel.getVModel();
        List<TableFields> tableFieldsList = tableModel.getFields();
        String mastKey = "jnpf_" + tableModel.getTable() + "_jnpf_";
        TableFields tableFields = tableFieldsList.stream().filter(t -> StringUtil.isNotEmpty(vModel) && vModel.equals(mastKey + t.getField())).findFirst().orElse(null);
        FormAllModel formModel = new FormAllModel();
        formModel.setJnpfKey(FormEnum.mastTable.getMessage());
        if (tableFields != null) {
            childModel.setTable(tableModel.getTable());
            formModel.setFormMastTableModel(childModel);
            childModel.setField(tableFields.getField());
            childModel.setVModel(vModel);
        }
        FormColumnModel mastTable = formModel(fieLdsModel);
        childModel.setMastTable(mastTable);
        formAllModel.add(formModel);
    }

    /**
     * 子表表属性添加
     **/
    private static void tableModel(FieLdsModel model, List<FormAllModel> formAllModel) {
        FormColumnTableModel tableModel = new FormColumnTableModel();
        List<FormColumnModel> childList = new ArrayList<>();
        ConfigModel config = model.getConfig();
        List<FieLdsModel> childModelList = config.getChildren();
        String table = model.getVModel();
        List<String> summaryField = StringUtil.isNotEmpty(model.getSummaryField()) ? JsonUtil.getJsonToList(model.getSummaryField(), String.class) : new ArrayList<>();
        Map<String, String> summaryName = new HashMap<>();
        for (FieLdsModel childmodel : childModelList) {
            if (childmodel.getProps() != null) {
                PropsBeanModel beanModel = JsonUtil.getJsonToBean(childmodel.getProps().getProps(), PropsBeanModel.class);
                PropsModel propsModel = new PropsModel();
                propsModel.setProps(childmodel.getProps().getProps());
                propsModel.setPropsModel(beanModel);
                childmodel.setProps(propsModel);
            }
            FormColumnModel childModel = formModel(childmodel);
            boolean isSummary = summaryField.contains(childmodel.getVModel());
            if (isSummary) {
                summaryName.put(childmodel.getVModel(), childmodel.getConfig().getLabel());
            }
            relationModel(childModelList, childmodel);
            childList.add(childModel);
        }
        tableModel.setLabel(config.getLabel());
        tableModel.setShowTitle(config.getShowTitle());
        tableModel.setActionText(StringUtil.isNotEmpty(model.getActionText()) ? model.getActionText() : "新增");
        tableModel.setSpan(config.getSpan());
        tableModel.setTableModel(table);
        tableModel.setChildList(childList);
        tableModel.setTableName(config.getTableName());
        tableModel.setShowSummary(model.getShowSummary());
        tableModel.setSummaryField(JsonUtil.getObjectToString(summaryField));
        tableModel.setSummaryFieldName(JsonUtil.getObjectToString(summaryName));
        FormAllModel formModel = new FormAllModel();
        formModel.setJnpfKey(FormEnum.table.getMessage());
        formModel.setChildList(tableModel);
        formAllModel.add(formModel);
    }

    private static void relationModel(List<FieLdsModel> childModelList, FieLdsModel childmodel) {
        String jnpfkey = childmodel.getConfig().getJnpfKey();
        if (FormEnum.relationFormAttr.getMessage().equals(jnpfkey) || FormEnum.popupAttr.getMessage().equals(jnpfkey)) {
            String relationField = childmodel.getRelationField().split("_jnpfTable_")[0];
            FieLdsModel child = childModelList.stream().filter(t -> relationField.equals(t.getVModel())).findFirst().orElse(null);
            if (child != null) {
                childmodel.setInterfaceId(child.getInterfaceId());
                childmodel.setModelId(child.getModelId());
                childmodel.setPropsValue(child.getPropsValue());
                childmodel.setRelationField(relationField);
            }
        }
    }

    /**
     * 属性赋值
     **/
    private static FormColumnModel formModel(FieLdsModel model) {
        ConfigModel configModel = model.getConfig();
        if (configModel.getDefaultValue() instanceof String) {
            configModel.setValueType("String");
        }
        if (configModel.getDefaultValue() == null) {
            configModel.setValueType("undefined");
        }
        FormColumnModel formColumnModel = new FormColumnModel();
        //级联判断多选还是单选
        if (JnpfKeyConsts.CASCADER.equals(configModel.getJnpfKey())) {
            PropsBeanModel propsMap = JsonUtil.getJsonToBean(model.getProps().getProps(), PropsBeanModel.class);
            model.setMultiple(propsMap.getMultiple());
        }
        formColumnModel.setFieLdsModel(model);
        return formColumnModel;
    }

    /**
     * 判断重复子表
     *
     * @return
     */
    public static boolean repetition(RecursionForm recursionForm, List<FormAllModel> formAllModel) {
        boolean flag = false;
        List<TableModel> tableModelList = recursionForm.getTableModelList();
        recursionForm(recursionForm, formAllModel);
        if (tableModelList.size() > 0) {
            List<FormAllModel> tables = formAllModel.stream().filter(t -> FormEnum.table.getMessage().equals(t.getJnpfKey())).collect(Collectors.toList());
            List<FormAllModel> mastTable = formAllModel.stream().filter(t -> FormEnum.mastTable.getMessage().equals(t.getJnpfKey())).collect(Collectors.toList());
            List<String> tableList = tables.stream().map(t -> t.getChildList().getTableName()).collect(Collectors.toList());
            List<String> mastTableList = mastTable.stream().map(t -> t.getFormMastTableModel().getTable()).collect(Collectors.toList());
            flag = tableList.stream().filter(item -> mastTableList.contains(item)).count() > 0;
        }
        return flag;
    }


}
