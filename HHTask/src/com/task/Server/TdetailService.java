package com.task.Server;

import java.util.List;

import com.task.entity.Project;
import com.task.entity.Tdetail;

public interface TdetailService {
	
	/**
	 * 添别一个总成
	 * @param details
	 */
	public void addAll(List<Tdetail> details);
	
	/**
	 * 获取所有的自制件
	 */
	public List<Tdetail> get(Project p);

	/**
	 * 获得ID和名称的下拉选项，别的属性没有
	 * @param project
	 * @return
	 */
	public List getZizhiSelect(Project project);

	/**
	 *  获出这颗树的明细
	 * @param project
	 * @return
	 */
	public List getAll(Project project);

	/**
	 * 获得所有总成
	 * @param project
	 * @return
	 */
	public List<Tdetail> getZongCheng(Project project);


}
