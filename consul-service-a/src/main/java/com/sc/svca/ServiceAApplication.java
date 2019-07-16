package com.sc.svca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.redtx.core.anno.EnableRedTx;
import com.sc.cmn.config.FeignHystrixConcurrencyStrategy;
//@EnableHystrixDashboard //Hystrix仪表板, server UI part, now using standalone version
//@EnableOAuth2Client
//@EnableOAuthToken
//@EnableTurbine //Notes: 开启turbine，@EnableTurbine注解包含了@EnableDiscoveryClient注解
@EnableDiscoveryClient 
@EnableFeignClients
@EnableCircuitBreaker //Note: 如果不加，Dashboard无法接收到来自Feign内部断路器的监控数据
@EnableHystrix //Note: 开启断路器
@EnableRedTx
@SpringBootApplication
public class ServiceAApplication {
	
	 /**
	  * http://localhost:8764/turbine.stream
	  	* 访问地址 http://localhost:8762/actuator/hystrix.stream
	  	* @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }
    
 
    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }     

 

}