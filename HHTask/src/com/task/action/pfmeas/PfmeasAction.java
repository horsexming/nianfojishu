package com.task.action.pfmeas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.Pfmeas.PfmeasServer;
import com.task.entity.Users;
import com.task.entity.Pfmeas.Pfmeas;
import com.task.util.Upload;
import com.task.util.Util;

public class PfmeasAction {

	private PfmeasServer pfmeasServer;
	private List<Pfmeas> pfmeaslist;
	private Pfmeas pfmeas;
	private String errorMessage;
	private List<Map> maps;
	private String deptname;
	private String username;
	private Integer del_id;
	private Integer sal_id;
	private Integer test;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer[] object;
	private File upload;
	private String uploadFileName;
	private String uploadFileContentType;


	// （1） 类型为File的xxx属性封装了该文件域对应的文件内容
	// （2） 类型为String的xxxFileName属性封装了该案文件域对应的文件的文件类型
	// （3） 类型为String的xxxContextType属性封装了该文件域对应的文件的类型
	
	// 查询所有
	public String findPfmeas() {
		if (pfmeas != null) {
			ActionContext.getContext().getSession().put("pfmeas", pfmeas);
		} else {
			pfmeas = (Pfmeas) ActionContext.getContext().getSession().get(
					"pfmeas");
		}
		Object[] object = this.pfmeasServer.findPfmeas(pfmeas, Integer
				.parseInt(cpage), pageSize, this.test);
			if (object != null && object.length > 0) {
				pfmeaslist= (List<Pfmeas>) object[0];
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (this.test != null) {
					if (this.test == 1) {
						this.setUrl("pfmeasAction_findPfmeas.action?test=1");
					}
					if (this.test == 2) {
						this.setUrl("pfmeasAction_findPfmeas.action?test=2");
					}
				} else {
					this.setUrl("pfmeasAction_findPfmeas.action");
				}

				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		return "findPfmeas";
	}

	// 添加之前先查出部门
	public String toaddPfmeas() {
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		this.deptname = loginUser.getDept();
		this.username = loginUser.getName();
		return "toaddPfmeas";
	}

	// 添加
	public String addPfmeas() {

		// JSONObject obj = new JSONObject();//返回前端json数据
		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		// //模块附件根目录
		// String uploadRootPath="/upload";
		// String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
		// SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		// String dateStr=formatter.format(new Date());
		// Calendar calendar= Calendar.getInstance();
		// int year = calendar.get(Calendar.YEAR); //获取年
		// int month = calendar.get(Calendar.MONTH) + 1; //获取月份，0表示1月份
		// int day = calendar.get(Calendar.DAY_OF_MONTH); //获取当前天数
		// int hour = calendar.get(Calendar.HOUR_OF_DAY); //获取当前小时
		// int minuts = calendar.get(Calendar.MINUTE); //获取当前分钟
		// int second = calendar.get(Calendar.SECOND); //获取当前秒
		// int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
		// String yearStr=""+year;
		// String monthStr=month<10?("0"+month):(""+month);
		// String dayStr=day<10?("0"+day):(""+day);
		// String hourStr=hour<10?("0"+hour):(""+hour);
		// String minutsStr=minuts<10?("0"+minuts):(""+minuts);
		// String secondStr=second<10?("0"+second):(""+second);
		// String
		// milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
		//		
		// //文件目录管理
		// String directoryPath=yearStr+"-"+monthStr;
		// String realDirectoryPath=realUploadRootPath+"//"+directoryPath;

		// //检查文件目录是否存在
		// File dir=new File(realDirectoryPath);
		// if(!dir.exists()){
		// dir.mkdirs();
		// }
		// 图片
		if (this.upload != null) {
			// 文件路径
			String realFileName = null;
			if (pfmeas.getPfmeas_time() != null
					&& !pfmeas.getPfmeas_time().equals("")) {
				realFileName = Util.DateToString(Util
						.StringToDate((String) pfmeas.getPfmeas_time(),
								"yyyy-MM-dd HH:mm:ss"), "yyyyMMddHHmmss");
			} else {
				realFileName = Util.getDateTime("yyyyMMddHHmmss");
			}
			String realFilePath = "/upload/pfmeas";
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就创建
			}
			Upload uploadFile = new Upload();
			uploadFile.UploadFile(upload, uploadFileName, realFileName,
					realFilePath, null);
			this.pfmeas.setPfmeas_file(realFileName);
		}
		this.pfmeasServer.savePfmeas(this.pfmeas);
		errorMessage = "添加成功!";
		return "addPfmeas";
	}

	// 删除
	public String delPfmeas() {
		this.pfmeasServer.delPfmeas(this.del_id);
		if (this.test != null) {
			return "delPfmeas";
		} else {
			return "delPfmeas";
		}

	}

	// 根据Id查询
	public String salPfmeas() {
		Users loginUser = Util.getLoginUser();// 获得登陆用户
		this.deptname = loginUser.getDept();
		this.username = loginUser.getName();
		this.pfmeas = this.pfmeasServer.findPfmeasById(this.sal_id);
		return "salPfmeas";
	}

