package com.task.Server;

import java.util.List;

import com.task.entity.Qexamine;
import com.task.entity.Stylebook;

public interface QexamineServer {
	//添加质量审查表
	public void saveQexamine(Qexamine qexamine);
	//修改质量审查表
	public void updateQexamine(Qexamine qexamine);
	//删除质量审查表
	public void deleteQexamine(Qexamine qexamine);
	//添加样品
	public void saveStyle(Stylebook stylebook);
	//分页查询全部质量审查表列表
	public List qexaminelist(int pageNo, int pageSize);
	public List qexamineFindList();
	//查询质量审查表列表数量
	public Integer qexamineCount();
	//单个质量审查表查询
	public Qexamine qexamineFind(int id);
	//单个样本查询
	public Stylebook stylebookFind(int id);
	//查询单表中全部样本
	public List stylebookList(int qid);
	//删除单个样本
	public void deleteStylebook(Stylebook stylebook);
	//单个样本修改
	public void updateStyleboook(Stylebook stylebook);
	
	
}
