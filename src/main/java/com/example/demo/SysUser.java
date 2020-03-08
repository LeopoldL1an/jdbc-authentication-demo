package com.example.demo;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SYS_USER")
public class SysUser {

    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解
    @GeneratedValue(generator = "idGenerator") //使用uuid的生成策略
    @Id
    @Column(name = "UUID")
    private String uuid;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ENABLED")
    private long enabled;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "AVATAR")
    private String avatar;
    @Column(name = "REMARK")
    private String remark;
}
