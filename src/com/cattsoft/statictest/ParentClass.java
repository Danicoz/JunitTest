package com.cattsoft.statictest;

public class ParentClass {

	private static String p_static = "���ྲ̬����";
	
	{
		System.out.println("�����ʼ������飡");
	}
	
	static{
		System.out.println(p_static);
		System.out.println("���ྲ̬��ʼ���飡");
	}
	
	public static void main(String[] args) {
		new ParentClass();//��̬����     ��̬��ʼ���飡     ��ʼ������飡   �ܣ� ��̬��ʼ���� > ��ʼ����
	}

}
	












