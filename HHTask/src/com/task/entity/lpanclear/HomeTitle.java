/**
 * 
 */
package com.task.entity.lpanclear;
import java.io.Serializable;
import java.util.Set;

/**
 * @author 梁盼
 * 房间标题信息实体类
 */
public class HomeTitle implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;                     //房间标题ID
    private String titleName;               //房间标题名称
    private String createDate;              //房间标题创建时间
    private String randomNum;               //标题对应的随机数
    
    

	public HomeTitle() {
		super();
	}
	public HomeTitle(Integer id, String titleName, String createDate) {
		super();
		this.id = id;
		this.titleName = titleName;
		this.createDate = createDate;
	}

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}   
	public String getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}
}
