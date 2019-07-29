package com.sc.dubbo.svca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redtx.core.anno.EnableRedTx;

@SpringBootApplication
@EnableRedTx
public class DubboServiceAApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServiceAApplication.class, args);
    }
    

}

