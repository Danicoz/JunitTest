package com.aug;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class GetDybzspsxbm {
    //Ä£Äâ¹¦ÄÜ

    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("#.00");
        int wdCityNum = 3642;
        int fwNum = 10505;
        String wdPercent;
        wdPercent = df.format(((float)wdCityNum/fwNum)*100);

        System.out.println("wdPercent=" + wdPercent);
        System.out.println(df.format((float)(wdCityNum/fwNum)));

        System.out.println(wdCityNum/fwNum);

        Object num = 555;
        Double db = Double.valueOf(num.toString());

        System.out.println("db>>>>" + db);

        int a=7;
        int b=9;
        Object c = 0;

        c = df.format(0);
        Object d = df.format((0/b)*100);
        System.out.println("d>>>>" + d);
        System.out.println("0000>" + df.format(0) + " c>>>>>" + c);

        System.out.println(df.format((float)a/b));
        System.out.println(df.format(a/(float)b));
        System.out.println(df.format((float)a/(float)b));
        System.out.println(df.format((float)(a/b)));


        Map<String, Object> map = new HashMap<String, Object>();

        String str = "0110,0140,0140,0140,0010,0010,0010,0010,0010,0010,0110,0110,0050,0440,0440,0110,0370,0110,0050,0110,0370,0010,0010,0010,0010,0180,0180,0050,0440,0140,0010,0010,0010,0010,0370,0110,0010,0440,0010,0050,0170,0010,0360,0140,0170,0170,0170,0110,0140,0170,0170,0170,0170,0140,0170,0360,0050,0170,0140,0440,0440,0440,0050,0370,0110,0440,0440,0440,0110,0110,0440,0370,0370,0140,0150,0150,0150,0140,0010,0440,0370,0370,0370,0440,0440,0370,0010,0010,0440,0020,0020,0440,0440,0440,0440,0440,0440,0440,0440,0440,0440,0440,0020,0020,0020,0020,0110,0440,0140,0170,0140,0020,0010,0140,0360,0110,0140,0020,0140,0360,0170,0360,0140,0360,0110,0170,0170,0110,0010,0020,0110,0140,0140,0020,0140,0010,0140,0140,0110,0180,0140,0020,0110,0110,0070,0140,0020,0110,0170,0110,0020,0110,0020,0110,0140,0360,0140,0140,0110,0110,0170,0020,0140,0140,0140,0140,0010,0020,0110,0140,0010,0180,0110,0170,0360,0010,0360,0180,0360,0170,0110,0170,0110,0110,0110,0140,0140,0140,0020,0010,0020,0010,0110,0110,0140,0010,0110,0140,0170,0110,0170,0360,0170,0360,0010";


        str = str.replace("9990,", "").replace("9990","");
        String[] str1 = str.split(",");
        List<String> list = Arrays.asList(str1);
        Set<String> set1 = new HashSet<>(list);
        System.out.println("set1=" + set1.size());



        Integer i = "0090,0020,0350,0120,0340,0150,0480,0140,0360,0030,0170,0060,0160,0270,0050,6030,0070,0440,0330,0100,"
                .lastIndexOf(",");
        System.out.println(i);

        Set<String> set = new HashSet<>();
        Collections.addAll(set, str1);

        System.out.println("set:" + set.size());

        for(String temp : set){
            System.out.print(temp + ",");
        }

    }

    @Test
    public void testOne(){
        String tableSchema = "pre_table_110000";
        System.out.println(tableSchema.substring(10, tableSchema.length()));
    }

}

