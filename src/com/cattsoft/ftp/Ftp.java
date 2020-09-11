package com.cattsoft.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.cattsoft.utils.FtpUtil;

/** */
/**
 * ֧�ֶϵ�������FTPʵ����
 * 
 * @version 0.1 ʵ�ֻ����ϵ��ϴ�����
 * @version 0.2 ʵ���ϴ����ؽ��Ȼ㱨
 * @version 0.3 ʵ������Ŀ¼�����������ļ���������Ӷ������ĵ�֧��
 */
public class Ftp {

	// ö����UploadStatus����

	public enum UploadStatus {
		Create_Directory_Fail, // Զ�̷�������ӦĿ¼����ʧ��
		Create_Directory_Success, // Զ�̷���������Ŀ¼�ɹ�
		Upload_New_File_Success, // �ϴ����ļ��ɹ�
		Upload_New_File_Failed, // �ϴ����ļ�ʧ��
		File_Exits, // �ļ��Ѿ�����
		Remote_Bigger_Local, // Զ���ļ����ڱ����ļ�
		Upload_From_Break_Success, // �ϵ������ɹ�
		Upload_From_Break_Failed, // �ϵ�����ʧ��
		Delete_Remote_Faild; // ɾ��Զ���ļ�ʧ��
	}

	// ö����DownloadStatus����
	public enum DownloadStatus {
		Remote_File_Noexist, // Զ���ļ�������
		Local_Bigger_Remote, // �����ļ�����Զ���ļ�
		Download_From_Break_Success, // �ϵ������ļ��ɹ�
		Download_From_Break_Failed, // �ϵ������ļ�ʧ��
		Download_New_Success, // ȫ�������ļ��ɹ�
		Download_New_Failed; // ȫ�������ļ�ʧ��
	}

	public FTPClient ftpClient = new FTPClient();
	private String ftpURL, username, pwd, ftpport, file1, file2;

