package com.task.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.IAnnualLeaveService;
import com.task.Server.LeaveServer;
import com.task.entity.AnnualLeave;
import com.task.entity.AskForLeave;
import com.task.entity.QxAskForLeave;
import com.task.entity.Users;
import com.task.entity.banci.BanCi;
import com.task.entity.singlecar.SingleCar;
import com.task.util.MKUtil;
import com.task.util.Util;
/**
 * 
 * @author liuxiaoting
 *
 */
@SuppressWarnings( { "unchecked", "serial" })
public class AskForLeaveAction extends ActionSupport {
	private LeaveServer leaveServer;// Server层
	private AskForLeave askForLeave;// 对象
	private QxAskForLeave qxAskForLeave;
	private String leaveId;// Id
	private List<AskForLeave> askList;// 列表集合
	private List<SingleCar> singleCarList;
	private SingleCar singleCar;
	private int id;// Id
	private int approvalId[];// Id数组
	private int ids[];//id数组
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;// 页面状态
	private String barcode;// 刷员工卡信息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List list;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private AnnualLeave annualLeave;                                 
	private String device;
	private String yuefen;
	private String depts;
	private IAnnualLeaveService als;
	private String tag;//标识请假时间能不能早于当前时间(noTime表示可以早于)
	private BanCi banCi;
	
	/**
	 * 换休协议
	 */
	public void listhuanxiuxieyi() {
		String aa = "换休协议";
		list = leaveServer.listhuanxiuxieyi(aa);
		MKUtil.writeJSON(list);
	}

	/***
	 * 导出Excel
	 * 
	 * @return
	 */
	public String exportExcel() {
		askForLeave = (AskForLeave) ActionContext.getContext().getSession()
				.get("askForLeave");
		leaveServer.exportExcel(askForLeave);
		return null;
	}
	
