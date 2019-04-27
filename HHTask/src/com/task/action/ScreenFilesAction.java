package com.task.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ScreenFilesService;
import com.task.entity.Screen;
import com.task.entity.ScreenFiles;
import com.task.entity.sop.ProcessInfor;
import com.task.util.MKUtil;

/**
 * 屏幕Action
 * 
 * @author 马凯
 * 
 */
public class ScreenFilesAction extends ActionSupport {

	private ScreenFilesService screenFilesService;
	private Screen screen;
	private Integer id;
	private List<ScreenFiles> files = new ArrayList<ScreenFiles>();;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	public String autoFile() {
		List<Map> list = screenFilesService.autoFile(screen.getId());
		MKUtil.writeJSON(list);
		return null;
	}

	public String showProcess() {
		return "showProcess";
	}
	
	public String add(){
		saveFiles(attachment, attachmentFileName, "screenfile", files);
		try {
			screenFilesService.add(files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String uploadPage(){
		return "uploadPage";
	}
	
	public String showFilePage(){
		files = screenFilesService.getFiles(id);
		return "showFilePage";
	}
	
	public String upload(){
		ProcessInfor p = new ProcessInfor();
		p.setId(id);
		saveFiles(attachment, attachmentFileName, "screenfile", files);
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setProcess(p);
		}
		screenFilesService.add(files);
		return "uploadOK";
	}
	
	public String delete(){
		try {
			screenFilesService.delete(id);
			MKUtil.writeJSON(true, "删除成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败!" + e.getMessage(), null);
		}
		return null;
	}
	
	public void saveFiles(File[] attachment, String[] attachmentFileName, String starStr, List<ScreenFiles> files2) {
		if (attachment != null && attachment.length > 0) {
			for (int i = 0; i < attachment.length; i++) {
				attachmentFileName[i] = attachmentFileName[i].toLowerCase();
				String attachmentName = "";
				String fileName = UUID.randomUUID().toString().replaceAll("-", "") + attachmentFileName[i].substring(attachmentFileName[i].lastIndexOf("."));
				attachmentName += fileName.toLowerCase();
				attachmentName.trim();

				// 上传到服务器
				String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/file/" + starStr) + File.separator + fileName;
				File file = new File(fileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], file);
				} catch (Exception e) {
					e.printStackTrace();
				}

				File saveDirFile = new File(ServletActionContext.getServletContext().getRealPath("/upload/file/" + starStr) + File.separator);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				File saveDirFile1 = new File("D:/WorkSpace/HHTask/WebRoot/upload/file/" + starStr + File.separator);
				if (!saveDirFile1.exists()) {
					saveDirFile1.mkdirs();
				}

				// 备份到项目
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/file/" + starStr + File.separator + fileName;
				File beiFenFile = new File(beiFenfileRealPath);
				try {
					FileCopyUtils.copy(attachment[i], beiFenFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				files2.get(i).setFilename(attachmentFileName[i].toLowerCase());
				files2.get(i).setFilepath(attachmentName.trim().toLowerCase());
			}
		} else {
			throw new RuntimeException("请上传文件!");
		}
//		return attachmentName;
	}

	public ScreenFilesService getScreenFilesService() {
		return screenFilesService;
	}
	
	public String getFilesByScreen(){
		files = screenFilesService.getFilesForAndroid(id);
		for (ScreenFiles it : files) {
			it.setProcess(null);
		}
		MKUtil.writeJSON(files);
		return null;
	}

	public void setScreenFilesService(ScreenFilesService screenFilesService) {
		this.screenFilesService = screenFilesService;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public List<ScreenFiles> getFiles() {
		return files;
	}

	public void setFiles(List<ScreenFiles> files) {
		this.files = files;
	}

}
