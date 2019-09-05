package com.cattsoft.utils.maputils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.cattsoft.json.fastjson.bean.Student;

public class MapUtilsTest {

	public static void main(String[] args) {
	
		Map<String, Object> nullMap = null;
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "������");
		map.put("age", 18);
		
		System.out.println(MapUtils.getString(map, "name"));//������
		System.out.println(MapUtils.getIntValue(map, "age"));//18
		System.out.println(MapUtils.getInteger(map, "age"));//18
		System.out.println(MapUtils.getString(map, "name", "������N"));//������
		System.out.println(MapUtils.getString(map, "address"));//null
		//��ȡ���� map ��addressֵ, null �򷵻ص�������
		System.out.println(MapUtils.getString(map, "address", "��β"));//��β

		//���ϱ���Ϊnull ����null, �����жϼ����Ƿ�Ϊnull
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
