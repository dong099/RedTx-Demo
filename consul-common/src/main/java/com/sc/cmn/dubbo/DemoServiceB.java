package com.sc.cmn.dubbo;
 
public interface DemoServiceB {
	String execute(String value);
    String executeUpd(Integer uid, String userName);
    String executeDel(Integer uid);
}
