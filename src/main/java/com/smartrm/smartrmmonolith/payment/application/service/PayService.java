package com.smartrm.smartrmmonolith.payment.application.service;

import com.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmmonolith.payment.application.dto.PlatformPaymentResultDto;
import com.smartrm.smartrmmonolith.payment.domain.AccountInfo;
import com.smartrm.smartrmmonolith.payment.domain.OrderInfo;
import com.smartrm.smartrmmonolith.payment.domain.PlatformType;

/**
 * @author: yoda
 * @description: 支付上下文应用层服务
 */
public interface PayService {
    
    /**
     * 开始扫码支付
     *
     * @param platformType 平台类型，目前固定为微信
     * @param order        订单信息
     * @return 支付二维码
     */
    PaymentQrCodeDto startQrCodePayForOrder(PlatformType platformType, OrderInfo order);
    
    /**
     * 免密扣款
     *
     * @param order      订单信息
     * @param accountDto 账号信息
     */
    void chargeForOrder(OrderInfo order, AccountInfo accountDto);
    
    /**
     * 退款结果通知处理
     *
     * @param refundResult 退款结果
     */
    void onRefundResult(PlatformPaymentResultDto refundResult);
    
    /**
     * 支付结果通知处理
     *
     * @param paymentResult 支付结果
     */
    void onPaymentResult(PlatformPaymentResultDto paymentResult);
    
    /**
     * 订单取消处理
     *
     * @param order 订单信息
     */
    void onOrderCanceled(OrderInfo order);
    
}
