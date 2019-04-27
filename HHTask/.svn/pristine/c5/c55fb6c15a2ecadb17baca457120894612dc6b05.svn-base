package com.task.Server;

import java.util.List;

import com.task.entity.Teammembers;

public interface TeammembersServer {

	// 添加班组成员
	public boolean saveTeammembers(Teammembers teammembers);

	// 根据ID查询出班组成员
	public Teammembers findByID(int id);

	// 删除班组成员
	public boolean deleteTeammembers(Teammembers teammembers);

	// 查询出班组成员
	public List findAll(String dept, int pageNo, int pageSize);

	// 查询出班组成员的总数
	public int getcount(String dept);

	// 根据工号查询出 卡号和姓名
	public List findmembernumber(String membernumber);

	// 根据工号查询出班组成员是否存在
	public List findtemembernumberteammembers(String gonghao);

	/***
	 * 根据待添加人员的工号以及添加人的工号查询人员信息
	 * 
	 * @param teammembersmembernumber
	 *            待添加人员的工号
	 * @param addUserCode
	 *            添加人员的工号
	 * @return Teammembers
	 */
	public Teammembers findTeammembersBYAddUser(String teammembersmembernumber,
			String addUserCode);

	/***
	 * 根据待添加人员的工号出该人员所有的下属成员
	 * 
	 * @param addUserCode
	 *            添加人员的工号
	 * @return List
	 */
	public List<Teammembers> findTeammembersBYAddUser(int addUserId,
			int pageNo, int pageSize);

	/***
	 * 根据待添加人员的工号查成员的总数
	 * 
	 * @param addUserCode
	 *            添加人员的工号
	 * @return int
	 */
	public int getcountBYAddUser(int addUserId);
}
