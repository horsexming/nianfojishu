package com.task.action.jxkh;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.jxkh.JiaoXiaoKaoHeServer;
import com.task.entity.jxkh.BmzZlh;
import com.task.entity.jxkh.DeptFenPeiXiShu;
import com.task.entity.jxkh.DeptUsersDuty;
import com.task.entity.jxkh.FenPeiQingKuang;
import com.task.entity.jxkh.InCome;
import com.task.entity.jxkh.RankingCounts;
import com.task.entity.jxkh.SalesAmountCoefficient;
import com.task.entity.jxkh.TargetAchievedMark;
import com.task.entity.jxkh.WaiWeiJieGouMuBiao;
import com.task.entity.jxkh.WeiWaiJieGou;
import com.task.entity.jxkh.WorkShopTiQu;
import com.task.entity.jxkh.WorkShopTiQuMx;
import com.task.entity.jxkh.WorkShopXiaoLvZz;
import com.task.entity.jxkh.YearImprove;
import com.task.entity.jxkh.ZbSjZk;
import com.task.entity.jxkh.ZhiZaoPingJia;
import com.task.entity.jxkh.DeptLeaderPenPei;
import com.task.util.MKUtil;
import com.task.util.Util;

public class JiaoXiaoKaoHeAction extends ActionSupport{
	private JiaoXiaoKaoHeServer jxkhServer;// Server层
	private DeptUsersDuty dud;//
	private List<DeptUsersDuty> dudList;
	private RankingCounts rankCounts;
	private List<RankingCounts> rankCountsList;
	private TargetAchievedMark tam;
	private List<TargetAchievedMark> tamList;
	private WaiWeiJieGouMuBiao wwjgmb;
	private List<WaiWeiJieGouMuBiao> wwjgmbList;
	private SalesAmountCoefficient sac;
	private List<SalesAmountCoefficient> sacList;
	private List<BmzZlh> bmzzlhList;
	private BmzZlh bmzzlh;
	private List<WeiWaiJieGou> wwjgList;
	private WeiWaiJieGou wwjg;
	private WorkShopXiaoLvZz wsxlzz;
	private List<WorkShopXiaoLvZz> wsxlzzList;
	private ZhiZaoPingJia zzpj;
	private ZbSjZk zbSjZk;
	private List<ZbSjZk> zbSjZkList;
	private List<WorkShopTiQuMx> wstqmxList;
	private WorkShopTiQuMx wstqmx;
	private WorkShopTiQu wstq;
	private List<WorkShopTiQu> wstqList;
	private FenPeiQingKuang fpqk;
	private List<FenPeiQingKuang> fpqkList;
	private DeptFenPeiXiShu dfpx;// 
	private List<DeptFenPeiXiShu> dfpxList;
	private List<DeptLeaderPenPei> dlppList;
	private YearImprove yearimprove;
	private List<YearImprove> yearimproveList;
	private InCome inCome;
	private List<InCome> inComeList;
	
	private Integer monthsNo;
	private String years;
	private String name;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private String pageStatus;// 页面状态
	private String months;
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	/**
	 * /部门职责确定
	 * @return
	 */
	
