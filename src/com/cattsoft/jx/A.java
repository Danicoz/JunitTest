package com.cattsoft.jx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A {

	private String key;
	private String A;
	private String B;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getB() {
		return B;
	}

	public void setB(String b) {
		B = b;
	}

	public static void main(String[] args) {

		//模拟创进来的List Bean集合
		A a = null;
		List<A> list = new ArrayList<A>();
		for (int i = 1; i <= 5; i++) {
			a = new A();
			a.setKey("key" + i);
			a.setA("a" + i);
			a.setB("b" + i);
			list.add(a);
		}
		a = new A();
		a.setKey("key1");
		a.setA("b1");
		a.setB("a1");
		list.add(a);

		//同个key 所有list
		Map<String, List<A>> map = new HashMap<String, List<A>>();
		List<A> list1 = null;
		for (A aBean : list) {
			String key = aBean.getKey();
			if (map.containsKey(key)) {
				list1 = map.get(key);
			} else {
				list1 = new ArrayList<A>();
			}
			list1.add(aBean);
			map.put(key, list1);
		}

		System.out.println(map.get("key5").size());
		
		//比较A=Z 和 Z=A
		List<A>addList = new ArrayList<A>();
		for (String key : map.keySet()) {
			List<A> aList = map.get(key);//获取key 下的list
			
				for(int i =0; i<aList.size(); i++){
					A a2 = aList.get(i);
					for(int j = i+1; j<aList.size(); j++){
						A a3 = aList.get(j);
						if(a2.getA().equals(a3.getB()) && a2.getB().equals(a3.getA())){
							a3.setA("移除的数据：" + a3.getA());
						}
					}
					addList.add(a2);//是否相同都要入库的
				}

		}

		System.out.println(addList.size());
//		System.out.println(map.size());
//		System.out.println();

	}
}
