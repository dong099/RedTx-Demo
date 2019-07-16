package com.sc.svca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redtx.core.anno.RedTxTransaction;
import com.sc.svca.client.FeignServiceBClient;
import com.sc.svca.client.FeignServiceCClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {
 
	@Autowired
	private FeignServiceBClient serviceBClient;
	
	@Autowired
	private FeignServiceCClient serviceCClient;

	@Override
	@RedTxTransaction
	public String execRedTx(String value, String rollbackFlag) {
		
		//codes on this section
		log.debug("now enter into A - service - {}, {}", value, rollbackFlag);
		
		String bResult = serviceBClient.runRedTx(value);
		
		log.debug("now called B - service- Result - {}", bResult);
		
		if (rollbackFlag != null && rollbackFlag.equals("true")) {
            throw new IllegalStateException("rollbackFlag");
        }
		
		String cResult = serviceCClient.runRedTx(value);
		log.debug("now called C - service- Result - {}", cResult);
	 
		return "success";
	}
}
