package com.sillybilly.loginmanager.service.impl;

import com.sillybilly.loginmanager.domain.TSaUser;
import com.sillybilly.loginmanager.dao.TSaUserMapper;
import com.sillybilly.loginmanager.service.ITSaUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-09
 */
@Service
public class TSaUserServiceImpl extends ServiceImpl<TSaUserMapper, TSaUser> implements ITSaUserService {

}
