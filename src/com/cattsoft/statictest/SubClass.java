package com.cattsoft.statictest;

public class SubClass extends ParentClass {
		private static String sub_static = "子静态变量";
		
		{
			System.out.println("子类初始化块！");
		}
		
		static{
			System.out.println(sub_static);
			System.out.println("子类静态初始化块！");
		}
		
		/*
		 父类静态变量
		父类静态初始化块！
		子静态变量
		子类静态初始化块！
		父类初始化代码块！
		子类初始化块！
		 */
		public static void main(String[] args) {
			new SubClass();
		}
		
}
