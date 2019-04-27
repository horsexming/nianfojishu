package com.task.action.gongyi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.UserServer;
import com.task.Server.gongyi.GongyiGuichengServer;
import com.task.Server.gongyi.gongxu.ProcessDataServer;
import com.task.Server.gongyi.score.GongyiGuichengScoreServer;
import com.task.Server.sop.ProcardTemplateServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.gongyi.GongyiGuicheng;
import com.task.entity.gongyi.GongyiGuichengAffix;
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
import com.task.entity.gongyi.score.GongyiGuichengScoreItem;
import com.task.entity.gongyi.vo.GongyiGuichengVo;
import com.task.entity.gzbj.Gzstore;
import com.task.entity.project.QuotedPrice;
import com.task.entity.sop.ProcessTemplate;
import com.task.util.MKUtil;

/**
 * 工艺规程
 * 
 * @author 陈曦
 * 
 */
public class GongyiGuichengAction extends ActionSupport {
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
	private Integer screenId;
	private GongyiGuicheng gongyiGuicheng;
	private GongyiGuichengVo gongyiGuichengVo;
	private List<GongyiGuichengVo> ggVoList;
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

	/** 所有的工艺规程记录 */
	private List<GongyiGuicheng> gongyiGuichengListForAll;

	/** 所有的工艺规程记录 FOR 看板 */
	private List<GongyiGuicheng> gongyiGuichengListForKanban;

	/** 工艺规程附件 */
	private GongyiGuichengAffix gongyiGuichengAffix;
	private List<GongyiGuichengAffix> gongyiGuichengAffixList;

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
	private GongyiGuichengScoreServer gongyiGuichengScoreServer;
	private List<ProcessData> processDataList;

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
	// 报价bom的rootId
	private Integer rootId;
	// 类型（试制,批产）
	private String productStyle;
	// 组装bom时将要参与组装的零件的id
	private Integer id;
	// 组装bom时被选中的零件的id
	private Integer pid;
	private Integer moveId;
	private Integer targetId;

