package com.task.action.bybz;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.bybz.CheckoutAndGagesServer;
import com.task.entity.Users;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.bybz.CheckoutAndGages;
import com.task.entity.bybz.LjuCheckRecord;
import com.task.entity.bybz.LjuCheckRecordMx;
import com.task.entity.sop.BuHeGePin;
import com.task.util.MKUtil;

/**
 * 
 * @author 王晓飞
 *
 */
public class CheckoutAndGagesAction {
	
	private CheckoutAndGages cag;
	private List<CheckoutAndGages> cagList;
	private BaoYangBiaoZhun bybz;
	private List<BaoYangBiaoZhun> bybzList;
	private LjuCheckRecord lcr;
	private List<LjuCheckRecord> lcrList;
	private LjuCheckRecordMx lcrMx;
	private List<LjuCheckRecordMx> lcrMxlist;
	private CheckoutAndGagesServer cagServer;
	private String code;
	
	private String errorMessage;
	private String successMessage;
	private int pageSize = 15;
	private int id;

	private String cpage = "1";
	private String total;
	private String url;
	private String status = "";
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	//添加检、量具
	public String addcag(){
		errorMessage =	cagServer.addCheckoutAndGage(cag);
		return "findAllcagList";
	}
	//查询所有检、量具
	public String findAllcagList(){
		if (cag != null) {
			ActionContext.getContext().getSession().put("cag", cag);
		} else {
			cag = (CheckoutAndGages) ActionContext.getContext().getSession()
					.get("cag");
		}

		Object[] obj = cagServer.findAllcagList(cag, Integer
				.parseInt(cpage), pageSize,status);
		cagList = (List<CheckoutAndGages>) obj[0];
		if(cagList!=null && cagList.size()>0){
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckoutAndGagesAction_findAllcagList.action?status="+status);
		}
		return "cag_showList";
	}
	//根据量、检具Id查询量、检具和校验标准
	public String findcagById(){
		cag =cagServer.findCagById(id);
		bybzList = cagServer.findbybzListBycagId(id);
		if("jiaoyan".equals(status)){
			return "cag_jiaoyan";
		}
		return "cag_show";
	}
	//修改量、检具
	public String updatecag(){
		errorMessage = cagServer.updateCag(cag);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功";
		}else{
			errorMessage = "修改失败";
		}
		return "findcagById";
	}
	//校验量、检具
	public String jYCag(){
		if(attachment!=null && attachment.length>0){
			String fileName =	MKUtil.saveFile(attachment, attachmentFileName, "LjuCheckRecord");
			lcr.setFileName(fileName);
		}
		errorMessage = cagServer.jYCag(lcr);
		return "findAllcagList";
	}
	//根据工号查询Users
	public void findUsersByCode(){
		try {
			Users user = cagServer.findUsersByCode(code);
			MKUtil.writeJSON(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询所有量、检具校验记录;
	public String findAlllcrList(){
		if (lcr != null) {
			ActionContext.getContext().getSession().put("lcr", lcr);
		} else {
			lcr = (LjuCheckRecord) ActionContext.getContext().getSession()
					.get("lcr");
		}

		Object[] obj = cagServer.findLcrList(lcr, Integer
				.parseInt(cpage), pageSize,status);
		lcrList = (List<LjuCheckRecord>) obj[0];
		if(lcrList!=null && lcrList.size()>0){
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("CheckoutAndGagesAction_findAlllcrList.action?status="+status);
		}
		return "lcr_showList";
	}
	//根据校验记录Id查询校验项;
	public String findlcrlcrMxlist(){
		lcrMxlist = cagServer.findLcrMxList(id);
		return "lcrMx_showList";
	}
	public CheckoutAndGages getCag() {
		return cag;
	}
	public void setCag(CheckoutAndGages cag) {
		this.cag = cag;
	}
	public List<CheckoutAndGages> getCagList() {
		return cagList;
	}
	public void setCagList(List<CheckoutAndGages> cagList) {
		this.cagList = cagList;
	}
	public BaoYangBiaoZhun getBybz() {
		return bybz;
	}
	public void setBybz(BaoYangBiaoZhun bybz) {
		this.bybz = bybz;
	}
	public List<BaoYangBiaoZhun> getBybzList() {
		return bybzList;
	}
	public void setBybzList(List<BaoYangBiaoZhun> bybzList) {
		this.bybzList = bybzList;
	}
	public LjuCheckRecord getLcr() {
		return lcr;
	}
	public void setLcr(LjuCheckRecord lcr) {
		this.lcr = lcr;
	}
	public List<LjuCheckRecord> getLcrList() {
		return lcrList;
	}
	public void setLcrList(List<LjuCheckRecord> lcrList) {
		this.lcrList = lcrList;
	}
	public LjuCheckRecordMx getLcrMx() {
		return lcrMx;
	}
	public void setLcrMx(LjuCheckRecordMx lcrMx) {
		this.lcrMx = lcrMx;
	}
	public List<LjuCheckRecordMx> getLcrMxlist() {
		return lcrMxlist;
	}
	public void setLcrMxlist(List<LjuCheckRecordMx> lcrMxlist) {
		this.lcrMxlist = lcrMxlist;
	}
	public CheckoutAndGagesServer getCagServer() {
		return cagServer;
	}
	public void setCagServer(CheckoutAndGagesServer cagServer) {
		this.cagServer = cagServer;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
