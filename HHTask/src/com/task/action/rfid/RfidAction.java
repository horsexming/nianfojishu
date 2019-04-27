package com.task.action.rfid;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.rfid.RfidServer;
import com.task.entity.rfid.Rfid;

/**
 * Rfid系统 20170414_licong
 * 
 * @author Li_Cong 
 */
@SuppressWarnings("unchecked")
public class RfidAction {
	private Rfid rfid;
	private RfidServer rfidServer;
	private List<Rfid> rfidList;
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
		if (rfid != null)
			ActionContext.getContext().getSession().put("Rfid", rfid);
		else
			rfid = (Rfid) ActionContext.getContext().getSession().get("Rfid");
		Object[] object = rfidServer.findRfid(rfid, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			rfidList = (List<Rfid>) object[0];
			if (rfidList != null && rfidList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("RfidAction_showList.action");
			}
			errorMessage = null;
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "rfid_show";
	}

	// 添加方法
	public String add() {
		if (rfid != null) {
			errorMessage = rfidServer.addRfid(rfid);
			if ("添加成功！".equals(errorMessage))
				url = "RfidAction_showList.action";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (rfid.getId() != null && rfid.getId() > 0
				&& rfid != null) {
			rfid = rfidServer.byIdRfid(rfid.getId());
			if (rfid != null)
				return "rfid_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}
	
	//修改方法
	public String update() {
		errorMessage = rfidServer.updateRfid(rfid);
		if ("修改成功！".equals(errorMessage))
			url = "rfidAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = rfidServer.deleteRfid(id);
			if ("删除成功！".equals(errorMessage))
				url = "rfidAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	//开始发送
	public void sendRfid(){
		rfidServer.sendRfid();
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

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Rfid getRfid() {
		return rfid;
	}

	public void setRfid(Rfid rfid) {
		this.rfid = rfid;
	}

	public RfidServer getRfidServer() {
		return rfidServer;
	}

	public void setRfidServer(RfidServer rfidServer) {
		this.rfidServer = rfidServer;
	}

	public List<Rfid> getRfidList() {
		return rfidList;
	}

	public void setRfidList(List<Rfid> rfidList) {
		this.rfidList = rfidList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
