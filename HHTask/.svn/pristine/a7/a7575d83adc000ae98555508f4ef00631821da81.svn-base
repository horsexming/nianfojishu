package com.task.action.zhaobiao;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhaobiao.CaoZuoServer;
import com.task.entity.Machine;
import com.task.entity.PassReal;
import com.task.entity.Users;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.MD5;
import com.task.util.MKUtil;
import com.tast.entity.zhaobiao.Baofei;
import com.tast.entity.zhaobiao.QueXian;
import com.tast.entity.zhaobiao.Zh_CaozuoDengji;
import com.tast.entity.zhaobiao.Zh_CaozuoEmp;
import com.tast.entity.zhaobiao.Zh_caozuo;
import com.tast.entity.zhaobiao.Zh_shebei;

public class CaoZuoAction extends ActionSupport {
	private CaoZuoServer caoZuoServer;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List list;
	private List listAll;
	//
	private Zh_shebei zhShebei;
	private Zh_CaozuoDengji zhCaozuoDengji;
	private Zh_CaozuoEmp zhCaozuoEmp;
	private ProcessTemplate processTemplate;// /ProcessTe
	private Zh_caozuo zhCaozuo;
	//
	private String pageStatus;
	private String pageName;
	private Integer pageId;
	private String[] dengji;
	private Users userss;
	private Baofei baofei;
	

