package com.smartrm.smartrmmonolith.infracore.exception;

import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CommonExceptionHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonExceptionHandler.class);
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResponse exceptionHandler(HttpServletRequest request, Exception e) {
        LOGGER.error("unknown error:", e);
        return CommonResponse.fail(CommonError.UnExpected);
    }
    
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public CommonResponse exceptionHandler(HttpServletRequest request,
                                           MethodArgumentNotValidException e) {
        return CommonResponse.fail(CommonError.RequestParamsInvalid);
    }
    
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResponse exceptionHandler(HttpServletRequest request,
                                           MissingServletRequestParameterException e) {
        return CommonResponse.fail(CommonError.RequestParamsInvalid);
    }
    
    @ResponseBody
    @ExceptionHandler(value = AccessDeniedException.class)
    public CommonResponse exceptionHandler(HttpServletRequest request, AccessDeniedException e) {
        return CommonResponse.fail(CommonError.NotAuthorized);
    }
    
    @ResponseBody
    @ExceptionHandler(value = DomainException.class)
    public CommonResponse domainExceptionHandler(HttpServletRequest request, DomainException e) {
        LOGGER.error("domain exception:" + e.getMsg(), e);
        return CommonResponse.fail(e.getErrCode());
    }
}
