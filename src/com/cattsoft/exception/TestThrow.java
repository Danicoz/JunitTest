package com.cattsoft.exception;

public class TestThrow {

	/**
	 * �ܣ�
	 * 	�����׳��쳣��ֻ������㲶�񲢴����쳣��
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			System.out.println("------���try------");
			innerTry1();
			System.out.println("------���try,�����ڲ�try֮��Ĵ����------");
		} catch (Exception e) {
			System.out.println("---���catch����-----" + e.getMessage());
		}finally{
			System.out.println("---���finally---");
		}
	}

	private static void innerTry()  {
//		int i = 0;
//		i = 100/i;
		try {
			innerTry1();
		} catch (Exception e) {
			System.out.println("�����ˣ�");
		}
	}
	
	private static void innerTry1() throws Exception {
		int i = 0;
		i = 100/i;
	}
}
