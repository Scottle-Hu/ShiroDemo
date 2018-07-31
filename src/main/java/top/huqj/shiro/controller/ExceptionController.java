package top.huqj.shiro.controller;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 处理shiro异常，包括验证失败，授权失败等
 *
 * @author huqj
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 处理授权失败的异常，跳转到授权失败提示页面
     *
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handlerUnauthorized() {
        return "unauthor";
    }

    /**
     * 处理鉴权失败，即验证失败
     *
     * @return
     */
    @ExceptionHandler({UnauthenticatedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handlerUnauthentication() {
        return "login";
    }

}
