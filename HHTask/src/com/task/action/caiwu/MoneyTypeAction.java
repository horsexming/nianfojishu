package com.task.action.caiwu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.caiwu.MoneyTypeServer;
import com.task.entity.CompanyVIP;
import com.task.entity.caiwu.MoneyType;
import com.task.util.MKUtil;

public class MoneyTypeAction extends ActionSupport{

	private MoneyTypeServer mtserver;
	private List<MoneyType> mtList;
	private MoneyType mt;
	private Integer id;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	
	
	public String addMonyType(){
		try {
			if(mt!=null){
			errorMessage =	mtserver.addMoneyType(mt);
				if("true".equals(errorMessage)){
					errorMessage = "添加成功!";
				}else{
					errorMessage = "添加失败！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "MoneyType_add";
	}
	public String jinyong(){
		try {
			mtserver.jinyong(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAllMoneyTypeByPage";
	}
	public String fanjinyong(){
		try {
		 mtserver.fanjinyong(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAllMoneyTypeByPage";
	}
	
	public String updateMoneyType(){
		try {
			errorMessage = "修改失败";
			boolean bool =	mtserver.updateMoneyType(mt);
			if(bool){
				errorMessage = "修改成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return "MoneyType_update";
	}
	public String delMoneyType(){
		try {
			mtserver.delMoneyType(mt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAllMoneyTypeByPage";
	}
	public String findMoneyTypeById(){
		try {
			mt = mtserver.findMoneyTypeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "MoneyType_update";
	}
	public void findAllMoneyType(){
		try {
			mtList = mtserver.findAllMoneyType();
			if(mtList!=null && mtList.size()>0){
				MKUtil.writeJSON(mtList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String findAllMoneyTypeByPage(){
		int count = mtserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		mtList = mtserver.findAllMoneyTypeBypage(Integer.parseInt(cpage), pageSize);
		if (mtList != null) {
			this.setUrl("MoneyTypeAction_findAllMoneyTypeByPage.action");
			return "MoneyType_List";
		}
		return ERROR;
	}
	public String findMoneyType(){
		if (mt != null) {
			ActionContext.getContext().getSession().put("mt", mt);
		} else {
			mt = (MoneyType) ActionContext.getContext().getSession()
					.get("mt");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		map = mtserver.findMoneyTypeCondition(mt, Integer
				.parseInt(cpage), pageSize);
		mtList = (List<MoneyType>) map.get(1);
		if (mtList != null && mtList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("MoneyTypeAction_findMoneyType.action");
			return "MoneyType_List";
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
			return ERROR;
		}
	}
	public MoneyTypeServer getMtserver() {
		return mtserver;
	}
	public void setMtserver(MoneyTypeServer mtserver) {
		this.mtserver = mtserver;
	}
	public List<MoneyType> getMtList() {
		return mtList;
	}
	public void setMtList(List<MoneyType> mtList) {
		this.mtList = mtList;
	}
	public MoneyType getMt() {
		return mt;
	}
	public void setMt(MoneyType mt) {
		this.mt = mt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	
	
}
