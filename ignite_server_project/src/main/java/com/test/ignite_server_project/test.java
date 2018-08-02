package com.test.ignite_server_project;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.IgniteConfiguration;


public class test {

	public static Ignite ignite;
	
	static
	{
		IgniteConfiguration cfg = new IgniteConfiguration();

		cfg.setClientMode(true);
		
		ignite = Ignition.start(cfg);
	}
	
	public static void main(String[] args) {
		
//		long starttime = System.currentTimeMillis();
		MyThread thread = null;
		IgniteCache<Long, Busi_trace> busiTrace = test.ignite.cache("SQL_PUBLIC_BUSI_TRACE");
		for(int i = 0; i < 100; i++)
		{
			System.out.println("thread"+i);
			thread = new  MyThread("thread"+i,busiTrace);

			
			thread.start();
			
			
		}
//		long end = System.currentTimeMillis();
//		System.out.println("插入速度："+(100000*1000)/(end-starttime));
	}
	
//	class MyThread extends Thread
//	{
//		public void run()
//		{
//			for(int i = 0; i < 10000; i++)
//			{
//				IgniteCache<Long, Busi_trace> busiTrace = ignite.cache("SQL_PUBLIC_BUSI_TRACE");
//				SqlFieldsQuery query1 = new SqlFieldsQuery("insert into busi_trace(TRACE_ID, BUSINESS_TYPE, STEP_ID, DATA, TIME, TIME_DES) values(?,?,?,?,?,?)");
//				busiTrace.query(query1.setArgs("wangzigui"+i,"8889","8889","8889",1000,"dsdfds"));
//			}
//		}
//	}
}
