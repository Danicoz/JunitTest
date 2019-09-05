package com.cattsoft.testfield;

/*
 	�����
	    ParentClass parent = new ParentClass();
		ParentClass--private
		ParentClass--protected
		ParentClass--friendly
		ParentClass--public
		
		ParentClass parentSub = new SubClass();
		SubClass--private
		ParentClass--protected
		ParentClass--friendly
		ParentClass--public
		
		SubClass subClass = new SubClass();
		SubClass--private
		SubClass--protected
		SubClass--friendly
		SubClass--public
		
		ParentClass--protected
		ParentClass--protected
		SubClass--public
	�ܣ�
		�� ���Ե�ֵ�Ƿ񸲸ǣ�ȡ���ڱ��������ͣ������Ǵ�����������͡�(����Ȩ��)
		�� private ���ܱ����ǣ�����public��protected��friendly���Ա�����;
		�� ȡ������������ֵ�����������͡�(��ȷ��������Ӧ�����ͣ���ȷ������Ȩ��)
		�� ͬ���Ʊ���������Ȩ���ֲ�ͬ������£���Ӱ��ֵ�ĸ��ǡ�
		������ʲô�������͵����ã�����ʱ����ñ����Ƿ������ڱ���ʱ������ѭ��Դ�롣(��ζ�Ÿ�������з����������쳣)
	 */

public class SubClass extends ParentClass{

	private String privateField = "SubClass--private";
	/*friendly*/String friendlyField = "SubClass--friendly";
	protected String protectedField = "SubClass--protected";
	public String publicField = "SubClass--public";
	
	public String getPrivateField(){
		return privateField;
	}
	
	
	public String diffField = "SubClass--public";
	
	public static void main(String[] args) {
		
		ParentClass parent = new ParentClass();
		System.out.println("ParentClass parent = new ParentClass();");
		System.out.println(parent.getPrivateField());
		System.out.println(parent.protectedField);
		System.out.println(parent.friendlyField);
		System.out.println(parent.publicField);
		parent.testParent();
		
		System.out.println();
		// ParentClass���ͣ�SubClass����   
		ParentClass parentSub = new SubClass();
		System.out.println("ParentClass parentSub = new SubClass();");
		System.out.println(parentSub.getPrivateField());//ͨ��set���¸�ֵ
		parentSub.testParent();
		//System.out.println(parentSub.privateField);
		
		System.out.println(parentSub.protectedField);
		System.out.println(parentSub.friendlyField);
		System.out.println(parentSub.publicField);
		
		System.out.println();
		SubClass subClass = new SubClass();
		System.out.println("SubClass subClass = new SubClass();");
		System.out.println(subClass.privateField);
		System.out.println(subClass.getPrivateField());
		System.out.println(subClass.protectedField);
		System.out.println(subClass.friendlyField);
		System.out.println(subClass.publicField);
		System.out.println(">>>" + subClass.a);
		System.out.println();
		System.out.println(parent.diffField);
		System.out.println(parentSub.diffField);
		System.out.println(subClass.diffField);
		
		subClass.testParent();
	}
	
	public void testParent(){
		System.out.println("sub>>>>>" + this.publicField);
	}

}
