package com.task.entity.peb;

import java.io.Serializable;
import java.util.List;

import com.task.util.FieldMeta;

/**
 * 生产效率简报（人事档案表）
 * ta_pebUsers
 * @author xs-cy
 *
 */
public class PebUsers implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@FieldMeta(name="班组")
	private String banZu;
	@FieldMeta(name="年份")
	private Integer year;
	@FieldMeta(name="月份")
	private Integer month;
	@FieldMeta(name="日")
	private Integer day;
	private Integer dangAnNum;//人事档案人数
	private Integer borrowNum;//借入人数
	private Integer lendNum;//借出人数
	private Float noChuQinNum;//未出勤人数
	private Float actualNum;//实际出勤人数
	
	private Float zsNumber;//折算台数（数量）
	private Float avgNumber;//人均台数
	
	private Float gzHour;//上班小时数
	private Float UPPH;//
	private String remarks;
	

	private Float mbTarget;//目标日人均
	private String category;//类别（其他、钣金工序）
	@FieldMeta(name="未出勤申请数量")
	private Float applyNum;//未出勤申请数量
	private Integer epId;
	private String epStatus;
	@FieldMeta(name="备注")
	private String applyRemark;//申请备注
	
	private List<PebBorrowAndLendLog> borrowLogList;
	private List<PebBorrowAndLendLog> lendLogList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBanZu() {
		return banZu;
	}
	public void setBanZu(String banZu) {
		this.banZu = banZu;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getDangAnNum() {
		return dangAnNum;
	}
	public void setDangAnNum(Integer dangAnNum) {
		this.dangAnNum = dangAnNum;
	}
	public Integer getBorrowNum() {
		return borrowNum;
	}
	public void setBorrowNum(Integer borrowNum) {
		this.borrowNum = borrowNum;
	}
	public Integer getLendNum() {
		return lendNum;
	}
	public void setLendNum(Integer lendNum) {
		this.lendNum = lendNum;
	}
	
	public Float getNoChuQinNum() {
		return noChuQinNum;
	}
	public void setNoChuQinNum(Float noChuQinNum) {
		this.noChuQinNum = noChuQinNum;
	}
	public Float getActualNum() {
		return actualNum;
	}
	public void setActualNum(Float actualNum) {
		this.actualNum = actualNum;
	}
	public Float getZsNumber() {
		return zsNumber;
	}
	public void setZsNumber(Float zsNumber) {
		this.zsNumber = zsNumber;
	}
	public Float getAvgNumber() {
		return avgNumber;
	}
	public void setAvgNumber(Float avgNumber) {
		this.avgNumber = avgNumber;
	}
	public Float getGzHour() {
		return gzHour;
	}
	public void setGzHour(Float gzHour) {
		this.gzHour = gzHour;
	}
	public Float getUPPH() {
		return UPPH;
	}
	public void setUPPH(Float uPPH) {
		UPPH = uPPH;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Float getMbTarget() {
		return mbTarget;
	}
	public void setMbTarget(Float mbTarget) {
		this.mbTarget = mbTarget;
	}
	public List<PebBorrowAndLendLog> getBorrowLogList() {
		return borrowLogList;
	}
	public void setBorrowLogList(List<PebBorrowAndLendLog> borrowLogList) {
		this.borrowLogList = borrowLogList;
	}
	public List<PebBorrowAndLendLog> getLendLogList() {
		return lendLogList;
	}
	public void setLendLogList(List<PebBorrowAndLendLog> lendLogList) {
		this.lendLogList = lendLogList;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEpStatus() {
		return epStatus;
	}
	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}
	
	public Float getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(Float applyNum) {
		this.applyNum = applyNum;
	}
	public String getApplyRemark() {
		return applyRemark;
	}
	public void setApplyRemark(String applyRemark) {
		this.applyRemark = applyRemark;
	}

	
	
}
