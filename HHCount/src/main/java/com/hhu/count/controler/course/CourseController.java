package com.hhu.count.controler.course;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhu.count.entity.course;
import com.hhu.count.server.AdminServer;
import com.hhu.count.server.CourseServer;
import com.hhu.count.server.ReadServer;
import com.hhu.count.server.UserServer;
import com.hhu.count.vo.CourseVO;
import com.hhu.count.vo.PoorUserVo;

@Controller
@RequestMapping("/Course")
public class CourseController {
	
	//格式化时间
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 	binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空
	}
	
	@Autowired
	AdminServer adminServer;
	@Autowired
	UserServer userServer;
	@Autowired
	CourseServer courseServer;
	@Autowired
	ReadServer readServer;
	//课程发布通过名称查询发布者
	@RequestMapping("/toRelease")
	public String ReleaseCourse(Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");		
		model.addAttribute("Writer", adminServer.selectAdminByname(username));
		return "course/toRelease";
	}
	//跳转已经发布的课程
	@RequestMapping("/AlreadyRelease")
	public String AlreadyRelease(@RequestParam(value="pn",defaultValue="1")Integer pn,HttpSession session,
			Model model) {
		String username = (String) session.getAttribute("username");
		String WriterId = adminServer.selectAdminByname(username).getId();
		PageHelper.startPage(pn,5);
		List<course> list = courseServer.selectByState(WriterId, "1");
		PageInfo<course>page = new PageInfo<course>(list,5);
		model.addAttribute("AdminCourseList", list);
		model.addAttribute("pageInfo", page);
		return "admin/LoginSuccess2";
		
		
	}
	//课程发布
	@RequestMapping("/Release")
	public String Release(course course) {
		courseServer.InsertCourse(course);
		return "redirect:../Admin/toAdminIndex";
	}
	//课程修改回显数据
	@RequestMapping("/UpdateCourse/{id}")
	public String UpdateCourse(@PathVariable String id,Model model) {
		System.out.println("CourseID"+id);
		model.addAttribute("updateCourse", courseServer.selectByID(id));
		return "course/ToUpdateCourse";
	}
	//课程修改
	@RequestMapping("/Update")
	public String Update(course course) {
		courseServer.updateCourse(course);	
		return "redirect:../Admin/toAdminIndex";
	}
	//课程删除
	@RequestMapping("/DeleteCourse/{id}")
	public String DeleteCourse(@PathVariable String id){
		readServer.DelMyreads(id);
		
		courseServer.DeleteCourseByID(id);
		return "redirect:../../Admin/toAdminIndex";
	}
	//查看课程详情
	@RequestMapping("/LookCourse/{id}")
	public String LookCourse(@PathVariable String id,Model model) {
		model.addAttribute("LookCourse", courseServer.selectByID(id));
		return "course/LookCourse";
	}
	//课程权限设置
	/*@RequestMapping("/CoursePower/{id}")
	public String CoursePower(@PathVariable String id,Model model) {
		String poorU = courseServer.selectByID(id).getCoursePower();
		System.out.println("poorU"+poorU);
		if(userServer.SelAllUser().size()<=0) {
			model.addAttribute("CoursePowerID", id);
			model.addAttribute("CoursePower",null);
			model.addAttribute("CoursePNull","暂无用户");
			return "course/CoursePower";
		}else if(poorU==null) {
			model.addAttribute("CoursePowerID",id);
			model.addAttribute("CoursePower",userServer.SelAllUser());
			return "course/CoursePower";	
		}else {			
			List<PoorUserVo> list = userServer.SelAllUser();
			String[] strs = poorU.split(",");
			for(int k =0;k<list.size();k++){
				for(int i = 0;i<strs.length;i++) {
					if((strs[i]).equals(list.get(k).getUsername())) {
						list.get(k).setState("1");
						continue;
					}
				}
			}
			model.addAttribute("CoursePowerID", id);
			model.addAttribute("CoursePower",list);
			return "course/CoursePower";
		}
	}*/
	//课程权限设置
	@RequestMapping("/UpadtePower/{id}")
	public String UpadtePower(@RequestParam(value="pn",defaultValue="1")Integer pn,HttpSession session,@PathVariable String id,Model model) {
		String username = (String) session.getAttribute("username");
		String WriterId = adminServer.selectAdminByname(username).getId();
		PageHelper.startPage(pn,8);
		List<course> list = courseServer.selectByWriterId(WriterId);
		PageInfo<course>page = new PageInfo<course>(list,3);
		model.addAttribute("pageInfo", page);
		model.addAttribute("CourseBuding",list );
		String poorU = courseServer.selectByID(id).getCoursePower();
		System.out.println("poorU"+poorU);
		if(userServer.SelAllUser().size()<=0) {
			model.addAttribute("CoursePowerID", id);
			model.addAttribute("powCoursename",courseServer.selectByID(id).getCoursename());
			model.addAttribute("CoursePower",null);
			model.addAttribute("CoursePNull","暂无用户");
			return "course/Binding";
		}else if(poorU==null) {
			model.addAttribute("CoursePowerID",id);
			model.addAttribute("powCoursename",courseServer.selectByID(id).getCoursename());
			model.addAttribute("CoursePower",userServer.SelAllUser());
			return "course/Binding";	
		}else {			
			List<PoorUserVo> list1 = userServer.SelAllUser();
			String[] strs = poorU.split(",");
			for(int k =0;k<list1.size();k++){
				for(int i = 0;i<strs.length;i++) {
					if((strs[i]).equals(list1.get(k).getUsername())) {
						list1.get(k).setState("1");
						continue;
					}
				}
				}
			model.addAttribute("powCoursename",courseServer.selectByID(id).getCoursename());
			model.addAttribute("CoursePowerID", id);
			model.addAttribute("CoursePower",list1);
			return "course/Binding";
		}
	}
	//权限修改
	/*@RequestMapping("/updatePoor/{CoursePowerID}")
	public String updatePoor(@PathVariable String CoursePowerID) {
			return "";
	}*/
	
	@ResponseBody
	@RequestMapping("/updatePoorState")
	public String updatePoorState(HttpServletRequest request) {
		String CoursePower = request.getParameter("CoursePower");
		String id = request.getParameter("id");
		course course = courseServer.selectByID(id);
		List<PoorUserVo> list1 = userServer.SelAllUser();
		if(list1.size()>0){
			for(int i=0;i<list1.size();i++) {
				if(readServer.selectReadBYCID(list1.get(i).getId(),id)) {	
					readServer.InsertRead(id, list1.get(i).getUsername(),list1.get(i).getId(),course.getCoursename());
						
				}	
			}
		}				
		readServer.powerMyread(id);
		String[] strs = CoursePower.split(",");
		for(int i = 0;i<strs.length;i++) {	
			readServer.shezhiMyread(id, strs[i]);
		}
		courseServer.updatePowers(id, CoursePower);
		return "success";
	}
	//权限绑定
	@RequestMapping("/PrivilegeBinding")
	public String PrivilegeBinding(@RequestParam(value="pn",defaultValue="1")Integer pn,HttpSession session,Model model) {
		String username = (String) session.getAttribute("username");
		String WriterId = adminServer.selectAdminByname(username).getId();
		PageHelper.startPage(pn,8);
		List<course> list = courseServer.selectByWriterId(WriterId);
		PageInfo<course>page = new PageInfo<course>(list,3);
		model.addAttribute("pageInfo", page);
		model.addAttribute("CourseBuding",list );
		return "course/Binding";
	}
	//发布课程
	@RequestMapping("/UpdateCourseState/{id}")
	public String UpdateCourseState(@PathVariable String id) {
		course course = courseServer.selectByID(id);
		/*List<PoorUserVo> list1 = userServer.SelAllUser();
		if(list1.size()>0){
			for(int i=0;i<list1.size();i++) {
				if(readServer.selectReadBYCID(list1.get(i).getId(),id)) {	
					readServer.InsertRead(id, list1.get(i).getUsername(),list1.get(i).getId(),course.getCoursename());
					String CoursePower = course.getCoursePower();
					String[] strs = CoursePower.split(",");
					for(int j = 0;j<strs.length;j++) {	
						readServer.shezhiMyread(id, strs[j]);
						Map<String, String>map = new HashMap<>();
						map.put("courseID", id);
						map.put("readername", strs[j]);
						System.out.println("123"+id);
						System.out.println("456"+strs[j]);
						if(readServer.selectByUnameAndCid(strs[j], id).getBuildFirst()==null) {
							readServer.setFtBulid(map);
						}	
					}
				}else {	
					if(readServer.selectByUnameAndCid(list1.get(i).getUsername(), id).getState().equals("1")) {
						Map<String, String>map = new HashMap<>();
						map.put("courseID", id);
						map.put("readername", list1.get(i).getUsername());
						if(readServer.selectByUnameAndCid(list1.get(i).getUsername(), id).getBuildFirst()==null) {
							readServer.setFtBulid(map);
						}	
						
					}	
				}	
			}
		}*/
		String CoursePower = course.getCoursePower();
		if(CoursePower==null) {
			System.out.println("aaa");
		}else{
			String[] strs = CoursePower.split(",");
			for(int j = 0;j<strs.length;j++) {	
				if(readServer.selectByUnameAndCid(strs[j], id).getBuildFirst()==null) {
					Map<String, String>map = new HashMap<>();
					map.put("courseID", id);
					map.put("readername", strs[j]);
					readServer.setFtBulid(map);
				}	
			}	
		}		
		String state = "1";
		if(course.getFirstTime()==null) {
			courseServer.updatefirstTime(id,new Date());
		}
		courseServer.updateCourseState(id,state,new Date(),null);	
		return "redirect:../../Admin/toAdminIndex"; 
	}
	
	//课程停止
	
	@RequestMapping("/CourseState/{id}")
	public String CourseState(@PathVariable String id) {
		String state = "0";
		courseServer.updateCourseState(id,state,null,new Date());
		//readServer.DelMyreads(id);
		return "redirect:../../Admin/toAdminIndex"; 
	}
	
	//课程模糊查询
	@RequestMapping("/selectCourseLike")
	public String selectCourseLike(@RequestParam(value="pn",defaultValue="1")Integer pn,course course,
			Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");
		String writerid = adminServer.selectAdminByname(username).getId();
		String coursename = course.getCoursename();
		System.out.println("coursename"+coursename);;
		System.out.println("writerid"+writerid);
		PageHelper.startPage(pn,5);
		List<course> list = courseServer.selectCourseLike(writerid, coursename);
		PageInfo<course>page = new PageInfo<course>(list,5);
		model.addAttribute("AdminCourseList", list);
		model.addAttribute("pageInfo", page);
		return "admin/LoginSuccess";
		
	}
	//管理员课程统计
	@RequestMapping("/AdminCount")
	public String AdminCount(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");
		String WriterId = adminServer.selectAdminByname(username).getId();
		PageHelper.startPage(pn,5);
		List<CourseVO> list = courseServer.SelCourseVO(WriterId);
		PageInfo<CourseVO>page = new PageInfo<CourseVO>(list,5);
		model.addAttribute("AdminCourseList", list);
		model.addAttribute("pageInfo", page);
		return "admin/AdminCount";
	}
	//课程批量发布
	@ResponseBody
	@RequestMapping("/BatchRelease")
	public String BatchRelease(HttpServletRequest request){
		String items = request.getParameter("fabuPiliang");
		String[] strs = items.split(",");
		for(int k=0;k<strs.length;k++) {
			course course = courseServer.selectByID(strs[k]);
			if(course.getFirstTime()==null) {
				courseServer.updatefirstTime(course.getId(),new Date());
			}
			String CoursePower = course.getCoursePower();
			if(CoursePower==null) {
				System.out.println("aaa");
			}else{
				String[] str2 = CoursePower.split(",");
				for(int j = 0;j<str2.length;j++) {	
					if(readServer.selectByUnameAndCid(str2[j], strs[k]).getBuildFirst()==null) {
						Map<String, String>map = new HashMap<>();
						map.put("courseID", strs[k]);
						map.put("readername",str2[j]);
						readServer.setFtBulid(map);
					}	
				}	
			}		
		}
		
		courseServer.BatchRelease(strs);
		return "success";
	}
	//批量停止课程
	@ResponseBody
	@RequestMapping("/StopCourse")
	public String StopCourse(HttpServletRequest request){
		String items = request.getParameter("fabuPiliang");
		String[] strs = items.split(",");
		courseServer.StopCourse(strs);
		//readServer.piliangDels(strs);
		return "success";
	}
	
}
