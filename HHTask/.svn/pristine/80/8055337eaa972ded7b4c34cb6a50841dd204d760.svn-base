package com.task.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.IOrderManagerService;
import com.task.Server.IPieceNumService;
import com.task.Server.IProductManagerService;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.ServerImpl.yw.ResponseUtil;
import com.task.entity.ClientManagement;
import com.task.entity.OrderManager;
import com.task.entity.PieceNum;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;

public class PieceNumAction extends ActionSupport implements
		ServletResponseAware {

	private IPieceNumService ips;
	private IProductManagerService ipm;
	private IOrderManagerService ios;
	private PieceNum pn;
	private HttpServletResponse response;
	private Double[] prices;
	private List list;
	private int id;
	private String numId;
	private String name;
	private String carType;
	private String type;
	private int[] selected;
	private Float[] num;
	private ClientManagement cm;//客户表
	private String ddType;
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private Integer[]goodsIds;
	private String pagestatus; //页面状态;
	private String tag;
	private Price price;
	/**
	 * @Title: queryAll
	 * @Description: 查询所有产品件号
	 * @return String
	 * @throws
	 */
	public String queryAll() {
		Object[] object = ips.queryAllPieceNum(Integer.parseInt(cpage),
				pageSize,pagestatus);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("pieceNum_queryAll.action?id="+id);
				errorMessage = null;
			} else
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
		} else
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		return "pieceNum_index";
	}

	/**
	 * @Title: queryPieceNumByCondition
	 * @Description: 根据条件查询产品件号
	 * @return String
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public String queryPieceNumByCondition() {
		Map map = new HashMap();
		if("KH".equals(tag)){
			map.put("kehuId", id);
			cm = ips.getClientManagementbyid(id);
		}
		if (name != null && !name.equals("")) {
			map.put("name", name);
		}
		if (numId != null && !numId.equals("")) {
			map.put("numId", numId);
		}
		if (carType != null && !carType.equals("")) {
			map.put("carType", carType);
		}
		if (type != null && !type.equals("")) {
			map.put("type", type);
		}
		if(pagestatus!=null && pagestatus.length()>0){
			map.put("pagestatus", pagestatus);
		}
		if
		 (map.size() > 0) {
			ActionContext.getContext().getSession().put("condition", map);
		} else {
			if (errorMessage == null || !errorMessage.equals("all")) {
				map = (Map) ActionContext.getContext().getSession().get(
						"condition");
				if(!"KH".equals(tag)){
					if(map!=null){
						map.remove("kehuId");
					}
					
				}
			} else
				ActionContext.getContext().getSession().remove("condition");
		}
		Object[] object = ips.queryPieceNumCondition(map, Integer
				.parseInt(cpage), pageSize,ddType);
		if (object != null && object.length > 0) {
			list = (List) object[0];
			if (list != null && list.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("pieceNum_queryPieceNumByCondition.action?tag="+tag+"&pagestatus="+pagestatus+"&id="+id);
				errorMessage = null;
			} else{
				errorMessage = "该客户没有绑定相关产品，请绑定后重试!";
			}
		} else{
			errorMessage = "该客户没有绑定相关产品，请绑定后重试!";
		}
		return "pieceNum_index";
	}

	/**
	 * @Title: initSelectedNum
	 * @Description: 初始化选择产品数量
	 * @return String
	 * @throws
	 */
	public String initSelectedNum() {
		if (selected != null && selected.length > 0) {
			Object[] object = ips.querySelectedProduct(selected);
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (object[1] != null) {
					errorMessage = (String) object[1];
				}
				return "pieceNum_selected";
			}
		}
		return null;
	}

	/**
	 * @Title: productDetail
	 * @Description: 产品明细
	 * @return String
	 * @throws
	 */
	public String productDetail() {
		
		if (selected != null && num != null && id != 0) {
			ipm.addProduct(selected,num,id,goodsIds);
		    	Map map=new HashMap();
		    	map.put("id", id);
		    	ResponseUtil.write(response, "添加产品成功！", "pieceNum_queryAll.action",
		    			map);
		    	return null;
		}
		ResponseUtil.write(response, "添加产品失败 ！",
				"orderManager_initOrder.action", null);
		return null;
	}

	public void getPriceByMarkId(){
//		Map map = new HashMap();
//		if (numId != null && !numId.equals("")) {
//			map.put("numId", numId);
//		}
		Price pp = ips.getPriceByMarkId(numId, null);
		MKUtil.writeJSON(pp);
	}
	/**
	 * @Title: add
	 * @Description: 添加产品件号
	 * @return String
	 * @throws
	 */
	/**
	 * public String add() { if (pn != null) { ips.add(pn);
	 * ResponseUtil.write(response, "添加成功", "pieceNum_queryAll.action", null); }
	 * else { ResponseUtil.write(response, "添加失败", "pieceNum_queryAll.action",
	 * null); } return null; }
	 */

	/**
	 * @Title: initUpdatePieceNum
	 * @Description: 初始化修改件号页面数据
	 * @return String
	 * @throws
	 */
	/**
	 * public String initUpdatePieceNum() { if (id != 0) { pn =
	 * ips.getPieceNumById(id); } return "pieceNum_update"; }
	 */
	/**
	 * @Title: update
	 * @Description: 修改件号
	 * @return String
	 * @throws
	 */
	/**
	 * public String update() { if (pn != null) { ips.update(pn); return
	 * "pieceNum_redirect"; } return null; }
	 */
	/**
	 * @Title: del
	 * @Description: 删除件号
	 * @return String
	 * @throws
	 */
	/**
	 * public String del() { if (id != 0) { ips.delPieceNumById(id);
	 * ResponseUtil.write(response, "删除成功!", "pieceNum_queryAll.action", null);
	 * } ResponseUtil.write(response, "删除失败!", "pieceNum_queryAll", null);
	 * return null; }
	 */
	public IPieceNumService getIps() {
		return ips;
	}

	public void setIps(IPieceNumService ips) {
		this.ips = ips;
	}

	public PieceNum getPn() {
		return pn;
	}

	public void setPn(PieceNum pn) {
		this.pn = pn;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
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

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float[] getNum() {
		return num;
	}

	public void setNum(Float[] num) {
		this.num = num;
	}

	public IProductManagerService getIpm() {
		return ipm;
	}

	public void setIpm(IProductManagerService ipm) {
		this.ipm = ipm;
	}

	public IOrderManagerService getIos() {
		return ios;
	}

	public void setIos(IOrderManagerService ios) {
		this.ios = ios;
	}

	public String getNumId() {
		return numId;
	}

	public void setNumId(String numId) {
		this.numId = numId;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setListPrice(Double[] listPrice) {
		this.prices = listPrice;
	}

	public Double[] getListPrice() {
		return prices;
	}

	public Double[] getPrices() {
		return prices;
	}

	public void setPrices(Double[] prices) {
		this.prices = prices;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Integer[] getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Integer[] goodsIds) {
		this.goodsIds = goodsIds;
	}

	public String getPagestatus() {
		return pagestatus;
	}

	public void setPagestatus(String pagestatus) {
		this.pagestatus = pagestatus;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public ClientManagement getCm() {
		return cm;
	}

	public void setCm(ClientManagement cm) {
		this.cm = cm;
	}

	public String getDdType() {
		return ddType;
	}

	public void setDdType(String ddType) {
		this.ddType = ddType;
	}
	

}
