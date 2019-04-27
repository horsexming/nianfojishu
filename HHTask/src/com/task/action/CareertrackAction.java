package com.task.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.CareertrackServer;
import com.task.entity.AssScore;
import com.task.entity.Careertrack;
import com.task.entity.Promotion;
import com.task.entity.RewardPunish;
import com.task.entity.Transfer;
import com.task.entity.Users;
import com.task.entity.renshi.DimissionLog;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.sop.BuHeGePin;
import com.task.util.MKUtil;
import com.task.util.Util;

public class CareertrackAction extends ActionSupport {
	private Careertrack ck;
	private Users user;
	private List<Careertrack> ckList;
	private CareertrackServer cksever;
	private Integer id;
	private Integer minashiId;//面试入职Id
	private Integer tianshu;//试用期期的天数(30天内显示)
	private AssScore zzkh;//转正考核成绩；
	private List<AssScore> jxList;//个人绩效(最近三条)
	private List<RewardPunish> jcList;//个人奖惩(最近三条);
	private Transfer transfer;//个人调动记录;(最近一条)
	private Promotion promotion;//个人晋升记录(最近一条);
	private Dimission_Handover lzjj;//离职交接
	private DimissionLog lzsq;//离职申请
	private Integer zzhtId;//劳动协议终止
	private Integer lztzId;//离职通知
	private Integer lzgzId;//离职工资
	private int jxsize ;
	private int jcsize;
	
	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String statue;
	private String tag;
	
