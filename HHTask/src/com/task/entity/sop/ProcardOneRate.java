package com.task.entity.sop;

/**
 * 同一工序个人的综合系数表（ta_sop_procardOneRate）
 * @author 贾辉辉
 *
 */
public class ProcardOneRate implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;//主键
	private String code;//员工号
	private String username;//员工姓名
	//private ProductProcess productProcess;
	private String gongxuInfor;//工序
	private Float oneRate;//个人综合系数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
/*	public ProductProcess getProductProcess() {
		return productProcess;
	}
	public void setProductProcess(ProductProcess productProcess) {
		this.productProcess = productProcess;
	}*/
	
	public Float getOneRate() {
		return oneRate;
	}
	
	public String getGongxuInfor() {
		return gongxuInfor;
	}
	public void setGongxuInfor(String gongxuInfor) {
		this.gongxuInfor = gongxuInfor;
	}
	public void setOneRate(Float oneRate) {
		this.oneRate = oneRate;
	}
	
}
