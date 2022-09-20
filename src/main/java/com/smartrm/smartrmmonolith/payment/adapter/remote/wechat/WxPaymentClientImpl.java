package com.smartrm.smartrmmonolith.payment.adapter.remote.wechat;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.exception.DomainException;
import com.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmmonolith.payment.application.remote.PaymentPlatformClient;
import com.smartrm.smartrmmonolith.payment.domain.Payment;
import com.smartrm.smartrmmonolith.payment.infrastructure.PaymentError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yoda
 * @description:
 */
@Component
public class WxPaymentClientImpl implements PaymentPlatformClient {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPaymentClientImpl.class);
    
    @Autowired
    private WXPayConfig wxPayConfig;
    
    @Value("${wechat.pay.notifyUrl}")
    private String notifyUrl;
    
    @Override
    public PaymentQrCodeDto queryQrCode(Payment payment) {
        WXPay pay = new WXPay(wxPayConfig);
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "smartrm货道售卖机商品");
        data.put("out_trade_no", Long.toHexString(payment.getOrder().getOrderId()));
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        data.put("total_fee", payment.getOrder().getTotalAmount().toPlainString());
        try {
            data.put("spbill_create_ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            throw new DomainException(CommonError.FailToGetLocalIp);
        }
        data.put("notify_url", notifyUrl);
        data.put("trade_type", "NATIVE");
        
        try {
            Map<String, String> resp = pay.unifiedOrder(data);
            if (resp.get("return_code").equals("SUCCESS") && resp.get("result_code").equals("SUCCESS")) {
                return new PaymentQrCodeDto(payment.getPaymentId(), resp.get("code_url"));
            } else {
                LOGGER.error("failed," + resp.get("return_code") + "," + resp.get("result_code"));
            }
        } catch (Exception e) {
            LOGGER.error("fail to pay on wechat platform.", e);
            throw new DomainException(PaymentError.WechatPayRequestFail);
        }
        throw new DomainException(PaymentError.WechatPayRequestFail);
    }
    
    @Override
    public void requestForDeduction(Payment payment) {
        //TODO: 调用微信扣款接口
    }
    
    @Override
    public void requestForRefund(Payment payment) {
        //TODO: 调用微信退款接口
    }
}
