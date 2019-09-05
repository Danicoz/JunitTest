package com.cattsoft.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonHelper {
	/**
	 * ��JSON�ַ���ת��ΪJSON����
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static JSONObject parseObject(String text) {
		JSONObject jsonObject = JSON.parseObject(text);
		return jsonObject;
	}

	/**
	 * ��JSON�ַ���ת��ΪJavaBean����
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static Object parseObject(String text, Class clazz) {
		Object obj = JSON.parseObject(text, clazz);
		return obj;
	}

	/**
	 * ��JSON�ַ���ת��ΪJavaBean����
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static String toJSONString(Object obj) {
		String jsonStr = JSON.toJSONString(obj);
		return jsonStr;
	}

	// public static void main(String args[]) {
	// String text = "{'name':'����ͷ','age':66}";
	// /** ��JSON�ַ���ת��ΪJSON���� **/
	// JSONObject json = parseObject(text);
	// System.out.println(json.getString("name"));
	// /** ��JSON�ַ���ת��ΪJavaBean���� **/
	// T_JavaBean t_bean =(T_JavaBean)parseObject(text, T_JavaBean.class);
	// System.out.println(t_bean.getName());
	// /** ��JavaBean����ת��ΪJSON�ַ��� **/
	// String jsonObject = toJSONString(t_bean);
	// System.out.println(jsonObject);
	// }
}