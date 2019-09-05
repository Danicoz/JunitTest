package com.cattsoft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库连接、数据库操作对象等一系列操作工具类
 * @author Danicoz
 *
 */
public class DBConn {
	
	/**
	 * 日志变量
	 */
	private static Logger logger = LoggerFactory.getLogger(DBConn.class);

	/**
	 * 数据库连接
	 */
	private static Connection conn = null;
			
	/**
	 * 数据库连接驱动
	 */
	private String driver = "";
	
	/**
	 * 数据库连接URL
	 */
	private String url = "";
	
	/**
	 * 数据库连接用户名
	 */
	private String user = "";
	
	/**
	 * 数据库连接密码
	 */
	private String password = "";
	
	/**
	 * 连接计数器, 负责统计实例连接数据库的失败的次数,  
	 */
	protected  int count = 0;
	
	/**
	 * 连接失败次数最大值
	 */
	protected final static int FAILEDCOUNT = 3;

	/**
	 * 构造函数
	 * @param dbElement 
	 */
	public DBConn(Element dbElement) {
		this.driver = dbElement.getChildTextTrim("driver");
		this.url = dbElement.getChildTextTrim("url");
		this.user = dbElement.getChildTextTrim("user");
		this.password = dbElement.getChildTextTrim("password");
		this.conn = createConnection();
	}
	
	/**
	 * 创建新的数据库连接
	 * 
	 * @return Connection
	 */
	public Connection createConnection() {
		//判断连接次数是否超过了限定的次数,如果是就退出程序.
		if (count > FAILEDCOUNT) {
			logger.error("数据库的连接次数已超过了限定的三次, 请检查连接参数及网络情况是否正常!");
			System.exit(1);
		}
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			logger.error("装载数据库驱动时出错，请检查驱动是否正确! ", e);
			//记录失败次数
			count ++;
		} catch (SQLException e) {
			logger.error("连接数据库错误，请检查参数设置及网络是否正常! ", e);
			
			//记录失败次数
			count ++;
		}
		//连接成功后, 则清零
		count = 0;
		return conn;
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public Connection getConnection() {
		try{
			if(conn == null || conn.isClosed()) {
				conn = this.createConnection();
			}
		} catch (SQLException e) {
			logger.error("创建数据库连接失败", e);
		}
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 */
	public void close() {
		try {
			if(conn == null || conn.isClosed()) {
			}else{
				conn.close();
				conn = null;
			}			
		} catch (SQLException e) {
			logger.error("关闭conn 失败:", conn);
		}
	}

}
