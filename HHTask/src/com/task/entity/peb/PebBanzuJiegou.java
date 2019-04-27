package com.task.entity.peb;

import java.io.Serializable;
import java.util.List;

import com.task.entity.Users;

/**
 * 产品下线（生产效率简报）
 * ta_pebProduction
 * @author wcy
 *
 */
public class PebBanzuJiegou implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Character se;//statisticsEfficiency 统计效率
	private Integer fatherId;// 父类Id
	private Integer belongLayer;// 当前层
	private String principals;//负责人ID，以;分隔
	private List<Users> userList; //负责人列表，页面传值
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
	
	public Integer getFatherId() {
		return fatherId;
	}
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
	public Integer getBelongLayer() {
		return belongLayer;
	}
	public void setBelongLayer(Integer belongLayer) {
		this.belongLayer = belongLayer;
	}
	public String getPrincipals() {
		return principals;
	}
	public void setPrincipals(String principals) {
		this.principals = principals;
	}
	
	
	public Character getSe() {
		return se;
	}
	public void setSe(Character se) {
		this.se = se;
	}
	public List<Users> getUserList() {
		return userList;
	}
	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}
	

	
}
