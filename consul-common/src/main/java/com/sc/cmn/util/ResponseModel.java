package com.sc.cmn.util;

import java.io.Serializable;

public class ResponseModel implements Serializable {
	private static final long serialVersionUID = 5285476448351872035L;

	public static final String Success = "success";
	public static final String Fail = "fail";

	private String code;
	private String msg;
	private Object data;

	// 私有构造函数，此类不允许手动实例化，需要调用getInstance()获取实例
	private ResponseModel() {
	}

	public ResponseModel(String code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 返回默认的实例
	 * 
	 * @return
	 */
	public static ResponseModel getInstance() {
		ResponseModel model = new ResponseModel();
		model.setCode(ResponseModel.Fail);
		return model;
	}
	
	public static ResponseModel getSucInstance() {
		ResponseModel model = new ResponseModel();
		model.setCode(ResponseModel.Success);
		return model;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
