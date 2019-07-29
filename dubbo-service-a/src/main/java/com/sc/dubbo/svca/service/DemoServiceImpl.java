package com.sc.dubbo.svca.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import com.redtx.core.anno.RedTxTransaction;
import com.sc.cmn.dubbo.DemoServiceB;
import com.sc.cmn.dubbo.DemoServiceC;

import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j
public class DemoServiceImpl implements DemoService {

	@Reference(version = "${demo.service.version}", application = "DubboServiceB")
	private DemoServiceB demoServiceB;
	
	@Reference(version = "${demo.service.version}", application = "DubboServiceC")
	private DemoServiceC demoServiceC;

	@Override
	@RedTxTransaction
	public String execRedTx(String value, String rollbackFlag) {
		// codes on this section
		log.debug("now enter into dubbo A - service - {}, {}", value, rollbackFlag);

		String bResult = demoServiceB.execute(value);

		log.debug("now called dubbo B - service- Result - {}", bResult);

		if (rollbackFlag != null && rollbackFlag.equals("true")) {
			throw new IllegalStateException("rollbackFlag");
		}

		String cResult = demoServiceC.execute(value);
		log.debug("now called dubbo C - service- Result - {}", cResult);

		return "success";
	}
	
	@Override
	@RedTxTransaction
	public String execRedTxUpd(Integer uid, String userName, String rollbackFlag) {

		// codes on this section
		log.debug("now enter into dubbo A - service-execRedTxUpd - {}, {}", userName, rollbackFlag);

		String bResult = demoServiceB.executeUpd(uid, userName);

		log.debug("now called dubbo B - service-execRedTxUpd- Result - {}", bResult);

		if (rollbackFlag != null && rollbackFlag.equals("true")) {
			throw new IllegalStateException("rollbackFlag");
		}

		String cResult = demoServiceC.executeUpd(uid, userName);
		log.debug("now called dubbo C - service-execRedTxUpd- Result - {}", cResult);

		return "success";
	}

	@Override
	@RedTxTransaction
	public String execRedTxDel(Integer uid, String rollbackFlag) {

		// codes on this section
		log.debug("now enter into dubbo A - service-execRedTxDel - {}, {}", uid, rollbackFlag);

		String bResult = demoServiceB.executeDel(uid);

		log.debug("now called dubbo B - service-execRedTxDel- Result - {}", bResult);

		if (rollbackFlag != null && rollbackFlag.equals("true")) {
			throw new IllegalStateException("rollbackFlag");
		}

		String cResult = demoServiceC.executeDel(uid);
		log.debug("now called dubbo C - service-execRedTxDel- Result - {}", cResult);

		return "success";
	}
}
