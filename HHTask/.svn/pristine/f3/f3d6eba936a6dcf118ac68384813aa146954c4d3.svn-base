package com.task.Server.sop;

import java.util.List;

import com.task.entity.sop.Block;
import com.task.entity.sop.BlockReceive;
import com.task.entity.sop.BlockUsers;

/****
 * 现场区块管理接口
 * 
 * @author 刘培
 * 
 */
public interface BlockServer { 
	/***
	 * 添加区块
	 * 
	 * @param block
	 * @return
	 */
	boolean addBlock(Block block);

	/***
	 * 查询区块
	 * 
	 * @param block
	 *            区块
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            数量
	 * @return
	 */
	Object[] findBlockByCondition(Block block, int pageNo, int pageSize);

	/***
	 * 查询所有区块
	 * 
	 * @return
	 */
	List findAllBlock(String blockStatus);

	/****
	 * 根据id查询区块
	 * 
	 * @param id
	 * @return
	 */
	Block findBlockById(int id);

	/***
	 * 更新区块信息
	 * 
	 * @param block
	 */
	void updateBlock(Block block);

	/***
	 * 通过卡号查询区块信息
	 * 
	 * @param card
	 * @return
	 */
	List findUserBlock(String card);

	/***
	 * 领取区块
	 * 
	 * @param id
	 *            区块id
	 */
	void receiveBlock(Integer id, String card);

	/***
	 * 提交区块
	 * 
	 * @param id
	 *            区块id
	 */
	void sendBlock(Integer id, String card);

	/**
	 * 根据区块id查询所有领取记录
	 * 
	 * @param id
	 *            区块id
	 * @return
	 */
	Object[] findAllReceiveBlock(Integer id, String card, int pageNo,
			int pageSize);

	/***
	 * 删除区块
	 * 
	 * @param block
	 */
	void deletBlock(Block block);

	/****
	 * 根据区块id查询用户
	 * 
	 * @param id
	 * @return
	 */
	List findBlockUsersById(int id);

	/***
	 * 分配区块用户
	 * 
	 * @param block
	 */
	void addBlockUser(BlockUsers blockUsers);

	/***
	 * 查询该区块今天是否是否已经领取
	 * 
	 * @param blockId
	 * @param card
	 * @return
	 */
	BlockReceive findReceBlockHis(Integer blockId, String card);

	/***
	 * 删除区块用户
	 * 
	 * @param blockUserId
	 *            用户id
	 * @return
	 */
	void delBlockUsers(Integer blockUserId);
	/***
	 * 确认区块
	 * 
	 * @param id
	 *            区块id
	 */
	void qrBlock(Integer id,Integer id1);

}
