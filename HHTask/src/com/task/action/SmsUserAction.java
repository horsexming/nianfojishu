package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.task.Server.SmsUserService;
import com.task.entity.DataGrid;
import com.task.entity.VSmsUser;
import com.task.util.MKUtil;

public class SmsUserAction extends ActionSupport implements ModelDriven<VSmsUser> {
	private VSmsUser smsUser = new VSmsUser();
	private SmsUserService smsUserService;

	public String datagrid(){
		DataGrid dg = smsUserService.toDataGrid(smsUser);
		MKUtil.writeJSON(dg);
		return null;
	}
	
	public String add(){
		try {
			smsUserService.save(smsUser);
			MKUtil.writeJSON(true,"添加成功",null);
		} catch (Exception e) {
			MKUtil.writeJSON(false,"添加失败:" + e.getMessage(),null);
		}
		
		return null;
	}
	
	public String edit(){
		try {
			smsUserService.update(smsUser);
			MKUtil.writeJSON(true,"修改成功",null);
		} catch (Exception e) {
			MKUtil.writeJSON(false,"修改失败:" + e.getMessage(),null);
		}
		return null;
	}
	
	public String delete(){
		try {
			smsUserService.delete(smsUser);
			MKUtil.writeJSON(true,"删除成功",null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false,"删除失败:" + e.getMessage(),null);
		}
		return null;
	}
	
	public String getUser(){
		return null;
	}

	@Override
	public VSmsUser getModel() {
		return smsUser;
	}

	public VSmsUser getSmsUser() {
		return smsUser;
	}

	public void setSmsUser(VSmsUser smsUser) {
		this.smsUser = smsUser;
	}

	public SmsUserService getSmsUserService() {
		return smsUserService;
	}

	public void setSmsUserService(SmsUserService smsUserService) {
		this.smsUserService = smsUserService;
	}
	
}
