package com.sillybilly.loginmanager.shiro.exception;


import com.sillybilly.loginmanager.base.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  全局异常处理
 * </p>
 *
 * @author sillybilly
 * @since 2021-01-04
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler
    @ResponseBody
    public APIResult<String> ErrorHandler(AuthorizationException e) {
        log.error("权限验证失败", e);
        return APIResult.error("403","用户无权访问");
    }
}
