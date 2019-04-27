package com.task.action.menjin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.AccessSwitchServer;
import com.task.entity.menjin.AccessSwitch;
import com.task.entity.rfid.Rfid;

/**
 * 
 * @author fy 门禁开关
 */
@SuppressWarnings("serial")
public class AccessSwitchAction  {
//	private Rfid rfid;
//	private RfidServer rfidServer;
//	private List<Rfid> rfidList;
	private AccessSwitch accessSwitch;
	private AccessSwitchServer accessSwitchServer;
	private List<AccessSwitch> accessSwitchList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String chepai = "";
	private Integer id = 0;
	private HttpServletRequest request;

	public String toadd() {
		return "rfid_add";
	}

	// 分页显示
	// 显示查询内容
	public String showList() {
		if (accessSwitch != null) ActionContext.getContext().getSession().put("AccessSwitch", accessSwitch);
		  else accessSwitch = (AccessSwitch) ActionContext.getContext().getSession().get("AccessSwitch");
		  Object[] object = accessSwitchServer.findAccessSwitch(accessSwitch, Integer.parseInt(cpage), pageSize);
		  if (object != null && object.length > 0) {
			  accessSwitchList = (List<AccessSwitch>) object[0];
				if (accessSwitchList != null && accessSwitchList.size() > 0) {
			    int count = (Integer) object[1];
			    int pageCount = (count + pageSize - 1) / pageSize;
			    this.setTotal(pageCount + "");
			    this.setUrl("AccessSwitch_showList.action");
		   }
		    errorMessage = null;
		  } else errorMessage = "没有找到你要查询的内容,请检查后重试!";
		  return "borrow_DaiIndex";
	}

	
	public AccessSwitch getAccessSwitch() {
		return accessSwitch;
	}

	public void setAccessSwitch(AccessSwitch accessSwitch) {
		this.accessSwitch = accessSwitch;
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

	public String getChepai() {
		return chepai;
	}

	public void setChepai(String chepai) {
		this.chepai = chepai;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

}
