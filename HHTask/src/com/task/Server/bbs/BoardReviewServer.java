package com.task.Server.bbs;


import java.util.List;
import java.util.Map;

import com.task.entity.bbs.BoardReview;



public interface BoardReviewServer {
	public BoardReview addBoardReview(BoardReview boardReview);//添加评论记录
	
	public String deleteBoardReview(BoardReview boardReview);//删除评论记录
	
	public String updateBoardReview(BoardReview boardReview);//更新评论记录
	
	public BoardReview getBoardReviewById(Integer id);//获得评论记录
	
	public Object[] findAllBoardReviewByboardId(Map map ,int  pageNo,int pageSize);//获得评论记录集合
	
	public List<BoardReview>findAllBoardReviewByparenId(Integer parentId);//根据父ID获得记录集合
	
	public List<BoardReview>findAllBoardReviewByboardId(Integer boardId);//根据boardID获得记录集合
}
