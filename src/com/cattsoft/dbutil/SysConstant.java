package com.cattsoft.dbutil;

import java.util.HashMap;
import java.util.Map;

public class SysConstant {
    public static final String DB_CONN_PROPERTIES_PATH = "conf/conn.properties";

	public static Map<String, T_Db_Info> DB_INFOS = new HashMap<String, T_Db_Info>();
    
    /** 目的数据库  **/
	public static String TAR_DBALIAS = "tarDB";
	
	/** 数据源数据库  **/
	public static String SRC_DBALIAS = "midDB";
}
