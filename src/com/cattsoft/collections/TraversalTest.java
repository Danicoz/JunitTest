package com.cattsoft.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class TraversalTest {

	@Test
	public void testIterator() {

		List list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");

		Iterator it = list.iterator();
		while (it.hasNext()) {
			Object str = it.next();
			System.out.println(str);
			if (str.equals("1")) {
				it.remove();
			}
			str = "4";//改不了原集合值
		}
		System.out.println(list);//[2, 3]
	}

	@Test
	public void testForeach() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");

		for (String str : list) {
			System.out.println(str);
			// list.remove(0);//ConcurrentModificationException

		}
	}

	@Test
	public void testListIterator() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println(list);// [1, 2, 3]

		ListIterator<String> lit = list.listIterator();

		while (lit.hasNext()) {
			String str = lit.next();
			System.out.print(str + " ");
			if (str.equals("3")) {
				lit.add("4");
			}

		}
		System.out.println("\r\n" + list);//[1, 2, 3, 4]

		//反向迭代
		while (lit.hasPrevious()) {
			String str = lit.previous();
			System.out.print(str + " ");//4 3 2 1
		}
		System.out.println(list);

	}

}
