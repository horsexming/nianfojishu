package com.task.Server.sys;

import java.util.List;

import com.task.entity.DeptNumber;
import com.task.entity.DeptNumberVo;

/**
 * 用户部门server层
 * @author txb
 *
 */
public interface UserDeptServer {
	/**
	 * 获得所有的部门列表用vo存储并通过userid判断该用户是否拥有该部门权限
	 * @return
	 */
	public List<DeptNumberVo> getDeptVos(Integer id); 
}
