package com.task.action;

import java.util.List;

import com.huawei.openapi.openaipexample.client.http.RwlBarcodeWebService;
import com.huawei.openapi.openaipexample.client.http.TbBarcodeLock;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.entity.AlertMessages;
import com.task.util.MKUtil;

public class RwlBarcodeWebServiceAction extends ActionSupport {
	List list;// 集合
	List<TbBarcodeLock> tblockList;// 集合
	TbBarcodeLock tbBarcodeLock;// 集合
	String successMessage;// 成功消息
	String errorMessage;// 错误消息
	Integer id;// id
	Integer[] ids;// ids
	String pageStatus;// 页面状态
	String startDate;// 开始时间
	String endDate;// 截止时间
	String barcode;// 条码
	String lockNO;// 隔离单号
	String reason;// 锁定/解锁原因

	// 分页
	String cpage = "1";
	String total;
	String url;
	int pageSize = 15;

	/****
	 * 查询所有锁定表信息
	 * 
	 * @return
	 */
	public String findAllTbBarcodeLockList() {
		if (tbBarcodeLock != null) {
			ActionContext.getContext().getSession().put("tbBarcodeLock",
					tbBarcodeLock);
		} else {
			tbBarcodeLock = (TbBarcodeLock) ActionContext.getContext()
					.getSession().get("tbBarcodeLock");
		}
		RwlBarcodeWebService rb = new RwlBarcodeWebService();
		Object[] object = rb.findAllTbBarcodeLockList(tbBarcodeLock, Integer
				.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			tblockList = (List<TbBarcodeLock>) object[0];
			int count = (Integer) object[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			if (pageStatus == null) {
				pageStatus = "";
			}
			this
					.setUrl("RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action?pageStatus="
							+ pageStatus);
		}
		return "showBarcodeLockList";
	}

	/****
	 * 解锁申请
	 * 
	 * @return
	 */
	public String getUnLock() {
		if (pageStatus == null) {
			pageStatus = "";
		}
		if (lockNO != null && lockNO.length() > 0) {
			String[] lockNos = lockNO.split(",");
			String newBarcodes = "";
			String newLockNos = "";
			for (String newLockNo : lockNos) {
				newLockNo = newLockNo.replaceAll(" ", "");
				newLockNos += newLockNo
						.substring(0, newLockNo.indexOf(";") + 1);
				newBarcodes += newLockNo.substring(newLockNo.indexOf(";") + 1,
						newLockNo.length());
			}
			RwlBarcodeWebService rb = new RwlBarcodeWebService();
			errorMessage = rb.Get_Info_Frmbarcode_EMSBarcodeUnLock(newBarcodes,
					newLockNos, reason);
			url = "RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action?cpage="
					+ cpage + "&pageStatus=" + pageStatus;
		} else {
			errorMessage = "请勾选隔离单号后再申请解锁!";
			url = "RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action?cpage="
					+ cpage + "&pageStatus=" + pageStatus;
		}
		return ERROR;
	}

	/****
	 * 手动增加条码锁定
	 * 
	 * @return
	 */
	public String sdAddFLock() {
		RwlBarcodeWebService rb = new RwlBarcodeWebService();
		rb.sdAddFLock(tbBarcodeLock);
		url = "RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action";
		return ERROR;
	}

	/****
	 * 手动锁定 查询华为接口
	 * 
	 * @param tbBarcodeLock
	 * @return
	 */
	public String sdLockForHw() {
		RwlBarcodeWebService rb = new RwlBarcodeWebService();
		errorMessage = rb.sdLockForHw(tbBarcodeLock);
		url = "RwlBarcodeWebServiceAction!findAllTbBarcodeLockList.action?pageStatus="
				+ pageStatus;
		return ERROR;
	}

	/****
	 * 手动解锁/锁定(针对没有隔离单号的条码)
	 * 
	 * @return
	 */
	public void sdUnFLock() {
		RwlBarcodeWebService rb = new RwlBarcodeWebService();
		errorMessage = rb.sdUnFLock(tbBarcodeLock);
		MKUtil.writeJSON(errorMessage);
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public List<TbBarcodeLock> getTblockList() {
		return tblockList;
	}

	public void setTblockList(List<TbBarcodeLock> tblockList) {
		this.tblockList = tblockList;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLockNO() {
		return lockNO;
	}

	public void setLockNO(String lockNO) {
		this.lockNO = lockNO;
	}

	public TbBarcodeLock getTbBarcodeLock() {
		return tbBarcodeLock;
	}

	public void setTbBarcodeLock(TbBarcodeLock tbBarcodeLock) {
		this.tbBarcodeLock = tbBarcodeLock;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
