package com.sillybilly.loginmanager.service;


import com.sillybilly.loginmanager.domain.TSaUser;

public interface LoginService {


    TSaUser getLoginInfoByUserName(String name);
}
