package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.QexamineServer;
import com.task.entity.Qexamine;
import com.task.entity.Stylebook;
import com.task.util.MKUtil;

public class QexamineAction extends ActionSupport {
	private Qexamine qexamine;
	private QexamineServer QexamineServer;
	private Stylebook stylebook;
	

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private String pageStatus;
	private List list;
	private List aaa;

	// 进入创建质量审查添加界面
	public String addQexamine() {
		qexamine = null;
		return "addQexamine";
	}
	// 进入修改质量审查界面
	public String infixQexamine() {
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		return "fixqexamine";
	}
	// 删除质量审查表
	public String deleteQexamine(){
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		QexamineServer.deleteQexamine(qexamine);
		return "qexamineList";
	}
	// 保存创建的质量审查表
	public String saveQexamine() {
		QexamineServer.saveQexamine(qexamine);
		return "qexamineList";
	}
	// 修改质量审查审查表
	public String fixQexamine(){
		QexamineServer.updateQexamine(qexamine);
		return "qexamineList";
	}

	// 进入添加样品界面
	public String enterStylebook() {
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		return "addstylebook";
	}
	//进入单个样品预览界面
	public String styleFind(){
		stylebook = QexamineServer.stylebookFind(stylebook.getId());
		qexamine = QexamineServer.qexamineFind(stylebook.getQid());
		return "stylebook";
	}
	// 进入添加缺陷系数界面
	public String enterCoffficient() {
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		return "addcoffficient";
	}
	// 进入添加缺陷数界面
	public String enterDefect() {
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		return "adddefect";
	}
	// 保存添加样品
	public String saveStylebook() {
		QexamineServer.saveStyle(stylebook);
		return "findQexamine";
	}

	// 分页查询全部质量审查表
	public String qexamineList() {
		list = QexamineServer.qexaminelist(Integer.parseInt(cpage), pageSize);
		this.setUrl("QexamineAction!qexamineList.action");
		int count = QexamineServer.qexamineCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "qexamineListfind";
	}
	//安卓查询全部质量审查表
	public void qexamineAndList(){
		try{
		list = QexamineServer.qexamineFindList();
		MKUtil.writeJSON(true,null,list);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 预览单个质量审查表
	public String findQexamine() {
		qexamine = QexamineServer.qexamineFind(qexamine.getId());
		list = QexamineServer.stylebookList(qexamine.getId());
		return 	"stylebooklist";
	}
	//删除单个样本
	public String deleteStylebook(){
		stylebook = QexamineServer.stylebookFind(stylebook.getId());
		QexamineServer.deleteStylebook(stylebook);
		errorMessage = "删除成功";
		//return "qexamineList";
		//url = "QexamineAction!findQexamine.action?qexamine.id="+stylebook.getQid();
		//return ERROR;
		return "stylebook";
	}
	//进入单个样本修改
	public String  enterFixStylebook(){
		stylebook = QexamineServer.stylebookFind(stylebook.getId());
		qexamine = QexamineServer.qexamineFind(stylebook.getQid());
		return "fixStylebook";
	}
	//修改单个样本,修改缺陷数
	public String fixStylebook(){
		QexamineServer.updateStyleboook(stylebook);
		return "findQexamine";
	}
	//进入修改缺陷数
	public String  enterFixCoffficient(){
		stylebook = QexamineServer.stylebookFind(stylebook.getId());
		qexamine = QexamineServer.qexamineFind(stylebook.getQid());
		return "fixCoffficient";
	}


	public void setQexamineServer(QexamineServer qexamineServer) {
		QexamineServer = qexamineServer;
	}

	public QexamineServer getQexamineServer() {
		return QexamineServer;
	}

	public void setStylebook(Stylebook stylebook) {
		this.stylebook = stylebook;
	}

	public Stylebook getStylebook() {
		return stylebook;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Qexamine getQexamine() {
		return qexamine;
	}

	public void setQexamine(Qexamine qexamine) {
		this.qexamine = qexamine;
	}

	public void setAaa(List aaa) {
		this.aaa = aaa;
	}

	public List getAaa() {
		return aaa;
	}

}
