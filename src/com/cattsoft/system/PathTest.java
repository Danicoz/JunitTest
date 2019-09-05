package com.cattsoft.system;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.junit.Test;

public class PathTest {
  
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));//获取本地路径
		System.out.println(File.separator);//获取分割符window:\ Linux:/
		
		String str = System.getProperty("user.dir");
		str = str + "/downFile";
		File file = new File(str);
		
	    File[] file1 = file.listFiles();
	    String name = file1[0].getName();
	    System.out.println(name);
		
	}
	
	//bin是本项目的classpath。所有的Java源文件编译后的.class文件复制到这个目录中。
	@Test
	public void testClasspath(){
		
		URL str1 = PathTest.class.getResource("");
		System.out.println(str1);//file:/E:/eclipseWord/JunitTest/bin/com/cattsoft/system/
		
		URL str2 = PathTest.class.getResource("/");
		System.out.println(str2);//file:/E:/eclipseWord/JunitTest/bin/
		
		URL str3 = PathTest.class.getClassLoader().getResource("");
		System.out.println(str3);//file:/E:/eclipseWord/JunitTest/bin/
		
	}
	
	@Test
	public void testCmd(){
		String pre = "";
		Properties properties = System.getProperties();
		String os = properties.getProperty("os.name");
		if(os.toUpperCase().contains("WINDOW")){
			pre = "cmd /c start ";
			System.out.println("window 系统 " + pre);
		}else{
			pre = "sh";
			System.out.println("非window 系统 " + pre);	
		}
		
		//////////////////////////////////////////////////
		String path = "mspaint"; 
	    Runtime run = Runtime.getRuntime();
	    try {
	 
	        Process process = run.exec("cmd.exe /k start " + path);
	 
	        InputStream in = process.getInputStream();
	 
	        while (in.read() != -1) {
	 
	            System.out.println(in.read());
	 
	        }
	 
	        in.close();
	 
	        process.waitFor();
	 
	    } catch (Exception e) {
	 
	        e.printStackTrace();
	 
	    }
		
	}
	
	//输出系统所有属性。
	@Test
	public void testSystem(){
		  Properties properties = System.getProperties(); 
		  for (String key : properties.stringPropertyNames()) { 
			  System.out.println(key + "=" + properties.getProperty(key)); 
			  }
		
	}
	
}
