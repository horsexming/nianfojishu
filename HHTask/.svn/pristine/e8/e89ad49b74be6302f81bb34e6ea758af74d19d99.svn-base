package com.task.action.shizhi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProjectOrderServer;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.ProjectOrder;
import com.task.entity.shizhi.ProjectOrderPart;
import com.task.entity.vo.shizhivo.ProjectOrderVo;
/**
 * 项目订单Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProjectOrderAction {
	private ProjectOrder projectOrder;//项目订单对象
	private List<ProjectOrder> pOrderList;//项目订单列表
	private List<ProjectOrderPart> pOrderPartList;//项目订单零件列表
	private ProjectOrderServer projectOrderServer;//项目订单服务
	private ProjectOrderVo projectOrderVo;//项目需求订单Vo
	private List<String> gNameList;//分组名称列表
	private List<String> cusNameList;//客户名称列表
	private ProTryMakeScore proTryMakeScore;//项目试制评审对象
	private List<ProTryMakeScore> ptmsList;//项目试制评审列表
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 7;
	/**
	 * 分页显示项目订单
	 * 
	 * @return
	 */
	public String showList(){
		if (projectOrder != null) {
			ActionContext.getContext().getSession().put("projectOrder",
					projectOrder);
		    } else {//用来保持分页时带着查询条件
		    	projectOrder = (ProjectOrder) ActionContext.getContext().getSession().get("projectOrder");
		      }
		if(projectOrder!=null&&projectOrder.getDeal()!=null&&projectOrder.getDeal()!=1&&projectOrder.getDeal()!=2){
			projectOrder.setDeal(null);
		}
		if(projectOrder!=null&&projectOrder.getStatus()!=null&&projectOrder.getStatus().equals("全部")){
			projectOrder.setStatus(null);
		}
		Map<Integer, Object> map = projectOrderServer.findProjectOrdersByCondition(
				projectOrder, Integer.parseInt(cpage), pageSize);
		pOrderList = (List<ProjectOrder>) map.get(1);// 显示页的技能系数列表
			if (pOrderList != null & pOrderList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("projectOrderAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}	
	 return "porder_show";
	}
	/**
	 * 跳往添加页面
	 * @return
	 */
	public String toadd(){
		ptmsList=projectOrderServer.findProTryMakeScoreAll();
		return "porder_add";
	}
	public String add(){
		if(proTryMakeScore.getId()!=null&&proTryMakeScore
				.getId()!=0){
			projectOrder.setProTryMakeScore(proTryMakeScore);
		}else{
			this.errorMessage="添加失败,请选择项目";
			return toadd();
		}
		if(projectOrder.getProductEngineer()!=null&&(projectOrder.getProductEngineer().equals("请先选择分组")
				||projectOrder.getProductEngineer().equals("请选择人员"))){
			projectOrder.setProductEngineer(null);
		}
		if(projectOrder.getTechnicalEngineer()!=null&&(projectOrder.getTechnicalEngineer().equals("请先选择分组")
				||projectOrder.getTechnicalEngineer().equals("请选择人员"))){
			projectOrder.setTechnicalEngineer(null);
		}
		boolean b=projectOrderServer.add(projectOrder);
		if(b){
			this.errorMessage="添加成功";
//			return showList();
			this.setUrl("projectOrderAction_showList.action");
			return "error";
		}else{
			this.errorMessage="添加失败";
			return toadd();
		}
	}
	/**
	 * 申请审批
	 * @return
	 * @throws Exception 
	 */
	public String toapproval() throws Exception{
			String msg=projectOrderServer.addapproval(projectOrder);
			if(msg.equals("true")){
				successMessage="成功提交申请！";
				return showList();
			}
		errorMessage=msg;
		return showList();
	}
	/**
	 * 根据项目id查询项目明细
	 * @return
	 */
	public String prodetail(){
		projectOrderVo=projectOrderServer.getProjectOrderVoById(projectOrder.getId());
		return "porder_prodetail";
	}
	/**
	 * 跳往修改页面
	 * @return
	 */
	public String toupdate(){
		if(projectOrder!=null&&projectOrder.getId()!=null){
			projectOrder=projectOrderServer.getById(projectOrder.getId());
			if(projectOrder==null){
				errorMessage="修改失败没有该项目订单";
				return showList();
			}else if(projectOrder.getStatus()!=null
					&&(projectOrder.getStatus().equals("同意")
					||projectOrder.getStatus().equals("审批中"))){
				errorMessage="该项目订单目前不可修改";
				return showList();
			}
		}
		return "porder_update";
	}
	public String update(){
		if(projectOrder.getProductEngineer()!=null&&projectOrder.getProductEngineer().equals("未选择")){
			projectOrder.setProductEngineer(null);
		}
		if(projectOrder.getTechnicalEngineer()!=null&&projectOrder.getProductEngineer().equals("未选择")){
			projectOrder.setProductEngineer(null);
		}
		boolean b=projectOrderServer.update(projectOrder);
		if(b){
			this.successMessage="修改成功";
			projectOrder=null;
			return showList();
		}else{
			this.errorMessage="修改失败";
			return "porder_update";
		}
	}
	public String delete(){
		if(projectOrder!=null&&projectOrder.getId()!=null){
			projectOrder=projectOrderServer.getById(projectOrder.getId());
			if(projectOrder.getStatus()!=null
					&&projectOrder.getStatus().equals("同意")){
				errorMessage="已同意的不可删除";
				return showList();
			}
			boolean b=projectOrderServer.deleteById(projectOrder.getId());
			if(b){
				projectOrder=null;
				successMessage="删除成功";
			}else{
				errorMessage="删除失败没有改项目订单";
			}
		}
		return showList();
	}
	/**
	 * 试制需求单跳往生成试制流水单
	 * @return
	 */
	public String toProduct(){
		projectOrderVo=projectOrderServer.getProjectOrderVoById(projectOrder.getId());
		return "porder_product";
	}
	/***************************************************************************
	 * 生成流水卡片
	 * 
	 * @return
	 */
	public String addProCard() {
		try {
			successMessage = projectOrderServer.addProCard(projectOrder,pOrderPartList);
		} catch (Exception e) {
			successMessage = "生成失败!请联系管理员!errormeg：" + e.getMessage();
			e.printStackTrace();
		}
		return toProduct();
	}
   //get和set方法
	public ProjectOrder getProjectOrder() {
		return projectOrder;
	}

	public void setProjectOrder(ProjectOrder projectOrder) {
		this.projectOrder = projectOrder;
	}

	public List<ProjectOrder> getpOrderList() {
		return pOrderList;
	}

	public void setpOrderList(List<ProjectOrder> pOrderList) {
		this.pOrderList = pOrderList;
	}

	public ProjectOrderServer getProjectOrderServer() {
		return projectOrderServer;
	}

	public void setProjectOrderServer(ProjectOrderServer projectOrderServer) {
		this.projectOrderServer = projectOrderServer;
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
	public List<String> getCusNameList() {
		return cusNameList;
	}
	public void setCusNameList(List<String> cusNameList) {
		this.cusNameList = cusNameList;
	}
	public List<String> getgNameList() {
		return gNameList;
	}
	public void setgNameList(List<String> gNameList) {
		this.gNameList = gNameList;
	}
	public List<ProTryMakeScore> getPtmsList() {
		return ptmsList;
	}
	public void setPtmsList(List<ProTryMakeScore> ptmsList) {
		this.ptmsList = ptmsList;
	}
	public ProTryMakeScore getProTryMakeScore() {
		return proTryMakeScore;
	}
	public void setProTryMakeScore(ProTryMakeScore proTryMakeScore) {
		this.proTryMakeScore = proTryMakeScore;
	}
	public ProjectOrderVo getProjectOrderVo() {
		return projectOrderVo;
	}
	public void setProjectOrderVo(ProjectOrderVo projectOrderVo) {
		this.projectOrderVo = projectOrderVo;
	}
	public List<ProjectOrderPart> getpOrderPartList() {
		return pOrderPartList;
	}
	public void setpOrderPartList(List<ProjectOrderPart> pOrderPartList) {
		this.pOrderPartList = pOrderPartList;
	}
	
	
	
}
