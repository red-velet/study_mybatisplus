package com.qxy.mp.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: SayHello
 * @Date: 2023/3/18 17:30
 * @Introduction:
 */
//@TableName("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    /**
     * 表示这是个主键字段-自增
     */
    
    private Long id;

    /**
     * 指定name123属性和数据库表tb_user的name字段对应
     */
    @TableField("name")
    private String name123;
    private Integer age;

    /**
     * 忽略该字段,无论查找还是新增都忽略
     */
    @TableField(exist = false)
    private String email;
}
