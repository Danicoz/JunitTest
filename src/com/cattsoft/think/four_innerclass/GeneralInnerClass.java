package com.cattsoft.think.four_innerclass;


/*
  	�ܣ�
  		1�������ڲ���û����static�����Ҷ������ⲿ��������;
  		2�������ڲ������ֱ�ӷ����ⲿ���ʵ��������ʵ������(�����ڲ������ʱ������ָ���ⲿ�������);
  		3�������ڲ����������ⲿ��Ķ�������ʱ����  �ⲿ������.this ���з���;
  		4�������ڲ�����󴴽�����һ���Ե����ⲿ��ķ��������ڲ��࣬�ڶ���  �ⲿ��ʵ��.new ���ɡ�
 */
public class GeneralInnerClass {

	private int x = 10;
	
	class MyInner{
		private String y = "Hello!";
		
		public void InnerMethod(){
			System.out.println("�ڲ���ı���y=" + y);
			System.out.println("�ⲿ��ı���x=" + x);
			outerMethod();
			System.out.println("�����ⲿ���������ⲿ��ı���x=" + GeneralInnerClass.this.x);
		}
	}
	
	public void outerMethod(){
		x++;
	}
	public MyInner makeInner(){
		return new MyInner();
	}
	
	public static void main(String[] args) {
		GeneralInnerClass gi = new GeneralInnerClass();
		//GeneralInnerClass.MyInner in = gi.new MyInner();
		//in.InnerMethod();
		MyInner in4 = gi.makeInner();
		in4.InnerMethod();
	}
	
}
