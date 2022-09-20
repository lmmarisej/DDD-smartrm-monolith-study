package com.smartrm.smartrmmonolith.infracore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartrm.smartrmmonolith.infracore.api.CommonError;
import com.smartrm.smartrmmonolith.infracore.api.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: yoda
 * @description:
 */
public class DomainAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       AccessDeniedException e) throws IOException, ServletException {
//    httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        CommonResponse response = CommonResponse.fail(CommonError.NotAuthorized);
        String resBody = new ObjectMapper().writeValueAsString(response);
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
