package com.task.action.zhaobiao;

//*
/*
 *招标订单Action
 *@ 张玉山
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.internet.NewsAddress;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.zhaobiao.DingdanServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.OrderManager;
import com.task.entity.ProductManager;
import com.task.entity.Users;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Dingdanzhuanhua;
import com.tast.entity.zhaobiao.InternalOrderDetailzhaobiao;
import com.tast.entity.zhaobiao.InternalOrderzhaobiao;
import com.tast.entity.zhaobiao.Nianlilv;
import com.tast.entity.zhaobiao.Rukudan;
import com.tast.entity.zhaobiao.Zhaobiao;
import com.tast.entity.zhaobiao.ZhaobiaoXi;
import com.tast.entity.zhaobiao.ZhaobiaoXis;
import com.tast.entity.zhaobiao.Zhtoubiao;
import com.tast.entity.zhaobiao.jihuadan;

public class DingdanAction extends ActionSupport {
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String pagename;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	//
	private List list;
	private DingdanServer dServer;
	private List listAll;

	private OrderManager oManager;

	private int[] selected;
	private Float[] selecteds;
	//
	private List<ProductManager> detailLis;
	private String message;
	private String beginTime;
	private String endTime;

	private String[] pieceNum;
	private String[] remerk;
	private String title;
	private String orderNum;

	private InternalOrderzhaobiao internalOrderzhaobiao;
	private InternalOrderDetailzhaobiao internalOrderDetailzhaobiao;
	private jihuadan jihuadan;
	private ZhaobiaoXis zhaobiaoXis;
	private Zhaobiao zhaobiao;
	private Zhtoubiao zhtoubiao;
	private ZhaobiaoXi zhaobiaoXi;
	private GoodsStore goodsStore;
	private GoodsStoreServer goodsStoreServer;
	// ------------------------------------------------
	private String[] nams;// 名称
	private Float[] zongshuliangs;// 总数量
	private String[] danweis;// 单位
	private String[] guiges;// 规格
	private Float[] caigous;// 采购数量
	private String[] shijians;// 到货期限
	private Integer[] oderids;//
	private Integer[] dateilids;
	private String[] lexings;
	private String[] dingdanIds;// 订单ID
	private String[] jihuanid;// jihua的id
	private Dingdanzhuanhua dingdanzhuanhua;
	private Rukudan rukudan;
	private Users users;
	/***
	 * 统计当前在职。试用.内退.   DingdanAction!listUserstatus.action
	 * 在职-在岗-试用-请假-内退-统筹外（退休)-离职中-离职
	 *   internal是否内部人员
	 */
	public String listUserstatus() {
	    list=dServer.listUserstatus();
		return  "listUserstatus";
	}
	//***************************************************************
	
	/*
	 * 统计现在人数
	 * 
	 */
	public String listLoginUsers() {
		 list=dServer.listLoginUsers();
		  return  "listLoginUsers";
	}
