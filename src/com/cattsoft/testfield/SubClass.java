package com.cattsoft.testfield;

/*
 	输出：
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
	总：
		① 属性的值是否覆盖，取决于变量的类型，而不是创建对象的类型。(访问权限)
		② private 不能被覆盖，其他public、protected、friendly可以被覆盖;
		③ 取子类或父类的属性值，看变量类型。(先确定变量对应的类型，再确定访问权限)
		④ 同名称变量，访问权限又不同的情况下，不影响值的覆盖。
		无论是什么数据类型的引用，运行时会调用被覆盖方法，在编译时都会遵循于源码。(意味着父类必须有方法，否则报异常)
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
		// ParentClass类型，SubClass对象   
		ParentClass parentSub = new SubClass();
		System.out.println("ParentClass parentSub = new SubClass();");
		System.out.println(parentSub.getPrivateField());//通过set重新赋值
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
