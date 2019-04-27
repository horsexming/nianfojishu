package com.task.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: Store
 * @Description: 库存表；
 * @author Damon
 * @date 2013-4-23 下午01:46:25
 * 
 *       add relaction Fields 入库:private Set<Storage> storages 关系:
 *       Store(one-to-many): Storage(many-to-one) 报废:private Set<Scrap> scraps
 *       关系:Store(one-to-many) : Scrap(many-to-one) 维修:private Set<Maintain>
 *       maintains 关系:Store(one-to-many): Maintain(many-to-one) 借 :private
 *       Set<Borrow> borrows 关系:Store(one-to-many): Borrow(many-to-one) 还
 *       :private Set<Also> alsos 关系:Store(one-to-many): Also(many-to-one)
 *       以旧换新:private Set<Renew> renews 关系:Store(one-to-many):
 *       Renew(many-to-one) 出库: 领 :private Set<Consuming> consumings
 *       关系:Store(one-to-many): Consuming(many-to-one)
 */
@SuppressWarnings("serial")
public class Store implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String number;// 编号
	private String matetag;// 名称
	private Float total;// 总数
	private String unit;// 单位
	private String format;// 规格
	private String storehouse;// 仓库()
	private String mix;// 　合成主码
	private String parClass;// 　分类
	private String place;// 位置(库位)
	private Integer period;// 维修周期（校检）
	private Date startDate;// 上次保养
	private Float curAmount;// 当前量
	private Integer maxBorrowNum;// 　最大可借量
	private String more;// 　备注
	private String more1;// 备注1
	private Float price;// 价格
	private String id1;//本厂编号

	private Integer carePeriod;// 保养周期
	private Integer curworkAmount;// 当前工作量
	private Date lastCareDate;// 最后维修日期
	private String serverCardId;// 加工件号
	private String carModel;// 车型
	private String duizhang;// 对账
	private Float minStore;// 最低库存，低于此最低库存就要申请采购
	private String appDept;// 申报部门
	private Float totMoney;// 合计金额

	private String classify; // (可借用/领用)

	private Set<Storage> storages = new HashSet<Storage>();//入库
	private Set<Scrap> scraps = new HashSet<Scrap>();//报废
	private Set<Maintain> maintains = new HashSet<Maintain>();//报检
	private Set<Borrow> borrows = new HashSet<Borrow>();//借
	private Set<Also> alsos = new HashSet<Also>();//还
	private Set<Renew> renews = new HashSet<Renew>();//以旧换新
	private Set<OutLib> outlibs = new HashSet<OutLib>();//出库
	private Set<Consuming> consumings = new HashSet<Consuming>();//领用

	
	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public Store() {
	}

	/** minimal constructor */
	public Store(Integer id) {
		this.id = id;
	}

	public String getAppDept() {
		return appDept;
	}

	public void setAppDept(String appDept) {
		this.appDept = appDept;
	}

	public Float getTotMoney() {
		return totMoney;
	}

	public void setTotMoney(Float totMoney) {
		this.totMoney = totMoney;
	}

	public String getServerCardId() {
		return serverCardId;
	}

	public void setServerCardId(String serverCardId) {
		this.serverCardId = serverCardId;
	}

	public Date getLastCareDate() {
		return lastCareDate;
	}

	public void setLastCareDate(Date lastCareDate) {
		this.lastCareDate = lastCareDate;
	}

	public Integer getCarePeriod() {
		return carePeriod;
	}

	public void setCarePeriod(Integer carePeriod) {
		this.carePeriod = carePeriod;
	}

	public Integer getCurworkAmount() {
		return curworkAmount;
	}

	public void setCurworkAmount(Integer curworkAmount) {
		this.curworkAmount = curworkAmount;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMatetag() {
		return this.matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStorehouse() {
		return this.storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public String getMix() {
		return this.mix;
	}

	public void setMix(String mix) {
		this.mix = mix;
	}

	public String getParClass() {
		return this.parClass;
	}

	public void setParClass(String parClass) {
		this.parClass = parClass;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getPeriod() {
		return this.period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Float getCurAmount() {
		return curAmount;
	}

	public void setCurAmount(Float curAmount) {
		this.curAmount = curAmount;
	}

	public Integer getMaxBorrowNum() {
		return maxBorrowNum;
	}

	public void setMaxBorrowNum(Integer maxBorrowNum) {
		this.maxBorrowNum = maxBorrowNum;
	}

	public String getMore() {
		return this.more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getMore1() {
		return this.more1;
	}

	public void setMore1(String more1) {
		this.more1 = more1;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getDuizhang() {
		return duizhang;
	}

	public void setDuizhang(String duizhang) {
		this.duizhang = duizhang;
	}

	public Float getMinStore() {
		return minStore;
	}

	public void setMinStore(Float minStore) {
		this.minStore = minStore;
	}

	public Set<Storage> getStorages() {
		return storages;
	}

	public void setStorages(Set<Storage> storages) {
		this.storages = storages;
	}

	public Set<Scrap> getScraps() {
		return scraps;
	}

	public void setScraps(Set<Scrap> scraps) {
		this.scraps = scraps;
	}

	public Set<Maintain> getMaintains() {
		return maintains;
	}

	public void setMaintains(Set<Maintain> maintains) {
		this.maintains = maintains;
	}

	public Set<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(Set<Borrow> borrows) {
		this.borrows = borrows;
	}

	public Set<Also> getAlsos() {
		return alsos;
	}

	public void setAlsos(Set<Also> alsos) {
		this.alsos = alsos;
	}

	public Set<Renew> getRenews() {
		return renews;
	}

	public void setRenews(Set<Renew> renews) {
		this.renews = renews;
	}

	public Set<OutLib> getOutlibs() {
		return outlibs;
	}

	public void setOutlibs(Set<OutLib> outlibs) {
		this.outlibs = outlibs;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public Set<Consuming> getConsumings() {
		return consumings;
	}

	public void setConsumings(Set<Consuming> consumings) {
		this.consumings = consumings;
	}

}
