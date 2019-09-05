package com.cattsoft.statictest;

/*
 * 静态变量和静态初始化块执行顺序      程序位置有关
 * （静态变量、静态初始化块）>（变量、初始化块）>构造器
 */
public class TestOrder {

	private static TestA a = new TestA();
	
	static{
		System.out.println("静态初始化块！");
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
