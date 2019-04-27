package com.task.action.oa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.oa.OAreimBursementServer;
import com.task.entity.Oabusiness;
import com.task.entity.Oareimbursement;
import com.task.entity.Storage;
import com.task.entity.Users;
import com.task.util.MKUtil;

/**
 * OA采购明细action
 * 
 * @author jhh
 * 
 */
public class OAreimBursementAction extends ActionSupport {
	private OAreimBursementServer oareimBurServer;// 报账单接口
	private Oareimbursement oaReimBursement; // 报账表
	private Oabusiness business;
	private Storage storage;// 入库历史记录对象
	private List list; // 常用List
	private List listPrint; // 可打印的报账单信息
	private List auditList; // 可打印的报账单信息
	private String tag; // 常用标志
	private String message;// 消息
	// 分页
	private String cpage = "1";

	public Oabusiness getBusiness() {
		return business;
	}

	public void setBusiness(Oabusiness business) {
		this.business = business;
	}

	private String total;
	private String url;
	private int pageSize = 20;
	private Integer id;// 主键
	private String startDate;// 开始时间
	private String endDate;// 截止时间

	private String baoxiaoStyle;// 按报销类别汇总
	private String errorMessage;// 报错信息
	private String powerTag;// 权限标识
	private Integer[] storageSelect;// 选择补打数组,审批数组
	private Integer[] ids;// 批量审核数组

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	/**
	 * 查找可以报账的入库明细
	 * 
	 * @return
	 */
	public String findBaozhangList() {
		this.pageSize = 70;
		this.setUrl("oaReimBursementAction!findBaozhangList.action?cpage="
				+ cpage);
		if (storage != null) {
			ActionContext.getContext().getSession().put("storage", storage);
		} else {
			storage = (Storage) ActionContext.getContext().getSession().get(
					"storage");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		Object[] obj = oareimBurServer.findStorageBaoZhang(storage, startDate,
				endDate, Integer.parseInt(cpage), pageSize, tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaozhangListOK";//storage_selectBaozhang.jsp
	}

	/**
	 * 查找报账历史记录
	 * 
	 * @return
	 */
	public String findBusList() {
		this.pageSize = 20;
		this.setUrl("oaReimBursementAction!findBusList.action");
		if (business != null) {
			ActionContext.getContext().getSession().put("business", business);
		} else {
			business = (Oabusiness) ActionContext.getContext().getSession()
					.get("business");
		}
		Object[] obj = oareimBurServer.findBusList(business, startDate,
				endDate, Integer.parseInt(cpage), pageSize, "list");
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "_findBusList";
	}

	// 导出汇总数据
	public String exportEXCEL() {
		oareimBurServer.findBusList(business, startDate, endDate, Integer
				.parseInt(cpage), pageSize, "excel");
		return null;
	}

	/**
	 * 获得选择报账的入库明细列表
	 * 
	 * @return
	 */
	public String findStorageBZList() {
		if (null != storageSelect && storageSelect.length > 0) {
			Object[] obj = oareimBurServer.findSelectedStorage(storageSelect);
			list = (List) obj[1];
			oaReimBursement = new Oareimbursement();
			oaReimBursement = (Oareimbursement) obj[0];
			return "findStorageBZListOK";
		} else {
			return ERROR;
		}

	}

	/**
	 * 添加报账信息
	 * 
	 * @return
	 */
	public String saveBaoZhang() {
		if (null != oaReimBursement && null != storageSelect
				&& storageSelect.length > 0) {
			list = oareimBurServer.saveBaozhang(oaReimBursement, storageSelect);
			return "saveBaoZhangOK";
		}
		return null;
	}

	/**
	 * 查找下拉字段（查找可报账的入库明细）
	 * 
	 * @return
	 */
	public String findSelectItemStorage() {
		String message = oareimBurServer.findOASelect(tag, powerTag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 查找报账信息(总经理审核，财务审核，采购打印)
	 * 
	 * @return
	 */
	public String findOAReimBursementList() {
		this.pageSize = 20;
		this.setUrl("oaReimBursementAction!findOAReimBursementList.action?tag="
				+ tag + "&powerTag=" + powerTag);
		if (oaReimBursement != null) {
			ActionContext.getContext().getSession().put("oaReimBursement",
					oaReimBursement);
		} else {
			oaReimBursement = (Oareimbursement) ActionContext.getContext()
					.getSession().get("oaReimBursement");
		}
		// 待付款并需要审批的凭证(>90)
		auditList = oareimBurServer.findMaturityOabur();

		Object[] obj = oareimBurServer.OAReimBursementList(oaReimBursement,
				startDate, endDate, Integer.parseInt(cpage), pageSize, tag,
				powerTag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];// 采购打印历史记录，总经理审核，财务付款审核
		listPrint = (List) obj[2];
		return "findOAReimBursementListOK";
	}

	/**
	 * 预览或打印报账单
	 * 
	 * @return
	 */
	public String findViewOAReimBursement() {
		if (null != id) {
			Object[] obj = oareimBurServer.findBusinesByOBSId(id);
			list = (List) obj[0];
			oaReimBursement = (Oareimbursement) obj[1];
			return "saveBaoZhangOK";
		}
		return null;
	}

	public String updateOBSById() {
		oareimBurServer.updateOBSById(id);
		String str = "";
		MKUtil.writeJSON(str);
		return null;
	}

	/**
	 * 条件查询下拉
	 * 
	 * @return
	 */
	public String selectItem() {
		String message = oareimBurServer.selectItem(tag, powerTag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 查看Business明细（进行审核）
	 * 
	 * @return
	 */
	public String findBusinessList() {
		this.pageSize = 20;
		this.setUrl("oaReimBursementAction!findBusinessList.action?tag=" + tag
				+ "&powerTag=" + powerTag);
		if (business != null) {
			ActionContext.getContext().getSession().put("business", business);
		} else {
			business = (Oabusiness) ActionContext.getContext().getSession()
					.get("business");
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		Object[] obj = oareimBurServer.findOABusinessList(business, startDate,
				endDate, Integer.parseInt(cpage), pageSize, tag, powerTag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];// 采购打印历史记录，总经理审核，财务付款审核
		return "findBusinessListOK";
	}

	/**
	 * 查找Business 下拉
	 * 
	 * @return
	 */

	public String selectBusinessItem() {
		String message = oareimBurServer.selectBusinessItem(tag, powerTag);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}

	/**
	 * 审核,单条审核
	 * 
	 * @return
	 */
	public String examBusiness() {
		// 判断跳转页面，若状态修改跳转到查询所有页面，状态未改保留原来页面
		Object[] obj = oareimBurServer.examBusiness(id, tag, powerTag);
		String jumpTag = (String) obj[0];
		this.tag = "exam";
		if ("exam".equals(jumpTag)) {// 继续审核，页面不跳转
			id = (Integer) obj[1];
			return "examBusinessOK";// 跳转到 findBusinessList
		} else {// 页面跳转到上层报账记录中
			// list=(List)obj[1];
			// findOAReimBursementList
			return "examOBSok";
		}
	}

	/**
	 * 批量审批是否可付款
	 * 
	 * @return
	 */
	public String examLotBusiness() {
		// 判断跳转页面，若状态修改跳转到查询所有页面，状态未改保留原来页面
		Object[] obj = oareimBurServer.examlotBusiness(ids, tag, powerTag);
		String jumpTag = (String) obj[0];
		this.tag = "exam";
		if ("exam".equals(jumpTag)) {// 继续审核，页面不跳转
			id = (Integer) obj[1];
			return "examBusinessOK";// 跳转到 findBusinessList
		} else if ("beyond".equals(jumpTag)) {
			// 弹出超出预算的窗口
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			try {
				PrintWriter out = response.getWriter();
				// out.println("<script> alert('报销金额超出部门预算!')</script>");

				out
						.println("<script> alert('信息添加成功!');location.href='oaReimBursementAction!findBusinessList.action?tag=exam&powerTag=manager&?cpage=1'; </script>");
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "examBusinessOK";// 跳转到 findBusinessList
		} else {// 页面跳转到上层报账记录中
			// list=(List)obj[1];
			// findOAReimBursementList
			return "examOBSok";
		}
	}

	/**
	 * 报账筛选（高于最低价的自动打回）
	 * 
	 * @return
	 */

	public String selectShaixuang() {
		if (oareimBurServer.updateSelectShaixuang(id)) {
			return "selectShaixuangOK";
		}
		return null;
	}

	/**
	 * 价格比较
	 * 
	 * @return
	 */
	public String findSameProductPrice() {
		list = oareimBurServer.findSameProductPrice(id, tag);
		return "findSameProductPriceOK";
	}

	/**
	 * 预报账查询
	 * 
	 * @return
	 */
	public String findPreBaozhangList() {
		this.pageSize = 50;
		this.setUrl("oaReimBursementAction!findPreBaozhangList.action");
		if (storage != null) {
			ActionContext.getContext().getSession().put("storage", storage);
		} else {
			storage = (Storage) ActionContext.getContext().getSession().get(
					"storage");
		}
		Object[] obj = oareimBurServer.findPreBaoZhang(storage, startDate,
				endDate, Integer.parseInt(cpage), pageSize, tag);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findPreBaozhangListOK";
	}

	/**
	 * 预报账审批
	 * 
	 * @return
	 */
	public String updateStorageBZStatus() {
		if (null != storageSelect && storageSelect.length > 0) {
			if (oareimBurServer.updateStorageBZStatus(storageSelect, tag)) {
				return "updateBZStatusOK";
			}
		}
		return "updateBZStatusOK";
	}

	public OAreimBursementServer getOareimBurServer() {
		return oareimBurServer;
	}

	public void setOareimBurServer(OAreimBursementServer oareimBurServer) {
		this.oareimBurServer = oareimBurServer;
	}

	public Oareimbursement getOaReimBursement() {
		return oaReimBursement;
	}

	public void setOaReimBursement(Oareimbursement oaReimBursement) {
		this.oaReimBursement = oaReimBursement;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListPrint() {
		return listPrint;
	}

	public void setListPrint(List listPrint) {
		this.listPrint = listPrint;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBaoxiaoStyle() {
		return baoxiaoStyle;
	}

	public void setBaoxiaoStyle(String baoxiaoStyle) {
		this.baoxiaoStyle = baoxiaoStyle;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPowerTag() {
		return powerTag;
	}

	public void setPowerTag(String powerTag) {
		this.powerTag = powerTag;
	}

	public Integer[] getStorageSelect() {
		return storageSelect;
	}

	public void setStorageSelect(Integer[] storageSelect) {
		this.storageSelect = storageSelect;
	}

	public List getAuditList() {
		return auditList;
	}

	public void setAuditList(List auditList) {
		this.auditList = auditList;
	}

}
