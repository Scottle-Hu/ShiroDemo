package top.huqj.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huqj
 */
@Controller
public class UnAuthorController {

    @RequestMapping("/unauthor")
    public String unauthor() {
        return "unauthor";
    }
}
