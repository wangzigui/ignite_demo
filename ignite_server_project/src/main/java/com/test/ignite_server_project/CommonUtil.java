package com.test.ignite_server_project;

public class CommonUtil {

	public static String changeToString(Object obj)
	{
		if(null == obj)
		{
			return null;
		}
		
		return obj.toString();
	}
}
