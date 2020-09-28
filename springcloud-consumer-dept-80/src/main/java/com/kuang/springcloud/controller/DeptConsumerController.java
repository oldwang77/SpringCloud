package com.kuang.springcloud.controller;

import com.kaung.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {

    // 理解：消费者，不应该有service层
    // RestTemplate .... 供我们直接调用就可以了,注册到spring中
    // RestTemplate的返回格式(url,实体，Map,Class<T> responseType)

    @Autowired
    // 提供多种便捷访问远程http服务的方法，简单的Restful服务模板
    private RestTemplate restTemplate;

    // 通过ribbon实现的时候，我们这里的地址是一个变量，是通过服务名来访问
    // 这个变量注册在eureka中，SpringCloud/springcloud-provider-dept-8001/src/main/java/com/kuang/springcloud/controller/DeptController.java
    // http://SPRINGCLOUD-PROVIDER-DEPT里面包含三个，localhost:7001,7002,7003

    // private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    @RequestMapping("/dept/add")
    public boolean addDept(Dept dept){
        // 要什么东西，去哪里拿
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

}
