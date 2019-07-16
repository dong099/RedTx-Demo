package com.sc.svcb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redtx.core.anno.EnableRedTx;
import com.redtx.core.anno.RedTxTransaction;
import com.sc.svcb.entity.master.DemoUser;
import com.sc.svcb.mapper.master.DemoUserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoUserMapper demoDemoUserMapper;

	@Override
	@RedTxTransaction
	public String updateDb(String value) {
		
		DemoUser user = new DemoUser();
		user.setUserId("consul-b-" + value);
		user.setUserName("consul-b-" + value);
		user.setPassword("password");
		user.setFkRole("Admin");
		user.setUserStatus("1");
		user.setPaswdReset(false);

		demoDemoUserMapper.insert(user);
		return "success";
	}
}
