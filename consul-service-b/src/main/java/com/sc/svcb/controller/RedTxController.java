package com.sc.svcb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.svcb.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RedTxController {
	@Autowired
	DemoService demoService;
	
	int cntNum = 0;

	@RequestMapping("/runRedTx")
    public String execute(@RequestParam("value") String value) {
		log.info("B-service </runRedTx> was called {}", cntNum++);
		demoService.updateDb(value);
		return "success";
	}
}
