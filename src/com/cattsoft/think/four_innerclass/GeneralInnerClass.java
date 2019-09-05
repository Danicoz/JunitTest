package com.cattsoft.think.four_innerclass;


/*
  	总：
  		1、常规内部类没有用static修饰且定义在外部类类体中;
  		2、常规内部类可以直接访问外部类的实例变量和实例方法(创建内部类对象时捕获了指向外部类的引用);
  		3、常规内部类需生成外部类的对象引用时，需  外部类名称.this 进行访问;
  		4、常规内部类对象创建，第一可以调用外部类的方法创建内部类，第二用  外部类实例.new 生成。
 */
public class GeneralInnerClass {

	private int x = 10;
	
	class MyInner{
		private String y = "Hello!";
		
		public void InnerMethod(){
			System.out.println("内部类的变量y=" + y);
			System.out.println("外部类的变量x=" + x);
			outerMethod();
			System.out.println("引用外部类对象调用外部类的变量x=" + GeneralInnerClass.this.x);
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
