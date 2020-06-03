package com.cattsoft.calendar;

import com.cattsoft.utils.DateHelper;

import java.util.Calendar;

/*
 *Calendar ���ڲ�����
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

//    Calendar ����
    public void testCalendarAttribute(){

        System.out.println(cld.getTime() + " " + cld.getTimeInMillis());
        System.out.println("��=" + cld.get(Calendar.YEAR));
        System.out.println("��=" + cld.get(Calendar.MONTH));
        System.out.println("��=" + cld.get(Calendar.DATE));
        System.out.println("��=" + cld.get(Calendar.MINUTE));
        System.out.println("��=" + cld.get(Calendar.MILLISECOND));

        System.out.println("����ĵڼ���=" + cld.get(Calendar.DAY_OF_YEAR));
        System.out.println("���µĵڼ���=" + cld.get(Calendar.DAY_OF_MONTH));
        System.out.println("���ܵĵڼ���=" + cld.get(Calendar.DAY_OF_WEEK) + " ע������������");
        System.out.println("���ܵĵڼ���=" + cld.get(Calendar.DAY_OF_WEEK_IN_MONTH) + " ע������һ����");
        System.out.println("���ڼ�=" + cld.get(Calendar.DAY_OF_WEEK) + " ע��������=1����");

        System.out.println("��ȡ��ǰ���� �����ֵ=" + cld.getActualMaximum(Calendar.DATE));
        System.out.println("��ȡ��ǰ���� �����ֵ=" + cld.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("��ȡ��ǰ���� ����Сֵ=" + cld.getActualMinimum(Calendar.DAY_OF_MONTH));

        //�ı����ڷ���
        cld.set(Calendar.YEAR, 2018);
        System.out.println("���¶������=" + cld.getTime());
        cld.set(Calendar.MONTH, 12);
        System.out.println("���¶����·�=" + cld.getTime());
        cld.set(Calendar.DATE,15);
        System.out.println("���¶�����=" + cld.getTime());
        cld.setTime(DateHelper.convertDate("2018-01-01","yyyy-MM-dd"));
        System.out.println("���¶�������=" + cld.getTime());

        //��������������
        cld.add(Calendar.YEAR,2);
        System.out.println("��ǰ������������" + cld.getTime());
        cld.add(Calendar.YEAR, -1);
        System.out.println("��ǰ���ڼ���һ��" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));

        cld.add(Calendar.MONTH, 2);
        System.out.println("��ǰ������������" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));

        cld.add(Calendar.DATE,2);
        System.out.println("��ǰ������������" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));
        cld.add(Calendar.DATE,-3);
        System.out.println("��ǰ���ڼ�������" + DateHelper.date2Str(cld.getTime(),"yyyy-MM-dd HH:mm:ss"));
    }

}
