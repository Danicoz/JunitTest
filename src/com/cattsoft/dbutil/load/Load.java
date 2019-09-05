package com.cattsoft.dbutil.load;

import java.sql.Connection;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cattsoft.dbutil.DBUtil;
import com.cattsoft.dbutil.SysConstant;

public class Load {

	private static Logger logger = LoggerFactory.getLogger(Load.class);
	public static void main(String[] args) {

		//Oracle Load方法
		String loadSql1 = "sqlldr userid=scott/123456@localhost:1521/ORCL control=./file/T_PM_ATE_TODAY.ctl log=./file/log.log";
		//String loadSql2 = "sqlldr userid=GS_ZYC/GS_ZYC2018@172.168.27.7:1522:1522/1522_gs control=./file/load.ctl log=C:/Users/Administrator/Desktop/log.log";
		String loadSql3 = "sqlldr userid=TEST/TEST@172.168.27.7:1522/catt11g control=./file/insert.ctl log=C:/Users/Administrator/Desktop/log.log";

		try {
			LoadUtil.execuSqlldr(loadSql3);
		} catch (Exception e) {
			logger.error("出错了:" + e);
			e.printStackTrace();
		}
		
		//Mysql Load方法
//		String sql = "LOAD DATA local INFILE './file/cz1.txt' "
//				+ "INTO TABLE T_JH_RES_IMSPORT1 character SET GBK FIELDS TERMINATED BY ',' "
//				+ "ENCLOSED BY '\"' ESCAPED BY '' LINES TERMINATED BY '\n' "
//				+ "IGNORE 1 LINES (OFFICENAME,CITY_NAME,ID,NAME,CODE,NM_CODE,NM_NAME,SHARDING_ID,STANDARD_NAME,STANDARD_CODE,PHYSIC_DEVICE_ID,BOARD_TYPE,BOARD_MODEL,PORT_MODEL)";
//		Connection conn = DBUtil.getDBConn(SysConstant.SRC_DBALIAS);
//		try {
//			logger.info("加载数据SQL=" + sql);
//			Integer i = DBUtil.execute(conn, sql, new ArrayList<String>());
//			logger.info("加载的数据量：" + i);
//		} catch (Exception e) {
//			logger.error("mysql数据加载出错：", e);
//			e.printStackTrace();
//		}
		
		
	}
}
	



