package com.task.action.shizhi;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.shizhi.ProTryMakeScoreServer;
import com.task.entity.project.ProjectManage;
import com.task.entity.shizhi.ProTryMakeScore;
import com.task.entity.shizhi.TryMake;
import com.task.entity.sop.Procard;
import com.task.entity.vo.shizhivo.ProTryMakeScoreVo;
import com.task.util.Util;

/**
 * 项目试制评审Action层
 * 
 * @author 唐晓斌
 * 
 */
public class ProTryMakeScoreAction {
	private ProTryMakeScoreServer proTryMakeScoreServer;// 项目试制评审服务层
	private ProTryMakeScore proTryMakeScore;// 项目试制评审对象
	private List<ProTryMakeScoreVo> ptmsVoList;// 项目试制评审Vo列表
	private TryMake tryMake;// 试制对象
	private List<String> gNameList;// 分组名称列表
	private List<String> cusNameList;// 客户名称列表
	private String month;// 月份
	private String flag;// 标记
	private List<String> qianmingList;// 电子签名地址

	private List<ProjectManage> projectManageList;// 项目对象列表
	private List<Procard> procardList;// 流水卡列表
	private ProjectManage projectManage; // 项目对象

	private String partNum;// 零件号
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer status ;// 0表示没有试制订单的项目
	private Integer epId;// 审批Id
	private Integer id;//
	private List list;//
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public String showList() {
		if (proTryMakeScore != null) {
			ActionContext.getContext().getSession().put("proTryMakeScore",
					proTryMakeScore);
		} else {// 用来保持分页时带着查询条件
			proTryMakeScore = (ProTryMakeScore) ActionContext.getContext()
					.getSession().get("proTryMakeScore");
		}
		if (status != null) {
			ActionContext.getContext().getSession().put("status", status);
		} else {// 用来保持分页时带着查询条件
			status = (Integer) ActionContext.getContext().getSession().get("status");
		}
		if(status==null){
			status=-1;
		}
		// //弥补客户重要系数
		// proTryMakeScoreServer.addCusimportance();
		Map<Integer, Object> map = proTryMakeScoreServer
				.findProTryMakrScoresByCondition(proTryMakeScore, Integer
						.parseInt(cpage), pageSize, status, partNum);
		ptmsVoList = (List<ProTryMakeScoreVo>) map.get(1);// 显示页的技能系数列表
		if (ptmsVoList != null & ptmsVoList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("proTryMakeScoreAction_showList.action?forreturn=1");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "ptms_show";
	}

	/**
	 * 跳往添加页面
	 * 
	 * @return
	 */
	public String toadd() {
		gNameList = proTryMakeScoreServer.getAllGroupName();
		projectManageList = proTryMakeScoreServer.getAllProjectManage();
		return "ptms_add";
	}

	public String add() {
		List<ProTryMakeScore> all = proTryMakeScoreServer.findAll();
		if (projectManage == null || projectManage.getId() == null) {
			errorMessage = "添加失败,您未选择项目或您选中的项目不存在！";
			return toadd();
		}
		projectManage = proTryMakeScoreServer
				.getProjectManageById(projectManage.getId());
		if (projectManage == null) {
			errorMessage = "添加失败,您未选择项目或您选中的项目不存在！";
			return toadd();
		}
		proTryMakeScore.setProName(projectManage.getProjectName());
		proTryMakeScore.setProNum(projectManage.getProjectNum());
		proTryMakeScore.setCusName(projectManage.getClient());
		if (proTryMakeScore.getProName() != null
				&& proTryMakeScore.getProNum() != null
				&& proTryMakeScore.getMonth() != null && all != null) {
			for (ProTryMakeScore p : all) {
				if (p.getProName() != null && p.getMonth() != null
						&& p.getProName().equals(proTryMakeScore.getProName())
						&& p.getMonth().equals(proTryMakeScore.getMonth())) {
					errorMessage = "添加失败,该月已存在该项目！";
					return toadd();
				}
			}
		}
		proTryMakeScore.setPoSize(0);
		boolean b = proTryMakeScoreServer.add(proTryMakeScore);
		if (b) {
			errorMessage = "添加成功";
			// 不让对象和参与查询
			proTryMakeScore.setTryMake(null);
			status = Integer.parseInt(ActionContext.getContext().getSession()
					.get("status").toString());
//			return showList();
			url="proTryMakeScoreAction_showList.action?forreturn=1";
			return "error";
		} else {
			errorMessage = "添加失败";
			return add();
		}
	}

	public String allBonus() {
		proTryMakeScore = proTryMakeScoreServer
				.getById(proTryMakeScore.getId());
		String proName = proTryMakeScore.getProName();
		ptmsVoList = proTryMakeScoreServer.getAllBouns(proName);
		return "ptms_allbonus";
	}

	public String toupdatePro() {
		cusNameList = proTryMakeScoreServer.getAllCusName();
		gNameList = proTryMakeScoreServer.getAllGroupName();
		proTryMakeScore = proTryMakeScoreServer
				.getById(proTryMakeScore.getId());
		return "ptms_updatePro";
	}

	public String updatePro() {
		List<ProTryMakeScore> all = proTryMakeScoreServer.findAll();
		if (proTryMakeScore.getProName() != null
				&& proTryMakeScore.getMonth() != null && all != null) {
			for (ProTryMakeScore p : all) {
				if (p.getId() != proTryMakeScore.getId()
						&& p.getProName() != null && p.getMonth() != null
						&& p.getProName().equals(proTryMakeScore.getProName())
						&& p.getMonth().equals(proTryMakeScore.getMonth())) {
					errorMessage = "修改失败,该月已存在该项目！";
					return toupdatePro();
				}
			}
		}
		boolean b = proTryMakeScoreServer.updatePro(proTryMakeScore);
		if (b) {
			successMessage = "修改成功";
			// 不让对象参与查询
			proTryMakeScore = null;
			status = Integer.parseInt(ActionContext.getContext().getSession()
					.get("status").toString());
			return showList();
		} else {
			errorMessage = "修改失败";
			return toupdatePro();
		}
	}

	public String delete() {

		boolean b = proTryMakeScoreServer.deleteById(proTryMakeScore.getId());
		if (b) {
			successMessage = "删除成功！";
		} else {
			errorMessage = "删除失败！";
		}
		proTryMakeScore = null;
		status = Integer.parseInt(ActionContext.getContext().getSession().get(
				"status").toString());
//		return showList();
		url="proTryMakeScoreAction_showList.action?forreturn=1";
		return "error";
	}

	/**
	 * 跳往试制单奖金的申请和打印页面
	 * 
	 * @return
	 */
	public String toApprovalOrPrint() {
		// flag :approval表示该月的奖金还没有申请或者被打回了,print表示该月的奖金已经被同意,none表示该月的奖金正在审批中
		if (month == null) {
			month = Util.DateToString(new Date(), "yyyy-MM");
		}
		Map<Integer, Object> map = proTryMakeScoreServer
				.updateLoadAndgetApprovalOrPrint(month);
		if (map != null) {
			if (map.get(0) != null) {
				epId = Integer.parseInt(map.get(0).toString());
			}
			if (map.get(1) != null) {
				if (flag == null || !flag.equals("view")) {
					flag = map.get(1).toString();
				}
			}
			if (map.get(2) != null) {
				ptmsVoList = (List<ProTryMakeScoreVo>) map.get(2);
			}
			if (map.get(3) != null) {
				qianmingList = (List<String>) map.get(3);
			}
		} else {
			errorMessage = "对不起没有找到该月份可分配奖金的试制单信息";
		}
		return "ptms_approvalOrPrint";
	}

	/**
	 * 申请试制单奖金
	 * 
	 * @return
	 */
	public String approvalOneMonth() {
		if (month == null) {
			successMessage = "对不起您还没有选择月份!";
		} else {
			try {
				boolean b = proTryMakeScoreServer.addApprovalOneMonth(month);
				if (b) {
					successMessage = "申请成功!";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				successMessage = e.getMessage();
			}
		}
		return toApprovalOrPrint();

	}

	/**
	 * 查看生产进度
	 * 
	 * @return
	 */
	public String showProduct() {
		if (id != null) {
			procardList = proTryMakeScoreServer.getprocardByTrymakeId(id);
			return "ptms_Product";
		}
		errorMessage = "没有找到目标!";
		return "error";
	}

	/**
	 * 分配月份试制奖金
	 * 
	 * @return
	 */
	public String distributionBonus() {
		if (month == null) {
			month = Util.getLastMonth("yyyy-MM");
		}
		// 核实该月的试制奖金是否已经申请
		boolean b = proTryMakeScoreServer.checkTryMakeApproval(month);
		if (!b) {
			errorMessage = "该月份没有审批通过的试制奖金";
			return "error";
		}
		list = proTryMakeScoreServer.getUserBonus(month);
		return "ptms_userBonus";
	}

	/**
	 * 生成月份试制奖金分配
	 * 
	 * @return
	 */
	public String addShiZhiBomus() {
		if (month == null) {
			month = Util.getLastMonth("yyyy-MM");
		}
			// 核实该月的试制奖金是否已经申请
			boolean b = proTryMakeScoreServer.checkTryMakeApproval(month);
			if (!b) {
				errorMessage = "该月份没有审批通过的试制奖金";
				return "error";
			}
			//该月份的试制奖金是否已经分配
		//	b = proTryMakeScoreServer.hasDistributionBonus(month);
			b = proTryMakeScoreServer.addUserBonus(month);
			if(b){
				errorMessage="分配成功!";
			}else{
				errorMessage="分配失败,请完成提奖的奖金分配!";
			}
			return "error";
		
	}

	// get set方法

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

	public ProTryMakeScoreServer getProTryMakeScoreServer() {
		return proTryMakeScoreServer;
	}

	public void setProTryMakeScoreServer(
			ProTryMakeScoreServer proTryMakeScoreServer) {
		this.proTryMakeScoreServer = proTryMakeScoreServer;
	}

	public ProTryMakeScore getProTryMakeScore() {
		return proTryMakeScore;
	}

	public TryMake getTryMake() {
		return tryMake;
	}

	public void setTryMake(TryMake tryMake) {
		this.tryMake = tryMake;
	}

	public void setProTryMakeScore(ProTryMakeScore proTryMakeScore) {
		this.proTryMakeScore = proTryMakeScore;
	}

	public List<ProTryMakeScoreVo> getPtmsVoList() {
		return ptmsVoList;
	}

	public void setPtmsVoList(List<ProTryMakeScoreVo> ptmsVoList) {
		this.ptmsVoList = ptmsVoList;
	}

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getgNameList() {
		return gNameList;
	}

	public void setgNameList(List<String> gNameList) {
		this.gNameList = gNameList;
	}

	public List<String> getCusNameList() {
		return cusNameList;
	}

	public void setCusNameList(List<String> cusNameList) {
		this.cusNameList = cusNameList;
	}

	public List<ProjectManage> getProjectManageList() {
		return projectManageList;
	}

	public void setProjectManageList(List<ProjectManage> projectManageList) {
		this.projectManageList = projectManageList;
	}

	public ProjectManage getProjectManage() {
		return projectManage;
	}

	public void setProjectManage(ProjectManage projectManage) {
		this.projectManage = projectManage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<String> getQianmingList() {
		return qianmingList;
	}

	public void setQianmingList(List<String> qianmingList) {
		this.qianmingList = qianmingList;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Procard> getProcardList() {
		return procardList;
	}

	public void setProcardList(List<Procard> procardList) {
		this.procardList = procardList;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
