package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.JoinTrainServer;
import com.task.entity.JoinTrain;
import com.task.entity.JoinTrainDetails;

import java.util.List;

/***
 * 入职培训Action
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class JoinTrainAction extends ActionSupport {

	private JoinTrainServer joinTrainServer;
	private JoinTrain joinTrain;// 培训记录
	private JoinTrainDetails jtDetails;// 培训记录明细
	private List<JoinTrain> joinTrainList;
	private String successMessage;
	private String errorMessage;
	private int id;

	private String cpage;
	private String total;
	private String url;
	private int pageSize;

	public JoinTrainAction() {
		cpage = "1";
		pageSize = 15;
	}

	// 添加入职培训
	public String addJoinTrain() {
		if (joinTrainServer.addJoinTrain(joinTrain, jtDetails)) {
			successMessage = "添加成功!";
			return "addJoinTrain";
		} else {
			return "error";
		}
	}

	// 查询入职培训记录
	public String findJoinTrainByUid() {
		joinTrainList = joinTrainServer.findJoinTrainByUid(Integer.valueOf(id));
		return "joinTrain";
	}

	// 删除入职培训
	public String delJoinTrain() {
		joinTrain = joinTrainServer.findJoinTrainById(id);
		if (joinTrain != null) {
			id = joinTrain.getUser().getId();
			if (joinTrainServer.delJoinTrain(joinTrain)) {
				return "addJoinTrain";
			}
		} else {
			errorMessage = "不存在该入职培训记录!";
		}

		return ERROR;
	}

	public JoinTrainServer getJoinTrainServer() {
		return joinTrainServer;
	}

	public void setJoinTrainServer(JoinTrainServer joinTrainServer) {
		this.joinTrainServer = joinTrainServer;
	}

	public JoinTrain getJoinTrain() {
		return joinTrain;
	}

	public void setJoinTrain(JoinTrain joinTrain) {
		this.joinTrain = joinTrain;
	}

	public List<JoinTrain> getJoinTrainList() {
		return joinTrainList;
	}

	public void setJoinTrainList(List<JoinTrain> joinTrainList) {
		this.joinTrainList = joinTrainList;
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

	public JoinTrainDetails getJtDetails() {
		return jtDetails;
	}

	public void setJtDetails(JoinTrainDetails jtDetails) {
		this.jtDetails = jtDetails;
	}
}
