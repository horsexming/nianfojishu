package com.hhu.count.serverImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hhu.count.dao.CourseDao;
import com.hhu.count.dao.ReadDao;
import com.hhu.count.dao.UserDao;
import com.hhu.count.entity.MyRead;
import com.hhu.count.entity.course;
import com.hhu.count.server.CourseServer;
import com.hhu.count.vo.CourseVO;
import com.hhu.count.vo.PoorUserVo;
import com.hhu.count.vo.UserCourseVO;

@Service("courseServer")
public class CourseServerImpl implements CourseServer {

	@Autowired
	CourseDao courseDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ReadDao readDao;
	//课程发布
	public void InsertCourse(course course){	
		course.setId(UUID.randomUUID().toString());
		course.setState("0");
		course.setCourseState("0");
		course.setCreatetime(new Date());
		courseDao.InsertCourse(course);
	}
	
	//通过发布者ID查询课程
	public List<course> selectByWriterId(String writerid){	
		return courseDao.selectByWriterId(writerid);
	}
	//查询所有
	public List<course> selectAll() {		
		return courseDao.selectAll();
	}
	
	//通过发布者ID以及状态查询
	public List<course>selectByState(String writerid,String state){
		Map<String, String>map = new HashMap<>();
		map.put("writerid", writerid);
		map.put("state", state);
		return courseDao.selectByState(map);
	}
	
	//修改课程信息
	public void updateCourse(course course) {
		System.out.println("course"+course.getCoursename());
		System.out.println("course"+course.getId());
		System.out.println("course"+course.getCoursetext());
		System.out.println("course"+course.getCourseRead());
		courseDao.updateCourse(course);		
	}
	//通过ID查询课程信息 
	public course selectByID(String id) {		
		return courseDao.selectByID(id);
	}
	//删除课程
	public void DeleteCourseByID(String id) {
		courseDao.DeleteCourseByID(id);		
	}
	//修改状态
	public void updateCourseState(String id,String state,Date publishTime,Date stopTime) {	
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("courseState","1");
		map.put("state",state);
		map.put("publishTime",publishTime);
		map.put("stopTime",stopTime);
		courseDao.updateCourseState(map);
	}
	//设置首次发布时间
	public void updatefirstTime(String id,Date firstDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("id",id);
		map.put("firstTime",firstDate);
		courseDao.updatefirstTime(map);
	}
	//模糊查询
	public List<course> selectCourseLike(String writerid,String coursename){
		Map<String,String> map = new HashMap<>();
		map.put("writerid", writerid);
		map.put("coursename",coursename);
		System.err.println("course****"+courseDao.selectCourseLike(map).size());
		if(courseDao.selectCourseLike(map)==null) {
			return null;		
		}
		return courseDao.selectCourseLike(map);
	}
	//课程名的模糊查询 
		public List<course> FindCLikeCName(String coursename,String id){
			
			Map<String, String>map = new HashMap<>();
			map.put("coursename", coursename);
			map.put("id", id);
			if(courseDao.FindCLikeCName(map)==null) {
				return null;		
			}
			return courseDao.FindCLikeCName(map);
		}
		//发布者名的模糊查询
		public List<course> FindCLikeWriteName(String writer,String id){
			Map<String, String>map = new HashMap<>();
			map.put("writer", writer);
			map.put("id", id);
			if(courseDao.FindCLikeWriteName(map)==null) {
				return null;		
			}
			return courseDao.FindCLikeWriteName(map);
			
		}
		
		//课程名的模糊查询 没有阅读记录
		public List<course> FindCLikeCName2(String coursename){
			return courseDao.FindCLikeCName2(coursename);			
		}
		//发布者名的模糊查询没有阅读记录
		public List<course> FindCLikeWriteName2(String writer){
			return courseDao.FindCLikeWriteName2(writer);
		}	
		
