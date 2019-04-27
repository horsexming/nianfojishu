package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MachineDayDjServer;
import com.task.entity.DJNR;
import com.task.entity.MachineDayDj;
import com.task.util.MKUtil;

public class MachineDayDjAction extends ActionSupport{
	private MachineDayDjServer mddserver;
	private List<MachineDayDj> mddList;
	private MachineDayDj mdd;//设备日点检表;
	private Integer id;//设备id;
	private String month;//月份
	private List<DJNR> djnrList;
	private String machine_djnr;
	
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	
	public String add(){
		if(mdd!=null){
			if(mddserver.add(mdd)){
				errorMessage="添加成功";
				return "addmdd";
			}
		}
		errorMessage="添加失败";
		return ERROR;
	}
	public String del(){
		if(mdd!=null){
			if(mddserver.del(mdd)){
				errorMessage="删除成功";
				return "addmdd";
			}
		}
		errorMessage="删除失败";
		return ERROR;
	}
	public String update(){
		if(mdd!=null){
			if(mddserver.update(mdd)){
				errorMessage="更新成功";
				return "addmdd";
			}
		}
		errorMessage="更新失败";
		return ERROR;
	}
	//条件查询(分页)
	public String findMDDByCondition(){
		if(mdd!=null){
			ActionContext.getContext().getSession().put("mdd", mdd);
		}else{
			mdd=(MachineDayDj) ActionContext.getContext().getSession().get("buhegepin");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		
		map=mddserver.findMDDByCondition(mdd, Integer.parseInt(cpage), pageSize);
		mddList=(List<MachineDayDj>) map.get(1);
		if(mddList!=null && mddList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("MachineDayDjAction_findMDDByCondition.action?statue=find");
			return "BuHeGePin_findAll";
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
			return ERROR;
		}
	}
	//根据月份查出某个设备某个月份每天点检(不分页)
	public String findmddbymonth(){
		mddList=mddserver.findmddbymonth(id, month);
		if(mddList!=null&&mddList.size()>0){
			return "findmddbymonth";
		}
		errorMessage="没有找到你要查询的内容，请检查后重试";
		return ERROR;
	}
	//根据设备Id,点检内容,查询出当月每天该内容所对应的状态
	public void findmonthstatus(){
		mddList=mddserver.findmonthstatus(machine_djnr, month, id);
		try {
			MKUtil.writeJSON(mddList);
		} catch (Exception e) {
			MKUtil.writeJSON(mddList);
		}
	}
	
	//由日运行时长计算月运行时长，计算月稼动率;
	public String jisuantest(){
		mddserver.jisuantest();
		return "error";
	}
	
	
	public MachineDayDj getMdd() {
		return mdd;
	}
	public void setMdd(MachineDayDj mdd) {
		this.mdd = mdd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public MachineDayDjServer getMddserver() {
		return mddserver;
	}

	public void setMddserver(MachineDayDjServer mddserver) {
		this.mddserver = mddserver;
	}
	public List<MachineDayDj> getMddList() {
		return mddList;
	}
	public void setMddList(List<MachineDayDj> mddList) {
		this.mddList = mddList;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public List<DJNR> getDjnrList() {
		return djnrList;
	}
	public void setDjnrList(List<DJNR> djnrList) {
		this.djnrList = djnrList;
	}
	public String getMachine_djnr() {
		return machine_djnr;
	}
	public void setMachine_djnr(String machineDjnr) {
		machine_djnr = machineDjnr;
	}
	
}
