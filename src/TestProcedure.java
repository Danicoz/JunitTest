import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mysql.jdbc.CallableStatement;


public class TestProcedure {

	@SuppressWarnings({ "null", "unused" })
	@Test
	public void test() {
		String driverName = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://176/test_liuyongjian";
		String userName ="develop";
		String pwd = "develop";
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
			try {
				Class.forName(driverName);
				conn = DriverManager.getConnection(dbURL,userName,pwd);
				System.out.println(conn);
				
//				if(conn != null){
//					return;
//				}
				
				String sql = null;
				if("8"==null){
					System.out.println(">>>>");
				 sql = "DELETE FROM T_CARRIER where s_obj_id  is null";
				 pstm = conn.prepareStatement(sql);
				}else{
					System.out.println(">>>>22");
					sql = "DELETE FROM T_CARRIER where  s_carrier_obj_id =  ? OR S_CARRIER_OBJ_ID IS NULL";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, "22");
				}
				
				pstm.executeUpdate();
				
				System.out.println("���ӳɹ�");
				//CallableStatement c = (CallableStatement) conn.prepareCall("{call SP_THREE2BIG()}");
				//System.out.println(c);
				//c.execute();
				System.out.println("���ô洢���̳ɹ�");
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	@Test
	public  void test2(){
		Map<String,String> map = new HashMap<String, String>();
		map.put(" : ", "s");
		map.put(" :2", "b");
		map.put(" : ", "d");
		
		System.out.println(">>>"+map.containsKey(" : "));
		System.out.println(">>>"+map.containsKey(" :2"));
		
		System.out.println(map.get(" : "));
		
	}
	
	@Test
	public  void test3(){
	//System.out.println(getFileNameRex("*{FileType}*{TyyyyMMddT}*"));
	String rex = ".*";
	String name = "ffff3";
	System.out.println(name.matches(rex));
	
	}
	
	@Test
	public void test4(){
		String str = "abc@";
		System.out.println(str.split("@")[0] + str.split("@").length);
	}

}
