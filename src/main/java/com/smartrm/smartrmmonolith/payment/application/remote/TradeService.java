package com.smartrm.smartrmmonolith.payment.application.remote;

import com.smartrm.smartrmmonolith.payment.domain.PaymentStateChangeEvent;

/**
 * @author: yoda
 * @description:
 */
public interface TradeService {
    
    void onPaymentStateChange(PaymentStateChangeEvent event);
    
}
