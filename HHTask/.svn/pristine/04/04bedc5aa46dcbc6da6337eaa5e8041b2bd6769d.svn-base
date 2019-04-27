package com.task.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.IntelligentDiagnosisServer;
import com.task.Server.UserServer;
import com.task.entity.CampanyName;
import com.task.entity.CompanyVIP;
import com.task.entity.IntelligentDiagnosis;
import com.task.entity.ProcessName;
import com.task.entity.QRCodeKu;
import com.task.entity.Users;
import com.task.entity.diaoyan.ResearchReport;
import com.task.util.MKUtil;

public class IntelligentDiagnosisAction {

	private IntelligentDiagnosis is;
	private List<IntelligentDiagnosis> isList;
	private IntelligentDiagnosisServer isserver;
	private List<CompanyVIP> cpList;
	private List<CampanyName> cnList;
	private List<ProcessName> pnList;
	private ProcessName pn;
	private CampanyName cn;
	private Users user;
	private String ceshiNo;
	private String campanyname;
	private Integer id;
	private Integer userId;
	private QRCodeKu qrcodeku;
	private List<QRCodeKu> qrcodekuList;
	private UserServer	userServer;
	private String no;
	private String strImg;
	private int size;
	private float nzsr;// 公司年总收入
	private float totalman;// 公司人工流层总人数
	private float totalpaci;// 公司人工年均收入
	private ResearchReport rr;
	private List<ResearchReport> rrList;
	
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String status;
	private String tag;

	public String initadd() {
		pnList = isserver.findAllpnlist();
		cpList = isserver.findAllcompanyviplist();
		if(no!=null && !"".equals(no)){
			qrcodeku = isserver.getqrcodekuByNo(no);
		}
		if (pnList != null && pnList.size() > 0) {
			size = pnList.size();
		}
		
//		int count=isserver.getcount();
//		int pageCount = (count + pageSize - 1) / pageSize;
//		this.setTotal(pageCount + "");
//		pnList=isserver.findAllpnlist(Integer.parseInt(cpage), pageSize);
//		if(pnList!=null){
//			this.setUrl("IntelligentDiagnosisAction_initadd.action");
//		}
		
		ceshiNo = isserver.findMaceshiNO();
		return "cn_add";
	}

	public String addpnList() {
		errorMessage = isserver.addpnlist(pnList);
		if ("true".equals(errorMessage)) {
			return "findAllpnlist";
		}
		return "pn_add";
	}

