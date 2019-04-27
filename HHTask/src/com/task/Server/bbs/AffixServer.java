package com.task.Server.bbs;


import java.util.Map;

import com.task.entity.bbs.Affix;



public interface AffixServer {
	public Affix addAffix(Affix affix);//添加附件记录
	
	public String deleteAffix(Affix affix);//删除附件记录
	
	public String updateAffix(Affix affix);//更新附件记录
	
	public Affix getAffixById(Integer id);//获得附件记录
	
	public Object[] findAllAffix(Map map ,int  pageNo,int pageSize);//获得附件记录集合
}
