package com.task.action.zhaobiao;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.zhaobiao.MarkIdServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.ProcessMarkIdZijian;
import com.tast.entity.zhaobiao.Waigoujianpinci;
import com.tast.entity.zhaobiao.WaigoujianpinciZi;
import com.tast.entity.zhaobiao.ZhUser;

/*
 * 用于供应商填写件号模版
 * 张玉山
 */
public class GysMarkIdjiepaiAction extends ActionSupport {
	private MarkIdServer markIdServer;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private List<ZhUser> zhuserList;
	private List list;
	private List listAll;
	private int id;// id
	private Float cgbl;
	private GysMarkIdjiepai gysMarkIdjiepai;
	private ProcessMarkIdZijian pIdZijian;
	private List<GysMarkIdjiepai> procardTemplateList;// 集合
	private ZhUser zhUser;
	private int[] selected;
	private Float num;
	private String markId;
	private String kgliao;
	private String status;
	private String tag;
	// Bom模板
	private ProcardTemplate procardTemplate;
	private ProcessTemplate processTemplate;

	private Waigoujianpinci waigoujianpinci;
	private WaigoujianpinciZi waigoujianpinciZi;
	private String gys;
	private String banBenNumber;

	/**
	 * 导入文件
	 */
	private File addfile;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	/***
	 * 查看所有供应商填写 的外购件
	 */
	public String listGysMarkIdjiepaichakan() {
		if (gysMarkIdjiepai != null) {
			ActionContext.getContext().getSession().put("gysMarkIdjiepai",
					gysMarkIdjiepai);
		} else {
			gysMarkIdjiepai = (GysMarkIdjiepai) ActionContext.getContext()
					.getSession().get("gysMarkIdjiepai");
		}
		Object[] object = markIdServer.listGysMarkIdjiepaichakan(
				gysMarkIdjiepai, Integer.parseInt(cpage), pageSize);
		zhuserList = markIdServer.findAllZhUser();
		if (object != null && object.length > 0) {
			list = (List<GysMarkIdjiepai>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("markIdAction!listGysMarkIdjiepaichakan.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listGysMarkIdjiepaichakan";//gysMarkIdjiepai_list_chakan.jsp
	}

	/***
	 * 绑定BOM外购件与外购件验收模板
	 */
	public String bandingWaigouyanshou() {
		markIdServer.bandingWaigouyanshou(selected, procardTemplate.getId(),
				procardTemplate.getJianyanjiepai());
		errorMessage = "绑定成功！！！";
		url = "markIdAction!listwaigoujianyan.action?procardTemplate.id="
				+ procardTemplate.getId();
		return ERROR;
	}

	/***
	 * BOm件号下面查看购件验收模板
	 * 
	 * @return
	 */
	public String listwaigoujianyan() {
		procardTemplate = markIdServer.ByIdprocardTemplate(procardTemplate
				.getId());

		listAll = markIdServer.bylistAllyiban(procardTemplate.getId());

		if (waigoujianpinci != null) {
			ActionContext.getContext().getSession().put("waigoujianpinci",
					waigoujianpinci);
		} else {
			waigoujianpinci = (Waigoujianpinci) ActionContext.getContext()
					.getSession().get("waigoujianpinci");
		}
		Object[] object = markIdServer.listwaigoujianyan(waigoujianpinci,
				procardTemplate.getId(), Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<Waigoujianpinci>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listwaigoujianyan.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listwaigoujianyan";
	}

	// -------------------------------------------------------------------------
	public String listBOMwaigou() {
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		Object[] object = markIdServer.listBOMwaigou(procardTemplate, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<ProcardTemplate>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listBOMwaigou.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listBOMwaigou";
	}

	// ---------------------------------------------------------------------------
	public String updatewaigoujianpinciZi() {
		markIdServer.updatewaigoujianpinciZi(waigoujianpinciZi);
		errorMessage = "修改成功！！！";
		return "updatewaigoujianpinciZi";
	}

	public String toUpdatewaigoujianpinciZi() {
		waigoujianpinciZi = markIdServer
				.byIdWaigoujianpinciZi(waigoujianpinciZi);
		if ("xj".equals(status)) {
			return "xunjianpiliang_update";
		}
		return "toUpdatewaigoujianpinciZi";
	}

	public String deletewaigoujianpinciZi() {
//		Waigoujianpinci waigoujianpinci = new Waigoujianpinci();
//		waigoujianpinci.setId(waigoujianpinciZi.getWaigoujianpinciId());
		markIdServer.deletewaigoujianpinciZi(waigoujianpinciZi);
		errorMessage = "删除成功！！！";
		url = "markIdAction!listyanshoupincizi.action?waigoujianpinci.id="
				+ waigoujianpinciZi.getId();
		return ERROR;
	}

	public String addwaigoujianpinciZi() {
		markIdServer.addwaigoujianpinciZi(waigoujianpinciZi);
		errorMessage = "添加成功！！！";
		// url="markIdAction!listWaigoujianpinci.action";
		return "addwaigoujianpinciZi";
	}

	public String toaddwaigoujianpinci() {
		num = markIdServer.byIdWaigoujianpinciZiZUida(waigoujianpinci.getId());
		waigoujianpinci = markIdServer.ByIdWaigoujianpinci(waigoujianpinci
				.getId());
		if ("xj".equals(status)) {
			return "xunjianpiliang_add";
		}
		return "toaddwaigoujianpinci";
	}

	public String listyanshoupincizi() {
		if (waigoujianpinciZi != null) {
			ActionContext.getContext().getSession().put("waigoujianpinciZi",
					waigoujianpinciZi);
		} else {
			waigoujianpinciZi = (WaigoujianpinciZi) ActionContext.getContext()
					.getSession().get("waigoujianpinciZi");
		}
		Object[] object = markIdServer.listyanshoupincizi(waigoujianpinci
				.getId(), waigoujianpinciZi, Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<WaigoujianpinciZi>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					url = "markIdAction!listyanshoupincizi.action?status="
							+ status;
					if (waigoujianpinci.getId() != null) {
						url += "&waigoujianpinci.id=" + waigoujianpinci.getId();
					}
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		if ("xj".equals(status)) {
			return "xunijanpiliang_list";
		}
		return "listyanshoupincizi";
	}

	public String deletewaigoujianpinci() {
		markIdServer.deletewaigoujianpinci(waigoujianpinci);
		errorMessage = "删除成功！！！";
		url = "markIdAction!listWaigoujianpinci.action?status=" + status;
		return ERROR;
	}

	public String updatewaigoujianpinci() {
		markIdServer.updatewaigoujianpinci(waigoujianpinci);
		errorMessage = "修改成功！！！";
		return "updatewaigoujianpinci";
	}

	public String toUpdatewaigoujianpinci() {
		waigoujianpinci = markIdServer.ByIdWaigoujianpinci(waigoujianpinci
				.getId());
		return "toUpdatewaigoujianpinci";
	}

	public String addwaigoujianpinci() {
		markIdServer.addwaigoujianpinci(waigoujianpinci);
		errorMessage = "添加成功！！！";
		// url="markIdAction!listWaigoujianpinci.action";
		return "addwaigoujianpinci";
	}

	public String listWaigoujianpinci() {
		if (waigoujianpinci != null) {
			ActionContext.getContext().getSession().put("waigoujianpinci",
					waigoujianpinci);
		} else {
			waigoujianpinci = (Waigoujianpinci) ActionContext.getContext()
					.getSession().get("waigoujianpinci");
		}
		Object[] object = markIdServer.listWaigoujianpinci(waigoujianpinci,
				Integer.parseInt(cpage), pageSize, status);

		if (object != null && object.length > 0) {
			list = (List<Waigoujianpinci>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listWaigoujianpinci.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listWaigoujianpinci";
	}

	// -------------------------------------- 以上 外购外协件验收频次规定
	// ---------------------------------------------------
	/***
	 * 补充节拍
	 */
	public String waiweijiepai() {
		ProcessMarkIdZijian newp = markIdServer.findProcessT(pIdZijian.getId());
		/*
		 * private Float opcaozuojiepai;// 操作人工节拍 private Float
		 * opshebeijiepai;// 操作设备节拍 private Float gzzhunbeijiepai;// 准备过程人工节拍
		 * private Float gzzhunbeicishu;// 准备次数 singleDuration deliveryDuration
		 */
		newp.setOpcaozuojiepai(pIdZijian.getOpcaozuojiepai());
		newp.setOpshebeijiepai(pIdZijian.getOpshebeijiepai());
		newp.setGzzhunbeijiepai(pIdZijian.getGzzhunbeijiepai());
		newp.setGzzhunbeicishu(pIdZijian.getGzzhunbeicishu());

		newp.setSingleDuration(pIdZijian.getSingleDuration());
		newp.setDeliveryDuration(pIdZijian.getDeliveryDuration());
		if (pIdZijian.getSingleDuration() != null) {
			newp
					.setCapacity((newp.getGzzhunbeijiepai()
							* newp.getGzzhunbeicishu()
							+ newp.getOpcaozuojiepai() + newp
							.getOpshebeijiepai())
							* 3600 / pIdZijian.getSingleDuration());
		}
		markIdServer.updateProcessMarkIdZijian(newp);
		// ----------------------------------------
		// GysMarkIdjiepai
		// newmarkID=markIdServer.findProcardTemById(gysMarkIdjiepai.getId());
		// newmarkID.setSingleDuration(gysMarkIdjiepai.getSingleDuration());//singleDuration;//
		// 单班时长
		// newmarkID.setDeliveryDuration(gysMarkIdjiepai.getDeliveryDuration());//
		// deliveryDuration;// 配送时长
		// markIdServer.updateProcardTemplate(newmarkID);
		// ---------------------------------------------------
		errorMessage = "操作成功！！！";
		return "waiweijiepai";
	}

	/***
	 * 外委填写节拍钱查询
	 */
	public String tojiepai() {
		pIdZijian = markIdServer.findProcessT(pIdZijian.getId());
		return "tojiepai";
	}

	/***
	 * 查选外委BOm下面的所有绑定的工序 listWaiweiGongxu
	 */
	public String listWaiweiGongxu() {
		if (pIdZijian != null) {
			ActionContext.getContext().getSession().put("pIdZijian", pIdZijian);
		} else {
			pIdZijian = (ProcessMarkIdZijian) ActionContext.getContext()
					.getSession().get("pIdZijian");
		}
		Object[] object = markIdServer.listWaiweiGongxu(pIdZijian,
				gysMarkIdjiepai.getId(), Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<ProcessMarkIdZijian>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listWaiweiGongxu.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listWaiweiGongxu";
	}

	/***
	 * 绑定工序
	 * 
	 */
	public String bandinggongxu() {
		markIdServer.bandinggongxu(selected, zhUser.getId(), procardTemplate
				.getId());
		errorMessage = "绑定成功!!!";
		url = "markIdAction!listgongxu.action?zhUser.id=" + zhUser.getId()
				+ "&procardTemplate.id=" + procardTemplate.getId();
		return ERROR;

	}

	/***
	 * 列表显示BOM下面的工序 已绑定工序和为绑定工序 listgongxu
	 */
	public String listgongxu() {
		zhUser = markIdServer.listByIdZhUserId(zhUser.getId());
		listAll = markIdServer.listYigongxu(zhUser.getId(), procardTemplate
				.getId());

		if (processTemplate != null) {
			ActionContext.getContext().getSession().put("processTemplate",
					processTemplate);
		} else {
			processTemplate = (ProcessTemplate) ActionContext.getContext()
					.getSession().get("processTemplate");
		}
		Object[] object = markIdServer.listgongxu(processTemplate, zhUser
				.getId(), procardTemplate.getId(), Integer.parseInt(cpage),
				pageSize);

		if (object != null && object.length > 0) {
			list = (List<ProcessTemplate>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listgongxu.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listgongxu";
	}

	/***
	 * 列表显示外委工序的所有Bom件号
	 * 
	 * @return
	 */
	public String listBom() {
		zhUser = markIdServer.listByIdZhUserId(zhUser.getId());

		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		Object[] object = markIdServer.listBom(procardTemplate, Integer
				.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<ProcardTemplate>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listBom.action?zhUser.id="
							+ zhUser.getId());
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listBom";
	}

	// -------------------------------------------以上为外委工序操作-----------------------------------------------------------
	/*
	 * 分页显示件号 查看
	 */
	public String listgysMarkIdjiepai() {
		if (gysMarkIdjiepai != null) {
			ActionContext.getContext().getSession().put("gysMarkIdjiepai",
					gysMarkIdjiepai);
		} else {
			gysMarkIdjiepai = (GysMarkIdjiepai) ActionContext.getContext()
					.getSession().get("gysMarkIdjiepai");
		}
		Object[] object = markIdServer.listgysMarkIdjiepai(gysMarkIdjiepai,
				Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<GysMarkIdjiepai>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listgysMarkIdjiepai.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listgysMarkIdjiepai";
	}

	/*
	 * 修改工资
	 */
	public String updateZhusergongzi() {
		ZhUser newzhUser = markIdServer.listByIdZhUserId(zhUser.getId());
		newzhUser.setGongzi(zhUser.getGongzi());
		newzhUser.setGongziStatus(null);
		markIdServer.updateZhusers(newzhUser);
		errorMessage = "修改成功";
		url = "markIdAction!gongzi.action";
		return ERROR;

	}

	// 申请工资审核
	public String shenqingongzi() {
		zhUser = markIdServer.listByIdZhUserId(zhUser.getId());
		Integer epId;
		String processName = "";
		try {
			processName = "供应商工资管理";
			epId = CircuitRunServerImpl.createProcess(processName,
					ZhUser.class, zhUser.getId(), "gongziStatus", "id", zhUser
							.getName()
							+ "人均/s工资修改审核", true, null);

			// epId = CircuitRunServerImpl.createProcess(197, ZhUser.class,
			// zhUser
			// .getId(), "gongziStatus", "id", zhUser.getName()
			// + "人均/s工资修改审核", false, null);
			if (epId != null && epId > 0) {
				zhUser.setGongziStatus("审核中");
				zhUser.setEpId(epId);
				markIdServer.updateZhusers(zhUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ------------------------------
		errorMessage = "申请提交成功！！！";
		url = "markIdAction!gongzi.action";
		return ERROR;
	}

	/*
	 * 提交动作 通过节拍和品均工总计算总成成本tijiao
	 */
	public String tijiao() {
		Users user = Util.getLoginUser();
		zhUser = markIdServer.listByIdZhUser(user.getId());
		gysMarkIdjiepai = markIdServer.findProcardTemById(gysMarkIdjiepai
				.getId());
		if (zhUser != null && zhUser.getGongzi() != null
				&& gysMarkIdjiepai.getSingleDuration() != null
				&& gysMarkIdjiepai.getDeliveryDuration() != null) {
			markIdServer.jiesuan2(zhUser.getGongzi(), gysMarkIdjiepai.getId());
			errorMessage = "提交成功!";
			return "tijiao";
		} else {
			errorMessage = "提交失败!您要填报的数据尚未填写完整!";
			// url="System/caigou/zhaobiao/bangding/Template_findProcard.jsp?id="+gysMarkIdjiepai.getId();
			// return ERROR;
			// url="Template_findProcard.jsp?id="+gysMarkIdjiepai.getId();
			return ERROR;
			// System/caigou/zhaobiao/bangding/Template_findProcard.jsp?id=${pageProcardTem.rootId}">明细(Details)</a>
		}

	}

	/*
	 * 添加工资
	 */
	public String updateZhusers() {
		ZhUser newzhUser = markIdServer.listByIdZhUserId(zhUser.getId());
		newzhUser.setGongzi(zhUser.getGongzi());
		markIdServer.updateZhusers(newzhUser);
		errorMessage = "添加成功！！！";
		url = "markIdAction!gongzi.action";
		return ERROR;
	}

	/*
	 * 判断是否填写了平均工资
	 */
	public String gongzi() {
		Users user = Util.getLoginUser();
		zhUser = markIdServer.listByIdZhUser(user.getId());
		return "gongzi";
	}

	/*
	 * 件号分页
	 */
	public String listtianxiejiepai() {
		if (gysMarkIdjiepai != null) {
			ActionContext.getContext().getSession().put("gysMarkIdjiepai",
					gysMarkIdjiepai);
		} else {
			gysMarkIdjiepai = (GysMarkIdjiepai) ActionContext.getContext()
					.getSession().get("gysMarkIdjiepai");
		}
		Object[] object = markIdServer.listtianxiejiepai(gysMarkIdjiepai,
				Integer.parseInt(cpage), pageSize);

		if (object != null && object.length > 0) {
			list = (List<GysMarkIdjiepai>) object[0];
			if (object != null && object.length > 0) {
				list = (List) object[0];
				if (list != null && list.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("markIdAction!listtianxiejiepai.action");
					errorMessage = null;
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			}
		}
		return "listtianxiejiepai";
	}

	/***
	 * 根据首层父类id查询流水卡片模板(页面生成树形结构)
	 * 
	 * @param procardTemplate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findProcardTemByRootId() {

		List<GysMarkIdjiepai> proList = markIdServer.findProcardTemByRootId(id);
		try {
			MKUtil.writeJSON(proList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 显示流水卡片详细
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findCardTemForShow() {
		Object[] obj = markIdServer.findCardTemForShow(id);
		if (obj != null) {
			gysMarkIdjiepai = (GysMarkIdjiepai) obj[0];
			procardTemplateList = (List<GysMarkIdjiepai>) obj[1];
			list = (List) obj[2];
			return "ProcardTemDetails";
		}
		return ERROR;
	}

	/***
	 * 修改流水单模板
	 * 
	 * @return
	 */
	public String updateProcardTem() {
		GysMarkIdjiepai oldProcardTem = markIdServer.findProcardTemById(id);
		if (oldProcardTem != null) {
			BeanUtils.copyProperties(oldProcardTem, gysMarkIdjiepai,
					new String[] { "markId", "proName", "banBenNumber","kgliao", "carStyle", "maxCount",
							"corrCount", "procardStyle", "unit",
							"productStyle", "trademark", "specification",
							"luhao", "number", "actualFixed", "yuanUnit",
							"quanzi1", "quanzi2", "jihuoType",
							"deliveryDuration" });
			boolean bool = markIdServer.updateProcardTemplate(gysMarkIdjiepai);
			if (bool) {
				return "updateProcardT";
			}
		} else {
			errorMessage = "不存在您要修改的模板信息!";
		}
		return ERROR;
	}

	/***
	 * 删除流水卡片
	 * 
	 * @return
	 */
	public String delProcard() {
		GysMarkIdjiepai oldProCard = markIdServer.findProcardTemById(id);
		if (oldProCard != null) {
			MKUtil.writeJSON(markIdServer.delProcardTemplate(oldProCard));
		}
		return null;
	}

	/***
	 * 添加流水卡片模板
	 * 
	 * @return
	 */
	public String addProcardTemplate() {
		// 查询父类流水卡片
		GysMarkIdjiepai fatherProTem = markIdServer
				.findProcardTemById(gysMarkIdjiepai.getFatherId());
		if (fatherProTem != null) {
			gysMarkIdjiepai.setGysMarkIdjiepai(fatherProTem);
			gysMarkIdjiepai.setMarkId(gysMarkIdjiepai.getMarkId().replaceAll(
					" ", ""));// 去除件号中的所有空格
			// 添加
			MKUtil.writeJSON(markIdServer.addProcardTemplate(gysMarkIdjiepai));
		}
		return null;
	}

	/***
	 * 添加工序信息
	 * 
	 * @return
	 */
	public String addProcessTemplate() {
		// 查询对应流水卡片
		GysMarkIdjiepai proTem = markIdServer.findProcardTemById(id);
		if (proTem != null) {
			pIdZijian.setGysMarkIdjiepai(proTem);
			MKUtil.writeJSON(markIdServer.addProcessTemplate(pIdZijian));
		}
		return null;
	}

	// 通过流水卡片id(外键)查询对应工序信息
	@SuppressWarnings("unchecked")
	public String findProcessByFkId() {
		List<ProcessMarkIdZijian> processList = markIdServer
				.findProcessByFkId(id);
		try {
			MKUtil.writeJSON(processList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 显示工序详细
	 * 
	 * @return
	 */
	public String showProcess() {
		pIdZijian = markIdServer.findProcessT(id);
		if (pIdZijian != null) {
			if (successMessage != null && "true".equals(successMessage)) {
				successMessage = "修改成功";
			}
			return "ProcessTemplateDetails";
		} else {
			errorMessage = "不存在您要查询的工序信息,请核对!";
		}
		return ERROR;
	}

	/***
	 * 更新工序模板信息
	 * 
	 * @param processT
	 * @return
	 */
	public String updateProcessT() {
		errorMessage = markIdServer.updateProcessT(pIdZijian);
		if ("true".equals(errorMessage)) {
			return "updateProcessT";
		}
		return ERROR;
	}

	/***
	 * 删除工序信息
	 */
	public void deleteProcess() {
		try {
			ProcessMarkIdZijian pt = markIdServer.findProcessT(id);
			if (pt != null) {
				markIdServer.delProcessT(pt);
				MKUtil.writeJSON(true, "删除成功!", null);
			} else
				MKUtil.writeJSON(false, "不存在您要删除的工序!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败!", null);
		}
	}

	// 批量导入供应供货产品信息;
	public String pladdGysMarkIdjiepai() {
		errorMessage = markIdServer.pladdGysMarkIdjiepai(addfile, status);
		 if("导入成功".equals(errorMessage)){
		 return "listAction";
		 }
		return "listgysMarkIdjiepai";
	}

	// 得到某一件号的总共配额；

	public void getSumCgbl() {
		try {
			Float cgbl = markIdServer.getSumCgbl(markId, kgliao,banBenNumber);
			MKUtil.writeJSON(cgbl);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}

	// 修改供应商产品配额
	public void updatecgbl() {
		try {
			errorMessage = markIdServer.changCgbl(id, cgbl, kgliao);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}

	}

	public void getepId() {
		try {
			Integer epId = markIdServer.getepId(id);
			MKUtil.writeJSON(epId);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("error");
		}
	}

	// 添加供应商产品;
	public String toadd() {
		zhuserList = markIdServer.findAllZhUser();
		return "gysMarkIdjiepai_add";
	}

	public String addgysjiepai() {
		errorMessage = markIdServer.addgysjiepai(gysMarkIdjiepai, gys);
		if ("true".equals(errorMessage)) {
			errorMessage = "添加成功!";
		}
		return "gysMarkIdjiepai_add";
	}
	
	
	/***
	 * 供应商综合信息 供应类别占比
	 * @return
	 */
	public String SupplyCategoryPercent() {
		GysMarkIdjiepai markIdjiepai=new GysMarkIdjiepai();
//		markIdjiepai.setGys(gys);	
		markIdjiepai.setZhuserId(id);	
		
		Object[] o=markIdServer.SupplyTypeCategory(markIdjiepai);
		List<String> list =(List<String>) o[0];
		Map<String, Object> map=new HashMap<String, Object>();
		int i=1;
		for (String string : list) {
			if (map.containsKey(string)) {
				i=Integer.parseInt(map.get(string).toString());
				i++;
			}
				map.put(string, i);
		}
		
		class Categorydata{
			public String name;
			public String value;
		}
		List<Categorydata> categorydatas = new ArrayList<Categorydata>();
		for (String key : map.keySet()) {
			Categorydata data=new Categorydata();
			data.name=key;
			data.value=map.get(key).toString();
			categorydatas.add(data);
		}  
		MKUtil.writeJSON(categorydatas);
		return null; 
	}
	
	//导出
	public void exprot(){
		markIdServer.exprot(gysMarkIdjiepai);
	}
	//查询所有巡检标准
	public void findAllJyPc(){
		try {
			List<Waigoujianpinci> wgpc =markIdServer.findAllJyPc();
			MKUtil.writeJSON(wgpc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// ------------------------------------------------------------------------------------------
	public MarkIdServer getMarkIdServer() {
		return markIdServer;
	}

	public void setMarkIdServer(MarkIdServer markIdServer) {
		this.markIdServer = markIdServer;
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getListAll() {
		return listAll;
	}

	public void setListAll(List listAll) {
		this.listAll = listAll;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public GysMarkIdjiepai getGysMarkIdjiepai() {
		return gysMarkIdjiepai;
	}

	public void setGysMarkIdjiepai(GysMarkIdjiepai gysMarkIdjiepai) {
		this.gysMarkIdjiepai = gysMarkIdjiepai;
	}

	public ProcessMarkIdZijian getpIdZijian() {
		return pIdZijian;
	}

	public void setpIdZijian(ProcessMarkIdZijian pIdZijian) {
		this.pIdZijian = pIdZijian;
	}

	public List<GysMarkIdjiepai> getProcardTemplateList() {
		return procardTemplateList;
	}

	public void setProcardTemplateList(List<GysMarkIdjiepai> procardTemplateList) {
		this.procardTemplateList = procardTemplateList;
	}

	public ZhUser getZhUser() {
		return zhUser;
	}

	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}

	public ProcardTemplate getProcardTemplate() {
		return procardTemplate;
	}

	public void setProcardTemplate(ProcardTemplate procardTemplate) {
		this.procardTemplate = procardTemplate;
	}

	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

	public int[] getSelected() {
		return selected;
	}

	public void setSelected(int[] selected) {
		this.selected = selected;
	}

	public Waigoujianpinci getWaigoujianpinci() {
		return waigoujianpinci;
	}

	public void setWaigoujianpinci(Waigoujianpinci waigoujianpinci) {
		this.waigoujianpinci = waigoujianpinci;
	}

	public WaigoujianpinciZi getWaigoujianpinciZi() {
		return waigoujianpinciZi;
	}

	public void setWaigoujianpinciZi(WaigoujianpinciZi waigoujianpinciZi) {
		this.waigoujianpinciZi = waigoujianpinciZi;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public File getAddfile() {
		return addfile;
	}

	public void setAddfile(File addfile) {
		this.addfile = addfile;
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

	public List<ZhUser> getZhuserList() {
		return zhuserList;
	}

	public void setZhuserList(List<ZhUser> zhuserList) {
		this.zhuserList = zhuserList;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public Float getCgbl() {
		return cgbl;
	}

	public void setCgbl(Float cgbl) {
		this.cgbl = cgbl;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGys() {
		return gys;
	}

	public void setGys(String gys) {
		this.gys = gys;
	}

	public String getBanBenNumber() {
		return banBenNumber;
	}

	public void setBanBenNumber(String banBenNumber) {
		this.banBenNumber = banBenNumber;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
