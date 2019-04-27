package com.task.entity.sop;
/**
 * 特权零件 ta_ProcardTemplatePrivilege
 * @author txb
 *
 */
public class ProcardTemplatePrivilege  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String markId;//件号
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	
}