	public Ftp(String _ftpURL, String _username, String _pwd, String _ftpport,
			String _file1, String _file2) {
		// ���ý�������ʹ�õ����������������̨
		ftpURL = _ftpURL;
		username = _username;
		pwd = _pwd;
		ftpport = _ftpport;
		file1 = _file1;
		file2 = _file2;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(
				new PrintWriter(System.out)));
	}
	
	public Ftp() {
		// TODO Auto-generated constructor stub
	}

	/** */
	/**
	 * ���ӵ�FTP������
	 * 
	 * @param hostname
	 *            ������
	 * @param port
	 *            �˿�
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @return �Ƿ����ӳɹ�
	 * @throws IOException
	 */
	public boolean connect(String hostname, int port, String username,
			String password) throws IOException {
		ftpClient.connect(hostname, port);
		ftpClient.setControlEncoding("GBK");
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (ftpClient.login(username, password)) {
				return true;
			}
		}
		disconnect();
		return false;
	}

	/** */
	/**
	 * ��FTP�������������ļ�,֧�ֶϵ��������ϴ��ٷֱȻ㱨
	 * 
	 * @param remote
	 *            Զ���ļ�·��
	 * @param local
	 *            �����ļ�·��
	 * @return �ϴ���״̬
	 * @throws IOException
	 */
	public DownloadStatus download(String remote, String local)
			throws IOException {
		// ���ñ���ģʽ
		ftpClient.enterLocalPassiveMode();
		// �����Զ����Ʒ�ʽ����
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		DownloadStatus result;

		// ���Զ���ļ��Ƿ����
		FTPFile[] files = ftpClient.listFiles(new String(
				remote.getBytes("GBK"), "iso-8859-1"));
		if (files.length != 1) {
			System.out.println("Զ���ļ�������");
			return DownloadStatus.Remote_File_Noexist;
		}

		long lRemoteSize = files[0].getSize();
		File f = new File(local);
		// ���ش����ļ������жϵ�����
		if (f.exists()) {
			long localSize = f.length();
			// �жϱ����ļ���С�Ƿ����Զ���ļ���С
			if (localSize >= lRemoteSize) {
				System.out.println("�����ļ�����Զ���ļ���������ֹ");
				return DownloadStatus.Local_Bigger_Remote;
			}

			// ���жϵ�����������¼״̬
			FileOutputStream out = new FileOutputStream(f, true);
			ftpClient.setRestartOffset(localSize);
			InputStream in = ftpClient.retrieveFileStream(new String(remote
					.getBytes("GBK"), "iso-8859-1"));
			byte[] bytes = new byte[1024];
			long step = lRemoteSize / 100;
			long process = localSize / step;
			int c;
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
				localSize += c;
				long nowProcess = localSize / step;
				if (nowProcess > process) {
					process = nowProcess;
					if (process % 10 == 0)
						System.out.println("���ؽ��ȣ�" + process);
					// TODO �����ļ����ؽ���,ֵ�����process������
				}
			}
			in.close();
			out.close();
			boolean isDo = ftpClient.completePendingCommand();
			if (isDo) {
				result = DownloadStatus.Download_From_Break_Success;
			} else {
				result = DownloadStatus.Download_From_Break_Failed;
			}
		} else {
			OutputStream out = new FileOutputStream(f);
			InputStream in = ftpClient.retrieveFileStream(new String(remote
					.getBytes("GBK"), "iso-8859-1"));
			byte[] bytes = new byte[1024];
			long step = lRemoteSize / 100;
			long process = 0;
			long localSize = 0L;
			int c;
			while ((c = in.read(bytes)) != -1) {
				out.write(bytes, 0, c);
				localSize += c;
				long nowProcess = localSize / step;
				if (nowProcess > process) {
					process = nowProcess;
					if (process % 10 == 0)
						System.out.println("���ؽ��ȣ�" + process);
					// TODO �����ļ����ؽ���,ֵ�����process������
				}
			}
			in.close();
			out.close();
			boolean upNewStatus = ftpClient.completePendingCommand();
			if (upNewStatus) {
				result = DownloadStatus.Download_New_Success;
			} else {
				result = DownloadStatus.Download_New_Failed;
			}
		}
		return result;
	}

	/** */
	/**
	 * �ϴ��ļ���FTP��������֧�ֶϵ�����
	 * 
	 * @param local
	 *            �����ļ����ƣ�����·��
	 * @param remote
	 *            Զ���ļ�·����ʹ��/home/directory1/subdirectory/file.ext����
	 *            http://www.guihua.org /subdirectory/file.ext
	 *            ����Linux�ϵ�·��ָ����ʽ��֧�ֶ༶Ŀ¼Ƕ�ף�֧�ֵݹ鴴�������ڵ�Ŀ¼�ṹ
	 * @return �ϴ����
	 * @throws IOException
	 */
	public UploadStatus upload(String local, String remote) throws IOException {
		// ����PassiveMode����
		ftpClient.enterLocalPassiveMode();
		// �����Զ��������ķ�ʽ����
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		ftpClient.setControlEncoding("GBK");
		UploadStatus result;
		// ��Զ��Ŀ¼�Ĵ���
		String remoteFileName = remote;
		if (remote.contains("/")) {
			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);
			// ����������Զ��Ŀ¼�ṹ������ʧ��ֱ�ӷ���
			if (CreateDirecroty(remote, ftpClient) == UploadStatus.Create_Directory_Fail) {
				return UploadStatus.Create_Directory_Fail;
			}
		}

		// ���Զ���Ƿ�����ļ�
		FTPFile[] files = ftpClient.listFiles(new String(remoteFileName.getBytes("GBK"), "iso-8859-1"));
		if (files.length == 1) {
			long remoteSize = files[0].getSize();
			File f = new File(local);
			long localSize = f.length();
			if (remoteSize == localSize) {
				return UploadStatus.File_Exits;
			} else if (remoteSize > localSize) {
				return UploadStatus.Remote_Bigger_Local;
			}

			// �����ƶ��ļ��ڶ�ȡָ��,ʵ�ֶϵ�����
			result = uploadFile(remoteFileName, f, ftpClient, remoteSize);

			// ����ϵ�����û�гɹ�����ɾ�����������ļ��������ϴ�
			if (result == UploadStatus.Upload_From_Break_Failed) {
				if (!ftpClient.deleteFile(remoteFileName)) {
					return UploadStatus.Delete_Remote_Faild;
				}
				result = uploadFile(remoteFileName, f, ftpClient, 0);
			}
		} else {
			result = uploadFile(remoteFileName, new File(local), ftpClient, 0);
		}
		return result;
	}

	/** */
	/**
	 * �Ͽ���Զ�̷�����������
	 * 
	 * @throws IOException
	 */
	public void disconnect() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}
	}

	/** */
	/**
	 * �ݹ鴴��Զ�̷�����Ŀ¼
	 * 
	 * @param remote
	 *            Զ�̷������ļ�����·��
	 * @param ftpClient
	 *            FTPClient ����
	 * @return Ŀ¼�����Ƿ�ɹ�
	 * @throws IOException
	 */
	public UploadStatus CreateDirecroty(String remote, FTPClient ftpClient)
			throws IOException {
		UploadStatus status = UploadStatus.Create_Directory_Success;
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(new String(directory
						.getBytes("GBK"), "iso-8859-1"))) {
			// ���Զ��Ŀ¼�����ڣ���ݹ鴴��Զ�̷�����Ŀ¼
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end)
						.getBytes("GBK"), "iso-8859-1");
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("����Ŀ¼ʧ��");
						return UploadStatus.Create_Directory_Fail;
					}
				}

				start = end + 1;
				end = directory.indexOf("/", start);

				// �������Ŀ¼�Ƿ񴴽����
				if (end <= start) {
					break;
				}
			}
		}
		return status;
	}

	/** */
	/**
	 * �ϴ��ļ���������,���ϴ��Ͷϵ�����
	 * 
	 * @param remoteFile
	 *            Զ���ļ��������ϴ�֮ǰ�Ѿ�������������Ŀ¼���˸ı�
	 * @param localFile
	 *            �����ļ� File���������·��
	 * @param processStep
	 *            ��Ҫ��ʾ�Ĵ�����Ȳ���ֵ
	 * @param ftpClient
	 *            FTPClient ����
	 * @return
	 * @throws IOException
	 */
	public UploadStatus uploadFile(String remoteFile, File localFile,
			FTPClient ftpClient, long remoteSize) throws IOException {
		UploadStatus status;
		// ��ʾ���ȵ��ϴ�
		long step = localFile.length() / 100;
		
		
		
		long process = 0;
		long localreadbytes = 0L;
		RandomAccessFile raf = new RandomAccessFile(localFile, "r");
		OutputStream out = ftpClient.appendFileStream(new String(remoteFile.getBytes("GBK"), "iso-8859-1"));
		// �ϵ�����
		if (remoteSize > 0) {
			ftpClient.setRestartOffset(remoteSize);
			process = step == 0 ? 0 : remoteSize / step;
			raf.seek(remoteSize);
			localreadbytes = remoteSize;
		}
		byte[] bytes = new byte[1024];
		int c;
		while ((c = raf.read(bytes)) != -1) {
			out.write(bytes, 0, c);
			localreadbytes += c;
			long temp = step == 0 ? 0 : localreadbytes / step;
			if ( temp != process) {
				process = step == 0 ? 0 : localreadbytes / step;
				System.out.println("�ϴ�����:" + process);
				// TODO �㱨�ϴ�״̬
			}
		}
		out.flush();
		raf.close();
		out.close();
		boolean result = ftpClient.completePendingCommand();
		if (remoteSize > 0) {
			status = result ? UploadStatus.Upload_From_Break_Success
					: UploadStatus.Upload_From_Break_Failed;
		} else {
			status = result ? UploadStatus.Upload_New_File_Success
					: UploadStatus.Upload_New_File_Failed;
		}
		return status;
	}

	public static void main(String[] args) {
		Ftp ftp = new Ftp();
		try {
			boolean flag = ftp.connect("10.131", 21, "Danicoz", "123456");
			UploadStatus str = ftp.upload("E:\\eclipseWord\\JunitTest\\file\\aa.txt", "./ftp/aa.txt");
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}

}
