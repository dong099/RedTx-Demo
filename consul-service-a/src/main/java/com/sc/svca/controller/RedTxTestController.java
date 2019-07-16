package com.sc.svca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sc.svca.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RedTxTestController {
 

	
	@Autowired
	DemoService demoService;
	
  
	@RequestMapping("/runRedTx")
    public String execute(@RequestParam("value") String value, @RequestParam(value = "rollbackFlag", required = false) String rollbackFlag) {
        String rst = demoService.execRedTx(value, rollbackFlag);
        log.debug("A - service </runRedTx> returned {}", rst);
        return rst;
    }

}
