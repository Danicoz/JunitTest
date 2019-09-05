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
	
	//���л�����
	public static void doSerializable() throws FileNotFoundException, IOException{
		Person ps = new Person();
		ps.setName("����");
		ps.setAge(22);
		ps.setSex("��");
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
		oos.writeObject(ps);
		System.out.println("Person�������л��ɹ���");
		oos.close();
	}
	
	//�����л�����
	public static Person doUnSerializable() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
		Person person = (Person) ois.readObject();
		System.out.println("Person�������л��ɹ���");  
		ois.close();
		return person;
	}
	
	
}
