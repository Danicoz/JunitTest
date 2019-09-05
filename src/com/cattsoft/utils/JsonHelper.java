package com.cattsoft.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonHelper {
	/**
	 * 将JSON字符串转换为JSON对象
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static JSONObject parseObject(String text) {
		JSONObject jsonObject = JSON.parseObject(text);
		return jsonObject;
	}

	/**
	 * 将JSON字符串转换为JavaBean对象
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static Object parseObject(String text, Class clazz) {
		Object obj = JSON.parseObject(text, clazz);
		return obj;
	}

	/**
	 * 将JSON字符串转换为JavaBean对象
	 * 
	 * @param text
	 * @return JSONObject
	 */
	public static String toJSONString(Object obj) {
		String jsonStr = JSON.toJSONString(obj);
		return jsonStr;
	}

	// public static void main(String args[]) {
	// String text = "{'name':'张老头','age':66}";
	// /** 将JSON字符串转换为JSON对象 **/
	// JSONObject json = parseObject(text);
	// System.out.println(json.getString("name"));
	// /** 将JSON字符串转换为JavaBean对象 **/
	// T_JavaBean t_bean =(T_JavaBean)parseObject(text, T_JavaBean.class);
	// System.out.println(t_bean.getName());
	// /** 将JavaBean对象转换为JSON字符串 **/
	// String jsonObject = toJSONString(t_bean);
	// System.out.println(jsonObject);
	// }
}