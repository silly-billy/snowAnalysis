package com.sillybilly.loginmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sillybilly.loginmanager.dao.TSaRolePermissionRelationMapper;
import com.sillybilly.loginmanager.domain.*;
import com.sillybilly.loginmanager.domain.bo.RoleAndPermissionInfoBO;
import com.sillybilly.loginmanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 登录实现 服务类
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-09
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ITSaUserService itSaUserService;

    @Autowired
    private ITSaRoleService itSaRoleService;

    @Autowired
    private ITSaUserRoleRelationService itSaUserRoleRelationService;

    @Autowired
    private TSaRolePermissionRelationMapper tSaRolePermissionRelationMapper;


    @Override
    public TSaUser getLoginInfoByUserName(String name) {
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("username",name);
        wrapper.eq("deleted",0);
        wrapper.select("id","username","password","create_time","update_time");
        TSaUser user = itSaUserService.getOne(wrapper);
        Assert.notNull(user,"不存在该用户名的用户");
        wrapper.clear();
        wrapper.eq("user_id",user.getId());
        List<TSaUserRoleRelation> userAndrole = itSaUserRoleRelationService.list(wrapper);
        Set<Integer> ids = userAndrole.stream().map(TSaUserRoleRelation::getRoleId).collect(Collectors.toSet());
        //获取用户的角色
        wrapper.clear();
        wrapper.select("id","role_name","create_time","update_time")
        .in("id",ids);
        List<TSaRole> roles = itSaRoleService.list(wrapper);
        Set<Integer> roleIds = roles.stream().map(TSaRole::getId).collect(Collectors.toSet());
        List<RoleAndPermissionInfoBO> roleAndPermissionInfos = tSaRolePermissionRelationMapper.queryByRoleIds(roleIds);
        roleAndPermissionInfos.stream().collect(Collectors.groupingBy(RoleAndPermissionInfoBO::getId))
                .forEach((key,value)->{
                    Set<TSaPermission> permissions = value.stream().map(m->{
                        TSaPermission permission = new TSaPermission();
                        permission.setId(m.getId());
                        permission.setPermissionName(m.getPermissionName());
                        return permission;
                    }).collect(Collectors.toSet());
                    for (int i = 0; i < roles.size(); i++) {
                        TSaRole role = roles.get(i);
                        if (key == role.getId()){
                            role.setPermissions(permissions);
                        }
                    }
                });
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}
