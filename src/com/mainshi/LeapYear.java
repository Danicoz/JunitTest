package com.mainshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class LeapYear {

	public static void main(String[] args) {
		
		//�����Ƿ�������
		LeapYear bean = new LeapYear();
		bean.testLeapYear();

	}
	
	@Test
	public void testLeapYear(){
		try {
			System.out.println("������һ����ݣ�");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int year = Integer.valueOf(br.readLine());
			
			if(year%4==0 && year%100 != 0 || year%400==0){
				System.out.println(year + "������");
			}else{
				System.out.println(year + "��������");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//1��+2��+3��+����+10��
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
