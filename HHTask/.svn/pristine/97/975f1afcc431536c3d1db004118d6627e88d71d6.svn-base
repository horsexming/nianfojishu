package com.task.action.perform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.perform.PerformsingleServer;
import com.task.entity.bargain.Bargain;
import com.task.entity.bargain.BargainingDetails;
import com.task.entity.perform.Performsingle;
import com.task.entity.perform.PerformsingleDetail;
import com.task.entity.project.QuotedPrice;
import com.task.entity.sop.OutSourcingApp;

public class PerformsingleAction extends ActionSupport {
	private PerformsingleServer performsingleServer;
	private Performsingle performsingle;
	private String bargain_source;
	private String bargain_num;
	private String purchase_name;
	private List list;
	private List<OutSourcingApp> outSourcingApplist;
	private Float money1;
	private List<PerformsingleDetail> performsingleDetails;

	private String errorMessage;
	private String successMessage;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<Map> maps;
	private QuotedPrice quotedPrice;
	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private Bargain bargain;
	private Float price;
	private BargainingDetails bargainingDetails;
	private String ids;

	// 进入添加执行单页面 1
	public String jumpaddBargain() {
		if (!"".equals(bargain_source)) {
			if ("OA".equals(bargain_source)) {
				// 查询OA单号
				list = performsingleServer.findbargainSource1(bargain_num);
				// for (int i = 0; i < list.size(); i++) {
				// OaAppDetail appDetail = (OaAppDetail) list.get(0);
				// if(appDetail.getDetailCount()==null||appDetail.getDetailBudgetMoney()==null){
				// heji=0F;
				// }else{
				// heji +=
				// appDetail.getDetailCount()*appDetail.getDetailBudgetMoney();
				// }
				// }
			}
			if ("SB".equals(bargain_source)) {
				// 根据设备单号查询设备
				Object[] object = performsingleServer
						.findbargainSource2(bargain_num);
				list = (List) object[0];
				bargainingDetails = this.performsingleServer
						.findBargainprice(bargain_num);// 查询相应的单价
			}
			if ("KVP".equals(bargain_source)) {
				// 根据kvp编号查询kvp
				Object[] object = performsingleServer
						.findbargainSource3(bargain_num);
				list = (List) object[0];
			}
			if ("零部件及工序外委采购".equals(bargain_source)) {
				// 根据外委申请编号查询外购外委评审
				// Object[] object =
				// performsingleServer.findbargainSource5(bargain_num);
				// list = (List) object[0];
				list = performsingleServer.findDGoodsListByIds(ids);
			}
			if ("原材料采购".equals(bargain_source)) {
				// 查询原材料的明细
				list = performsingleServer.findbargainSource6(bargain_num);
			}
			if ("包装物".equals(bargain_source)) {
				// 查询为包装物的明细
				list = performsingleServer.findbargainSource6(bargain_num);
				if (list != null)
					pageSize = list.size();
				else
					pageSize = 0;
			}
			if ("XBYJ".equals(bargain_source)) {
				// 根据议价单号查询询比议价
				Object[] object = performsingleServer
						.findbargainSource4(bargain_num);
				list = (List) object[0];
				money1 = (Float) object[1];
				// for (int i = 0; i < list.size(); i++) {
				// BargainGoods bargainGoods = (BargainGoods) list.get(i);
				// if(bargainGoods.getGoods_amount()==null||money1==null){
				// heji=0F;
				// }else{
				// heji +=
				// Integer.parseInt(bargainGoods.getGoods_amount())*money1;
				// }
				// }
			}
			if ("设备".equals(bargain_source)) {
				// 查看设备明细
				list = performsingleServer.findbargainSource7(bargain_num);
			}
		}
			return "jumpaddBargain";
			// 1 performsingleAction_addPerformsingle
	}

	// 添加采购执行单
	@SuppressWarnings("unchecked")
	public String addPerformsingle() {
		// 根据外委申请编号查询外购外委评审
		Object[] object = performsingleServer.findbargainSource5(bargain_num);
		outSourcingApplist = (List<OutSourcingApp>) object[0];
		boolean bool = performsingleServer.addPerformsingle(performsingle,
				performsingleDetails, outSourcingApplist);
		if (bool) {
			return "addPerformsingle";
		} else {
			return ERROR;
		}
	}

