package com.task.action.menjin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.AccessServer;
import com.task.entity.menjin.Access;
import com.task.util.LedCarPush;

/**
 * 门禁系统 20150920_licong
 * 
 * @author Li_Cong 记录表
 */
@SuppressWarnings("serial")
public class AccessAction extends HttpServlet {
	private Access access;
	private AccessServer accessServer;
	private List<Access> accessList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String chepai = "";
	private HttpServletRequest request;
	private String barurl1;

	// 接收摄像头传过来的消息(Http)
	public void carPaiVerify() {
		if (barurl1 != null && !"".equals(barurl1)) {
			accessServer.carPaiVerifyImpl(barurl1);
		} else {
			System.out.println("数据空空如也，请发指令.");
		}
	}

	// 一键开门
	public String oneOpen() {
		/****
		 * 一键开门流程 1、查询记录表中最近一次车辆扫描记录 得到车牌和摄像头ip 2、得到ip摄像头最近一次车牌号记录
		 * 3、将得到的车牌号与记录表中的对比。
		 * 4、相等，激发摄像头做开门操作，同时查询记录表。如有车辆相关信息。设置为新的状态，如果没有，添加状态。
		 * 5、不相等，证明扫描的车牌信息不符。需要再次触发，相等，方可开门
		 */
		// 1、
		errorMessage = accessServer.oneOpen();
		return "error";
	}

	// 一键开门 xinde
	public String oneOpenXin() {
		/****
		 * 一键开门流程 新一键开门流程 1、查询记录表中最近一次车辆扫描记录 得到车牌和摄像头ip 　如果不为空，才开门，状态改为已开门，
		 * 2、如果第一条为已识别，将记录改为已开门
		 */
		// 1、
		errorMessage = accessServer.oneOpenXin();

		return "error";
	}

	public String oneceshi() {
		LedCarPush.ledShow("", "", 7);
		return "";
	}

	
	@SuppressWarnings("unchecked")
	public String showList() {
		if (access != null) {
			ActionContext.getContext().getSession().put("Access", access);
		} else {
			access = (Access) ActionContext.getContext().getSession().get(
					"Access");
		}
		Map<Integer, Object> map = accessServer.findAccessByCondition(access,
				Integer.parseInt(cpage), pageSize);
		accessList = (List<Access>) map.get(1);// 显示面试单列表
		if (accessList != null && accessList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AccessAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "accessAction_show";
	}

	// 接受摄像头传输过来的数据
	public void devicemanagement() {
		String barurl = "";
		// barurl = accessServer.ReceivingCards();
		System.out.println("3:" + barurl);

		if (barurl != null && !"".equals(barurl)) {
			JSONObject jsonObject = JSONObject.fromObject(barurl);
			JSONObject jsonObject2 = jsonObject.getJSONObject("PlateResult");
			String car = jsonObject2.getString("license");
			String carTime = jsonObject.getString("timeString");
			Access accessCar = accessServer.findOneAccess(car, carTime, "");
			if (accessCar != null) {
				/*
				 * if (accessServer.openDoor()) { System.out.println("开门结束");
				 * accessCar.setUseSend("已使用"); return; } else {
				 * System.out.println("设备连接失败，无法开门，请联系管理员");
				 * accessCar.setUseSend("开门失败"); }
				 */
			} else {
				System.out.println("没有进出权限哦，请先申请！");
			}
		} else {
			System.out.println("数据空空如也，请发指令咯");
		}
		// MKUtil.writeJSON(barurl);
	}

	/**
	 * 跳转到添加方法
	 * 
	 * @return
	 */
	public String toadd() {
		return "accessAction";
	}

	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}

	public AccessServer getAccessServer() {
		return accessServer;
	}

	public void setAccessServer(AccessServer accessServer) {
		this.accessServer = accessServer;
	}

	public List<Access> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<Access> accessList) {
		this.accessList = accessList;
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

	public String getBarurl1() {
		return barurl1;
	}

	public void setBarurl1(String barurl1) {
		this.barurl1 = barurl1;
	}

}
