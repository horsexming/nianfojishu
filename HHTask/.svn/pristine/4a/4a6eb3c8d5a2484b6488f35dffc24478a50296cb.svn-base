package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.task.Server.SmsGroupService;
import com.task.entity.SmsGroup;
import com.task.entity.VSmsGroup;
import com.task.util.MKUtil;

public class SmsGroupAction extends ActionSupport implements ModelDriven<SmsGroup> {
	private SmsGroupService smsGroupService;
	private SmsGroup smsGroup = new SmsGroup();
	
	public String index(){
		return SUCCESS;
	}

	public String add() {
		if(smsGroupService.add(smsGroup)){
			MKUtil.writeJSON(true, "添加成功", null);
		} else {
			MKUtil.writeJSON(false, "添加失败", null);
		}
		return null;
	}
	
	public String update(){
		if(smsGroupService.update(smsGroup)){
			MKUtil.writeJSON(true, "更新成功", null);
		} else {
			MKUtil.writeJSON(false, "更新失败", null);
		}
		return null;
	}

	public String delete(){
		if(smsGroupService.delete(smsGroup)){
			MKUtil.writeJSON(true, "删除成功", null);
		} else {
			MKUtil.writeJSON(false, "删除失败", null);
		}
		return null;
	}
	
	public String getAll(){
		try {
			List<VSmsGroup> groups = smsGroupService.getAll();
			MKUtil.writeJSON(true, null, groups);
		} catch (Exception e) {
			MKUtil.writeJSON(true, "获取分组失败:" + e.getMessage(), null);
		}
		
		return null;
	}
	
	public String list(){
		List<VSmsGroup> groups = smsGroupService.findAll();
		MKUtil.writeJSON(groups);
		return null;
	}
	
	public SmsGroupService getSmsGroupService() {
		return smsGroupService;
	}

	public void setSmsGroupService(SmsGroupService smsGroupService) {
		this.smsGroupService = smsGroupService;
	}

	@Override
	public SmsGroup getModel() {
		return smsGroup;
	}
	
}
