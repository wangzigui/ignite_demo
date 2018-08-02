package com.test.ignite_server_project;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sdf {
	public static void main(String[] args) {
		FileWriter fw = null;
		try{
			fw = new FileWriter("E:\\softwaretools\\logstash-6.2.4\\bin\\log.log",true);
			for(int i = 0;i <10;i++)
			{
				fw.write("asdf@|@{\"func\":\"latency\",\"business_type\":\"businessType1\",\"time\":\""
						+ new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date(System.currentTimeMillis())) + "\",\"trace_id\":\"" + i + "\"}@|@sdfsdf" + "\r\n");
				fw.flush();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
