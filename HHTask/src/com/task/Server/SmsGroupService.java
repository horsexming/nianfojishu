package com.task.Server;

import java.util.List;
import com.task.entity.SmsGroup;
import com.task.entity.VSmsGroup;

/**
 * 短信的组功能
 * @author 马凯
 *
 */
public interface SmsGroupService {
	/**
	 * 添加新的组
	 * @param smsGroup
	 */
	public boolean add(SmsGroup smsGroup);
	
	/**
	 * 查询一个对象
	 * @param smsGroup
	 * @return
	 */
	public SmsGroup get(SmsGroup smsGroup);
	
	/**
	 * 更新组的名字
	 * @param smsGroup
	 */
	public boolean update(SmsGroup smsGroup);
	
	/**
	 * 删除一个组
	 * @param smsGroup
	 */
	public boolean delete(SmsGroup smsGroup);
	
	/**
	 * 查出所有的群组,以及人
	 * @return 
	 */
	public List<VSmsGroup> findAll();
	
	/**
	 * 查出所有群组
	 * @return
	 */
	public List<VSmsGroup> getAll();

	/**
	 * 通过ID查找群组
	 * @param groupId
	 * @return
	 */
	public SmsGroup get(int groupId);
}
