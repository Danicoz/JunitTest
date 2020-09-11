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

	// bean转换json
	public String testBean2Json() {

		Book book = new Book();
		book.setId("0001");
		book.setName("摆渡人");

		Gson gson = new Gson();
		String bookJson = gson.toJson(book);
		System.out.println(bookJson);// {"id":"0001","name":"摆渡人"}

		Student stu = new Student();
		stu.setName("张三");
		stu.setAge(22);
		stu.setDesc("班长");
		stu.setSex("男");

		Book book1 = new Book();
		book1.setId("0002");
		book1.setName("穆斯林葬礼");

		Set<Book> books = new HashSet<Book>();
		books.add(book);
		books.add(book1);

		stu.setBooks(books);

		String stuJson = gson.toJson(stu);
		// {"name":"张三","age":22,"sex":"男","desc":"班长","books":[{"id":"0002","name":"穆斯林葬礼"},{"id":"0001","name":"摆渡人"}]}
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

	// 转换List 或 Set 集合
	@Test
	public void testComplexJson() {
		Gson gson = new Gson();
		String json = "[{\"id\":\"1\",\"name\":\"Json技术\"},{\"id\":\"2\",\"name\":\"java技术\"}]";
		System.out.println(json);

		List<Book> list = gson.fromJson(json, new TypeToken<List<Book>>() {
		}.getType());
		System.out.println(list);// [Book [id=1, name=Json技术], Book [id=2,
									// name=java技术]]
		System.out.println(list.get(0).getName());// Json技术

		Set set = gson.fromJson(json, new TypeToken<Set>() {
		}.getType());
		System.out.println(set);
	}

	// json 工具
	@Test
	public void testJsonUtil(){
		
		//格式化Json
		Book book = new Book();
		book.setId("0001");
		book.setName("摆渡人");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(book);
		System.out.println(json);
		
		
		//是否是json字符串:通过捕捉的异常来判断是否是json
		boolean jsonFlag = false;
		try{
			new JsonParser().parse(json + "json").getAsJsonObject();
			jsonFlag = true;
		}catch(Exception e){
			jsonFlag = false;
		}
		System.out.println(jsonFlag);
		
		//json串中获取或去掉某个属性
		try{
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(json);
			JsonObject jo = je.getAsJsonObject();
			String value = jo.get("id").toString();
			System.out.println(value);
			
			//去掉某个属性
			jo.remove("name");
			json = jo.toString();
			System.out.println(json.toString());
			
			//增加某个属性
			jo.addProperty("name", "摆渡人");
			json = jo.toString();
			System.out.println(json.toString());
			
			//判断是否有属性
			boolean isContains = jo.has("id");
			System.out.println(isContains);
			
		}catch(Exception e){
			System.out.println("属性不存在！");
		}
		
		//处理时间格式
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Gson gson1 = builder.create();
		Book book1 = new Book();
		book1.setId("0001");
		book1.setName("摆渡人");
		book1.setTime(new Date());
		
		String json1 = gson1.toJson(book1);
		System.out.println(json1.toString());
		
	}
	

}
