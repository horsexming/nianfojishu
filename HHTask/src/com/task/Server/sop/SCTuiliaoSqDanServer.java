package com.task.Server.sop;

import java.util.List;

import com.task.entity.sop.Procard;
import com.task.entity.sop.SCTuiliaoSqDan;

public interface SCTuiliaoSqDanServer {
	/**
	 * 添加生产退料申请单
	 * @param sqd
	 * @return
	 */
	boolean addSctuiliaoSqDan(SCTuiliaoSqDan sqd);
	/**
	 * 查询所有生产退料申请单
	 * @param sqd
	 * @param pageNo
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	 Object[] findAllSctuiliaoSqDan(SCTuiliaoSqDan sqd ,int pageNo,int pageSize,String pageStatus);
	 /**
	  * 根据id查询退料申请单
	  * @param id
	  * @return
	  */
	 SCTuiliaoSqDan findSctuiliaoSqDanById(Integer id);
	 /**
	  * 修改生产退料申请单
	  * @param sqd
	  * @return
	  */
	 boolean updateSctuiliaoSqDan(SCTuiliaoSqDan sqd);
	 /**
	  * 删除生产退料申请单
	  * @param sqd
	  * @return
	  */
	 boolean delSctuiliaoSqDan(SCTuiliaoSqDan sqd);
	 /**
	  * 根据件号 生产批次查询procard信息
	  */
	 Procard findProcardOne(String markId,String selfCard);
	 /**
	  * 
	  */
	 String addmoresqd(SCTuiliaoSqDan sqd, SCTuiliaoSqDan[] sqdArrays);
}
