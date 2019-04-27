package com.task.action.menjin;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.WarehouseApplicationServer;
import com.task.entity.OaAppDetail;
import com.task.entity.menjin.WarehouseApplication;
import com.task.entity.menjin.WarehouseCertificate;
import com.task.util.MKUtil;

/**
 * 存库位申请Action层 2016-11-17
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class WarehouseApplicationAction {
	private WarehouseApplicationServer warehouseApplicationServer;// Server层
	private WarehouseCertificate warehouseCertificate;//凭证信息
	private WarehouseApplication warehouseApplication;//申请信息
	private OaAppDetail oadetail;//采购申报明细
	private List<WarehouseApplication> warehouseApplicationList;//开门申请表集合
	private List<WarehouseCertificate> warehouseCertificateList;//开门凭证表集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer ids;// id
	private Integer number;// 确认数量
	private String pageStatus;// 页面状态// 绑定功能

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private Integer userId;
	private int pageSize = 15;
	
	public String Test() {
//		warehouseApplicationServer.huanyuanProcard();
		return "error";
	}

	//控制库位门状态
	public String openandClose(){
		warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(warehouseCertificate.getId());
		if (warehouseCertificate!=null) {
			errorMessage = warehouseApplicationServer.openandClose(warehouseCertificate);
			if("true".equals(errorMessage)){
				errorMessage = "操作成功！";
				url = "WarehouseApplicationAction_showList_1.action?id="+id;
			}
		}
		return "error";
	}
	
	//给屏幕发送二维码信息
	public void sendTow(){
		if (id==null||id<=0) MKUtil.writeJSON(false, "信息为空，发送失败！", null);
		warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(id);
		if (warehouseCertificate!=null) {
			errorMessage = warehouseApplicationServer.sendTow(warehouseCertificate);
			if ("true".equals(errorMessage)) MKUtil.writeJSON(true, "发送成功", null);
			else MKUtil.writeJSON(false, errorMessage, null);
		}
	}
	
	public void oacAndroid(){
		if (userId!=null&&userId>0) {
			warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(id);
			if (warehouseCertificate!=null) {
				errorMessage = warehouseApplicationServer.oaCloseA(warehouseCertificate);
				if("true".equals(errorMessage)){
					MKUtil.writeJSON(true, "操作成功！", null);
				}else {
					MKUtil.writeJSON(false, "操作失败！", null);
				}
			}
		}
		MKUtil.writeJSON(false, "用户信息为空，操作失败！", null);
	}
	
	public void oactoWeb(){
		if (userId!=null&&userId>0) {
			warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(id);
			if (warehouseCertificate!=null) {
				errorMessage = warehouseApplicationServer.oaCloseA(warehouseCertificate);
				if("true".equals(errorMessage)){
					MKUtil.writeJSON(true, "操作成功！", null);
				}else {
					MKUtil.writeJSON(false, "操作失败！", null);
				}
			}
		}
		MKUtil.writeJSON(false, "用户信息为空，操作失败！", null);
	}
	
	public void shansuo(){
		warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(id);
		if (warehouseCertificate!=null) {
			warehouseApplicationServer.shansuo(warehouseCertificate);
		}
	}
	
	//控制库位门状态
	public String queren(){
		warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(warehouseCertificate.getId());
		if (warehouseCertificate!=null) {
			errorMessage = warehouseApplicationServer.querenAnd(warehouseCertificate,1);
			if("trues".equals(errorMessage)){
				errorMessage = "操作成功！";
				url = "WarehouseApplicationAction_showList.action?id="+id;
			}else if ("true".equals(errorMessage)) {
				errorMessage = "操作成功！";
				url = "WarehouseApplicationAction_showList_1.action?id="+id;
			}
		}
		return "error";
	}
	//控制库位门状态
	public void queren_Android(){
		if (userId!=null&&userId>0) {
			warehouseCertificate = warehouseApplicationServer.byIdWarehouseC(id);
			if (warehouseCertificate!=null) {
				errorMessage = warehouseApplicationServer.querenAnd(warehouseCertificate,number);
				if("trues".equals(errorMessage)){
					errorMessage = "shen";
					MKUtil.writeJSON(true, errorMessage, null);
				}else if ("true".equals(errorMessage)) {
					errorMessage = "ping";
					MKUtil.writeJSON(true, errorMessage, null);
				}else {
					MKUtil.writeJSON(false, errorMessage, null);
				}
			}
		}
		MKUtil.writeJSON(false, "用户信息为空，操作失败！", null);
	}
	
	// 分页查询内容
	public String showList() {
		if (warehouseApplication != null) {
			ActionContext.getContext().getSession().put("WarehouseApplications",
					warehouseApplication);
		} else {// 用来保持分页时带着查询条件
			warehouseApplication = (WarehouseApplication) ActionContext.getContext().getSession()
					.get("WarehouseApplications");
		}
		Map<Integer, Object> map = warehouseApplicationServer.findWarehouseApplication(warehouseApplication,
				Integer.parseInt(cpage), pageSize);
		warehouseApplicationList = (List<WarehouseApplication>) map.get(1);// 显示面试单列表
		if (warehouseApplicationList != null && warehouseApplicationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("WarehouseApplicationAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "warehouseApplication_show";
	}
	
	public void showList_android(){
		if (userId!=null&&userId>0) {
			Map<Integer, Object> map = warehouseApplicationServer.findWarehouseAppAndroid(warehouseApplication,
					Integer.parseInt(cpage), pageSize, userId);
			warehouseApplicationList = (List<WarehouseApplication>) map.get(1);
			if (warehouseApplicationList != null && warehouseApplicationList.size() > 0) {
				MKUtil.writeJSON(true, "massage", warehouseApplicationList);
			}else{
				MKUtil.writeJSON(false, "您当前没有已申请的信息", null);
			}
		}
		MKUtil.writeJSON(false, "员工信息为空", null);
	}
	
	// 分页查询内容
	public String showList_1() {
		warehouseCertificateList = warehouseApplicationServer.findWareCert(id);ids = id;
		if (warehouseCertificateList != null && warehouseCertificateList.size()==0) errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "warehouseCertificate_show";
	}
	
	// 分页查询内容
	public void showList_android_c() {
		if (userId!=null&&userId>0&&id!=null&&id>0) {
			warehouseApplication = warehouseApplicationServer.byIdWarehouseApplication(id, userId);
			if (warehouseApplication!=null) {
				warehouseCertificateList = warehouseApplicationServer.findWareCert(id);
				if (warehouseCertificateList != null && warehouseCertificateList.size()>0) MKUtil.writeJSON(true, "massage", warehouseCertificateList);
				else MKUtil.writeJSON(false, "无可操作库位", null);
			}
			MKUtil.writeJSON(false, "申请为空！", null);
		}
		MKUtil.writeJSON(false, "员工信息为空", null);
	}

	// 添加方法
	public String add() {
		if (warehouseApplication != null) {
			errorMessage = warehouseApplicationServer.addWarehouseApplication(warehouseApplication,warehouseCertificateList);
			if ("添加成功！".equals(errorMessage))
				url = "WarehouseApplicationAction_showList.action";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}


	// 修改方法
	public String update() {
		errorMessage = warehouseApplicationServer.updateWarehouseApplication(warehouseApplication);
		if ("修改成功！".equals(errorMessage))
			url = "WarehouseApplicationAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = warehouseApplicationServer.deleteWarehouseApplication(id);
			if ("删除成功！".equals(errorMessage))
				url = "WarehouseApplicationAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	public WarehouseApplicationServer getWarehouseApplicationServer() {
		return warehouseApplicationServer;
	}

	public void setWarehouseApplicationServer(
			WarehouseApplicationServer warehouseApplicationServer) {
		this.warehouseApplicationServer = warehouseApplicationServer;
	}

	public WarehouseApplication getWarehouseApplication() {
		return warehouseApplication;
	}

	public void setWarehouseApplication(WarehouseApplication warehouseApplication) {
		this.warehouseApplication = warehouseApplication;
	}

	public OaAppDetail getOadetail() {
		return oadetail;
	}

	public void setOadetail(OaAppDetail oadetail) {
		this.oadetail = oadetail;
	}

	public List<WarehouseApplication> getWarehouseApplicationList() {
		return warehouseApplicationList;
	}

	public void setWarehouseApplicationList(
			List<WarehouseApplication> warehouseApplicationList) {
		this.warehouseApplicationList = warehouseApplicationList;
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

	public List<WarehouseCertificate> getWarehouseCertificateList() {
		return warehouseCertificateList;
	}

	public void setWarehouseCertificateList(
			List<WarehouseCertificate> warehouseCertificateList) {
		this.warehouseCertificateList = warehouseCertificateList;
	}

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

	public WarehouseCertificate getWarehouseCertificate() {
		return warehouseCertificate;
	}

	public void setWarehouseCertificate(WarehouseCertificate warehouseCertificate) {
		this.warehouseCertificate = warehouseCertificate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	// 构造方法

}
