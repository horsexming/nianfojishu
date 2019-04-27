package com.task.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.banci.SchedulingTable;
import com.task.entity.caiwu.noncore.PayableType;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.FingerprintMg;
import com.task.entity.menjin.ToolCabine;

/**
 * 用户表
 * 
 * @author 刘培
 */
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String code;// 工号
	private String cardId;// 卡号
	private String name;// 人名
	private String sex;// 性别
	private String nation;// 民族
	private String birthplace;// 籍贯
	private String residence;// 户籍
	private Date bothday;// 出生日期
	private String dept;// 部门
	private String duty;// 职务
	private Integer dutyLevel;// 职务级别
	private String post;// 职级
	private String jobtitle;// 职称
	private String leveltitles;// 职称级别
	private String more;// 备注
	private String power;// 权限
	private String yearWage;// 年资
	private String education;// 学历
	private Integer deptId;// 部门id
	private String onWork;// 状态：在职 离职
	private String uid;// 身份证
	private Date joined;// 入职时间
	private String zhuanzhengtime;// 转正时间
	private Date leaveDate;// 离职时间
	private Date tryDays;// 试用期
	private String oneMonth;// 是否足月
	private String isFormal;// 是否正式
	private String leaveAgreement;// 离职协议附件
	private String loginIp;// 登录ip
	private String userNature;// 员工性质

	private String bank;// 所属银行
	private String bankCards;// 银行卡

	private String lastLogin;// 最后一次登录时间
	private String nowLogin;// 当前登录时间
	private Integer loginCount;// 登录次数
	private Integer whenOnlineLong;// 在线总时长;(分钟)
	private Integer whenOnlineLongOfDay;// 当天在线时长(不存入数据库)(分钟)

	private String wagePauseGrant;// 工资暂停发放(是/否)
	private Integer backStage;// 是否拥有后台权限
	private String internal;// 是否属于内部人员 (是 /否)
	private Float allGongshi;// 人员本月总工时
	private String whiteCard;// 是否为白名单
	private String parkAdmin;// 是否为车位管理员
	private String classrole;// 学生，老师
	private Boolean selected;// 是否被选中（页面传值）

	private Password password;// 密码
	private Set<WorkLogClass> worklogClass;// 日志类别(一对多)
	private Set<ModuleFunction> moduleFunction;// 模块功能(多对多)
	private Set<ProcessGzstore> processGzstore;// 工序（多对多）
	private Set<AccessEquipment> accessEquipments;// 门禁设备（多对多）
	private Set<ToolCabine> toolCabines;// 柜子（多对多）
	private Set<PayableType> payableTypes;// 应付类型（多对多）

	private Set<FingerprintMg> fingerprintMg;// 指纹信息表（一对多）
	private Set<SchedulingTable> schedulingTable;// 排班信息表（一对多）
	private Set<Machine> machine;// 工位设备(一对多)
	private Integer banci_id;// 班次id
	private String banci_name;// 班次名称
	private String doorStatus;// 在那扇门中（门名称/null）
	private String user_privilege;// 浩顺考勤机权限(MANAGER:管理员 USER:用户 PEGISTER:登记员
									// OPERTOR:操作员)

	private Integer codeIdNum;
	private Set<JiMiLeiXing> jimileixing;// 机密类型（多对对）
	private List<JiMiLeiXing> jimiList;
	private Set<DeptNumber> deptNumber;// 管理部门（多对多）
	private Set<Category> categorySet;// 物料类别
	private UsersCard userscard;
	private Set<UserRole> userRole;

	private String userStatus;// 人员在岗状态(在岗/离开)
	private String fristTime;// 当天首次打卡

	private String picture;// 页面传值使用
	private String dutyStatus;// 页面传值使用 值日状态
	private List<Calendar> backlogList;// 页面传值使用 代办事项状态
	private Integer successNum;//
	private Integer totalNum;
	private Integer timeOutNum;

	private String phoneNumber; // 显示到快递柜使用

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private Integer successPer;// 完成百分比
	private String successHours;// 完成小时数

	public Users() {

	}

	// 人员状态屏幕使用
	public Users(Integer id, String code, String name, String userStatus,
			String picture, String sex) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.userStatus = userStatus;
		this.picture = picture;
		this.sex = sex;
	}

	public Users(Integer id, String code, String name, String userStatus,
			String picture, String sex, Integer banci_id) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.userStatus = userStatus;
		this.picture = picture;
		this.sex = sex;
		this.banci_id = banci_id;
	}

	// 显示到快递柜使用
	public Users(String code, Integer id, String name, String userStatus,
			String sex, String phoneNumber) {
		this.name = name;
		this.userStatus = userStatus;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.id = id;
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFristTime() {
		return fristTime;
	}

	public void setFristTime(String fristTime) {
		this.fristTime = fristTime;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	/**
	 * @return the user_privilege
	 */
	public String getUser_privilege() {
		return user_privilege;
	}

	/**
	 * MANAGER:管理员 USER:用户 PEGISTER:登记员 OPERTOR:操作员
	 * 
	 * @param userPrivilege
	 *            the user_privilege to set
	 */
	public void setUser_privilege(String userPrivilege) {
		user_privilege = userPrivilege;
	}

	@JSONField(serialize = false)
	public Set<PayableType> getPayableTypes() {
		return payableTypes;
	}

	public void setPayableTypes(Set<PayableType> payableTypes) {
		this.payableTypes = payableTypes;
	}

	@JSONField(serialize = false)
	public Set<SchedulingTable> getSchedulingTable() {
		return schedulingTable;
	}

	public void setSchedulingTable(Set<SchedulingTable> schedulingTable) {
		this.schedulingTable = schedulingTable;
	}

	@JSONField(serialize = false)
	public Set<FingerprintMg> getFingerprintMg() {
		return fingerprintMg;
	}

	public void setFingerprintMg(Set<FingerprintMg> fingerprintMg) {
		this.fingerprintMg = fingerprintMg;
	}

	@JSONField(serialize = false)
	public Set<ToolCabine> getToolCabines() {
		return toolCabines;
	}

	public void setToolCabines(Set<ToolCabine> toolCabines) {
		this.toolCabines = toolCabines;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public Date getBothday() {
		return bothday;
	}

	public void setBothday(Date bothday) {
		this.bothday = bothday;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Integer getDutyLevel() {
		return dutyLevel;
	}

	public void setDutyLevel(Integer dutyLevel) {
		this.dutyLevel = dutyLevel;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getYearWage() {
		return yearWage;
	}

	public void setYearWage(String yearWage) {
		this.yearWage = yearWage;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getOnWork() {
		return onWork;
	}

	public void setOnWork(String onWork) {
		this.onWork = onWork;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getTryDays() {
		return tryDays;
	}

	public void setTryDays(Date tryDays) {
		this.tryDays = tryDays;
	}

	public String getOneMonth() {
		return oneMonth;
	}

	public void setOneMonth(String oneMonth) {
		this.oneMonth = oneMonth;
	}

	public String getIsFormal() {
		return isFormal;
	}

	public void setIsFormal(String isFormal) {
		this.isFormal = isFormal;
	}

	@JSONField(serialize = false)
	public Set<WorkLogClass> getWorklogClass() {
		return worklogClass;
	}

	public void setWorklogClass(Set<WorkLogClass> worklogClass) {
		this.worklogClass = worklogClass;
	}

	@JSONField(serialize = false)
	public Password getPassword() {
		return password;
	}

	public void setPassword(Password password) {
		this.password = password;
	}

	@JSONField(serialize = false)
	public Set<ModuleFunction> getModuleFunction() {
		return moduleFunction;
	}

	public void setModuleFunction(Set<ModuleFunction> moduleFunction) {
		this.moduleFunction = moduleFunction;
	}

	public String getLeaveAgreement() {
		return leaveAgreement;
	}

	public void setLeaveAgreement(String leaveAgreement) {
		this.leaveAgreement = leaveAgreement;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankCards() {
		return bankCards;
	}

	public void setBankCards(String bankCards) {
		this.bankCards = bankCards;
	}

	public String getWagePauseGrant() {
		return wagePauseGrant;
	}

	public void setWagePauseGrant(String wagePauseGrant) {
		this.wagePauseGrant = wagePauseGrant;
	}

	@JSONField(serialize = false)
	public Set<ProcessGzstore> getProcessGzstore() {
		return processGzstore;
	}

	public void setProcessGzstore(Set<ProcessGzstore> processGzstore) {
		this.processGzstore = processGzstore;
	}

	public Integer getBackStage() {
		return backStage;
	}

	public void setBackStage(Integer backStage) {
		this.backStage = backStage;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof Users) {
			Users u = (Users) obj;
			if (id != null)
				return this.id.equals(u.getId());
		}
		return false;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getLeveltitles() {
		return leveltitles;
	}

	public void setLeveltitles(String leveltitles) {
		this.leveltitles = leveltitles;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getNowLogin() {
		return nowLogin;
	}

	public void setNowLogin(String nowLogin) {
		this.nowLogin = nowLogin;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Float getAllGongshi() {
		return allGongshi;
	}

	public void setAllGongshi(Float allGongshi) {
		this.allGongshi = allGongshi;
	}

	public String getWhiteCard() {
		return whiteCard;
	}

	public void setWhiteCard(String whiteCard) {
		this.whiteCard = whiteCard;
	}

	public String getParkAdmin() {
		return parkAdmin;
	}

	public void setParkAdmin(String parkAdmin) {
		this.parkAdmin = parkAdmin;
	}

	public Integer getBanci_id() {
		return banci_id;
	}

	public void setBanci_id(Integer banciId) {
		banci_id = banciId;
	}

	public String getBanci_name() {
		return banci_name;
	}

	public void setBanci_name(String banciName) {
		banci_name = banciName;
	}

	@JSONField(serialize = false)
	public Set<AccessEquipment> getAccessEquipments() {
		return accessEquipments;
	}

	@JSONField(serialize = false)
	public Set<JiMiLeiXing> getJimileixing() {
		return jimileixing;
	}

	public void setJimileixing(Set<JiMiLeiXing> jimileixing) {
		this.jimileixing = jimileixing;
	}

	public List<JiMiLeiXing> getJimiList() {
		return jimiList;
	}

	@JSONField(serialize = false)
	public void setJimiList(List<JiMiLeiXing> jimiList) {
		this.jimiList = jimiList;
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}

	public void setAccessEquipments(Set<AccessEquipment> accessEquipments) {
		this.accessEquipments = accessEquipments;
	}

	public Integer getCodeIdNum() {
		return codeIdNum;
	}

	public void setCodeIdNum(Integer codeIdNum) {
		this.codeIdNum = codeIdNum;
	}

	public String getZhuanzhengtime() {
		return zhuanzhengtime;
	}

	public void setZhuanzhengtime(String zhuanzhengtime) {
		this.zhuanzhengtime = zhuanzhengtime;
	}

	@JSONField(serialize = false)
	public Set<Machine> getMachine() {
		return machine;
	}

	public void setMachine(Set<Machine> machine) {
		this.machine = machine;
	}

	public String getClassrole() {
		return classrole;
	}

	public void setClassrole(String classrole) {
		this.classrole = classrole;
	}

	@JSONField(serialize = false)
	public Set<DeptNumber> getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(Set<DeptNumber> deptNumber) {
		this.deptNumber = deptNumber;
	}

	@JSONField(serialize = false)
	public Set<Category> getCategorySet() {
		return categorySet;
	}

	public void setCategorySet(Set<Category> categorySet) {
		this.categorySet = categorySet;
	}

	@JSONField(serialize = false)
	public UsersCard getUserscard() {
		return userscard;
	}

	public void setUserscard(UsersCard userscard) {
		this.userscard = userscard;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@JSONField(serialize = false)
	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public String getUserNature() {
		return userNature;
	}

	public void setUserNature(String userNature) {
		this.userNature = userNature;
	}

	public Integer getWhenOnlineLong() {
		return whenOnlineLong;
	}

	public void setWhenOnlineLong(Integer whenOnlineLong) {
		this.whenOnlineLong = whenOnlineLong;
	}

	public Integer getWhenOnlineLongOfDay() {
		return whenOnlineLongOfDay;
	}

	public void setWhenOnlineLongOfDay(Integer whenOnlineLongOfDay) {
		this.whenOnlineLongOfDay = whenOnlineLongOfDay;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDutyStatus() {
		return dutyStatus;
	}

	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}

	public List<Calendar> getBacklogList() {
		return backlogList;
	}

	public void setBacklogList(List<Calendar> backlogList) {
		this.backlogList = backlogList;
	}

	public Integer getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getTimeOutNum() {
		return timeOutNum;
	}

	public void setTimeOutNum(Integer timeOutNum) {
		this.timeOutNum = timeOutNum;
	}

	public Integer getSuccessPer() {
		return successPer;
	}

	public void setSuccessPer(Integer successPer) {
		this.successPer = successPer;
	}

	public String getSuccessHours() {
		return successHours;
	}

	public void setSuccessHours(String successHours) {
		this.successHours = successHours;
	}
}
