package com.naran.foundation.util.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpUpload {
	
	private static final String hostIp = "115.28.57.127";
	
	private static final int port = 21;

	private static final String username = "dxx";
	
	private static final String password = ".a0123456";
	
	private static SimpleDateFormat sdf = null;
	
	private static final String linux_tmping="uploadImages/image";
	
	static {
		sdf = new SimpleDateFormat("yyyyMMdd");
	}
	
	public static void main(String[] args) {
		FileInputStream in;
		try {
			in = new FileInputStream(new File("D:/test.png"));
			boolean flag = uploadFile("out1.png", in); 
			System.out.println(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public static boolean downFile(String url, String username, String password, String remotePath,String fileName,String localPath) {
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	        ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录  
	        FTPFile[] fs = ftp.listFiles();  
	        for(FTPFile ff:fs) {
	            if(ff.getName().equals(fileName)){
	                File localFile = new File(localPath+"/"+ff.getName());
	                OutputStream is = new FileOutputStream(localFile);
	                ftp.retrieveFile(ff.getName(), is);
	                is.close();
	            }
	        }
	          
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}
	
	
	/**
	* Description: 向FTP服务器上传文件
	* @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目录
	* @param filename 上传到FTP服务器上的文件名
	* @param input 输入流
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean uploadFile(String filename, InputStream input) {
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try {
	        int reply; 
	        ftp.connect(hostIp, port);//连接FTP服务器 
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        ftp.login(username, password);//登录 
	        reply = ftp.getReplyCode(); 
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            ftp.disconnect(); 
	            return success; 
	        }
	        ftp.setControlEncoding("UTF-8");
	        ftp.enterLocalPassiveMode();
	        createFolder(ftp,getFolderName());
	        ftp.changeWorkingDirectory(getFolderName()); 
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        success = ftp.storeFile(new String(filename.getBytes("UTF-8"),"iso-8859-1"), input);          
	         
	        input.close();
	        closeCon(ftp);
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    } finally {
	        if (ftp.isConnected()) {
	            try { 
	                ftp.disconnect();
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return success; 
	}
	
	 /** 
     *  
     * <p>删除ftp上的文件</p> 
     * @param srcFname 
     * @return true || false 
     */  
    public boolean removeFile(String srcFname){  
        boolean flag = false;
        FTPClient ftpClient = new FTPClient(); 
        if( ftpClient != null ){  
            try {  
                flag = ftpClient.deleteFile(srcFname);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return flag;  
    }
    
    
    /** 
     *<p>销毁ftp连接</p>  
     */  
    public static void closeCon(FTPClient ftp) {
        if(ftp !=null){  
            if(ftp.isConnected()){  
                try {  
                	ftp.logout();  
                	ftp.disconnect();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            } 
        } 
    }
    
    public static boolean copyDirectory(String url, String username, String password, String from, String to,String fileName,String fromUrl) {
        boolean success = false;
        FTPClient ftpClient = new FTPClient();
        FTPFile[] filelist;
        try {
 	        int reply;
 	        ftpClient.connect(url, port);
 	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
 	        ftpClient.login(username, password);//登录
 	        reply = ftpClient.getReplyCode();
 	        if (!FTPReply.isPositiveCompletion(reply)) {
 	        	ftpClient.disconnect();
 	            return success;
 	        }
            // 枚举当前from目录所有文件+子目录
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            filelist = ftpClient.listFiles(from);
            for (FTPFile f : filelist) {
				if(StringUtils.equals(f.getName(), fileName)) {
					if (f.isFile()) {
						ftpClient.setBufferSize(1024); 
			            ftpClient.setControlEncoding("UTF-8");
			            ftpClient.enterLocalPassiveMode();
			            createFolder(ftpClient,to);
			            ByteArrayOutputStream fos = new ByteArrayOutputStream();
			            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			            ftpClient.retrieveFile(from + fileName, fos);
			            ftpClient.changeWorkingDirectory(to);
			            ByteArrayInputStream in = new ByteArrayInputStream(fos.toByteArray());
			            success = ftpClient.storeFile(new String(fileName.getBytes("UTF-8"),"iso-8859-1"), in);
					}
				}
			}
            closeCon(ftpClient);
        } catch (IOException e) {
        	success = false;
        }
        System.out.println(success);
        return success;
    }
    
    private static void createFolder(FTPClient client,String path) {
    	StringBuilder sb = new StringBuilder();
    	for (String key : path.split("/")) {
    		sb.append(key + "/");
    		try {
				client.makeDirectory(sb.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    private static String getFolderName() {
		String ymd = sdf.format(new Date());
		return linux_tmping + "/" + ymd;
	}
    
}
