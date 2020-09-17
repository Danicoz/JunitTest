package com.cattsoft.file;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class FileTest {

	public static void main(String[] args) {

		System.out.println("删除超过指定时间的文件：");
		deleteFileDay("C:/Users/Administrator/Desktop/ftp", 60);

		System.out.println("删除文件夹：");
		deldir("C:/Users/Administrator/Desktop/gg", true);
	}

	/**
	 * 删除文件夹或清空文件夹
	 * 
	 * @param dir_path
	 *            文件夹的path
	 * @param isDelRootDir
	 *            true 删除，false 清空
	 */
	public static void deldir(String dir_path, boolean isDelRootDir) {
		File file = new File(dir_path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] childFiles = file.list();
		File temp = null;

		for (int i = 0; i < childFiles.length; i++) {
			// File.separator与系统有关的默认名称分隔符
			if (dir_path.endsWith(File.separator)) {
				temp = new File(dir_path + childFiles[i]);
			} else {
				temp = new File(dir_path + File.separator + childFiles[i]);
			}
			if (temp.isFile()) {
				System.out.println("删除的文件:" + temp);
				temp.delete();
			}
			if (temp.isDirectory()) {
				deldir(dir_path + "/" + childFiles[i], true);// 先删除文件夹里面的文件
				java.io.File temp1 = new java.io.File(dir_path + "/"
						+ childFiles[i]);
				System.out.println("删除的文件夹:" + temp1);
				temp1.delete(); // 删除空文件夹
			}
		}

		if (isDelRootDir) {
			System.out.println("删除这个目录下的所有文件,包括本身:" + file);
			file.delete();
		}
	}

	public void deleteFlie() {
		deleteFileDay(System.getProperty("user.dir") + "/downFile", 60);
	}

	/**
	 * 删除ftp下载的文件
	 * 
	 * @param sFileUrl
	 *            文件路径
	 * @param savedays
	 *            保存的天数
	 */
	public static void deleteFileDay(String sFileUrl, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l; // * 1000l * 3600 * 24;保存最长时间间隔
			System.out.println("开始检查目录" + sFileUrl + "下的文件是否过期  saveTime="
					+ saveDaylong + "ms");
			if (sFileUrl != null && !sFileUrl.trim().equals("")) {

				File dirFile = new File(sFileUrl);
				File files[] = dirFile.listFiles();
				for (File file : files) {
					if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
						System.out.println(file.getPath() + ",这个文件已经过期,删除该文件");
						file.delete();
					}
				}
			} else {
				System.out.println("文件目录为sFileUrl:" + sFileUrl + "为空");
			}

		} catch (Exception ex) {
			System.out.println("删除目录下" + sFileUrl + "的文件出错:");
		}

	}

	@Test
	public void createFile() {
		File file = new File("C:\\Users\\Danicoz\\Desktop\\ftp.TXT");
		try {
			if (!file.exists()) {
				file.createNewFile();
			} else {
				// file.delete();
			}
			FileInputStream fr = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fr));
			String str = null;
			while ((str = br.readLine()) != null) {
				System.out.println(str);
			}
			FileOutputStream fo = new FileOutputStream(file);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fo));
			bw.write("ggg");
			bw.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDelFile() {
		delFolder("./deleteFile");
	}

	// 删除文件夹
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有文件
			String filePath = folderPath;
			filePath = filePath.toString();
			File FilePath = new File(filePath);
			if (FilePath.listFiles().length == 0) {
				FilePath.delete(); // 删除空文件夹
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	@Test
	public void testReanameTo() throws IOException {
		// File f = new File("./file/c.txt");
		// f.createNewFile();
		// f.renameTo(new File("./file/cc.txt"));

		// FileWriter fw = new FileWriter("./file/c.txt", true);
		// fw.write("test_writer1");
		// fw.flush();
		// fw.close();

		// FileInputStream fs = new FileInputStream("./file/c.txt");
		// FileOutputStream fo = new FileOutputStream("./file/c1.txt");
		//
		// byte[] b = new byte[1024];
		// int len = 0;
		// while((len=fs.read(b))!=-1){
		// fo.write(b, 0 ,len);
		// }
		// fo.close();
		// fs.close();

		// 字符缓冲拷贝文件
//		 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./file/c.txt"), "GBK"));
//		 BufferedWriter bw = new BufferedWriter(new FileWriter("./file/c1.txt"));
//
//		 String line = null;
//		 while ((line = br.readLine()) != null) {
//		 bw.write(line);
//		 bw.newLine();
//		 bw.flush();
//		 }
//		 bw.close();
//		 br.close();

		// 字节流缓冲拷贝图片
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		bis = new BufferedInputStream(new FileInputStream("./file/银川市.pdf"));// 匿名类，传入一个InputStream流对象
		bos = new BufferedOutputStream(new FileOutputStream("./file/银川市1.pdf"));
		byte[] b = new byte[1024];
		int len = 0;
		while ((len= bis.read(b)) != -1) {
			bos.write(b, 0 , len);
			bos.flush();
		}
		
		bos.close();
		bis.close();
	}

	/**
	 * 浏览器选择文件上传
	 */
	public void testMultipartFile(MultipartFile multipartFile) throws IOException {

		String outPath = "./file/某某.pdf"; // 下载的地址

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		bis = new BufferedInputStream(multipartFile.getInputStream());
		bos = new BufferedOutputStream(new FileOutputStream(outPath));
		byte[] b = new byte[1024];
		int len = 0;
		while ((len= bis.read(b)) != -1) {
			bos.write(b, 0 , len);
			bos.flush();
		}

		bos.close();
		bis.close();
	}

	
	@Test
	public void testFile(){
		File file = new File("./file");
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".ctl");
			}
		});
		
		for(File f : files){
			System.out.println(f.getName());
		}
	}
    
}
