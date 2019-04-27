package com.task.action.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.task.Server.bbs.AffixServer;
import com.task.entity.bbs.Affix;

public class AffixAction extends ActionSupport implements ServletRequestAware,  ServletResponseAware, SessionAware, ServletContextAware , ModelDriven {
	private ServletContext servletContext;
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected Map<String, Object> session;
	
	//（1）   类型为File的xxx属性封装了该文件域对应的文件内容
	//（2）   类型为String的xxxFileName属性封装了该案文件域对应的文件的文件类型
	//（3）   类型为String的xxxContextType属性封装了该文件域对应的文件的类型
	private List<File> fileList;
	private List<String> fileListFileName;
	private List<String> fileListContextType;
	private AffixServer affixServer;
	
	public String upload(){
		FileOutputStream fos=null;
		FileInputStream fis=null;
		try {
			//ServletContext servletContext = ServletActionContext.getServletContext();  
			//HttpServletRequest request = ServletActionContext.getRequest();  
			//HttpServletResponse response = ServletActionContext.getResponse();  
			//模块附件根目录
			String uploadRootPath="/upload/bbs";
			String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
			//SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
			//String dateStr=formatter.format(new Date());
			Calendar  calendar= Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);    //获取年
			int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
			int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
			int hour = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
			int minuts = calendar.get(Calendar.MINUTE);          //获取当前分钟
			int second = calendar.get(Calendar.SECOND);          //获取当前秒
			int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
			String yearStr=""+year;
			String monthStr=month<10?("0"+month):(""+month);
			String dayStr=day<10?("0"+day):(""+day);
			String hourStr=hour<10?("0"+hour):(""+hour);
			String minutsStr=minuts<10?("0"+minuts):(""+minuts);
			String secondStr=second<10?("0"+second):(""+second);
			String milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
			
			//文件目录管理
			String directoryPath=yearStr+"-"+monthStr;
			String realDirectoryPath=realUploadRootPath+"//"+directoryPath;
			
			//检查文件目录是否存在
			File dir=new File(realDirectoryPath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			//保存文件
			if(fileList!=null){
				int size=fileList.size();
				for(int i=0;i<size;i++){
					//文件路径
					String fileName=fileListFileName.get(i);
					String fileType  = fileName.substring(fileName.lastIndexOf('.') + 1);
					String realFileName=hourStr+minutsStr+secondStr+milliSecondStr+"."+fileType;
					String realFilePath=realDirectoryPath+"//"+realFileName;
					fos=new FileOutputStream(realFilePath);
					fis=new FileInputStream(fileList.get(i));
					byte[] buffers=new byte[1024];
					int len=0;
					while((len=fis.read(buffers))!=-1){
						fos.write(buffers, 0, len);
					}
					long fileSize=fileList.get(i).length();
					String serverName=request.getServerName();//获取服务器的名字
					int serverPort=request.getServerPort();//获取服务器的端口号
					String contextPath=request.getContextPath();//项目根目录
					//不含主机名 端口
					String www="http://"+serverName+":"+serverPort;
					String webPath=contextPath+uploadRootPath+"/"+directoryPath+"/"+realFileName;
					//String webPath=uploadRootPath+"/"+directoryPath+"/"+realFileName;
					//String fileUrl =www+webPath;
					
					//************************备份*************************
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/bbs"
						+ "//" + directoryPath;
					File beiFenfileRealPathFile = new File(beiFenfileRealPath);
					if(!beiFenfileRealPathFile.exists()){
						beiFenfileRealPathFile.mkdirs();
					}
					String beiFenfile = "D:/WorkSpace/HHTask/WebRoot/upload/bbs"
						+ "//" + directoryPath+"//"+realFileName;
					File beiFenfileFile = new File(beiFenfile);
					try {
						FileCopyUtils.copy(fileList.get(i), beiFenfileFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//***********************备份**************************
					String fileUrl =webPath;
					Affix affix=new Affix();
					affix.setFileName(fileName);
					affix.setRealFileName(realFileName);
					affix.setFileType(fileType);
					affix.setPath(webPath);
					affix.setCreateDate(new Date());
					affixServer.addAffix(affix);
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", fileUrl);
					//.println(obj.toJSONString());
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(obj.toJSONString());
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	
	//KVP上传路径
	public String upload1(){
		FileOutputStream fos=null;
		FileInputStream fis=null;
		try {
			//ServletContext servletContext = ServletActionContext.getServletContext();  
			//HttpServletRequest request = ServletActionContext.getRequest();  
			//HttpServletResponse response = ServletActionContext.getResponse();  
			//模块附件根目录
			String uploadRootPath="/upload/kvp";
			String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
			//SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
			//String dateStr=formatter.format(new Date());
			Calendar  calendar= Calendar.getInstance();
			int year = calendar.get(Calendar.YEAR);    //获取年
			int month = calendar.get(Calendar.MONTH) + 1;   //获取月份，0表示1月份
			int day = calendar.get(Calendar.DAY_OF_MONTH);    //获取当前天数
			int hour = calendar.get(Calendar.HOUR_OF_DAY);       //获取当前小时
			int minuts = calendar.get(Calendar.MINUTE);          //获取当前分钟
			int second = calendar.get(Calendar.SECOND);          //获取当前秒
			int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
			String yearStr=""+year;
			String monthStr=month<10?("0"+month):(""+month);
			String dayStr=day<10?("0"+day):(""+day);
			String hourStr=hour<10?("0"+hour):(""+hour);
			String minutsStr=minuts<10?("0"+minuts):(""+minuts);
			String secondStr=second<10?("0"+second):(""+second);
			String milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
			
			//文件目录管理
			String directoryPath=yearStr+"-"+monthStr;
			String realDirectoryPath=realUploadRootPath+"//"+directoryPath;
			
			//检查文件目录是否存在
			File dir=new File(realDirectoryPath);
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			//保存文件
			if(fileList!=null){
				int size=fileList.size();
				for(int i=0;i<size;i++){
					//文件路径
					String fileName=fileListFileName.get(i);
					String fileType  = fileName.substring(fileName.lastIndexOf('.') + 1);
					String realFileName=hourStr+minutsStr+secondStr+milliSecondStr+"."+fileType;
					String realFilePath=realDirectoryPath+"//"+realFileName;
					fos=new FileOutputStream(realFilePath);
					fis=new FileInputStream(fileList.get(i));
					byte[] buffers=new byte[1024];
					int len=0;
					while((len=fis.read(buffers))!=-1){
						fos.write(buffers, 0, len);
					}
					long fileSize=fileList.get(i).length();
					String serverName=request.getServerName();//获取服务器的名字
					int serverPort=request.getServerPort();//获取服务器的端口号
					String contextPath=request.getContextPath();//项目根目录
					//不含主机名 端口
					String www="http://"+serverName+":"+serverPort;
					String webPath=contextPath+uploadRootPath+"/"+directoryPath+"/"+realFileName;
					//String webPath=uploadRootPath+"/"+directoryPath+"/"+realFileName;
					//String fileUrl =www+webPath;
					
					//************************备份*************************
					String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/bbs"
						+ "//" + directoryPath;
					File beiFenfileRealPathFile = new File(beiFenfileRealPath);
					if(!beiFenfileRealPathFile.exists()){
						beiFenfileRealPathFile.mkdirs();
					}
					String beiFenfile = "D:/WorkSpace/HHTask/WebRoot/upload/bbs"
						+ "//" + directoryPath+"//"+realFileName;
					File beiFenfileFile = new File(beiFenfile);
					try {
						FileCopyUtils.copy(fileList.get(i), beiFenfileFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
					//***********************备份**************************
					String fileUrl =webPath;
					Affix affix=new Affix();
					affix.setFileName(fileName);
					affix.setRealFileName(realFileName);
					affix.setFileType(fileType);
					affix.setPath(webPath);
					affix.setCreateDate(new Date());
					affixServer.addAffix(affix);
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", fileUrl);
					//.println(obj.toJSONString());
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().write(obj.toJSONString());
					response.getWriter().close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	
	public String download(){
		FileInputStream fis=null;
		try {
			String downloadPath=servletContext.getRealPath("/affix");
			String path=request.getParameter("path");
			String fileName=request.getParameter("name");
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			fis=new FileInputStream(downloadPath+"//"+path);
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


	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}
	
	public AffixServer getAffixServer() {
		return affixServer;
	}

	public void setAffixServer(AffixServer affixServer) {
		this.affixServer = affixServer;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.servletContext=context;
	}

	@Override
	public Object getModel() {
		// TODO Auto-generated method stub
		//return this.model;
		return null;
	}

	public List<String> getFileListFileName() {
		return fileListFileName;
	}

	public void setFileListFileName(List<String> fileListFileName) {
		this.fileListFileName = fileListFileName;
	}

	public List<String> getFileListContextType() {
		return fileListContextType;
	}

	public void setFileListContextType(List<String> fileListContextType) {
		this.fileListContextType = fileListContextType;
	}
}
