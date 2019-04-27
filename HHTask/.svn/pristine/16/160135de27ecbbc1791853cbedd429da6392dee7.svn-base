package com.task.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.CredentialsServer;
import com.task.entity.Credentials;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class CredentialsAction extends ActionSupport {
	private CredentialsServer credentialsServer;
	private Credentials credentials;// 证件对象
	private Credentials credentials1;// 证件对象
	private List<Credentials> credentialslist;// 证件对象集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Users user;

	// 分页
	private String tage;// 证件标识  1：驾驶证 2：行驶证    修改/页面为2/查看页面为空
	private String ps;//person： 个人添加  admin：管理员添加
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Integer id;
	// 上传文件对象
	private File fujian;// 驾驶证
	// 上传文件名
	private String fujianFileName;
	private String fujianContentType;
	
	private File fujian1;// 行驶证
	// 上传文件名
	private String fujian1FileName;
	private String fujian1ContentType;
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	/**
	 * 获取分页显示所有证件信息
	 * 
	 */
	public String showallcredentials() {
		if (credentials != null) {
			ActionContext.getContext().getSession().put("Credentials",
					credentials);
		} else {
			credentials = (Credentials) ActionContext.getContext().getSession()
					.get("Credentials");
		}
		Map<Integer, Object> map = credentialsServer.findcredentials(
				credentials, Integer.parseInt(cpage), pageSize,ps);
		credentialslist = new ArrayList<Credentials>();
		if (map != null) {
			credentialslist = (List<Credentials>) map.get(1);
			if (credentialslist != null && credentialslist.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("CredentialsAction!showallcredentials.action?ps="+ps);
			} else {
				errorMessage = "没有符合条件的信息,请检查后重试!";
			}
		}
		return "showcredentials";
	}

	/**
	 * 显示单条证件信息
	 * 
	 */
	public String findcredentialsById() {
		credentials = credentialsServer.findcredentialsById(id);
		if (credentials != null) {
			this.setCredentials(credentials);
			if (credentials.getCardtype().equals("驾驶证")) {
				return "jiashizheng";
			} else {
				return "xingshizheng";
			}
		} else {
			errorMessage = "显示失败,不存在该证件信息！";
			return this.showallcredentials();
		}
	}

	/**
	 * 删除单条证件信息
	 * 
	 */
	public String delete() {
		boolean b = credentialsServer.delete(id);
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		credentials = null;
		return this.showallcredentials();
	}

	/**
	 * 添加证件信息
	 * 
	 */
	public String add() {
		if(credentials!=null){
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/CredentialsFile";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				credentials.setCredentialsfile(realFilePath + "/"+ realFileName);// 文件新名称
			}
			errorMessage = credentialsServer.addCredentials(credentials, ps);
			if ("添加成功！".equals(errorMessage)) {
				url = "CredentialsAction!showallcredentials.action?ps="+ps;
			}
		}
		return "error";
	}
	
	public String addOne() {
		String mess = "";
		if(credentials!=null){
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/CredentialsFile";
				String path = ServletActionContext.getServletContext()
				.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian, fujianFileName,
						null, realFilePath, null);
				credentials.setCredentialsfile(realFilePath + "/"+ realFileName);// 文件新名称
			}
			errorMessage = credentialsServer.addCredentials(credentials, ps);
			if ("添加成功！".equals(errorMessage)) {
				url = "CredentialsAction!showallcredentials.action?ps="+ps;
			}
			mess = errorMessage + " ";
		}
		if(credentials1!=null){
			if (fujian1 != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/CredentialsFile";
				String path = ServletActionContext.getServletContext()
				.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName = upload.UploadFile(fujian1, fujian1FileName,
						null, realFilePath, null);
				credentials1.setCredentialsfile(realFilePath + "/"+ realFileName);// 文件新名称
			}
			errorMessage = credentialsServer.addCredentials(credentials1, ps);
			if ("添加成功！".equals(errorMessage)) {
				url = "CredentialsAction!showallcredentials.action?ps="+ps;
			}
			errorMessage = mess + errorMessage;
		}
		return "error";
	}

	public String toAdd(){
		return "addzhengjianInfor";
	}
	
	/**
	 * 跳转添加证件信息
	 * 
	 */
	public String jumpadd() {
		if (tage.equals("1")) {
			if(ps.equals("person")){
				user = Util.getLoginUser();
			}
			return "addjiashizheng";
		} else {
			if(ps.equals("person")){
				user = Util.getLoginUser();
			}else{
				user = null;
			}
			return "addxingshizheng";
		}
	}
	/**
	 * 跳转到修改方法
	 * 
	 * @return
	 */
	public String toupdate() {
		credentials = credentialsServer.findcredentialsById(id);
		if (credentials != null) {
			this.setCredentials(credentials);
			if (credentials.getCardtype().equals("驾驶证")) {
				return "jiashizheng";
			} else {
				return "xingshizheng";
			}
		}			
		return "error";
	}
	/**
	 * 修改方法
	 * 
	 * @return
	 */
	public void update() {
		errorMessage = credentialsServer.updateCredentials(credentials);
		if ("true".equals(errorMessage)) {
			MKUtil.writeJSON("修改成功");
		}
	}

	public String shenqignGongche(){
		if(credentials!=null&&credentials.getId()!=null){
			errorMessage = credentialsServer.shenqing(credentials);
			if ("申请成功！".equals(errorMessage))
				url = "CredentialsAction!showallcredentials.action?ps="+ps;
		}
		return "error";
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

	public CredentialsServer getCredentialsServer() {
		return credentialsServer;
	}

	public void setCredentialsServer(CredentialsServer credentialsServer) {
		this.credentialsServer = credentialsServer;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public List<Credentials> getCredentialslist() {
		return credentialslist;
	}

	public void setCredentialslist(List<Credentials> credentialslist) {
		this.credentialslist = credentialslist;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTage() {
		return tage;
	}

	public void setTage(String tage) {
		this.tage = tage;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public File getFujian() {
		return fujian;
	}

	public void setFujian(File fujian) {
		this.fujian = fujian;
	}

	public String getFujianFileName() {
		return fujianFileName;
	}

	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}

	public String getFujianContentType() {
		return fujianContentType;
	}

	public void setFujianContentType(String fujianContentType) {
		this.fujianContentType = fujianContentType;
	}

	public Credentials getCredentials1() {
		return credentials1;
	}

	public void setCredentials1(Credentials credentials1) {
		this.credentials1 = credentials1;
	}

	public File getFujian1() {
		return fujian1;
	}

	public void setFujian1(File fujian1) {
		this.fujian1 = fujian1;
	}

	public String getFujian1FileName() {
		return fujian1FileName;
	}

	public void setFujian1FileName(String fujian1FileName) {
		this.fujian1FileName = fujian1FileName;
	}

	public String getFujian1ContentType() {
		return fujian1ContentType;
	}

	public void setFujian1ContentType(String fujian1ContentType) {
		this.fujian1ContentType = fujian1ContentType;
	}
	
}
