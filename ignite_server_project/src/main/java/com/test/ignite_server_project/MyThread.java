package com.test.ignite_server_project;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.IgniteConfiguration;


class MyThread extends Thread {
//	private static Ignite ignite;
	
	private String threadid;
	IgniteCache<Long, Busi_trace> busiTrace;
	public MyThread(String threadid,IgniteCache<Long, Busi_trace> busiTrace)
	{
		this.threadid = threadid;
		this.busiTrace = busiTrace;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

//	static {
//		IgniteConfiguration cfg = new IgniteConfiguration();
//
//		cfg.setClientMode(true);
//
//		ignite = Ignition.start(cfg);
//	}

	public void run() {
		for (int i = 0; i < 1000; i++) {
			
			SqlFieldsQuery query1 = new SqlFieldsQuery(
					"insert into busi_trace(TRACE_ID, BUSINESS_TYPE, STEP_ID, DATA, TIME, TIME_DES) values(?,?,?,?,?,?)");
//			sdf.format(System.currentTimeMillis())
			busiTrace.query(query1.setArgs(threadid+"wangzigui" + i, "8889", "8889", "8889", System.currentTimeMillis(), "dfsd"));
		}
	}
}
