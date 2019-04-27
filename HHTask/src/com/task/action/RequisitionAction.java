package com.task.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.RequisitionService;
import com.task.entity.Requisition;
import com.task.entity.Users;

public class RequisitionAction extends ActionSupport {
	private RequisitionService requisitionService;
	private Requisition requisition;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private List<Requisition> requisitionList;
	private List<Requisition> requisitionList1;
	private List<Requisition> requisitionList2;
	private List<Requisition> requisitionList3;
	private List<Requisition> reList;
	private String pageStatus;

	private int id;

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	// 添加
	public String addSubmit() {

		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());

		requisition.setItdate(writeDate);
		requisition.setSingleumber(writeDate);

		boolean bool = requisitionService.add(requisition);
		if (bool) {
			successMessage = "添加成功";
		}
		return "addsubmit";

	}

	// 备注回复功能
	public String remarkRequisition() {
		requisition = requisitionService.findAssetById(id);
		return "remark";
	}

	public String upremarkRequisition() {
		Requisition oldrequisition = requisitionService.findAssetById(id);
		if (oldrequisition != null) {
			oldrequisition.setReplyremarks(requisition.getReplyremarks());
			oldrequisition.setManager("完成");
			requisitionService.update(oldrequisition);
		}
		return "updateSubmit";

	}

	// 初始化 修改
	public String initUpRequisition() {
		requisition = requisitionService.findAssetById(id);
		return "uprequisition";

	}

	// 修改
	public String updateRequisition() {
		requisitionService.update(requisition);
		return "updrequisition";
	}

	// 明细
	public String findByclientManagement() {
		requisition = requisitionService.findAssetById(id);
		return "findByclientManagement";
	}

	public String findRequisition() {
		requisitionService.update(requisition);

		return "findquisition";
	}

	// 删除
	public String delSubmit() {
		requisition = requisitionService.findAssetById(id);
		requisitionService.delete(requisition);

		return "delsubmit";

	}

	// 查询所有

	@SuppressWarnings("unchecked")
	public String findAll() {
		if (requisition != null) {
			ActionContext.getContext().getSession().put("requisition",
					requisition);
		} else {
			requisition = (Requisition) ActionContext.getContext().getSession()
					.get("requisition");
		}
		Object[] object = null;

		if (pageStatus == null) {
			requisitionList1 = requisitionService.findAll("审批");// 审核中
			requisitionList2 = requisitionService.findAll("打回");// 打回
			requisitionList3 = requisitionService.findAll("执行中");// 同意
			requisition.setManager("完成");
			object = requisitionService.findAll(requisition, Integer
					.parseInt(cpage), pageSize, pageStatus);

		} else {
			if ("tongyi".equals(pageStatus)) {
				object = requisitionService.findAll(requisition, 0, 0,
						"zuzxzong");// 同意
				requisitionList1 = (List<Requisition>) object[0];
			}
			object = requisitionService.findAll(requisition, Integer
					.parseInt(cpage), pageSize, pageStatus);
		}

		if (object != null && object.length > 0) {
			requisitionList = (List<Requisition>) object[0];
			if (requisitionList != null && requisitionList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("RequisitionAction!findAll.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findAll";

	}

	// 日志管理
	// 条件查询
	public String updateSubmit() {

		String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		requisition = requisitionService.findAssetById(id);
		if (requisition != null) {
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("jingli".equals(pageStatus)) {
					requisition.setManagerdate(writeDate);
					// 如何拿出session中的Users对象
					Users user = (Users) ActionContext.getContext()
							.getSession().get(TotalDao.users);
					String userDept = user.getDept();// 部门
					if (userDept != null && userDept.equals("财务")
							|| userDept.equals("工艺")) {
						requisition.setManager("总经理审批");
					} else {
						
						if (userDept != null && userDept.equals("信息")
								|| userDept.equals("采购")
								|| userDept.equals("市场")
								|| userDept.equals("人力资源")) {
							requisition.setManager("企划副总审批");
						} else {
							requisition.setManager("生产副总审批");
						}
					}
				} else if ("daifuzong".equals(pageStatus)
						|| "zhongfuzong".equals(pageStatus)) {
					requisition.setDgmanagerdate(writeDate);
					requisition.setManager("总经理审批");
				} else if ("zongjingli".equals(pageStatus)) {
					requisition.setGmanagerdate(writeDate);
					requisition.setManager("任务执行中");
				} else if ("tongyi".equals(pageStatus)) {
					requisition.setExecutiondate(writeDate);
					requisition.setManager("任务执行中");
				} else if ("zuzxzong".equals(pageStatus)) {
					requisition.setCompletedate(writeDate);
					requisition.setManager("完成");
				} else if ("back".equals(pageStatus.substring(0, 4))) {
					requisition.setManager("打回");
					pageStatus = pageStatus.substring(4);
				}

				requisitionService.update(requisition);
				return "updateSubmit";
			} else {
				errorMessage = "参数异常!请检查后重试!";
			}
		} else {
			errorMessage = "不存在您要查询的数据!";
		}
		return ERROR;
	}

	public RequisitionService getRequisitionService() {
		return requisitionService;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public List<Requisition> getRequisitionList() {
		return requisitionList;
	}

	public List<Requisition> getReList() {
		return reList;
	}

	public void setReList(List<Requisition> reList) {
		this.reList = reList;
	}

	public String getCpage() {
		return cpage;
	}

	public String getTotal() {
		return total;
	}

	public String getUrl() {
		return url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setRequisitionService(RequisitionService requisitionService) {
		this.requisitionService = requisitionService;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setRequisitionList(List<Requisition> requisitionList) {
		this.requisitionList = requisitionList;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the requisitionList1
	 */
	public List<Requisition> getRequisitionList1() {
		return requisitionList1;
	}

	/**
	 * @return the requisitionList2
	 */
	public List<Requisition> getRequisitionList2() {
		return requisitionList2;
	}

	/**
	 * @return the requisitionList3
	 */
	public List<Requisition> getRequisitionList3() {
		return requisitionList3;
	}

	/**
	 * @param requisitionList1
	 *            the requisitionList1 to set
	 */
	public void setRequisitionList1(List<Requisition> requisitionList1) {
		this.requisitionList1 = requisitionList1;
	}

	/**
	 * @param requisitionList2
	 *            the requisitionList2 to set
	 */
	public void setRequisitionList2(List<Requisition> requisitionList2) {
		this.requisitionList2 = requisitionList2;
	}

	/**
	 * @param requisitionList3
	 *            the requisitionList3 to set
	 */
	public void setRequisitionList3(List<Requisition> requisitionList3) {
		this.requisitionList3 = requisitionList3;
	}

}
