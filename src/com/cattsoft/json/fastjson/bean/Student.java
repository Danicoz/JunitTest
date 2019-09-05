package com.cattsoft.json.fastjson.bean;

public class Student {

	public String studentName;
	public Integer studentAge;
	
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getStudentAge() {
		return studentAge;
	}
	public void setStudentAge(Integer studentAge) {
		this.studentAge = studentAge;
	}
	
	@Override
	public String toString() {
		String str = this.studentName + ":" + this.getStudentAge();
		return str;
	}
	
	
}
