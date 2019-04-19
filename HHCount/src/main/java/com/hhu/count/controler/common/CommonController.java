package com.hhu.count.controler.common;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhu.count.entity.User;
import com.hhu.count.entity.course;
import com.hhu.count.server.AdminServer;
import com.hhu.count.server.CourseServer;
import com.hhu.count.server.ReadServer;
import com.hhu.count.server.UserServer;
import com.hhu.count.vo.UserReadVo;

/*
 * 
 * 公用Controller
 * 
 * */

@Controller
@RequestMapping("/Common")
public class CommonController {
	
	@Autowired
	AdminServer adminServer;
	@Autowired
	UserServer userServer;
	@Autowired
	CourseServer courseServer;
	@Autowired
	ReadServer readServer;
	//index自动跳转到登录页
	@RequestMapping("/login")
	private String Login() {
		return "admin/Login";
	}
	@RequestMapping("/SelectRegister")
	public String SelectRegister(Model model,HttpSession session) {	
			model.addAttribute("role","user");
			session.setAttribute("login",UUID.randomUUID().toString());
			return "user/UserRegister";
	}
	
	@RequestMapping("/Login")
	public String Login(User user,Model model,HttpSession session,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		System.out.println("role"+user.getRole());
		if("Admin".equals(user.getRole())) {
			String username = adminServer.selectByUAndPass(user);
			System.out.println("username"+username);
			if(username=="0") {
				model.addAttribute("LoginError", "用户名或密码错误");
				return "admin/Login";
			}else {
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30*60);
				String WriterId = adminServer.selectAdminByname(username).getId();
				PageHelper.startPage(pn,5);
				System.out.println(pn);
				List<course> list = courseServer.selectByWriterId(WriterId);
				PageInfo<course>page = new PageInfo<course>(list,5);
				model.addAttribute("AdminCourseList", list);
				model.addAttribute("pageInfo", page);
				return "admin/LoginSuccess";
			}
			
		}else if("User".equals(user.getRole())) {	
			String username =  userServer.selectByUAndPass(user);
			if(username=="0") {
				model.addAttribute("LoginError", "用户名或密码错误");
				return "admin/Login";
			}else {
				String id = userServer.selectUserByname(username).getId();
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30*60);
				PageHelper.startPage(pn,5);
				List<UserReadVo> list = userServer.selectUserRead(id);
				PageInfo<UserReadVo>page = new PageInfo<UserReadVo>(list,5);
				model.addAttribute("pageInfo", page);		
				model.addAttribute("myread", list);
				return "user/Myread";																													
			}
		}
		return "error/RegisterError";	
	}
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("username");
		return "admin/Login";
	}
	

}
