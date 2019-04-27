package com.task.entity.caiwu;

import java.io.Serializable;

/**
 * 凭证字表:(ta_DocumentWord)
 * @author wxf
 * 凭证字是财务软件中使用的名词之一。
 *	“收款凭证”，凭证字就是“收”。用于收取现金、银行存款时做
	“付款凭证”，凭证字就是“付”。用于支付现金、银行存款时做
	“转帐凭证”，凭证字就是“转”。用于不涉及现金或银行存款时做
	“记款凭证”，凭证字就是“记”。通用凭证，适用于以上前三种凭证字。
	备注：提现金、银行存款的提取，既可用收款凭证，也可用付款凭证。
 */
public class DocumentWord implements Serializable{
	private Integer id;
	private String name;//凭证名称
	private String qianzhui;//前缀
	private String status;//使用、禁用
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
	public String getQianzhui() {
		return qianzhui;
	}
	public void setQianzhui(String qianzhui) {
		this.qianzhui = qianzhui;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
