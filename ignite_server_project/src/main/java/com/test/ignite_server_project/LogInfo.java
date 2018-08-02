package com.test.ignite_server_project;

import java.io.Serializable;

public class LogInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -241720576254327267L;

	private String id;
	
	private String func;
	
	private String business_type;
	
	private String trace_id;
	
	private String step_id;
	
	private String data;
	
	private String time;

	public String getData() {
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	@Override
	public String toString() {
		return "LogInfo [id=" + id + ", func=" + func + ", business_type=" + business_type + ", trace_id=" + trace_id
				+ ", step_id=" + step_id + ", data=" + data + ", time=" + time + "]";
	}

}
