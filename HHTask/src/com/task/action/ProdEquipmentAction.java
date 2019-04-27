package com.task.action;

import java.io.File;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.xy.YWithXInterval;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ProdEquipmentService;
import com.task.entity.DJNR;
import com.task.entity.Machine;
import com.task.entity.OaAppDetail;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.entity.VOStorage;
import com.task.entity.VOStore;
import com.task.entity.bybz.BaoYangRecord;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessSaveLog;
import com.task.entity.sop.ProcessWlqr;
import com.task.entity.sop.ProcessinforFuLiao;
import com.task.util.FieldMeta;
import com.task.util.MKUtil;
import com.task.util.Util;

public class ProdEquipmentAction extends ActionSupport {
	private Machine machine;// 设备表
	private ProdEquipmentService prodEquipmentService;
	private List list;
	private List proList;
	private List airList;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String jsonList;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private TaSopGongwei taSopGongwei;
	private List<TaSopGongwei> gongweiList;
	private List<Machine> machineList;
	private VOStorage vsto = new VOStorage();
	private VOStore vos = new VOStore();
	private Procard procard;
	private File addmachine;
	private String addmachineContentType;// 文件类型
	private String addmachineFileName;// 文件名称
	private Float nowSbNh;// 本次设备能耗
	private List<DJNR> djnrList; // 点检内容
	private Users user;
	private int[] arrayId;
	private String tag;
	private Integer deptId;
	private List<String> workPositionlist;
	private List<ProcessinforFuLiao> flList;//
	private List<ProcessSaveLog> psaveLogList;//
	private List<ProcessWlqr> ListProcesswlqr;
	private List<BaoYangRecord> byRecordList;
	private String operation;
	private String showCloumn;
	private String showText;
	// 添加
	public String add() {
		int count = prodEquipmentService.findAlly(machine);
		if (count < 1) {
			prodEquipmentService.add(machine);
			machine = null;
			return "findAllRe";
		} else {
			errorMessage = "该设备已添加,请勿重复添加!谢谢!";
			return ERROR;
		}

	}

	// 批量导入
	public String addMachine() {
		int count = prodEquipmentService.findAlly(machine);
		try {
			prodEquipmentService.addMachine(addmachine);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			return ERROR;
		}
		return "findAllRe";
	}

