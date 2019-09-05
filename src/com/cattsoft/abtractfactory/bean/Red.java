package com.cattsoft.abtractfactory.bean;

import com.cattsoft.abtractfactory.Color;

public class Red implements Color {

	@Override
	public void fill() {
		System.out.println("Red---fill()");

	}

}
