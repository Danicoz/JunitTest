package com.cattsoft.factory;

/**
 * 工厂模式
 * @author Administrator
	 总：
	主要解决：主要解决接口选择的问题。
	何时使用：我们明确地计划不同条件下创建不同实例时。
	如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品。
	关键代码：创建过程在其子类执行。
	① 通过反射的方式创建对象;
	② 通过范式定义工厂类
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
