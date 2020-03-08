package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("method")
@AllArgsConstructor
public class MethodTestController {

    ServerProperties serverProperties;

    // 当使用角色控制时方可使用hasRole
    @PreAuthorize("hasRole('USER')")
    @GetMapping("userRole")
    public String userRole() {
        return "user ok:" + serverProperties.getPort();
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("adminRole")
    public String adminRole() {
        return "admin ok:" + serverProperties.getPort();
    }

    // 当使用权限控制时方可使用hasAuthority
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("userAuthority")
    public String userAuthority() {
        return "user ok:" + serverProperties.getPort();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("adminAuthority")
    public String adminAuthority() {
        return "admin ok:" + serverProperties.getPort();
    }

}
