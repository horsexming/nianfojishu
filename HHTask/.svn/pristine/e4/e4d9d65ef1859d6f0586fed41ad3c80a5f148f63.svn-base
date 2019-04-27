package com.task.Server.sop;

import java.util.List;

import com.task.entity.sop.FuLiaoZhuiSu;
import com.task.entity.sop.Procard;

public interface FuLiaoZhuiSuServer {
	/**
	 * 添加
	 * @param flzs
	 * @return
	 */
	String	addflzs(FuLiaoZhuiSu flzs);
	/**
	 * 查询所有辅料追溯
	 * @param flzs
	 * @param pageNo
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Object[] findAllflzs(FuLiaoZhuiSu flzs,Integer pageNo,Integer pageSize,String pageStatus);
	/**
	 *删除 
	 * @param flzs
	 */
	String	delflzs(FuLiaoZhuiSu flzs);
	/**
	 * 
	 * @param orderNum
	 */
	List<String> getywmarkIds(String orderNum);
	/**
	 * 根据内部订单和业务件号查询Procard信息
	 * @param orderNum
	 * @param ywmarkId
	 * @return
	 */
	Procard	findProcardBy(String orderNum ,String ywmarkId);
	/**
	 * 根据工位查询获得设备编号
	 */
	String getshebeiNo(String gongwei);
	/**
	 * 根据Id查询flzs信息
	 */
	FuLiaoZhuiSu findflzsById(Integer id);
	/**
	 * 修改辅料追溯
	 */
	String	updateflzs(FuLiaoZhuiSu flzs);
	/**根据件号获得零件名称
	 * getproName
	 */
	String getproName(String markId);
	
}
