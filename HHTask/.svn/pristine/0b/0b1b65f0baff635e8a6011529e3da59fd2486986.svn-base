package com.task.action.pro;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.pro.ProjectManageServer;
import com.task.entity.project.BaomiOperateLog;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectTime;
import com.task.util.MKUtil;

@SuppressWarnings("serial")
public class ProjectManageAction extends ActionSupport {

	private ProjectManageServer projectManageServer;// Server层
	private ProjectManage projectManage;// 对象
	private ProjectTime projectTime;// 对象
	private List<ProjectManage> projectManageList;// 集合
	private List<Object> list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String[] otherName;
	private BaomiOperateLog baomiOperateLog;
	private List<BaomiOperateLog> bmoLogList;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private int[] userIds;
	/***
	 * 添加立项信息
	 * 
	 * 
	 */
	public String addProjectManage() {
		try {
			projectManageServer.addProjectManage(projectManage, attachment,
					attachmentFileName,otherName,userIds);
//			setUrl("ProjectManage_findPMByCondition.action?cpage=" + cpage + "&total="
//					+ total+"&pageStatus="+pageStatus);
			errorMessage = "添加立项信息成功!";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "添加失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 删除立项信息
	 * 
	 * 
	 */
	public String delProjectManage() {
//		projectManage = projectManageServer.findProjectManage(id);
		try {
			projectManageServer.delProjectManage(id);
			errorMessage = "删除成功!";
			url = "ProjectManage_findPMByCondition.action?cpage=" + cpage + "&total="
					+ total+"&pageStatus="+pageStatus;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "删除失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 修改立项信息
	 * 
	 * 
	 */
	public String updateProjectManage() {
		// projectManage = projectManageServer.findProjectManage(id);
		try {
			projectManageServer.updateProjectManage(projectManage,attachment,attachmentFileName,otherName,userIds);
			errorMessage = "修改成功!";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "修改失败,原因:" + e.getMessage();
		}
		setUrl("ProjectManage_findProjectManage.action?pageStatus="+pageStatus+"&id=" + projectManage.getId());
		return ERROR;
	}

	/***
	 * 核算填报完成，修改立项状态为“核算”
	 * 
	 * 
	 */
	public String updateProManForHs() {
		projectManage = projectManageServer.afindProjectManage(id);
		try {
			projectManage.setStatus("核算");
			projectManageServer.update(projectManage);
			errorMessage = "提交成功!";
			url = "ProjectManage_findHsPro.action";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "提交失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 查询立项信息
	 * 
	 * @param id
	 */
	public String findProjectManage() {
		projectManage = projectManageServer.afindProjectManage(id);
		if (projectManage == null) {
			errorMessage = "不存在您要查询的项目立项信息!";
			return ERROR;
		}

		if (pageStatus != null && "update".equals(pageStatus)) {
			return "ProjectManage_update";// 修改
		} else if (pageStatus != null && "hs".equals(pageStatus)) {
			return "ProjectManage_hs";// 核算填报
		}
		return "ProjectManage_detail";// 明细
	}

	/***
	 * 查询立项信息（分页、条件查询）
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String findPMByCondition() {
		if (projectManage != null) {
			ActionContext.getContext().getSession().put("projectManage",
					projectManage);
		} else {
			projectManage = (ProjectManage) ActionContext.getContext()
					.getSession().get("projectManage");
		}
		Object[] object = projectManageServer.findPMByCondition(projectManage,
				Integer.parseInt(cpage), pageSize,pageStatus);
		if (object != null && object.length > 0) {
			projectManageList = (List<ProjectManage>) object[0];
			if (projectManageList != null && projectManageList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectManage_findPMByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "ProjectManage_list";
	}

	/***
	 * 项目立项信息审批
	 * 
	 * @return
	 */
	public String findPMForAudit() {
		Object[] object = projectManageServer.findPMForAudit(Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			projectManageList = (List<ProjectManage>) object[0];
			if (projectManageList != null && projectManageList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectManage_findPMByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "暂时没有需要审核的内容!";
			}
		}
		return "ProjectManage_list_audit";
	}

	/***
	 * 查询所有待核算的项目
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findHsPro() {
		if (projectManage != null) {
			ActionContext.getContext().getSession().put("projectManage",
					projectManage);
		} else {
			projectManage = new ProjectManage();
			projectManage.setAduitStatus("同意");
			projectManage.setStatus("立项");
		}
		Object[] object = projectManageServer.findPMByCondition(projectManage,
				Integer.parseInt(cpage), pageSize,pageStatus);
		if (object != null && object.length > 0) {
			projectManageList = (List<ProjectManage>) object[0];
			if (projectManageList != null && projectManageList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectManage_findHsPro.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "ProjectManage_hsList";
	}

	/***
	 * 查询项目对应的时间表
	 * 
	 * @param proId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findDeptProTime() {
		list = projectManageServer.afindDeptProTime(id);
		if (pageStatus != null && "show".equals(pageStatus)) {// 展示时间表
			return "PorDateTime_findAll";
		}
		return "PorDateTime_update";
	}

	/**
	 * 指派各部门填报录入时间
	 * 
	 * @param proTime
	 */
	public void addProTime() {
		try {
			projectManageServer.updateProTime(projectTime);
			MKUtil.writeJSON(true, "", null);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "指派失败,原因:" + e.getMessage();
			MKUtil.writeJSON(true, errorMessage, null);
		}
	}

	/***
	 * 根据项目id以及类别编号查询时间表并完成该类别
	 * 
	 * @param proId
	 *            项目id
	 * @param pageStatus
	 *            类别编号
	 */
	public String updateProTimeForFinal() {
		Map<Integer, Object>map=projectManageServer.updateProTimeForFinal(id, pageStatus);
		//return "findQuotedPrice";
		boolean b=Boolean.parseBoolean(map.get(1).toString());
		String msg=null;
		if(map.get(2)!=null){
			 msg=map.get(2).toString();
		}
		if(b){
			MKUtil.writeJSON("提交成功");
		}else{
			MKUtil.writeJSON(msg);
		}
		
		return null;
	}

	/***
	 * 查询所有的项目信息(输出Json)
	 */
	@SuppressWarnings("unchecked")
	public void findAllProMan() {
		List<ProjectManage> list = projectManageServer.findAllProMan();
		for (ProjectManage projectManage : list) {
			projectManage.setProjectTimeSet(null);
		}
		MKUtil.writeJSON(list);
	}
	/**
	 * 删除某个文件
	 * @return
	 */
	public void delwenjian(){
		try {
			boolean bool =	projectManageServer.delwenjian(id);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			MKUtil.writeJSON(false);
		}
		
	}
	
	public String baomiLogShow(){
		if (baomiOperateLog != null) {
			ActionContext.getContext().getSession().put("baomiOperateLog",
					baomiOperateLog);
		    } else {//用来保持分页时带着查询条件
		    	baomiOperateLog = (BaomiOperateLog) ActionContext.getContext().getSession().get("baomiOperateLog");
		      }
		Map<Integer, Object> map = projectManageServer.findBmlogByCondition(
				baomiOperateLog, Integer.parseInt(cpage), pageSize);
			bmoLogList = (List<BaomiOperateLog>) map.get(1);// 显示页的技能系数列表
			if (bmoLogList != null & bmoLogList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProjectManage_baomiLogShow.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "baomiLog_show";
	}
	/**
	 * 弥补保密日志
	 * ProjectManage_mibuBaomi.action
	 */
	public void mibuBaomi(){
		projectManageServer.mibuBaomi();
	}
	
	public ProjectManageServer getProjectManageServer() {
		return projectManageServer;
	}

	public void setProjectManageServer(ProjectManageServer projectManageServer) {
		this.projectManageServer = projectManageServer;
	}

	public ProjectManage getProjectManage() {
		return projectManage;
	}

	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}

	public List<ProjectManage> getProjectManageList() {
		return projectManageList;
	}

	public void setProjectManageList(List<ProjectManage> projectManageList) {
		this.projectManageList = projectManageList;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
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

	public ProjectTime getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(ProjectTime projectTime) {
		this.projectTime = projectTime;
	}

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String[] getOtherName() {
		return otherName;
	}

	public void setOtherName(String[] otherName) {
		this.otherName = otherName;
	}

	public BaomiOperateLog getBaomiOperateLog() {
		return baomiOperateLog;
	}

	public void setBaomiOperateLog(BaomiOperateLog baomiOperateLog) {
		this.baomiOperateLog = baomiOperateLog;
	}

	public List<BaomiOperateLog> getBmoLogList() {
		return bmoLogList;
	}

	public void setBmoLogList(List<BaomiOperateLog> bmoLogList) {
		this.bmoLogList = bmoLogList;
	}

	public int[] getUserIds() {
		return userIds;
	}

	public void setUserIds(int[] userIds) {
		this.userIds = userIds;
	}
	
}
