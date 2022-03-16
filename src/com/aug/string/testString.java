package com.aug.string;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
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

        //ddddrrddddd
        System.out.println("ddddrrddddd");

        int num = 2<<3;
        System.out.println("2<<3===" + num);

        final String finalStr = "aa";
        //finalStr = finalStr + "bb";
        System.out.println("finalStr===" + finalStr);

        System.gc();
    }

    @Test
    public void testBreak(){

        ok:
        for(int i = 0;i < 10; i++){
            for (int j = 0;j < 10;j++){
                System.out.println("i=" + i + ",j=" + j);
                if (j == 5){
                    break ok;
                }
            }
        }
    }

    @Test
    public void testSwap(){

        Student student = new Student();
        System.out.println(student.getName());
        swap(student);
        System.out.println(student.getName());
    }

    public void swap(Student stu){
        stu.setName("测试.");
        System.out.println(stu.getName());
    }
    public class Student{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testFile() throws IOException, ClassNotFoundException {
        File file = new File("D:\\aaa.sql");
        System.out.println(file.exists());

        if(!file.exists()){
            file.createNewFile();
        }

        //反射
        Class class1 = Student.class;
        System.out.println(class1.getName());

        Student stu = new Student();
        Class class2 = stu.getClass();
        System.out.println(class2.getName());

        Class class3 = Class.forName("com.aug.domain.Student");
        System.out.println(class3.getName());
    }

    @Test
    public void testStringBuffer(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abcdefg");
        System.out.println(stringBuffer.reverse());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abcdefg");
        System.out.println(stringBuilder.reverse());

    }

}
