package com.task.entity.led;

import java.io.Serializable;
import java.util.Set;

public class LED implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 名称
	private Integer number;// 编号
	private String sendStatus;// 待推送、已推送
	private String dress;// 穿戴标准
	private String ip;// IP
	private String port;// 端口
	private String domainName;// 域名
	private String stations;// 工位
	private Float width;// 宽
	private Float higth;// 高
	private Integer fontSize;// 字体大小
	private Integer endfontSize;// 完成字体大小
	private Integer color;// 颜色()
	private Integer iactionType;// 移动方式(2-左移 5-连续上移)
	private Integer ialignStyle;// 对齐方式(0－左对齐 1－居中 2－右对齐)
	private Integer iholdTime;// 暂停时间
	private String style;// 样式 (1、64*32 2、192*64(倒计时) 3、128*64)
	private Set<LEDLog> lEDLog;// LED记录

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getStations() {
		return stations;
	}

	public void setStations(String stations) {
		this.stations = stations;
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public Float getHigth() {
		return higth;
	}

	public void setHigth(Float higth) {
		this.higth = higth;
	}

	public Integer getFontSize() {
		return fontSize;
	}

	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}

	public Set<LEDLog> getlEDLog() {
		return lEDLog;
	}

	public void setlEDLog(Set<LEDLog> lEDLog) {
		this.lEDLog = lEDLog;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDress() {
		return dress;
	}

	public void setDress(String dress) {
		this.dress = dress;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Integer getIactionType() {
		if (iactionType == null) {
			iactionType = 5;
		}
		return iactionType;
	}

	public void setIactionType(Integer iactionType) {
		this.iactionType = iactionType;
	}

	public Integer getIalignStyle() {
		if (ialignStyle == null) {
			ialignStyle = 0;
		}
		return ialignStyle;
	}

	public void setIalignStyle(Integer ialignStyle) {
		this.ialignStyle = ialignStyle;
	}

	public Integer getIholdTime() {
		if (iholdTime == null) {
			iholdTime = 20;
		}
		return iholdTime;
	}

	public void setIholdTime(Integer iholdTime) {
		this.iholdTime = iholdTime;
	}

	public Integer getEndfontSize() {
		return endfontSize;
	}

	public void setEndfontSize(Integer endfontSize) {
		this.endfontSize = endfontSize;
	}

}
