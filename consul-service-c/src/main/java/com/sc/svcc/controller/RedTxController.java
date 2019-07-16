package com.sc.svcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.svcc.service.DemoService;

@RestController
public class RedTxController {
	@Autowired
	DemoService demoService;

	@RequestMapping("/runRedTx")
    public String execute(@RequestParam("value") String value) {
		demoService.updateDb(value);
		return "success";
	}
}
