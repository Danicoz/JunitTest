package com.cattsoft.statictest;

public class SubClass extends ParentClass {
		private static String sub_static = "�Ӿ�̬����";
		
		{
			System.out.println("�����ʼ���飡");
		}
		
		static{
			System.out.println(sub_static);
			System.out.println("���ྲ̬��ʼ���飡");
		}
		
		/*
		 ���ྲ̬����
		���ྲ̬��ʼ���飡
		�Ӿ�̬����
		���ྲ̬��ʼ���飡
		�����ʼ������飡
		�����ʼ���飡
		 */
		public static void main(String[] args) {
			new SubClass();
		}
		
}
