package com.task.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.LeaveServer;
import com.task.Server.WageServer;
import com.task.Server.android.processpush.OneMachineServer;
import com.task.Server.menjin.AccessEquipmentServer;
import com.task.Server.menjin.VisitServer;
import com.task.Server.onemark.OneLightServer;
import com.task.Server.sop.ProcardServer;
import com.task.entity.AskForLeave;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.android.processpush.OneMachine;
import com.task.entity.fin.UserMonthMoney;
import com.task.entity.menjin.Visit;
import com.task.entity.onemark.OneLight;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.util.MKUtil;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class SuspsomAction {
	private List<Procard> procardList;// 集合
	private String errorMessage;
	private String successMessage;
	private String gongwei;
	private OneMachine machine;
	private Procard procard;
	private AskForLeave askForLeave;
	private String ipAddress;
	private Visit visit;
	private Wage wage;
	private List<Visit> visitList;
	private List<ProcessTemplateFile> list;// 工艺图纸表
	private List<OneLight> listLight;// 一体机上的灯
	private List<AskForLeave> askList;// 请假列表集合
	private List<Wage> wageList;//工资
	private List<UserMonthMoney> userMonthMoneyList;//月度奖金
	private OneMachineServer machineServer;
	private OneLightServer lightServer;
	private AccessEquipmentServer accessEquipmentServer;
	private ProcardServer procardServer;
	private WageServer wageServer;//工资模板
	private LeaveServer leaveServer;// 
	private VisitServer visitServer;
	private List<String> list2;
	private List list3;
	private List listi = null;// 缺陷图纸
	private List<Map<String, Object>> listAll = null;
	private String gow;
	private String name;
	private String code;
	private String tag;
	private Integer id;
	private String pageStatus;// 页面状态

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	
	//考勤下载text
	public void text(){
//		errorMessage = KaoqjReadUtil.onAddConnection("192.168.0.153", 4370);
		//visitServer.findUsers();
	}
	//判断服务器是否有响应。并获取秘钥。
	public void receiveSecretKey(){
		MKUtil.writeJSON(true, "8c160649-4333-44db-bfa9-6fdf8992eccf", null);
	}
	
	//查看档案柜内容
	public String dangan() {
		Users users = Util.getLoginUser();
		if(users!=null){
			if(accessEquipmentServer.dangQuan(Util.getLoginUser().getId())){
				return "login_Dang_1";
			}else {
				ActionContext.getContext().getSession().put(
						TotalDao.users, null);
				return "login_Dang";
			}
		}else {
			return "login_Dang";
		}
	}
	
	// 来访查询
	public String visitFind() {
		visitList = visitServer.findVisitS();
		return "Visit_show";
	}

	// 弹出框登录
	public String denglu() {
		if (gow == null) {
			gow = "";
		}
		return "otherLogin_float";
	}
	
	// 选择页面
	public String xuanzhe() {
		return "oneMac_xuanzhe";
	}
	// 请假页面
	public String qingjia() {
		Object[] o = leaveServer.selectAllByLeavePage(askForLeave, Integer.parseInt(cpage), pageSize, pageStatus, startDate, endDate);
		if (o != null && o.length > 0) {
			askList = (List<AskForLeave>) o[0];
			if (askList != null && askList.size() > 0) {
				int count = (Integer) o[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SuspsomAction_qingjia.action?pageStatus=self");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "oneMac_ask";
	}
	// 奖金工资
	public String jiangjingongzi() {
		Object[] o = wageServer.findWageByCondition(wage, Integer.parseInt(cpage), pageSize, pageStatus);
		if (o != null && o.length > 0) {
			wageList = (List<Wage>) o[0];
			if (wageList != null && wageList.size() > 0) {
				int count = (Integer) o[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SuspsomAction_jiangjingongzi.action?pageStatus=print");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		userMonthMoneyList = procardServer.findByUserCodeMonthMoney(code);
		return "oneMac_wageandjangjin";
	}
	//积分
	public String jifen() {
		Object[] o = wageServer.findWageByCondition(wage, Integer.parseInt(cpage), pageSize, pageStatus);
		if (o != null && o.length > 0) {
			wageList = (List<Wage>) o[0];
			if (wageList != null && wageList.size() > 0) {
				int count = (Integer) o[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SuspsomAction_jiangjingongzi.action?pageStatus=print");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		userMonthMoneyList = procardServer.findByUserCodeMonthMoney(code);
		return "oneMac_wageandjangjin";
	}

	// 弹出框登录
	public String toOut() {
		return "logOff__1";
	}

	// 查询一体机对应的缺陷图纸
	public String quxianTuzhi() {
		if (ipAddress == null || ipAddress.equals("")) {
			errorMessage = "请选择一体机";
			return "quexiantuzhi_show";
		}
		gongwei = machineServer.gongw(ipAddress);
		if ("".equals(gongwei)) {
			errorMessage = "当前设备工位绑定为空！请绑定";
			return "quexiantuzhi_show";
		}
		list = machineServer.findquexiantuzhi(gongwei);
		if (list == null || list.size() <= 0) {
			errorMessage = "当前工位件号还未上传缺陷图纸!";
		}
		return "quexiantuzhi_show";
	}

	// 根据一体机编号查对应工位，根据工位查对应的已领工序
	public String selectGongxu() {
		// name = machineServer.name();
 		if (ipAddress == null || ipAddress.equals("")) {
			errorMessage = "请选择一体机";
			return "Su_show";
		}
		gongwei = machineServer.gongw(ipAddress);
		if ("".equals(gongwei)) {
			errorMessage = "当前设备工位绑定为空！请绑定";
			listAll = null;
			return "Su_show";
		}
		if (tag == null)
			tag = "";
		// 顶灯
		listLight = lightServer.findGongweiLight(ipAddress);
		// 工序
		listAll = machineServer.findgognxuInfor(gongwei, tag);
		if (listAll == null || listAll.size() <= 0) {
			errorMessage = "当前工位未开始生产,您可先领取工序!";
		}
		// 首检待检码
		list3 = machineServer.findDjm();

		gongwei = gongwei.replaceAll("'", "");
		String[] gongw = gongwei.split(",");
		if (gongw.length > 3) {
			name = "yes";
			gow = gongwei;
			gongwei = gongw[0] + "," + gongw[1] + "," + gongw[2] + ",....,"
					+ gongw[gongw.length - 3] + "," + gongw[gongw.length - 2]
					+ "," + gongw[gongw.length - 1];
		}
		if ("one".equals(tag))
			tag = "";
		else
			tag = "one";
		return "Su_show";///System/SOP/oneMachine/show_1.jsp
	}

	public String ling() {
		// machine = machineServer.byIp(loginIp);
		gow = machineServer.gongw(hqIp());
		gongwei = gow;
		return "gongwei_show";
	}

	// 显示当前工位 领工序信息
	public String findProcardByGwNum() {
		pageSize = 50;
		if (!"".equals(gongwei)) {
			if (procard != null) {
				ActionContext.getContext().getSession().put("Procards_",
						procard);
			} else {
				procard = (Procard) ActionContext.getContext().getSession()
						.get("Procards_");
			}
			// 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号
			Object[] o = procardServer.findProcardListByUserCard(procard,
					Integer.parseInt(cpage), pageSize, gongwei, "gongweiList",
					null);
			if (o != null && o.length > 0) {
				procardList = (List<Procard>) o[0];
				if (procardList != null && procardList.size() > 0) {
					int count = (Integer) o[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("SuspsomAction_findProcardByGwNum.action?pageStatus="+pageStatus);
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
				return "Procard_noCardList_1";
			}
		}
		errorMessage = "当前设备绑定工位为空";
		procardList = null;
		return "Procard_noCardList_1";
	}

	// 显示当前工位领工序信息
	public String findProcardByUserNum() {
		// 如果是无卡(员工卡)领料
		if (!"".equals(gongwei)) {
			// 根据卡号查询人员，得到人员对应工序的对应最小批次、已发卡的件号
			procardList = machineServer.findProcardListByGwCard(gongwei);
			return "Procard_noCardList_1";
		} else {
			// 领工序
			// procard = procardServer.findProcardByCardNum(cardNumber);
			// if (procard != null && "总成".equals(procard.getProcardStyle())) {
			// return "findProcardView";
			// } else {
			// errorMessage = "您的刷卡信息错误!";
			// }
		}
		return "error";
	}

	public String tuzhi() {
		ipAddress = hqIp();
		if (ipAddress != null && !"".equals(ipAddress)) {
			machine = machineServer.byIp(ipAddress);
			if (machine != null) {// 不为空
				list3 = machineServer.findPushgongweiById(machine.getId());
				if (list3 != null && list3.size() > 0) {// 如果工位存在
					list2 = machineServer.tuzhiLj(ipAddress);
					if (list2 != null && list2.size() > 0)
						errorMessage = null;
					else
						errorMessage = "当前工位没有可显示的图纸，请领工序后查看";
				}
			} else {
			}
		}
		return "tuzhi_show";
	}

	// 获取IP
	private String hqIp() {
		HttpServletRequest request = ServletActionContext.getRequest();
		// HttpServletResponse response = ServletActionContext.getResponse();
		// 获得真实ip
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
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

	public String getGongwei() {
		return gongwei;
	}

	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}

	public OneMachineServer getMachineServer() {
		return machineServer;
	}

	public void setMachineServer(OneMachineServer machineServer) {
		this.machineServer = machineServer;
	}

	public List<ProcessTemplateFile> getList() {
		return list;
	}

	public void setList(List<ProcessTemplateFile> list) {
		this.list = list;
	}

	public OneMachine getMachine() {
		return machine;
	}

	public void setMachine(OneMachine machine) {
		this.machine = machine;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public List<String> getList2() {
		return list2;
	}

	public void setList2(List<String> list2) {
		this.list2 = list2;
	}

	public List getList3() {
		return list3;
	}

	public void setList3(List list3) {
		this.list3 = list3;
	}

	public String getGow() {
		return gow;
	}

	public void setGow(String gow) {
		this.gow = gow;
	}

	public ProcardServer getProcardServer() {
		return procardServer;
	}

	public void setProcardServer(ProcardServer procardServer) {
		this.procardServer = procardServer;
	}

	public List<Map<String, Object>> getListAll() {
		return listAll;
	}

	public void setListAll(List<Map<String, Object>> listAll) {
		this.listAll = listAll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OneLight> getListLight() {
		return listLight;
	}

	public void setListLight(List<OneLight> listLight) {
		this.listLight = listLight;
	}

	public OneLightServer getLightServer() {
		return lightServer;
	}

	public void setLightServer(OneLightServer lightServer) {
		this.lightServer = lightServer;
	}

	public List getListi() {
		return listi;
	}

	public void setListi(List listi) {
		this.listi = listi;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Visit getVisit() {
		return visit;
	}

	public void setVisit(Visit visit) {
		this.visit = visit;
	}

	public List<Visit> getVisitList() {
		return visitList;
	}

	public void setVisitList(List<Visit> visitList) {
		this.visitList = visitList;
	}

	public VisitServer getVisitServer() {
		return visitServer;
	}

	public void setVisitServer(VisitServer visitServer) {
		this.visitServer = visitServer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AccessEquipmentServer getAccessEquipmentServer() {
		return accessEquipmentServer;
	}
	public void setAccessEquipmentServer(AccessEquipmentServer accessEquipmentServer) {
		this.accessEquipmentServer = accessEquipmentServer;
	}

	public AskForLeave getAskForLeave() {
		return askForLeave;
	}

	public void setAskForLeave(AskForLeave askForLeave) {
		this.askForLeave = askForLeave;
	}

	public List<AskForLeave> getAskList() {
		return askList;
	}

	public void setAskList(List<AskForLeave> askList) {
		this.askList = askList;
	}

	public LeaveServer getLeaveServer() {
		return leaveServer;
	}

	public void setLeaveServer(LeaveServer leaveServer) {
		this.leaveServer = leaveServer;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public Wage getWage() {
		return wage;
	}

	public void setWage(Wage wage) {
		this.wage = wage;
	}

	public List<Wage> getWageList() {
		return wageList;
	}

	public void setWageList(List<Wage> wageList) {
		this.wageList = wageList;
	}

	public List<UserMonthMoney> getUserMonthMoneyList() {
		return userMonthMoneyList;
	}

	public void setUserMonthMoneyList(List<UserMonthMoney> userMonthMoneyList) {
		this.userMonthMoneyList = userMonthMoneyList;
	}

	public WageServer getWageServer() {
		return wageServer;
	}

	public void setWageServer(WageServer wageServer) {
		this.wageServer = wageServer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the procard
	 */
	public Procard getProcard() {
		return procard;
	}
	/**
	 * @param procard the procard to set
	 */
	public void setProcard(Procard procard) {
		this.procard = procard;
	}

}
