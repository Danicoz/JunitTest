package com.cattsoft.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.DateFormatter;

import org.joda.time.format.DateTimeFormat;
import org.junit.Test;


public class Dateformate {

	@Test
	public void dateFormatTest() throws ParseException {
		
			Date nowDate = new Date();
			System.out.println("nowDate: " + nowDate);
			Calendar now = Calendar.getInstance();
			System.out.println("now: " + now);
			now.setTime(nowDate); 
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String str = formatter.format(now.getTime());
			System.out.println("formatDate:" + str);
			
			String time = "" + now.get(now.YEAR) + "-" + (now.get(now.MONTH) + 1) + "-"
					+ now.get(now.DATE) + " " + now.get(now.HOUR_OF_DAY) + ":"
					+ now.get(now.MINUTE) + ":" + now.get(now.SECOND);
			System.out.println("time: " + time);
			
			Date formatTime = Dateformate.parses("2017-4-13 9:38:52", "yyyy-MM-dd HH:mm:dd");
			System.out.println("formatTime: " + formatTime);//返回的是formatTime: Mon May 22 09:38:00 CST 2017
			
			System.out.println(Dateformate.isChinese("总"));
			System.out.println(Dateformate.isChinese("d"));
			
			System.out.println("currentTimeMillis=" + System.currentTimeMillis() 
					+ " Date.getTime=" + new Date().getTime());
		
	}
	
	@Test
	public void stringTest(){
		String str = Dateformate.toFirstUpperCase("danicoz");
		System.out.println("首字母大写: " + str);
	}

	@Test
	public void testDate() throws ParseException{
		//时间转成特定格式的字符串
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String str = formatter.format(new Date());
		System.out.println(str);
		
		//时间字符串转成时间
		Date dt = new SimpleDateFormat("yyyy-MM-dd").parse("2019-08-06");
		System.out.println(dt);
		
		//joda-time 
        org.joda.time.format.DateTimeFormatter fm = DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss");
        
		//时间字符串转成时间后获取对应的年月日jdk1.8
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime ldt = LocalDateTime.parse("2019-08-06 10:10:10", dft);
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		
		System.out.println(new Date().getTime());
		System.out.println(new Date().getTime()/1000);
	}
	
	public static Date parses(String strDate, String pattern)
			throws ParseException {
		return new SimpleDateFormat(pattern).parse(strDate);
	}
	
	//判断是否是中文
	public static boolean isChinese(String str) {
		String regEx = "[\\u4e00-\\u9fa5]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}
	
	//首字母大写
	public static String toFirstUpperCase(String str) {
		if (str == null || "".equals(str.trim()))
			return "";
		String firstChar = str.substring(0, 1).toUpperCase();
		String lastStr = str.substring(1);
		return firstChar + lastStr;
	}
	
	/**
	 * 去除字符串数组中的重复值
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 去重复的字符串数组
	 */
	public static String[] filterRepeat(String[] stringArray) {
		ArrayList arrayList = new ArrayList();
		for (String str : stringArray) {
			if (!arrayList.contains(str)) {
				arrayList.add(str);
			}
		}
		return (String[]) arrayList.toArray(new String[] {});
	}
	
	@Test
	public void arrayTest(){
		ArrayList array = new ArrayList();
		array.add("a");
		array.add("b");
		array.add("c");
		System.out.println(((String[]) array.toArray(new String[] {}))[1]);
	}
	
	//时间的增减操作
	@Test
	public void dateChange(){
		String str = getBeforeDate(0, new Date(), "yyyy-MM-dd");
		System.out.println("当前日期减2天：" + str);
	}
	
	/**
	 * 返回当前时间减去的天数日期
	 * 
	 * @return
	 */
	public static String getBeforeDate(int day, Date date,String mode) {
		SimpleDateFormat sdf = new SimpleDateFormat(mode);
		String endTime = "";
		try {
			String startTime = sdf.format(date);
			Date date_start = sdf.parse(startTime);
			Calendar cd = Calendar.getInstance();
			cd.setTime(date_start);
			System.out.println(Calendar.DATE);
			cd.add(Calendar.DATE, -day);// 增加一天
			endTime = sdf.format(cd.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endTime;
	}
	
	@Test
	public void parseDate(){
		System.out.println(Str2Date("yyyyMMdd", "20170606"));
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		System.out.println(sd.format(Str2Date("yyyyMMdd", "20170606")));
	}
	/**
	 * 按reg转换当前时间
	 * 
	 * @param reg
	 * @return
	 */
	public static Date Str2Date(String format, String iTaskId) {
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sdf.parse(iTaskId);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	@Test
	public void test3() throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ahh:mm:ss");
		Date str = sf.parse("2017-06-20 下午4:03:03");
		System.out.println(str);
	}
	
	@Test
	public void test4(){
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ahh:mm:ss");
		//Date str = sf.parse(date);

		//20200401
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";//获取年份
		String month = (calendar.get(Calendar.MONTH) + 1) + "";//获取月份

		System.out.println("year = " + year + " month = " + month);


	}
	
////////////////////////////////////////////////////////////////
	public static String getDate(Date d, int range, String format) {
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar tmp = Calendar.getInstance();

		tmp.setTime(d);
		tmp.add(Calendar.DATE, -range);
		return date2Str(tmp.getTime(), format);
	}
	
	public static String date2Str(Date d, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(d);
	}
	
	@Test
	public void testGetDate(){
		String str = getDate(new Date(), 2, "yyyy-MM-dd");
		System.out.println(str);
		
		String date1 = getDate(new Date(), 1, "yyyy-MM-dd");
		String date2 = getDate(new Date(), 0, "yyyy-MM-dd");
	    String getResNeIdSQL= "SELECT A.S_RES_NE_ID, A.S_USRE_ACCOUNT "
				+ " FROM DEVELOP.T_USER_REL_DEVICE A "
				+ " WHERE A.I_DEVICE_TYPE = 3 "
				+ " AND A.S_USRE_ACCOUNT IN ( "
				+ " SELECT B.S_ACCOUNT "
				+ " FROM DEVELOP.T_PM_ATE B "
				+ " WHERE B.D_UPDATE_TIME >= TO_DATE('" + date1 + "','YYYY-MM-DD') "
	            + " AND B.D_UPDATE_TIME < TO_DATE('" + date2 + "','YYYY-MM-DD')) ";
	    System.out.println(getResNeIdSQL);
	}
////////////////////////////////////////////////////////////////
	

	@Test
	public void testFileName(){
		String str = getFileDate("HW_20180510.txt");
		System.out.println(str);
	}
	public String getFileDate(String fileName){
		int startIndex = fileName.lastIndexOf("_");
		int endIndex = fileName.lastIndexOf(".txt");
		String strDate = fileName.substring(startIndex+1, endIndex);
		int intDate = Integer.parseInt(strDate)-1;
		return String.valueOf(intDate);
	}
}
