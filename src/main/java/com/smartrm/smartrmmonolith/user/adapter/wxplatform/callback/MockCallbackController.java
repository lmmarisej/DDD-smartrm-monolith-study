package com.smartrm.smartrmmonolith.user.adapter.wxplatform.callback;

import com.smartrm.smartrmmonolith.user.application.dto.PaymentContractInfoDto;
import com.smartrm.smartrmmonolith.user.application.dto.WxContractSigningNotification;
import com.smartrm.smartrmmonolith.user.application.dto.WxContractSigningResponse;
import com.smartrm.smartrmmonolith.user.application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/mock/user/callback")
public class MockCallbackController {
    
    @Autowired
    AppUserService appUserService;
    
    @RequestMapping(value = "/wxContractSign", method = RequestMethod.POST)
    @ResponseBody
    public WxContractSigningResponse notifyContractSigningResult(
            @RequestBody WxContractSigningNotification notification) {
        PaymentContractInfoDto contractInfoDto = new PaymentContractInfoDto();
        contractInfoDto.setContractId(notification.getContractId());
        contractInfoDto.setOpenId(notification.getOpenid());
        contractInfoDto.setContractCode(notification.getContractCode());
        appUserService.onPaymentContractSigned(contractInfoDto);
        WxContractSigningResponse response = new WxContractSigningResponse();
        response.setReturnCode("SUCCESS");
        response.setReturnMsg("OK");
        return response;
    }
}
