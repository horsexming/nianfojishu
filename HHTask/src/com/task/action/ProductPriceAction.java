package com.task.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.ProductPriceServer;
import com.task.Server.UserServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.DTOProcess;
import com.task.entity.ProductPrice;
import com.task.entity.ProductProcess;
import com.task.entity.SpareParts;
import com.task.entity.TaSopGongwei;
import com.task.entity.Users;
import com.task.util.MKUtil;

public class ProductPriceAction extends ActionSupport {
	private ProductPrice productPrice;
	private ProductProcess productProcess;
	private SpareParts spareParts;
	private ProductPriceServer productPriceServer;
	private UserServer userServer;
	private List list;
	private String tag;// 通用标志
	private String code;
	private String messagePower;// 共用属性
	private Double OPLabourBeat; // 人工节拍
	private Double OPEquipmentBeat; // 设备节拍
	private Double PRLabourBeat; // 人工节拍
	private Double PRPrepareTIme; // 准备次数
	private String jobNum;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private Integer id;// 主键

	private String gongweihao;
	private String shebeiCode;
	private Integer[] productPriceId;

	private String startDate;// 开始时间
	private String endDate;// 截止时间
	private Float price;// 单价

	public String mkId;// 件号
	public Float rukuCount;// 入库数量
	public String errorMessage;
	public String successMessage;

