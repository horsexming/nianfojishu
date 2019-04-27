package com.task.action.sys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sys.LicenseMsgServer;
import com.task.entity.system.LicenseMsg;
import com.task.util.DateUtil;
import com.task.util.MKUtil;
import com.task.util.licensecreate.CreateLicense;

/**
 * License Action层
 * 
 * @author 唐晓斌
 * 
 */
@SuppressWarnings("serial")
public class LicenseMsgAction extends ActionSupport {
	// 公司业务层对象
	private LicenseMsgServer licenseMsgServer;
	private List<LicenseMsg> comList;
	private List<LicenseMsg> enddingList;//即将到期的公司
	private List<LicenseMsg> endList;//到期的公司
	// 公司实体对象
	private LicenseMsg licenseMsg;

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页信息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	

	/**
	 * 获取分页显示的公司列表信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showLicenseMsgs() {
		if (licenseMsg != null) {
			ActionContext.getContext().getSession().put("licenseMsg",
					licenseMsg);
		} else {
			licenseMsg = (LicenseMsg) ActionContext.getContext().getSession()
					.get("licenseMsg");
		}

		Map<Integer, Object> map = licenseMsgServer.findCompanysByCondition(
				licenseMsg, Integer.parseInt(cpage), pageSize);
		comList = new ArrayList<LicenseMsg>();
		if (null != map) {
			comList = (List<LicenseMsg>) map.get(1);// 显示页的公司信息列表
			enddingList =(List<LicenseMsg>) map.get(3);//显示页证书快到期的公司
			endList =(List<LicenseMsg>) map.get(4);//显示页证书快到期的公司
			if (comList != null && comList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("licenseMsgAction_showLicenseMsgs.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "showlicensemsg";
	}

	/**
	 * 删除公司
	 * 
	 * @return
	 */
	public String delete() {
		licenseMsg = licenseMsgServer.getById(licenseMsg.getId());
		String filename = null;
		if (licenseMsg != null) {
			filename = licenseMsg.getCompanyUrl();
		}
		boolean b = licenseMsgServer.delete(licenseMsg.getId());
		if (b) {
			successMessage = "删除成功！";
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ "upload/file/liclib/" + filename + ".lic";
			File file = new File(filePath);
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		} else {
			errorMessage = "删除失败！";
		}
		licenseMsg = null;
		return showLicenseMsgs();

	}

	/**
	 * 添加公司
	 * 
	 * @return
	 */
	public String add() {
		String end=licenseMsg.getNotAfter();
		if(end ==null||end==""){
			successMessage = "添加失败！请填写结束时间！";
			return showLicenseMsgs();
		}
		String now=DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		long endTime=DateUtil.parseDate(end, "yyyy-MM-dd").getTime();
		long nowTime=DateUtil.parseDate(now, "yyyy-MM-dd").getTime();
		if(nowTime>endTime){
			successMessage = "添加失败！结束时间必须在今天或今天之后";
			return showLicenseMsgs();
		}
		boolean b = licenseMsgServer.add(licenseMsg);
		if (b) {
			// 打开存放上传文件的位置
			String libpath =  ServletActionContext.getServletContext()
			.getRealPath("/")
			+ "upload/file/liclib";
			
			File file1 = new File(libpath);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			// 创建license证书
			CreateLicense cLicense = new CreateLicense();
			// 获取参数
			String path=ServletActionContext.getServletContext()
			.getRealPath("/")
			+ "upload/file/liclib/"
			+ licenseMsg.getCompanyUrl()
			+ ".lic";
			cLicense.setParam(path, licenseMsg.getStartTime(), licenseMsg
					.getNotAfter(), licenseMsg.getCompanyName() + "的license证书");
			// 生成证书
			cLicense.create();
			successMessage = "添加成功！";
		} else {
			successMessage = "添加失败！";
		}
		return showLicenseMsgs();
	}

	/**
	 * 跳往修改公司信息页面
	 * 
	 * @return
	 */
	public String toupdate() {
		licenseMsg = licenseMsgServer.getById(licenseMsg.getId());
		if (licenseMsg != null) {
			return "updatelicensemsg";
		} else {
			errorMessage = "编辑失败,不存在该公司信息！";
			return this.showLicenseMsgs();
		}
	}

	public String update() {
		LicenseMsg lg=licenseMsgServer.getById(licenseMsg.getId());
		String fileName=null;
		if(lg!=null){
			fileName=lg.getCompanyUrl();
		}
		boolean b = licenseMsgServer.update(licenseMsg);
		if (b) {
			String path =  ServletActionContext.getServletContext().getRealPath("/upload/file/liclib");
			if(fileName!=null&&!fileName.equals("")){
				File file2 = new File(path + "/" + fileName+".lic");
				if (file2.exists()) {
					file2.delete();// 将原证书删掉
				}
			}
			// 创建license证书
			CreateLicense cLicense = new CreateLicense();
			// 获取参数
			String path2=ServletActionContext.getServletContext()
			.getRealPath("/")
			+ "upload/file/liclib/"
			+ licenseMsg.getCompanyUrl()
			+ ".lic";
			cLicense.setParam(path2, licenseMsg.getStartTime(), licenseMsg
					.getNotAfter(), licenseMsg.getCompanyName() + "的license证书");
			// 生成证书
			cLicense.create();
			successMessage = "编辑成功！";
			return this.showLicenseMsgs();
		} else {
			errorMessage = "编辑失败！";
			return toupdate();
		}

	}
	
	// get和set方法

	public String getCpage() {
		return cpage;
	}

	public LicenseMsgServer getLicenseMsgServer() {
		return licenseMsgServer;
	}

	public void setLicenseMsgServer(LicenseMsgServer licenseMsgServer) {
		this.licenseMsgServer = licenseMsgServer;
	}

	public LicenseMsg getLicenseMsg() {
		return licenseMsg;
	}

	public void setLicenseMsg(LicenseMsg licenseMsg) {
		this.licenseMsg = licenseMsg;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<LicenseMsg> getComList() {
		return comList;
	}

	public void setComList(List<LicenseMsg> comList) {
		this.comList = comList;
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

	public List<LicenseMsg> getEnddingList() {
		return enddingList;
	}

	public void setEnddingList(List<LicenseMsg> enddingList) {
		this.enddingList = enddingList;
	}

	public List<LicenseMsg> getEndList() {
		return endList;
	}

	public void setEndList(List<LicenseMsg> endList) {
		this.endList = endList;
	}



}
