package com.smartrm.smartrmmonolith.payment.application.remote;

import com.smartrm.smartrmmonolith.payment.application.dto.PaymentQrCodeDto;
import com.smartrm.smartrmmonolith.payment.domain.Payment;

/**
 * @author: yoda
 * @description:
 */
public interface PaymentPlatformClient {
    
    PaymentQrCodeDto queryQrCode(Payment payment);
    
    void requestForDeduction(Payment payment);
    
    void requestForRefund(Payment payment);
    
}
