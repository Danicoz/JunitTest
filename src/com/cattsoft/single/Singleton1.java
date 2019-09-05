package com.cattsoft.single;

/**
 * 饿汉式：常用
 * 	描述：这种方式比较常用，但容易产生垃圾对象。
 * 	优点：没有加锁，执行效率会提高。
 * 	缺点：类加载时就初始化，浪费内存。
 */
public class Singleton1 {

	private static Singleton1 instance = new Singleton1();
	private Singleton1(){}
	
	public static Singleton1 getInstance(){
		return instance;
	}
}
