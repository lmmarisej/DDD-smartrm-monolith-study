package com.smartrm.smartrmmonolith.user.adapter.wxminipro.controller;

import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import com.smartrm.smartrmmonolith.user.adapter.wxminipro.vo.SignInResultVo;
import com.smartrm.smartrmmonolith.user.application.dto.SignInCommandDto;
import com.smartrm.smartrmmonolith.user.application.dto.SignInResultDto;
import com.smartrm.smartrmmonolith.user.application.dto.UserInfoDto;
import com.smartrm.smartrmmonolith.user.application.dto.UserInfoQueryDto;
import com.smartrm.smartrmmonolith.user.application.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;

/**
 * @author: yoda
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private AppUserService appUserService;
    
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse<SignInResultVo> signIn(@Validated @RequestBody SignInCommandDto cmd,
                                                 final HttpServletResponse response) {
        SignInResultDto result = appUserService.signInOrSignUp(cmd);
        if (result.getToken() != null) {
            response.setHeader("Authorization", result.getToken());
        }
        SignInResultVo data = new SignInResultVo();
        data.setResult(result.getResult().code());
        return CommonResponse.success(data);
    }
    
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse<UserInfoDto> getUserInfo(@NotBlank @RequestParam String openId) {
        UserInfoQueryDto query = new UserInfoQueryDto();
        query.setOpenId(openId);
        return CommonResponse.success(appUserService.getUserInfo(query));
    }
    
}
