package com.task.entity.gongyi.gongxu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/***
 * 工序表
 * 
 * @表名 ta_sop_GongyiGuicheng_ProcessData
 * @author 陈曦
 * 
 */
public class ProcessData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer id;
	/** 工序ID */
	private Integer gongxuId;// 工序ID别的表读
	private Integer gongxuNo;// 工序号
	private String gongxuName;// 工序名

	private Integer shebeiId; // 设备ID
	private String shebeiNo; // 设备编号
	private String shebeiName; // 设备名称

	private Integer gongzhuangId; // 工装ID
	private String gongzhuangNo; // 工装编号
	private String gongzhuangName; // 工装名称

	private Integer liangjuId; // 量具ID
	private String liangjuNo; // 量具编号
	private String liangjuName; // 量具名称
	private String liangjuNoForCompany; // 本厂编号
	private String pici;// 批次
	private String remark;// 评论

	/** 索引号 */
	private String suoyinNumb;
	/** 更改单号 */
	private String danNumb;
	/** 签名 */
	private String qianming;
	/** 日期 */
	private Date qianmingDate;
	/** 工序图 */
	private String processImg;
	/** 工序视频 */
	private String processVideo;
	/** 焊接图 */
	private String hanjieImg;
	/** 焊接视频 */
	private String hanjieVideo;
	/** 材料 */
	private String cailiao;
	/** 基准 */
	private String jizhun;
	/** 夹、模具 */
	private String jiaOrMoju;
	/** 工艺规程ID */
	private Integer gongyiGuichengId;
	// 分别对应 工序图表栏目分区明细 工序说明栏区A3 A4
	private Integer onePage;
	private Integer twoPage;
	private Integer threePage;

	// 检验图表字段
	private String jianyanZhidaoImg;
	private String yichangYaoling;
	/** 编辑状态 */
	private String editStatus;

	/** 作业规范集合 */
	private OperationStandard operationStandard;
	private List<OperationStandard> operationStandardList;
	private String isGys;//是否为供应商(null或者no表示不是数字表示供应商id为表ZhUser的id)

	/** 毛料图表新增参数 */
	/** 毛料类型(管料 板料) */
	private String maoliaoType;
	/** 牌号 */
	private String paihao;
	/** 厚度 */
	private String houdu;
	/** 分类 */
	private String fenlei;
	/** 技术条件 */
	private String jishuTiaojian;
	/** 切料设备 */
	private String qieliaoShebei;

	/** 每道工序共同的标记 */
	private Long commonMark;
	/** 获得前段参数 */
	private String params;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSuoyinNumb() {
		return suoyinNumb;
	}

	public void setSuoyinNumb(String suoyinNumb) {
		this.suoyinNumb = suoyinNumb;
	}

	public String getQianming() {
		return qianming;
	}

	public void setQianming(String qianming) {
		this.qianming = qianming;
	}

	public Date getQianmingDate() {
		return qianmingDate;
	}

	public void setQianmingDate(Date qianmingDate) {
		this.qianmingDate = qianmingDate;
	}

	public String getProcessImg() {
		return processImg;
	}

	public void setProcessImg(String processImg) {
		this.processImg = processImg;
	}

	public String getJizhun() {
		return jizhun;
	}

	public void setJizhun(String jizhun) {
		this.jizhun = jizhun;
	}

	public String getJiaOrMoju() {
		return jiaOrMoju;
	}

	public void setJiaOrMoju(String jiaOrMoju) {
		this.jiaOrMoju = jiaOrMoju;
	}

	public String getDanNumb() {
		return danNumb;
	}

	public void setDanNumb(String danNumb) {
		this.danNumb = danNumb;
	}

	public OperationStandard getOperationStandard() {
		return operationStandard;
	}

	public void setOperationStandard(OperationStandard operationStandard) {
		this.operationStandard = operationStandard;
	}

	public List<OperationStandard> getOperationStandardList() {
		return operationStandardList;
	}

	public void setOperationStandardList(
			List<OperationStandard> operationStandardList) {
		this.operationStandardList = operationStandardList;
	}

	public Integer getGongyiGuichengId() {
		return gongyiGuichengId;
	}

	public void setGongyiGuichengId(Integer gongyiGuichengId) {
		this.gongyiGuichengId = gongyiGuichengId;
	}

	public String getProcessVideo() {
		return processVideo;
	}

	public void setProcessVideo(String processVideo) {
		this.processVideo = processVideo;
	}

	public String getCailiao() {
		return cailiao;
	}

	public void setCailiao(String cailiao) {
		this.cailiao = cailiao;
	}

	public String getGongxuName() {
		return gongxuName;
	}

	public void setGongxuName(String gongxuName) {
		this.gongxuName = gongxuName;
	}

	public String getParams() {
		if (params != null) {
			return params.replace("\\t", "").replace("\\r", "").replace("\\n",
					"").replace("\\f", "").replace("\\", "");
		}
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getOnePage() {
		return onePage;
	}

	public void setOnePage(Integer onePage) {
		this.onePage = onePage;
	}

	public Integer getTwoPage() {
		return twoPage;
	}

	public void setTwoPage(Integer twoPage) {
		this.twoPage = twoPage;
	}

	public Integer getThreePage() {
		return threePage;
	}

	public void setThreePage(Integer threePage) {
		this.threePage = threePage;
	}

	public String getJianyanZhidaoImg() {
		return jianyanZhidaoImg;
	}

	public void setJianyanZhidaoImg(String jianyanZhidaoImg) {
		this.jianyanZhidaoImg = jianyanZhidaoImg;
	}

	public String getYichangYaoling() {
		return yichangYaoling;
	}

	public void setYichangYaoling(String yichangYaoling) {
		this.yichangYaoling = yichangYaoling;
	}

	public String getShebeiNo() {
		return shebeiNo;
	}

	public void setShebeiNo(String shebeiNo) {
		this.shebeiNo = shebeiNo;
	}

	public String getShebeiName() {
		return shebeiName;
	}

	public void setShebeiName(String shebeiName) {
		this.shebeiName = shebeiName;
	}

	public String getGongzhuangNo() {
		return gongzhuangNo;
	}

	public void setGongzhuangNo(String gongzhuangNo) {
		this.gongzhuangNo = gongzhuangNo;
	}

	public String getGongzhuangName() {
		return gongzhuangName;
	}

	public void setGongzhuangName(String gongzhuangName) {
		this.gongzhuangName = gongzhuangName;
	}

	public String getPici() {
		return pici;
	}

	public void setPici(String pici) {
		this.pici = pici;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getShebeiId() {
		return shebeiId;
	}

	public void setShebeiId(Integer shebeiId) {
		this.shebeiId = shebeiId;
	}

	public Integer getGongzhuangId() {
		return gongzhuangId;
	}

	public void setGongzhuangId(Integer gongzhuangId) {
		this.gongzhuangId = gongzhuangId;
	}

	public Integer getGongxuNo() {
		return gongxuNo;
	}

	public void setGongxuNo(Integer gongxuNo) {
		this.gongxuNo = gongxuNo;
	}

	public String getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(String editStatus) {
		this.editStatus = editStatus;
	}

	public String getMaoliaoType() {
		return maoliaoType;
	}

	public void setMaoliaoType(String maoliaoType) {
		this.maoliaoType = maoliaoType;
	}

	public String getPaihao() {
		return paihao;
	}

	public void setPaihao(String paihao) {
		this.paihao = paihao;
	}

	public String getHoudu() {
		return houdu;
	}

	public void setHoudu(String houdu) {
		this.houdu = houdu;
	}

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}

	public String getJishuTiaojian() {
		return jishuTiaojian;
	}

	public void setJishuTiaojian(String jishuTiaojian) {
		this.jishuTiaojian = jishuTiaojian;
	}

	public String getQieliaoShebei() {
		return qieliaoShebei;
	}

	public void setQieliaoShebei(String qieliaoShebei) {
		this.qieliaoShebei = qieliaoShebei;
	}

	public Long getCommonMark() {
		return commonMark;
	}

	public void setCommonMark(Long commonMark) {
		this.commonMark = commonMark;
	}

	public String getHanjieImg() {
		return hanjieImg;
	}

	public void setHanjieImg(String hanjieImg) {
		this.hanjieImg = hanjieImg;
	}

	public String getHanjieVideo() {
		return hanjieVideo;
	}

	public void setHanjieVideo(String hanjieVideo) {
		this.hanjieVideo = hanjieVideo;
	}

	public Integer getLiangjuId() {
		return liangjuId;
	}

	public void setLiangjuId(Integer liangjuId) {
		this.liangjuId = liangjuId;
	}

	public String getLiangjuNo() {
		return liangjuNo;
	}

	public void setLiangjuNo(String liangjuNo) {
		this.liangjuNo = liangjuNo;
	}

	public String getLiangjuName() {
		return liangjuName;
	}

	public void setLiangjuName(String liangjuName) {
		this.liangjuName = liangjuName;
	}

	public Integer getGongxuId() {
		return gongxuId;
	}

	public void setGongxuId(Integer gongxuId) {
		this.gongxuId = gongxuId;
	}

	public String getLiangjuNoForCompany() {
		return liangjuNoForCompany;
	}

	public void setLiangjuNoForCompany(String liangjuNoForCompany) {
		this.liangjuNoForCompany = liangjuNoForCompany;
	}

	public String getIsGys() {
		return isGys;
	}

	public void setIsGys(String isGys) {
		this.isGys = isGys;
	}
	
}
