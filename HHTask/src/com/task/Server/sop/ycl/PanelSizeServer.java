package com.task.Server.sop.ycl;

import java.util.List;

import com.task.entity.codetranslation.CodeTranslation;
import com.task.entity.sop.ycl.PanelSize;

public interface PanelSizeServer {
	/**
	 * 添加
	 * @param panelsize
	 * @return
	 */
	public String addPanelSize(PanelSize panelsize );
	/**
	 * 修改
	 * @param panelsize
	 * @return
	 */
	public String updatePanelSize(PanelSize panelsize);
	/**
	 * 删除
	 * 
	 */
	public String delPanelSize(PanelSize panelsize);
	/**
	 * 查询
	 */
	Object[] findAllPanelSize(PanelSize panelsize  ,int pageNo, 
			int pageSize,String status,Float houdu);
	/**
	 * 根据Id获取
	 */
	PanelSize findPanelSizeById(Integer id);
	
}
