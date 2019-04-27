package com.task.entity.gzbj;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Machine;
import com.task.entity.Users;
import com.task.entity.android.OsScope;
import com.task.entity.sop.ProcessGzstoreFuLiao;
import com.task.util.FieldMeta;

/*
 * 
 * 工序表 (表名:ta_processgzstore)
 */
public class ProcessGzstore implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer processNO;// 工序号
	@FieldMeta(name = "工序名称")
	private String processName;// 工序名
	private String shebeistatus;// 设备验证
	private String gongzhuangstatus;// 工装验证
	private String liangjustatus;// 量具验证
	private String guifanstatus;// 规范验证
	private String kaoqingstatus;// 考勤验证
	private String processStatus;// 状态(并行/单独)
	private String isPrice;// 是否参与奖金分配(true/false)
	private String zjStatus;// 是否首检
	private String isJisuan;// 是否计算(yes/no)
	private String productStyle;// 生产类型（自制，外委）
	private String more;// 对工装的描述
	private String isNeedFuliao;// 是否需要辅料(yes/no)
	private String status;// （已更新，未更新）;
	private String needSave;// 是否转存(是，否)
	private String isSpecial;// 是否特殊工序;（特殊,普通,打回 默认普通）
	private Integer epId;// 流程ID

	private Set processgzstores = new HashSet();
	private Set<Machine> processgzstores1 = new HashSet();
	private Set<Users> users;// 用户（多对多）
	private Set<ProcessGzstoreFuLiao> processGzstoreFuLiaos;// 工序辅料
	private List<ProcessGzstoreFuLiao> fuliaoList;// 工序辅料(页面传值使用)
	
	private Set<OsScope> os;// 检查项 （一对多）

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProcessNO() {
		return processNO;
	}

	public void setProcessNO(Integer processNO) {
		this.processNO = processNO;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getShebeistatus() {
		return shebeistatus;
	}

	public void setShebeistatus(String shebeistatus) {
		this.shebeistatus = shebeistatus;
	}

	public String getGongzhuangstatus() {
		return gongzhuangstatus;
	}

	public void setGongzhuangstatus(String gongzhuangstatus) {
		this.gongzhuangstatus = gongzhuangstatus;
	}

	public String getLiangjustatus() {
		return liangjustatus;
	}

	public void setLiangjustatus(String liangjustatus) {
		this.liangjustatus = liangjustatus;
	}

	public String getGuifanstatus() {
		return guifanstatus;
	}

	public void setGuifanstatus(String guifanstatus) {
		this.guifanstatus = guifanstatus;
	}

	public String getKaoqingstatus() {
		return kaoqingstatus;
	}

	public void setKaoqingstatus(String kaoqingstatus) {
		this.kaoqingstatus = kaoqingstatus;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public String getIsPrice() {
		return isPrice;
	}

	public void setIsPrice(String isPrice) {
		this.isPrice = isPrice;
	}

	public String getZjStatus() {
		return zjStatus;
	}

	public void setZjStatus(String zjStatus) {
		this.zjStatus = zjStatus;
	}

	public String getIsJisuan() {
		return isJisuan;
	}

	public void setIsJisuan(String isJisuan) {
		this.isJisuan = isJisuan;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getIsNeedFuliao() {
		return isNeedFuliao;
	}

	public void setIsNeedFuliao(String isNeedFuliao) {
		this.isNeedFuliao = isNeedFuliao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNeedSave() {
		return needSave;
	}

	public void setNeedSave(String needSave) {
		this.needSave = needSave;
	}

	public String getIsSpecial() {
		return isSpecial;
	}

	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	@JSONField(serialize = false)
	public Set getProcessgzstores() {
		return processgzstores;
	}

	public void setProcessgzstores(Set processgzstores) {
		this.processgzstores = processgzstores;
	}

	@JSONField(serialize = false)
	public Set<Machine> getProcessgzstores1() {
		return processgzstores1;
	}

	public void setProcessgzstores1(Set<Machine> processgzstores1) {
		this.processgzstores1 = processgzstores1;
	}

	@JSONField(serialize = false)
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	@JSONField(serialize = false)
	public Set<ProcessGzstoreFuLiao> getProcessGzstoreFuLiaos() {
		return processGzstoreFuLiaos;
	}

	public void setProcessGzstoreFuLiaos(
			Set<ProcessGzstoreFuLiao> processGzstoreFuLiaos) {
		this.processGzstoreFuLiaos = processGzstoreFuLiaos;
	}

	@JSONField(serialize = false)
	public List<ProcessGzstoreFuLiao> getFuliaoList() {
		return fuliaoList;
	}

	public void setFuliaoList(List<ProcessGzstoreFuLiao> fuliaoList) {
		this.fuliaoList = fuliaoList;
	}

	@JSONField(serialize = false)
	public Set<OsScope> getOs() {
		return os;
	}

	public void setOs(Set<OsScope> os) {
		this.os = os;
	}

}