	//添加
	public String addDeptUsersDuty(){
		errorMessage =	jxkhServer.addDeptUsersDuty(dud);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "dud_add";
	}
	//查询
	public String findAllDudList(){
		if (dud != null) {
			ActionContext.getContext().getSession().put("dud", dud);
		} else {
			dud = (DeptUsersDuty) ActionContext.getContext().getSession()
					.get("dud");
		}
		Object[] obj =	jxkhServer.findAllDudList(dud, pageSize, Integer.parseInt(cpage), pageStatus);
		dudList = (List<DeptUsersDuty>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllDudList.action?pageStatus="+pageStatus);
		return "dud_showList";
	} 
	//Id查询
	public String findDudById(){
		dud =jxkhServer.findDudById(id);
		return "dud_update";
	}
	
	public void findDudById0(){
		try {
			dud =	jxkhServer.findDudById(id);
			MKUtil.writeJSON(dud);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询
	public void findDudList(){
		try {
			dudList =	jxkhServer.findDudList(dud, pageStatus);
			MKUtil.writeJSON(dudList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//修改
	public String updateDud(){
		errorMessage =	jxkhServer.updateDud(dud);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "dud_update";
	}
	//删除
	public String delDud(){
		errorMessage =jxkhServer.delDud(dud);
		return "findAllDudList";
	}
	/**
	 * 名次人数确定表 
	 * @return
	 */
	//添加
	public String addRankingCounts(){
		errorMessage = jxkhServer.addRankingCounts(rankCounts);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "rankCounts_add";
	}
	//查询
	public String findAllRankCountsList(){
		if (rankCounts != null) {
			ActionContext.getContext().getSession().put("rankCounts", rankCounts);
		} else {
			rankCounts = (RankingCounts) ActionContext.getContext().getSession()
					.get("rankCounts");
		}
		Object[] obj =	jxkhServer.findAllRankCountsList(rankCounts, pageSize, Integer.parseInt(cpage), pageStatus);
		rankCountsList = (List<RankingCounts>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllRankCountsList.action?pageStatus="+pageStatus);
		return "rankCounts_showList";
	}
	//修改
	public String updateRankCounts(){
		errorMessage =	jxkhServer.updateRankCounts(rankCounts);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "rankCounts_update";
	}
	//Id查询
	public String findRankCountsById(){
		rankCounts = jxkhServer.findRankCountsById(id);
		return "rankCounts_update";
	}
	//删除
	public String delRankCounts(){
		errorMessage =	jxkhServer.delRankCounts(rankCounts);
		return "findAllRankCountsList";
	}
	/**
	 *月销售额与提取系数关系表 
	 */
	//添加
	public String addsac(){
		errorMessage =jxkhServer.addsac(sac);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "sac_add";
	}
	//查询
	public String findAllSacList(){
		if (sac != null) {
			ActionContext.getContext().getSession().put("sac", sac);
		} else {
			sac = (SalesAmountCoefficient) ActionContext.getContext().getSession()
					.get("sac");
		}
		Object[] obj =	jxkhServer.findAllSacList(sac, pageSize, Integer.parseInt(cpage), pageStatus);
		sacList = (List<SalesAmountCoefficient>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllSacList.action?pageStatus="+pageStatus);
		return "sac_showList";
	}
	//Id查询
	public String getsacById(){
		sac	= jxkhServer.getsacById(sac);
		return "sac_update";
	}
	//获取当前最大金额
	
	public void getMaxSac(){
		try {
			sac =jxkhServer.updateMaxSac();
			MKUtil.writeJSON(sac);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//修改
	public String updateSac(){
		errorMessage =	jxkhServer.updateSac(sac);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "sac_update";
	}
	//删除
	public String delSac(){
		errorMessage = jxkhServer.delSac(sac);
		return "findAllSacList";
	}
	/**
	 * 目标达成未达成加减分表
	 * @return
	 */
	//添加
	public String addTargetAchievedMark(){
		errorMessage =jxkhServer.addTargetAchievedMark(tam);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "tam_add";
	}
	//查询
	public String findAllTamList(){
		if (tam != null) {
			ActionContext.getContext().getSession().put("tam", tam);
		} else {
			tam = (TargetAchievedMark) ActionContext.getContext().getSession()
					.get("tam");
		}
		Object[] obj =	jxkhServer.findAllTamList(tam, pageSize, Integer.parseInt(cpage), pageStatus);
		tamList = (List<TargetAchievedMark>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllTamList.action?pageStatus="+pageStatus);
		return "tam_showList";
	}
	//单个查询
	public String findTamBy(){
		tam =	jxkhServer.findTamBy(tam);
		return "tam_update";
	}
	//修改
	public String updateTam(){
		errorMessage =	jxkhServer.updateTam(tam);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "tam_update";
	}
	//删除
	public String delTam(){
		errorMessage =jxkhServer.delTam(tam);
		return "findAllTamList";
	}
	/**
	 * 委外结构目标值表 :WaiWeiJieGouMuBiao
	 * @return
	 */
	//添加
	public String addWwJgMb(){
		errorMessage =jxkhServer.addWwJgMb(wwjgmb);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "wwjgmb_add";
	}
	//查询
	public String findAllWwJgMbList(){
		if (wwjgmb != null) {
			ActionContext.getContext().getSession().put("wwjgmb", wwjgmb);
		} else {
			wwjgmb = (WaiWeiJieGouMuBiao) ActionContext.getContext().getSession()
					.get("wwjgmb");
		}
		Object[] obj =	jxkhServer.findAllWwJgMbList(wwjgmb, pageSize, Integer.parseInt(cpage), pageStatus);
		wwjgmbList = (List<WaiWeiJieGouMuBiao>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllWwJgMbList.action?pageStatus="+pageStatus);
		return "wwjgmb_showList";
	}
	//查询单个
	public String findWwJgMbBy(){
		wwjgmb = jxkhServer.findWwJgMbBy(wwjgmb);
		return "wwjgmb_update";
	}
	public void findWwJgMbBy0(){
		try {
			wwjgmb = jxkhServer.findWwJgMbBy(wwjgmb);
			MKUtil.writeJSON(wwjgmb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改
	public String updateWwJgMb(){
		errorMessage =jxkhServer.updateWwJgMb(wwjgmb);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "wwjgmb_update";
	}
	//删除
	public String dleWwJgMb(){
		errorMessage =jxkhServer.dleWwJgMb(wwjgmb);
		return "findAllWwJgMbList";
	}
	/**
	 * 部门长周例会  BmzZlh 
	 * @return
	 */
	//添加
	public String addBmzZlh(){
		errorMessage =jxkhServer.addBmzZlh(bmzzlh);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "bmzzlh_add";
	}
	
	//ajax 添加
	public void addBmzZlh0(){
		try {
			errorMessage =jxkhServer.addBmzZlh0(id,bmzzlh);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询
	public String findAllBmzZlhList(){
		if (bmzzlh != null) {
			ActionContext.getContext().getSession().put("bmzzlh", bmzzlh);
		} else {
			bmzzlh = (BmzZlh) ActionContext.getContext().getSession()
					.get("bmzzlh");
		}
		Object[] obj =	jxkhServer.findAllBmzZlhList(bmzzlh, pageSize, Integer.parseInt(cpage), pageStatus);
		bmzzlhList = (List<BmzZlh>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllBmzZlhList.action?pageStatus="+pageStatus);
		return "bmzzlh_showList";
	}
	//查询单个
	public String findbmzzlhById(){
		bmzzlh =jxkhServer.findbmzzlhById(id);
		return "bmzzlh_show";
	}
	//条件查询多个
	public void findbmzzlhList(){
		try {
			if(bmzzlh==null){
				bmzzlh = new BmzZlh();
			}
			bmzzlh.setLeaderName(name);
			bmzzlh.setMonths(years);
			 Object[] obj =	jxkhServer.findbmzzlList(bmzzlh);
			MKUtil.writeJSON(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改
	public String updateBmzZlh(){
		errorMessage =jxkhServer.updateBmzZlh(bmzzlh);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "bmzzlh_update";
	}
	/**
	 * 委外比结构比表 : WeiWaiJieGou
	 * @return
	 */
	//ajax_查询
	public void findWwjgListBy(){
		try {
			Object[] obj =	jxkhServer.findWwjgBy(wwjg);
			MKUtil.writeJSON(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加
	public void addwwjg(){
		try {
			errorMessage = jxkhServer.addWeiWaiJieGou0(wwjg, pageStatus);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 车间效率增长情况表:WorkShopXiaoLvZz
	 * @return
	 */
	//查询
	public void findWsxlzBy(){
		try {
			Object[] obj = jxkhServer.findWsxlzBy(wsxlzz);
			MKUtil.writeJSON(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加、修改
	public void addWsxlz(){
		try {
			errorMessage =	jxkhServer.addWorkShopXiaoLvZz(wsxlzz);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 制造评价:(ZhiZaoPingJia)
	 * @return
	 */
	//查询
	public void findZzpjBy(){
		try {
			Object[] obj = jxkhServer.findZzpjBy(zzpj);
			MKUtil.writeJSON(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//添加 修改制造评价
	public void addZzpj(){
		try {
			errorMessage =	jxkhServer.addZzpj(zzpj);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 指标实际状况
	 * @return
	 */
	//添加
	public String addzbSjZk(){
		errorMessage =	jxkhServer.addZbSjZk(zbSjZk);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "zbSjZk_add";
	}
	//查询
	public String findAllzbSjZk(){
		if (zbSjZk != null) {
			ActionContext.getContext().getSession().put("zbSjZk", zbSjZk);
		} else {
			zbSjZk = (ZbSjZk) ActionContext.getContext().getSession()
					.get("zbSjZk");
		}
		Object[] obj =	jxkhServer.findAllZbSjZk(zbSjZk, pageSize, Integer.parseInt(cpage), pageStatus);
		zbSjZkList = (List<ZbSjZk>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllzbSjZk.action?pageStatus="+pageStatus);
		return "zbSjZk_showList";
	}
	//修改
	public String updatezbSjZk(){
		errorMessage = jxkhServer.updateZbSjZk(zbSjZk);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "zbSjZk_update";
	}
	//根据Id查询
	public String findzbSjZkbyId(){
		zbSjZk	=jxkhServer.findzbSjZkbyId(id);
		return "zbSjZk_update";
	}
	//删除
	public String delzbSjZk(){
		errorMessage = jxkhServer.delZbSjZk(zbSjZk);
		return "findAllzbSjZk";
	}
	/**
	 * 车间工资中提取明细 :(WorkShopTiQuMx)
	 * @return
	 */
	
	//添加
	public String addWstqmx(){
		errorMessage = jxkhServer.addWstqmx(wstqmx);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "wstqmx_add";
	}
	//查询
	public String findAllWstqmx(){
		if (wstqmx != null) {
			ActionContext.getContext().getSession().put("wstqmx", wstqmx);
		} else {
			wstqmx = (WorkShopTiQuMx) ActionContext.getContext().getSession()
					.get("wstqmx");
		}
		Object[] obj =	jxkhServer.findAllWstqmx(wstqmx, pageSize, Integer.parseInt(cpage), pageStatus);
		wstqmxList = (List<WorkShopTiQuMx>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllWstqmx.action?pageStatus="+pageStatus);
		return "wstqmx_List";
	}
	//修改
	public String updateWstqmx(){
		errorMessage =	jxkhServer.updateWstqmx(wstqmx);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "wstqmx_update";
	}
	//删除
	public String delWstqmx(){
		errorMessage = jxkhServer.delWstqmx(wstqmx);
		return "findAllWstqmx";
	}
	//根据Id查询
	public String findWstqmxById(){
		wstqmx =	jxkhServer.findWstqmxById(id);
		return "wstqmx_update";
	}
	/**
	 * 分配情况表
	 * @return
	 */
	// 获取当前月份各部门分配情况
	public String huoquFenPeiQingkuang(){
		if(null == months || months.length()==0){
			months =Util.getDateTime("yyyy-MM");
		}
		try {
			Object[] obj =jxkhServer.huoquFenPeiQingkuang(months);
			fpqkList = (List<FenPeiQingKuang>) obj[0];
			errorMessage = (String) obj[1];
			dud = new DeptUsersDuty();
			dud.setOneOrTwo(1);
			dudList = jxkhServer.findDudList(dud, "");
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage =e.getMessage();
		}
		return "fpqk_showList";
	}
	public void huoquFenPeiQingkuang0(){
		try {
			Object[] obj =jxkhServer.huoquFenPeiQingkuang(months);
			fpqkList = (List<FenPeiQingKuang>) obj[0];
			MKUtil.writeJSON(fpqkList);
		} catch (Exception e) {
			MKUtil.writeJSON("error");
		}
	}
	
	/**
	 * 各部门分配系数表:(ta_DeptFenPeiXiShu)
	 * @return
	 */
	//添加
	public String addfpxs(){
		errorMessage =jxkhServer.adddfpxs(dfpx);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "dfpx_add";
	}
	//查询
	public String findAlldfpxs(){
		if (dfpx != null) {
			ActionContext.getContext().getSession().put("dfpx", dfpx);
		} else {
			dfpx = (DeptFenPeiXiShu) ActionContext.getContext().getSession()
					.get("dfpx");
		}
		Object[] obj =	jxkhServer.findAlldfpxs(dfpx, pageSize, Integer.parseInt(cpage), pageStatus);
		dfpxList = (List<DeptFenPeiXiShu>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAlldfpxs.action?pageStatus="+pageStatus);
		return "dfpx_List";
	}
	//修改
	public String updatedfpxs(){
		errorMessage =jxkhServer.updatedfpxs(dfpx);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "dfpx_update";
	}
	//删除
	public String deldfpxs(){
		errorMessage= jxkhServer.deldfpxs(dfpx);
		return "findAlldfpxs";
	}
	//根据Id查询
	public String getdppxsById(){
		dfpx =jxkhServer.getdppxsById(id);
		return "dfpx_update";
	}
	/**
	 * 部门长分配情况
	 * @return
	 */
	//获取部门长分配情况信息
	public String huoqudlppList(){
		try {
			dlppList =	jxkhServer.huoqudlppList(months);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dlpp_showList";
	}
	//完善部门长分配排名信息
	public String updatedlppList(){
		dlppList=jxkhServer.updatedlppList(dlppList, months);
		return "dlpp_showList";
	}
	
	/**
	 * 年度改善自选 YearImprove
	 * @return
	 */
	//添加
	public String addyearimprove(){
		errorMessage =	jxkhServer.addyearimprove(yearimprove);
		if("true".equals(errorMessage)){
			errorMessage = "添加成功!~";
		}
		return "yearimprove_add";
	}
	//查询
	public String findAllyiveList(){
		if (yearimprove != null) {
			ActionContext.getContext().getSession().put("yearimprove", yearimprove);
		} else {
			yearimprove = (YearImprove) ActionContext.getContext().getSession()
					.get("yearimprove");
		}
		Object[] obj =	jxkhServer.findAllyiveList(yearimprove,pageStatus, pageSize, Integer.parseInt(cpage));
		yearimproveList = (List<YearImprove>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllyiveList.action?pageStatus="+pageStatus);
		return "yearimprove_List";
	}
	//id查询
	public String findyearimproveById(){
		yearimprove =	jxkhServer.findyearimproveById(id);
		return "yearimprove_update";
	}
	//更新
	public String updateyearimprove(){
		errorMessage =	jxkhServer.updateyearimprove(yearimprove);
		if("true".equals(errorMessage)){
			errorMessage = "修改成功!~";
		}
		return "yearimprove_update";
	}
	//删除
	public String delyearimprove(){
		errorMessage =	jxkhServer.delyearimprove(yearimprove);
		return "findAllyiveList";
	}
	/**
	 * 收入情况表
	 * @return
	 */
	
	//查询所有
	public String findAllInComeList(){
		if (inCome != null) {
			ActionContext.getContext().getSession().put("inCome", inCome);
		} else {
			inCome = (InCome) ActionContext.getContext().getSession()
					.get("inCome");
		}
		Object[] obj =	jxkhServer.findAllInComeList(inCome,months, pageSize, Integer.parseInt(cpage));
		inComeList = (List<InCome>) obj[0];
		int count =(Integer) obj[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("JiaoXiaoKaoHeAction_findAllInComeList.action?pageStatus="+pageStatus);
		return "inCome_List";
	}
	//
	public String getInComeById(){
		inCome = jxkhServer.getInComeById(id);
		return "inCome_show";
	}
	//计算收入
	public String wanShaInCome(){
		errorMessage =	jxkhServer.wanShaInCome(inCome);
		if("true".equals(errorMessage)){
			errorMessage = "计算完成";
		}
		return "inCome_show";
	}
	
	
	public JiaoXiaoKaoHeServer getJxkhServer() {
		return jxkhServer;
	}
	public void setJxkhServer(JiaoXiaoKaoHeServer jxkhServer) {
		this.jxkhServer = jxkhServer;
	}
	public DeptUsersDuty getDud() {
		return dud;
	}
	public void setDud(DeptUsersDuty dud) {
		this.dud = dud;
	}
	public List<DeptUsersDuty> getDudList() {
		return dudList;
	}
	public void setDudList(List<DeptUsersDuty> dudList) {
		this.dudList = dudList;
	}
	public RankingCounts getRankCounts() {
		return rankCounts;
	}
	public void setRankCounts(RankingCounts rankCounts) {
		this.rankCounts = rankCounts;
	}
	public List<RankingCounts> getRankCountsList() {
		return rankCountsList;
	}
	public void setRankCountsList(List<RankingCounts> rankCountsList) {
		this.rankCountsList = rankCountsList;
	}
	public TargetAchievedMark getTam() {
		return tam;
	}
	public void setTam(TargetAchievedMark tam) {
		this.tam = tam;
	}
	public List<TargetAchievedMark> getTamList() {
		return tamList;
	}
	public void setTamList(List<TargetAchievedMark> tamList) {
		this.tamList = tamList;
	}
	public WaiWeiJieGouMuBiao getWwjgmb() {
		return wwjgmb;
	}
	public void setWwjgmb(WaiWeiJieGouMuBiao wwjgmb) {
		this.wwjgmb = wwjgmb;
	}
	public List<WaiWeiJieGouMuBiao> getWwjgmbList() {
		return wwjgmbList;
	}
	public void setWwjgmbList(List<WaiWeiJieGouMuBiao> wwjgmbList) {
		this.wwjgmbList = wwjgmbList;
	}
	public SalesAmountCoefficient getSac() {
		return sac;
	}
	public void setSac(SalesAmountCoefficient sac) {
		this.sac = sac;
	}
	public List<SalesAmountCoefficient> getSacList() {
		return sacList;
	}
	public void setSacList(List<SalesAmountCoefficient> sacList) {
		this.sacList = sacList;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPageStatus() {
		return pageStatus;
	}
	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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
	public List<BmzZlh> getBmzzlhList() {
		return bmzzlhList;
	}
	public void setBmzzlhList(List<BmzZlh> bmzzlhList) {
		this.bmzzlhList = bmzzlhList;
	}
	public BmzZlh getBmzzlh() {
		return bmzzlh;
	}
	public void setBmzzlh(BmzZlh bmzzlh) {
		this.bmzzlh = bmzzlh;
	}
	public Integer getMonthsNo() {
		return monthsNo;
	}
	public void setMonthsNo(Integer monthsNo) {
		this.monthsNo = monthsNo;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<WeiWaiJieGou> getWwjgList() {
		return wwjgList;
	}
	public void setWwjgList(List<WeiWaiJieGou> wwjgList) {
		this.wwjgList = wwjgList;
	}
	public WeiWaiJieGou getWwjg() {
		return wwjg;
	}
	public void setWwjg(WeiWaiJieGou wwjg) {
		this.wwjg = wwjg;
	}
	public WorkShopXiaoLvZz getWsxlzz() {
		return wsxlzz;
	}
	public void setWsxlzz(WorkShopXiaoLvZz wsxlzz) {
		this.wsxlzz = wsxlzz;
	}
	public List<WorkShopXiaoLvZz> getWsxlzzList() {
		return wsxlzzList;
	}
	public void setWsxlzzList(List<WorkShopXiaoLvZz> wsxlzzList) {
		this.wsxlzzList = wsxlzzList;
	}
	public ZhiZaoPingJia getZzpj() {
		return zzpj;
	}
	public void setZzpj(ZhiZaoPingJia zzpj) {
		this.zzpj = zzpj;
	}
	public ZbSjZk getZbSjZk() {
		return zbSjZk;
	}
	public void setZbSjZk(ZbSjZk zbSjZk) {
		this.zbSjZk = zbSjZk;
	}
	public List<ZbSjZk> getZbSjZkList() {
		return zbSjZkList;
	}
	public void setZbSjZkList(List<ZbSjZk> zbSjZkList) {
		this.zbSjZkList = zbSjZkList;
	}
	public List<WorkShopTiQuMx> getWstqmxList() {
		return wstqmxList;
	}
	public void setWstqmxList(List<WorkShopTiQuMx> wstqmxList) {
		this.wstqmxList = wstqmxList;
	}
	public WorkShopTiQuMx getWstqmx() {
		return wstqmx;
	}
	public void setWstqmx(WorkShopTiQuMx wstqmx) {
		this.wstqmx = wstqmx;
	}
	public WorkShopTiQu getWstq() {
		return wstq;
	}
	public void setWstq(WorkShopTiQu wstq) {
		this.wstq = wstq;
	}
	public List<WorkShopTiQu> getWstqList() {
		return wstqList;
	}
	public void setWstqList(List<WorkShopTiQu> wstqList) {
		this.wstqList = wstqList;
	}
	public FenPeiQingKuang getFpqk() {
		return fpqk;
	}
	public void setFpqk(FenPeiQingKuang fpqk) {
		this.fpqk = fpqk;
	}
	public List<FenPeiQingKuang> getFpqkList() {
		return fpqkList;
	}
	public void setFpqkList(List<FenPeiQingKuang> fpqkList) {
		this.fpqkList = fpqkList;
	}
	public DeptFenPeiXiShu getDfpx() {
		return dfpx;
	}
	public void setDfpx(DeptFenPeiXiShu dfpx) {
		this.dfpx = dfpx;
	}
	public List<DeptFenPeiXiShu> getDfpxList() {
		return dfpxList;
	}
	public void setDfpxList(List<DeptFenPeiXiShu> dfpxList) {
		this.dfpxList = dfpxList;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public List<DeptLeaderPenPei> getDlppList() {
		return dlppList;
	}
	public void setDlppList(List<DeptLeaderPenPei> dlppList) {
		this.dlppList = dlppList;
	}
	public YearImprove getYearimprove() {
		return yearimprove;
	}
	public void setYearimprove(YearImprove yearimprove) {
		this.yearimprove = yearimprove;
	}
	public List<YearImprove> getYearimproveList() {
		return yearimproveList;
	}
	public void setYearimproveList(List<YearImprove> yearimproveList) {
		this.yearimproveList = yearimproveList;
	}
	public InCome getInCome() {
		return inCome;
	}
	public void setInCome(InCome inCome) {
		this.inCome = inCome;
	}
	public List<InCome> getInComeList() {
		return inComeList;
	}
	public void setInComeList(List<InCome> inComeList) {
		this.inComeList = inComeList;
	}
	
	
}
