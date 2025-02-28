/*
 * Copyright (c) 2021-2031, 河北计全科技有限公司 (https://www.jeequan.com & jeequan@126.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.payment.biz.jeepay.core.model.params;

import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.payment.biz.jeepay.core.constants.CS;
import com.taotao.cloud.payment.biz.jeepay.core.model.params.alipay.AlipayIsvParams;
import com.taotao.cloud.payment.biz.jeepay.core.model.params.wxpay.WxpayIsvParams;
import com.taotao.cloud.payment.biz.jeepay.core.model.params.ysf.YsfpayIsvParams;

/**
 * 抽象类 isv参数定义
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/8 16:33
 * @modify ZhuXiao
 */
public abstract class IsvParams {

    public static IsvParams factory(String ifCode, String paramsStr){

        if(CS.IF_CODE.WXPAY.equals(ifCode)){
            return JSONObject.parseObject(paramsStr, WxpayIsvParams.class);
        }else if(CS.IF_CODE.ALIPAY.equals(ifCode)){
            return JSONObject.parseObject(paramsStr, AlipayIsvParams.class);
        }else if(CS.IF_CODE.YSFPAY.equals(ifCode)){
            return JSONObject.parseObject(paramsStr, YsfpayIsvParams.class);
        }
        return null;
    }

    /**
     *  敏感数据脱敏
    */
    public abstract String deSenData();

}
