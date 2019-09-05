package com.cattsoft.think.four_innerclass;

/*
  	总：
  		1、静态内部类用static修饰且定义在外部类类体中;
  		2、静态内部类只能访问外部类的static 成员, 不能直接访问外部类的实例变量和实例方法,只能通过外部类的对象调用;
  		3、静态内部类不再捕获指向外部类的引用,所以不能使用this关键字来访问外部类的成员;
  		4、静态内部类对象的创建不需通过外部类实例,直接调用外部类。
 */
public class StaticInnerClass {

	private int i = 10;
	private static int x = 100;
	
	static class MyInner{
		private int y = 20;
		public void innerMethod(){
			System.out.println("x=" + x);
			//System.out.println("i=" + i); 报错
			System.out.println("y=" + y);
			//System.out.println("i=" + GeneralInnerClass.this.i);
		}
		
		public static void main(String[] args) {
//			StaticInnerClass st = new StaticInnerClass();
//			System.out.println("i=" + st.i);
			StaticInnerClass.MyInner inner = new StaticInnerClass.MyInner();
			inner.innerMethod();
		}
		
	}
}
