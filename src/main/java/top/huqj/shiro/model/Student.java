package top.huqj.shiro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huqj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String id;

    private String name;

    private short age;

    private byte gender;

    private int grade;

    private String major;
}
