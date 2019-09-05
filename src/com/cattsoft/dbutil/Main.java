package com.cattsoft.dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cattsoft.dbutil.bean.Address;

public class Main {
	private final static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws SQLException {
		Main main = new Main();
		new Config().init_dbconns();
		main.testExecute();
		// main.testColumnName();
		// main.testUpdate1();
		// main.testUpdate2();
		// main.testBatch1();
		// main.testBatch2();
		// main.testBatch3();
		// main.testQueryForObject();
		// main.testQueryForOList();
		// main.testGetRowValue();
		// main.testQueryLinkedHashMapList();
		// main.testQueryHashMapObject();
//		 main.testQueryHashMapList();
	//	main.testQueryForArrayList();
//		main.testGetColumnName();
		
	}

	/**
	 * 注释时利用{@link}与@see 把有关代码关联起来，方便查看代码逻辑
	 * @see com.cattsoft.dbutil.DBUtil
	 * @see com.cattsoft.dbutil.DBUtil#getDBConn
	 * @throws SQLException
	 */
	// 执行SQL语句
	public void testExecute() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		System.out.println(conn.toString());
		conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		System.out.println(conn.toString());
		String sql = "INSERT INTO ADDRESS VALUES(6, 'sz')";
		Integer i = DBUtil.execute(conn, sql, null);
		System.out.println(i);
	}

	// 获取字段名称
	public void testColumnName() {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT xh,zz,'姓名' FROM ADDRESS";
		List list = DBUtil.getColumnName(conn, sql);
		for (Object str : list) {
			System.out.println(str);
		}
	}

	// 无参数更新数据
	public void testUpdate1() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "UPDATE ADDRESS SET XH='5' WHERE XH=66";
		Integer i = DBUtil.update(conn, sql);
		System.out.println(i);
	}

	// 有参数更新数据
	public void testUpdate2() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);

		String sql = "UPDATE ADDRESS SET zz=? WHERE XH=5";
		Object[] params = new Object[1];
		params[0] = "liu";
		Integer i = DBUtil.update(conn, sql, params);
		System.out.println(i);
	}

	// 执行用数组存储的多个SQL语句
	public void testBatch1() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql1 = "INSERT INTO ADDRESS VALUES(1, 'fs')";
		String sql2 = "INSERT INTO ADDRESS VALUES(1, 'sw')";
		String[] str = new String[2];
		str[0] = sql1;
		str[1] = sql2;
		DBUtil.batch(conn, str);
	}

	// 执行用List存储的多个SQL语句
	public void testBatch2() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql1 = "INSERT INTO ADDRESS VALUES(1, 'zq')";
		String sql2 = "INSERT INTO ADDRESS VALUES(1, 'st')";
		List<String> list = new ArrayList<String>();
		list.add(sql1);
		list.add(sql2);
		DBUtil.batch(conn, list);
	}

	// 有参数批量处理
	public void testBatch3() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "INSERT INTO ADDRESS VALUES(?, ?)";
		Object[][] params = new Object[2][2];
		for (int i = 0; i < 2; i++) {
			params[i][0] = i;
			params[i][1] = i + "号";
		}
		DBUtil.batch(conn, sql, params);
	}

	// 查询某个具体的对象数据
	public void testQueryForObject() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS WHERE XH=?";// 无条件取查询的第一条
		Object[] param = new Object[1];
		param[0] = 2;
		Address ad = (Address) DBUtil.queryForObject(conn, sql, param,
				Address.class);// param 可为 null
		System.out.println(ad.getXh() + ":" + ad.getZz());
	}

	// 查询结果转成List集合
	public void testQueryForOList() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS ORDER BY XH ASC";
		List<Address> list = DBUtil.queryForOList(conn, sql, null,Address.class);
		for (Address ad : list) {
			System.out.println(ad.getXh() + ":" + ad.getZz());
		}
	}

	// 获取最大值或获取某个字段的值
	public void testGetRowValue() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT MAX(XH) FROM ADDRESS";
		long i = DBUtil.getRowValue(conn, sql, null);
		System.out.println(i);
	}

	@Test
	// 查询结果转成List集合; value为LinkedHashMap集合;key:列名称 value:列值
	public void testQueryLinkedHashMapList() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS";
		List list = DBUtil.queryForList(conn, sql, null);
		for (Object obj : list) {
			Map map = (Map) obj;
			System.out.println(map.get("XH") + ":" + map.get("ZZ"));
		}
	}

	// 查询结果为List集合，value为HashMap；key:columnLabel,value:列值
	public void testQueryHashMapList() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS";
		List list = DBUtil.queryHashMapList(conn, sql, null);
		for (Object obj : list) {
			HashMap map = (HashMap) obj;
			System.out.println(map.get("XH") + ":" + map.get("ZZ"));
		}
	}

	// 查询结果为HashMap集合，只能查询第一行值
	@Test
	public void testQueryHashMapObject() throws SQLException {
		Connection conn = DBUtil.getDBConn(SysConstant.SRC_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS";
		Map map = DBUtil.queryHashMapObject(conn, sql, null);
		System.out.println(map.get("XH") + ":" + map.get("ZZ"));
	}
	
	//查结果为List字符串数组
	public void testQueryForArrayList() throws SQLException{
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS";
		List<Object[]> oList = DBUtil.queryForArrayList(conn, sql, null);
		List<String>list = new ArrayList<String>();
		for(Object obj : oList){
			list.add(Arrays.toString((Object[])obj));
			System.out.println(Arrays.toString((Object[])obj));
		}
		System.out.println(list.get(0).substring(1,list.get(0).length()-1));
	}
	
	//查询列名
	public void testGetColumnName(){
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "SELECT XH,ZZ FROM ADDRESS";
		List<String> list = DBUtil.getColumnName(conn, sql);
		for(String str : list){
			System.out.println("列名=" + str);
		}
	}
	
	@Test
	public void testIndex() throws SQLException{
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		String sql = "ALTER INDEX IDX_SNO REBUILD";
		Statement stmt = conn.createStatement();
		//String sql = "ALTER INDEX IDX_SNO REBUILD";
		//String sql = "CREATE INDEX IDX_SNO1 ON STUDENT(SNO)";
		boolean flag = stmt.execute(sql);
		System.out.println("flag=" + flag);
		System.out.println(stmt.execute(sql));
	
	}
	
	@Test
	public void testLoad(){
		Connection conn = DBUtil.getDBConn(SysConstant.SRC_DBALIAS);
		String sql1 ="SELECT t.FILE_CYCLE_TIME,t.BUSI_TYPE,t.STATUS,t.FTP_FILENAME,t.SCAN_START_TIME FROM src_ftp_file_log t WHERE t.FTP_FILENAME='IPRAN_DEVICE_INFO_20180614.txt' ORDER BY t.ID DESC LIMIT 1";
		String sql2 = "SELECT RESULT_SUCCESS_LINE,RESULT_STATUS,RESULT_END_TIME FROM src_load_task_instance WHERE ID='20180801210735693'";
		
		try {
			Map<String,String> map1 = DBUtil.queryHashMapObject(conn, sql1, null);
			Map<String,String> map2 = DBUtil.queryHashMapObject(conn, sql2, null);
			
			String taskType = map2.get("RESULT_STATUS")=="8"?"1":"2";
			
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO T_DATA_MONITOR_DETAIL(I_ITEM_ID,I_MODULE_ID,S_STATIC_TIME,I_TASK_TYPE,S_SOURCE_TABLE,S_TARGET_TABLE,I_SOURCE_DATA,"
					+ "I_THRESHOLD_VALUE,I_IS_INCREMENTAL,I_INCREMENTAL_COUNT,D_START_TIME,D_END_TIME) values ("); 
			sb.append("SEQ_DATA_MONITOR.NEXTVAL, ");
			sb.append("1, ");
			sb.append(map1.get("FILE_CYCLE_TIME") + ", ");
			sb.append(taskType + ", ");
			sb.append(map1.get("FTP_FILENAME") + ", ");
			sb.append("1, ");
			sb.append(map1.get("") + ", ");
			sb.append(map1.get("") + ", ");
			sb.append(map1.get("") + ", ");
			sb.append(map1.get("") + ")");
			DBUtil.update(conn, sql1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void testGetrowcol() throws SQLException{
		String sql ="SELECT XH,ZZ FROM ADDRESS WHERE ZZ= 'SZ'";
		Connection conn = DBUtil.getDBConn(SysConstant.SRC_DBALIAS);
		Object obj = DBUtil.get1row1col(conn, sql, null);
		System.out.println(obj.toString());
		if(obj != null && !"".equals(obj)){
			
		}
	}
}

