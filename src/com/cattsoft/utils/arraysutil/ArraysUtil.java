package com.cattsoft.utils.arraysutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArraysUtil {

	public static void main(String[] args) {
		//��������
	    String[] aArray = new String[5];
	    String[] bArray = {"a","b","c", "d", "e"};
	    String[] cArray = new String[]{"a","b","c","d","e"};
	}
	
	public static void printArray(Object[] a){
		String str = Arrays.toString(a);
		System.out.println(str);
	}
	
	//�������
	@Test
	public void testFill(){
		Object[] a = new Object[5];
		Arrays.fill(a, 8);
		printArray(a);//8,8,8,8,8
		
		Arrays.fill(a, 1, 3, 6);//λ�� 1-3 �滻��6 ������3
		printArray(a);//8,6,6,8,8
	}
	
	//��������
	@Test
	public void testSort(){
		Object[] a = {1,3,4,6,2};
		Arrays.sort(a);
		printArray(a);
		
		String[] str ={"a","e","c","b"};
		Arrays.sort(str);
		printArray(str);
	}
	
	@Test
	public void testEquals(){
		int[] a1 = {1,2,4,5};
		int[] a2 = {1,2,5,4};
		System.out.println(Arrays.equals(a1, a2));//false  �ı���ֵ˳��Ϊ false����������
	
		Arrays.sort(a1);
		Arrays.sort(a2);
		System.out.println(Arrays.equals(a1, a2));//true	
	}
	
	
	@Test
	public void testBinarySearch(){
		int[] a = {1,3,14,26,7,11,53};
		System.out.println(Arrays.binarySearch(a, 14));
		System.out.println(Arrays.binarySearch(a, 17));//-4
		
		System.out.println(Arrays.binarySearch(a, 1, 3, 14));//2
		System.out.println(Arrays.binarySearch(a, 3, 6, 14));//-7  λ�ô� 3 �� 7  ���ң������� 7
	}
	
	//����ת��  arrayList
	@Test
	public void testArrayToArrayList(){
		String[]str = {"1","4","6","2","13"};
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(str));
		for(String a : list){
			System.out.print(a + " ");
		}
		System.out.println();
		
		HashSet<String>set = new HashSet<String>(Arrays.asList(str));
		System.out.println(set.size());
	}
	
	@Test
	public void testArrayListToArray(){
		List<String>list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		String[]str = list.toArray(new String[list.size()]);
		printArray(str);
		
	}
	
	//�Ƿ����ĳ��ֵ
	@Test
	public void testArrayContains(){
		String[]str = {"1","4","6","2","13"};
		boolean flag = Arrays.asList(str).contains("1");//true
		System.out.println(flag);
		
		int i = Arrays.binarySearch(str, "45");
		if(i < 0)
			flag = false;
		else
			flag = true;
		
		System.out.println(flag);//false
	}
	
	@Test
	public void testReverse(){
	    Object[] intArray = { 1, 2, 3, 4, 5 };
	    ArrayUtils.reverse(intArray);
	    printArray(intArray);//[5, 4, 3, 2, 1]
	}
	
}
