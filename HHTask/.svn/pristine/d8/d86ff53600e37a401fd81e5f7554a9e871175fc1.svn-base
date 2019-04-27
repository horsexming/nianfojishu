package com.task.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.InsRecordService;
import com.task.Server.InsScopeService;
import com.task.Server.InsTemplateService;
import com.task.Server.UserServer;
import com.task.entity.Users;
import com.task.entity.android.InsScope;
import com.task.entity.android.InsTemplate;
import com.task.entity.android.OsScope;
import com.task.entity.android.OsTemplate;
import com.task.entity.sop.BuHeGePin;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.util.MKUtil;

/**
 * 
 * @author 马凯
 * 
 */
@SuppressWarnings("serial")
public class InsTemplateAction extends ActionSupport {
	private InsTemplateService insTemplateService;
	private InsScopeService insScopeService;
	private InsRecordService insRecordService;
	private UserServer userService;
	private InsTemplate t;
	private OsTemplate t1;
	private String markId;
	private List<String> markIdList;
	private List<OsTemplate> t1List;
	private List<OsScope> scList;
	private List<InsScope> l;// 巡检项
	private List<InsTemplate> ts;
	private List<String> processNolist;
	private String	gongxuNum;
	private String dept;
	private String zhonglei;
	
	private String errorMessage;
	private String successMessage;// 成功信息

