package com.cattsoft.param;

/*
	Java���ֱ�����
		1���������ͱ���������char��byte��short��int��long��float��double��boolean��
		2���������ͱ����������ࡢ�ӿڡ�����(������������Ͷ�������)��
		
	�ܣ�	
	1�����ڻ������ͣ��ڷ������ڶԷ��������������¸�ֵ��������ı�ԭ�б�����ֵ��
	2�������������ͣ��ڷ������ڶԷ��������������¸������ã�������ı�ԭ�б��������е����á�
	3���������ڶԲ����������㣬��Ӱ��ԭ�б�����ֵ��
	4���������ڶԲ�����ָ���������Խ������㣬���ı�ԭ�б�����ָ����������ֵ�� (�Զ�������Խ��в����Ż�ı�ԭʼֵ�� ��setName())
	ǰ��������ı��ֵֻ���ڷ���������Ч���൱�ھֲ�����������ִ���꣬�������ھͽ����ˡ�
	
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
		System.out.println("�ڷ��������¸�ֵ��������=" + pt.num);
	}
	
	public void add(int i){
		i += 9;
	}
	
	public void add(PramsTest pt){
		pt.num += 9;
	}
	
	public static void main(String[] args) {
		System.out.println("********* ����ֵ���ݺ����ô��ݣ�*********");
		
		PramsTest pt = new PramsTest();
		
		System.out.println("����---��������");
		System.out.println("ԭʼֵ=" + pt.num);
		
		pt.change(pt.num);
		System.out.println("�������Ͳ������¸�ֵ=" + pt.num);
		
		pt.change(pt);
		System.out.println("�������Ͳ������¸�ֵ=" + pt.num);
		
		System.out.println("����---��������");
		pt = new PramsTest();
		System.out.println("ԭʼֵ=" + pt.num);
		
		pt.add(pt.num);
		System.out.println("�ı�������Ͳ���ֵ=" + pt.num);
		
		pt.add(pt);
		System.out.println("�ı��������Ͳ���ֵ=" + pt.num);
		
		pt = new PramsTest();
		System.out.println("Booleanԭʼֵ=" + pt.bool);
		
		pt.changeBool(pt.bool);
		System.out.println("Boolean���¸�ֵ=" + pt.bool);
	
	}
	
}
