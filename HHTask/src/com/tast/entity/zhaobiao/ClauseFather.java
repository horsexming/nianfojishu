package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 协议主表
 *   ClauseFather
 */
public class ClauseFather implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private String name;// 协议名称
	private String jianjie;// 协议简介

	public Integer getId() {
		return id;
	}



	public String getJianjie() {
		return jianjie;
	}

	public void setJianjie(String jianjie) {
		this.jianjie = jianjie;
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


}
