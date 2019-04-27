package com.task.Server;

import com.task.entity.MsgGroup;

public interface MsgGroupServiceI {

	/** 添加 */
	public boolean add(MsgGroup msg);
	
	/** 删除 */
	public boolean delete(MsgGroup msg);
	
	/** 修改 */
	public boolean modify(MsgGroup msg);

	public Object[] getList(int parseInt, int pageSize);
	
	/** 查询 */
//	public List<MsgGroup> get();
	
}
