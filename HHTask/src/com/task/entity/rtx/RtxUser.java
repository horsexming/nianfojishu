package com.task.entity.rtx;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

// default package



/**
 * rxt用户实体类
 * 表名:SYS_User
 * @author txb
 * RtxUser entity. @author MyEclipse Persistence Tools
 */

public class RtxUser  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 1L;
     private Integer id;
     private String userName;
     private String pwd;
     private Short pwdType;
     private Integer smsId;
     private String name;
     private Short userType;
     private Short gender;
     private Short face;
     private String mobile;
     private String email;
     private String phone;
     private Short profileOpenLevel;
     private Short buddyAuth;
     private Integer lastLogonTime;
     private String userRight;
     private Short accountState;
     private Integer userVersion;
     private Integer authType;
     private Set<RtxDept> rtxDept;

    // Constructors

    /** default constructor */
    public RtxUser() {
    }

	/** minimal constructor */
    public RtxUser(String userName) {
        this.userName = userName;
    }
    
    /** full constructor */
    public RtxUser(String userName, String pwd, Short pwdType, Integer smsId, String name, Short userType, Short gender, Short face, String mobile, String email, String phone, Short profileOpenLevel, Short buddyAuth, Integer lastLogonTime, String userRight, Short accountState, Integer userVersion, Integer authType) {
        this.userName = userName;
        this.pwd = pwd;
        this.pwdType = pwdType;
        this.smsId = smsId;
        this.name = name;
        this.userType = userType;
        this.gender = gender;
        this.face = face;
        this.mobile = mobile;
        this.email = email;
        this.phone = phone;
        this.profileOpenLevel = profileOpenLevel;
        this.buddyAuth = buddyAuth;
        this.lastLogonTime = lastLogonTime;
        this.userRight = userRight;
        this.accountState = accountState;
        this.userVersion = userVersion;
        this.authType = authType;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return this.pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Short getPwdType() {
        return this.pwdType;
    }
    
    public void setPwdType(Short pwdType) {
        this.pwdType = pwdType;
    }

    public Integer getSmsId() {
        return this.smsId;
    }
    
    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Short getUserType() {
        return this.userType;
    }
    
    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public Short getGender() {
        return this.gender;
    }
    
    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Short getFace() {
        return this.face;
    }
    
    public void setFace(Short face) {
        this.face = face;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Short getProfileOpenLevel() {
        return this.profileOpenLevel;
    }
    
    public void setProfileOpenLevel(Short profileOpenLevel) {
        this.profileOpenLevel = profileOpenLevel;
    }

    public Short getBuddyAuth() {
        return this.buddyAuth;
    }
    
    public void setBuddyAuth(Short buddyAuth) {
        this.buddyAuth = buddyAuth;
    }

    public Integer getLastLogonTime() {
        return this.lastLogonTime;
    }
    
    public void setLastLogonTime(Integer lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    public String getUserRight() {
        return this.userRight;
    }
    
    public void setUserRight(String userRight) {
        this.userRight = userRight;
    }

    public Short getAccountState() {
        return this.accountState;
    }
    
    public void setAccountState(Short accountState) {
        this.accountState = accountState;
    }

    public Integer getUserVersion() {
        return this.userVersion;
    }
    
    public void setUserVersion(Integer userVersion) {
        this.userVersion = userVersion;
    }

    public Integer getAuthType() {
        return this.authType;
    }
    
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }
    @JSONField(serialize = false)
	public Set<RtxDept> getRtxDept() {
		return rtxDept;
	}

	public void setRtxDept(Set<RtxDept> rtxDept) {
		this.rtxDept = rtxDept;
	}
   








}