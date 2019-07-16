package com.sc.svca.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "consul-service-c", fallback = FeignServiceCClient.ServiceBClientFallback.class)
public interface FeignServiceCClient {
    
    @RequestMapping(value = "/runRedTx",method = RequestMethod.GET)
    String runRedTx(@RequestParam(value = "value") String value);


    @Component
    class ServiceBClientFallback implements FeignServiceCClient {
 
        @Override
        public String runRedTx(String value) {
        	return "Calling consul-service-c </runRedTx>  FAILED! - FALLING BACK - with parameter - value:" + value;  	
        }
                
    }
}