	/**
	 * 市场输入报价人工费用
	 * 
	 * @return
	 */
	public String findbaojia() {
		this.pageSize = 15;
		this.setUrl("productPriceAction!findbaojia.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != productPrice) {
			request.getSession().setAttribute("productPrice", productPrice);
		} else {
			productPrice = (ProductPrice) request.getSession().getAttribute(
					"productPrice");
		}
		Object[] obj = productPriceServer.queryProductPrice(productPrice,
				Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findBaojia";
	}

	/**
	 * 更改直接人工
	 * 
	 * @return
	 */
	public String updatePrice() {
		ProductPrice pp = productPriceServer.getProductPrice(id);
		pp.setLaborcost(price);
		if (productPriceServer.updateProductPrice(pp)) {
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("dalyOut", "ok");
			MKUtil.writeJSON(map2);
		}

		return null;
	}

	// 添加总成件号单价
	public String saveProductPrice() {
		if (productPriceServer.saveProduct(productPrice)) {
			return "saveProductPriceSuccess";
		}
		return ERROR;
	}

	// 查询总成件号
	public String queryProductPrice() {
		this.messagePower = messagePower;
		this.pageSize = 16;
		this.setUrl("productPriceAction!queryProductPrice.action?messagePower="
				+ messagePower);
		Object[] obj = productPriceServer.queryProductPrice(productPrice,
				Integer.parseInt(cpage), pageSize);

		int count = (Integer) obj[0];

		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		if ("tijiang".equals(messagePower)) {
			return "tijiangShow";
		}
		return "queryProductPrice";
	}

	// 获取一个总成件号
	public String getOneProductPrice() {
		this.messagePower = messagePower;
		if ("update".equals(tag)) {
			// 修改单件计价
			productPrice = productPriceServer.getProductPrice(id);
			return "getproductPrice";
		} else if ("querySpareParts".equals(tag)) {
			list = productPriceServer.findSparePartsById(id);
			return "findSparePartsByID";
		} else if ("delete".equals(tag)) {
			this.cpage = cpage;
			if (productPriceServer.deleteProductPrice(id)) {// 删除成功
				return "deleteProductPriceOK";
			}
		}
		return "";
	}

	/**
	 * 临时分配
	 * 
	 * */
	// 通过件号查询总成以及组件
	public String findSpareParts() {
		ProductPrice productPrice = productPriceServer
				.findProductPriceByMkId(mkId);// 通过件号查询总成
		if (productPrice != null) {
			list = productPriceServer.findSparePartsById(productPrice.getId());
			return "findSpareParts";
		} else {
			errorMessage = "不存在该件号:" + mkId + "的总成信息，请录入!";
		}
		return ERROR;
	}

	// 根据ID查看工序表,修改零件也可共用
	public String fingProductProcess() {
		list = productPriceServer.findProductProcessById(id);
		return "findProductProcess";
	}

	/****
	 * 
	 * 临时结束
	 */

	// 修改总成件号
	public String updateProductPrice() {
		this.messagePower = messagePower;
		if (productPriceServer.updateProductPrice(productPrice)) {
			return "updateProductPriceOK";
		}
		return ERROR;
	}

	// 添加零件号
	public String saveSpareParts() {
		if (spareParts != null) {
			if (productPriceServer.saveSpareParts(spareParts, id)) {
				this.id = id;
				return "saveSparePartsOK";
			} else {
				return ERROR;
			}
		}
		return ERROR;
	}

	// 根据ID查看工序表,修改零件也可共用
	public String queryProductProcessById() {
		if ("update".equals(tag)) {// 修改获取
			spareParts = productPriceServer.getOneSpareParts(id);
			return "getOneSpareParts";
		} else if ("queryProductProcee".equals(tag)) {// 查询子工序
			list = productPriceServer.findProductProcessById(id);
			return "queryProductProcess";
		} else {
			return "";
		}
	}

	// 添加工序
	public String saveProductProcess() {
		if (null != productProcess) {
			productPriceServer.saveProductProcess(productProcess, id);
			return "saveProductProcessOK";
		} else {
			return ERROR;
		}
	}

	// 获取单个工序
	public String getOneProductProcessById() {
		if ("update".equals(tag)) {
			productProcess = productPriceServer.getProductProcess(id);
			// this.id=productProcess.getSpareParts().getId();
			return "getOneProductProcess";
		} else if ("delete".equals(tag)) {
			productProcess = productPriceServer.getProductProcess(id);
			this.id = productPriceServer.getProductProcess(
					productProcess.getId()).getSpareParts().getId();
			productPriceServer.deleteProductProces(productProcess);
			return "deleteProductProcessOK";
		} else {
			return ERROR;
		}
	}

	// 修改工序信息
	public String updateProductProcess() {
		// if(null!=productProcess){
		if (productPriceServer.updateProductProcee(productProcess)) {
			// this.id=productProcess.getSpareParts().getId();
			// 根据ID获取ID
			ProductProcess pro = productPriceServer
					.getProductProcess(productProcess.getId());
			this.id = pro.getSpareParts().getId();
			// this.id=productPriceServer.getSpareParets(id)
			return "updateProductProcessOK";
		} else {
			return ERROR;
		}
		// }

	}

	// 查找员工信息
	public String findUserName() {
		String message = productPriceServer.findUserName(code);
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

	// 查找工位信息
	/*
	public String findGongwei() {
		String message = productPriceServer.findGongwei("");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ERROR;
	}*/
	public void findGongwei() {
		if (tag!=null) {
			list=productPriceServer.findGongwei1(tag);
			MKUtil.writeJSON(list);
		}
		
	}

	// 根据工位查找设备编号
	public String findShebeiCode() {
		String message = productPriceServer.findShebeiCode(tag);
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

	// 修改零组件
	public String updateSpareParts() {
		this.id = productPriceServer.updateSpareParts(spareParts);
		return "updateSparePartsOK";
	}

	// 删除零组件
	public String deleteSpareParts() {
		this.id = productPriceServer.deleteSpareParts(id);
		return "deleteOK";
	}

	// 根据页面
	public String getGongweiAndOth() {
		// 通过JSON获取对象
		TaSopGongwei gongwei = productPriceServer.findGongweiAndOth(gongweihao,
				shebeiCode);
		gongwei.setProductProcess(null);
		gongwei.setProcessTemplate(null);
		SpareParts sp = productPriceServer.getOneSpareParts(id);
		sp.setProductProcess(null);
		ProductPrice pp = productPriceServer.getProductPrice(sp.getProduct()
				.getId());

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dalyOut", pp.getDailyoutput());
		map.put("gongwei", gongwei);
		MKUtil.writeJSON(map);
		return null;
	}

	/***
	 * 获得工位信息(工序模板修改使用)
	 * 
	 * @return
	 */
	public String getGongwei() {
		// 通过JSON获取对象
		TaSopGongwei gongwei = productPriceServer.findGongweiAndOth(gongweihao,
				shebeiCode);
		MKUtil.writeJSON(gongwei);
		return null;
	}

	// 根据页面
	public String getUpdateGongweiAndOth() {
		// 通过JSON获取对象
		TaSopGongwei gongwei = productPriceServer.findGongweiAndOth(gongweihao,
				shebeiCode);
		gongwei.setProductProcess(null);
		SpareParts sp = productPriceServer.getOneSpareParts(productPriceServer
				.getProductProcess(id).getSpareParts().getId());

		ProductPrice pp = productPriceServer.getProductPrice(sp.getProduct()
				.getId());
		pp.setSpareParts(null);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("dalyOut", pp.getDailyoutput());
		map2.put("gongwei", gongwei);

		MKUtil.writeJSON(map2);
		return null;
	}

	// 计算应提奖额和单工序的工资
	public String jisuanDanjianBonus() {
		this.messagePower = messagePower;
		this.cpage = cpage;
		if (productPriceServer.jisuanDanjianBonus(productPriceId)) {
			return "jisuanOK";
		}
		return ERROR;
	}

	/**
	 * @Description: 试制计算
	 * @return
	 * @throws
	 */

	public String shizhi() {
		this.messagePower = "tijiang";
		if (productPriceServer.shizhijisuan(startDate, endDate, productPriceId)) {
			AlertMessagesServerImpl.addAlertMessages("试制奖金审批", "月度试制奖金审批", "1");
		}
		return "shizhijisuan";
	}

	/**
	 * @Title: display
	 * @Description: 显示试算成品页面
	 * @return String
	 * @throws
	 */
	public String initTrial() {
		String jsonStr = productPriceServer.packageData(id, null);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonStr);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: saveData
	 * @Description: 保存数据
	 * @return String
	 * @throws
	 */
	public String saveData() {
		Map session = ActionContext.getContext().getSession();
		StringBuffer str = new StringBuffer();
		if (jobNum != null) {
			String[] allJobNum = jobNum.split(";");
			for (String num : allJobNum) {
				Users user = userServer.findUserByCode(num);
				if (user != null) {
					str.append(user.getName()).append(";");
					DTOProcess dto = new DTOProcess(id, jobNum, OPLabourBeat,
							OPEquipmentBeat, PRLabourBeat, PRPrepareTIme,
							(String) str.subSequence(0, str.length() - 1));
					session.put(id, dto);
				}
			}
		}
		return null;
	}

	public String packagePro() {
		Double mentioningAwardPrice = null;
		if (code != null && code.equals("1")) {
			ProductPrice pro = productPriceServer.getProductPrice(id);
			mentioningAwardPrice = pro.getOnePrice();
		} else {
			mentioningAwardPrice = productPriceServer.getBonus(id);
		}
		String jsonStr = productPriceServer.packageProduct(id,
				mentioningAwardPrice);
		if (jsonStr != null) {
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(jsonStr);
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Title: trial
	 * @Description: 试算
	 * @return String json数据
	 * @throws
	 */
	public String trial() {
		String jsonStr = productPriceServer.trial(id);
		if (jsonStr != null) {
			try {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(jsonStr);
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Title: getNameByNum
	 * @Description: 根据工号获取名字
	 * @return String 名字
	 * @throws
	 */
	public String getNameByNum() {
		StringBuffer str = new StringBuffer();
		if (code != null) {
			String[] allJobNum = code.split(";");
			for (String num : allJobNum) {
				Users user = userServer.findUserByCode(num);
				if (user != null)
					str.append(user.getName()).append(";");
			}
			if (str != null && str.length() > 0) {
				try {
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setCharacterEncoding("utf-8");
					response.getWriter().write(
							"{\"name\":\"" + str.substring(0, str.length() - 1)
									+ "\"}");
					response.getWriter().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public Double getOPLabourBeat() {
		return OPLabourBeat;
	}

	public void setOPLabourBeat(Double oPLabourBeat) {
		OPLabourBeat = oPLabourBeat;
	}

	public Double getOPEquipmentBeat() {
		return OPEquipmentBeat;
	}

	public void setOPEquipmentBeat(Double oPEquipmentBeat) {
		OPEquipmentBeat = oPEquipmentBeat;
	}

	public Double getPRLabourBeat() {
		return PRLabourBeat;
	}

	public void setPRLabourBeat(Double pRLabourBeat) {
		PRLabourBeat = pRLabourBeat;
	}

	public Double getPRPrepareTIme() {
		return PRPrepareTIme;
	}

	public void setPRPrepareTIme(Double pRPrepareTIme) {
		PRPrepareTIme = pRPrepareTIme;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
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

	public Integer[] getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Integer[] productPriceId) {
		this.productPriceId = productPriceId;
	}

	public String getMessagePower() {
		return messagePower;
	}

	public void setMessagePower(String messagePower) {
		this.messagePower = messagePower;
	}

	public String getGongweihao() {
		return gongweihao;
	}

	public void setGongweihao(String gongweihao) {
		this.gongweihao = gongweihao;
	}

	public String getShebeiCode() {
		return shebeiCode;
	}

	public void setShebeiCode(String shebeiCode) {
		this.shebeiCode = shebeiCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setProductPrice(ProductPrice productPrice) {
		this.productPrice = productPrice;
	}

	public ProductPrice getProductPrice() {
		return productPrice;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public ProductPriceServer getProductPriceServer() {
		return productPriceServer;
	}

	public void setProductPriceServer(ProductPriceServer productPriceServer) {
		this.productPriceServer = productPriceServer;
	}

	public ProductProcess getProductProcess() {
		return productProcess;
	}

	public void setProductProcess(ProductProcess productProcess) {
		this.productProcess = productProcess;
	}

	public SpareParts getSpareParts() {
		return spareParts;
	}

	public void setSpareParts(SpareParts spareParts) {
		this.spareParts = spareParts;
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getMkId() {
		return mkId;
	}

	public void setMkId(String mkId) {
		this.mkId = mkId;
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

}
