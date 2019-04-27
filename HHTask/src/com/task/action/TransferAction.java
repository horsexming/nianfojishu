package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TransferServer;
import com.task.entity.Careertrack;
import com.task.entity.Transfer;

public class TransferAction extends ActionSupport{
	
	private Transfer transfer;//
	private TransferServer transfersever;
	private List<Transfer> trList;
	private Integer id;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	
	//根据userId 查询 个人调动信息;
	public String showtransfer(){
		if(id!=null && id>0){
			trList = transfersever.findTransferbyuserId(id);
			if(trList!=null && trList.size()>0){
				errorMessage = "";
			}else{
				errorMessage = "该员工目前尚未有过调动";
			}
		}
		return "transferList";
	}
	
	//添加
	public String addtransfer(){
		if(transfer!=null){
			errorMessage = transfersever.add(transfer);
			if("true".equals(errorMessage)){
				errorMessage = "";
				return "showtransfer";
			}
		}
			return "transferadd";
	}
	//删除
	public String deltransfer(){
		if(transfer!=null){
			if(transfersever.del(transfer)){
				errorMessage = "del_true";
			}else{
				errorMessage = "del_error";
			}
		}
		
		return "showtransfer";
	}
	//修改
	public String upadtetransfer(){
		if(transfer!=null){
			if(transfersever.update(transfer)){
				return "showtransfer";
			}
		}
		return ERROR;
		
	}
	//根据id 获得
	public String showtransferbyId(){
		if(id!=null && id>0){
			transfer =transfersever.findTransferbyId(id);
			return "updatetransfer";
		}
		return "";
	}
	//查询所有分页
	public String showtransferList(){
		if("del_true".equals(errorMessage)){
			errorMessage = "删除成功!";
		}else if("del_error".equals(errorMessage)){
			errorMessage = "删除失败!";
		}
		int count=transfersever.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		trList=transfersever.FindAllTransfer(Integer.parseInt(cpage), pageSize);
		if(trList!=null){
			this.setUrl("TransferAction_showtransferList.action");
			
		}else{
			errorMessage="没有人员调动信息";
		}
		return "transferList";
	}
	//条件查询
	public String findtransferList(){
		if(transfer!=null){
			ActionContext.getContext().getSession().put("transfer", transfer);
		}else{
			transfer=(Transfer) ActionContext.getContext().getSession().get("transfer");
		}
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		
		map=transfersever.findTransferByCondition(transfer, Integer.parseInt(cpage), pageSize);
		trList=(List<Transfer>) map.get(1);
		if(trList!=null && trList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			if(transfer!=null && transfer.getUserId()!=null){
				id = transfer.getUserId();
			}
			this.setUrl("TransferAction_findtransferList.action?status="+status+"&id="+id);
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		
		return "transferList";
	}
	
	
	public Transfer getTransfer() {
		return transfer;
	}
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	public TransferServer getTransfersever() {
		return transfersever;
	}
	public void setTransfersever(TransferServer transfersever) {
		this.transfersever = transfersever;
	}
	
	public List<Transfer> getTrList() {
		return trList;
	}

	public void setTrList(List<Transfer> trList) {
		this.trList = trList;
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


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	 

}
