package com.task.Server;

import java.io.Serializable;

import com.task.entity.Tclaimform;
/**
 * 索赔单
 * @author 马凯
 *
 */
public interface TclaimformService {

	/**
	 * 添加
	 * @param claimform
	 */
	public Serializable add(Tclaimform claimform);

	/**
	 * 列表
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] list(int parseInt, int pageSize);

	
	/**
	 * 获取
	 * @param claimform
	 * @return
	 */
	public Tclaimform get(Tclaimform claimform);
	
	/**
	 * 更新
	 * @param claimform
	 */
	public void update(Tclaimform claimform);

	/**
	 * 删除
	 * @param claimform
	 */
	public void delete(Tclaimform claimform);

	/**
	 * 通知
	 * @param claimform
	 */
	public void updateNotification(Tclaimform claimform);

	/**
	 * 扣款
	 * @param claimform
	 */
	public void updateDebit(Tclaimform claimform);
}
