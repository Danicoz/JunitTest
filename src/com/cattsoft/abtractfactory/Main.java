package com.cattsoft.abtractfactory;

import com.cattsoft.abtractfactory.bean.Blue;
import com.cattsoft.abtractfactory.bean.Circle;
import com.cattsoft.abtractfactory.bean.Rectangle;
import com.cattsoft.abtractfactory.bean.Red;
import com.cattsoft.abtractfactory.service.AbstractFactory;
import com.cattsoft.abtractfactory.service.ColorFactory;
import com.cattsoft.abtractfactory.service.FactoryProducer;
import com.cattsoft.abtractfactory.service.ShapeFactory;

/*
 	�ܣ�
 		��Ҫ�������Ҫ����ӿ�ѡ������⡣
		��ʱʹ�ã�ϵͳ�Ĳ�Ʒ�ж���һ���Ĳ�Ʒ�壬��ϵͳֻ��������ĳһ��Ĳ�Ʒ��
		��ν������һ����Ʒ�����棬��������Ʒ��
		�ؼ����룺��һ��������ۺ϶��ͬ���Ʒ��
		
		�� ������󹤳����ṩ��Щ������;
		�� ���幤���ഴ������;
		�� �������Ĺ�����ʵ�֡�
 */
public class Main {

	public static void main(String[] args) {
		
		//��ȡShapeFactory ����
		AbstractFactory shapeFactory = FactoryProducer.getFactory(ShapeFactory.class);
		
		//��ȡ Circle �Ķ���
		Shape circle = shapeFactory.getShape(Circle.class);
		circle.draw();
		
		//��ȡ Rectangle ����
		Shape rt = shapeFactory.getShape(Rectangle.class);
		rt.draw();
		
		//��ȡColorFactory ����
		AbstractFactory colorFactory = FactoryProducer.getFactory(ColorFactory.class);
		
		//��ȡred ����
		Color red = colorFactory.getColor(Red.class);
		red.fill();
		
		//��ȡ blue ����
		Color bule = colorFactory.getColor(Blue.class);
		bule.fill();
		
	}
}
