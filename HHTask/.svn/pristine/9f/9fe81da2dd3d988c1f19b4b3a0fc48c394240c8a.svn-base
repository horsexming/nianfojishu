package com.task.entity;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.bybz.BaoYangBiaoZhun;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.pmi.PmiManagement;
import com.task.util.FieldMeta;

/****
 * 设备表(表名:machine)
 * 
 * 
 */
public class Machine  implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	@FieldMeta(name="id",id=true)
	private Integer id;
	// private Integer fk_progzid;//与工序关联的标识
	@FieldMeta(name="报修条码")
	private String barcode;// 报修条码
	@FieldMeta(name="工区")
	private String workArea; // 工区
	@FieldMeta(name="工位")
	private String workPosition;// 工位
	@FieldMeta(name="设备编号")
	private String no;// 设备编号
	@FieldMeta(name="类型")
	private String type;
	@FieldMeta(name="名称")
	private String name;
	private String classGroup;
	@FieldMeta(name="状态")
	private String status;// 故障、故障指派、维修中、 修复待验证、 正常
	@FieldMeta(name="制造厂家")
	private String Manufacturer;// 制造厂家
	private String statusMore;
	@FieldMeta(name="备注")
	private String more;// 备注
	@FieldMeta(name="添加时间")
	private String addTime;// 添加时间
	private String isManual;//是，否
	@FieldMeta(name="设备型号")
	private String unitType;//设备型号
	@FieldMeta(name="设备品牌")
	private String brand;//设备品牌
	@FieldMeta(name="折旧年限")
	private Integer depreciationYear;// 折旧年限
	@FieldMeta(name="到期时间")
	private String endtime;// 到期时间
	@FieldMeta(name="购买设备时间")
	private String buytime;// 购买设备时间
	@FieldMeta(name="购买金额")
	private Float buyamount;// 购买金额
	@FieldMeta(name="设备净值")
	private String equipmentworth;// 设备净值
	@FieldMeta(name="供应商")
	private String isGys;// 是否为供应商(null或者no表示不是数字表示供应商id为表ZhUser的id)
	@FieldMeta(name="关键设备")
	private String isKey;// 是否关键设备（yes:是，no null :不是）
	private String jdlfirstdate;// 第一次使用的时间
	@FieldMeta(name="稼动率")
	private Float jiadonglv;// （设备运行时长/设备使用时长）稼动率
	private Float machine_yzsj;
	private String islsxgw;//是否为流水线工位
	@FieldMeta(name="标准偏差")
	private Float stdev;//标准偏差
	private Float cp;//
	private Float ca;//
	private Float cpk;//
	
	
	private Set<EquipmentChanges> equChanges;// 设备移动信息(设备对移动记录( 一对多))
	private Set<ProcessGzstore> machines;// 工序库表关联
	private PmiManagement pmiManagements;// 关联pmi表
	private String isdj;// 是否需要点检;
	private Set<MachineSparePart> machineSpareParts;// 关键设备的备件
	private Set<DJNR> djnr;// 对应点检内容表； 多对多;
	private List<DJNR> djnrList;
	private Set<Users> userset;//用户对应工位设备(多对多);
	private String useStatus;//使用状态;()
	private Set<BaoYangBiaoZhun> bybzSet;//保养标准（一对多关系）
	private List<BaoYangBiaoZhun> bybzList;//也面传值使用
	private String baoyangType;//保养分类
	@FieldMeta(name="图纸",url="/upload/file/machine",img=true)
	private String pic;
	private File picF;
	private String  picFContentType;
	private String picFFileName;
	
	
	public Machine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEquipmentworth() {
		return equipmentworth;
	}

	public void setEquipmentworth(String equipmentworth) {
		Float equipmentworth_f = Float.parseFloat(equipmentworth);
		if(equipmentworth_f>0){
			this.equipmentworth = equipmentworth;
		}else{
			this.equipmentworth ="0.00";
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getWorkPosition() {
		return workPosition;
	}

	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return type  ;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassGroup() {
		return classGroup;
	}

	public void setClassGroup(String classGroup) {
		this.classGroup = classGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusMore() {
		return statusMore;
	}

	public void setStatusMore(String statusMore) {
		this.statusMore = statusMore;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getDepreciationYear() {
		return depreciationYear;
	}

	public void setDepreciationYear(Integer depreciationYear) {
		this.depreciationYear = depreciationYear;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getBuytime() {
		return buytime;
	}

	public void setBuytime(String buytime) {
		this.buytime = buytime;
	}

	public Float getBuyamount() {
		return buyamount;
	}

	public void setBuyamount(Float buyamount) {
		this.buyamount = buyamount;
	}

	public String getIsGys() {
		return isGys;
	}

	public void setIsGys(String isGys) {
		this.isGys = isGys;
	}

	@JSONField(serialize = false)
	public Set<EquipmentChanges> getEquChanges() {
		return equChanges;
	}

	public void setEquChanges(Set<EquipmentChanges> equChanges) {
		this.equChanges = equChanges;
	}

	@JSONField(serialize = false)
	public Set<ProcessGzstore> getMachines() {
		return machines;
	}

	public void setMachines(Set<ProcessGzstore> machines) {
		this.machines = machines;
	}

	@JSONField(serialize = false)
	public PmiManagement getPmiManagements() {
		return pmiManagements;
	}

	public void setPmiManagements(PmiManagement pmiManagements) {
		this.pmiManagements = pmiManagements;
	}

	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	@JSONField(serialize = false)
	public Set<MachineSparePart> getMachineSpareParts() {
		return machineSpareParts;
	}

	public void setMachineSpareParts(Set<MachineSparePart> machineSpareParts) {
		this.machineSpareParts = machineSpareParts;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	@JSONField(serialize = false)
	public Set<DJNR> getDjnr() {
		return djnr;
	}

	public void setDjnr(Set<DJNR> djnr) {
		this.djnr = djnr;
	}

	public List<DJNR> getDjnrList() {
		return djnrList;
	}

	public void setDjnrList(List<DJNR> djnrList) {
		this.djnrList = djnrList;
	}

	public String getIsdj() {
		return isdj;
	}

	public void setIsdj(String isdj) {
		this.isdj = isdj;
	}

	public String getJdlfirstdate() {
		return jdlfirstdate;
	}

	public void setJdlfirstdate(String jdlfirstdate) {
		this.jdlfirstdate = jdlfirstdate;
	}

	
	public Float getJiadonglv() {
		return jiadonglv;
	}

	public void setJiadonglv(Float jiadonglv) {
		this.jiadonglv = jiadonglv;
	}

	public Float getMachine_yzsj() {
		return machine_yzsj;
	}

	public void setMachine_yzsj(Float machineYzsj) {
		machine_yzsj = machineYzsj;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getIsManual() {
		return isManual;
	}

	public void setIsManual(String isManual) {
		this.isManual = isManual;
	}
	
	@JSONField(serialize = false)
	public Set<Users> getUserset() {
		return userset;
	}

	public void setUserset(Set<Users> userset) {
		this.userset = userset;
	}

	public String getIslsxgw() {
		return islsxgw;
	}

	public void setIslsxgw(String islsxgw) {
		this.islsxgw = islsxgw;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	@JSONField(serialize = false)
	public Set<BaoYangBiaoZhun> getBybzSet() {
		return bybzSet;
	}

	public void setBybzSet(Set<BaoYangBiaoZhun> bybzSet) {
		this.bybzSet = bybzSet;
	}

	public List<BaoYangBiaoZhun> getBybzList() {
		return bybzList;
	}

	public void setBybzList(List<BaoYangBiaoZhun> bybzList) {
		this.bybzList = bybzList;
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

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public File getPicF() {
		return picF;
	}

	public void setPicF(File picF) {
		this.picF = picF;
	}

	public String getPicFContentType() {
		return picFContentType;
	}

	public void setPicFContentType(String picFContentType) {
		this.picFContentType = picFContentType;
	}

	public String getPicFFileName() {
		return picFFileName;
	}

	public void setPicFFileName(String picFFileName) {
		this.picFFileName = picFFileName;
	}

	public String getBaoyangType() {
		return baoyangType;
	}

	public void setBaoyangType(String baoyangType) {
		this.baoyangType = baoyangType;
	}
	
	
}
