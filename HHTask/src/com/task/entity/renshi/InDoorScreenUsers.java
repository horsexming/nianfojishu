package com.task.entity.renshi;

import java.io.Serializable;


/**
 * ta_hr_InDoorScreenUsers
 * @author WCY
 *
 */
public class InDoorScreenUsers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer usersId;
	private String usersName;
	private InDoorScreen screen;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUsersId() {
		return usersId;
	}
	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}
	public InDoorScreen getScreen() {
		return screen;
	}
	public void setScreen(InDoorScreen screen) {
		this.screen = screen;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	
	
	
	
}
