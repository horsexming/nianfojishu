package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.QuestionTemplateServer;
import com.task.entity.Careertrack;
import com.task.entity.QuestionTemplate;
import com.task.entity.Questionnaire;

public class QuestionTemplateAction  extends ActionSupport{

	private QuestionTemplate qt;
	private List<QuestionTemplate> qtList;
	private Questionnaire qe;
	private List<Questionnaire> qeList;
	private Integer id;
	private QuestionTemplateServer qtserver;
	private int size;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private String tag;
	
	//查询所有(分页)
	public String showAllqtList(){
		if("del_true".equals(errorMessage)){
			errorMessage = "删除成功!";
		}else if("del_error".equals(errorMessage)){
			errorMessage = "删除失败!";
		}
		int count=qtserver.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		qtList=qtserver.showAllqtList(Integer.parseInt(cpage), pageSize);
		if(qtList!=null){
			this.setUrl("QuestionTemplateAction_showAllqtList.action");
			
		}else{
			errorMessage="没有员工职业轨迹";
		}
		return "qt_showlist";
	}
	//条件查询
	public String findqtList(){
		if(qt!=null){
			ActionContext.getContext().getSession().put("qt", qt);
		}else{
			qt=(QuestionTemplate) ActionContext.getContext().getSession().get("qt");
		}
		
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=qtserver.findqtListByCondition(qt, Integer.parseInt(cpage), pageSize,status);
		qtList=(List<QuestionTemplate>) map.get(1);
		if(qtList!=null && qtList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("QuestionTemplateAction_findqtList.action?status="+status);
			
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "qt_showlist";
	}
	//添加
	public String addqt(){
		if(qt!=null){
			errorMessage = "添加失败!";
			if(qtserver.add(qt)){
				errorMessage = null;
				return "showAllqtList";
			}
		}
		return "qt_add";
	}
	//删除
	public String delqt(){
		if(qt!=null){
			errorMessage = "del_error";
			if(qtserver.del(qt)){
				errorMessage = "del_true";
			}
		}
		return "showAllqtList";
	}
	//修改调查问卷模板
	public String updateqt(){
		if(qt!=null){
			errorMessage = "update_error";
			if(qtserver.update(qt)){
				id = qt.getId();
				errorMessage = "update_true";	}
		}
		return "findqeListbyid";
	}
	
	// 根据 调查模板Id 查询 调查问卷明细
	public String findqeListbyid(){
		if("update_error".equals(errorMessage)){
			errorMessage = "修改失败!";
		}else if("update_true".equals(errorMessage)){
			errorMessage = "修改成功!";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = qtserver.findqeList(id);
		if(map!=null && map.size()>0){
			qt = (QuestionTemplate) map.get(1);
			qeList = (List<Questionnaire>) map.get(2);
			if(qeList!=null && qeList.size()>0){
				size = qeList.size();
			}
			if("mingxi".equals(status)){
				return "qe_showlist";
			}else{
				return "qt_show";
			}
			
		}
		
			return  ERROR;
	}
	//根据id 获得调查问卷明细
	public String findqebyId(){
		if(id!=null){
			qe = qtserver.findqebyId(id);
			if(qe!=null){
				return "qe_show";
			}
		}
		return ERROR;
	}
	
	//修改 Questionnaire 调查问卷明细
	public String updateqe(){
		if(qe!=null){
			qe = qtserver.findqebyId(qe.getId());
			if(qe.getQuestionTemplate()!=null && qe.getQuestionTemplate().getId()!=null){
				id = qe.getQuestionTemplate().getId();
			}
			errorMessage = "修改失败";
			if(qtserver.updateqe(qe)){
				errorMessage = "修改成功";
			}
		}
		return "findqeListbyid";
	}
	public String delqe(){
		if(qe!=null){
			qe = qtserver.findqebyId(qe.getId());
			if(qe.getQuestionTemplate()!=null && qe.getQuestionTemplate().getId()!=null){
				id = qe.getQuestionTemplate().getId();
			}
			errorMessage = "删除失败";
			if(qtserver.delqe(qe)){
				errorMessage = "删除成功";
			}
		}
		return "findqeListbyid";
	}
	
	public QuestionTemplate getQt() {
		return qt;
	}
	public void setQt(QuestionTemplate qt) {
		this.qt = qt;
	}
	public List<QuestionTemplate> getQtList() {
		return qtList;
	}
	public void setQtList(List<QuestionTemplate> qtList) {
		this.qtList = qtList;
	}
	public Questionnaire getQe() {
		return qe;
	}
	public void setQe(Questionnaire qe) {
		this.qe = qe;
	}
	public List<Questionnaire> getQeList() {
		return qeList;
	}
	public void setQeList(List<Questionnaire> qeList) {
		this.qeList = qeList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public QuestionTemplateServer getQtserver() {
		return qtserver;
	}
	public void setQtserver(QuestionTemplateServer qtserver) {
		this.qtserver = qtserver;
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
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
