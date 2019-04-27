package com.task.action.pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.pro.QuotedPriceServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.bargain.BarContract;
import com.task.entity.bargain.Bargain;
import com.task.entity.perform.Performsingle;
import com.task.entity.project.Investor;
import com.task.entity.project.InvestorEvaluation;
import com.task.entity.project.InvestorMonthJx;
import com.task.entity.project.InvestorOfQuotedPrice;
import com.task.entity.project.ProjectTime;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedPriceCost;
import com.task.entity.project.QuotedPriceFenhong;
import com.task.entity.project.QuotedPriceFile;
import com.task.entity.project.QuotedPriceLog;
import com.task.entity.project.QuotedPriceUserCost;
import com.task.entity.project.QuotedPricejy;
import com.task.entity.project.QuotedPricejyDetail;
import com.task.entity.project.QuotedPricejyDetailFile;
import com.task.entity.project.QuotedPricejyUsers;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.project.RechargeRecord;
import com.task.entity.sop.OutSourcingApp;
import com.task.util.DateUtil;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

@SuppressWarnings( { "serial", "unchecked" })
public class QuotedPriceAction extends ActionSupport {

	private QuotedPriceServer quotedPriceServer;// Server层
	private QuotedPrice quotedPrice;// 对象
	private List<OutSourcingApp> osaList;// 外委外购评审
	private ProjectTime projectTime;// 对象
	private ProjectTime bomprojectTime;// 对象
	private QuotedProcessInfor qpInfor;// 对象
	private List<QuotedPrice> quotedPriceList;// 集合
	private List<OutSourcingApp> outSouList;// 外委(外采）产品申报单
	private List<Bargain> bargainList;// 议价表（主表）
	private List<Performsingle> performsingleList;// 采购执行单（主表）
	private List<BarContract> barContractList;// 合同（主表）
	private List<Price> pricesList;// 价格（主表）
	private List<Object> list;// 集合
	private QuotedPriceCost quotedPriceCost;
	private QuotedPriceUserCost quotedPriceUserCost;
	private List<QuotedPriceUserCost> qpUserCostList;
	private List<QuotedPriceCost> qpCostList;
	private QuotedPriceFenhong qpfh;// 分红
	private List<QuotedPriceFenhong> qpfhList;// 分红列表
	private Investor investor;// 投资人
	private Users user;
	private RechargeRecord rechargeRecord;// 充值记录
	private List<RechargeRecord> rechargeRecordlist;
	private InvestorMonthJx investorMonthJx;//
	private List<InvestorMonthJx> investorMonthJxList;//
	private List<Investor> investorList;
	private List<Users> userList;// 未绑定用户
	private List<Users> bangUserList;// 已绑定用户
	private InvestorOfQuotedPrice investorOfQp;
	private List<InvestorOfQuotedPrice> investorOfQpList;
	private List<InvestorEvaluation> investorEvaluationList;//
	private InvestorEvaluation investorEvaluation;
	private List<InvestorEvaluation> ieList;
	private QuotedPriceFile quotedPriceFile;//报价图纸
	private List<QuotedPriceFile> qpFileList;//
	private String gygfFileName;//
	private String realPath;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private int id;// id
	private int id2;// id
	private String pageStatus;// 页面状态
	private Map map;
	private Float money;
	private Float moneysz;
	private Float moneysj;
	private Float moneyqt;
	private String ytRadio;// 原图标记
	private String proStatus;// 项目阶段
	private List<QuotedPriceLog> qpLogList;// 报价系统日志列表
	private QuotedPriceLog quotedPriceLog;// 报价系统日志
	private Float totalMoney, tzMoney, dfMoney, zongCount, shengyuCount,
			selfAllMoney, selfThisMoney;
	private String start;
	private String end;
	private String title;//
	private String jyContext;
	private String code;
	private String name;
	private String productStyle;

	private QuotedPricejy qpjy;
	private List<QuotedPricejy> qpjydclList;//
	private List<QuotedPricejy> qpjyList;//
	private QuotedPricejyDetail qpjyd;
	private QuotedPricejyDetailFile qpjydFile;
	private List<QuotedPricejyDetailFile> qpjydFileList;
	private List<QuotedPricejyDetail> qpjydList;//
	private QuotedPricejyUsers qpjyUser;//
	private List<QuotedPricejyUsers> qpjyUserList;//
	
	
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	private File accessory;// 上传附件
	private String accessoryContentType;// 附件类型
	private String accessoryFileName;// 文件名称

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private int count = 0;

	private String markId;// 零件号
	private Integer moveId;// 移动的模板的id
	private Integer targetId;// 目标模板的id
	private Boolean isAll;// 更新单个或者所有报价零件
	private String op;// 操作
	private String data;//

	private String isneedPrice = "yes";// 外购外委录入时否则表示上层为外购，该层不需要录入价格
	private String zizhiisneedPrice = "yes";// 外购外委录入时否则表示原材料不外购
	private String ids;// 组装id以","隔开
	private Integer[] usersId;//
	private String allId;// 总成件号
	private List<Price> priceList;

	// 根据件号总查询
	public String allProcardSelsct() {
		quotedPriceList = quotedPriceServer.findQuotedPriceList(allId, Integer
				.parseInt(cpage), pageSize);
		outSouList = quotedPriceServer.findOutSourcingAppList(allId, Integer
				.parseInt(cpage), pageSize);
		;
		bargainList = quotedPriceServer.findBargainList(allId, Integer
				.parseInt(cpage), pageSize);
		;
		performsingleList = quotedPriceServer.findPerformsingleList(allId,
				Integer.parseInt(cpage), pageSize);
		;
		barContractList = quotedPriceServer.findBarContractList(allId, Integer
				.parseInt(cpage), pageSize);
		;
		pricesList = quotedPriceServer.findPricesList(allId, Integer
				.parseInt(cpage), pageSize);
		;
		return "allProcardSelsct";
	}

