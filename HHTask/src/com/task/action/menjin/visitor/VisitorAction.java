package com.task.action.menjin.visitor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.menjin.visitor.VisitorServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.DeptNumber;
import com.task.entity.Users;
import com.task.entity.menjin.visitor.Visitor;
import com.task.util.AESEnctypeUtil;
import com.task.util.MKUtil;
import com.task.util.PostUtil;
import com.task.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * 访客系统
 * @author WCY
 *
 */
public class VisitorAction {

	private Integer id;
	private VisitorServer visitorServer;
	
	private String cpage = "1";
	private Integer pageSize=15;
	private String url;
	private String errorMessage;
	private String total;
	
	private Visitor visitor;
	private List<Visitor> visitorList;
	
	private String tag;
	private String pageStatus;
	private String idCard;
	private String deptName;
	private String param;
	private String p;
	private String ip;
	
	/**
	 * 上传身份证照片
	 */
	public void uploadByIdentity() {
		try {
			if(idCard==null || idCard==null) {
				MKUtil.writeJSON(false,"没有找到您的身份信息，请重试",null);
			}else {
				String image = ServletActionContext.getRequest().getParameter("image");
				image = image.replaceAll("data:image/jpg;base64,", "");
				image = image.replaceAll("data:image/png;base64,", "");
				image = image.replaceAll("data:image/jpeg;base64,", "");
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] bs = decoder.decodeBuffer(image); 
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				String fileName = Util.getDateTime("yyyyMMdd")+"-"+idCard+".jpg";
				String fileRealPath = ServletActionContext.getServletContext()
						.getRealPath("/upload/file/menjin/visitor")
						+ "/" + fileName;
				File uploadFile = new File(fileRealPath);
				ImageIO.write(bufferedImage, "jpeg", uploadFile);
				
//				visitorIdentity.setPicture(fileName);
//				visitorIdentity = visitorServer.judgeIdentity(visitorIdentity);
				
				MKUtil.writeJSON(true,"success",fileName);
			}
		} catch (Exception e) {
			MKUtil.writeJSON(false,"系统异常："+e.getMessage(),null);
			e.printStackTrace();
		}
	}
	
	/**
	 * 上传拍照的照片
	 */
	public void uploadByPicture() {
		try {
			String image = ServletActionContext.getRequest().getParameter("image");
			image = image.replaceAll("data:image/jpg;base64,", "");
			image = image.replaceAll("data:image/png;base64,", "");
			image = image.replaceAll("data:image/jpeg;base64,", "");
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bs = decoder.decodeBuffer(image); 
			ByteArrayInputStream inputStream = new ByteArrayInputStream(bs);
			BufferedImage bufferedImage = ImageIO.read(inputStream);
			String fileName = Util.getDateTime("yyyyMMdd")+"-"+idCard+"-new.jpg";
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file/menjin/visitor")
					+ "/" + fileName;
			File uploadFile = new File(fileRealPath);
			ImageIO.write(bufferedImage, "jpg", uploadFile);
			MKUtil.writeJSON(true,"success",fileName);
		} catch (Exception e) {
			MKUtil.writeJSON(false,"系统异常："+e.getMessage(),null);
			e.printStackTrace();
		}
	}
	/**
	 * 判断身份
	 */
	public String judgeIdentity() {
		ip = visitorServer.getVisitorIp();
		return "visitor_identification";
	}
	
	public void getIdentityPicture() {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		response.setContentType("image/jpeg");
        response.setCharacterEncoding("utf-8");
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file/menjin/visitor")
				+ "/" + param;
        
        //获取服务器文件

        response.setContentType("image/jpeg");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        Integer index = 10;
        for (int i=0;i<index; i++) {
        	try{
        		File file = new File(fileRealPath);
        		response.setHeader("Content-Disposition", "attachment;filename="+file.getName());
        		InputStream ins = new FileInputStream(file);
        		OutputStream os = response.getOutputStream();
        		byte[] b = new byte[1024];
        		int len;
        		while((len = ins.read(b)) > 0){
        			os.write(b,0,len);
        		}
        		os.flush();
        		os.close();
        		ins.close();
        		break;
        	}catch (IOException ioe){
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	}
		}
	}
	
	public String toVisitorApply() {
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response = ServletActionContext.getResponse();
//		if (request.getProtocol().compareTo("HTTP/1.0") == 0){
//			response.setHeader("Pragma","no-cache");
//		}else if (request.getProtocol().compareTo("HTTP/1.1") == 0){
//			response.setHeader("Cache-Control","no-cache");
//		}
//		visitorIdentity = visitorServer.getVisitorIdentityById(visitorIdentity.getId(), "encry");
		//保存指纹和人像
//		visitor.getFingerprint();//页面传入
//		visitor.getPicture();//页面传入
//		if(visitor!=null) {
//			StringBuffer buffer = new StringBuffer();
//			String[] split = visitor.getIdentityCard().split("");
//			for (int i=0;i<split.length;i++) {
//				if(split[i]!=null && !split[i].equals("")) {
//					if(i<3 || i>split.length-7) {
//						buffer.append(split[i]);
//					}else {
//						buffer.append("*");
//					}
//				}
//			}
//			visitor.setIdentityCard(buffer.toString());
//			String name = visitor.getVisitorName();
//			if(name==null || name.equals("")) {
//				throw new RuntimeException("姓名为空，系统异常。");
//			}
//			String[] split2 = name.split("");
//			String returnName = split2[0];
//			if(split2.length==2) {
//				returnName+="*";
//			}else if(split2.length==3){
//				returnName+="*"+split2[2];
//			}else if(split2.length==4){
//				returnName+="**"+split2[3];
//			}else {
//				returnName = name;
//			}
//			visitor.setVisitorName(returnName);
//		}
		visitor.setDateTime(Util.getDateTime("yyyy-MM-dd"));
		ip = visitorServer.getVisitorIp();
		return "visitor_apply";
	}
	
	/**
	 * 获取可以访客的部门
	 */
	public void findAllDeptByIsVisitor() {
		List<DeptNumber> list = visitorServer.findAllDeptByIsVisitor("是");
		List<String> list2 =new ArrayList<String>();
		for (DeptNumber deptNumber : list) {
			list2.add(deptNumber.getDept());
		}
		MKUtil.writeJSON(list2);
	}
	
	// 通过部门查询人员(输出json)
	public String findUsersByDept() {
		try {
			List<Users> list = visitorServer.findUsersByDept(deptName);
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
		return null;
	}
	
	public String addVisitorApply() {
//		String message = null;
		try {
			List<Visitor> vl = new ArrayList<Visitor>();
			if(visitorList!=null) {
				for (Visitor visitor : visitorList) {
					if(visitor!=null&& visitor.getId()!=null) {
						Visitor v = visitorServer.getVisitorById(visitor.getId());
						if(v!=null) {
							vl.add(v);
						}
					}
				}
			}
			if(pageStatus!=null && pageStatus.equals("select")) {
				
			}else {
				visitor.setAddTime(Util.getDateTime());
				visitor = visitorServer.addVisitorApply(visitor, pageStatus);
				vl.add(visitor);
			}
			visitorList = new ArrayList<Visitor>(vl);
			for (Visitor visitor : visitorList) {
				StringBuffer buffer = new StringBuffer();
				String[] split = visitor.getIdentityCard().split("");
				for (int i=0;i<split.length;i++) {
					if(split[i]!=null && !split[i].equals("")) {
						if(i<3 || i>split.length-7) {
							buffer.append(split[i]);
						}else {
							buffer.append("*");
						}
					}
				}
				visitor.setIdentityCard(buffer.toString());
				String name = visitor.getVisitorName();
				if(name==null || name.equals("")) {
					throw new RuntimeException("姓名为空，系统异常。");
				}
				String[] split2 = name.split("");
				String returnName = split2[0];
				if(split2.length==2) {
					returnName+="*";
				}else if(split2.length==3){
					returnName+="*"+split2[2];
				}else if(split2.length==4){
					returnName+="**"+split2[3];
				}else {
					returnName = name;
				}
				visitor.setVisitorName(returnName);
			}
//			visitor= new Visitor();
//			visitor.setFingerId("0008");
//			if(visitor!=null) {
//				MKUtil.writeJSON(true,"申请成功，请耐心等待，我们将以短信的方式提醒您，感谢您的使用。",visitor);
//			}else {
//				MKUtil.writeJSON(false, "申请失败，请联系管理员", null);
//			}
			ip = visitorServer.getVisitorIp();
			return "visitor_identificationList";
		} catch (Exception e) {
			errorMessage = e.toString();
			setUrl("");
			return "error";
		}
//		MKUtil.writeJSON(message);
//		return "error";
	}
	
	/**
	 * 进入客户端主页
	 * @return
	 */
	public String toVisitorIndex() {

		return "visitor_index";
	} 
	
	/**
	 * 进入选择认证方式
	 */
	public String toVisitorCheck() {
		try {
			param = getIpAddress(ServletActionContext.getRequest());
			ip = visitorServer.getVisitorIp();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "visitor_checked";
	}
	
	/**
	 * 根据条件查询访客申请记录
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findVisitorByCon() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(visitor!=null) {
			session.put("findVisitorByCon", visitor);
		}else {
			visitor = (Visitor) session.get("findVisitorByCon");
		}
		
		Map<String, Object> map = visitorServer.findVisitorByCon(visitor, Integer.parseInt(cpage), pageSize,tag);
		if(map!=null && map.size()>0) {
			visitorList = (List<Visitor>) map.get("list");
			Integer count = (Integer) map.get("count");
			Integer pageCount = (count+pageSize-1)/pageSize;
			total = pageCount+"";
		}
		setUrl("visitorAction!findVisitorByCon.action?tag="+tag);
		return "visitor_list";
	}
	
	public String showVisitorDetail() {
//		visitor = visitorServer.getVisitorById(id);
		visitorList = visitorServer.fingVisitorFollowById(id);
		return "visitor_inDetail";
	}
	
	//进出确认
	public void verifyInAndOut() {
		String message = visitorServer.verifyInAndOut(param, pageStatus);
		MKUtil.writeJSON(message);
	}
	
	/**
	 * 获取今天的所有可以进出的指纹库
	 */
	public void getFingerDataByDateTime() {
		visitorList = visitorServer.findVisitorByDatetime(Util.getDateTime("yyyy-MM-dd"), "inandout");
		
		List<String> returnStrList = new ArrayList<String>();
		if(visitorList!=null && visitorList.size()>0) {
			for (Visitor visitor : visitorList) {
				returnStrList.add(visitor.getFingerprint());
			}
		}
		MKUtil.writeJSON(returnStrList);
	}
	
	/**
	 * 扫描去打印凭证
	 * @return
	 */
	public String execute() {
		visitor = visitorServer.getVisitorVoucher(p);
		return "visitor_voucher";
	}
	
	/**
	 * 前往打印
	 * @return
	 */
	public String toPrintVoucher() {
		visitorList = visitorServer.visitorByVoucherEnctype(param);
		if(visitorList==null || visitorList.size()==0) {
			errorMessage = "凭证无效，请重新核实，谢谢";
			return "visitor_index";
		}
		visitor = visitorList.get(0);
		return "visitor_print";
	}
	
	
	public void getIntervieweeByPhone() {
		Users users = visitorServer.getIntervieweeByPhone(param);
		MKUtil.writeJSON(users);
	}
	
	//根据身份证查找指纹特征值
	public void fingFingerByIdentity() {
		if (pageStatus!=null && pageStatus.equals("idCard")) {
			visitor = visitorServer.getVisitorByIdenttiyCard(idCard,"idCard",param);
		}else {
			visitor = visitorServer.getVisitorByIdenttiyCard(idCard,"finger",null);
		}
		if(visitor!=null) {
			MKUtil.writeJSON(visitor);
		}else {
			MKUtil.writeJSON(null);
		}
	}
	
	//根据身份证查找指纹特征值和id
	public void loadExistsFinger() {
		visitor = visitorServer.getVisitorByIdentityId(id,"finger");
		if(visitor!=null) {
			MKUtil.writeJSON(true,null,visitor.getFingerprint(),visitor.getFingerId());
		}
	}
	
	//填充入指纹库已存在的指纹信息
	public void fillFingerByVisitorId() {
		visitor = visitorServer.fillFingerByVisitorId(id);
		MKUtil.writeJSON(visitor);
	}
	
	//添加常防申请
	public String addLongVisitorApply() {
		try {
			visitor = visitorServer.addLongVisitorApply(visitor, pageStatus);
			if(visitor!=null) {
				setErrorMessage("申请成功");
				setUrl("");
			}else {
				setErrorMessage("申请失败，请联系管理员");
				setUrl("");
			}
		} catch (Exception e) {
			errorMessage = e.toString();
		}
		return "error";
	}
	
	/**
	 * 提交申请
	 * @return
	 */
	public String confirmVisitorApply() {
		
		try {
			errorMessage = visitorServer.confirmVisitorApply(visitorList, pageStatus);
		} catch (Exception e) {
			errorMessage = "系统异常，请联系管理员："+e.toString();
		}
		MKUtil.writeJSON(errorMessage);
		ip = visitorServer.getVisitorIp();
		return "visitor_identificationList";
	}
	
	/**
	 * 前往添加随访人
	 * @return
	 */
	public String gotoAddFollow() {
		if(visitorList!=null && visitorList.size()>0) {
			for (Visitor visitor : visitorList) {
				
				if(visitor!=null && visitor.getId()!=null) {
					this.visitor = visitorServer.getVisitorById(visitor.getId());
					break;
				}
			}
		}
		ip = visitorServer.getVisitorIp();
		return "visitor_identification";
	}
	
	public void deleteVisitorByIdCard() {
		boolean flag = visitorServer.deleteVisitorByIdCard(idCard,param);
		MKUtil.writeJSON(flag);
	}
	
	public void deleteVisitor() {
		boolean b = visitorServer.deleteVisitorById(id);
		MKUtil.writeJSON(b);
	}
	
	public void findFollowVisitorVoucher() {
		List<String> list = visitorServer.findFollowVisitorVoucher(param);
		MKUtil.writeJSON(list);
	}
	
	
	/**
	 * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址;
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public final static String getIpAddress(HttpServletRequest request) throws IOException {
		// 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
//		String ip=request.getHeader("x-forwarded-for");
//		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//			ip=request.getHeader("Proxy-Client-IP");
//		}
//		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//			ip=request.getHeader("WL-Proxy-Client-IP");
//		}
//		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//			ip=request.getHeader("X-Real-IP");
//		}
//		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
//			ip=request.getRemoteAddr();
//		}
//		ip = request.getHeader("X-Forwarded-For");
//		System.out.println(ip);
// 
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("Proxy-Client-IP");
//				System.out.println(ip);
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("WL-Proxy-Client-IP");
//				System.out.println(ip);
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_CLIENT_IP");
//				System.out.println(ip);
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//				System.out.println(ip);
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getRemoteAddr();
//				System.out.println(ip);
//			}
//		} else if (ip.length() > 15) {
//			String[] ips = ip.split(",");
//			for (int index = 0; index < ips.length; index++) {
//				String strIp = (String) ips[index];
//				if (!("unknown".equalsIgnoreCase(strIp))) {
//					ip = strIp;
//					break;
//				}
//			}
//		}
//		return ip;
		return null;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VisitorServer getVisitorServer() {
		return visitorServer;
	}

	public void setVisitorServer(VisitorServer visitorServer) {
		this.visitorServer = visitorServer;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public List<Visitor> getVisitorList() {
		return visitorList;
	}

	public void setVisitorList(List<Visitor> visitorList) {
		this.visitorList = visitorList;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
