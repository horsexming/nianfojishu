package com.task.action;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ProjectTrackRecordService;
import com.task.Server.ProjectTrackService;
import com.task.entity.ProjectTrack;
import com.task.entity.ProjectTrackRecord;

/**
 * 项目跟踪
 * @author 马凯
 *
 */
public class ProjectTrackAction extends ActionSupport {
	
	private ProjectTrack projectTrack;
	private ProjectTrackRecord projectTrackRecord;
	
	private ProjectTrackService projectTrackService;
	private ProjectTrackRecordService projectTrackRecordService;
	
	public String add(){
		if(projectTrackService.get(projectTrack.getRoot()) != null){
			return "has";
		}
		projectTrackService.add(projectTrack);
		return SUCCESS;
	}

	public String addInput(){
		return INPUT;
	}

	public String list(){
		projectTrack = projectTrackService.get(projectTrack.getRoot());
		if(projectTrack == null){
			return "nohas"; 
		}
		projectTrackRecord = projectTrackRecordService.get(projectTrack);
		return SUCCESS;
	}
	
	public String closed(){
		projectTrackService.updateClose(projectTrack);
		return null;
	}

	public ProjectTrack getProjectTrack() {
		return projectTrack;
	}

	public void setProjectTrack(ProjectTrack projectTrack) {
		this.projectTrack = projectTrack;
	}

	public ProjectTrackService getProjectTrackService() {
		return projectTrackService;
	}

	public void setProjectTrackService(ProjectTrackService projectTrackService) {
		this.projectTrackService = projectTrackService;
	}

	public ProjectTrackRecord getProjectTrackRecord() {
		return projectTrackRecord;
	}

	public void setProjectTrackRecord(ProjectTrackRecord projectTrackRecord) {
		this.projectTrackRecord = projectTrackRecord;
	}

	public ProjectTrackRecordService getProjectTrackRecordService() {
		return projectTrackRecordService;
	}

	public void setProjectTrackRecordService(ProjectTrackRecordService projectTrackRecordService) {
		this.projectTrackRecordService = projectTrackRecordService;
	}



}
