package com.task.action.sop;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.SCTuiliaoSqDanServer;
import com.task.entity.sop.Procard;
import com.task.entity.sop.SCTuiliaoSqDan;
import com.task.util.MKUtil;

public class SCTuiliaoSqDanAction  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private SCTuiliaoSqDanServer sqdSever;
	private List<SCTuiliaoSqDan> sqdList;
	private SCTuiliaoSqDan sqd;
	private SCTuiliaoSqDan [] sqdArrays;
	private String [] examineLots;
	private Float [] llNumbers;
	private Float [] tlNumbers;
	private String markId;
	private String selfCard;
	private int id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	
	//查询所有
	public String findAllSctuiliaoSqDan(){
		if (sqd != null) {
			ActionContext.getContext().getSession().put("sqd", sqd);
		} else {
			sqd = (SCTuiliaoSqDan) ActionContext.getContext()
					.getSession().get("sqd");
		}
		Object[] object = sqdSever.findAllSctuiliaoSqDan(sqd, Integer
				.parseInt(cpage), pageSize,  pageStatus);
		if (object != null && object.length > 0) {
			sqdList = (List<SCTuiliaoSqDan>) object[0];
			if (sqdList != null && sqdList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("SCTuiliaoSqDanAction_findAllSctuiliaoSqDan.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "show_sqdList";
	}
	//添加
	public String addmoresqd(){
		sqdArrays = new SCTuiliaoSqDan [examineLots.length]; 
		for (int i = 0; i < sqdArrays.length; i++) {
			SCTuiliaoSqDan sqd1 = new SCTuiliaoSqDan();
			sqd1.setExamineLot(examineLots[i]);
			sqd1.setLlNumber(llNumbers[i]);
			sqd1.setTlNumber(tlNumbers[i]);
			sqdArrays[i]=sqd1;
		}
		errorMessage = sqdSever.addmoresqd(sqd, sqdArrays);
		if("true".equals(errorMessage)){
			return "findAllSctuiliaoSqDan";
		}
		return "error";
	}
	//根据Id获得
	public String findSctuiliaoSqDanById(){
		sqd = sqdSever.findSctuiliaoSqDanById(id);
		return "sqd_show";
	}
	//根据件号生产批次查询procard
	public void findProcardOne(){
		Procard procard = sqdSever.findProcardOne(markId, selfCard);
		MKUtil.writeJSON(procard);
	}
	//删除
	public String delSctuiliaoSqDan(){
		boolean bool = sqdSever.delSctuiliaoSqDan(sqd);
		if(bool){
			setUrl("SCTuiliaoSqDanAction_findAllSctuiliaoSqDan.action?pageStatus="+pageStatus+"&cpage="+cpage);
		}else{
			errorMessage = "删除失败!";
		}
		return "error";
	}
	public SCTuiliaoSqDanServer getSqdSever() {
		return sqdSever;
	}
	public void setSqdSever(SCTuiliaoSqDanServer sqdSever) {
		this.sqdSever = sqdSever;
	}
	public List<SCTuiliaoSqDan> getSqdList() {
		return sqdList;
	}
	public void setSqdList(List<SCTuiliaoSqDan> sqdList) {
		this.sqdList = sqdList;
	}
	public SCTuiliaoSqDan getSqd() {
		return sqd;
	}
	public void setSqd(SCTuiliaoSqDan sqd) {
		this.sqd = sqd;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public SCTuiliaoSqDan[] getSqdArrays() {
		return sqdArrays;
	}
	public void setSqdArrays(SCTuiliaoSqDan[] sqdArrays) {
		this.sqdArrays = sqdArrays;
	}
	public String[] getExamineLots() {
		return examineLots;
	}
	public void setExamineLots(String[] examineLots) {
		this.examineLots = examineLots;
	}
	public Float[] getLlNumbers() {
		return llNumbers;
	}
	public void setLlNumbers(Float[] llNumbers) {
		this.llNumbers = llNumbers;
	}
	public Float[] getTlNumbers() {
		return tlNumbers;
	}
	public void setTlNumbers(Float[] tlNumbers) {
		this.tlNumbers = tlNumbers;
	}
	
 
}
