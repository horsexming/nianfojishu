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
	//用户名密码登录
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
	//短信验证码登录
	@RequestMapping("/LoginMsM")
	public String LoginMsM(User user,Model model,HttpSession session,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		System.out.println("role"+user.getRole());
		if("Admin".equals(user.getRole())) {
			String username2 = adminServer.selectByp(user.getUsername());
			if(session.getAttribute("randomCode")==null) {
				model.addAttribute("LoginError", "验证码已失效");
				return "Sendsms";	
			}
			int s = Integer.parseInt(user.getPassword());
			int sn = (int) session.getAttribute("randomCode");
			System.out.println("username"+username2);
			if(username2=="0") {
				model.addAttribute("LoginError", "手机号不存在");
				return "Sendsms";
			}else if(s==sn) {
				String username = adminServer.selectByp2(user.getUsername());
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30*60);
				String WriterId = adminServer.selectAdminByname(username).getId();
				PageHelper.startPage(pn,5);
				System.out.println(pn);
				List<course> list = courseServer.selectByWriterId(WriterId);
				PageInfo<course>page = new PageInfo<course>(list,5);
				model.addAttribute("AdminCourseList", list);
				model.addAttribute("pageInfo", page);
				session.removeAttribute("randomCode");
				return "admin/LoginSuccess";
			}else{
				model.addAttribute("LoginError", "验证码不正确");
				return "Sendsms";																													
			}
			
		}else if("User".equals(user.getRole())) {	
			String username2 =  userServer.SelUserByPhone(user.getUsername());
			System.out.println(session.getAttribute("randomCode"));
			System.out.println(user.getPassword());
			if(session.getAttribute("randomCode")==null) {
				model.addAttribute("LoginError", "验证码已失效");
				return "Sendsms";	
			}
			int s = Integer.parseInt(user.getPassword());
			int sn = (int) session.getAttribute("randomCode");
			if(username2=="0") {
				model.addAttribute("LoginError", "手机号不存在");
				return "Sendsms";
			}else if(s==sn){
				System.out.println(session.getAttribute("randomCode"));
				session.removeAttribute("randomCode");
				String username =  userServer.SelUserByPhone2(user.getUsername());
				String id = userServer.selectUserByname(username).getId();
				session.setAttribute("username", username);
				session.setMaxInactiveInterval(30*60);
				PageHelper.startPage(pn,5);
				List<UserReadVo> list = userServer.selectUserRead(id);
				PageInfo<UserReadVo>page = new PageInfo<UserReadVo>(list,5);
				model.addAttribute("pageInfo", page);		
				model.addAttribute("myread", list);
				return "user/Myread";
			}else{
				model.addAttribute("LoginError", "验证码不正确");
				return "Sendsms";																													
			}
		}
		return "error/RegisterError";	
	}
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("username");
		return "admin/Login";
	}
	@RequestMapping("/Sendsms")
	public String Sendsms() {
		return "Sendsms";
	}
	
	

}
