package com.task.entity.renshi;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 面试登记表 （表名ta_hr_rz_interviewLog）
 * @author txb&&licong
 *
 */
public class InterviewLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name="应聘职位")
	private String job;//应聘职位
	@FieldMeta(name="应聘部门")
	private String interviewDept;//应聘部门
	private String gereneducation;//个人学历
	@FieldMeta(name="名字")
	private String name;//名字
	@FieldMeta(name="性别")
	private String sex;//性别
	private String birthday;//生日
    private String marriage;//婚姻
    private String height;//身高
    @FieldMeta(name="户籍地址")
    private String census;//户籍地址
    private String censusStatus;//户籍性质（农，非农）
    private String workAge;//工龄
    @FieldMeta(name="毕业学校")
    private String finishSchool;//毕业学校
    @FieldMeta(name="毕业时间")
    private String finishTime;//毕业时间
    @FieldMeta(name="专业")
    private String specialty;//专业
    private String specialtyResult;//专业成果
    private String contactAddress;//通讯地址
    private String zipCode;//邮编
    private String email;//邮箱
    @FieldMeta(name="联系方式")
    private String tel;//联系方式
    private String beforeWorkAdd;//原工作单位
    private String beforWork;//原工作
    private String nowAddress;//现住址
    private String cardID;//身份证号码
    /**
     * 家庭信息
     */
    private Set<Inter_Family> families;//家庭信息表（一对多）
    
    private String experience;//主要经历
    private String strongPoint;//专业技能及特长描述
    private Float wantPay;//期望工资
    private String otherWant;//其他要求 
   
    /**
     * 评审结果
     */
    @FieldMeta(name="专业知识")
    private String specialtyScore;//专业知识
    @FieldMeta(name="相关工作经验")
    private String experienceScore;//相关工作经验
    private String job_direction;//职业取向
    private String nature_hobby;//性格爱好
    private String intelligence;//才智
    private String job_attitude;//职业态度
    private String analytical_skills;//分析能力
    private String voice_ability;//语言表达能力
    private String communication_skills;//沟通技巧
    private String education;//学历
    @FieldMeta(name="主考评语")
    private String examiner_remark;//主考评语
    private String enroll_result;//录取结果（试用/不录用）
    @FieldMeta(name="拟安排岗位")
    private String ni_post;//拟安排岗位
    private String ni_salary;//拟定试用期工资
    private String factory_opinion;//工厂意见
    private String comment;//备注
   
    /**
     * 添加时间
     * @return
     */
    private String interviewAddTime;//提交时间
	private String interviewUpDatetime;//修改时间
	
	
	 
    /**
     *建立对象的关系
     *2015-06-26
     */
	private String inter_status;//入职人员状态。（待测试/待评价/待入职(yes)/已入职/未录取(no)）
	private Set<InterviewQuizzes> interviewQuizzes;//测试单对象。一对多
   
	private String interStatus;//录取结果(未审批、审批中、同意、打回)
	private Integer epId;
	
    public Set<Inter_Family> getFamilies() {
		return families;
	}
	public void setFamilies(Set<Inter_Family> families) {
		this.families = families;
	}
	public String getInter_status() {
		return inter_status;
	}
	public void setInter_status(String interStatus) {
		inter_status = interStatus;
	}
	public Set<InterviewQuizzes> getInterviewQuizzes() {
		return interviewQuizzes;
	}
	public void setInterviewQuizzes(Set<InterviewQuizzes> interviewQuizzes) {
		this.interviewQuizzes = interviewQuizzes;
	}
	public String getGereneducation() {
		return gereneducation;
	}
	public void setGereneducation(String gereneducation) {
		this.gereneducation = gereneducation;
	}
	public String getInterviewAddTime() {
		return interviewAddTime;
	}
	public void setInterviewAddTime(String interviewAddTime) {
		this.interviewAddTime = interviewAddTime;
	}
	public String getInterviewUpDatetime() {
		return interviewUpDatetime;
	}
	public void setInterviewUpDatetime(String interviewUpDatetime) {
		this.interviewUpDatetime = interviewUpDatetime;
	}
	public String getInterviewDept() {
		return interviewDept;
	}
	public void setInterviewDept(String interviewDept) {
		this.interviewDept = interviewDept;
	}
	public String getSpecialtyScore() {
		return specialtyScore;
	}
	public void setSpecialtyScore(String specialtyScore) {
		this.specialtyScore = specialtyScore;
	}
	public String getExperienceScore() {
		return experienceScore;
	}
	public void setExperienceScore(String experienceScore) {
		this.experienceScore = experienceScore;
	}
	public String getJob_direction() {
		return job_direction;
	}
	public void setJob_direction(String jobDirection) {
		job_direction = jobDirection;
	}
	public String getNature_hobby() {
		return nature_hobby;
	}
	public void setNature_hobby(String natureHobby) {
		nature_hobby = natureHobby;
	}
	public String getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(String intelligence) {
		this.intelligence = intelligence;
	}
	public String getJob_attitude() {
		return job_attitude;
	}
	public void setJob_attitude(String jobAttitude) {
		job_attitude = jobAttitude;
	}
	
	public String getAnalytical_skills() {
		return analytical_skills;
	}
	public void setAnalytical_skills(String analyticalSkills) {
		analytical_skills = analyticalSkills;
	}
	public String getVoice_ability() {
		return voice_ability;
	}
	public void setVoice_ability(String voiceAbility) {
		voice_ability = voiceAbility;
	}
	public String getCommunication_skills() {
		return communication_skills;
	}
	public void setCommunication_skills(String communicationSkills) {
		communication_skills = communicationSkills;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExaminer_remark() {
		return examiner_remark;
	}
	public void setExaminer_remark(String examinerRemark) {
		examiner_remark = examinerRemark;
	}
	public String getEnroll_result() {
		return enroll_result;
	}
	public void setEnroll_result(String enrollResult) {
		enroll_result = enrollResult;
	}
	public String getNi_post() {
		return ni_post;
	}
	public void setNi_post(String niPost) {
		ni_post = niPost;
	}
	public String getNi_salary() {
		return ni_salary;
	}
	public void setNi_salary(String niSalary) {
		ni_salary = niSalary;
	}
	public String getFactory_opinion() {
		return factory_opinion;
	}
	public void setFactory_opinion(String factoryOpinion) {
		factory_opinion = factoryOpinion;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
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
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getCensus() {
		return census;
	}
	public void setCensus(String census) {
		this.census = census;
	}
	public String getCensusStatus() {
		return censusStatus;
	}
	public void setCensusStatus(String censusStatus) {
		this.censusStatus = censusStatus;
	}
	public String getWorkAge() {
		return workAge;
	}
	public void setWorkAge(String workAge) {
		this.workAge = workAge;
	}
	public String getFinishSchool() {
		return finishSchool;
	}
	public void setFinishSchool(String finishSchool) {
		this.finishSchool = finishSchool;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getSpecialtyResult() {
		return specialtyResult;
	}
	public void setSpecialtyResult(String specialtyResult) {
		this.specialtyResult = specialtyResult;
	}
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getBeforeWorkAdd() {
		return beforeWorkAdd;
	}
	public void setBeforeWorkAdd(String beforeWorkAdd) {
		this.beforeWorkAdd = beforeWorkAdd;
	}
	public String getBeforWork() {
		return beforWork;
	}
	public void setBeforWork(String beforWork) {
		this.beforWork = beforWork;
	}
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getStrongPoint() {
		return strongPoint;
	}
	public void setStrongPoint(String strongPoint) {
		this.strongPoint = strongPoint;
	}
	public Float getWantPay() {
		return wantPay;
	}
	public void setWantPay(Float wantPay) {
		this.wantPay = wantPay;
	}
	public String getOtherWant() {
		return otherWant;
	}
	public void setOtherWant(String otherWant) {
		this.otherWant = otherWant;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getInterStatus() {
		return interStatus;
	}
	public void setInterStatus(String interStatus) {
		this.interStatus = interStatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}
