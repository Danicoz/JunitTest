package com.cattsoft.quart;

import java.util.HashMap;



import org.quartz.Job;

import com.cattsoft.utils.QuartzManager;


public class QuartTest {

	/**
	 * addJob(String jobName, Job job, String time, HashMap jobDataMap)
	 * 
	 * jobName : ��������;
	 * job : �����ʵ����;
	 * time : ʱ���ʽ
	 * jobDataMap �� �����������ȡ�̶���key��
	 * 
	 */
	public static void main(String[] args) {
		
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("plan", "plan");
			String crontab = "0/3 * * * * ?";//һ�����һ��
			QuartzManager.addJob("���Զ�ʱ����", new Worker(), crontab, map);
		} catch (Exception  e) {
			e.printStackTrace();
		}

	}

}
