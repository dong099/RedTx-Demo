package com.sc.mgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.redtx.mgr.anno.RedtxServer;

@RedtxServer
@SpringBootApplication
public class RedTxServer {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(RedTxServer.class);
		springApplication.run(args);
	}
}
