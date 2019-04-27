package com.task.entity.banci;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 班次管理 
 * 表名： ta_banci
 * @author Li_Cong
 *
 */
public class BanCi implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//名称
	private String banCiType;//班次类型(固定班次/门卫班次)
	private String firsttime;//开始时间
	private String finishtime;//结束时间
	
	private Integer firstBeforeMin;//开始前几分钟有效
	private String firstBeforeTime;//开始前打卡时间
	private Integer firstLaterMin;//开始后几分钟有效
	private String firstLaterTime;//开始后打卡时间
	
	private Integer finishBeforeMin;//结束前几分钟有效
	private String finishBeforeTime;//结束前打卡时间
	private Integer finishLaterMin;//结束后几分钟有效
	private String finishLaterTime;//结束后打卡时间
	
	private String wxstarttime;//午休开始时间;
	private String wxendtime;//午休结束时间;
	
	private Integer bcNumber;//班次时段
	private String addtime;//添加时间
	private String updatetime;//修改时间
	private String sbdate;//上班星期;
	private String isSynchronize;//是否自动同步节假日(是/否)
	private String isOvernight;//是否隔夜(是/否)
	private String xiaFuis;//下班是否覆盖(是/否)
//	private String isOverTime;//是否有加班(是/否)
//	private String overTimeStart;//加班开始时间;
//	private String overTimeEnd;//加班结束时间;
	
	private Integer gzTime;//工作时长(分钟)
	private Integer xxTime;//中途休息时长(分钟)
	
	private Set<BanCiTime> banCiTime;//班次工作时间段
	private String banCiTimeShow;//班次工作时段显示
	private Set<SchedulingTable> schedulingTable;//排班信息表
	
	
	public String getXiaFuis() {
		return xiaFuis;
	}
	public void setXiaFuis(String xiaFuis) {
		this.xiaFuis = xiaFuis;
	}
	/**
	 * @return the xxTime
	 */
	public Integer getXxTime() {
		return xxTime;
	}
	/**
	 * @param xxTime the xxTime to set
	 */
	public void setXxTime(Integer xxTime) {
		this.xxTime = xxTime;
	}
	public String getBanCiType() {
		return banCiType;
	}
	public void setBanCiType(String banCiType) {
		this.banCiType = banCiType;
	}
	@JSONField(serialize = false)
	public Set<SchedulingTable> getSchedulingTable() {
		return schedulingTable;
	}
	public void setSchedulingTable(Set<SchedulingTable> schedulingTable) {
		this.schedulingTable = schedulingTable;
	}
	@JSONField(serialize = false)
	public Set<BanCiTime> getBanCiTime() {
		return banCiTime;
	}
	public void setBanCiTime(Set<BanCiTime> banCiTime) {
		this.banCiTime = banCiTime;
	}
	public String getBanCiTimeShow() {
		return banCiTimeShow;
	}
	public void setBanCiTimeShow(String banCiTimeShow) {
		this.banCiTimeShow = banCiTimeShow;
	}
	public Integer getBcNumber() {
		return bcNumber;
	}
	public void setBcNumber(Integer bcNumber) {
		this.bcNumber = bcNumber;
	}
	public Integer getGzTime() {
		return gzTime;
	}
	public void setGzTime(Integer gzTime) {
		this.gzTime = gzTime;
	}
	public String getIsOvernight() {
		return isOvernight;
	}
	public void setIsOvernight(String isOvernight) {
		this.isOvernight = isOvernight;
	}
//	public String getIsOverTime() {
//		return isOverTime;
//	}
//	public void setIsOverTime(String isOverTime) {
//		this.isOverTime = isOverTime;
//	}
//	public String getOverTimeStart() {
//		return overTimeStart;
//	}
//	public void setOverTimeStart(String overTimeStart) {
//		this.overTimeStart = overTimeStart;
//	}
//	public String getOverTimeEnd() {
//		return overTimeEnd;
//	}
//	public void setOverTimeEnd(String overTimeEnd) {
//		this.overTimeEnd = overTimeEnd;
//	}
	public String getIsSynchronize() {
		return isSynchronize;
	}
	public void setIsSynchronize(String isSynchronize) {
		this.isSynchronize = isSynchronize;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirsttime() {
		return firsttime;
	}
	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}
	public String getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	public String getName() {
		return name;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getWxstarttime() {
		return wxstarttime;
	}
	public void setWxstarttime(String wxstarttime) {
		this.wxstarttime = wxstarttime;
	}
	public String getWxendtime() {
		return wxendtime;
	}
	public void setWxendtime(String wxendtime) {
		this.wxendtime = wxendtime;
	}
	public String getSbdate() {
		return sbdate;
	}
	public void setSbdate(String sbdate) {
		this.sbdate = sbdate;
	}
	public Integer getFirstBeforeMin() {
		return firstBeforeMin;
	}
	public void setFirstBeforeMin(Integer firstBeforeMin) {
		this.firstBeforeMin = firstBeforeMin;
	}
	public String getFirstBeforeTime() {
		return firstBeforeTime;
	}
	public void setFirstBeforeTime(String firstBeforeTime) {
		this.firstBeforeTime = firstBeforeTime;
	}
	public Integer getFirstLaterMin() {
		return firstLaterMin;
	}
	public void setFirstLaterMin(Integer firstLaterMin) {
		this.firstLaterMin = firstLaterMin;
	}
	public String getFirstLaterTime() {
		return firstLaterTime;
	}
	public void setFirstLaterTime(String firstLaterTime) {
		this.firstLaterTime = firstLaterTime;
	}
	public Integer getFinishBeforeMin() {
		return finishBeforeMin;
	}
	public void setFinishBeforeMin(Integer finishBeforeMin) {
		this.finishBeforeMin = finishBeforeMin;
	}
	public String getFinishBeforeTime() {
		return finishBeforeTime;
	}
	public void setFinishBeforeTime(String finishBeforeTime) {
		this.finishBeforeTime = finishBeforeTime;
	}
	public Integer getFinishLaterMin() {
		return finishLaterMin;
	}
	public void setFinishLaterMin(Integer finishLaterMin) {
		this.finishLaterMin = finishLaterMin;
	}
	public String getFinishLaterTime() {
		return finishLaterTime;
	}
	public void setFinishLaterTime(String finishLaterTime) {
		this.finishLaterTime = finishLaterTime;
	}
	
	
	
	
}
