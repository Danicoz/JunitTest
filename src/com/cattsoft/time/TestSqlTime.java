package com.cattsoft.time;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestSqlTime {

	@Test
	public void test() throws ParseException {
		Timestamp date = new Timestamp(System.currentTimeMillis());
		System.out.println(date);
		Date dat = date;
		
		Date d =  new Date();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd ahh:mm:ss");
		String str = sp.format(d);
		Date d2 = sp.parse(str);
		System.out.println(d2);
	}
	
	@Test
	public void test2(){
		 String dateString = "2017-09-5 17:20:12";//2017-9-7 下午5:27:21
		 String dateString1 = "2017-9-7 下午5:27:21";
		  try {
		   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		   Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString1);
		   System.out.println("时间" + date);
		   System.out.println("时间1" + date1);
		   System.out.println("时间间隔" + (date1.getTime()-date.getTime())/(24*60*60*1000));
		   // 下面将字符串转换为日期格式后显示的格式是2009-09-15
		   String str = DateFormat.getDateInstance().format(date);
		   // 如果想换一种别的格式，可以用下面的办法，得到任何的日期格式都可以
		   // 输出的结果为2009/09/15 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
		   // 输出的结果为2009-09-15 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		   // SimpleDateFormat sdf2 = new
		   // SimpleDateFormat("yyyy年MM月dd日");输出的结果为2009年09月15日 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(date));
		  } catch (Exception ex) {
		   System.out.println(ex.getMessage());
		  }
	}
	
	@Test
	public void test3(){
		
		System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
		
		System.out.println(new java.sql.Timestamp(new Date().getTime()));
		
	}

}
