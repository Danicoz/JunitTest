package com.cattsoft.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyJsonTest {

	// 无序
	@Test
	public void test() {
		JSONObject obj = new JSONObject();
		// {"age":23,"name":"Tom","num":101,"sex":"male"}
		obj.put("name", "Tom");
		obj.put("num", 101);
		obj.put("age", 23);
		obj.put("sex", "male");

		System.out.println(obj);
	}

	// 有序
	@Test
	public void testSort() {
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("name", "Tom");
		params.put("num", 101);
		params.put("age", 23);
		params.put("sex", "male");
		String str = "{1:\"游泳\", \"2\":\"羽毛球\"}";
		params.put("hobby", str);

		String array = JSONArray.toJSONString(params);
		System.out.println(array.replace("\\", ""));

		// JSONObject obj = new JSONObject(array);
	}

	// 字符串转成JSON对象
	@Test
	public void stringToJson() {

		String jsonStr = "{\"id\": 2," + " \"title\": \"json title\", "
				+ "\"config\": {" + "\"width\": 34," + "\"height\": 35,"
				+ "}, \"data\": [" + "\"JAVA\", \"JavaScript\", \"PHP\"" + "]}";

		JSONObject obj = JSON.parseObject(jsonStr);

		System.out.println(obj);

		// 获取各个属性值
		Integer id = obj.getInteger("id");
		String title = obj.getString("title");
		System.out.println("id=" + id + " title=" + title);

		// 根据属性名获取JSONObject类
		JSONObject obj1 = obj.parseObject(obj.getString("config"));
		Integer width = obj1.getInteger("width");
		Integer height = obj1.getInteger("height");
		System.out.println("width=" + width + " height=" + height);

		// 根据属性名获取JSONArray数组
		JSONArray array = obj.getJSONArray("data");
		for (int i = 0; i < array.size(); i++) {
			System.out.print(array.get(i) + " ");
		}

		System.out.println();
		//转成简单 Bean对象
		String jsonStr1 = "{\"id\": 2," + " \"title\": \"json title\"}";
		TestBean obj2 = JSON.parseObject(jsonStr1, TestBean.class);
		System.out.println(obj2.toString());

	}

}