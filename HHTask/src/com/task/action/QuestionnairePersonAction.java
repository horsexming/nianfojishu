package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.QuestionTemplateServer;
import com.task.Server.QuestionnairePersonServer;
import com.task.entity.QuestionTemplate;
import com.task.entity.Questionnaire;
import com.task.entity.QuestionnairePerson;
import com.task.entity.QuestionnaireUse;

public class QuestionnairePersonAction extends ActionSupport{

	private QuestionnairePerson qp;
	private QuestionnaireUse qu;
	private List<QuestionnairePerson> qplist;
	private List<QuestionnaireUse> qulist;
	private QuestionTemplate qt;
	private List<Questionnaire> qeList;
	private QuestionnairePersonServer qpserver;
	private QuestionTemplateServer qtserver;
	private Integer id;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private String tag;
	public String initadd(){
		if(id!=null && id>0){
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map =qtserver.findqeList(id);
			if(map!=null && map.size()>0){
				qt=	(QuestionTemplate) map.get(1);
				qeList = (List<Questionnaire>) map.get(2);
			}
		}
		return "qp_add";
	}
	
	//查询所有
	public String showAllqplist(){
		if("del_true".equals(errorMessage)){
			errorMessage = "删除成功!";
		}else if("del_error".equals(errorMessage)){
			errorMessage = "删除失败!";
		}
		int count=qpserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		qplist=qpserver.findAllQUlist(Integer.parseInt(cpage), pageSize);
		if(qplist!=null){
			this.setUrl("QuestionnairePersonAction_showAllqplist.action");
			
		}else{
			errorMessage="没有员工职业轨迹";
		}
		return "qp_showlist";
	}
	//条件查询
	public String findqplist(){
		if(qp!=null){
			ActionContext.getContext().getSession().put("qp", qp);
		}else{
			qp=(QuestionnairePerson) ActionContext.getContext().getSession().get("qp");
		}
		
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=qpserver.findQUlistByCondition(qp, Integer.parseInt(cpage), pageSize,status);
		qplist=(List<QuestionnairePerson>) map.get(1);
		if(qplist!=null && qplist.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("QuestionnairePersonAction_findqplist.action?status="+status);
			
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "qp_showlist";
	}
	
	public String addqp(){
		if(qp!=null){
			errorMessage = "添加失败";
			if("true".equals(qpserver.add(qp))){
				errorMessage = null;
				return "showAllqplist";
			}
		}
		return "initadd";
	}
	
	public String updateqp(){
		if(qp!=null){
			errorMessage = "update_error";
			if(qpserver.update(qp)){
				errorMessage = "update_true";
			}
		}
		return "findqpByid";
	}
	public String delqp(){
		if(qp!=null){
			errorMessage = "del_error";
			if(qpserver.del(qp)){
				errorMessage = "del_true";
			}
		}
		return "showAllqplist";
	}
	public String findqpByid(){
		if(qp!=null){
			if("update_true".equals(errorMessage)){
				errorMessage="修改成功!";
			}else if("update_error".equals(errorMessage)){
				errorMessage="修改失败!";
			}
			Map<Integer, Object> map = new HashMap<Integer, Object>();
			map = qpserver.findQpByid(qp.getId());
			if(map!=null&&map.size()>0){
				qp=(QuestionnairePerson) map.get(1);
				qulist = (List<QuestionnaireUse>) map.get(2);
				if("mingxi".equals(status)){
					return "qu_showlist";
				}else{
					return "qp_show";
				}
			}
		}
		return ERROR;
	}
	public QuestionnairePerson getQp() {
		return qp;
	}
	public void setQp(QuestionnairePerson qp) {
		this.qp = qp;
	}
	public QuestionnaireUse getQu() {
		return qu;
	}
	public void setQu(QuestionnaireUse qu) {
		this.qu = qu;
	}
	public List<QuestionnairePerson> getQplist() {
		return qplist;
	}
	public void setQplist(List<QuestionnairePerson> qplist) {
		this.qplist = qplist;
	}
	public List<QuestionnaireUse> getQulist() {
		return qulist;
	}
	public void setQulist(List<QuestionnaireUse> qulist) {
		this.qulist = qulist;
	}
	public QuestionnairePersonServer getQpserver() {
		return qpserver;
	}
	public void setQpserver(QuestionnairePersonServer qpserver) {
		this.qpserver = qpserver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}

	public QuestionTemplate getQt() {
		return qt;
	}

	public void setQt(QuestionTemplate qt) {
		this.qt = qt;
	}

	public List<Questionnaire> getQeList() {
		return qeList;
	}

	public void setQeList(List<Questionnaire> qeList) {
		this.qeList = qeList;
	}

	public QuestionTemplateServer getQtserver() {
		return qtserver;
	}

	public void setQtserver(QuestionTemplateServer qtserver) {
		this.qtserver = qtserver;
	}
	
	
}
