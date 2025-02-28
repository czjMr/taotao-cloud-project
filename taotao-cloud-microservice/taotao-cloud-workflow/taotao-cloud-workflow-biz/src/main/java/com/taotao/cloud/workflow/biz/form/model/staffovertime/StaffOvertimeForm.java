package com.taotao.cloud.workflow.biz.form.model.staffovertime;


import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 员工加班申请表
 */
@Data
public class StaffOvertimeForm {
    @NotBlank(message = "申请人不能为空")
    @Schema(description = "申请人")
    private String applyUser;
    @NotBlank(message = "流程标题不能为空")
    @Schema(description = "流程标题")
    private String flowTitle;
    @NotNull(message = "紧急程度不能为空")
    @Schema(description = "紧急程度")
    private Integer flowUrgent;
    @Schema(description = "总计时间")
    private String totleTime;
    @Schema(description = "加班事由")
    private String cause;
    @NotNull(message = "开始时间不能为空")
    @Schema(description = "开始时间")
    private Long startTime;
    @NotNull(message = "结束时间不能为空")
    @Schema(description = "结束时间")
    private Long endTime;
    @NotNull(message = "申请日期不能为空")
    @Schema(description = "申请日期")
    private Long applyDate;
    @NotBlank(message = "申请部门不能为空")
    @Schema(description = "申请部门")
    private String department;
    @NotBlank(message = "记入类别不能为空")
    @Schema(description = "记入类别")
    private String category;
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
