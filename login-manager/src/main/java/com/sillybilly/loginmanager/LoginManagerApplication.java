package com.sillybilly.loginmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(basePackages = "com.sillybilly.loginmanager.dao")
@SpringBootApplication(scanBasePackages = "com.sillybilly.loginmanager")
public class LoginManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginManagerApplication.class, args);
    }

}
