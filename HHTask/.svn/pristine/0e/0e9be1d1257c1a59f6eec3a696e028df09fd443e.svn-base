package com.task.entity;

import java.util.Date;

import com.task.util.StrutsTreeITreeNode;
import com.task.util.StrutsTreeNode;

/**
 * 员工培训
 * 
 * @author 刘培
 * 
 */
public class Terrain implements StrutsTreeITreeNode , java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long terrainId;
	private long fatherTerrainId;
	private String terrainName;
	private String fullName;
	private String terrainDesc;
	private long sign;
	private Date beginDate;
	private Date invalidDate;
	private long creator;
	private Date createTime;
	private long lastModifier;
	private Date lastModifyTime;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public long getFatherTerrainId() {
		return fatherTerrainId;
	}

	public void setFatherTerrainId(long fatherTerrainId) {
		this.fatherTerrainId = fatherTerrainId;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public long getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(long lastModifier) {
		this.lastModifier = lastModifier;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getTerrainDesc() {
		return terrainDesc;
	}

	public void setTerrainDesc(String terrainDesc) {
		this.terrainDesc = terrainDesc;
	}

	public long getTerrainId() {
		return terrainId;
	}

	public void setTerrainId(long terrainId) {
		this.terrainId = terrainId;
	}

	public String getTerrainName() {
		return terrainName;
	}

	public void setTerrainName(String terrainName) {
		this.terrainName = terrainName;
	}

	public StrutsTreeNode toTreeNode() {
		StrutsTreeNode treenode = new StrutsTreeNode();

		treenode.setId((int) getTerrainId());
		treenode.setParentId((int) getFatherTerrainId());
		treenode.setName(getTerrainName());
		return treenode;
	}

	public long getSign() {
		return sign;
	}

	public void setSign(long sign) {
		this.sign = sign;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
