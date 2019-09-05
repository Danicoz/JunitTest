package com.cattsoft.split;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Split {

	@Test
	public void test() {
		String str = "/Ems=�㶫��Ϊ���򲨷ּ�������/Ne=111-9610-���������¾�/Shelf=2/Board=3/Port=5/";
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
		System.out.println("GX-YL,07751,½���ػ���Z01/����ZXA10C220-OLT02(FTTH/EPON),10.105.67.23,NA-0-3-2,28,LOID,0775000004006141,0775000004006141,,,2018-06-11 20:31:42,-23.85,4.82,-21.77,2.19,26.59,26.04,���й�˥�������/���й�˥�������,150002,07757310760,,,N,,EPON,���й�˥���".split(",").length);
		System.out.println("String �Դ����ַ���01��" + "222@333@".split("@").length);//2
		System.out.println("String �Դ����ַ���02��" + "@333@222".split("@").length);//3
		System.out.println("ad".split("@")[0]);
		
		String str = "0777120190723017314|#|07773684365|#|2019-07-23 10:12:04|#|10F|#|�ص���ע�����ص��ӵ����ţ�0777120190723017314FWBZ_CL1���û�����������-21���û���ҵ���������ʹ�ã��Ѻ��û��������������ע���û�����������-21���û���ҵ���������ʹ�ã��Ѻ��û��������|#|99775|#|½�ĵ�|#||#||#||#||#||#||#|";
		System.out.println(str);	
		String[]str1 = sPlit(str,"|#|");
		System.out.println("========" + str1.length);//�������[]
		
		
		System.out.println("�ָ���1:" + sPlit("222@333@", "@").length + ":" + sPlit("222@333@", "@")[2]);//3
		System.out.println("�ָ���2:" + sPlit("222@333@444", "@").length);//3
		System.out.println("�ָ���3:" + sPlit("@333@", "@").length);//3
		System.out.println("�ָ���4:" + sPlit("@333@444", "@").length);//3
	}
	
	/**
	 * ���ݷָ����
	 * @param lineStr		��ȡ�ַ���
	 * @param split			�ָ��
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
			temp = temp.substring(index + le, temp.length());//ѭ��ȥ����ȡ���ֶ�
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
	 * ���ݷָ����
	 * @param lineStr		��ȡ�ַ���
	 * @param split			�ָ��
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
			temp = temp.substring(index + le, temp.length());//ѭ��ȥ����ȡ���ֶ�
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
