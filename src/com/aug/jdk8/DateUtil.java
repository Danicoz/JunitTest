package com.aug.jdk8;
import javafx.scene.control.Separator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 日期类
 * @author hgx
 * @date 2012-2-20
 * @version 1.0
 */
public class DateUtil {

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 从容器中获取bean
	 */

	/**
	 * 时间转为字符串
	 * @param date   为空时 默认 当前时间
	 * @param format 未空时 默认 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String dateToString(Date date,String format) {
		if(date==null) {
			date = new Date();
		}
		if(StringUtils.isEmpty(format)) {
			format="yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	}

	public static LocalDate stringToLocalDate(String dateStr,String format) {
		if(StringUtils.isBlank(dateStr)) {
			return LocalDate.now();
		}
		if(StringUtils.isBlank(format)) {
			format = "yyyy-MM-dd";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return LocalDate.parse(dateStr,formatter);
	}
	
	/**
	 * 根据当前月份 获取上月
	 * @param currMonth
	 * @return
	 */
	public static String getLastMonthByCurrMonth(String currMonth) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date currdate=null;
		try {
			currdate = formatter.parse(currMonth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Calendar   calendar= Calendar.getInstance();
        calendar.setTime(currdate);
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1);
        return formatter.format(calendar.getTime());
	}
	/**
	 * 获取现在时间
	 * 
	 * @return 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	
	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateStrLong(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 * @throws ParseException 
	 */
	public static String dateToStrLong(String dateDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = dateStrLong(formatter.parse(dateDate));
		return dateString;
	}
	
	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @return
	 * @throws ParseException 
	 */
	public static String dateToStrLong2(String dateDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateToStr(formatter.parse(dateDate));
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param
	 * @return
	 */
	public static String dateToStr(Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	public static boolean sameDate(Date d1, Date d2){
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		return fmt.format(d1).equals(fmt.format(d2));
	}
	
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}
	
	/**
	 * @author hgx
	 * 2012-02-27
	 * 获得当月第一天
	 * @return
	 */
	public static String getStartDate(){
		Date dt = new Date();
		int mt = dt.getMonth() + 1;
		int da = dt.getDate();
		int year = dt.getYear() + 1900;

		String month = "";
		String start = "";
		if (mt < 10) {
			month = "0" + mt;
		} else {
			month = "" + mt;
		}
		// 开始时间默认为月初，结束时间为月尾
		start = year + "-" + month + "-01";
		return start;
	}
	
	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(sj1);
			Date mydate = myFormatter.parse(sj2);
			Calendar c = Calendar.getInstance();
			c.setTime(mydate);
			c.add(Calendar.DATE,1);
			mydate = c.getTime();
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2012年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}
	
	public static String getLastMonth(Date date,int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		if(date==null) {
			date=new Date();
		}
		c.setTime(date);
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return sdf.format(m);
	}


