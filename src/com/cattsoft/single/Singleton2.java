package com.cattsoft.single;

public class Singleton2 {

	/**
	 * ����ʽ���̲߳���ȫ
	 * �Ƿ���̰߳�ȫ����
	 * ʵ���Ѷȣ���
	 * ���������ַ�ʽ���������ʵ�ַ�ʽ������ʵ������������ǲ�֧�ֶ��̡߳���Ϊû�м��� synchronized�������ϸ��������������㵥��ģʽ��
	 */
	private static Singleton2 instance;
	private Singleton2(){}
	
	public static Singleton2 getInstance(){
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}
	
	/**
	 * ����ʽ���̰߳�ȫ
	 * �ŵ㣺��һ�ε��òų�ʼ���������ڴ��˷ѡ�
	 * ȱ�㣺������� synchronized ���ܱ�֤��������������Ӱ��Ч�ʡ�
	 */
	public static synchronized Singleton2 getInstance1(){
		
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}
	
}
