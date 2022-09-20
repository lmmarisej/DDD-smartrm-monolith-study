package com.smartrm.smartrmmonolith.payment.adapter.remote.wechat;

import com.github.wxpay.sdk.WXPayConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yoda
 * @description: 微信支付配置
 */
@Configuration
public class WechatPayConfig implements WXPayConfig {
    
    @Value("${wechat.pay.appId}")
    private String appId;
    
    @Value("${wechat.pay.mchId}")
    private String mchId;
    
    @Value("${wechat.pay.key}")
    private String key;
    
    @Value("${wechat.pay.certPath}")
    private String certPath;
    
    private byte[] certData;
    
    @PostConstruct
    public void init() throws IOException {
//    File file = new File(certPath);
//    InputStream certStream = new FileInputStream(file);
//    this.certData = new byte[(int) file.length()];
//    certStream.read(this.certData);
//    certStream.close();
    }
    
    @Override
    public String getAppID() {
        return appId;
    }
    
    @Override
    public String getMchID() {
        return mchId;
    }
    
    @Override
    public String getKey() {
        return key;
    }
    
    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }
    
    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
    
    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