	/***
	 * 添加核算信息(总)
	 * 
	 * 
	 */
	public String addQuotedPrice() {
		try {
			quotedPriceServer.addQuotedPrice(quotedPrice, attachment,
					attachmentFileName);
			errorMessage = "添加核算信息成功!";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "添加失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 添加工艺规范Tree
	 * 
	 * @return
	 */
	public String addQuotedPriceTree() {
		// 查询父类流水卡片
		QuotedPrice fatherQuotedPrice = quotedPriceServer
				.afindQuotedPrice(quotedPrice.getFatherId());
		try {
			if (fatherQuotedPrice != null) {
				// 各属性赋值
				quotedPrice.setQuotedPrice(fatherQuotedPrice);
				// 添加
				quotedPriceServer.add(quotedPrice);
				MKUtil.writeJSON(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "添加失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/***
	 * 删除核算信息
	 * 
	 * 
	 */
	public String delQuotedPrice() {
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		try {
			quotedPriceServer.delQuotedPrice(quotedPrice);
			errorMessage = "删除成功!";
			url = "QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus=all";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "删除失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	public void updateMarkId() {
		if (id != 0 && markId != null) {
			List<String> markIds = quotedPriceServer.getAllMarkId();
			if (isAll && markIds.contains(markId)) {
				MKUtil.writeJSON(false, "修改失败,新件号已经存在不允许修改全部", null);
			}
			boolean b = quotedPriceServer.updateMarkId(id, markId, isAll);
			if (b) {
				MKUtil.writeJSON(b, "修改成功", null);
			} else {
				MKUtil.writeJSON(b, "修改失败", null);
			}
		}
	}

	/**
	 * 已选中的零件为主同步所有同件号的零件
	 */
	public void updateQuotedPriceByMarkId() {
		if (id != 0) {
			boolean b = quotedPriceServer.updateQuotedPrice(id);
			if (b) {
				MKUtil.writeJSON(b, "更新成功！", null);
			} else {
				MKUtil.writeJSON(b, "更新失败！", null);
			}
		} else {
			MKUtil.writeJSON(false, "您选中的模板不存在，添加失败！", null);
		}
	}

	/***
	 * 删除工艺规范Tree
	 * 
	 * @return
	 */
	public String delQuotedPriceTree() {
		// 查询父类流水卡片
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		try {
			quotedPriceServer.del(quotedPrice);
			MKUtil.writeJSON(true, "删除成功", null);
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "删除失败,原因:" + e.getMessage();
			MKUtil.writeJSON(false, errorMessage, null);
		}
		return ERROR;
	}

	/***
	 * 修改核算信息
	 * 
	 * 
	 */
	public String updateQuotedPrice() {
		try {
			QuotedPrice qp = quotedPriceServer.afindQuotedPrice(id);
			if (qp != null) {
				BeanUtils.copyProperties(qp, quotedPrice, new String[] {
						"markId", "proName", "carStyle", "corrCount",
						"materialXh", "materialXhsj", "materialXhsz",
						"xingbie", "unit", "productStyle", "trademark",
						"specification", "luhao", "number", "actualFixed",
						"yuanUnit", "quanzi1", "quanzi2", "yucailiaostatus",
						"needProcess" });
				quotedPriceServer.update(quotedPrice);
				return "findQuotedPrice";
			} else {
				errorMessage = "不存在您要修改的模板信息!";
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "修改失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/**
	 * 移动报价零件
	 * 
	 * @return
	 */
	public void moveQuotedPrice() {
		if (moveId != null && targetId != null) {
			boolean b = quotedPriceServer.saveMoveQuotedPrice(moveId, targetId);
			if (b) {
				MKUtil.writeJSON(true, "移动成功！", null);
			} else {
				MKUtil.writeJSON(false, "移动失败！", null);
			}
		} else {
			MKUtil.writeJSON(false, "您选中的模板不存在，移动！", null);
		}
	}

	/***
	 * 查询核算信息（分页、条件查询）
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String findQPByCondition() {
		if (quotedPrice != null) {
			ActionContext.getContext().getSession().put("quotedPrice",
					quotedPrice);
		} else {
			quotedPrice = (QuotedPrice) ActionContext.getContext().getSession()
					.get("quotedPrice");
		}
		// 填报页面查询所有待录入数据
		if (pageStatus != null && !"all".equals(pageStatus)) {
			list = quotedPriceServer.findNeedLuru(pageStatus);
		}
		Object[] object = quotedPriceServer.findPMByCondition(quotedPrice,
				Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			quotedPriceList = (List<QuotedPrice>) object[0];
			if (quotedPriceList != null && quotedPriceList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null && pageStatus.length() > 0) {
					this
							.setUrl("QuotedPrice_findQPByCondition.action?pageStatus="
									+ pageStatus);
				} else {
					this.setUrl("QuotedPrice_findQPByCondition.action");
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要填报的内容!";
			}
		}
		return "QuotedPrice_list";
	}

	/**
	 * 查看报价系统日志
	 * 
	 * @return
	 */
	public String trackQuotedPrice() {
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		qpLogList = quotedPriceServer.getQuotedPriceLog(id);
		return "QuotedPrice_trackQuotedPrice";
	}

	/**
	 * 添加报价日志
	 * 
	 * @return
	 */
	public String addQuotedPriceLog() {
		if (quotedPriceLog == null) {
			return trackQuotedPrice();
		}
		String fileName = null;
		if (accessory != null) {
			Random rd = new Random();
			fileName = "accessory"
					+ DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
			// 为防有同时文件上传产生同名文件就产生4位随机数用来做文件名后缀
			fileName = fileName + rd.nextInt(10) + rd.nextInt(10)
					+ rd.nextInt(10) + rd.nextInt(10);
			String[] strs = accessoryFileName.split("\\.");
			if (strs != null && strs.length >= 2) {
				fileName = fileName + "." + strs[strs.length - 1];
				quotedPriceLog.setAccessory("upload/file/qpLog" + "/"
						+ fileName);
			} else {
				errorMessage = "请先定义文件的类型！";
				return trackQuotedPrice();
			}

		}
		Users user = Util.getLoginUser();
		quotedPriceLog.setRootId(id);
		quotedPriceLog.setUserName(user.getName());
		quotedPriceLog.setCode(user.getCode());
		quotedPriceLog.setTime(DateUtil.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		boolean b = quotedPriceServer.addQuotedPriceLog(quotedPriceLog);
		if (b && accessory != null) {
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/qpLog");
			File file1 = new File(path);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			// 将证书写入文件夹中
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(accessory);
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 如果有重名将原附录删掉
				}
				os = new FileOutputStream(path + "/" + fileName);
				byte[] bt = new byte[1024];
				int length = 0;
				while (-1 != (length = is.read(bt, 0, bt.length))) {
					os.write(bt);
				}
				successMessage = "日志添加成功！";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				errorMessage = "找不到文件！";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorMessage = "文件输入出错！";
			} finally {
				try {
					os.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (b) {
			successMessage = "日志添加成功！";
		}
		return trackQuotedPrice();
	}

	/**
	 * 删除报价日志
	 * 
	 * @return
	 */
	public String deleteLog() {
		quotedPriceLog = quotedPriceServer.getQpLogById(quotedPriceLog.getId());
		if (quotedPriceLog == null || quotedPriceLog.getRootId() == null) {
			pageStatus = "all";
			return findQPByCondition();
		}
		id = quotedPriceLog.getRootId();
		String fileName = quotedPriceLog.getAccessory();
		boolean b = quotedPriceServer.deleteQPLogByid(quotedPriceLog.getId());
		if (b) {
			if (fileName != null) {
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath("");
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 如果有重名将原附录删掉
				}
			}

			successMessage = "删除成功";
		} else {
			errorMessage = "删除失败";
		}
		return trackQuotedPrice();

	}

	/**
	 * 跳往修改报价日志页面
	 * 
	 * @return
	 */
	public String toupdateLog() {
		quotedPriceLog = quotedPriceServer.getQpLogById(quotedPriceLog.getId());
		if (quotedPriceLog == null) {
			MKUtil.writeJSON("您要修改的日志不存在！");
			return null;
		} else {
			return "QuotedPrice_updateQuotedPriceLog";
		}
	}

	/**
	 * 修改报价日志
	 * 
	 * @return
	 */
	public String updateQuotedPriceLog() {
		if (quotedPriceLog == null) {
			return trackQuotedPrice();
		}

		QuotedPriceLog qpLog = quotedPriceServer.getQpLogById(quotedPriceLog
				.getId());
		if (qpLog == null) {
			MKUtil.writeJSON("您要修改的日志不存在！");
			return null;
		} else {
			id = qpLog.getRootId();
		}

		String fileName = null;
		if (accessory != null) {
			// 删除原附件
			if (qpLog.getAccessory() != null) {
				String path = ServletActionContext.getServletContext()
						.getRealPath("");
				File file2 = new File(path + "/" + qpLog.getAccessory());
				if (file2.exists()) {
					file2.delete();// 如果有重名将原附录删掉
				}
			}
			Random rd = new Random();
			fileName = "accessory"
					+ DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
			// 为防有同时文件上传产生同名文件就产生4位随机数用来做文件名后缀
			fileName = fileName + rd.nextInt(10) + rd.nextInt(10)
					+ rd.nextInt(10) + rd.nextInt(10);
			String[] strs = accessoryFileName.split("\\.");
			if (strs != null && strs.length >= 2) {
				fileName = fileName + "." + strs[strs.length - 1];
				quotedPriceLog.setAccessory("upload/file/qpLog" + "/"
						+ fileName);
			} else {
				errorMessage = "请先定义文件的类型！";
				return trackQuotedPrice();
			}

		}
		Users user = Util.getLoginUser();
		quotedPriceLog.setTime(DateUtil.formatDate(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		boolean b = quotedPriceServer.updateQuotedPriceLog(quotedPriceLog);
		if (b && accessory != null) {
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/qpLog");
			File file1 = new File(path);
			if (!file1.exists()) {
				file1.mkdirs();// 如果不存在文件夹就创建
			}
			// 将证书写入文件夹中
			InputStream is = null;
			OutputStream os = null;
			try {
				is = new FileInputStream(accessory);
				File file2 = new File(path + "/" + fileName);
				if (file2.exists()) {
					file2.delete();// 如果有重名将原附录删掉
				}
				os = new FileOutputStream(path + "/" + fileName);
				byte[] bt = new byte[1024];
				int length = 0;
				while (-1 != (length = is.read(bt, 0, bt.length))) {
					os.write(bt);
				}
				successMessage = "日志修改成功！";
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				errorMessage = "找不到文件！";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorMessage = "文件输入出错！";
			} finally {
				try {
					os.close();
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else if (b) {
			successMessage = "日志修改成功！";
		}
		return trackQuotedPrice();
	}

	/***
	 * 查询核算信息用于all、 bom、显示
	 * 
	 * @param id
	 */
	public String findQuotedPrice() {
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		if (quotedPrice == null) {
			errorMessage = "不存在您要查询的项目核算信息!";
			return ERROR;
		}
		if (pageStatus != null && pageStatus.length() > 0) {
			// 查询指派时间
			projectTime = quotedPriceServer.findProjectTime(
					quotedPrice.getId(), pageStatus);
			
			
			if ("all".equals(pageStatus)) {
				return "QuotedPrice_tree";// 总管理
			} else if ("start".equals(pageStatus)) {// 项目启动中
				return "QuotedPrice_afterstart";// bom规范填报
			}else if("showBom".equals(pageStatus)){
				return "QuotedPrice_bom";// bom规范填报
			}else {
				if (projectTime!=null && projectTime.getProvisionTime() != null
						&& projectTime.getProvisionTime().length() > 0) {
					if ("bom".equals(pageStatus)||"bombaomi".equals(pageStatus)) {
						return "QuotedPrice_bom";// bom规范填报
					} else {
						// 查询bom录入数据
						bomprojectTime = quotedPriceServer.findProjectTime(
								quotedPrice.getId(), "bom");
						if ("rg".equals(pageStatus)) {
							return "QuotedPrice_bom_showRg";
						}
						return "QuotedPrice_bom_dept";// 各部门填报
					}
				} else {
					errorMessage = projectTime.getClassName()+ ",未指派时间,可能无需填报,请注意。";
					return ERROR;
				}
			}
		}
		return "QuotedPrice_detail";// 明细
	}

	/***
	 * 查询id对应数据组装树形结构
	 */
	@SuppressWarnings("unchecked")
	public void findAllForTree() {
		List list = quotedPriceServer.findAllForTree(id);
		if (list != null && list.size() > 0) {
			// 清空数据关联关系，用于输出Json数据
			List<QuotedPrice> newList = new ArrayList<QuotedPrice>();
			for (int i = 0; i < list.size(); i++) {
				QuotedPrice qp = (QuotedPrice) list.get(i);
				qp.setQuotedPrice(null);
				qp.setQuotedPriceSet(null);
				qp.setQpinfor(null);
				newList.add(qp);
			}
			MKUtil.writeJSON(newList);
		}
	}

	/***
	 * 查询工艺规范单条明细 cl、sb、gz、fl、rg、wgww、cw
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findQpDetailForShow() {
		Object[] obj = quotedPriceServer.findQpDetailForShow(id);
		if (obj != null) {
			quotedPrice = (QuotedPrice) obj[0];
			quotedPriceList = (List<QuotedPrice>) obj[1];
			if (quotedPrice.getProcardStyle() != null
					&& quotedPrice.getProcardStyle().equals("外购")
					&& (quotedPrice.getNeedProcess() == null || !quotedPrice
							.getNeedProcess().equals("yes"))) {
			} else {
				list = (List) obj[2];
			}
			if (pageStatus != null && pageStatus.length() > 0) {
				if ("cl".equals(pageStatus)||"clbaomi".equals(pageStatus)
						|| "sb".equals(pageStatus)|| "sbbaomi".equals(pageStatus)
						|| "gz".equals(pageStatus)|| "gzbaomi".equals(pageStatus)
						|| "fl".equals(pageStatus) || "flbaomi".equals(pageStatus) 
						|| "rg".equals(pageStatus) || "rgbaomi".equals(pageStatus) 
						|| "cwgl".equals(pageStatus)|| "cwglbaomi".equals(pageStatus)
						|| "nyxh".equals(pageStatus)|| "nyxhbaomi".equals(pageStatus)
						|| "bzys".equals(pageStatus)|| "bzysbaomi".equals(pageStatus)
						|| "gzf".equals(pageStatus)|| "gzfbaomi".equals(pageStatus)
						) {
					return "QuotedPrice_dept";
				} else if ("wgww".equals(pageStatus)||"wgwwbaomi".equals(pageStatus)) {
					isneedPrice = "yes";
					zizhiisneedPrice = "yes";
					if (quotedPrice != null
							&& quotedPrice.getProcardStyle() != null
							&& quotedPrice.getProcardStyle().equals("自制")) {
						if (quotedPrice.getYucailiaostatus() == null
								|| quotedPrice.getYucailiaostatus().equals("否")) {
							zizhiisneedPrice = "no";
						}

					}
					if (obj[3] != null) {
						QuotedPrice fqp = (QuotedPrice) obj[3];
						if (fqp.getYucailiaostatus() != null
								&& fqp.getYucailiaostatus().equals("是")) {
							isneedPrice = "no";
							zizhiisneedPrice = "no";
						}
					}
					return "QuotedPrice_dept";
				} else if ("start".equals(pageStatus)) {
					return "QuotedPrice_detailstart";
				}
			}
			return "QuotedPrice_detail";
		}
		return ERROR;
	}

	/* ============== 工序操作部分======== */
	/***
	 * 添加工序信息
	 * 
	 * @return
	 */
	public String addQpInfor() {
		// 查询对应流水卡片
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		try {
			if (quotedPrice != null) {
				if (quotedPrice.getProcardStyle().equals("外购")
						&& (quotedPrice.getNeedProcess() == null || !quotedPrice
								.getNeedProcess().equals("yes"))) {
					MKUtil.writeJSON(false, "非半成品外购件不可添加工序", null);
				}
				qpInfor.setQuotedPrice(quotedPrice);
				quotedPriceServer.addQpInfor(qpInfor);
				MKUtil.writeJSON(true, "添加工序成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "添加工序失败,原因:" + e.getMessage();
			MKUtil.writeJSON(false, errorMessage, null);
		}
		return null;
	}

	/***
	 * 删除工序信息
	 * 
	 * @return
	 */
	public String delQpInfor() {
		// 查询对应流水卡片
		qpInfor = quotedPriceServer.findQpInfor(id);
		try {
			if (qpInfor != null) {
				quotedPriceServer.delQpInfor(qpInfor);
				MKUtil.writeJSON(true, "删除工序成功", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "删除工序失败,原因:" + e.getMessage();
			MKUtil.writeJSON(false, errorMessage, null);
		}
		return null;
	}

	/***
	 * 查询件号对应的所有工序信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void findQpInforByFkid() {
		List list = quotedPriceServer.findQpInforByFkid(id);
		List<QuotedProcessInfor> newList = new ArrayList<QuotedProcessInfor>();
		for (int i = 0; i < list.size(); i++) {
			QuotedProcessInfor newqpInfor = (QuotedProcessInfor) list.get(i);
			newqpInfor.setQuotedPrice(null);
			newList.add(newqpInfor);
		}
		MKUtil.writeJSON(newList);
	}

	/**
	 * 材料费、外购、财务风水电气费、管理以及财务费 填报
	 */
	public String updateQuotedPriceForcl() {
		try {
			quotedPriceServer.updateQuotedPriceForcl(quotedPrice, pageStatus);
			return "afindQuotedPrice";
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "填报材料信息失败,原因:" + e.getMessage();
		}
		return ERROR;
	}

	/**
	 * 各部门录入各部分数据
	 * 
	 * @param qpInfor
	 * @param pageStatus
	 */
	public String updateDeptLuru() {
		quotedPriceServer.updateDeptLuru(qpInfor, pageStatus);
		return "afindQuotedPrice";
	}

	/***
	 * 报价汇总
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAllPrice() {
		// 查询对应流水卡片
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		list = quotedPriceServer.findAllPrice(id);
		money = quotedPriceServer.findAllMoney(id);
		moneysz = quotedPriceServer.findAllMoneysz(id);
		moneysj = quotedPriceServer.findAllMoneysj(id);
		return "QuotedPrice_allPrice";
	}

	/**
	 * 显示计算试算成品页面
	 * 
	 * @throws
	 */
	public String initTrial() {
		String jsonStr = quotedPriceServer.packageData(id, null);
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
	 * @Title: trial
	 * @Description: 试算
	 * @return String json数据
	 * @throws
	 */
	public String trial() {
		quotedPriceServer.trial(id);
		return "rengongHesuan";
	}

	/**
	 * 查询工序明细
	 * 
	 * @return
	 */
	public String showProcess() {
		qpInfor = quotedPriceServer.showProcess(id);
		return "QuotedPrice_bom_qpInfor";

	}

	/**
	 * 修改工序明细
	 * 
	 * @return
	 */
	public String updateProcess() {
		if (qpInfor.getIsOld() == null || !qpInfor.getIsOld().equals("yes")) {
			if (qpInfor.getGongzhuangNumber() != null
					&& !qpInfor.getGongzhuangNumber().equals("")
					&& (qpInfor.getGongzhuang() == null || qpInfor
							.getGongzhuang().equals(""))) {
				errorMessage = "不能只填写工装名称,修改失败";
				qpInfor = quotedPriceServer.findQpInfor(qpInfor.getId());
				return "QuotedPrice_bom_qpInfor";
			}
			if (qpInfor.getGongzhuang() != null
					&& !qpInfor.getGongzhuang().equals("")
					&& (qpInfor.getGongzhuangNumber() == null || qpInfor
							.getGongzhuangNumber().equals(""))) {
				errorMessage = "不能只填写工装编号,修改失败";
				qpInfor = quotedPriceServer.findQpInfor(qpInfor.getId());
				return "QuotedPrice_bom_qpInfor";
			}
		}
		quotedPriceServer.updateProcess(qpInfor);
		return "QuotedPrice_bom_qpInfor";

	}

	/**
	 * 启动项目
	 * 
	 * @return
	 */
	public String startproject() {
		boolean b = quotedPriceServer.updateStartProject(id);
		if (b) {
			successMessage = "项目启动成功！";
		} else {
			successMessage = "项目启动失败！";
		}
		if (quotedPrice == null) {
			quotedPrice = new QuotedPrice();
		}
		quotedPrice.setBelongLayer(0);
		return findQPByCondition();
	}

	/**
	 * 提交启动中的项目让项目开始项目跟踪
	 */
	public void submitProject() {
		if (id != 0) {
			boolean b = quotedPriceServer.updateSubmitProject(id);
			if (b) {
				MKUtil.writeJSON(b, "提交成功！项目进入项目跟踪阶段！", null);
			} else {
				MKUtil.writeJSON(b, "您提交的项目含有未更改的临时工装！", null);
			}
		} else {
			MKUtil.writeJSON(false, "您提交的项目不存在！", null);
		}
	}

	/**
	 * ajax 获取含有少许字符的全字符
	 */
	public void getAllNames() {
		if (markId != null) {
			quotedPriceList = quotedPriceServer.getAllNames(markId);
			try {
				MKUtil.writeJSON(quotedPriceList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制模板
	 * 
	 * @return
	 */
	public void copyProcard() {
		// 被选中的主卡模板
		QuotedPrice qp1 = quotedPriceServer.afindQuotedPrice(id);
		if (markId == null || markId.equals("")) {
			MKUtil.writeJSON(false, "您选中的模板不存在，添加失败！", null);
			return;
		}
		QuotedPrice qp2 = quotedPriceServer.findQuotedPriceByMarkId(markId);
		if (qp1 != null && qp2 != null) {
			if (qp1.getProcardStyle() != null && qp2.getProcardStyle() != null) {
				boolean b = true;
				if (qp1.getMarkId() != null && qp2.getMarkId() != null
						&& qp1.getMarkId().equals(qp2.getMarkId())) {
					MKUtil.writeJSON(false, "同一件号不能互相复制，复制失败！", null);
					return;
				}
				qp1.setQuotedPrice(null);
				qp2.setQuotedPrice(null);
				if (qp1.getProcardStyle().equals("外购")) {
					MKUtil.writeJSON(false, "外购件下不能复制模板，复制失败！", null);
					return;
				} else if (qp1.getProcardStyle().equals("自制")
						&& !qp2.getProcardStyle().equals("自制")) {
					MKUtil.writeJSON(false, "自制件下只能复制自制件下的工序，复制失败！", null);
					return;
				} else if (qp1.getProcardStyle().equals("自制")
						&& qp2.getProcardStyle().equals("自制")) {
					b = b & quotedPriceServer.saveCopyProcess(qp1, qp2);
				} else if (!qp1.getProcardStyle().equals("总成")
						&& qp2.getProcardStyle().equals("总成")) {
					MKUtil.writeJSON(false, "非总成零件下面不能复制总成模板，复制失败！", null);
					return;
				} else if (qp1.getProcardStyle().equals("总成")
						|| (qp1.getProcardStyle().equals("组合") && !qp2
								.getProcardStyle().equals("总成"))) {
					b = b & quotedPriceServer.saveCopyQuotedPrice(qp1, qp2);
				}
				if (b) {
					MKUtil.writeJSON(true, "模板复制成功！", null);
				}
			} else {
				MKUtil.writeJSON(false, "您选中的模板没有类型，复制失败！", null);
				return;
			}
		} else {
			MKUtil.writeJSON(false, "您选中的模板不存在，添加失败！", null);
		}
	}

	/**
	 * 根据报价bom查出其外委外购评审状况
	 * 
	 * @return
	 */
	public String findeOsa() {
		osaList = quotedPriceServer.findOsa(id);
		return "QuotedPrice_showosa";
	}

	/**
	 * 发送外委外购评审提醒
	 */
	public void sendOsaNotify() {
		if (ids == null || ids.equals("")) {
			MKUtil.writeJSON("没有选中的外委外购评审");
		} else {
			String msg = quotedPriceServer.sendOsaNotify(ids);
			MKUtil.writeJSON(msg);
		}
	}

	/**
	 * 将整棵报价bom树打入到试制bom中
	 */
	public void buildBomtosop() {
		if (quotedPrice != null && quotedPrice.getRootId() != null) {
			Map<Integer, Object> map = quotedPriceServer.buildProcardTemp(
					quotedPrice.getRootId(), "试制");
			if (map != null) {
				boolean b = Boolean.parseBoolean(map.get(1).toString());
				MKUtil.writeJSON(b, map.get(2).toString(), null);
			}
		}
	}

	public String buildBomtolp() {
		if (quotedPrice != null && quotedPrice.getRootId() != null) {
			Map<Integer, Object> map = quotedPriceServer.buildProcardTemp(
					quotedPrice.getRootId(), "批产");
			if (map != null) {
				boolean b = Boolean.parseBoolean(map.get(1).toString());
				if (b) {
					quotedPriceServer.updateStatus(quotedPrice.getRootId(),
							"核算完成");
				}
				errorMessage = map.get(2).toString();
				return "error";
			}
		}
		return "没有找到目标BOM,进入lp失败!";
	}

	/**
	 * 各项费用填报
	 * 
	 * @return
	 */
	public String findQpForCost() {
		if (quotedPrice != null) {
			ActionContext.getContext().getSession().put("quotedPrice",
					quotedPrice);
		} else {
			quotedPrice = (QuotedPrice) ActionContext.getContext().getSession()
					.get("quotedPrice");
		}
		// // 填报页面查询所有待录入数据
		// if (pageStatus != null && !"all".equals(pageStatus)) {
		// list = quotedPriceServer.findNeedLuru(pageStatus);
		// }
		Object[] object = quotedPriceServer.findQpForCost(quotedPrice, Integer
				.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			quotedPriceList = (List<QuotedPrice>) object[0];
			if (quotedPriceList != null && quotedPriceList.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("QuotedPrice_findQpForCost.action?cpage=" + cpage);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你的项目内容!";
			}
		}

		return "QuotedPrice_forcost";
	}

	/**
	 * 展示树形为报价使用
	 * 
	 * @return
	 */
	public String findQPforCost() {
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		if (quotedPrice == null) {
			errorMessage = "没有找到对应的信息!";
			return "error";
		} else {
			return "QuotedPrice_treecost";
		}
	}

	/**
	 * 查询费用申报明细
	 * 
	 * @return
	 */
	public String findQpDetailForCost() {
		investor = quotedPriceServer.getInvestorByLogin();
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		if (quotedPrice != null && quotedPrice.getAllFeiyong() == null) {
			quotedPrice = quotedPriceServer.changeQpAllFeiYong(id);
		}
		if (pageStatus == null || pageStatus.length() == 0) {
			qpCostList = quotedPriceServer.getQpCostDetail(id);
			return "QuotedPrice_costdetail";
		} else if (pageStatus.equals("singletz") || pageStatus.equals("alltz")) {
			String msg = quotedPriceServer.gettzzg();
			if (!"true".equals(msg)) {
				errorMessage = msg;
				return "error";
			}
			Object[] objs = quotedPriceServer.getQpUserCostMsg(id,
					quotedPriceUserCost, pageStatus);
			// totalMoney,tzMoney,selfAllMoney,selfThisMoney,list
			if (objs[0] == null) {// 求资总额
				zongCount = 0f;
			} else {
				zongCount = Float.parseFloat(objs[0].toString());
			}
			if (objs[1] == null) {// 剩余份数
				shengyuCount = 0f;
			} else {
				shengyuCount = Float.parseFloat(objs[1].toString());
			}
			if (objs[2] == null) {// 个人投资总额
				selfAllMoney = 0f;
			} else {
				selfAllMoney = Float.parseFloat(objs[2].toString());
			}
			if (objs[3] == null) {// 个人该阶段总额
				selfThisMoney = 0f;
			} else {
				selfThisMoney = Float.parseFloat(objs[3].toString());
			}
			if (objs[4] != null) {// 个人该阶段总额
				qpUserCostList = (List<QuotedPriceUserCost>) objs[4];
			}
		}
		return "QuotedPrice_usercostdetail";
	}

	/**
	 * 添加费用申报
	 * 
	 * @return
	 */
	public String addQpCost() {
		if (quotedPriceServer.addQpCost(quotedPriceCost)) {
			errorMessage = "申请成功";
		} else {
			errorMessage = "申请失败";
		}
		this.setUrl("QuotedPrice_findQpDetailForCost.action?id="
				+ quotedPriceCost.getQpId());
		return "error";

	}

	/**
	 * 展示参与科目
	 */
	public void showCostType() {
		list = quotedPriceServer.showCostType(id);
		MKUtil.writeJSON(list);
	}

	/**
	 * 参与投资
	 * 
	 * @return
	 */
	public String addQpUserCost() {
		try {
			String msg = quotedPriceServer.addQpUserCost(quotedPriceUserCost);
			if ("true".equals(msg)) {
				errorMessage = "申请成功";
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		this.setUrl("QuotedPrice_findQpDetailForCost.action?id="
				+ quotedPriceUserCost.getQpId() + "&pageStatus=" + pageStatus);
		return "error";
	}

	/**
	 * 项目下阶段申请
	 * 
	 * @return
	 */
	public String ApplyQP() {
		String msg = quotedPriceServer.ApplyQP(id);
		if ("true".equals(msg)) {
			errorMessage = "申请成功";
		} else {
			errorMessage = msg;
		}
		this.setUrl("QuotedPrice_findQpForCost.action?cpage=" + cpage
				+ "&pageStatus=" + pageStatus);
		return "error";
	}

	/**
	 * 展示分红
	 * 
	 * @return
	 */
	public String showFh() {
		qpfhList = quotedPriceServer.getFhList(id);
		return "QuotedPrice_showFh";
	}

	public String showInvestors() {
		if (investor != null) {
			ActionContext.getContext().getSession().put("investor", investor);
		} else {// 用来保持分页时带着查询条件
			investor = (Investor) ActionContext.getContext().getSession().get(
					"investor");
		}
		Map<Integer, Object> map = quotedPriceServer.findInvestorsByCondition(
				investor, Integer.parseInt(cpage), pageSize);
		investorList = (List<Investor>) map.get(1);// 显示页的技能系数列表
		if (investorList != null & investorList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("QuotedPrice_showInvestors.action?cpage=" + cpage);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "QuotedPrice_showInvestors";
	}

	public String findQpPartner() {
		investor = quotedPriceServer.getInvestorByLogin();
		investorOfQpList = quotedPriceServer.getInvestorOfQpList(id);
		quotedPrice = quotedPriceServer.getQPById(id);
		proStatus = "no";
		if (investorOfQpList != null && investorOfQpList.size() > 0) {
			for (InvestorOfQuotedPrice iop : investorOfQpList) {
				if (iop.getRole() != null && iop.getRole().equals("组长")) {
					proStatus = "yes";// 表示已有组长
					break;
				}
			}
		}
		return "QuotedPrice_showPartners";
	}

	/**
	 * 添加投资人
	 * 
	 * @return
	 */
	public String toaddTzr() {
		// 未绑定人员处理
		if (user != null) {
			ActionContext.getContext().getSession().put("user", user);
		} else {// 用来保持分页时带着查询条件
			user = (Users) ActionContext.getContext().getSession().get("user");
		}
		Object[] object = quotedPriceServer.findAllUser(user, Integer
				.parseInt(cpage), pageSize);// 条件查询所有用户
		if (object != null && object.length > 0) {
			userList = (List<Users>) object[0];
			if (userList != null && userList.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("QuotedPrice_toaddTzr.action");
			} else {
				errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
			}
		} else {
			errorMessage = "抱歉!您查询的用户不存在或者已经与该功能绑定!";
		}
		// 已绑定人员处理
		bangUserList = quotedPriceServer.findAllBangUser();
		if (bangUserList != null && bangUserList.size() > 0) {
			count = bangUserList.size();
		}
		return "QuotedPrice_addInvestors";

	}

	/**
	 * 添加投资人
	 * 
	 * @return
	 */
	public String addTzr() {
		errorMessage = quotedPriceServer.addTzr(usersId);
		this.setUrl("QuotedPrice_showInvestors.action");
		return "error";
	}

	/**
	 * 设置投资人资格
	 */
	public void updatezg() {
		String msg = quotedPriceServer.updatezg(id, op);
		MKUtil.writeJSON(msg);
	}

	public String investorHome() {
		investor = quotedPriceServer.getInvestorByLogin();
		if (investor == null) {
			return "login";
		}
		return "investor_home";
	}

	public String showInvestorsMsg() {
		investor = quotedPriceServer.getInvestorByLogin();
		if (quotedPriceUserCost != null) {
			ActionContext.getContext().getSession().put("quotedPriceUserCost",
					quotedPriceUserCost);
		} else {// 用来保持分页时带着查询条件
			quotedPriceUserCost = (QuotedPriceUserCost) ActionContext
					.getContext().getSession().get("quotedPriceUserCost");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("tzend", end);
		} else {// 用来保持分页时带着查询条件
			end = (String) ActionContext.getContext().getSession().get("tzend");
		}
		if (start != null) {
			ActionContext.getContext().getSession().put("tzstart", start);
		} else {// 用来保持分页时带着查询条件
			start = (String) ActionContext.getContext().getSession().get(
					"tzstart");
		}
		Map<Integer, Object> map = quotedPriceServer
				.findQPriceUserCostsByCondition(quotedPriceUserCost, investor
						.getId(), Integer.parseInt(cpage), pageSize, start, end);
		qpUserCostList = (List<QuotedPriceUserCost>) map.get(1);
		if (qpUserCostList != null & qpUserCostList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("QuotedPrice_showInvestorsMsg.action");
		} else {
		}
		return "investors_infor";
	}

	public String toinmoney() {
		investor = quotedPriceServer.getInvestorByLogin();
		rechargeRecordlist = quotedPriceServer.getRechargeRecords(investor
				.getId());
		investorMonthJx = quotedPriceServer
				.saveOrGetInvestorMonthJxsById(investor.getId());
		if (investorMonthJx == null) {
			errorMessage = "对不起!您没有可充值的金额,请联系人事查看是否有工资绩效模板!";
			return "error";
		}
		// money =quotedPriceServer.getSelfJxgx(investor.getId());
		return "investors_inmoney";
	}

	public String inmoney() {
		String msg = quotedPriceServer.inmoney(id, money);
		if (msg.equals("true")) {
			errorMessage = "充值成功";
			url = "QuotedPrice_showInvestorsMsg.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 找到投资的项目
	 * 
	 * @return
	 */
	public String toSelectLeaderList() {
		quotedPriceList = quotedPriceServer.findSelectLeaderList();
		return "selectLeaderList";
	}

	/**
	 * 找到需要选组长的零件和其成员
	 * 
	 * @return
	 */
	public String toSelectLeader() {
		quotedPrice = quotedPriceServer.getQPById(id);
		investorList = quotedPriceServer.getQpInvestorList(id);
		return "selectLeader";
	}

	/**
	 * 选取组长
	 * 
	 * @return
	 */
	public String selectLeader() {
		String msg = quotedPriceServer.saveLeader(id, id2);
		if (!"true".equals(msg)) {
			errorMessage = msg;
		} else {
			errorMessage = "推选成功!";
		}
		url = "QuotedPrice_findQpPartner.action?id=" + id;
		return "error";
	}

	/**
	 * 跳往评价页面
	 * 
	 * @return
	 */
	public String toEvaluation() {
		quotedPrice = quotedPriceServer.getQPById(id);
		investorOfQp = quotedPriceServer.getInvestorOfQpById(id2);
		ieList = quotedPriceServer.findieList(id2);
		return "QuotedPrice_evaluation";
	}

	/**
	 * 评价组长
	 * 
	 * @return
	 */
	public String evaluationartner() {
		Map<Integer, Object> map = quotedPriceServer
				.evaluationartner(investorEvaluation);
		String msg = map.get(0).toString();
		if (msg.equals("true")) {
			id = Integer.parseInt(map.get(1).toString());
			url = "QuotedPrice_findQpPartner.action?id=" + id + "&id2="
					+ investorEvaluation.getIofQpId();
			errorMessage = "评价成功!";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 设置项目投标
	 * 
	 * @return
	 */
	public String tosetProTz() {
		money = quotedPriceServer.findAllMoney(id);
		moneysz = quotedPriceServer.findAllMoneysz(id);
		moneysj = quotedPriceServer.findAllMoneysj(id);
		moneyqt = quotedPriceServer.findAllMoneyqt(id);
		projectTime = quotedPriceServer.findProjectTime(id, "gzf");
		quotedPrice = quotedPriceServer.getQPById(id);
		if (quotedPrice == null || quotedPrice.getProcardStyle() == null
				|| !quotedPrice.getProcardStyle().equals("总成")) {
			errorMessage = "对不起没有找到对应的目标!";
			return "error";
		} else {
			return "QuotedPrice_setProTz";
		}
	}

	/**
	 * 
	 * @return
	 */
	public String setProTz() {
		String msg = quotedPriceServer.setProTz(quotedPrice);
		if (!msg.equals("true")) {
			errorMessage = msg;
		} else {
			errorMessage = "设置成功!";
		}
		url = "QuotedPrice_tosetProTz.action?id=" + quotedPrice.getId()
				+ "&pageStatus=" + pageStatus;
		return "error";
	}

	public void findYsFeiYong() {
		qpCostList = quotedPriceServer.findQpCostList(id, "预算申报");
		MKUtil.writeJSON(qpCostList);
	}

	public void updatefbStatus() {
		String msg = quotedPriceServer.updatefbStatus(id);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 展示成本
	 * 
	 * @return
	 */
	public String showRealCost() {
		qpCostList = quotedPriceServer.findQpCostList(id, null);
		return "QuotedPrice_showRealCost";
	}

	/**
	 * 成本折线图
	 * 
	 * @return
	 */
	public String tocbzheixan() {
		qpCostList = quotedPriceServer.findQpTrueCostList(id);
		Gson gson = new Gson();
		data = gson.toJson(qpCostList);
		return "cbzhexian";
	}

	/**
	 * 所有的外购件的已有价格
	 * 
	 * @return
	 */
	public String findAllOutPrice() {
		if (!"".equals(allId)) {
			priceList = quotedPriceServer.findAllOutPrice(allId);
		}
		return "findAllPrice";
	}

	/**
	 * 批量导入报价BOM
	 * 
	 * @return
	 */
	public String pladdquotedPrice() {
		try {
			String isbaomi = quotedPriceServer.getIsbaomiByqpId(quotedPrice.getId());
			if(isbaomi!=null&&isbaomi.equals("是")){
				errorMessage = quotedPriceServer.daorubmQuotedPrice(accessory,
						accessoryFileName, quotedPrice);
			}else{ 
				errorMessage = quotedPriceServer.pladdQuotedPrice(accessory,
						accessoryFileName, quotedPrice,productStyle);
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
		
		return "error";
	}
	
	
	public String showCardTz(){
		quotedPrice = quotedPriceServer.afindQuotedPrice(id);
		if (quotedPrice == null) {
			errorMessage = "对不起没有找到目标零件!";
			return ERROR;
		}
		list = quotedPriceServer.findQuotedPriceTz(id);
		return "quotedPriceTzs";
	}
	
	public String updateQuotedPriceTz() {
		if (this.accessory != null) {
			String fileName = accessoryFileName;
			int index = fileName.lastIndexOf(".");
			String fileType=fileName.substring(index);
			/* set upload path*/
			String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
			String realFileName2 = null;
			if(fileType.equalsIgnoreCase(".bmp")||fileType.equalsIgnoreCase(".dib")
					||fileType.equalsIgnoreCase(".gif")||fileType.equalsIgnoreCase(".jfif")
					||fileType.equalsIgnoreCase(".jpe")||fileType.equalsIgnoreCase(".jpeg")
					||fileType.equalsIgnoreCase(".jpg")||fileType.equalsIgnoreCase(".png")
					||fileType.equalsIgnoreCase(".tif")||fileType.equalsIgnoreCase(".tiff")
					||fileType.equalsIgnoreCase(".ico")){
				realFileName2="jz_"+Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
			}
				
				
			String realFilePath = "/upload/file/bjTz/"+ Util.getDateTime("yyyy-MM");;
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();// 如果不存在文件夹就创建
			}
			//保存文件
			Upload upload = new Upload();
			upload.UploadFile(accessory, fileName, realFileName,
					realFilePath, null);
			if(realFileName2!=null){
				//将图纸加盖印章
				String icon_fileRealPath = ServletActionContext
				.getServletContext().getRealPath(
				"/upload/file/yz/icon_ytwrq.png");
				//生成加章文件
				Util.markImageByIcon(icon_fileRealPath, path+"/"+realFileName, path+"/"+realFileName2);
				/* set processTemplateFile*/
			}
			quotedPriceFile.setFileName(realFileName);
//			quotedPriceFile.setFileName2(realFileName2);
			quotedPriceFile.setMonth(Util.getDateTime("yyyy-MM"));
			quotedPriceFile.setOldfileName(fileName);
			String msg = this.quotedPriceServer
					.saveQuotedPriceFile(this.quotedPriceFile, id,
							ytRadio);
			if (!msg.equals("true")) {
				errorMessage = msg;
				return ERROR;
			}
		}
		else {
			return ERROR;
		}
		return ERROR;

	}
	
	
	/**
	 * 显示工序图纸
	 * 
	 * @return
	 */
	public String showProcessTz() {
		qpInfor = quotedPriceServer.findQpInfor(id);
		list = quotedPriceServer.getProcessTz(id);
		return "quotedPriceProcessTzs";
	}
	
	/**
	 * 上传工序图纸
	 * @return
	 */
	public String updateQuotedPriceProcessTz() {
		if (this.accessory != null) {
			String fileName = accessoryFileName;
			int index = fileName.lastIndexOf(".");
			String fileType=fileName.substring(index);
			/* set upload path*/
			String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
			String realFileName2 = null;
			if(fileType.equalsIgnoreCase(".bmp")||fileType.equalsIgnoreCase(".dib")
					||fileType.equalsIgnoreCase(".gif")||fileType.equalsIgnoreCase(".jfif")
					||fileType.equalsIgnoreCase(".jpe")||fileType.equalsIgnoreCase(".jpeg")
					||fileType.equalsIgnoreCase(".jpg")||fileType.equalsIgnoreCase(".png")
					||fileType.equalsIgnoreCase(".tif")||fileType.equalsIgnoreCase(".tiff")
					||fileType.equalsIgnoreCase(".ico")){
				realFileName2="jz_"+Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
			}
				
				
			String realFilePath = "/upload/file/bjTz/"+ Util.getDateTime("yyyy-MM");;
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();// 如果不存在文件夹就创建
			}
			//保存文件
			Upload upload = new Upload();
			upload.UploadFile(accessory, fileName, realFileName,
					realFilePath, null);
			if(realFileName2!=null){
				//将图纸加盖印章
				String icon_fileRealPath = ServletActionContext
				.getServletContext().getRealPath(
				"/upload/file/yz/icon_ytwrq.png");
				//生成加章文件
				Util.markImageByIcon(icon_fileRealPath, path+"/"+realFileName, path+"/"+realFileName2);
				/* set processTemplateFile*/
			}
			quotedPriceFile.setFileName(realFileName);
//			quotedPriceFile.setFileName2(realFileName2);
			quotedPriceFile.setMonth(Util.getDateTime("yyyy-MM"));
			quotedPriceFile.setOldfileName(fileName);
			String msg = this.quotedPriceServer
					.saveQuotedPriceProcessFile(this.quotedPriceFile, id);
			if (!msg.equals("true")) {
				errorMessage = msg;
				return ERROR;
			}
		}
		else {
			return ERROR;
		}
		return ERROR;

	}
	/**
	 * 下载图纸
	 * @return
	 */
	public String DownloadProcessTz(){
		 quotedPriceFile = quotedPriceServer.findTzById(id);
		if (quotedPriceFile != null ) {
			String fileRealPath = ServletActionContext
			.getServletContext().getRealPath("/upload/file/bjTz")+"/"+quotedPriceFile.getMonth()
			+ "/" + quotedPriceFile.getFileName();
			File file=new File(fileRealPath);
			realPath="/upload/file/bjTz"+"/"+quotedPriceFile.getMonth()
			+ "/" + quotedPriceFile.getFileName();
			if(file.exists()){
				//返回时的名字
				gygfFileName=quotedPriceFile.getOldfileName();
				gygfFileName=gygfFileName.replaceAll("原图","YT" );
				gygfFileName=gygfFileName.replaceAll("工艺卡","GYK" );
				gygfFileName=gygfFileName.replaceAll("展开图","ZKT");
				AttendanceTowServerImpl.updateJilu(8,quotedPriceFile, quotedPriceFile.getId(), "件号:"+quotedPriceFile.getMarkId()+"第"+quotedPriceFile.getProcessNO()+ "工序" + quotedPriceFile.getProcessName()+" "+quotedPriceFile.getType()+ " 报价图纸名:"+quotedPriceFile.getOldfileName()+"(下载)");
				return "download";
			}else{
				errorMessage="您要下载的文件已经被删除,或者不存在此文件!请重试";
				return "error";
			}
		}
		errorMessage="您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}
	
	public InputStream getInputStream() throws Exception {
		//return new FileInputStream(dir);
		// 如果dir是绝对路径
		// return
		return ServletActionContext.getServletContext().getResourceAsStream(realPath);
		// 如果dir是Resource下的相对路径
	}
	/**
	 * 删除图纸
	 * @return
	 */
	public String deleteProcessTz() {
		quotedPriceFile = quotedPriceServer
				.findTzById(quotedPriceFile.getId());
		String month = null;
		String fileName = null;
		String fileName2 = null;
		if (quotedPriceFile != null) {
			fileName = quotedPriceFile.getFileName();
			fileName2 = quotedPriceFile.getFileName2();
			month = quotedPriceFile.getMonth();
		}
		boolean b = quotedPriceServer.deleteTz(quotedPriceFile
				.getId());
		if (b) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/bjTz");
			if (month != null && month.length() > 0) {
				path += "/" + month;
			}
			File file = new File(path + "/" + fileName);
			File file2 = new File(path + "/" + fileName2);
			if (file.exists()) {
				file.delete();
			}
			if (file2.exists()) {
				file2.delete();
			}
			errorMessage = "删除成功!";
		} else {
			errorMessage = "删除失败!";
		}
		if (pageStatus != null && pageStatus.equals("card")) {
			url = "QuotedPrice_showCardTz.action?id=" + id;
		} else {
			url = "QuotedPrice_showProcessTz.action?id=" + id;
		}
		return ERROR;

	}
	
	/**
	 * 查询会议纪要(绑定报价BOM)
	 * @return
	 */
	public String findjyList(){
		if (start != null) {
			ActionContext.getContext().getSession().put("jystart",
					start);
		} else {
			start = (String) ActionContext.getContext().getSession()
					.get("jystart");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("jyend",
					end);
		} else {
			end = (String) ActionContext.getContext().getSession()
					.get("jyend");
		}
		if (title != null) {
			ActionContext.getContext().getSession().put("jytitle",
					title);
		} else {
			title = (String) ActionContext.getContext().getSession()
					.get("jytitle");
		}
		if (jyContext != null) {
			ActionContext.getContext().getSession().put("jyjyContext",
					jyContext);
		} else {
			jyContext = (String) ActionContext.getContext().getSession()
					.get("jyjyContext");
		}
		if (code != null) {
			ActionContext.getContext().getSession().put("jycode",
					code);
		} else {
			code = (String) ActionContext.getContext().getSession()
					.get("jycode");
		}if (name != null) {
			ActionContext.getContext().getSession().put("jyname",
					name);
		} else {
			name = (String) ActionContext.getContext().getSession()
					.get("jyname");
		}
		qpjydclList = quotedPriceServer.findDcljyListByqpId(id,start, end,title,jyContext,code,name);
		qpjyList= quotedPriceServer.findjyListByqpId(id,start, end,title,jyContext,code,name);
		return "quotedPrice_jyList";
	}
	/**
	 * 查询会议纪要(不绑定报价BOM)
	 * @return
	 */
	public String findjyListnoqpId(){
		if (start != null) {
			ActionContext.getContext().getSession().put("noqpIdjystart",
					start);
		} else {
			start = (String) ActionContext.getContext().getSession()
			.get("noqpIdjystart");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("noqpIdjyend",
					end);
		} else {
			end = (String) ActionContext.getContext().getSession()
			.get("noqpIdjyend");
		}
		if (title != null) {
			ActionContext.getContext().getSession().put("noqpIdjytitle",
					title);
		} else {
			title = (String) ActionContext.getContext().getSession()
			.get("noqpIdjytitle");
		}
		if (jyContext != null) {
			ActionContext.getContext().getSession().put("noqpIdjyjyContext",
					jyContext);
		} else {
			jyContext = (String) ActionContext.getContext().getSession()
			.get("noqpIdjyjyContext");
		}
		if (code != null) {
			ActionContext.getContext().getSession().put("noqpIdjycode",
					code);
		} else {
			code = (String) ActionContext.getContext().getSession()
			.get("noqpIdjycode");
		}if (name != null) {
			ActionContext.getContext().getSession().put("noqpIdjyname",
					name);
		} else {
			name = (String) ActionContext.getContext().getSession()
			.get("noqpIdjyname");
		}
		qpjydclList = quotedPriceServer.findDcljyListnoqpId(start, end,title,jyContext,code,name);
		qpjyList= quotedPriceServer.findjyListnoqpId(start, end,title,jyContext,code,name);
//		return "quotedPrice_jyList";
		return "quotedPrice_jyListnoqpId";
	}
	/**
	 * 查询个人填报纪要(绑定报价BOM)
	 * @return
	 */
	public String findselfjyList(){
		qpjydclList = quotedPriceServer.findselfDcljyList("hadqpId");
		qpjyList= quotedPriceServer.findselfjyList("hadqpId");
		return "quotedPrice_jyselfList";
	}
	/**
	 * 查询个人填报纪要(不绑定报价BOM)
	 * @return
	 */
	public String findselfjyListnoqpId(){
		qpjydclList = quotedPriceServer.findselfDcljyList("noqpId");
		qpjyList= quotedPriceServer.findselfjyList("noqpId");
		return "quotedPrice_jyselfList";
	}
	/**
	 * 查询待审批纪要(绑定报价BOM)
	 * @return
	 */
	public String finddspjyList(){
		qpjydclList= quotedPriceServer.finddspjyList("hadqpId");
		qpjyList= quotedPriceServer.findAlljyList("hadqpId");
		return "quotedPrice_jydqrList";
	}
	/**
	 * 查询待审批纪要(不绑定报价BOM)
	 * @return
	 */
	public String finddspjyListnoqpId(){
		qpjydclList= quotedPriceServer.finddspjyList("noqpId");
		qpjyList= quotedPriceServer.findAlljyList("noqpId");
		return "quotedPrice_jydqrList";
	}
	
	/**
	 * 跳往添加纪要(绑定报价BOM)
	 * @return
	 */
	public String toaddjy(){
		return "quotedPrice_addjy";
	}
	/**
	 * 跳往添加纪要(不绑定报价BOM)
	 * @return
	 */
	public String toaddjynoqpId(){
		return "quotedPrice_addjynoqpId";
	}
	/**
	 * 添加进度纪要(绑定报价BOM)
	 * @return
	 */
	public String addjy(){
		String msg = quotedPriceServer.addjy(qpjy,id);
		if(msg.equals("true")){
			errorMessage="成功";
		}else{
			errorMessage = msg;
		}
		return "error";
	}
	/**
	 * 添加进度纪要(不绑定报价BOM)
	 * @return
	 */
	public String addjynoqpId(){
		String msg = quotedPriceServer.addjy(qpjy);
		if(msg.equals("true")){
			errorMessage="成功";
		}else{
			errorMessage = msg;
		}
		return "error";
	}

	/*
    * @author fy
　　* @date 2018/6/4 10:49
　　* @Description: 导入会议纪要
　　* @param []
　　* @return java.lang.String
　　* @throws
　　*/
	public  String  importjy(){
		errorMessage = quotedPriceServer.importjy(accessory);
		return "error";
	}
	/**
	 * 前往指派纪要人员
	 * @return
	 */
	public String tojydispatche(){
		qpjyUserList = quotedPriceServer.findJyUsersByJyId(id);
		return "quotedPrice_jydispatche";
	}
	/**
	 * 指派纪要人员
	 */
	public void jydispatche(){
		Object[]objs = quotedPriceServer.jydispatche(qpjyUser);
		MKUtil.writeJSON(true, objs[0].toString(), objs[1].toString());
	}
	/**
	 * 取消指派
	 */
	public void jydispatcheremove(){
		String msg = quotedPriceServer.jydispatcheremove(id);
		MKUtil.writeJSON(msg);
	}
	/**
	 * 前往填报
	 * @return
	 */
	public String totbjy(){
		qpjyd = quotedPriceServer.getQpjydById(id);
		qpjydFileList = quotedPriceServer.findjyfilesByjydId(id);
		if(qpjyd==null){
			errorMessage="没有找到对应目标";
			return "error";
		}
		return "quotedPrice_jytb";
	}
	/**
	 * 纪要填报
	 * @return
	 */
	public String jytb(){
		if (this.attachment != null&&attachment.length>0) {
			List<QuotedPricejyDetailFile> qpjdFileList = new ArrayList<QuotedPricejyDetailFile>();
			for(int i=0;i<attachment.length;i++){
				if(attachment[i]==null){
					continue;
				}
				String fileName = attachmentFileName[i];
				int index = fileName.lastIndexOf(".");
				String fileType=fileName.substring(index);
				/* set upload path*/
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
				String realFilePath = "/upload/file/jy";
				String path = ServletActionContext.getServletContext().getRealPath(
						realFilePath);
				File dir = new File(path);
				if (!dir.exists()) {
					dir.mkdirs();// 如果不存在文件夹就创建
				}
				//保存文件
				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				QuotedPricejyDetailFile qpjydFile2 = new QuotedPricejyDetailFile();
				qpjydFile2.setFileName(realFileName);
				qpjydFile2.setOldFileName(fileName);
				qpjdFileList.add(qpjydFile2);
			}
			
			String msg = this.quotedPriceServer
					.saveQuotedPricejydFile(qpjyd,qpjdFileList);
			if (!msg.equals("true")) {
				errorMessage = msg;
				return ERROR;
			}else{
//				MKUtil.writeJSON("填报成功,等待审核!");
				errorMessage ="填报成功,等待审核!";
				url="QuotedPrice_totbjy.action?id="+qpjyd.getId();
				return ERROR;
			}
		}else {
			String msg = this.quotedPriceServer
			.saveQuotedPricejydFile(qpjyd,null);
			if (!msg.equals("true")) {
//				MKUtil.writeJSON(msg);
				errorMessage = msg;
				return ERROR;
			}else{
				errorMessage ="填报成功,等待审核!";
				url="QuotedPrice_totbjy.action?id="+qpjyd.getId();
				return ERROR;
			}
		}
	}
	/**
	 * 上传纪要描述文件
	 * @return
	 */
	public String uploadJymsFile(){
		if (this.accessory != null) {
			String fileName = accessoryFileName;
			int index = fileName.lastIndexOf(".");
			String fileType=fileName.substring(index);
			/* set upload path*/
			String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")+fileType;
			String realFilePath = "/upload/file/jy";;
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();// 如果不存在文件夹就创建
			}
			qpjydFile = new QuotedPricejyDetailFile();
			qpjydFile.setFileName(realFileName);
			qpjydFile.setOldFileName(fileName);
			qpjydFile.setJydId(id);
			String msg = this.quotedPriceServer
					.saveQuotedPricejymsFile(qpjydFile);
			if (!msg.equals("true")) {
				errorMessage = msg;
			}else{
				//保存文件
				Upload upload = new Upload();
				upload.UploadFile(accessory, fileName, realFileName,
						realFilePath, null);
				errorMessage="上传成功!";
				url="QuotedPrice_toshowjyfile.action?id="+id;
			}
		}else {
			errorMessage="请选择文件";
		}
		return ERROR;
	}
	
	/**
	 * 查看纪要文件
	 * @return
	 */
	public String toshowjyfile(){
		qpjyd = quotedPriceServer.getQpjydById(id);
		qpjydFileList = quotedPriceServer.findjyfilesByjydId(id);
		return "quotedPrice_jydFile";
	}
	
	/**
	 * 下载纪要文件
	 * @return
	 */
	public String DownloadjydFile(){
		qpjydFile = quotedPriceServer.agetjydFileById(id);
		if (qpjydFile != null ) {
			String fileRealPath = ServletActionContext
			.getServletContext().getRealPath("/upload/file/jy")
			+ "/" + qpjydFile.getFileName();
			File file=new File(fileRealPath);
			realPath="/upload/file/jy"
			+ "/" + qpjydFile.getFileName();
			if(file.exists()){
				//返回时的名字
				gygfFileName=qpjydFile.getOldFileName();
				gygfFileName=gygfFileName.replaceAll("原图","YT" );
				gygfFileName=gygfFileName.replaceAll("工艺卡","GYK" );
				gygfFileName=gygfFileName.replaceAll("展开图","ZKT");
				return "download";
			}else{
				errorMessage="您要下载的文件已经被删除,或者不存在此文件!请重试";
				return "error";
			}
		}
		errorMessage="您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}
	/**
	 * 审批纪要内容
	 */
	public void spjyd(){
		String msg = quotedPriceServer.spjyd(qpjyd,op);
		if(msg.equals("true")){
			MKUtil.writeJSON("成功");
		}else{
			MKUtil.writeJSON(msg);
		}
	}
	/**
	 * 删除纪要文件
	 * @return
	 */
	public String deletejyFile() {
		String path = ServletActionContext.getServletContext().getRealPath(
		"/upload/file/jy");
		Object[] objs = quotedPriceServer.deletejyFile(id,path);
		String msg=objs[0].toString();
		if(msg.equals("true")){
			errorMessage="删除成功!";
			if("1".equals(data)){
				url="QuotedPrice_toshowjyfile.action?id="+objs[1].toString();
			}else{
				url="QuotedPrice_totbjy.action?id="+objs[1].toString();
			}
		}else{
			errorMessage=msg;
		}
		return ERROR;

	}
	/**
	 * 编辑纪要
	 * @return
	 */
	public String toEditjy(){
		qpjy = quotedPriceServer.getQpjyAndDetailById(id);
		return "quotedPrice_jyedit";
	}
	/**
	 * 添加BOM物料采购清单
	 */
	public void addqpBommatrial(){
		try {
			String msg = quotedPriceServer.addqpBommatrial(quotedPrice);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			// TODO: handle exception
			MKUtil.writeJSON(e.getMessage());
		}
	}
	/**
	 * 编辑进度纪要
	 * @return
	 */
	public String editjy(){
		String msg = quotedPriceServer.editjy(qpjy);
		if(msg.equals("true")){
			errorMessage="修改成功";
			url="QuotedPrice_toEditjy.action?id="+qpjy.getId();
		}else{
			errorMessage=msg;
		}
		return "error";
	}
	/**
	 * 删除纪要
	 * @return
	 */
	public String deletejy(){
		Object[] objs=quotedPriceServer.deletejy(id);
		String msg = objs[0].toString();
		if(msg.equals("true")){
			errorMessage="删除成功!";
			url="QuotedPrice_findjyList.action?id="+objs[1].toString();
		}else{
			errorMessage=msg;
		}
		return "error";
	}

	/*
	    * @author fy
	　　* @date 2018/6/5 10:22
	　　* @Description:删除会议纪要
	　　* @param []
	　　* @return java.lang.String
	　　* @throws
	　　*/
    public String deletehyjy(){
        Object[] objs=quotedPriceServer.deletehyjy(id);
        String msg = objs[0].toString();
        if(msg.equals("true")){
            errorMessage="删除成功!";
//            url="QuotedPrice_findjyList.action?id="+objs[1].toString();
            url="QuotedPrice_findjyListnoqpId.action";
        }else{
            errorMessage=msg;
        }
        return "error";
    }
	
	/**
	 * 根据必要的信息查询？？
	 */
	public void getQuotedPriceByCon(){
		List<QuotedPrice> quotedPriceList = quotedPriceServer.getQuotedPriceByCon(id);
		MKUtil.writeJSON(quotedPriceList);
	}
	
	/**
	 * 导出报价BOM
	 * @return
	 */
	public void exportQuotedPrice(){
		quotedPriceServer.exportQuotedPrice(id);
	}
	
	public QuotedPriceServer getQuotedPriceServer() {
		return quotedPriceServer;
	}

	public void setQuotedPriceServer(QuotedPriceServer quotedPriceServer) {
		this.quotedPriceServer = quotedPriceServer;
	}

	public QuotedPrice getQuotedPrice() {
		return quotedPrice;
	}

	public void setQuotedPrice(QuotedPrice quotedPrice) {
		this.quotedPrice = quotedPrice;
	}

	public List<QuotedPrice> getQuotedPriceList() {
		return quotedPriceList;
	}

	public void setQuotedPriceList(List<QuotedPrice> quotedPriceList) {
		this.quotedPriceList = quotedPriceList;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
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

	public QuotedProcessInfor getQpInfor() {
		return qpInfor;
	}

	public void setQpInfor(QuotedProcessInfor qpInfor) {
		this.qpInfor = qpInfor;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public ProjectTime getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(ProjectTime projectTime) {
		this.projectTime = projectTime;
	}

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
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

	public ProjectTime getBomprojectTime() {
		return bomprojectTime;
	}

	public void setBomprojectTime(ProjectTime bomprojectTime) {
		this.bomprojectTime = bomprojectTime;
	}

	public List<QuotedPriceLog> getQpLogList() {
		return qpLogList;
	}

	public void setQpLogList(List<QuotedPriceLog> qpLogList) {
		this.qpLogList = qpLogList;
	}

	public QuotedPriceLog getQuotedPriceLog() {
		return quotedPriceLog;
	}

	public void setQuotedPriceLog(QuotedPriceLog quotedPriceLog) {
		this.quotedPriceLog = quotedPriceLog;
	}

	public File getAccessory() {
		return accessory;
	}

	public void setAccessory(File accessory) {
		this.accessory = accessory;
	}

	public String getAccessoryContentType() {
		return accessoryContentType;
	}

	public void setAccessoryContentType(String accessoryContentType) {
		this.accessoryContentType = accessoryContentType;
	}

	public String getAccessoryFileName() {
		return accessoryFileName;
	}

	public void setAccessoryFileName(String accessoryFileName) {
		this.accessoryFileName = accessoryFileName;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public Integer getMoveId() {
		return moveId;
	}

	public void setMoveId(Integer moveId) {
		this.moveId = moveId;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Boolean getIsAll() {
		return isAll;
	}

	public void setIsAll(Boolean isAll) {
		this.isAll = isAll;
	}

	public String getIsneedPrice() {
		return isneedPrice;
	}

	public void setIsneedPrice(String isneedPrice) {
		this.isneedPrice = isneedPrice;
	}

	public String getZizhiisneedPrice() {
		return zizhiisneedPrice;
	}

	public void setZizhiisneedPrice(String zizhiisneedPrice) {
		this.zizhiisneedPrice = zizhiisneedPrice;
	}

	public List<OutSourcingApp> getOsaList() {
		return osaList;
	}

	public void setOsaList(List<OutSourcingApp> osaList) {
		this.osaList = osaList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public QuotedPriceCost getQuotedPriceCost() {
		return quotedPriceCost;
	}

	public void setQuotedPriceCost(QuotedPriceCost quotedPriceCost) {
		this.quotedPriceCost = quotedPriceCost;
	}

	public List<QuotedPriceCost> getQpCostList() {
		return qpCostList;
	}

	public void setQpCostList(List<QuotedPriceCost> qpCostList) {
		this.qpCostList = qpCostList;
	}

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public QuotedPriceUserCost getQuotedPriceUserCost() {
		return quotedPriceUserCost;
	}

	public void setQuotedPriceUserCost(QuotedPriceUserCost quotedPriceUserCost) {
		this.quotedPriceUserCost = quotedPriceUserCost;
	}

	public List<QuotedPriceUserCost> getQpUserCostList() {
		return qpUserCostList;
	}

	public void setQpUserCostList(List<QuotedPriceUserCost> qpUserCostList) {
		this.qpUserCostList = qpUserCostList;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Float getTzMoney() {
		return tzMoney;
	}

	public void setTzMoney(Float tzMoney) {
		this.tzMoney = tzMoney;
	}

	public Float getSelfAllMoney() {
		return selfAllMoney;
	}

	public void setSelfAllMoney(Float selfAllMoney) {
		this.selfAllMoney = selfAllMoney;
	}

	public Float getSelfThisMoney() {
		return selfThisMoney;
	}

	public void setSelfThisMoney(Float selfThisMoney) {
		this.selfThisMoney = selfThisMoney;
	}

	public QuotedPriceFenhong getQpfh() {
		return qpfh;
	}

	public void setQpfh(QuotedPriceFenhong qpfh) {
		this.qpfh = qpfh;
	}

	public List<QuotedPriceFenhong> getQpfhList() {
		return qpfhList;
	}

	public void setQpfhList(List<QuotedPriceFenhong> qpfhList) {
		this.qpfhList = qpfhList;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public List<Investor> getInvestorList() {
		return investorList;
	}

	public void setInvestorList(List<Investor> investorList) {
		this.investorList = investorList;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public List<Users> getBangUserList() {
		return bangUserList;
	}

	public void setBangUserList(List<Users> bangUserList) {
		this.bangUserList = bangUserList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer[] getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer[] usersId) {
		this.usersId = usersId;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Float getDfMoney() {
		return dfMoney;
	}

	public void setDfMoney(Float dfMoney) {
		this.dfMoney = dfMoney;
	}

	public Float getZongCount() {
		return zongCount;
	}

	public void setZongCount(Float zongCount) {
		this.zongCount = zongCount;
	}

	public Float getShengyuCount() {
		return shengyuCount;
	}

	public void setShengyuCount(Float shengyuCount) {
		this.shengyuCount = shengyuCount;
	}

	public Float getMoneysz() {
		return moneysz;
	}

	public void setMoneysz(Float moneysz) {
		this.moneysz = moneysz;
	}

	public Float getMoneysj() {
		return moneysj;
	}

	public void setMoneysj(Float moneysj) {
		this.moneysj = moneysj;
	}

	public Float getMoneyqt() {
		return moneyqt;
	}

	public void setMoneyqt(Float moneyqt) {
		this.moneyqt = moneyqt;
	}

	public RechargeRecord getRechargeRecord() {
		return rechargeRecord;
	}

	public void setRechargeRecord(RechargeRecord rechargeRecord) {
		this.rechargeRecord = rechargeRecord;
	}

	public List<RechargeRecord> getRechargeRecordlist() {
		return rechargeRecordlist;
	}

	public void setRechargeRecordlist(List<RechargeRecord> rechargeRecordlist) {
		this.rechargeRecordlist = rechargeRecordlist;
	}

	public InvestorMonthJx getInvestorMonthJx() {
		return investorMonthJx;
	}

	public void setInvestorMonthJx(InvestorMonthJx investorMonthJx) {
		this.investorMonthJx = investorMonthJx;
	}

	public List<InvestorMonthJx> getInvestorMonthJxList() {
		return investorMonthJxList;
	}

	public void setInvestorMonthJxList(List<InvestorMonthJx> investorMonthJxList) {
		this.investorMonthJxList = investorMonthJxList;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public InvestorOfQuotedPrice getInvestorOfQp() {
		return investorOfQp;
	}

	public void setInvestorOfQp(InvestorOfQuotedPrice investorOfQp) {
		this.investorOfQp = investorOfQp;
	}

	public List<InvestorOfQuotedPrice> getInvestorOfQpList() {
		return investorOfQpList;
	}

	public void setInvestorOfQpList(List<InvestorOfQuotedPrice> investorOfQpList) {
		this.investorOfQpList = investorOfQpList;
	}

	public List<InvestorEvaluation> getInvestorEvaluationList() {
		return investorEvaluationList;
	}

	public void setInvestorEvaluationList(
			List<InvestorEvaluation> investorEvaluationList) {
		this.investorEvaluationList = investorEvaluationList;
	}

	public InvestorEvaluation getInvestorEvaluation() {
		return investorEvaluation;
	}

	public void setInvestorEvaluation(InvestorEvaluation investorEvaluation) {
		this.investorEvaluation = investorEvaluation;
	}

	public List<InvestorEvaluation> getIeList() {
		return ieList;
	}

	public void setIeList(List<InvestorEvaluation> ieList) {
		this.ieList = ieList;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAllId() {
		return allId;
	}

	public void setAllId(String allId) {
		this.allId = allId;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public List<OutSourcingApp> getOutSouList() {
		return outSouList;
	}

	public void setOutSouList(List<OutSourcingApp> outSouList) {
		this.outSouList = outSouList;
	}

	public List<Bargain> getBargainList() {
		return bargainList;
	}

	public void setBargainList(List<Bargain> bargainList) {
		this.bargainList = bargainList;
	}

	public List<Performsingle> getPerformsingleList() {
		return performsingleList;
	}

	public void setPerformsingleList(List<Performsingle> performsingleList) {
		this.performsingleList = performsingleList;
	}

	public List<BarContract> getBarContractList() {
		return barContractList;
	}

	public void setBarContractList(List<BarContract> barContractList) {
		this.barContractList = barContractList;
	}

	public List<Price> getPricesList() {
		return pricesList;
	}

	public void setPricesList(List<Price> pricesList) {
		this.pricesList = pricesList;
	}

	public QuotedPriceFile getQuotedPriceFile() {
		return quotedPriceFile;
	}

	public void setQuotedPriceFile(QuotedPriceFile quotedPriceFile) {
		this.quotedPriceFile = quotedPriceFile;
	}

	public List<QuotedPriceFile> getQpFileList() {
		return qpFileList;
	}

	public void setQpFileList(List<QuotedPriceFile> qpFileList) {
		this.qpFileList = qpFileList;
	}

	public String getYtRadio() {
		return ytRadio;
	}

	public void setYtRadio(String ytRadio) {
		this.ytRadio = ytRadio;
	}

	public String getGygfFileName() {
		return gygfFileName;
	}

	public void setGygfFileName(String gygfFileName) {
		this.gygfFileName = gygfFileName;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public QuotedPricejy getQpjy() {
		return qpjy;
	}

	public void setQpjy(QuotedPricejy qpjy) {
		this.qpjy = qpjy;
	}

	

	public List<QuotedPricejy> getQpjyList() {
		return qpjyList;
	}

	public void setQpjyList(List<QuotedPricejy> qpjyList) {
		this.qpjyList = qpjyList;
	}

	public QuotedPricejyUsers getQpjyUser() {
		return qpjyUser;
	}

	public void setQpjyUser(QuotedPricejyUsers qpjyUser) {
		this.qpjyUser = qpjyUser;
	}

	public List<QuotedPricejyUsers> getQpjyUserList() {
		return qpjyUserList;
	}

	public void setQpjyUserList(List<QuotedPricejyUsers> qpjyUserList) {
		this.qpjyUserList = qpjyUserList;
	}

	public List<QuotedPricejy> getQpjydclList() {
		return qpjydclList;
	}

	public void setQpjydclList(List<QuotedPricejy> qpjydclList) {
		this.qpjydclList = qpjydclList;
	}

	public QuotedPricejyDetail getQpjyd() {
		return qpjyd;
	}

	public void setQpjyd(QuotedPricejyDetail qpjyd) {
		this.qpjyd = qpjyd;
	}

	public QuotedPricejyDetailFile getQpjydFile() {
		return qpjydFile;
	}

	public void setQpjydFile(QuotedPricejyDetailFile qpjydFile) {
		this.qpjydFile = qpjydFile;
	}

	public List<QuotedPricejyDetailFile> getQpjydFileList() {
		return qpjydFileList;
	}

	public void setQpjydFileList(List<QuotedPricejyDetailFile> qpjydFileList) {
		this.qpjydFileList = qpjydFileList;
	}

	public List<QuotedPricejyDetail> getQpjydList() {
		return qpjydList;
	}

	public void setQpjydList(List<QuotedPricejyDetail> qpjydList) {
		this.qpjydList = qpjydList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJyContext() {
		return jyContext;
	}

	public void setJyContext(String jyContext) {
		this.jyContext = jyContext;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	
	
	
	
}
