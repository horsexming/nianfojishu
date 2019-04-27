package com.task.action.pro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.pro.ProjectPoolServer;
import com.task.entity.DeptNumber;
import com.task.entity.project.ProjectManageYfpd;
import com.task.entity.project.ProjectManageyf;
import com.task.entity.project.ProjectManageyfAgree;
import com.task.entity.project.ProjectManageyfEr;
import com.task.entity.project.ProjectManageyfUser;
import com.task.entity.project.ProjectPool;
import com.task.entity.project.YfUser;
import com.task.util.MKUtil;
import com.task.util.Util;

public class ProjectPoolAction extends ActionSupport{
	//projectPoolAction
	private ProjectPoolServer projectPoolServer;
	private ProjectPool projectPool;
	private List<ProjectPool> projectPoolList;
	private ProjectManageyf  projectManageyf;//
	private List<ProjectManageyf> projectManageyfList;//
	private List<ProjectManageyfUser> yfUserList;//研发负责人list
	private List<ProjectManageyfAgree> agreeList;//评分结果记录表
	private List<ProjectManageyfEr> erList;//报选项目审批表
	private List<ProjectManageYfpd> pdList;//人员明细
	private ProjectManageyfAgree agree;
	private ProjectManageyfUser yfUser;
	private YfUser yfuserMiddle;
	
	private int id;// id
	private int id2;// id
	private String ids;
	private String pageStatus;// 页面状态
	private Integer userId;
	private Integer proejctYfUserId;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	private File accessory;// 上传附件
	private String accessoryContentType;// 附件类型
	private String accessoryFileName;// 文件名称
	
	private String message;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private int count = 0;
	private String rootIdStr;
	private String tag;
	private ProjectManageyfEr er; //审批候选项目
	private String resultStatus; //审批结果
	private String status;
	private Integer store;
	private Integer weight;
	
	private String oldFiles;
	private String oldFileNames;
	/**
	 * 前往添加项目池
	 * @return
	 */
	public String toAddPool(){
		return "projectPool_toAdd";
	}
	
	public String addPool(){
		if(null==projectPool.getStartTime() || "".equals(projectPool.getStartTime())){
			projectPool.setStartTime(Util.getDateTime("yyyy-MM-dd"));
		}
		String msg = projectPoolServer.addPool(projectPool);
		if(msg.equals("true")){
			errorMessage="添加成功!";
			url = "projectPoolAction_poolShowList.action";
		}else{
			errorMessage = msg;
		}
		return "error";
	}
	
	/**
	 * 删除项目池
	 * @return
	 */
	public String delPool(){
		errorMessage = projectPoolServer.delProjectPool(id);
		setUrl("projectPoolAction_poolShowList.action?pageStatus="+pageStatus);
		return "error";
	}
	