	public static String getLastMonthAndDay(Date date,int i) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		if(date==null) {
			date=new Date();
		}
		c.setTime(date);
		c.add(Calendar.MONTH, -i);
		Date m = c.getTime();
		return sdf.format(m);
	}
	/**
	* 
	* 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	* 
	* @param date
	* @return
	*/
	public static int getSeason(Date date) {
		int season = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
			case Calendar.JANUARY:
			case Calendar.FEBRUARY:
			case Calendar.MARCH:
			season = 1;
			break;
			case Calendar.APRIL:
			case Calendar.MAY:
			case Calendar.JUNE:
			season = 2;
			break;
			case Calendar.JULY:
			case Calendar.AUGUST:
			case Calendar.SEPTEMBER:
			season = 3;
			break;
			case Calendar.OCTOBER:
			case Calendar.NOVEMBER:
			case Calendar.DECEMBER:
			season = 4;
			break;
			default:
			break;
		}
		return season;
	}
	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(Date date1, Date date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}
	
	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddhhmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 
	 * @param args
	 */
	public static boolean RightDate(String date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		;
		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		try {
			sdf.parse(date);
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	private static String String(Date currentTime) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 计算2个时间工作日
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long getWorkdayTimeInMillis(long start, long end){
		//如果起始时间大于结束时间，将二者交换
		if(start>end){
			long temp = start;
			start = end;
			end = temp;
		}
		//根据参数获取起始时间与结束时间的日历类型对象
		Calendar sdate = Calendar.getInstance();
		Calendar edate = Calendar.getInstance();
		sdate.setTimeInMillis(start);
		edate.setTimeInMillis(end);
		//如果两个时间在同一周并且都不是周末日期，则直接返回时间差，增加执行效率
		if(sdate.get(Calendar.YEAR)==edate.get(Calendar.YEAR)
				&& sdate.get(Calendar.WEEK_OF_YEAR)==edate.get(Calendar.WEEK_OF_YEAR)
				&& sdate.get(Calendar.DAY_OF_WEEK)!=1 && sdate.get(Calendar.DAY_OF_WEEK)!=7
				&& edate.get(Calendar.DAY_OF_WEEK)!=1 && edate.get(Calendar.DAY_OF_WEEK)!=7){
			return new Long(end-start);
		}
		//首先取得起始日期与结束日期的下个周一的日期
		Calendar snextM = getNextMonday(sdate);
		Calendar enextM = getNextMonday(edate);
		//获取这两个周一之间的实际天数
		int days = getDaysBetween(snextM, enextM);
		//获取这两个周一之间的工作日数(两个周一之间的天数肯定能被7整除，并且工作日数量占其中的5/7)
		int workdays = days/7*5;
		//获取开始时间的偏移量
		long scharge = 0;
		if(sdate.get(Calendar.DAY_OF_WEEK)!=1 && sdate.get(Calendar.DAY_OF_WEEK)!=7){
			//只有在开始时间为非周末的时候才计算偏移量
			scharge += (7-sdate.get(Calendar.DAY_OF_WEEK))*24*3600000;
			scharge -= sdate.get(Calendar.HOUR_OF_DAY)*3600000;
			scharge -= sdate.get(Calendar.MINUTE)*60000;
			scharge -= sdate.get(Calendar.SECOND)*1000;
			scharge -= sdate.get(Calendar.MILLISECOND);
		}
		//获取结束时间的偏移量
		long echarge = 0;
		if(edate.get(Calendar.DAY_OF_WEEK)!=1 && edate.get(Calendar.DAY_OF_WEEK)!=7){
			//只有在结束时间为非周末的时候才计算偏移量
			echarge += (7-edate.get(Calendar.DAY_OF_WEEK))*24*3600000;
			echarge -= edate.get(Calendar.HOUR_OF_DAY)*3600000;
			echarge -= edate.get(Calendar.MINUTE)*60000;
			echarge -= edate.get(Calendar.SECOND)*1000;
			echarge -= edate.get(Calendar.MILLISECOND);
		}
		//计算最终结果，具体为：workdays加上开始时间的时间偏移量，减去结束时间的时间偏移量
		return workdays*24*3600000+scharge-echarge;
	}
	private static Calendar getNextMonday(Calendar cal){
		int addnum=9-cal.get(Calendar.DAY_OF_WEEK);
		if(addnum==8)addnum=1;//周日的情况
		cal.add(Calendar.DATE, addnum);
		return cal;
	}
	/**
	 * 获取两个日期之间的实际天数，支持跨年
	 */
	public static int getDaysBetween(Calendar start, Calendar end){
		if(start.after(end)){
			Calendar swap = start;
			start = end;
			end = swap;
		}
		int days = end.get(Calendar.DAY_OF_YEAR)- start.get(Calendar.DAY_OF_YEAR);
		int y2 = end.get(Calendar.YEAR);
		if (start.get(Calendar.YEAR) != y2) {
			start = (Calendar) start.clone();
			do {
				days += start.getActualMaximum(Calendar.DAY_OF_YEAR);
				start.add(Calendar.YEAR, 1);
			}while(start.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 获取某月的最后一天
	 *
	 */
	public static String getLastDayOfMonth(String strYearAndMonth)throws Exception {
		String[] yearAndMonth = strYearAndMonth.split("-");
		String strYear = yearAndMonth[0];
		String strMonth = yearAndMonth[1];
		int year = Integer.parseInt(strYear);
		int month = Integer.parseInt(strMonth);
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,year);
		//设置月份
		cal.set(Calendar.MONTH, month-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		//格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 获取某年最后一天日期
	 *
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static String getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();
		return dateToString(currYearLast,"yyyy-MM-dd");
	}

	/**
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getStrDateByDefinePatternt(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date parseStr(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateStr);
	}

	/**
	 * 判断指定日期时间段内哪些日期是工作日，哪些不是
	 * @param startDate
	 * @param endDate
	 * @return 返回的格式 {"2021-03-06":false,"2021-03-08":true}，属于工作日则true，反之亦然
	 */
//	public static Map<String,Boolean> judgeWorkDates(String startDate,String endDate){
//		LocalDate startLocalDate = LocalDate.parse(startDate,dtf);
//		LocalDate endLocalDate = LocalDate.parse(endDate,dtf);
//
//		List<WhSpecialTime> workDates = whSpecialTimeMapper.getSpecifiedDates(startDate,endDate);
//		//将查询结果转换成map ，例{"2021-02-01":true},是工作日则true
//		Map<String,Boolean>  workDatesMap = new HashMap();
//		workDates.forEach(whSpecialTime ->{
//			String isHolidayStr = whSpecialTime.getIsHoliday();
//			Boolean isWorkDate;
//			if ("Y".equals(isHolidayStr)){
//				isWorkDate = false;
//			}else if ("N".equals(isHolidayStr)){
//				isWorkDate = true;
//			}else {
//				isWorkDate = null;
//			}
//			workDatesMap.put(sdf.format(whSpecialTime.getDay()),isWorkDate);
//		});
//
//		Map<String,Boolean> result = new HashMap<>();
//
//		//用于做比较的日期，由统计开始日期开始，每循环一次加一天，直到与统计日期相等则结束
//		LocalDate compareDate =  startLocalDate;
//		while (!compareDate.isAfter(endLocalDate)){
//
//			String compareDateStr = compareDate.format(dtf);
//
//			//The values are numbered following the ISO-8601 standard, from 1 (Monday) to 7 (Sunday).
//			int dayOfWeek = compareDate.getDayOfWeek().getValue();
//			Boolean isWorkDate;
//			//判断是否周末
//			if (dayOfWeek == 6 || dayOfWeek == 7){
//				//判断这个周末日期是否是工作日
//				isWorkDate = workDatesMap.get(compareDateStr) != null ? workDatesMap.get(compareDateStr): false;
//				result.put(compareDateStr,isWorkDate);
//
//			}else {
//				//判断非周末日期是否是假日
//				isWorkDate = workDatesMap.get(compareDateStr) != null ? workDatesMap.get(compareDateStr): true;
//				result.put(compareDateStr,isWorkDate);
//			}
//			compareDate = compareDate.plusDays(1);
//		}
//		return result;
//	}

	/**
	 *
	 * @param day
	 * @return
	 */
	public static Date strToDateTime(String day) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(day);
		return date;
	}


	/**
	 * 获取环比时间
	 * 简单环比规则
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static DatePeriod linkRelativeDateSimp(String startDateStr,String endDateStr) {
		DatePeriod datePeriod = new DatePeriod();
		datePeriod.setStartDateStr(startDateStr);
		datePeriod.setEndDateStr(endDateStr);

		datePeriod.setStartLocalDate(LocalDate.parse(startDateStr,dtf));
		datePeriod.setEndLocalDate(LocalDate.parse(endDateStr,dtf));

		/*
		* 环比开始时间是 开始时间倒推原时间跨度等长度再往前推一推，
		* 如 2021-03-01 至 2021-03-15 时间跨度是15天（尊重他们原有的包头包尾）
		* 参照 com.augurit.xmjg.monthly.service.impl.MonthlyReportServiceImpl.getOfficeCondition中的代码
		* 同比时间段则是  2021-02-13 至 2021-02-28
		*
		* */
		datePeriod.setStartLocalDateOfQoQ(datePeriod.getStartLocalDate()
				.plusDays(-2 - datePeriod.getStartLocalDate().until(datePeriod.getEndLocalDate(),ChronoUnit.DAYS)));
		// 环比结束时间是，开始时间 startDateStr 的前一天
		datePeriod.setEndLocalDateOfQoQ(datePeriod.getStartLocalDate().plusDays(-1));


		datePeriod.setStartDateOfQoQStr(datePeriod.getStartLocalDateOfQoQ().format(dtf));
		datePeriod.setEndDateOfQoQStr(datePeriod.getEndLocalDateOfQoQ().format(dtf));

		return datePeriod;
	}


	/**
	 * 根据给定时间段
	 * 推算环比时间段
	 *
	 * 1、查询时间段是某月1日到这个月的最后一天的，向前推移一个自然月获取环比数据，如查询同年 2月1至2月28 环比数据为同年 1月1至1月31
	 * 2、开始时间不是1号，但是时间跨度是一个月的，向前推移一个自然月获取环比数据，如查询同年 2月15至3月15 环比数据为同年 1月15至2月15
	 * 3、时间跨度小于一个月或时间跨度超过一个月的就向前推相同的自然日来做环比，如查询 2020-02-15至2021-03-1 需向前推移 380天，环比数据 2019-01-31至2020-02-15
	 * @param startDateStr 开始时间
	 * @param endDateStr 结束时间
	 * @return DatePeriod
	 */
	public static DatePeriod linkRelativeDateComp(String startDateStr,String endDateStr) {
		return linkRelativeDateComp(LocalDate.parse(startDateStr,dtf),LocalDate.parse(endDateStr,dtf));
	}


	public static DatePeriod linkRelativeDateComp(LocalDate startLocalDate,LocalDate endLocalDate) {
		DatePeriod datePeriod = new DatePeriod();
		datePeriod.setStartLocalDate(startLocalDate);
		datePeriod.setEndLocalDate(endLocalDate);
		return linkRelativeDateComp(datePeriod);
	}

	/**
	 *
	 * 根据给定时间段
	 * 推算环比时间段
	 * 复杂环比规则
	 * 1、查询时间段是某月1日到这个月的最后一天的，向前推移一个自然月获取环比数据，如查询同年 2月1至2月28 环比数据为同年 1月1至1月31
	 * 2、开始时间不是1号，但是时间跨度是一个月的，向前推移一个自然月获取环比数据，如查询同年 2月15至3月15 环比数据为同年 1月15至2月15
	 * 3、时间跨度小于一个月或时间跨度超过一个月的就向前推相同的自然日来做环比，如查询 2020-02-15至2021-03-1 需向前推移 380天，环比数据 2019-01-31至2020-02-15
	 * @param datePeriod
	 * @return DatePeriod
	 */
	public static DatePeriod linkRelativeDateComp(DatePeriod datePeriod) {

		// 获取查询时间段
		LocalDate startDate = datePeriod.getStartLocalDate();
		LocalDate endDate = datePeriod.getEndLocalDate() ;

		datePeriod.setStartDateStr(startDate.format(dtf));
		datePeriod.setEndDateStr(startDate.format(dtf));


		// 获取开始时间是一个月当中的第几天
		int startDateDayOfMonth = startDate.getDayOfMonth();
		// 获取开始时间所在月的最后一天
		LocalDate lastDayOfMonth = startDate.with(TemporalAdjusters.lastDayOfMonth());
		// 结束日期在开始时间所在月的最后一天的前面则 -1，相等则 0，后面则 1
		int endDateCompareToLastDayOfMonth = endDate.compareTo(lastDayOfMonth);
		// 结束日期在开始时间累加一个自然月后的前面则 -1，相等则 0，后面则 1
		int compare = endDate.compareTo(startDate.plusMonths(1));

		// 环比时间-开始日期
		LocalDate startDateForQoQ = null;
		// 环比时间-结束日期
		LocalDate endDateForQoQ = null;

		// 时间跨度为一个月的，向前推移一个自然月获取环比数据，
		if ((startDateDayOfMonth == 1 && endDateCompareToLastDayOfMonth == 0)
				|| (startDateDayOfMonth != 1 && compare == 0)) {
			startDateForQoQ = startDate.plusMonths(-1);
			endDateForQoQ = startDate.minusDays(1);

			// 时间跨度小于或者大于一个月的，向前推相同的自然日来做环比
		} else if ((startDateDayOfMonth == 1 && endDateCompareToLastDayOfMonth < 0)
				|| (startDateDayOfMonth != 1 && compare < 0)
				|| compare > 0) {
			long until = startDate.until(endDate, ChronoUnit.DAYS) + 1;

			startDateForQoQ = startDate.plusDays(-until);
			endDateForQoQ = startDate.minusDays(1);
		}

		datePeriod.setStartLocalDateOfQoQ(startDateForQoQ);
		datePeriod.setEndLocalDateOfQoQ(endDateForQoQ);

		datePeriod.setStartDateOfQoQStr(startDateForQoQ.format(dtf));
		datePeriod.setEndDateOfQoQStr(endDateForQoQ.format(dtf));
		return datePeriod;
	}

	public static void main(String[] args) {

		DatePeriod datePeriod1 = linkRelativeDateComp("2021-04-11", "2021-04-20");
		System.out.println("StartDateStr==" + datePeriod1.getStartDateStr()
				+ File.separator + "EndDateStr==" + datePeriod1.getEndLocalDate()
				+ File.separator + "StartDateOfQoQStr" + datePeriod1.getStartDateOfQoQStr()
				+ File.separator + "EndDateOfQoQStr" + datePeriod1.getEndDateOfQoQStr());
	}

	}