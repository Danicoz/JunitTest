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
 *  1) HashSet���������Ǳ�TreeSet��(�ر�����õ���ӡ���ѯԪ�صȲ���)����ΪTreeSet��Ҫ����ĺ�����㷨��ά������Ԫ�صĴ���
 *     ֻ�е���Ҫһ�����������Setʱ����Ӧ��ʹ��TreeSet������Ӧ��ʹ��HashSet
 *	2) ������ͨ�Ĳ��롢ɾ��������LinkedHashSet��HashSetҪ����һ�㣬������ά�������������Ŀ�����ɵġ���������Ϊ��������Ĵ��ڣ�����LinkedHashSet�����
 *	3) EnumSet������Setʵ������������õģ�����ֻ�ܱ���ͬһ��ö�����ö��ֵ��Ϊ����Ԫ��
 *	4) HashSet��TreeSet��EnumSet����"�̲߳���ȫ"�ģ�ͨ������ͨ��Collections�������synchronizedSortedSet������"��װ"��Set���ϡ�
 *	    SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 * @author Administrator
 *
 */
public class SetTest {

	/**
	 * Set ���ܰ������ظ�Ԫ�صļ���
	 * ԭ���ж�hashCode()�Ƿ���ͬ���ǲ����������equals�жϣ� 
	 * 	         �ж�equals() �Ƿ���ͬ���ǲ��������������ݡ�
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
	 * �ܣ� Ԫ�ص�˳�����������˳��һ��
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

		System.out.println(ts);// ��������

		System.out.println(ts.first());
		System.out.println(ts.last());

		// ���С��4���Ӽ�
		System.out.println(ts.headSet("4"));// [1, 2, 3]
		// ������ڻ����4���Ӽ�
		System.out.println(ts.tailSet("4"));// [4, 8]

		// �Ƴ���һλ�����һλ��ɾ��
		System.out.println(ts.pollFirst());
		System.out.println(ts.pollLast());
		System.out.println(ts);// [2, 3, 4]

		// ���ڵ���4С��8�Ӽ�
		System.out.println(ts.subSet("4", "8"));// [4]
		// 1����ǰ��С��0����������ȣ�1����ǰ�ߴ�
	}
/*********************************************************************************************/	
	
	/**
	 * �Զ�������˳�򣬽�������
	 * 1����ǰ��С��0����������ȣ�1����ǰ�ߴ�
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
		System.out.println(es4);// ���[SUMMER,FALL,WINTER]
		
	}
/*********************************************************************************************/

	

}





