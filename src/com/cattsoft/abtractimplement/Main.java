package com.cattsoft.abtractimplement;

/**
 * 	�ܣ�
 * 		���⣺ ��ʵ��һ���ӿ�ʱ������ʵ�ֽӿ��ϵ����з���; 
 * 			 ��ʱֻ��Ҫ���𷽷�����ʱ�������һ��������ʵ����ӿڡ�
 *  ע��
 * 			 ���������, ʵ�ֲ���Ҫ�ķ�������������Ϊ�գ�Ҳ������д(�ɵ���); 
 * 			 �����̳г�����ʱֻ����д��Ҫ��ע�ķ������С�
 *
 */
public class Main {

	public static void main(String[] args) {
		ITWorker it = new ITWorker();
		it.draw();
		it.network();
		it.fill();
	}

}
