package top.huqj.shiro.utils;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author huqj
 */
public class IniUtilsTest {

    private IniUtils iniUtils = new IniUtils("shiro.ini");

    @Test
    public void testHasUser() {
        assert iniUtils.hasUser("root");
        assert !iniUtils.hasUser("huqj");
    }

    @Test
    public void testGetPassByUser() {
        Optional<String> root = iniUtils.getPasswordByUser("root");
        assert root.isPresent();
        assert root.get().equals("123");
        Optional<String> huqj = iniUtils.getPasswordByUser("huqj");
        assert !huqj.isPresent();
    }

    @Test
    public void testGetRolesByUser() {
        List<String> root = iniUtils.getRolesByUser("root");
        assert root.size() == 1;
        assert root.get(0).equals("admin");
        List<String> huqj = iniUtils.getRolesByUser("huqj");
        assert huqj.size() == 0;
        List<String> stu1 = iniUtils.getRolesByUser("stu1");
        assert stu1.size() == 1;
        assert stu1.get(0).equals("student");
    }

    @Test
    public void testGetPassAndRole() {
        List<String> root = iniUtils.getPassAndRolesByUser("root");
        assert root.size() == 2;
        assert root.get(0).equals("123");
        assert root.get(1).equals("admin");
        List<String> tea1 = iniUtils.getPassAndRolesByUser("tea1");
        assert tea1.size() == 2;
        assert tea1.get(0).equals("12345");
        assert tea1.get(1).equals("teacher");
        List<String> root1 = iniUtils.getPassAndRolesByUser("root1");
        assert root1.size() == 0;
    }

    @Test
    public void testGetPermissionByRole() {
        List<String> admin = iniUtils.getPermissionsByRole("admin");
        assert admin.size() == 3;
        assert admin.get(0).equals("student:*");
        assert admin.get(1).equals("teacher:*");
        assert admin.get(2).equals("administrator:*");
        List<String> teacher = iniUtils.getPermissionsByRole("teacher");
        assert teacher.size() == 2;
        assert teacher.get(0).equals("teacher:update");
    }


}
