package com.cattsoft.think.innerclass;

/*
 * �ڲ�����Է�����Χ������ӡ�
 * �ڲ�����õ�items,next(),end(),current()�����ⲿ������
 * 
 * �ⲿ�಻��ֱ�ӷ����ڲ���ı���
 * 
 * ԭ��
 *   ���ⲿ����󴴽��ڲ������ʱ�� �ڲ������Ҳ����һ��ָ���ⲿ����������
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
	
	//�����ڲ���
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
		
		//�����ڲ���
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
//			//��ʼ������
//			Sequence seq = new Sequence(10);
//			for(int i = 0; i< 10; i++){
//				seq.add(Integer.toString(i));
//			}
//			Selector selector = seq.selector();
//			while(!selector.end()){
//				System.out.print(selector.current() + " ");
//				selector.next();
//			}
			
			//��������ʽ����
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
	
