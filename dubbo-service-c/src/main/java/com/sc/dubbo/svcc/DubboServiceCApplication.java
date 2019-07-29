package com.sc.dubbo.svcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redtx.core.anno.EnableRedTx;

@SpringBootApplication
@EnableRedTx
public class DubboServiceCApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServiceCApplication.class, args);
    }
    

}