	// 修改
	public String updatePfmeas() {

		// JSONObject obj = new JSONObject();//返回前端json数据
		// ServletContext servletContext =
		// ServletActionContext.getServletContext();
		// HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		// //模块附件根目录
		// String uploadRootPath="/upload";
		// String realUploadRootPath=servletContext.getRealPath(uploadRootPath);
		// //SimpleDateFormat formatter=new
		// SimpleDateFormat("yyyyMMddhhmmssSSS");
		// //String dateStr=formatter.format(new Date());
		// Calendar calendar= Calendar.getInstance();
		// int year = calendar.get(Calendar.YEAR); //获取年
		// int month = calendar.get(Calendar.MONTH) + 1; //获取月份，0表示1月份
		// int day = calendar.get(Calendar.DAY_OF_MONTH); //获取当前天数
		// int hour = calendar.get(Calendar.HOUR_OF_DAY); //获取当前小时
		// int minuts = calendar.get(Calendar.MINUTE); //获取当前分钟
		// int second = calendar.get(Calendar.SECOND); //获取当前秒
		// int milliSecond=calendar.get(Calendar.MILLISECOND);//毫秒
		// String yearStr=""+year;
		// String monthStr=month<10?("0"+month):(""+month);
		// String dayStr=day<10?("0"+day):(""+day);
		// String hourStr=hour<10?("0"+hour):(""+hour);
		// String minutsStr=minuts<10?("0"+minuts):(""+minuts);
		// String secondStr=second<10?("0"+second):(""+second);
		// String
		// milliSecondStr=milliSecond<10?("00"+milliSecond):(milliSecond<100)?("0"+milliSecond):(""+milliSecond);
		//		
		// //文件目录管理
		// String directoryPath=yearStr+"-"+monthStr;
		// String realDirectoryPath=realUploadRootPath+"//"+directoryPath;
		//		
		// //检查文件目录是否存在
		// File dir=new File(realDirectoryPath);
		// if(!dir.exists()){
		// dir.mkdirs();
		// }
		// //图片
		// try {
		// if(this.Pfmeas_file!=null){
		// //文件路径
		// String fileName=this.Pfmeas_fileFileName;
		// String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
		// String
		// realFileName=hourStr+minutsStr+secondStr+milliSecondStr+"."+fileType;
		// String realFilePath=realDirectoryPath+"//"+realFileName;
		// FileOutputStream fos;
		//				
		// fos = new FileOutputStream(realFilePath);
		//				
		// FileInputStream fis=new FileInputStream(this.Pfmeas_file);
		// byte[] buffers=new byte[1024];
		// int len=0;
		// while((len=fis.read(buffers))!=-1){
		// fos.write(buffers, 0, len);
		// }
		// String serverName=request.getServerName();//获取服务器的名字
		// int serverPort=request.getServerPort();//获取服务器的端口号
		// String contextPath=request.getContextPath();//项目根目录
		// //不含主机名 端口
		// String www="http://"+serverName+":"+serverPort;
		// String
		// webPath=contextPath+uploadRootPath+"/"+directoryPath+"/"+realFileName;
		// this.Pfmeas.setPfmeas_file(webPath);
		// }
		//			
		// } catch (Exception e1) {
		// e1.printStackTrace();
		// }

		if (this.upload != null) {
			// 文件路径
			String realFileName = null;
			if (pfmeas.getPfmeas_time() != null
					&& !pfmeas.getPfmeas_time().equals("")) {
				realFileName = Util.DateToString(Util.StringToDate(pfmeas
						.getPfmeas_time(), "yyyy-MM-dd HH:mm:ss"),
						"yyyyMMddHHmmss");
			} else {
				realFileName = Util.getDateTime("yyyyMMddHHmmss");
			}
			String realFilePath = "/upload/pinzhi";
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			Upload uploadFile = new Upload();
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就创建
			}
			// 删除原文件
			String oldFileName = pfmeasServer.getFileName(pfmeas.getId());
			if (oldFileName != null) {
				File oldFile = new File(path + "/" + oldFileName);
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
			uploadFile.UploadFile(upload, uploadFileName, realFileName,
					realFilePath, null);
			this.pfmeas.setPfmeas_file(realFileName);
		}

		this.pfmeasServer.updatePfmeas(this.pfmeas);
		this.errorMessage = "修改成功！";
		return "updatePfmeas";
	}

	// 下载附件
	public String downloadPfmeas() {
		this.pfmeas = this.pfmeasServer.findPfmeasById(this.sal_id);
		String url = pfmeas.getPfmeas_file();
		String fileName = pfmeas.getPfmeas_file() + "."
				+ url.substring(url.lastIndexOf('.') + 1);
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		FileInputStream fis = null;
		try {
			String downloadPath = servletContext.getRealPath("/upload");
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
			fis = new FileInputStream(downloadPath + "//"
					+ url.substring(url.indexOf("/upload") + 8));
			int len = 0;
			byte[] buffers = new byte[1024];
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + "\"");
			while ((len = fis.read(buffers)) != -1) {
				OutputStream os = response.getOutputStream();
				os.write(buffers, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String showLIst() {
		return "fmeas_show";
	}

	

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


	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public Integer[] getObject() {
		return object;
	}

	public void setObject(Integer[] object) {
		this.object = object;
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

	public PfmeasServer getPfmeasServer() {
		return pfmeasServer;
	}

	public void setPfmeasServer(PfmeasServer pfmeasServer) {
		this.pfmeasServer = pfmeasServer;
	}

	public Pfmeas getPfmeas() {
		return pfmeas;
	}

	public void setPfmeas(Pfmeas pfmeas) {
		this.pfmeas = pfmeas;
	}

	public List<Pfmeas> getPfmeaslist() {
		return pfmeaslist;
	}

	public void setPfmeaslist(List<Pfmeas> pfmeaslist) {
		this.pfmeaslist = pfmeaslist;
	}

}
