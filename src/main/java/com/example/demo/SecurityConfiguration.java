package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity
//@Profile("https")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);
    private ObjectMapper objectMapper;
    private CustomUserService customUserService;
    private CustomPasswordEncoder customPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 校验权限
                .httpBasic()
                .authenticationEntryPoint((req, resp, exception) -> returnMap(resp, 403, "未登录", exception.getLocalizedMessage()))

                // 无权限
                .and()
                .authorizeRequests()
                .antMatchers("/login**")
                .permitAll()
                .anyRequest()
                .authenticated()

                // 登录
                .and()
                .formLogin()
//                .loginProcessingUrl("/login_perform")
                .failureHandler((req, resp, exception) -> returnMap(resp, 401, "登录失败", exception.getLocalizedMessage()))
                .successHandler((req, resp, auth) -> returnMap(resp, 200, "登录成功", auth))
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler((req, resp, exception) -> returnMap(resp, 403, "没有权限", ""))

                // 退出
                .and()
                .logout()
//                .logoutUrl("/logout_perform")
//                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler((req, resp, auth) -> returnMap(resp, 403, "退出成功", ""))
                .permitAll()

                .and().csrf().disable().cors().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("pass").roles("USER");
        auth.userDetailsService(customUserService)
                .passwordEncoder(customPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    private void returnMap(HttpServletResponse response, int code, String message, Object info) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String, Object> mm = new HashMap<>();
        mm.put("code", code);
        mm.put("message", message);
        mm.put("info", info);
        out.write(objectMapper.writeValueAsString(mm));
        out.flush();
        out.close();
        logger.info(mm.toString());
    }
}
