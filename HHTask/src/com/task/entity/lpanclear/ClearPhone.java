/**
 * 
 */
package com.task.entity.lpanclear;

import java.io.Serializable;

/**
 * 图片路径类
 */
public class ClearPhone implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;             //主键ID
    private String route;           //base64格式图片名称
    private String primaryName;     //图片原名称
	private String pictureDay;      //所属打扫时间
    private String content;         //描述
    private String dayload;         //图片上传时间
    private String manload;         //图片上传人
    
    


	public ClearPhone() {
		super();
	}	
	public ClearPhone(Integer id, String route, String pictureDay,String content,String dayload,String manload,String primaryName) {
		super();
		this.id = id;
		this.route = route;
		this.primaryName = primaryName;
		this.pictureDay = pictureDay;
		this.content = content;
		this.dayload = dayload;
		this.manload = manload;		
	}






	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
    public String getPrimaryName() {
		return primaryName;
	}
	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}
	public String getPictureDay() {
		return pictureDay;
	}
	public void setPictureDay(String pictureDay) {
		this.pictureDay = pictureDay;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDayload() {
		return dayload;
	}
	public void setDayload(String dayload) {
		this.dayload = dayload;
	}
	public String getManload() {
		return manload;
	}
	public void setManload(String manload) {
		this.manload = manload;
	}
}
