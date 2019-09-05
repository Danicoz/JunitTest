package com.cattsoft.ftp;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局变量
 * @author Danicoz
 *
 */
public class SysConstant {
	

	/**
	 * 判断是否第一次扫描
	 */
	public static boolean isFirstCollect = true;
	
	/**
	 * 记录文件的大小
	 */
	public static Map<String, Long>sizeMapBef = new HashMap<String, Long>();
}
