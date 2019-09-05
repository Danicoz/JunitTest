package com.cattsoft.exception;

import java.io.IOException;

public class TestTry {

	/**
	 * 总：
	 *    ①try就是为了捕捉异常处理异常的；
	 * 	  ②try作用于自己代码域，若有出错，则捕捉且不会执行后面的代码；
	 *    ③内部try出错且有处理，外层try代码域还是会执行；
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		try {
			System.out.println("------外层try------");
			//innerTry2();
			innerTry();
			//innerTry1();
											//int j =0;j = 100/j;		
			System.out.println("---外层try，调用内部try后执行的代码---");
		} catch (Exception e) {
			System.out.println("---外层catch出错-----" + e.getMessage());
		}finally{
			System.out.println("---外层finally---");
		}
		
		System.out.println("这层代码会执行吗？");
		
	}

	/**
	 * 测试内部try 出错
	 * 总： 会捕捉内部错误，外层代码正确则不会捕捉，而且外层代码都会运行;
	 * 	     外层代码出错捕捉外层错误，外层代码不会运行;
	 */
	private static void innerTry() {
		
		try {
			System.out.println("------内层try------");
			int i = 0;
			System.out.println(100/i);
		} catch (Exception e) {
			System.out.println("---内层catch出错-----" + e.getMessage());
		}finally{
			System.out.println("---内层finally---");
		}
		
	}
	
	
	/**
	 * 测试内部try 出错，但不处理
	 * 总： 不会捕捉内部错误，外层代码正确也会捕捉处理，外层代码不会运行;
	 */
	private static void innerTry1() {
		
		try {
			System.out.println("------内层try------");
			int i = 0;
			System.out.println(100/i);
//		} catch (Exception e) {
//			System.out.println("---内层catch出错-----" + e.getMessage());
		}finally{
			System.out.println("---内层finally---");
		}
		
	}
	
	
	private static void innerTry2()  throws Exception{

			int i = 0;
			System.out.println(100/i);

		
	}
	
}
