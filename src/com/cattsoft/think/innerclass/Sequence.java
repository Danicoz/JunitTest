package com.cattsoft.think.innerclass;

/*
 * 内部类可以访问外围类的例子。
 * 内部类调用的items,next(),end(),current()都是外部的引用
 * 
 * 外部类不能直接访问内部类的变量
 * 
 * 原理：
 *   当外部类对象创建内部类对象时， 内部类对象也捕获一个指向外部类对象的引用
 */
public class Sequence {

	private Object[] items;
	private int next = 0;
	
	public Sequence(int size){
		super();
		items = new Object[size];
	}
	public void add(Object x){
		if(next < items.length){
			items[next++] = x;
		}
	}
	
	//常规内部类
	private class SequenceSelector implements Selector{

		private int i = 0;
		@Override
		public boolean end() {
			return i == items.length;
		}

		@Override
		public Object current() {
			return items[i];
		}

		@Override
		public void next() {
			if (i < items.length)
				i++;
		}
	}
		public Selector selector(){
			return new SequenceSelector();
		}
		
		//匿名内部类
		public Selector sel(){

			return new Selector(){
				private int i = 0;
				@Override
				public boolean end() {
					return i == items.length;
				}

				@Override
				public Object current() {
					return items[i];
				}

				@Override
				public void next() {
					if (i < items.length)
						i++;
				}
			};
		}
		
		public static void main(String[] args) {
//			//初始化变量
//			Sequence seq = new Sequence(10);
//			for(int i = 0; i< 10; i++){
//				seq.add(Integer.toString(i));
//			}
//			Selector selector = seq.selector();
//			while(!selector.end()){
//				System.out.print(selector.current() + " ");
//				selector.next();
//			}
			
			//匿名类形式调用
			Sequence sq =  new Sequence(10);
			for(int i = 0; i< 10; i++){
				sq.add(Integer.toString(i));
			}
			Selector sl = sq.sel();
			while(!sl.end()){
				System.out.print(sl.current() + " ");
				sl.next();
			}
		}		
	}
	
