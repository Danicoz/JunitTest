package com.cattsoft.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class PatternTest {

	public static void main(String[] args) {
		String s = "����_C_��ɽ����ɽ����ģ���";
		// ��Ҫƥ����ַ���д��������ʽ��Ȼ��Ҫ��ȡ���ַ�ʹ������������
		// ���������Ҫ��ȡ���һ�����֣����������ǡ�һ�����ּ��ϴ��ڵ���0���������ټ��Ͻ�������
		Pattern pattern = Pattern.compile("([\\s\\S]*)(\\^_)(\\w+)(\\^_)([\\s\\S]*)");
		Matcher matcher = pattern.matcher(s);
		boolean flag = matcher.find();
		System.out.println(flag);
		if(matcher.find()){
			System.out.println(matcher.group(1));
		}
		
	
		
//		System.out.println(matcher.group(0));
//		System.out.println(matcher.group(1));
		//System.out.println(matcher.group(2));
	}
	
	@Test
	public void testP(){
		String str = "����_C_��ɽ����ɽ����ģ���";
		//([\\s\\S]*)[\\^_](\\w+)[\\^_]([\\s\\S]*),[\\^_](\\w+)[\\^_]
		Pattern pattern = Pattern.compile("(.*)[\\^_](\\w+)[\\^_](.*)");
		Matcher matcher = pattern.matcher(str);
//		System.out.println(matcher.find());//true
//		System.out.println(matcher.find());//false
		if(matcher.find()){
			System.out.println(matcher.groupCount());
			System.out.println(matcher.group(2));
		}
//		System.out.println(str.indexOf("_") + ":" + str.lastIndexOf("_"));
//		System.out.println(str.substring(str.indexOf("_") + 1,str.lastIndexOf("_")));//��û��ƥ���ϻس���
	}
	
	
	/**
	 * ������
	 */
	@Test 
	public void test1(){
		String str = "I'm singing while you're dancing.";
		Pattern p = Pattern.compile("(\\w+)(?=cing\\b)");
		Matcher m = p.matcher(str);
		System.out.println(m.find());
		System.out.println(m.toString());
		System.out.println(m.groupCount());
		for(int i =0;i<m.groupCount(); i++){
			System.out.println(m.group(i));
		}
		
		str = "����_C_��ɽ����ɽ����ģ���";
		p = Pattern.compile("(\\w+)(?=_)");
		m = p.matcher(str);
		if (m.find()) {
			System.out.println(m.toString());
			System.out.println(m.groupCount());
			for (int i = 0; i < m.groupCount(); i++) {
				System.out.println(m.group(i));
			}
		}
		
		p = Pattern.compile("(?<=[_])(\\w+)(?=[_])");
		m = p.matcher(str);
		System.out.println(m.find());
		for(int i =0;i<m.groupCount(); i++){
			System.out.println(m.group(i));
		}
		
		p = Pattern.compile("C");
		m = p.matcher(str);
		System.out.println(m.find());
		
		 str = "aa2223bb";
		 
		 System.out.println(str.charAt(6));
	}
	
	
	@Test
	public void test2(){
		String str = "";
		String reg = "";
		Pattern p = null;
		Matcher m = null;
		
		reg = "^(\\d+)\\^(\\d+)";
		str = "4^4^8^8";
		p = Pattern.compile(reg);
		m = p.matcher(str);
		System.out.println(m.find());//true
		
		reg = "^(\\d+)\\^(\\d+)(.*)$";
		str = "^4^4^8^8";
		p = Pattern.compile(reg);
		m = p.matcher(str);
		System.out.println(m.find());//true
		
		reg = "(?<=[_])(\\w+)(?=[_])";
		str = "����_C_��ɽ����ɽ����ģ���";
		p = Pattern.compile(reg);
		m = p.matcher(str);
		if(m.find())
		System.out.println(m.group(0));//C
		
		
		
		
	}
	
	
	
}
