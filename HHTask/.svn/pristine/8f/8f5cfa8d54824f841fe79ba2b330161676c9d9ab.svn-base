package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.*;
import com.task.entity.*;

/**
 * 员工培训Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("serial")
public class TrainAction extends ActionSupport {

	private TrainServer trainServer;// Server层
	private Train train;// 员工培训对象
	private List<Train> trainList;// 员工培训集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加员工培训信息
	public String addTrain() {
		if (trainServer.addTrain(train)) {
			successMessage = "添加培训信息成功!";
			return "addTrainSuccess";
		} else {
			errorMessage = "添加培训信息失败!";
			return ERROR;
		}
	}

	// 构造方法

	public TrainServer getTrainServer() {
		return trainServer;
	}

	public void setTrainServer(TrainServer trainServer) {
		this.trainServer = trainServer;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public List<Train> getTrainList() {
		return trainList;
	}

	public void setTrainList(List<Train> trainList) {
		this.trainList = trainList;
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

}
