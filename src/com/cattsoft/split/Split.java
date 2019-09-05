package com.cattsoft.split;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Split {

	@Test
	public void test() {
		String str = "/Ems=广东华为城域波分集中网管/Ne=111-9610-肇庆永丰新局/Shelf=2/Board=3/Port=5/";
		String[] ss = str.split("/");
		int count  = 0;
		for(String s : ss){
			
			if(count == 0){
				System.out.println(s);
				count++;
				continue;
			}
			System.out.println(s);
		}
		
		//System.out.println("ss[6]=" + ss[6]);
		System.out.printf(ss[5].substring(ss[5].lastIndexOf("=")+1), ss[5].length());
		
	}
	
	@Test
	public void ovSplit(){
		System.out.println("GX-YL,07751,陆川县汇丰局Z01/中兴ZXA10C220-OLT02(FTTH/EPON),10.105.67.23,NA-0-3-2,28,LOID,0775000004006141,0775000004006141,,,2018-06-11 20:31:42,-23.85,4.82,-21.77,2.19,26.59,26.04,上行光衰质量达标/下行光衰质量达标,150002,07757310760,,,N,,EPON,下行光衰达标".split(",").length);
		System.out.println("String 自带的字符串01：" + "222@333@".split("@").length);//2
		System.out.println("String 自带的字符串02：" + "@333@222".split("@").length);//3
		System.out.println("ad".split("@")[0]);
		
		String str = "0777120190723017314|#|07773684365|#|2019-07-23 10:12:04|#|10F|#|回单备注：【回单子单单号：0777120190723017314FWBZ_CL1】用户处光损正常-21，用户的业务可以正常使用，已和用户解释清楚；免责备注：用户处光损正常-21，用户的业务可以正常使用，已和用户解释清楚|#|99775|#|陆文登|#||#||#||#||#||#||#|";
		System.out.println(str);	
		String[]str1 = sPlit(str,"|#|");
		System.out.println("========" + str1.length);//数组包含[]
		
		
		System.out.println("分割结果1:" + sPlit("222@333@", "@").length + ":" + sPlit("222@333@", "@")[2]);//3
		System.out.println("分割结果2:" + sPlit("222@333@444", "@").length);//3
		System.out.println("分割结果3:" + sPlit("@333@", "@").length);//3
		System.out.println("分割结果4:" + sPlit("@333@444", "@").length);//3
	}
	
	/**
	 * 数据分割操作
	 * @param lineStr		读取字符串
	 * @param split			分割符
	 * @return
	 */
	private String[] sPlit(String lineStr, String split) {
		
		List<String> list = new ArrayList<String>();
		String temp = lineStr;
		int index = temp.indexOf(split);
		int le = split.length();
		
		while(index > -1){
			String temp2 = temp.substring(0, index);
			list.add(temp2);
			temp = temp.substring(index + le, temp.length());//循环去掉已取的字段
			index = temp.indexOf(split);
		}
		list.add(temp);
		String[] str = new String[list.size()];
		for(int i = 0; i < str.length; i++){
			str[i] = list.get(i);
		}
		return str;
	}

	
	
	/**
	 * 数据分割操作
	 * @param lineStr		读取字符串
	 * @param split			分割符
	 * @return
	 */
	
	private String[] sPlit1(String lineStr, String split) {
		
		List<String> list = new ArrayList<String>();
		String temp = lineStr;
		int index = temp.indexOf(split);
		int le = split.length();
		
		while(index > -1){
			String temp2 = temp.substring(0, index);
			list.add(temp2);
			temp = temp.substring(index + le, temp.length());//循环去掉已取的字段
			index = temp.indexOf(split);
		}
		list.add(temp);
		String[] str = new String[list.size()];
		for(int i = 0; i < str.length; i++){
			str[i] = list.get(i);
		}
		return str;
	}
	@Test
	public void testSplit1(){
		String lineStr = "1,2,3,";
		String str[] = sPlit(lineStr, ",");
		String str1[] = sPlit1(lineStr, ",");
		System.out.println("str=" + str.length + " str1=" + str1.length);
		for(String s : str){
			System.out.println(">>> " + s);
		}
	}

}
