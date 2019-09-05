package com.cattsoft.param;

/*
	Java两种变量：
		1、基本类型变量，包括char、byte、short、int、long、float、double、boolean。
		2、引用类型变量，包括类、接口、数组(基本类型数组和对象数组)。
		
	总：	
	1、对于基本类型，在方法体内对方法参数进行重新赋值，并不会改变原有变量的值。
	2、对于引用类型，在方法体内对方法参数进行重新赋予引用，并不会改变原有变量所持有的引用。
	3、方法体内对参数进行运算，不影响原有变量的值。
	4、方法体内对参数所指向对象的属性进行运算，将改变原有变量所指向对象的属性值。 (对对象的属性进行操作才会改变原始值， 如setName())
	前三种情况改变的值只会在方法体内有效，相当于局部变量，方法执行完，生命周期就结束了。
	
 */

public class PramsTest {

	protected int num =0;
	protected boolean bool = false;
	
	
	public void changeBool(boolean b){
		b = true;
	}
	
	public void change(int i){
		i = 9;
	}
	
	public void change(PramsTest pt){
		PramsTest temp = new PramsTest();
		temp.num = 9;
		pt = temp;
		System.out.println("在方法中重新赋值引用类型=" + pt.num);
	}
	
	public void add(int i){
		i += 9;
	}
	
	public void add(PramsTest pt){
		pt.num += 9;
	}
	
	public static void main(String[] args) {
		System.out.println("********* 测试值传递和引用传递！*********");
		
		PramsTest pt = new PramsTest();
		
		System.out.println("参数---基本类型");
		System.out.println("原始值=" + pt.num);
		
		pt.change(pt.num);
		System.out.println("基本类型参数重新赋值=" + pt.num);
		
		pt.change(pt);
		System.out.println("引用类型参数重新赋值=" + pt.num);
		
		System.out.println("参数---引用类型");
		pt = new PramsTest();
		System.out.println("原始值=" + pt.num);
		
		pt.add(pt.num);
		System.out.println("改变基本类型参数值=" + pt.num);
		
		pt.add(pt);
		System.out.println("改变引用类型参数值=" + pt.num);
		
		pt = new PramsTest();
		System.out.println("Boolean原始值=" + pt.bool);
		
		pt.changeBool(pt.bool);
		System.out.println("Boolean重新赋值=" + pt.bool);
	
	}
	
}
