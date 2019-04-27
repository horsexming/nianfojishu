package com.task.entity.project;
/**
 * 研发项目(ta_pro_ProjectManageyf)
 * @author txb
 *
 */
public class ProjectManageyf implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String proNum;//项目编号
	private String proName;//项目名称
	private Integer addUserId;//添加人
	private String addUserName;//
	private String addUserCode;//
	private String addTime;//添加时间
	private String startTime;//开始时间
	private String reTime;//预完成时间
	private String shijiTime;//实际完成时间
	private Integer outTime;  //超出时间
	private String outExplain; //延期说明
	private String outTimeResult;//超时同意结果
	private String outTimeexplainPerson; //延期审批人
	private Integer delayTime;//延期时间  提前或延后时间    delayTime   正负值
	private String zpTime;//指派时间
	private Integer poolId;//项目池id
	private String remark;//描述
	private String status;//状态 待指派。待完善。待填报。待执行。执行中。重新执行。待确认。完成 <已逾期>
	//   ，待执行，执行中，完成 ，打回（重新执行）
	
	//----------------------优先级
	
	
	private Integer rootId;//
	private String fatherId;//
	private Integer belongLayer;// 当前层
	
	private Integer gradeEpId; //评分审批id
	private Integer gradeStore;//评分分数→项目评分
	private String gradeStatus; //评分状态  //预申请，待审批，审批中，同意，打回
	private String gradeRemark;//评分描述
	
	private Integer sumStore; // 主项目和主项目下所的的分数 
	private Integer selfStore; //本项目预期分数
	private Integer actualStore;//本项目实际分值
	private String yfProjectFile; //研发项目文件
	private String aliasFile;//文件别名
	private String schedule; //项目进度内容
	private Integer projectManageId;//项目立项信息
	
	private Integer depends;//前置任务
	private Integer duration;//工期
	private String  startIsMilestone;//开始是程碑
	private String endIsMilestone;//结束是里程碑
	private String model;//关联模型
	private String takeChangeMan;//负责人--暂时没用到
	
	private String principals;//负责人       --页面传值时使用
	private String  proportion;//占比		--
	private Integer person;//人数 	--
	private Double selfMoney; //个人所得金额  --
	private Double money;//金额		--
	private String chooseStatus;//候选项目审批状态	--
	private String userCode; //		--
	private String userName; //		--
	private ProjectPool projectPool;//--
	private Integer complete;//--已完成项目数
	private Integer sumCount; //--总数
	private Integer unComplete;//未完成项目数
	
	//YfUser 表对应 ProjectManageyfUser
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProNum() {
		return proNum;
	}
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getAddUserCode() {
		return addUserCode;
	}
	public void setAddUserCode(String addUserCode) {
		this.addUserCode = addUserCode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getPoolId() {
		return poolId;
	}
	public void setPoolId(Integer poolId) {
		this.poolId = poolId;
	}
	public String getReTime() {
		return reTime;
	}
	public void setReTime(String reTime) {
		this.reTime = reTime;
	}
	public String getZpTime() {
		return zpTime;
	}
	public void setZpTime(String zpTime) {
		this.zpTime = zpTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public Integer getBelongLayer() {
		return belongLayer;
	}
	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}
	public String getGradeStatus() {
		return gradeStatus;
	}
	public void setGradeStatus(String gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
	public Integer getGradeEpId() {
		return gradeEpId;
	}
	public void setGradeEpId(Integer gradeEpId) {
		this.gradeEpId = gradeEpId;
	}
	public String getGradeRemark() {
		return gradeRemark;
	}
	public void setGradeRemark(String gradeRemark) {
		this.gradeRemark = gradeRemark;
	}
	public Integer getGradeStore() {
		return gradeStore;
	}
	public void setGradeStore(Integer gradeStore) {
		this.gradeStore = gradeStore;
	}
	public String getPrincipals() {
		return principals;
	}
	public void setPrincipals(String principals) {
		this.principals = principals;
	}
	public String getYfProjectFile() {
		return yfProjectFile;
	}
	public void setYfProjectFile(String yfProjectFile) {
		this.yfProjectFile = yfProjectFile;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getChooseStatus() {
		return chooseStatus;
	}
	public void setChooseStatus(String chooseStatus) {
		this.chooseStatus = chooseStatus;
	}
	public Integer getSelfStore() {
		return selfStore;
	}
	public void setSelfStore(Integer selfStore) {
		this.selfStore = selfStore;
	}
	public Integer getSumStore() {
		return sumStore;
	}
	public void setSumStore(Integer sumStore) {
		this.sumStore = sumStore;
	}
	public String getProportion() {
		return proportion;
	}
	public void setProportion(String proportion) {
		this.proportion = proportion;
	}
	public Integer getPerson() {
		return person;
	}
	public void setPerson(Integer person) {
		this.person = person;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getActualStore() {
		return actualStore;
	}
	public void setActualStore(Integer actualStore) {
		this.actualStore = actualStore;
	}
	public ProjectPool getProjectPool() {
		return projectPool;
	}
	public void setProjectPool(ProjectPool projectPool) {
		this.projectPool = projectPool;
	}
	public Integer getComplete() {
		return complete;
	}
	public void setComplete(Integer complete) {
		this.complete = complete;
	}
	public Integer getSumCount() {
		return sumCount;
	}
	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}
	public Integer getUnComplete() {
		return unComplete;
	}
	public void setUnComplete(Integer unComplete) {
		this.unComplete = unComplete;
	}
	public String getShijiTime() {
		return shijiTime;
	}
	public void setShijiTime(String shijiTime) {
		this.shijiTime = shijiTime;
	}
	public Integer getProjectManageId() {
		return projectManageId;
	}
	public void setProjectManageId(Integer projectManageId) {
		this.projectManageId = projectManageId;
	}
	public Integer getOutTime() {
		return outTime;
	}
	public void setOutTime(Integer outTime) {
		this.outTime = outTime;
	}
	public String getOutExplain() {
		return outExplain;
	}
	public void setOutExplain(String outExplain) {
		this.outExplain = outExplain;
	}
	public String getOutTimeResult() {
		return outTimeResult;
	}
	public void setOutTimeResult(String outTimeResult) {
		this.outTimeResult = outTimeResult;
	}
	public String getOutTimeexplainPerson() {
		return outTimeexplainPerson;
	}
	public void setOutTimeexplainPerson(String outTimeexplainPerson) {
		this.outTimeexplainPerson = outTimeexplainPerson;
	}
	public Double getSelfMoney() {
		return selfMoney;
	}
	public void setSelfMoney(Double selfMoney) {
		this.selfMoney = selfMoney;
	}
	public Integer getDepends() {
		return depends;
	}
	public void setDepends(Integer depends) {
		this.depends = depends;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public String getStartIsMilestone() {
		return startIsMilestone;
	}
	public void setStartIsMilestone(String startIsMilestone) {
		this.startIsMilestone = startIsMilestone;
	}
	public String getEndIsMilestone() {
		return endIsMilestone;
	}
	public void setEndIsMilestone(String endIsMilestone) {
		this.endIsMilestone = endIsMilestone;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getTakeChangeMan() {
		return takeChangeMan;
	}
	public void setTakeChangeMan(String takeChangeMan) {
		this.takeChangeMan = takeChangeMan;
	}
	public Integer getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}
	public String getAliasFile() {
		return aliasFile;
	}
	public void setAliasFile(String aliasFile) {
		this.aliasFile = aliasFile;
	}
	
	
}
