package com.task.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.EquipmentService;
import com.task.Server.MachineDayYZSJServer;
import com.task.entity.Econdition;
import com.task.entity.Machine;
import com.task.entity.MachineDayYZSJ;
import com.task.entity.Maintenance;
import com.task.entity.OaAppDetail;
import com.task.entity.OaApplyForm;
import com.task.entity.Parts;
import com.task.entity.Responsibilities;
import com.task.entity.Users;
import com.task.entity.ProcardEss;
import com.task.util.Util;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;

public class EquipmentAction extends ActionSupport {
	private EquipmentService equipmentService;
	private MachineDayYZSJServer mdyServer;
	private ProcardEss procard;
	private Machine machine;
	private Parts parts;
	private Maintenance maintenance;
	private Econdition econdition;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// ID
	private String pageStatus;// 页面状态
	private List<Maintenance> repairList;
	private List<Maintenance> repairList1;
	private List<Maintenance> repairList2;
	private List<Maintenance> repairList3;
	private List<Maintenance> repairList4;
	private List<Maintenance> reList;
	private List<Econdition> econditionList;
	private List partslist;
	private List list;
	private String[] partName;
	private String[] pictureNo;
	private String[] num;
	private String[] unit;
	private String[] more;
	private String status;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String partsradio;
	private int sum;
	private int summ;
	private int sumxiufu;
	private Date date1;
	private Date date2;
	private String month1;
	private String month2;
	private float sumli;
	private String fileName;
	private OaAppDetail oaAppDetail;
	private OaApplyForm oaApplyForm;
	private Responsibilities responsibilities;
	private String tag;
	
