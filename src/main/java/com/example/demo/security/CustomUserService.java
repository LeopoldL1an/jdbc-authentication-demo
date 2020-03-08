package com.example.demo.security;

import com.example.demo.SysUser;
import com.example.demo.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {
    UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails u = new CustomUserDetails();
        SysUser user = service.getUserByName(username);
        if(user == null) throw new UsernameNotFoundException("用户不存在");
        u.setUsername(username);
        u.setPassword(user.getPassword());
        u.setEnabled(user.getEnabled() == 1);
        u.setAuthority(username.toUpperCase());
        return u;
    }
}
