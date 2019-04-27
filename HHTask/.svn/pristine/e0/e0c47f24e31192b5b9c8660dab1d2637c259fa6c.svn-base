package com.task.action.sop;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.SellPriceServer;
import com.task.entity.Price;
import com.task.entity.renshi.InterviewLog;
import com.task.entity.sop.SellPrice;
import com.task.util.MKUtil;

public class SellPriceAction extends ActionSupport {
	private SellPriceServer sellPriceServer;
	private SellPrice sellPrice;
	private List<SellPrice> sellPriceList;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private List unsellPriceList;
	private String fatherPartNumber = "";
	private String errorMessage;
	private String successMessage;
	private List<String> clientNameList;
	private int id;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String statue = "update";

	// 添加销售价格
	public String addSellPrice() {

		if ("".equals(sellPrice.getStarttime())
				|| "".equals(sellPrice.getEndtime())) {
			errorMessage = "价格有效期时间不能为空!";
			return ERROR;
		}
		boolean bool = sellPriceServer.addSellPrice(sellPrice, attachment,
				attachmentFileName, fatherPartNumber);
		if (bool == true) {
			successMessage = "添加" + sellPrice.getClientManagement() + ":"
					+ sellPrice.getName() + ":" + sellPrice.getHair() + "成功!";

			sellPrice = null;
			return "addSellPriceSuccess";
		}
		errorMessage = "添加" + sellPrice.getClientManagement() + ":" + sellPrice.getHair()
				+ sellPrice.getPartNumber() + "失败!请重试";
		return ERROR;
	}

	// 分页查询
	public String findAllSellPrice() {
		sellPriceList = sellPriceServer.findAllSellPrice(Integer
				.parseInt(cpage), pageSize);
		int count = sellPriceServer.getCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		sellPrice = null;
		if ("find".equals(statue)) {
			this.setUrl("SellPriceAction!findAllSellPrice.action?statue=find");
			return "findAllSellPriceSuccess";
		} else if ("update".equals(statue)) {
			this
					.setUrl("SellPriceAction!findAllSellPrice.action?statue=update");
			return "updateFileSuccess";
		} else {
			return ERROR;
		}
	}

	// 条件查询
	// public String findSellPriceByCondition() {
	// if (sellPrice != null) {
	// ActionContext.getContext().getSession().put("PRICE", sellPrice);
	// } else {// 用来保持分页时带着查询条件
	// sellPrice = (SellPrice) ActionContext.getContext().getSession()
	// .get("PRICE");
	// }
	// Map<Integer, Object> map = sellPriceServer.findSellPriceByCondition(
	// sellPrice, Integer.parseInt(cpage), pageSize, statue);
	// if (map.get(3) != null) {
	// unsellPriceList = (List) map.get(3);// 没有归档的档案列表
	// }
	// sellPriceList = (List<SellPrice>) map.get(1);// 显示分页的档案列表
	// if (sellPriceList != null & sellPriceList.size() > 0) {
	// int count = (Integer) map.get(2);
	// int pageCount = (count + pageSize - 1) / pageSize;
	// this.setTotal(pageCount + "");
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue="
	// + statue);
	// } else {
	// errorMessage = "没有找到你要查询的内容,请检查后重试!";
	// }
	// if ("find".equals(statue)) {
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue=find");
	// return "findAllSellPriceSuccess";
	// } else if ("update".equals(statue)) {
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue=update");
	// return "updateFileSuccess";
	// } else if ("all".equals(statue)) {
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue=all");
	// return "findAllSellPriceSuccess";
	// } else if ("dept".equals(statue)) {
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue=dept");
	// return "findAllSellPriceSuccess";
	// } else if ("single".equals(statue)) {
	// this
	// .setUrl("SellPriceAction!findSellPriceByCondition.action?statue=single");
	// return "updateFileSuccess";
	// } else {
	// return ERROR;
	// }
	//	
	// }

	// 通过id查找销售价格
	public String findSellPriceById() {
		sellPrice = sellPriceServer.findSellPriceById(id);
		if (sellPrice != null) {
			return "findSellPriceByIdSuccess";
		}
		errorMessage = "不存在此价格!";
		return ERROR;
	}
	
	
	

	// 跳转到添加页面
	public String toadd() {
		clientNameList=sellPriceServer.findClientNameList();
		return "SellPrice_add";
	}

	// 跳转到查询页面
	
	@SuppressWarnings("unchecked")
	public String tofind() {
		if (sellPrice != null) {
			ActionContext.getContext().getSession().put("sellPrice", sellPrice);
		} else {// 用来保持分页时带着查询条件
			sellPrice = (SellPrice) ActionContext.getContext().getSession()
					.get("sellPrice");
		}
		Object[] object = sellPriceServer.findSellPriceByCondition(sellPrice,
				Integer.parseInt(cpage), pageSize);// 条件查询所有用户
		if (object != null && object.length > 0) {
			sellPriceList = (List<SellPrice>) object[0];
			if (sellPriceList != null && sellPriceList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("SellPriceAction!tofind.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的信息!";
		}
		clientNameList=sellPriceServer.findClientNameList();
		return "SellPrice_find";
	}

	// 删除销售价格
	public String deleteSellPrice() {
		sellPrice = sellPriceServer.findSellPriceById(id);
		if (sellPrice != null) {
			if (sellPriceServer.deleteSellPrice(sellPrice) == true) {
				successMessage = "删除" + sellPrice.getName() + ":"
						+ sellPrice.getHair() + ":" + sellPrice.getClientManagement()
						+ "成功!";
				return "deleteSellPrice";
			} else {
				errorMessage = "删除" + sellPrice.getName() + ":"
						+ sellPrice.getHair() + ":" + sellPrice.getClientManagement()
						+ "失败!请重试!";
			}
		} else {
			errorMessage = "不存在该价格!";
		}

		return ERROR;
	}

	// 修改销售价格
	public String updateSellPrice() {
		boolean bool = this.sellPriceServer.updateSellPrice(sellPrice, attachment,
				attachmentFileName, fatherPartNumber);
		if (bool) {
			this.errorMessage = "修改成功!";
			url="SellPriceAction!tofind.action?";
		} else {
			this.errorMessage = "修改失败!";
		}
		return "error";
	}

	// 跳转到修改页面
	public String toupdate() {
		SellPrice sellPrice2 = sellPriceServer.findSellPriceById(id);
		
		if (sellPrice2 != null) {
			sellPrice = sellPrice2;
			clientNameList=sellPriceServer.findClientNameList();
			return "SellPrice_update";
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}

		return "error";
	}

	public SellPriceServer getSellPriceServer() {
		return sellPriceServer;
	}

	public void setSellPriceServer(SellPriceServer sellPriceServer) {
		this.sellPriceServer = sellPriceServer;
	}

	public String getFatherPartNumber() {
		return fatherPartNumber;
	}

	public void setFatherPartNumber(String fatherPartNumber) {
		this.fatherPartNumber = fatherPartNumber;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public SellPrice getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(SellPrice sellPrice) {
		this.sellPrice = sellPrice;
	}

	public List<SellPrice> getSellPriceList() {
		return sellPriceList;
	}

	public void setSellPriceList(List<SellPrice> sellPriceList) {
		this.sellPriceList = sellPriceList;
	}

	public List getUnsellPriceList() {
		return unsellPriceList;
	}

	public void setUnsellPriceList(List unsellPriceList) {
		this.unsellPriceList = unsellPriceList;
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

	public List<String> getClientNameList() {
		return clientNameList;
	}

	public void setClientNameList(List<String> clientNameList) {
		this.clientNameList = clientNameList;
	}

	

}
