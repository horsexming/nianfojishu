package com.task.action.menjin;

import java.util.List;
import java.util.Map;




import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.AccessRecordsServer;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.AccessRecords;
import com.task.entity.menjin.CarInOutType;

/**
 * 门禁系统 20150920_licong
 * 
 * @author Li_Cong 进出记录表
 */

@SuppressWarnings("unchecked")
public class AccessRecordsAction {
	private AccessRecords accessRecords;
	private CarInOutType carInOutType;
	private AccessLogInfor accessLogInfor;
	private AccessRecordsServer accessRecordsServer;
	private List<AccessRecords> accessRecordList;
	private List<CarInOutType> carInOutTypeList;
	private List<AccessLogInfor> accessLogInforList;
	private String errorMessage;
	private String successMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	
	public String showList(){
		if (accessRecords != null) {
			ActionContext.getContext().getSession().put("AccessRecords",
					accessRecords);
		} else {
			accessRecords = (AccessRecords) ActionContext
					.getContext().getSession().get("AccessRecords");
		}
		Map<Integer, Object> map = accessRecordsServer
				.findAccessRecordsByCondition(accessRecords, Integer
						.parseInt(cpage), pageSize, tag);
		accessRecordList = (List<AccessRecords>) map.get(1);// 显示列表
		if (accessRecordList != null && accessRecordList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AccessRecordsAction_showList.action?tag="+tag);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "accessRecordsAction_show";
	}
	
	/**
	 * 门禁设备使用记录查看
	 * @return
	 */
	public String showList_log(){
		pageSize = 30;
		if (accessLogInfor != null) {
			ActionContext.getContext().getSession().put("AccessLogInfor",
					accessLogInfor);
		} else {
			accessLogInfor = (AccessLogInfor) ActionContext
			.getContext().getSession().get("AccessLogInfor");
		}
		Map<Integer, Object> map = accessRecordsServer
		.findAccessLogInforByCondition(accessLogInfor, Integer
				.parseInt(cpage), pageSize, tag);
		accessLogInforList = (List<AccessLogInfor>) map.get(1);// 显示列表
		if (accessLogInforList != null && accessLogInforList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AccessRecordsAction_showList_log.action?tag="+tag);
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		if("shui".equals(tag))
			return "accessRecordsActionShui_show";
		return "accessRecordsActionLog_show";
	}
	
	
	public String showList_carType(){
		if (carInOutType != null) {
			ActionContext.getContext().getSession().put("CarInOutType",
					carInOutType);
		} else {
			carInOutType = (CarInOutType) ActionContext
					.getContext().getSession().get("CarInOutType");
		}
		Map<Integer, Object> map = accessRecordsServer
				.findCarInOutTypeByCondition(carInOutType, Integer
						.parseInt(cpage), pageSize);
		carInOutTypeList = (List<CarInOutType>) map.get(1);// 列表
		if (carInOutTypeList != null && carInOutTypeList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("AccessRecordsAction_showList_carType.action");
			errorMessage = null;
		} else {
			errorMessage = "没有符合条件的信息,请检查后重试!";
		}
		return "accessRecordsAction_show_carType";
	}
	
	
	/**
	 * 跳转到修改方法
	 * 
	 * @return
	 */
	public String toupdate() {
		CarInOutType carInOutType1 = accessRecordsServer
				.getByIdCarInOutType(carInOutType.getId());
		if (carInOutType1 != null) {
			carInOutType = carInOutType1;
			return "accessRecordsAction_update_carType";
		}
		errorMessage = "不存在该数据!请检查";
		return "error";
	}

	/**
	 * 修改方法
	 * 
	 * @return
	 */
	public String carType_update() {
		errorMessage = accessRecordsServer
				.updateCarInOutType(carInOutType);
		if ("true".equals(errorMessage)) {
			errorMessage = "修改成功";
			url = "AccessRecordsAction_showList_carType.action";
			return "error";
		}
		return "error";
	}

	
	
	public AccessRecords getAccessRecords() {
		return accessRecords;
	}
	public void setAccessRecords(AccessRecords accessRecords) {
		this.accessRecords = accessRecords;
	}
	public AccessRecordsServer getAccessRecordsServer() {
		return accessRecordsServer;
	}
	public void setAccessRecordsServer(AccessRecordsServer accessRecordsServer) {
		this.accessRecordsServer = accessRecordsServer;
	}
	public List<AccessRecords> getAccessRecordList() {
		return accessRecordList;
	}
	public void setAccessRecordList(List<AccessRecords> accessRecordList) {
		this.accessRecordList = accessRecordList;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public CarInOutType getCarInOutType() {
		return carInOutType;
	}

	public void setCarInOutType(CarInOutType carInOutType) {
		this.carInOutType = carInOutType;
	}

	public List<CarInOutType> getCarInOutTypeList() {
		return carInOutTypeList;
	}

	public void setCarInOutTypeList(List<CarInOutType> carInOutTypeList) {
		this.carInOutTypeList = carInOutTypeList;
	}

	public AccessLogInfor getAccessLogInfor() {
		return accessLogInfor;
	}

	public void setAccessLogInfor(AccessLogInfor accessLogInfor) {
		this.accessLogInfor = accessLogInfor;
	}

	public List<AccessLogInfor> getAccessLogInforList() {
		return accessLogInforList;
	}

	public void setAccessLogInforList(List<AccessLogInfor> accessLogInforList) {
		this.accessLogInforList = accessLogInforList;
	}
	

}
