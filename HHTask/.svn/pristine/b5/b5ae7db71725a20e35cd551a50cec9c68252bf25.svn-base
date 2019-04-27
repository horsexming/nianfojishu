package com.task.Server;

import java.util.List;

import com.task.entity.Tclaimform;
import com.task.entity.TclaimsRecord;

/**
 * 索赔单记录Service
 * @author 马凯
 *
 */
public interface TclaimsRecordService {
	/**
	 * 通过索赔单,获取索赔单记录
	 * @param tclaimform
	 * @return
	 */
	public List<TclaimsRecord> get(Tclaimform tclaimform);

	/**
	 * 批量分析
	 * @param ids
	 * @param reason
	 * @param name
	 */
	public void updateAll(Integer[] ids, String reason, String name,String filename, String responsibility);

	/**
	 * 获取所有待通知的人
	 * @param root
	 * @return
	 */
	public List<TclaimsRecord> getNotification(Tclaimform root);

	/**
	 * 整改
	 * @param ids
	 * @param handle
	 * @param name
	 * @param fileName
	 */
	public void updateHandle(Integer[] ids, String handle, String name, String fileName);

	/**
	 * 获得
	 * @param integer
	 * @return
	 */
	public TclaimsRecord get(Integer integer);
}
