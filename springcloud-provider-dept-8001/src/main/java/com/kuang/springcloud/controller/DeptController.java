package com.kuang.springcloud.controller;

import com.kaung.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 提供Restful服务
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    // @Autowired
    // private DiscoveryClient discoveryClient;  // 获取一些配置信息，得到具体的微服务

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){return deptService.addDept(dept);}

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){return deptService.queryById(id);}

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){return deptService.queryAll();}

}
