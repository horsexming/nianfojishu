package com.task.action.bbs;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.task.Dao.TotalDao;
import com.task.Server.BoardService;
import com.task.Server.UserServer;

import com.task.Server.bbs.BoardReviewServer;

import com.task.entity.Users;
import com.task.entity.Vboard;

import com.task.entity.bbs.BoardReview;
import com.task.util.MKUtil;

/***
 * 留言滚动看板
 * 
 * @author 陈曦
 * 
 */
public class BoardReviewAction extends ActionSupport {
	private BoardReview boardReview;
	private List<BoardReview> boardReviewList;

	private Vboard board;
	private List<Vboard> boardList;

	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private int count;
	private Date lastBoardReviewDate;

	private BoardReviewServer boardReviewServer;
	private BoardService boardServer;
	private UserServer userServer;

	public String getBoardReviewPage() {
		findAllBoardReviewByboardId();
		this.board = boardServer.get(boardReview.getBoardId());
		return "getBoard_success";
	}

	public String getBoardReviewShowAllPage() {
		// findAllBoardReview();
		findAllBoardReviewByscrnId();
		return "getBoardReviewShowAllPage_success";
	}

	// 获得评论添加
	public String getBoardReviewAddPage() {
		return "getBoardReviewAddPage_success";
	}

	// 添加评论记录
	public String addBoardReview() {
		try {
			Users user = (Users) ActionContext.getContext().getSession().get(
					TotalDao.users);
			this.boardReview.setCreateUserId(user.getId());
			this.boardReview.setCreateDate(new Date());
			BoardReview result = boardReviewServer
					.addBoardReview(this.boardReview);
			result.setContent(result.getContent().replaceAll("\\s", " "));
			if (result != null) {
				user.setPassword(null);
				user.setWorklogClass(null);
				user.setModuleFunction(null);
				result.setCreateUser(user);
				MKUtil.writeJSON(true, "操作成功", result);
				// return "addBoardReview_success";
			} else {
				MKUtil.writeJSON(false, "操作失败", null);
				// return "addBoardReview_failure";
			}
		} catch (Exception e) {
			MKUtil.writeJSON(false, "操作失败", null);
			// return "addBoardReview_failure";
		}
		return null;
	};

	// 删除评论记录
	public String deleteBoardReview() {
		try {
			String result = boardReviewServer
					.deleteBoardReview(this.boardReview);
			if ("true".equals(result)) {
				return "deleteBoardReview_success";
			} else {
				return "deleteBoardReview_failure";
			}
		} catch (Exception e) {
			return "deleteBoardReview_failure";
		}
	};

	// 获得更新评论
	public String getBoardReviewUpdatePage() {
		this.boardReview = boardReviewServer
				.getBoardReviewById(this.boardReview.getId());
		return "getBoardReviewUpdatePage_success";
	}

	// 更新评论记录
	public String updateBoardReview() {
		try {
			BoardReview BoardReview = boardReviewServer
					.getBoardReviewById(this.boardReview.getId());
			String result = boardReviewServer.updateBoardReview(boardReview);
			if ("true".equals(result)) {
				return "updateBoardReview_success";
			} else {
				return "updateBoardReview_failure";
			}
		} catch (Exception e) {
			return "updateBoardReview_failure";
		}
	};

	// 获得评论记录
	public String getBoardReviewById() {
		this.boardReview = boardReviewServer
				.getBoardReviewById(this.boardReview.getId());
		return null;
	};

