package com.cattsoft.abtractfactory.bean;

import com.cattsoft.abtractfactory.Shape;

public class Rectangle implements Shape {

	@Override
	public void draw() {
		System.out.println("Rectangle---draw()");
		
	}

}
