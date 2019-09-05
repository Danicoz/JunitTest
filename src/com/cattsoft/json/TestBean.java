package com.cattsoft.json;

public class TestBean {

	public Integer id;
	public String title;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		String str = "id=" + this.id + " title=" + this.title;
		return str.toString();
	}
	
}
