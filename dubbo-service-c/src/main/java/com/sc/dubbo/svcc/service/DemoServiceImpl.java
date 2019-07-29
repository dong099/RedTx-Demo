package com.sc.dubbo.svcc.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.redtx.core.anno.RedTxTransaction;
import com.sc.cmn.dubbo.DemoServiceC;
import com.sc.dubbo.svcc.entity.master.DemoUser;
import com.sc.dubbo.svcc.mapper.master.DemoUserMapper;

import lombok.extern.slf4j.Slf4j;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}")
@Slf4j
public class DemoServiceImpl implements DemoServiceC {

	@Autowired
	private DemoUserMapper demoDemoUserMapper;

	@Override
	@RedTxTransaction
	public String execute(String value) {
		
		DemoUser user = new DemoUser();
		user.setUserId("dubbo-c-" + value);
		user.setUserName("dubbo-c-" + value);
		user.setPassword("password");
		user.setFkRole("Admin");
		user.setUserStatus("1");
		user.setPaswdReset(false);

		demoDemoUserMapper.insert(user);
		return "success";
	}
	
	@Override
	@RedTxTransaction
	public String executeUpd(Integer uid, String userName) {
		
		DemoUser user = demoDemoUserMapper.selectByPrimaryKey(uid);	
		user.setUserName("dubbo-c-" + userName);

		demoDemoUserMapper.updateByPrimaryKey(user);
		return "success";
	}
	
	@Override
	@RedTxTransaction
	public String executeDel(Integer uid) {			 
		demoDemoUserMapper.deleteByPrimaryKey(uid);
		return "success";
	}
}
