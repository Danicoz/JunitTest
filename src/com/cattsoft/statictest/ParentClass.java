package com.cattsoft.statictest;

public class ParentClass {

	private static String p_static = "父类静态变量";
	
	{
		System.out.println("父类初始化代码块！");
	}
	
	static{
		System.out.println(p_static);
		System.out.println("父类静态初始化块！");
	}
	
	public static void main(String[] args) {
		new ParentClass();//静态变量     静态初始化块！     初始化代码块！   总： 静态初始化块 > 初始化块
	}

}
	












