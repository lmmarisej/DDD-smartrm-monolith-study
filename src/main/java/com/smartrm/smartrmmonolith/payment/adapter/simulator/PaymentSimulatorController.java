package com.smartrm.smartrmmonolith.payment.adapter.simulator;

import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import com.smartrm.smartrmmonolith.payment.application.dto.PlatformPaymentResultDto;
import com.smartrm.smartrmmonolith.payment.application.dto.PlatformResultCode;
import com.smartrm.smartrmmonolith.payment.application.service.PayService;
import com.smartrm.smartrmmonolith.payment.domain.PlatformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/mock/payment")
public class PaymentSimulatorController {
    
    @Autowired
    private PayService payService;
    
    @RequestMapping(value = "/pay/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse finishPay(@PathVariable Long orderId) {
        PlatformPaymentResultDto result = new PlatformPaymentResultDto();
        result.setPlatformType(PlatformType.Wechat);
        result.setOrderId(orderId);
        result.setResultCode(PlatformResultCode.Success);
        payService.onPaymentResult(result);
        return CommonResponse.success();
    }
    
    @RequestMapping(value = "/refund/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse finishRefund(@PathVariable Long orderId) {
        PlatformPaymentResultDto result = new PlatformPaymentResultDto();
        result.setOrderId(orderId);
        result.setPlatformType(PlatformType.Wechat);
        result.setResultCode(PlatformResultCode.Success);
        payService.onRefundResult(result);
        return CommonResponse.success();
    }
    
}
