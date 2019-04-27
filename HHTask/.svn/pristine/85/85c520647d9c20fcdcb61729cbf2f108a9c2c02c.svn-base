package com.task.action.pro;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.pro.ProjectMaterialOrderServer;
import com.task.entity.Users;
import com.task.entity.project.ProjectManage;
import com.task.entity.project.ProjectMaterial;
import com.task.entity.project.ProjectMaterialOrder;
import com.task.util.MKUtil;
/**
 * 研发项目材料清单Action
 * @author txb
 *
 */
@SuppressWarnings("serial")
public class ProjectMaterialOrderAction extends ActionSupport {

	private ProjectMaterialOrderServer projectMaterialOrderServer;//研发项目材料清单server
	private ProjectMaterialOrder projectMaterialOrder;//研发项目材料清单
	private ProjectMaterial projectMaterial;//研发项目材料
	private ProjectManage projectManage;//项目对象
	private List<ProjectMaterialOrder> pmoList;//研发项目材料清单列表
	private List<ProjectMaterial> pmList;//研发项目材料列表
	private List<ProjectManage> pmanageList;//项目列表
	private String idshifa;//每种材料的id和实发数量的拼接， 格式（id:shifa,id:shifa）
	
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	private String goodsMarkId;// 库存件号
	private String goodsFormat;// 库存规格
	private float shifa;// 实发数量
	private String receiveuser;//领料人卡号
	private String isbaomi;
	private int cannotreceive=0;
   
	 public String showList(){
			if (projectMaterialOrder != null) {
				ActionContext.getContext().getSession().put("projectMaterialOrder",
						projectMaterialOrder);
			    } else {//用来保持分页时带着查询条件
			    	projectMaterialOrder = (ProjectMaterialOrder) ActionContext.getContext().getSession().get("projectMaterialOrder");
			      }
			Map<Integer, Object> map = projectMaterialOrderServer.findProjectMaterialOrdersByCondition(
					projectMaterialOrder, Integer.parseInt(cpage), pageSize,null);
			pmoList = (List<ProjectMaterialOrder>) map.get(1);// 显示页的技能系数列表
				if (pmoList != null & pmoList.size() > 0) {
					int count = (Integer) map.get(2);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("projectMaterialOrderAction_showList.action");
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}	
		 return "promaterialorder_show";
	 }
	 public String showbmList(){
		 if (projectMaterialOrder != null) {
			 ActionContext.getContext().getSession().put("projectMaterialOrder",
					 projectMaterialOrder);
		 } else {//用来保持分页时带着查询条件
			 projectMaterialOrder = (ProjectMaterialOrder) ActionContext.getContext().getSession().get("projectMaterialOrder");
		 }
		 Map<Integer, Object> map = projectMaterialOrderServer.findbmProjectMaterialOrdersByCondition(
				 projectMaterialOrder, Integer.parseInt(cpage), pageSize,null,isbaomi);
		 pmoList = (List<ProjectMaterialOrder>) map.get(1);// 显示页的技能系数列表
		 if (pmoList != null & pmoList.size() > 0) {
			 int count = (Integer) map.get(2);
			 int pageCount = (count + pageSize - 1) / pageSize;
			 this.setTotal(pageCount + "");
			 this.setUrl("projectMaterialOrderAction_showbmList.action");
		 } else {
			 errorMessage = "没有找到你要查询的内容,请检查后重试!";
		 }	
		 return "promaterialorder_showbm";
	 }
	 
	 public void exprotqd(){
		 projectMaterialOrderServer.exprotqd(id);
	 }
	 
