package com.task.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.MenuKeyListener;

import sun.net.www.content.image.gif;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.IntegralGiftServer;
import com.task.entity.Gift;
import com.task.entity.IgiftSet;
import com.task.entity.IndianaGift;
import com.task.entity.Integral;
import com.task.entity.IntegralGift;
import com.task.entity.Users;
import com.task.entity.XiaoFei;
import com.task.util.MKUtil;
import com.task.util.Util;

public class IntegralGiftAction {
	
	
	private IntegralGift integralgift;//礼品领取对象
	private Gift gift;//礼品对象
	private IgiftSet igSet;//活动时间设置对象
	private List<IgiftSet> igSetList;//
	private IndianaGift indianaGift;//夺宝系统对象
	private List<IndianaGift> idgiftList;
	private Users user;
	private List<IntegralGift> igList;
	private List<Gift> giftList;
	private IntegralGiftServer igserver;
	private Integer id;
	private Float xaIntegral;
	private String flag; 
	private String str;
	private String qihao;
	
	private String name;
	private Long sum;
	private int peoplenum;
	
	private Integral integral;//积分对象
	private List<XiaoFei> xfList ;//当前登录人最近20条消费记录;
	private List<IntegralGift> dhuserList;//当前登录人最近三条兑换记录
	private List<IntegralGift> dhAllList;//所有人最近十五条兑换记录
	private List<IntegralGift> zjuserList;//当前登录人最近三条兑换记录;
	private List<IntegralGift> zjAllList;//所有人最近十五条兑换记录;
	private String dhnum;//兑换码
	private  boolean bool;//
	
	private String attachment1;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	
	private String errorMessage;
	private String successMessage;
	
