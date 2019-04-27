package com.task.entity;

import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 调查问卷模板表(ta_QuestionTemplate)
 * @author 王晓飞
 *
 */

public class QuestionTemplate implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String addusername;//添加人姓名
	private String adddept;//添加人部门
	private Integer adduserId;//添加人userId
	private String addtime;//添加时间
	private String name;//问卷名称
	private Set<Questionnaire> questionnaire;//对应调查问卷明细(一对多)
	private List<Questionnaire> qeList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddusername() {
		return addusername;
	}
	public void setAddusername(String addusername) {
		this.addusername = addusername;
	}
	public String getAdddept() {
		return adddept;
	}
	public void setAdddept(String adddept) {
		this.adddept = adddept;
	}
	public Integer getAdduserId() {
		return adduserId;
	}
	public void setAdduserId(Integer adduserId) {
		this.adduserId = adduserId;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JSONField(serialize = false)
	public Set<Questionnaire> getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(Set<Questionnaire> questionnaire) {
		this.questionnaire = questionnaire;
	}
	public List<Questionnaire> getQeList() {
		return qeList;
	}
	public void setQeList(List<Questionnaire> qeList) {
		this.qeList = qeList;
	}
	
	
}
