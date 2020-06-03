package com.cattsoft.calendar;

import com.cattsoft.utils.DateHelper;

import java.util.Calendar;

/*
 *Calendar 日期测试类
 * */
public class CalendarTest {
    public static Calendar cld = null;
    static{
        cld = Calendar.getInstance();
    }

    public static void main(String[] args) {
        CalendarTest cldTest = new CalendarTest();
        cldTest.testCalendarAttribute();
    }

//    Calendar 属性
    public void testCalendarAttribute(){

        System.out.println(cld.getTime() + " " + cld.getTimeInMillis());
        System.out.println("年=" + cld.get(Calendar.YEAR));
        System.out.println("月=" + cld.get(Calendar.MONTH));
        System.out.println("日=" + cld.get(Calendar.DATE));
        System.out.println("分=" + cld.get(Calendar.MINUTE));
        System.out.println("秒=" + cld.get(Calendar.MILLISECOND));

        System.out.println("当年的第几天=" + cld.get(Calendar.DAY_OF_YEAR));
        System.out.println("当月的第几天=" + cld.get(Calendar.DAY_OF_MONTH));
        System.out.println("当周的第几天=" + cld.get(Calendar.DAY_OF_WEEK) + " 注：从周日算起");
        System.out.println("当周的第几天=" + cld.get(Calendar.DAY_OF_WEEK_IN_MONTH) + " 注：从周一算起");
        System.out.println("星期几=" + cld.get(Calendar.DAY_OF_WEEK) + " 注：从周日=1算起");

        System.out.println("获取当前日期 日最大值=" + cld.getActualMaximum(Calendar.DATE));
        System.out.println("获取当前日期 日最大值=" + cld.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("获取当前日期 月最小值=" + cld.getActualMinimum(Calendar.DAY_OF_MONTH));

        //改变日期方法
        cld.set(Calendar.YEAR, 2018);
        System.out.println("重新定义年份=" + cld.getTime());
        cld.set(Calendar.MONTH, 12);
        System.out.println("重新定义月份=" + cld.getTime());
        cld.set(Calendar.DATE,15);
        System.out.println("重新定义日=" + cld.getTime());
        cld.setTime(DateHelper.convertDate("2018-01-01","yyyy-MM-dd"));
        System.out.println("重新定义日期=" + cld.getTime());

        //年月日增减运算
        cld.add(Calendar.YEAR,2);
        System.out.println("当前日期增加两年" + cld.getTime());
        cld.add(Calendar.YEAR, -1);
        System.out.println("当前日期减少一年" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));

        cld.add(Calendar.MONTH, 2);
        System.out.println("当前日期增加两月" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));

        cld.add(Calendar.DATE,2);
        System.out.println("当前日期增加两天" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));
        cld.add(Calendar.DATE,-3);
        System.out.println("当前日期减少三天" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));
    }

}
