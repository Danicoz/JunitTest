package com.cattsoft.think.innerclass;

/*
 *ÄäÃûÀà
 */
public class AnonymousInnerClass {

	public Selector sel(){
		return new Selector(){

			@Override
			public boolean end() {
				return false;
			}

			@Override
			public Object current() {
				return null;
			}

			@Override
			public void next() {	
			}
		};
	}
	public static void main(String[] args) {
		AnonymousInnerClass as = new AnonymousInnerClass();
		System.out.println("end=" + as.sel().end());
	}
}
