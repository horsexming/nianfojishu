package com.task.entity.sop;
/**
 * 零件替明细(外购件采购替换物料)ta_sop_w_ProcardReplaceDetail
 * @author txb
 *
 */
public class ProcardReplaceDetail implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	//原资料
	private String rootMarkId;
	private String rootSelfCard;//
	private Integer procardId;//
	private String ywMarkId;//
	private String markId;//
	private String selfCard;//
	private Float filnalCount;//
	private Float cgNumber;//
	private String proName;//
	private String kgliao;//
	private String tuhao;
	//替换资料
	private Integer newProcardId;//
	private String newMarkId;
	private String newSelfCard;//
	private Float newFilnaCount;//
	private Float newCgnumber;
	private String newProName;//
	private String newKgliao;//
	private String newtuhao;
	private String addTime;//
	
	private ProcardReplace procardReplace;
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
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public Float getFilnalCount() {
		return filnalCount;
	}
	public void setFilnalCount(Float filnalCount) {
		this.filnalCount = filnalCount;
	}
	public Float getCgNumber() {
		return cgNumber;
	}
	public void setCgNumber(Float cgNumber) {
		this.cgNumber = cgNumber;
	}
	public String getNewMarkId() {
		return newMarkId;
	}
	public void setNewMarkId(String newMarkId) {
		this.newMarkId = newMarkId;
	}
	public String getNewSelfCard() {
		return newSelfCard;
	}
	public void setNewSelfCard(String newSelfCard) {
		this.newSelfCard = newSelfCard;
	}
	public Float getNewFilnaCount() {
		return newFilnaCount;
	}
	public void setNewFilnaCount(Float newFilnaCount) {
		this.newFilnaCount = newFilnaCount;
	}
	public Float getNewCgnumber() {
		return newCgnumber;
	}
	public void setNewCgnumber(Float newCgnumber) {
		this.newCgnumber = newCgnumber;
	}//
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getNewProName() {
		return newProName;
	}
	public void setNewProName(String newProName) {
		this.newProName = newProName;
	}
	public String getNewKgliao() {
		return newKgliao;
	}
	public void setNewKgliao(String newKgliao) {
		this.newKgliao = newKgliao;
	}
	
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public Integer getNewProcardId() {
		return newProcardId;
	}
	public void setNewProcardId(Integer newProcardId) {
		this.newProcardId = newProcardId;
	}
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public ProcardReplace getProcardReplace() {
		return procardReplace;
	}
	public void setProcardReplace(ProcardReplace procardReplace) {
		this.procardReplace = procardReplace;
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