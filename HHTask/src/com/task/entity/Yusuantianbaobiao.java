package com.task.entity;

import com.task.util.FieldMeta;



/**
 * 预算填报表格
 * 表名：ta_Yusuantianbaobiao
 * @author 刘培
 * 
 */
public class Yusuantianbaobiao implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name = "项目名称")
	private String xiangmumingda; 			//项目名称或者代码
	private Integer epid;
	private float gudingzichan;      		//固定资产
	private float renlichengbenzengjia; 	//人力成本增加
	private float shiyanfeiyong;			//试验费用
	private float qitafeiyong;				//其他费用
	private float shouyijine;				//收益金额
	private String shouyinianxian;			//收益年限
	private String yusuanleixing;			//预算类型
	private String niandu;					//年度
	@FieldMeta(name = "部门")
	private String bumen;					//部门
	@FieldMeta(name = "姓名")
	private String name;					//输入人姓名
	private String code;					//输入人工号
	private String time;					//时间
	private String addtime;					//添加时间
	private String shenpi;					//预备审批
	private String beizhu;					//备注数据
	@FieldMeta(name = "预算金额")
	private float zongjine;					//总金额	
	private float shengyu;					//剩余金额
	private float kx1;						//开销
	private float kx2;						//开销	
	private float kx3;						//开销	
	private float kx4;						//开销	
	private float kx5;						//开销	
	private float kx6;						//开销	
	private float kx7;						//开销	
	private float kx8;						//开销	
	private float kx9;						//开销	
	private float kx10;						//开销	
	private float kx11;						//开销	
	private float kx12;						//开销	
	private Yusuantianbaototal yusuantianbaototal;
	private String currency;				//币种类型
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getXiangmumingda() {
		return xiangmumingda;
	}
	public void setXiangmumingda(String xiangmumingda) {
		this.xiangmumingda = xiangmumingda;
	}
	public float getGudingzichan() {
		return gudingzichan;
	}
	public void setGudingzichan(float gudingzichan) {
		this.gudingzichan = gudingzichan;
	}
	public float getRenlichengbenzengjia() {
		return renlichengbenzengjia;
	}
	public void setRenlichengbenzengjia(float renlichengbenzengjia) {
		this.renlichengbenzengjia = renlichengbenzengjia;
	}
	public float getShiyanfeiyong() {
		return shiyanfeiyong;
	}
	public void setShiyanfeiyong(float shiyanfeiyong) {
		this.shiyanfeiyong = shiyanfeiyong;
	}
	public float getQitafeiyong() {
		return qitafeiyong;
	}
	public void setQitafeiyong(float qitafeiyong) {
		this.qitafeiyong = qitafeiyong;
	}
	public float getShouyijine() {
		return shouyijine;
	}
	public void setShouyijine(float shouyijine) {
		this.shouyijine = shouyijine;
	}
	public String getShouyinianxian() {
		return shouyinianxian;
	}
	public void setShouyinianxian(String shouyinianxian) {
		this.shouyinianxian = shouyinianxian;
	}
	public String getYusuanleixing() {
		return yusuanleixing;
	}
	public void setYusuanleixing(String yusuanleixing) {
		this.yusuanleixing = yusuanleixing;
	}
	public String getNiandu() {
		return niandu;
	}
	public void setNiandu(String niandu) {
		this.niandu = niandu;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getShenpi() {
		return shenpi;
	}
	public void setShenpi(String shenpi) {
		this.shenpi = shenpi;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public float getZongjine() {
		return zongjine;
	}
	public void setZongjine(float zongjine) {
		this.zongjine = zongjine;
	}
	public float getShengyu() {
		return shengyu;
	}
	public void setShengyu(float shengyu) {
		this.shengyu = shengyu;
	}
	public float getKx1() {
		return kx1;
	}
	public void setKx1(float kx1) {
		this.kx1 = kx1;
	}
	public float getKx2() {
		return kx2;
	}
	public void setKx2(float kx2) {
		this.kx2 = kx2;
	}
	public float getKx3() {
		return kx3;
	}
	public void setKx3(float kx3) {
		this.kx3 = kx3;
	}
	public float getKx4() {
		return kx4;
	}
	public void setKx4(float kx4) {
		this.kx4 = kx4;
	}
	public float getKx5() {
		return kx5;
	}
	public void setKx5(float kx5) {
		this.kx5 = kx5;
	}
	public float getKx6() {
		return kx6;
	}
	public void setKx6(float kx6) {
		this.kx6 = kx6;
	}
	public float getKx7() {
		return kx7;
	}
	public void setKx7(float kx7) {
		this.kx7 = kx7;
	}
	public float getKx8() {
		return kx8;
	}
	public void setKx8(float kx8) {
		this.kx8 = kx8;
	}
	public float getKx9() {
		return kx9;
	}
	public void setKx9(float kx9) {
		this.kx9 = kx9;
	}
	public float getKx10() {
		return kx10;
	}
	public void setKx10(float kx10) {
		this.kx10 = kx10;
	}
	public float getKx11() {
		return kx11;
	}
	public void setKx11(float kx11) {
		this.kx11 = kx11;
	}
	public float getKx12() {
		return kx12;
	}
	public void setKx12(float kx12) {
		this.kx12 = kx12;
	}
	public Yusuantianbaototal getYusuantianbaototal() {
		return yusuantianbaototal;
	}
	public void setYusuantianbaototal(Yusuantianbaototal yusuantianbaototal) {
		this.yusuantianbaototal = yusuantianbaototal;
	}
	public void setEpid(Integer epid) {
		this.epid = epid;
	}
	public Integer getEpid() {
		return epid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