	public String findAllpnlist() {
		if (pn != null) {
			ActionContext.getContext().getSession().put("pn", pn);
		} else {
			pn = (ProcessName) ActionContext.getContext().getSession()
					.get("pn");
		}
		if ("del_true".equals(errorMessage)) {
			errorMessage = "删除成功!";
		} else if ("del_error".equals(errorMessage)) {
			errorMessage = "删除失败!";
		}

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = isserver.findpnListCondition(pn, Integer.parseInt(cpage),
				pageSize);
		pnList = (List<ProcessName>) map.get(1);
		if (pnList != null && pnList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if (status == null) {
				this.setUrl("IntelligentDiagnosisAction_findAllpnlist.action?");
			} else {
				this
						.setUrl("IntelligentDiagnosisAction_findAllpnlist.action?status="
								+ status);
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "pn_showlist";
	}

	public String updatepn() {
		if (pn != null) {
			if (isserver.updatepn(pn)) {
				errorMessage = "修改成功";
			}
		}
		return "pn_show";
	}

	public String delpn() {
		errorMessage = "del_error";
		if (isserver.delpn(pn)) {
			errorMessage = "del_true";
		}
		return "findAllpnlist";
	}

	public String getpnById() {
		if (id != null && id > 0) {
			pn = isserver.getpnByid(id);
		}
		return "pn_show";
	}

	public String addMore() {
		if(userId!=null && userId>0){
			cn.setUserId(userId);
		}else if(no!=null && !"".equals(no)){
			qrcodeku = isserver.getqrcodekuByNo(no);
			if(qrcodeku!=null){
				cn.setUserId(qrcodeku.getUserId());
			}
		}
		if (isserver.addMore(cn)) {
			return "finaAllcnList";
		}
		return "initadd";
	}

	public String finaAllcnList() {
		if (cn != null) {
			ActionContext.getContext().getSession().put("cn", cn);
		} else {
			cn = (CampanyName) ActionContext.getContext().getSession()
					.get("cn");
		}
		if ("del_true".equals(errorMessage)) {
			errorMessage = "删除成功!";
		} else if ("del_error".equals(errorMessage)) {
			errorMessage = "删除失败!";
		}

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = isserver.findcnListCondition(cn, Integer.parseInt(cpage),
				pageSize,status);
		cnList = (List<CampanyName>) map.get(1);
		if (cnList != null && cnList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("IntelligentDiagnosisAction_finaAllisList.action?status="
							+ status);

		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if("diaoyan".equals(status)){
			return "rrList_show";
		}
		return "cn_showlist";
	}

	public String delcn() {
		errorMessage = "del_error";
		if (isserver.del(cn)) {
			errorMessage = "del_true";
		}
		if("diaoyan".equals(status)){
			return "finaAllcnList_0";
		}
		return "finaAllcnList";
	}

	public String findisByid() {
		if ("update_error".equals(errorMessage)) {
			errorMessage = "删除失败!";
		} else if ("update_true".equals(errorMessage)) {
			errorMessage = "删除成功!";
		}
		cn = isserver.findIsbyid(id);
		isList = isserver.findislistByid(id);
		if (isList != null && isList.size() > 0) {
			size = isList.size();
		}
		return "cn_show";
	}

	public String findHzByid() {
		if(userId!=null && userId>0){
			user = isserver.getuserByid(userId);
		}else if(null!=no && !"".equals(no)){
			qrcodeku = isserver.getqrcodekuByNo(no);
		}
			cn = isserver.findIsbyid(id);
		if("diaoyan".equals(status)){
			if("update_true".equals(errorMessage)){
				errorMessage ="修改成功!~";
			}
			rrList = isserver.findrrLisById(id);
			return "rr_show";
		}
			if(cn!=null && cn.getUserId()!=null){
				user = isserver.getuserByid(cn.getUserId());
			}
		isList = isserver.findHz(id);
		return "cn_hzshow";
	}

	public String updateis() {
		errorMessage = "update_error";
		if (isserver.update(cn)) {
			id = cn.getId();
			errorMessage = "update_true";
		}
		return "findisByid";
	}
	//检验二维编号之前是否存在
	public void getqrcodekuByNo(){
		try {
			boolean bool = isserver.isqrcodeByNo(no);
			if(bool){
				qrcodeku = isserver.getqrcodekuByNo(no);
				if(qrcodeku!=null){
					MKUtil.writeJSON(bool,"true",qrcodeku);
				}
			}
			
			} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false,null,null);
		}
		
	}
	public String initadduser(){
	
		return "QRCOdetest";
	}
	public void addQRCodeKu(){
		try {
			boolean bool = isserver.addQRCode(qrcodeku, strImg);
			if(bool){
				if(qrcodeku!=null && qrcodeku.getId()>0){
					MKUtil.writeJSON(true, "true", qrcodeku);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false,null,null);
		}
		
		
	}
	
	public String adduser(){
		try {
			ceshiNo = isserver.findMaxCodeNO();
			user.setCode(ceshiNo);
			errorMessage =	userServer.addUser(user, null, null, null);
			if("true".equals(errorMessage)){
				return "QRCOdetest";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "error";
	}
	
	//查询所有二维码信息
	public String findAllQRCodeList(){
		if (qrcodeku != null) {
			ActionContext.getContext().getSession().put("qrcodeku", qrcodeku);
		} else {
			qrcodeku = (QRCodeKu) ActionContext.getContext().getSession()
					.get("qrcodeku");
		}
		if("success".equals(errorMessage)){
			errorMessage = "恭喜，已经可以发展下家";
		}else if("error".equals(errorMessage)){
			errorMessage = "成为可发展下的经销商失败";
		}
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map = isserver.findAllQRCodeList(qrcodeku, Integer.parseInt(cpage),
				pageSize,status);
		if("person".equals(status)){
			qrcodeku = isserver.findQRCodeKuByuserId(null);
		}
		qrcodekuList = (List<QRCodeKu>) map.get(1);
		if (qrcodekuList != null && qrcodekuList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("IntelligentDiagnosisAction_findAllQRCodeList.action?status="
							+ status);

		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "QRCodeList";
		
	}
	//成为可发展下家的
	public String updateQRcode1(){
		boolean bool = isserver.updateQRCode1(qrcodeku);
		errorMessage = "error";
		if(bool){
			errorMessage = "success";
		}
		return "findAllQRCodeList";
	}
	//添加调研报告
	public String addResearchReport(){
		errorMessage =isserver.addResearchReport(cn);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!";
			return "finaAllcnList_0";
		}
		return "rr_add";
	}
	//修改调研报告
	public String updateRr(){
		errorMessage = isserver.updateRr(rrList, id);
		if("true".equals(errorMessage)){
			errorMessage = "update_true";
		}
		return "findHzByid";
	}
	//删除某一条调研问题点
	public void delRr(){
		try {
			boolean bool =	isserver.delRr(rr);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false);
		}
	}
	
	public IntelligentDiagnosis getIs() {
		return is;
	}

	public void setIs(IntelligentDiagnosis is) {
		this.is = is;
	}

	public List<IntelligentDiagnosis> getIsList() {
		return isList;
	}

	public void setIsList(List<IntelligentDiagnosis> isList) {
		this.isList = isList;
	}

	public IntelligentDiagnosisServer getIsserver() {
		return isserver;
	}

	public void setIsserver(IntelligentDiagnosisServer isserver) {
		this.isserver = isserver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getNzsr() {
		return nzsr;
	}

	public void setNzsr(float nzsr) {
		this.nzsr = nzsr;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<CompanyVIP> getCpList() {
		return cpList;
	}

	public void setCpList(List<CompanyVIP> cpList) {
		this.cpList = cpList;
	}

	public String getCeshiNo() {
		return ceshiNo;
	}

	public void setCeshiNo(String ceshiNo) {
		this.ceshiNo = ceshiNo;
	}

	public float getTotalman() {
		return totalman;
	}

	public void setTotalman(float totalman) {
		this.totalman = totalman;
	}

	public float getTotalpaci() {
		return totalpaci;
	}

	public void setTotalpaci(float totalpaci) {
		this.totalpaci = totalpaci;
	}

	public String getCampanyname() {
		return campanyname;
	}

	public void setCampanyname(String campanyname) {
		this.campanyname = campanyname;
	}

	public List<CampanyName> getCnList() {
		return cnList;
	}

	public void setCnList(List<CampanyName> cnList) {
		this.cnList = cnList;
	}

	public CampanyName getCn() {
		return cn;
	}

	public void setCn(CampanyName cn) {
		this.cn = cn;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<ProcessName> getPnList() {
		return pnList;
	}

	public void setPnList(List<ProcessName> pnList) {
		this.pnList = pnList;
	}

	public ProcessName getPn() {
		return pn;
	}

	public void setPn(ProcessName pn) {
		this.pn = pn;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public QRCodeKu getQrcodeku() {
		return qrcodeku;
	}

	public void setQrcodeku(QRCodeKu qrcodeku) {
		this.qrcodeku = qrcodeku;
	}

	

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getStrImg() {
		return strImg;
	}

	public void setStrImg(String strImg) {
		this.strImg = strImg;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public List<QRCodeKu> getQrcodekuList() {
		return qrcodekuList;
	}

	public void setQrcodekuList(List<QRCodeKu> qrcodekuList) {
		this.qrcodekuList = qrcodekuList;
	}

	public ResearchReport getRr() {
		return rr;
	}

	public void setRr(ResearchReport rr) {
		this.rr = rr;
	}

	public List<ResearchReport> getRrList() {
		return rrList;
	}

	public void setRrList(List<ResearchReport> rrList) {
		this.rrList = rrList;
	}
	
}
