package com.cattsoft.file;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;

import org.junit.Test;

public class FileTest {

	public static void main(String[] args) {

		System.out.println("ɾ������ָ��ʱ����ļ���");
		deleteFileDay("C:/Users/Administrator/Desktop/ftp", 60);

		System.out.println("ɾ���ļ��У�");
		deldir("C:/Users/Administrator/Desktop/gg", true);
	}

	/**
	 * ɾ���ļ��л�����ļ���
	 * 
	 * @param dir_path
	 *            �ļ��е�path
	 * @param isDelRootDir
	 *            true ɾ����false ���
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
			// File.separator��ϵͳ�йص�Ĭ�����Ʒָ���
			if (dir_path.endsWith(File.separator)) {
				temp = new File(dir_path + childFiles[i]);
			} else {
				temp = new File(dir_path + File.separator + childFiles[i]);
			}
			if (temp.isFile()) {
				System.out.println("ɾ�����ļ�:" + temp);
				temp.delete();
			}
			if (temp.isDirectory()) {
				deldir(dir_path + "/" + childFiles[i], true);// ��ɾ���ļ���������ļ�
				java.io.File temp1 = new java.io.File(dir_path + "/"
						+ childFiles[i]);
				System.out.println("ɾ�����ļ���:" + temp1);
				temp1.delete(); // ɾ�����ļ���
			}
		}

		if (isDelRootDir) {
			System.out.println("ɾ�����Ŀ¼�µ������ļ�,��������:" + file);
			file.delete();
		}
	}

	public void deleteFlie() {
		deleteFileDay(System.getProperty("user.dir") + "/downFile", 60);
	}

	/**
	 * ɾ��ftp���ص��ļ�
	 * 
	 * @param sFileUrl
	 *            �ļ�·��
	 * @param savedays
	 *            ���������
	 */
	public static void deleteFileDay(String sFileUrl, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l; // * 1000l * 3600 * 24;�����ʱ����
			System.out.println("��ʼ���Ŀ¼" + sFileUrl + "�µ��ļ��Ƿ����  saveTime="
					+ saveDaylong + "ms");
			if (sFileUrl != null && !sFileUrl.trim().equals("")) {

				File dirFile = new File(sFileUrl);
				File files[] = dirFile.listFiles();
				for (File file : files) {
					if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
						System.out.println(file.getPath() + ",����ļ��Ѿ�����,ɾ�����ļ�");
						file.delete();
					}
				}
			} else {
				System.out.println("�ļ�Ŀ¼ΪsFileUrl:" + sFileUrl + "Ϊ��");
			}

		} catch (Exception ex) {
			System.out.println("ɾ��Ŀ¼��" + sFileUrl + "���ļ�����:");
		}

	}

	@Test
	public void createFile() {
		File file = new File("C:\\Users\\Administrator\\Desktop\\ftp.TXT");
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

	// ɾ���ļ���
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // ɾ�������������ļ�
			String filePath = folderPath;
			filePath = filePath.toString();
			File FilePath = new File(filePath);
			if (FilePath.listFiles().length == 0) {
				FilePath.delete(); // ɾ�����ļ���
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
				delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
				delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
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

		// �ַ����忽���ļ�
		 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./file/c.txt")));
		// BufferedWriter bw = new BufferedWriter(new
		// FileWriter("./file/c1.txt"));
		//
		// String line = null;
		// while ((line = br.readLine()) != null) {
		// bw.write(line);
		// bw.newLine();
		// bw.flush();
		// }
		// bw.close();
		// br.close();

		// �ֽ������忽��ͼƬ
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		bis = new BufferedInputStream(new FileInputStream("./file/����.png"));// �����࣬����һ��InputStream������
		bos = new BufferedOutputStream(new FileOutputStream("./file/����1.png"));
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
