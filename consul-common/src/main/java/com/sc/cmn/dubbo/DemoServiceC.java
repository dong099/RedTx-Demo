package com.sc.cmn.dubbo;

 
public interface DemoServiceC {
	String execute(String value);
    String executeUpd(Integer uid, String userName);
    String executeDel(Integer uid);
}
