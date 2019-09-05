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
		 String dateString = "2017-09-5 17:20:12";//2017-9-7 ����5:27:21
		 String dateString1 = "2017-9-7 ����5:27:21";
		  try {
		   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		   Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString1);
		   System.out.println("ʱ��" + date);
		   System.out.println("ʱ��1" + date1);
		   System.out.println("ʱ����" + (date1.getTime()-date.getTime())/(24*60*60*1000));
		   // ���潫�ַ���ת��Ϊ���ڸ�ʽ����ʾ�ĸ�ʽ��2009-09-15
		   String str = DateFormat.getDateInstance().format(date);
		   // ����뻻һ�ֱ�ĸ�ʽ������������İ취���õ��κε����ڸ�ʽ������
		   // ����Ľ��Ϊ2009/09/15 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date));
		   // ����Ľ��Ϊ2009-09-15 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
		   // SimpleDateFormat sdf2 = new
		   // SimpleDateFormat("yyyy��MM��dd��");����Ľ��Ϊ2009��09��15�� 17:20:12
		   System.out.println(new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss").format(date));
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
