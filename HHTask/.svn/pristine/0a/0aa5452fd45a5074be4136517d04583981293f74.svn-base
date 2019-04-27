package com.task.action.shizhi;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.TryMakeServer;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.TryMake;
import com.task.entity.vo.shizhivo.TryMakeVo;
import com.task.entity.sop.ProcardTemplate;
import com.task.util.MKUtil;

/**
 * 试制零件Action层
 * 
 * @author 唐晓斌
 * 
 */
public class TryMakeAction {
	private TryMakeServer tryMakeServer;// 试制零件服务层
	private TryMake tryMake;// 试制零件对象
	private List<TryMakeVo> tmVoList;// 试制零件列表
	private ProTryMakeScore proTryMakeScore;// 项目试制评审对象
	private ProcardTemplate procardTemplate;// 零件对象
    private List<ProTryMakeScore> ptmList;//项目试制评审列表
    private List<ProcardTemplate> qpList;//总成零件列表
    
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String showList() {
		if (tryMake != null) {
			ActionContext.getContext().getSession().put("tryMake", tryMake);
		} else {// 用来保持分页时带着查询条件
			tryMake = (TryMake) ActionContext.getContext().getSession().get(
					"tryMake");
		}
//		//弥补产能，工艺和奖金负荷系数
//		tryMakeServer.addLoad();
		Map<Integer, Object> map = tryMakeServer.findTryMakesByCondition(
				tryMake, Integer.parseInt(cpage), pageSize,proTryMakeScore);
		tmVoList = (List<TryMakeVo>) map.get(1);// 显示页的试制零件列表
		if (tmVoList != null & tmVoList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("tryMakeAction_showList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "trymake_show";
	}
   public String toadd(){
	   ptmList=tryMakeServer.findProTryMakeScoreAll();
	   return "trymake_add";
   }
   /**
    * 通过项目Id获取其下拥有的总成零件
    */
   public void getParts(){
	   if(proTryMakeScore!=null&&proTryMakeScore.getId()!=null){
		  qpList= tryMakeServer.findPartsByProId(proTryMakeScore.getId());
		  MKUtil.writeJSON(true, null, qpList);
	   }else{
		   MKUtil.writeJSON(false, "您未选择项目，或您选中的项目不存在", null);
	   }
   }
	
	public String add() {
		Set<TryMake> tmSet = tryMakeServer.getTMSetByPTMSId(proTryMakeScore
				.getId());
		if (tmSet!=null&&tmSet.size() > 0) {
			for (TryMake tm : tmSet) {
				if (tm.getQpId() != null && procardTemplate.getId() != null
						&& tm.getQpId().equals(procardTemplate.getId())) {
					errorMessage = "添加失败,该试制项目已含有该试制零件！";
					return toadd();
				}
			}
		}
		proTryMakeScore = tryMakeServer.getProTryMakeScore(proTryMakeScore
				.getId());
		tryMake.setProTryMakeScore(proTryMakeScore);
		procardTemplate=tryMakeServer.getProcardTemplateById(procardTemplate.getId());
		tryMake.setProcardTemplate(procardTemplate);
		boolean b = tryMakeServer.add(tryMake);
		if (b) {
			errorMessage = "添加成功！";
//			return showList();
			this.setUrl("tryMakeAction_showList.action");
			return "error";
		} else {
			errorMessage = "添加失败！";
			return "trymake_add";
		}
	}

	public String toupdate() {
		tryMake = tryMakeServer.getById(tryMake.getId());
		proTryMakeScore=tryMakeServer.getProTryMakeScoreByTmId(tryMake.getId());
		procardTemplate=tryMakeServer.getProcardTemplateById(tryMake.getQpId());
		if (tryMake != null) {
			return "trymake_update";

		} else {
			errorMessage = "修改失败,不存在该试制零件！";
		}
		return showList();
	}
	public void ischangeStatus(){
		boolean b=tryMakeServer.ischangeStatus(tryMake.getId());
		MKUtil.writeJSON(b,"该零件还没有通过审批的项目需求单",null);
	}
	public String update() {
		boolean b = tryMakeServer.update(tryMake);
		if (b) {
			successMessage = "修改成功！";
			tryMake = null;
			return showList();
		} else {
			errorMessage = "修改失败！";
			return toupdate();
		}
	}

	public String delete() {
		boolean b = tryMakeServer.deleteById(tryMake.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		tryMake = null;
		return showList();
	}

	// get set方法


	public TryMakeServer gettryMakeServer() {
		return tryMakeServer;
	}

	public void settryMakeServer(TryMakeServer tryMakeServer) {
		this.tryMakeServer = tryMakeServer;
	}

	public TryMake getTryMake() {
		return tryMake;
	}

	public void setTryMake(TryMake tryMake) {
		this.tryMake = tryMake;
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

	public TryMakeServer getTryMakeServer() {
		return tryMakeServer;
	}

	public void setTryMakeServer(TryMakeServer tryMakeServer) {
		this.tryMakeServer = tryMakeServer;
	}

	public List<TryMakeVo> getTmVoList() {
		return tmVoList;
	}

	public void setTmVoList(List<TryMakeVo> tmVoList) {
		this.tmVoList = tmVoList;
	}

	public ProTryMakeScore getProTryMakeScore() {
		return proTryMakeScore;
	}

	public void setProTryMakeScore(ProTryMakeScore proTryMakeScore) {
		this.proTryMakeScore = proTryMakeScore;
	}

	public ProcardTemplate getProcardTemplate() {
		return procardTemplate;
	}

	public void setProcardTemplate(ProcardTemplate procardTemplate) {
		this.procardTemplate = procardTemplate;
	}
	public List<ProTryMakeScore> getPtmList() {
		return ptmList;
	}
	public void setPtmList(List<ProTryMakeScore> ptmList) {
		this.ptmList = ptmList;
	}
	public List<ProcardTemplate> getQpList() {
		return qpList;
	}
	public void setQpList(List<ProcardTemplate> qpList) {
		this.qpList = qpList;
	}

}
