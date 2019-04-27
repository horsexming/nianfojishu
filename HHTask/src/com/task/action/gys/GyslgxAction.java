package com.task.action.gys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.gys.GyslgxServer;
import com.task.entity.Users;
import com.task.entity.gys.ProcardGys;
import com.task.entity.gys.ProcessGysInfor;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.sop.ProcessInfor;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class GyslgxAction {
	private ProcardGys procardGys;
	private ProcessGysInfor process;
	private List<ProcardGys> procardGysList;
	private List<ProcardGys> unprocardGysList;
	private GyslgxServer gyslgxServer;
	private Integer maxBelongLayer;// 最大层
	private List list;
	private Integer id;
	private Integer[] processIds;
	private Float[] processNumbers;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private String pageStatus;//页面状态
	
	private List<String> contentList;// 自检内容
	private List<String> isQualifiedList;// 自检结果

	public String showList() {
		pageStatus="history";
		if (procardGys != null) {
			ActionContext.getContext().getSession().put("procardGys",
					procardGys);
		} else {
			procardGys = (ProcardGys) ActionContext.getContext().getSession()
					.get("procardGys");
		}
		ZhUser zhUser = Util.getCurrzhUser();
		Map<Integer, Object> map = null;
		if (zhUser!=null) {
				map = gyslgxServer.findProcardGysByCondition(procardGys,
						Integer.parseInt(cpage),pageStatus, pageSize, "zhuserId ="
								+ zhUser.getId());
		} else {
			map = gyslgxServer.findProcardGysByCondition(procardGys, Integer
					.parseInt(cpage), pageStatus,pageSize, null);
		}
		procardGysList = new ArrayList<ProcardGys>();
		if (null != map) {
			unprocardGysList = (List<ProcardGys>) map.get(1);// 显示未激活的流水卡
			procardGysList = (List<ProcardGys>) map.get(2);// 显示页的已激活的流水卡
			list= (List) map.get(4);
			if (procardGysList != null && procardGysList.size() > 0) {
				int count = (Integer) map.get(3);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("gyslgxAction_showList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "gys_findProcardList";
	}
	/**
	 * 展示可领工序的流水卡
	 * @return
	 */
	public String lgxshowList() {
		pageStatus="NoCardLingGX";
		if (procardGys != null) {
			ActionContext.getContext().getSession().put("procardGys",
					procardGys);
		} else {
			procardGys = (ProcardGys) ActionContext.getContext().getSession()
					.get("procardGys");
		}
		ZhUser zhUser = Util.getCurrzhUser();
		Map<Integer, Object> map = null;
		if (zhUser!=null) {
				map = gyslgxServer.findProcardGysByCondition(procardGys,
						Integer.parseInt(cpage),pageStatus, pageSize, "zhuserId ="
								+ zhUser.getId());
		} else {
			map = gyslgxServer.findProcardGysByCondition(procardGys,
					Integer.parseInt(cpage),pageStatus, pageSize, null);
			//map = null;
		}
		procardGysList = new ArrayList<ProcardGys>();
		if (null != map) {
			//unprocardGysList = (List<ProcardGys>) map.get(1);// 显示未激活的流水卡
			procardGysList = (List<ProcardGys>) map.get(2);// 显示页的已激活的流水卡
			if (procardGysList != null && procardGysList.size() > 0) {
				int count = (Integer) map.get(3);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("gyslgxAction_lgxshowList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "gys_findProcardList";
	}
/**
 * 激活外购件
 * @return
 */
	public String jihuoProcardGys() {
		boolean b = gyslgxServer.jihuoProcardGys(id);
		procardGys = gyslgxServer.findProcardGysById(id);
		if (procardGys != null) {
			if (procardGys.getProcardStyle().equals("总成")) {
				maxBelongLayer = gyslgxServer.findMaxbelongLayer(procardGys
						.getRootId());
				return "gys_viewProcard";
			} else {
				errorMessage = "请直接发总成流水卡!!";
			}
		} else {
			errorMessage = "不存在您要查看的流水单信息!请检查或重试!";
		}
		return "error";
	}
	/***
	 * 生产进度查看
	 * 
	 * @return
	 */
	public String findProcardGysView() {
		procardGys = gyslgxServer.findProcardGysById(id);
		if (procardGys != null) {
			if (procardGys.getProcardStyle().equals("总成")) {
				maxBelongLayer = gyslgxServer.findMaxbelongLayer(procardGys
						.getRootId());
				return "gys_viewProcard";
			} else {
				errorMessage = "请直接发总成流水卡!!";
			}
		} else {
			errorMessage = "不存在您要查看的流水单信息!请检查或重试!";
		}
		return "error";
	}
	/***
	 * 查询节点(输出json)
	 */
	@SuppressWarnings("unchecked")
	public void findProByBel() {
		List<ProcardGys> list = gyslgxServer.findProByBel(id, maxBelongLayer);
		MKUtil.writeJSON(list);
	}

	/***
	 * 进度查看、领取工序
	 * 
	 * @return
	 */
	public String findProcardGysByRunCard2() {
		ProcardGys procardgys = gyslgxServer.findProcardGysById(id);
		if (procardgys != null) {
			if (procardgys.getStatus()!=null&&!"入库".equals(procardgys.getStatus())) {
				Object[] obj = gyslgxServer.findProcardByRunCard(id);
				if (obj != null) {
					if (obj.length > 3) {
						errorMessage = (String) obj[3];
					} else {
						procardGys = (ProcardGys) obj[0];
						procardGysList = (List<ProcardGys>) obj[1];
						list = (List) obj[2];
						return "gysProcess_Receive2";
					}
				} else {
					errorMessage = "该流水卡片错误!无法找到对应工艺信息!请更换!";
				}
			} else {
					errorMessage = "该生产周转卡尚未绑定工艺流水单!无法使用!";
			}
		}

		return "error";
	}
	/**************************************************************************
	 * 现场自检表
	 * 
	 * @return
	 */
	public String showZj() {
		list = gyslgxServer.listProvisionByMarkId(procardGys.getMarkId());
		// list = procardServer.findZjXiang();
		if (list != null)
			pageSize = list.size();
		return "gysProcess_zj";
	}
	/****
	 * 添加自检
	 * 
	 * @return
	 */
	public String addZj() {
		// for (int i = 1; i < contentList.size(); i++) {
		// ProcessZj processZj = (ProcessZj) contentList.get(i);
		// System.out.println("第"+i+"个数组(内容)"+processZj.getZjItem());
		//			
		// }
		// for (int i = 0; i < isQualifiedList.size(); i++) {
		// ProcessZj processZj = (ProcessZj) contentList.get(i);
		// System.out.println("第"+i+"个数组(结果)"+processZj.getIsQualified());
		//			
		// }
		boolean bool = gyslgxServer.saveZj(contentList, isQualifiedList, id);
		if (bool) {
			successMessage = "自检完成";
		}
		return "gysProcess_zj";
	}
	/***************************************************************************
	 * 查询工序明细
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findProcess() {
		Object[] obj = gyslgxServer.findProcess(id);
		List list = (List) obj[0];
		String message = (String) obj[1];
		Boolean bool = (Boolean) obj[2];
		MKUtil.writeJSON(bool, message, list);
		return null;
	}
	/***************************************************************************
	 * 领取工序
	 * 
	 * @return
	 */
	public String collorProcess() {
		Users loginUser = Util.getLoginUser();
		if (loginUser == null) {
			errorMessage = "请登录后再提交数据!";
			return "error";
		}
		String message;
		try {
			message = gyslgxServer.collorProcess(processIds, processNumbers,
					list);
			MKUtil.writeJSON(message);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("领取工序失败!");
		}
		return null;
	}
	/***************************************************************************
	 * 提交工序
	 * 
	 * @return
	 */
	public String submitProcess() {
		Users loginUser = Util.getLoginUser();
		if (loginUser == null) {
			errorMessage = "请登录后再提交数据!";
			return "error";
		}

		String message = null;
		Map<String, Object> maps = null;
		try {
			// --------------------------------------工装------------------------------------
			if (process != null) {
				ProcessGysInfor oldP = gyslgxServer.getObjectByIdProcessInfor(process.getId());
				if ("是".equals(oldP.getGongzhuangstatus())) {
					if (oldP.getGzstoreId() != null) {
						Gzstore old = gyslgxServer.getObjectByIdGzstore(oldP
								.getGzstoreId());
						if (old.getSybjcs()!=null && old.getSybjcs()>0) {
							message = "报检数量不足,请去报检!";
							MKUtil.writeJSON(false, message, maps);
							return null;
						}
					}
				}
				message = gyslgxServer.updateProcess(process);
				if ("提交工序成功".equals(message)) {
					// 提交最后一道工序后激活待干工序
					gyslgxServer.updateJihuo(process.getId(), id);
				}
				procardGys = gyslgxServer.findProcardGysById(id);
				maps = new HashMap<String, Object>();
				maps.put("process", process);
				maps.put("procard", procardGys);
				MKUtil.writeJSON(true, message, maps);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, message + e.getMessage(), maps);

		}
		return null;
	}
	public String deleteprocardGystree(){
		boolean b = gyslgxServer.deleteprocardtree(id);
		if (b) {
			successMessage = "删除成功!";
		} else {
			successMessage = "删除失败!";
		}
			return showList();
	}
	public GyslgxServer getGyslgxServer() {
		return gyslgxServer;
	}

	public void setGyslgxServer(GyslgxServer gyslgxServer) {
		this.gyslgxServer = gyslgxServer;
	}

	public ProcardGys getProcardGys() {
		return procardGys;
	}

	public void setProcardGys(ProcardGys procardGys) {
		this.procardGys = procardGys;
	}

	public List<ProcardGys> getProcardGysList() {
		return procardGysList;
	}

	public void setProcardGysList(List<ProcardGys> procardGysList) {
		this.procardGysList = procardGysList;
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

	public List<ProcardGys> getUnprocardGysList() {
		return unprocardGysList;
	}

	public void setUnprocardGysList(List<ProcardGys> unprocardGysList) {
		this.unprocardGysList = unprocardGysList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getMaxBelongLayer() {
		return maxBelongLayer;
	}

	public void setMaxBelongLayer(Integer maxBelongLayer) {
		this.maxBelongLayer = maxBelongLayer;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}
	public List<String> getContentList() {
		return contentList;
	}
	public void setContentList(List<String> contentList) {
		this.contentList = contentList;
	}
	public List<String> getIsQualifiedList() {
		return isQualifiedList;
	}
	public void setIsQualifiedList(List<String> isQualifiedList) {
		this.isQualifiedList = isQualifiedList;
	}
	public Integer[] getProcessIds() {
		return processIds;
	}
	public void setProcessIds(Integer[] processIds) {
		this.processIds = processIds;
	}
	public Float[] getProcessNumbers() {
		return processNumbers;
	}
	public void setProcessNumbers(Float[] processNumbers) {
		this.processNumbers = processNumbers;
	}
	public ProcessGysInfor getProcess() {
		return process;
	}
	public void setProcess(ProcessGysInfor process) {
		this.process = process;
	}

}
