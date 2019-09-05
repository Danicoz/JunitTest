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
 	总：
 		主要解决：主要解决接口选择的问题。
		何时使用：系统的产品有多于一个的产品族，而系统只消费其中某一族的产品。
		如何解决：在一个产品族里面，定义多个产品。
		关键代码：在一个工厂里聚合多个同类产品。
		
		① 定义抽象工厂类提供哪些工厂类;
		② 定义工厂类创建方法;
		③ 定义具体的工厂类实现。
 */
public class Main {

	public static void main(String[] args) {
		
		//获取ShapeFactory 工厂
		AbstractFactory shapeFactory = FactoryProducer.getFactory(ShapeFactory.class);
		
		//获取 Circle 的对象
		Shape circle = shapeFactory.getShape(Circle.class);
		circle.draw();
		
		//获取 Rectangle 对象
		Shape rt = shapeFactory.getShape(Rectangle.class);
		rt.draw();
		
		//获取ColorFactory 工厂
		AbstractFactory colorFactory = FactoryProducer.getFactory(ColorFactory.class);
		
		//获取red 对象
		Color red = colorFactory.getColor(Red.class);
		red.fill();
		
		//获取 blue 对象
		Color bule = colorFactory.getColor(Blue.class);
		bule.fill();
		
	}
}
