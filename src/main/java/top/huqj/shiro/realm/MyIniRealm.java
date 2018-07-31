package top.huqj.shiro.realm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import top.huqj.shiro.utils.IniUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * 使用ini文件方式进行配置的自定义realm
 *
 * @author huqj
 */
public class MyIniRealm extends AuthorizingRealm {

    /**
     * 读取shiro.ini的工具类
     */
    private IniUtils iniUtils = new IniUtils("shiro.ini");

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> rolesByUser = iniUtils.getRolesByUser(username);
        for (String role : rolesByUser) {
            info.addRole(role);
            List<String> permissionsByRole = iniUtils.getPermissionsByRole(role);
            info.addStringPermissions(permissionsByRole);
        }
        return info;
    }

    /**
     * 鉴权
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new AuthenticationException();
        }
        if (iniUtils.hasUser(username)) {
            Optional<String> passwordByUser = iniUtils.getPasswordByUser(username);
            if (passwordByUser.isPresent()) {
                if (password.equals(passwordByUser.get())) {
                    return new SimpleAuthenticationInfo(username, password, getName());
                } else {
                    throw new IncorrectCredentialsException();
                }
            } else {
                throw new AuthenticationException();
            }
        } else {
            throw new UnknownAccountException();
        }
    }
}
