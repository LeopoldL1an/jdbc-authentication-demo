package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpa extends JpaRepository<SysUser, String> {
    SysUser getFirstByUsername(String username);
}
