package com.cattsoft.utils;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private int age;
	private String name;

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@SuppressWarnings("null")
	public static void main(String[] args) {
		List<Object> rowList = new ArrayList<Object>();
		Person p = new Person();
		p.setAge(11);
		p.setName("11");
		rowList.add(p);
		Person p1 = new Person();
		p1.setAge(22);
		p1.setName("22");
		rowList.add(p1);
		Person p3 = new Person();
		p3.setAge(33);
		p3.setName("33");
		rowList.add(p3);
	}
}
