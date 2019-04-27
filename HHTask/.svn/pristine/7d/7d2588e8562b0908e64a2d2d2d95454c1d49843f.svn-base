package com.task.action.payment;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.payment.SignatureServer;
import com.task.entity.approval.Signature;
import com.task.entity.payment.PaymentDetail;
import com.task.util.Upload;
import com.task.util.Util;

public class SignatureAction  extends ActionSupport{
	private Signature signature;
	private SignatureServer signatureServer;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List list;
	private List<Map> maps;
	private String errorMessage;
	//上传电子签名
	private  File  signature_address;
	private String signature_addressFileName;
	private String signature_addressContextType;
	private Integer del_id;
	private Integer sal_id;
	private String test;
	

	//查询所有电子签名
	public String findSignature() {
		if (signature != null) {
			ActionContext.getContext().getSession().put("signature", signature);
		} else if(test==null){
			signature = (Signature) ActionContext.getContext().getSession()
					.get("signature");
		}
		Object[] object = this.signatureServer.findSignature(signature, Integer
				.parseInt(cpage), pageSize,test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("signatureAction_findSignature.action?test="+test);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findSignature";
	}
	
	//查看历史电子签名
	public String findHistorySignature(){
		if (signature != null) {
			ActionContext.getContext().getSession().put("signature", signature);
		} else{
			signature = (Signature) ActionContext.getContext().getSession().get("signature");
		}
		Object[] object = this.signatureServer.findHistorySignature(signature, Integer
				.parseInt(cpage), pageSize,test);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("signatureAction_findHistorySignature.action?test="+test);
			}
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findHistorySignature";
	}
	
	
	//上传电子签名
	public String addSignature(){
		if(this.signature_address!=null){
			//文件路径
			String fileType  = signature_addressFileName.substring(signature_addressFileName.lastIndexOf("."), signature_addressFileName.length());
			String realFileName=Util.getDateTime("yyyyMMddHHmmss")+fileType;
			String realFilePath="/upload/signature";
			Upload upload=new Upload();
			upload.UploadFile(signature_address, signature_addressFileName, realFileName, realFilePath, null);
			if(signature==null){
				signature=new Signature();
			}
			this.signature.setSignature_address("/upload/signature/"+realFileName);
		}
		this.signatureServer.saveSignature(this.signature,this.test);
		errorMessage="添加成功!";
		return "addSignature";
	}
	//根据编号查询电子签名
	public String findSignatureByid(){
		this.signature = this.signatureServer.findSignatureByid(this.sal_id);
		return "findSignatureByid";
	}
	
	//更新电子签名
	public String updateSignature(){
		if(this.signature_address!=null){
			//文件路径
			String fileType  = signature_addressFileName.substring(signature_addressFileName
					.lastIndexOf("."), signature_addressFileName.length());
			String realFileName=Util.getDateTime("yyyyMMddHHmmss")+fileType;
			String realFilePath="/upload/signature";
			Upload upload=new Upload();
			upload.UploadFile(signature_address, signature_addressFileName, realFileName, realFilePath, null);
			this.signature.setSignature_address("/upload/signature/"+realFileName);
		}
		this.signatureServer.updateSignature(this.signature);
		this.errorMessage="修改成功！";
		return "updateSignature";
	}
	
	//删除历史电子签名
	public String delSignature(){
		this.signatureServer.delSignature(this.del_id);
		return "delSignature";
	}
	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}

	public SignatureServer getSignatureServer() {
		return signatureServer;
	}

	public void setSignatureServer(SignatureServer signatureServer) {
		this.signatureServer = signatureServer;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public File getSignature_address() {
		return signature_address;
	}

	public void setSignature_address(File signatureAddress) {
		signature_address = signatureAddress;
	}

	public String getSignature_addressFileName() {
		return signature_addressFileName;
	}

	public void setSignature_addressFileName(String signatureAddressFileName) {
		signature_addressFileName = signatureAddressFileName;
	}

	public String getSignature_addressContextType() {
		return signature_addressContextType;
	}

	public void setSignature_addressContextType(String signatureAddressContextType) {
		signature_addressContextType = signatureAddressContextType;
	}

	public Integer getDel_id() {
		return del_id;
	}

	public void setDel_id(Integer delId) {
		del_id = delId;
	}

	public Integer getSal_id() {
		return sal_id;
	}

	public void setSal_id(Integer salId) {
		sal_id = salId;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

 

 

}
