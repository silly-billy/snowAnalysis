package com.sillybilly.loginmanager.shiro;

import com.sillybilly.loginmanager.domain.TSaPermission;
import com.sillybilly.loginmanager.domain.TSaRole;
import com.sillybilly.loginmanager.domain.TSaUser;
import com.sillybilly.loginmanager.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  自定义权限管理与认证
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-04
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {


    @Autowired
    private LoginService loginService;

    /**
     * 权限配置管理
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        TSaUser user = loginService.getLoginInfoByUserName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (TSaRole role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for (TSaPermission permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证配置类
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (ObjectUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        TSaUser user = loginService.getLoginInfoByUserName(name);
        if (user == null) {
            //这里返回后会报出对应异常
            log.info("CustomRealm#doGetAuthenticationInfo 用户信息获取异常！");
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
