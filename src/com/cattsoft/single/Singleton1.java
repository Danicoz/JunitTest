package com.cattsoft.single;

/**
 * ����ʽ������
 * 	���������ַ�ʽ�Ƚϳ��ã������ײ�����������
 * 	�ŵ㣺û�м�����ִ��Ч�ʻ���ߡ�
 * 	ȱ�㣺�����ʱ�ͳ�ʼ�����˷��ڴ档
 */
public class Singleton1 {

	private static Singleton1 instance = new Singleton1();
	private Singleton1(){}
	
	public static Singleton1 getInstance(){
		return instance;
	}
}
