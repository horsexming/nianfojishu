package com.task.action.quality;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.quality.QualityServer;
import com.task.ServerImpl.quality.QualityServerImpl;
import com.task.entity.Users;
import com.task.entity.gongyi.GongyiGuichengAffix;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.quality.Quality;
import com.task.util.Upload;
import com.task.util.Util;

public class QualityAction {
	private QualityServer qualityServer;  
	private Quality quality;
	private String errorMessage;
	private List<Map> maps ;
	private String deptname;
	private String username;
	private Integer del_id;
	private Integer sal_id;
	private Integer test;
	
	//（1）   类型为File的xxx属性封装了该文件域对应的文件内容
	//（2）   类型为String的xxxFileName属性封装了该案文件域对应的文件的文件类型
	//（3）   类型为String的xxxContextType属性封装了该文件域对应的文件的类型
	private File quality_file;
	private String quality_fileFileName;
	private String quality_fileFileContentType;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	
	
	public Integer getTest() {
		return test;
	}
	public void setTest(Integer test) {
		this.test = test;
	}
	public Integer getSal_id() {
		return sal_id;
	}
	public void setSal_id(Integer salId) {
		sal_id = salId;
	}
	public Integer getDel_id() {
		return del_id;
	}
	public void setDel_id(Integer delId) {
		del_id = delId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public File getQuality_file() {
		return quality_file;
	}
	public void setQuality_file(File qualityFile) {
		quality_file = qualityFile;
	}
	public String getQuality_fileFileName() {
		return quality_fileFileName;
	}
	public void setQuality_fileFileName(String qualityFileFileName) {
		quality_fileFileName = qualityFileFileName;
	}
	
	public String getQuality_fileFileContentType() {
		return quality_fileFileContentType;
	}
	public void setQuality_fileFileContentType(String qualityFileFileContentType) {
		quality_fileFileContentType = qualityFileFileContentType;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	public QualityServer getQualityServer() {
		return qualityServer;
	}
	public void setQualityServer(QualityServer qualityServer) {
		this.qualityServer = qualityServer;
	}
	public Quality getQuality() {
		return quality;
	}
	public void setQuality(Quality quality) {
		this.quality = quality;
	}
	
	
	//查询所有
	public String findQuailty(){
		if (quality != null) {
			ActionContext.getContext().getSession().put("quality", quality);
		} else {
			quality = (Quality) ActionContext.getContext().getSession().get("quality");
		}
		Object[] object = this.qualityServer.findQuailty(quality,Integer.parseInt(cpage), pageSize,this.test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if(this.test!=null){
					if(this.test==1){
						this.setUrl("QualityAction_findQuailty.action?test=1");
					}if(this.test==2){
						this.setUrl("QualityAction_findQuailty.action?test=2");
					}
				}else{
					this.setUrl("QualityAction_findQuailty.action");
				}
				
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findQuailty";
	}
	
	
	//添加之前先查出部门
	public String toaddQuality(){
		Users loginUser=Util.getLoginUser();//获得登陆用户
		 this.deptname = loginUser.getDept();
		 this.username = loginUser.getName();
		return "toaddQuality";
	}
	
	
	//添加
	public String addQuality(){
		
//		JSONObject obj = new JSONObject();//返回前端json数据
//		ServletContext servletContext = ServletActionContext.getServletContext();  
//		HttpServletRequest request = ServletActionContext.getRequest();  
//		HttpServletResponse response = ServletActionContext.getResponse();
//		//模块附件根目录
//		String uploadRootPath="/upload";
//		String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
		//SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		//String dateStr=formatter.format(new Date());
//		Calendar  calendar= Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);    //获取年
//		int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
//		int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
//		int minuts = calendar.get(Calendar.MINUTE);          //获取当前分钟
//		int second = calendar.get(Calendar.SECOND);          //获取当前秒
//		int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
//		String yearStr=""+year;
//		String monthStr=month<10?("0"+month):(""+month);
//		String dayStr=day<10?("0"+day):(""+day);
//		String hourStr=hour<10?("0"+hour):(""+hour);
//		String minutsStr=minuts<10?("0"+minuts):(""+minuts);
//		String secondStr=second<10?("0"+second):(""+second);
//		String milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
//		
//		//文件目录管理
//		String directoryPath=yearStr+"-"+monthStr;
//		String realDirectoryPath=realUploadRootPath+"//"+directoryPath;
		
//		//检查文件目录是否存在
//		File dir=new File(realDirectoryPath);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
		//图片
			if(this.quality_file!=null){
				//文件路径
				String fileType  = quality_fileFileName.substring(quality_fileFileName
						.lastIndexOf("."), quality_fileFileName.length());
				String realFileName=null;
				if(quality.getQuality_time()!=null&&!quality.getQuality_time().equals("")){
					realFileName=Util.DateToString(Util.StringToDate(quality.getQuality_time(), "yyyy-MM-dd HH:mm:ss"), "yyyyMMddHHmmss")+fileType;
				}else{
					realFileName=Util.getDateTime("yyyyMMddHHmmss")+fileType;
				}
				String realFilePath="/upload/pinzhi";
				// 打开存放上传文件的位置
				String path =  ServletActionContext.getServletContext().getRealPath(realFilePath);
				File file=new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}
				Upload upload=new Upload();
				upload.UploadFile(quality_file, quality_fileFileName, realFileName, realFilePath, null);
				this.quality.setQuality_file(realFileName);
			}
		this.qualityServer.saveQuality(this.quality);
		errorMessage="添加成功!";
		return "addQuality";
	}
	
	//删除
	public String delQuality(){
		this.qualityServer.delQuality(this.del_id);
		if(this.test!=null){
			return "delQuality1";
		}else{
			return "delQuality";
		}
		
	}
	
	//根据Id查询
	public String salQuality(){
		Users loginUser=Util.getLoginUser();//获得登陆用户
		 this.deptname = loginUser.getDept();
		 this.username = loginUser.getName();
		this.quality=this.qualityServer.findQuailtyById(this.sal_id);
		return "salQuality";
	}
	
	//修改
	public String updateQuality(){
		
//		JSONObject obj = new JSONObject();//返回前端json数据
//		ServletContext servletContext = ServletActionContext.getServletContext();  
//		HttpServletRequest request = ServletActionContext.getRequest();  
//		HttpServletResponse response = ServletActionContext.getResponse();
//		//模块附件根目录
//		String uploadRootPath="/upload";
//		String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
//		//SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
//		//String dateStr=formatter.format(new Date());
//		Calendar  calendar= Calendar.getInstance();
//		int year = calendar.get(Calendar.YEAR);    //获取年
//		int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
//		int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
//		int hour = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
//		int minuts = calendar.get(Calendar.MINUTE);          //获取当前分钟
//		int second = calendar.get(Calendar.SECOND);          //获取当前秒
//		int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
//		String yearStr=""+year;
//		String monthStr=month<10?("0"+month):(""+month);
//		String dayStr=day<10?("0"+day):(""+day);
//		String hourStr=hour<10?("0"+hour):(""+hour);
//		String minutsStr=minuts<10?("0"+minuts):(""+minuts);
//		String secondStr=second<10?("0"+second):(""+second);
//		String milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
//		
//		//文件目录管理
//		String directoryPath=yearStr+"-"+monthStr;
//		String realDirectoryPath=realUploadRootPath+"//"+directoryPath;
//		
//		//检查文件目录是否存在
//		File dir=new File(realDirectoryPath);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		//图片
//		try {
//			if(this.quality_file!=null){
//				//文件路径
//				String fileName=this.quality_fileFileName;
//				String fileType  = fileName.substring(fileName.lastIndexOf('.') + 1);
//				String realFileName=hourStr+minutsStr+secondStr+milliSecondStr+"."+fileType;
//				String realFilePath=realDirectoryPath+"//"+realFileName;
//				FileOutputStream fos;
//				
//					fos = new FileOutputStream(realFilePath);
//				
//				FileInputStream fis=new FileInputStream(this.quality_file);
//				byte[] buffers=new byte[1024];
//				int len=0;
//				while((len=fis.read(buffers))!=-1){
//					fos.write(buffers, 0, len);
//				}
//				String serverName=request.getServerName();//获取服务器的名字
//				int serverPort=request.getServerPort();//获取服务器的端口号
//				String contextPath=request.getContextPath();//项目根目录
//				//不含主机名 端口
//				String www="http://"+serverName+":"+serverPort;
//				String webPath=contextPath+uploadRootPath+"/"+directoryPath+"/"+realFileName;
//				this.quality.setQuality_file(webPath);
//			}
//			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		if(this.quality_file!=null){
			//文件路径
			String fileType  = quality_fileFileName.substring(quality_fileFileName
					.lastIndexOf("."), quality_fileFileName.length());
			String realFileName=null;
			if(quality.getQuality_time()!=null&&!quality.getQuality_time().equals("")){
				realFileName=Util.DateToString(Util.StringToDate(quality.getQuality_time(), "yyyy-MM-dd HH:mm:ss"), "yyyyMMddHHmmss")+fileType;
			}else{
				realFileName=Util.getDateTime("yyyyMMddHHmmss")+fileType;
			}
			String realFilePath="/upload/pinzhi";
			// 打开存放上传文件的位置
			String path =  ServletActionContext.getServletContext().getRealPath(realFilePath);
			Upload upload=new Upload();
			File file=new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就创建
			}
			//删除原文件
			String oldFileName=qualityServer.getFileName(quality.getId());
			if(oldFileName!=null){
				File oldFile=new File(path+"/"+oldFileName);
				if(oldFile.exists()){
					oldFile.delete();
				}
			}
			upload.UploadFile(quality_file, quality_fileFileName, realFileName, realFilePath, null);
			this.quality.setQuality_file(realFileName);
		}
		
		this.qualityServer.updateQuality(this.quality);
		this.errorMessage="修改成功！";
		return "updateQuality";
	}
	
	//下载附件
	public String downloadQuality(){
		this.quality=this.qualityServer.findQuailtyById(this.sal_id);
		String url=quality.getQuality_file();
		String fileName=quality.getQuality_file()+"."+url.substring(url.lastIndexOf('.')+1);
		ServletContext servletContext = ServletActionContext.getServletContext();  
		HttpServletRequest request = ServletActionContext.getRequest();  
		HttpServletResponse response = ServletActionContext.getResponse();
		FileInputStream fis=null;
		try {
			String downloadPath=servletContext.getRealPath("/upload");
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			fis=new FileInputStream(downloadPath+"//"+url.substring(url.indexOf("/upload")+8));
			int len=0;
			byte[] buffers=new byte[1024];
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition","attachment;filename=\"" + fileName + "\"");
			while((len=fis.read(buffers))!=-1){
				OutputStream os=response.getOutputStream();
				os.write(buffers, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	

}
