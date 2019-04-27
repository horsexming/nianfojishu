package com.task.Server;

import java.util.List;
import java.util.Map;


import com.task.entity.Integral;
import com.task.entity.XiaoFei;

public interface XiaoFeiServerDao {
	/**
	 * 删除消费记录；
	 */
	public boolean delXiaoFei(XiaoFei xf);
	/**
	 * 修改消费记录
	 */
	public boolean updataXiaoFei(XiaoFei xf);
	/**
	 * 显示消费记录(个人)
	 */
	public Map<Integer, Object> findIntegralByCondition(XiaoFei xf,int pageNo, int pageSize);
	/**
	 * 显示消费记录(所有)
	 */
	public List<XiaoFei> findAllByPage(int pageNo, int pageSize);
	/**
	 * 获得总数量
	 */
	public int getcont();
}
