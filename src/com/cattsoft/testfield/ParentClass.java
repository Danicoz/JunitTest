package com.cattsoft.testfield;

public class ParentClass {

	private String privateField = "ParentClass--private";
	/* friendly */String friendlyField = "ParentClass--friendly";
	protected String protectedField = "ParentClass--protected";
	public String publicField = "ParentClass--public";
	
	protected String diffField = "ParentClass--protected";
	
	public String a = "�������е�����";
	
	public String getPrivateField(){
		return privateField;
	}
	
	public void testParent(){
		System.out.println("parent>>>>>" + this.publicField);
	}
}