	public String updateresponsibilities() {
		equipmentService.updateresponsibilities(responsibilities);
		errorMessage="修改成功!!!";
		return "updateresponsibilities";
	}
	public String toUpdateresponsibilities() {
		responsibilities=equipmentService.ByIdResponsibilities(responsibilities.getId());
		return "toUpdateresponsibilities";
	}
	public String deleteresponsibilities() {
		equipmentService.deleteresponsibilities(responsibilities);
		errorMessage="删除成功!!!";
		url="EquipmentAction!listresponsibilities.action";
		return ERROR;
	}
	public String addresponsibilities() {
		if (responsibilities!=null) {
			equipmentService.addresponsibilities(responsibilities);
			errorMessage="添加成功!!!";
		}
		return "addresponsibilities";
	}
	/***
	 * 
	 * @return
	 */
	public String listresponsibilities() {
		if (responsibilities != null) {
			ActionContext.getContext().getSession().put("responsibilities", responsibilities);
		} else {
			responsibilities = (Responsibilities) ActionContext.getContext()
					.getSession().get("responsibilities");
		}
		Object[] object = equipmentService.listresponsibilities(responsibilities, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<Responsibilities>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("EquipmentAction!listresponsibilities.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listresponsibilities";
	}
	//--------------------------------------------------------------------------------------------------
	// 报修条码查询
	public String findAll() {
		List machinelist = equipmentService.findAllByStatus(machine
				.getBarcode());
		if (machinelist != null && machinelist.size() > 0) {
			machine = (Machine) machinelist.get(0);
			if (machine != null) {
				// 创建报修对象
				Maintenance maintenance = new Maintenance();
				maintenance.setWorkArea(machine.getWorkArea());
				maintenance.setWorkPosition(machine.getWorkPosition());
				maintenance.setNo(machine.getNo());
				int count = equipmentService.findAlly(maintenance);
				if (count > 0) {
					errorMessage = "该设备已报修,请勿重复保修!谢谢!";
					machine = null;
					return "findAllRe";
				}
			}
			list = equipmentService.selectPeopleForZhipai(null);
			return "findAllRe";
		} else {
			errorMessage = "没有查询到您要报修的设备!请检查后重试!";
			return ERROR;
		}

	}
	// 报修条码查询
	public String findAllId() {
		machine = equipmentService.findAllByStatus(machine.getId());
		if (machine != null) {
			// 创建报修对象
			Maintenance maintenance = new Maintenance();
			maintenance.setWorkArea(machine.getWorkArea());
			maintenance.setWorkPosition(machine.getWorkPosition());
			maintenance.setNo(machine.getNo());
			int count = equipmentService.findAlly(maintenance);
			if (count > 0) {
				errorMessage = "该设备已报修,请勿重复保修!谢谢!";
				machine = null;
				return ERROR;
			}else {
				list = equipmentService.selectPeopleForZhipai(null);
				return "baoxiuId";
			}
		} else {
			errorMessage = "没有查询到您要报修的设备!请检查后重试!";
			return ERROR;
		}
	}

	// 入库条码查询
	@SuppressWarnings("unchecked")
	public String pcocardfindAll() {
		List pcocardList = equipmentService.findAllwarehousing(procard
				.getBarcode());
		if (pcocardList != null && pcocardList.size() > 0) {
			procard = (ProcardEss) pcocardList.get(0);
			return "findAllfindAllwarehousing";
		} else {
			errorMessage = "没有查询你要入库的条码!请检查后重试!";
			return ERROR;
		}

	}

	// 更新
	public String pcocardupdte() {
		procard = equipmentService.findAssetBId(procard.getId());
		if (procard != null) {
			equipmentService.update(procard);
		}
		return "procarder";
	}

	// 设备故障修复率

	public String findsum() {
		sum = equipmentService.findAlls(date1, date2);
		sumxiufu = equipmentService.findAllss(date1, date2);
		sumli = Float.parseFloat(String.format("%.2f", sumxiufu * 0.1F / sum
				* 1000));

		return "findAlls";

	}

	// 设备完好率
	public String findas() {
		Object[] object = equipmentService.findAllys(null, month1, month2,
				Integer.parseInt(cpage), pageSize, status);
		if (object != null && object.length > 0) {
			econditionList = (List<Econdition>) object[0];
			if (econditionList != null && econditionList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("EquipmentAction!findas.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAllas";

	}

	// 维修条码查询
	@SuppressWarnings("unchecked")
	public String findAllber() {
		List brcoder = equipmentService.findAllbarcoder(maintenance
				.getBarcode());
		if (brcoder != null && brcoder.size() > 0) {
			Maintenance maintenance = (Maintenance) brcoder.get(0);
			if (maintenance != null) {
				// 获得登录用户信息
				Users loginUser = (Users) ActionContext.getContext()
						.getSession().get(TotalDao.users);
				maintenance.setStatus("维修中");
				maintenance.setRepairMan(loginUser.getName());// 维修人
				boolean bool = equipmentService.update(maintenance);// 更新报修信息
				pageStatus = "guzhang";
				return "upremarkMaintenance";//EquipmentAction!findAlll.action?pageStatus=${pageStatus}
			} else {
				errorMessage = "没有查询到你扫描的条码信息!请检查!";
			}
		} else {
			errorMessage = "此设备!正在受理中!请扫描其它维修条码！";
		}
		return ERROR;
	}

	// 设备报修添加
	public String findAllother() {
		list = equipmentService.selectPeopleForZhipai(null);
		return "otherker";
	}

	public String add() {
		int count = equipmentService.findAlly(maintenance);
		if (count < 1) {
			if (partsradio != null && partsradio.equals("yes")) {
				maintenance.setRepairMan(maintenance.getRepairMan());
			}
			boolean bool = equipmentService.add(maintenance, pageStatus);
			machine = null;
			if (pageStatus != null && pageStatus.length() > 0
					&& "barcode".equals(pageStatus)) {
				return "findAllRe";
			} else {
				return "findallother";
			}
		} else {
			errorMessage = "该设备已报修,请勿重复保修!谢谢!";
			return ERROR;
		}

	}
	//licong Add
	public String add1() {
		int count = equipmentService.findAlly(maintenance);
		if (count < 1) {
			if (partsradio != null && partsradio.equals("yes")) {
				maintenance.setRepairMan(maintenance.getRepairMan());
			}
			if(equipmentService.add(maintenance, pageStatus)){
				errorMessage = "报修成功！";
				url = "EquipmentAction!findAlll.action";
				return ERROR;
			}else {
				errorMessage = "报修失败！";
				return ERROR;
			}
		} else {
			errorMessage = "该设备已报修,请勿重复保修!谢谢!";
			return ERROR;
		}
	}

	// 导出Excel月度统计
	public String macrepairexcel() {
		fileName = equipmentService.excelMaintenance(date1, date2);
		if (fileName != null && !"no".equals(fileName)) {
			return "generateexcel";
		} else {
			errorMessage = "导出数据出错，请检查后重试!";
			return ERROR;
		}

	}

	// 修复反馈
	public String remarkMaintenance() {
		maintenance = equipmentService.findAssetById(id);
		list = equipmentService.selectPeopleForZhipai(null);

		return "remarker";

	}

	// 确认修复
	public String upremarkMaintenance() {
		Maintenance oldrepair = equipmentService.findAssetById(id);

		if (oldrepair != null) {

			if ("故障".equals(oldrepair.getStatus())
					|| "故障指派".equals(oldrepair.getStatus())) {
				oldrepair.setRepairMan(maintenance.getRepairMan());
				oldrepair.setStatus("故障");
				equipmentService.update(oldrepair);
				pageStatus = "guzhangzhipai";
				errorMessage = "zhipai";
				return "updateZhipai";
			} else if ("修复待验证".equals(oldrepair.getStatus())) {
				oldrepair.setPersontime(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));
				oldrepair.setStatus("正常");
				equipmentService.update(oldrepair);
				// 更新申报明细状态为"采购中"
				float sc = 0;
				try {
					sc = (Util.getDateDiff(Util.DateToString(oldrepair.getAlarmTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss", Util.getDateTime(), "yyyy-MM-dd HH:mm:ss"))/1000/60/60;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MachineDayYZSJ mdy=mdyServer.findmdybyno(oldrepair.getNo(), null);
				mdyServer.update(mdy, sc, "weixiu");
				equipmentService.updateOaAppDetail(oldrepair.getBarcode());

				return "addrepairsingler";
			} else {
				oldrepair.setTimetorepair(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(new Date()));
				oldrepair.setFaultReason(maintenance.getFaultReason());
				oldrepair.setCountdowntime(120);
				oldrepair.setStatus("修复待验证");
				if (partsradio != null && partsradio.equals("yes")) {
					for (int i = 0; i < pictureNo.length; i++) {
						Parts parts = new Parts();
						parts.setPartName(partName[i]);
						parts.setNum(num[i]);
						parts.setPictureNo(pictureNo[i]);
						parts.setUnit(unit[i]);
						parts.setMore(more[i]);
						parts.setBarcode(oldrepair.getBarcode());
						parts.setMaintenance(oldrepair);
						equipmentService.addParts(parts);
					}

				}

			}
			boolean bool = equipmentService.update(oldrepair);
		}
		return "upremarkMaintenance";

	}

	// 删除
	public String delSubmitt() {
		maintenance = equipmentService.findAssetById(id);
		if (maintenance != null) {
			equipmentService.delete(maintenance);
			return "addmchine";
		}
		errorMessage = "不存在您删除的数据,请检查!";
		return ERROR;
	}

	// 修改
	public String initupmaintenance() {
		maintenance = equipmentService.findAssetById(id);
		list = equipmentService.selectPeopleForZhipai(null);
		if (pageStatus != null && pageStatus.length() > 0) {
			if (pageStatus.equals("guzhang")
					|| pageStatus.equals("guzhangzhipai")) {
				if ("故障".equals(maintenance.getStatus())
						|| "故障指派".equals(maintenance.getStatus())) {
					list = equipmentService.selectPeopleForZhipai("设备维修");
					return "remark";
				}
			}
		}
		return "updaterr";
	}

	public String updatemaintenance() {

		equipmentService.update(maintenance);
		String persontime=maintenance.getPersontime();
		
		return "updatermaintenancer";
	}

	// 查询所有
	@SuppressWarnings("unchecked")
	public String findAlll() {
		if (maintenance != null) {
			ActionContext.getContext().getSession().put("maintenance",
					maintenance);
			ActionContext.getContext().getSession().put("date1", date1);
			ActionContext.getContext().getSession().put("date2", date2);

		} else {
			maintenance = (Maintenance) ActionContext.getContext().getSession()
					.get("maintenance");
			date1 = (Date) ActionContext.getContext().getSession().get("date1");
			date2 = (Date) ActionContext.getContext().getSession().get("date2");

		}
		//计算维修时长
		String msg = equipmentService.calculateUpdateTime();
		
		Object[] object = null;
		if (pageStatus != null && pageStatus.equals("null")) {
			pageStatus = null;
		}
		if (errorMessage != null && errorMessage.equals("null")) {
			errorMessage = null;
		}
		if (pageStatus == null) {
			repairList1 = equipmentService.findAll("故障");
			repairList2 = equipmentService.findAll("维修中");
			repairList3 = equipmentService.findAll("故障指派");
			repairList4 = equipmentService.findAll("修复待验证");
			maintenance.setStatus("正常");
			object = equipmentService.findAll(maintenance, Integer
					.parseInt(cpage), pageSize, pageStatus, date1, date2);

		} else if (pageStatus != null
				&& "findallMaintenance".equals(pageStatus)) {
			repairList1 = equipmentService.findAllByStatuss("故障");
			repairList2 = equipmentService.findAllByStatuss("维修中");
			repairList3 = equipmentService.findAllByStatuss("故障指派");
			repairList4 = equipmentService.findAllByStatuss("修复待验证");
			maintenance.setStatus("正常");
			object = equipmentService.findAll(maintenance, Integer
					.parseInt(cpage), pageSize, pageStatus, date1, date2);

		} else {
			if ("guzhang".equals(pageStatus)) {
				object = equipmentService.findAll(maintenance, 0, 0,
						"weixiuzhong", date1, date2);//
				if(object!=null&&object[0]!=null){
					repairList1 = (List<Maintenance>) object[0];
				}else{
					repairList1 = new ArrayList<Maintenance>();
				}
			}
			if ("guzhangzhipai".equals(pageStatus)) {
				if (errorMessage != null && "zhipai".equals(errorMessage)) {
					repairList1 = equipmentService.findAllByStatuss("故障");
					maintenance = new Maintenance();
					maintenance.setStatus("故障指派");
				} else {
					object = equipmentService.findAll(maintenance, 0, 0,
							"guzhang", date1, date2);// 
					repairList1 = (List<Maintenance>) object[0];
				}
			}
			object = equipmentService.findAll(maintenance, Integer
					.parseInt(cpage), pageSize, pageStatus, date1, date2);
		}
		if (object != null && object.length > 0) {
			repairList = (List<Maintenance>) object[0];
			if (repairList != null && repairList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("EquipmentAction!findAlll.action?pageStatus="
						+ pageStatus + "&errorMessage=" + errorMessage);
			}
		}
		if (pageStatus != null && "findallMaintenance".equals(pageStatus)
				|| "findByCon".equals(pageStatus)) {
			return "findAllRer";//queryEquipment.jsp
		}
		return "findallMaintenance";
	}

	// 零件查询管理
	public String findAllparts() {
		if (parts != null) {
			ActionContext.getContext().getSession().put("parts", parts);
		} else {
			parts = (Parts) ActionContext.getContext().getSession()
					.get("parts");
		}
		Object[] object = equipmentService.findPartsByCondition(parts, date1,
				date2, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			partslist = (List<Parts>) object[0];
			if (partslist != null && partslist.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (date1 != null && date2 != null) {
					this.setUrl("EquipmentAction!findAllparts.action?date1="
							+ Util.DateToString(date1, "yyyy-MM-dd HH:mm:ss")
							+ "&date2="
							+ Util.DateToString(date2, "yyyy-MM-dd HH:mm:ss"));
				} else {
					this.setUrl("EquipmentAction!findAllparts.action");
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAllparts";

	}

	public String condition() {
		maintenance = equipmentService.findAssetById(id);
		if (maintenance != null) {
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("guzhang".equals(pageStatus)) {
					maintenance.setStatus("维修中");
				} else if ("weixiuzhong".equals(pageStatus)) {
					maintenance.setStatus("修复待验证");

				} else {
					errorMessage = "参数异常!请检查后重试!";
					return ERROR;
				}
				equipmentService.update(maintenance);
				return "upremarkMaintenance";
			} else {
				errorMessage = "不存在您要查询的数据!";
			}

		}
		return ERROR;

	}

	// 明细
	public String findByclintMangagement() {
		maintenance = equipmentService.findAssetById(id);
		if (maintenance != null) {
			partslist = equipmentService.findAllparts(maintenance.getBarcode());
		}
		return "findByclientManagement";
	}

	public List getPartslist() {
		return partslist;
	}

	public void setPartslist(List partslist) {
		this.partslist = partslist;
	}

	public String findmaintenance() {
		equipmentService.update(maintenance);
		return "findmaintenance";
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getId() {
		return id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	public List<Maintenance> getRepairList() {
		return repairList;
	}

	public List<Maintenance> getRepairList1() {
		return repairList1;
	}

	public List<Maintenance> getRepairList2() {
		return repairList2;
	}

	public List<Maintenance> getRepairList3() {
		return repairList3;
	}

	public List<Maintenance> getRepairList4() {
		return repairList4;
	}

	public List<Maintenance> getReList() {
		return reList;
	}

	public List getList() {
		return list;
	}

	public String getCpage() {
		return cpage;
	}

	public String getTotal() {
		return total;
	}

	public String getUrl() {
		return url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setRepairList(List<Maintenance> repairList) {
		this.repairList = repairList;
	}

	public void setRepairList1(List<Maintenance> repairList1) {
		this.repairList1 = repairList1;
	}

	public void setRepairList2(List<Maintenance> repairList2) {
		this.repairList2 = repairList2;
	}

	public void setRepairList3(List<Maintenance> repairList3) {
		this.repairList3 = repairList3;
	}

	public void setRepairList4(List<Maintenance> repairList4) {
		this.repairList4 = repairList4;
	}

	public void setReList(List<Maintenance> reList) {
		this.reList = reList;
	}

	public void setList(List list) {
		this.list = list;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Parts getParts() {
		return parts;
	}

	public void setParts(Parts parts) {
		this.parts = parts;
	}

	public String getPartsradio() {
		return partsradio;
	}

	public void setPartsradio(String partsradio) {
		this.partsradio = partsradio;
	}

	public int getSumxiufu() {
		return sumxiufu;
	}

	public void setSumxiufu(int sumxiufu) {
		this.sumxiufu = sumxiufu;
	}

	public float getSumli() {
		return sumli;
	}

	public void setSumli(float sumli) {
		this.sumli = sumli;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Econdition getEcondition() {
		return econdition;
	}

	public void setEcondition(Econdition econdition) {
		this.econdition = econdition;
	}

	public int getSumm() {
		return summ;
	}

	public void setSumm(int summ) {
		this.summ = summ;
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public List<Econdition> getEconditionList() {
		return econditionList;
	}

	public void setEconditionList(List<Econdition> econditionList) {
		this.econditionList = econditionList;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProcardEss getProcard() {
		return procard;
	}

	public void setProcard(ProcardEss procard) {
		this.procard = procard;
	}

	public String[] getPartName() {
		return partName;
	}

	public void setPartName(String[] partName) {
		this.partName = partName;
	}

	public String[] getPictureNo() {
		return pictureNo;
	}

	public void setPictureNo(String[] pictureNo) {
		this.pictureNo = pictureNo;
	}

	public String[] getNum() {
		return num;
	}

	public void setNum(String[] num) {
		this.num = num;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getUnit() {
		return unit;
	}

	public void setUnit(String[] unit) {
		this.unit = unit;
	}

	public String[] getMore() {
		return more;
	}

	public void setMore(String[] more) {
		this.more = more;
	}

	public OaAppDetail getOaAppDetail() {
		return oaAppDetail;
	}

	public void setOaAppDetail(OaAppDetail oaAppDetail) {
		this.oaAppDetail = oaAppDetail;
	}

	public OaApplyForm getOaApplyForm() {
		return oaApplyForm;
	}

	public void setOaApplyForm(OaApplyForm oaApplyForm) {
		this.oaApplyForm = oaApplyForm;
	}
	public Responsibilities getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(Responsibilities responsibilities) {
		this.responsibilities = responsibilities;
	}
	public MachineDayYZSJServer getMdyServer() {
		return mdyServer;
	}
	public void setMdyServer(MachineDayYZSJServer mdyServer) {
		this.mdyServer = mdyServer;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
}
