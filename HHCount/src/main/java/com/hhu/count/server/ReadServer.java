package com.hhu.count.server;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hhu.count.entity.MyRead;
import com.hhu.count.entity.OldRead;
import com.hhu.count.vo.CountVo;
import com.hhu.count.vo.Countmingxi;
import com.hhu.count.vo.OldReadVO;

public interface ReadServer {
	//添加到我的阅读
	public void InsertRead(String id,String readername,String readerID,String coursename);
	//通过用户ID查询阅读记录
	public List<MyRead> selectByUserID(String readerID);
	//通过用户名和课程id查询阅读记录
	public MyRead selectByUnameAndCid(String username,String id);
	//设置我的阅读量
	public void setMyReading(String reading,String id);
	//删除我的阅读记录
	public void delFromMRByID(String id);
	//阅读按天统计
	public List<CountVo>CountByDay(String id);
	public List<Countmingxi>Countmingxi1(String id,String MyrDate);
	//阅读按月统计
	public List<CountVo>CountByMonth(String readerID);
	public List<Countmingxi>Countmingxito(Map map);
	//阅读按年统计
	public List<CountVo>CountByYear(String readerID);
	public List<Countmingxi>Countmingxi3(Map map);
	//查询阅读记录
	public boolean selectReadBYCID(String readerID,String courseID);
	//删除阅读记录
	public void DelMyreads(String courseID);
	//批量删除阅读记录
	//public void piliangDels(String []id);
	//通过id查询阅读数 
	public MyRead selMyReadById(String id);
	//取消阅读权限
	public void powerMyread(String courseID);
	//设置阅读权限
	public void shezhiMyread(String courseID, String readername);
	//查询历史记录
	public List<OldRead>selOldRead(String myreaderId);
	public void InsOldRead(String myreaderId,Date readerDate);
	public void SetOldRead(String id ,String oldReading);
	public void InsOldRead2(String myreaderId,Date readerDate,String OldReading);
	public List<OldReadVO>selByMRId(String myreaderId);
	//设置首次绑定时间
	public void setFtBulid(Map<String,String>map);

}
