package com.cattsoft.think.four_innerclass;

/*
  	�ܣ�
  		1����̬�ڲ�����static�����Ҷ������ⲿ��������;
  		2����̬�ڲ���ֻ�ܷ����ⲿ���static ��Ա, ����ֱ�ӷ����ⲿ���ʵ��������ʵ������,ֻ��ͨ���ⲿ��Ķ������;
  		3����̬�ڲ��಻�ٲ���ָ���ⲿ�������,���Բ���ʹ��this�ؼ����������ⲿ��ĳ�Ա;
  		4����̬�ڲ������Ĵ�������ͨ���ⲿ��ʵ��,ֱ�ӵ����ⲿ�ࡣ
 */
public class StaticInnerClass {

	private int i = 10;
	private static int x = 100;
	
	static class MyInner{
		private int y = 20;
		public void innerMethod(){
			System.out.println("x=" + x);
			//System.out.println("i=" + i); ����
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