	/**
	 * 获取加班时长，在页面中显示
	 */
	public void getLeaveTime() {
		
		try {
			float[] time = leaveServer.computeDayAndHourByTime(askForLeave.getLeaveStartDate(), askForLeave.getLeaveEndDate(),id);//id=usersId
			MKUtil.writeJSON(true, null, time[0], time[1]);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false,e.toString(),null);
		}
	}

	// 判断换休

	public String panduanhuanxiu() {
		Users users = Util.getLoginUser();
		AnnualLeave newannualLeave = new AnnualLeave();
		newannualLeave = leaveServer.Bynamehuanxiu(askForLeave.getLeavePersonCode());
		float[] result=new float[2];
		float kyTime=0f;//可用时间，天数
		if (newannualLeave != null) {
			try {
				result = leaveServer.computeDayAndHourByTime(askForLeave.getLeaveStartDate(), askForLeave.getLeaveEndDate(), users.getId());
				float day = result[0];
				float hours = result[1];
				Float gzTime = leaveServer.banciGzTime(askForLeave.getLeavePersonCode());
				float qjTime = day+(hours)/gzTime;
				BigDecimal bg = new BigDecimal(newannualLeave.getSurplus());
				kyTime = bg.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();
				if (qjTime <= kyTime) {
					errorMessage = "您可以申请换休!";
				} else {
					errorMessage = "请假时间过长,已超过换休时间！！！";
				}
			} catch (Exception e) {
				errorMessage="请假失败：信息为" +e.toString();
			}
			
		} else {
			errorMessage = "你没有换休时间！！！";
		}
		
		MKUtil.writeJSON(new String[]{errorMessage,result[0]+"",result[1]+"",kyTime+""} );
		return null;
	}

	// **判断年休

	public String panduannianxiu() {
		AnnualLeave newannualLeave = new AnnualLeave();
		newannualLeave = leaveServer.BynameNianxiu(askForLeave
				.getLeavePersonCode());
		if (newannualLeave != null) {
			long chashi = ((Util.StringToDate(askForLeave.getLeaveEndDate(),
					"yyyy-MM-dd").getTime() - Util.StringToDate(
					askForLeave.getLeaveStartDate(), "yyyy-MM-dd").getTime()) / 1000 / 60 / 60 / 24);
			int shijian = (int) chashi;
			if (shijian < newannualLeave.getSurplus()) {
				errorMessage = "您可以申请年休!";
			} else {
				errorMessage = "请假时间过长,已超过年休时间！！！";
			}
		} else {
			errorMessage = "你没有年假！！！";
		}
		MKUtil.writeJSON(errorMessage);
		return null;
	}

	public String preAskForLeave() {//insertLeave
//		if (Util.chackMobileOrPc()) {
//			return "mobile_insertLeve";
//		}
		return "insertLeave";
	}

	// 保存或修改数据
	public String saveOrUpdate() {
		if (askForLeave.getLeaveStartDate() != null
				&& askForLeave.getLeaveEndDate() != null) {
			String msg = leaveServer.checkTime(askForLeave.getLeaveStartDate(),
					askForLeave.getLeaveEndDate(), askForLeave
							.getLeavePersonCode(), askForLeave.getLeaveId(),tag);
			if (!msg.equals("true")) {
				errorMessage = msg;
				if ("mobile".equals(getDevice())) {
					MKUtil.writeJSON(errorMessage);
					return null;
				} else {
					setUrl("AskForLeaveAction!preAskForLeave.action?askForLeave.appayTag="+askForLeave.getAppayTag());
					return "error";
				}
			}
		} else {
			errorMessage = "请假的时间不能为空";
			if ("mobile".equals(getDevice())) {
				MKUtil.writeJSON(errorMessage);
				return null;
			} else {
				setUrl("AskForLeaveAction!preAskForLeave.action?askForLeave.appayTag="+askForLeave.getAppayTag());
				return "error";
			}
		}
		if (askForLeave.getLeaveId() != null) {
			AskForLeave oldAskForLeave = leaveServer.selectOne(askForLeave
					.getLeaveId());
			if (oldAskForLeave != null) {
				if ("审批中".equals(oldAskForLeave.getApprovalStatus())
						&& "同意".equals(oldAskForLeave.getApprovalStatus())) {
					errorMessage = "申请已审批,修改失败!";
					return ERROR;
				}else {
					leaveServer.updateLeave(askForLeave, oldAskForLeave);
				}
			} else {
				errorMessage = "不存在您要修改的信息!请检查!";
			}
		} else {
			String msg;
			try {
				msg = leaveServer.saveLeave(askForLeave,approvalId,ids);
				this.pageStatus = "self";
				if(!msg.equals("true")){
					errorMessage=msg;
					return "error";
				}
			} catch (Exception e) {
				errorMessage ="请假失败："+ e.toString();
				return "error";
			}
			
		}
		// als.gengxinhuanxiu();
		if ("mobile".equals(getDevice())) {
			MKUtil.writeJSON("saveOk");
			return null;
		} else {
			return "saveOk";
		}
	}

	/*
	 * 安卓端请假接口
	 */
	public void savenianxiu() {
//		if (askForLeave.getLeaveStartDate() != null
//				&& askForLeave.getLeaveEndDate() != null) {
//			String msg = leaveServer.checkTime(askForLeave.getLeaveStartDate(),
//					askForLeave.getLeaveEndDate(),
//					askForLeave.getLeavePerson(), askForLeave.getLeaveId(),tag);
//			if (!msg.equals("true")) {
//				errorMessage = msg;
//				MKUtil.writeJSON(false, errorMessage, "");
//				return;
//			}
//		} else {
//			errorMessage = "请假的时间不能为空";
//			MKUtil.writeJSON(false, errorMessage, "");
//			return;
//		}
//		AnnualLeave newannualLeave = new AnnualLeave();
//		newannualLeave = leaveServer.BynameNianxiu(askForLeave
//				.getLeavePersonCode());
//		if (newannualLeave != null
//				|| (askForLeave.getLeaveTypeOf() != null && !askForLeave
//						.getLeaveTypeOf().equals("年休"))) {
//			long chashi = ((Util.StringToDate(askForLeave.getLeaveEndDate(),
//					"yyyy-MM-dd").getTime() - Util.StringToDate(
//					askForLeave.getLeaveStartDate(), "yyyy-MM-dd").getTime()) / 1000 / 60 / 60 / 24);
//			int shijian = (int) chashi;
//			if (askForLeave.getLeaveTypeOf().equals("年休")) {
//				if (shijian >= newannualLeave.getSurplus()) {
//					errorMessage = "请假时间过长,已超过年休时间！！！";
//					MKUtil.writeJSON(false, errorMessage, "");
//				}
//			}
//			boolean b = leaveServer.saveLeave1(askForLeave);
//			if (b) {
//				errorMessage = "申请成功!";
//			} else {
//				errorMessage = "申请失败!";
//			}
//			MKUtil.writeJSON(true, errorMessage, "");
//
//		} else {
//			errorMessage = "你没有年假！！！";
//			MKUtil.writeJSON(false, errorMessage, "");
//		}
		MKUtil.writeJSON(false, "APP请假暂时不可用，请登录PEBS系统申请请假！", "");
	}

	// 修改数据前执行的查询
	public String updateLeaves() {
//		askForLeave = leaveServer.selectOne(id);
//		if("xj".equals(pageStatus)){
//			return "qxAskForLeave_add";
//		}
//		return "selectOne";//updateLeave.jsp
		errorMessage="对不起，请假修改功能正在维护，请删除请假后再重新登记请假信息，谢谢。";
		return "error";
	}

	// 删除
	public String deleteLeaves() {
		try {
			leaveServer.deleteLeave(id);
		} catch (Exception e) {
			errorMessage ="删除请假失败"+e.toString();
		}
		return "deleteOK";
	}

	// 查询列表信息
	public String selectAllLeave() {
		askList = leaveServer.selectAll();
		return "selectAllOk";
	}

	// 请假信息分页

	public String selectAllByLeavePage() {
		if (askForLeave != null) {
			ActionContext.getContext().getSession().put("askForLeave",
					askForLeave);
		} else {
			askForLeave = (AskForLeave) ActionContext.getContext().getSession()
					.get("askForLeave");
		}
		Object[] object = leaveServer.selectAllByLeavePage(askForLeave, Integer
				.parseInt(cpage), pageSize, pageStatus, startDate, endDate);
		if ("self".equals(pageStatus)) {
			list = leaveServer.findPrintList();
		}
		if (object != null && object.length > 0) {
			askList = (List<AskForLeave>) object[0];
			if (askList != null && askList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("AskForLeaveAction!selectAllByLeavePage.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if (pageStatus != null && "audit".equals(pageStatus)) {
			return "auditAllOk";
		} else if (pageStatus != null && "findAll".equals(pageStatus)) {
			return "findAllOk";
		} else {
			return "successPage";//private.jsp
		}
	}

	public String getPrintById() {
		askForLeave = leaveServer.findAskLeaveById(id);
		return "printAskLeave";
	}

	// 请假审核：同意、打回
	public String approvalByOkOrBack() {
		successMessage = leaveServer.updateLeaveApproval(id, pageStatus);
		pageStatus = "audit";
		return "approvalOk";
	}

	// 待出门记录
	public String findExitList() {
		this.pageSize = 15;
		Object[] object = leaveServer.findExitList(Integer.parseInt(cpage),
				pageSize);
		list = (List) object[0];
		int count = (Integer) object[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("AskForLeaveAction!findExitList.action");
		pageStatus = "";
		return "AskForLeaveAction_findExitList";
	}

	/**
	 * 门卫出门刷卡
	 * 
	 * @return
	 */
	public String updateExit() {
		String str = leaveServer.updateExit(barcode);
		if ("exit".equals(str)) {
			pageStatus = "ok";
			successMessage = "请假出门，允许出门，请放行";
		} else if ("retu".equals(str)) {
			pageStatus = "ok";
			successMessage = "请假结束，已返回返回，请放行";
		} else {
			pageStatus = "ng";
			successMessage = "系统中未发现已经审核通过的出门申请，请核实或通知系统管理员";
		}
		return "AskForLeaveAction_findExitList";
	}
	/**
	 * 归还用车
	 * @return
	 */
    public String tobackCarList(){
    	if (singleCar != null) {
			ActionContext.getContext().getSession().put("singleCar",
					singleCar);
		    } else {//用来保持分页时带着查询条件
		    	singleCar = (SingleCar) ActionContext.getContext().getSession().get("singleCar");
		      }
		Map<Integer, Object> map = leaveServer.findSingleCarsByCondition(
				singleCar, Integer.parseInt(cpage), pageSize,pageStatus);
		singleCarList = (List<SingleCar>) map.get(1);// 显示页的技能系数列表
			if (singleCarList != null & singleCarList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("AskForLeaveAction!tobackCarList.action?pageStatus="+pageStatus);
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "backCar_show";
    }
    public String tobackCar(){
    	singleCar = leaveServer.getSingleCarById(id);
    	return "backCar";
    }
    public String backCar(){
    	String msg = leaveServer.backCar(singleCar);
    	if(msg=="true"){
    		errorMessage="归还成功!";
    		this.setUrl("AskForLeaveAction!tobackCarList.action");
    	}else{
    		errorMessage=msg;
    	}
    	return "error";
    }
    public String carDetail(){
    	singleCar = leaveServer.getSingleCarById(id);
    	askForLeave = leaveServer.getLeaveByCarId(id);
    	return "carDetail";
    }
    /**
     * 获取部门负责人
     * @return
     */
    public void getDeptUsers(){
    	list = leaveServer.getDeptUsers(id);
    	MKUtil.writeJSON(list);
    }
    /**
     * 获取车牌
     */
    public void getCarNumber(){
    	list = leaveServer.getCarNumber();
    	MKUtil.writeJSON(list);
    }
    //申请销假;
    public String addqxAskForLeave(){
    	boolean bool;
		try {
			bool = leaveServer.addqxAskForLeave(qxAskForLeave);
			errorMessage = "申请销假失败!";
	    	if(bool){
	    		errorMessage =  "申请销假成功!";
	    	}
		} catch (Exception e) {
			errorMessage = "申请销假失败:"+e.toString();
		}
    	
    	return "qxAskForLeaveshow";
    }
    //查看个人销假信息
    public String qxAskForLeaveshow(){
    	list = leaveServer.showQxAskForLeave(id);
    	
    	return "qxAskForLeave_show";
    }
    //根据Id查看销假信息；
    public String showqxAskForLeaveById(){
    	qxAskForLeave = leaveServer.QxAskForLeaveByid(id);
    	return "qxAskForLeave_byId";
    }
    //根据Id删除销假记录
    public String delqxAskForLeave(){
    		boolean bool = leaveServer.delQxAskForLeave(qxAskForLeave);
    		errorMessage = "删除失败";
    		if(bool){
    			errorMessage = "删除成功!";
    		}
    	return "qxAskForLeaveshow";
    }
    
	// getter和setter方法
	public LeaveServer getLeaveServer() {
		return leaveServer;
	}

	public void setLeaveServer(LeaveServer leaveServer) {
		this.leaveServer = leaveServer;
	}

	public AskForLeave getAskForLeave() {
		return askForLeave;
	}

	public void setAskForLeave(AskForLeave askForLeave) {
		this.askForLeave = askForLeave;
	}

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public List<AskForLeave> getAskList() {
		return askList;
	}

	public void setAskList(List<AskForLeave> askList) {
		this.askList = askList;
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

	public int[] getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(int[] approvalId) {
		this.approvalId = approvalId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public AnnualLeave getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(AnnualLeave annualLeave) {
		this.annualLeave = annualLeave;
	}

	public IAnnualLeaveService getAls() {
		return als;
	}

	public void setAls(IAnnualLeaveService als) {
		this.als = als;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public List<SingleCar> getSingleCarList() {
		return singleCarList;
	}

	public void setSingleCarList(List<SingleCar> singleCarList) {
		this.singleCarList = singleCarList;
	}

	public SingleCar getSingleCar() {
		return singleCar;
	}

	public void setSingleCar(SingleCar singleCar) {
		this.singleCar = singleCar;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public QxAskForLeave getQxAskForLeave() {
		return qxAskForLeave;
	}

	public void setQxAskForLeave(QxAskForLeave qxAskForLeave) {
		this.qxAskForLeave = qxAskForLeave;
	}

	public BanCi getBanCi() {
		return banCi;
	}

	public void setBanCi(BanCi banCi) {
		this.banCi = banCi;
	}

}
