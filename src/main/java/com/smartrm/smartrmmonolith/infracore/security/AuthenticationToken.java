package com.smartrm.smartrmmonolith.infracore.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author: yoda
 * @description:
 */
public class AuthenticationToken extends AbstractAuthenticationToken {
    
    private String code;
    private String openId;
    
    public AuthenticationToken(String code, String openId,
                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.openId = openId;
        this.code = code;
        super.setAuthenticated(true);
    }
    
    @Override
    public Object getCredentials() {
        return code;
    }
    
    @Override
    public Object getPrincipal() {
        return openId;
    }
}