	// 删除采购执行单
	@SuppressWarnings("unchecked")
	public String delPerformsingle() {
		// 根据外委申请编号查询外购外委评审
		Object[] object = performsingleServer.findbargainSource5(bargain_num);
		outSourcingApplist = (List<OutSourcingApp>) object[0];
		boolean bool = performsingleServer.delPerformsingle(performsingle,
				outSourcingApplist);
		if (bool) {
			return "delPerformsingle";
		} else {
			return ERROR;
		}
	}

	// 查询采购申请单
	public String findPerformsingle() {
		if (performsingle != null) {
			ActionContext.getContext().getSession().put("performsingle",
					performsingle);
		} else {
			performsingle = (Performsingle) ActionContext.getContext()
					.getSession().get("performsingle");
		}
		Object[] object = this.performsingleServer.findPerformsingle(
				performsingle, Integer.parseInt(cpage), pageSize, tag);
		list = performsingleServer.findPerformsingle1(tag);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("performsingleAction_findPerformsingle.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findPerformsingle";
	}

	public String salPerformsingle() {
		// 根据单号查询OA
		if (performsingle != null) {
			Object[] b = performsingleServer.salPerformsingle(performsingle);
			list = (List) b[0];
			// for (int i = 0; i < list.size(); i++) {
			// PerformsingleDetail detail = (PerformsingleDetail) list.get(i);
			// }
			performsingle = (Performsingle) b[1];
			// if("OA".equals(performsingle.getPurchase_category())){
			// //获取项目对象
			// this.quotedPrice =
			// this.performsingleServer.getQuotedPrice(performsingle);
			// //获取询比议价
			// bargain = this.performsingleServer.findbargain(performsingle);
			// }if("SB".equals(performsingle.getPurchase_category())){
			// //获取项目
			// this.quotedPrice =
			// this.performsingleServer.getQuotedPrice1(performsingle);
			// //获取询比议价
			// bargain = this.performsingleServer.findbargain(performsingle);
			// }
			return "salPerformsingle";
		} else {
			return ERROR;
		}
	}

	// 采购执行单审批
	public String findContractExamList() {
		Object[] obj = performsingleServer.findContractExamList(Integer
				.parseInt(cpage), pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("performsingleAction_findContractExamList.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findContractExamList";
	}

	// 采购执行单审批
	public String updateContractExamList() {
		try {
			if (performsingleServer.updateContractExamList(detailSelect, tag)) {
				return "updateContractExamList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	public PerformsingleServer getPerformsingleServer() {
		return performsingleServer;
	}

	public void setPerformsingleServer(PerformsingleServer performsingleServer) {
		this.performsingleServer = performsingleServer;
	}

	public Performsingle getPerformsingle() {
		return performsingle;
	}

	public void setPerformsingle(Performsingle performsingle) {
		this.performsingle = performsingle;
	}

	public String getBargain_source() {
		return bargain_source;
	}

	public void setBargain_source(String bargainSource) {
		bargain_source = bargainSource;
	}

	public String getBargain_num() {
		return bargain_num;
	}

	public void setBargain_num(String bargainNum) {
		bargain_num = bargainNum;
	}

	public String getPurchase_name() {
		return purchase_name;
	}

	public void setPurchase_name(String purchaseName) {
		purchase_name = purchaseName;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Float getMoney1() {
		return money1;
	}

	public void setMoney1(Float money1) {
		this.money1 = money1;
	}

	public List<PerformsingleDetail> getPerformsingleDetails() {
		return performsingleDetails;
	}

	public void setPerformsingleDetails(
			List<PerformsingleDetail> performsingleDetails) {
		this.performsingleDetails = performsingleDetails;
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

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public QuotedPrice getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(QuotedPrice quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public Bargain getBargain() {
		return bargain;
	}

	public void setBargain(Bargain bargain) {
		this.bargain = bargain;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public BargainingDetails getBargainingDetails() {
		return bargainingDetails;
	}

	public void setBargainingDetails(BargainingDetails bargainingDetails) {
		this.bargainingDetails = bargainingDetails;
	}

	public List<OutSourcingApp> getOutSourcingApplist() {
		return outSourcingApplist;
	}

	public void setOutSourcingApplist(List<OutSourcingApp> outSourcingApplist) {
		this.outSourcingApplist = outSourcingApplist;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
