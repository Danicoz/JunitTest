package com.cattsoft.abtractimplement;

/**
 * 	总：
 * 		问题： 当实现一个接口时，必须实现接口上的所有方法; 
 * 			 有时只需要个别方法，这时可以设计一个抽象类实现这接口。
 *  注：
 * 			 抽象类设计, 实现不需要的方法，方法可以为空，也可以重写(可调用); 
 * 			 这样继承抽象类时只需重写需要关注的方法就行。
 *
 */
public class Main {

	public static void main(String[] args) {
		ITWorker it = new ITWorker();
		it.draw();
		it.network();
		it.fill();
	}

}
