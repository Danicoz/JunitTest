package com.cattsoft.think;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

import org.junit.Test;

public class CollectionTest {
	
	
	@Test
	public void sumTest(){
		int fact,i,j,sum=0;
		for(i=1; i<=10; i++){
			fact = 1;
			for(j=1; j<=i; j++)
				fact*=j;
			sum+=fact;
		}
		
		System.out.println(sum);
	
	}
	
	@Test
	public void fileTest(){
		byte[] buf = new byte[1024];
		
		try {
			FileInputStream fis = new FileInputStream("C:/Users/Administrator/Desktop/指对程序/子槽.txt");
			int bytes = fis.read(buf, 0, 1024);
			String str = new String(buf, 0, bytes);
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		
		//Arrays.asList: 可以把元素列表、数组转成 list
		//Colloctions.addAll: 可以把元素列表、数组增加到collection 中
		//list : set 改变序列的值
		Collection<Integer>coll = new ArrayList<Integer>(Arrays.asList(1,2,3,4));
//		System.out.println("coll" + coll);
//		
//		Integer[] moreInts = {5,6,7,8};
//		coll.addAll(Arrays.asList(moreInts));
//		System.out.println("Add moreInts" + coll);
//		
//		Collections.addAll(coll, 9,10,11,12);
//		System.out.println("Collection Add1" + coll);
//		
//		Collections.addAll(coll, moreInts);
//		System.out.println("Collections Add2" + coll);
//		
//		List<Integer>list = Arrays.asList(13,14,15,16);
//		list.set(1, 100);
//		System.out.println("list set" + list);
//		
//		
//		list.add(17);
		//System.out.println("Add list" + list); 底层是数组， 不能改变他的值
		
		Stack<String>st = new Stack<String>();
		st.push("1");
		st.push("2");
		st.push("3");
		
		while(!st.empty()){
			System.out.println(st.pop());
			System.out.println();
			System.out.println(st.size());
		}
		
	}
	
	
	
	
	
}
