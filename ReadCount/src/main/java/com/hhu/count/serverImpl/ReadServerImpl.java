package com.hhu.count.serverImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhu.count.dao.CourseDao;
import com.hhu.count.dao.ReadDao;
import com.hhu.count.entity.MyRead;
import com.hhu.count.entity.OldRead;
import com.hhu.count.server.ReadServer;
import com.hhu.count.vo.CountVo;
import com.hhu.count.vo.Countmingxi;
import com.hhu.count.vo.OldReadVO;

@Service("readServer")
public class ReadServerImpl implements ReadServer {

	@Autowired 
	ReadDao readDao;
	@Autowired 
	CourseDao coueseDao;
	//添加到我的阅读;
	public void InsertRead(String id,String readername,String readerID,String coursename) {
		MyRead read = new MyRead();
		read.setId(UUID.randomUUID().toString());
		read.setCourseID(id);
		read.setReadername(readername);
		read.setReading("0");
		read.setCoursename(coursename);
		read.setReaderID(readerID);
		read.setMyrDate(new Date());
		readDao.InsertRead(read);
	}
	
	//通过用户ID查询阅读记录
	public List<MyRead> selectByUserID(String readerID){		
		return readDao.selectByUserID(readerID);
	}
	
	//通过用户名和课程id查询阅读记录
	public MyRead selectByUnameAndCid(String username,String id) {
		Map<String, String>map = new HashMap<>();
		map.put("courseID",id);
		map.put("readername", username);
		return readDao.selectByUnameAndCid(map);
	}
	
	//设置我的阅读量
	public void setMyReading(String reading,String id) {
		Map<String, String>map = new HashMap<>();
		map.put("reading",reading);
		map.put("id", id);
		readDao.setMyReading(map);	
	}
	
	//删除我的阅读记录
	public void delFromMRByID(String id) {
		readDao.delFromMRByID(id);
	}
	
	//阅读按天统计
	public List<CountVo>CountByDay(String id){	
		return readDao.CountByDay(id);
	}
	public List<Countmingxi>Countmingxi1(String id,String MyrDate){
		Map<String, Object>map = new HashMap<>();
		map.put("readerID", id);
		map.put("MyrDate", MyrDate);
		return readDao.Countmingxi1(map);
	}
	//阅读按月统计
	public List<CountVo>CountByMonth(String readerID){
		
		return readDao.CountByMonth(readerID);
	}
	public List<Countmingxi>Countmingxito(Map map){
		return readDao.Countmingxito(map);
	}
	//阅读按年统计
	public List<CountVo>CountByYear(String readerID){
		
		return readDao.CountByYear(readerID);
	}
	public List<Countmingxi>Countmingxi3(Map map){
		return readDao.Countmingxi3(map);
	}
	
	//查询阅读记录
	public boolean selectReadBYCID(String readerID,String courseID){
		Map<String, String> map = new HashMap<>();
		map.put("readerID", readerID);
		map.put("courseID", courseID);
		if(readDao.selectReadBYCID(map).size()==0) {			
			return true;
		}
		return false;
	}
	//删除阅读记录
	public void DelMyreads(String courseID) {
		readDao.DelMyreads(courseID);	
	}
	//通过id查询阅读数 
	public MyRead selMyReadById(String id) {	
		return readDao.selMyReadById(id);
	}
	//批量删除阅读记录
	/*public void piliangDels(String []id) {
		Map<String,Object> map = new HashMap<>();
		map.put("ids", id);
		readDao.piliangDels(map);
	}*/
	//取消阅读权限
	public void powerMyread(String courseID) {
		readDao.powerMyread(courseID);
	}
	//设置阅读权限
	public void shezhiMyread(String courseID, String readername) {
		Map<String, String>map = new HashMap<>();
		map.put("courseID", courseID);
		map.put("readername", readername);		
		if(coueseDao.selectByID(courseID).getState().equals("1")) {
			if(readDao.selectByUnameAndCid(map).getBuildFirst()==null) {
				setFtBulid(map);
			}	
		}		
		readDao.shezhiMyread(map);	
	}
	//查询历史记录
	public List<OldRead>selOldRead(String myreaderId){
		return readDao.selOldRead(myreaderId);	
	}
	public void InsOldRead(String myreaderId,Date readerDate) {
		OldRead oldRead = new OldRead();
		oldRead.setId(UUID.randomUUID().toString());
		oldRead.setMyreaderId(myreaderId);
		oldRead.setReaderDate(readerDate);
		oldRead.setOldReading("1");
		readDao.InsOldRead(oldRead);
	}
	public void SetOldRead(String id ,String oldReading) {
		Map<String, String>map = new HashMap<>();
		map.put("id", id);
		map.put("oldReading", oldReading);
		readDao.SetOldRead(map);
		
	}
	
	public void InsOldRead2(String myreaderId,Date readerDate,String OldReading) {
		OldRead oldRead = new OldRead();
		oldRead.setId(UUID.randomUUID().toString());
		oldRead.setMyreaderId(myreaderId);
		oldRead.setReaderDate(readerDate);
		oldRead.setOldReading(OldReading);
		readDao.InsOldRead(oldRead);
	}
	public List<OldReadVO>selByMRId(String myreaderId){
		return readDao.selByMRId(myreaderId);
	}
	
	//设置首次绑定时间
	public void setFtBulid(Map<String,String>map) {
		readDao.setFtBulid(map);
	}
	//年度图表
	public Integer Tubiao(String readerID,String readerDate) {
		Map map = new HashMap<>();
		map.put("readerID", readerID);
		map.put("readerDate", readerDate);
		return readDao.Tubiao(map);
	}

}