	//显示项目池和主项目
	public String poolShowList(){
		if (projectPool != null) {
			ActionContext.getContext().getSession().put("projectPool",
					projectPool);
		} else {
			projectPool = (ProjectPool) ActionContext.getContext()
					.getSession().get("projectPool");
		}
		Object[] object = projectPoolServer.findPoolByCondition(projectPool,
				Integer.parseInt(cpage), pageSize,pageStatus);
		if (object != null && object.length > 0) {
			projectPoolList = (List<ProjectPool>) object[0];
			if (projectPoolList != null && projectPoolList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("projectPoolAction_poolShowList.action?pageStatus="+pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "Projectpool_list";
	}
	
	/**
	 * 编辑项目池和主项目
	 */
	public String toEditPool(){
		projectPool =projectPoolServer.getProjectPoolById(id);
		if(null==projectPool){
			url="projectPoolAction_poolShowList.action";
			return "error";
		}
		return "projectPool_toEditPool";
	}
	
	/**
	 * 编辑项目池
	 * @return
	 */
	public String editPool(){
		String result = projectPoolServer.editPool(projectPool);
		setUrl("projectPoolAction_poolShowList.action");
		if("success".equals(result)){
			//return "Projectpool_list";
			setErrorMessage("修改成功");
		}else{
			errorMessage = result;
		}
		return "error";
	}
	/**
	 * 获取个人研发项目
	 * @return
	 */
	public String selfProjectManageyfList(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (projectManageyf != null) {
			ActionContext.getContext().getSession().put("projectManageyf",
					projectManageyf);
		} else {
			projectManageyf = (ProjectManageyf) ActionContext.getContext()
					.getSession().get("projectManageyf");
		}
		Object[] object = projectPoolServer.findselfProjectmanageYf(projectManageyf,
				Integer.parseInt(cpage), pageSize,pageStatus);
		if (object != null && object.length > 0) {
			projectManageyfList = (List<ProjectManageyf>) object[0];
			if (projectManageyfList != null && projectManageyfList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("projectPoolAction_selfProjectManageyfList.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if("applychoose".equals(pageStatus)){
			return "ProjectManageyf_applychoose";
		}
		return "ProjectManageyf_self";
		
		
	}
	
	/**
	 * 前往 项目指派
	 * @return
	 */
	public String toPoolAssign(){
		yfUserList = projectPoolServer.getAssignUserList(id);
		projectManageyf = projectPoolServer.getProjectManageyfById(id); //查询项目信息，比较时间差
		return "ProjectManageyf_zhipai";
	}
	
	/**
	 * 指派
	 */
	public void projectManagerAssign(){
		Object[] objs = projectPoolServer.projectManageryfAssign(yfUser);
		
		MKUtil.writeJSON(true, objs[0].toString(), objs[1].toString());
	}
	
	/**
	 * 取消指派
	 */
	public void cancelAssion(){
		String result = projectPoolServer.cancelAssion(id);
		MKUtil.writeJSON(result);
	}
	
	/**
	 * 前往评分页面
	 * @return
	 */
	public String toProjectGrade(){
		//自己的项目显示
		projectManageyf = projectPoolServer.getProjectManageyfById(id);//项目id
		
		//显示其他项目
		projectManageyfList = projectPoolServer.getOtherPMyf(id);
		
		//显示已确认人列表
		agreeList = projectPoolServer.findProjectManageyfAgrees(id);
		
		if(null!=tag && "affirmGrade".equals(tag)){
			return "projectManageyf_affirmGrade";
		}
		return "projectManageyf_grade";
	}
	
	
	/**
	 * 提交分数
	 * @return
	 */
	public String submitProjectGrade(){
		String result = projectPoolServer.saveProjectStore(projectManageyf);
		if("success".equals(result)){
			errorMessage = "提交分数成功！";
			setUrl("projectPoolAction_selfProjectManageyfList.action");
		}else{
			errorMessage = result;
		}
		return "error";
	}
	
	/**
	 * 前往添加子项目页面
	 * @return
	 */
	public String toAddSubProject(){
		if("detail".equals(pageStatus)) {
			projectManageyf = projectPoolServer.getProjectManageyfById(id);
			return "projectmanageyf_detail";
		}
		//projectManageyf = projectPoolServer.getProjectManageyfById(id);
		//项目id  --隐藏
		//根据项目id和登录用户信息查看是参与人还是负责人
		yfUser =projectPoolServer.getProjectYfUserByprojectId(id);
		if(yfUser==null){
			errorMessage = "您没有权限访问此项目，谢谢";
			return ERROR;
		}
		if("gantt".equals(pageStatus)){
			projectManageyf = projectPoolServer.getProjectManageyfById(id);
			return "ProjectManageYf_ganttManage";//"ProjectManageYf_gantt";
		}
			
		return "ProjectManageyf_addSub";
	}
	
	/**
	 * 根据项目rootId查找所有的子项目
	 */
	public void findProjectManageyfByRootId(){
		int rootId = Integer.parseInt(rootIdStr);
		List<ProjectManageyf> list = projectPoolServer.findProjectManageyfByRootId(rootId);
		MKUtil.writeJSON(list);
	}
	
	public String findProjectByRootId(){
		projectManageyfList = projectPoolServer.findProjectwhereByRootId(id);
		return "ProjectManageyf_subStoreTable";
	}
	
	/**
	 * 根据项目fatherId查找所有的子项目
	 */
	public void getSubListById(){
		int id = Integer.parseInt(rootIdStr);
		List<ProjectManageyf> list = projectPoolServer.getSubListById(id);
		MKUtil.writeJSON(list);
	}
	/**
	 * 显示子项目甘特图
	 * @return
	 */
	public String showSubGantt(){//id
		return "ProjectManageYf_newGantt";
		//return "ProjectManageYf_subGantt";
	}
	/**
	 * 根据id查找项目
	 */
	public void findProjectManageyfById(){
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		MKUtil.writeJSON(projectManageyf);
	}
	
	/**
	 * 提交评分是否同意结果
	 * @return
	 */
	public String submitExamineResult(){
		message = projectPoolServer.submitExamineResult(agree);
		errorMessage = message;
		
		return "error";
	}
	
	/**
	 * 添加子项目或者修改项目
	 */
	public String saveAndUpdateYf(){
		Object[] obj =  projectPoolServer.saveAndUpdateYf(projectManageyf);
		errorMessage = obj[0].toString();
		projectManageyf = projectPoolServer.getProjectManageyfById(projectManageyf.getId());
		//id = ;
		yfUser = projectPoolServer.getProjectYfUserByprojectId(projectManageyf.getRootId());
		setUrl("projectPoolAction_toAddSubProject.action?id="+projectManageyf.getRootId()+"&pageStatus="+pageStatus);
		return "error";
	}
	
	public void saveOrUpdateYf(){
		Object[] obj = projectPoolServer.saveAndUpdateYf(projectManageyf);
		MKUtil.writeJSON(true, obj[0].toString(), obj[1]);
	}
	
	//删除子项目
	public String delProject(){
		ProjectManageyf projectYf = projectPoolServer.getProjectManageyfById(id2);
		errorMessage = projectPoolServer.delProject(projectYf.getId(),pageStatus);
		setUrl("projectPoolAction_toAddSubProject.action?id="+projectYf.getRootId()+"&pageStatus="+pageStatus);
		
		//项目池管理员删除主项目
		if(null!=pageStatus && "manage".equals(pageStatus)){
			MKUtil.writeJSON(errorMessage);
		}
		return "error";
	}
	
	//前往绑定参与人
	public String toProjectYfBindPlayer(){
		//根据项目获得参与人
		//yfUserList = projectPoolServer.findprojectYfUserByProId(id,null);
		erList = projectPoolServer.selectChooseList(id,null);
		return "ProjectManageyf_bindplayers";
	}
	
	/**
	 * 查找部门
	 */
	public void searchDept(){
		int rootId = Integer.parseInt(rootIdStr);
		List<DeptNumber> list = projectPoolServer.findDeptByRootId(rootId);
		MKUtil.writeJSON(list);
	}

	// ajax通过部门id获取部门下的人员
	public void getusers() {
		
		List list = projectPoolServer.getUsersByDeptId(id,ids);
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//绑定参与人
	public void bindPlayers(){
		String message[] = projectPoolServer.addProjectPlayers(userId, id);
		MKUtil.writeJSON(message);
	}
	
	/**
	 * 获取项目预选参与人
	 */
	public String getBindPlayers(){
		 yfUserList = projectPoolServer.findprojectYfUserByProId(id,id2);
		 if("get".equals(pageStatus)) {
			 
			 return "projectManageyf_participant";
		 }else {
			 MKUtil.writeJSON(yfUserList);
			 return "";
		 }
	}
	
	/**
	 * 根据项目id获得参与人列表 
	 */
	public String findBindPlayers(){
		yfUserList = projectPoolServer.getUserListByProId(id);
		
		if("get".equals(pageStatus)) {
			return "projectManageyf_participant";
		}else {
			MKUtil.writeJSON(yfUserList);
			return "";
		}
	}
	
	/**
	 * 直接指派为参与人员
	 */
	public void directBindPlayers(){
		String result = projectPoolServer.directBindPlayers(yfuserMiddle, id,weight);
		MKUtil.writeJSON(result);
	}
	
	/**
	 * 使用projectManageyfEr 直接指派参与人
	 */
	public void zhipaiPlayers() {
		errorMessage = projectPoolServer.zhipaiPlayers(id, userId, weight, pageStatus);
		MKUtil.writeJSON(errorMessage);
	}
	/**
	 * 取消项目的参与人员
	 */
	public void cancelBindPlayers(){
		String result = projectPoolServer.cancelBindPlayers(yfuserMiddle);
		MKUtil.writeJSON(result);
	}
	
	/**
	 * 取消项目参与人
	 */
	public void cancelPlayers(){
		String string = projectPoolServer.cancelPlayers(id2, id);//id proejctManageyf.id
		MKUtil.writeJSON(string);
	}
	
	/**
	 * 前往报选子项目页面
	 * @return
	 */
	public String toApplyChooseProject(){
		
		return "ProjectManageyf_applychoose";
	}
	
	/**
	 * 报选项目
	 * @return
	 */
	public String chooseSubProject(){
		message = projectPoolServer.chooseSubProject(id);
		url = "projectPoolAction_selfProjectManageyfList.action?pageStatus=applychoose";
		if("success".equals(message)){
			errorMessage = "申报成功！";
		}else{
			errorMessage = message;
		}
		return "error";
		//查询项目参与人
//		if (projectManageyf != null) {
//			ActionContext.getContext().getSession().put("projectManageyf",
//					projectManageyf);
//		} else {
//			projectManageyf = (ProjectManageyf) ActionContext.getContext()
//					.getSession().get("projectManageyf");
//		}
//		Object[] object = projectPoolServer.findselfProjectmanageYf(projectManageyf,
//				Integer.parseInt(cpage), pageSize,pageStatus);
//		if (object != null && object.length > 0) {
//			projectManageyfList = (List<ProjectManageyf>) object[0];
//			if (projectManageyfList != null && projectManageyfList.size() > 0) {
//				int count = (Integer) object[1];
//				int pageCount = (count + pageSize - 1) / pageSize;
//				this.setTotal(pageCount + "");
//				this.setUrl("projectPoolAction_selfProjectManageyfList.action");
//				errorMessage = null;
//			} else {
//				errorMessage = "没有找到你要查询的内容,请检查后重试!";
//			}
//		}
//		if("applychoose".equals(pageStatus)){
//			return "ProjectManageyf_applychoose";
//		}
//		return "ProjectManageyf_self";
	}

	/**
	 * 查询已经申报项目的人
	 */
	public void selectChooseList(){
		
		erList = projectPoolServer.selectChooseList(id,"预申请");
		
		MKUtil.writeJSON(erList);
		
	}
	
	/**
	 * 审批报选项目
	 */
	public void examineProject(){
		String result = projectPoolServer.examineProject(er,id,weight);
		MKUtil.writeJSON(result);
	}
	/**
	 * 进入填报项目进度页面
	 * @return
	 */
	public String gotoFillSchedule(){//pageStatus
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		//status
		return "projectManageyf_fillSchedule";
	}
	
	/**
	 * 填报项目进度
	 * @return
	 */
	public String fillSchedule(){
		
		return "projectManageyf_fillSchedule";
	}
	
	/**
	 * 保存或提交项目进度
	 * @return
	 */
	public String saveOrSubmitSchedule(){
		
		StringBuffer fileUrlBuffer = new StringBuffer();
		StringBuffer otherNameBuffer = new StringBuffer();
		
		if(oldFiles!=null && oldFileNames!=null){
			//处理旧文件空格问题
			String[] oldFilesplit = oldFiles.split(",");
			String[] oldFileNamesplit = oldFileNames.split(",");
			for (int i=0;i<oldFilesplit.length;i++) {
				if(i==0){
					fileUrlBuffer.append(oldFilesplit[i].trim());
					otherNameBuffer.append(oldFileNamesplit[i].trim());
				}else{
					fileUrlBuffer.append(","+oldFilesplit[i].trim());
					otherNameBuffer.append(","+oldFileNamesplit[i].trim());
				}
			}
		}else {
			fileUrlBuffer.append("");
			otherNameBuffer.append("");
		}
		
		if(attachment!=null && attachment.length>0){
			String names[] = null;
			String fileType = null;
			for(int i=0;i<attachment.length;i++){
				names = attachmentFileName[i].split("\\.");
				if(names!=null && names.length>=2){
					fileType = names[names.length-1];
				}
				String fileName = "project"+Util.getDateTime("yyyyMMddHHmmss")+"."+fileType;
				String otherName = names[0]+"."+fileType;
				String uploadFile = Util.UploadFile(attachment[i], "", fileName, "/upload/file/project", null);
				if(fileName.equals(uploadFile)){
					//处理文件名称
					if(i==0 && "".equals(fileUrlBuffer.toString())){
						fileUrlBuffer.append(fileName);
					}else{
						fileUrlBuffer.append(","+fileName);
					}
					if(i==0 && "".equals(otherNameBuffer.toString())){
						otherNameBuffer.append(otherName);
					}else{
						otherNameBuffer.append(","+otherName);
					}
				}else{
					errorMessage = uploadFile;
					return "error";
				}
			}
			projectManageyf.setYfProjectFile(fileUrlBuffer.toString());
			projectManageyf.setAliasFile(otherNameBuffer.toString());
		}
		
		errorMessage = projectPoolServer.saveOrSubmitSchedule(projectManageyf, tag);
		projectManageyf = projectPoolServer.getProjectManageyfById(projectManageyf.getId());
		
		//url = "projectPoolAction_selfProjectManageyfList.action?pageStatus=applychoose";
		return "error";
	}
	
	/**
	 * 审批子项目完成情况
	 * @return
	 */
	public String toExamineSubProject(){
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		//查找项目百分比
//		try {
//			//String proportion = projectPoolServer.getProPercentnm(projectManageyf.getId());
//			//projectManageyf.setProportion(proportion);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return "projectManageyf_examineSubProject";
	}
	
	/**
	 * 审批子项目
	 * @return
	 */
	public String examineSubProject(){
		
		message = projectPoolServer.examineSubProject(projectManageyf, resultStatus);
		errorMessage = message;
		return "error";
	}
	
	/**
	 * 获得项目研发成果
	 * @return
	 */
	public String getStoreResult(){
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		try {
			projectManageyf = projectPoolServer.getProStoreByProId(projectManageyf);
			pdList = projectPoolServer.getProyfpdByProId(id);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			setUrl("projectPoolAction_selfProjectManageyfList.action?pageStatus=applychoose");
			return "error";
		}
		return "ProjectManageyf_proStore";
	}
	
	//进入人员汇总页面
	public String toSelfStore(){
		
		return "ProjectManageyf_selfStoreTable";
	}
	
	/**
	 * 按项目池汇总
	 */
	public void summarizingPool(){
		
		List<ProjectManageYfpd> list = projectPoolServer.summarizingPool();
		MKUtil.writeJSON(list);
	}
	
	/**
	 * 按主项目汇总
	 */
	public void summarizingRootPro(){
		List<ProjectManageYfpd> list = projectPoolServer.summarizingRootPro();
		MKUtil.writeJSON(list);
	}
	
	/**
	 * 按年份汇总
	 */
	public void summarizingYear(){
		
		
	}
	
	/**
	 * 按月份汇总
	 */
	public void summarizingMonth(){
		
		
	}
	
	/**
	 * 按人员汇总
	 */
	public void personnelSummary(){
		List<ProjectManageYfpd> list = projectPoolServer.personnelSummary();
		MKUtil.writeJSON(list);
	}
	
	//个人研发项目汇总
	public String selfYfProjectStore(){
		try {
			projectManageyfList = projectPoolServer.selfYfProjectStore(pageStatus);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			if("applychoose".equals(pageStatus)){
				setUrl("projectPoolAction_selfProjectManageyfList.action?pageStatus=applychoose");
			}else{
				setUrl("projectPoolAction_selfProjectManageyfList.action");
			}
			return "error";
		}
		if("applychoose".equals(pageStatus)){
			return "ProjectManageyf_selfStoreTable";
		}
		return "ProjectManageyf_storeTable";
	}
	
	
	//总项目汇总
	public String toProjectManageYfStore(){
		projectManageyfList = projectPoolServer.projectManageResult();
		return "ProjectManageyf_storeTable";
	}
	
	//项目中人员明细
	public void getProyfpdByProId(){
		List<ProjectManageYfpd> proYfpd= projectPoolServer.getProyfpdByProId(id);//;proYfpd =
		MKUtil.writeJSON(proYfpd);
	}
	
	//根据项目id查找项目本层占比
	public void getProPercentnm(){
		String proPercentnm = "";
		try {
			proPercentnm = projectPoolServer.getProPercentnm(id,store,tag);//根据项目id查找项目占比
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(proPercentnm);
	}
	
	//查看子项目汇总信息
	public String selectSubPro(){
		projectManageyfList = projectPoolServer.selectSubPro(id);
		return "ProjectManageyf_subStoreTable";
	}
	
	/**
	 * 测试发送消息提醒
	 */
	public void sendMessage(){
		projectPoolServer.sendProjectManageYfInfo();
	}
	
	/**
	 * 申请延期
	 * @return
	 */
	public String applyForPostpone(){
		
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		return "ProjectManageyf_applyForPostpone";
	}
	
	/**
	 * 提交延期
	 * @return
	 */
	public String submitForPostpone(){
		
		errorMessage= projectPoolServer.submitForPostpone(projectManageyf,pageStatus);
//		if("".equals(pageStatus)){
//			
//		}else{
//			
//		}
		setUrl("projectPoolAction_selfProjectManageyfList.action?pageStatus="+pageStatus);
		
		return "error";
	}
	
	/**
	 * 进入延期审批页面
	 * @return
	 */
	public String examineForPostone(){
		projectManageyf = projectPoolServer.getProjectManageyfById(id);
		
		return "projectManageyf_examineForPostone";
	}
	
	/**
	 * 审批延期.
	 * @return
	 */
	public String examineForPostoneOut(){
		errorMessage = projectPoolServer.examineForPostone(id, status);
		
		return "error";
	}
	
	/**
	 * 主项目提交确认文件
	 * @return
	 */
	public String finalSubmitFile(){
		
		List<File> attachments=new ArrayList<File>();
		List<String> attachmentNames = new ArrayList<String>();
		if(null!=attachment){
			//处理空值
			for (int i = 0; i <attachment.length; i++) {
				if(null!=attachment[i]){
					attachments.add(attachment[i]);
					attachmentNames.add(attachmentFileName[i]);
				}
			}
		}
		
		
		errorMessage = projectPoolServer.finalSubmitFile(projectManageyf,attachments,attachmentNames);
		setUrl("projectPoolAction_toAddSubProject.action?id="+projectManageyf.getId());
		return ERROR;
	}
	
	/**
	 * 查询指定user所有的项目
	 * @return
	 */
	public String showSelfProject(){
		projectManageyfList = projectPoolServer.showSelfProject(userId);
		
		return "ProjectManageyf_showSelfProject";
	}
	/**
	 * 查询user在项目池的所有项目
	 * @return
	 * id 为项目id
	 */
	public String showSelfProjectAndPool(){
		projectManageyfList = projectPoolServer.showSelfProjectAndPool(userId,id);
		return "ProjectManageyf_showSelfProject";
	}
	/**
	 * 查询user在主项目下的所有项目
	 * @return
	 */
	public String showSelfProjectByRoot(){
		projectManageyfList = projectPoolServer.showSelfProjectByRoot(userId,id);
		return "ProjectManageyf_showSelfProject";
	}
	
	/**
	 * 根据ids删除其他主项目
	 * @return
	 */
	public void delProjectByIds(){
		String result;
		try {
			result = projectPoolServer.delProjectByIds(ids);
			MKUtil.writeJSON(result);
		} catch (Exception e) {
			MKUtil.writeJSON(e.toString());
		}
	}
	
	/**
	 * 查找某项目未指派人员
	 * @return
	 */
	public String findUnErList() {
		erList = projectPoolServer.findUnErList(id, pageStatus);
		return "projectManageyf_erList";
	}
	
	
	public String getERById() {
		er = projectPoolServer.getProjectManageyfErById(id, null);
		return "ProjectManageyf_addSub";
	}
	
	@SuppressWarnings("unchecked")
	public String searchProFile() {
		Map<String, Object> map = projectPoolServer.searchProFile(projectManageyf, pageSize, Integer.parseInt(cpage), pageStatus);
		if (map != null && map.size()> 0) {
			projectManageyfList = (List<ProjectManageyf>) map.get("list");
			if (projectManageyfList != null && projectManageyfList.size() > 0) {
				int count = (Integer) map.get("count");
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("projectPoolAction_searchProFile.action?pageStatus="+pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "ProjectManageYf_proFileList";
	}
	
	public String toxqBind() {
		erList = projectPoolServer.findUnErList(id, pageStatus);
		return "ProjectManageyf_xqBind";
	}
	
	public void xqBind() {
		errorMessage = projectPoolServer.xqBind(id,erList,ids);
		MKUtil.writeJSON(errorMessage);
	}
	
	
	class Cal {
		private String title;
		private String start;
		private String end;
		private String url;
		private Integer id;
		private String content;
		private String sendUserId;// 发送用户id
		private String sendUserName;// 发送用户名称
		private String sendUserImg;// 发送用户头像
		private String addTime;
		private String color;
		private String functionUrl;
		private String functionId;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getStart() {
			return start;
		}
		public void setStart(String start) {
			this.start = start;
		}
		public String getEnd() {
			return end;
		}
		public void setEnd(String end) {
			this.end = end;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getSendUserId() {
			return sendUserId;
		}
		public void setSendUserId(String sendUserId) {
			this.sendUserId = sendUserId;
		}
		public String getSendUserName() {
			return sendUserName;
		}
		public void setSendUserName(String sendUserName) {
			this.sendUserName = sendUserName;
		}
		public String getSendUserImg() {
			return sendUserImg;
		}
		public void setSendUserImg(String sendUserImg) {
			this.sendUserImg = sendUserImg;
		}
		public String getAddTime() {
			return addTime;
		}
		public void setAddTime(String addTime) {
			this.addTime = addTime;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public String getFunctionUrl() {
			return functionUrl;
		}
		public void setFunctionUrl(String functionUrl) {
			this.functionUrl = functionUrl;
		}
		public String getFunctionId() {
			return functionId;
		}
		public void setFunctionId(String functionId) {
			this.functionId = functionId;
		}
		
	}
	/**
	 * 根据
	 * 员工
	 * 开始时间
	 * 结束时间
	 * 查找员工的项目任务
	 * @return
	 */
	public void selfProjectManageyfByDateDiff() {
		if(projectManageyf==null) {
			projectManageyf = new ProjectManageyf();
		}
		projectManageyfList = projectPoolServer.findselfProjectmanageYfByDateDiff(userId, projectManageyf.getZpTime(), projectManageyf.getReTime(), pageStatus);
				//.findselfProjectmanageYf(projectManageyf,
				//0, 0,pageStatus);
		if(projectManageyfList!=null && projectManageyfList.size()>0) {
			
			List<Cal> calList = new ArrayList<Cal>();
			for (ProjectManageyf yf : projectManageyfList) {
				Cal cal = new Cal();
				cal.title = "项目管理\n"+yf.getProName();
				cal.start = yf.getZpTime();
				cal.end = yf.getReTime();
				cal.url = "";
				cal.id = yf.getId();
				cal.content = yf.getRemark();
//				cal.sendUserId = yf.get
				cal.addTime = yf.getStartTime();
				cal.color = "#61E1E3";
				cal.functionUrl = "projectPoolAction_gotoFillSchedule.action?pageStatus=applychoose&id="+yf.getId();
				cal.functionId = "";
				calList.add(cal);
			}
			MKUtil.writeJSON(calList);
		}else {
			MKUtil.writeJSON("");
		}
	}
	
	public ProjectPoolServer getProjectPoolServer() {
		return projectPoolServer;
	}
	public void setProjectPoolServer(ProjectPoolServer projectPoolServer) {
		this.projectPoolServer = projectPoolServer;
	}
	public ProjectPool getProjectPool() {
		return projectPool;
	}
	public void setProjectPool(ProjectPool projectPool) {
		this.projectPool = projectPool;
	}
	
	public List<ProjectPool> getProjectPoolList() {
		return projectPoolList;
	}

	public void setProjectPoolList(List<ProjectPool> projectPoolList) {
		this.projectPoolList = projectPoolList;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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
	public File getAccessory() {
		return accessory;
	}
	public void setAccessory(File accessory) {
		this.accessory = accessory;
	}
	public String getAccessoryContentType() {
		return accessoryContentType;
	}
	public void setAccessoryContentType(String accessoryContentType) {
		this.accessoryContentType = accessoryContentType;
	}
	public String getAccessoryFileName() {
		return accessoryFileName;
	}
	public void setAccessoryFileName(String accessoryFileName) {
		this.accessoryFileName = accessoryFileName;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public ProjectManageyf getProjectManageyf() {
		return projectManageyf;
	}

	public void setProjectManageyf(ProjectManageyf projectManageyf) {
		this.projectManageyf = projectManageyf;
	}

	public List<ProjectManageyf> getProjectManageyfList() {
		return projectManageyfList;
	}

	public void setProjectManageyfList(List<ProjectManageyf> projectManageyfList) {
		this.projectManageyfList = projectManageyfList;
	}

	public List<ProjectManageyfUser> getYfUserList() {
		return yfUserList;
	}

	public void setYfUserList(List<ProjectManageyfUser> yfUserList) {
		this.yfUserList = yfUserList;
	}

	public ProjectManageyfUser getYfUser() {
		return yfUser;
	}

	public void setYfUser(ProjectManageyfUser yfUser) {
		this.yfUser = yfUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRootIdStr() {
		return rootIdStr;
	}

	public void setRootIdStr(String rootIdStr) {
		this.rootIdStr = rootIdStr;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public List<ProjectManageyfAgree> getAgreeList() {
		return agreeList;
	}

	public void setAgreeList(List<ProjectManageyfAgree> agreeList) {
		this.agreeList = agreeList;
	}

	public ProjectManageyfAgree getAgree() {
		return agree;
	}
	public void setAgree(ProjectManageyfAgree agree) {
		this.agree = agree;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProejctYfUserId() {
		return proejctYfUserId;
	}

	public void setProejctYfUserId(Integer proejctYfUserId) {
		this.proejctYfUserId = proejctYfUserId;
	}

	public List<ProjectManageyfEr> getErList() {
		return erList;
	}

	public void setErList(List<ProjectManageyfEr> erList) {
		this.erList = erList;
	}

	public ProjectManageyfEr getEr() {
		return er;
	}

	public void setEr(ProjectManageyfEr er) {
		this.er = er;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public YfUser getYfuserMiddle() {
		return yfuserMiddle;
	}

	public void setYfuserMiddle(YfUser yfuserMiddle) {
		this.yfuserMiddle = yfuserMiddle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public List<ProjectManageYfpd> getPdList() {
		return pdList;
	}

	public void setPdList(List<ProjectManageYfpd> pdList) {
		this.pdList = pdList;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getOldFiles() {
		return oldFiles;
	}

	public void setOldFiles(String oldFiles) {
		this.oldFiles = oldFiles;
	}

	public String getOldFileNames() {
		return oldFileNames;
	}

	public void setOldFileNames(String oldFileNames) {
		this.oldFileNames = oldFileNames;
	}
	
}
