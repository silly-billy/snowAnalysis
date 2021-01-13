package com.sillybilly.loginmanager.controller;


import com.sillybilly.loginmanager.domain.TSaUser;
import com.sillybilly.loginmanager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-09
 */
@RestController
@RequestMapping("/t-sa-user")
public class TSaUserController {

    @Autowired
    private LoginService loginService;


    @GetMapping(value = "/test")
    public TSaUser test(){
        return loginService.getLoginInfoByUserName("silly-billy");
    }

}
