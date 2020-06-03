package com.aug;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间段生成
 * @author Deepin
 *
 */
public class SjdGenUtil {
    //
    private static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Sjd> genSjd() throws Exception{
        return genSjd("2018-06-01");
    }

    //
    public static List<Sjd> genSjd(String startDateStr) throws Exception{
        Calendar start = Calendar.getInstance();
        start.setTime( str2DateYmd( startDateStr ) );
        return startGenSjd(start);
    }

    public static List<Sjd> genSjd(Date startDate) throws Exception{
        return null;
    }
    /**
     * 生成时间段的方法
     * @param start
     * @return
     */
    private static List<Sjd> startGenSjd(Calendar start) throws Exception{
        List<Sjd> list = new ArrayList<Sjd>();//保存结果
        Calendar cur = Calendar.getInstance();
        //循环月
        while( start.getTimeInMillis() < cur.getTimeInMillis() ){
            Sjd sjd = new Sjd();
            sjd.setKssjDate(start.getTime());
            sjd.setKssj( date2StrYmd(start.getTime()));

            start.set(Calendar.DAY_OF_MONTH, start.getActualMaximum(Calendar.DAY_OF_MONTH));
            sjd.setJssjDate(start.getTime());
            sjd.setJssj( date2StrYmd(start.getTime()));

            start.add(Calendar.DAY_OF_YEAR, 1);

            list.add( sjd );
        }

        //循环年
        start.setTime( str2DateYmd("2018-06-01") );
        while(start.getTimeInMillis() < cur.getTimeInMillis()){
            Sjd sjd = new Sjd();
            sjd.setKssjDate(start.getTime());
            sjd.setKssj( date2StrYmd(start.getTime()));

            start.set(Calendar.DAY_OF_YEAR, start.getActualMaximum(Calendar.DAY_OF_YEAR));
            sjd.setJssjDate(start.getTime());
            sjd.setJssj( date2StrYmd(start.getTime()));

            start.add(Calendar.YEAR, 1);
            list.add( sjd );
        }

        //本月结束
        Calendar curMonth = Calendar.getInstance();
        curMonth.set(Calendar.DAY_OF_MONTH, curMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
        //本年度至今
        String curYearStr = curMonth.get(Calendar.YEAR)+"-01-01";
        Sjd sjd = new Sjd();
        sjd.setKssj( curYearStr );
        sjd.setKssjDate(  str2DateYmd(curYearStr) );

        sjd.setJssj(  date2StrYmd( curMonth.getTime()) );
        sjd.setJssjDate( str2DateYmd( sjd.getJssj() )  );
        list.add( sjd );
        //历史至今
        Sjd sjd2 = new Sjd();
        sjd2.setKssj( "2018-06-01" );
        sjd2.setKssjDate( str2DateYmd( "2018-06-01" ) );

        sjd2.setJssj(  date2StrYmd(curMonth.getTime()));
        sjd2.setJssjDate( str2DateYmd( sjd.getJssj() )  );
        list.add( sjd2 );

//		for(Sjd sjd3 : list){
//			System.out.print(sjd3+"-> ");
//			System.out.println(date2StrYmd(sjd3.getKssjDate())+", "+date2StrYmd(sjd3.getJssjDate()));
//		}

        return list;
    }


    public static Date str2DateYmd(String str) throws Exception {
        return dateFmt.parse(str);
    }

    public static String date2StrYmd(Date date) {
        return dateFmt.format(date);
    }
    public static String date2StrYmdHms(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }

    /**
     *
     * @param sjdList
     * @param blsj
     * @return
     */
    public static List<Sjd> subSjd(List<Sjd> sjdList, Date blsj){
        List<Sjd> subList = new ArrayList<Sjd>();
        for(int i=0; i<sjdList.size(); i++){
            if( sjdList.get(i).getJssjDate().getTime() >= blsj.getTime() ){
                subList.add(sjdList.get(i));
            }
        }
//		for(Sjd sjd : subList){
//			System.out.print( sjd.getKssj()+","+sjd.getJssj()+",-> ");
//			System.out.println( date2StrYmd(sjd.getKssjDate())+","+date2StrYmd(sjd.getJssjDate()));
//		}
//		System.out.println("----------------");
        return subList;
    }

    public static void main(String args[]) {
        try{
            List<Sjd> all = SjdGenUtil.genSjd();
            System.out.println("----------------");

            for (Sjd sjd : all) {
                System.out.println("sjd=" + sjd.toString());
            }

//            SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//            String strDate = "2019-03-29 15:46:26";
//
//            SjdGenUtil.subSjd(all, dateFmt.parse(strDate));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
