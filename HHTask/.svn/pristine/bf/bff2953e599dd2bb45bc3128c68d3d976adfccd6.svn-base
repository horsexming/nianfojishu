package com.task.entity.bybz;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 量、检具校验记录表；（ta_bybz_LjuCheckRecord） 和量、检具校验记录明细表(LjuCheckRecordMx) 一对多关系
 * 
 * @author 王晓飞
 *
 */
public class LjuCheckRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 量、检具名称;
	private Integer cagId;// 量、检具Id
	private String jyUsers;// 校验人
	private Integer jyUsresId;// 校验人Id
	private String jyUsresCode;// 校验人工号
	private String jyTime;// 校验时间
	private String fileName;// 校验报告文件名
	private Set<LjuCheckRecordMx> ljuCRMSet;// 量、检具校验记录明细表 一对多
	private List<LjuCheckRecordMx> ljuCRMList;// 页面传值

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCagId() {
		return cagId;
	}

	public void setCagId(Integer cagId) {
		this.cagId = cagId;
	}

	public String getJyUsers() {
		return jyUsers;
	}

	public void setJyUsers(String jyUsers) {
		this.jyUsers = jyUsers;
	}

	public Integer getJyUsresId() {
		return jyUsresId;
	}

	public void setJyUsresId(Integer jyUsresId) {
		this.jyUsresId = jyUsresId;
	}

	public String getJyUsresCode() {
		return jyUsresCode;
	}

	public void setJyUsresCode(String jyUsresCode) {
		this.jyUsresCode = jyUsresCode;
	}

	public String getJyTime() {
		return jyTime;
	}

	public void setJyTime(String jyTime) {
		this.jyTime = jyTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set<LjuCheckRecordMx> getLjuCRMSet() {
		return ljuCRMSet;
	}

	public void setLjuCRMSet(Set<LjuCheckRecordMx> ljuCRMSet) {
		this.ljuCRMSet = ljuCRMSet;
	}

	public List<LjuCheckRecordMx> getLjuCRMList() {
		return ljuCRMList;
	}

	public void setLjuCRMList(List<LjuCheckRecordMx> ljuCRMList) {
		this.ljuCRMList = ljuCRMList;
	}

}
