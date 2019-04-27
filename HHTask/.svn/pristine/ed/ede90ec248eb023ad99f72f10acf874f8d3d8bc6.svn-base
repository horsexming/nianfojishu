package com.task.action.sop.muju;

import java.io.File;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.sop.muju.MouldApplyOrderServer;
import com.task.entity.Price;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.muju.MouldApplyOrder;
import com.task.entity.sop.muju.MouldDetail;
import com.task.entity.sop.muju.MouldPingGu;
import com.task.util.MKUtil;

public class MouldApplyOrderAction {

	private MouldApplyOrderServer maoserver;
	private MouldApplyOrder mao;
	private MouldDetail md;
	private MouldPingGu mpg;
	private List<MouldApplyOrder> maoList;
	private List<MouldDetail> mdList;
	private List<MouldPingGu> mpgList;
	private List<Price> priceList;
	private List<String> strList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer id2;// id
	private String pageStatus;// 页面状态
	private String planNumber;
	private String markIdS;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String [] otherNames;
	//添加开模申请单
	public String addmao(){
		errorMessage =maoserver.addMoa(mao, attachment, attachmentFileName, otherNames);
		if("true".equals(errorMessage)){
			return "findmaoList";
		}
		return "mao_add";
	}
	//查询开模申请
	public String findmaoList(){
		if (mao != null) {
			ActionContext.getContext().getSession().put("mao", mao);
		} else {
			mao = (MouldApplyOrder) ActionContext.getContext().getSession()
					.get("mao");
		}
		Object[] obj =	maoserver.findMoaList(mao, Integer.parseInt(cpage), pageSize, pageStatus);
		maoList = (List<MouldApplyOrder>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("MouldApplyOrderAction_findmaoList.action?pageStatus="+pageStatus);
		return "mao_list";
	}
	//根据申请单id查询开模申请单和开模明细
	public String findMoaAndmdList(){
		Object [] obj= maoserver.findMoaAndmdList(id,id2);
		mao = (MouldApplyOrder) obj[0];
		mdList = (List<MouldDetail>) obj[1];
		mpgList = (List<MouldPingGu>) obj[2];
		strList = (List<String>) obj[3];
		if("caigou".equals(pageStatus)){
			priceList = maoserver.findPriceByNo(mao.getPlanNumber());
		}
		return "mao_show";
	}
	//删除开模明细
	public void delmd(){
		try {
			errorMessage =	maoserver.delmd(md);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询所有开模明细
	public String findmdList(){
		if (md != null) {
			ActionContext.getContext().getSession().put("md", md);
		} else {
			md = (MouldDetail) ActionContext.getContext().getSession()
					.get("md");
		}
		Object[] obj =	maoserver.findMdList(md, Integer.parseInt(cpage), pageSize, pageStatus);
		mdList = (List<MouldDetail>) obj[0];
		int count = (Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("MouldApplyOrderAction_findmdList.action?pageStatus="+pageStatus);
		return "md_list";
	}
	//修改开模申请单
	public String updateMoa(){
		try {
			errorMessage =	maoserver.updateMoa(mao, attachment, attachmentFileName, otherNames,pageStatus);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.toString();
		}
		if(errorMessage!="true") {
			return "error";
		}
		return "findmaoList";
	}
	//查询最大申请单号
	
	public void findMaxNo(){
		try {
			errorMessage =	maoserver.findMaxNo();
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//删除开模申请单
	public String delmao(){
		errorMessage =	maoserver.delMoa(mao);
		if("true".equals(errorMessage)){
			return "findmaoList";
		}
		errorMessage = "删除失败";
		return "error";
	}
	//查出所有已同意还没价格的申请单
	public void findmaoListNoPrice(){
		
		try {
			maoList = maoserver.findmaoListNoPrice();
			MKUtil.writeJSON(maoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//根据申请单号查询磨具申请单;
	public void findMaoOne(){
		try {
			mao = maoserver.findMaoOne(planNumber);
			MKUtil.writeJSON(mao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	//下发供应采购
	public String caigou(){
		errorMessage = maoserver.caigou(mao, id);
		return "error";
	}
	//根据件号获取所有未激活的数量
	public void getWtcNumber(){
		try {
			Integer wtcNumber =	maoserver.getWtcNumber(markIdS);
			MKUtil.writeJSON(wtcNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public MouldApplyOrderServer getMaoserver() {
		return maoserver;
	}
	public void setMaoserver(MouldApplyOrderServer maoserver) {
		this.maoserver = maoserver;
	}
	public MouldApplyOrder getMao() {
		return mao;
	}
	public void setMao(MouldApplyOrder mao) {
		this.mao = mao;
	}
	public MouldDetail getMd() {
		return md;
	}
	public void setMd(MouldDetail md) {
		this.md = md;
	}
	public MouldPingGu getMpg() {
		return mpg;
	}
	public void setMpg(MouldPingGu mpg) {
		this.mpg = mpg;
	}
	public List<MouldApplyOrder> getMaoList() {
		return maoList;
	}
	public void setMaoList(List<MouldApplyOrder> maoList) {
		this.maoList = maoList;
	}
	public List<MouldDetail> getMdList() {
		return mdList;
	}
	public void setMdList(List<MouldDetail> mdList) {
		this.mdList = mdList;
	}
	public List<MouldPingGu> getMpgList() {
		return mpgList;
	}
	public void setMpgList(List<MouldPingGu> mpgList) {
		this.mpgList = mpgList;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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
	public String[] getOtherNames() {
		return otherNames;
	}
	public void setOtherNames(String[] otherNames) {
		this.otherNames = otherNames;
	}
	public String getPlanNumber() {
		return planNumber;
	}
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	public List<Price> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public List<String> getStrList() {
		return strList;
	}
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public String getMarkIdS() {
		return markIdS;
	}
	public void setMarkIdS(String markIdS) {
		this.markIdS = markIdS;
	}
	
	
}
