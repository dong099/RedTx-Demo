package com.sc.svca.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;


/**
 * also can specify own config , configuration = FeignConfiguration.class)
 *
 */
@FeignClient(name = "consul-service-b", fallback = FeignServiceBClient.ServiceBClientFallback.class)
public interface FeignServiceBClient {

	@GetMapping(value = "/test")
	String test();

	@RequestMapping(value = "/hi", method = RequestMethod.GET)
	String sayHiFromClientOne(@RequestParam(value = "name") String name);

	@RequestMapping(value = "/runRedTx", method = RequestMethod.GET)
	String runRedTx(@RequestParam(value = "value") String value);

	// String getSessionId(@RequestHeader("X-Auth-Token") String token);

	@Slf4j
	@Component
	class ServiceBClientFallback implements FeignServiceBClient {

		@Override
		public String test() {
			log.info("now flow into callback method for /test method. ");
			return "Calling B service - </test> failed";
		}

		@Override
		public String sayHiFromClientOne(String name) {
			return "Calling B service </hi> failed - with parameter - name:" + name;
			// SERVICE sayHiFromClientOne FAILED! - FALLING BACK - with parameter - name:abc

		}

		@Override
		public String runRedTx(String value) {
			return "Calling consul-service-b </runRedTx>  FAILED! - FALLING BACK - with parameter - value:" + value;
		}

	}
}