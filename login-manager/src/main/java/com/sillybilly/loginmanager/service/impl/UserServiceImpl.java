package com.sillybilly.loginmanager.service.impl;

import com.sillybilly.loginmanager.domain.User;
import com.sillybilly.loginmanager.dao.UserMapper;
import com.sillybilly.loginmanager.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
