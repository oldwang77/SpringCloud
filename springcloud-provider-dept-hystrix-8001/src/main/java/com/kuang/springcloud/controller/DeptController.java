package com.kuang.springcloud.controller;


import com.kaung.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/get/{id}")
    // 设置一个备选方案
    @HystrixCommand(fallbackMethod="hystrixGet")
    public Dept get(@PathVariable("id") Long id){
        Dept dept = deptService.queryById(id);
        if(null == dept){
            throw new RuntimeException("id=>"+id+"，不存在该用户！");
        }
        return dept;
    }

    // 备选方法
    public Dept hystrixGet(@PathVariable Long id){
        return new Dept()
                .setDeptno(id)
                .setDname("id=>"+id+"，不存在该用户！null--@Hystrix")
                .setDb_source("no this Database in Mysql");
    }
}
