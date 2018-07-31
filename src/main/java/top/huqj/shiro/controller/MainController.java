package top.huqj.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.huqj.shiro.utils.IniUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huqj
 */
@Controller
public class MainController {

    /**
     * 所有角色列表
     */
    private List<String> roleList = Arrays.asList("admin", "student", "teacher");

    private IniUtils iniUtils = new IniUtils("shiro.ini");

    /**
     * 前往主界面
     *
     * @return
     */
    @RequestMapping("/main")
    @RequiresAuthentication
    public String main(HttpServletRequest request) {
        request.setAttribute("roles", getRoles());
        return "main";
    }

    /**
     * 前往个人中心
     *
     * @return
     */
    @RequestMapping("/personal")
    @RequiresAuthentication
    public String personal(HttpServletRequest request) {
        request.setAttribute("roles", getRoles());
        request.setAttribute("permissions", getPermissions());
        return "personal";
    }

    /**
     * 获取当前用户的角色信息
     *
     * @return
     */
    private List<String> getRoles() {
        List<String> roles = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        boolean[] hasRoles = subject.hasRoles(roleList);
        for (int i = 0; i < hasRoles.length; i++) {
            boolean yes = hasRoles[i];
            if (yes) {
                roles.add(roleList.get(i));
            }
        }
        return roles;
    }

    /**
     * 获取当前用户的权限信息
     *
     * @return
     */
    private List<String> getPermissions() {
        List<String> permissions = new ArrayList<>();
        Subject subject = SecurityUtils.getSubject();
        boolean[] hasRoles = subject.hasRoles(roleList);
        for (int i = 0; i < hasRoles.length; i++) {
            boolean yes = hasRoles[i];
            if (yes) {
                permissions.addAll(iniUtils.getPermissionsByRole(roleList.get(i)));
            }
        }
        return permissions;
    }
}
