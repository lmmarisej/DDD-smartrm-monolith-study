package com.smartrm.smartrmmonolith.infracore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public UserAuthenticationFilter userAuthenticationFilter() {
        return new UserAuthenticationFilter();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement()
                // 取消session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 仅认证特定入口
                .antMatchers("/trade/cabinet/open/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new DomainAuthenticationEntryPoint())     // 匿名用户访问无权限资源时的异常
                .accessDeniedHandler(new DomainAccessDeniedHandler());              // 认证过的用户访问无权限资源时的异常
        http.addFilterAt(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//
//  @Autowired
//  private WxAppletAuthenticationManager wxAppletAuthenticationManager;
//
//  @Bean
//  public WxAppletAuthenticationFilter wxAppletAuthenticationFilter() {
//    WxAppletAuthenticationFilter wxAppletAuthenticationFilter = new WxAppletAuthenticationFilter(
//        "/login");
//    wxAppletAuthenticationFilter.setAuthenticationManager(wxAppletAuthenticationManager);
//    wxAppletAuthenticationFilter
//        .setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
//    return wxAppletAuthenticationFilter;
//  }
//
//  @Bean
//  public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
//    return new CustomAuthenticationSuccessHandler();
//  }
//
//  @Bean
//  public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//    return new JwtAuthenticationTokenFilter();
//  }

}
