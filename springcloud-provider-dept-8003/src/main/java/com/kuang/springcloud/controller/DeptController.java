package com.kuang.springcloud.controller;

import com.kaung.springcloud.pojo.Dept;
import com.kuang.springcloud.service.DeptService;
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
    @Autowired
    private DiscoveryClient discoveryClient;  // 获取一些配置信息，得到具体的微服务

    @PostMapping("/dept/add")
    public boolean addDept(@RequestBody Dept dept){return deptService.addDept(dept);}

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){return deptService.queryById(id);}

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){return deptService.queryAll();}

    // 注册进来的微服务，获取一些消息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        // 获取微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>service:"+services);

        // 得到一个具体的微服务信息，通过微服务的ID，即applicationName
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t"
                            + instance.getPort() + "\t"
                            + instance.getUri() + "\t"
                            + instance.getServiceId());
        }
        return this.discoveryClient;
    }

}
