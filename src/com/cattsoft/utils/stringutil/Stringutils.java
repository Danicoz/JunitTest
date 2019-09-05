package com.cattsoft.utils.stringutil;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

/*
 * 教程：
 * https://blog.csdn.net/hj7jay/article/details/75461367
 */

public class Stringutils {
	
	@Test
	public void testCollectionToArray(){
		List<String>list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		String[] array = org.springframework.util.StringUtils.toStringArray(list);//集合转成字符串数组
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
		//其他方法  stripStart 
		System.out.println(StringUtils.strip("G刘G永坚G", "G"));//去掉字符串前后的字符G
		System.out.println(StringUtils.indexOf("G刘永G坚", "G", 1));//3   从位置1开始找
		
		//System.out.println(str.contains(null));//空指针异常
		System.out.println(StringUtils.contains(str, null));//false
		System.out.println(StringUtils.contains(null, "a"));//false   StringUtils 对 null 情况作了判断，不用再次判断
		
		System.out.println(StringUtils.contains("abc", ""));//true
		System.out.println("abc".contains(""));//true
		System.out.println(StringUtils.containsIgnoreCase("ABCd", "D"));//true 
		//////////////////////////////////////////
		System.out.println(StringUtils.substring("abc", 1));//bc  位置1开始截取后面的字符串
		System.out.println(StringUtils.substring("abc", -1));//c  从右位置1 往后截取字符串

		System.out.println(StringUtils.substringBefore("abc", "b"));//a
		System.out.println(StringUtils.substringAfter("abc", "b"));//c
		System.out.println(StringUtils.substringBetween("-a-", "-", "-"));//a  找不到返回null
		System.out.println(StringUtils.substring("abc", 1, 2));//b
		
		//长度 并非是 位置， 而是截取的个数
		System.out.println(StringUtils.left("abc", 2));//ab
		System.out.println(StringUtils.right("abc", 2));//bc
		System.out.println(StringUtils.mid("abc", 1, 3));//bc
		
		System.out.println(StringUtils.join(new String[]{"a","b","c"}, ","));//a,b,c
		System.out.println(StringUtils.removeStart("GG刘永坚", "G"));//G刘永坚
		System.out.println(StringUtils.remove("刘G永G坚G", "G"));//刘永坚  区分大小写
		System.out.println(StringUtils.replaceChars("GGlyj", "G", ""));//lyj
		System.out.println(StringUtils.replaceChars("GG刘永坚lyj", "刘", "刘-"));//GG刘永坚lyj
		System.out.println(StringUtils.replaceAll("刘永坚GG", "刘", "刘-"));//刘-永坚GG
		System.out.println(StringUtils.repeat("GGG",2));//GGGGGG
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}