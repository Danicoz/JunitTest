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
	 * JSON �ַ����� JSON ����֮���ת���������л��ͷ����л��Ĺ��̣�
	 * JavaBean------>JSON�ַ��������л�
	 * JSON�ַ���------>JavaBean:�����л�
	 * ѧϰ��ַ��https://www.cnblogs.com/Jie-Jack/p/3758046.html
	 * @author ������
	 *
	 */
public class MyJSON {
	
	// json�ַ���-�򵥶�����
	private static final String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
	// json�ַ���-��������
	private static final String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
	// ���Ӹ�ʽjson�ַ���
	private static final String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,"
			+ "\"course\":{\"courseName\":\"english\",\"code\":1270},"
			+ "\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

	public static void main(String[] args) {

		JSONObject simObj = JSON.parseObject(JSON_OBJ_STR);
//		JSONObject arrayObj = JSON.parseObject(JSON_ARRAY_STR);//������������JSON
		JSONObject compObj = JSON.parseObject(COMPLEX_JSON_STR);
		System.out.println(simObj);
	}
	
	//��json�ַ���ת�� JSONObject����
	@Test
	public void testJSONStrToJSONObject(){
		JSONObject obj1 = JSON.parseObject(JSON_OBJ_STR);
		JSONObject obj2 = JSONObject.parseObject(JSON_OBJ_STR);//JSONObject �� JSON ������
		
		System.out.println("obj1=" + obj1 + " obj2=" + obj2);
		String name = obj1.getString("studentName");
		Integer age = obj2.getInteger("studentAge");
		System.out.println("studentName=" + name + " studentAge=" + age);
	}
	
	//json�ַ���-����������JSONArray֮���ת��
	@Test
	public void testJSONStrToJSONArray(){
		JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
		System.out.println(jsonArray);
		//ѭ������1
		for(int i = 0; i < jsonArray.size(); i++){
			JSONObject obj = jsonArray.getJSONObject(i);
			System.out.print("studentName=" + obj.getString("studentName"));
			System.out.println(" studentAge=" + obj.getInteger("studentAge"));
		}
		
		//ѭ������2
		for(Object obj : jsonArray){
			JSONObject jsonObject = (JSONObject) obj;
			System.out.print("studentName=" + jsonObject.getString("studentName"));
			System.out.println(" studentAge=" + jsonObject.getInteger("studentAge"));
		}
	}
	
	//����json��ʽ�ַ�����JSONObject֮���ת��
	@Test
	public void testComplexJSONStrToJSONObject(){
		
		JSONObject jsonObj = JSON.parseObject(COMPLEX_JSON_STR);
		String name = jsonObj.getString("teacherName");
		Integer age = jsonObj.getInteger("teacherAge");
		System.out.println("��ʦ����Ϣ��" + name + " " + age);
		
		JSONObject courseObj = jsonObj.getJSONObject("course");
		String courseName = courseObj.getString("courseName");
		Integer code = courseObj.getInteger("code");
		System.out.println("�̵Ŀγ���Ϣ��" + courseName + " " + code);
		
		JSONArray studentArray = jsonObj.getJSONArray("students");
		System.out.print("�̵�ѧ����Ϣ��" );
		for(Object obj : studentArray){
			JSONObject object = (JSONObject) obj;
			String studentName = object.getString("studentName");
			Integer studentAge = object.getInteger("studentAge");
			System.out.print( studentName + " " + studentAge);
		}
	}
	

	/**
	 * json�ַ���ת�ɶ�Ӧ�Ķ���
	 */
	@Test
	public void testJSONStrToJavaBeanObj(){
		
		Student stu = JSON.parseObject(JSON_OBJ_STR, Student.class);
		String stuName = stu.getStudentName();
		Integer stuAge = stu.getStudentAge();
		System.out.println(stuName + " " + stuAge);
		
		//����2
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
			str = str + "ѧ��������" + studentName + " ���䣺" + studentAge + " ";
		}
		
		StringBuffer sb = new StringBuffer();
		sb.append("��ʦ������" + teacherName + " ���䣺" + teancherAge + "\n");
		sb.append("�̵Ŀγ̣�" + courseName + " �γ̱�ţ�" + code + "\n");
		sb.append("ѧ���У�" + "\n" + str);
		System.out.println(sb.toString());
		
	}
	
	/**
	 * Bean ת��  JSON�ַ���
	 */
	
	@Test
	public void testJavaBeanToJSON(){
		Student stu = new Student();
		stu.setStudentName("����");
		stu.setStudentAge(22);
		
		String jsonStr = JSON.toJSONString(stu, true);
		System.out.println("jsonStr=" + jsonStr);
		
		//�ж��Ƿ��� json �ַ���
		try{
			JSONObject.parse(jsonStr);
			//new JsonParser().parse(jsonStr).getAsJsonObject();
		}catch(Exception e){
			System.out.println("����json");
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
		
		String listJson1 = JSON.toJSONString(list);//��׼���
		String listJson = JSON.toJSONString(list, true);//��ʽ�����
		
		System.out.println(listJson1);
		System.out.println(listJson);

		//�¸�
		//JSONArray.fromObject(maps);
		
	}
	
	@Test
	public void testComplexBean2Json(){
		Teacher tc = new Teacher();
		tc.setTeacherName("����");
		tc.setTeacherAge(43);
		
		Course course = new Course();
		course.setCourseName("����");
		course.setCode(0001);
		tc.setCourse(course);
		
		List<Student>sts = new ArrayList<Student>();
		Student st = new Student();
		st.setStudentName("С��");
		st.setStudentAge(12);
		sts.add(st);
		
		st = new Student();
		st.setStudentName("С��");
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
			System.out.println(str + " ����JSON�ַ���");
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
