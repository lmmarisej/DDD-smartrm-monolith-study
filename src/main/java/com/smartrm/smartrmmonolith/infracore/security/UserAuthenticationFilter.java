package com.smartrm.smartrmmonolith.infracore.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yoda
 * @description:
 */
public class UserAuthenticationFilter extends OncePerRequestFilter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationFilter.class);
    
    @Autowired
    JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("Authorization");
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        try {
            JwtToken jwtToken = new JwtToken(jwtUtil, token);
            String auths = jwtToken.getClaim("authorities");
            String openId = jwtToken.getSubject();
            String code = jwtToken.getClaim("wxJsCode");
            List<String> authList = Arrays.asList(auths.split(";"));
            List<SimpleGrantedAuthority> authorities = authList.stream()
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            SecurityContextHolder.getContext().setAuthentication(new AuthenticationToken(code, openId, authorities));
            LOGGER.info("authenticated user:{},{},{}", openId, code, auths);
        } catch (RuntimeException e) {
            LOGGER.error("unexpected exception when authenticating.", e);
        }
        chain.doFilter(request, response);
    }
    
}
