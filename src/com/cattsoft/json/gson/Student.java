package com.cattsoft.json.gson;

import java.util.Set;

public class Student {

	private String name;
	private int age;
	private String sex;
	private String desc;
	private Set books;
	
	
	public Student() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Set getBooks() {
		return books;
	}
	public void setBooks(Set books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", desc=" + desc + ", books=" + books + "]";
	}
	
	
	
}
