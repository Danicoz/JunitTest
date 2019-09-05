package com.cattsoft.collections;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class CollectionsTest {

	/**
	 * shuffle():�������˳��
	 */
	@Test
	public void shuffletest() {
		List<Double>list = new ArrayList<Double>();
		double array[] = {112, 111, 23, 456, 231 };
		for (int i = 0; i < array.length; i++) {
		list.add(new Double(array[i]));
		}
		Collections.shuffle(list);
		for (int i = 0; i < array.length; i++) {
		  System.out.println(list.get(i));
		}
	}
	
	/**
	 * sort():��Ȼ˳������(��)
	 * reverse():��������(��)
	 */
	@Test
	public void sortTest(){
		List<Integer>list = new ArrayList<Integer>();
		Random random = new Random();
		for(int i = 0; i < 10; i++){
			list.add(random.nextInt(100));
		}
		Collections.sort(list);
		System.out.print("sort():");
		for(int i = 0; i < list.size(); i++){
			System.out.print(list.get(i) + ",");
		}
		System.out.println();
		Collections.reverse(list);
		System.out.print("reverse():");
		for(int i = 0; i<list.size(); i++){
			System.out.print(list.get(i) + ",");
		}
	}
	
	/**
	 * fill():ȫ���滻
	 */
	@Test
	public void fillTest(){
		List<String>list = new ArrayList<String>();
		String[]str = {"aa","bb","cc","dd"};
		for(int i = 0; i<str.length; i++){
			list.add(new String(str[i]));
		}
		Collections.fill(list, "ee");
		System.out.print("fill():");
		for(int i = 0; i<list.size(); i++){
			System.out.print(list.get(i) + ",");
		}
		
	}
	
	/**
	 * copy(src, tar):��tar���ݸ��Ƶ�src�С�src.size() >= tar.size()
	 */
	@Test
	public void copyTest(){
		List<Integer>src = new ArrayList<Integer>();
		List<Integer>tar = new ArrayList<Integer>();
		
		Integer[] arr = {12,13,14,15};
		for(int i = 0; i < arr.length; i++){
			src.add(new Integer(arr[i]));
		}
		Integer[] arr1 = {16,17};
		for(int i = 0; i < arr1.length; i++){
			tar.add(new Integer(arr1[i]));
		}
		
		List<String>tar1 = new ArrayList<String>();
		String[] arr2 = {"aa", "bb"};
		for(int i = 0; i < arr2.length; i++){
			tar1.add(new String(arr2[i]));
		}
		//Collections.copy(src, tar1);
		
		Collections.copy(src, tar);
		
		System.out.print("copy():");
		for(int i = 0; i < src.size(); i++){
			System.out.print(src.get(i) + ",");
		}
		
	}
	
	/**
	 * min():��ȡ��Сֵ
	 * max():��ȡ���ֵ
	 */
	@Test
	public void minAndMaxTest(){
		List<Integer>list = new ArrayList<Integer>();
		Integer[]arr = {12,13,14,15,16,8};
		for(int i = 0; i < arr.length; i++){
			list.add(new Integer(arr[i]));
		}
		System.out.println("min=" + Collections.min(list));
		System.out.println("max=" + Collections.max(list));
	}
	
	/**
	 * IndexOfSubList(src,tar):tar������src��һ�γ��ֵ��±�
	 * lastIndexOfSubList(src,tar):tar������src�����ֵ��±�
	 * 
	 */
	@Test
	public void indexTest(){
		List<Integer>src = new ArrayList<Integer>();
		List<Integer>tar = new ArrayList<Integer>();
		
		Integer[] arr = {12,13,14,15,16,14};
		for(int i = 0; i < arr.length; i++){
			src.add(new Integer(arr[i]));
		}
		Integer[] arr1 = {14};
		for(int i = 0; i < arr1.length; i++){
			tar.add(new Integer(arr1[i]));
		}
		
		int firstIndex = Collections.indexOfSubList(src, tar);
		System.out.println("indexOfSubList=" + firstIndex + " ֵ=" + src.get(firstIndex));
		int lastIndex = Collections.lastIndexOfSubList(src, tar);
		System.out.println("lastIndexOfSubList=" + lastIndex + " ֵ=" + src.get(lastIndex));
	}
	
	/**
	 * rotate(list,data):�ƶ�����  dataΪ���������ƶ��������������ƶ�
	 */
	@Test
	public void rotateTest(){
		List<Integer>list = new ArrayList<Integer>();
		Integer[]arr = {12,13,14,15,16,8};
		for(int i = 0; i < arr.length; i++){
			list.add(new Integer(arr[i]));
		}
		Collections.rotate(list, -1);
		System.out.print("�����ƶ� rotate():");
		for(int i = 0; i < list.size(); i++){
			if(i != list.size()-1)
			System.out.print(list.get(i) + ",");
			else
				System.out.println(list.get(i));
		}
		
		Collections.rotate(list, 1);
		System.out.print("�����ƶ� rotate():");
		for(int i = 0; i < list.size(); i++){
			if(i != list.size()-1)
			System.out.print(list.get(i) + ",");
			else
				System.out.println(list.get(i));
		}
	}
	
	@Test
	public void testContainAll(){
		List<String>list1 = new ArrayList<String>();
		List<String>list2 = new ArrayList<String>();
		
		list1.add("1");
		list1.add("2");
		list1.add("3");
		
		list2.add("1");
		list2.add("2");
		list2.add("3");
		
//		int num = list1==null?0:list1.size();
//		System.out.println("num=" + num);
//		
//		boolean flag = list1.containsAll(list2);
//		System.out.println("flag=" + flag);
//		
//		list1.add("d");
//		boolean flag1 = list1.containsAll(list2);
//		System.out.println("flag1=" + flag1);
		
		list1.addAll(list2);
		
		
	}
	
	/****************List ���ϱ������ַ���****************/
	//��ͨfor�������±������
	@Test
	public void testForListOne(){
		
		List<Integer>list = new ArrayList<Integer>();
		for(int i=1; i<=5; i++){
			list.add(i);
		}
		
		//System.out.println(list.size());
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i));//4 �Ľ��û�����˵�������ı���
			if(i == 2){
				list.remove(i);
				i--; //�ؼ�����
			}
		}
	}
	
	//Iterator ���� ;����
	@Test
	public void testForListTwo(){
		
		List<Integer>list = new ArrayList<Integer>();
		for(int i=1; i <= 5; i++){
			list.add(i);
		}
		
		Iterator<Integer>it = list.iterator();
		while(it.hasNext()){
			Integer num = it.next();
			if(num == 2){
				it.remove();
			}
			System.out.println("num=" + num);
		}
		System.out.println(list.size());//4
	}
	
	
	/**************���� Map ���ֱ���************/
	
	//ʹ��ȡ������ɾ������
	@Test
	public void testMapOne(){
		
		Map<Integer, String>map = new HashMap<Integer, String>();
		for(int i=1; i<=5; i++){
			map.put(i, "value" + i);
		}
		
		for(Map.Entry<Integer, String> entry : map.entrySet()){
			Integer key = entry.getKey();
			String value = entry.getValue();
			//String value = map.get(key);
			System.out.println(key + ":" + value);
			//map.remove(key); //����ConcurrentModificationException
		}
		
		
	}
	
	@Test
	public void testMapTwo(){
		
		Map<Integer, String>map = new HashMap<Integer, String>();
		for(int i=1; i<=5; i++){
			map.put(i, "value" + i);
		}
		
		//Set<Integer>set = map.keySet();
		for(Integer key : map.keySet()){
			String value = map.get(key);
			System.out.println(key + ":" + value);
			//map.remove(key);//����ConcurrentModificationException
		}
		
	}
	
	//ɾ���Ͳ�ѯ
	@Test
	public void testMapThree(){
		
		Map<Integer, String>map = new HashMap<Integer, String>();
		for(int i=1; i<=5; i++){
			map.put(i, "value" + i);
		}
		
		Iterator<Map.Entry<Integer, String>>it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, String>entry = it.next();
			Integer key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key + ":" + value);
			
			if(value.equals("value2")){
				it.remove();
			}
		}
		System.out.println(map.size());//4
	}

	
	
	
}
