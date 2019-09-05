package com.cattsoft.statictest;

/*
 * ��̬�����;�̬��ʼ����ִ��˳��      ����λ���й�
 * ����̬��������̬��ʼ���飩>����������ʼ���飩>������
 */
public class TestOrder {

	private static TestA a = new TestA();
	
	static{
		System.out.println("��̬��ʼ���飡");
	}
	
	private static TestB b = new TestB();
	public static void main(String[] args) {
		new TestOrder();
	}

}

	class TestA{
		public TestA() {
			System.out.println("Test-A");
		}
	}
	
	class TestB{
		public TestB() {
			System.out.println("Test-B");
		}
	}
