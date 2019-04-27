
package com.task.entity.dmltry;

import java.io.Serializable;

/**
 * apk	上传表(ta_DmlAppFileUrl)
 * @author 杜明龙
 *
 */
public class DmlAppFileUrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer id;
	private Integer coide;		//版本号
	private String appfileurlfj; // 附件名称
	private DmltryAppFiles dmltryAppFiles; // 外键
	private String addTimeDmlAppFileUrl; // 提交时间
	private String upDateTimeDmlAppFileUrl;// 修改时间

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppfileurlfj() {
		return appfileurlfj;
	}

	public void setAppfileurlfj(String appfileurlfj) {
		this.appfileurlfj = appfileurlfj;
	}

	public DmltryAppFiles getDmltryAppFiles() {
		return dmltryAppFiles;
	}

	public void setDmltryAppFiles(DmltryAppFiles dmltryAppFiles) {
		this.dmltryAppFiles = dmltryAppFiles;
	}

	public String getAddTimeDmlAppFileUrl() {
		return addTimeDmlAppFileUrl;
	}

	public void setAddTimeDmlAppFileUrl(String addTimeDmlAppFileUrl) {
		this.addTimeDmlAppFileUrl = addTimeDmlAppFileUrl;
	}

	public String getUpDateTimeDmlAppFileUrl() {
		return upDateTimeDmlAppFileUrl;
	}

	public void setUpDateTimeDmlAppFileUrl(String upDateTimeDmlAppFileUrl) {
		this.upDateTimeDmlAppFileUrl = upDateTimeDmlAppFileUrl;
	}

	public Integer getCoide() {
		return coide;
	}

	public void setCoide(Integer coide) {
		this.coide = coide;
	}

}
