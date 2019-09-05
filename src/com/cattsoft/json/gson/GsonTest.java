package com.cattsoft.json.gson;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {

	}

	// beanת��json
	public String testBean2Json() {

		Book book = new Book();
		book.setId("0001");
		book.setName("�ڶ���");

		Gson gson = new Gson();
		String bookJson = gson.toJson(book);
		System.out.println(bookJson);// {"id":"0001","name":"�ڶ���"}

		Student stu = new Student();
		stu.setName("����");
		stu.setAge(22);
		stu.setDesc("�೤");
		stu.setSex("��");

		Book book1 = new Book();
		book1.setId("0002");
		book1.setName("��˹������");

		Set<Book> books = new HashSet<Book>();
		books.add(book);
		books.add(book1);

		stu.setBooks(books);

		String stuJson = gson.toJson(stu);
		// {"name":"����","age":22,"sex":"��","desc":"�೤","books":[{"id":"0002","name":"��˹������"},{"id":"0001","name":"�ڶ���"}]}
		System.out.println(stuJson);

		return stuJson;// stuJson bookJson
	}

	@Test
	public void testJson2Bean() {
		Gson gson = new Gson();
		String json = new GsonTest().testBean2Json();
		Student stu = gson.fromJson(json, Student.class);
		System.out.println(stu.getName() + stu.getDesc() + stu.getBooks());
		System.out.println(stu);
	}

	// ת��List �� Set ����
	@Test
	public void testComplexJson() {
		Gson gson = new Gson();
		String json = "[{\"id\":\"1\",\"name\":\"Json����\"},{\"id\":\"2\",\"name\":\"java����\"}]";
		System.out.println(json);

		List<Book> list = gson.fromJson(json, new TypeToken<List<Book>>() {
		}.getType());
		System.out.println(list);// [Book [id=1, name=Json����], Book [id=2,
									// name=java����]]
		System.out.println(list.get(0).getName());// Json����

		Set set = gson.fromJson(json, new TypeToken<Set>() {
		}.getType());
		System.out.println(set);
	}

	// json ����
	@Test
	public void testJsonUtil(){
		
		//��ʽ��Json
		Book book = new Book();
		book.setId("0001");
		book.setName("�ڶ���");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(book);
		System.out.println(json);
		
		
		//�Ƿ���json�ַ���:ͨ����׽���쳣���ж��Ƿ���json
		boolean jsonFlag = false;
		try{
			new JsonParser().parse(json + "json").getAsJsonObject();
			jsonFlag = true;
		}catch(Exception e){
			jsonFlag = false;
		}
		System.out.println(jsonFlag);
		
		//json���л�ȡ��ȥ��ĳ������
		try{
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(json);
			JsonObject jo = je.getAsJsonObject();
			String value = jo.get("id").toString();
			System.out.println(value);
			
			//ȥ��ĳ������
			jo.remove("name");
			json = jo.toString();
			System.out.println(json.toString());
			
			//����ĳ������
			jo.addProperty("name", "�ڶ���");
			json = jo.toString();
			System.out.println(json.toString());
			
			//�ж��Ƿ�������
			boolean isContains = jo.has("id");
			System.out.println(isContains);
			
		}catch(Exception e){
			System.out.println("���Բ����ڣ�");
		}
		
		//����ʱ���ʽ
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Gson gson1 = builder.create();
		Book book1 = new Book();
		book1.setId("0001");
		book1.setName("�ڶ���");
		book1.setTime(new Date());
		
		String json1 = gson1.toJson(book1);
		System.out.println(json1.toString());
		
	}
	

}
