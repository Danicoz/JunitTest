package com.cattsoft.utils;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	// 参数文件路径
	public static void deleteFileDay(String sFileUrl, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l * 3600 * 24;
			logger.info("开始检查目录" + sFileUrl + "下的文件是否过期  saveTime="
					+ saveDaylong + "ms");
			if (sFileUrl != null && !sFileUrl.trim().equals("")) {
				File dirFile = new File(sFileUrl);
				File files[] = dirFile.listFiles();
				for (File file : files) {
					if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
						logger.info(file.getPath() + "文件已经过期 删除该文件");
						file.delete();
					}
				}
			} else {
				logger.info("文件目录为sFileUrl:" + sFileUrl + "为空");
			}

		} catch (Exception ex) {
			logger.error("删除目录下" + sFileUrl + "的文件出错:");
		}
	}

	// 参数为文件进来
	public static void deleteFile(File file, int savedays) {
		Date nowdate = new Date();
		try {
			long saveDaylong = savedays * 1000l * 3600 * 24;
			logger.info("开始检查文件" + file + "下是否过期");
			if (nowdate.getTime() - file.lastModified() >= saveDaylong) {
				file.delete();
				logger.info("已删除文件");
			}
		} catch (Exception ex) {
			logger.error("删除文件出错", ex);
		}
	}

	// 删除文件夹
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有文件
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if(myFilePath.listFiles().length == 0){
				myFilePath.delete(); // 删除空文件夹
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
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
//	public static void main(String[] args) {
//		delFolder("./deleteFile");
//	}
}