	// 获得评论记录集合
	public String findAllBoardReviewByboardId() {
		Map map = new HashMap();
		Integer boardId = this.boardReview.getBoardId();
		map.put("boardId", boardId);
		Object[] object = boardReviewServer.findAllBoardReviewByboardId(map,
				Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			boardReviewList = (List<BoardReview>) object[0];
			if (boardReviewList != null && boardReviewList.size() > 0) {
				int size = boardReviewList.size();
				for (int i = 0; i < size; i++) {
					BoardReview boardReview = this.boardReviewList.get(i);
					Users user = userServer.findUserById(boardReview
							.getCreateUserId());
					boardReview.setCreateUser(user);
					List<BoardReview> subBoardReviews = boardReviewServer
							.findAllBoardReviewByparenId(boardReview.getId());
					if (subBoardReviews != null) {
						int subSize = subBoardReviews.size();
						for (int j = 0; j < subSize; j++) {
							BoardReview subBoardReview = subBoardReviews.get(j);
							Users subUser = userServer
									.findUserById(subBoardReview
											.getCreateUserId());
							subBoardReview.setCreateUser(subUser);
						}
						boardReview.setSubBoardReviewList(subBoardReviews);
					}
					if (i == 0) {
						lastBoardReviewDate = boardReview.getCreateDate();
					}
				}
				count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("boardReviewAction!getBoardReviewPage.action?boardReview.boardId="
								+ boardId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return null;
		// return "findAllBoardReviewByboardId_success";
	}

	public String findAllBoardReview() {
		List<Vboard> boardList = boardServer.list();
		if (boardList != null) {
			int size = boardList.size();
			for (int i = 0; i < size; i++) {
				Vboard board = boardList.get(i);
				List<BoardReview> boardReviewList = boardReviewServer
						.findAllBoardReviewByboardId(board.getId());
				if (boardReviewList != null) {
					int boardReviewListSize = boardReviewList.size();
					for (int j = 0; j < boardReviewListSize; j++) {
						BoardReview boardReview = boardReviewList.get(j);
						Users user = userServer.findUserById(boardReview
								.getCreateUserId());
						boardReview.setCreateUser(user);
						List<BoardReview> subBoardReviews = boardReviewServer
								.findAllBoardReviewByparenId(boardReview
										.getId());
						if (subBoardReviews != null) {
							int subSize = subBoardReviews.size();
							for (int k = 0; k < subSize; k++) {
								BoardReview subBoardReview = subBoardReviews
										.get(k);
								Users subUser = userServer
										.findUserById(subBoardReview
												.getCreateUserId());
								subBoardReview.setCreateUser(subUser);
							}
							boardReview.setSubBoardReviewList(subBoardReviews);
						}
					}
					board.setBoardReviewList(boardReviewList);
				}
			}
		}
		this.boardList = boardList;
		return null;
	}

	// 根据屏幕ID查询所有发布内容
	public String findAllBoardReviewByscrnId() {
		List<Vboard> titleList = (List<Vboard>) boardServer
				.findTitleAllByscrnId(this.board.getScrnId())[0];
		if (titleList != null) {
			int size = titleList.size();
			for (int i = 0; i < size; i++) {
				Vboard title = titleList.get(i);
				List<Vboard> boardList = (List<Vboard>) boardServer
						.findBoardAllByparentId(title.getId())[0];
				if (boardList != null) {
					int boardSize = boardList.size();
					for (int m = 0; m < boardSize; m++) {
						Vboard subBoard = boardList.get(m);
						List<BoardReview> boardReviewList = boardReviewServer
								.findAllBoardReviewByboardId(subBoard.getId());
						if (boardReviewList != null) {
							int boardReviewListSize = boardReviewList.size();
							for (int j = 0; j < boardReviewListSize; j++) {
								BoardReview boardReview = boardReviewList
										.get(j);
								Users user = userServer
										.findUserById(boardReview
												.getCreateUserId());
								boardReview.setCreateUser(user);
								List<BoardReview> subBoardReviews = boardReviewServer
										.findAllBoardReviewByparenId(boardReview
												.getId());
								if (subBoardReviews != null) {
									int subSize = subBoardReviews.size();
									for (int k = 0; k < subSize; k++) {
										BoardReview subBoardReview = subBoardReviews
												.get(k);
										Users subUser = userServer
												.findUserById(subBoardReview
														.getCreateUserId());
										subBoardReview.setCreateUser(subUser);
									}
									boardReview
											.setSubBoardReviewList(subBoardReviews);
								}
							}
							subBoard.setBoardReviewList(boardReviewList);
						}
					}
					title.setBoardList(boardList);
				}
			}
		}
		this.boardList = titleList;
		return null;
	}

	public BoardReview getBoardReview() {
		return boardReview;
	}

	public void setBoardReview(BoardReview boardReview) {
		this.boardReview = boardReview;
	}

	public List<BoardReview> getBoardReviewList() {
		return boardReviewList;
	}

	public void setBoardReviewList(List<BoardReview> boardReviewList) {
		this.boardReviewList = boardReviewList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
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

	public BoardReviewServer getBoardReviewServer() {
		return boardReviewServer;
	}

	public void setBoardReviewServer(BoardReviewServer boardReviewServer) {
		this.boardReviewServer = boardReviewServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public void setBoard(Vboard board) {
		this.board = board;
	}

	public BoardService getBoardServer() {
		return boardServer;
	}

	public void setBoardServer(BoardService boardServer) {
		this.boardServer = boardServer;
	}

	public Vboard getBoard() {
		return board;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getLastBoardReviewDate() {
		return lastBoardReviewDate;
	}

	public void setLastBoardReviewDate(Date lastBoardReviewDate) {
		this.lastBoardReviewDate = lastBoardReviewDate;
	}

	public List<Vboard> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<Vboard> boardList) {
		this.boardList = boardList;
	}

}
