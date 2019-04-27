package com.task.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TclaimformService;
import com.task.entity.Tclaimform;
import com.task.entity.TclaimsRecord;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class TclaimformAction extends ActionSupport {
	
	private TclaimformService tclaimformService;
	
	private Tclaimform claimform;
	private List<TclaimsRecord> records;
	private List<Tclaimform> tclaimforms;
	
	private String dept;
	
	private String errorMessage;
	private String successMessage;// 成功信息
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	public String addInput() {
		return "addInput";
	}
	
	public String add(){
		
		claimform.setRecords(new HashSet<TclaimsRecord>(records));

		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		claimform.setUsername(user.getName());

		tclaimformService.add(claimform);
		
		return "addOk";
	}
	
	public String list(){
		Object[] object = tclaimformService.list(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			tclaimforms = (List<Tclaimform>) object[0];
			if (tclaimforms != null && tclaimforms.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("tclaimform_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";
	}
	
	public String updateInput(){
		claimform = tclaimformService.get(claimform);
		return "updateInput";
	}
	
	public String update(){
		try {
			tclaimformService.update(claimform);
			MKUtil.writeJSON(true, "修改成功!", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败:" + e.getMessage(), null);
			
		}
		return null;
	}
	
	public String delete(){
		try {
			tclaimformService.delete(claimform);
			MKUtil.writeJSON(true, "删除成功!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String notification(){
		try {
			tclaimformService.updateNotification(claimform);
			MKUtil.writeJSON(true, "添加通知成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加通知失败:" + e.getMessage(), null);
		}
		return null;
	}
	
	public String debitInput (){
		tclaimformService.get(claimform);
		return "debitInput";
	}
	
	public String debit() {
		try {
			tclaimformService.updateDebit(claimform);
			MKUtil.writeJSON(true, "扣款成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "扣款失败:" + e.getMessage(), null);
		}
		return null;
	}

	public TclaimformService getTclaimformService() {
		return tclaimformService;
	}

	public void setTclaimformService(TclaimformService tclaimformService) {
		this.tclaimformService = tclaimformService;
	}

	public Tclaimform getClaimform() {
		return claimform;
	}

	public void setClaimform(Tclaimform claimform) {
		this.claimform = claimform;
	}

	public List<TclaimsRecord> getRecords() {
		return records;
	}

	public void setRecords(List<TclaimsRecord> records) {
		this.records = records;
	}

	public List<Tclaimform> getTclaimforms() {
		return tclaimforms;
	}

	public void setTclaimforms(List<Tclaimform> tclaimforms) {
		this.tclaimforms = tclaimforms;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
}
