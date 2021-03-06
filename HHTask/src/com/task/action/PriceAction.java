package com.task.action;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ClientManagementServer;
import com.task.Server.JiMiLeiXingServerDao;
import com.task.Server.PriceServerDao;
import com.task.Server.WareHouseAuthService;
import com.task.Server.menjin.AccessEquipmentServer;
import com.task.entity.AppLiPrice;
import com.task.entity.ClientManagement;
import com.task.entity.JiMiLeiXing;
import com.task.entity.Price;
import com.task.entity.PriceList;
import com.task.entity.Users;
import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.BarContractDetails;
import com.task.entity.menjin.AccessWebcam;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

@SuppressWarnings( { "serial", "unchecked" })
public class PriceAction extends ActionSupport {

	private PriceServerDao priceServerDao;
	private Price price;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private PriceList pricelist1;//
	private List priceList;
	private List unpriceList;
	private String fatherPartNumber = "";
	private String errorMessage;
	private String successMessage;
	private int id;
	private String hiddenvaul;
	private int[] idArray;
	private List<Price> price_KHList;
	private YuanclAndWaigj yuanclAndWaigj;// 原材料和外购件对象
	private List<YuanclAndWaigj> YuanclAndWaigjList;// 原材料和外购件列表
	private BarContract barContract;// 采购合同对象
	private BarContractDetails barContractDetails;// 合同明细
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String statue = "update";
	private String markId;// 件号
	private String productCategory1;// 产品类别;
	private ZhuserOffer zhuserOffer;//
	private JiMiLeiXingServerDao jimileixingserver;
	private AccessEquipmentServer accessEquipmentServer;
	private List<JiMiLeiXing> jimiList;
	private List<AccessWebcam> accessWebcamlist;
	private Integer num_1;
	private ClientManagementServer cms;
	private List<ClientManagement> cmList;
	private List<ZhUser> zhuserList;// 供应商
	private List<Object[]> reData;// 返回数据
	private ClientManagement cm;
	private ProcardTemplate pt;
	private File addprice;
	private String partNumber;
	private ZhUser ZhUser;
	private WareHouseAuthService wareHouseAuthService;
	private List<String> strList;
	private List<String> strList1;
	private BarContract bt;
	private List<AppLiPrice> appLiPriceList;
	private String status;
	private AppLiPrice appLiPrice;
	private String gys;
	private String tags;

	public void pricetime() {
		// String[] value = { markId, productCategory1 };
		Price price1 = priceServerDao.findPriceByMarkIdMaxTime(price);
		String endtime = null;
		if (price1 != null) {
			endtime = price1.getPricePeriodEnd();

		}
		MKUtil.writeJSON(endtime);

	}

