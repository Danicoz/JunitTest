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

	@Test
	public void testList(){

		Integer[] str = {1,2,3,4,5};
		Integer[] str1 = {1,2,3,4,5};
		List<Integer[]>list = new ArrayList<>();
		list.add(str);
        list.add(str1);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
        jsonObject.put("list1", str);

		System.out.println(jsonObject.toString());


		String[]temp = new String[5];
        System.out.println(temp.length);

        System.out.println(temp[0]);
	}

	@Test
	public  void  testCreatExcel(){

		String str = "{\"btInfo\":[{\"splcmc\":[\"政府投资工程建设项目审批流程图（房屋建筑类）\"],\"splclxmc\":\"政府投资工程建设项目（房屋建筑类）\",\"splcbm\":[\"4\"],\"splclx\":1},{\"splcmc\":[\"线性工程建设项目审批流程图\"],\"splclxmc\":\"政府投资工程建设项目（线性工程类）\",\"splcbm\":[\"5\"],\"splclx\":2},{\"splcmc\":[\"简易低风险类工程建设项目审批流程图\"],\"splclxmc\":\"小型社会投资项目\",\"splcbm\":[\"6\"],\"splclx\":4},{\"splcmc\":[\"内部改造类工程建设项目审批流程图\",\"新建扩建类工程建设项目审批流程图\",\"现状改建类工程建设项目审批流程图\"],\"splclxmc\":\"其他\",\"splcbm\":[\"1\",\"2\",\"3\"],\"splclx\":10}],\"xzqhdm\":\"110000\",\"isQualified\":[\"否\",\"否\",\"否\",\"是\",\"否\",\"是\"]," +
				"\"xzqhmc\":\"广州\"," +
				"\"isUpdate\":{\"" +
				"isUpdateSqb4\":[[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"是\"]]," +
				"\"isUpdateSqb3\":[[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"是\"]]," +
				"\"isUpdateSqb2\":[[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"否\"]," +
				"[\"\",\"\",\"\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]]," +
				"\"isUpdateSqb1\":[[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"否\"],[\"\",\"\",\"\"]," +
				"[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]]," +
				"\"isUpdateBjlc3\":[[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjlc2\":[[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"]],\"isUpdateBjqd1\":[[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjlc4\":[[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjqd3\":[[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjqd2\":[[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjqd4\":[[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBszn4\":[[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBszn1\":[[\"否\",\"否\",\"是\"],[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"是\",\"否\",\"是\"],[\"是\",\"否\",\"否\"]],\"isUpdateBjlc1\":[[\"是\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBszn3\":[[\"是\",\"否\",\"是\"],[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"否\"],[\"否\",\"否\",\"否\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]],\"isUpdateBszn2\":[[\"否\",\"否\",\"是\"],[\"否\",\"否\",\"是\"],[\"是\",\"否\",\"否\"],[\"\",\"\",\"\"],[\"是\",\"否\",\"否\"],[\"是\",\"否\",\"否\"]]}," +
				"\"remarks\":[\"0000\",\"9999\",\"00000\",\"1234567\",\"无\",\"545464ffff4444444\"]}";

		JSONObject jsonObject = JSON.parseObject(str);
		String str1 = JSON.toJSONString(str, true);
		//System.out.println(str1);


		String xzqhmc = jsonObject.getString("xzqhmc");
		String xzqhdm = jsonObject.getString("xzqhdm");
		System.out.println("xzqhdm=" + xzqhdm + "  xzqhmc=" + xzqhmc);

		JSONObject isUpdateObject = jsonObject.getJSONObject("isUpdate");
		System.out.println("isUpdateObject===" + isUpdateObject);

		String isUpdateSqb4 = isUpdateObject.getString("isUpdateSqb4");
		System.out.println("isUpdateSqb4==" + isUpdateSqb4);
		String[] isUpdateSqb4Array = isUpdateSqb4.split("],\\[");

		System.out.println("isUpdateSqb4Array=" + isUpdateSqb4Array.length + "   isUpdateSqb4Array[0]=" +isUpdateSqb4Array[0] );


		System.out.println("=====remarks======");
		String remarks = jsonObject.getString("remarks");
		System.out.println("remarks=" + remarks);
		System.out.println("=====remarks======");

		System.out.println("===================");
		JSONArray jsonArray = jsonObject.getJSONArray("btInfo");
		System.out.println(jsonArray);
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			System.out.println("splcmc=" + obj.getString("splcmc"));
			System.out.println(" splclxmc=" + obj.getString("splclxmc"));

			String str11 =  obj.getString("splcmc");
			str11 = str11.substring( 1, str11.length() - 1 );
			System.out.println("str11=== " + str11.substring( 1, str11.length() - 1 ));
			String str111[] = str11.split(",");
			System.out.println("str111[]==" + str111.length + "  str111[0]===" + str111[0]);
		}
		System.out.println("===================");
//		JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
//		System.out.println(jsonArray);
//		//循环方法1
//		for(int i = 0; i < jsonArray.size(); i++){
//			JSONObject obj = jsonArray.getJSONObject(i);
//			System.out.print("studentName=" + obj.getString("studentName"));
//			System.out.println(" studentAge=" + obj.getInteger("studentAge"));
//		}

	}

	@Test
	public void testSubstring(){
		String str = "[\"内部改造类工程建设项目审批流程图\",\"新建扩建类工程建设项目审批流程图\",\"现状改建类工程建设项目审批流程图\"]";
		str = str.substring(1, str.length()-1);
		System.out.println("str==" + str);


	}
	
	
}




















