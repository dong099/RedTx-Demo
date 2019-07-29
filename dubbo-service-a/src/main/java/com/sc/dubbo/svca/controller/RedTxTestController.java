package com.sc.dubbo.svca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.dubbo.svca.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RedTxTestController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam("value") String value) {		
		log.debug("A - service </hello> returned {}", value);
		return "success";
	}

	@RequestMapping("/runRedTx")
	public String execute(@RequestParam("value") String value,
			@RequestParam(value = "rollbackFlag", required = false) String rollbackFlag) {
		String rst = demoService.execRedTx(value, rollbackFlag);
		log.debug("A - service </runRedTx> returned {}", rst);
		return rst;
	}

	@RequestMapping("/runRedTxUpd")
	public String executeUpd(@RequestParam("uid") Integer uid, @RequestParam("userName") String userName,
			@RequestParam(value = "rollbackFlag", required = false) String rollbackFlag) {
		String rst = demoService.execRedTxUpd(uid, userName, rollbackFlag);
		log.debug("A - service </runRedTxUpd> returned {}", rst);
		return rst;
	}

	@RequestMapping("/runRedTxDel")
	public String executeDel(@RequestParam("uid") Integer uid,
			@RequestParam(value = "rollbackFlag", required = false) String rollbackFlag) {
		String rst = demoService.execRedTxDel(uid, rollbackFlag);
		log.debug("A - service </runRedTxDel> returned {}", rst);
		return rst;
	}

}
