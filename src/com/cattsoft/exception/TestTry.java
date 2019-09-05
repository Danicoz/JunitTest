package com.cattsoft.exception;

import java.io.IOException;

public class TestTry {

	/**
	 * �ܣ�
	 *    ��try����Ϊ�˲�׽�쳣�����쳣�ģ�
	 * 	  ��try�������Լ����������г�����׽�Ҳ���ִ�к���Ĵ��룻
	 *    ���ڲ�try�������д������try�������ǻ�ִ�У�
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		try {
			System.out.println("------���try------");
			//innerTry2();
			innerTry();
			//innerTry1();
											//int j =0;j = 100/j;		
			System.out.println("---���try�������ڲ�try��ִ�еĴ���---");
		} catch (Exception e) {
			System.out.println("---���catch����-----" + e.getMessage());
		}finally{
			System.out.println("---���finally---");
		}
		
		System.out.println("�������ִ����");
		
	}

	/**
	 * �����ڲ�try ����
	 * �ܣ� �Ჶ׽�ڲ�������������ȷ�򲻻Ჶ׽�����������붼������;
	 * 	     ���������׽�����������벻������;
	 */
	private static void innerTry() {
		
		try {
			System.out.println("------�ڲ�try------");
			int i = 0;
			System.out.println(100/i);
		} catch (Exception e) {
			System.out.println("---�ڲ�catch����-----" + e.getMessage());
		}finally{
			System.out.println("---�ڲ�finally---");
		}
		
	}
	
	
	/**
	 * �����ڲ�try ������������
	 * �ܣ� ���Ჶ׽�ڲ�������������ȷҲ�Ჶ׽���������벻������;
	 */
	private static void innerTry1() {
		
		try {
			System.out.println("------�ڲ�try------");
			int i = 0;
			System.out.println(100/i);
//		} catch (Exception e) {
//			System.out.println("---�ڲ�catch����-----" + e.getMessage());
		}finally{
			System.out.println("---�ڲ�finally---");
		}
		
	}
	
	
	private static void innerTry2()  throws Exception{

			int i = 0;
			System.out.println(100/i);

		
	}
	
}
