package com.taotao.cloud.workflow.biz.form.model.violationhandling;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 违章处理申请表
 *
 */
@Data
public class ViolationHandlingForm {
    @NotNull(message = "紧急程度不能为空")
    @Schema(description = "紧急程度")
    private Integer flowUrgent;
    @Schema(description = "违章地点")
    private String violationSite;
    @Schema(description = "备注")
    private String description;
    @Schema(description = "车牌号")
    private String plateNum;
    @Schema(description = "负责人")
    private String leadingOfficial;
    @NotNull(message = "违章日期不能为空")
    @Schema(description = "违章日期")
    private Long  peccancyDate;
    @NotBlank(message = "流程标题不能为空")
    @Schema(description = "流程标题")
    private String flowTitle;
    @Schema(description = "驾驶人")
    private String driver;
    @Schema(description = "违章扣分")
    private String deduction;
    @NotNull(message = "通知日期不能为空")
    @Schema(description = "通知日期")
    private Long  noticeDate;
    @NotNull(message = "限处理日期不能为空")
    @Schema(description = "限处理日期")
    private Long  limitDate;
    @Schema(description = "违章行为")
    private String violationBehavior;
    @Schema(description = "违章罚款")
    private BigDecimal amountMoney;
    @NotBlank(message = "流程主键不能为空")
    @Schema(description = "流程主键")
    private String flowId;
    @NotBlank(message = "流程单据不能为空")
    @Schema(description = "流程单据")
    private String billNo;
    @Schema(description = "提交/保存 0-1")
    private String status;
    @Schema(description = "候选人")
    private Map<String, List<String>> candidateList;

}
