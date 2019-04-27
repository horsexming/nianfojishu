package com.task.entity.sop.spc;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 
 * @author 王晓飞 spc控制测量明细表:(ta_sop_spc_SpcControlGroup)
 *	和spc控制表  SpcControl 多对一;
 */
public class SpcControlGroup  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String	cldate;//测量日期
	private Integer groupsNO;//组数
	private Float xbar;//群组均值
	private Float range;//群组极差
	private Float xdbar;//总均值
	private Float rbar;//极差均值
	private Float uclxbar;//上限均值
	private Float lclxbar;//下限均值
	private Float uclr;//极差最大值
	private Float lclr;//极差最小值
	private Float stdev;//标准偏差
	private Float cp;//
	private Float ca;//
	private Float cpk;//
	
	private SpcControl spcControl;//SPC控制表表头
	private Set<SpcControlClDetail> setSpcControlClDetail;
	private List<SpcControlClDetail> detailList;
	
	private Float[] clvalues;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCldate() {
		return cldate;
	}

	public void setCldate(String cldate) {
		this.cldate = cldate;
	}

	public Integer getGroupsNO() {
		return groupsNO;
	}

	public void setGroupsNO(Integer groupsNO) {
		this.groupsNO = groupsNO;
	}

	

	public Float getXbar() {
		return xbar;
	}

	public void setXbar(Float xbar) {
		this.xbar = xbar;
	}

	public Float getRange() {
		return range;
	}

	public void setRange(Float range) {
		this.range = range;
	}

	public Float getXdbar() {
		return xdbar;
	}

	public void setXdbar(Float xdbar) {
		this.xdbar = xdbar;
	}

	public Float getRbar() {
		return rbar;
	}

	public void setRbar(Float rbar) {
		this.rbar = rbar;
	}

	public Float getUclxbar() {
		return uclxbar;
	}

	public void setUclxbar(Float uclxbar) {
		this.uclxbar = uclxbar;
	}

	public Float getLclxbar() {
		return lclxbar;
	}

	public void setLclxbar(Float lclxbar) {
		this.lclxbar = lclxbar;
	}

	public Float getUclr() {
		return uclr;
	}

	public void setUclr(Float uclr) {
		this.uclr = uclr;
	}

	
	public Float getLclr() {
		return lclr;
	}

	public void setLclr(Float lclr) {
		this.lclr = lclr;
	}
	@JSONField(serialize = false)
	public SpcControl getSpcControl() {
		return spcControl;
	}

	public void setSpcControl(SpcControl spcControl) {
		this.spcControl = spcControl;
	}
	@JSONField(serialize = false)
	public Set<SpcControlClDetail> getSetSpcControlClDetail() {
		return setSpcControlClDetail;
	}

	public void setSetSpcControlClDetail(
			Set<SpcControlClDetail> setSpcControlClDetail) {
		this.setSpcControlClDetail = setSpcControlClDetail;
	}
	public List<SpcControlClDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SpcControlClDetail> detailList) {
		this.detailList = detailList;
	}

	public Float[] getClvalues() {
		return clvalues;
	}

	public void setClvalues(Float[] clvalues) {
		this.clvalues = clvalues;
	}

	public Float getStdev() {
		return stdev;
	}

	public void setStdev(Float stdev) {
		this.stdev = stdev;
	}

	public Float getCp() {
		return cp;
	}

	public void setCp(Float cp) {
		this.cp = cp;
	}

	public Float getCa() {
		return ca;
	}

	public void setCa(Float ca) {
		this.ca = ca;
	}

	public Float getCpk() {
		return cpk;
	}

	public void setCpk(Float cpk) {
		this.cpk = cpk;
	}

	
	
	
	
	
	
	
}
