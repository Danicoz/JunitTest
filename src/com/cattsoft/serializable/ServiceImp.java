package com.cattsoft.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ServiceImp {

	static String path = "./file/serializable.txt";
	
	//序列化对象
	public static void doSerializable() throws FileNotFoundException, IOException{
		Person ps = new Person();
		ps.setName("张三");
		ps.setAge(22);
		ps.setSex("男");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
		oos.writeObject(ps);
		System.out.println("Person对象序列化成功！");
		oos.close();
	}
	
	//反序列化对象
	public static Person doUnSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
		Person person = (Person) ois.readObject();
		System.out.println("Person对象反序列化成功！");  
		ois.close();
		return person;
	}
	
	
}
