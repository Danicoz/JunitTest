package com.cattsoft.utils.arraysutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArraysUtil {

	public static void main(String[] args) {
		//声明数组
	    String[] aArray = new String[5];
	    String[] bArray = {"a","b","c", "d", "e"};
	    String[] cArray = new String[]{"a","b","c","d","e"};
	}
	
	public static void printArray(Object[] a){
		String str = Arrays.toString(a);
		System.out.println(str);
	}
	
	//填充数组
	@Test
	public void testFill(){
		Object[] a = new Object[5];
		Arrays.fill(a, 8);
		printArray(a);//8,8,8,8,8
		
		Arrays.fill(a, 1, 3, 6);//位置 1-3 替换成6 ，不含3
		printArray(a);//8,6,6,8,8
	}
	
	//正序排序
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
		System.out.println(Arrays.equals(a1, a2));//false  改变数值顺序为 false，需先排序
	
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
		System.out.println(Arrays.binarySearch(a, 3, 6, 14));//-7  位置从 3 到 7  查找，不包含 7
	}
	
	//数组转成  arrayList
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
	
	//是否包含某个值
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
