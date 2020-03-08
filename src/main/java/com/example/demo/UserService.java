package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    UserJpa userJpa;

    public SysUser getUserByName(String username) {
        return userJpa.getFirstByUsername(username);
    }

}