	private int pageSize = 15;
	private String cpage="1";
	private String total;
	private String url;
	private String status;
	
	
	public String addgift(){
		try {
			errorMessage = "添加失败";
			String filename = MKUtil.saveFile(attachment, attachmentFileName,"Gift" );
			gift.setPicture(filename);
			if(igserver.addgift(gift)){
				errorMessage = "添加成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "gift_add";
	}
	public String showgiftList(){
		try {
			if(gift!=null){
				ActionContext.getContext().getSession().put("gift", gift);
			}else{
				gift=(Gift) ActionContext.getContext().getSession().get("gift");
			}
			if("del_true".equals(errorMessage)){
				errorMessage = "删除成功";
			}else if("del_error".equals(errorMessage)){
				errorMessage = "删除失败";
			}
			Map<Integer, Object> map=new HashMap<Integer,Object>();
			map=igserver.findgiftByCondition(gift, Integer.parseInt(cpage), pageSize);
			giftList=(List<Gift>)map.get(1);
			if(giftList!=null&&giftList.size()>0){
				int count=(Integer)map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");  
				this.setUrl("IntegralGiftAction_showgiftList.action?status="+status);
			}
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "gift_List";
	
	}
	public String toupdategift(){
		try {
			if(id!=null && id>0){
				gift = igserver.getGiftById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "gift_update";
	}
	public String updategift(){
		errorMessage = "修改失败";
		Gift oldgift =  igserver.getGiftById(gift.getId());
		if (attachment!=null) {
			String filename = MKUtil.saveFile(attachment, attachmentFileName,"Gift" );
			gift.setPicture(filename);
		} else {
			gift.setPicture(oldgift.getPicture());
		}
		try {
			if(gift!=null){
				
				if(igserver.update(gift)){
					errorMessage = "修改成功";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "gift_update";
	}
	public String delgift(){
		errorMessage = "del_error";
		try {
			if(igserver.delgift(gift)){
					errorMessage = "del_true";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showgiftList";
	}
	public String initaddintegralgift(){
		try {
			if("lq_true".equals(errorMessage)){
				errorMessage = "兑换成功";
			}
			gift = igserver.getGiftById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Igift_add";
	}
	//领取礼品
	public String addintegralgift(){
		try {
			if(integralgift!=null){
				errorMessage =	igserver.addIntegralGift(integralgift,dhnum);
				if("true".equals(errorMessage)){
					errorMessage = "lq_true";
					return "initaddintegralgift";
				   }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "error";
	}
	
	public String showigList(){
		try {
			if(integralgift!=null){
				ActionContext.getContext().getSession().put("integralgift", integralgift);
			}else{
				integralgift=(IntegralGift) ActionContext.getContext().getSession().get("integralgift");
			}
			if("del_true".equals(errorMessage)){
				errorMessage = "删除成功";
			}else if("del_error".equals(errorMessage)){
				errorMessage = "删除失败";
			}
			Map<Integer, Object> map=new HashMap<Integer,Object>();
			map=igserver.findIGByCondition(integralgift, Integer.parseInt(cpage), pageSize,flag);
			igList=(List<IntegralGift>)map.get(1);
			if(igList!=null&&igList.size()>0){
				int count=(Integer)map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");  
				this.setUrl("IntegralGiftAction_showigList.action?status="+status);
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Igift_List";
	}
	public String delintegralgift(){
		try {
			errorMessage = "del_error";
			if(integralgift!=null){
				if(igserver.delIntegralGift(integralgift)){
					errorMessage = "del_true";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showigList";
	}
	//判断某人是否有足够的积分兑换礼品
	public void isenough(){
		try {
			boolean  bool = igserver.Isenough(id, xaIntegral);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	//把礼品变为可抽奖的
	public String Luckdraw(){
		try {
			if(igserver.Luckdraw(id)){
				return "showgiftList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	//当前登录人，兑换礼品
	public String initaddintegralgift1(){
		try {
			user = Util.getLoginUser();
			giftList = igserver.findAllGift();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Igift_add1";
	}
	
	//根据userId 获取 积分信息
	public String giftindex(){
		try {
		Map<String, Object> map = new HashMap<String, Object>();
			map =	igserver.GiftIndex();
			if(map!=null && map.size()>0){
				//当前登录人的积分信息
				if(map.get("integral")!=null){
					integral = (Integral) map.get("integral");
				}
				//当前登录人最近二十条消费记录
				if(map.get("xfList")!=null){
					xfList = (List<XiaoFei>) map.get("xfList");
				}
				//当前登录人最近三条兑换记录
				if(map.get("dhuserList")!=null){
					dhuserList = (List<IntegralGift>) map.get("dhuserList");
				}
				//所有人最近十五条兑换记录
				if(map.get("dhAllList")!=null){
					dhAllList = (List<IntegralGift>) map.get("dhAllList");
				}
				//当前登录人最近三条兑换记录;
				if(map.get("zjuserList")!=null){
					zjuserList = (List<IntegralGift>) map.get("zjuserList");
				}
				//所有人最近十五条兑换记录;
				if(map.get("zjAllList")!=null){
					zjAllList = (List<IntegralGift>) map.get("zjAllList");
				}
				bool = igserver.iscjzg();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gift_index";
	}
	// 初始化抽奖界面
	public String initchoujiang(){
		try {
			if(!igserver.findigSet()){
				errorMessage = "嘿嘿，现在还不是抽奖时间，审查元素改代码也不行哦!";
				return "error";
			}
			name = igserver.findcjgift();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gift_choujiang";
	}
	// 抽奖扣除相应积分，减去相应库存
	public void choujiang(){
		try {
			errorMessage = igserver.choujiang(id, name);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	//根据userId 和兑换码 兑换奖品
	public void dhchoujiang(){
		try {
			Users user =	Util.getLoginUser();
			gift =	igserver.dhchoujiang(user.getId(), dhnum);
			if(gift!=null){
				MKUtil.writeJSON(gift);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
		
	}
	
	//获得最近三条中奖记录;
	public void findzjIgift(){
		try {
			errorMessage =	igserver.findZJIgift();
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}
	
	//设置抽奖时间
	public String addigSet(){
		if(igSet!=null){
			errorMessage =	igserver.addigSet(igSet);
			if("true".equals(errorMessage)){
				errorMessage = "设置成功";
			}
		}
		return "igift_set";
	}
	//查询最近一次抽奖时设置;判断当前时间是否在抽奖时间内
	public void iscjTime(){
		try {
			boolean bool = igserver.findigSet();
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
		
	}
	//赠送红心;
	public String Giveintegral(){
		errorMessage =	igserver.Giveintegral(integral);
		if("true".equals(errorMessage)){
			errorMessage = "赠送成功!";
			return "integral_jy";
		}
		return "error";
	}
	//报名参与夺宝
	public String addindian(){
		errorMessage =igserver.addindian(indianaGift);
		if("true".equals(errorMessage)){
			errorMessage = "报名成功,请等待开奖!";
		}
		return "error";
	}
	//计算中奖编号;计算公式为:(50个时间之和+时时彩5位开奖数字（传过来的str）)%奖品总需人次+10000001 = 幸运号码
	public  String jszjnum(){
			errorMessage = igserver.jszjnum(str,qihao);
			if(errorMessage!=null && !"".equals(errorMessage)
					&& errorMessage.indexOf("true")>=0){
			String[] array	=errorMessage.split("_");
				if(array!=null && array.length == 2){
					errorMessage =	igserver.zjuser(array[1 ],qihao);
				}
				
			}
		return "indianaGift_kj";
	}
	//查询出所有活动
	public String findAlligSetList(){
		int count=igserver.getigSetcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		igSetList=igserver.AlligSetList(Integer.parseInt(cpage), pageSize);
		if(igSetList!=null){
			this.setUrl("IntegralGiftAction_findAlligSetList.action");
		}
		errorMessage="暂时还没有不合格品";
		if("baoming".equals(status)){
			return "igSet_list";
		}
		return "igSet_showlist";
	}
	//根据活动Id查出参加活动人员信息
	public String findidgiftList(){
		try {
			Map<Integer, Object> map=new HashMap<Integer,Object>();
			map=igserver.findidgift(id, Integer.parseInt(cpage), pageSize);
			idgiftList=(List<IndianaGift>)map.get(1);
			if(idgiftList!=null&&idgiftList.size()>0){
				int count=(Integer)map.get(2);
				sum = (Long) map.get(3);
				peoplenum = (Integer)map.get(4);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");  
				this.setUrl("IntegralGiftAction_findidgiftList.action?id="+id);
			}else{
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "idgift_list";
	
	}
	public String initaddigSet(){
		giftList = igserver.findgiftList();
		return "igift_set";
	}
	public IntegralGift getIntegralgift() {
		return integralgift;
	}
	public void setIntegralgift(IntegralGift integralgift) {
		this.integralgift = integralgift;
	}
	public Gift getGift() {
		return gift;
	}
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	public List<IntegralGift> getIgList() {
		return igList;
	}
	public void setIgList(List<IntegralGift> igList) {
		this.igList = igList;
	}
	public List<Gift> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<Gift> giftList) {
		this.giftList = giftList;
	}
	public IntegralGiftServer getIgserver() {
		return igserver;
	}
	public void setIgserver(IntegralGiftServer igserver) {
		this.igserver = igserver;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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
	 
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Float getXaIntegral() {
		return xaIntegral;
	}
	public void setXaIntegral(Float xaIntegral) {
		this.xaIntegral = xaIntegral;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Integral getIntegral() {
		return integral;
	}
	public void setIntegral(Integral integral) {
		this.integral = integral;
	}
	public List<XiaoFei> getXfList() {
		return xfList;
	}
	public void setXfList(List<XiaoFei> xfList) {
		this.xfList = xfList;
	}
	public List<IntegralGift> getDhuserList() {
		return dhuserList;
	}
	public void setDhuserList(List<IntegralGift> dhuserList) {
		this.dhuserList = dhuserList;
	}
	public List<IntegralGift> getDhAllList() {
		return dhAllList;
	}
	public void setDhAllList(List<IntegralGift> dhAllList) {
		this.dhAllList = dhAllList;
	}
	public List<IntegralGift> getZjuserList() {
		return zjuserList;
	}
	public void setZjuserList(List<IntegralGift> zjuserList) {
		this.zjuserList = zjuserList;
	}
	public List<IntegralGift> getZjAllList() {
		return zjAllList;
	}
	public void setZjAllList(List<IntegralGift> zjAllList) {
		this.zjAllList = zjAllList;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttachment1() {
		return attachment1;
	}
	public void setAttachment1(String attachment1) {
		this.attachment1 = attachment1;
	}
	public File[] getAttachment() {
		return attachment;
	}
	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}
	public String[] getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String[] attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	public String[] getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String[] attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getDhnum() {
		return dhnum;
	}
	public void setDhnum(String dhnum) {
		this.dhnum = dhnum;
	}
	public IgiftSet getIgSet() {
		return igSet;
	}
	public void setIgSet(IgiftSet igSet) {
		this.igSet = igSet;
	}
	public boolean isBool() {
		return bool;
	}
	public void setBool(boolean bool) {
		this.bool = bool;
	}
	public IndianaGift getIndianaGift() {
		return indianaGift;
	}
	public void setIndianaGift(IndianaGift indianaGift) {
		this.indianaGift = indianaGift;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public List<IgiftSet> getIgSetList() {
		return igSetList;
	}
	public void setIgSetList(List<IgiftSet> igSetList) {
		this.igSetList = igSetList;
	}
	public String getQihao() {
		return qihao;
	}
	public void setQihao(String qihao) {
		this.qihao = qihao;
	}
	public List<IndianaGift> getIdgiftList() {
		return idgiftList;
	}
	public void setIdgiftList(List<IndianaGift> idgiftList) {
		this.idgiftList = idgiftList;
	}
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	public int getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(int peoplenum) {
		this.peoplenum = peoplenum;
	}

	
	
	

}
