package com.cattsoft.collections;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

/**
 * �ܣ�
 * 	1��ö�ټ���Map,key������ö�����ͣ�
 * 	2��ֻ�Ǽ򵥱������ݣ�ʹ��foreachѭ��������
 * 	3���漰����������ɾ���������Ҫ�õ�����ɾ��������ConcurrentModificationException�쳣��
 *  4�������ڱ����������������ݡ�
 * @author Administrator
 *
 */
public class MapTest {

	@Test
	public void testEnumMap() {
		EnumMap enumMap = new EnumMap(Season.class);
		enumMap.put(Season.SPRING, "��������");
		enumMap.put(Season.SUMMER, "��������");
		enumMap.put(Season.FALL, "���ɪɪ");
		enumMap.put(Season.WINTER, "��ѩ����");

		System.out.println(enumMap);
	}

	@Test
	public void testKeySet() {
		Map map = new HashMap();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");

		Iterator it = map.keySet().iterator();

		while (it.hasNext()) {
			String str = (String) it.next();
			System.out.print(str + " ");

			if (map.containsKey("key4")) {
//				it.remove();
			    //map.put("key4_conKey", "value4");//�����д���ֻ���½���map��������
			}

			if (map.containsValue("value4")) {
				it.remove();
				// map.put("key41", "value4_conValue");
			}
		}
		System.out.println(map);
	}

	@Test
	public void testEntrySet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> entry = (Entry<String, String>) it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		//ʹ��foreach�������ݣ�Ч�ʸ���
		Set set = map.entrySet();
		for(Object obj : set){
			Entry e = (Entry) obj;
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
	
	//��������valueֵ
	@Test
	public void testValues(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		
		Iterator it = map.values().iterator();
		while(it.hasNext()){
			String value = (String) it.next();
			System.out.println(value);
		}	  
	}
}

enum Season {
	SPRING, SUMMER, FALL, WINTER
}