	private String usercode;
	private String password;
	private String jsonText;
	private String status;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private int index = 0;
	
	
	public String addInput() {
		return "addInput";//InsTemplate_addInput.jsp
	}
	public void findAllmarkidlist(){
		try {
			markIdList = insTemplateService.findAllmarkidlist(zhonglei);
			if(markIdList!=null && markIdList.size()>0){
				MKUtil.writeJSON(markIdList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
		
	}
	
	public void findprocardBymarkId() {
		try {
			ProcardTemplate pt = (ProcardTemplate) insTemplateService
					.findprocardBymarkId(markId);
			processNolist = insTemplateService.findprocessNoList(markId);
			List<Object> objlist = new ArrayList<Object>();
			if (pt != null) {
				objlist.add(pt);
			}
			if(processNolist!=null && processNolist.size()>0){
				objlist.add(processNolist);
			}
			
			if (objlist != null && objlist.size() > 0) {
				MKUtil.writeJSON(objlist);
			}
		} catch (Exception e) {
			MKUtil.writeJSON("error");
		}

	}

	public String add() {
		try {
			Map session = ActionContext.getContext().getSession();
			Users user = (Users) session.get(TotalDao.users);
			t.setUsername(user.getName());
			insTemplateService.add(t);
			MKUtil.writeJSON(true, "添加成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加失败:" + e.getMessage(), null);
			e.printStackTrace();
		}
		return null;
	}

	public String update() {
		try {
			Map session = ActionContext.getContext().getSession();
			Users user = (Users) session.get(TotalDao.users);
			t.setUsername(user.getName());
			insTemplateService.update(t);
			MKUtil.writeJSON(true, "修改成功", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "修改失败:" + e.getMessage(), null);
			e.printStackTrace();
		}
		return null;
	}

	public String updateInput() {
		t = insTemplateService.get(t);
		l = insScopeService.get(t);
		if (l != null && l.size() > 0) {
			index = l.size();
		}
		return "updateInput";
	}

	// 条件查询
	public String showList() {
		if (t != null) {
			ActionContext.getContext().getSession().put("t", t);
		} else {
			t = (InsTemplate) ActionContext.getContext().getSession().get("t");
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		map = insTemplateService.findInsTemplateByCondition(t, Integer
				.parseInt(cpage), pageSize);
		ts = (List<InsTemplate>) map.get(1);
		if (ts != null && ts.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("InsTemplate_showList.action");
			return "list";
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
			return ERROR;
		}
	}

	public String list() {
		if (t1 != null) {
			ActionContext.getContext().getSession().put("t1", t1);
		} else {
			t1 = (OsTemplate) ActionContext.getContext().getSession().get("t1");
		}
		Object[] object = insTemplateService.list(t1, Integer.parseInt(cpage),
				pageSize,status);
		if (object != null && object.length > 0) {
			t1List = (List<OsTemplate>) object[0];
			if (t1List != null && t1List.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("InsTemplate_list.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";//InsTemplate_list.jsp
	}

	public String listdel() {
		Object[] object = insTemplateService.list(t1, Integer.parseInt(cpage),
				pageSize,status);
		status = "del";
		if (object != null && object.length > 0) {
			t1List = (List<OsTemplate>) object[0];
			if (t1List != null && t1List.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("InsTemplate_listdel.action?status="+status);
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return "list";
	}

	public String getData() {

		if (!userService.login(usercode, password)) {
			MKUtil.writeJSON(false, "工号或者密码错误", null);
			return null;
		}
		try {
			ts = insTemplateService.getData();
			// MKUtil.writeJSON(true, "数据下载成功", ts);

			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("success", true);
			maps.put("message", "数据下载成功");
			maps.put("data", ts);
			String JSONStr = JSON.toJSONString(maps,
					SerializerFeature.WriteDateUseDateFormat);

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(JSONStr);
			response.getWriter().close();

		} catch (Exception e) {
			MKUtil.writeJSON(false, "数据下载失败", null);
			e.printStackTrace();
		}
		return null;
	}

	public String delete() {

		try {
			insTemplateService.delete(t);
			errorMessage = "删除成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorMessage = "删除失败";
		}

		return "deleteInsTemplate";

	}
	public void gettuzhi(){
		try {
			List<ProcessTemplateFile> filelist = insTemplateService.getjytuzhi(markId, gongxuNum);
			if(filelist!=null && filelist.size()>0){
				MKUtil.writeJSON(filelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	public void gettuzhi1(){
		try {
			List<ProcessTemplateFile> filelist = insTemplateService.getjytuzhi1(markId);
			if(filelist!=null && filelist.size()>0){
				MKUtil.writeJSON(filelist);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	//根据件号 工序号 得到工序名
	public void getgongxuName(){
		try {
			String gongxuName = insTemplateService.getgongxuName(markId, gongxuNum);
			if(gongxuName!=null && !"".equals(gongxuName)){
				MKUtil.writeJSON(gongxuName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	public InsTemplateService getInsTemplateService() {
		return insTemplateService;
	}

	public void setInsTemplateService(InsTemplateService insTemplateService) {
		this.insTemplateService = insTemplateService;
	}

	public InsTemplate getT() {
		return t;
	}

	public void setT(InsTemplate t) {
		this.t = t;
	}

	public UserServer getUserService() {
		return userService;
	}

	public void setUserService(UserServer userService) {
		this.userService = userService;
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

	public List<InsTemplate> getTs() {
		return ts;
	}

	public void setTs(List<InsTemplate> ts) {
		this.ts = ts;
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

	public InsRecordService getInsRecordService() {
		return insRecordService;
	}

	public void setInsRecordService(InsRecordService insRecordService) {
		this.insRecordService = insRecordService;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<InsScope> getL() {
		return l;
	}

	public void setL(List<InsScope> l) {
		this.l = l;
	}

	public InsScopeService getInsScopeService() {
		return insScopeService;
	}

	public void setInsScopeService(InsScopeService insScopeService) {
		this.insScopeService = insScopeService;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public OsTemplate getT1() {
		return t1;
	}

	public void setT1(OsTemplate t1) {
		this.t1 = t1;
	}

	public List<OsTemplate> getT1List() {
		return t1List;
	}

	public void setT1List(List<OsTemplate> t1List) {
		this.t1List = t1List;
	}

	public List<OsScope> getScList() {
		return scList;
	}

	public void setScList(List<OsScope> scList) {
		this.scList = scList;
	}

	public List<String> getMarkIdList() {
		return markIdList;
	}

	public void setMarkIdList(List<String> markIdList) {
		this.markIdList = markIdList;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public List<String> getProcessNolist() {
		return processNolist;
	}

	public void setProcessNolist(List<String> processNolist) {
		this.processNolist = processNolist;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGongxuNum() {
		return gongxuNum;
	}
	public void setGongxuNum(String gongxuNum) {
		this.gongxuNum = gongxuNum;
	}
	public String getZhonglei() {
		return zhonglei;
	}
	public void setZhonglei(String zhonglei) {
		this.zhonglei = zhonglei;
	}
	
}
