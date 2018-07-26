package top.huqj.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.Realm;

/**
 * 使用ini文件方式进行配置的自定义realm
 *
 * @author huqj
 */
public class MyIniRealm implements Realm {

    @Override
    public String getName() {
        return "MyIniRealm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return true;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        if (username.equals("test")) {
            throw new AuthenticationException();
        }
        return new SimpleAccount(username, password, getName());
    }
}
