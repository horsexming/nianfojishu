package com.task.entity.sop;

/***
 * 每周不合格统计(表名:ta_sop_FailureSSMarkId)
 * 
 * @author 刘培
 * 
 */
public class FailureSSMarkId implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String markId;// 零件号
	private String client;// 客户

	private Float oneWeekSc;// 周提交数量
	private Float oneWeekFc;// 不合格品数量
	private Float frequency;// 发生频率

	private Float targetPPM;// 目标值PPM
	private String weekds;// 周(yyyy年xx周)

	private String addTime;// 添加时间(yyyy-MM-dd HH:mm:ss)

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

	public Float getOneWeekSc() {
		return oneWeekSc;
	}

	public void setOneWeekSc(Float oneWeekSc) {
		this.oneWeekSc = oneWeekSc;
	}

	public Float getOneWeekFc() {
		return oneWeekFc;
	}

	public void setOneWeekFc(Float oneWeekFc) {
		this.oneWeekFc = oneWeekFc;
	}

	public Float getFrequency() {
		if (frequency != null) {
			return Float.parseFloat(String.format("%.0f", frequency));
		}
		return frequency;
	}

	public void setFrequency(Float frequency) {
		this.frequency = frequency;
	}

	public Float getTargetPPM() {
		return targetPPM;
	}

	public void setTargetPPM(Float targetPPM) {
		this.targetPPM = targetPPM;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getWeekds() {
		return weekds;
	}

	public void setWeekds(String weekds) {
		this.weekds = weekds;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

}
