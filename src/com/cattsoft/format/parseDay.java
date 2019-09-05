package com.cattsoft.format;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class parseDay {

	@Test
	public void test() {
		System.out.println(getParsedFileName("2017-06-05", "yyyy-MM-dd"));
	}
	
	private String getParsedFileName(String fileName,String dateFormat){
		String parseDateFormat = "yyyyMMdd";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		SimpleDateFormat sdf_parse = new SimpleDateFormat(parseDateFormat);
		String day_parse = sdf_parse.format(date);
		String day = sdf.format(date);
		String fileNameRegex_parse = fileName.replace(day, day_parse);
		return fileNameRegex_parse;
	}

	@Test
	public void getBeforeTime(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sf.parse("2017-06-14 11:08");
			System.out.println("测试时间方法 = " + 
			sf.format(new Date(date.getTime() + (-14) * 24 * 60 * 60 * 1000)));//17/6/14  输出 2017-05-31
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFormat() throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		
		date = sf.parse("2018-03-03");
		
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		System.out.println(cd);
		int xq = cd.DAY_OF_WEEK;
		int ts = cd.get(xq);
		System.out.println(ts);
		System.out.println(Calendar.DAY_OF_WEEK);
		
		
		Date date1 = new Date();
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd E");
		String afterFormat = sf1.format(date1);
		System.out.println(afterFormat);

	}
	
	
	@Test
	public void getParse() {
		
//		String str = "";
//		Pattern pattern = null;
//		pattern = Pattern.compile("[0-9]*");
//		System.out.println(pattern.matcher(str).matches());
//		
//		
//		String reg = "-\\^-\\^-\\^-\\^(\\d+)\\^(\\d+)\\^(\\d+)";
//		str = "-^-^-^-^3^7^3";
//		pattern = Pattern.compile(reg);
//		System.out.println("2=====" + pattern.matcher(str).matches());
//		
		
		File file = new File("C:\\Users\\Administrator\\Desktop\\指对SQL.sql");
		if(file.exists()){
			file.delete();
		}
		
		
		
	}
}

