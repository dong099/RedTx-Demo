package com.sc.dubbo.svcb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redtx.core.anno.EnableRedTx;

@SpringBootApplication
@EnableRedTx
public class DubboServiceBApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServiceBApplication.class, args);
    }
    

}

