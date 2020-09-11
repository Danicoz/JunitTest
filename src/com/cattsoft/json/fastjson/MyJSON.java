package com.cattsoft.json.fastjson;

import java.util.*;

import com.google.gson.JsonObject;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cattsoft.json.fastjson.bean.Course;
import com.cattsoft.json.fastjson.bean.Student;
import com.cattsoft.json.fastjson.bean.Teacher;
import com.google.gson.JsonParser;

	/**
	 * JSON 字符串和 JSON 对象之间的转换就是序列化和反序列化的过程，
	 * JavaBean------>JSON字符串：序列化
	 * JSON字符串------>JavaBean:反序列化
	 * 学习地址：https://www.cnblogs.com/Jie-Jack/p/3758046.html
	 * @author 刘永坚
	 *
	 */
public class MyJSON {
	
	// json字符串-简单对象型
	private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
	// json字符串-数组类型
	private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
	// 复杂格式json字符串
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,"
			+ "\"course\":{\"courseName\":\"english\",\"code\":1270},"
			+ "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

	public static void main(String[] args) {

		JSONObject simObj = JSON.parseObject(JSON_OBJ_STR);
//		JSONObject arrayObj = JSON.parseObject(JSON_ARRAY_STR);//出错，数组类型JSON
		JSONObject compObj = JSON.parseObject(COMPLEX_JSON_STR);
		System.out.println(simObj);
	}
	
	//简单json字符串转成 JSONObject对象
	@Test
	public void testJSONStrToJSONObject(){
		JSONObject obj1 = JSON.parseObject(JSON_OBJ_STR);
		JSONObject obj2 = JSONObject.parseObject(JSON_OBJ_STR);//JSONObject 是 JSON 的子类
		
		System.out.println("obj1=" + obj1 + " obj2=" + obj2);
		String name = obj1.getString("studentName");
		Integer age = obj2.getInteger("studentAge");
		System.out.println("studentName=" + name + " studentAge=" + age);
	}
	
	//json字符串-数组类型与JSONArray之间的转换
	@Test
	public void testJSONStrToJSONArray(){
		JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
		System.out.println(jsonArray);
		//循环方法1
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			System.out.print("studentName=" + obj.getString("studentName"));
			System.out.println(" studentAge=" + obj.getInteger("studentAge"));
		}
		
