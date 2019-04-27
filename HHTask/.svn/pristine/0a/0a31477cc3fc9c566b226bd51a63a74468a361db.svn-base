package com.task.action;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.jna.platform.unix.X11.XUnmapEvent;
import com.task.Server.InsRecordService;
import com.task.Server.UserServer;
import com.task.entity.Users;
import com.task.entity.android.InsRecord;
import com.task.entity.android.InsRecordScope;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsRecord;
import com.task.entity.android.OsRecordScope;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.gzbj.ProcessAndMeasuring;
import com.task.entity.shizhi.SkillScore;
import com.task.entity.sop.BreakSubmit;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcessInfor;
import com.task.util.MKUtil;
import com.tast.entity.zhaobiao.Waigoujianpinci;
import com.tast.entity.zhaobiao.WaigoujianpinciZi;

public class InsRecordAction extends ActionSupport {
	private InsRecordService insRecordService;
	private UserServer userService;

	private InsRecord record;
	private List<OsScope> osList;
	private List<InsRecord> list;
	private List<InsScope> islist;
	private List<InsRecordScope> isrList;
	private ProcessInfor processInfor;
	private List<ProcessInfor> processList;
	private String usercode;
	private String password;
	private String jsonText;
	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private Integer id;
	private List<Waigoujianpinci> xjbzlist;
	private WaigoujianpinciZi xujianpingci;
	private OsTemplate osTemplate;
	private InsTemplate insTemplate;
	private String isHege;// 是否合格（yes no）
	private List<OsRecordScope> osRecordScopeList;
	private List<OsRecord> osRecordList;
	private Float jcCount;
	private String status;//
	private String tag;
	private BreakSubmit breakSubmit;
	private Procard procard;
	private List<ProcessAndMeasuring> pamList;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String errorMessage;
	private String successMessage;// 成功信息

