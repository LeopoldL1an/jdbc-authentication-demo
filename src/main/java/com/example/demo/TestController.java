package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("principal")
    public Principal test(Principal principal) {
        System.out.println("principal:");
        System.out.println(principal);
        return principal;
    }

    @GetMapping("authentication")
    public Principal test1(Authentication authentication) {
        System.out.println("authentication:");
        System.out.println(authentication);
        return authentication;
    }

    @GetMapping("holder")
    public String test2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println("SecurityContextHolder:");
        System.out.println(currentPrincipalName);
        return currentPrincipalName;
    }

    @GetMapping("request")
    public Principal test3(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        System.out.println("HttpServletRequest:");
        System.out.println(principal);
        return principal;
    }

}
