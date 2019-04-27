package com.task.action.menjin;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.OftenVisitServer;
import com.task.entity.menjin.OftenVisit;
import com.task.util.Upload;
import com.task.util.Util;

public class OftenVisitAction{
	private OftenVisitServer oftenVisitServer;
	private OftenVisit oftenvisit;
	private List<OftenVisit> oftenVisitList;
	private String errorMessage;
	private String successMessage;
	
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	// 上传文件对象
	private File fujian;// 行驶证
	// 上传文件名
	private String fujianFileName;
	// 上传文件类型
	private String fujianContentType;
	
	private String taga = "";//来访申请部门.所有
	// 添加来访登记表
	public String add() {
		if (oftenvisit != null) {
			if (fujian != null) {
				// 文件路径
				String realFileName = Util.getDateTime("yyyyMMddHHmmss");
				// 打开存放文件的位置
				String realFilePath = "/upload/file/OftenVisitCar";
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就新建
				}
				Upload upload = new Upload();// 文件上传工具类
				realFileName=upload.UploadFile(fujian, fujianFileName, null,
						realFilePath, null);
				oftenvisit.setCarFiles(realFilePath+"/"+realFileName);// 文件新名称
				
				errorMessage = oftenVisitServer.addOftenVisit(oftenvisit);
				if ("已申请!".equals(errorMessage)) {
					url = "OftenVisitAction_toadd.action";
					return "error";
				}
			}
		}
		errorMessage = "申请失败!请重试";
		return "error";
	}
	//跳转到来访登记表
	public String toadd(){
		return "oftenVisitAction_add";
	}
   //跳转查询页面
	@SuppressWarnings("unchecked")
	public String showList() {
		if (oftenvisit != null) {
			ActionContext.getContext().getSession().put("visit", oftenvisit);
		} else {
			oftenvisit = (OftenVisit) ActionContext.getContext().getSession()
					.get("oftenvisit");
		}
		Map<Integer, Object> map =  oftenVisitServer.findOftenVisitByCondition(oftenvisit, Integer
				.parseInt(cpage), pageSize);
		oftenVisitList = (List<OftenVisit>) map.get(1);// 显示面试单列表
		if (oftenVisitList != null && oftenVisitList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("OftenVisitAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "oftenVisitAction_show";
	}
	
	/**
	 * 跳转到修改方法
	 * 
	 * @return
	 */
	public String toupdate() {
		if (oftenvisit!=null) {
			OftenVisit oftenVisit1 = oftenVisitServer
			.OftenVisitByid(oftenvisit.getId());
			if (oftenVisit1 != null) {
				oftenvisit = oftenVisit1;
				return "oftenVisitAction_update";
			}
			errorMessage = "不存在该数据!请检查";
			return "error";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	/**
	 * 修改方法
	 * 
	 * @return
	 */
	public String update() {
		errorMessage = oftenVisitServer
				.updateOftenVisit(oftenvisit);
		if ("修改成功".equals(errorMessage)) {
			errorMessage = "修改成功!";
			url = "OftenVisitAction_showList.action";
			return "error";
		}
		return "error";
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

	public String getTaga() {
		return taga;
	}
	public void setTaga(String taga) {
		this.taga = taga;
	}
	public OftenVisitServer getOftenVisitServer() {
		return oftenVisitServer;
	}
	public void setOftenVisitServer(OftenVisitServer oftenVisitServer) {
		this.oftenVisitServer = oftenVisitServer;
	}
	public OftenVisit getOftenvisit() {
		return oftenvisit;
	}
	public void setOftenvisit(OftenVisit oftenvisit) {
		this.oftenvisit = oftenvisit;
	}
	public List<OftenVisit> getOftenVisitList() {
		return oftenVisitList;
	}
	public void setOftenVisitList(List<OftenVisit> oftenVisitList) {
		this.oftenVisitList = oftenVisitList;
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
	
}
