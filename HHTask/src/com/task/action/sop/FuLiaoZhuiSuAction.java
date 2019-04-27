package com.task.action.sop;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.sop.FuLiaoZhuiSuServer;
import com.task.entity.sop.FuLiaoZhuiSu;
import com.task.entity.sop.Procard;
import com.task.util.MKUtil;

public class FuLiaoZhuiSuAction {

	private	List<FuLiaoZhuiSu> flzslist;// 集合
	private FuLiaoZhuiSu flzs;
	private FuLiaoZhuiSuServer flzsServer;
	
	private String orderNum ;
	private String ywmarkId ;
	private	String successMessage;// 成功消息
	private	String errorMessage;// 错误消息
	private	Integer id;// id
	private	Integer[] ids;// ids
	private	String pageStatus;// 页面状态
	private	String startDate;// 开始时间
	private	String endDate;// 截止时间

	// 分页
	private	String cpage = "1";
	private	String total;
	private	String url;
	private	int pageSize = 15;
	
	
	public String addflzs(){
		errorMessage =	flzsServer.addflzs(flzs);
		return "flzs_add";
	}
	public String findAllflzs(){
		if (flzs != null) {
			ActionContext.getContext().getSession().put("FuLiaoZhuiSu", flzs);
		} else {
			flzs = (FuLiaoZhuiSu) ActionContext.getContext().getSession()
					.get("flzs");
		}
		Object[] obj = flzsServer.findAllflzs(flzs, Integer
				.parseInt(cpage), pageSize,pageStatus);
		flzslist = (List<FuLiaoZhuiSu>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("FuLiaoZhuiSuAction_findAllflzs.action");
		return "flzs_List";
	}
	public String delflzs(){
		errorMessage =	flzsServer.delflzs(flzs);
		if("true".equals(errorMessage)){
			errorMessage = "删除成功";
		}else{
			errorMessage = "删除失败";
		}
		return "findAllflzs";
	}
	/**
	 * 根据内部订单号查询业务件号
	 */
	public void getywmarkIds(){
		try {
			List<String> strList = flzsServer.getywmarkIds(orderNum);
			MKUtil.writeJSON(strList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据内部订单和业务件号查询Procard信息
	 * @return
	 */
	public void findProcardBy(){
		try {
		Procard procard= flzsServer.findProcardBy(orderNum,ywmarkId);
			MKUtil.writeJSON(procard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据工位查询获得设备编号
	 * @return
	 */
	public void getshebeiNo(){
		try {
			String shebeiNo= flzsServer.getshebeiNo(orderNum);
			MKUtil.writeJSON(shebeiNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String findflzsById(){
		flzs = flzsServer.findflzsById(id);
		return "flzs_show";
	}
	/**
	 * 修改flzs
	 * @return
	 */
	public String updateflzs(){
		errorMessage =	flzsServer.updateflzs(flzs);
		return "flzs_show";
	}
	/**
	 * 根据件号获得零件名称
	 * @return
	 * 
	 */
	public void getproName(){
		try {
			String  proName =	flzsServer.getproName(orderNum);
			MKUtil.writeJSON(proName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<FuLiaoZhuiSu> getFlzslist() {
		return flzslist;
	}
	public void setFlzslist(List<FuLiaoZhuiSu> flzslist) {
		this.flzslist = flzslist;
	}
	public FuLiaoZhuiSu getFlzs() {
		return flzs;
	}
	public void setFlzs(FuLiaoZhuiSu flzs) {
		this.flzs = flzs;
	}
	public FuLiaoZhuiSuServer getFlzsServer() {
		return flzsServer;
	}
	public void setFlzsServer(FuLiaoZhuiSuServer flzsServer) {
		this.flzsServer = flzsServer;
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
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	
	
	
	
}
