package com.cattsoft.xmlUtils;

import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

public class ParseXml {

	private static Logger logger = LoggerFactory.getLogger(ParseXml.class);
	
	/**
	 * 解析 XML, 返回数组
	 * @param result
	 * @return
	 */
	public static String[] xml2Str(String result) {
		String[] str = new String[3];
		String resultCode = "";
		String reason = "";
		String emosBillID = "";
		
		StringReader read = null;
		InputSource is = null;
		SAXBuilder builder = null;
		Document doc = null;
		
		try {
			read = new StringReader(result);
			is = new InputSource(read);
			builder = new SAXBuilder();
			doc = builder.build(is);
			
			Element rootEle = doc.getRootElement();
			resultCode = rootEle.getChildTextTrim("resultcode");
			reason = rootEle.getChildTextTrim("reason");
			emosBillID = rootEle.getChildTextTrim("emosBillID");
		    
			str[0] = resultCode;
			str[1] = reason;
			str[2] = emosBillID;
		} catch (Exception e) {
			//logger.info("返回结果XML解析异常", e);
		}
		
		return str;
	}
	
	/**
	 * 解析 XML, 返回数组
	 * @param result
	 * @return
	 */
	public static Person xml2Bean(String result) {
		
		Person person = null;
		StringReader read = null;
		InputSource is = null;
		SAXBuilder builder = null;
		Document doc = null;
		
		try {
			read = new StringReader(result);
			is = new InputSource(read);
			builder = new SAXBuilder();
			doc = builder.build(is);
			Element rootEle = doc.getRootElement();
			
			String name = rootEle.getChildTextTrim("resultcode");
			String sex = rootEle.getChildTextTrim("reason");
			Integer age = Integer.parseInt(rootEle.getChildTextTrim("emosBillID"));
			
			person = new Person();
			person.setAge(age);
			person.setName(name);
			person.setSex(sex);
			
		} catch (Exception e) {
			logger.info("返回结果XML解析异常", e);
		}
		
		return person;
	}
	
	public static class Person{
		private String name;
		private String sex;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	}
	
	public static void main(String[] args) {

	}
	
	
}

