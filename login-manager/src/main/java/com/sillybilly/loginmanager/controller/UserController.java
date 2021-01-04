package com.sillybilly.loginmanager.controller;


import com.sillybilly.loginmanager.domain.User;
import com.sillybilly.loginmanager.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping(value = "getUser")
    public User getUser(){
        return iUserService.getById(1);
    }

}
