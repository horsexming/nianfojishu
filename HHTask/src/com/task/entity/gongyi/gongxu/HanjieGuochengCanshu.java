package com.task.entity.gongyi.gongxu;

import java.io.Serializable;

public class HanjieGuochengCanshu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	/**材料牌号及厚度*/
	private String cailiaoPaihaoHoudu;
	/**焊料名称直径*/
	private String hanliaoMingchengZhijing;
	/**气体流量*/
	private String qitiLiuliang;
	/**电流强度*/
	private String dianliuQiangdu;
	/**保护气体*/
	private String baohuQiti;
	/**钨丝直径*/
	private String wusiZhijing;
	/**送丝速度*/
	private String songsiSudu;
	/**电弧电压*/
	private String dianhuDianya;
	/**附注*/
	private String remark;
	/**获取前段参数*/
	private String params;
	private Integer processDataId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getHanliaoMingchengZhijing() {
		return hanliaoMingchengZhijing;
	}
	public void setHanliaoMingchengZhijing(String hanliaoMingchengZhijing) {
		this.hanliaoMingchengZhijing = hanliaoMingchengZhijing;
	}
	public String getQitiLiuliang() {
		return qitiLiuliang;
	}
	public void setQitiLiuliang(String qitiLiuliang) {
		this.qitiLiuliang = qitiLiuliang;
	}
	public String getDianliuQiangdu() {
		return dianliuQiangdu;
	}
	public void setDianliuQiangdu(String dianliuQiangdu) {
		this.dianliuQiangdu = dianliuQiangdu;
	}
	public String getBaohuQiti() {
		return baohuQiti;
	}
	public void setBaohuQiti(String baohuQiti) {
		this.baohuQiti = baohuQiti;
	}
	public String getWusiZhijing() {
		return wusiZhijing;
	}
	public void setWusiZhijing(String wusiZhijing) {
		this.wusiZhijing = wusiZhijing;
	}
	public String getSongsiSudu() {
		return songsiSudu;
	}
	public void setSongsiSudu(String songsiSudu) {
		this.songsiSudu = songsiSudu;
	}
	public String getDianhuDianya() {
		return dianhuDianya;
	}
	public void setDianhuDianya(String dianhuDianya) {
		this.dianhuDianya = dianhuDianya;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCailiaoPaihaoHoudu() {
		return cailiaoPaihaoHoudu;
	}
	public void setCailiaoPaihaoHoudu(String cailiaoPaihaoHoudu) {
		this.cailiaoPaihaoHoudu = cailiaoPaihaoHoudu;
	}
	public Integer getProcessDataId() {
		return processDataId;
	}
	public void setProcessDataId(Integer processDataId) {
		this.processDataId = processDataId;
	}
	public String getParams() {
		if(params!=null){
			return params.replace("\\t", "").replace("\\r","").replace("\\n","").replace("\\f","").replace("\\","");
		}
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
}