	//-----------------------------三检  管理    标示贴报废原因-----------------------------------------
	public String listBaofei() {
		if (baofei != null) {
			ActionContext.getContext().getSession().put("baofei", baofei);
		} else {
			baofei = (Baofei) ActionContext.getContext().getSession().get(
					"baofei");
		}
		Object[] object = caoZuoServer.listBaofei(baofei, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<Baofei>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("caoZuoAction!listBaofei.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listBaofei";
	}
	public String addbaofei() {
		caoZuoServer.addbaofei(baofei);
		errorMessage="添加成功";
		return "addbaofei";
	}
	public String toUpdatbaofei() {
		baofei=caoZuoServer.ByIdBaofei(baofei.getId());
		return "toUpdatbaofei";
	}
	public String updatebaofei() {
		caoZuoServer.updatebaofei(baofei);
		errorMessage="修改成功";
		return "toUpdatbaofei";
	}
	public String deleteBaofei() {
		caoZuoServer.deleteBaofei(baofei);
		errorMessage="删除成功！";
		url="caoZuoAction!listBaofei.action";
		return ERROR;
	}

	public void listBaofeiname() {
		list=caoZuoServer.listBaofeiname(pageStatus);
		MKUtil.writeJSON(list);	
}

	//--------------------------------------------------密码重置-----------------------------------------------
	// --------------------------------------------------密码重置-----------------------------------------------
	/*
	 * 密码重置 类表所有用户
	 */
	public String listUsers() {
		if (userss != null) {
			ActionContext.getContext().getSession().put("userss", userss);
		} else {
			userss = (Users) ActionContext.getContext().getSession().get(
					"userss");
		}
		Object[] object = caoZuoServer.listUsers(userss, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<Users>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("caoZuoAction!listUsers.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listUsers";
	}

	public String chongzhi() {
		userss = caoZuoServer.ByIdUsers(userss.getId());
		caoZuoServer.updateUsers(userss);
		return "chongzhi";
	}

	// ---------------------------------------------------------------------------------------------------------------

	// 综合
	public String listCaoZuoHuiZong() {
		if (zhCaozuo != null) {
			ActionContext.getContext().getSession().put("zhCaozuo", zhCaozuo);
		} else {
			zhCaozuo = (Zh_caozuo) ActionContext.getContext().getSession().get(
					"zhCaozuo");
		}
		Object[] object = caoZuoServer.listCaoZuoHuiZong(zhCaozuo, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<Zh_caozuo>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("caoZuoAction!listCaoZuoHuiZong.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listCaoZuoHuiZong";
	}

	public String listCaoZuo() {
		if (zhShebei != null) {
			ActionContext.getContext().getSession().put("zhShebei", zhShebei);
		} else {
			zhShebei = (Zh_shebei) ActionContext.getContext().getSession().get(
					"zhShebei");
		}
		Object[] object = caoZuoServer.listCaoZuo(zhShebei, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<Zh_shebei>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("caoZuoAction!listCaoZuo.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "listCaoZuo";
	}

	// 下拉工位编号
	public void listmachinegongwei() {
		list = caoZuoServer.listmachinegongwei();
		MKUtil.writeJSON(list);
	}

	// 下拉设备编号
	public void listshebeibianhao() {
		listAll = caoZuoServer.listshebeibianhao(pageStatus);
		List newList = new ArrayList();
		for (int i = 0; i < listAll.size(); i++) {
			Machine machine = (Machine) listAll.get(i);
			machine.setEquChanges(null);
			machine.setMachines(null);
			newList.add(machine);
		}
		MKUtil.writeJSON(newList);
	}

	// 得到设备名称
	public void listshebeiname() {
		listAll = caoZuoServer.listshebeibianhao(pageStatus, pageName);
		// ASM-01 5009044
//		List newList1 = new ArrayList();
//		for (int i = 0; i < listAll.size(); i++) {
//			Machine machine = (Machine) listAll.get(i);
//			machine.setEquChanges(null);
//			machine.setMachines(null);
//			newList1.add(machine);
//		}
//		MKUtil.writeJSON(newList1);
		MKUtil.writeJSON(listAll);
	}

	// 得到工名称
	public void listshebeigongxu() {
		list = caoZuoServer.listshebeigongxu(pageStatus, pageName);
		List newList2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			ProcessTemplate processTemplate = (ProcessTemplate) list.get(i);
			processTemplate.setProcardTemplate(null);
			processTemplate.setTaSopGongwei(null);
			newList2.add(processTemplate);
		}
		MKUtil.writeJSON(newList2);
	}

	public String addshebei() {
		caoZuoServer.addshebei(zhShebei);
		return "addshebei";
	}

	public String todengji() {
		zhShebei = caoZuoServer.ByIdZhshebei(zhShebei.getId());
		list = caoZuoServer.listDengji(zhShebei.getId());
		return "tozhCaozuoDengji";
	}

	public String addzhCaozuoDengji() {
		zhShebei = caoZuoServer.ByIdZhshebei(zhCaozuoDengji.getShebeiId());
		zhShebei.setStatus("设备操作等价添加完成");
		caoZuoServer.updateshebei(zhShebei);

		caoZuoServer.addzhCaozuoDengji(zhCaozuoDengji);
		return "addzhCaozuoDengji";
	}

	public String tozhCaozuoEmp() {
		zhShebei = caoZuoServer.ByIdZhshebei(zhShebei.getId());
		list = caoZuoServer.listzhCaozuoEmp(zhShebei.getId());
		listAll = caoZuoServer.listDengji(zhShebei.getId());
		return "tozhCaozuoEmp";
	}

	public void listEmp() {
		list = caoZuoServer.listEmp(pageName);
		List newList2 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Users user = (Users) list.get(i);
			user.setWorklogClass(null);
			user.setModuleFunction(null);
			newList2.add(user);
		}
		MKUtil.writeJSON(newList2);
	}

	public String addzhCaozuoEmp() {

		caoZuoServer.addzhCaozuoEmp(zhCaozuoEmp);
		zhShebei = caoZuoServer.ByIdZhshebei(zhCaozuoEmp.getShebeiId());
		zhShebei.setStatus("添加操作员工");
		caoZuoServer.updateshebei(zhShebei);
		zhCaozuoDengji = caoZuoServer.byIdEmp(zhCaozuoEmp.getDengjiId());
		/**
		 * 
		 * 
		 */
		Zh_caozuo newzhCaozuo = new Zh_caozuo();
		newzhCaozuo.setGongweiId(zhShebei.getGongweiId());
		newzhCaozuo.setShebeiId(zhShebei.getShebeiId());
		newzhCaozuo.setShebeiname(zhShebei.getShebeiname());
		newzhCaozuo.setShebeigongxuName(zhShebei.getShebeigongxuName());

		newzhCaozuo.setNameId(zhCaozuoEmp.getNameId());
		newzhCaozuo.setName(zhCaozuoEmp.getName());
		newzhCaozuo.setCaozuodengji(zhCaozuoDengji.getCaozuodengji());
		// 操作时长
		newzhCaozuo.setCaozuoshichang("0");
		caoZuoServer.addzhCao(newzhCaozuo);
		return "addzhCaozuoEmp";
	}

	public String toupdatezhShebei() {
		zhShebei = caoZuoServer.ByIdZhshebei(zhShebei.getId());
		return "toupdatezhShebei";
	}

	public String updateshebei() {
		caoZuoServer.updateshebei(zhShebei);
		return "updateshebei";
	}

	public String deletezhShebei() {
		caoZuoServer.deletezhShebei(zhShebei);
		return "deletezhShebei";
	}

	public String deletezhCaozuoDengji() {
		caoZuoServer.deletezhCaozuoDengji(zhCaozuoDengji);
		return "deletezhCaozuoDengji";
	}

	public String deletezhCaozuoEmp() {
		caoZuoServer.deletezhCaozuoEmp(zhCaozuoEmp);
		return "deletezhCaozuoEmp";
	}
		public String addshebeiEmp() {
			zhShebei.setStatus("设备操作等级添加完成");
			caoZuoServer.addshebei(zhShebei);
			caoZuoServer.adddengji(dengji,zhShebei.getId());
			return "addshebeiEmp";
		}
		public String deletezhShebeiEmp() {
			caoZuoServer.deletezhShebei(zhShebei);
			zhCaozuoDengji.setShebeiId(zhShebei.getId());
			caoZuoServer.deletezhCaozuoDengji(zhCaozuoDengji);
			return "deletezhShebeiEmp";
		}
	
	
	

	public CaoZuoServer getCaoZuoServer() {
		return caoZuoServer;
	}

	public void setCaoZuoServer(CaoZuoServer caoZuoServer) {
		this.caoZuoServer = caoZuoServer;
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

	public Zh_shebei getZhShebei() {
		return zhShebei;
	}

	public void setZhShebei(Zh_shebei zhShebei) {
		this.zhShebei = zhShebei;
	}

	public Zh_CaozuoDengji getZhCaozuoDengji() {
		return zhCaozuoDengji;
	}

	public void setZhCaozuoDengji(Zh_CaozuoDengji zhCaozuoDengji) {
		this.zhCaozuoDengji = zhCaozuoDengji;
	}

	public Zh_CaozuoEmp getZhCaozuoEmp() {
		return zhCaozuoEmp;
	}

	public void setZhCaozuoEmp(Zh_CaozuoEmp zhCaozuoEmp) {
		this.zhCaozuoEmp = zhCaozuoEmp;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

	public Zh_caozuo getZhCaozuo() {
		return zhCaozuo;
	}

	public void setZhCaozuo(Zh_caozuo zhCaozuo) {
		this.zhCaozuo = zhCaozuo;
	}

	public String[] getDengji() {
		return dengji;
	}

	public void setDengji(String[] dengji) {
		this.dengji = dengji;
	}
	
	public Users getUserss() {
		return userss;
	}

	public void setUserss(Users userss) {
		this.userss = userss;
	}
	public Baofei getBaofei() {
		return baofei;
	}
	public void setBaofei(Baofei baofei) {
		this.baofei = baofei;
	}

}
