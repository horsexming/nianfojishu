package com.task.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SortableField {

	public SortableField() {
	}

	public SortableField(FieldMeta meta, Field field, Object obj) {
		super();
		this.meta = meta;
		this.field = field;
		this.name = field.getName();
		this.type = field.getType();
		this.value = "";
		try {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj
					.getClass());
			Method method = pd.getReadMethod();// 获得get方法
			Object propertyObj = method.invoke(obj);// 获得get方法的值
			if (propertyObj != null) {
				this.value = propertyObj.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public SortableField(FieldMeta meta, Field field,String value) {
		super();
		this.name = field.getName();
		this.type = field.getType();
		this.isId = meta.id();
		this.url = meta.url();
		this.isImg = meta.img();
		this.value = value;
		
		
	}

	public SortableField(FieldMeta meta, String name, Class<?> type) {
		super();
		this.meta = meta;
		this.name = name;
		this.type = type;
	}

	private FieldMeta meta;
	private Field field;
	private String name;
	private String value;
	private Class<?> type;
	private String url;
	private boolean isId;
	private boolean isImg;

	public FieldMeta getMeta() {
		return meta;
	}

	public void setMeta(FieldMeta meta) {
		this.meta = meta;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isId() {
		return isId;
	}

	public void setId(boolean isId) {
		this.isId = isId;
	}

	public boolean isImg() {
		return isImg;
	}

	public void setImg(boolean isImg) {
		this.isImg = isImg;
	}

}
