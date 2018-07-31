package top.huqj.shiro.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 使用ini4j操作ini文件的工具类
 *
 * @author huqj
 */
@Log4j
public class IniUtils {

    @Getter
    @Setter
    private String filePath;

    @Setter
    @Getter
    private Ini ini;

    private static String USERS = "users";

    private static String ROLES = "roles";

    public IniUtils(String filePath) {
        this.filePath = filePath;
        try {
            URL url = this.getClass().getClassLoader().getResource(filePath);
            ini = new Ini(url);
        } catch (IOException e) {
            log.error(e, e);
            ini = null;
        }
    }

    /**
     * 判断是否存在该用户
     *
     * @param name
     * @return
     */
    public boolean hasUser(String name) {
        if (ini == null) {
            return false;
        }
        Profile.Section section = ini.get(USERS);
        return section != null && section.keySet().contains(name);
    }

    /**
     * 根据用户名获取密码
     *
     * @param username
     * @return
     */
    public Optional<String> getPasswordByUser(String username) {
        if (ini == null) {
            return Optional.empty();
        }
        Profile.Section section = ini.get(USERS);
        if (section == null) {
            return Optional.empty();
        }
        String passAndRoles = section.get(username);
        if (passAndRoles == null) {
            return Optional.empty();
        }
        String[] strs = passAndRoles.split(",");
        if (strs.length > 0) {
            return Optional.of(strs[0]);
        } else {
            return Optional.empty();
        }
    }

    /**
     * 获取用户的角色
     *
     * @param username
     * @return
     */
    public List<String> getRolesByUser(String username) {
        if (ini == null) {
            return Collections.emptyList();
        }
        Profile.Section section = ini.get(USERS);
        if (section == null) {
            return Collections.emptyList();
        }
        String passAndRoles = section.get(username);
        if (passAndRoles == null) {
            return Collections.emptyList();
        }
        String[] strs = passAndRoles.split(",");
        if (strs.length > 1) {
            return Arrays.asList(Arrays.copyOfRange(strs, 1, strs.length));
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 获取密码和角色
     *
     * @param username
     * @return
     */
    public List<String> getPassAndRolesByUser(String username) {
        if (ini == null) {
            return Collections.emptyList();
        }
        Profile.Section section = ini.get(USERS);
        if (section == null) {
            return Collections.emptyList();
        }
        String passAndRoles = section.get(username);
        if (passAndRoles == null) {
            return Collections.emptyList();
        }
        String[] strs = passAndRoles.split(",");
        if (strs.length > 0) {
            return Arrays.asList(strs);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 获取某个角色的全部权限信息
     *
     * @param role
     * @return
     */
    public List<String> getPermissionsByRole(String role) {
        if (ini == null) {
            return Collections.emptyList();
        }
        Profile.Section section = ini.get(ROLES);
        if (section == null) {
            return Collections.emptyList();
        }
        String permissions = section.get(role);
        if (!StringUtils.isEmpty(permissions)) {
            String[] strs = permissions.split(",");
            return Arrays.asList(strs);
        }
        return Collections.emptyList();
    }

}
