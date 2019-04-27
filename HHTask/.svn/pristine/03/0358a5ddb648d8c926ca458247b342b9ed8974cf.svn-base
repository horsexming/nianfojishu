package com.task.action.sop;

import java.util.Date;
import java.util.List;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.BlockServer;
import com.task.entity.sop.Block;
import com.task.entity.sop.BlockUsers;
import com.task.util.MKUtil;
import com.task.util.Util;

/**
 * 现场区块管理Action层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings({"serial","unchecked"})
public class BlockAction extends ActionSupport {

	private BlockServer blockServer;// Server层
	private Block block;// 对象
	private BlockUsers blockUsers;// 对象
	private List<Block> blockList;// 集合
	private List<Object> list;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer id1;// id
	private String pageStatus;// 页面状态
	private String blockStatus;// 区块类别

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String card;// 卡号

	/****
	 * 添加区块
	 * 
	 * @return
	 */
	public String addBlok() {
		try {
			blockServer.addBlock(block);
			errorMessage = "添加成功!";
			MKUtil.writeJSON(true, errorMessage, block);
		} catch (Exception e) {
			errorMessage = "添加失败!";
			MKUtil.writeJSON(false, errorMessage, block);
		}
		return null;
	}

	/****
	 * 查询区块
	 * 
	 * @return
	 */
	public String findBlockByCondition() {
		if (block != null) { 
			ActionContext.getContext().getSession().put("block", block);
		} else {
			block = (Block) ActionContext.getContext().getSession()
					.get("block");
		}
		Object[] object = blockServer.findBlockByCondition(block, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			blockList = (List<Block>) object[0];
			if (blockList != null && blockList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("BlockAction_findBlockByCondition.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "block_manage";//boclk_manage.jsp
	}

	/***
	 * 查询所有区块
	 */
	public String findAllBlock() {
		blockList = blockServer.findAllBlock(blockStatus);
		if (pageStatus != null && "add".equals(pageStatus)) {
			return "block_add";
		} else {
			return "block_showAll";
		}
	}

	/****
	 * 根据id查询区块
	 * 
	 * @param id
	 * @return
	 */
	public String findBlock() {
		block = blockServer.findBlockById(id);
		if (block != null) {
			list = blockServer.findBlockUsersById(id);
			return "boclk_fenpei";
		} else {
			errorMessage = "不存在您要查看的区块";
			return ERROR;
		}
	}

	/***
	 * 
	 *分配人员
	 * 
	 * @return
	 */
	public String fenpeiUser() {
		Block oldBlock = blockServer.findBlockById(block.getId());
		if (oldBlock != null) {
			try {
				blockUsers.setBlock(oldBlock);
				blockServer.addBlockUser(blockUsers);// 添加用户
				oldBlock.setUserCount(oldBlock.getUserCount() == null ? 0
						: oldBlock.getUserCount() + 1);// 增加用户数量
				blockServer.updateBlock(oldBlock);
				errorMessage = "分配成功!";
				url = "BlockAction_findBlock.action?id=" + block.getId();
			} catch (Exception e) {
				errorMessage = "分配失败!";
			}
		}
		return ERROR;
	}

	/***
	 * 通过卡号查询区块信息
	 * 
	 * @param card
	 * @return
	 */
	public String findUserBlock() {
		blockList = blockServer.findUserBlock(card);
		return "boclk_Receive";
	}

	/***
	 * 领取区块
	 * 
	 * @return
	 */
	public String receiveBlock() {
		try {
			blockServer.receiveBlock(id, card);
			errorMessage = "领取成功!";
			url = "BlockAction_findUserBlock.action?card=" + card;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "领取失败!";
		}
		return ERROR;
	}

	/***
	 * 提交区块
	 * 
	 * @return
	 */
	public String sendBlock() {
		try {// 判断该卡号是否领取过
			// BlockReceive blockReceive = blockServer.findReceBlockHis(id,
			// card);
			// if (blockReceive != null) {
			// if (blockReceive.getStatus().equals("已领取")) {
			String subAmTime = Util.getDateTime("yyyy-MM-dd") + " 11:00:00";
			String subAmPmTime = Util.getDateTime("yyyy-MM-dd") + " 13:00:00";
			String subPmTime = Util.getDateTime("yyyy-MM-dd") + " 15:00:00";
			Date subAmDate = Util
					.StringToDate(subAmTime, "yyyy-MM-dd HH:mm:ss");
			Date subAmPmDate = Util.StringToDate(subAmPmTime,
					"yyyy-MM-dd HH:mm:ss");
			Date subPmDate = Util
					.StringToDate(subPmTime, "yyyy-MM-dd HH:mm:ss");
			if (new Date().before(subAmDate)) {
				errorMessage = "请在11:00之后提交工作!谢谢";
				return ERROR;
			} else if (new Date().before(subPmDate)
					&& subAmPmDate.before(new Date())) {
				errorMessage = "请在15:00之后提交工作!谢谢";
				return ERROR;
			}

			blockServer.sendBlock(id, card);
			errorMessage = "提交成功!";
			url = "BlockAction_findUserBlock.action?card=" + card;
			// } else {
			// errorMessage = "该区块的打扫任务已经提交,无需重复提交!谢谢!";
			// }
			// } else {
			// errorMessage = "您今天尚未领取过该区块,您需先领取该区块!";
			// }

		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "提交失败!";
		}
		return ERROR;
	}
	/***
	 * 提交区块
	 * 
	 * @return
	 */
	public String qrBlock() {
		try {
			blockServer.qrBlock(id,id1);
			errorMessage = "确认成功!";
			url = "BlockAction_findAllReceiveBlock.action?id=" + id1 + "&cpage="+cpage+"&total="+total;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "确认失败!";
		}
		return ERROR;
	}

	/**
	 * 根据区块id查询所有领取记录
	 * 
	 * @return
	 */
	public String findAllReceiveBlock() {
		Object[] object = blockServer.findAllReceiveBlock(id, card, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			list = (List<Object>) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				String url = "";
				if (card != null && card.length() > 0) {
					this.setUrl("BlockAction_findAllReceiveBlock.action?id=" + id
							+ "&card=" + card);
				} else {
					this.setUrl("BlockAction_findAllReceiveBlock.action?id=" + id);
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "boclk_ReceiveList";
	}

	/***
	 * 删除区块
	 * 
	 * @param block
	 */
	public String deletBlock() {
		block = blockServer.findBlockById(id);
		try {
			blockServer.deletBlock(block);
			errorMessage = "删除成功!";
			url = "BlockAction_findBlockByCondition.action?block.blockName=";
		} catch (Exception e) {
			errorMessage = "删除失败!";
		}
		return ERROR;
	}

	/***
	 * 区块移动
	 * 
	 * @return
	 */
	public String moveBlock() {
		Block oldBlock = blockServer.findBlockById(id);
		try {
			oldBlock.setTopDistance(block.getTopDistance());
			oldBlock.setLeftDistance(block.getLeftDistance());
			oldBlock.setWidth(block.getWidth());
			oldBlock.setHight(block.getHight());
			blockServer.updateBlock(oldBlock);
			MKUtil.writeJSON(true, "", null);
		} catch (Exception e) {
			errorMessage = "移动失败!";
			MKUtil.writeJSON(false, errorMessage, null);
		}
		return null;
	}

	/**
	 * 删除区块用户
	 * 
	 * @return
	 */
	public String delBlockUser() {
		try {
			blockServer.delBlockUsers(id);
			errorMessage = "删除成功!";
			url = "BlockAction_findBlock.action?id=" + block.getId();
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "删除失败!";
		}
		return ERROR;
	}

	public BlockServer getBlockServer() {
		return blockServer;
	}

	public void setBlockServer(BlockServer blockServer) {
		this.blockServer = blockServer;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public List<Block> getBlockList() {
		return blockList;
	}

	public void setBlockList(List<Block> blockList) {
		this.blockList = blockList;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public BlockUsers getBlockUsers() {
		return blockUsers;
	}

	public void setBlockUsers(BlockUsers blockUsers) {
		this.blockUsers = blockUsers;
	}

	// 构造方法

}
