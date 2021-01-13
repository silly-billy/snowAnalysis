package com.sillybilly.loginmanager.controller;


import com.sillybilly.loginmanager.base.APIResult;
import com.sillybilly.loginmanager.domain.TSaUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户登录 前端控制器
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-09
 */
@Slf4j
@RestController
public class TSaLoginController {

    @GetMapping("/login")
    public APIResult<String> login(TSaUser user) {
        String name = user.getUsername();
        if (StringUtils.isEmpty(name)) {
            return APIResult.error("1000","请输入用户名！");
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                name,
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return APIResult.error("1001","用户名不存在！");
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return APIResult.error("1002","账号或密码错误！");
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return APIResult.error("1003","用户没有权限");
        }
        return APIResult.ok(name);
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }



}
