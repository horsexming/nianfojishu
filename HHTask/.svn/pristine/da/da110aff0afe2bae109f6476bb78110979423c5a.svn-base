package com.task.action.gongyi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.UserServer;
import com.task.Server.bbs.AffixServer;
import com.task.Server.gongyi.GongyiGuichengServer;
import com.task.Server.gongyi.gongxu.ProcessDataServer;
import com.task.Server.sop.ProcardTemplateServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.bbs.Affix;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.gongxu.DetectionItem;
import com.task.entity.gongyi.gongxu.HanjieGuochengCanshu;
import com.task.entity.gongyi.gongxu.HanjieJianceXiangmu;
import com.task.entity.gongyi.gongxu.HanjieZuoyeGuifan;
import com.task.entity.gongyi.gongxu.MaoliaoJishuTiaojian;
import com.task.entity.gongyi.gongxu.MaoliaoParam;
import com.task.entity.gongyi.gongxu.OperationOrder;
import com.task.entity.gongyi.gongxu.OperationOrderItem;
import com.task.entity.gongyi.gongxu.OperationStandard;
import com.task.entity.gongyi.gongxu.ProcessData;
import com.task.entity.gongyi.gongxu.ProcessGuochengCanshu;
import com.task.entity.gongyi.gongxu.ProcessPart;
import com.task.entity.gongyi.gongxu.ProcessTable;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.MKUtil;

/**
 * 工艺规程
 * 
 * @author 陈曦
 * 
 */
public class GongyiGuichengCopyAction extends ActionSupport {
	/** 工艺程序图表 */
	private ProcessTable processTable;
	/** 工序数据 */
	private ProcessData processData;
	/** 零件 */
	private ProcessPart processPart;
	/** 操作规范 */
	private OperationStandard operationStandard;
	/** 检测项目 */
	private DetectionItem detectionItem;
	/** 过程参数 */
	private ProcessGuochengCanshu processGuochengCanshu;
	/** 操作顺序 */
	private OperationOrder operationOrder;
	/** 操作顺序下检测项目 */
	private OperationOrderItem operationOrderItem;
	/** 毛料参数 */
	private MaoliaoParam maoliaoParam;
	/** 毛料技术条件 */
	private MaoliaoJishuTiaojian maoliaoJishuTiaojian;
	/** 焊接作业规范 */
	private HanjieZuoyeGuifan hanjieZuoyeGuifan;
	/** 焊接过程参数 */
	private HanjieGuochengCanshu hanjieGuochengCanshu;
	/** 焊接检测项目 */
	private HanjieJianceXiangmu hanjieJianceXiangmu;

	private String role;
	private GongyiGuicheng gongyiGuicheng;

	/** 编制工艺规程集合 待编制 已编制 */
	private List<GongyiGuicheng> gongyiGuichengListForDbz;
	private List<GongyiGuicheng> gongyiGuichengListForYbz;

	/** 校对工艺规程集合 待校对 已校对 */
	private List<GongyiGuicheng> gongyiGuichengListForDjd;
	private List<GongyiGuicheng> gongyiGuichengListForYjd;

	/** 审核工艺规程集合 待审核 已审核 */
	private List<GongyiGuicheng> gongyiGuichengListForDsh;
	private List<GongyiGuicheng> gongyiGuichengListForYsh;

	/** 审批工艺规程集合 待批准 已批准 */
	private List<GongyiGuicheng> gongyiGuichengListForDpz;
	private List<GongyiGuicheng> gongyiGuichengListForYpz;

	/** 历史版本 */
	private List<GongyiGuicheng> gongyiGuichengListForHistory;

	/** 打印预览页面 */
	private String print;

	/** 生产工序集合 */
	private List<ProcessTemplate> processList;
	private ProcessTemplate processTemplate;

	private String errorMessage;
	private String successMessage;
	/** 分页 */
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private GongyiGuichengServer gongyiGuichengServer;
	private UserServer userServer;
	private ProcardTemplateServer procardTemplateServer;
	private ProcessDataServer processDataServer;

	// -----------------------------------------------------
	// （1） 类型为File的xxx属性封装了该文件域对应的文件内容
	// （2） 类型为String的xxxFileName属性封装了该案文件域对应的文件的文件类型
	// （3） 类型为String的xxxContextType属性封装了该文件域对应的文件的类型
	private File processDataImgFile;
	private String processDataImgFileFileName;
	private String processDataImgFileContextType;
	private File processDataVideoFile;
	private String processDataVideoFileFileName;
	private String processDataVideoFileContextType;
	private String affixType;

