package com.task.entity;

import java.util.List;
import java.util.Set;

/**
 * 调查问卷使用人表:(ta_Questionnaireperson)
 * @author 王晓飞
 *
 */
public class QuestionnairePerson implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String qename;//问卷名称
	private String usename;//使用人姓名
	private String dctime;//调查时间
	private Set<QuestionnaireUse> queset;//对应调查问卷使用表(一对多)
	private List<QuestionnaireUse> qulist;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQename() {
		return qename;
	}
	public void setQename(String qename) {
		this.qename = qename;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getDctime() {
		return dctime;
	}
	public void setDctime(String dctime) {
		this.dctime = dctime;
	}
	public Set<QuestionnaireUse> getQueset() {
		return queset;
	}
	public void setQueset(Set<QuestionnaireUse> queset) {
		this.queset = queset;
	}
	public List<QuestionnaireUse> getQulist() {
		return qulist;
	}
	public void setQulist(List<QuestionnaireUse> qulist) {
		this.qulist = qulist;
	}
	
	
}
