package com.kaung.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

// 所有想要实现网络通信，第一步就要先实现序列化，否则会报错
// ORM对应关系映射，类表关系映射
// @Data可以为类提供读写功能，从而不用写get、set方法。
@Data
@NoArgsConstructor
@Accessors(chain = true)        //链式写法
public class Dept implements Serializable {
    private Long deptno;    //主键
    private String dname;

    //查看这个数据是存储在哪个数据库的字段，一个服务对应一个数据库
    //同一个信息可能存在于不同的数据库
    private String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }

    /**
     * 链式写法
     *  Dept dept = new Dept();
     *  dept......
     */
}
