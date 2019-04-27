package com.task.Server;

import java.util.List;

import com.task.entity.Templateb;
import com.task.entity.Templatenode;

public interface ProcessManagementService {
	public boolean add(Templateb templateb);// 增加

	public boolean add(Templatenode templatenode);// 增加

	public boolean delete(Templateb templateb);// 删除

	public boolean update(Templateb templateb);// 修改

	public Templateb findAssetById(int id);// 根据ID查询

	List selectGrouping(String status);

	Object[] findTemplatebByCondition(Templateb templateb, int pageNo,
			int pageSize);

	List findPersonByGroupId(Integer id);
}