		//批量发布课程
		public void BatchRelease(String []id) {
			Map<String,Object> map = new HashMap<>();
			map.put("ids", id);															
			map.put("state", "1");
			map.put("courseState","1");
			map.put("publishTime", new Date());
			map.put("stopTime", null);
			courseDao.BatchRelease(map);		
		}
		//批量停止课程
		public void StopCourse(String []id) {
			Map<String,Object> map = new HashMap<>();
			map.put("ids", id);															
			map.put("state", "0");
			map.put("publishTime",null);
			map.put("courseState","1");
			map.put("stopTime", new Date());
			courseDao.BatchRelease(map);	
		}
		//用户首页
		public List<UserCourseVO>selectAllCourse(String id){
			
			return courseDao.selectAllCourse(id);
		}
		//查询最新记录
		public List<course>selectLimitTen(){
			return courseDao.selectLimitTen();
		}
		public List<course>selectLimitTen(String username){
			List<course>list = courseDao.selectLimitTen();
			List<course>list2 = new ArrayList<>();
			for(int i = 0;i<list.size();i++) {
				String uname = list.get(i).getCoursePower();
				if(uname!=null) {
					String []iteam = uname.split(",");
					for(int j = 0;j<iteam.length;j++) {
						if(iteam[j].equals(username)) {
							list2.add(list.get(i));
							continue;
						}	
					}
					
				}
				
			}	
			return list2;
		}
		public List<course>selLimitUser(String username,Integer pn, Integer pagesize){
			List<course>list = courseDao.selLimitUser(pn, pagesize);
			List<course>list2 = new ArrayList<>();
			for(int i = 0;i<list.size();i++) {
				String uname = list.get(i).getCoursePower();
				if(uname!=null) {
					String []iteam = uname.split(",");
					for(int j = 0;j<iteam.length;j++) {
						if(iteam[j].equals(username)) {
							list2.add(list.get(i));
							continue;
						}	
					}
					
				}
				
			}	
			return list2;	
		}
		//课程统计
		public List<CourseVO>SelCourseVO(String writerid){
			
			return courseDao.SelCourseVO(writerid);
		}
		
		//权限绑定
		public void updatePowers(String id,String CoursePower) {
			Map< String, String>map = new HashMap<>();
			map.put("id", id);
			map.put("CoursePower", CoursePower);
			courseDao.updatePowers(map);
		}
		
		public List<course>chaxunceshi(String id ,String username,Integer pn,Integer pagesize){
			List<course>list = courseDao.chaxunceshi(id,pn,pagesize);
			System.out.println(list.size());
			List<course>list2 = new ArrayList<>();
			for(int i = 0;i<list.size();i++) {
				String uname = list.get(i).getCoursePower();
				if(uname!=null) {
					String []iteam = uname.split(",");
					for(int j = 0;j<iteam.length;j++) {
						if(iteam[j].equals(username)) {
							list2.add(list.get(i));
							continue;
						}	
					}
					
				}
				
			}
			
			return list2;
			
		}
		
		public List<course>chaxunceshi(String id ,String username){
			List<course>list = userDao.selectNotUserRead(id);
			List<course>list2 = new ArrayList<>();
			for(int i = 0;i<list.size();i++) {
				String uname = list.get(i).getCoursePower();
				if(uname!=null) {
					String []iteam = uname.split(",");
					for(int j = 0;j<iteam.length;j++) {
						if(iteam[j].equals(username)) {
							list2.add(list.get(i));
							continue;
						}	
					}
					
				}
				
			}	
			return list2;		
		}

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
		
		public boolean chaxunReadBYCID(String readerID,String courseID){
			Map<String, String> map = new HashMap<>();
			map.put("readerID", readerID);
			map.put("courseID", courseID);
			System.out.println("aaa"+readDao.selectReadBYCID(map));
			if(readDao.selectReadBYCID(map).size()==0) {			
				return true;
			}
			return false;
		}

}
