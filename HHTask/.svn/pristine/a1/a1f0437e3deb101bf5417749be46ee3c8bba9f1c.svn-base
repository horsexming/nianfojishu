package com.task.Server.iao;

import com.task.entity.iao.IAOApply;

public interface IAOServer {
	/**添加出入申请**/
	boolean saveIAO(IAOApply iaoApply);
	/***查询出入申请**/
	Object[] findIAOApply(IAOApply iaoApply, String startDate, String endDate,
			Integer cpage, Integer pageSize,String tag);
	/**下拉菜单**/
	String findIAOStyle(String tag);
	/**根据ID获取对象**/
	IAOApply getIAOApplyById(Integer id);
	/**更新申请信息**/
	boolean updateIAOApp(IAOApply iaoApply,String tag);
	/**删除申请出入信息**/
	boolean deleteIAO(IAOApply iaoApply);
	/**数据导出**/
	void explorerEXL(IAOApply iaoApply, String startDate, String endDate);
}
