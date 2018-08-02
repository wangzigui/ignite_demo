package com.test.ignite_server_project;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.cache.Cache;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.binary.BinaryObject;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.IgniteException;
import org.apache.ignite.IgniteJdbcDriver;
import org.apache.ignite.IgniteJdbcThinDriver;
import org.apache.ignite.internal.client.impl.connection.*;
import org.apache.ignite.lang.IgniteCallable;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.log4j.Logger;




/**
 * 此段代码为服务器代码，主要完成两项工作：1.启动ignite服务器；2.建表（目前建立了Code_info和Code_rmd两张表）
 */
public class App {
	//private static final Logger  log = Logger.getLogger(App.class);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		IgniteConfiguration cfg = new IgniteConfiguration();
//		// Ignite persistence configuration.
//		DataStorageConfiguration storageCfg = new DataStorageConfiguration();
//		// Enabling the persistence.
//		storageCfg.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
//		// Applying settings.
//		cfg.setDataStorageConfiguration(storageCfg);

		// 启动客户端
		Ignite ignite = Ignition.start();
//		ignite.active(true);

		
		
		//log.info("Code Successful！");
		System.out.println("Start Successful!");
		
		// 注册JDBC连接ignite内存数据库
		Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

		// 开始连接指定IP的ignite
		//10.71.21.177
		Connection conn = DriverManager.getConnection("jdbc:ignite:thin://172.16.33.50/");

		// 1.新建两张表
		// Code_info表 ： codeindex(VARCHAR)    name(VARCHAR)
		// Code_rmd表   : time(long)            codeindex(VARCHAR)     price(FLOAT)   volume(FLOAT)     
		try (Statement stmt = conn.createStatement()) {

//		    // 新建Code_info表: index, name
//		    stmt.executeUpdate("CREATE TABLE Code_info (" + 
//		    " codeindex VARCHAR PRIMARY KEY, name VARCHAR) " +
//		    " WITH \"template=replicated\"");
//
//		    // 新建Code_rmd表 , time,index,price,volume		    
//		    stmt.executeUpdate("CREATE TABLE Code_rmd (" +
//		    	    " time LONG, codeindex VARCHAR, price FLOAT, volume FLOAT, " +
//		    	    " PRIMARY KEY (time, codeindex)) " +
//		    	    " WITH \"backups=1, affinityKey=time\"");
		    
//		    stmt.executeUpdate("CREATE TABLE Busi_trace (" +
//		    	    " id varchar(36) primary key,trace_id varchar(36),business_type varchar(10),step_id varchar(20),data varchar(500),time varchar(30) ) " +
//		    	    " ;");
		    
//		    stmt.executeUpdate("DROP TABLE Login_info ");
		  
		    // 建立索引的方法，可提升查询速度，以后也许会用到
		    // 在City表中建立索引 idx_code_name
		    //stmt.executeUpdate("CREATE INDEX idx_code_name ON Code_info (codeindex)");

		    // 在Person表中建立索引 idx_rmd_name
		    //stmt.executeUpdate("CREATE INDEX idx_rmd_name ON Code_rmd (time)");
		}
		while(true)
		{
			
		}
		// 关闭与服务器的连接
		//conn.close();
		// 关闭服务器
		//ignite.close();

	}
	
	
	

}
