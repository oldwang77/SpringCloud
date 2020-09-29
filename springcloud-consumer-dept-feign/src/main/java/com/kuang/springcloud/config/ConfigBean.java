package com.kuang.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {   //@Configuration  -- spring applicationContext.xml

    // 注册Bean
    // 配置负载均衡实现restTemplate
    /*
    IRule:
            1. AvailabilityFilteringRule：会先过滤掉、跳闸即访问故障的服务，对剩下的服务进行轮询
            2. RoundRobinRule ： 轮询、默认
            3. RandomRule：随机
            4. RetryRule：会先按照轮询获取服务，如果服务获取失败，则会在指定的时间内重试。
     */

    @Bean
    @LoadBalanced       // Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