	 /**
	  * 仓库展示已同意的材料清单
	  * @return
	  */
	 public String ckShowList(){
		 if (projectMaterialOrder != null) {
			 ActionContext.getContext().getSession().put("projectMaterialOrder",
					 projectMaterialOrder);
		 } else {//用来保持分页时带着查询条件
			 projectMaterialOrder = (ProjectMaterialOrder) ActionContext.getContext().getSession().get("projectMaterialOrder");
		 }
		 Map<Integer, Object> map = projectMaterialOrderServer.findProjectMaterialOrdersByCondition(
				 projectMaterialOrder, Integer.parseInt(cpage), pageSize," aduitStatus='同意'");
		 pmoList = (List<ProjectMaterialOrder>) map.get(1);// 显示页的技能系数列表
		 if (pmoList != null & pmoList.size() > 0) {
			 int count = (Integer) map.get(2);
			 int pageCount = (count + pageSize - 1) / pageSize;
			 this.setTotal(pageCount + "");
			 this.setUrl("projectMaterialOrderAction_ckShowList.action");
		 } else {
			 errorMessage = "没有找到你要查询的内容,请检查后重试!";
		 }	
		 return "promaterialorder_ck_show";
	 }
	 public String add(){
		 if(pmList!=null&&pmList.size()>0){
				Set<ProjectMaterial> pmSet=new HashSet<ProjectMaterial>();
				int count=0;
				for(ProjectMaterial pm:pmList){
					if(pm!=null){
						pm.setProjectMaterialOrder(projectMaterialOrder);
						pm.setProName(projectMaterialOrder.getProName());
						pmSet.add(pm);
						count++;
					}
				}
				if(count>0){
					boolean b=false;
					try {
						b = projectMaterialOrderServer.add(projectMaterialOrder,pmSet);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						successMessage="添加失败!";
						return "promaterialorder_add";
					}
					if(b){
						successMessage="添加成功!";
					}else{
						successMessage="添加失败!";
					}
				}else{
					successMessage="添加失败!没有填写材料!";
				}
			}
		
		 return "promaterialorder_add";
		 
	 }
//	 public String toadd(){
//		 pmanageList=projectMaterialOrderServer.findAllProjectManage();
//		 return "promaterialorder_add";
//	 }
//	public String toapproval(){
//		boolean b=false;
//		try {
//			b = projectMaterialOrderServer.addapproval(projectMaterialOrder);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			b=false;
//		}
//		if(b){
//			successMessage="成功提交申请！";
//			return showList();
//		}
//	errorMessage="申请提交失败！";
//	return showList();
//	}
	public String pmdetail(){
		if(projectMaterialOrder!=null&&projectMaterialOrder.getId()!=null){
			projectMaterialOrder=projectMaterialOrderServer.getById(projectMaterialOrder.getId());
			pmList=projectMaterialOrderServer.findMaterialByOrderId(projectMaterialOrder.getId());
			return "promaterialorder_pmdetail";
		}
		return null;
	}
	public String todelete(){
		if(projectMaterialOrder!=null&&projectMaterialOrder.getId()!=null){
			boolean b=projectMaterialOrderServer.deleteById(projectMaterialOrder.getId());
			if(b){
				successMessage="删除成功!";
			}else{
				successMessage="删除成功!";
			}
		}
		projectMaterialOrder=null;
		return showList();
		
	}
	public String toupdate(){
		if(projectMaterialOrder!=null&&projectMaterialOrder.getId()!=null){
			projectMaterialOrder=projectMaterialOrderServer.getById(projectMaterialOrder.getId());
			pmList=projectMaterialOrderServer.findMaterialByOrderId(projectMaterialOrder.getId());
			return "promaterialorder_update";
		}
		return null;
	}
	public String update(){
		if(pmList!=null&&pmList.size()>0){
			Set<ProjectMaterial> pmSet=new HashSet<ProjectMaterial>();
			int count=0;
			for(ProjectMaterial pm:pmList){
				if(pm!=null){
					pm.setProjectMaterialOrder(projectMaterialOrder);
					pm.setProName(projectMaterialOrder.getProName());
					pmSet.add(pm);
					count++;
				}
			}
			if(count>0){
				boolean b= projectMaterialOrderServer.update(projectMaterialOrder,pmSet);
				if(b){
					successMessage="修改成功!";
				}else{
					successMessage="修改失败!";
				}
			}else{
				successMessage="修改失败!没有填写材料!";
			}
		}
	
	 return "promaterialorder_update";
	}
	/**
	 * 跳往领取材料页面
	 * @return
	 */
	public String toReceive (){
		Map<Integer, Object> map =projectMaterialOrderServer.toReceive(projectMaterialOrder.getId());
		if(map==null){
			successMessage="清单数据不对,领取失败！";
		}else{
			projectMaterialOrder=(ProjectMaterialOrder) map.get(1);
			pmList= (List<ProjectMaterial>) map.get(2);
			cannotreceive=Integer.parseInt(map.get(3).toString());
			return "promaterialorder_receive";
		}
		return null;
		
	}
	/**
	 * 领取材料
	 * @return
	 */
	public void receiveMaterial(){
		Users user=projectMaterialOrderServer.getReceiver(receiveuser);
		if(user==null){
			MKUtil.writeJSON(false, "库存不足！领料人不存在！", null);		
			return;
		}
		try {
			boolean b=projectMaterialOrderServer.receiveMaterial(projectMaterialOrder.getId(),idshifa,user);
			if(b){
				MKUtil.writeJSON(b, "领料成功！", null);
				return;
			}else{
				MKUtil.writeJSON(b, "领料失败!", null);
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MKUtil.writeJSON(false, "库存不足！领料失败", null);		
			return;
			}
		
	}
	/**
	 * 核对实发数量
	 */
	public void checkshifacount() {
		boolean b = projectMaterialOrderServer.checkshifacount(goodsMarkId, goodsFormat, shifa);
		MKUtil.writeJSON(b, null, null);
	}
	public ProjectMaterialOrderServer getProjectMaterialOrderServer() {
		return projectMaterialOrderServer;
	}

	public void setProjectMaterialOrderServer(
			ProjectMaterialOrderServer projectMaterialOrderServer) {
		this.projectMaterialOrderServer = projectMaterialOrderServer;
	}

	public ProjectMaterialOrder getProjectMaterialOrder() {
		return projectMaterialOrder;
	}

	public void setProjectMaterialOrder(ProjectMaterialOrder projectMaterialOrder) {
		this.projectMaterialOrder = projectMaterialOrder;
	}

	public ProjectMaterial getProjectMaterial() {
		return projectMaterial;
	}

	public void setProjectMaterial(ProjectMaterial projectMaterial) {
		this.projectMaterial = projectMaterial;
	}

	public List<ProjectMaterialOrder> getPmoList() {
		return pmoList;
	}

	public void setPmoList(List<ProjectMaterialOrder> pmoList) {
		this.pmoList = pmoList;
	}

	public List<ProjectMaterial> getPmList() {
		return pmList;
	}

	public void setPmList(List<ProjectMaterial> pmList) {
		this.pmList = pmList;
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
	public ProjectManage getProjectManage() {
		return projectManage;
	}
	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}
	public List<ProjectManage> getPmanageList() {
		return pmanageList;
	}
	public void setPmanageList(List<ProjectManage> pmanageList) {
		this.pmanageList = pmanageList;
	}
	public int getCannotreceive() {
		return cannotreceive;
	}
	public void setCannotreceive(int cannotreceive) {
		this.cannotreceive = cannotreceive;
	}
	public String getIdshifa() {
		return idshifa;
	}
	public void setIdshifa(String idshifa) {
		this.idshifa = idshifa;
	}
	public String getGoodsMarkId() {
		return goodsMarkId;
	}
	public void setGoodsMarkId(String goodsMarkId) {
		this.goodsMarkId = goodsMarkId;
	}
	public String getGoodsFormat() {
		return goodsFormat;
	}
	public void setGoodsFormat(String goodsFormat) {
		this.goodsFormat = goodsFormat;
	}
	public float getShifa() {
		return shifa;
	}
	public void setShifa(float shifa) {
		this.shifa = shifa;
	}
	public String getReceiveuser() {
		return receiveuser;
	}
	public void setReceiveuser(String receiveuser) {
		this.receiveuser = receiveuser;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIsbaomi() {
		return isbaomi;
	}
	public void setIsbaomi(String isbaomi) {
		this.isbaomi = isbaomi;
	}
	
	

}
