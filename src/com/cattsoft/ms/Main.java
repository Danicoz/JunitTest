package com.cattsoft.ms;

public class Main {
	 public static void main(String[] args) { 
		 int arr[]={9,1,5,2,2,5,1,8,7,9,8}; 
		 System.out.println(getOne(arr));
	 }
	 
	 

	 /**
	  * ������㣺����������ͬʱΪ0����ͬ��Ϊ1
	  * ���������ɶ����ƽ������㣺
	  * arr[3]=5 =(101)B��arr[2]=4=(100)B��
	  * 1^1=0 0^0=0 1^0=1
	  * (101)^(100)=001
	  * �����arr[3]��Ϊ1 
	  */
	 public static int getOne(int[] array) { 
		 int n = array.length; 
		 if(n == 1) 
			 return array[0]; 
		 
		 int result = array[0]; 
		 for(int i = 1 ; i < n ; i++) { 
			 System.out.println("result:" + result);
			 result ^= array[i];//�������Ŀɽ�����  
		 } 
		 
		 return result; 
		 } 
 }
