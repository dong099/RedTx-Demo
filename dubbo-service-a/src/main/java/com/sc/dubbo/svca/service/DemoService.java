package com.sc.dubbo.svca.service;

 
public interface DemoService {
	String execRedTx(String value, String rollbackFlag);
	String execRedTxUpd(Integer uid, String value, String rollbackFlag);
    String execRedTxDel(Integer uid, String rollbackFlag);
}
