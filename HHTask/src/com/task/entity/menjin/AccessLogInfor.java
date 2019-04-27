package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 门禁日志记录表
 * 
 * @author Li_Cong 12-07 表名 ta_mj_AccessLogInfor 记录输出日志记录
 */
public class AccessLogInfor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String infor;// 进出信息
	private String cardId;// 卡号
	private String username;// 姓名
	private String yanzheng;// 验证码
	private String inOutType;// 进出类型
	private String useStatus;// 使用状态
	private String aceName;// 设备名称
	private Integer aceId;// 设备Id
	private String aceIp;// 设备IP
	private String addTime;// 添加时间

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getYanzheng() {
		return yanzheng;
	}

	public void setYanzheng(String yanzheng) {
		this.yanzheng = yanzheng;
	}

	public String getAceIp() {
		return aceIp;
	}

	public void setAceIp(String aceIp) {
		this.aceIp = aceIp;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAceName() {
		return aceName;
	}

	public void setAceName(String aceName) {
		this.aceName = aceName;
	}

	public String getInOutType() {
		return inOutType;
	}

	public void setInOutType(String inOutType) {
		this.inOutType = inOutType;
	}

	public Integer getAceId() {
		return aceId;
	}

	public void setAceId(Integer aceId) {
		this.aceId = aceId;
	}

}
