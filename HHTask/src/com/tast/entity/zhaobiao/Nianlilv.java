package com.tast.entity.zhaobiao;

import java.io.Serializable;

public class Nianlilv  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;//id	
	private String nianfen;//年份
	private Float  lilv;//年利率
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNianfen() {
		return nianfen;
	}
	public void setNianfen(String nianfen) {
		this.nianfen = nianfen;
	}
	public Float getLilv() {
		return lilv;
	}
	public void setLilv(Float lilv) {
		this.lilv = lilv;
	}
	
	
}
