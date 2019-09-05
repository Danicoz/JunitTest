package com.cattsoft.exception;

public class TestThrow {

	/**
	 * 总：
	 * 	往上抛出异常后，只会在最顶层捕获并处理异常。
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			System.out.println("------外层try------");
			innerTry1();
			System.out.println("------外层try,调用内部try之后的代码块------");
		} catch (Exception e) {
			System.out.println("---外层catch出错-----" + e.getMessage());
		}finally{
			System.out.println("---外层finally---");
		}
	}

	private static void innerTry()  {
//		int i = 0;
//		i = 100/i;
		try {
			innerTry1();
		} catch (Exception e) {
			System.out.println("出错了！");
		}
	}
	
	private static void innerTry1() throws Exception {
		int i = 0;
		i = 100/i;
	}
}
