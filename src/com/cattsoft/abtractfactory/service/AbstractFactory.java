package com.cattsoft.abtractfactory.service;

import com.cattsoft.abtractfactory.Color;
import com.cattsoft.abtractfactory.Shape;

public abstract class AbstractFactory {

	public abstract Color getColor(Class clazz);
	public abstract Shape getShape(Class clazz);
	
}
