package com.task.Server;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.KeHuManYiDiaoCha;

public interface KeHuManYiDiaoChaServer {
	
	/**添加
	 * @param khmydc
	 * @return
	 */
	public boolean add(KeHuManYiDiaoCha khmydc,File[] attachment,
			String[] attachmentFileName,String status);
	/**
	 * 修改
	 * @param khmydc
	 * @return
	 */
	public boolean update(KeHuManYiDiaoCha khmydc,File[] attachment,
			String[] attachmentFileName);
	/**
	 * 删除
	 * @param khmydc
	 * @return
	 */
	public boolean del(KeHuManYiDiaoCha khmydc);
	/**
	 * 查询
	 * @return
	 */
	public Map<Integer, Object> findAllkhmydclist(KeHuManYiDiaoCha khmydc,
			int pageNo, int pageSize,String statue);
	
	public List queryAllClient();
	
}
