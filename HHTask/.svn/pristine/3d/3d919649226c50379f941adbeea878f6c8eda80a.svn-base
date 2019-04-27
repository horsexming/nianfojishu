package com.task.ServerImpl.bbs;

import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.bbs.BoardReviewServer;
import com.task.entity.bbs.BoardReview;




public class BoardReviewServerImpl implements BoardReviewServer{
	private TotalDao totalDao;
	
	//添加评论记录
	public BoardReview addBoardReview(BoardReview boardReview) {
		boolean result= totalDao.save(boardReview);
		if(result){
			return boardReview;
		}else {
			return null;
		}
	}
	
	//删除评论记录
	public String deleteBoardReview(BoardReview boardReview) {
		boolean result= totalDao.delete(boardReview);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//更新评论记录
	public String updateBoardReview(BoardReview boardReview) {
		boolean result= totalDao.update(boardReview);
		if(result){
			return "true";
		}else {
			return "false";
		}
	}
	
	//获得评论记录
	public BoardReview getBoardReviewById(Integer id) {
		if(id != null){
			return (BoardReview)totalDao.getObjectById(BoardReview.class, id);
		}
		return null;
	}
	
	//获得评论记录集合
	public Object[] findAllBoardReviewByboardId(Map map, int pageNo, int pageSize) {
		String hql = "from BoardReview b where 1 = 1";
		if(map!=null){
			if(map.get("boardId")!=null){
				Integer proId=(Integer) map.get("boardId");
				hql+=" and b.boardId="+proId;
			}
		}
		hql+=" and b.parentId is null";
		hql+=" order by b.createDate desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	public List findAllBoardReviewByparenId(Integer  parentId){
		if(parentId!=null){
			String hql = "from BoardReview b where 1 = 1";
			hql+=" and b.parentId="+parentId;
			hql+=" order by b.createDate asc";
			List list = totalDao.find(hql);
			return list;
		}
		return null;
	}
	public List findAllBoardReviewByboardId(Integer  boardId){
		if(boardId!=null){
			String hql = "from BoardReview b where 1 = 1";
			hql+=" and b.boardId="+boardId;
			hql+=" order by b.createDate desc";
			List list = totalDao.find(hql);
			return list;
		}
		return null;
	}
	
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
