package com.task.action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.DJNRServer;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.util.MKUtil;
import com.task.util.Util;

public class DJNRAction extends ActionSupport{
	
	
	private DJNR djnr;//点检内容表;
	private String nr;
	private List<DJNR> djnrList;
	private DJNRServer djnrServer;
	private Integer id;
	private String data;//日期;
	private List<DJNR> List;
	private List<Machine> bdList;
	private List<Machine> wbdList;
	private Integer size;
	
	
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	
	public String add(){
		if(	djnrServer.add(djnr)){
			errorMessage="添加成功";
			return "adddjnr";
		}
		errorMessage="添加失败";
		return ERROR;
	}
	public String del(){
		if(djnrServer.del(djnr)){
			errorMessage="添加成功";
			return "adddjnr";
		}
		errorMessage="删除失败";
		return ERROR;
	}
	
	public void delbd(){
		errorMessage="删除失败!";
		if(djnrServer.del(djnr)){
			errorMessage="删除成功!";
		}
		try {
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	public String update(){
		if(djnrServer.update(djnr)){
			errorMessage="修改成功";
			return "adddjnr";
		}
		errorMessage="修改失败";
		return ERROR;
	}
	public String findAlldjnr(){
		djnrList=djnrServer.findAllDJNR(Integer.parseInt(cpage), pageSize);
		
			if("bd".equals(pageStatus)){
				djnrList=djnrServer.getdjnrbyId(id);
				if(djnrList!=null&&djnrList.size()>0){
					size=djnrList.size();
				}
				
				return "findAlldjnrbd";
			}
			
		
		return "findAlldjnr";
	}
	public String initupdate(){
		djnr=djnrServer.finddjnrbyid(djnr);
		if(djnr!=null){
			return "initupdate";
		}
		return ERROR;
	}
	public String getdjnrbyId(){
		djnrList=djnrServer.getdjnrbyId(id);
		errorMessage="该设备没有绑定点检内容，请联系相关人员绑定。";
		if(djnrList!=null&&djnrList.size()>0){
			data=Util.getDateTime("yyyy年MM月dd日");
			errorMessage=null;
		}
				return "getdjnr";//
	}
	public String getdjnrbyId1(){
		djnrList=djnrServer.getdjnrbyId1(id);
		if(djnrList!=null&&djnrList.size()>0){
			try {
				MKUtil.writeJSON(djnrList);
			} catch (Exception e) {
				MKUtil.writeJSON(e);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public String bdmachine(){
		if(djnrServer.bdmachine(djnr)){
			errorMessage="绑定成功";
		}
		errorMessage="绑定失败";
		return ERROR;
	}
	public DJNR getDjnr() {
		return djnr;
	}
	public void setDjnr(DJNR djnr) {
		this.djnr = djnr;
	}
	public List<DJNR> getDjnrList() {
		return djnrList;
	}
	public void setDjnrList(List<DJNR> djnrList) {
		this.djnrList = djnrList;
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
	

	public DJNRServer getDjnrServer() {
		return djnrServer;
	}
	public void setDjnrServer(DJNRServer djnrServer) {
		this.djnrServer = djnrServer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<DJNR> getList() {
		return List;
	}
	public void setList(List<DJNR> list) {
		List = list;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	
	public List<Machine> getBdList() {
		return bdList;
	}
	public void setBdList(List<Machine> bdList) {
		this.bdList = bdList;
	}
	public List<Machine> getWbdList() {
		return wbdList;
	}
	public void setWbdList(List<Machine> wbdList) {
		this.wbdList = wbdList;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
