package top.huqj.shiro.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 登陆有关的控制器
 *
 * @author huqj
 */
@Controller
public class LoginController {

    /**
     * 前往登陆页面
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    /**
     * 用户校验
     *
     * @return
     */
    @RequestMapping("/check")
    public String check(HttpServletRequest request) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        if (!StringUtils.isEmpty(exceptionClassName)) {
            if (exceptionClassName.equals(UnknownAccountException.class.getName())) {
                request.setAttribute("errMsg", "用户不存在");
            } else if (exceptionClassName.equals(IncorrectCredentialsException.class.getName())) {
                request.setAttribute("errMsg", "密码错误");
            } else {
                request.setAttribute("errMsg", "未知登录错误");
            }
        }
        return "login";
    }

}
