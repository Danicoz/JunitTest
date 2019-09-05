package com.cattsoft.utils;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	// �����ļ�·��
	public static void deleteFileDay(String sFileUrl, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l * 3600 * 24;
			logger.info("��ʼ���Ŀ¼" + sFileUrl + "�µ��ļ��Ƿ����  saveTime="
					+ saveDaylong + "ms");
			if (sFileUrl != null && !sFileUrl.trim().equals("")) {
				File dirFile = new File(sFileUrl);
				File files[] = dirFile.listFiles();
				for (File file : files) {
					if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
						logger.info(file.getPath() + "�ļ��Ѿ����� ɾ�����ļ�");
						file.delete();
					}
				}
			} else {
				logger.info("�ļ�Ŀ¼ΪsFileUrl:" + sFileUrl + "Ϊ��");
			}

		} catch (Exception ex) {
			logger.error("ɾ��Ŀ¼��" + sFileUrl + "���ļ�����:");
		}
	}

	// ����Ϊ�ļ�����
	public static void deleteFile(File file, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l * 3600 * 24;
			logger.info("��ʼ����ļ�" + file + "���Ƿ����");
			if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
				file.delete();
				logger.info("��ɾ���ļ�");
			}
		} catch (Exception ex) {
			logger.error("ɾ���ļ�����", ex);
		}
	}

	// ɾ���ļ���
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // ɾ�������������ļ�
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if(myFilePath.listFiles().length == 0){
				myFilePath.delete(); // ɾ�����ļ���
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
				deleteFile(temp, 2);
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// ��ɾ���ļ���������ļ�
				delFolder(path + "/" + tempList[i]);// ��ɾ�����ļ���
				flag = true;
			}
		}
		return flag;
	}
	
//	public static void main(String[] args) {
//		delFolder("./deleteFile");
//	}
}
