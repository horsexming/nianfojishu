package com.task.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.CompanyVIPServer;
import com.task.entity.CompanyBoss;
import com.task.entity.CompanyVIP;
import com.task.entity.sop.BuHeGePin;

public class CompanyVIPAction extends ActionSupport {

	private CompanyVIPServer cpserver;
	private CompanyVIP companvip;
	private List<CompanyVIP> companvipList;
	private CompanyBoss cb;
	private Integer id;
	private String campanyname;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String fatherPartNumber = "";
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String statue;

	// 初始化添加页面
	public String initadd() throws UnsupportedEncodingException {
		if ("adderror".equals(errorMessage)) {
			errorMessage = "申请企业会员失败!";
		}
		if(campanyname!=null && !"".equals(campanyname)){
			campanyname =	URLEncoder.encode(URLEncoder.encode(campanyname,"utf-8"),"utf-8");
		}
		companvip = new CompanyVIP();
		companvip.setVipNo(cpserver.findMaxvipNO());
		return "ComanyVIPadd1";
	}

	// 添加企业会员
	public String add() {
		boolean bool = cpserver.add(companvip);
		if (bool) {
			errorMessage = "true";
			return "addsuccess";
		}
		errorMessage = "添加失败";
		return ERROR;
	}

	// 查找所有企业会员(分页)
	public String showcompanvipList() {
		if ("del_true".equals(errorMessage)) {
			errorMessage = "删除成功!";
		} else if ("update_true".equals(errorMessage)) {
			errorMessage = "修改成功!";
		}
		int count = cpserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		companvipList = cpserver.FindAllCompanyVIP(Integer.parseInt(cpage),
				pageSize);
		companvip = null;
		if (companvipList != null) {
			this.setUrl("CompanyVIPAction_showcompanvipList.action");
			return "companvip_showlist";
		}
		errorMessage = "暂时还没有不合格品";
		return ERROR;
	}

	// 查找企业会员(按条件)
	public String findcompanvipList() {
		if (companvip != null) {
			ActionContext.getContext().getSession().put("companvip", companvip);
		} else {
			companvip = (CompanyVIP) ActionContext.getContext().getSession()
					.get("companvip");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		map = cpserver.findCompanyVIPCondition(companvip, Integer
				.parseInt(cpage), pageSize);
		companvipList = (List<CompanyVIP>) map.get(1);
		if (companvipList != null && companvipList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CompanyVIPAction_findcompanvipList.action");
			return "companvip_showlist";
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
			return ERROR;
		}
	}

	// 根据id查找企业会员
	public String findcompanvipById() {
		companvip = cpserver.findCompanyVIPById(id);
		if (companvip != null) {
			if ("update".equals(statue)) {
				return "companvip_update";
			}
			return "companvip_show";
		}
		return "";
	}

	// 根据id查找企业会员的企业负责人
	public String findcbById() {
		cb = cpserver.findCompanyBossById(id);
		if (cb != null) {
			return "companyBoss_show";
		}
		return "";
	}

	public String updateComanyVIP() {
		if (companvip != null) {
			if (cpserver.update(companvip, attachment, attachmentFileName,
					fatherPartNumber)) {
				errorMessage = "update_true";
				return "updateComanyVIP";
			}
		}
		errorMessage = "修改失败";
		return ERROR;
	}

	public String delComanyVIP() {
		if (companvip != null) {
			if (cpserver.del(companvip)) {
				errorMessage = "del_true";
				return "delcompanvip";
			}
		}
		errorMessage = "删除失败";
		return ERROR;
	}

	// 根据会员编号查找企业会员;
	public String showComanyVIPbyvipNO() {
		if ("true".equals(errorMessage)) {
			companvip = cpserver.showComanyVIPbyvipNO(companvip.getVipNo());
			errorMessage = companvip.getName() + "申请企业会员成功!请及时完善企业资料!";
			return "ComanyVIPadd";
		} else {
			errorMessage = "adderror";
			return "adderror";
		}

	}

	public CompanyVIPServer getCpserver() {
		return cpserver;
	}

	public void setCpserver(CompanyVIPServer cpserver) {
		this.cpserver = cpserver;
	}

	public CompanyVIP getCompanvip() {
		return companvip;
	}

	public void setCompanvip(CompanyVIP companvip) {
		this.companvip = companvip;
	}

	public List<CompanyVIP> getCompanvipList() {
		return companvipList;
	}

	public void setCompanvipList(List<CompanyVIP> companvipList) {
		this.companvipList = companvipList;
	}

	public CompanyBoss getCb() {
		return cb;
	}

	public void setCb(CompanyBoss cb) {
		this.cb = cb;
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

	public String getFatherPartNumber() {
		return fatherPartNumber;
	}

	public void setFatherPartNumber(String fatherPartNumber) {
		this.fatherPartNumber = fatherPartNumber;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCampanyname() {
		return campanyname;
	}

	public void setCampanyname(String campanyname) {
		this.campanyname = campanyname;
	}

}
