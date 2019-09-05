package com.cattsoft.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import org.junit.Test;

public class FileTest {

	
	
	public static void main(String[] args) {
		
		 for (int i = 0; i < args.length; i++) {  
	            System.out.println("args[" + i + "] is <" + args[i] + ">");  
	        }  
		
		String path = System.getProperty("user.dir") + "/downFile/gg.txt";
		File file = new File(path);
		
		try {
			String path1 = file.getAbsolutePath();
			String name = file.getName();
			String parent = file.getParent();
			String path2 = file.getPath();
			File parentFile = file.getParentFile();
			System.out.println("path1=" + path1 + " name=" + name + " parent=" + 
			parent + " path2=" + path2 + " FileName=" + parentFile.getName());
			
			String str = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((str = br.readLine()) != null){
				if(!str.equals("")){
					System.out.println(str);
				}
				//System.out.println(br);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBufferReader() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(br.readLine());
	}
	
	@Test
	public void testInputOutput(){
		System.out.println("print out:");
		int b;
		try {
			while((b = System.in.read()) != -1){
				System.out.println((char)b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断是否是文件夹或是文件
	 * @throws IOException
	 */
	@Test
	public void testFile() throws IOException{
	
		String fPath = System.getProperty("user.dir") + "/file/gg.txt";
		File file = new File(fPath.substring(0, fPath.lastIndexOf("/")));
		if(!file.isDirectory()){
			file.mkdir();
		}
		System.out.println(file.getAbsolutePath());
		
		File file2 = new File(file, "aa.txt");
		if(!file2.exists()){
			file2.createNewFile();
		}
		System.out.println(file2.getAbsolutePath());
	}
	
	
	@Test
	public void testCopyFile() throws IOException{
		String pathSrc = System.getProperty("user.dir") + "/file/aa.txt";
		String pathTar = System.getProperty("user.dir") + "/file/bb.txt";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathSrc)));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathTar)));
		
//		String b = "";
//		while((b = br.readLine() )!= null){
//			wr.write(b + "\r");
//		}
//		wr.flush();
		
		char [] buffer = new char[1024];
		
		while(br.ready()){
			int i = buffer.length;
			int len = br.read(buffer, 0, buffer.length);
			wr.write(buffer, 0, len);
		}
		
		wr.flush();
		
		br.close();
		wr.close();
	}
	
	
	@Test
	public void testAvailable() throws IOException{
		String pathSrc = System.getProperty("user.dir") + "/file/aa.txt";
		String pathTar = System.getProperty("user.dir") + "/file/bb.txt";
		FileInputStream fi = new FileInputStream(pathSrc);
		FileOutputStream fo = new FileOutputStream(pathTar);
		byte [] buffer = new byte[1024];
		System.out.println(fi.available());
		while(fi.available() > 0){
			//System.out.println(">>>" + fi.available());//1764
			int read_byte = fi.read(buffer);
			//System.out.println(">>>" + fi.available());//740
			System.out.print(new String(buffer, 0, read_byte, "GBK"));
			fo.write(buffer, 0, read_byte);
			//fo.write(buffer);//284
			fo.flush();
		}
	}
	
	
	/**
	 * 把数据一行一行写入文件
	 */
	@Test
	public void testWriteFile(){
		
		try {
			String pathSrc = System.getProperty("user.dir") + "/file/" + 
					new SimpleDateFormat("yyyymmdd_HH").format(new Date()) + ".txt";
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathSrc), "GBK"));
			
			for(int i = 0; i<=10; i++){
				String str = "lidj列间谍机,坑爹,we , null,2doj, ,," + i;
				wr.write(str + System.getProperty("line.separator"));
			}
			wr.flush();
			wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	
	
	
}