	public String showckList(){
		int count=cksever.getcont();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		ckList=cksever.FindAllCareertrack(Integer.parseInt(cpage), pageSize);
		if(ckList!=null){
			this.setUrl("CareertrackAction_showckList.action");
			
		}else{
			errorMessage="没有员工职业轨迹";
		}
		return "Careertrack_showlist";
	}
	public String findckList(){
		if(ck!=null){
			ActionContext.getContext().getSession().put("ck", ck);
		}else{
			ck=(Careertrack) ActionContext.getContext().getSession().get("ck");
		}
		
		Map<Integer, Object> map=new HashMap<Integer,Object>();
		map=cksever.findCareertrackByCondition(ck, Integer.parseInt(cpage), pageSize,statue);
		ckList=(List<Careertrack>) map.get(1);
		if(ckList!=null && ckList.size()>0){
			int count=(Integer)map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");  
			this.setUrl("CareertrackAction_findckList.action?statue");
			
		}else{
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Careertrack_showlist";
	}
	
	public String showckById(){
		Map<String, Object> map = new HashMap<String, Object>();
		if(id!=null && id >0){
			ck = cksever.showCareertrackByid(id);
			 user = cksever.findUsersByckId(id);
		map=cksever.findUsersById(id);
		if(map!=null && map.size()>0){
			if(map.get("minashiId")!=null && (Integer)map.get("minashiId")>0){
				minashiId =(Integer) map.get("minashiId");
			}
			if (map.get("lzsq")!=null){
				lzsq = (DimissionLog) map.get("lzsq");
			}
			if(map.get("lzjj")!=null){
				lzjj = (Dimission_Handover) map.get("lzjj");
			}
			
			if (map.get("zzhtId")!=null && (Integer)map.get("zzhtId")>0){
				zzhtId =(Integer)map.get("zzhtId");
			}
			if (map.get("lztzId")!=null && (Integer)map.get("lztzId")>0){
				lztzId =(Integer)map.get("lztzId");
			}
			if (map.get("lzgzId")!=null && (Integer)map.get("lzgzId")>0){
				lzgzId =(Integer)map.get("lzgzId");
			}
			if(map.get("jxList")!=null){
				jxList = (List<AssScore>) map.get("jxList");
				jxsize = jxList.size();
			}
			if(map.get("jcList")!=null){
				jcList = (List<RewardPunish>) map.get("jcList");
				jcsize = jcList.size();
			}
			if(map.get("transfer")!=null){
				transfer = (Transfer) map.get("transfer");
			}
			if(map.get("promotion")!=null){
				promotion =  (Promotion) map.get("promotion");
			}
			if(map.get("tianshu")!=null){
				tianshu = (Integer) map.get("tianshu");
			}
			if(map.get("zzkh")!=null){
				zzkh = (AssScore) map.get("zzkh");
			}
			
		}
		errorMessage = "该员工下，没有获得任何数据。";
		}
		errorMessage = "该用户Id不存在!";
		return "Careertrack_ByUid";
	}
	public String  addMore(){
	boolean bool =	cksever.addMore();
		if(bool){
			try {
				MKUtil.writeJSON("true");
			} catch (Exception e) {
				MKUtil.writeJSON(e);
				e.printStackTrace();
			}
		}
		return ERROR;
	}
	public String tiaozhuan(){
		 user = cksever.findUsersByckId(id);
		 if(user!=null){
			 return tag;
		 }
		return ERROR;
	}
	//查看职业轨迹个人
	public String  findckbyuserId(){
		user = Util.getLoginUser();
		ck =cksever.findCareertrackByuId(user.getId());
		if(ck!=null){
			id = ck.getId();
		}
		return "showckById";
	}
	public String xiufuUserId(){
		cksever.xiufuUserId();
		return "error";
	}
	public String shuaixin(){
		cksever.shuaixin();
		return "error";
	}
	public Careertrack getCk() {
		return ck;
	}
	public void setCk(Careertrack ck) {
		this.ck = ck;
	}
	public CareertrackServer getCksever() {
		return cksever;
	}
	public void setCksever(CareertrackServer cksever) {
		this.cksever = cksever;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMinashiId() {
		return minashiId;
	}
	public void setMinashiId(Integer minashiId) {
		this.minashiId = minashiId;
	}
	public Integer getZzhtId() {
		return zzhtId;
	}
	public void setZzhtId(Integer zzhtId) {
		this.zzhtId = zzhtId;
	}
	public Integer getLztzId() {
		return lztzId;
	}
	public void setLztzId(Integer lztzId) {
		this.lztzId = lztzId;
	}
	public Integer getLzgzId() {
		return lzgzId;
	}
	public void setLzgzId(Integer lzgzId) {
		this.lzgzId = lzgzId;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public List<Careertrack> getCkList() {
		return ckList;
	}

	public void setCkList(List<Careertrack> ckList) {
		this.ckList = ckList;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public List<AssScore> getJxList() {
		return jxList;
	}
	public void setJxList(List<AssScore> jxList) {
		this.jxList = jxList;
	}
	public List<RewardPunish> getJcList() {
		return jcList;
	}
	public void setJcList(List<RewardPunish> jcList) {
		this.jcList = jcList;
	}
	public int getJxsize() {
		return jxsize;
	}
	public void setJxsize(int jxsize) {
		this.jxsize = jxsize;
	}
	public int getJcsize() {
		return jcsize;
	}
	public void setJcsize(int jcsize) {
		this.jcsize = jcsize;
	}
	public Dimission_Handover getLzjj() {
		return lzjj;
	}
	public void setLzjj(Dimission_Handover lzjj) {
		this.lzjj = lzjj;
	}
	public DimissionLog getLzsq() {
		return lzsq;
	}
	public void setLzsq(DimissionLog lzsq) {
		this.lzsq = lzsq;
	}
	public Transfer getTransfer() {
		return transfer;
	}
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
	public Promotion getPromotion() {
		return promotion;
	}
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	public Integer getTianshu() {
		return tianshu;
	}
	public void setTianshu(Integer tianshu) {
		this.tianshu = tianshu;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public AssScore getZzkh() {
		return zzkh;
	}
	public void setZzkh(AssScore zzkh) {
		this.zzkh = zzkh;
	}
	
	
	

}