//	public String listLoginUsers() {
//			if (users != null) {
//				ActionContext.getContext().getSession().put("users", users);
//			} else {
//				users = (Users) ActionContext.getContext().getSession().get(
//						"users");
//			}
//
//			Object[] object = dServer.listLoginUsers(users, Integer.parseInt(cpage),
//					pageSize);
//
//			if (object != null && object.length > 0) {
//				list = (List<Users>) object[0];
//				if (object != null && object.length > 0) {
//					list = (List) object[0];
//					if (list != null && list.size() > 0) {
//						int count = (Integer) object[1];
//						int pageCount = (count + pageSize - 1) / pageSize;
//						this.setTotal(pageCount + "");
//						this.setUrl("DingdanAction!listLoginUsers.action");
//						errorMessage = null;
//					} else {
//						errorMessage = "没有找到你要查询的内容,请检查后重试!";
//					}
//				}
//			}
//	
//		return "listLoginUsers";
//	}
	// -----------------------------------------------
	public String listRukudancaigou() {
		if (rukudan != null) {
			ActionContext.getContext().getSession().put("rukudan", rukudan);
		} else {
			rukudan = (Rukudan) ActionContext.getContext().getSession().get(
					"rukudan");
		}

		Object[] object = dServer.listRukudan(rukudan, Integer.parseInt(cpage),
				pageSize);

		if (object != null && object.length > 0) {
			list = (List<Rukudan>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listRukudanwuliu.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listRukudanwuliu";
	}

	/*
	 * 物流确认并入库
	 */
	public String quruku() {
		rukudan.setStatus("入库完成");
		rukudan.setRukushijian(Util.getDateTime());
		dServer.updateRukudan(rukudan);
		//
		zhtoubiao = dServer.byIdzhtoubiao(rukudan.getCaigiuId());
		zhtoubiao.setRukustatus("入库完成");
		dServer.updatezhtoubiao(zhtoubiao);
		// 与入库做对接
		GoodsStore newgs = new GoodsStore();
		newgs.setGoodsStoreMarkId(rukudan.getName());// 件号
		newgs.setGoodsStoreGoodsName(rukudan.getMingcheng());// 名称
		newgs.setGoodsStoreLot(rukudan.getPicihao());// 批次
		newgs.setGoodsStoreCount(Float.parseFloat(rukudan.getRukushuliang()));// 数量
		newgs.setGoodsStoreWarehouse("原材料库");// 所属仓库
		newgs.setGoodsStoreSupplier(rukudan.getGys());// 供应商
		newgs.setGoodsStoreCharger(rukudan.getWuliuren());// 经办人
		newgs.setGoodsStorePerson(rukudan.getWuliuren());// 负责人
		newgs.setGoodsStoreDate(rukudan.getRukushijian());// 入库时间
		newgs.setOrderId(rukudan.getDindanhao());// 订单号
		newgs.setGoodsStoreSendId(rukudan.getSonghuodanhao());// 送货单号
		newgs.setGoodsStoreFormat(rukudan.getGuige());// 规格
		newgs.setGoodsStoreUnit(rukudan.getDanwei());// 单位
		newgs.setStyle("正常（原材料）");// 入库类型
		newgs.setGoodsStoreNumber(rukudan.getRukubianhao());// 申请单号
		newgs.setGoodsStoreLuId(rukudan.getLupihao());
		// addSdrk(newgs);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		newgs.setGoodsStoreTime(sdf.format(new Date()));

		Map session = ActionContext.getContext().getSession();
		Users user = (Users) session.get(TotalDao.users);

		newgs.setGoodsStorePlanner(user.getName());
		newgs.setStatus("入库");
		newgs.setGoodsStoreMarkId(newgs.getGoodsStoreMarkId().replaceAll("\\s",
				""));
		if (newgs.getGoodsStoreFormat() == null) {
			newgs.setGoodsStoreFormat("");
		}
		dServer.saveSgrk(newgs);
		// ---------------------------------------
		errorMessage = "入库完成";
		return "quruku";
	}

	/*
	 * 物流
	 */
	public String toruku() {
		rukudan = dServer.ByIdrukudan(rukudan.getId());
		return "toruku";
	}

	/*
	 * 入库分页物流
	 */
	public String listRukudanwuliu() {
		rukudan.setStatus("物流入库中");
		rukudan.setYanshou("同意");
		if (rukudan != null) {
			ActionContext.getContext().getSession().put("rukudan", rukudan);
		} else {
			rukudan = (Rukudan) ActionContext.getContext().getSession().get(
					"rukudan");
		}

		Object[] object = dServer.listRukudan(rukudan, Integer.parseInt(cpage),
				pageSize);

		if (object != null && object.length > 0) {
			list = (List<Rukudan>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listRukudanwuliu.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listRukudan";
	}

	/*
	 * 品质部门补充入库申请单
	 */
	public String updateRukudanpinzhi() {
		rukudan.setStatus("物流入库中");
		dServer.updateRukudan(rukudan);
		// ------------------
		zhtoubiao = dServer.byIdzhtoubiao(rukudan.getCaigiuId());
		zhtoubiao.setRukustatus("物流入库中");
		dServer.updatezhtoubiao(zhtoubiao);
		// --------------------
		errorMessage = "入库申请单补充完毕！已提交至物流进行入库";
		return "updateRukudanpinzhi";

	}

	/*
	 * 质检
	 */
	public String zhijian() {
		rukudan = dServer.ByIdrukudan(rukudan.getId());
		return "zhijian";
	}

	/*
	 * 入库单分页 质检
	 */
	public String listRukudan() {
		rukudan.setStatus("质检中");
		if (rukudan != null) {
			ActionContext.getContext().getSession().put("rukudan", rukudan);
		} else {
			rukudan = (Rukudan) ActionContext.getContext().getSession().get(
					"rukudan");
		}

		Object[] object = dServer.listRukudan(rukudan, Integer.parseInt(cpage),
				pageSize);

		if (object != null && object.length > 0) {
			list = (List<Rukudan>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listRukudan.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listRukudan";
	}

	/*
	 * 入库前查询投标信息
	 */
	public String torukudanchaxun() {
		zhtoubiao = dServer.byIdzhtoubiao(zhtoubiao.getTid());
		return "torukudanchaxun";
	}

	/*
	 * 从招标 添加入库申请单 采购
	 */
	public String addRukudanzhaobiao() {
		dServer.addRukudan(rukudan);
		// 修改投标状态
		zhtoubiao = dServer.byIdzhtoubiao(rukudan.getCaigiuId());
		zhtoubiao.setRukustatus("质检中");
		dServer.updatezhtoubiao(zhtoubiao);
		errorMessage = "添加入库申请单成功    已提交至品质部门质检！！！";
		return "addRukudancaigou";
	}

	/*
	 * 差选招标项目下面的所有中标信息
	 */
	public String rukuchaxun() {
		if (zhtoubiao != null) {
			ActionContext.getContext().getSession().put("zhtoubiao", zhtoubiao);
		} else {
			zhtoubiao = (Zhtoubiao) ActionContext.getContext().getSession()
					.get("zhtoubiao");
		}
		Object[] object = dServer.rukuchaxun(zhaobiao.getId(), zhtoubiao,
				Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<Zhtoubiao>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!rukuchaxun.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "rukuchaxun";
	}

	// 添加入库申请单 采购
	public String addRukudancaigou() {
		dServer.addRukudan(rukudan);
		// --修改采购计划详细的状态
		dingdanzhuanhua = dServer.byIDdingdan(rukudan.getCaigiuId());
		dingdanzhuanhua.setStatus("入库中");
		dServer.updateDingdanzhuanhua(dingdanzhuanhua);
		errorMessage = "添加成功";
		return "addRukudancaigou";
	}

	/*
	 * 申请入库单前去查询信息
	 */
	public String torukudan() {
		dingdanzhuanhua = dServer.byIDdingdan(dingdanzhuanhua.getId());
		internalOrderzhaobiao = dServer.byIDOrder(dingdanzhuanhua
				.getInternalOrderzhaobiaoid());
		return "torukudan";
	}

	/*
	 * 展开采购的材料。入库
	 */
	public String ruku() {
		if (dingdanzhuanhua != null) {
			ActionContext.getContext().getSession().put("dingdanzhuanhua",
					dingdanzhuanhua);
		} else {
			dingdanzhuanhua = (Dingdanzhuanhua) ActionContext.getContext()
					.getSession().get("dingdanzhuanhua");
		}
		Object[] object = dServer.listxiangxi(internalOrderzhaobiao.getId(),
				dingdanzhuanhua, Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<Dingdanzhuanhua>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listxiangxi.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "ruku";
	}

	/*
	 * 展示采购计划下的采购明细
	 */
	public String showcaigou() {
		list = dServer.showcaigou(internalOrderzhaobiao.getId());
		return "showcaigou";
	}

	/*
	 * 生成采购详细
	 */
	public String addcaigouxiangxiangxin() {
		dServer.addDingdanzhuanhua(nams, zongshuliangs, danweis, guiges,
				caigous, shijians, oderids, dateilids, lexings, dingdanIds,
				jihuanid);
		// ----------------------审批-
		// 触发审批 12
		internalOrderzhaobiao = dServer.byIdinternalOrderzhaobiao(oderids[0]);
		Integer epId;
		try {
			epId = CircuitRunServerImpl.createProcess(194,
					InternalOrderzhaobiao.class, internalOrderzhaobiao.getId(),
					"status", "id",
					"DingdanAction!showcaigou.action?internalOrderzhaobiao.id="
							+ internalOrderzhaobiao.getId(), "采购计划审核", false,
					null);
			if (epId != null && epId > 0) {
				internalOrderzhaobiao.setStatus("审核中");
				internalOrderzhaobiao.setEpId(epId);
				dServer.updatetoubiao(internalOrderzhaobiao);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ------------------------------
		return "addcaigouxiangxiangxin";
	}

	/*
	 * 选择计划单下的需要采购的原材料
	 */
	public String xuanzhejihua() {
		if (selected != null && selected.length > 0) {
			list = dServer.listxuanzhejihua(selected);
		}
		return "xuanzhejihua";
	}

	/*
	 * 添加采购项目
	 */
	public String addzhaobiao() {
		dServer.addzhaobiaoAddZhabiaoXi(zhaobiao, zhaobiaoXis);
		errorMessage = "已经成功生成招标项目！！！";
		url = "DingdanAction!listInternalOrderzhaobiao_InternalOrderDetail.action";
		return ERROR;
	}

	/*
	 * 一次性生成原材料採購
	 */
	public String addJihuadan() {
		if (selected != null && selected.length > 0) {
			Object[] object = dServer.integrationOrderDetail1(selected);
			if (object != null && object.length > 0) {
				detailLis = (List<ProductManager>) object[0];
				message = (String) object[1];
				for (int i = 0; i < selected.length; i++) {
					if (i == 0) {
						beginTime = selected[i] + ",";
					} else
						beginTime += selected[i] + ",";
				}
				beginTime = beginTime.substring(0, beginTime.lastIndexOf(","));
			}
			// return "addJihuadan";
			// 加添采购数量
			list = dServer.listAlljihuaByinternalOrderID();
			return "listjihua";
		}
		return null;
	}

	/*
	 * 前去采购前查询
	 */
	public String caigou() {
		internalOrderzhaobiao = dServer
				.byIDOrder(internalOrderzhaobiao.getId());
		list = dServer.ByInternalOrderzhaobiaoId(internalOrderzhaobiao.getId());
		return "caigou";
	}

	/*
	 * 输入采购数量
	 */

	/***
	 * jihua列表
	 */
	// public String listjihuadan() {
	// if (jihuadan != null) {
	// ActionContext.getContext().getSession().put("jihuadan", jihuadan);
	// } else {
	// jihuadan = (jihuadan) ActionContext.getContext().getSession().get(
	// "jihuadan");
	// }
	// Object[] object =
	// dServer.listjihuadan(internalOrderDetailzhaobiao.getId(),jihuadan,
	// Integer
	// .parseInt(cpage), pageSize);
	//
	// if (object != null && object.length > 0) {
	// list = (List<jihuadan>) object[0];
	// if (object != null && object.length > 0) {
	// list = (List) object[0];
	// if (list != null && list.size() > 0) {
	// int count = (Integer) object[1];
	// int pageCount = (count + pageSize - 1) / pageSize;
	// this.setTotal(pageCount + "");
	// this.setUrl("DingdanAction!listjihuadan.action");
	// errorMessage = null;
	// } else {
	// errorMessage = "没有找到你要查询的内容,请检查后重试!";
	// }
	// }
	// }
	// return "listjihuadan";
	// }
	public String listjihuadan() {
		if (dingdanzhuanhua != null) {
			ActionContext.getContext().getSession().put("dingdanzhuanhua",
					dingdanzhuanhua);
		} else {
			dingdanzhuanhua = (Dingdanzhuanhua) ActionContext.getContext()
					.getSession().get("dingdanzhuanhua");
		}
		Object[] object = dServer.listjihuadan(internalOrderDetailzhaobiao
				.getId(), dingdanzhuanhua, Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<Dingdanzhuanhua>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listjihuadan.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "xuanzhejihua";
	}

	/*
	 * 订单分页
	 */
	public String listOrderManager() {
		if (oManager != null) {
			ActionContext.getContext().getSession().put("oManager", oManager);
		} else {
			oManager = (OrderManager) ActionContext.getContext().getSession()
					.get("oManager");
		}
		Object[] object = dServer.listOrderManager(oManager, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<OrderManager>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listOrderManager.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listOrderManager";
	}

	/*
	 * 订单转换
	 */
	/*
	 * public String printStorage() { if (selected != null && selected.length >
	 * 0) { Object[] object = dServer.integrationOrderDetail(selected); if
	 * (object != null && object.length > 0) { detailLis =
	 * (List<ProductManager>) object[0]; message = (String) object[1]; for (int
	 * i = 0; i < selected.length; i++) { if (i == 0) { beginTime = selected[i]
	 * + ","; } else beginTime += selected[i] + ","; } beginTime =
	 * beginTime.substring(0, beginTime.lastIndexOf(",")); } //return
	 * "printStorage"; } return null; }
	 */
	public String printStorage() {
		if (selected != null && selected.length > 0) {
			Object[] object = dServer.integrationOrderDetail(selected);
			if (object != null && object.length > 0) {
				detailLis = (List<ProductManager>) object[0];
				message = (String) object[1];
				for (int i = 0; i < selected.length; i++) {
					if (i == 0) {
						beginTime = selected[i] + ",";
					} else
						beginTime += selected[i] + ",";
				}
				beginTime = beginTime.substring(0, beginTime.lastIndexOf(","));
			}
			return "addJihuadan";

		}
		return null;
	}

	/*
	 * 保存订单
	 */
	public String batchConversion() {
		if (selected != null) {
			dServer.batchConversionOrder(pieceNum, selecteds, remerk, orderNum,
					title);
		}
		return "batchConversion";
	}

	public String listInternalOrderzhaobiao() {
		if (internalOrderzhaobiao != null) {
			ActionContext.getContext().getSession().put(
					"internalOrderzhaobiao", internalOrderzhaobiao);
		} else {
			internalOrderzhaobiao = (InternalOrderzhaobiao) ActionContext
					.getContext().getSession().get("internalOrderzhaobiao");
		}
		Object[] object = dServer.listInternalOrderzhaobiao1(
				internalOrderzhaobiao, Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<InternalOrderzhaobiao>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("DingdanAction!listInternalOrderzhaobiao1.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listInternalOrderzhaobiao";
	}

	public String listInternalOrderDetail() {
		if (internalOrderDetailzhaobiao != null) {
			ActionContext.getContext().getSession().put(
					"internalOrderDetailzhaobiao", internalOrderDetailzhaobiao);
		} else {
			internalOrderDetailzhaobiao = (InternalOrderDetailzhaobiao) ActionContext
					.getContext().getSession().get(
							"internalOrderDetailzhaobiao");
		}
		Object[] object = dServer.listInternalOrderDetail(
				internalOrderDetailzhaobiao, Integer.parseInt(cpage), pageSize,
				internalOrderzhaobiao.getId());

		if (object != null && object.length > 0) {
			list = (List<InternalOrderDetailzhaobiao>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("DingdanAction!listInternalOrderDetail.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listInternalOrderDetail";
	}

	public String listInternalOrderzhaobiao_InternalOrderDetail() {
		if (internalOrderzhaobiao != null) {
			ActionContext.getContext().getSession().put(
					"internalOrderzhaobiao", internalOrderzhaobiao);
		} else {
			internalOrderzhaobiao = (InternalOrderzhaobiao) ActionContext
					.getContext().getSession().get("internalOrderzhaobiao");
		}
		Object[] object = dServer.listInternalOrderzhaobiao(
				internalOrderzhaobiao, Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<InternalOrderzhaobiao>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("DingdanAction!listInternalOrderzhaobiao_InternalOrderDetail.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listInternalOrderzhaobiao_InternalOrderDetail";
	}

	public String listInternalOrderDetail_hedui() {
		if (internalOrderDetailzhaobiao != null) {
			ActionContext.getContext().getSession().put(
					"internalOrderDetailzhaobiao", internalOrderDetailzhaobiao);
		} else {
			internalOrderDetailzhaobiao = (InternalOrderDetailzhaobiao) ActionContext
					.getContext().getSession().get(
							"internalOrderDetailzhaobiao");
		}
		Object[] object = null;
		try {
			object = dServer.listInternalOrderDetail(
					internalOrderDetailzhaobiao, Integer.parseInt(cpage),
					pageSize, internalOrderzhaobiao.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (object != null && object.length > 0) {
			list = (List<InternalOrderDetailzhaobiao>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("DingdanAction!listInternalOrderDetail_hedui.action?internalOrderzhaobiao.id="
									+ internalOrderzhaobiao.getId());
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listInternalOrderDetail_hedui";
	}

	// ajax下拉模版
	public void listmoban() {
		listAll = dServer.findAllDept();
		MKUtil.writeJSON(listAll);
	}

	public DingdanServer getdServer() {
		return dServer;
	}

	public OrderManager getoManager() {
		return oManager;
	}

	public void setoManager(OrderManager oManager) {
		this.oManager = oManager;
	}

	public void setdServer(DingdanServer dServer) {
		this.dServer = dServer;
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

	public InternalOrderDetailzhaobiao getInternalOrderDetailzhaobiao() {
		return internalOrderDetailzhaobiao;
	}

	public void setInternalOrderDetailzhaobiao(
			InternalOrderDetailzhaobiao internalOrderDetailzhaobiao) {
		this.internalOrderDetailzhaobiao = internalOrderDetailzhaobiao;
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

	public String getPagename() {
		return pagename;
	}

	public InternalOrderzhaobiao getInternalOrderzhaobiao() {
		return internalOrderzhaobiao;
	}

	public void setInternalOrderzhaobiao(
			InternalOrderzhaobiao internalOrderzhaobiao) {
		this.internalOrderzhaobiao = internalOrderzhaobiao;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<ProductManager> getDetailLis() {
		return detailLis;
	}

	public void setDetailLis(List<ProductManager> detailLis) {
		this.detailLis = detailLis;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String[] getPieceNum() {
		return pieceNum;
	}

	public void setPieceNum(String[] pieceNum) {
		this.pieceNum = pieceNum;
	}

	public String[] getRemerk() {
		return remerk;
	}

	public void setRemerk(String[] remerk) {
		this.remerk = remerk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public ZhaobiaoXis getZhaobiaoXis() {
		return zhaobiaoXis;
	}

	public void setZhaobiaoXis(ZhaobiaoXis zhaobiaoXis) {
		this.zhaobiaoXis = zhaobiaoXis;
	}

	public Zhaobiao getZhaobiao() {
		return zhaobiao;
	}

	public void setZhaobiao(Zhaobiao zhaobiao) {
		this.zhaobiao = zhaobiao;
	}

	public String[] getNams() {
		return nams;
	}

	public void setNams(String[] nams) {
		this.nams = nams;
	}

	public Float[] getZongshuliangs() {
		return zongshuliangs;
	}

	public void setZongshuliangs(Float[] zongshuliangs) {
		this.zongshuliangs = zongshuliangs;
	}

	public String[] getDanweis() {
		return danweis;
	}

	public void setDanweis(String[] danweis) {
		this.danweis = danweis;
	}

	public String[] getGuiges() {
		return guiges;
	}

	public void setGuiges(String[] guiges) {
		this.guiges = guiges;
	}

	public Float[] getCaigous() {
		return caigous;
	}

	public void setCaigous(Float[] caigous) {
		this.caigous = caigous;
	}

	public String[] getShijians() {
		return shijians;
	}

	public void setShijians(String[] shijians) {
		this.shijians = shijians;
	}

	public Integer[] getOderids() {
		return oderids;
	}

	public void setOderids(Integer[] oderids) {
		this.oderids = oderids;
	}

	public Integer[] getDateilids() {
		return dateilids;
	}

	public void setDateilids(Integer[] dateilids) {
		this.dateilids = dateilids;
	}

	public String[] getLexings() {
		return lexings;
	}

	public void setLexings(String[] lexings) {
		this.lexings = lexings;
	}

	public String[] getDingdanIds() {
		return dingdanIds;
	}

	public void setDingdanIds(String[] dingdanIds) {
		this.dingdanIds = dingdanIds;
	}

	public String[] getJihuanid() {
		return jihuanid;
	}

	public void setJihuanid(String[] jihuanid) {
		this.jihuanid = jihuanid;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

	public jihuadan getJihuadan() {
		return jihuadan;
	}

	public void setJihuadan(jihuadan jihuadan) {
		this.jihuadan = jihuadan;
	}

	public Dingdanzhuanhua getDingdanzhuanhua() {
		return dingdanzhuanhua;
	}

	public void setDingdanzhuanhua(Dingdanzhuanhua dingdanzhuanhua) {
		this.dingdanzhuanhua = dingdanzhuanhua;
	}

	public Rukudan getRukudan() {
		return rukudan;
	}

	public void setRukudan(Rukudan rukudan) {
		this.rukudan = rukudan;
	}

	public Zhtoubiao getZhtoubiao() {
		return zhtoubiao;
	}

	public void setZhtoubiao(Zhtoubiao zhtoubiao) {
		this.zhtoubiao = zhtoubiao;
	}

	public ZhaobiaoXi getZhaobiaoXi() {
		return zhaobiaoXi;
	}

	public void setZhaobiaoXi(ZhaobiaoXi zhaobiaoXi) {
		this.zhaobiaoXi = zhaobiaoXi;
	}

	public GoodsStore getGoodsStore() {
		return goodsStore;
	}

	public void setGoodsStore(GoodsStore goodsStore) {
		this.goodsStore = goodsStore;
	}

	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}

	/**
	 * @return the selecteds
	 */
	public Float[] getSelecteds() {
		return selecteds;
	}

	/**
	 * @param selecteds the selecteds to set
	 */
	public void setSelecteds(Float[] selecteds) {
		this.selecteds = selecteds;
	}
}
