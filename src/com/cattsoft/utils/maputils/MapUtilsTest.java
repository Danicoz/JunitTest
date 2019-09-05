package com.cattsoft.utils.maputils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.cattsoft.json.fastjson.bean.Student;

public class MapUtilsTest {

	public static void main(String[] args) {
	
		Map<String, Object> nullMap = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "刘永坚");
		map.put("age", 18);
		
		System.out.println(MapUtils.getString(map, "name"));//刘永坚
		System.out.println(MapUtils.getIntValue(map, "age"));//18
		System.out.println(MapUtils.getInteger(map, "age"));//18
		System.out.println(MapUtils.getString(map, "name", "刘永坚N"));//刘永坚
		System.out.println(MapUtils.getString(map, "address"));//null
		//获取集合 map 的address值, null 则返回第三参数
		System.out.println(MapUtils.getString(map, "address", "汕尾"));//汕尾

		//集合本身为null 返回null, 无需判断集合是否为null
		System.out.println(MapUtils.getString(nullMap, "name"));//null
		
		System.out.println("///////////////////////////");
		map.put("user.name", "lisi");
		
		Student stu = new Student();
		stu.setStudentName(MapUtils.getString(map, "user.name"));
		System.out.println(stu.toString());
		
		stu.setStudentAge(MapUtils.getIntValue(map, "user.age", 18));
		System.out.println(stu.toString());
		
		
	}
	
}
