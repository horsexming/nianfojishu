package com.task.entity.sop;

import java.util.Set;
/**
 *  物料替换 ta_sop_w_ProcardReplace
 * @author txb
 *
 */
public class ProcardReplace  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String rootMarkId;
	private String rootSelfCard;//
	private String ywMarkId;//
	private Integer rootId;
	private Float rootFilnalCount;
	private String markId;
	private String kgliao;//
	private String wgType;
	private String banbenNumber;//
	private Integer banci;
	private Float allcount;//
	private Float cgNumber;//
	private String unit;//
	private String tuhao;//

	private String newmarkId;
	private String newkgliao;//
	private String newwgType;
	private String newbanbenNumber;//
	private Integer newbanci;
	private Float newallcount;//
	private Float newcgNumber;//
	private String newunit;//
	private String newtuhao;

	private Integer addUserId;//
	private String addUserName;//
	private String addTime;//
	private Integer epId;//
	private String applyStatus;//审批状态

	private Set<ProcardReplaceDetail> prdSet;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRootMarkId() {
		return rootMarkId;
	}
	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}
	public String getRootSelfCard() {
		return rootSelfCard;
	}
	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}
	public String getYwMarkId() {
		return ywMarkId;
	}
	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}
	public Integer getRootId() {
		return rootId;
	}
	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}
	public Float getRootFilnalCount() {
		return rootFilnalCount;
	}
	public void setRootFilnalCount(Float rootFilnalCount) {
		this.rootFilnalCount = rootFilnalCount;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getWgType() {
		return wgType;
	}
	public void setWgType(String wgType) {
		this.wgType = wgType;
	}
	public String getBanbenNumber() {
		return banbenNumber;
	}
	public void setBanbenNumber(String banbenNumber) {
		this.banbenNumber = banbenNumber;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public Float getAllcount() {
		return allcount;
	}
	public void setAllcount(Float allcount) {
		this.allcount = allcount;
	}
	public Float getCgNumber() {
		return cgNumber;
	}
	public void setCgNumber(Float cgNumber) {
		this.cgNumber = cgNumber;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNewmarkId() {
		return newmarkId;
	}
	public void setNewmarkId(String newmarkId) {
		this.newmarkId = newmarkId;
	}
	public String getNewkgliao() {
		return newkgliao;
	}
	public void setNewkgliao(String newkgliao) {
		this.newkgliao = newkgliao;
	}
	public String getNewwgType() {
		return newwgType;
	}
	public void setNewwgType(String newwgType) {
		this.newwgType = newwgType;
	}
	public String getNewbanbenNumber() {
		return newbanbenNumber;
	}
	public void setNewbanbenNumber(String newbanbenNumber) {
		this.newbanbenNumber = newbanbenNumber;
	}
	public Integer getNewbanci() {
		return newbanci;
	}
	public void setNewbanci(Integer newbanci) {
		this.newbanci = newbanci;
	}
	public Float getNewallcount() {
		return newallcount;
	}
	public void setNewallcount(Float newallcount) {
		this.newallcount = newallcount;
	}
	public Float getNewcgNumber() {
		return newcgNumber;
	}
	public void setNewcgNumber(Float newcgNumber) {
		this.newcgNumber = newcgNumber;
	}
	public String getNewunit() {
		return newunit;
	}
	public void setNewunit(String newunit) {
		this.newunit = newunit;
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public Set<ProcardReplaceDetail> getPrdSet() {
		return prdSet;
	}
	public void setPrdSet(Set<ProcardReplaceDetail> prdSet) {
		this.prdSet = prdSet;
	}
	public String getTuhao() {
		return tuhao;
	}
	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}
	public String getNewtuhao() {
		return newtuhao;
	}
	public void setNewtuhao(String newtuhao) {
		this.newtuhao = newtuhao;
	}


}
