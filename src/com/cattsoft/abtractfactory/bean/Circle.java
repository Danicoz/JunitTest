package com.cattsoft.abtractfactory.bean;

import com.cattsoft.abtractfactory.Shape;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("Circle---draw()");

	}

}
