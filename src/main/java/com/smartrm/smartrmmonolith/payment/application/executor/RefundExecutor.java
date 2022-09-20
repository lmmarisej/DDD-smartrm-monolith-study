package com.smartrm.smartrmmonolith.payment.application.executor;

import com.smartrm.smartrmmonolith.infracore.scheduler.ExecutorJobScheduler;
import com.smartrm.smartrmmonolith.infracore.scheduler.RetryExecutorBase;
import com.smartrm.smartrmmonolith.payment.application.remote.PaymentPlatformClient;
import com.smartrm.smartrmmonolith.payment.domain.Payment;
import com.smartrm.smartrmmonolith.payment.domain.PaymentState;
import com.smartrm.smartrmmonolith.payment.domain.repository.PaymentRepository;
import com.smartrm.smartrmmonolith.payment.domain.wechat.RefundSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Map;

/**
 * @author: yoda
 * @description: 退款任务
 */
public class RefundExecutor extends RetryExecutorBase {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Autowired
    @Qualifier("mockPaymentPlatformClientImpl")
    private PaymentPlatformClient paymentPlatformClient;
    
    @Autowired
    private RefundSpecification specification;
    
    @Autowired
    private ExecutorJobScheduler scheduler;
    
    @Override
    public void run(Map<String, Object> params) {
        Long orderId = (Long) params.get("orderId");
        Payment payment = paymentRepository.getByOrderId(orderId);
        if (payment != null) {
            if (payment.getState() == PaymentState.WaitingForDeduction) {
                if (specification.isSatisfiedBy(payment)) {
                    //请求退款
                    payment.refundTried();
                    paymentPlatformClient.requestForDeduction(payment);
                    paymentRepository.update(payment);
                }
                //只要还处于等待退款状态就一直重试
                scheduler.scheduleRetry(RefundExecutor.class, params, 60000, 10000);
            } else if (payment.getState() == PaymentState.RefundFailed) {
                //TODO: 根据情况判断是否需要继续重试退款
            }
        }
    }
}
