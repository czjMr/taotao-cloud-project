package com.taotao.cloud.wechat.biz.module.cp.handler;

import com.taotao.cloud.wechat.biz.module.cp.utils.JsonUtils;
import java.util.Map;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.message.WxCpXmlOutMessage;

/**
 *  @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class LogHandler extends AbstractHandler {
  @Override
  public WxCpXmlOutMessage handle(WxCpXmlMessage wxMessage, Map<String, Object> context, WxCpService cpService,
                                  WxSessionManager sessionManager) {
    this.logger.info("\n接收到请求消息，内容：{}", JsonUtils.toJson(wxMessage));
    return null;
  }

}
