package com.cattsoft.abtractfactory.service;

import com.cattsoft.abtractfactory.Color;
import com.cattsoft.abtractfactory.Shape;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Color getColor(Class clazz) {
		return null;
	}

	@Override
	public Shape getShape(Class clazz) {
		Shape shape = null;
		
		try {
			shape = (Shape) Class.forName(clazz.getName()).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return shape;
	}



}
