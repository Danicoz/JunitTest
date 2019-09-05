package com.cattsoft.sql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;

public class ConnTest {

	@Test
	public void test() throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement stm = null;
		PreparedStatement pstm = null;
		String url = "jdbc:oracle:thin:@172.168.10.8:1521:orcl";
		String user = "develop";
		String password = "develop";
		String className = "oracle.jdbc.OracleDriver";
		
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
			
			//stm = conn.createStatement();
			String sql = "Insert into T_TEST(ID,UPDATE_TIME) values(?,?)";
			pstm = conn.prepareStatement(sql);
			//rs = stm.getResultSet();
			pstm.setString(1,"22");
			pstm.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
//			String sql = "Insert into T_TEST(ID,UPDATE_TIME) "
//					+ "values(" + 3 + "," + new java.sql.Timestamp(new Date().getTime()) + ")";
//			
			pstm.execute();
			System.out.println(sql);
			//stm.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			//stm.close();
		pstm.close();
			conn.close();
		}
		
	}

}
