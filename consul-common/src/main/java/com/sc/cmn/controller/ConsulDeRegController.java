package com.sc.cmn.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.consul.serviceregistry.ConsulRegistration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.Service;

@RestController
public class ConsulDeRegController {
	private static final Logger log = LoggerFactory.getLogger(ConsulDeRegController.class);
	private final ConsulClient consulClient;
    private final ConsulRegistration consulRegistration;

    public ConsulDeRegController(ConsulClient consulClient, ConsulRegistration consulRegistration) {
        this.consulClient = consulClient;
        this.consulRegistration = consulRegistration;
    }
    
	@DeleteMapping("api/deregister")
    public void deregister() {
        String currentInstanceId = consulRegistration.getInstanceId();
        List<Member> members = consulClient.getAgentMembers().getValue();
        for (Member member : members) {
            String address = member.getAddress();
            ConsulClient clearClient = new ConsulClient(address);
            try {
                Map<String, Service> serviceMap = clearClient.getAgentServices().getValue();
                for (Entry<String, Service> entry : serviceMap.entrySet()) {
                    Service service = entry.getValue();
                    String instanceId = service.getId();
                    if (currentInstanceId.equals(instanceId)) {
                        log.info("在{}客户端上的服务 :{}为无效服务，准备清理...................", address, currentInstanceId);
                        clearClient.agentServiceDeregister(currentInstanceId);
                    }
                }
                
                //curl -X DELETE http://172.10.1.63:8201/api/deregister
                break;
            } catch (Exception e) {
            	e.printStackTrace();
                log.error("异常信息: {}", e);
            }
        }
    }
	
	
	 
}
