package com.cattsoft.collections;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.junit.Test;

import com.cattsoft.collections.bean.A;
import com.cattsoft.collections.bean.B;
import com.cattsoft.collections.bean.C;

/**
 *  1) HashSet的性能总是比TreeSet好(特别是最常用的添加、查询元素等操作)，因为TreeSet需要额外的红黑树算法来维护集合元素的次序。
 *     只有当需要一个保持排序的Set时，才应该使用TreeSet，否则都应该使用HashSet
 *	2) 对于普通的插入、删除操作，LinkedHashSet比HashSet要略慢一点，这是由维护链表所带来的开销造成的。不过，因为有了链表的存在，遍历LinkedHashSet会更快
 *	3) EnumSet是所有Set实现类中性能最好的，但它只能保存同一个枚举类的枚举值作为集合元素
 *	4) HashSet、TreeSet、EnumSet都是"线程不安全"的，通常可以通过Collections工具类的synchronizedSortedSet方法来"包装"该Set集合。
 *	    SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 * @author Administrator
 *
 */
public class SetTest {

	/**
	 * Set 不能包含有重复元素的集合
	 * 原理：判断hashCode()是否相同，是不增，否进入equals判断； 
	 * 	         判断equals() 是否相同，是不增，否新增数据。
	 * @param args
	 */
	@Test
	public void testHashSet() {
		HashSet<Object> hs = new HashSet<Object>();
		hs.add(new A());
		hs.add(new A());

		hs.add(new B());
		hs.add(new B());

		hs.add(new C());
		hs.add(new C());

		System.out.println(hs); //[B@1, B@1, C@2, A@4caaf64e, A@6e677ea2] 
	}
	
/*********************************************************************************************/	
	/**
	 * 总： 元素的顺序总是与添加顺序一致
	 */
	@Test
	public void testLinkedHashSet() {
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		lhs.add("1");
		lhs.add("2");
		lhs.add("3");
		lhs.add("8");
		lhs.add("4");
		
		System.out.println(lhs.contains("4"));

		System.out.println(lhs);

		lhs.remove("1");
		System.out.println(lhs);

		lhs.add("1");
		System.out.println(lhs);
	}
	
/*********************************************************************************************/	
	@Test
	public void testTreeSet() {
		TreeSet<String> ts = new TreeSet<String>();
		ts.add("1");
		ts.add("2");
		ts.add("3");
		ts.add("8");
		ts.add("4");

		System.out.println(ts);// 升序排序

		System.out.println(ts.first());
		System.out.println(ts.last());

		// 输出小于4的子集
		System.out.println(ts.headSet("4"));// [1, 2, 3]
		// 输出大于或等于4的子集
		System.out.println(ts.tailSet("4"));// [4, 8]

		// 移除第一位或最后一位并删除
		System.out.println(ts.pollFirst());
		System.out.println(ts.pollLast());
		System.out.println(ts);// [2, 3, 4]

		// 大于等于4小于8子集
		System.out.println(ts.subSet("4", "8"));// [4]
		// 1代表前者小，0代表两者相等，1代表前者大。
	}
/*********************************************************************************************/	
	
	/**
	 * 自定义排序顺序，降序排序
	 * 1代表前者小，0代表两者相等，1代表前者大。
	 */
	@Test
	public void testSort() {

		TreeSet ts = new TreeSet(new Comparator() {
			public int compare(Object o1, Object o2) {

				M m1 = (M) o1;
				M m2 = (M) o2;
				return m1.age > m2.age ? -1 : m1.age < m2.age ? 1 : 0;
			}

		});

		ts.add(new M(1));
		ts.add(new M(2));
		ts.add(new M(4));
		ts.add(new M(3));
		
		System.out.println(ts);

	}

	class M {
		int age;

		public M(int age) {
			this.age = age;
		}

		public String toString() {
			return "M[age:" + age + "]";
		}
	}
/*********************************************************************************************/	
	enum Session {
		SPRING, SUMMER, FALL, WINTER
	}
	
	@Test
	public void testEnumSet(){
		EnumSet es1 = EnumSet.allOf(Session.class);
		System.out.println(es1);//[SPRING, SUMMER, FALL, WINTER]

		EnumSet es4 = EnumSet.range(Session.SUMMER, Session.WINTER);
		System.out.println(es4);// 输出[SUMMER,FALL,WINTER]
		
	}
/*********************************************************************************************/

	

}





