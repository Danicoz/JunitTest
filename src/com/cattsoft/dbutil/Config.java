package com.cattsoft.dbutil;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	private final static Logger logger = LoggerFactory.getLogger(Config.class);

	public void init_dbconns(){
		Map<String, Properties> pros = new HashMap<String, Properties>();
		for (Entry<String, T_Db_Info> entry : SysConstant.DB_INFOS.entrySet()) {
			String key = entry.getKey();
			T_Db_Info dbinfo = entry.getValue();
			try {
				// Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
				logger.info("开始装载[" + dbinfo.getDbname() + "]DB配置");
				Properties info = new Properties();
				info.setProperty("url", dbinfo.getUrl());
				info.setProperty("username", dbinfo.getUsername());
				info.setProperty("password", dbinfo.getPassword());
				info.setProperty("initialSize", "1");
				info.setProperty("minIdle", "1");
				info.setProperty("maxActive", "50");
				info.setProperty("maxWait", "172800000");
				info.setProperty("timeBetweenEvictionRunsMillis", "60000");
				info.setProperty("minEvictableIdleTimeMillis", "60000");
				info.setProperty("testWhileIdle", "true");
				info.setProperty("testOnBorrow", "false");
				info.setProperty("testOnReturn", "false");
				info.setProperty("maxPoolPreparedStatementPerConnectionSize", "10");
				info.setProperty("filters", "stat");
				info.setProperty("removeAbandoned", "true");
				info.setProperty("removeAbandonedTimeout", "18000");//查询最长时间6小时
				String driverClass = dbinfo.getDriverclass();
				if (T_Db_Info.DBTYPE_MYSQL.equalsIgnoreCase(dbinfo.getDbtype())) {
					driverClass = "com.mysql.jdbc.Driver";
					info.setProperty("validationQuery", "select 1");
					info.setProperty("poolPreparedStatements", "false");
				} else if (T_Db_Info.DBTYPE_ORACLE.equalsIgnoreCase(dbinfo.getDbtype())) {
					driverClass = "oracle.jdbc.driver.OracleDriver";
					info.setProperty("validationQuery", "select 1 FROM DUAL");
					info.setProperty("poolPreparedStatements", "true");
					info.setProperty("maxOpenPreparedStatements", "100");
				}
				info.setProperty("driverClassName", driverClass);
				pros.put(dbinfo.getDbname(), info);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		DBUtil.init(pros);
	}

	
}
