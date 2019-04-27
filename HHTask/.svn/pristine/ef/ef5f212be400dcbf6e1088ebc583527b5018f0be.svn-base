package com.task.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.IAnnualLeaveService;
import com.task.Server.kq.IDepartmentService;
import com.task.entity.AnnualLeave;
import com.task.entity.Overtime;
import com.task.entity.Users;
import com.task.util.Util;

/**
 * 
 * @author zjs
 *
 */
public class AnnualLeaveAction  extends ActionSupport {
	private IAnnualLeaveService als;
	private IDepartmentService ids;
	private AnnualLeave al;
	private List list;
	private List listoveT;
	private String errorMessage;
	private String nameStr;
	private String jobNumStr;
	private String deptIDStr;
	private List deptList;
	private int id;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private Overtime askForLeave;
	private String pagestatus;
	private String tag;
	/***
	 * 清空换休时间
	 */
	public String qinkongdept() {
		als.qinkongdept();
		errorMessage="清空成功!!!";
		url="annualLeave!listhuanxiudept.action?al.status=";
		return ERROR;
	}
	
	/***
	 * 主管查看本部门的换休管理
	 */
	public String listhuanxiudept() {
		if (al != null) {
			ActionContext.getContext().getSession().put("al", al);
		} else {
			al = (AnnualLeave) ActionContext.getContext().getSession()
					.get("al");
		}
		Users user = (Users) Util.getLoginUser();
		al.setDept(user.getDept());
		Object[] object = als.listhuanxiu(al, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List<AnnualLeave>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave!listhuanxiudept.action?al.status=");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listhuanxiudept";
	}
	//------------------------------------------
	/***
	 * 
	 * 个人换休明细
	 * 
	 * @return
	 */
	public String listhuanxiumingxi() {
		Users user = (Users) Util.getLoginUser();
		//als.gengxinhuanxiu(user.getCode());
		
		al = als.BynameHuanxiu(user.getCode());
		if(al!=null && null!=al.getSurplus()){
			BigDecimal bg = new BigDecimal(al.getSurplus());
			al.setSurplus(bg.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		}
		list = als.listhuanxiumingxi(user.getCode());
		listoveT = als.overTimeList(user.getCode());
		return "listhuanxiumingxi";//gerenhuanxiumingxi.jsp
	}
	//-----
	/***
	 * 换休    下面查看所有加班记录
	 * 
	 */
	public String mingxijiaban() {
		Object[] object = als.mingxijiaban(al.getJobNum(),askForLeave, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List<Overtime>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave!mingxijiaban.action?al.jobNum="+al.getJobNum());
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "mingxijiaban";//askForLeave_list_huanxiu.jsp
	}

	/***
	 * 
	 * 个人年休明细
	 * 
	 * @return
	 */
	public String gerennianxiumingxi() {
		Users user = (Users) Util.getLoginUser();
		al = als.BynameNianxiu(user.getCode());
		list = als.ByAskForLeaveCarId(user.getCode());
		return "gerennianxiumingxi";//AnnualLeaveAskForLeave_list.jsp
	}
	
	public String gengxin() {
		//als.gengxinhuanxiu();
		//url="annualLeave!listhuanxiu.action";
		return "gengxin";//annualLeave!listhuanxiu.action
	}
	
		
	public String listhuanxiu() {
		//更新换休数据
//		Object[] object = als.findAnnualLeaveCondition1(null, Integer
//				.parseInt(cpage), pageSize);
//		if (object != null && object.length > 0) {
//			list = (List) object[0];
//			if (list != null && list.size() > 0) {
//				deptList = ids.queryAllDepartment();
//				int count = (Integer) object[1];
//				int pageCount = (count + pageSize - 1) / pageSize;
//				this.setTotal(pageCount + "");
//				this.setUrl("annualLeave_listhuanxiu.action");
//				errorMessage = null;
//			} else
//				errorMessage = "没有找到你要查询的内容,请检查后重试!";
//		} else
//			errorMessage = "没有找到你要查询的内容,请检查后重试!";
//		return "listhuanxiu";
		if (al != null) {
			ActionContext.getContext().getSession().put("al", al);
		} else {
			al = (AnnualLeave) ActionContext.getContext().getSession()
					.get("al");
		}
		Object[] object = als.listhuanxiu(al, Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List<AnnualLeave>) object[0];
			for (Object object2 : list) {
				al =  (AnnualLeave) object2;
				if(al!=null && null!=al.getSurplus()){
					BigDecimal bg = new BigDecimal(al.getSurplus());
					al.setSurplus(bg.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
				}
			}
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave!listhuanxiu.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listhuanxiu";//annualLeave_list_huanxiu.jsp
	}
	
	/**
	 * 年休添加后跳转显示
	 * @return
	 */
	public String list() {
		Object[] object = als.findAnnualLeaveCondition(null, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				deptList = ids.queryAllDepartment();
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave_list.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";//annualLeave_index.jsp
	}

	
	
	public String queryAnnualLeaveByCondition() {
		Map map = new HashMap();
		if (nameStr != null && !nameStr.equals("")) {
			map.put("nameStr", nameStr);
		}
		if (deptIDStr != null && !deptIDStr.equals("选择部门")) {
			map.put("deptIDStr", deptIDStr);
		}
		if (jobNumStr != null && !jobNumStr.equals("")) {
			map.put("jobNumStr", jobNumStr);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("annualLeave", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"annualLeave");
			} else
				ActionContext.getContext().getSession().remove("annualLeave");
		}
		Object[] object = als.findAnnualLeaveCondition(map, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				deptList = ids.queryAllDepartment();
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave_queryAnnualLeaveByCondition.action");
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "index";
	}

	public String initAdd() {
		deptList = ids.queryAllDepartment();
		return "success";
	}

	public String add() {
		if (al != null) {
			als.add(al);
		}
		return "redirectIndex";//annualLeave_list.action
	}

	public String BatchAnnualLeave() {
		als.batchAddAnnualLeave();
		return "redirectIndex";
	}

	public String initUpdate() {
		if (id != 0) {
			al = als.getAnnualLeaveById(id);
			deptList = ids.queryAllDepartment();
		}
		return "success";
	}

	public String update() {
		if (al != null) {
			als.update(al);
		}
		return "redirectIndex";
	}

	public String del() {
		if (id != 0) {
			al = als.getAnnualLeaveById(id);
			als.delete(al);
		}
		return "redirectIndex";
	}
	
	public void daochu() {
		if("nian".equals(tag)){
			als.daochuAll("年休");
		}else if("huan".equals(tag)){
			als.daochuAll("换休");
		}else {
			als.daochuAll(null);
		}
	}

	public String mingxi() {
		// list=als.ByCode(al.getJobNum());
		Object[] object = als.ByCode(al.getJobNum(), Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + ""); 
				this.setUrl("annualLeave!mingxi.action?al.jobNum="+al.getJobNum());
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "mingxi";//AskForLeave_list.jsp
	}
	public String mingxihuanxiu() {
		// list=als.ByCode(al.getJobNum());
		Object[] object = als.ByCodehuanxiu(al.getJobNum(), Integer.parseInt(cpage),
				pageSize);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave!mingxihuanxiu.action?al.jobNum="+al.getJobNum());
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "mingxi";//AskForLeave_list.jsp
	}

	public String listAnnualLeave() {
		if (al != null) {
			ActionContext.getContext().getSession().put("al", al);
		} else {
			al = (AnnualLeave) ActionContext.getContext().getSession()
					.get("al");
		}
		Object[] object = als.listAnnualLeave(al, Integer.parseInt(cpage),
				pageSize,pagestatus);
		if (object != null && object.length > 0) {
			list = (List<AnnualLeave>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("annualLeave!listAnnualLeave.action?pagestatus="+pagestatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "index";
	}

	public IAnnualLeaveService getAls() {
		return als;
	}

	public void setAls(IAnnualLeaveService als) {
		this.als = als;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getNameStr() {
		return nameStr;
	}

	public void setNameStr(String nameStr) {
		this.nameStr = nameStr;
	}

	public String getJobNumStr() {
		return jobNumStr;
	}

	public void setJobNumStr(String jobNumStr) {
		this.jobNumStr = jobNumStr;
	}

	public String getDeptIDStr() {
		return deptIDStr;
	}

	public void setDeptIDStr(String deptIDStr) {
		this.deptIDStr = deptIDStr;
	}

	public List getDeptList() {
		return deptList;
	}

	public void setDeptList(List deptList) {
		this.deptList = deptList;
	}

	public IDepartmentService getIds() {
		return ids;
	}

	public void setIds(IDepartmentService ids) {
		this.ids = ids;
	}

	public AnnualLeave getAl() {
		return al;
	}

	public void setAl(AnnualLeave al) {
		this.al = al;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Overtime getAskForLeave() {
		return askForLeave;
	}

	public void setAskForLeave(Overtime askForLeave) {
		this.askForLeave = askForLeave;
	}

	public String getPagestatus() {
		return pagestatus;
	}

	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List getListoveT() {
		return listoveT;
	}

	public void setListoveT(List listoveT) {
		this.listoveT = listoveT;
	}

}
