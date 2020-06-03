package com.cattsoft.quart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Worker implements Job{

	@Override
	public void execute(JobExecutionContext param) throws JobExecutionException {
		
		System.out.println(">>>>");
		String name = param.getJobDetail().getName();
		HashMap<String, String> map = (HashMap<String, String>)param.getJobDetail().getJobDataMap().get("jobDataMap");
		System.out.println("name=" + name + "参数=" + map.get("plan"));
		
		String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		System.out.println(str);
		System.out.println("定时任务启动：" + str);

		new Worker1().work();
		new Worker2().work();
	}

	
}
