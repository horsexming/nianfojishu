package com.task.entity;

import com.task.util.FieldMeta;


/***
 * 
 * 提奖记录表(表名:Mentionrecord)
 * 
 * @author 钟永林
 */
public class Mentionrecord  implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id; // 序号 int
	@FieldMeta(name="月份")
	private String mentionMonth; // 月份 varchar
	@FieldMeta(name="应提奖金额")
	private Float mentionshallMoney; // 应提奖金额 Float
	@FieldMeta(name="实际提奖金额")
	private Float mentionactualMoney; // 实际提奖金额 Float
	@FieldMeta(name="提奖状态")
	private String mentionstatus;// 提奖状态 varchar(审核中、可提奖、已提奖)
	private String mentiondatetime;// 时间锥
	private String mentionmore; // 备注 varchar
	private Integer epId;// 审批流程Id
	

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMentionMonth() {
		return mentionMonth;
	}

	public void setMentionMonth(String mentionMonth) {
		this.mentionMonth = mentionMonth;
	}

	public Float getMentionshallMoney() {
		return mentionshallMoney;
	}

	public void setMentionshallMoney(Float mentionshallMoney) {
		this.mentionshallMoney = mentionshallMoney;
	}

	public Float getMentionactualMoney() {
		return mentionactualMoney;
	}

	public void setMentionactualMoney(Float mentionactualMoney) {
		this.mentionactualMoney = mentionactualMoney;
	}

	public String getMentionstatus() {
		return mentionstatus;
	}

	public void setMentionstatus(String mentionstatus) {
		this.mentionstatus = mentionstatus;
	}

	public String getMentionmore() {
		return mentionmore;
	}

	public void setMentionmore(String mentionmore) {
		this.mentionmore = mentionmore;
	}

	public String getMentiondatetime() {
		return mentiondatetime;
	}

	public void setMentiondatetime(String mentiondatetime) {
		this.mentiondatetime = mentiondatetime;
	}

}
