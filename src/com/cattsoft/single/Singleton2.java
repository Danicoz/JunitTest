package com.cattsoft.single;

public class Singleton2 {

	/**
	 * 懒汉式，线程不安全
	 * 是否多线程安全：否
	 * 实现难度：易
	 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
	 */
	private static Singleton2 instance;
	private Singleton2(){}
	
	public static Singleton2 getInstance(){
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}
	
	/**
	 * 懒汉式，线程安全
	 * 优点：第一次调用才初始化，避免内存浪费。
	 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
	 */
	public static synchronized Singleton2 getInstance1(){
		
		if(instance == null){
			instance = new Singleton2();
		}
		return instance;
	}
	
}
