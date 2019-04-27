package com.task.action.bp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.bp.TempletService;
import com.task.entity.Users;
import com.task.entity.bp.Templet;
import com.task.util.MKUtil;
import com.task.util.StrutsTreeNode;
import com.task.util.StrutsTreeUtil;

@SuppressWarnings("all")
public class TempletAction extends ActionSupport {
	private TempletService templetService;
	private List<Templet> productTemplets;
	private Templet templet;
	private Templet rootTemplet;
	private StrutsTreeNode root;
	private Integer temp;
	private List<String> models;
	private boolean templetBoss; //BOSS有生杀大权
	
	private List<Templet> listVerify;
	
	private String successMessage;
	private String errorMessage;
	
	//添加顶级
	public String addRoot(){
		if(templetService.addRoot(templet)){
			successMessage = "添加" + templet.getName() +"成功";
		} else {
			errorMessage = "添加失败";
		}
		if(temp != null){
			rootTemplet= new Templet();
			rootTemplet.setId(temp);
		}
		return "chainToList";
	}

	//列表
	public String list() {
		
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		String name = user.getName();
		if(templetBoss || (session.get("templetBoss") instanceof Boolean)){
			productTemplets = templetService.getProductTemplet(null);
			models = templetService.getModels(null);
			templetBoss = true;
			session.put("templetBoss", templetBoss);
			
		} else {
			models = templetService.getModels(name);
			productTemplets = templetService.getProductTemplet(name);
		}
		
		if(rootTemplet != null){
			List list = templetService.findTempletTree(rootTemplet.getId());
			this.root = StrutsTreeUtil.getTreeRoot(list);
		}
		return SUCCESS;
	}

	//显示审核列表
	public String listVerify(){
		listVerify = templetService.findForVerify();
		if(templet != null){
			rootTemplet = templetService.getRoot(templet);
			List list = templetService.findTempletTree(rootTemplet.getId());
			this.root = StrutsTreeUtil.getTreeRoot(list);
		}
		return SUCCESS;
	}

	//审核同意
	public String agree(){
		templetService.agree(templet);
		return "redirectLV";
	}
	
	//驳回
	public String overrule(){
		templetService.overrule(templet);
		return "redirectLV";
	}
	
	//修改
	public String update() {
		if(templetService.update(templet)){
			successMessage = "修改" + templet.getName() + "成功  ";
		} else {
			errorMessage = "修改失败";
		}
		if(temp != null ){
			rootTemplet= new Templet();
			rootTemplet.setId(temp);
		}
		return "chainToList";
	}

	public String add() {
		if(templetService.add(templet)){
			successMessage = "添加" + templet.getName() + "成功  ";
		} else {
			errorMessage = "添加失败";
		}
		if(temp != null){
			rootTemplet= new Templet();
			rootTemplet.setId(temp);
		}
		return "chainToList";
	}

	public String delete() {
		if(temp != null && templet.getId() == temp){
			rootTemplet = null;
		} else {
			if(temp != null){
				rootTemplet= new Templet();
				rootTemplet.setId(temp);
			}
		}
		if(templetService.delete(templet)){
			successMessage = "删除" + templet.getName() + "成功";
		} else {
			errorMessage = "无法删除" + templet.getName() + "，请先删除下面的配件。 ";
		}
		return "chainToList";
	}

	public String findTempletByIdForTree() {
		String message = templetService.findForSetlect(templet.getId());
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//按照车型查找顶层Templet
	public String searchByModel(){
		List<Templet> t = null;
		try {
			Map session = ActionContext.getContext().getSession();
			Users user = (Users) session.get(TotalDao.users);
			String name = user.getName();
			if(templetBoss || (session.get("templetBoss") instanceof Boolean)){
				t = templetService.searchByModel(templet,null);
				templetBoss = true;
				session.put("templetBoss", templetBoss);
			} else {
				 t = templetService.searchByModel(templet,name);
			}
			MKUtil.writeJSON(true,null,t);
		} catch (Exception e) {
			MKUtil.writeJSON(false,"查询失败,错误消息：" + e.getMessage(),null);
			e.printStackTrace();
		}
		return null;
	}
	
	public String searchByModelAll(){
		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);
		String name = user.getName();
		List<Templet> t = templetService.searchByModel(templet,null);
		MKUtil.writeJSON(true,null,t);
		return null;
	}

	// --------下面是getter and setter
	public TempletService getTempletService() {
		return templetService;
	}

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}

	public Templet getTemplet() {
		return templet;
	}

	public void setTemplet(Templet templet) {
		this.templet = templet;
	}

	public StrutsTreeNode getRoot() {
		return root;
	}

	public void setRoot(StrutsTreeNode root) {
		this.root = root;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List<Templet> getProductTemplets() {
		return productTemplets;
	}

	public void setProductTemplets(List<Templet> productTemplets) {
		this.productTemplets = productTemplets;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Templet getRootTemplet() {
		return rootTemplet;
	}

	public void setRootTemplet(Templet rootTemplet) {
		this.rootTemplet = rootTemplet;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public boolean isTempletBoss() {
		return templetBoss;
	}

	public void setTempletBoss(boolean templetBoss) {
		this.templetBoss = templetBoss;
	}

	public List<Templet> getListVerify() {
		return listVerify;
	}

	public void setListVerify(List<Templet> listVerify) {
		this.listVerify = listVerify;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}


}
