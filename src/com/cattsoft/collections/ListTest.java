package com.cattsoft.collections;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import com.cattsoft.dbutil.DBUtil;
import com.cattsoft.dbutil.SysConstant;
import org.junit.Test;


/**
 * 1. java提供的List就是一个"线性表接口"，ArrayList(基于数组的线性表)、LinkedList(基于链的线性表)是线性表的两种典型实现
 *	2. Queue代表了队列，Deque代表了双端队列(既可以作为队列使用、也可以作为栈使用)
 *	3. 因为数组以一块连续内存来保存所有的数组元素，所以数组在随机访问时性能最好。所以的内部以数组作为底层实现的集合在随机访问时性能最好。
 *	4. 内部以链表作为底层实现的集合在执行插入、删除操作时有很好的性能
 *	5. 进行迭代操作时，以链表作为底层实现的集合比以数组作为底层实现的集合性能好
 */
public class ListTest {

	/**
	 * 1) ensureCapacity(int minCapacity): 将ArrayList集合的Object[]数组长度增加minCapacity
	 * 2) trimToSize(): 调整ArrayList集合的Object[]数组长度为当前元素的个数。程序可以通过此方法来减少ArrayList集合对象占用的内存空间
	 */
	@Test
	public void testArrayList(){
		List list = new ArrayList();
		
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
		
		//拼接字符串
		String values = "9" + ",";
		for (int i = 0; i < list.size(); i++) {
			values = values + list.get(i);
			if (i < list.size() - 1) {
				values = values + ",";
			}
		}
		System.out.println(values);
		
		
		
		
		System.out.println(list);
		
		//向指定的位置插入数据
		list.add(1, "5");
		System.out.println(list);//[1, 5, 2, 3, 4]
		
		for(Object str : list){
			System.out.println(str);
			//list.add("8");//ConcurrentModificationException
		}
		
		list.remove(1);
		System.out.println(list);//[1, 2, 3, 4]
		
		//输出下标
		System.out.println(list.indexOf("2"));//1
		
		//替换下标
		list.set(3, "5");
		System.out.println(list);//[1, 2, 3, 5]
		
		//输出子集,不包括 2
		System.out.println(list.subList(0, 2));//[1, 2]
		
	}
	
	
	/**
	 * Stack的后进先出的特点
	 */
	@Test
	public void testStack(){
		Stack st = new Stack();
		st.push("1");
		st.push("2");
		st.push("3");
		System.out.println(st);
		
		st.push("4");
		
		//取数据但不删除
		System.out.println(st.peek());
		System.out.println(st);
		
		//取数据且删除		
		System.out.println(st.pop());
		System.out.println(st);

	}
	
	/**
	 * LinkedList同时表现出了双端队列、栈的用法。功能非常强大。栈顶先出
	 * 
	 */
	@Test
	public void testLinkedList(){
		LinkedList ll = new LinkedList();
		
		ll.offer("1");//入队列尾部
		ll.push("2");//入栈顶
		ll.offerFirst("3");//入栈顶
		ll.offer("4");
		System.out.println(ll);//[3, 2, 1, 4]
	
		
		for(int i=0; i<ll.size(); i++){
			System.out.println(ll.get(i));
		}
		
		//取栈顶数据但不删除
		System.out.println(ll.peekFirst());
		System.out.println(ll.peek());
		System.out.println(ll);//[3, 2, 1]
		
		//取栈顶数据且删除
		System.out.println(ll.poll());
		System.out.println(ll.pollLast());
		System.out.println(ll);//[2]
	}
	
	/**
	 * PriorityQueue不允许插入null元素，它还需要对队列元素进行排序
	 */
	@Test
	public void testPriorityQueue(){
	
		PriorityQueue pq = new PriorityQueue();
		pq.add(-1);
		pq.add(2);
		pq.add(3);
		pq.add(7);
		pq.add(1);
		
		System.out.println(pq);//[-1, 2, 3, 7, 2]
		
		PriorityQueue pq1 = new PriorityQueue();
		pq1.offer(6);
		pq1.offer(-1);
		pq1.offer(9);
		pq1.offer(7);
	
		System.out.println(pq1);//[-1, 6, 9, 7]
	}
	
	@Test
	public void testFor(){
		for(int i = 0; i < 3; i++){
			System.out.println(i);
			
			for(int j = i + 1; j < 3; j++){
				System.out.println("j" + j);
				if(j==2){
					i = 1;
					break;
				}
			}
		}
	}

	@Test
	public void testList() throws SQLException {
		String sql = "select DATE_FORMAT(DATE,'%Y-%m-%d') str from t_calendar_info where STATUS IN(0,3) order by DATE";
		Connection conn = DBUtil.getDBConn(SysConstant.TAR_DBALIAS);
		List<String> list = DBUtil.queryHashMapList(conn, sql,null);

		System.out.println(list.get(0));
		Integer firstNum = list.indexOf("2020-12-30");
		Integer secondNum = list.indexOf("2020-12-31");
		System.out.println("firstNum=" + firstNum + " secondNum=" + secondNum);

	}



	
}
