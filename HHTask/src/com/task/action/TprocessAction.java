package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TprocessService;
import com.task.entity.Project;
import com.task.entity.Tdetail;
import com.task.entity.Tprocess;

/**
 * 项目管理_工序
 * 
 * @author 马凯
 * 
 */
public class TprocessAction extends ActionSupport {
	private Project project;
	private List<Tdetail> details;
	private List<Tprocess> processes;
	private Tdetail detail;
	private TprocessService tprocessService;

	public String addInput() {
		detail = tprocessService.getDetail(detail);
		return "addInput";
	}

	public String list(){
		details = tprocessService.listDetail(project);
		return "list";
	}
	
	public String add(){
		tprocessService.add(processes);
		return "addOk";
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Tdetail> getDetails() {
		return details;
	}

	public void setDetails(List<Tdetail> details) {
		this.details = details;
	}

	public List<Tprocess> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Tprocess> processes) {
		this.processes = processes;
	}

	public TprocessService getTprocessService() {
		return tprocessService;
	}

	public void setTprocessService(TprocessService tprocessService) {
		this.tprocessService = tprocessService;
	}

	public Tdetail getDetail() {
		return detail;
	}

	public void setDetail(Tdetail detail) {
		this.detail = detail;
	}
	


}
