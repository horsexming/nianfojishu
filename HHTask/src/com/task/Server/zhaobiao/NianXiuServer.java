package com.task.Server.zhaobiao;

import java.util.Date;
import java.util.List;

import com.task.entity.Users;
import com.tast.entity.zhaobiao.KaoQin;
import com.tast.entity.zhaobiao.NianXiu;

public interface NianXiuServer {
	/*
	 * 分页查询年休表
	 * 
	 */
	Object[] listnianxiu(KaoQin kaoQin, Integer parseInt, Integer pageSize);

	List Byyufen(String yuefen);
	
	String updateKaoqin(KaoQin kaoQin);

	void addKaoqin(String yuefen);

	void addKaoqin_1(String yuefen);
	void addKaoqin_2(String yuefen);
	void exportExcel(String yuefen);

	KaoQin ById(Integer id);

	/**
	 * 删除
	 * @param kaoQin
	 * @return
	 */
	String shanchuById(KaoQin kaoQin);

	/**
	 * 汇总某个人的月报
	 * @param yuefen
	 * @param listDate
	 * @param users
	 */
	void onePeople(String yuefen, int listDate, Users users);

	/**
	 * 更新单个
	 * @param kaoQin
	 * @return
	 */
	String jiSuanById(KaoQin kaoQin);
	
}
