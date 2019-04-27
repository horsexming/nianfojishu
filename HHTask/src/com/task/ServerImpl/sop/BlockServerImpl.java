package com.task.ServerImpl.sop;

import java.util.Date;
import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.sop.BlockServer;
import com.task.entity.sop.Block;
import com.task.entity.sop.BlockReceive;
import com.task.entity.sop.BlockUsers;
import com.task.util.Util;

/****
 * 现场区块管理实现类
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("unchecked")
public class BlockServerImpl implements BlockServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/***
	 * 添加区块
	 * 
	 * @param block
	 * @return
	 */
	@Override
	public boolean addBlock(Block block) {
		if (block != null) {
			block.setAddDateTime(Util.getDateTime());
			block.setStatus("初始");
			return totalDao.save(block);
		}
		return false;
	}

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
	@Override
	public Object[] findBlockByCondition(Block block, int pageNo, int pageSize) {
		if (block == null) {
			block = new Block();
		} 
		String hql = totalDao.criteriaQueries(block, null);
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		for (Object object : list) {
			Block b =  (Block) object;
			List<String> name = totalDao.query("select userName from BlockUsers where block.id = ?", b.getId());
			if(!name.isEmpty())
				b.setYibangding(name.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
			int ii = totalDao.getCount("from BlockReceive where status = '完成'");
			if(ii>0)
				b.setDaiqueren(true);
		}
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 查询所有区块
	 * 
	 * @return
	 */
	@Override
	public List findAllBlock(String blockStatus) {
		String hql = "from Block where blockStatus=?";
		return totalDao.query(hql, blockStatus);
	}

	/****
	 * 根据id查询区块
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Block findBlockById(int id) {
		return (Block) totalDao.getObjectById(Block.class, id);
	}

	/****
	 * 根据区块id查询用户
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List findBlockUsersById(int id) {
		String hql = "from BlockUsers where block.id=?";
		return totalDao.query(hql, id);
	}

	/***
	 * 更新区块信息
	 * 
	 * @param block
	 */
	@Override
	public void updateBlock(Block block) {
		if (block != null) {
			totalDao.update(block);
		}
	}

	/***
	 * 分配区块用户
	 * 
	 * @param block
	 */
	@Override
	public void addBlockUser(BlockUsers blockUsers) {
		if (blockUsers != null) {
			totalDao.save(blockUsers);
		}
	}

	/***
	 * 通过卡号查询区块信息
	 * 
	 * @param card
	 * @return
	 */
	@Override
	public List findUserBlock(String card) {
		if (card != null && card.length() > 0) {
			// 查询人员
			String hql_user = "from BlockUsers where userCard=?";
			BlockUsers blockUsers = (BlockUsers) totalDao.getObjectByCondition(
					hql_user, card);
			String hql = "from Block where id ";
			if (blockUsers!=null&&blockUsers.getBlockId() != null && blockUsers.getBlockId() > 0) {
				hql += "=" + blockUsers.getBlockId();
			} else {
				hql += "in (select block.id from BlockUsers where userCard='"
						+ card + "') ";
			}
			// 查询待处理的区块
			List<Block> list = totalDao.query(hql);
			for (Block block : list) {
				if (blockUsers.getBlockId() != null
						&& blockUsers.getBlockId().equals(block.getId())) {
					block.setStatus(blockUsers.getBlockStatus());
				} else {
					block.setStatus("初始");
				}
			}
			return list;
		}
		return null;
	}

	/***
	 * 已领取区块
	 * 
	 * @param id
	 *            区块id
	 */
	@Override
	public void receiveBlock(Integer id, String card) {
		Block block = findBlockById(id);
		if (block != null) {
			String hql = "from BlockUsers where userCard=? and block.id=?";
			BlockUsers blockUsers = (BlockUsers) totalDao.getObjectByCondition(
					hql, card, id);
			if (blockUsers != null) {
				// 创建领取记录
				BlockReceive br = new BlockReceive();
				String startTime = Util.getDateTime();
				br.setReceiveDate(Util.getDateTime("yyyy-MM-dd"));
				br.setReceiveMonth(Util.getDateTime("yyyy-MM"));
				br.setCard(blockUsers.getUserCard());
				br.setUserName(blockUsers.getUserName());
				br.setStartTime(startTime);
				br.setStatus("已领取");
				br.setBlockId(block.getId());
				br.setBlockUsers(blockUsers);
				totalDao.save(br);
				// 更新区块信息
				Integer jihuoCount = block.getJihuoCount() == null ? 0 : block
						.getJihuoCount() + 1;
				if (jihuoCount < block.getUserCount()) {
					block.setStatus("领取中");
				} else {
					block.setStatus("已领取");
				}
				block.setJihuoCount(jihuoCount);// 激活数量
				totalDao.update(block);
				// 更新领取人的区块领取信息
				blockUsers.setBlockId(block.getId());
				blockUsers.setBlockStatus("已领");
				totalDao.update(blockUsers);
			}
		}
	}

	/***
	 * 查询该区块今天是否是否已经领取
	 * 
	 * @param blockId
	 * @param card
	 * @return
	 */
	@Override
	public BlockReceive findReceBlockHis(Integer blockId, String card) {
		String hql = "from BlockReceive where blockId=? and card=?  and status in ('已领取','完成')";
		return (BlockReceive) totalDao.getObjectByCondition(hql, blockId, card);
	}

	/***
	 * 提交区块
	 * 
	 * @param id
	 *            区块id
	 */
	@Override
	public void sendBlock(Integer id, String card) {
		Block block = findBlockById(id);
		if (block != null) {
			String hql = "from BlockUsers where userCard=? and block.id=?";
			BlockUsers blockUsers = (BlockUsers) totalDao.getObjectByCondition(
					hql, card, id);
			String endTime = Util.getDateTime();
			BlockReceive br = findReceBlockHis(id, card);
			br.setEndTime(endTime);
			br.setStatus("完成");
			totalDao.update(br);
			// 更新区块信息
			Integer finalCount = block.getFinalCount() == null ? 0 : block
					.getFinalCount() + 1;
			if (finalCount >= block.getUserCount()) {
				block.setStatus("完成");
			}
			block.setFinalCount(finalCount);// 激活数量
			totalDao.update(block);
			// 更新领取人的区块领取信息
			blockUsers.setBlockId(null);
			blockUsers.setBlockStatus("初始");
			totalDao.update(blockUsers);
		}
	}
	/***
	 * 确认区块
	 * 
	 * @param id
	 *            区块id
	 */
	@Override
	public void qrBlock(Integer id,Integer id1) {
		Block block = findBlockById(id1);
		if (block != null) {
			BlockReceive br = (BlockReceive) totalDao.getObjectById(BlockReceive.class, id);
			br.setQrTime(Util.getDateTime());
			br.setStatus("已确认");
			totalDao.update(br);
			// 更新区块信息
			Integer finalCount = block.getFinalCount() == null ? 0 : block
					.getFinalCount() + 1;
			if (finalCount >= block.getUserCount()) {
				block.setStatus("完成");
			}
			block.setFinalCount(finalCount);// 激活数量
			totalDao.update(block);
		}
	}

	public static void main(String[] args) {
		String subAmTime = Util.getDateTime("yyyy-MM-dd") + " 19:00:00";
		String subPmTime = Util.getDateTime("yyyy-MM-dd") + " 20:00:00";
		Date subAmDate = Util.StringToDate(subAmTime, "yyyy-MM-dd HH:mm:ss");
		Date subPmDate = Util.StringToDate(subPmTime, "yyyy-MM-dd HH:mm:ss");
		System.out.println(new Date().after(subAmDate));
		System.out.println(new Date().before(subPmDate));
	}

	/**
	 * 根据区块id查询所有已领取记录
	 * 
	 * @param id
	 *            区块id
	 * @return
	 */
	@Override
	public Object[] findAllReceiveBlock(Integer id, String card, int pageNo,
			int pageSize) {
		String hql = "from BlockReceive where  blockId=? ";
		if (card != null && card.length() > 0) {
			hql += " and card ='" + card + "'";
		}
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, id);
		int count = totalDao.getCount(hql, id);
		Object[] o = { list, count };
		return o;
	}

	/***
	 * 删除区块
	 * 
	 * @param block
	 */
	@Override
	public void deletBlock(Block block) {
		if (block != null) {
			String hql = "delete BlockReceive where blockId=?";
			totalDao.createQueryUpdate(hql, null, block.getId());
			totalDao.delete(block);
		}
	}

	/***
	 * 删除区块用户
	 * 
	 * @param blockUserId
	 *            用户id
	 * @return
	 */
	@Override
	public void delBlockUsers(Integer blockUserId) {
		BlockUsers blockUser = (BlockUsers) totalDao.getObjectById(
				BlockUsers.class, blockUserId);
		if (blockUser != null) {
			totalDao.delete(blockUser);
		}
	}
}
