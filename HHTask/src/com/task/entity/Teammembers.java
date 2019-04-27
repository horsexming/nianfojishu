package com.task.entity;

/***
 * 班组成员表(表名:ta_hr_jjfp_teammembers)
 * 
 * @author 钟永林
 */
public class Teammembers implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id; // 序号
	private String teammembersteam; // 班组
	private String teammembersteamname; // 成员姓名
	private String teammembersmembernumber; // 成员工号
	private String teammemberscardnumber;// 成员卡号
	private String teammembersdatatime; // 时间
	private String teammembersremarks; // 备注
	private Integer addUserId;// 添加人ID
	private String addUserCode;// 添加人的工号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeammembersteam() {
		return teammembersteam;
	}

	public void setTeammembersteam(String teammembersteam) {
		this.teammembersteam = teammembersteam;
	}

	public String getTeammembersteamname() {
		return teammembersteamname;
	}

	public void setTeammembersteamname(String teammembersteamname) {
		this.teammembersteamname = teammembersteamname;
	}

	public String getTeammembersmembernumber() {
		return teammembersmembernumber;
	}

	public void setTeammembersmembernumber(String teammembersmembernumber) {
		this.teammembersmembernumber = teammembersmembernumber;
	}

	public String getTeammembersdatatime() {
		return teammembersdatatime;
	}

	public void setTeammembersdatatime(String teammembersdatatime) {
		this.teammembersdatatime = teammembersdatatime;
	}

	public String getTeammembersremarks() {
		return teammembersremarks;
	}

	public void setTeammembersremarks(String teammembersremarks) {
		this.teammembersremarks = teammembersremarks;
	}

	public String getTeammemberscardnumber() {
		return teammemberscardnumber;
	}

	public void setTeammemberscardnumber(String teammemberscardnumber) {
		this.teammemberscardnumber = teammemberscardnumber;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public String getAddUserCode() {
		return addUserCode;
	}

	public void setAddUserCode(String addUserCode) {
		this.addUserCode = addUserCode;
	}

}
