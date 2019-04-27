package com.task.entity.sop;

/**
 * 设变影响外委明细
 * @author txb
 *
 */
public class ProcardSbWwDetail  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//
	private Integer procardId;//
	private String markId;//
	private String selfCard;//批次
	private String procardStyle;//生产类型
	private String banBenNumber;// 版本号
	private Integer banci;// 版次
	private String proName;//
	private String tuhao;//
	private String kgliao;//
	private String sbtype;//设变类型（新加,删除,用量调整,工序调整）
	private ProcardSbWw procardSbWw;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getTuhao() {
		return tuhao;
	}
	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}
	
	public String getKgliao() {
		return kgliao;
	}
	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}
	public String getSbtype() {
		return sbtype;
	}
	public void setSbtype(String sbtype) {
		this.sbtype = sbtype;
	}
	public String getBanBenNumber() {
		return banBenNumber;
	}
	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
	}
	public ProcardSbWw getProcardSbWw() {
		return procardSbWw;
	}
	public void setProcardSbWw(ProcardSbWw procardSbWw) {
		this.procardSbWw = procardSbWw;
	}
	
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public void copyValue(ProcardSbWwDetail source){
		this.procardId=source.getProcardId();//
		this.procardStyle=source.getProcardStyle();
		this.markId=source.getMarkId();//
		this.selfCard=source.getSelfCard();
		this.banBenNumber=source.getBanBenNumber();// 版本号
		this.banci=source.getBanci();// 版次
		this.proName=source.getProName();//
		this.tuhao=source.getTuhao();//
		this.kgliao=source.getKgliao();//
		this.sbtype=source.getSbtype();//设变类型（新加,删除,用量调整）
	}
	public String getProcardStyle() {
		return procardStyle;
	}
	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}
	
}