	public String submit() {
		if (!userService.login(usercode, password)) {
			MKUtil.writeJSON(false, "工号或者密码错误", null);
			return null;
		}
		Users u = userService.findUserByCode(usercode);
		try {
			List<InsRecord> list = JSON.parseArray(jsonText, InsRecord.class);

			for (InsRecord insRecord : list) {
				insRecord.setGroupDate(insRecord.getNowDate().substring(0, 11));
				insRecord.setUsername(u.getName());
			}
			insRecordService.add(list);
			MKUtil.writeJSON(true, "数据提交成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "数据提交失败", null);
		}
		return null;
	}

	public String get() {
		try {
			record.setGroupDate(new String(record.getGroupDate().getBytes(
					"ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		list = insRecordService.get(record);
		return "show";
	}

	public String list() {

		Object[] object = insRecordService.list(pageSize, Integer
				.parseInt(cpage));
		if (object != null && object.length > 0) {
			list = (List<InsRecord>) object[0];
			if (list != null && list.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("InsRecord_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";
	}

	public String getToXjList() {
		if (processInfor != null) {
			ActionContext.getContext().getSession().put("processInfor",
					processInfor);
		} else {// 用来保持分页时带着查询条件
			processInfor = (ProcessInfor) ActionContext.getContext()
					.getSession().get("processInfor");
		}
		Map<Integer, Object> map = insRecordService.getToXjList(processInfor,
				startDate, endDate, Integer.parseInt(cpage), pageSize);
		processList = (List<ProcessInfor>) map.get(1);// 显示页的技能系数列表
		if (processList != null & processList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("InsRecord_getToXjList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "InsRecord_toXjLis";
	}

	public String getXjProcess() {
		processInfor = insRecordService.getProcessById(id,breakSubmit);
		if ((processInfor == null || !("已领").equals(processInfor.getStatus())) && !"sqjy".equals(status)) {
			errorMessage = "对不起该工序没有在生产!";
			return "error";
		}
		
		osTemplate = insRecordService.updateOsTemplate(id);
		osList = insRecordService.getcheckList(id);
		xjbzlist = insRecordService.findAllxjbzlist();
		xujianpingci = insRecordService.findxunjianpici(processInfor,
				osTemplate);
		pamList = insRecordService.pamListByProcessName(processInfor.getProcessName());
		if(breakSubmit!=null && breakSubmit.getId()!=null){
			breakSubmit = insRecordService.findbreaksubmitById(breakSubmit.getId());
			if(xujianpingci!=null){
				xujianpingci.setChoujian(breakSubmit.getTjbreakcount());
			}
		}
		return "checkProcess";
	}

	// 更新标识贴
	public String xJProcess() {
		String msg = insRecordService.xJProcess(processInfor,
				osRecordList, jcCount,breakSubmit);
		url = "InsRecord_getToXjList.action";
		if (msg.equals("true")) {
			errorMessage = "检验完成!";
		} else {
			errorMessage = msg;
		}
		if("sqjy".equals(status)){
			setUrl("ProcardAction!findbreaksubmitList.action?pageStatus="+tag);
		}
		return "error";
	}

	// 巡检模板绑定巡检标准
	public String OstbdXjbz() {
		errorMessage = insRecordService.Ostbdxjbz(osTemplate);
		if ("true".equals(errorMessage)) {
			return "getXjProcess";
		}
		return "error";
	}
	//外购申检跳转页面；
	public String towgsqjy(){
		Object[] obj = insRecordService.getOsTemplate(id);
		breakSubmit = (BreakSubmit) obj[0];
		procard = (Procard) obj[1];
		osTemplate = (OsTemplate) obj[2];
		osList = (List<OsScope>) obj[3];
		xujianpingci = (WaigoujianpinciZi) obj[4];
		xjbzlist = insRecordService.findAllxjbzlist();
 		return "wgcheckProcess";
	}
	
	public InsRecordService getInsRecordService() {
		return insRecordService;
	}

	public void setInsRecordService(InsRecordService insRecordService) {
		this.insRecordService = insRecordService;
	}

	public UserServer getUserService() {
		return userService;
	}

	public void setUserService(UserServer userService) {
		this.userService = userService;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJsonText() {
		return jsonText;
	}

	public void setJsonText(String jsonText) {
		this.jsonText = jsonText;
	}

	public InsRecord getRecord() {
		return record;
	}

	public void setRecord(InsRecord record) {
		this.record = record;
	}

	public List<InsRecord> getList() {
		return list;
	}

	public void setList(List<InsRecord> list) {
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

	public ProcessInfor getProcessInfor() {
		return processInfor;
	}

	public void setProcessInfor(ProcessInfor processInfor) {
		this.processInfor = processInfor;
	}

	public List<ProcessInfor> getProcessList() {
		return processList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setProcessList(List<ProcessInfor> processList) {
		this.processList = processList;
	}

	public OsTemplate getOsTemplate() {
		return osTemplate;
	}

	public void setOsTemplate(OsTemplate osTemplate) {
		this.osTemplate = osTemplate;
	}

	public String getIsHege() {
		return isHege;
	}

	public void setIsHege(String isHege) {
		this.isHege = isHege;
	}

	public List<OsRecordScope> getOsRecordScopeList() {
		return osRecordScopeList;
	}

	public void setOsRecordScopeList(List<OsRecordScope> osRecordScopeList) {
		this.osRecordScopeList = osRecordScopeList;
	}

	public List<OsScope> getOsList() {
		return osList;
	}

	public void setOsList(List<OsScope> osList) {
		this.osList = osList;
	}

	public List<InsScope> getIslist() {
		return islist;
	}

	public void setIslist(List<InsScope> islist) {
		this.islist = islist;
	}

	public List<InsRecordScope> getIsrList() {
		return isrList;
	}

	public void setIsrList(List<InsRecordScope> isrList) {
		this.isrList = isrList;
	}

	public InsTemplate getInsTemplate() {
		return insTemplate;
	}

	public void setInsTemplate(InsTemplate insTemplate) {
		this.insTemplate = insTemplate;
	}

	public Float getJcCount() {
		return jcCount;
	}

	public void setJcCount(Float jcCount) {
		this.jcCount = jcCount;
	}

	public List<Waigoujianpinci> getXjbzlist() {
		return xjbzlist;
	}

	public void setXjbzlist(List<Waigoujianpinci> xjbzlist) {
		this.xjbzlist = xjbzlist;
	}

	public WaigoujianpinciZi getXujianpingci() {
		return xujianpingci;
	}

	public void setXujianpingci(WaigoujianpinciZi xujianpingci) {
		this.xujianpingci = xujianpingci;
	}

	public List<OsRecord> getOsRecordList() {
		return osRecordList;
	}

	public void setOsRecordList(List<OsRecord> osRecordList) {
		this.osRecordList = osRecordList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BreakSubmit getBreakSubmit() {
		return breakSubmit;
	}

	public void setBreakSubmit(BreakSubmit breakSubmit) {
		this.breakSubmit = breakSubmit;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<ProcessAndMeasuring> getPamList() {
		return pamList;
	}

	public void setPamList(List<ProcessAndMeasuring> pamList) {
		this.pamList = pamList;
	}

	

}
