package com.task.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.entity.*;
import com.task.entity.renshi.InterviewLog;

/**
 * 合同条款Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class ProvisionAction extends ActionSupport {

	private ProvisionServer ProvisionServer;// Server层（改）
	private InterviewLog interviewLog;
	private Provision provision;// 对象（改）
	private List<Provision> provisionList;// 集合（改）
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private int num;
	private Integer abc;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加合同条款
	public String addProvision() throws UnsupportedEncodingException {
		boolean bool = ProvisionServer.addProvision(provision);
		if (bool) {
			successMessage = URLEncoder.encode(URLEncoder.encode("添加条款:"
					+ provision.getContent() + " 成功!", "utf-8"), "utf-8");
			return "addProvisionSuccess";
		}
		return ERROR;
	}
	
	//查询面试单条款 李聪add
	public String findAllProvisionMsd(){
		pageSize = 100;
		if (provision != null) {
			ActionContext.getContext().getSession().put("provision", provision);
		} else {
			provision = (Provision) ActionContext.getContext().getSession()
					.get("provision");
		}
 		Object[] object = ProvisionServer.findAllProvisionMsd(provision, Integer
				.parseInt(cpage), pageSize);
 		if (object != null && object.length > 0) {
			provisionList = (List<Provision>) object[0];
			if (provisionList != null && provisionList.size() > 0) {
				Integer count = (Integer) object[1];
				abc = count;
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProvisionAction!findAllProvision.action");
				try {
					if (successMessage != null && successMessage.length() > 0) {
						successMessage = URLDecoder.decode(successMessage,
								"utf-8");
					}
					if (errorMessage != null && errorMessage.length() > 0) {
						errorMessage = URLDecoder.decode(errorMessage, "utf-8");
					} else {
						errorMessage = null;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
 		}
			return "interviewQuizzes"; //面试单添加页面
	}

	// 查询所有合同条款(分页+条件查询)
	@SuppressWarnings("unchecked")
	public String findAllProvision() {
		if (provision != null) {
			ActionContext.getContext().getSession().put("provision", provision);
		} else {
			provision = (Provision) ActionContext.getContext().getSession()
					.get("provision");
		}
 		Object[] object = ProvisionServer.findAllProvision(provision, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			provisionList = (List<Provision>) object[0];
			if (provisionList != null && provisionList.size() > 0) {
				Integer count = (Integer) object[1];
				abc = count;
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProvisionAction!findAllProvision.action");
				try {
					if (successMessage != null && successMessage.length() > 0) {
						successMessage = URLDecoder.decode(successMessage,
								"utf-8");
					}
					if (errorMessage != null && errorMessage.length() > 0) {
						errorMessage = URLDecoder.decode(errorMessage, "utf-8");
					} else {
						errorMessage = null;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAllSuccess";
	}
	//上移
	public String upById(){
		Provision  pr1= ProvisionServer.findProvisionById(id);
		Provision  pr2= ProvisionServer.findUpProvisionByNum(pr1.getNum()-1);
		pr1.setNum(pr1.getNum()-1);
		pr2.setNum(pr2.getNum()+1);
		ProvisionServer.updateProvision(pr1);
		ProvisionServer.updateProvision(pr2);
		return "addProvisionSuccess";
	}
	//下移
	public String downById(){
		Provision  pr1= ProvisionServer.findProvisionById(id);
		Provision  pr2= ProvisionServer.findUpProvisionByNum(pr1.getNum()+1);
		pr1.setNum(pr1.getNum()+1);
		pr2.setNum(pr2.getNum()-1);
		ProvisionServer.updateProvision(pr1);
		ProvisionServer.updateProvision(pr2);
		
		return "addProvisionSuccess";
	}
	// 通过Id查询条款
	public String findProvisionById() {
		provision = ProvisionServer.findProvisionById(id);
		if (provision != null) {
			return "findAllSuccess";
		} else {
			errorMessage = "不存在该条款!请检查后重试!";
		}
		return ERROR;
	}

	// 删除条款
	public String delProvision() throws UnsupportedEncodingException {
		provision = ProvisionServer.findProvisionById(id);
		if (provision != null) {
			boolean bool = ProvisionServer.delProvision(provision);
			if (bool) {

				successMessage = URLEncoder.encode(URLEncoder.encode("删除条款:"
						+ provision.getContent() + " 成功!", "utf-8"), "utf-8");
			} else {
				errorMessage = URLEncoder.encode(URLEncoder.encode("删除条款:"
						+ provision.getContent() + " 失败!", "utf-8"), "utf-8");
			}
			return "addProvisionSuccess";
		} else {
			errorMessage = "不存在该条款!请检查后重试!";
		}
		return ERROR;
	}
	public void delProvision1() {
		
	}
	// 修改条款
	public String updateProvision() throws UnsupportedEncodingException {
		Provision oldProvision = ProvisionServer.findProvisionById(id);
		if (oldProvision != null) {
			boolean bool = ProvisionServer.updateProvision(provision,num);
			if (bool) {

				successMessage = URLEncoder.encode(URLEncoder.encode("修改条款:"
						+ provision.getContent() + " 成功!", "utf-8"), "utf-8");
			} else {
				errorMessage = URLEncoder.encode(URLEncoder.encode("修改条款:"
						+ provision.getContent() + " 失败!", "utf-8"), "utf-8");
			}
			return "addProvisionSuccess";
		} else {
			errorMessage = "不存在该条款!请检查后重试!";
		}
		return ERROR;
	}

	public InterviewLog getInterviewLog() {
		return interviewLog;
	}

	public void setInterviewLog(InterviewLog interviewLog) {
		this.interviewLog = interviewLog;
	}

	public ProvisionServer getProvisionServer() {
		return ProvisionServer;
	}

	public void setProvisionServer(ProvisionServer provisionServer) {
		ProvisionServer = provisionServer;
	}

	public Provision getProvision() {
		return provision;
	}

	public void setProvision(Provision provision) {
		this.provision = provision;
	}

	public List<Provision> getProvisionList() {
		return provisionList;
	}

	public void setProvisionList(List<Provision> provisionList) {
		this.provisionList = provisionList;
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

	public void setNum(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setAbc(Integer abc) {
		this.abc = abc;
	}

	public Integer getAbc() {
		return abc;
	}


}
