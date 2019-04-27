package com.task.entity.zhiliang;

import java.io.Serializable;

/**
 * 可靠性测试记录
 * @author xs-cy
 *
 */
public class ReliabilityTestPro implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String proName; //项目名称
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
}
