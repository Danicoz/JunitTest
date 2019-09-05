package com.mainshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class LeapYear {

	public static void main(String[] args) {
		
		//测试是否是闰年
		LeapYear bean = new LeapYear();
		bean.testLeapYear();

	}
	
	@Test
	public void testLeapYear(){
		try {
			System.out.println("请输入一个年份：");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int year = Integer.valueOf(br.readLine());
			
			if(year%4==0 && year%100 != 0 || year%400==0){
				System.out.println(year + "是闰年");
			}else{
				System.out.println(year + "不是闰年");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//1！+2！+3！+……+10！
	@Test
	public void testCount(){
	
		int sum = 0;
		int n = 1;
		for(int i=1; i<=10; i++){
			n = n*i;
			sum = sum + n;
		}
		System.out.println("sum=" + sum);
	}
	
	
	
	
}
