package com.task.entity.sop;
/**
 * 
 * @author 辅料追溯 :(ta_sop_FuLiaoZhuiSu)
 *
 */
public class FuLiaoZhuiSu implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String orderNum;//订单编号(内部)
	private String ywmarkId;//业务件号
	private String rootmarkId;//总成件号
	private String rootselfCard;//总成件号
	private String markId;//件号
	private String proName;//名称
	private String num;//数量
	private String scdate;//生产日期
	private String gongwei;//工位
	private String shebeiNo;//设备表
	private String groupA;//组分A
	private String groupB;//组分B
	private String person;//负责人
	private String addtime;//添加时间
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	public String getRootmarkId() {
		return rootmarkId;
	}
	public void setRootmarkId(String rootmarkId) {
		this.rootmarkId = rootmarkId;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getScdate() {
		return scdate;
	}
	public void setScdate(String scdate) {
		this.scdate = scdate;
	}
	public String getGongwei() {
		return gongwei;
	}
	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}
	public String getShebeiNo() {
		return shebeiNo;
	}
	public void setShebeiNo(String shebeiNo) {
		this.shebeiNo = shebeiNo;
	}
	public String getGroupA() {
		return groupA;
	}
	public void setGroupA(String groupA) {
		this.groupA = groupA;
	}
	public String getGroupB() {
		return groupB;
	}
	public void setGroupB(String groupB) {
		this.groupB = groupB;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getRootselfCard() {
		return rootselfCard;
	}
	public void setRootselfCard(String rootselfCard) {
		this.rootselfCard = rootselfCard;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	
}