	//
	/**
	 * 添加工艺规程记录
	 * 
	 * @return
	 */
	public String addGongyiGuicheng() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		GongyiGuicheng gongyiGuicheng = null;
		this.gongyiGuicheng.setStatus("待编制");
		this.gongyiGuicheng.setCreateUserId(user.getId());
		this.gongyiGuicheng.setCreateUserName(user.getName());
		this.gongyiGuicheng.setCreateDate(new Date());
		this.gongyiGuicheng.setScore(0);
		this.gongyiGuicheng.setTotalScore(75);
		gongyiGuicheng = gongyiGuichengServer
				.addGongyiGuicheng(this.gongyiGuicheng);
		if (gongyiGuicheng != null) {
			// 添加工序数据
			/*
			 * List<ProcessTemplate> processList =
			 * procardTemplateServer.findProcessByFkId
			 * (this.gongyiGuicheng.getJianId());
			 * 
			 * for(ProcessTemplate processTemplate : processList){ ProcessData
			 * processData=new ProcessData();
			 * processData.setProcessId(processTemplate.getId());
			 * processData.setProcessNo(processTemplate.getProcessNO());
			 * processData.setProcessName(processTemplate.getProcessName());
			 * processData.setShebeiNo(processTemplate.getShebeiNo());
			 * processData.setShebeiName(processTemplate.getShebeiName());
			 * processData.setGongyiGuichengId(this.gongyiGuicheng.getId());
			 * this.processDataServer.addProcessData(processData); }
			 */
			return "addGongyiGuicheng_success";
		} else {
			return "addGongyiGuicheng_failure";
		}
	};

	public String deleteGongyiGuicheng() {
		boolean result = this.gongyiGuichengServer
				.deleteGongyiGuicheng(this.gongyiGuicheng);
		if (result) {
			return "deleteGongyiGuicheng_success";
		} else {
			return "deleteGongyiGuicheng_failure";
		}
	};

	// 获得工艺规程更新页面
	public String getGongyiGuiChengUpdatePage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		return "getGongyiGuiChengUpdatePage_success";
	}

	// 更新工艺规程记录
	public String updateGongyiGuicheng() {
		GongyiGuicheng gongyiGuicheng = null;
		gongyiGuicheng = this.gongyiGuichengServer
				.updateGongyiGuicheng(this.gongyiGuicheng);
		if (gongyiGuicheng != null) {
			// return "updateGongyiGuicheng_success";
			MKUtil.writeJSON(true, "操作成功", gongyiGuicheng);
		} else {
			// return "updateGongyiGuicheng_failure";
			MKUtil.writeJSON(true, "操作失败", null);
		}
		return null;
	};

	// 获得工艺规程详细信息
	public String getGongyiGuichengDetail() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		return "getGongyiGuichengDetail_success";
	};

	// 获得工艺规程记录
	public String getGongyiGuichengById() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		MKUtil.writeJSON(true, "操作成功", gongyiGuicheng);
		return null;
	};

	/**
	 * 
	 * @param offset
	 * @return
	 */
	public String findAllGongyiGuicheng() {
		if ("bianzhi".equals(role)) {
			// 待编制
			this.gongyiGuichengListForDbz = this.gongyiGuichengServer
					.findAllGongyiGuichengForDbz(this.gongyiGuicheng);
			// 已编制
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForYbz(this.gongyiGuicheng, Integer
							.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForYbz = (List<GongyiGuicheng>) object[0];
				if (this.gongyiGuichengListForYbz != null
						&& this.gongyiGuichengListForYbz.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=bianzhi");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_bianzhi_success";
		} else if ("jiaodui".equals(role)) {
			// 待校对
			this.gongyiGuichengListForDjd = this.gongyiGuichengServer
					.findAllGongyiGuichengForDjd(this.gongyiGuicheng);
			// 已校对
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForYjd(this.gongyiGuicheng, Integer
							.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForYjd = (List<GongyiGuicheng>) object[0];
				if (gongyiGuichengListForYjd != null
						&& gongyiGuichengListForYjd.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=jiaodui");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_jiaodui_success";
		} else if ("shenhe".equals(role)) {
			// 待审核
			this.gongyiGuichengListForDsh = this.gongyiGuichengServer
					.findAllGongyiGuichengForDsh(this.gongyiGuicheng);
			// 已审核
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForYsh(this.gongyiGuicheng, Integer
							.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForYsh = (List<GongyiGuicheng>) object[0];
				if (gongyiGuichengListForYsh != null
						&& gongyiGuichengListForYsh.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=shenhe");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_shenhe_success";
		} else if ("pizhun".equals(role)) {
			// 待审核
			this.gongyiGuichengListForDpz = this.gongyiGuichengServer
					.findAllGongyiGuichengForDpz(this.gongyiGuicheng);
			// 已审核
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForYpz(this.gongyiGuicheng, Integer
							.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForYpz = (List<GongyiGuicheng>) object[0];
				if (gongyiGuichengListForYpz != null
						&& gongyiGuichengListForYpz.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=pizhun");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_pizhun_success";
		}
		return null;
	}

	// 查看件号为select 件号 件名
	public String findJianNumbForSelect() {
		List jianNumbList = this.gongyiGuichengServer.findJianNumbForSelect();
		if (jianNumbList != null) {
			MKUtil.writeJSON(true, "操作成功", jianNumbList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	/******************************************* 工艺规程首页 ***********************************************************/
	// 获得页面
	// 获得工艺规程
	public String getGongyiGuiChengGygcPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		if ("print".equals(print)) {
			return "getGongyiGuiChengGygcPage_print_success";
		}
		return "getGongyiGuiChengGygcPage_success";
	}

	// 更新工艺规程页面数据
	public String updateGongyiGuiChengGygcPage() {

		GongyiGuicheng gongyiGuicheng = this.gongyiGuichengServer
				.updateGongyiGuicheng(this.gongyiGuicheng);

		if (gongyiGuicheng != null) {
			// return "updateGongyiGuicheng_success";
			MKUtil.writeJSON(true, "操作成功", gongyiGuicheng);
		} else {
			// return "updateGongyiGuicheng_failure";
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	private String addGongyiGuichengHistory() {
		GongyiGuicheng gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		GongyiGuicheng gongyiGuichengTemp = gongyiGuicheng;

		Integer gongyiGuichengIdTemp = gongyiGuichengTemp.getId();
		// 复制工艺规程
		gongyiGuichengTemp.setId(null);
		gongyiGuichengTemp.setParentId(gongyiGuichengIdTemp);
		gongyiGuichengTemp.setTotalScore(75);
		GongyiGuicheng gongyiGuichengHistory = this.gongyiGuichengServer
				.addGongyiGuicheng(gongyiGuichengTemp);

		Integer gongyiGuichengIdHistory = gongyiGuichengHistory.getId();
		// 复制工序图表
		ProcessTable processTableTemp = this.processDataServer
				.getProcessTableBygongyiGuichengId(gongyiGuichengIdTemp);
		if (processTableTemp != null) {
			processTableTemp.setId(null);
			processTableTemp.setGongyiGuichengId(gongyiGuichengIdHistory);
			ProcessTable processTableHistory = this.processDataServer
					.addProcessTable(processTableTemp);
		}
		// 复制工序数据
		List<ProcessData> processDataListTemp = this.processDataServer
				.getProcessDataListBygongyiGuichengId(gongyiGuichengIdTemp);
		for (ProcessData processDataTemp : processDataListTemp) {
			Integer processDataIdTemp = processDataTemp.getId();
			processDataTemp.setId(null);
			processDataTemp.setGongyiGuichengId(gongyiGuichengIdHistory);
			ProcessData processDataHistory = this.processDataServer
					.addProcessData(processDataTemp);
			Integer processDataIdHistory = processDataHistory.getId();
			// 复制零件
			List<ProcessPart> processPartListTemp = (List<ProcessPart>) this.processDataServer
					.getProcessPartListByprocessDataId(processDataIdTemp);
			for (ProcessPart processPartTemp : processPartListTemp) {
				processPartTemp.setId(null);
				processPartTemp.setProcessDataId(processDataIdHistory);
				this.processDataServer.addProcessPart(processPartTemp);
			}
			// 复制作业规范
			List<OperationStandard> operationStandardListTemp = (List<OperationStandard>) this.processDataServer
					.findAllOperationStandardListByprocessDataId(processDataIdTemp);
			for (OperationStandard operationStandardTemp : operationStandardListTemp) {
				operationStandardTemp.setId(null);
				operationStandardTemp.setProcessDataId(processDataIdHistory);
				this.processDataServer
						.addOperationStandard(operationStandardTemp);
			}
			// 复制检测项目
			List<DetectionItem> detectionItemListTemp = (List<DetectionItem>) this.processDataServer
					.findAllDetectionItemListByprocessDataId(processDataIdTemp);
			for (DetectionItem detectionItemTemp : detectionItemListTemp) {
				detectionItemTemp.setId(null);
				detectionItemTemp.setProcessDataId(processDataIdHistory);
				this.processDataServer.addDetectionItem(detectionItemTemp);
			}
			// 复制过程参数
			List<ProcessGuochengCanshu> processGuochengCanshuListTemp = (List<ProcessGuochengCanshu>) this.processDataServer
					.getProcessGuochengCanshuByprocessDataId(processDataIdTemp);
			for (ProcessGuochengCanshu processGuochengCanshuTemp : processGuochengCanshuListTemp) {
				processGuochengCanshuTemp.setId(null);
				processGuochengCanshuTemp
						.setProcessDataId(processDataIdHistory);
				this.processDataServer
						.addProcessGuochengCanshu(processGuochengCanshuTemp);
			}

			// 复制操作顺序
			List<OperationOrder> operationOrderListTemp = (List<OperationOrder>) this.processDataServer
					.getOperationOrderListByprocessDataId(processDataIdTemp);
			for (OperationOrder operationOrderTemp : operationOrderListTemp) {
				Integer operationOrderIdTemp = operationOrderTemp.getId();
				operationOrderTemp.setId(null);
				operationOrderTemp.setProcessDataId(processDataIdHistory);
				OperationOrder operationOrderHistory = this.processDataServer
						.addOperationOrder(operationOrderTemp);
				Integer operationOrderIdHistory = operationOrderHistory.getId();
				// 复制操作顺序下检测项目
				List<OperationOrderItem> operationOrderItemListTemp = this.processDataServer
						.getOperationOrderItemListByoperationOrderId(operationOrderIdTemp);
				for (OperationOrderItem operationOrderItemTemp : operationOrderItemListTemp) {
					operationOrderItemTemp.setId(null);
					operationOrderItemTemp
							.setOperationOrderId(operationOrderIdHistory);
					this.processDataServer
							.addOperationOrderItem(operationOrderItemTemp);
				}
			}
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	/******************************************* 工艺程序图表 ****************************************************/
	// 获得工艺程序图表
	public String getGongyiGuiChengGycxtbPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		// List<ProcessTemplate> processList =
		// procardTemplateServer.findProcessByFkId(this.gongyiGuicheng.getJianId());
		// gongyiGuicheng.setProcessList(processList);
		if ("print".equals(print)) {
			return "getGongyiGuiChengGycxtbPage_print_success";
		}
		return "getGongyiGuiChengGycxtbPage_success";
	}

	public String updateGongyiGuiChengGycxtbPage() {
		// 工艺图表
		String[] dateFormats = new String[] { "yyyy-MM-dd" }; // 此处的true必须加上
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(dateFormats), true);
		String processTableParams = this.processTable.getParams();
		JSONObject processTableJson = JSONObject.fromObject(processTableParams);
		ProcessTable processTable = (ProcessTable) JSONObject.toBean(
				processTableJson, ProcessTable.class);
		if (processTable.getId() != null) {
			processTable = this.processDataServer
					.updateProcessTable(processTable);
		} else {
			processTable = this.processDataServer.addProcessTable(processTable);
		}
		// 工序数据
		String processDataParams = this.processData.getParams();
		JSONArray processDataJson = JSONArray.fromObject(processDataParams);
		List<ProcessData> processDataList = (List<ProcessData>) JSONArray
				.toCollection(processDataJson, ProcessData.class);
		if (processDataList != null) {
			int processDataListSize = processDataList.size();
			for (int i = 0; i < processDataListSize; i++) {
				ProcessData processData = processDataList.get(i);
				if (processData != null) {
					if (processData.getId() != null) {
						this.processDataServer.updateProcessData(processData);
					} else {
						processData.setEditStatus("未完成");
						this.processDataServer.addProcessData(processData);
					}
				}
			}
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	public String getProcessTableBygongyiGuichengId() {
		ProcessTable processTable = this.processDataServer
				.getProcessTableBygongyiGuichengId(this.processTable
						.getGongyiGuichengId());
		if (processTable != null) {
			MKUtil.writeJSON(true, "操作成功", processTable);
		} else {
			MKUtil.writeJSON(false, "操作成功", null);
		}
		return null;
	}

	// 获得工序数据集合
	public String getProcessDataListBygongyiGuichengId() {
		List<ProcessData> processDataList = this.processDataServer
				.getProcessDataListBygongyiGuichengId(this.gongyiGuicheng
						.getId());
		MKUtil.writeJSON(true, "操作成功", processDataList);
		return null;
	}

	// 删除工序数据
	public String deleteProcessDataById() {
		if (this.getProcessData().getId() != null) {
			this.processDataServer.deleteProcessData(this.processData);
		}
		return null;
	}

	/********************************************** 工序图表栏目分区明细· **************************************************************/
	// 获得工序图表栏目分区明细
	public String getGongyiGuiChengGxtblmfqmxPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		this.processData = processData;
		if ("print".equals(print)) {
			return "getGongyiGuiChengGxtblmfqmxPage_print_success";
		}
		return "getGongyiGuiChengGxtblmfqmxPage_success";
	}

	public String updateGongyiGuiChengGxtblmfqmxPage() {
		try {
			// 零件
			String processPartParams = this.processPart.getParams();
			JSONArray processPartJson = JSONArray.fromObject(processPartParams);
			List<ProcessPart> processPartList = (List<ProcessPart>) JSONArray
					.toCollection(processPartJson, ProcessPart.class);
			if (processPartList != null) {
				int processPartListSize = processPartList.size();
				for (int i = 0; i < processPartListSize; i++) {
					ProcessPart processPart = processPartList.get(i);
					if (processPart != null) {
						if (processPart.getId() != null) {
							this.processDataServer
									.updateProcessPart(processPart);
						} else {
							this.processDataServer.addProcessPart(processPart);
						}
					}
				}
			}
			// 操作规范
			String operationStandardParams = this.operationStandard.getParams();
			JSONArray operationStandardJson = JSONArray
					.fromObject(operationStandardParams);
			List<OperationStandard> operationStandardList = (List<OperationStandard>) JSONArray
					.toCollection(operationStandardJson,
							OperationStandard.class);
			if (operationStandardList != null) {
				int operationStandardListSize = operationStandardList.size();
				for (int i = 0; i < operationStandardListSize; i++) {
					OperationStandard operationStandard = operationStandardList
							.get(i);
					if (operationStandard != null) {
						if (operationStandard.getId() != null) {
							this.processDataServer
									.updateOperationStandard(operationStandard);
						} else {
							this.processDataServer
									.addOperationStandard(operationStandard);
						}
					}
				}
			}

			// 检查项目
			String detectionItemParams = this.detectionItem.getParams();
			JSONArray detectionItemJson = JSONArray
					.fromObject(detectionItemParams);
			List<DetectionItem> detectionItemList = (List<DetectionItem>) JSONArray
					.toCollection(detectionItemJson, DetectionItem.class);
			if (detectionItemList != null) {
				int detectionItemListSize = detectionItemList.size();
				for (int i = 0; i < detectionItemListSize; i++) {
					DetectionItem detectionItem = detectionItemList.get(i);
					if (detectionItem != null) {
						if (detectionItem.getId() != null) {
							this.processDataServer
									.updateDetectionItem(detectionItem);
						} else {
							this.processDataServer
									.addDetectionItem(detectionItem);
						}
					}
				}
			}

			// 工序数据
			String processDataParams = this.processData.getParams();
			JSONObject processDataJson = JSONObject
					.fromObject(processDataParams);
			ProcessData processData = (ProcessData) JSONObject.toBean(
					processDataJson, ProcessData.class);
			this.processDataServer.updateProcessData(processData);

			// 工艺规程
			String[] dateFormats = new String[] { "yyyy-MM-dd" }; // 此处的true必须加上
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(dateFormats), true);

			String gongyiGuichengParams = this.gongyiGuicheng.getParams();
			JSONObject gongyiGuichengJson = JSONObject
					.fromObject(gongyiGuichengParams);

			GongyiGuicheng gongyiGuicheng = (GongyiGuicheng) JSONObject.toBean(
					gongyiGuichengJson, GongyiGuicheng.class);
			this.gongyiGuichengServer.updateGongyiGuicheng(gongyiGuicheng);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	// 获得零件
	public String getProcessPartListByprocessDataId() {
		List<ProcessPart> processPartList = this.processDataServer
				.getProcessPartListByprocessDataId(this.processPart
						.getProcessDataId());
		if (processPartList != null && !processPartList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", processPartList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	// 删除零件
	public String deleteProcessPartById() {
		if (this.processPart.getId() != null) {
			this.processDataServer.deleteProcessPart(this.processPart);
		}
		return null;
	}

	// 获得作业规范
	public String getOperationStandardListByprocessDataId() {
		List<OperationStandard> processPartList = this.processDataServer
				.findAllOperationStandardListByprocessDataId(this.operationStandard
						.getProcessDataId());
		if (processPartList != null && !processPartList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", processPartList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	// 获得检查项目
	public String getDetectionItemListByprocessDataId() {
		List<DetectionItem> detectionItemList = this.processDataServer
				.findAllDetectionItemListByprocessDataId(this.detectionItem
						.getProcessDataId());
		if (detectionItemList != null && !detectionItemList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", detectionItemList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	// 过得过程参数
	public String getProcessGuochengCanshuByprocessDataId() {
		List<ProcessGuochengCanshu> processGuochengCanshuList = (List<ProcessGuochengCanshu>) this.processDataServer
				.getProcessGuochengCanshuByprocessDataId(this.processGuochengCanshu
						.getProcessDataId());
		if (processGuochengCanshuList != null
				&& !processGuochengCanshuList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", processGuochengCanshuList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	// 获得工序数据
	public String getProcessDataId() {
		ProcessData processData = this.processDataServer
				.getProcessDataById(this.processData.getId());
		if (processData != null) {
			MKUtil.writeJSON(true, "操作成功", processData);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	/********************************************** 工序说明栏区 **************************************************************/
	// 获得工序说明栏区
	public String getGongyiGuiChengGxsmlqPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		// List<ProcessTemplate> processList =
		// procardTemplateServer.findProcessByFkId(this.gongyiGuicheng.getJianId());
		// gongyiGuicheng.setProcessList(processList);
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		// 根据工序ID判断是否存在工序数据ID 不存在添加工序数据记录
		/*
		 * if(processData==null){ ProcessData processDataTemp=null;
		 * processDataTemp=new ProcessData();
		 * processDataTemp.setGongyiGuichengId
		 * (this.processData.getGongyiGuichengId());
		 * processDataTemp.setGongyiGuichengId(this.gongyiGuicheng.getId());
		 * 
		 * this.processDataServer.addProcessData(processDataTemp);
		 * this.processData=processDataTemp; }
		 */
		this.processData = processData;
		// this.processTemplate=
		// (ProcessTemplate)this.procardTemplateServer.findProcessT(this.processData.getProcessId());
		if ("print".equals(print)) {
			return "getGongyiGuiChengGxsmlqPage_print_success";
		}
		return "getGongyiGuiChengGxsmlqPage_success";
	}

	public String updateGongyiGuiChengGxsmlqPage() {

		// 工序数据
		String processDataParams = this.processData.getParams();
		JSONObject processDataJson = JSONObject.fromObject(processDataParams);
		ProcessData processData = (ProcessData) JSONObject.toBean(
				processDataJson, ProcessData.class);
		this.processDataServer.updateProcessData(processData);

		// 操作规范
		String operationStandardParams = this.operationStandard.getParams();
		JSONArray operationStandardJson = JSONArray
				.fromObject(operationStandardParams);
		List<OperationStandard> operationStandardList = (List<OperationStandard>) JSONArray
				.toCollection(operationStandardJson, OperationStandard.class);
		if (operationStandardList != null) {
			int operationStandardListSize = operationStandardList.size();
			for (int i = 0; i < operationStandardListSize; i++) {
				OperationStandard operationStandard = operationStandardList
						.get(i);
				if (operationStandard != null) {
					if (operationStandard.getId() != null) {
						this.processDataServer
								.updateOperationStandard(operationStandard);
					} else {
						this.processDataServer
								.addOperationStandard(operationStandard);
					}
				}
			}
		}

		// 检查项目
		String detectionItemParams = this.detectionItem.getParams();
		JSONArray detectionItemJson = JSONArray.fromObject(detectionItemParams);
		List<DetectionItem> detectionItemList = (List<DetectionItem>) JSONArray
				.toCollection(detectionItemJson, DetectionItem.class);
		if (detectionItemList != null) {
			int detectionItemListSize = detectionItemList.size();
			for (int i = 0; i < detectionItemListSize; i++) {
				DetectionItem detectionItem = detectionItemList.get(i);
				if (detectionItem != null) {
					if (detectionItem.getId() != null) {
						this.processDataServer
								.updateDetectionItem(detectionItem);
					} else {
						this.processDataServer.addDetectionItem(detectionItem);
					}
				}
			}
		}
		// 过程参数
		String processGuochengCanshuParams = this.processGuochengCanshu
				.getParams();
		JSONArray processGuochengCanshuJson = JSONArray
				.fromObject(processGuochengCanshuParams);
		List<ProcessGuochengCanshu> processGuochengCanshuList = (List<ProcessGuochengCanshu>) JSONArray
				.toCollection(processGuochengCanshuJson,
						ProcessGuochengCanshu.class);
		for (ProcessGuochengCanshu processGuochengCanshu : processGuochengCanshuList) {
			if (processGuochengCanshu != null) {
				if (processGuochengCanshu.getId() != null) {
					this.processDataServer
							.updateProcessGuochengCanshu(processGuochengCanshu);
				} else {
					this.processDataServer
							.addProcessGuochengCanshu(processGuochengCanshu);
				}
			}
		}

		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	/********************************************** 工序说明栏区a4 **************************************************************/
	// 获得工序图表栏目分区明细
	public String getGongyiGuiChengGxsmlqa4Page() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		this.processData = processData;
		if ("print".equals(print)) {
			return "getGongyiGuiChengGxsmlqa4Page_print_success";
		}
		return "getGongyiGuiChengGxsmlqa4Page_success";
	}

	public String updateGongyiGuiChengGxsmlqa4Page() {
		try {

			// 操作规范
			String operationStandardParams = this.operationStandard.getParams();
			JSONArray operationStandardJson = JSONArray
					.fromObject(operationStandardParams);
			List<OperationStandard> operationStandardList = (List<OperationStandard>) JSONArray
					.toCollection(operationStandardJson,
							OperationStandard.class);
			if (operationStandardList != null) {
				int operationStandardListSize = operationStandardList.size();
				for (int i = 0; i < operationStandardListSize; i++) {
					OperationStandard operationStandard = operationStandardList
							.get(i);
					if (operationStandard != null) {
						if (operationStandard.getId() != null) {
							this.processDataServer
									.updateOperationStandard(operationStandard);
						} else {
							this.processDataServer
									.addOperationStandard(operationStandard);
						}
					}
				}
			}

			// 检查项目
			String detectionItemParams = this.detectionItem.getParams();
			JSONArray detectionItemJson = JSONArray
					.fromObject(detectionItemParams);
			List<DetectionItem> detectionItemList = (List<DetectionItem>) JSONArray
					.toCollection(detectionItemJson, DetectionItem.class);
			if (detectionItemList != null) {
				int detectionItemListSize = detectionItemList.size();
				for (int i = 0; i < detectionItemListSize; i++) {
					DetectionItem detectionItem = detectionItemList.get(i);
					if (detectionItem != null) {
						if (detectionItem.getId() != null) {
							this.processDataServer
									.updateDetectionItem(detectionItem);
						} else {
							this.processDataServer
									.addDetectionItem(detectionItem);
						}
					}
				}
			}

			// 工序数据
			String processDataParams = this.processData.getParams();
			JSONObject processDataJson = JSONObject
					.fromObject(processDataParams);
			ProcessData processData = (ProcessData) JSONObject.toBean(
					processDataJson, ProcessData.class);
			this.processDataServer.updateProcessData(processData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	/********************************************** 工序说明栏区毛料 **************************************************************/
	// 获得工序图表栏目分区明细
	public String getGongyiGuiChengGxsmlqMaoliaoPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		this.processData = processData;
		if ("print".equals(print)) {
			return "getGongyiGuiChengGxsmlqMaoliaoPage_print_success";
		}
		return "getGongyiGuiChengGxsmlqMaoliaoPage_success";
	}

	public String updateGongyiGuiChengGxsmlqMaoliaoPage() {
		try {

			// 毛料参数
			String maoliaoParamParams = this.maoliaoParam.getParams();
			JSONArray maoliaoParamJson = JSONArray
					.fromObject(maoliaoParamParams);
			List<MaoliaoParam> maoliaoParamList = (List<MaoliaoParam>) JSONArray
					.toCollection(maoliaoParamJson, MaoliaoParam.class);
			for (MaoliaoParam maoliaoParam : maoliaoParamList) {
				if (maoliaoParam != null) {
					if (maoliaoParam.getId() != null) {
						this.processDataServer.updateMaoliaoParam(maoliaoParam);
					} else {
						this.processDataServer.addMaoliaoParam(maoliaoParam);
					}
				}
			}
			// 毛料技术条件
			String maoliaoJishuTiaojianParams = this.maoliaoJishuTiaojian
					.getParams();
			JSONArray maoliaoJishuTiaojianJson = JSONArray
					.fromObject(maoliaoJishuTiaojianParams);
			List<MaoliaoJishuTiaojian> maoliaoJishuTiaojianList = (List<MaoliaoJishuTiaojian>) JSONArray
					.toCollection(maoliaoJishuTiaojianJson,
							MaoliaoJishuTiaojian.class);
			for (MaoliaoJishuTiaojian maoliaoJishuTiaojian : maoliaoJishuTiaojianList) {
				if (maoliaoJishuTiaojian != null) {
					if (maoliaoJishuTiaojian.getId() != null) {
						this.processDataServer
								.updateMaoliaoJishuTiaojian(maoliaoJishuTiaojian);
					} else {
						this.processDataServer
								.addMaoliaoJishuTiaojian(maoliaoJishuTiaojian);
					}
				}
			}

			// 工序数据
			String processDataParams = this.processData.getParams();
			JSONObject processDataJson = JSONObject
					.fromObject(processDataParams);
			ProcessData processData = (ProcessData) JSONObject.toBean(
					processDataJson, ProcessData.class);
			this.processDataServer.updateProcessData(processData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	// 获得毛料参数
	public String getMaoliaoParamListByProcessDataId() {
		List<MaoliaoParam> maoliaoParamList = (List<MaoliaoParam>) this.processDataServer
				.getMaoliaoParamListByProcessDataId(this.maoliaoParam
						.getProcessDataId());
		if (maoliaoParamList != null && !maoliaoParamList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", maoliaoParamList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	// 获得毛料技术参数
	public String getMaoliaoJishuTiaojianListByProcessDataId() {
		List<MaoliaoJishuTiaojian> maoliaoJishuTiaojianList = (List<MaoliaoJishuTiaojian>) this.processDataServer
				.getMaoliaoJishuTiaojianListByProcessDataId(this.maoliaoJishuTiaojian
						.getProcessDataId());
		if (maoliaoJishuTiaojianList != null
				&& !maoliaoJishuTiaojianList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", maoliaoJishuTiaojianList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	/********************************************** 工序说明栏区焊接 **************************************************************/
	// 获得工序图表栏目分区明细
	public String getGongyiGuiChengGxsmlqHanjiePage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		this.processData = processData;
		if ("print".equals(print)) {
			return "getGongyiGuiChengGxsmlqHanjiePage_print_success";
		}
		return "getGongyiGuiChengGxsmlqHanjiePage_success";
	}

	public String updateGongyiGuiChengGxsmlqHanjiePage() {
		try {

			// 焊接作业规范
			String hanjieZuoyeGuifanParams = this.hanjieZuoyeGuifan.getParams();
			JSONArray hanjieZuoyeGuifanJson = JSONArray
					.fromObject(hanjieZuoyeGuifanParams);
			List<HanjieZuoyeGuifan> hanjieZuoyeGuifanList = (List<HanjieZuoyeGuifan>) JSONArray
					.toCollection(hanjieZuoyeGuifanJson,
							HanjieZuoyeGuifan.class);
			for (HanjieZuoyeGuifan hanjieZuoyeGuifan : hanjieZuoyeGuifanList) {
				if (hanjieZuoyeGuifan != null) {
					if (hanjieZuoyeGuifan.getId() != null) {
						this.processDataServer
								.updateHanjieZuoyeGuifan(hanjieZuoyeGuifan);
					} else {
						this.processDataServer
								.addHanjieZuoyeGuifan(hanjieZuoyeGuifan);
					}
				}
			}
			// 焊接过程参数
			String hanjieGuochengCanshuParams = this.hanjieGuochengCanshu
					.getParams();
			JSONObject hanjieGuochengCanshuJson = JSONObject
					.fromObject(hanjieGuochengCanshuParams);
			HanjieGuochengCanshu hanjieGuochengCanshu = (HanjieGuochengCanshu) JSONObject
					.toBean(hanjieGuochengCanshuJson,
							HanjieGuochengCanshu.class);
			if (hanjieGuochengCanshu.getId() != null) {
				this.processDataServer
						.updateHanjieGuochengCanshu(hanjieGuochengCanshu);
			} else {
				this.processDataServer
						.addHanjieGuochengCanshu(hanjieGuochengCanshu);
			}

			// 检测项目
			String hanjieJianceXiangmuParams = this.hanjieJianceXiangmu
					.getParams();
			JSONArray hanjieJianceXiangmuJson = JSONArray
					.fromObject(hanjieJianceXiangmuParams);
			List<HanjieJianceXiangmu> hanjieJianceXiangmuList = (List<HanjieJianceXiangmu>) JSONArray
					.toCollection(hanjieJianceXiangmuJson,
							HanjieJianceXiangmu.class);
			for (HanjieJianceXiangmu hanjieJianceXiangmu : hanjieJianceXiangmuList) {
				if (hanjieJianceXiangmu != null) {
					if (hanjieJianceXiangmu.getId() != null) {
						this.processDataServer
								.updateHanjieJianceXiangmu(hanjieJianceXiangmu);
					} else {
						this.processDataServer
								.addHanjieJianceXiangmu(hanjieJianceXiangmu);
					}
				}
			}

			// 工序数据
			String processDataParams = this.processData.getParams();
			JSONObject processDataJson = JSONObject
					.fromObject(processDataParams);
			ProcessData processData = (ProcessData) JSONObject.toBean(
					processDataJson, ProcessData.class);
			this.processDataServer.updateProcessData(processData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	public String getHanjieZuoyeGuifanListByProcessDataId() {
		List<HanjieZuoyeGuifan> hanjieZuoyeGuifanList = (List<HanjieZuoyeGuifan>) this.processDataServer
				.getHanjieZuoyeGuifanListByProcessDataId(this.hanjieZuoyeGuifan
						.getProcessDataId());
		if (hanjieZuoyeGuifanList != null && !hanjieZuoyeGuifanList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", hanjieZuoyeGuifanList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	public String getHanjieGuochengCanshuByProcessDataId() {
		HanjieGuochengCanshu hanjieGuochengCanshu = (HanjieGuochengCanshu) this.processDataServer
				.getHanjieGuochengCanshuByProcessDataId(this.hanjieGuochengCanshu
						.getProcessDataId());
		if (hanjieGuochengCanshu != null) {
			MKUtil.writeJSON(true, "操作成功", hanjieGuochengCanshu);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	public String getHanjieJianceXiangmuListByProcessDataId() {
		List<HanjieJianceXiangmu> hanjieJianceXiangmuList = (List<HanjieJianceXiangmu>) this.processDataServer
				.getHanjieJianceXiangmuListByProcessDataId(this.hanjieJianceXiangmu
						.getProcessDataId());
		if (hanjieJianceXiangmuList != null
				&& !hanjieJianceXiangmuList.isEmpty()) {
			MKUtil.writeJSON(true, "操作成功", hanjieJianceXiangmuList);
		} else {
			MKUtil.writeJSON(false, "操作失败", null);
		}
		return null;
	}

	/********************************************** 检验项目栏区 **************************************************************/
	// 获得检验项目栏区
	public String getGongyiGuiChengJyxmlqPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// 获得工序信息
		ProcessData processData = this.processDataServer
				.getProcessDataBygongyiGuichengIdandprocessId(this.processData
						.getGongyiGuichengId(), this.processData.getId());

		this.processData = processData;
		if ("print".equals(print)) {
			return "getGongyiGuiChengJyxmlqPage_print_success";
		}
		return "getGongyiGuiChengJyxmlqPage_success";
	}

	public String updateGongyiGuiChengJyxmlqPage() {
		// 检验图表数据

		// 操作顺序
		// 检查项目
		String operationOrderParams = this.operationOrder.getParams();
		JSONArray operationOrderJson = JSONArray
				.fromObject(operationOrderParams);

		JsonConfig jsonConfig = new JsonConfig();// 参数设置
		jsonConfig.setRootClass(OperationOrder.class);// 设置array中的对象类型
		Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("operationOrderItemList", OperationOrderItem.class);
		jsonConfig.setClassMap(classMap);

		List<OperationOrder> operationOrderList = (List<OperationOrder>) JSONArray
				.toCollection(operationOrderJson, jsonConfig);
		if (operationOrderList != null) {
			int operationOrderListSize = operationOrderList.size();
			for (int i = 0; i < operationOrderListSize; i++) {
				OperationOrder operationOrder = operationOrderList.get(i);
				if (operationOrder != null) {
					if (operationOrder.getId() != null) {
						this.processDataServer
								.updateOperationOrder(operationOrder);
					} else {
						this.processDataServer
								.addOperationOrder(operationOrder);
					}

					List<OperationOrderItem> operationOrderItemList = operationOrder
							.getOperationOrderItemList();
					int operationOrderItemListSize = operationOrderItemList
							.size();
					for (int j = 0; j < operationOrderItemListSize; j++) {
						OperationOrderItem operationOrderItem = operationOrderItemList
								.get(j);
						if (operationOrderItem.getId() != null) {
							this.processDataServer
									.updateOperationOrderItem(operationOrderItem);
						} else {
							operationOrderItem
									.setOperationOrderId(operationOrder.getId());
							this.processDataServer
									.addOperationOrderItem(operationOrderItem);
						}
					}
				}
			}
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	public String getOperationOrderListByprocessDataId() {
		List<OperationOrder> operationOrderList = this.processDataServer
				.getOperationOrderListByprocessDataId(this.operationOrder
						.getProcessDataId());
		for (OperationOrder operationOrder : operationOrderList) {
			List<OperationOrderItem> operationOrderItemList = this.processDataServer
					.getOperationOrderItemListByoperationOrderId(operationOrder
							.getId());
			operationOrder.setOperationOrderItemList(operationOrderItemList);
		}

		MKUtil.writeJSON(true, "操作成功", operationOrderList);

		return null;
	}

	// 删除操作顺序
	public String deleteOperationOrderById() {
		if (this.operationOrder.getId() != null) {
			this.processDataServer.deleteOperationOrder(this.operationOrder);
		}
		return null;
	}

	// 删除操作顺序下的检查项目
	public String deleteOperationOrderItemById() {
		if (this.operationOrderItem.getId() != null) {
			this.processDataServer
					.deleteOperationOrderItem(this.operationOrderItem);
		}
		return null;
	}

	/*********************************************** 提交审核 **********************************************************************/
	// 提交审核
	public String submitGongyiGuicheng() {
		try {
			String processName="工艺规范审核";
			Integer epId = CircuitRunServerImpl.createProcess(processName,
					GongyiGuicheng.class,gongyiGuicheng.getId(), "status",
					"id","工艺审核流程", true,null);
//			Integer epId = CircuitRunServerImpl.createProcess(14,
//					GongyiGuicheng.class, this.gongyiGuicheng.getId(),
//					"status", "id", "工艺审核流程", false, null);
			this.gongyiGuicheng.setEpId(epId);
			this.gongyiGuicheng.setStatus("审批中");
			this.gongyiGuichengServer.updateGongyiGuicheng(gongyiGuicheng);
			return "submitGongyiGuicheng_success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 提交
	 * 
	 * @return
	 */
	public String presentGongyiGuicheng() {
		GongyiGuicheng gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		String stauts = gongyiGuicheng.getStatus();
		GongyiGuicheng gongyiGuichengTemp = new GongyiGuicheng();
		gongyiGuichengTemp.setId(gongyiGuicheng.getId());
		if (gongyiGuicheng != null) {
			if ("待编制".equals(stauts)) {
				gongyiGuichengTemp.setStatus("已编制");
				this.gongyiGuichengServer
						.updateGongyiGuicheng(gongyiGuichengTemp);
			}
			if ("已编制".equals(stauts)) {
				gongyiGuichengTemp.setStatus("已校对");
				this.gongyiGuichengServer
						.updateGongyiGuicheng(gongyiGuichengTemp);
			}
			if ("已校对".equals(stauts)) {
				gongyiGuichengTemp.setStatus("已审核");
				this.gongyiGuichengServer
						.updateGongyiGuicheng(gongyiGuichengTemp);
			}
			if ("已审核".equals(stauts)) {
				gongyiGuichengTemp.setStatus("已批准");
				this.gongyiGuichengServer
						.updateGongyiGuicheng(gongyiGuichengTemp);
			}
		}
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	public String backGongyiGuicheng() {
		// GongyiGuicheng
		// gongyiGuicheng=this.gongyiGuichengServer.getGongyiGuichengById(this.gongyiGuicheng.getId());
		GongyiGuicheng gongyiGuicheng = new GongyiGuicheng();
		gongyiGuicheng.setId(this.gongyiGuicheng.getId());
		gongyiGuicheng.setStatus("打回");
		this.gongyiGuichengServer.updateGongyiGuicheng(gongyiGuicheng);
		return null;
	}

	// 完成工序编辑
	public String finishProcessData() {
		ProcessData processData = new ProcessData();
		processData.setId(this.processData.getId());
		processData.setEditStatus("已完成");
		this.processDataServer.updateProcessData(processData);
		MKUtil.writeJSON(true, "操作成功", null);
		return null;
	}

	/**
	 * 获得工艺集合
	 * 
	 * @return
	 */
	public String findProcessList() {
		// this.gongyiGuicheng=this.gongyiGuichengServer.getGongyiGuichengById(this.gongyiGuicheng.getId());
		// List<ProcessTemplate> processList =
		// procardTemplateServer.findProcessByFkId(this.gongyiGuicheng.getJianId());
		// this.processList=processList;
		return "findProcessList_success";
	}

	/**
	 * 获得设备集合
	 * 
	 * @return
	 */
	public String getMachineList() {

		List machineList = this.processDataServer.getMachineList();
		MKUtil.writeJSON(true, "操作成功", machineList);
		return null;
	}

	/**
	 * 获得工装集合
	 * 
	 * @return
	 */

	public List getGzstoreList() {
		List<Gzstore> gzstoreList = this.processDataServer.getGzstoreList();
		MKUtil.writeJSON(true, "操作成功", gzstoreList);
		return null;
	}

	/***************************************************************************************************/
	/**
	 * 工序添加规范
	 * 
	 * @return
	 */
	public String getProcessDataAddPage() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		return "getProcessDataAddPage";
	}

	/******************************************** 附件上传 ***********************************************************/
	public String uploadProcessDataAffix() {
		JSONObject obj = new JSONObject();// 返回前端json数据
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 模块附件根目录
		String uploadRootPath = "/upload/gongyi";
		String realUploadRootPath = servletContext.getRealPath(uploadRootPath);
		// SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		// String dateStr=formatter.format(new Date());
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR); // 获取年
		int month = calendar.get(Calendar.MONTH) + 1; // 获取月份，0表示1月份
		int day = calendar.get(Calendar.DAY_OF_MONTH); // 获取当前天数
		int hour = calendar.get(Calendar.HOUR_OF_DAY); // 获取当前小时
		int minuts = calendar.get(Calendar.MINUTE); // 获取当前分钟
		int second = calendar.get(Calendar.SECOND); // 获取当前秒
		int milliSecond = calendar.get(Calendar.MILLISECOND);// 毫秒
		String yearStr = "" + year;
		String monthStr = month < 10 ? ("0" + month) : ("" + month);
		String dayStr = day < 10 ? ("0" + day) : ("" + day);
		String hourStr = hour < 10 ? ("0" + hour) : ("" + hour);
		String minutsStr = minuts < 10 ? ("0" + minuts) : ("" + minuts);
		String secondStr = second < 10 ? ("0" + second) : ("" + second);
		String milliSecondStr = milliSecond < 10 ? ("00" + milliSecond)
				: (milliSecond < 100) ? ("0" + milliSecond)
						: ("" + milliSecond);

		// 文件目录管理
		String directoryPath = yearStr + "-" + monthStr;
		String realDirectoryPath = realUploadRootPath + "//" + directoryPath;

		// 检查文件目录是否存在
		File dir = new File(realDirectoryPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 图片
		try {
			if (this.processDataImgFile != null) {
				// 文件路径
				String fileName = this.processDataImgFileFileName;
				String fileType = fileName
						.substring(fileName.lastIndexOf('.') + 1);
				String realFileName = hourStr + minutsStr + secondStr
						+ milliSecondStr + "." + fileType;
				String realFilePath = realDirectoryPath + "//" + realFileName;
				FileOutputStream fos;

				fos = new FileOutputStream(realFilePath);

				FileInputStream fis = new FileInputStream(
						this.processDataImgFile);
				byte[] buffers = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffers)) != -1) {
					fos.write(buffers, 0, len);
				}
				long fileSize = this.processDataImgFile.length();
				String serverName = request.getServerName();// 获取服务器的名字
				int serverPort = request.getServerPort();// 获取服务器的端口号
				String contextPath = request.getContextPath();// 项目根目录
				// 不含主机名 端口
				String www = "http://" + serverName + ":" + serverPort;
				String webPath = contextPath + uploadRootPath + "/"
						+ directoryPath + "/" + realFileName;
				// String
				// webPath=uploadRootPath+"/"+directoryPath+"/"+realFileName;
				// String fileUrl =www+webPath;

				// ************************备份*************************
				String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/gongyi"
						+ "//" + directoryPath;
				File beiFenfileRealPathFile = new File(beiFenfileRealPath);
				if (!beiFenfileRealPathFile.exists()) {
					beiFenfileRealPathFile.mkdirs();
				}
				String beiFenfile = "D:/WorkSpace/HHTask/WebRoot/upload/gongyi"
						+ "//" + directoryPath + "//" + realFileName;
				File beiFenfileFile = new File(beiFenfile);
				try {
					FileCopyUtils.copy(this.processDataImgFile, beiFenfileFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				ProcessData processData = new ProcessData();
				processData.setId(this.processData.getId());
				// JSONObject obj = new JSONObject();
				if ("processImg".equals(affixType)) {
					processData.setProcessImg(webPath);
					obj.put("processImg", webPath);
				} else if ("processVideo".equals(affixType)) {
					processData.setProcessVideo(webPath);
					obj.put("processVideo", webPath);
				} else if ("hanjieImg".equals(affixType)) {
					processData.setHanjieImg(webPath);
					obj.put("hanjieImg", webPath);
				} else if ("hanjieVideo".equals(affixType)) {
					processData.setHanjieVideo(webPath);
					obj.put("hanjieVideo", webPath);
				} else {
					obj.put("null", null);
				}
				this.processDataServer.updateProcessData(processData);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.renderJavaScript(response, obj);
		return null;
	}

	// -------------------------------------------------------------------------------------------------------
	/**************************************** 查看历史版本 **************************************************************/
	public String getGongyiGuichengListForHistoryByParentId() {
		this.gongyiGuichengListForHistory = (List<GongyiGuicheng>) this.gongyiGuichengServer
				.getGongyiGuichengListForHistory(this.gongyiGuicheng);

		return "getGongyiGuichengListForHistoryByParentId_success";
	}

	/**************************************** 辅助方法 **************************************************************/
	private void renderJavaScript(HttpServletResponse response, Object obj) {
		final String prefixJavaScript = "<script type=\"text/javascript\">";
		final String suffixJavaScript = "</script>";
		String content = "window.parent.uploadProcessDataAffixResend(\'"
				+ obj.toString() + "\');";
		StringBuffer sb = new StringBuffer();
		sb.append(prefixJavaScript).append(content).append(suffixJavaScript);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(sb.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询根据件号ID工序名称集合
	 * 
	 * @return
	 */
	/*
	 * public String findProcessListForSelect(){ //获得工序信息 List<ProcessTemplate>
	 * processList =
	 * procardTemplateServer.findProcessByFkId(this.gongyiGuicheng.getJianId());
	 * if(processList!=null){ List processListTemp=new ArrayList(); for(int
	 * i=0,len=processList.size();i<len;i++){ ProcessTemplate
	 * processTemplate=(ProcessTemplate)processList.get(i);
	 * com.alibaba.fastjson.JSONObject jo=new com.alibaba.fastjson.JSONObject();
	 * jo.put("id",processTemplate.getId());
	 * jo.put("processName",processTemplate.getProcessName());
	 * processListTemp.add(jo); } MKUtil.writeJSON(true, "操作成功",
	 * processListTemp); }else{ MKUtil.writeJSON(false, "操作失败", null); } return
	 * null; }
	 */
	/**
	 * 查询根据工艺规程ID工序数据集合
	 * 
	 * @return
	 */
	public String findProcessDataListForSelect() {
		// 获得工序信息
		List<ProcessData> processDataList = this.processDataServer
				.getProcessDataListBygongyiGuichengId(this.gongyiGuicheng
						.getId());
		MKUtil.writeJSON(true, "操作成功", processDataList);
		return null;
	}

	/******************************** SET GET *******************************************************/
	public GongyiGuicheng getGongyiGuicheng() {
		return gongyiGuicheng;
	}

	public void setGongyiGuicheng(GongyiGuicheng gongyiGuicheng) {
		this.gongyiGuicheng = gongyiGuicheng;
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

	public GongyiGuichengServer getGongyiGuichengServer() {
		return gongyiGuichengServer;
	}

	public void setGongyiGuichengServer(
			GongyiGuichengServer gongyiGuichengServer) {
		this.gongyiGuichengServer = gongyiGuichengServer;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public ProcardTemplateServer getProcardTemplateServer() {
		return procardTemplateServer;
	}

	public void setProcardTemplateServer(
			ProcardTemplateServer procardTemplateServer) {
		this.procardTemplateServer = procardTemplateServer;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ProcessTemplate> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessTemplate> processList) {
		this.processList = processList;
	}

	public ProcessTable getProcessTable() {
		return processTable;
	}

	public void setProcessTable(ProcessTable processTable) {
		this.processTable = processTable;
	}

	public ProcessDataServer getProcessDataServer() {
		return processDataServer;
	}

	public void setProcessDataServer(ProcessDataServer processDataServer) {
		this.processDataServer = processDataServer;
	}

	public ProcessData getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessData processData) {
		this.processData = processData;
	}

	public ProcessPart getProcessPart() {
		return processPart;
	}

	public void setProcessPart(ProcessPart processPart) {
		this.processPart = processPart;
	}

	public OperationStandard getOperationStandard() {
		return operationStandard;
	}

	public void setOperationStandard(OperationStandard operationStandard) {
		this.operationStandard = operationStandard;
	}

	public DetectionItem getDetectionItem() {
		return detectionItem;
	}

	public void setDetectionItem(DetectionItem detectionItem) {
		this.detectionItem = detectionItem;
	}

	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

	public OperationOrder getOperationOrder() {
		return operationOrder;
	}

	public void setOperationOrder(OperationOrder operationOrder) {
		this.operationOrder = operationOrder;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForDbz() {
		return gongyiGuichengListForDbz;
	}

	public void setGongyiGuichengListForDbz(
			List<GongyiGuicheng> gongyiGuichengListForDbz) {
		this.gongyiGuichengListForDbz = gongyiGuichengListForDbz;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForYbz() {
		return gongyiGuichengListForYbz;
	}

	public void setGongyiGuichengListForYbz(
			List<GongyiGuicheng> gongyiGuichengListForYbz) {
		this.gongyiGuichengListForYbz = gongyiGuichengListForYbz;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForDjd() {
		return gongyiGuichengListForDjd;
	}

	public void setGongyiGuichengListForDjd(
			List<GongyiGuicheng> gongyiGuichengListForDjd) {
		this.gongyiGuichengListForDjd = gongyiGuichengListForDjd;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForYjd() {
		return gongyiGuichengListForYjd;
	}

	public void setGongyiGuichengListForYjd(
			List<GongyiGuicheng> gongyiGuichengListForYjd) {
		this.gongyiGuichengListForYjd = gongyiGuichengListForYjd;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForDpz() {
		return gongyiGuichengListForDpz;
	}

	public void setGongyiGuichengListForDpz(
			List<GongyiGuicheng> gongyiGuichengListForDpz) {
		this.gongyiGuichengListForDpz = gongyiGuichengListForDpz;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForYpz() {
		return gongyiGuichengListForYpz;
	}

	public void setGongyiGuichengListForYpz(
			List<GongyiGuicheng> gongyiGuichengListForYpz) {
		this.gongyiGuichengListForYpz = gongyiGuichengListForYpz;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForDsh() {
		return gongyiGuichengListForDsh;
	}

	public void setGongyiGuichengListForDsh(
			List<GongyiGuicheng> gongyiGuichengListForDsh) {
		this.gongyiGuichengListForDsh = gongyiGuichengListForDsh;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForYsh() {
		return gongyiGuichengListForYsh;
	}

	public void setGongyiGuichengListForYsh(
			List<GongyiGuicheng> gongyiGuichengListForYsh) {
		this.gongyiGuichengListForYsh = gongyiGuichengListForYsh;
	}

	public File getProcessDataImgFile() {
		return processDataImgFile;
	}

	public void setProcessDataImgFile(File processDataImgFile) {
		this.processDataImgFile = processDataImgFile;
	}

	public String getProcessDataImgFileFileName() {
		return processDataImgFileFileName;
	}

	public void setProcessDataImgFileFileName(String processDataImgFileFileName) {
		this.processDataImgFileFileName = processDataImgFileFileName;
	}

	public String getProcessDataImgFileContextType() {
		return processDataImgFileContextType;
	}

	public void setProcessDataImgFileContextType(
			String processDataImgFileContextType) {
		this.processDataImgFileContextType = processDataImgFileContextType;
	}

	public File getProcessDataVideoFile() {
		return processDataVideoFile;
	}

	public void setProcessDataVideoFile(File processDataVideoFile) {
		this.processDataVideoFile = processDataVideoFile;
	}

	public String getProcessDataVideoFileFileName() {
		return processDataVideoFileFileName;
	}

	public void setProcessDataVideoFileFileName(
			String processDataVideoFileFileName) {
		this.processDataVideoFileFileName = processDataVideoFileFileName;
	}

	public String getProcessDataVideoFileContextType() {
		return processDataVideoFileContextType;
	}

	public void setProcessDataVideoFileContextType(
			String processDataVideoFileContextType) {
		this.processDataVideoFileContextType = processDataVideoFileContextType;
	}

	public void setGongyiGuichengListForHistory(
			List<GongyiGuicheng> gongyiGuichengListForHistory) {
		this.gongyiGuichengListForHistory = gongyiGuichengListForHistory;
	}

	public OperationOrderItem getOperationOrderItem() {
		return operationOrderItem;
	}

	public void setOperationOrderItem(OperationOrderItem operationOrderItem) {
		this.operationOrderItem = operationOrderItem;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForHistory() {
		return gongyiGuichengListForHistory;
	}

	public MaoliaoParam getMaoliaoParam() {
		return maoliaoParam;
	}

	public void setMaoliaoParam(MaoliaoParam maoliaoParam) {
		this.maoliaoParam = maoliaoParam;
	}

	public MaoliaoJishuTiaojian getMaoliaoJishuTiaojian() {
		return maoliaoJishuTiaojian;
	}

	public void setMaoliaoJishuTiaojian(
			MaoliaoJishuTiaojian maoliaoJishuTiaojian) {
		this.maoliaoJishuTiaojian = maoliaoJishuTiaojian;
	}

	public HanjieZuoyeGuifan getHanjieZuoyeGuifan() {
		return hanjieZuoyeGuifan;
	}

	public void setHanjieZuoyeGuifan(HanjieZuoyeGuifan hanjieZuoyeGuifan) {
		this.hanjieZuoyeGuifan = hanjieZuoyeGuifan;
	}

	public HanjieGuochengCanshu getHanjieGuochengCanshu() {
		return hanjieGuochengCanshu;
	}

	public void setHanjieGuochengCanshu(
			HanjieGuochengCanshu hanjieGuochengCanshu) {
		this.hanjieGuochengCanshu = hanjieGuochengCanshu;
	}

	public HanjieJianceXiangmu getHanjieJianceXiangmu() {
		return hanjieJianceXiangmu;
	}

	public void setHanjieJianceXiangmu(HanjieJianceXiangmu hanjieJianceXiangmu) {
		this.hanjieJianceXiangmu = hanjieJianceXiangmu;
	}

	public ProcessGuochengCanshu getProcessGuochengCanshu() {
		return processGuochengCanshu;
	}

	public void setProcessGuochengCanshu(
			ProcessGuochengCanshu processGuochengCanshu) {
		this.processGuochengCanshu = processGuochengCanshu;
	}

	public String getAffixType() {
		return affixType;
	}

	public void setAffixType(String affixType) {
		this.affixType = affixType;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}
}
