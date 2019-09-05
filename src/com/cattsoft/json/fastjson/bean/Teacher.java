package com.cattsoft.json.fastjson.bean;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(orders={"teacherName","teacherAge","course","students"})
public class Teacher {

	public String teacherName;
	public Integer teacherAge;
	public Course course;
	public List<Student> students;
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getTeacherAge() {
		return teacherAge;
	}
	public void setTeacherAge(Integer teacherAge) {
		this.teacherAge = teacherAge;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	
	
}
