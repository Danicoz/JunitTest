package com.cattsoft.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cattsoft.Conf;

/**
 * 数据库操作工具类
 * @author Administrator
 *
 */
public class DBUtil {

	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
	
	/**
	 * 中间库连接对象
	 */
	private static DBConn midDBConn = null;
	
	/**
	 * 后台库连接对象
	 */
	private static DBConn destDBConn = null;//resDBConn、EmsDBConn
	
	/**
	 * 数据库连接初始化操作
	 * 
	 */
	public static void init(Conf conf){
		midDBConn = new DBConn(conf.getMidDBConn());
		destDBConn = new DBConn(conf.getDestDBConn());
	}
	
	/**
	 * 释放资源
	 */
	public static void clear(){
		midDBConn.close();
		destDBConn.close();
	}
	
	
	/**
	 * 获取数据库的数据封装成 对象集合
	 * @param conn
	 * @param sql
	 * @param clazz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static List queryForOList(Connection conn, String sql, Class clazz, Object params[]) throws Exception{
		List list = null;
		try{
			QueryRunner qRunner = new QueryRunner(); 
			long start = System.currentTimeMillis();
			
			list = (List)qRunner.query(conn, sql, new BeanListHandler(clazz), params);  

			if ((System.currentTimeMillis() - start) > 0)
				logger.info("queryForOList <sql>" + sql + "<sql>" + "执行时间:"
						+ (System.currentTimeMillis() - start) + "ms"
						+ "|params:" + params + "<size>");
			
		}catch(Exception e){
			logger.error("查询 sql失败:", e);
		}
		finally{
			close(conn); 
		}
        return list;
	}
	
	/**
	 * 数据批量处理
	 * @param conn
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static int batch(Connection conn, String sql, Object params[][]) throws SQLException{
		int n =0;
		try{
			QueryRunner qRunner = new QueryRunner(); 
			long start = System.currentTimeMillis();
			n = qRunner.batch(conn, sql, params).length;
			if ((System.currentTimeMillis() - start) > 0){
				logger.info("batch params <sql>" + sql + "<sql>" + "每次批量执行时间: "
						+ (System.currentTimeMillis() - start) + "ms"
						+ "|<size>" + (params == null ? 0 : params.length) + "<size>");}	
		}finally{
			close(conn); 	
		}	
        return n;
	}
	

	/**
	 * 获取最大值
	 * @param conn
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static String queryMax(Connection conn, String sql) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		String reStr = null;

		try{
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				reStr = rs.getString(1);
			}
		}finally{
			close(rs, st);
			close(conn);
		}
		
		return reStr;
	}
	
	
	/**
	 * 关闭 Connection 连接
	 */
	private static void close(Connection conn) {
		if(conn != null){
			try {
				 conn.close();
			} catch (SQLException e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 关闭ResultSet 和 Statement 资源连接
	 */
	private static void close(ResultSet rs, Statement st) {
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * 获取中间库连接对象
	 * @return
	 */
	public static DBConn getMidDBConn(){
		return midDBConn;
	}
	
	/**
	 * 获取后库连接对象
	 * @return
	 */
	public static DBConn getDestDBConn(){
		return destDBConn;
	}
	
	
}
