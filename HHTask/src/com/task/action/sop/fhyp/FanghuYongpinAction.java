package com.task.action.sop.fhyp;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.sop.fhyp.FanghuYongpinServer;
import com.task.entity.Users;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.sop.fhyp.FanghuYongpin;
import com.task.entity.sop.fhyp.FanghuYongpinQuanxian;
import com.task.util.MKUtil;

public class FanghuYongpinAction extends ActionSupport{
	/**防护用品*/
	private FanghuYongpin fanghuYongpin;
	private List<FanghuYongpin> fanghuYongpinList;
	private List<FanghuYongpin> fanghuYongpinGuigeList;
	
	private FanghuYongpinQuanxian fanghuYongpinQuanxian;
	private List<FanghuYongpinQuanxian> fanghuYongpinQuanxianList;
	private FanghuYongpinServer fanghuYongpinServer;
	private UserServer userServer;
	private String errorMessage;
	private String successMessage;
	/**分页*/
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	
	/**
	 * 获得添加防护用品page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinAddPage(){
		return "getFanghuYongpinAddPage_success";
	}
	
	/**
	 * 添加防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String addFanghuYongpin(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.addFanghuYongpin(this.fanghuYongpin);
		return "addFanghuYongpin_success";
	}
	/**
	 * 删除防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String deleteFanghuYongpin(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.deleteFanghuYongpin(this.fanghuYongpin);
		return "deleteFanghuYongpin_success";
	}
	
	/**
	 * 获得更新防护用品page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinUpdatePage(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.getFanghuYongpinById(this.fanghuYongpin.getId());
		this.fanghuYongpin=fanghuYongpin;
		return "getUpdateFanghuYongpinPage_success";
	}
	/**
	 * 更新防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateFanghuYongpin(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.updateFanghuYongpin(this.fanghuYongpin);
		return "updateFanghuYongpin_success";
	}
	
	/**
	 * 获得防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String  getFanghuYongpinById(Integer id){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.getFanghuYongpinById(this.fanghuYongpin.getId());
		MKUtil.writeJSON(true, "操作成功", fanghuYongpin);
		return null;
	}
	/**
	 * 获得防护用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinLeibieList(){
		Object[] object = this.fanghuYongpinServer.getFanghuYongpinList(this.fanghuYongpin, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.fanghuYongpinList = (List<FanghuYongpin>) object[0];
			if (this.fanghuYongpinList != null && this.fanghuYongpinList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("fanghuYongpinAction!getFanghuYongpinList.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else{
			errorMessage = null;
		}
		return "getFanghuYongpinLeibieList_success";
	}
	
	/**
	 * 获得添加防护用品规格page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinGuigeAddPage(){
		return "getFanghuYongpinGuigeAddPage_success";
	}
	
	/**
	 * 添加防护用品规格
	 * @param fanghuYongpin
	 * @return
	 */
	public String addFanghuYongpinGuige(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.addFanghuYongpin(this.fanghuYongpin);
		return "addFanghuYongpinGuige_success";
	}
	/**
	 * 删除防护用品规格
	 * @param fanghuYongpin
	 * @return
	 */
	public String deleteFanghuYongpinGuige(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.deleteFanghuYongpin(this.fanghuYongpin);
		return "deleteFanghuYongpinGuige_success";
	}
	/**
	 * 获得添加防护用品规格page
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinGuigeUpdatePage(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.getFanghuYongpinById(this.fanghuYongpin.getId());
		this.fanghuYongpin=fanghuYongpin;
		return "getFanghuYongpinGuigeUpdatePage_success";
	}
	/**
	 * 更新防护用品规格
	 * @param fanghuYongpin
	 * @return
	 */
	public String updateFanghuYongpinGuige(){
		FanghuYongpin fanghuYongpin=this.fanghuYongpinServer.updateFanghuYongpin(this.fanghuYongpin);
		return "updateFanghuYongpinGuige_success";
	}
	/**
	 * 获得添加防护用品规格集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinGuigeListByParentId(){
		this.fanghuYongpinGuigeList=(List<FanghuYongpin>)this.fanghuYongpinServer.getFanghuYongpinGuigeListByParentId(this.fanghuYongpin.getParentId());
		return "getFanghuYongpinGuigeListByParentId_success";
	}
	/**
	 * 获得防护用品
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinQuanxianAddPage(){
		
		return null;
	}
	
	/**
	 * 获得防护用品集合
	 * @param fanghuYongpin
	 * @return
	 */
	public String getFanghuYongpinQuanxianUserList(){
		Object[] object = this.fanghuYongpinServer.getFanghuYongpinQuanxianList(this.fanghuYongpinQuanxian, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.fanghuYongpinQuanxianList = (List<FanghuYongpinQuanxian>) object[0];
			if (this.fanghuYongpinQuanxianList != null && this.fanghuYongpinQuanxianList.size() > 0) {
				for(FanghuYongpinQuanxian fanghuYongpinQuanxian: this.fanghuYongpinQuanxianList){
					Users lingquUser=userServer.findUserById(fanghuYongpinQuanxian.getLingquUserId());
					fanghuYongpinQuanxian.setLingquUser(lingquUser);
				}
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("fanghuYongpinAction!getFanghuYongpinQuanxianUserList.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else{
			errorMessage = null;
		}
		return "getFanghuYongpinQuanxianUserList_success";
	}
	
	public String getFanghuYongpinListForSelect(){
		List<FanghuYongpin> fanghuYongpinList=(List<FanghuYongpin>)this.fanghuYongpinServer.getFanghuYongpinListForSelect();
		
		if(fanghuYongpinList!=null && !fanghuYongpinList.isEmpty()){
			MKUtil.writeJSON(true, "操作成功", fanghuYongpinList);
		}else{
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}
	
	public String getFanghuYongpinGuigeListForSelect(){
		List<FanghuYongpin> fanghuYongpinList=(List<FanghuYongpin>)this.fanghuYongpinServer.getFanghuYongpinGuigeListByParentId(this.fanghuYongpin.getParentId());
		if(fanghuYongpinList!=null && !fanghuYongpinList.isEmpty()){
			MKUtil.writeJSON(true, "操作成功", fanghuYongpinList);
		}else{
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
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
	public FanghuYongpin getFanghuYongpin() {
		return fanghuYongpin;
	}
	public void setFanghuYongpin(FanghuYongpin fanghuYongpin) {
		this.fanghuYongpin = fanghuYongpin;
	}
	public void setFanghuYongpinList(List<FanghuYongpin> fanghuYongpinList) {
		this.fanghuYongpinList = fanghuYongpinList;
	}
	public FanghuYongpinServer getFanghuYongpinServer() {
		return fanghuYongpinServer;
	}
	public void setFanghuYongpinServer(FanghuYongpinServer fanghuYongpinServer) {
		this.fanghuYongpinServer = fanghuYongpinServer;
	}
	public List<FanghuYongpinQuanxian> getFanghuYongpinQuanxianList() {
		return fanghuYongpinQuanxianList;
	}
	public void setFanghuYongpinQuanxianList(List<FanghuYongpinQuanxian> fanghuYongpinQuanxianList) {
		this.fanghuYongpinQuanxianList = fanghuYongpinQuanxianList;
	}
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public List<FanghuYongpin> getFanghuYongpinList() {
		return fanghuYongpinList;
	}

	public List<FanghuYongpin> getFanghuYongpinGuigeList() {
		return fanghuYongpinGuigeList;
	}

	public void setFanghuYongpinGuigeList(List<FanghuYongpin> fanghuYongpinGuigeList) {
		this.fanghuYongpinGuigeList = fanghuYongpinGuigeList;
	}
	
}
