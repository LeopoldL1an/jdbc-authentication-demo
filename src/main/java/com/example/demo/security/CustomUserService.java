package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails u = new CustomUserDetails();
        if ("user".equals(username)) {
            u.setUsername(username);
            u.setPassword("111");
            u.setEnabled(true);
            u.setAuthority("USER"); // 添加权限
        } else if ("admin".equals(username)) {
            u.setUsername(username);
            u.setPassword("222");
            u.setEnabled(true);
            u.setAuthority("ADMIN");
        }
        return u;
    }

    // db

}
