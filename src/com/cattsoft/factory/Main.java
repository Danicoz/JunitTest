package com.cattsoft.factory;

/**
 * ����ģʽ
 * @author Administrator
	 �ܣ�
	��Ҫ�������Ҫ����ӿ�ѡ������⡣
	��ʱʹ�ã�������ȷ�ؼƻ���ͬ�����´�����ͬʵ��ʱ��
	��ν������������ʵ�ֹ����ӿڣ����ص�Ҳ��һ������Ĳ�Ʒ��
	�ؼ����룺����������������ִ�С�
	�� ͨ������ķ�ʽ��������;
	�� ͨ����ʽ���幤����
 */
	public class Main {

	public static void main(String[] args) {
		ShapeFactory sf = new ShapeFactory();
		
		Circle circle = sf.getClass(Circle.class);
		circle.draw();
		
		Rectangle rt = sf.getClass(Rectangle.class);
		rt.draw();
	}

}
