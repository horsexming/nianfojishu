package com.task.action.fin;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.fin.FileManagerServer;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.fin.jmwj.FileLocation;
import com.task.entity.fin.jmwj.FileManager;
import com.task.entity.fin.jmwj.FileType;

public class FileManagerAction extends ActionSupport {
	private FileManagerServer fileManagerServer;
	private FileManager fileManger;//保存
	private FileManager fidefileManager;//查询
	
	private FileType fileType;
	private FileLocation fileLocation;
	private String tag;
	private String message;
	private List<FileManager> listDetail;
	private List list;
	private List listType;//存放位置
//	private ArchiveUnarchiverAplt archiveUnarchiverAplt;//
	private String errorMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	
	//管理文件类型及存放位置
	public String findAllTypeLocation(){
		listType=fileManagerServer.findListType();
		this.pageSize = 15;
		this.setUrl("FileManagerAction!findAllTypeLocation.action" );
		HttpServletRequest request = ServletActionContext.getRequest();
		
		
		Object[] obj = fileManagerServer.findFileLocation( Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findFileTypeOK";
	}
	//创建类型及存放位置
	public String createFileType(){
		return "createOK";
	}
	//添加机密文件
	public String saveFile(){
		if(fileManagerServer.saveFileManager(fileManger)){
			return "saveFileOK";
		}
		return "error";
	}
	//更改
	public String updateFileM(){		
		if(fileManagerServer.updateFile(fileManger)){
			return "saveFileOK";
		}
		return "error";
	}
	//查看单条机密文件
	public String getOneFileManager(){
		if(null!=id){
			fileManger=fileManagerServer.getFileManagerById(id);
			return "showFileManager";//fileManager_updatet.jsp
		}
		return ERROR;
	}
	//条件查询 查找机密文件
	public String findFileManager(){
		this.pageSize = 15;
		this.setUrl("FileManagerAction!findFileManager.action" );
		HttpServletRequest request = ServletActionContext.getRequest();
		// BaoxiaoDan baoxiao=new BaoxiaoDan();
		if (null != fidefileManager) {
			
			request.getSession().setAttribute("fidefileManager", fidefileManager);
		} else {
			fidefileManager = (FileManager) request.getSession().getAttribute("fidefileManager");
		}
		
		Object[] obj = fileManagerServer.findFile(fidefileManager, startDate,
				endDate, Integer.parseInt(cpage), pageSize, this.tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findFileOK";//fileManager_list.jsp
	}
	//删除                
	public String deleteFileManager(){
		if(null!=id){
			fileManagerServer.deleteFileManager(id);
		}
		return "saveFileOK";
	}
	//申请取出文件 生成验证码
	public String addYanZheng() {
		errorMessage = fileManagerServer.addYanZheng(fileManger);
		if ("验证码已生成".equals(errorMessage))
			url = "FileManagerAction!findFileManager.action";
		return "error";
	}
	//添加类型
	public String addFileType(){
		fileManagerServer.addFileType(fileType);
		return "addOK";
	}
	//添加存放位置
	public String addFileLocation(){
		fileManagerServer.addFileLocation(fileLocation);
		return "addOK";
	}
	//查找下拉列表
	public String findStyle(){
		String message = fileManagerServer.findStyle(tag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}
	//删除文件类型
	public String deleteFileType(){
		fileManagerServer.deleteFileTypeByID(id);
		return "deleteFileTypeOK";
	}
	//删除文件存放位置
	public String deleteFileLocation(){
		fileManagerServer.deleteFileLocationById(id);
		return "deleteFileTypeOK";
	}
	public FileManager getFileManger() {
		return fileManger;
	}
	public void setFileManger(FileManager fileManger) {
		this.fileManger = fileManger;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<FileManager> getListDetail() {
		return listDetail;
	}
	public void setListDetail(List<FileManager> listDetail) {
		this.listDetail = listDetail;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public FileManagerServer getFileManagerServer() {
		return fileManagerServer;
	}


	public void setFileManagerServer(FileManagerServer fileManagerServer) {
		this.fileManagerServer = fileManagerServer;
	}
	public FileManager getFidefileManager() {
		return fidefileManager;
	}
	public void setFidefileManager(FileManager fidefileManager) {
		this.fidefileManager = fidefileManager;
	}
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	public FileLocation getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(FileLocation fileLocation) {
		this.fileLocation = fileLocation;
	}
	public List getListType() {
		return listType;
	}
	public void setListType(List listType) {
		this.listType = listType;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
