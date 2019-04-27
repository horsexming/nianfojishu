package com.task.entity;

import java.io.Serializable;

/**
 * 项目启动书_项目责任书(第五步)
 * @author 马凯
 *
 */
public class ProjectStartResponsible  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String responsibility;//会签纪要上传路径

	private ProjectStart root;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public ProjectStart getRoot() {
		return root;
	}

	public void setRoot(ProjectStart root) {
		this.root = root;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}


}
