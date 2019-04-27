package com.task.action.quality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.quality.QualityAbnormalresumeService;
import com.task.entity.Taskmanager;
import com.task.entity.quality.QualityAbnormalresume;
import com.task.util.MKUtil;
import com.task.util.Util;

public class QualityAbnormalresumeAction {
	private QualityAbnormalresumeService qualityAbnormalresumeService;
	private QualityAbnormalresume qualityAbnormalresume;
	private List<QualityAbnormalresume> QARs;
	private List<File> fileUpload;
	private List<String> fileUploadContentType;
	private List<String> fileUploadFileName;
	private List<Object[]> dateList;
	private Integer count;
	private Integer count_tag;
	private String tag;
	private String AttachmentPath;
	private String level;
	private String[] Filenames;
	private Integer id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private boolean confirm = false;
	private int sum;

	public String addpage() {
		return "QualityAbnormalresume_addpage";
	}

	public String add() throws IOException {

		String Path;
		String filename = "";
		AttachmentPath = "";
		List<File> files = getFileUpload();
		if (files != null && files.size() > 0) {
			for (int i = 0; i < files.size(); i++) {
				Path = "/upload/file/qualityAbnormalresume/";
				String realFilePath = ServletActionContext.getServletContext()
						.getRealPath(Path);
				// 如果不存在文件夹就创建
				File file = new File(realFilePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				String fullname=fileUploadFileName.get(i);
				String[] filetype=fullname.split("\\.");
				filename = Util.getDateTime("yyyyMMddHHmmss_") + i + "."
						+ filetype[filetype.length-1];
				FileOutputStream fos = new FileOutputStream(realFilePath + "\\"+ filename);
				AttachmentPath += filename;
				AttachmentPath += "/";
				// 建立上传文件的输入流
				FileInputStream fis = new FileInputStream(files.get(i));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
			}
		}
		
		qualityAbnormalresume.setAttachmentPath(AttachmentPath);
		errorMessage=qualityAbnormalresumeService.addQualityAbnormalresume(qualityAbnormalresume);
		return "success";
	}

	public String delete() {
		qualityAbnormalresumeService.deleteQualityAbnormalresume(id);
		return "success";
	}

	public String updatepage() {
		qualityAbnormalresume=qualityAbnormalresumeService.findQualityAbnormalresume(id);
		return "QualityAbnormalresume_updatepage";
	}

	public String update() {
		qualityAbnormalresumeService.updateQualityAbnormalresume(qualityAbnormalresume);
		this.setUrl("QualityAbnormalresumeAction_list.action?cpage="+cpage);
		errorMessage="修改成功";
		return "error";
	}

	public String list() {
		if (qualityAbnormalresume != null) {
			ActionContext.getContext().getSession().put("qualityAbnormalresume",
					qualityAbnormalresume);
		} else {
			qualityAbnormalresume = (QualityAbnormalresume) ActionContext.getContext().getSession()
					.get("qualityAbnormalresume");
		}
		
		Object[] objects = qualityAbnormalresumeService
				.findallQualityAbnormalresume(qualityAbnormalresume, Integer.parseInt(cpage),
						pageSize);
		if (objects != null && objects.length > 0) {
			QARs = (List<QualityAbnormalresume>) objects[0];
			sum = (Integer) objects[1];
			if (QARs != null && QARs.size() > 0) {
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (level == null) {
					level = "";
				}
				this.setUrl("QualityAbnormalresumeAction_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}

		} else {
			errorMessage = "抱歉!没有您查询的信息!";
		}

		return "QualityAbnormalresume_list";
	}
	public String findqaforPic(){
		Map<String,Object> map = qualityAbnormalresumeService.findqaforPic(tag,count_tag);
		if(map!=null&&map.size()>0){
			dateList = (List<Object[]>)map.get("dateList");
			count = (Integer)map.get("count");
		}
		return "QualityAbnormalresume_pic";
	}
	public QualityAbnormalresumeService getQualityAbnormalresumeService() {
		return qualityAbnormalresumeService;
	}

	public void setQualityAbnormalresumeService(
			QualityAbnormalresumeService qualityAbnormalresumeService) {
		this.qualityAbnormalresumeService = qualityAbnormalresumeService;
	}

	public QualityAbnormalresume getQualityAbnormalresume() {
		return qualityAbnormalresume;
	}

	public void setQualityAbnormalresume(
			QualityAbnormalresume qualityAbnormalresume) {
		this.qualityAbnormalresume = qualityAbnormalresume;
	}

	public List<File> getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(List<File> fileUpload) {
		this.fileUpload = fileUpload;
	}

	public List<String> getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(List<String> fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public List<String> getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(List<String> fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getAttachmentPath() {
		return AttachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		AttachmentPath = attachmentPath;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String[] getFilenames() {
		return Filenames;
	}

	public void setFilenames(String[] filenames) {
		Filenames = filenames;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public List<QualityAbnormalresume> getQARs() {
		return QARs;
	}

	public void setQARs(List<QualityAbnormalresume> qARs) {
		QARs = qARs;
	}

	

	public List<Object[]> getDateList() {
		return dateList;
	}

	public void setDateList(List<Object[]> dateList) {
		this.dateList = dateList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getCount_tag() {
		return count_tag;
	}

	public void setCount_tag(Integer countTag) {
		count_tag = countTag;
	}
	
}
