package com.aug.string;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class testString {

    @Test
    public void testDouble(){
        Double d1 = Double.parseDouble("0");
        if(d1 == 0){
            System.out.println("进来会等于0！");
        }

        Map<String, Object> map = new HashMap<>();

        double d2 = 0;
        map.put("per", d2);
        System.out.println(map.get("per"));

        Object str = null;

        String str1 = (String)str;
        System.out.println(str1);
    }

    @Test
    public void testLong(){
        String str = "1111";
        Long temp  = Long.parseLong(str);
        System.out.println(temp);

        Integer num1 = new Integer(11);
        Integer num2 = new Integer(22);
        System.out.println(num1.intValue() - num2.intValue());
    }

    @Test
    public void testBigInteger(){
        String str = "0.0";
        double bb = 0.0;
        int temp = (int)bb;
        BigInteger aa = BigInteger.valueOf(temp);
        System.out.println(Double.valueOf(str).intValue());
        System.out.println(aa);



    }


}
