package com.task.entity;

import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/****
 * 表名:macrepair
 * 
 * @author 程玉林
 * 
 */
public class Maintenance implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String workArea;
	private String workPosition; 	
	private String no;
	private String type;//设备类型
	private String name;//设备名称
	private String classGroup;
	private String status;// (故障、故障指派、维修中、修复待验证、正常)
	private Date alarmTime;// 报修时间
	private Integer alermManId;
	private String alermMan;
	private Integer alermDeptId;
	private String alermDept;
	private Integer alermBossId;
	private String alermBoss;
	private String barcode;// 报修单号
	private Date startTime;
	private Date repTime;
	private Date endTime;
	private String repairMan;
	private String faultDetail;
	private String faultReason;
	private String repairDetail;
	private Integer checkManId;
	private String checkMan;
	private Date checkTime;
	private String checkIdea;
	private String changeParts;
	private String more;
	private Integer countdowntime;// 倒计时天数
	private String timetorepair;// 修复时间
	private String persontime;// 修复确认时间
	private Integer userid;// 用户Id
	private Float updateTime;//维修时长(小时)
	private Set<Parts> partsset;// (保修对零件：一对多)

	public Integer getId() {
		return id;
	}
	@JSONField(serialize=false)
	public Set<Parts> getPartsset() {
		return partsset;
	}

	public void setPartsset(Set<Parts> partsset) {
		this.partsset = partsset;
	}

	public String getWorkArea() {
		return workArea;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public String getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getClassGroup() {
		return classGroup;
	}

	public String getStatus() {
		return status;
	}

	public Date getAlarmTime() {
		return alarmTime;
	}

	public Integer getAlermManId() {
		return alermManId;
	}

	public String getAlermMan() {
		return alermMan;
	}

	public Integer getAlermDeptId() {
		return alermDeptId;
	}

	public String getAlermDept() {
		return alermDept;
	}

	public Integer getAlermBossId() {
		return alermBossId;
	}

	public String getAlermBoss() {
		return alermBoss;
	}

	public String getBarcode() {
		return barcode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public Date getRepTime() {
		return repTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public String getRepairMan() {
		return repairMan;
	}

	public String getFaultDetail() {
		return faultDetail;
	}

	public String getFaultReason() {
		return faultReason;
	}

	public String getRepairDetail() {
		return repairDetail;
	}

	public Integer getCheckManId() {
		return checkManId;
	}

	public String getCheckMan() {
		return checkMan;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public String getCheckIdea() {
		return checkIdea;
	}

	public String getChangeParts() {
		return changeParts;
	}

	public String getMore() {
		return more;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassGroup(String classGroup) {
		this.classGroup = classGroup;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAlarmTime(Date alarmTime) {
		this.alarmTime = alarmTime;
	}

	public void setAlermManId(Integer alermManId) {
		this.alermManId = alermManId;
	}

	public void setAlermMan(String alermMan) {
		this.alermMan = alermMan;
	}

	public void setAlermDeptId(Integer alermDeptId) {
		this.alermDeptId = alermDeptId;
	}

	public void setAlermDept(String alermDept) {
		this.alermDept = alermDept;
	}

	public void setAlermBossId(Integer alermBossId) {
		this.alermBossId = alermBossId;
	}

	public void setAlermBoss(String alermBoss) {
		this.alermBoss = alermBoss;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setRepTime(Date repTime) {
		this.repTime = repTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	public void setFaultDetail(String faultDetail) {
		this.faultDetail = faultDetail;
	}

	public void setFaultReason(String faultReason) {
		this.faultReason = faultReason;
	}

	public void setRepairDetail(String repairDetail) {
		this.repairDetail = repairDetail;
	}

	public void setCheckManId(Integer checkManId) {
		this.checkManId = checkManId;
	}

	public void setCheckMan(String checkMan) {
		this.checkMan = checkMan;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public void setCheckIdea(String checkIdea) {
		this.checkIdea = checkIdea;
	}

	public void setChangeParts(String changeParts) {
		this.changeParts = changeParts;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCountdowntime() {
		return countdowntime;
	}

	public void setCountdowntime(Integer countdowntime) {
		this.countdowntime = countdowntime;
	}

	public String getTimetorepair() {
		return timetorepair;
	}

	public void setTimetorepair(String timetorepair) {
		this.timetorepair = timetorepair;
	}

	public String getPersontime() {
		return persontime;
	}

	public void setPersontime(String persontime) {
		this.persontime = persontime;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Float getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Float updateTime) {
		this.updateTime = updateTime;
	}

}
