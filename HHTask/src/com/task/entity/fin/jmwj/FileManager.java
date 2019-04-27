package com.task.entity.fin.jmwj;

import java.io.Serializable;

/**
 * 机密文件管理（ta_fin_jmfileManager）
 * @author jhh
 *
 */
public class FileManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String fileName;//文件名称
	private String fileType;//文件类型
	private String fileLocation;//文件存放位置
	private String filearchivesNo;//文件档案编号
	private String fileXingzhi;//文件性质 原件/复印件
	private Integer archivesId;//档案ID
	private String fileRemarks;//备注
	private String fileStoreTime;//存放时间
	private String fileStoreUser;//存放维护人
	private Integer epId;//流程id
	
	private String fileNumber;//文件编号
	private Integer fileCount;//数量
	private Integer filePages;//文件页数
	private String fileSignDate;//文件签订日期
	
	private String danganWeizhi;//档案柜编号
	private String danganId;//档案柜ID
	
	private String isCundang;//是否存档
	private Integer cundangCount;//存档数量
	
	public String getDanganWeizhi() {
		return danganWeizhi;
	}
	public void setDanganWeizhi(String danganWeizhi) {
		this.danganWeizhi = danganWeizhi;
	}
	public String getDanganId() {
		return danganId;
	}
	public void setDanganId(String danganId) {
		this.danganId = danganId;
	}
	public Integer getFilePages() {
		return filePages;
	}
	public void setFilePages(Integer filePages) {
		this.filePages = filePages;
	}
	public String getIsCundang() {
		return isCundang;
	}
	public void setIsCundang(String isCundang) {
		this.isCundang = isCundang;
	}
	public Integer getCundangCount() {
		return cundangCount;
	}
	public void setCundangCount(Integer cundangCount) {
		this.cundangCount = cundangCount;
	}
	public Integer getId() {
		return id;
	}
	public String getFileNumber() {
		return fileNumber;
	}
	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}
	public Integer getFileCount() {
		return fileCount;
	}
	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}
	public String getFileSignDate() {
		return fileSignDate;
	}
	public void setFileSignDate(String fileSignDate) {
		this.fileSignDate = fileSignDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public String getFilearchivesNo() {
		return filearchivesNo;
	}
	public void setFilearchivesNo(String filearchivesNo) {
		this.filearchivesNo = filearchivesNo;
	}
	public Integer getArchivesId() {
		return archivesId;
	}
	public void setArchivesId(Integer archivesId) {
		this.archivesId = archivesId;
	}
	public String getFileRemarks() {
		return fileRemarks;
	}
	public void setFileRemarks(String fileRemarks) {
		this.fileRemarks = fileRemarks;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getFileStoreTime() {
		return fileStoreTime;
	}
	public void setFileStoreTime(String fileStoreTime) {
		this.fileStoreTime = fileStoreTime;
	}
	public String getFileStoreUser() {
		return fileStoreUser;
	}
	public void setFileStoreUser(String fileStoreUser) {
		this.fileStoreUser = fileStoreUser;
	}
	public String getFileXingzhi() {
		return fileXingzhi;
	}
	public void setFileXingzhi(String fileXingzhi) {
		this.fileXingzhi = fileXingzhi;
	}
	
}
