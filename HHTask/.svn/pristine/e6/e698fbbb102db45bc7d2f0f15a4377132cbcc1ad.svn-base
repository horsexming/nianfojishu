package com.task.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

/***
 * 上传文件
 * 
 * @author 刘培
 * 
 */
public class Upload {

	/***
	 * 
	 * 
	 * @param file
	 *            文件 (必须填写)
	 * @param fileFileName
	 *            上传文件时的名称 (为空时 必须填写新的文件名)
	 * @param newFileName
	 *            新的文件名 (为空时 默认为yyyyMMddHHmmss+上传时的文件格式)
	 * @param uploadPath
	 *            保存地址 (必须填写)
	 * @param backupPath
	 *            备份地址 (为空时 则不对文件进行备份操作)
	 */
	public String UploadFile(File file, String fileFileName,
			String newFileName, String uploadPath, String backupPath) {
		// 上传文件
		if (file != null && uploadPath != null && uploadPath.length() > 0) {
			if (newFileName == null || newFileName.length() <= 0) {
				if (fileFileName != null && fileFileName.length() > 0) {
					newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
							.format(new Date())
							+ fileFileName.substring(fileFileName
									.lastIndexOf("."), fileFileName.length());
				} else {
					return "error";
				}
			}

			// 上传文件到服务器
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath(uploadPath)
					+ "/" + newFileName;
			File uploadFile = new File(fileRealPath);
			try {
				FileCopyUtils.copy(file, uploadFile);
			} catch (Exception e) {
				e.printStackTrace();
				return "上传文件失败!";
			}

			if (backupPath != null && backupPath.length() > 0) {
				// 备份文件
				String beiFenfileRealPath = backupPath + "/" + newFileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(file, beiFenFile);
				} catch (IOException e) {
					// return "备份文件失败!";
				}
			}
			return newFileName;
		}
		return "error";
	}
}
