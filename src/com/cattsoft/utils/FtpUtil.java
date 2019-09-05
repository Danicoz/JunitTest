package com.cattsoft.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cattsoft.ftp.FTP_INFO;
import com.cattsoft.ftp.SysConstant;

/**
 * 服务器FTP 操作工具类
 * 
 * @author Danicoz
 *
 */
public class FtpUtil {

	private static Logger logger = LoggerFactory.getLogger("collect");
	private FTPClient ftpClient;

	public FtpUtil(FTPClient ftpClient) {
		this.ftpClient = ftpClient;
	}
	
	public FtpUtil() {
	}

	/**
	 * 
	 * @param remotePaths
	 *            FTP路径
	 * @param dateFormat
	 *            日期格式
	 * @param mode
	 *            FTP模式
	 * @param suffix
	 *            文件后缀
	 * @return
	 */
	public Map<String, List<String>> ftpScanner(List<String> remotePaths,
			String dateFormat, String mode, String suffix) {

		Map<String, List<String>> path_files = new HashMap<String, List<String>>();

		try {
			for (String remotePath : remotePaths) {
				List<String> file_list = new ArrayList<String>();
				logger.info("扫描FTP目录的路径为：" + remotePath);
				ftpClient.changeWorkingDirectory("/" + remotePath);// 转到FTP服务器目录

				if (Integer.valueOf(mode) == 1) {
					logger.info("FTP切换成主动模式！");
					ftpClient.enterLocalActiveMode();// 主动模式
				} else {
					logger.info("FTP切换成被动模式！");
					ftpClient.enterLocalPassiveMode();// 被动模式
				}

				FTPFile[] fs = ftpClient.listFiles();

				if (SysConstant.isFirstCollect) {// 第一次扫描，记录文件大小
					logger.info("第一次扫描目录：" + remotePath + ",记录该目录下的文件大小！");
					for (FTPFile file : fs) {
						String ftpFileName = file.getName();
						SysConstant.sizeMapBef.put(ftpFileName, file.getSize());
					}
					SysConstant.isFirstCollect = false;
				} else {// 第二次扫描判断是否完全下载
					for (FTPFile file2 : fs) {
						String fileName = file2.getName();
						if (SysConstant.sizeMapBef.containsKey(fileName)) {
							if (SysConstant.sizeMapBef.get(fileName) == file2
									.getSize()) {// 下载完毕
								if (isTodayFtpFile(fileName, dateFormat, suffix)) {
									file_list.add(fileName);// 绝对路径
								}
							} else {// 还没有下载完毕 更新这次的值
								SysConstant.sizeMapBef.put(fileName,
										file2.getSize());
							}
						} else {
							SysConstant.sizeMapBef.put(fileName,
									file2.getSize());// 添加新的
						}
					}
				}
				path_files.put(remotePath, file_list);
			}

		} catch (Exception e) {
			logger.error("FTP扫描文件失败！" + e);
		}
		return path_files;
	}

	/**
	 * FTP 下载文件操作
	 * 
	 * @param fileNames
	 *            文件集合
	 * @param path
	 *            FTP目录
	 * @param local_file_path
	 *            本地目录
	 * @param mode
	 *            FTP模式
	 * @return
	 */
	public List<File> ftpDown(List<String> fileNames, String path,
			String localFilePath, String mode) {
		List<File> downLoadFiles = new ArrayList<File>();
		try {
			ftpClient.changeWorkingDirectory("/" + path);// 转移到指定的FTP服务器目录
			for (String fileName : fileNames) {
				try {
					File localFile = new File(localFilePath + File.separator
							+ fileName);
					OutputStream is = new FileOutputStream(localFile);
					if (Integer.valueOf(mode) == 1) {
						logger.info("ftp切换为主动模式！");
						ftpClient.enterLocalActiveMode();// 主动模式
					} else {
						logger.info("ftp切换为被动模式！");
						ftpClient.enterLocalPassiveMode();// 被动模式
					}
					ftpClient.retrieveFile(fileName, is);
					downLoadFiles.add(localFile);
					logger.info(fileName + "文件下载成功,路径："
							+ localFile.getAbsolutePath());
					is.close();
				} catch (Exception e) {
					logger.error(fileName + "文件下载失败", e);
				}
			}
		} catch (Exception ex) {
			logger.error("ftp下载失败", ex);
		}
		return downLoadFiles;
	}

	public boolean uploadFile(List<String> fileNames, String path, String localFilePath, String mode) {

		BufferedInputStream bis = null;
		try {
			for (String fileName : fileNames) {
				
				bis = new BufferedInputStream(new FileInputStream(localFilePath + File.separator + fileName));
				ftpClient.storeFile(path + File.separator + fileName, bis);
				
			}

		} catch (IOException e) {
			logger.error("上传文件异常", e);
			return false;
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				ftpLoginOut();
				if (ftpClient.isConnected()) {
					ftpClient.disconnect();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * FTP登录操作
	 * 
	 * @param url
	 * @param ip
	 * @param port
	 * @param user
	 * @param password
	 */
	public boolean ftpLogin(String url, String ip, String port, String user,
			String password) {
		boolean success = false;
		try {
			int reply = 0;

			if (url == null || "".equals(url)) {
				ftpClient.connect(ip, Integer.valueOf(port));
			} else {
				ftpClient.connect(url);
			}

			ftpClient.login(user, password);// 登录
			reply = ftpClient.getReplyCode();
			ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			} else {
				success = true;
			}
		} catch (Exception ex) {
			logger.error("ftp连接失败！！", ex);
		}
		return success;
	}

	/**
	 * 关闭FTP服务器
	 */
	private void ftpLoginOut() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			logger.error("ftp服务关闭失败！", e);
		}

	}

	/**
	 * FTP 重连操作
	 * 
	 * @param ftpInfo
	 *            FTP连接对象
	 * @param ftpClient
	 *            FTP客户端
	 * @param taskName
	 *            FTP名称
	 */
	public void reConnect(FTP_INFO ftpInfo, FtpUtil ftpClient, String taskName) {
		String ip = ftpInfo.getIp();
		String port = ftpInfo.getPort();
		String user = ftpInfo.getUser();
		String password = ftpInfo.getPassword();
		String url = ftpInfo.getUrl();

		if (ftpClient != null && ftpClient.isConnect()) {
			logger.info("断开FTP连接...");
			ftpClient.ftpLoginOut();
		}
		ftpClient.ftpLogin(url, ip, port, user, password);
	}

	/**
	 * 判断FTP是否连接
	 * 
	 * @return
	 */
	private boolean isConnect() {
		int reply = this.ftpClient.getReplyCode();
		if (this.ftpClient != null && FTPReply.isPositiveCompletion(reply)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断满足当天日期且文件后缀正确的文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param dateFormat
	 *            日期格式
	 * @param suffix
	 *            文件后缀
	 * @return
	 */
	private boolean isTodayFtpFile(String fileName, String dateFormat,
			String suffix) {
		boolean flag = false;
		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			String day = sdf.format(date);// yyyyMMdd
			if (fileName.contains(day) && fileName.endsWith(suffix)) {
				flag = true;
			}
		} catch (Exception e) {
			logger.error("判断ftp文件是否为当日文件出错!", e);
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		FtpUtil ftp = new FtpUtil(new FTPClient());
		Boolean b = ftp.ftpLogin(null, "172.168.10.131", "21", "Danicoz", "123456");
		System.out.println("b=" + b);
		
		List<String>files = new ArrayList<String>();
		files.add("aa.txt");
		ftp.uploadFile(files, "./ftp", "./file", "1");
	}
	
}