	// 条件查询
	@SuppressWarnings("unchecked")
	public String findAll() {
		if (showCloumn != null) {
			ActionContext.getContext().getSession().put("showCloumn", showCloumn);
		} else {
			showCloumn = (String) ActionContext.getContext().getSession()
					.get("showCloumn");
		}
		String[] strs =Util.getFieldAndRemarks(Machine.class, showCloumn);
		successMessage = strs[0];
		if(showCloumn==null || showCloumn.length()==0){
			showCloumn = strs[1];
		}
		showText = strs[2];
		if (("bdgw".equals(pageStatus) || "ybdgw".equals(pageStatus) || "wbdgw"
				.equals(pageStatus))) {
			if (machine != null) {
				ActionContext.getContext().getSession().put("machine", machine);
			} else if (!"nocx".equals(tag)) {
				machine = (Machine) ActionContext.getContext().getSession()
						.get("machine");
			}
		} else {
			if (machine != null) {
				ActionContext.getContext().getSession().put("machine_bd",
						machine);
			} else if (!"nocx".equals(tag)) {
				machine = (Machine) ActionContext.getContext().getSession()
						.get("machine_bd");
			}
		}
//		if (operation != null) {
//			ActionContext.getContext().getSession().put("operation", operation);
//		} else if (!"nocx".equals(tag)) {
//			operation = (String) ActionContext.getContext().getSession()
//					.get("operation");
//		}
		if (id > 0) {
			user = prodEquipmentService.findUserById(id);
			deptId = prodEquipmentService.getdeptId(user.getPassword()
					.getDeptNumber());
		}
		Object[] object = prodEquipmentService.findMachineByCondition(machine,
				Integer.parseInt(cpage), pageSize, pageStatus, id);
//		Object[] object = prodEquipmentService.findMachineByCondition1(machine,
//				Integer.parseInt(cpage), pageSize, pageStatus, id,showCloumn);
		if (object != null && object.length > 0) {
			machineList = (List<Machine>) object[0];
			list =  (List) object[0];
			if ((machineList != null && machineList.size() > 0)) {
//			jsonList =	(String) object[0];
//			if ((jsonList != null && jsonList.length() > 0)) {	
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null) {
					this
							.setUrl("ProdEquipmentAction!findAll.action?pageStatus="
									+ pageStatus+"&operation="+operation);
					if (id > 0) {
						this
								.setUrl("ProdEquipmentAction!findAll.action?pageStatus="
										+ pageStatus
										+ "&id="
										+ id
										+ "&tag=nocx");
					}
				} else {
					this.setUrl("ProdEquipmentAction!findAll.action?operation="+operation);
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if ("bdgw".equals(pageStatus) || "ybdgw".equals(pageStatus)
				|| "wbdgw".equals(pageStatus)) {
			return "bdGongwei";
		}
		return "addSuccess";// addEquipment.jsp
	}
	public void delbybz(){
		MKUtil.writeJSON(prodEquipmentService.deletebybz(id));
	}
	/*
	 * 添加保养记录
	 */
	public String addBaoYangRecord(){
		if(machine!=null&&byRecordList!=null&&byRecordList.size()>0){
			errorMessage = prodEquipmentService.addBaoYangRecord(byRecordList, machine);
		}
		return "error";
	}
	public String baoYangRecordList(){
		machine = prodEquipmentService.findAssetById(id);
		byRecordList = prodEquipmentService.findbyrList(id);
		return "baoYangrecord_List";
	}
	/***
	 * 根据编号、工区、工位 查询设备 以及维修记录
	 * 
	 * @return
	 */
	public String findMachineByNum() {
		// 查询设备
		machine = prodEquipmentService.findMachineByNum(machine);
		// 查询设备报修记录
		list = prodEquipmentService.findMaintenanceByNum(machine);

		// 查询设备报修记录
		nowSbNh = Float.parseFloat(String.format("%.2f", prodEquipmentService
				.findSbNhByProId(id)));

		// 查询工序领取记录(设备能耗记录)
		proList = prodEquipmentService.findPIRLogByProId(id);
		// 查询辅料使用情况
		flList = prodEquipmentService.findProcessFlByProId(id);
		//查询物料确认记录信息
		ListProcesswlqr = prodEquipmentService.findProcessWlqrById(id);
		// 查询临时存放信息
		try {
			psaveLogList = prodEquipmentService.findProcessSaveLogByProId(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 查询气密检验记录
		airList = prodEquipmentService.findAirByProId(id);

		return "findMachine";
	}

	/***
	 * 根据编号、工区、工位 查询设备 以及维修记录
	 * 
	 * @return
	 */
	public void findPIRLogByFk_pirLId() {
		// 查询设备能耗记录
		List list = prodEquipmentService.findPIRLogByFk_pirLId(id);
		MKUtil.writeJSON(list);
	}

	// 动态检索与当前时间同步，更新其设备净值
	public String toupdate() throws Exception {
		this.machineList = this.prodEquipmentService.updatedomachine();
		return "toupdate";
	}

	// 修改
	public String initupmachiner() {
		machine = prodEquipmentService.findAssetById(id);
		list = prodEquipmentService.findListbybz(id);
		return "upper";// uppEquipment.jsp
	}
	public String toaddbyr() {
		machine = prodEquipmentService.findAssetById(id);
		list = prodEquipmentService.findListbybz(id);
		return "addBaoYangrecord";
	}
	// 二维码显示
	public String checkMachiner() {
		if (Util.getLoginUser() == null) {
			errorMessage = "请先登录";
			return "shebeiLogin";
		}
		machine = prodEquipmentService.findAssetById(id);
		if (machine != null && machine.getWorkPosition() != null) {
			gongweiList = prodEquipmentService.findTaSopGongwei(machine);
			gongweiList = prodEquipmentService.updateTaSopGongwei(gongweiList);
		}
		return "checkMachiner";
	}

	public String updatemachine() {
		prodEquipmentService.update(machine);// 更新设备
		prodEquipmentService.updateTaSopGongwei(machine);// 更新设备对应工位
		return "updatermachin";
	}

	// 打印
	public String printStorage() {
		if (vsto != null && vsto.getSelected() != null
				&& vsto.getSelected().length > 0) {
			machineList = prodEquipmentService.printStorage(vsto.getSelected());
		}
		return "success";
	}

	// 删除
	public String delequipment() {
		machine = prodEquipmentService.findAssetById(id);
		if (machine != null) {
			prodEquipmentService.delete(machine);
			return "addmchiner";
		}
		errorMessage = "不存在您删除的数据,请检查!";
		return ERROR;

	}

	// 绑定 设备点检内容;
	public void bddjnr() {
		boolean bool = prodEquipmentService.bddjnr(machine, djnrList);
		errorMessage = "绑定失败";
		if (bool) {
			errorMessage = "绑定成功";

		}
		try {
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}

	}

	// 人员绑定工位设备
	public String Usersbdgw() {
		if (prodEquipmentService.Userbdgw(id, arrayId)) {
			return "findAll";
		}
		return ERROR;
	}

	// 人员解除绑定工位设备
	public String Usersjcbdgw() {
		if (prodEquipmentService.Userjcbdgw(arrayId, id)) {
			return "findAll";
		}
		return ERROR;
	}
	public String toaddDJday(){
		 machine=prodEquipmentService.getmachine(id);
		 djnrList=prodEquipmentService.getdjnrbyId(id);
		 return "djday_add";
	}
	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProdEquipmentService getProdEquipmentService() {
		return prodEquipmentService;
	}

	public void setProdEquipmentService(
			ProdEquipmentService prodEquipmentService) {
		this.prodEquipmentService = prodEquipmentService;
	}

	public List<Machine> getMachineList() {
		return machineList;
	}

	public void setMachineList(List<Machine> machineList) {
		this.machineList = machineList;
	}

	public VOStorage getVsto() {
		return vsto;
	}

	public void setVsto(VOStorage vsto) {
		this.vsto = vsto;
	}

	public VOStore getVos() {
		return vos;
	}

	public void setVos(VOStore vos) {
		this.vos = vos;
	}

	public TaSopGongwei getTaSopGongwei() {
		return taSopGongwei;
	}

	public void setTaSopGongwei(TaSopGongwei taSopGongwei) {
		this.taSopGongwei = taSopGongwei;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public List getProList() {
		return proList;
	}

	public void setProList(List proList) {
		this.proList = proList;
	}


	public List getAirList() {
		return airList;
	}

	public void setAirList(List airList) {
		this.airList = airList;
	}

	public File getAddmachine() {
		return addmachine;
	}

	public void setAddmachine(File addmachine) {
		this.addmachine = addmachine;
	}

	public String getAddmachineContentType() {
		return addmachineContentType;
	}

	public void setAddmachineContentType(String addmachineContentType) {
		this.addmachineContentType = addmachineContentType;
	}

	public String getAddmachineFileName() {
		return addmachineFileName;
	}

	public void setAddmachineFileName(String addmachineFileName) {
		this.addmachineFileName = addmachineFileName;
	}

	public List<DJNR> getDjnrList() {
		return djnrList;
	}

	public void setDjnrList(List<DJNR> djnrList) {
		this.djnrList = djnrList;
	}

	public Float getNowSbNh() {
		return nowSbNh;
	}

	public void setNowSbNh(Float nowSbNh) {
		this.nowSbNh = nowSbNh;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public int[] getArrayId() {
		return arrayId;
	}

	public void setArrayId(int[] arrayId) {
		this.arrayId = arrayId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<String> getWorkPositionlist() {
		return workPositionlist;
	}

	public void setWorkPositionlist(List<String> workPositionlist) {
		this.workPositionlist = workPositionlist;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public List<ProcessinforFuLiao> getFlList() {
		return flList;
	}

	public void setFlList(List<ProcessinforFuLiao> flList) {
		this.flList = flList;
	}

	public List<ProcessSaveLog> getPsaveLogList() {
		return psaveLogList;
	}

	public void setPsaveLogList(List<ProcessSaveLog> psaveLogList) {
		this.psaveLogList = psaveLogList;
	}

	public List<TaSopGongwei> getGongweiList() {
		return gongweiList;
	}

	public void setGongweiList(List<TaSopGongwei> gongweiList) {
		this.gongweiList = gongweiList;
	}

	public List<ProcessWlqr> getListProcesswlqr() {
		return ListProcesswlqr;
	}

	public void setListProcesswlqr(List<ProcessWlqr> listProcesswlqr) {
		ListProcesswlqr = listProcesswlqr;
	}

	public List<BaoYangRecord> getByRecordList() {
		return byRecordList;
	}

	public void setByRecordList(List<BaoYangRecord> byRecordList) {
		this.byRecordList = byRecordList;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getShowCloumn() {
		return showCloumn;
	}

	public void setShowCloumn(String showCloumn) {
		this.showCloumn = showCloumn;
	}

	public String getJsonList() {
		return jsonList;
	}

	public void setJsonList(String jsonList) {
		this.jsonList = jsonList;
	}

	public String getShowText() {
		return showText;
	}

	public void setShowText(String showText) {
		this.showText = showText;
	}
	
}
