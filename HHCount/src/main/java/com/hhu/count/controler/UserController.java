package com.hhu.count.controler;

import java.text.Format;
import java.text.ParseException;
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
import com.hhu.count.entity.MyRead;
import com.hhu.count.entity.OldRead;
import com.hhu.count.entity.User;
import com.hhu.count.server.CourseServer;
import com.hhu.count.server.ReadServer;
import com.hhu.count.server.UserServer;
import com.hhu.count.vo.CountVo;
import com.hhu.count.vo.Countmingxi;
import com.hhu.count.vo.OldReadVO;
import com.hhu.count.vo.UserReadVo;

@RequestMapping("/User")
@Controller
public class UserController {
	//格式化时间
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true)); // true:允许输入空值，false:不能为空
	}
	
	@Autowired
	UserServer userServer;
	@Autowired
	CourseServer courseServer;
	@Autowired
	ReadServer readServer;
	@RequestMapping("/Register")
	public String insertUser(User user,HttpSession session) {
		if(userServer.SelByPhAndNa(user)) {
			return "admin/Login";
		}
		if(user.getLogin().equals(session.getAttribute("login"))) {
			session.removeAttribute("login");
			userServer.InsertUser(user);
		}
		return "admin/Login";
	}
	//通过用户名查找一个用户
	@ResponseBody
	@RequestMapping(value = "/SelectByUsername" , produces = "application/json; charset=utf-8")
	public String SelectByUsername(String username) {		
		return userServer.selectByUsername(username);		
	}
	
	//跳转到首页
	/*@RequestMapping("/toUserIndex")
	public String toUserIndex(Model model,HttpSession session,@RequestParam(value="pn",defaultValue="1")Integer pn) {	
		String id = selectID(session);
		Integer pagesize = 5;
		Integer pnsize;
		String username = (String) session.getAttribute("username");
		if(userServer.selectUserRead(id).size()==0) {
			if(pn==1) {
				pnsize = 0;
			}else {
				pnsize = pagesize*(pn-1);
			}
			List<course> list1 = courseServer.selLimitUser(username, pn, pagesize);
			List<course> pagelist = courseServer.selectLimitTen(username);
			UserPage page = new UserPage();
			page.setPagesize(pagesize);				
			if(pagelist.size()%pagesize==0) {
				page.setPage(pagelist.size()/pagesize); 
			}else {
				page.setPage(pagelist.size()/pagesize+1); 
			}
			model.addAttribute("selectLimitTen", list1);
			model.addAttribute("pn", pn);
			model.addAttribute("page", page.getPage());
			model.addAttribute("pagenum",pagelist.size());			
			return "user/LoginSuccess";	
		}else {
			if(pn==1) {
				pnsize = 0;
			}else {
				pnsize = pagesize*(pn-1);
			}
			List<course> list1 = courseServer.chaxunceshi(id ,username,pnsize,pagesize);
			List<course> pagelist = courseServer.chaxunceshi(id, username);
			System.out.println(list1.size()+"222");
			UserPage page = new UserPage();
			page.setPagesize(pagesize);				
			if(pagelist.size()%pagesize==0) {
				page.setPage(pagelist.size()/pagesize); 
			}else {
				page.setPage(pagelist.size()/pagesize+1); 
			}
			model.addAttribute("selectLimitTen", list1);
			model.addAttribute("pn", pn);
			model.addAttribute("page", page.getPage());
			model.addAttribute("pagenum",pagelist.size());
			return "user/LoginSuccess";					
		}			
	}*/
	
	//修改个人信息(回显)
	@RequestMapping("/updateUser")
	public String updateUser(Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");
		User user = userServer.selectUserByname(username);
		model.addAttribute("updateUser", user);
		return "user/toUserUpdate";
	}
	//修改个人信息(修改)
	@RequestMapping("/Update")
	public String Update(User user) {
		userServer.updateUser(user);
		return "redirect:/User/Myread";
	}
	//查看课程详情
	@RequestMapping("/LookCourse/{id}")
	public String LookCourse(@PathVariable String id,@RequestParam String pn,Model model,HttpSession session) {
		String username = (String) session.getAttribute("username");	
		String readID = userServer.selectUserByname(username).getId();
		String coursename = courseServer.selectByID(id).getCoursename();
		if(readServer.selectReadBYCID(readID,id)) {	
			readServer.InsertRead(id,username,readID,coursename);
		}			
		String reading = readServer.selectByUnameAndCid(username,id).getReading();
		String Myreading = readServer.selectByUnameAndCid(username,id).getId();
		model.addAttribute("readID",readID);
		model.addAttribute("pn",pn);
		model.addAttribute("reading",reading);
		model.addAttribute("Myreading",Myreading);
		model.addAttribute("LookCourse", courseServer.selectByID(id));
		return "user/LookCourse";				
	}
	//从我的阅读中删除
	@RequestMapping("/delMyRead/{rid}")
	public String delMyRead(@PathVariable String rid) {
		readServer.delFromMRByID(rid);
		return "redirect:/User/Myread";
	}	
	//添加到我的课程
	@RequestMapping("/addCourse/{id}")
	public String addCourse(@PathVariable String id,Model model,HttpSession session) {
		String readername = (String) session.getAttribute("username");
		String readerID = selectID(session) ;	
		String coursename = courseServer.selectByID(id).getCoursename();
		readServer.InsertRead(id,readername,readerID,coursename);
		return "redirect:/User/Myread";
	}
	//跳转到我的课程
	@RequestMapping("/Myread")
	public String Myread(HttpSession session,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		String id = selectID(session) ;
		PageHelper.startPage(pn,5);
		List<UserReadVo> list = userServer.selectUserRead(id);
		PageInfo<UserReadVo>page = new PageInfo<UserReadVo>(list,5);
		model.addAttribute("pageInfo", page);		
		model.addAttribute("myread", list);
		return "user/Myread";
	}
	//设置阅读量
	@ResponseBody
	@RequestMapping("/setReadingnum")
	public String batchDeletes(HttpServletRequest request) {
		String reading = request.getParameter("reading");// System.out.println(items);
		String id = request.getParameter("id");	
		String reading2 = readServer.selMyReadById(id).getReading();
		int reading1 = Integer.parseInt(reading);
		int reading3 = Integer.parseInt(reading2);
		int reading4 = reading1+reading3;				
		Integer num=0;
		if(readServer.selOldRead(id).size()==0) {
			readServer.InsOldRead(id,new Date());
		}else {
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<OldRead> list = readServer.selOldRead(id);
			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				if(sFormat.format(new Date()).equals(sFormat.format(list.get(i).getReaderDate()))) {
					num = 2;
					int Oudread = Integer.parseInt(list.get(i).getOldReading());
					int Oudread2 = Oudread+reading1;
					readServer.SetOldRead(list.get(i).getId(),String.valueOf(Oudread2));
				}
			}
			if(num==0) {
				readServer.InsOldRead2(id, new Date(),reading);
			}
			
		}
		readServer.setMyReading(String.valueOf(reading4), id);
		return "success";
		
	}
	//查询阅读量
	@ResponseBody
	@RequestMapping("/findReadingID")
	public MyRead findReadingID(HttpServletRequest request,HttpSession session) {
		String id= request.getParameter("id");
		String username = (String) session.getAttribute("username");
		System.out.println("reading"+readServer.selectByUnameAndCid(username,id).getReading());			
		return readServer.selectByUnameAndCid(username,id);
		
		
	}		
	//设置阅读量
	@RequestMapping("/SetReadingN/{rid}")
	public String SetReadingN(@PathVariable String rid,@RequestParam String pn, HttpSession session) {
		Integer num=0;
		if(readServer.selOldRead(rid).size()==0) {
			readServer.InsOldRead(rid,new Date());
		}else {
			SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<OldRead> list = readServer.selOldRead(rid);
			System.out.println(list.size());
			for(int i=0;i<list.size();i++) {
				if(sFormat.format(new Date()).equals(sFormat.format(list.get(i).getReaderDate()))) {
					num = 2;
					int Oudread = Integer.parseInt(list.get(i).getOldReading());
					int Oudread2 = Oudread+1;
					readServer.SetOldRead(list.get(i).getId(),String.valueOf(Oudread2));
				}
			}
			if(num==0) {
				readServer.InsOldRead(rid,new Date());
			}
			
		}
		String reading = readServer.selMyReadById(rid).getReading();
		int reading1 = Integer.parseInt(reading);
		int reading2 = reading1+1;
		String reading3 = String.valueOf(reading2);
		readServer.setMyReading(reading3, rid);
		return "redirect:/User/Myread?pn="+pn;
		
	}
	
	//跳转到统计
	@RequestMapping("/ReadCount")
	public String ReadCount(HttpSession session,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {		
		String id = selectID(session) ;
		PageHelper.startPage(pn,5);
		List<CountVo> list = readServer.CountByDay(id);
		PageInfo<CountVo>page = new PageInfo<CountVo>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("co","Day");
		return"user/ReadCount";
	}
	//按照月份统计
	@RequestMapping("/ReadCountByM")
	public String ReadCountByM(HttpSession session,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		String id = selectID(session);
		PageHelper.startPage(pn,5);
		List<CountVo> list = readServer.CountByMonth(id);
		PageInfo<CountVo>page = new PageInfo<CountVo>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("co","Month");
		return"user/ReadCount";
	}
	
	//按照月份统计
	@RequestMapping("/ReadCountByY")
	public String ReadCountByYear(HttpSession session,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		String id = selectID(session);
		PageHelper.startPage(pn,5);
		List<CountVo> list = readServer.CountByYear(id);
		PageInfo<CountVo>page = new PageInfo<CountVo>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("Month","Month");
		model.addAttribute("co", "Year");
		return"user/ReadCount";
	}
	//阅读明细
	@RequestMapping("/Countmingxi1/{readerDate}")
	public String Countmingxi1(HttpSession session,@PathVariable String readerDate,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		String id = selectID(session);
		PageHelper.startPage(pn,5);
		List<Countmingxi> list = readServer.Countmingxi1(id, readerDate);
		PageInfo<Countmingxi>page = new PageInfo<Countmingxi>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("co", "Day");
		model.addAttribute("readerDate", readerDate);
		return "user/Countmingxi";
	}
	@RequestMapping("/Countmingxi2/{readerDate}")
	public String Countmingxi2(HttpSession session,@PathVariable String readerDate,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) throws Exception {
		String id = selectID(session);
		Map map = new HashMap();
		map.put("readerID", id);
		map.put("readerDate", readerDate);
		System.out.println(readerDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");		
		Date Date = formatter.parse(readerDate);
		PageHelper.startPage(pn,5);
		List<Countmingxi> list = readServer.Countmingxito(map);
		PageInfo<Countmingxi>page = new PageInfo<Countmingxi>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("co", "Month");
		model.addAttribute("readerDate", readerDate);
		return "user/Countmingxi";
	}
	@RequestMapping("/Countmingxi3/{readerDate}")
	public String Countmingxi3(HttpSession session,@PathVariable String readerDate,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) throws Exception {
		String id = selectID(session);
		System.out.println(readerDate);
		Map map = new HashMap();
		map.put("readerID", id);
		map.put("readerDate", readerDate);
		PageHelper.startPage(pn,5);
		List<Countmingxi> list = readServer.Countmingxi3(map);
		PageInfo<Countmingxi>page = new PageInfo<Countmingxi>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("ReadCount", list);
		model.addAttribute("co", "Year");
		model.addAttribute("readerDate", readerDate);
		return "user/Countmingxi";
	}
	
	//通过手机号查询用户是否存在
	@ResponseBody
	@RequestMapping(value = "/SelUserByP" , produces = "application/json; charset=utf-8")
	public String SelUserByP(String phone) {	
		return userServer.SelUserByPhone(phone);
	}
	//查询阅读记录
	@RequestMapping("/oudReader/{rid}")
	private String oudReader(@PathVariable String rid,Model model,@RequestParam(value="pn",defaultValue="1")Integer pn) {
		PageHelper.startPage(pn,5);
		List<OldReadVO> list = readServer.selByMRId(rid);
		PageInfo<OldReadVO>page = new PageInfo<OldReadVO>(list,5);
		model.addAttribute("pageInfo", page);
		model.addAttribute("OldReadCount", list);
		model.addAttribute("oldId", rid);
		return "user/OldReadCount";
		
	}
						
	//通过用户名查询用户ID
	public String selectID(HttpSession session) {
		String username = (String) session.getAttribute("username");
		String id = userServer.selectUserByname(username).getId();
		return id;
	}
	
	
	
	
	
	
	
	
		

}
