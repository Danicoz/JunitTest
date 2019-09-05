package com.cattsoft.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateHelper {
	// Canleanda
	public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyyyMMddHH = "yyyyMMddHH";
	public static final String yyyyMMddHHmm = "yyyyMMddHHmm";

	public static long getCurDate() {
		return Long.parseLong(date2Str(new Date(), yyyyMMdd));
	}

	public static long getCurTime() {
		return Long.parseLong(date2Str(new Date(), yyyyMMddHHmmss));
	}

	public static long getCurTimeSSS() {
		return Long.parseLong(date2Str(new Date(), yyyyMMddHHmmssSSS));
	}

	/**
	 * 日期转字符串
	 */
	public static String date2Str(Date d, String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(d);
	}

	/**
	 * 字符串转日期
	 * 
	 * @throws ParseException
	 */
	public static Date convertDate(String dateTimeStr, String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		Date d = null;
		try {
			d = sdf.parse(dateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 日期字符串转日期字符串
	 */
	public static String datetimestr2datetimestr(String dateTimeStr, String sdateFormat, String tdateFormat) {
		String datestr = null;
		DateFormat sdf = new SimpleDateFormat(sdateFormat);
		Date d = null;
		try {
			d = sdf.parse(dateTimeStr);
			datestr = new SimpleDateFormat(tdateFormat).format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return datestr;
	}

	public static long getDate(Date d) {
		return Long.parseLong(date2Str(d, yyyyMMdd));
	}

	public static long getTime(Date d) {
		return Long.parseLong(date2Str(d, yyyyMMddHHmmss));
	}

	public static long getTimeSSS(Date d) {
		return Long.parseLong(date2Str(d, yyyyMMddHHmmssSSS));
	}

	public static long getDateTime(Date d, int range, String format) {
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(d);
		tmp.add(Calendar.DATE, -range);
		return Long.parseLong(date2Str(tmp.getTime(), format));
	}

	/**
	 * 得到dateTimeStr日期的前几天、后几天
	 */
	public static long getDate(Date d, int day) {
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar tmp = Calendar.getInstance();

		tmp.setTime(d);
		tmp.add(Calendar.DATE, day);
		return Long.parseLong(date2Str(tmp.getTime(), yyyyMMddHHmmss));
	}

	public static String getDate(Date d, int range, String format) {
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar tmp = Calendar.getInstance();

		tmp.setTime(d);
		tmp.add(Calendar.DATE, -range);
		return date2Str(tmp.getTime(), format);
	}

	/**
	 * 得到dateTimeStr日期的前几天、后几天
	 */
	public static long getDate(String dateTimeStr, int day) {
		long date = 0;
		DateFormat format = new SimpleDateFormat(yyyyMMdd);
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar tmp = Calendar.getInstance();
		try {
			tmp.setTime(format.parse(dateTimeStr));
			tmp.add(Calendar.DATE, day);
			date = Long.parseLong(date2Str(tmp.getTime(), yyyyMMdd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 得到两个日期相隔的日期区间段字符串
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param dateFroamt
	 *            日期格式字符串
	 * @throws ParseException
	 */
	public static List<String> getDateInterval(String startDateTimeStr, String endDateTimeStr) {
		List<String> dateString = new ArrayList<String>();
		DateFormat format = new SimpleDateFormat(yyyyMMdd);
		// 设置起始时间段(得到的日期会大于起始时间段一天)
		Calendar calStartDate = Calendar.getInstance();
		try {
			calStartDate.setTime(format.parse(startDateTimeStr));
			calStartDate.add(Calendar.DATE, -1);
			// 设置结束时间段
			Calendar calEndDate = Calendar.getInstance();
			calEndDate.setTime(format.parse(endDateTimeStr));
			while (calStartDate.before(calEndDate)) {
				calStartDate.add(Calendar.DAY_OF_YEAR, 1);
				dateString.add(format.format(calStartDate.getTime()));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

	/**
	 * 
	 * @param d
	 * @param range
	 * @return
	 */
	public static long getTime(Date date, int range) {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, -range);
		String str1 = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		long startTime = Long.parseLong(str1 + "0000");
		return startTime;
	}

	public static Long getTime(long mills, String dateFormatStr) {
		Date date = new Date(mills);
		return Long.parseLong(date2Str(date, dateFormatStr));
	}

	// gives back today at yyyy/MM/dd HH:mm:ss.000
	// gives back today at yyyy/MM/dd HH:mm:00.000
	// gives back today at yyyy/MM/dd HH:00:00.000
	// gives back today at yyyy/MM/dd 00:00:00.000
	// gives back today at yyyy/MM/01 00:00:00.000
	// gives back today at yyyy/01/01 00:00:00.000
	public static Date truncDate(Date date, int flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (flag) {
		case 1:
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case 2:
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case 3:
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case 4:
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.HOUR, 0);
			break;
		case 5:
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			break;
		case 6:
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.MONTH, 0);
			break;
		default:
		}
		return cal.getTime();
	}

	public static Date getCycleStartTime(Date date, int mpType) throws Exception {
		Calendar cal = Calendar.getInstance();
		if (date != null) {
			cal.setTime(date);
		}
		cal.setTime(truncDate(cal.getTime(), 2));
		if (mpType == 44640) {
			cal.setTime(truncDate(cal.getTime(), 5));
		} else if (mpType == 10080) {// 处理周的时间格式
			cal.setFirstDayOfWeek(Calendar.MONDAY);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 周一为一周的开始
			cal.setTime(truncDate(cal.getTime(), 4));
		} else if (mpType == 1440) {
			cal.setTime(truncDate(cal.getTime(), 4));
		} else if (mpType == 60) {
			cal.setTime(truncDate(cal.getTime(), 2));
		} else if (mpType == 30) {// 处理周的时间格式
			int min = cal.get(Calendar.MINUTE) / 30;
			min = 30 * min;
			cal.set(Calendar.MINUTE, min);
		} else if (mpType == 15) {// 处理周的时间格式
			int min = cal.get(Calendar.MINUTE) / 15;
			min = 15 * min;
			cal.set(Calendar.MINUTE, min);
		} else if (mpType == 5) {// 处理周的时间格式
			int min = cal.get(Calendar.MINUTE) / 5;
			min = 5 * min;
			cal.set(Calendar.MINUTE, min);
		} else {
			throw new Exception("不支持的周期类型" + mpType);
		}
		return cal.getTime();
	}

	public static Date getLastCycleTime(Date date, int mptype, int range) {
		Calendar tmp = Calendar.getInstance();
		tmp.setTime(date);
		switch (mptype) {
		case 1440:
			tmp.add(Calendar.DATE, range);
			break;
		case 10080:
			tmp.add(Calendar.WEEK_OF_YEAR, range);
			break;
		case 44640:
			tmp.add(Calendar.MONTH, range);
			break;
		}
		return tmp.getTime();
	}

	/**
	 * 计算日期是星期几
	 * 
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static int getDayOfWeek(String day) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new SimpleDateFormat("yyyyMMdd").parse(day));
		int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		if (dayofweek == 1) {
			dayofweek = 7;
		} else {
			dayofweek = dayofweek - 1;
		}
		return dayofweek;
	}

	public static void main(String args[]) throws Exception {
		// System.out.println(getCurDate());
		// System.out.println(getCurTime());
		// System.out.println(getCurTimeSSS());
		// System.out.println(date2Str(new Date(),yyyyMMddHHmmssSSS));
		// System.out.println(convertDate("20120304",yyyyMMdd));
		 System.out.println(datetimestr2datetimestr("20120304",yyyyMMdd,"yyyy-MM-dd HH:mm:ss"));
		//
		// System.out.println(StringHelper.getString(getDateInterval("20140328","20140402"),","));
		// System.out.println(getDate("20140401",-1));
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmmss");
		// System.out.println(sd.parse("20141106050240"));

		// System.out.println(getTime(getCycleStartTime(sd.parse("20141106054740"), 1440)));
		// System.out.println(getTime(getLastCycleTime(getCycleStartTime(sd.parse("20150106054740"), 10080), 10080, 1)));

		String start = DateHelper.date2Str(DateHelper.getCycleStartTime(
				DateHelper.convertDate("20150105", "yyyyMMdd"), 10080), "yyyyMMdd");
		String end = DateHelper.date2Str(DateHelper.getLastCycleTime(
				DateHelper.convertDate(start, "yyyyMMdd"), 10080, 1), "yyyyMMdd");

		System.out.println(start);
		System.out.println(end);

		System.out.println(DateHelper.getDayOfWeek("20150711"));

	}
}
