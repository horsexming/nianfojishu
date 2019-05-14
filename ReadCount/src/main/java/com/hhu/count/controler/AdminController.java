package com.hhu.count.controler;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhu.count.entity.Admin;
import com.hhu.count.entity.course;
import com.hhu.count.server.AdminServer;
import com.hhu.count.server.CourseServer;
import com.hhu.count.server.UserServer;
import com.hhu.count.vo.AdminCourseVO;
/*
 * 管理员相关的Controller
 * */
@Controller
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	AdminServer adminServer;
	@Autowired
	CourseServer courseServer;
	@Autowired
	UserServer userServer;
	
	//通过用户名查找一个用户
	@ResponseBody
	@RequestMapping(value = "/SelectByUsername" , produces = "application/json; charset=utf-8")
	public String SelectByUsername(String username) {		
		System.out.println(adminServer.selectByUsername(username));
		return adminServer.selectByUsername(username);		
	}	
	//用户注册
	@RequestMapping("/Register")
	public String Register(Admin admin,Model model) {
		/*adminServer.InsertAdmin(admin);
		model.addAttribute("AdminRegister", "success");*/
		return "admin/Login";
	}
	//跳转到首页
	@RequestMapping("/toAdminIndex")
	public String toAdminIndex(@RequestParam(value="pn",defaultValue="1")Integer pn,HttpSession session,
			Model model) {
		String username = (String) session.getAttribute("username");
		String WriterId = adminServer.selectAdminByname(username).getId();
		PageHelper.startPage(pn,5);
		List<course> list = courseServer.selectByWriterId(WriterId);
		PageInfo<course>page = new PageInfo<course>(list,5);
		model.addAttribute("AdminCourseList", list);
		model.addAttribute("pageInfo", page);
		return "admin/LoginSuccess";
	}		
	//修改个人信息(回显)
	@RequestMapping("/updateAdmin")
	public String updateUser(Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");
		Admin admin = adminServer.selectAdminByname(username);
		model.addAttribute("updateAdmin", admin);
		return "admin/toAdminUpdate";
	}
	//修改个人信息(修改)
	@RequestMapping("/Update")
	public String Update(Admin admin) {
		adminServer.updateAdmin(admin);
		return "redirect:/Admin/toAdminIndex";
	}
	//课程统计
	@RequestMapping("/UserCourse/{id}")
	public String UserCourse(@PathVariable("id")String id,Model model,
			@RequestParam(value="pn",defaultValue="1")Integer pn,HttpSession session) {
		PageHelper.startPage(pn,5);
		List<AdminCourseVO> list = adminServer.selectAdminCour(id);
		PageInfo<AdminCourseVO>page = new PageInfo<AdminCourseVO>(list,5);
		System.out.println("poorReading"+list.get(0).getPoorR());
		model.addAttribute("UserCour", list);
		model.addAttribute("UserCourid", id);
		model.addAttribute("pageInfo", page);
		return "admin/UserCourse";
	}
	
	@ResponseBody
	@RequestMapping(value = "/SelectByUsername2" , produces = "application/json; charset=utf-8")
	public String SelectByUsername2(String username,HttpSession session) {		
		String username2 = (String) session.getAttribute("username");
		if(!username.equals(username2)) {
			return adminServer.selectByUsername(username);	
		}
		return "1";		
	}
	
	//手机号修改验证
		@ResponseBody
		@RequestMapping(value = "/SelUserByP5" , produces = "application/json; charset=utf-8")
		public String SelUserByP5(String phone,HttpSession session) {
			String username = (String) session.getAttribute("username");
			String phone2 = adminServer.selectAdminByname(username).getPhone();
			if(phone.equals(phone2)) {
				return "0";
			}else if(userServer.SelUserByPhone(phone)=="1"||adminServer.selectByp(phone)=="1") {
				return "1";
			}else {
				return "0";
			}
			
		}
	
}
