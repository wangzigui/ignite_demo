 package com.test.ignite_server_project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;



public class Busi_trace implements Serializable{
	
	private String business_type;
	
	private String trace_id;
	
	private String step_id;
	
	private String data;
	
	private long time;
	
	private String time_des;

	public Busi_trace(){}
	
	public Busi_trace(Map<String, Object> loginfo) {
		this.business_type = CommonUtil.changeToString(loginfo.get("business_type"));
		this.data = CommonUtil.changeToString(loginfo.get("data"));
		String time_des = null;
		if(StringUtils.isNotEmpty(CommonUtil.changeToString(loginfo.get("time"))))
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			time_des = sdf.format(new Date(Long.parseLong(CommonUtil.changeToString(loginfo.get("time")))));
		}
		
		this.time = Long.parseLong(CommonUtil.changeToString(loginfo.get("time")));
	    this.time_des = time_des;
		this.step_id = CommonUtil.changeToString(loginfo.get("step_id"));
		this.trace_id = CommonUtil.changeToString(loginfo.get("trace_id"));
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getTime_des() {
		return time_des;
	}

	public void setTime_des(String time_des) {
		this.time_des = time_des;
	}

	public String getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
	}

	public String getTrace_id() {
		return trace_id;
	}

	public void setTrace_id(String trace_id) {
		this.trace_id = trace_id;
	}

	public String getStep_id() {
		return step_id;
	}

	public void setStep_id(String step_id) {
		this.step_id = step_id;
	}
}
