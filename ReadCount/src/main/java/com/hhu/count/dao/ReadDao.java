package com.hhu.count.dao;

import java.util.List;
import java.util.Map;

import com.hhu.count.entity.MyRead;
import com.hhu.count.entity.OldRead;
import com.hhu.count.vo.CountVo;
import com.hhu.count.vo.Countmingxi;
import com.hhu.count.vo.OldReadVO;

public interface ReadDao {
	//添加到我的阅读
	public void InsertRead(MyRead read) ;
	//通过用户ID查询阅读记录
	public List<MyRead> selectByUserID(String readerID);
	//通过用户名和课程id查询阅读记录
	public MyRead selectByUnameAndCid(Map<String,String>map);
	//设置我的阅读量
	public void setMyReading(Map<String,String>map);
	//删除我的阅读记录
	public void delFromMRByID(String id);
	//阅读按天统计
	public List<CountVo>CountByDay(String readerID);
	public List<Countmingxi>Countmingxi1(Map<String,Object>map);
	//阅读按月统计
	public List<CountVo>CountByMonth(String readerID);
	public List<Countmingxi>Countmingxito(Map map);
	//阅读按年统计
	public List<CountVo>CountByYear(String readerID);
	public List<Countmingxi>Countmingxi3(Map<String,Object>map);
	//查询阅读记录
	public List<MyRead> selectReadBYCID(Map<String, String>map);
	//删除阅读记录
	public void DelMyreads(String courseID);
	//批量删除阅读记录
	//public void piliangDels(Map<String, Object>map);
	//通过id查询阅读数 
	public MyRead selMyReadById(String id);
	//取消阅读权限
	public void powerMyread(String courseID);
	//设置阅读权限
	public void shezhiMyread(Map<String, String>map);
	//查询历史记录
	public List<OldRead>selOldRead(String myreaderId);
	public void InsOldRead(OldRead oldRead);
	public void SetOldRead(Map<String,String>map);
	public List<OldReadVO>selByMRId(String myreaderId);
	//设置首次绑定时间
	public void setFtBulid(Map<String, String>map);
	//年度图表
	public Integer Tubiao(Map map);
	
}
