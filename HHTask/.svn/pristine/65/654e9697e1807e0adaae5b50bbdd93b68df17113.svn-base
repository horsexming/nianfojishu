package com.task.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.ServerImpl.FileService;
import com.task.entity.Users;
import com.task.entity.ZhFile;

public class FileAction extends ActionSupport {
	private File[] imgpath;// 实际的上传文件
	private String[] imgpathFileName; // 上传文件名
	private ZhFile file;
	private List<ZhFile> files;
	private FileService fileService;
	
	//提示信息
	private String errorMessage;
	private String successMessage;
	
	public String fileUpload(){
		files = fileService.getList(file.getFid(),file.getMid());
		return INPUT;
	}
	
	public String save() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String targetFileName = "";
		if (imgpath != null) {
			String targetDirectory = ServletActionContext.getServletContext().getRealPath("/");
			for (int i = 0; i < imgpath.length; i++) {
				String str = getfileName(imgpathFileName[i]);
				if (i < imgpathFileName.length - 1) {
					targetFileName += str + ",";
				} else {
					targetFileName += str;
				}
				File target = new File(targetDirectory, str);
				try {
					FileCopyUtils.copy(imgpath[i], target);
				} catch (Exception e) {
					targetFileName = "";
					break;
				}
				String excelRealPath = "D:/WorkSPace/Pts/WebRoot/guige/";
				String excelRealPath1 = ServletActionContext.getServletContext().getRealPath("/") + "upload/guige/";
				File target2 = new File(excelRealPath, str);
				File target3 = new File(excelRealPath1, str);
				try {
					FileCopyUtils.copy(imgpath[i], target2);
					FileCopyUtils.copy(imgpath[i], target3);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		}
		Users user = (Users) session.get(TotalDao.users);
		String name = user.getName();
		file.setUrl(targetFileName);// 上传的文件名称
		file.setCreattime(writeDate);// 创建时间
		file.setCreator(name);
		
		
		fileService.save(file);
		
		return "toFileUpload";
	}

	public String delete(){
		fileService.delete(file);
		return "toFileUpload";
	}
	
	// 上传的文件名+当前时间+随机数
	private String getfileName(String fileFileName) {
		int position = fileFileName.lastIndexOf(".");
		String extension = fileFileName.substring(position);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = sdf.format(new Date());
		extension =curDate+(int)(Math.random()*10000+1) + extension;
		return extension;
	}
	
	public File[] getImgpath() {
		return imgpath;
	}

	public void setImgpath(File[] imgpath) {
		this.imgpath = imgpath;
	}

	public String[] getImgpathFileName() {
		return imgpathFileName;
	}

	public void setImgpathFileName(String[] imgpathFileName) {
		this.imgpathFileName = imgpathFileName;
	}


	public ZhFile getFile() {
		return file;
	}

	public void setFile(ZhFile file) {
		this.file = file;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}


	public List<ZhFile> getFiles() {
		return files;
	}

	public void setFiles(List<ZhFile> files) {
		this.files = files;
	}
}
