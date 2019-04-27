package com.task.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class DownAction extends ActionSupport {

	private String fileName;// 文件存储名称
	private String fileName2;// 下载到本地的文件名称
	private String directory;// 文件路径
	private String errorMessage;

	@Override
	public String execute() {
		if (fileName != null && fileName.length() > 0) {
			try {
				// 解码(进行解码)
				fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath(directory)
					+ "/" + fileName;
			if (fileName2 != null && fileName2.length() > 0) {
				try {
					fileName2 = java.net.URLEncoder.encode(fileName2, "UTF-8");
					// fileName2 = new String(fileName2.getBytes("GB2312"),
					// "ISO_8859_1");
					// fileName2=new String(fileName2.trim().getBytes("UTF-8"),
					// "ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			if (fileName2 == null || fileName2.length() == 0) {
				fileName2 = fileName;
			}
			// fileName2="DKBA8.015.0385.dwg";
			File file = new File(fileRealPath);
			if (file.exists()) {
				return SUCCESS;
			} else {
				errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
				return INPUT;
			}
		}
		errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
		return INPUT;
	}

	public InputStream getInputStream() throws Exception {
		String dir = directory + fileName;
		// return new FileInputStream(dir);
		// 如果dir是绝对路径
		// return
		return ServletActionContext.getServletContext()
				.getResourceAsStream(dir);
		// 如果dir是Resource下的相对路径
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

}
