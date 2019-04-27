package com.task.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.BoardService;
import com.task.Server.bbs.ScrnServer;
import com.task.entity.Users;
import com.task.entity.Vboard;
import com.task.entity.bbs.Scrn;
import com.task.util.MKUtil;

public class BoardAction extends ActionSupport {
	
	private Vboard board;
	private List<Vboard> boards;
	private BoardService boardService;
	private ScrnServer scrnServer;
	
	private String errorMessage;
	private String successMessage;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	
	//获得添加标题页面
	public String addTitlePage(){
		return "addTitlePage";
	}
	//添加标题
	public String addTitle(){
		try {
			Users user=(Users) ActionContext.getContext().getSession().get(TotalDao.users);// 获得登录用户信息
			board.setCreateUserId(user.getId());
			board.setCreateDate(new Date());
			boardService.add(board);
			MKUtil.writeJSON(true, "添加成功", board);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加失败", null);
			e.printStackTrace();
		}
		return null;
	}
	//删除标题
	public String deleteTitle(){
		try {
			board = boardService.get(board.getId());
			boardService.delete(board.getId());
			MKUtil.writeJSON(true, "删除成功", board);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败", board);
			e.printStackTrace();
		}
		return null;
	}
	//过得更新标题页面
	public String updateTitlePage(){
		board = boardService.get(board.getId());
		return "getUpdateTitlePage";
	}
	//更新标题
	public String updateTitle(){
		try {
			boardService.update(board);
			MKUtil.writeJSON(true, "修改成功", board);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "修改失败", null);
		}
		return null;
	}
	
	//获得标题集合
	public String findTitleAllByscrnId(){
		Integer boardId=this.board.getScrnId();
		Map map=new HashMap();
		Object[] object = boardService.findTitleAllByscrnId(this.board,Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			boards = (List<Vboard>) object[0];
			if (boards != null && boards.size() > 0) {
				for(Vboard vboard: boards){
					Scrn scrn=scrnServer.getScrnById(vboard.getScrnId());
					if(scrn!=null){
						vboard.setScrnName(scrn.getName());
					}
				}
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("board_findTitleAllByscrnId.action?board.scrnId="+boardId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "listTitle";
	}
	
	//获得发布内容
	public String addPage(){
		return "addPage";
	}
	//添加发布内容
	public String add(){
		try {
			Users user=(Users) ActionContext.getContext().getSession().get(TotalDao.users);// 获得登录用户信息
			board.setCreateUserId(user.getId());
			board.setCreateDate(new Date());
			boardService.add(board);
			//return "add_success";
			MKUtil.writeJSON(true, "添加成功", board);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "添加失败", null);
			e.printStackTrace();
		}
		return null;
	}
	//获得发布内容
	public String get(){
		board = boardService.get(board.getId());
		return "get";
	}
	
	//发布内容页面
	public String updatePage(){
		board = boardService.get(board.getId());
		board.setContent(htmlspecialchars(board.getContent()));
		return "updatePage";
	}
	
	//更新发布内容
	public String update(){
		try {
			boardService.update(board);
			//return "update_success";
			MKUtil.writeJSON(true, "修改成功", board);
		} catch (Exception e) {
			e.printStackTrace();
			//return "update_failure";
			MKUtil.writeJSON(false, "修改失败", null);
		}
		return null;
	}
	//删除发布内容
	public String delete(){
		try {
			board = boardService.get(board.getId());
			boardService.delete(board.getId());
			MKUtil.writeJSON(true, "删除成功", board);
			//return "delete_success";
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败", board);
			e.printStackTrace();
			//return "delete_failure";
		}
		return null;
	}
	
	public String list(){
		boards = boardService.list();
		for(Vboard vboard: boards){
			Scrn scrn=scrnServer.getScrnById(vboard.getScrnId());
			if(scrn!=null){
				vboard.setScrnName(scrn.getName());
			}
		}
		return "list";
		
	}
	
	public String findBoardAllByparentId(){
		Integer parentId=this.board.getScrnId();
		Map map=new HashMap();
		Object[] object = boardService.findBoardAllByparentId(this.board,Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			boards = (List<Vboard>) object[0];
			if (boards != null && boards.size() > 0) {
				for(Vboard vboard: boards){
					Scrn scrn=scrnServer.getScrnById(vboard.getScrnId());
					if(scrn!=null){
						vboard.setScrnName(scrn.getName());
					}
				}
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("board_findBoardAllByparentId.action?board.parentId="+parentId);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "list";
	}
	
	private String htmlspecialchars(String str) {
		if(str == null){
			return "";
		}
		return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;");
	}
	
	public BoardService getBoardService() {
		return boardService;
	}

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	public Vboard getBoard() {
		return board;
	}

	public void setBoard(Vboard board) {
		this.board = board;
	}

	public List<Vboard> getBoards() {
		return boards;
	}

	public void setBoards(List<Vboard> boards) {
		this.boards = boards;
	}

	public ScrnServer getScrnServer() {
		return scrnServer;
	}

	public void setScrnServer(ScrnServer scrnServer) {
		this.scrnServer = scrnServer;
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
}
