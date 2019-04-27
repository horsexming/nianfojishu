package com.task.action.qimi;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.qimi.GasServer;
import com.task.entity.qimi.Gas;

public class GasAction extends ActionSupport {
	private GasServer gasServer;
	private Gas gas;
	private String errorMessage;
	private String successMessage;
	private List<String> gasList;
	private String number;
	private String String;
	private String markId;// 件号
	private String selfCard;// 批次
	private String cpage = "1";
	private int pageSize = 15;
	private String ERROR;
	private String total;
	private String url;
	private String statue;
	// 添加气密入库表
	public String addGas() {
		if (gas!=null&&"".equals(gas.getNumber())) {
			successMessage = "序列号不能为空!";
		}
		successMessage = gasServer.addGas(gas);
		if ("true".equals(successMessage)) {
			successMessage = "添加" + gas.getNumber() + "成功!";
			gas = null;
			return "Gas_add";
		}
		successMessage += gas.getNumber();
		errorMessage ="已存在该序列号，不能重复";
		return "error";

	}
	//查询气密入库表
	@SuppressWarnings("unchecked")
	public Object findGas() {
		if (gas != null) {
			ActionContext.getContext().getSession().put("gas", gas);
		} else {
			gas = (Gas) ActionContext.getContext().getSession()
					.get("gas");
		}
		Object[] object =  this.gasServer.findGas(gas, Integer
				.parseInt(cpage), pageSize);
		boolean bool=true;
		if (object != null && object.length > 0) {
			gasList = (List<String>) object[0];
			if (gasList != null && gasList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("gasAction_findGas.action?statue=find");
			}else {
				bool=false;
			}
		} else {
			bool=false;
		}
		if(!bool){
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}

		return "Gas_toadd";
       
	}

	// 跳转到添加页面
	public String toadd() {
		return "Gas_add";
	}
	

	public String getString() {
		return String;
	}

	public void setString(String string) {
		String = string;
	}

	public GasServer getGasServer() {
		return gasServer;
	}

	public void setGasServer(GasServer gasServer) {
		this.gasServer = gasServer;
	}

	public Gas getGas() {
		return gas;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
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
	public List<String> getGasList() {
		return gasList;
	}
	public void setGasList(List<String> gasList) {
		this.gasList = gasList;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
	public String getCpage() {
		return cpage;
	}
	public void setCpage(String cpage) {
		this.cpage = cpage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getERROR() {
		return ERROR;
	}
	public void setERROR(String eRROR) {
		ERROR = eRROR;
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
	public String getStatue() {
		return statue;
	}
	public void setStatue(String statue) {
		this.statue = statue;
	}
    
}
