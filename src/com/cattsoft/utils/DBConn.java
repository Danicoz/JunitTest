package com.cattsoft.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ���ݿ����ӡ����ݿ���������һϵ�в���������
 * @author Danicoz
 *
 */
public class DBConn {
	
	/**
	 * ��־����
	 */
	private static Logger logger = LoggerFactory.getLogger(DBConn.class);

	/**
	 * ���ݿ�����
	 */
	private static Connection conn = null;
			
	/**
	 * ���ݿ���������
	 */
	private String driver = "";
	
	/**
	 * ���ݿ�����URL
	 */
	private String url = "";
	
	/**
	 * ���ݿ������û���
	 */
	private String user = "";
	
	/**
	 * ���ݿ���������
	 */
	private String password = "";
	
	/**
	 * ���Ӽ�����, ����ͳ��ʵ���������ݿ��ʧ�ܵĴ���,  
	 */
	protected  int count = 0;
	
	/**
	 * ����ʧ�ܴ������ֵ
	 */
	protected final static int FAILEDCOUNT = 3;

	/**
	 * ���캯��
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
	 * �����µ����ݿ�����
	 * 
	 * @return Connection
	 */
	public Connection createConnection() {
		//�ж����Ӵ����Ƿ񳬹����޶��Ĵ���,����Ǿ��˳�����.
		if (count > FAILEDCOUNT) {
			logger.error("���ݿ�����Ӵ����ѳ������޶�������, �������Ӳ�������������Ƿ�����!");
			System.exit(1);
		}
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			logger.error("װ�����ݿ�����ʱ�������������Ƿ���ȷ! ", e);
			//��¼ʧ�ܴ���
			count ++;
		} catch (SQLException e) {
			logger.error("�������ݿ��������������ü������Ƿ�����! ", e);
			
			//��¼ʧ�ܴ���
			count ++;
		}
		//���ӳɹ���, ������
		count = 0;
		return conn;
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 */
	public Connection getConnection() {
		try{
			if(conn == null || conn.isClosed()) {
				conn = this.createConnection();
			}
		} catch (SQLException e) {
			logger.error("�������ݿ�����ʧ��", e);
		}
		return conn;
	}
	
	/**
	 * �ر����ݿ�����
	 */
	public void close() {
		try {
			if(conn == null || conn.isClosed()) {
			}else{
				conn.close();
				conn = null;
			}			
		} catch (SQLException e) {
			logger.error("�ر�conn ʧ��:", conn);
		}
	}

}