		//循环方法2
		for(Object obj : jsonArray){
			JSONObject jsonObject = (JSONObject) obj;
			System.out.print("studentName=" + jsonObject.getString("studentName"));
			System.out.println(" studentAge=" + jsonObject.getInteger("studentAge"));
		}
	}
	
	//复杂json格式字符串与JSONObject之间的转换
	@Test
	public void testComplexJSONStrToJSONObject(){
		
		JSONObject jsonObj = JSON.parseObject(COMPLEX_JSON_STR);
		String name = jsonObj.getString("teacherName");
		Integer age = jsonObj.getInteger("teacherAge");
		System.out.println("老师的信息：" + name + " " + age);
		
		JSONObject courseObj = jsonObj.getJSONObject("course");
		String courseName = courseObj.getString("courseName");
		Integer code = courseObj.getInteger("code");
		System.out.println("教的课程信息：" + courseName + " " + code);
		
		JSONArray studentArray = jsonObj.getJSONArray("students");
		System.out.print("教的学生信息：" );
		for(Object obj : studentArray){
			JSONObject object = (JSONObject) obj;
			String studentName = object.getString("studentName");
			Integer studentAge = object.getInteger("studentAge");
			System.out.print( studentName + " " + studentAge);
		}
	}
	

	/**
	 * json字符串转成对应的对象
	 */
	@Test
	public void testJSONStrToJavaBeanObj(){
		
		Student stu = JSON.parseObject(JSON_OBJ_STR, Student.class);
		String stuName = stu.getStudentName();
		Integer stuAge = stu.getStudentAge();
		System.out.println(stuName + " " + stuAge);
		
		//方法2
        Student student = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});
        String stuName1 = student.getStudentName();
		Integer stuAge1 = student.getStudentAge();
		System.out.println(stuName1 + " " + stuAge1);
		
	}
	
	@Test
	public void testJSONStrToJavaBeanList(){
		
		ArrayList<Student> students = JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>(){});
		
		for(Student stu : students){
			String stuName = stu.getStudentName();
			Integer stuAge = stu.getStudentAge();
			System.out.println(stuName + " " + stuAge);
		}
	}
	
	@Test
	public void testComplexJSONStrToJavaBean(){
		Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>(){});
		
		String teacherName = teacher.getTeacherName();
		Integer teancherAge = teacher.getTeacherAge();
		
		Course course = teacher.getCourse();
		String courseName = course.getCourseName();
		Integer code = course.getCode();
		
		List<Student>students = teacher.getStudents();
		String str = "";
		for(Student stu : students){
			String studentName = stu.getStudentName();
			Integer studentAge = stu.getStudentAge();
			str = str + "学生姓名：" + studentName + " 年龄：" + studentAge + " ";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("老师姓名：" + teacherName + " 年龄：" + teancherAge + "\n");
		sb.append("教的课程：" + courseName + " 课程编号：" + code + "\n");
		sb.append("学生有：" + "\n" + str);
		System.out.println(sb.toString());
		
	}
	
	/**
	 * Bean 转成  JSON字符串
	 */
	
	@Test
	public void testJavaBeanToJSON(){
		Student stu = new Student();
		stu.setStudentName("张三");
		stu.setStudentAge(22);
		
		String jsonStr = JSON.toJSONString(stu, true);
		System.out.println("jsonStr=" + jsonStr);
		
		//判断是否是 json 字符串
		try{
			JSONObject.parse(jsonStr);
			//new JsonParser().parse(jsonStr).getAsJsonObject();
		}catch(Exception e){
			System.out.println("不是json");
		}
		
		
	}
	
	@Test
	public void testListToJSON(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("key1", "One");
		map1.put("key2", "Two");

		//20200211
		JSONObject object = new JSONObject();
		object.put("1", map1);
		System.out.println(">>>" + object.toString() + "<<<") ;

		String str = object.toJSONString();
		JSONObject object1 = JSONObject.parseObject(str);
		String str1 = object1.getString("1");
		System.out.println(str1);

		Map<String,String> map = (Map) object1.get("1");
		for(String str2 : map.keySet()){
			System.out.println(">>>" + map.get(str2));
		}
		////

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("key1", "Three");
		map2.put("key2", "Four");
		        
		list.add(map1);
		list.add(map2);
		
		String listJson1 = JSON.toJSONString(list);//标准输出
		String listJson = JSON.toJSONString(list, true);//格式化输出
		
		System.out.println(listJson1);
		System.out.println(listJson);

		//奥格
		//JSONArray.fromObject(maps);
		
	}
	
	@Test
	public void testComplexBean2Json(){
		Teacher tc = new Teacher();
		tc.setTeacherName("张三");
		tc.setTeacherAge(43);
		
		Course course = new Course();
		course.setCourseName("语文");
		course.setCode(0001);
		tc.setCourse(course);
		
		List<Student>sts = new ArrayList<Student>();
		Student st = new Student();
		st.setStudentName("小明");
		st.setStudentAge(12);
		sts.add(st);
		
		st = new Student();
		st.setStudentName("小张");
		st.setStudentAge(14);
		sts.add(st);
		tc.setStudents(sts);
		
		String str = JSON.toJSONString(tc, true);
		
		System.out.println(str);

		JSONObject object = JSON.parseObject(str);
		System.out.println(object.toString());
		
		try{
			JSONObject.parse(str);
		}catch(Exception e){
			System.out.println(str + " 不是JSON字符串");
		}
		
		
		
		
		
	}
	
	@Test
	public void testJsonDate(){
//		String jsonDate = JSON.toJSONString("");
//		System.out.println(jsonDate);
		String jsonStr = "{0}";
		JSONObject.parse(jsonStr);

	}
	
	
	
}
