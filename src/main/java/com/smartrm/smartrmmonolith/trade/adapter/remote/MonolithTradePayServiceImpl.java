package com.smartrm.smartrmmonolith.trade.adapter.remote;

import com.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmmonolith.payment.domain.AccountInfo;
import com.smartrm.smartrmmonolith.payment.domain.OrderInfo;
import com.smartrm.smartrmmonolith.payment.domain.PlatformType;
import com.smartrm.smartrmmonolith.trade.domain.Order;
import com.smartrm.smartrmmonolith.trade.domain.PaymentQrCode;
import com.smartrm.smartrmmonolith.trade.domain.remote.TradePayService;
import com.smartrm.smartrmmonolith.trade.domain.remote.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yoda
 * @description:
 */
@Service
public class MonolithTradePayServiceImpl implements TradePayService {
    
    @Autowired
    com.smartrm.smartrmmonolith.payment.application.service.PayService payService;
    
    
    @Override
    public PaymentQrCode startQrCodePayForOrder(PlatformType platformType, Order order) {
        PaymentQrCodeDto dto = payService
                .startQrCodePayForOrder(
                        platformType,
                        OrderInfo.Builder()
                                .machineId(order.getMachineId())
                                .orderId(order.getOrderId())
                                .type(order.getType())
                                .totalAmount(order.totalAmount())
                                .build()
                );
//    order.setPaymentId(dto.getPaymentId());
        return new PaymentQrCode(dto.getPaymentId(), dto.getCodeUrl());
    }
    
    @Override
    public void chargeForOrder(OrderInfo order, UserInfo userInfo) {
        payService
                .chargeForOrder(order, new AccountInfo(userInfo.getAccountId(), userInfo.getContractId()));
    }
}
