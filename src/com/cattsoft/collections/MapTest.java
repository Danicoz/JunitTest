package com.cattsoft.collections;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;

/**
 * 总：
 * 	1、枚举集合Map,key必须是枚举类型；
 * 	2、只是简单遍历数据，使用foreach循环遍历；
 * 	3、涉及到遍历过程删除的情况，要用迭代器删除，否则报ConcurrentModificationException异常；
 *  4、不能在遍历过程中新增数据。
 * @author Administrator
 *
 */
public class MapTest {

	@Test
	public void testEnumMap() {
		EnumMap enumMap = new EnumMap(Season.class);
		enumMap.put(Season.SPRING, "春风徐来");
		enumMap.put(Season.SUMMER, "夏日炎炎");
		enumMap.put(Season.FALL, "秋分瑟瑟");
		enumMap.put(Season.WINTER, "白雪皑皑");

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
			    //map.put("key4_conKey", "value4");//新增有错误，只能新建个map保存数据
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
		
		//使用foreach遍历数据，效率更高
		Set set = map.entrySet();
		for(Object obj : set){
			Entry e = (Entry) obj;
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}
	
	//遍历集合value值
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