	// 初始化添加页面;
	public String initadd() {
		price = new Price();
		price.setFileNumber(priceServerDao.findMaxfileNumberprice());
		price.setPricePeriodStart(Util.getDateTime("yyyy-MM-dd"));
//		try {
//			price.setPricePeriodEnd(Util.DateToString(Util.getCalendarDate(new Date(), 364), "yyyy-MM-dd"));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		if (errorMessage != null && "true".equals(errorMessage.trim())) {
			errorMessage = "添加成功!";
		}
		cmList = cms.queryAllClient();
		Users user = Util.getLoginUser();
		strList = wareHouseAuthService.getUpdate(user.getCode());
		if (strList == null || strList.size() == 0) {
			return "price_noAdd";
		}
//		zhuserList = priceServerDao.findAllZhUser();
		return "showjsp";//addPrice.jsp
	}

	public String shenqing() {
		errorMessage = priceServerDao.addAppLIPrice(id);
		if ("申请成功".equals(errorMessage))
			url = "PriceAction!findPriceByCondition.action?statue=" + statue;
		return "error";
	}

	public String initaddwg() {
		price = new Price();
		price.setFileNumber(priceServerDao.findMaxfileNumberprice());
		if (errorMessage != null && "true".equals(errorMessage.trim())) {
			errorMessage = "添加成功!";
		}
//		zhuserList = priceServerDao.findAllZhUser();
		strList = new ArrayList<String>();
		statue = "addwg";
		strList.add("外购");
		return "showjsp";
	}

	public void getzhuserOfferById() {
		zhuserOffer = priceServerDao.findZhuserOfferById(id);
		try {
			MKUtil.writeJSON(zhuserOffer);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getHtNum() {
		strList = priceServerDao.getAllHtNum();
		try {
			MKUtil.writeJSON(strList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getBarContractByNum() {
		bt = priceServerDao.getBarContractByNum(partNumber);
		try {
			if (bt != null) {
				MKUtil.writeJSON(bt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findAllZhUser() {
		try {
			zhuserList = priceServerDao.findAllZhUser();
			MKUtil.writeJSON(zhuserList);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}

	// 添加价格
	public String addPrice() {
		// Price price1 = null;
		// if (!"其他".equals(price.getProductCategory())) {
		// price1 = priceServerDao.findPriceByMarkIdMaxTime(price);
		// }
		// if ("".equals(price.getPricePeriodStart())) {
		// errorMessage = "价格有效期限起始时间不能为空!";
		// return ERROR;
		// } else if (price1 != null
		// && price1.getPricePeriodStart() != null
		// && price1.getPricePeriodStart().length() > 0
		// && price.getPricePeriodStart() != null
		// && price.getPricePeriodStart().length() > 0
		// && !Util.compareTime(price.getPricePeriodStart(), "yyyy-MM-dd",
		// price1.getPricePeriodStart(), "yyyy-MM-dd")) {
		// errorMessage = "价格有效期限起始时间" + price.getPricePeriodStart()
		// + "不能比之前的价格有效期起始时间" + price1.getPricePeriodStart() + "早！";
		// return ERROR;
		// }
		// if (price1 != null) {
		// if ("true".equals(hiddenvaul)) {
		// Price oldPrice = priceServerDao.findPriceById(price1.getId());
		// if (price.getPricePeriodStart().equals(
		// price1.getPricePeriodStart())) {
		// price1.setPricePeriodEnd(price.getPricePeriodStart());
		//
		// } else {
		// price1.setPricePeriodEnd(Util.getSpecifiedDayAfter(price
		// .getPricePeriodStart(), -1));
		// }
		// boolean bool = priceServerDao.updatePrice(price1, attachment,
		// attachmentFileName, oldPrice);
		// if (!bool) {
		// errorMessage = "添加" + price.getProductCategory() + ":"
		// + price.getName() + "失败!请重试";
		// return ERROR;
		// }
		// }
		// }
		if (pricelist1 != null && pricelist1.getPriceList() != null
				&& pricelist1.getPriceList().size() > 0) {
			priceList = pricelist1.getPriceList();
		}
		errorMessage = priceServerDao.addPrice(priceList, price, attachment,
				attachmentFileName, fatherPartNumber, id);
		if ("true".equals(errorMessage)) {
			return "initadd";
		}
		return ERROR;
	}

	// 分页查询
	public String findAllPrice() {
		Users user = Util.getLoginUser();
		boolean b = priceServerDao.getAccessPermission();
		if (!b) {
			errorMessage = "当前外部网络无法访问!";
			return ERROR;
		}
		strList = wareHouseAuthService.getShow(user.getCode());
		strList1 = wareHouseAuthService.getPrice(user.getCode());
		priceList = priceServerDao.findAllPrice(Integer.parseInt(cpage),
				pageSize);
		int count = priceServerDao.getCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		price = null;
		if ("find".equals(statue)) {
			this.setUrl("PriceAction!findAllPrice.action?statue=find");
			return "findAllPriceSuccess";//findAllPrice.jsp
		} else if ("update".equals(statue)) {
			this.setUrl("PriceAction!findAllPrice.action?statue=update");
			return "updateFileNumberSuccess";
		} else {
			return ERROR;
		}
	}
	
	public String querenGuihao(){
		errorMessage = priceServerDao.querenGuihao(price);
		url = "PriceAction!findPriceWeiCun.action?statue="+ statue + "&tags=" + tags;
		return "error";
	}
	
	// 未存柜的条件查询
	public String findPriceWeiCun() {
		Users user = Util.getLoginUser();
		if (price != null) {
			ActionContext.getContext().getSession().put("PRICEwei", price);
		} else {// 用来保持分页时带着查询条件
			price = (Price) ActionContext.getContext().getSession()
					.get("PRICEwei");
		}
		accessWebcamlist = accessEquipmentServer.findDanganGui(null);
		strList1 = wareHouseAuthService.getPrice(user.getCode());
		Map<Integer, Object> map = priceServerDao.findPriceWeiCun(price,
				Integer.parseInt(cpage), pageSize, statue, tags);
		unpriceList = (List) map.get(1);
		if (unpriceList != null && unpriceList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PriceAction!findPriceWeiCun.action?statue="
					+ statue + "&tags=" + tags);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "findAllWeiPrice";//findAllWeiPrice.jsp
	}

	// 条件查询
	public String findPriceByCondition() {
		if (price != null) {
			ActionContext.getContext().getSession().put("PRICE", price);
		} else {// 用来保持分页时带着查询条件
			price = (Price) ActionContext.getContext().getSession()
					.get("PRICE");
		}
		Users user = Util.getLoginUser();
		strList1 = wareHouseAuthService.getPrice(user.getCode());
		ZhUser zhuser = priceServerDao.findZhuserBygys(gys);
		if (zhuser != null) {
			price.setGysId(zhuser.getId());
		}
		Map<Integer, Object> map = priceServerDao.findPriceByCondition(price,
				Integer.parseInt(cpage), pageSize, statue, tags);
		if (map.get(3) != null) {
			unpriceList = (List) map.get(3);// 没有归档的档案列表
		}
		priceList = (List<Price>) map.get(1);// 显示分页的档案列表
		if (priceList != null && priceList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PriceAction!findPriceByCondition.action?statue="
					+ statue + "&tags=" + tags);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("find".equals(statue)) {
			if ("admin".equals(tags)) {
				unpriceList = null;
			}
			this.setUrl("PriceAction!findPriceByCondition.action?statue=find"
					+ "&tags=" + tags);
			return "findAllPriceSuccess";// findAllPrice.jsp
		} else if ("update".equals(statue)) {
			this.setUrl("PriceAction!findPriceByCondition.action?statue=update"
					+ "&tags=" + tags);
			return "updateFileNumberSuccess";
		} else if ("all".equals(statue)) {
			this.setUrl("PriceAction!findPriceByCondition.action?statue=all"
					+ "&tags=" + tags);
			return "findAllPriceSuccess";
		} else if ("ALL".equals(statue)) {
			this.setUrl("PriceAction!findPriceByCondition.action?statue=ALL"
					+ "&tags=" + tags);
			return "findAllPriceSuccess";
		} else if ("dept".equals(statue)) {
			this.setUrl("PriceAction!findPriceByCondition.action?statue=dept"
					+ "&tags=" + tags);
			return "findAllPriceSuccess";
		} else if ("single".equals(statue)) {
			this.setUrl("PriceAction!findPriceByCondition.action?statue=single"
					+ "&tags=" + tags);
			return "updateFileNumberSuccess";
		} else if ("dangan".equals(statue)) {
			this
					.setUrl("PriceAction!findPriceByCondition.action?statue=dangan&num_1="
							+ num_1);
			return "danganNumberSuccess";
		} else {
			return ERROR;
		}
		// HttpServletRequest request = ServletActionContext.getRequest();
		// int pageSize = 15;
		// if (price != null) {
		// request.getSession().setAttribute("PRICE", price);
		// } else {
		// price = (Price) request.getSession().getAttribute("PRICE");
		// }
		// priceList = priceServerDao.findPriceByCondition(price, Integer
		// .parseInt(cpage), pageSize);
		// this.setUrl("PriceAction!findPriceByCondition.action");
		// this.setTotal((String) request.getAttribute("total"));
		// price = null;
		// if ("find".equals(statue)) {
		// this.setUrl("PriceAction!findPriceByCondition.action?statue=find");
		// return "findAllPriceSuccess";
		// } else if ("update".equals(statue)) {
		// this
		// .setUrl("PriceAction!findPriceByCondition.action?statue=update");
		// return "updateFileNumberSuccess";
		// } else {
		// return ERROR;
		// }
	}

	public String findAllPrice1() {
		Map map = new HashMap();
		if (price != null) {
			if (price.getName() != null && !"".equals(price.getName())) {
				map.put("name", price.getName());
			}
			if (price.getPartNumber() != null
					&& !"".equals(price.getPartNumber())) {
				map.put("partNUmber", price.getPartNumber());
			}
			if (price.getKehuId() != null && price.getKehuId() > 0) {
				map.put("kehuId", price.getKehuId());
			}
		}
		if (statue != null && !"".equals(statue)) {
			map.put("statue", statue);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (successMessage == null || !successMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else {
				ActionContext.getContext().getSession().remove("condition");
			}
		}
		Map<Integer, Object> obj = priceServerDao.findPriceByCondition(map,
				Integer.parseInt(cpage), pageSize);
		priceList = (List<Price>) obj.get(1);// 显示分页的档案列表
		cmList = cms.queryAllClient();
//		zhuserList = priceServerDao.findAllZhUser();
		if (priceList != null & priceList.size() > 0) {
			int count = (Integer) obj.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PriceAction!findAllPrice1.action?statue=" + statue);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "product_price";
	}

	public String showKHPrice() {
		Map<String, String> map = new HashMap<String, String>();
		if (price != null) {
			if (price.getName() != null && !"".equals(price.getName())) {
				map.put("name", price.getName());
			}
			if (price.getPartNumber() != null
					&& !"".equals(price.getPartNumber())) {
				map.put("partNUmber", price.getPartNumber());
			}
		}
		if (id > 0) {
			map.put("kehuId", id + "");
			cm = cms.getClientManagementById(id);
		}
		if (statue != null && "KHPrice".equals(statue)) {
			map.put("statue", statue);
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (successMessage == null || !successMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
				if (map.get("kehuId") != null) {
					id = Integer.parseInt(map.get("kehuId"));
					cm = cms.getClientManagementById(id);
				}
			} else {
				ActionContext.getContext().getSession().remove("condition");
			}
		}
		Map<Integer, Object> obj = priceServerDao.findPriceByCondition(map,
				Integer.parseInt(cpage), pageSize);
		priceList = (List<Price>) obj.get(1);// 显示分页的档案列表
		cmList = cms.queryAllClient();
		if (priceList != null & priceList.size() > 0) {
			int count = (Integer) obj.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PriceAction!showKHPrice.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "client_Showbdprice";

	}

	// 通过id查找价格
	public String findPriceById() {
		price = priceServerDao.findPriceById(id);
		jimiList = jimileixingserver.showJiMiList();
//		zhuserList = priceServerDao.findAllZhUser();
		accessWebcamlist = accessEquipmentServer.findDanganGui(null);
		Users user = Util.getLoginUser();
		strList1 = wareHouseAuthService.getPrice(user.getCode());
		strList = wareHouseAuthService.getUpdate(user.getCode());
		cmList = cms.queryAllClient();
		if (price != null) {
			if ("xgprice".equals(statue)) {
				return "xgprice";//product_xgprice.jsp
			} else {
				errorMessage = isyouxiaoqi(price);
				return "findPriceByIdSuccess";//showPrice.jsp
			}
		}
		errorMessage = "不存在此价格!";
		return ERROR;
	}

	public void findPriceById1() {
		price = priceServerDao.findPriceById(id);
		try {
			MKUtil.writeJSON(price);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findZhuerList() {
		reData = priceServerDao.findZhUserIdAndName();
		MKUtil.writeJSON(reData);
	}

	// 根据客户Id查询产品信息;
	public String findPriceBykehuId() {
		Map<String, String> map = new HashMap<String, String>();
		if (id > 0) {
			map.put("id", id + "");
			cm = cms.getClientManagementById(id);
		}
		if (price != null) {
			if (price.getName() != null && !"".equals(price.getName())) {
				map.put("name", price.getName());
			}
			if (price.getPartNumber() != null
					&& !"".equals(price.getPartNumber())) {
				map.put("partNUmber", price.getPartNumber());
			}
		}
		if (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (successMessage == null || !successMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
			} else {
				ActionContext.getContext().getSession().remove("condition");
			}
		}
		Map<Integer, Object> obj = priceServerDao.findPriceBykehuId(map,
				Integer.parseInt(cpage), pageSize);
		priceList = (List<Price>) obj.get(1);// 显示分页的档案列表
		cmList = cms.queryAllClient();
		if (priceList != null & priceList.size() > 0) {
			int count = (Integer) obj.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("PriceAction!findPriceBykehuId.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "client_Showprice";

	}

	// 删除价格
	public String deletePrice() {
		price = priceServerDao.findPriceById(id);
		if (price != null) {
			if (priceServerDao.deletePrice(price) == true) {
				successMessage = "删除" + price.getName() + "成功!";
				return "deletePriceSuccess";
			} else {
				errorMessage = "删除" + price.getName() + "失败!请重试!";
			}
		} else {
			errorMessage = "不存在该价格!";
		}

		return ERROR;
	}

	// 修改价格
	public String updatePrice() {
		Price oldPrice = priceServerDao.findPriceById(price.getId());
	errorMessage= priceServerDao.updatePrice(price, attachment,
				attachmentFileName, oldPrice);
		if ("true".equals(errorMessage)) {
			if (oldPrice.getDanganId() == null && price.getDanganId() != null
					&& !"".equals(price.getDanganId())) {
				accessEquipmentServer
						.add1AndLess1(null, price.getDanganId(), 2);
			} else if (oldPrice.getDanganId() != null
					&& price.getDanganId() != null
					&& !price.getDanganId().equals(oldPrice.getDanganId())) {
				accessEquipmentServer.add1AndLess1(oldPrice.getDanganId(),
						price.getDanganId(), 1);
			} else {
			}
			this.id = price.getId();

			return "updatePriceSuccess";
		}
		errorMessage = errorMessage+="修改" + price.getName() + "失败!请重试!";
		return ERROR;
	}

	public String updatePrice1() {
		boolean bool = priceServerDao.updatePrice(price);
		if (bool) {
			errorMessage = "修改价格成功";
			return "xgprice";
		}
		errorMessage = "修改价格失败";
		return ERROR;
	}

	private String oldHost;

	// 修改档案号
	public String updateFileName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (price != null) {
			Price olcPrice = priceServerDao.findPriceById(id);
			if (olcPrice != null) {
				olcPrice.setFileNumber(price.getFileNumber());
				if (priceServerDao.updateFileName(olcPrice) == true) {
					String other = "fileNumber=" + olcPrice.getFileNumber();
					int num = priceServerDao.updateAttachmentName(olcPrice,
							other);
					if (num > 0) {
						String host = request.getHeader("Referer");
						oldHost = host.substring(host.lastIndexOf("/"))
								.replaceAll("/", "");
						return "updateFileNameSuccess";
					}
				}
			}
		}
		return ERROR;
	}

	// 成件最大档案号；
	public void PriceMaxFileName() {
		try {
			String maxfileNumber = priceServerDao.findMaxfileNumberprice();
			MKUtil.writeJSON(maxfileNumber);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}

	}

	/**
	 * 根据件号查询合同信息(外委工序使用)
	 * 
	 * @return
	 */
	public String findPriceByMarkId() {
		try {
			Price price = priceServerDao.findPriceByMarkId(markId);

			MKUtil.writeJSON(price);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
		return null;
	}

	// 绑定客户产品
	public String updatekehuId() {
		priceServerDao.updatePriceKehuId(idArray, id);
		return "jiechukehuId";
	}

	// 解除客户产品
	public String jiechukehuId() {
		priceServerDao.jiechuPriceKehuId(price.getId());
		price = priceServerDao.findPriceById(price.getId());
		return "jiechukehuId";
	}

	// 判断某个产品是否在价格有限期内 在有效期内为true,不在为false;
	private String isyouxiaoqi(Price price1) {
		if (price1 != null) {
			if (price1.getPricePeriodEnd() == null
					|| "".equals(price1.getPricePeriodEnd())) {
				return "true";
			} else if (price1.getPricePeriodStart() != null
					&& !"".equals(price1.getPricePeriodStart() != null)
					&& price1.getPricePeriodEnd() != null
					&& !"".equals(price1.getPricePeriodEnd())) {
				Date date2 = Util.StringToDate(price1.getPricePeriodEnd(),
						"yyyy-MM-dd");
				boolean bool = new Date().before(date2);
				return bool + "";
			}
		}
		return "";
	}

	// 批量导入外购件

	public String Pladdprice() {
		errorMessage = priceServerDao.PladdPrice(addprice, statue, price
				.getProduceType());
		if ("true".equals(errorMessage)) {
			errorMessage = "导入成功!";
		}
		return "error";
	}

	// 根据件号 查出规格
	public void findSpecification() {
		try {
			List<String> strList = new ArrayList<String>();
			strList = priceServerDao.findSpecification(partNumber);
			if (strList != null && strList.size() > 0) {
				MKUtil.writeJSON(strList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}

	// 根据件号 查到该件号采购比例总和
	public void findsumcgbl() {
		try {
			float cgbl = priceServerDao.findsumcgbl(partNumber);
			MKUtil.writeJSON(cgbl);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}

	}

	// 根据件号，名称，规格，查询price
	public void findprice() {
		try {
			price = priceServerDao.findeprice(price);
			MKUtil.writeJSON(price);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}

	}

	/**
	 * 通过件号，少部分字段获取整个外购件原材信息
	 */
	public void getAllNames() {
		if (yuanclAndWaigj != null
				&& (yuanclAndWaigj.getMarkId() != null || yuanclAndWaigj
						.getName() != null)) {
			YuanclAndWaigjList = priceServerDao.getAllNames(yuanclAndWaigj);
			if (YuanclAndWaigjList != null && YuanclAndWaigjList.size() > 0) {
				MKUtil.writeJSON(YuanclAndWaigjList);
			}
		}
	}
	
	/**
	 * 检索外购件原材料信息
	 */
	public void searchAllNames(){
		if(yuanclAndWaigj!=null && yuanclAndWaigj.getMarkId()!=null){
			YuanclAndWaigjList = priceServerDao.searchAllNames(yuanclAndWaigj);
			if (YuanclAndWaigjList != null && YuanclAndWaigjList.size() > 0) {
				MKUtil.writeJSON(YuanclAndWaigjList);
			}
		}
		
	}

	/**
	 * 通过件号
	 */
	public void getpriceListby() {
		if (pt != null) {
			Object[] obj = priceServerDao.getpdtList(pt);
			if (obj != null && obj.length == 2) {
				MKUtil.writeJSON(obj);
			}

		}
	}

	// 根据件号，规格，名称， 查询出对应的工序
	public void getgongxunum() {
		if (pt != null) {
			List<ProcessTemplate> ptList = priceServerDao.getgongxunum(pt);
			MKUtil.writeJSON(ptList);
		}
	}

	// 根据供应商Id 查出供应商信息；
	public void finfZhuserByid() {
		if (id > 0) {
			try {
				ZhUser = priceServerDao.finfZhuserByid(id);
				MKUtil.writeJSON(ZhUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 查找某个人是否有手机端的权限
	public void findPhoneqx() {
		Users user = Util.getLoginUser();
		strList = wareHouseAuthService.getPhone(user.getCode());
		try {
			MKUtil.writeJSON(strList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getProcardMx() {
		Map<String, Object> map = priceServerDao.getProcardMx(id);
		if (map != null && map.size() > 0) {
			if (map.get("price") != null) {
				price = (Price) map.get("price");
			}
			if (map.get("barContract") != null) {
				barContract = (BarContract) map.get("barContract");
			}
		}
		return "procard_laiyuan";
	}

	/**
	 * 查询所有合同档案文件申请页面
	 * 
	 * @return
	 */
	public String findAllAppLiPrice() {
		if (appLiPrice != null) {
			ActionContext.getContext().getSession().put("AppLiPrice",
					appLiPrice);
		} else {
			appLiPrice = (AppLiPrice) ActionContext.getContext().getSession()
					.get("AppLiPrice");
		}
		Map<Integer, Object> map = priceServerDao.findAllAppLiPrice(appLiPrice,
				Integer.parseInt(cpage), pageSize, status);
		appLiPriceList = (List<AppLiPrice>) map.get(1);
		if (appLiPriceList != null & appLiPriceList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("PriceAction!findAllAppLiPrice.action?status="
							+ status);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "AppLiPrice_List";
	}

	// 通过件号查询总成名
	public void findZCPtName() {
		try {
			ProcardTemplate pt = priceServerDao.findZCPtName(markId);
			MKUtil.writeJSON(pt);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void cgblpd(){
		try {
			price =	priceServerDao.findPriceById(id);
			errorMessage =	priceServerDao.cgblpd(price);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导出价格 exportExcel
	public void exportExcel(){
		ZhUser zhuser = priceServerDao.findZhuserBygys(gys);
		if (zhuser != null) {
			price.setGysId(zhuser.getId());
		}
		priceServerDao.exportExcel(price, statue);
	}
	// 批量把之前有限期内的价格失效掉，只保留最近录入的一条
	
	public void loseEfficacyPrice(){
		priceServerDao.loseEfficacyPrice(price);
	}
	
	public AppLiPrice getAppLiPrice() {
		return appLiPrice;
	}

	public void setAppLiPrice(AppLiPrice appLiPrice) {
		this.appLiPrice = appLiPrice;
	}

	public List<AppLiPrice> getAppLiPriceList() {
		return appLiPriceList;
	}

	public void setAppLiPriceList(List<AppLiPrice> appLiPriceList) {
		this.appLiPriceList = appLiPriceList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOldHost() {
		return oldHost;
	}

	public void setOldHost(String oldHost) {
		this.oldHost = oldHost;
	}

	public List getPriceList() {
		return priceList;
	}

	public void setPriceList(List priceList) {
		this.priceList = priceList;
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

	public File[] getAttachment() {
		return attachment;
	}

	public void setAttachment(File[] attachment) {
		this.attachment = attachment;
	}

	public PriceServerDao getPriceServerDao() {
		return priceServerDao;
	}

	public void setPriceServerDao(PriceServerDao priceServerDao) {
		this.priceServerDao = priceServerDao;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
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

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public List getUnpriceList() {
		return unpriceList;
	}

	public void setUnpriceList(List unpriceList) {
		this.unpriceList = unpriceList;
	}

	public void setHiddenvaul(String hiddenvaul) {
		this.hiddenvaul = hiddenvaul;

	}

	public String getHiddenvaul() {
		return hiddenvaul;
	}

	public void setProductCategory1(String productCategory1) {
		this.productCategory1 = productCategory1;
	}

	public String getProductCategory1() {
		return productCategory1;
	}

	public JiMiLeiXingServerDao getJimileixingserver() {
		return jimileixingserver;
	}

	public void setJimileixingserver(JiMiLeiXingServerDao jimileixingserver) {
		this.jimileixingserver = jimileixingserver;
	}

	public List<JiMiLeiXing> getJimiList() {
		return jimiList;
	}

	public void setJimiList(List<JiMiLeiXing> jimiList) {
		this.jimiList = jimiList;
	}

	public Integer getNum_1() {
		return num_1;
	}

	public void setNum_1(Integer num_1) {
		this.num_1 = num_1;
	}

	public List<AccessWebcam> getAccessWebcamlist() {
		return accessWebcamlist;
	}

	public void setAccessWebcamlist(List<AccessWebcam> accessWebcamlist) {
		this.accessWebcamlist = accessWebcamlist;
	}

	public AccessEquipmentServer getAccessEquipmentServer() {
		return accessEquipmentServer;
	}

	public void setAccessEquipmentServer(
			AccessEquipmentServer accessEquipmentServer) {
		this.accessEquipmentServer = accessEquipmentServer;
	}

	public ClientManagementServer getCms() {
		return cms;
	}

	public void setCms(ClientManagementServer cms) {
		this.cms = cms;
	}

	public List<ClientManagement> getCmList() {
		return cmList;
	}

	public void setCmList(List<ClientManagement> cmList) {
		this.cmList = cmList;
	}

	public int[] getIdArray() {
		return idArray;
	}

	public void setIdArray(int[] idArray) {
		this.idArray = idArray;
	}

	public ClientManagement getCm() {
		return cm;
	}

	public void setCm(ClientManagement cm) {
		this.cm = cm;
	}

	public List<ZhUser> getZhuserList() {
		return zhuserList;
	}

	public void setZhuserList(List<ZhUser> zhuserList) {
		this.zhuserList = zhuserList;
	}

	public PriceList getPricelist1() {
		return pricelist1;
	}

	public void setPricelist1(PriceList pricelist1) {
		this.pricelist1 = pricelist1;
	}

	public File getAddprice() {
		return addprice;
	}

	public void setAddprice(File addprice) {
		this.addprice = addprice;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public YuanclAndWaigj getYuanclAndWaigj() {
		return yuanclAndWaigj;
	}

	public void setYuanclAndWaigj(YuanclAndWaigj yuanclAndWaigj) {
		this.yuanclAndWaigj = yuanclAndWaigj;
	}

	public List<YuanclAndWaigj> getYuanclAndWaigjList() {
		return YuanclAndWaigjList;
	}

	public void setYuanclAndWaigjList(List<YuanclAndWaigj> yuanclAndWaigjList) {
		YuanclAndWaigjList = yuanclAndWaigjList;
	}

	public ProcardTemplate getPt() {
		return pt;
	}

	public void setPt(ProcardTemplate pt) {
		this.pt = pt;
	}

	public List<Object[]> getReData() {
		return reData;
	}

	public void setReData(List<Object[]> reData) {
		this.reData = reData;
	}

	public ZhUser getZhUser() {
		return ZhUser;
	}

	public void setZhUser(ZhUser zhUser) {
		ZhUser = zhUser;
	}

	public WareHouseAuthService getWareHouseAuthService() {
		return wareHouseAuthService;
	}

	public void setWareHouseAuthService(
			WareHouseAuthService wareHouseAuthService) {
		this.wareHouseAuthService = wareHouseAuthService;
	}

	public List<String> getStrList() {
		return strList;
	}

	public void setStrList(List<String> strList) {
		this.strList = strList;
	}

	public List<String> getStrList1() {
		return strList1;
	}

	public void setStrList1(List<String> strList1) {
		this.strList1 = strList1;
	}

	public BarContract getBt() {
		return bt;
	}

	public void setBt(BarContract bt) {
		this.bt = bt;
	}

	public BarContract getBarContract() {
		return barContract;
	}

	public void setBarContract(BarContract barContract) {
		this.barContract = barContract;
	}

	public BarContractDetails getBarContractDetails() {
		return barContractDetails;
	}

	public void setBarContractDetails(BarContractDetails barContractDetails) {
		this.barContractDetails = barContractDetails;
	}

	public ZhuserOffer getZhuserOffer() {
		return zhuserOffer;
	}

	public void setZhuserOffer(ZhuserOffer zhuserOffer) {
		this.zhuserOffer = zhuserOffer;
	}

	public String getGys() {
		return gys;
	}

	public void setGys(String gys) {
		this.gys = gys;
	}

	public List<Price> getPrice_KHList() {
		return price_KHList;
	}

	public void setPrice_KHList(List<Price> priceKHList) {
		price_KHList = priceKHList;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}
