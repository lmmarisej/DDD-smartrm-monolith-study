package com.smartrm.smartrmmonolith.user.adapter.wxplatform.client.impl;

import com.smartrm.smartrmmonolith.user.application.client.WxPlatformClient;
import com.smartrm.smartrmmonolith.user.application.dto.WxCode2SessionResultDto;
import org.springframework.stereotype.Component;

/**
 * @author: yoda
 * @description: 模拟微信开放平台接口响应
 */
@Component
public class MockWxPlatformClientImpl implements WxPlatformClient {
    
    @Override
    public WxCode2SessionResultDto code2Session(String code) {
        WxCode2SessionResultDto ret = new WxCode2SessionResultDto();
        ret.setSessionKey("session_key_" + code);
        ret.setOpenId("open_id_" + code);
        ret.setErrcode(0);
        ret.setErrmsg("");
        return ret;
    }
}
