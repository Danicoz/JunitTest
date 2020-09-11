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
 * ������FTP ����������
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
	 *            FTP·��
	 * @param dateFormat
	 *            ���ڸ�ʽ
	 * @param mode
	 *            FTPģʽ
	 * @param suffix
	 *            �ļ���׺
	 * @return
	 */
	public Map<String, List<String>> ftpScanner(List<String> remotePaths,
			String dateFormat, String mode, String suffix) {

		Map<String, List<String>> path_files = new HashMap<String, List<String>>();

		try {
			for (String remotePath : remotePaths) {
				List<String> file_list = new ArrayList<String>();
				logger.info("ɨ��FTPĿ¼��·��Ϊ��" + remotePath);
				ftpClient.changeWorkingDirectory("/" + remotePath);// ת��FTP������Ŀ¼

				if (Integer.valueOf(mode) == 1) {
					logger.info("FTP�л�������ģʽ��");
					ftpClient.enterLocalActiveMode();// ����ģʽ
				} else {
					logger.info("FTP�л��ɱ���ģʽ��");
					ftpClient.enterLocalPassiveMode();// ����ģʽ
				}

				FTPFile[] fs = ftpClient.listFiles();

				if (SysConstant.isFirstCollect) {// ��һ��ɨ�裬��¼�ļ���С
					logger.info("��һ��ɨ��Ŀ¼��" + remotePath + ",��¼��Ŀ¼�µ��ļ���С��");
					for (FTPFile file : fs) {
						String ftpFileName = file.getName();
						SysConstant.sizeMapBef.put(ftpFileName, file.getSize());
					}
					SysConstant.isFirstCollect = false;
				} else {// �ڶ���ɨ���ж��Ƿ���ȫ����
					for (FTPFile file2 : fs) {
						String fileName = file2.getName();
						if (SysConstant.sizeMapBef.containsKey(fileName)) {
							if (SysConstant.sizeMapBef.get(fileName) == file2
									.getSize()) {// �������
								if (isTodayFtpFile(fileName, dateFormat, suffix)) {
									file_list.add(fileName);// ����·��
								}
							} else {// ��û��������� ������ε�ֵ
								SysConstant.sizeMapBef.put(fileName,
										file2.getSize());
							}
						} else {
							SysConstant.sizeMapBef.put(fileName,
									file2.getSize());// ����µ�
						}
					}
				}
				path_files.put(remotePath, file_list);
			}

		} catch (Exception e) {
			logger.error("FTPɨ���ļ�ʧ�ܣ�" + e);
		}
		return path_files;
	}

	/**
	 * FTP �����ļ�����
	 * 
	 * @param fileNames
	 *            �ļ�����
	 * @param path
	 *            FTPĿ¼
	 * @param local_file_path
	 *            ����Ŀ¼
	 * @param mode
	 *            FTPģʽ
	 * @return
	 */
	public List<File> ftpDown(List<String> fileNames, String path,
			String localFilePath, String mode) {
		List<File> downLoadFiles = new ArrayList<File>();
		try {
			ftpClient.changeWorkingDirectory("/" + path);// ת�Ƶ�ָ����FTP������Ŀ¼
			for (String fileName : fileNames) {
				try {
					File localFile = new File(localFilePath + File.separator
							+ fileName);
					OutputStream is = new FileOutputStream(localFile);
					if (Integer.valueOf(mode) == 1) {
						logger.info("ftp�л�Ϊ����ģʽ��");
						ftpClient.enterLocalActiveMode();// ����ģʽ
					} else {
						logger.info("ftp�л�Ϊ����ģʽ��");
						ftpClient.enterLocalPassiveMode();// ����ģʽ
					}
					ftpClient.retrieveFile(fileName, is);
					downLoadFiles.add(localFile);
					logger.info(fileName + "�ļ����سɹ�,·����"
							+ localFile.getAbsolutePath());
					is.close();
				} catch (Exception e) {
					logger.error(fileName + "�ļ�����ʧ��", e);
				}
			}
		} catch (Exception ex) {
			logger.error("ftp����ʧ��", ex);
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
			logger.error("�ϴ��ļ��쳣", e);
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
	 * FTP��¼����
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

			ftpClient.login(user, password);// ��¼
			reply = ftpClient.getReplyCode();
			ftpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
			} else {
				success = true;
			}
		} catch (Exception ex) {
			logger.error("ftp����ʧ�ܣ���", ex);
		}
		return success;
	}

	/**
	 * �ر�FTP������
	 */
	private void ftpLoginOut() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			logger.error("ftp����ر�ʧ�ܣ�", e);
		}

	}

	/**
	 * FTP ��������
	 * 
	 * @param ftpInfo
	 *            FTP���Ӷ���
	 * @param ftpClient
	 *            FTP�ͻ���
	 * @param taskName
	 *            FTP����
	 */
	public void reConnect(FTP_INFO ftpInfo, FtpUtil ftpClient, String taskName) {
		String ip = ftpInfo.getIp();
		String port = ftpInfo.getPort();
		String user = ftpInfo.getUser();
		String password = ftpInfo.getPassword();
		String url = ftpInfo.getUrl();

		if (ftpClient != null && ftpClient.isConnect()) {
			logger.info("�Ͽ�FTP����...");
			ftpClient.ftpLoginOut();
		}
		ftpClient.ftpLogin(url, ip, port, user, password);
	}

	/**
	 * �ж�FTP�Ƿ�����
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
	 * �ж����㵱���������ļ���׺��ȷ���ļ�
	 * 
	 * @param fileName
	 *            �ļ�����
	 * @param dateFormat
	 *            ���ڸ�ʽ
	 * @param suffix
	 *            �ļ���׺
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
			logger.error("�ж�ftp�ļ��Ƿ�Ϊ�����ļ�����!", e);
			flag = false;
		}
		return flag;
	}
	
	public static void main(String[] args) {
		FtpUtil ftp = new FtpUtil(new FTPClient());
		Boolean b = ftp.ftpLogin(null, ".168..", "21", "Danicoz", "123456");
		System.out.println("b=" + b);
		
		List<String>files = new ArrayList<String>();
		files.add("aa.txt");
		ftp.uploadFile(files, "./ftp", "./file", "1");
	}
	
}