	//
	/**
	 * 添加工艺规程记录
	 * 
	 * @return
	 */
	public String addGongyiGuicheng() {
		Users user = (Users) ActionContext.getContext().getSession().get(
				TotalDao.users);// 获得登录用户信息
		List<String> jianNums = gongyiGuichengServer.findJianNumbsAll();
		if (gongyiGuicheng.getJianNumb() != null
				&& jianNums.contains(gongyiGuicheng.getJianNumb())) {
			errorMessage = "件号已存在，添加失败";
			return "addGongyiGuicheng_failure";
		}
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

	/**
	 * 用报价bom生成工艺编制系统的bom
	 * 
	 * @return
	 */
	public String createCraftBom() {
		boolean b = gongyiGuichengServer.addCraftBom(rootId);
		String result = null;
		if (b) {
			result = "<script type='text/javascript'>alert('已生成工艺控制bom！');"
					+ "window.location.href='QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus=all'</script>";
		} else {
			result = "<script type='text/javascript'>alert('工艺控制bom生成失败！');"
					+ "window.location.href='QuotedPrice_findQPByCondition.action?quotedPrice.belongLayer=0&pageStatus=all'</script>";
		}

		MKUtil.writeJSON(result);
		return null;

	}

	/**
	 * 根据工艺规范id从报价系统查询预览bom的rootId
	 * 
	 * @return
	 */
	public String getBomReviewById() {
		if (gongyiGuicheng != null && gongyiGuicheng.getId() != null) {
			rootId = gongyiGuichengServer.getBomRootIdById(gongyiGuicheng
					.getId());
			if (rootId != null) {
				// String result="<script type='text/javascript'>" +
				// "window.location.href='"+ServletActionContext.getServletContext().getRealPath("/")+"System/gongyi/gongyi_guicheng_bomreview.jsp?rootId="+rootId+"'</script>";
				// MKUtil.writeJSON(result);
				return "bomReview";
			}
		}
		return null;
	}

	/**
	 * 根据件号查询工艺规程状态
	 * 
	 * @return
	 */
	public String getGongyiGuichengByjianNumb() {
		if (gongyiGuicheng != null && gongyiGuicheng.getJianNumb() != null) {
			Map<Integer, Object> map = gongyiGuichengServer
					.getGongyiGuichengByJianNumb(gongyiGuicheng.getJianNumb(),
							rootId);
			if (map != null && map.get(1) != null) {
				if (map.get(2).toString().equals("1")) {
					gongyiGuicheng = (GongyiGuicheng) map.get(1);
					// return getGongyiGuiChengGygcPage();
					return getGongyiGuichengDetail();
				} else if (map.get(2).toString().equals("0")) {
					return "gongyi_guicheng_submit";
				} else {
					String result = "<html> <div align='center'><h3><font color='red'>对不起该工艺规程目前的状态您没有权限查看！</font></h3><div></html>";
					MKUtil.writeJSON(result);
					return null;
				}
			}
		}
		return null;
	}
public void findBomForReview(){
	List list = gongyiGuichengServer.findBomForReview(id);
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
	/**
	 *通过报价系统的rootId将工艺控制bom送入sop或者lp中
	 */
	public void createSopOrLpBom() {
		Map<Integer, Object> map = gongyiGuichengServer
				.createSopOrLpBomByRootId(rootId, productStyle);
		if (map != null) {
			boolean b = Boolean.parseBoolean(map.get(1).toString());
			MKUtil.writeJSON(b, map.get(2).toString(), null);
		}
	}

	/**
	 * 自定义组装bom
	 */
	public String checkBuildBom() {
		String result = null;
		if (gongyiGuicheng.getId() != null) {
			Map<Integer, Object> map = gongyiGuichengServer
					.checkBuildById(gongyiGuicheng.getId());
			boolean b = Boolean.parseBoolean(map.get(1).toString());
			String msg = map.get(2).toString();
			if (!b) {
				result = "<script type='text/javascript'>alert('"
						+ msg
						+ "');"
						+ "window.location.href='gongyiGuichengAction!findAllGongyiGuicheng.action?role=all'</script>";
				MKUtil.writeJSON(result);
			} else {
				rootId = Integer.parseInt(map.get(3).toString());
				return "buildBom";
			}
		}
		return null;

	}

	/**
	 * 加载自定义组装BOM树
	 * 
	 * @return
	 */
	public void loadBulidBomTree() {
		if (rootId != null) {
			ggVoList = (List<GongyiGuichengVo>) ActionContext.getContext()
					.getSession().get("bulidBOM" + rootId);
			if (ggVoList == null) {// 第一次加载
				gongyiGuicheng = gongyiGuichengServer
						.getGongyiGuichengById(rootId);
				if (gongyiGuicheng != null) {
					gongyiGuichengVo = new GongyiGuichengVo(gongyiGuicheng);
					gongyiGuichengVo.setId(1);
					gongyiGuichengVo.setRootId(rootId);
					gongyiGuichengVo.setBelongLayer(1);
					ggVoList = new ArrayList<GongyiGuichengVo>();
					ggVoList.add(gongyiGuichengVo);
					ActionContext.getContext().getSession().put(
							"bulidBOM" + rootId, ggVoList);
					MKUtil.writeJSON(ggVoList);
				}
			} else {
				MKUtil.writeJSON(ggVoList);
			}
		}
	}

	/**
	 * 自由组装使用的list
	 * 
	 * @return
	 */
	public String findGongyiGuichengList() {
		if (gongyiGuicheng == null) {
			gongyiGuicheng = (GongyiGuicheng) ActionContext.getContext()
					.getSession().get("gongyiGuichengBulid");
			if (gongyiGuicheng == null) {
				gongyiGuicheng = new GongyiGuicheng();
				gongyiGuicheng.setStatus("已批准");
			} else {
				gongyiGuicheng.setStatus("已批准");
				if (gongyiGuicheng.getProcardStyle() != null
						&& gongyiGuicheng.getProcardStyle().endsWith("")) {
					gongyiGuicheng.setProcardStyle(null);
				}
			}
		} else {
			if (gongyiGuicheng.getProcardStyle() != null
					&& (gongyiGuicheng.getProcardStyle().equals("") || gongyiGuicheng
							.getProcardStyle().equals("请选择"))) {
				gongyiGuicheng.setProcardStyle(null);
			}
			ActionContext.getContext().getSession().put("gongyiGuichengBulid",
					gongyiGuicheng);
		}
		Object[] os = gongyiGuichengServer.findAllGongyiGuichengForAll(
				gongyiGuicheng, Integer.parseInt(cpage), pageSize,
				" procardStyle!='总成'");
		gongyiGuichengListForAll = (List<GongyiGuicheng>) os[0];
		int count = (Integer) os[1];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		this.setUrl("gongyiGuichengAction!findGongyiGuichengList.action");
		return "buildBOMList";
	}

	/**
	 * 参与组装Bom树
	 */
	public void bulidBomTree() {
		ggVoList = (List<GongyiGuichengVo>) ActionContext.getContext()
				.getSession().get("bulidBOM" + rootId);
		if (id != null && pid != null && rootId != null && ggVoList != null
				&& ggVoList.size() > 0) {
			boolean b = false;
			Integer belongLayer = 1;
			Integer maxId = 0;
			for (GongyiGuichengVo ggVo : ggVoList) {
				if (ggVo.getGid().equals(id) && ggVo.getFatherId() != null
						&& ggVo.getFatherId().equals(pid)) {
					MKUtil.writeJSON(false, "参与组装失败,不可再同一零件下添加添加同一零件", null);
					return;
				}
				if (ggVo.getId() != null && ggVo.getId().equals(pid)
						&& ggVo.getRootId().equals(rootId)) {
					belongLayer = ggVo.getBelongLayer();
					b = true;
				}
				if (ggVo.getId() >= maxId) {
					maxId = ggVo.getId();
				}
			}
			if (b) {
				gongyiGuicheng = gongyiGuichengServer.getGongyiGuichengById(id);
				if (gongyiGuicheng != null) {
					gongyiGuichengVo = new GongyiGuichengVo(gongyiGuicheng);
					maxId++;
					gongyiGuichengVo.setId(maxId);
					gongyiGuichengVo.setRootId(rootId);
					gongyiGuichengVo.setFatherId(pid);
					belongLayer++;
					gongyiGuichengVo.setBelongLayer(belongLayer);
					ggVoList.add(gongyiGuichengVo);
					ActionContext.getContext().getSession().put(
							"bulidBOM" + rootId, ggVoList);
					MKUtil.writeJSON(true, "参与组装成功!", null);
					return;
				}
			} else {
				MKUtil.writeJSON(true, "参与组装失败，您选中的零件不存在bom中!", null);
				return;
			}
		} else {
			MKUtil.writeJSON(false, "参与组装失败!", null);
			return;
		}
	}

	/**
	 * 取消参与组装Bom树
	 */
	public void outBulidBomTree() {
		ggVoList = (List<GongyiGuichengVo>) ActionContext.getContext()
				.getSession().get("bulidBOM" + rootId);
		if (id != null && rootId != null && ggVoList != null
				&& ggVoList.size() > 0) {
			List<Integer> remove = new ArrayList<Integer>();
			Integer belongLayer = 0;
			Integer maxBelongLayer = 0;
			List<GongyiGuichengVo> removeList = new ArrayList<GongyiGuichengVo>();
			for (int i = 0; i < ggVoList.size(); i++) {
				if (ggVoList.get(i).getBelongLayer() > maxBelongLayer) {
					maxBelongLayer = ggVoList.get(i).getBelongLayer();
				}
				if (ggVoList.get(i).getId().equals(id)) {
					belongLayer = ggVoList.get(i).getBelongLayer();
					remove.add(ggVoList.get(i).getId());
					removeList.add(ggVoList.get(i));
				}
			}

			if (remove.size() > 0) {
				for (int j = 0; j <= (maxBelongLayer - belongLayer); j++) {
					for (int i = 0; i < ggVoList.size(); i++) {
						if (ggVoList.get(i).getFatherId() != null
								&& remove.contains(ggVoList.get(i)
										.getFatherId())
								&& !remove.contains(ggVoList.get(i).getId())) {
							remove.add(ggVoList.get(i).getId());
							removeList.add(ggVoList.get(i));
						}
					}
				}
				ggVoList.removeAll(removeList);
				ActionContext.getContext().getSession().put(
						"bulidBOM" + rootId, ggVoList);
				MKUtil.writeJSON(true, "取消参与组装成功!", null);
				return;
			}
			// int n=-1;
			// for(int i=0;i<ggVoList.size();i++){
			// if(ggVoList.get(i).getId().equals(id)){
			// n=i;
			// break;
			// }
			// }
			// if(n>-1){
			// ggVoList.remove(n);
			// ActionContext.getContext().getSession().put("bulidBOM"+rootId,ggVoList);
			// MKUtil.writeJSON(true,"取消参与组装成功!",null);
			// return ;
			// }
		} else {
			MKUtil.writeJSON(false, "取消参与组装失败!", null);
			return;
		}
	}

	/**
	 * 移动自由组装bom树结点
	 */
	public void moveBuildBomNode() {
		ggVoList = (List<GongyiGuichengVo>) ActionContext.getContext()
				.getSession().get("bulidBOM" + rootId);
		if (ggVoList != null && ggVoList.size() > 0) {
			GongyiGuichengVo target = null;
			GongyiGuichengVo move = null;
			Integer maxBelongLayer = 0;
			for (GongyiGuichengVo ggVo : ggVoList) {
				if (ggVo.getId().equals(targetId)) {
					target = ggVo;
				} else if (ggVo.getId().equals(moveId)) {
					move = ggVo;
				}
				if (ggVo.getBelongLayer() > maxBelongLayer) {
					maxBelongLayer = ggVo.getBelongLayer();
				}
			}
			if (target == null) {
				MKUtil.writeJSON(false, "目标不存在!", null);
				return;
			}
			if (move == null) {
				MKUtil.writeJSON(false, "移动对象不存在!", null);
				return;
			}
			Integer changeBelongLayer = target.getBelongLayer() + 1
					- move.getBelongLayer();
			Integer maxMoveBelongLayer = maxBelongLayer - move.getBelongLayer();
			for (GongyiGuichengVo ggVo1 : ggVoList) {
				if (ggVo1.getId().equals(moveId)) {
					move.setFatherId(targetId);
				}
			}
			List<Integer> moveIdList = new ArrayList<Integer>();
			moveIdList.add(moveId);
			for (int i = 0; i < maxMoveBelongLayer; i++) {
				for (GongyiGuichengVo ggVo2 : ggVoList) {
					if (ggVo2.getFatherId() != null
							&& moveIdList.contains(ggVoList.get(i)
									.getFatherId())
							&& !moveIdList.contains(ggVoList.get(i).getId())) {
						moveIdList.add(ggVoList.get(i).getId());
					}
				}
			}
			for (GongyiGuichengVo ggVo3 : ggVoList) {
				if (moveIdList.contains(ggVo3.getId())) {
					ggVo3.setBelongLayer(ggVo3.getBelongLayer()
							+ changeBelongLayer);
				}
			}
			ActionContext.getContext().getSession().put("bulidBOM" + rootId,
					ggVoList);
			MKUtil.writeJSON(true, "移动成功!", null);
			return;

		}

	}

	/**
	 * 将自由组装的BOM推送进sop或lp
	 * 
	 * @param ggVoList
	 * @param productStyle
	 * @return
	 */
	public void buildBomtoProcard() {
		ggVoList = (List<GongyiGuichengVo>) ActionContext.getContext()
				.getSession().get("bulidBOM" + rootId);
		if (ggVoList != null && ggVoList.size() > 0) {
			Map<Integer, Object> map = gongyiGuichengServer.buildBomtoProcard(
					ggVoList, productStyle);
			if (map != null) {
				boolean b = Boolean.parseBoolean(map.get(1).toString());
				MKUtil.writeJSON(b, map.get(2).toString(), null);
			}
		}
	}

	/**
	 * 根据id删除工艺规程
	 * 
	 * @return
	 */
	public String deleteGongyiGuicheng() {
		gongyiGuicheng = gongyiGuichengServer
				.getGongyiGuichengById(gongyiGuicheng.getId());
		if (gongyiGuicheng == null) {
			errorMessage = "此记录已被删除！请不要继续删除！";
			return "deleteGongyiGuicheng_failure";
		}
		boolean result = this.gongyiGuichengServer
				.deleteGongyiGuicheng(this.gongyiGuicheng);
		if (result) {
			if ("all".equals(this.role)) {
				return "deleteGongyiGuicheng_all_success";
			}
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
		} else if ("all".equals(role)) {
			if (this.gongyiGuicheng != null) {
				ActionContext.getContext().getSession().put("gongyiGuicheng",
						this.gongyiGuicheng);
			} else {
				this.gongyiGuicheng = (GongyiGuicheng) ActionContext
						.getContext().getSession().get("gongyiGuicheng");
			}
			// 所有
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForAll(this.gongyiGuicheng, Integer
							.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForAll = (List<GongyiGuicheng>) object[0];
				if (gongyiGuichengListForAll != null
						&& gongyiGuichengListForAll.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=all");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_all_success";
		} else if ("kanban".equals(role)) {
			// 所有
			Object[] object = this.gongyiGuichengServer
					.findAllGongyiGuichengForKanban(this.gongyiGuicheng,
							Integer.parseInt(cpage), pageSize);
			if (object != null && object.length > 0) {
				this.gongyiGuichengListForKanban = (List<GongyiGuicheng>) object[0];
				if (this.gongyiGuichengListForKanban != null
						&& this.gongyiGuichengListForKanban.size() > 0) {
					int count = (Integer) object[1];
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this
							.setUrl("gongyiGuichengAction!findAllGongyiGuicheng.action?role=kanban");
					errorMessage = null;
				} else
					errorMessage = null;
			} else {
				errorMessage = null;
			}
			return "findAllGongyiGuicheng_kanban_success";
		}

		return null;
	}

	public String findGygcToSopOrLp() {
		if (this.gongyiGuicheng != null) {
			ActionContext.getContext().getSession().put("gongyiGuicheng",
					this.gongyiGuicheng);
		} else {
			this.gongyiGuicheng = (GongyiGuicheng) ActionContext.getContext()
					.getSession().get("gongyiGuicheng");
		}
		Object[] object = this.gongyiGuichengServer.findGygcToSopOrLp(
				this.gongyiGuicheng, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			this.gongyiGuichengListForAll = (List<GongyiGuicheng>) object[0];
			if (gongyiGuichengListForAll != null
					&& gongyiGuichengListForAll.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("gongyiGuichengAction!findGygcToSopOrLp.action");
				errorMessage = null;
			} else
				errorMessage = null;
		} else {
			errorMessage = null;
		}
		return "findAllGongyiGuicheng_all_success";
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

	/**
	 * 添加一个内容一样的工艺规范记录作为历史
	 * 
	 * @return
	 */
	public String addGongyiGuichengHistory() {
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

		// 复制打分记录
		List<GongyiGuichengScoreItem> gongyiGuichengScoreItemListTemp = (List<GongyiGuichengScoreItem>) this.gongyiGuichengScoreServer
				.getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(
						gongyiGuichengIdTemp, null);
		for (GongyiGuichengScoreItem gongyiGuichengScoreItemTemp : gongyiGuichengScoreItemListTemp) {
			gongyiGuichengScoreItemTemp.setId(null);
			gongyiGuichengScoreItemTemp
					.setGongyiGuichengId(gongyiGuichengIdHistory);
			this.gongyiGuichengScoreServer
					.addGongyiGuichengScoreItem(gongyiGuichengScoreItemTemp);
		}
		// 复制 附件
		List<GongyiGuichengAffix> gongyiGuichengAffixListTemp = (List<GongyiGuichengAffix>) this.processDataServer
				.getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId(
						gongyiGuichengIdTemp, null);
		for (GongyiGuichengAffix gongyiGuichengAffixTemp : gongyiGuichengAffixListTemp) {
			gongyiGuichengAffixTemp.setId(null);
			gongyiGuichengAffixTemp
					.setGongyiGuichengId(gongyiGuichengIdHistory);
			this.processDataServer
					.addGongyiGuichengAffix(gongyiGuichengAffixTemp);
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
			// 复制打分记录
			List<GongyiGuichengScoreItem> gongyiGuichengScoreItemListTempTemp = (List<GongyiGuichengScoreItem>) this.gongyiGuichengScoreServer
					.getGongyiGuichengScoreItemListByGongyiGuichengIdAndProcessDataId(
							gongyiGuichengIdTemp, processDataIdTemp);
			for (GongyiGuichengScoreItem gongyiGuichengScoreItemTempTemp : gongyiGuichengScoreItemListTempTemp) {
				gongyiGuichengScoreItemTempTemp.setId(null);
				gongyiGuichengScoreItemTempTemp
						.setGongyiGuichengId(gongyiGuichengIdHistory);
				gongyiGuichengScoreItemTempTemp
						.setProcessDataId(processDataIdHistory);
				this.gongyiGuichengScoreServer
						.addGongyiGuichengScoreItem(gongyiGuichengScoreItemTempTemp);
			}

			// 复制作业规范
			List<GongyiGuichengAffix> gongyiGuichengAffixListTempTemp = (List<GongyiGuichengAffix>) this.processDataServer
					.getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId(
							gongyiGuichengIdTemp, processDataIdTemp);
			for (GongyiGuichengAffix gongyiGuichengAffixTempTemp : gongyiGuichengAffixListTempTemp) {
				gongyiGuichengAffixTempTemp.setId(null);
				gongyiGuichengAffixTempTemp
						.setGongyiGuichengId(gongyiGuichengIdHistory);
				gongyiGuichengAffixTempTemp
						.setProcessDataId(processDataIdHistory);
				this.processDataServer
						.addGongyiGuichengAffix(gongyiGuichengAffixTempTemp);
			}
			/*
			 * List<OperationStandard>
			 * operationStandardListTemp=(List<OperationStandard
			 * >)this.processDataServer
			 * .findAllOperationStandardListByprocessDataId(processDataIdTemp);
			 * for(OperationStandard operationStandardTemp:
			 * operationStandardListTemp){ operationStandardTemp.setId(null);
			 * operationStandardTemp.setProcessDataId(processDataIdHistory);
			 * this
			 * .processDataServer.addOperationStandard(operationStandardTemp); }
			 */
			// 复制检测项目
			/*
			 * List<DetectionItem>
			 * detectionItemListTemp=(List<DetectionItem>)this
			 * .processDataServer.
			 * findAllDetectionItemListByprocessDataId(processDataIdTemp);
			 * for(DetectionItem detectionItemTemp: detectionItemListTemp){
			 * detectionItemTemp.setId(null);
			 * detectionItemTemp.setProcessDataId(processDataIdHistory);
			 * this.processDataServer.addDetectionItem(detectionItemTemp); }
			 */
			// 复制过程参数
			/*
			 * List<ProcessGuochengCanshu>
			 * processGuochengCanshuListTemp=(List<ProcessGuochengCanshu
			 * >)this.processDataServer
			 * .getProcessGuochengCanshuByprocessDataId(processDataIdTemp);
			 * for(ProcessGuochengCanshu processGuochengCanshuTemp:
			 * processGuochengCanshuListTemp){
			 * processGuochengCanshuTemp.setId(null);
			 * processGuochengCanshuTemp.setProcessDataId(processDataIdHistory);
			 * this
			 * .processDataServer.addProcessGuochengCanshu(processGuochengCanshuTemp
			 * ); }
			 */

			// 复制操作顺序
			/*
			 * List<OperationOrder>
			 * operationOrderListTemp=(List<OperationOrder>)
			 * this.processDataServer
			 * .getOperationOrderListByprocessDataId(processDataIdTemp);
			 * for(OperationOrder operationOrderTemp: operationOrderListTemp){
			 * Integer operationOrderIdTemp=operationOrderTemp.getId();
			 * operationOrderTemp.setId(null);
			 * operationOrderTemp.setProcessDataId(processDataIdHistory);
			 * OperationOrder
			 * operationOrderHistory=this.processDataServer.addOperationOrder
			 * (operationOrderTemp); Integer
			 * operationOrderIdHistory=operationOrderHistory.getId();
			 * //复制操作顺序下检测项目 List<OperationOrderItem>
			 * operationOrderItemListTemp=
			 * this.processDataServer.getOperationOrderItemListByoperationOrderId
			 * (operationOrderIdTemp); for(OperationOrderItem
			 * operationOrderItemTemp: operationOrderItemListTemp){
			 * operationOrderItemTemp.setId(null);
			 * operationOrderItemTemp.setOperationOrderId
			 * (operationOrderIdHistory);
			 * this.processDataServer.addOperationOrderItem
			 * (operationOrderItemTemp); } }
			 */
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
		if (this.processTable != null) {
			String processTableParams = this.processTable.getParams();
			if (processTableParams != null && !"".equals(processTableParams)) {
				JSONObject processTableJson = JSONObject
						.fromObject(processTableParams);
				ProcessTable processTable = (ProcessTable) JSONObject.toBean(
						processTableJson, ProcessTable.class);
				if (processTable != null && processTable.getId() != null) {
					processTable = this.processDataServer
							.updateProcessTable(processTable);
				} else {
					processTable = this.processDataServer
							.addProcessTable(processTable);
				}
			}
		}
		// 工序数据
		if (this.processData != null) {
			String processDataParams = this.processData.getParams();
			if (processDataParams != null && !"".equals(processDataParams)) {
				JSONArray processDataJson = JSONArray
						.fromObject(processDataParams);
				List<ProcessData> processDataList = (List<ProcessData>) JSONArray
						.toCollection(processDataJson, ProcessData.class);
				if (processDataList != null) {
					int processDataListSize = processDataList.size();
					for (int i = 0; i < processDataListSize; i++) {
						ProcessData processData = processDataList.get(i);
						if (processData != null) {
							if (processData.getId() != null) {
								this.processDataServer
										.updateProcessData(processData);
							} else {
								processData.setEditStatus("未完成");
								this.processDataServer
										.addProcessData(processData);
							}
						}
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
	@SuppressWarnings("unchecked")
	public String getProcessDataListBygongyiGuichengId() {
		List<ProcessData> processDataList = this.processDataServer
				.getProcessDataListBygongyiGuichengId(this.gongyiGuicheng
						.getId());
		MKUtil.writeJSON(true, "操作成功", processDataList);
		return null;
	}

	// 获得工序数据集合
	@SuppressWarnings("unchecked")
	public String getProcListBygoGuId() {
		this.gongyiGuicheng = this.gongyiGuichengServer
				.getGongyiGuichengById(this.gongyiGuicheng.getId());
		processDataList = this.processDataServer
				.getProcessDataListBygongyiGuichengId(this.gongyiGuicheng
						.getId());
		return "gongyi_guicheng_gycxtb2";
	}

	/***
	 * 获得在干工序信息
	 * 
	 * @return
	 */
	public String getProcessBygGygcId() {
		List<ProcessData> processDataList = this.processDataServer
				.getProcessBygGygcId(this.gongyiGuicheng.getId());
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

	/************************************************ 工艺规程附件页面 ****************************************************************************/
	public String getGongyiGuiChengUploadPage() {
		String weizhi = this.gongyiGuichengAffix.getWeizhi();
		if (!"gxsmlq".equals(weizhi)) {
			return "getGongyiGuiChengUploadPage_gygcsy_success";
		} else {
			return "getGongyiGuiChengUploadPage_success";
		}

	}

	/*********************************************** 提交审核 **********************************************************************/
	// 提交审核
	public String submitGongyiGuicheng() {
		try {
			String processName = "工艺规范审核";
			Integer epId = CircuitRunServerImpl.createProcess(processName,
					GongyiGuicheng.class, gongyiGuicheng.getId(), "status",
					"id", "工艺审核流程", true, null);
			// Integer epId = CircuitRunServerImpl.createProcess(14,
			// GongyiGuicheng.class, this.gongyiGuicheng.getId(),
			// "status", "id", "工艺审核流程", false,null);
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
			if ("打回".equals(stauts)) {
				gongyiGuichengTemp.setStatus("已编制");
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
		MKUtil.writeJSON(true, "操作成功", null);
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

	/******************************************** 附件上传 ***********************************************************/
	public String uploadProcessDataAffix2() {
		String weizhi = this.gongyiGuichengAffix.getWeizhi();
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
				// String contextPath = request.getContextPath();// 项目根目录
				String contextPath = "";// 项目根目录
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
				GongyiGuichengAffix gongyiGuichengAffix = new GongyiGuichengAffix();
				gongyiGuichengAffix.setAffixType(this.gongyiGuichengAffix
						.getAffixType());
				gongyiGuichengAffix.setUrl(webPath);
				gongyiGuichengAffix.setFileName(fileName.substring(0, fileName
						.lastIndexOf('.')));
				gongyiGuichengAffix.setFileType(fileType);
				gongyiGuichengAffix.setWeizhi(weizhi);
				gongyiGuichengAffix
						.setGongyiGuichengId(this.gongyiGuichengAffix
								.getGongyiGuichengId());
				gongyiGuichengAffix.setProcessDataId(this.gongyiGuichengAffix
						.getProcessDataId());

				GongyiGuichengAffix gongyiGuichengAffixTemp = this.processDataServer
						.addGongyiGuichengAffix(gongyiGuichengAffix);
				// gongyiGuichengAffix.setId(gongyiGuichengAffixTemp.getId());
				obj = JSONObject.fromObject(gongyiGuichengAffixTemp);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// this.renderJavaScript2(response, obj);
		if (!"gxsmlq".equals(weizhi)) {
			return "uploadProcessDataAffix2_gygcsy_success";
		} else {
			return "uploadProcessDataAffix2_success";
		}

	}

	// 附件下载
	public String downloadProcessDataAffix2() {
		GongyiGuichengAffix gongyiGuichengAffix = this.processDataServer
				.getGongyiGuichengAffixById(this.gongyiGuichengAffix.getId());
		String url = gongyiGuichengAffix.getUrl();
		String fileName = gongyiGuichengAffix.getFileName() + "."
				+ url.substring(url.lastIndexOf('.') + 1);
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		FileInputStream fis = null;
		try {
			String downloadPath = servletContext.getRealPath("/upload");
			fileName = new String(fileName.getBytes(), "ISO-8859-1");
			fis = new FileInputStream(downloadPath + "//"
					+ url.substring(url.indexOf("/gongyi")));
			int len = 0;
			byte[] buffers = new byte[1024];
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ fileName + "\"");
			while ((len = fis.read(buffers)) != -1) {
				OutputStream os = response.getOutputStream();
				os.write(buffers, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 删除工艺规程附件
	public String deleteGongyiGuichengAffix() {
		GongyiGuichengAffix gongyiGuichengAffix = this.processDataServer
				.getGongyiGuichengAffixById(this.gongyiGuichengAffix.getId());
		String weizhi = gongyiGuichengAffix.getWeizhi();
		this.processDataServer
				.deleteGongyiGuichengAffix(this.gongyiGuichengAffix);

		String url = gongyiGuichengAffix.getUrl();
		ServletContext servletContext = ServletActionContext
				.getServletContext();

		try {
			String downloadPath = servletContext.getRealPath("/upload");
			String subUrl = url.substring(url.indexOf("/gongyi"));
			String gongyiGuichengAffixUrl = downloadPath + "//" + subUrl;
			File gongyiGuichengAffixFile = new File(gongyiGuichengAffixUrl);
			if (gongyiGuichengAffixFile.isFile()
					&& gongyiGuichengAffixFile.exists()) {
				gongyiGuichengAffixFile.delete();
			}

			String gongyiGuichengAffixBeifenUrl = "D:/WorkSpace/HHTask/WebRoot/upload"
					+ "//" + subUrl;
			File gongyiGuichengAffixBeifenFile = new File(
					gongyiGuichengAffixBeifenUrl);
			if (gongyiGuichengAffixBeifenFile.isFile()
					&& gongyiGuichengAffixBeifenFile.exists()) {
				gongyiGuichengAffixBeifenFile.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		// MKUtil.writeJSON(true, "操作成功", null);
		if (!"gxsmlq".equals(weizhi)) {
			return "deleteGongyiGuichengAffix_gygcsy_success";
		} else {
			return "deleteGongyiGuichengAffix_success";
		}

	}

	/************************************** 获得工艺规程附件 ***************************************************/
	public String getGongyiGuichengAffixListByGuiyiGuichengIdAndProcessDataId() {
		List<GongyiGuichengAffix> gongyiGuichengAffixList = (List<GongyiGuichengAffix>) this.processDataServer
				.getGongyiGuichengAffixListByGongyiGuichengAffix(this.gongyiGuichengAffix);
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengAffixList);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String findGGAffByggIDAndProId() {
		gongyiGuichengAffixList = (List<GongyiGuichengAffix>) this.processDataServer
				.getGongyiGuichengAffixListByGongyiGuichengAffix(this.gongyiGuichengAffix);
		return "showTuzhi";
	}

	/**
	 * 获得工艺规程附件集合 for Select 工艺规程看板 工艺ID 工序ID 附件类型图片
	 * 
	 * @param gongyiGuichengAffix
	 * @return
	 */
	public String getGongyiGuichengAffixListForTupianForSelectByGongyiGuichengIdAndProcessDataId() {
		List<GongyiGuichengAffix> gongyiGuichengAffixList = (List<GongyiGuichengAffix>) this.processDataServer
				.getGongyiGuichengAffixListForTupianForSelectByGongyiGuichengIdAndProcessDataId(this.gongyiGuichengAffix);
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengAffixList);
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

	private void renderJavaScript2(HttpServletResponse response, Object obj) {
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

	// 电子看板
	public String getGongyiGuichengKanbanPage() {
		// this.gongyiGuicheng=this.gongyiGuichengServer.getGongyiGuichengById(this.gongyiGuicheng.getId());
		return "getGongyiGuichengKanbanPage_success";
	}

	// 查询出所有的所有的在干件号
	public String getJianNumbForZaigan() {
		List<Map> jianNumbList = this.gongyiGuichengServer
				.getJianNumbForZaigan(this.screenId);
		MKUtil.writeJSON(true, "操作成功", jianNumbList);
		return null;
	}

	public String getGongyiGuichengListByJianNumb() {
		List<GongyiGuicheng> gongyiGuichengList = this.gongyiGuichengServer
				.getGongyiGuichengListByJianNumb(this.gongyiGuicheng
						.getJianNumb());
		MKUtil.writeJSON(true, "操作成功", gongyiGuichengList);
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

	public List<GongyiGuichengAffix> getGongyiGuichengAffixList() {
		return gongyiGuichengAffixList;
	}

	public void setGongyiGuichengAffixList(
			List<GongyiGuichengAffix> gongyiGuichengAffixList) {
		this.gongyiGuichengAffixList = gongyiGuichengAffixList;
	}

	public GongyiGuichengAffix getGongyiGuichengAffix() {
		return gongyiGuichengAffix;
	}

	public void setGongyiGuichengAffix(GongyiGuichengAffix gongyiGuichengAffix) {
		this.gongyiGuichengAffix = gongyiGuichengAffix;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForAll() {
		return gongyiGuichengListForAll;
	}

	public void setGongyiGuichengListForAll(
			List<GongyiGuicheng> gongyiGuichengListForAll) {
		this.gongyiGuichengListForAll = gongyiGuichengListForAll;
	}

	public GongyiGuichengScoreServer getGongyiGuichengScoreServer() {
		return gongyiGuichengScoreServer;
	}

	public void setGongyiGuichengScoreServer(
			GongyiGuichengScoreServer gongyiGuichengScoreServer) {
		this.gongyiGuichengScoreServer = gongyiGuichengScoreServer;
	}

	public List<GongyiGuicheng> getGongyiGuichengListForKanban() {
		return gongyiGuichengListForKanban;
	}

	public void setGongyiGuichengListForKanban(
			List<GongyiGuicheng> gongyiGuichengListForKanban) {
		this.gongyiGuichengListForKanban = gongyiGuichengListForKanban;
	}

	public Integer getScreenId() {
		return screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public List<ProcessData> getProcessDataList() {
		return processDataList;
	}

	public void setProcessDataList(List<ProcessData> processDataList) {
		this.processDataList = processDataList;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public GongyiGuichengVo getGongyiGuichengVo() {
		return gongyiGuichengVo;
	}

	public void setGongyiGuichengVo(GongyiGuichengVo gongyiGuichengVo) {
		this.gongyiGuichengVo = gongyiGuichengVo;
	}

	public List<GongyiGuichengVo> getGgVoList() {
		return ggVoList;
	}

	public void setGgVoList(List<GongyiGuichengVo> ggVoList) {
		this.ggVoList = ggVoList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

}
