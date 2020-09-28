package com.kuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 启动之后访问  http://localhost:7003/
@SpringBootApplication
@EnableEurekaServer         //服务器的启动类，可以接受别人注册进来
public class EurakaServer_7003 {
    public static void main(String[] args) {
        SpringApplication.run(EurakaServer_7003.class,args);
    }
}
