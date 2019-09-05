package com.cattsoft.serializable;

import java.io.IOException;
import java.text.MessageFormat;

public class Main {

	public static void main(String[] args) throws Exception, IOException {
		ServiceImp.doSerializable();
		Person p = ServiceImp.doUnSerializable();
		
		System.out.println(p.toString());
		
		String pattern = "name={0},age={1},sex={2}";  
		Object[] arguments = { p.getName(), p.getAge(), p.getSex() };
		System.out.println(MessageFormat.format(pattern, arguments));
	}
}
