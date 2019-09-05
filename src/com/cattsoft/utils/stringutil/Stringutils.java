package com.cattsoft.utils.stringutil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

/*
 * �̳̣�
 * https://blog.csdn.net/hj7jay/article/details/75461367
 */

public class Stringutils {
	
	@Test
	public void testCollectionToArray(){
		List<String>list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		String[] array = org.springframework.util.StringUtils.toStringArray(list);//����ת���ַ�������
		for(Object str : array)
			System.out.print(str + " ");
		
		array = list.toArray(new String[list.size()]);
		for(Object str : array)
			System.out.print(str + " ");
		
		String str = "";
		System.out.println(org.springframework.util.StringUtils.isEmpty(str));
		str = null;
		System.out.println(org.springframework.util.StringUtils.isEmpty(str));
	}
	
	@Test
	public void testCommonStringUtils(){
		String str = " ";
		System.out.println(StringUtils.isEmpty(str));//false
		System.out.println(StringUtils.isBlank(str));//true
		//��������  stripStart 
		System.out.println(StringUtils.strip("G��G����G", "G"));//ȥ���ַ���ǰ����ַ�G
		System.out.println(StringUtils.indexOf("G����G��", "G", 1));//3   ��λ��1��ʼ��
		
		//System.out.println(str.contains(null));//��ָ���쳣
		System.out.println(StringUtils.contains(str, null));//false
		System.out.println(StringUtils.contains(null, "a"));//false   StringUtils �� null ��������жϣ������ٴ��ж�
		
		System.out.println(StringUtils.contains("abc", ""));//true
		System.out.println("abc".contains(""));//true
		System.out.println(StringUtils.containsIgnoreCase("ABCd", "D"));//true 
		//////////////////////////////////////////
		System.out.println(StringUtils.substring("abc", 1));//bc  λ��1��ʼ��ȡ������ַ���
		System.out.println(StringUtils.substring("abc", -1));//c  ����λ��1 �����ȡ�ַ���

		System.out.println(StringUtils.substringBefore("abc", "b"));//a
		System.out.println(StringUtils.substringAfter("abc", "b"));//c
		System.out.println(StringUtils.substringBetween("-a-", "-", "-"));//a  �Ҳ�������null
		System.out.println(StringUtils.substring("abc", 1, 2));//b
		
		//���� ������ λ�ã� ���ǽ�ȡ�ĸ���
		System.out.println(StringUtils.left("abc", 2));//ab
		System.out.println(StringUtils.right("abc", 2));//bc
		System.out.println(StringUtils.mid("abc", 1, 3));//bc
		
		System.out.println(StringUtils.join(new String[]{"a","b","c"}, ","));//a,b,c
		System.out.println(StringUtils.removeStart("GG������", "G"));//G������
		System.out.println(StringUtils.remove("��G��G��G", "G"));//������  ���ִ�Сд
		System.out.println(StringUtils.replaceChars("GGlyj", "G", ""));//lyj
		System.out.println(StringUtils.replaceChars("GG������lyj", "��", "��-"));//GG������lyj
		System.out.println(StringUtils.replaceAll("������GG", "��", "��-"));//��-����GG
		System.out.println(StringUtils.repeat("GGG",2));//GGGGGG
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}