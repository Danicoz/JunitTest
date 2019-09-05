package com.cattsoft.abtractfactory.service;

import com.cattsoft.abtractfactory.Color;
import com.cattsoft.abtractfactory.Shape;

public class ColorFactory extends AbstractFactory {

	@Override
	public Color getColor(Class clazz) {
		
		Color color = null;
		
		try {
			color = (Color) Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return color;
	}

	@Override
	public Shape getShape(Class clazz) {
		return null;
	}

}
