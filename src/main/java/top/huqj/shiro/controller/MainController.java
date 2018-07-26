package top.huqj.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huqj
 */
@Controller
public class MainController {

    /**
     * 前往主界面
     *
     * @return
     */
    @RequestMapping("/main")
    @RequiresAuthentication
    public String main() {
        return "main";
    }
}
