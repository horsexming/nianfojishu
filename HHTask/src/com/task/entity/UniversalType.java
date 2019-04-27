package com.task.entity;

import java.io.Serializable;

/**
 * 通用类型类（ta_UniversalType）
 * @author WCY
 *
 */
public class UniversalType implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;
	private String name;
	private UniversalCategory category;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UniversalCategory getCategory() {
		return category;
	}
	public void setCategory(UniversalCategory category) {
		this.category = category;
	}
	
	
	
}