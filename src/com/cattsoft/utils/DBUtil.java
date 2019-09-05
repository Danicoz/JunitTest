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
 * ���ݿ����������
 * @author Administrator
 *
 */
public class DBUtil {

	private static Logger logger = LoggerFactory.getLogger(DBUtil.class);
	
	/**
	 * �м�����Ӷ���
	 */
	private static DBConn midDBConn = null;
	
	/**
	 * ��̨�����Ӷ���
	 */
	private static DBConn destDBConn = null;//resDBConn��EmsDBConn
	
	/**
	 * ���ݿ����ӳ�ʼ������
	 * 
	 */
	public static void init(Conf conf){
		midDBConn = new DBConn(conf.getMidDBConn());
		destDBConn = new DBConn(conf.getDestDBConn());
	}
	
	/**
	 * �ͷ���Դ
	 */
	public static void clear(){
		midDBConn.close();
		destDBConn.close();
	}
	
	
	/**
	 * ��ȡ���ݿ�����ݷ�װ�� ���󼯺�
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
				logger.info("queryForOList <sql>" + sql + "<sql>" + "ִ��ʱ��:"
						+ (System.currentTimeMillis() - start) + "ms"
						+ "|params:" + params + "<size>");
			
		}catch(Exception e){
			logger.error("��ѯ sqlʧ��:", e);
		}
		finally{
			close(conn); 
		}
        return list;
	}
	
	/**
	 * ������������
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
				logger.info("batch params <sql>" + sql + "<sql>" + "ÿ������ִ��ʱ��: "
						+ (System.currentTimeMillis() - start) + "ms"
						+ "|<size>" + (params == null ? 0 : params.length) + "<size>");}	
		}finally{
			close(conn); 	
		}	
        return n;
	}
	

	/**
	 * ��ȡ���ֵ
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
	 * �ر� Connection ����
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
	 * �ر�ResultSet �� Statement ��Դ����
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
	 * ��ȡ�м�����Ӷ���
	 * @return
	 */
	public static DBConn getMidDBConn(){
		return midDBConn;
	}
	
	/**
	 * ��ȡ������Ӷ���
	 * @return
	 */
	public static DBConn getDestDBConn(){
		return destDBConn;
	}
	
	
}
