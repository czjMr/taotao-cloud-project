package com.taotao.cloud.payment.biz.bootx.core.pay.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxm
* @date 2021/5/24
*/
@Data
@Accessors(chain = true)
@Schema(title = "异常信息")
public class ExceptionInfo {
    /** 错误码 */
    private int errorCode;
    /** 错误信息 */
    private String errorMsg;

    public ExceptionInfo(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
