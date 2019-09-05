package com.cattsoft.quart;

import java.util.HashMap;



import org.quartz.Job;

import com.cattsoft.utils.QuartzManager;


public class QuartTest {

	/**
	 * addJob(String jobName, Job job, String time, HashMap jobDataMap)
	 * 
	 * jobName : 任务名称;
	 * job : 具体的实现类;
	 * time : 时间格式
	 * jobDataMap ： 传参数（需获取固定的key）
	 * 
	 */
	public static void main(String[] args) {
		
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("plan", "plan");
			String crontab = "0/3 * * * * ?";//一秒输出一次
			QuartzManager.addJob("测试定时启动", new Worker(), crontab, map);
		} catch (Exception  e) {
			e.printStackTrace();
		}

	}

}
