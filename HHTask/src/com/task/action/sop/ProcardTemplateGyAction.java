package com.task.action.sop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.sop.ProcardTemplateGyServer;
import com.task.Server.sop.ProcardTemplateServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.Users;
import com.task.entity.sop.DesignfeedbackNotice;
import com.task.entity.sop.DesignfeedbackNoticeFile;
import com.task.entity.sop.ManualOrderPlan;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardAboutBanBenApply;
import com.task.entity.sop.ProcardBanBenJudge;
import com.task.entity.sop.ProcardReProduct;
import com.task.entity.sop.ProcardReProductFile;
import com.task.entity.sop.ProcardSbWaster;
import com.task.entity.sop.ProcardSbWasterxc;
import com.task.entity.sop.ProcardSbWg;
import com.task.entity.sop.ProcardSbWw;
import com.task.entity.sop.ProcardTBanbenRelation;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcardTemplateBanBen;
import com.task.entity.sop.ProcardTemplateBanBenApply;
import com.task.entity.sop.ProcardTemplateBanBenJudges;
import com.task.entity.sop.ProcardTemplateBanBenJudgesFile;
import com.task.entity.sop.ProcardTemplateChangeLog;
import com.task.entity.sop.ProcessAndWgProcardTem;
import com.task.entity.sop.ProcessChangeNotice;
import com.task.entity.sop.ProcessChangeNoticeFile;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessInforWWApplyDetail;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.entity.sop.TechnicalchangeLog;
import com.task.entity.sop.WaigouPlan;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.system.UserDept;
import com.task.entity.zgkh.AssessPersonnel;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

import ext.huawei.gdp.ecfeeback.bean.ECAFeedBackBean;

/**
 * 流水卡片模板工艺控制Action
 * 
 * @author txb
 * 
 */
public class ProcardTemplateGyAction extends ActionSupport {
	private ProcardTemplateGyServer procardTemplateGyServer;
	private ECAFeedBackBean ecaFeedBackBean;
	private List<ECAFeedBackBean> efbbList;
	private List<ProcessTemplate> processList;
	private String processListString;
	private List<String> processStringList;
	private ProcardTemplateServer procardTemplateServer;// 流水卡片模板
	// use2findProcessByFkId
	private ProcardTemplate procardTemplate;// 对象
	private ProcardTemplate pageProcardTemplate;// 对象
	private ProcessTemplate processTemplate;// 对象
	private ProcardTemplateBanBenApply bbAply;// 版本升级申请
	private ProcardTemplateBanBen ptbb;// 版本升级明细
	private ProcardTBanbenRelation ptbbRelation;// 版本关系
	private List<ProcardTemplate> procardTemplateList;// 流水卡模板列表
	private List<ProcessTemplate> processTemplateList;// 工序模板列表
	private List<ProcardTemplateBanBenApply> bbAplyList;// 模板版本升级申请列表
	private List<ProcardTemplateBanBen> ptbbList;// 模板版本升级申请明细列表
	private List<ProcardTBanbenRelation> ptbbRelationList;// 版本关系列表
	private List<ProcardAboutBanBenApply> pabbList;// 设变涉及生产任务
	private List<ProcardAboutBanBenApply> pabbList2;// 设变涉及生产任务
	private List<UserDept> userDeptList;// 部门负责人
	private ProcardAboutBanBenApply pabb;//
	private ProcardTemplateBanBenJudges ptbbJudges;// 设变评审人员
	private List<ProcardBanBenJudge> pbbJudgeList;//
	private List<ProcardBanBenJudge> pbbJudgeList2;//
	private ProcardBanBenJudge pbbJudge;// 生产件评论
	private List<ProcardTemplateChangeLog> ptchangeLogList;//
	private ProcessTemplateFile processTemplateFile;// 工序工艺规范图纸
	private ProcardSbWg procardsbwg;// 设变影响外购情况
	private ProcardSbWw procardSbWw;// 设变影响外委情况
	private List<ProcardSbWg> procardsbwgList;//
	private List<ProcardSbWw> procardSbWwList;//
	private ProcessInforWWApplyDetail wwApplyDetail;
	private WaigouWaiweiPlan waigouWaiweiPlan;
	private List<WaigouWaiweiPlan> waigouWaiweiPlanList;
	private List<WaigouPlan> waigouPlanList;//
	private List<ManualOrderPlan> manyalOrderPlanList;// 物料需求
	private WaigouPlan waigouplan;//
	private ManualOrderPlan mop;// 物料需求明细
	private ProcessInfor processInfor;// 生产工序
	private List<ProcessInfor> processInforList;
	private List<ProcardReProductFile> prpFileList;// 返修图纸
	private ProcardReProductFile prpFile;// 返修图纸
	private Procard procard;// 生产件
	private ProcardSbWaster procardSbWaster;
	private List<ProcardSbWaster> procardSbWasterList;
	private ProcardSbWasterxc procardSbWasterxc;
	private List<ProcardSbWasterxc> procardSbWasterxcList;
	private ProcessAndWgProcardTem pawp;
	private List<ProcessAndWgProcardTem> pawpList;
	private List list;
	private List list2;
	private List list3;
	private Integer id;
	private Integer id2;
	private Integer rootId;
	private String ids;
	private Integer maxBelongLayer;// 最大层
	private YuanclAndWaigj yclAndWgj;// 原材料外购件
	private String type;// 类型
	private String remark;// 备注
	private String start;// 开始时间
	private String end;// 结束时间
	private String ytRadio;// 原图标记
	private String markId;
	private String bzjdCount;
	private String idname1;
	private String idname2;
	private String idname3;
	private List<String> ryzbList;// 人员组别
	private List<String> pszbList;// 人员组别
	private List<String> pszbList2;// 人员组别

	private String pageStatus;// 页面状态
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private File gygf;
	private String gygfFileName;
	private String gygfFileContentType;
	private File bomTree;
	private String bomTreeFileName;
	private String bomTreeFileContentType;
	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;
	private String tag;// 查看图纸或上传缺陷图纸标识
	private int[] checkboxs;
	private String realPath;
	private List<ProcardReProduct> preProductList;// 返修单
	private ProcardReProduct preProduct;
	private String status;
	// 时间查询
	private String startDate;
	private String endDate;
	public List<String> file_uploadFileName;
	public List<String> file_uploadContentType;
	public List<File> file_upload;
	public TechnicalchangeLog technicalchangeLog;// 设变台账
	public List<TechnicalchangeLog> tclList;//
	public ProcessChangeNotice pcn;
	public List<ProcessChangeNotice> pcnList;
	public List<ProcessChangeNoticeFile> pcnFileList;
	public ProcessChangeNoticeFile pcnfile;
	public DesignfeedbackNotice ecar;
	public List<DesignfeedbackNotice> ecarList;
	public List<DesignfeedbackNoticeFile> ecarfileList;
	public DesignfeedbackNoticeFile ecarfile;
	public ProcardTemplateBanBenJudgesFile ptbbjfile;//
	public List<ProcardTemplateBanBenJudgesFile> ptbbjfileList;//

	public String findProcardTem() {
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_sh",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_sh");
		}
		Object[] object = procardTemplateGyServer.findProcardTem(
				procardTemplate, Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("procardTemplateGyAction_findProcardTem.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "procardTGy_showList";
	}

	/**
	 * 查找当前登陆人需要编制流程的零件（已BOM为导向）
	 * 
	 * @return
	 */
	public String findProcardTemForBz() {
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_bz",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_bz");
		}
		Object[] object = procardTemplateGyServer.findProcardTemForBz(
				procardTemplate, Integer.parseInt(cpage), pageSize, "总成");
		bzjdCount = procardTemplateGyServer.getSpjdCount();
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("procardTemplateGyAction_findProcardTemForBz.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "procardTbz_showList";
	}

	/**
	 * 查询在一个bom下需要登陆人编制的
	 * 
	 * @return
	 */
	public String findSonsForBz() {
		if (Util.getLoginUser() == null) {
			errorMessage = "请先登录！";
			return "loginError";
		}

		procardTemplateList = procardTemplateGyServer.findSonsForBz(id,
				pageProcardTemplate);
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		bzjdCount = procardTemplateGyServer.getSpjdCount();
		return "procardTbz_showSonList";
	}

	/**
	 * 批量审批
	 * 
	 * @return
	 */
	public String batchApprovalBz() {
		String msg = procardTemplateGyServer.batchApprovalBz(checkboxs, tag);
		if (msg.equals("true")) {
			errorMessage = "审批成功";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	public void batchZpBz() {
		String msg = procardTemplateGyServer.batchZpBz(procardTemplateList);
		if (msg.equals("true")) {
			MKUtil.writeJSON("指派成功!");
		} else {
			MKUtil.writeJSON(msg);
		}
	}

	public String shenyue() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		pageStatus = "shenyue";
		return "Template_findProcard2";
	}

	public String jiaodui() {
		Map<Integer, Object> map = procardTemplateGyServer
				.getProcardTemDifference(id);
		return "Template_jiaodui";
	}

	public String findSingleProcards() {
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];
			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("procardTemplateGyAction_findSingleProcards.action?tag="
								+ tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "procardTGy_showSingleList";
	}

	public String showProcardDetailForTz() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		list = procardTemplateGyServer.findCardTemplateTz(id);
		processTemplateList = procardTemplateGyServer
				.getProcessTemplateByProcardId(id);
		if ("quexian".equals(tag))
			return "procardTGy_ProcessSCquexianTz";
		else
			return "procardTGy_showProcessForTz";
	}

	public String findPicbyMarkId() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		Map<String, String> tzwzmap = procardTemplateGyServer
				.findPicbyMarkId(id);
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					String zipName = procardTemplate.getMarkId();
					File zipFile = new File(path + "/" + zipName + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
							zout.putNextEntry(new ZipEntry(tzwzmap
									.get(filename))); // 导出名称
							while ((len = in.read(buf)) > 0) {
								zout.write(buf, 0, len);
							}
							zout.closeEntry();
							in.close();
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					try {
						zout.close();
					} catch (Exception e) {
						// TODO: handle exception
						errorMessage = "对不起没有找到实体图纸!";
						return "error";
					}

					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + zipName + ".zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起,没有找到图纸!";
					return "error";
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String findPICforProduct() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		procard = procardTemplateGyServer.getProcardById(procard.getId());
		Map<String, String> tzwzmap = procardTemplateGyServer
				.findPICforProduct(procard);
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					String zipName = procard.getOrderNumber();
					File zipFile = new File(path + "/" + zipName + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
							zout.putNextEntry(new ZipEntry(tzwzmap
									.get(filename))); // 导出名称
							while ((len = in.read(buf)) > 0) {
								zout.write(buf, 0, len);
							}
							zout.closeEntry();
							in.close();
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					try {
						zout.close();
					} catch (Exception e) {
						// TODO: handle exception
						errorMessage = "对不起没有找到实体图纸!";
						return "error";
					}

					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + zipName + ".zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起,没有找到图纸!";
					return "error";
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	public String daochutz() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录!";
			return "error";
		}
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		Map<String, String> tzwzmap = procardTemplateGyServer.findAllByRootId(
				id, status);
		if (tzwzmap != null) {
			try {
				Set<String> keys = tzwzmap.keySet();
				if (keys != null && keys.size() > 0) {
					String path = ServletActionContext.getServletContext()
							.getRealPath("/upload/file/processTz");
					// ZIP打包图片
					String zipName = procardTemplate.getMarkId();
					File zipFile = new File(path + "/" + zipName + ".zip");
					byte[] buf = new byte[1024];
					int len;
					ZipOutputStream zout = new ZipOutputStream(
							new FileOutputStream(zipFile));
					for (String filename : keys) {
						FileInputStream in = null;
						try {
							in = new FileInputStream(new File(path + "/"
									+ filename));
							if (in == null) {
								continue;
							}
							zout.putNextEntry(new ZipEntry(tzwzmap
									.get(filename))); // 导出名称
							while ((len = in.read(buf)) > 0) {
								zout.write(buf, 0, len);
							}
							zout.closeEntry();
							in.close();
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
					try {
						zout.close();
					} catch (Exception e) {
						// TODO: handle exception
						errorMessage = "对不起没有找到实体图纸!";
						return "error";
					}

					// 下载图片
					FileInputStream zipInput = new FileInputStream(zipFile);
					HttpServletResponse response = ServletActionContext
							.getResponse();
					OutputStream out = response.getOutputStream();
					response.setContentType("application/octet-stream");
					response.setHeader("Content-Disposition",
							"attachment; filename=" + zipName + ".zip");
					while ((len = zipInput.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					zipInput.close();
					out.flush();
					out.close();
					// 删除压缩包
					zipFile.delete();
					return null;
				} else {
					errorMessage = "对不起,没有找到图纸!";
					return "error";
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		errorMessage = "对不起没有找到图纸!";
		return "error";
	}

	/***
	 * 查询工艺规范
	 * 
	 * @return
	 */
	public String findProcardGongyiGuifan() {
		list = procardTemplateGyServer.findProcardGongyiGuifan(id);
		if (list != null && list.size() > 0) {
			return "Process_showGygf";
			// return "findGongyiGuifan";
		} else {
			errorMessage = "不存在该零件的工艺规范信息!";
		}
		return ERROR;
	}

	/***
	 * 查询工艺规范
	 * 
	 * @return
	 */
	public String findProcessGongyiGuifan() {
		list = procardTemplateGyServer.findProcessGongyiGuifan(id);
		if ("quexian".equals(tag)) {
			return "Process_showQXtz";
		} else {
			if (list != null && list.size() > 0)
				return "Process_showGygf";
			// return "findGongyiGuifan";
			else
				errorMessage = "不存在该工序的工艺规范信息!";
		}
		return ERROR;
	}

	/**
	 * 获取工艺编制人员名称
	 */
	public void getGyPeople() {
		list = procardTemplateGyServer.getGyPeople("gy", tag);
		// System.out.println(list.size());
		MKUtil.writeJSON(list);
	}

	/**
	 * 获取设变对应成员组名单
	 */
	public void getsbtypePepole() {
		list = procardTemplateGyServer.getGyPeople("sb", tag);
		// System.out.println(list.size());
		MKUtil.writeJSON(list);
	}

	/**
	 * 提交模板
	 */
	public void submitProcard() {
		errorMessage = (String) ActionContext.getContext().getApplication()
				.get("BOMDaoru");
		if (errorMessage != null) {
			MKUtil.writeJSON(false, errorMessage, null);
		}
		try {
			String msg = procardTemplateGyServer.submitProcard(id, 0);
			if (msg.equals("true")) {
				procardTemplate = procardTemplateGyServer
						.getProcardTemplateById(id);
				MKUtil.writeJSON(true, "提交成功!", procardTemplate.getRootId());
			} else {
				MKUtil.writeJSON(false, msg, null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			String msg = e.getMessage();
			MKUtil.writeJSON(false, msg, null);
		}
		
	}

	/**
	 * 打回模板
	 */
	public void backProcard() {
		String msg = procardTemplateGyServer.backProcard(id);
		if (msg.equals("true")) {
			procardTemplate = procardTemplateGyServer
					.getProcardTemplateById(id);
			MKUtil.writeJSON(true, "已打回!", procardTemplate.getRootId());
		} else {
			MKUtil.writeJSON(false, msg, null);
		}
	}

	/**
	 * 跳往模板bom预览图
	 * 
	 * @return
	 */
	public String reviewBom() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		maxBelongLayer = procardTemplateGyServer
				.findMaxbelongLayer(procardTemplate.getRootId());
		return "procardTGy_viewCard";
	}

	public String findProcardByRunCard2() {
		String receiveStatus = "";
		Integer procarId = id;
		Object[] obj = procardTemplateGyServer.findProcardByRunCard(procarId);
		if (obj != null) {
			if (obj.length > 3) {
				errorMessage = (String) obj[3];
			} else {
				procardTemplate = (ProcardTemplate) obj[0];
				procardTemplateList = (List<ProcardTemplate>) obj[1];
				list = (List) obj[2];
				return "ProcessGy_Receive2";
			}
		} else {
			errorMessage = "该流水卡片错误!无法找到对应工艺信息!请更换!";
		}
		return ERROR;
	}

	/**
	 * 将原有的工艺编制图纸更新到现在表中
	 */
	public void copyTzXinxi() {
		boolean b = procardTemplateGyServer.copyTzXinxi();
		MKUtil.writeJSON(true);
	}

	/**
	 * 根据件号和工序号更新工艺规范的工序名称
	 */
	public void updateProcessNameOfFile() {
		boolean b = procardTemplateGyServer.updateProcessNameOfFile();
		MKUtil.writeJSON(true);
	}

	public void findProByBel() {
		List<ProcardTemplate> list = procardTemplateGyServer.findProByBel(id,
				maxBelongLayer);
		MKUtil.writeJSON(list);
	}

	/**
	 * 卡片图纸
	 * 
	 * @return
	 */
	public String showCardTz() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		errorMessage = pageStatus;
		if (procardTemplate == null) {
			errorMessage = "对不起没有找到目标卡片!";
			return ERROR;
		}
		list = procardTemplateGyServer.findCardTemplateTz(id);
		return "ProcardTemplateTzs";
	}

	public String updateProcardTz() {
		if (this.file_upload != null) {
			String fileName = file_uploadFileName.get(0);
			int index = fileName.lastIndexOf(".");
			String fileType = fileName.substring(index);
			/* set upload path */
			String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS")
					+ fileType;
			String realFileName2 = null;
			if (fileType.equalsIgnoreCase(".bmp")
					|| fileType.equalsIgnoreCase(".dib")
					|| fileType.equalsIgnoreCase(".gif")
					|| fileType.equalsIgnoreCase(".jfif")
					|| fileType.equalsIgnoreCase(".jpe")
					|| fileType.equalsIgnoreCase(".jpeg")
					|| fileType.equalsIgnoreCase(".jpg")
					|| fileType.equalsIgnoreCase(".png")
					|| fileType.equalsIgnoreCase(".tif")
					|| fileType.equalsIgnoreCase(".tiff")
					|| fileType.equalsIgnoreCase(".ico")
					|| fileType.equalsIgnoreCase(".pdf")
					|| fileType.equalsIgnoreCase(".PDF")) {
				realFileName2 = "jz_" + Util.getDateTime("yyyyMMddHHmmssSSS")
						+ fileType;
			}

			String realFilePath = "/upload/file/processTz/"
					+ Util.getDateTime("yyyy-MM");
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();// 如果不存在文件夹就创建
			}
			// 保存文件
			Upload upload = new Upload();
			upload.UploadFile(file_upload.get(0), fileName, realFileName,
					realFilePath, null);
			if (realFileName2 != null) {
				if (fileType.equalsIgnoreCase(".bmp")
						|| fileType.equalsIgnoreCase(".dib")
						|| fileType.equalsIgnoreCase(".gif")
						|| fileType.equalsIgnoreCase(".jfif")
						|| fileType.equalsIgnoreCase(".jpe")
						|| fileType.equalsIgnoreCase(".jpeg")
						|| fileType.equalsIgnoreCase(".jpg")
						|| fileType.equalsIgnoreCase(".png")
						|| fileType.equalsIgnoreCase(".tif")
						|| fileType.equalsIgnoreCase(".tiff")
						|| fileType.equalsIgnoreCase(".ico")) {
					// 将图纸加盖印章
					String icon_fileRealPath = ServletActionContext
							.getServletContext().getRealPath(
									"/upload/file/yz/icon_ytwrq.png");
					// 生成加章文件
					Util.markImageByIcon(icon_fileRealPath, path + "/"
							+ realFileName, path + "/" + realFileName2);
					/* set processTemplateFile */
				} else if (fileType.equalsIgnoreCase(".pdf")
						|| fileType.equalsIgnoreCase(".PDF")) {
					// 将PDF加盖印章
					String icon_fileRealPath = ServletActionContext
							.getServletContext().getRealPath(
									"/upload/file/yz/icon_sk.png");
					// 生成加章文件
					Util.markPDFByIcon(icon_fileRealPath, path + "/"
							+ realFileName, path + "/" + realFileName2);
				}
			}
			processTemplateFile.setFileName(realFileName);
			processTemplateFile.setFileName2(realFileName2);
			processTemplateFile.setMonth(Util.getDateTime("yyyy-MM"));
			fileName = fileName.replaceAll("-ZKT", "-展开图");
			fileName = fileName.replaceAll("-YT", "-原图");
			fileName = fileName.replaceAll("-GYK", "-工艺卡");
			processTemplateFile.setOldfileName(fileName);
			try {
				String msg = this.procardTemplateGyServer.saveProcessTemplateFile(
						this.processTemplateFile, id, ytRadio, tag);
				if (!msg.equals("true")) {
					errorMessage = msg;
					return ERROR;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		} else {
			return ERROR;
		}
		return ERROR;

		/*
		 * if (this.attachment != null && attachment.length > 0) {
		 * 
		 * Users user = Util.getLoginUser(); if (user == null) { errorMessage =
		 * "请先登录"; return ERROR; } for (int i = 0; i < attachment.length; i++) {
		 * 
		 * String fileName = attachmentFileName[i]; // 文件路径 String fileType =
		 * fileName.substring(fileName.lastIndexOf("."), fileName.length());
		 * String realFileName = null; realFileName =
		 * Util.getDateTime("yyyyMMddHHmmss_") + i + fileType; String
		 * realFilePath = "/upload/file/processTz/" +
		 * Util.getDateTime("yyyy-MM"); // 打开存放上传文件的位置 String path =
		 * ServletActionContext.getServletContext() .getRealPath(realFilePath);
		 * File file = new File(path); if (!file.exists()) { file.mkdirs();//
		 * 如果不存在文件夹就创建 } this.processTemplateFile.setFileName(realFileName);
		 * processTemplateFile.setMonth(Util.getDateTime("yyyy-MM"));
		 * processTemplateFile.setOldfileName(fileName); String msg =
		 * this.procardTemplateGyServer
		 * .saveProcessTemplateFile(this.processTemplateFile, id, ytRadio); if
		 * (!msg.equals("true")) { errorMessage = msg; return ERROR; } Upload
		 * upload = new Upload(); upload.UploadFile(attachment[i], fileName,
		 * realFileName, realFilePath, null); } } errorMessage = "添加成功!"; url =
		 * "procardTemplateGyAction_showCardTz.action?id=" + id; return ERROR;
		 */

	}

	/**
	 * 展示外购件
	 * 
	 * @return
	 */
	public String showYclAndWgj() {
		if (yclAndWgj != null) {
			ActionContext.getContext().getSession().put("yclAndWgj", yclAndWgj);
		} else {// 用来保持分页时带着查询条件
			yclAndWgj = (YuanclAndWaigj) ActionContext.getContext()
					.getSession().get("yclAndWgj");
		}
		if (yclAndWgj == null) {
			yclAndWgj = new YuanclAndWaigj();
		}
		if (yclAndWgj.getSpecification() != null
				&& yclAndWgj.getSpecification().length() == 0) {
			yclAndWgj.setSpecification(null);
		}
		// if (type != null && type.equals("wgj")) {
		// yclAndWgj.setClClass("外购件");
		// // yclAndWgj.setTrademark(null);
		// } else if (type != null
		// && (type.equals("ycl") || type.equals("zuhe") || type
		// .equals("yl"))) {
		// yclAndWgj.setClClass("原材料");
		// yclAndWgj.setMarkId(null);
		// }
		Map<Integer, Object> map = procardTemplateGyServer
				.findYuanclAndWaigjsByCondition(yclAndWgj, Integer
						.parseInt(cpage), pageSize, type);
		list = (List) map.get(1);// 显示页的原材料和外购件列表
		if (list != null & list.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_showYclAndWgj.action?type="
					+ type);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "Template_wgjAndycl";
	}

	/**
	 * BOM模版自检
	 * 
	 * @return
	 */
	public String checkSelf() {
		procardTemplateList = procardTemplateGyServer.checkSelf(id);
		return "Template_checkSelf";

	}

	/**
	 * 跳转导入BOM页面
	 * 
	 * @return
	 */
	public String toDaoRuBom() {
		return "Template_daoRuBom";
	}

	/**
	 * 跳转导入BOM页面
	 * 
	 * @return
	 */
	public String toDaoRuHwBom() {
		errorMessage = (String) ActionContext.getContext().getApplication()
				.get("BOMDaoru");
		if (errorMessage != null) {
			return ERROR;
		}
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_hw",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_hw");
		}
		if (procardTemplate == null) {
			procardTemplate = new ProcardTemplate();
			procardTemplate.setProcardStyle("总成");
		}
		// procardTemplate.setProcardStyle("总成");
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("procardTemplateGyAction_toDaoRuHwBom.action?pageStatus="
								+ pageStatus);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		if ("sz".equals(pageStatus)) {
			return "Template_daoRuHwSZBom";
		}
		return "Template_daoRuHwBom";
	}

	/**
	 * 导入bom
	 * 
	 * @return
	 */
	public String daoRuBom() { 
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导入BOM,请稍等片刻~~或者去他那喝杯茶~~");
			} else {
				return ERROR;
			}
			String msg = procardTemplateGyServer.daoRuBomK3(bomTree,
					bomTreeFileName);
			if (msg == null || msg.length() == 0 || msg.equals("true")) {
				successMessage = "导入成功";
			} else {
				successMessage = msg;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
		}
		ActionContext.getContext().getApplication().put("BOMDaoru", null);
		return "Template_daoRuBom";
	}

	public String daoRuHwBom() {
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导入BOM,请稍等片刻~~或者去他那喝杯茶吧~~");
			} else {
				return ERROR;
			}
			String msg = procardTemplateGyServer.daoRuHwBom(bomTree,
					bomTreeFileName, null);
			if (msg == null || msg.length() == 0 || msg.equals("true")) {
				successMessage = "导入成功";
			} else {
				successMessage = msg;
			}
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
		}
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_hw",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_hw");
		}
		if (procardTemplate == null) {
			procardTemplate = new ProcardTemplate();
			procardTemplate.setProcardStyle("总成");
		}
		// procardTemplate.setProcardStyle("总成");
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, "sz");
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("procardTemplateGyAction_toDaoRuHwBom.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		ActionContext.getContext().getApplication().put("BOMDaoru", null);
		return "Template_daoRuHwBom";
	}

	public String daoRuLYBom() {
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导入BOM,请稍等片刻~~或者去他那喝杯茶吧~~");
			} else {
				return ERROR;
			}
			String msg =	procardTemplateGyServer.daoruLyJxBOM(bomTree, bomTreeFileName,type);
			if (msg == null || msg.length() == 0 || msg.equals("true")) {
				successMessage = "导入成功";
			} else {
				successMessage = msg;
			}
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
		}
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_hw",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_hw");
		}
		if (procardTemplate == null) {
			procardTemplate = new ProcardTemplate();
			procardTemplate.setProcardStyle("总成");
		}
		// procardTemplate.setProcardStyle("总成");
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, "sz");
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("procardTemplateGyAction_toDaoRuHwBom.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		ActionContext.getContext().getApplication().put("BOMDaoru", null);
		return "Template_daoRuHwBom";
	}

	
	/****
	 * 一键导入工序
	 * 
	 * @param rootId
	 * @return
	 */
	public String daoRuProcessTempalte() {
		Users user = Util.getLoginUser();
		if (user == null) {
			MKUtil.writeJSON("请先登录!");
			return null;
		}
		List<AssessPersonnel> apList = procardTemplateGyServer.getGyPeople(
				"gy", "工序导入");
		boolean b = false;
		for (AssessPersonnel ap : apList) {
			if (ap.getUserId().equals(user.getId())) {
				b = true;
			}
		}
		if (!b) {
			MKUtil.writeJSON("对不起您没有导入工序权限");
			return null;
		}
		errorMessage = (String) ActionContext.getContext().getApplication()
				.get("BOMDaoru");
		if (errorMessage == null) {
			Users loginUser = Util.getLoginUser();
			ActionContext.getContext().getApplication().put(
					"BOMDaoru",

					loginUser.getDept() + "的" + loginUser.getName()
							+ "正在导BOM工序,请稍等片刻~~或者去他那喝杯茶~~");
		} else {
			MKUtil.writeJSON(errorMessage);
			return null;
		}
		List<ProcardTemplate> ptList = procardTemplateGyServer.getGongxuPt(id);
		if (ptList != null && ptList.size() > 0) {
			int cgcount = 0;
			int cwcount = 0;
			StringBuffer sb = new StringBuffer();
			List<String> passmarkIdLIst = new ArrayList<String>();
			Statement sql = null;
			ResultSet rs = null;
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
			String dbURL = "jdbc:sqlserver://192.168.2.37:1433;databaseName=yyf_ytDB"; // 连接服务器和数据库sample
			String userName = "tiaomao"; // 默认用户名
			String userPwd = "a123456"; // 密码
			Connection dbConn = null;
			try {
				Class.forName(driverName);
				dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
				sql = dbConn.createStatement();
			} catch (Exception e1) {
				e1.printStackTrace();
				ActionContext.getContext().getApplication().put("BOMDaoru",
						null);
				MKUtil
						.writeJSON("导入失败，无法连接数据源(192.168.2.37:1433;databaseName=yyf_ytDB)!");
				return null;
			}
			try {
				// Object[] obj2 = procardTemplateGyServer
				// .updatedaoRuProcessTemplate(null, sql, rs);
				for (ProcardTemplate pt : ptList) {
					if (passmarkIdLIst.contains(pt.getMarkId())) {
						continue;
					} else {
						passmarkIdLIst.add(pt.getMarkId());
					}
					Object[] obj = procardTemplateGyServer
							.updatedaoRuProcessTemplate(pt, sql, rs);
					String m1 = obj[0].toString();
					String m2 = obj[1].toString();
					if (m1.equals("false")) {
						errorMessage = m2;
						break;
					} else {
						if (m1.equals("1")) {
							cgcount++;
						} else {
							cwcount++;
							sb.append(cwcount + "、" + m2);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage = "导入成功" + cgcount + "个</br>导入失败" + cwcount
						+ "个</br>";
				if (cwcount > 0) {
					errorMessage += sb.toString();
				}
				ActionContext.getContext().getApplication().put("BOMDaoru",
						null);
				MKUtil.writeJSON(errorMessage + ",出现异常导入中断!");
				return null;
			}
			try {
				dbConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				ActionContext.getContext().getApplication().put("BOMDaoru",
						null);
				MKUtil.writeJSON(errorMessage + ",出现异常导入中断!");
				return null;
			}

			errorMessage = "导入成功" + cgcount + "个</br>导入失败" + cwcount + "个</br>";
			if (cwcount > 0) {
				errorMessage += sb.toString();
			}

		} else {
			errorMessage = "没有可以导入工序的零件!";
		}

		ActionContext.getContext().getApplication().put("BOMDaoru", null);
		MKUtil.writeJSON(errorMessage);
		return null;
	}

	/****
	 * 修复模具
	 * 
	 * @param rootId
	 * @return
	 */
	public String xiufuGzstoreId() {
		Users user = Util.getLoginUser();
		if (user == null) {
			MKUtil.writeJSON("请先登录!");
			return null;
		}
		int cgcount = 0;
		int cwcount = 0;
		StringBuffer sb = new StringBuffer();
		List<String> passmarkIdLIst = new ArrayList<String>();
		Statement sql = null;
		ResultSet rs = null;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://192.168.2.37:1433;databaseName=yyf_ytDB"; // 连接服务器和数据库sample
		String userName = "tiaomao"; // 默认用户名
		String userPwd = "a123456"; // 密码
		Connection dbConn = null;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			sql = dbConn.createStatement();
		} catch (Exception e1) {
			e1.printStackTrace();
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil
					.writeJSON("导入失败，无法连接数据源(192.168.2.37:1433;databaseName=yyf_ytDB)!");
			return null;
		}
		try {
			errorMessage = procardTemplateGyServer.updategzprocess(sql, rs);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(errorMessage + ",出现异常导入中断!"+e.getMessage());
			return null;
		}
		try {
			dbConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil.writeJSON(errorMessage + ",出现异常导入中断!");
			return null;
		}
		MKUtil.writeJSON(errorMessage);
		return null;
	}

	public void processAndWgProcard() {
		// 开始工序关联外购件 wxf
		try {
			procardTemplateServer.processAndWgProcard(rootId);
			MKUtil.writeJSON("关联成功!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON("关联失败!");
		}
	}

	public void quxiaoApplication() {// procardTemplateGyAction_quxiaoApplication.action?remark=BOMDaoru
		ActionContext.getContext().getApplication().put(remark, null);
		MKUtil.writeJSON("true");
	}

	// 导入华为试制BOM
	public String daoRuHwSZBom() {
		errorMessage = (String) ActionContext.getContext().getApplication()
				.get("BOMDaoru");
		if (errorMessage == null) {
			Users loginUser = Util.getLoginUser();
			if(loginUser==null){
				errorMessage ="请先登录!";
				return ERROR;
			}
			ActionContext.getContext().getApplication().put(
					"BOMDaoru",
					loginUser.getDept() + "的" + loginUser.getName()
							+ "正在导入BOM,请稍等片刻~~或者去他那喝杯茶吧~~");
		} else {
			return ERROR;
		}
		try {
			String msg = procardTemplateGyServer.daoRuHwSZBom(bomTree,
					bomTreeFileName, null);
			if (msg == null || msg.length() == 0 || msg.equals("true")) {
				successMessage = "导入成功";
			} else {
				successMessage = msg;
			}
			ActionContext.getContext().getApplication().remove("BOMDaoru");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
			ActionContext.getContext().getApplication().remove("BOMDaoru");
		}
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate_hw",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate_hw");
		}
		if (procardTemplate == null) {
			procardTemplate = new ProcardTemplate();
			procardTemplate.setProcardStyle("总成");
		}
		// procardTemplate.setProcardStyle("总成");
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, "sz");
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("procardTemplateGyAction_toDaoRuHwBom.action?pageStatus=sz");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "Template_daoRuHwSZBom";
	}

	public String toBomCompare() {
		return "Template_compare";
	}

	/**
	 * BOM文件与现有数据比较
	 * 
	 * @return
	 */
	public String bomCompare() {
		if (bomTreeFileName == null) {
			errorMessage = "无效的文件!";
			return "Template_compare";
		}
		try {
			String msg = procardTemplateGyServer.getbomCompare(bomTree,
					bomTreeFileName);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
		}
		return "Template_compare";
	}

	public void daoChuBom() {
		procardTemplateGyServer.findDaoChuBom(id, pageStatus);
	}

	public void daoChuBom1() {
		procardTemplateGyServer.findDaoChuBom1(pageStatus);
	}

	/**
	 * 导出单个生产Bom
	 */
	public void daoChuBomByID() {
		procardTemplateGyServer.findDaoChuBom(id, pageStatus);
	}

	public void daoChuHWBom() {
		procardTemplateGyServer.findDaoChuHWBom(id);
	}

	/**
	 * 显示可以升级版本号的件号
	 * 
	 * @return
	 */
	public String showBanBenList() {
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		if (procardTemplate == null) {
			procardTemplate = new ProcardTemplate();
		}
		procardTemplate.setBzStatus("已批准");
		Object[] object = procardTemplateGyServer.findProcardTem2(
				procardTemplate, Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("procardTemplateGyAction_showBanBenList.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "Template_showBanBenList";
	}

	/**
	 * 获取编制状态
	 */
	public void getBzStatus() {
		String bzStatus = procardTemplateGyServer.getBzStatus(id);
		MKUtil.writeJSON(bzStatus);
	}

	/**
	 * 获取要升级件号所在的所有总成号
	 * 
	 * @return
	 */
	public void showUPtotalPtList() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		if (procardTemplate != null) {
			procardTemplateList = procardTemplateGyServer
					.findUPtotalPtList(procardTemplate.getMarkId());
		}
		MKUtil.writeJSON(true, null, procardTemplate, procardTemplateList);
	}

	/**
	 * 获取连带升级的BOM树枝干
	 * 
	 * @return
	 */
	public String findSonToUP() {
		procardTemplateList = procardTemplateGyServer
				.findSonToUP(checkboxs, id);
		return "Template_SonToUPList";
	}

	/**
	 * 展示想要升级版本的的模板
	 * 
	 * @return
	 */
	public String showToUpdatebanben() {
		procardTemplateList = procardTemplateGyServer.showToUpdatebanben(ids);
		return "Template_toUpdatebanben";
	}

	/**
	 * 申请升级版本
	 * 
	 * @return
	 */
	public String applyUpdateBan() {
		String msg = procardTemplateGyServer.applyUpdateBan(
				procardTemplateList, remark);
		if (msg.equals("true")) {

		}
		return null;
	}

	/**
	 * 分页显示版本升级申请
	 * 
	 * @return
	 */
	public String bbApplyList() {
		if (bbAply != null) {
			ActionContext.getContext().getSession().put("bbAply", bbAply);
		} else {// 用来保持分页时带着查询条件
			bbAply = (ProcardTemplateBanBenApply) ActionContext.getContext()
					.getSession().get("bbAply");
		}
		Map<Integer, Object> map = procardTemplateGyServer
				.findBbAplyListByCondition(bbAply, Integer.parseInt(cpage),
						pageSize, start, end, pageStatus);
		bbAplyList = (List<ProcardTemplateBanBenApply>) map.get(1);// 显示页的技能系数列表
		if (bbAplyList != null & bbAplyList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_bbApplyList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "ptbbApply_show";
	}

	/**
	 * 版本升级申请明细
	 * 
	 * @return
	 */
	public String bbApplydetail() {
		bbAply = procardTemplateGyServer.getBBApplyById(id);
		ptbbList = procardTemplateGyServer.findptBBListByBBApplyId(id);
		return "ptbbApply_detail";
	}

	/**
	 * 版本关系展示
	 * 
	 * @return
	 */
	public String bbRelationShow() {
		if (ptbbRelation != null) {
			ActionContext.getContext().getSession().put("ptbbRelation",
					ptbbRelation);
		} else {// 用来保持分页时带着查询条件
			ptbbRelation = (ProcardTBanbenRelation) ActionContext.getContext()
					.getSession().get("ptbbRelation");
		}
		Map<Integer, Object> map = procardTemplateGyServer
				.findPtbbRelationListByCondition(ptbbRelation, Integer
						.parseInt(cpage), pageSize);
		ptbbRelationList = (List<ProcardTBanbenRelation>) map.get(1);// 显示页的技能系数列表
		if (ptbbRelationList != null & ptbbRelationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_bbRelationShow.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "ptbbRelation_show";
	}

	// ---又GG了好忧伤。。。。
	/**
	 * 跳往版本升级的页面（父页面）
	 * 
	 * @return
	 */
	public String toUpLv() {
		return "procardTem_upLv";
	}

	/**
	 * 展示树形
	 */
	public void toUpLvTree() {
		procardTemplateList = procardTemplateGyServer.getProcardTemListById(id);
		MKUtil.writeJSON(procardTemplateList);
	}

	/**
	 * 跳往版本升级页面（子页面）
	 * 
	 * @return
	 */
	public String toupptlv() {
		try {
			procardTemplate = procardTemplateGyServer
					.getProcardTemplateById(id);
			if (!procardTemplate.getBzStatus().equals("已批准")) {
				errorMessage = "对不起该零件的编制状态未达到已批准，不允许版本!";
			}
			bbAply = procardTemplateGyServer.getBanBenApplyByptId(id);
			bbAplyList = procardTemplateGyServer.getHistoryBanBenApplyByptId(
					id, "agree");
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "procardTem_upptlv";
	}

	// public String upptlv() {
	// try {
	// String msg = procardTemplateGyServer.upptlv(procardTemplate);
	// if (msg.equals("true")) {
	// errorMessage = "版本升级申请成功!";
	// } else {
	// errorMessage = msg;
	// }
	// } catch (Exception e) {
	// // TODO: handle exception76
	// errorMessage = e.getMessage();
	// }
	// url = "procardTemplateGyAction_toupptlv.action?id="
	// + procardTemplate.getId();
	// return "error";
	// }
	public void upptlvNew() {
		// MKUtil.writeJSON("test!");
		Object[] objs = null;
		try {
			objs = procardTemplateGyServer.upptlvnew(ptbbList, rootId, bbAply,
					ptbbJudges);
		} catch (Exception e) {
			MKUtil.writeJSON(e.getMessage());
			return;
		}
		if (objs != null) {
			try {
				String msg = objs[0].toString();
				if (msg.equals("true")) {
					id = Integer.parseInt(objs[1].toString());
					bbAply = new ProcardTemplateBanBenApply();
					bbAply.setId(id);
					MKUtil.writeJSON("版本升级申请成功!");
				} else {
					MKUtil.writeJSON(msg);
				}
			} catch (Exception e) {
				// TODO: handle exception
				MKUtil.writeJSON(e.getMessage());
			}
		}
	}

	public void sbzpgbm() {
		// MKUtil.writeJSON("test!");
		try {
			Object[] objs = procardTemplateGyServer.sbzpgbm(bbAply, ptbbJudges);
			if (objs != null) {
				String msg = objs[0].toString();
				if (msg.equals("true")) {
					id = Integer.parseInt(objs[1].toString());
					bbAply = new ProcardTemplateBanBenApply();
					bbAply.setId(id);
					MKUtil.writeJSON("指派成功!");
				} else {
					MKUtil.writeJSON(msg);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			MKUtil.writeJSON(e.getMessage());
		}
	}

	public String ptbbRelationShow() {
		if (ptbbRelation != null) {
			ActionContext.getContext().getSession().put("ptbbRelation",
					ptbbRelation);
		} else {// 用来保持分页时带着查询条件
			ptbbRelation = (ProcardTBanbenRelation) ActionContext.getContext()
					.getSession().get("ptbbRelation");
		}
		Map<Integer, Object> map = procardTemplateGyServer
				.findPtbbRelationsByCondition(ptbbRelation, Integer
						.parseInt(cpage), pageSize);
		ptbbRelationList = (List<ProcardTBanbenRelation>) map.get(1);// 显示页的技能系数列表
		if (ptbbRelationList != null & ptbbRelationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_ptbbRelationShow.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "ptbbRelationShow";
	}

	public String stoprealtion() {
		String msg = procardTemplateGyServer.stoprealtion(id);
		if (msg.equals("true")) {
			errorMessage = "申请成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_ptbbRelationShow.action";
		return "error";
	}

	/**
	 * 导入工艺图纸内容
	 */
	public void checkAndUpdateTz() {
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导BOM图纸,请稍等片刻~~或者去他那喝杯茶~~");
			} else {
				MKUtil.writeJSON(errorMessage);
				return;
			}
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/gytz/");
			String path2 = ServletActionContext.getServletContext()
					.getRealPath("");
			String msg = procardTemplateGyServer.checkAndUpdateTz(id, path,
					path2);
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil.writeJSON("导入失败!" + e);
		}
	}

	public void ncAndleisheFile() {
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导编程图纸,请稍等片刻~~或者去他那喝杯茶~~");
			} else {
				MKUtil.writeJSON(errorMessage);
				return;
			}
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/bctz/");
			String path2 = ServletActionContext.getServletContext()
					.getRealPath("");
			String msg = procardTemplateGyServer.ncAndleisheFile(path, path2);
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			e.printStackTrace();
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
			MKUtil.writeJSON("导入失败!" + e);
		}
	}

	/**
	 * 修改图纸名称
	 */
	public void updateUnuploadTzname() {
		try {
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/gytz/");
			String path2 = ServletActionContext.getServletContext()
					.getRealPath("");
			String msg = procardTemplateGyServer.updateUnuploadTzname(id, path);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("修改失败!" + e);
		}
	}

	/**
	 * 跳往指派编制,校对,审核,批准成员
	 * 
	 * @return
	 */
	public String tozpbz() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		return "Template_zpbz";
	}

	public String tozhipaiBOmTree() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		return "Template_zpBOmTree";
	}

	/**
	 *指派编制,校对,审核,批准成员
	 * 
	 * @return
	 */
	public String zpbz() {
		String bzStatus = procardTemplateGyServer.getBzStatus(procardTemplate
				.getId());
		procardTemplate.setBzStatus(bzStatus);
		String msg = procardTemplateGyServer.zpbz(procardTemplate, 0);
		if (tag != null && tag.equals("ajax")) {
			if (msg.equals("true")) {
				MKUtil.writeJSON("指派成功");
			} else {
				MKUtil.writeJSON(msg);
			}
			return null;

		}
		if (msg.equals("true")) {
			errorMessage = "指派成功";
			url = "procardTemplateGyAction_tozpbz.action?id="
					+ procardTemplate.getId();
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 *指派BOM结构的校对,审核,批准成员
	 * 
	 * @return
	 */
	public String zpBOmTree() {
		String msg = procardTemplateGyServer.zpBOmTree(procardTemplate);
		if (msg.equals("true")) {
			errorMessage = "指派成功";
			url = "procardTemplateGyAction_tozhipaiBOmTree.action?id="
					+ procardTemplate.getId();
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 显示下阶层
	 * 
	 * @return
	 */
	public String moveStatus() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		procardTemplateList = procardTemplateGyServer.findPtListByFatherId(id);
		return "Template_moveStatus";
	}

	/**
	 * 修改下阶层
	 * 
	 * @return
	 */
	public String sonMoveStatus() {
		String msg = procardTemplateGyServer.sonMoveStatus(procardTemplateList,
				id);
		url = "procardTemplateGyAction_moveStatus.action?id=" + id;
		errorMessage = "设置成功";
		MKUtil.writeJSON(errorMessage);
		return null;
	}

	/**
	 * 批量删除下阶层
	 * 
	 * @return
	 */
	public void deleteSons() {
		try {
			String msg = procardTemplateGyServer.deleteSons(checkboxs, id);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}

	public String tosbApply() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id);
		return "Template_sbApply";
	}

	/**
	 * 历史版本
	 * 
	 * @return
	 */
	public String showHistoryList() {
		procardTemplateList = procardTemplateGyServer.findHistoryList(id);
		return "Template_historyList";
	}

	/**
	 * 申请设变
	 */
	public void applySb() {
		String msg = procardTemplateGyServer.applySb(id);
		if (msg.equals("true")) {
			MKUtil.writeJSON(true, "申请成功!", null);
		} else {
			MKUtil.writeJSON(false, msg, null);
		}
	}

	/**
	 * 申请设变
	 */
	public void applyBomTree() {
		String msg = procardTemplateGyServer.applyBomTree(id);
		if (msg.equals("true")) {
			MKUtil.writeJSON(true, "申请成功!", null);
		} else {
			MKUtil.writeJSON(false, msg, null);
		}
	}

	/**
	 * 将BOM以Exlce的样式显示
	 * 
	 * @return
	 */
	public String showBOMasExl() {
		procardTemplateList = procardTemplateGyServer.findBOMasExl(id);
		return "Template_showBOMasExl";
	}

	/**
	 * 将BOM以Exlce的样式显示 、 并显示工序及添加工序
	 * 
	 * @return
	 */
	public String showBOMasExlAndProcedure() {
		processStringList = new ArrayList<String>();
		processListString = "";
		procardTemplateList = procardTemplateGyServer.findBOMasExl(id);
		for (ProcardTemplate procardTemplate : procardTemplateList) {
			List<ProcessTemplate> processList = procardTemplateServer
					.findProcessByFkId(procardTemplate.getId());
			for (ProcessTemplate processTemplate : processList) {
				processListString += processTemplate.getProcessName();
				processListString += ";";
			}
			processStringList.add(processListString);
			processListString = "";
		}
		return "Template_showBOMasExlAndProcedure";
	}

	public String findTzfg() {
		list = procardTemplateGyServer.findTzfg("郝拯民", "李元芳");
		return "Template_findTzfg";
	}

	public String DownloadProcessTz() {
		ProcessTemplateFile processFile = procardTemplateGyServer
				.findGyTzById(id);
		if (processFile != null) {
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file/processTz")
					+ "/"
					+ processFile.getMonth()
					+ "/"
					+ processFile.getFileName();
			File file = new File(fileRealPath);
			realPath = "/upload/file/processTz" + "/" + processFile.getMonth()
					+ "/" + processFile.getFileName();
			if (file.exists()) {
				// 返回时的名字
				try {
					gygfFileName = java.net.URLEncoder.encode(processFile
							.getOldfileName(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				AttendanceTowServerImpl.updateJilu(8, processFile, processFile
						.getId(), "件号:" + processFile.getMarkId() + "第"
						+ processFile.getProcessNO() + "工序"
						+ processFile.getProcessName() + " "
						+ processFile.getType() + " 图纸名:"
						+ processFile.getOldfileName() + "(下载)");
				return "download";
			} else {
				String fileRealPath2 = ServletActionContext.getServletContext()
						.getRealPath("/upload/file/processTz")
						+ "/"
						+ processFile.getMonth()
						+ "/"
						+ processFile.getFileName2();
				file = new File(fileRealPath2);
				realPath = "/upload/file/processTz" + "/"
						+ processFile.getMonth() + "/"
						+ processFile.getFileName2();
				if (file.exists()) {
					// 返回时的名字
					try {
						gygfFileName = java.net.URLEncoder.encode(processFile
								.getOldfileName(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					AttendanceTowServerImpl.updateJilu(8, processFile,
							processFile.getId(), "件号:"
									+ processFile.getMarkId() + "第"
									+ processFile.getProcessNO() + "工序"
									+ processFile.getProcessName() + " "
									+ processFile.getType() + " 图纸名:"
									+ processFile.getOldfileName() + "(下载)");
					return "download";
				} else {
					errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
					return "error";
				}
			}
		}
		errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}

	public InputStream getInputStream() throws Exception {
		// return new FileInputStream(dir);
		// 如果dir是绝对路径
		// return
		return ServletActionContext.getServletContext().getResourceAsStream(
				realPath);
		// 如果dir是Resource下的相对路径
	}

	/**
	 * 删除重复工序
	 */
	public void deleteSameProcess() {
		String msg = procardTemplateGyServer.deleteSameProcess();
		MKUtil.writeJSON(msg);
	}

	/**
	 * 原材料下移
	 */
	public void changeycl() {

		procardTemplateGyServer.changeycl();
	}

	/**
	 * 导入自制件的长、宽、厚
	 * 
	 * @return
	 */
	public String daorutest() {
		procardTemplateGyServer.daorutest(bomTree);
		return "error";
	}

	/**
	 * 获取要设变的零件信息
	 */
	public void getProcardTemForsb() {
		procardTemplate = procardTemplateGyServer.getProcardTemplateById(id,
				rootId);
		MKUtil.writeJSON(procardTemplate);
	}

	/****
	 * 获取华为设变待反馈清单--下发
	 * 
	 * @return
	 */
	public String findHWEcList() {
		efbbList = procardTemplateGyServer.updateHWEcList(ecaFeedBackBean);
		return "sbApply_show_hw";
	}

	/**
	 * 设变申请记录
	 * 
	 * @return
	 */
	public String findSbApplyList() {
		if (bbAply != null) {
			ActionContext.getContext().getSession().put("bbAply", bbAply);
		} else {// 用来保持分页时带着查询条件
			bbAply = (ProcardTemplateBanBenApply) ActionContext.getContext()
					.getSession().get("bbAply");
		}
		if (startDate != null) {
			ActionContext.getContext().getSession().put("startDate", startDate);
		} else {// 用来保持分页时带着查询条件
			startDate = (String) ActionContext.getContext().getSession().get(
					"startDate");
		}
		if (endDate != null) {
			ActionContext.getContext().getSession().put("endDate", endDate);
		} else {// 用来保持分页时带着查询条件
			endDate = (String) ActionContext.getContext().getSession().get(
					"endDate");
		}
		Map<Integer, Object> map = procardTemplateGyServer.findSbApplyList(
				bbAply, Integer.parseInt(cpage), pageSize, pageStatus, markId,
				startDate, endDate, null);
		ryzbList = procardTemplateGyServer.getRyzbList("sb");
		bbAplyList = (List<ProcardTemplateBanBenApply>) map.get(1);// 显示页的技能系数列表
		if (bbAplyList != null & bbAplyList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_findSbApplyList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "sbApply_show";
	}

	/**
	 * 设变申请记录
	 * 
	 * @return
	 */
	public String findselfSbApplyList() {
		if (bbAply != null) {
			ActionContext.getContext().getSession().put("bbAply", bbAply);
		} else {// 用来保持分页时带着查询条件
			bbAply = (ProcardTemplateBanBenApply) ActionContext.getContext()
					.getSession().get("bbAply");
		}
		if (startDate != null) {
			ActionContext.getContext().getSession().put("startDate", startDate);
		} else {// 用来保持分页时带着查询条件
			startDate = (String) ActionContext.getContext().getSession().get(
					"startDate");
		}
		if (endDate != null) {
			ActionContext.getContext().getSession().put("endDate", endDate);
		} else {// 用来保持分页时带着查询条件
			endDate = (String) ActionContext.getContext().getSession().get(
					"endDate");
		}
		list = procardTemplateGyServer.finddclSbApplyList(bbAply, Integer
				.parseInt(cpage), pageSize, pageStatus, markId, startDate,
				endDate, "self");
		Map<Integer, Object> map = procardTemplateGyServer.findSbApplyList(
				bbAply, Integer.parseInt(cpage), pageSize, pageStatus, markId,
				startDate, endDate, "self");
		ryzbList = procardTemplateGyServer.getRyzbList("sb");
		bbAplyList = (List<ProcardTemplateBanBenApply>) map.get(1);// 显示页的技能系数列表
		if (bbAplyList != null & bbAplyList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_findselfSbApplyList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "sbApply_show2";
	}

	/**
	 * 显示设变进度
	 * 
	 * @return
	 */
	public String showSbApplyJd() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录";
			url = "login.jsp";
			return "error";
		}
		Object[] objs = procardTemplateGyServer.showSbApplyJd(bbAply.getId());
		bzjdCount = procardTemplateGyServer.getSpjdCount();
		ryzbList = procardTemplateGyServer.getRyzbList("sb");
		if (objs != null) {
			if (objs[0] != null) {
				bbAply = (ProcardTemplateBanBenApply) objs[0];
				if (bbAply != null && bbAply.getProcessStatus().equals("分发项目组")) {
					list = procardTemplateGyServer.findCpryList();
				}
			}
			if (objs[1] != null) {
				pabbList = (List<ProcardAboutBanBenApply>) objs[1];// 同总成
			}
			if (objs[2] != null) {
				pabbList2 = (List<ProcardAboutBanBenApply>) objs[2];// 非同总成
			}
			if (objs[3] != null) {
				ptchangeLogList = (List<ProcardTemplateChangeLog>) objs[3];// 修改日志
			}
		} else {
			return "没有找到对应的设变单!";
		}
		return "sbApply_showjd";
	}

	/**
	 * 显示设变进度（新流程）
	 * 
	 * @return
	 */
	public String showSbApplyJd2() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录";
			url = "login.jsp";
			return "error";
		}
		ProcardTemplateBanBenApply pta = procardTemplateGyServer
				.getBBApplyById(bbAply.getId());
		if (pta == null) {
			errorMessage = "没有找到目标设变单";
			return "error";
		} else if (pta.getIsold() != null && pta.getIsold().equals("是")) {
			return showSbApplyJd();
		}
		Object[] objs = procardTemplateGyServer.showSbApplyJd2(bbAply.getId());
		bzjdCount = procardTemplateGyServer.getSpjdCount();
		ryzbList = procardTemplateGyServer.getRyzbList("sb");
		if (objs != null) {
			if (objs[0] != null) {
				bbAply = (ProcardTemplateBanBenApply) objs[0];
				if (bbAply != null && bbAply.getProcessStatus().equals("分发项目组")) {
					list = procardTemplateGyServer.findCpryList();
				}
			}
			if (objs[1] != null) {
				pabbList = (List<ProcardAboutBanBenApply>) objs[1];// 同总成
			}
			if (objs[2] != null) {
				pabbList2 = (List<ProcardAboutBanBenApply>) objs[2];// 非同总成
			}
			if (objs[3] != null) {
				// ptchangeLogList = (List<ProcardTemplateChangeLog>) objs[3];//
				// 修改日志
				pszbList = (List<String>) objs[3];// 设变需要评审的组别
			}
			if (objs[4] != null) {
				pszbList2 = (List<String>) objs[4];// 设变需要评审的组别
			}
		} else {
			return "没有找到对应的设变单!";
		}
		return "sbApply_showjd2";
	}

	public void updatepbbremark() {
		String msg = procardTemplateGyServer.updatepbbremark(ptbb);
		MKUtil.writeJSON(msg);
	}

	public void findCpryList() {
		list = procardTemplateGyServer.findCpryList();
		MKUtil.writeJSON(list);
	}

	public String toSelectSbProcardTemplate() {
		rootId = procardTemplateGyServer.getPtIdBybbApplyId(id);
		bbAply = procardTemplateGyServer.getProcardTemplateBanbenApplyById(id);
		ptbbList = procardTemplateGyServer.findptBBListByBBApplyId(id);
		return "Template_findBomforsb";
	}

	public void findSbProcardTemByRootId() {
		List<ProcardTemplate> proList = procardTemplateGyServer
				.findSbProcardTemByRootId(id);
		try {
			MKUtil.writeJSON(proList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 设变初评
	 */
	public void submitchupin() {
		String msg = procardTemplateGyServer.submitchupin(ptbbJudges, remark);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 无相关生产零件内部评审
	 */
	public void noprocardns() {
		String msg = procardTemplateGyServer.noprocardns(ptbbJudges);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 跳转设变相关部门
	 * 
	 * @return
	 */
	public String findAboutDept() {
		// bzjdCount = procardTemplateGyServer.getSpjdCount();
		// 获取设变必须通知的部门
		remark = procardTemplateGyServer.gettzDept();
		return "sbApply_showDept";
	}

	/**
	 * 获取设变影响部门
	 */
	public void getDeptTreeVos() {
		MKUtil.writeJSON(procardTemplateGyServer.getDeptTreeVos(ids));
	}

	/**
	 * 跳往发起设变页面
	 * 
	 * @return
	 */
	public String toAddSb() {
		list = procardTemplateGyServer.findCpryList();
		if (ecaFeedBackBean != null) {
			efbbList = procardTemplateGyServer.updateHWEcList(ecaFeedBackBean);
			if (efbbList != null && efbbList.size() > 0) {
				ecaFeedBackBean = efbbList.get(0);
			}
		}
		return "sbApply_toAddSb";
	}

	/**
	 * 发起设变
	 * 
	 * @return
	 */
	public String addSbApply() {

		if (attachment != null && attachment.length > 0) {
			String newFileName = MKUtil.saveFile(attachment,
					attachmentFileName, "sbFile");
			for (int i = 0; i < attachment.length; i++) {
				gygfFileName += "|" + attachment[i];
			}
			if (gygfFileName != null && gygfFileName.length() > 0) {
				gygfFileName = gygfFileName.substring(1);
			}
			Object[] obj = procardTemplateGyServer.addSbApply(bbAply, id,
					gygfFileName, newFileName);
			errorMessage = obj[0].toString();
		} else {
			Object[] obj = procardTemplateGyServer.addSbApply(bbAply, id, null,
					null);
			errorMessage = obj[0].toString();
			if (errorMessage.equals("发起成功")) {
				url = "procardTemplateGyAction_toAddSb.action";
			}
		}

		return "error";
	}

	// 设变指派项目主管
	public String sbffxmz() {
		String msg = procardTemplateGyServer.sbffxmz(bbAply, id);
		errorMessage = msg;
		url = "procardTemplateGyAction_showSbApplyJd.action?bbAply.id="
				+ bbAply.getId();
		return "error";
	}

	/**
	 * 提交内审
	 */
	public void submitnp() {
		String msg = procardTemplateGyServer.submitnp(ptbbJudges, pbbJudgeList,
				pbbJudgeList2, tag);
		MKUtil.writeJSON(msg);
	}

	public void passns() {
		String msg = procardTemplateGyServer.submitnp(id);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 确认设变
	 */
	public void suresb() {
		try {
			String msg = procardTemplateGyServer.suresb(id, ptbbJudges,
					ptbbList);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}

	/**
	 * 同步生产件
	 */
	public void tbProcard() {
		errorMessage = (String) ActionContext.getContext().getApplication()
				.get("tbProcard" + id);
		if (errorMessage == null) {
			Users loginUser = Util.getLoginUser();
			ActionContext.getContext().getApplication().put(
					"tbProcard" + id,
					loginUser.getDept() + "的" + loginUser.getName()
							+ "同步此设变单请勿重复同步");
		} else {
			MKUtil.writeJSON(errorMessage);
			return;
		}
		try {
			String msg = procardTemplateGyServer.tbProcard(bbAply, ptbbJudges);
			MKUtil.writeJSON(msg);
			ActionContext.getContext().getApplication().put("tbProcard" + id,
					null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
			ActionContext.getContext().getApplication().put("tbProcard" + id,
					null);
		}

	}

	/**
	 * 获取BOM编制节点数量
	 */
	public void getSpjdCount() {
		bzjdCount = procardTemplateGyServer.getSpjdCount();
		MKUtil.writeJSON(bzjdCount);
	}

	/**
	 * 获取零件信息
	 */
	public void getProcardTemplateMsg() {
		List<ProcardTemplate> pt = procardTemplateGyServer
				.getProcardTemplateMsg(type, markId);
		MKUtil.writeJSON(pt);
	}

	/**
	 * 删除设变
	 */
	public String deleteSb() {
		String msg = procardTemplateGyServer.deleteSb(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_findSbApplyList.action?cpage=" + cpage;
		return "error";

	}

	/**
	 * 取消设变
	 * 
	 * @return
	 */
	public String quxiaoSb() {
		String msg = procardTemplateGyServer.quxiaoSb(bbAply);
		if (msg.equals("true")) {
			errorMessage = "取消成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_findSbApplyList.action?cpage=" + cpage;
		return "error";
	}

	/**
	 * 关闭设变
	 * 
	 * @return
	 */
	public String closeSb() {
		String msg = procardTemplateGyServer.closeSb(id, type);
		if (msg.equals("true")) {
			errorMessage = "关闭成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_findSbApplyList.action?cpage=" + cpage;
		return "error";
	}

	/**
	 * 关闭设变
	 * 
	 * @return
	 */
	public void closeSbwithremark() {
		String msg = procardTemplateGyServer.closeSb2(id, remark);
		if (msg.equals("true")) {
			errorMessage = "true";
		} else {
			errorMessage = msg;
		}
		MKUtil.writeJSON(errorMessage);
	}

	/**
	 * 关闭设变
	 * 
	 * @return
	 */
	public void closeclosenoremark() {
		String msg = procardTemplateGyServer.closeclosenoremark(id);
		if (msg.equals("true")) {
			errorMessage = "true";
		} else {
			errorMessage = msg;
		}
		MKUtil.writeJSON(errorMessage);
	}

	/**
	 * 打回设变
	 */
	public void dahuisb() {
		String msg = procardTemplateGyServer.dahuisb(id, remark);
		if (msg.equals("true")) {
			errorMessage = "true";
		} else {
			errorMessage = msg;
		}
		MKUtil.writeJSON(errorMessage);
	}

	/**
	 * 打回设变申请
	 * 
	 * @return
	 */
	public String backsbApply() {
		String msg = procardTemplateGyServer.backsbApply(id);
		if (msg.equals("true")) {
			errorMessage = "打回成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_showSbApplyJd.action?bbAply.id=" + id;
		return "error";
	}

	/**
	 * 查询生产件设变后外购件增减情况列表
	 * 
	 * @return
	 */
	public String procardSbWgshowList() {
		if (procardsbwg != null) {
			ActionContext.getContext().getSession().put("procardsbwg",
					procardsbwg);
		} else {// 用来保持分页时带着查询条件
			procardsbwg = (ProcardSbWg) ActionContext.getContext().getSession()
					.get("procardsbwg");
		}
		Map<Integer, Object> map = procardTemplateGyServer
				.findprocardsbwgsByCondition(procardsbwg, Integer
						.parseInt(cpage), pageSize);
		procardsbwgList = (List<ProcardSbWg>) map.get(1);// 显示页的技能系数列表
		if (procardsbwgList != null & procardsbwgList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_procardSbWgshowList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "procardsbwg_show";
	}

	/**
	 * 生产件设变后需要增加外购件前往处理
	 * 
	 * @return
	 */
	public String toaddsbcg() {
		procardsbwg = procardTemplateGyServer.getProcardsbwgByid(id);
		return "procardsbwg_addcg";
	}

	/**
	 * 添加设变采购进入物料需求
	 * 
	 * @return
	 */
	public String addsbcgTowlxq() {
		String msg = procardTemplateGyServer.addsbcgTowlxq(procardsbwg);
		if (msg.equals("true")) {
			errorMessage = "进入物料需求成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_procardSbWgshowList.action?cpage="
				+ cpage;
		return "error";
	}

	/**
	 * 关闭不处理
	 * 
	 * @return
	 */
	public String closesbcg() {
		String msg = procardTemplateGyServer.closesbcg(id);
		if (msg.equals("true")) {
			errorMessage = "处理成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_procardSbWgshowList.action?cpage="
				+ cpage;
		return "error";
	}

	/**
	 * 跳往减单页面
	 * 
	 * @return
	 */
	public String todeletesbcg() {
		Map<Integer, Object> map = procardTemplateGyServer
				.findSbdeleteAboutcg(id);
		if (map != null) {
			procardsbwg = (ProcardSbWg) map.get(1);
			manyalOrderPlanList = (List<ManualOrderPlan>) map.get(2);
			waigouPlanList = (List<WaigouPlan>) map.get(3);
		}
		return "procardsbwg_deletecg";
	}

	/**
	 * 设变对物料需求减单
	 * 
	 * @return
	 */
	public String manyalOrderPlansbld() {
		String msg = procardTemplateGyServer.manyalOrderPlansbld(id, mop);
		if (msg.equals("true")) {
			errorMessage = "处理成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_todeletesbcg.action?id=" + id
				+ "&cpage=" + cpage;
		return "error";
	}

	/**
	 * 设变对外购订单减单
	 * 
	 * @return
	 */
	public String waigouPlansbld() {
		String msg = procardTemplateGyServer.waigouPlansbld(id, waigouplan);
		if (msg.equals("true")) {
			errorMessage = "申请处理成功!";
		} else {
			errorMessage = msg;
		}
		url = "procardTemplateGyAction_todeletesbcg.action?id=" + id
				+ "&cpage=" + cpage;
		return "error";
	}

	/**
	 * 查询生产返修单
	 * 
	 * @return
	 */
	public String findProcardReProductList() {
		if (preProduct != null) {
			ActionContext.getContext().getSession().put("preProduct",
					preProduct);
		} else {
			preProduct = (ProcardReProduct) ActionContext.getContext()
					.getSession().get("preProduct");
		}
		Object[] object = procardTemplateGyServer.findPreProduct(preProduct,
				Integer.parseInt(cpage), pageSize, pageStatus);
		if (object != null && object.length > 0) {
			preProductList = (List<ProcardReProduct>) object[0];
			if (preProductList != null && preProductList.size() > 0) {
				// size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this
						.setUrl("procardTemplateGyAction_findProcardReProductList.action");
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "procardReProduct_show";
	}

	/**
	 * 获取返修单
	 * 
	 * @return
	 */
	public String getfxd() {
		// preProduct = procardTemplateGyServer.getProcardReProductById(id);
		Object[] objs = procardTemplateGyServer.getProcardReProductMsgById(id);
		if (objs == null) {
			errorMessage = "没有找到目标返修单!";
			return "error";
		} else {
			preProduct = (ProcardReProduct) objs[0];
			if (objs[1] != null) {
				processInfor = (ProcessInfor) objs[1];
			}
			if (objs[2] != null) {
				prpFileList = (List<ProcardReProductFile>) objs[2];
			}
		}
		return "procardReProduct_update";
	}

	/**
	 * 上传返修工序图纸
	 * 
	 * @return
	 */
	public String updatefxtz() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录";
			return ERROR;
		}
		if (this.gygf != null) {
			String fileName = gygfFileName;
			// 文件路径
			String fileType = fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
			String realFileName = null;
			realFileName = Util.getDateTime("yyyyMMddHHmmss") + fileType;
			String realFilePath = "/upload/file/fxtz";
			// 打开存放上传文件的位置
			String path = ServletActionContext.getServletContext().getRealPath(
					realFilePath);
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();// 如果不存在文件夹就创建
			}
			prpFile = new ProcardReProductFile();
			prpFile.setFileName(realFileName);
			prpFile.setOldFileName(fileName);
			prpFile.setAddTime(Util.getDateTime());
			prpFile.setAddUser(user.getName());
			prpFile.setAddUserId(user.getId());
			prpFile.setAddCode(user.getCode());
			String msg = this.procardTemplateGyServer.savefxFile(this.prpFile,
					id);
			if (!msg.equals("true")) {
				errorMessage = msg;
				return ERROR;
			}
			Upload upload = new Upload();
			upload.UploadFile(gygf, fileName, realFileName, realFilePath, null);
		} else {
			errorMessage = "请选择文件!";
			url = "procardTemplateGyAction_getfxd.action?id=" + id;
			return ERROR;
		}
		errorMessage = "添加成功!";
		url = "procardTemplateGyAction_getfxd.action?id=" + id;
		return ERROR;

	}

	/**
	 * 设变影响外委记录列表展示
	 * 
	 * @return
	 */
	public String showWwSbList() {
		if (procardSbWw != null) {
			ActionContext.getContext().getSession().put("procardSbWw",
					procardSbWw);
		} else {
			procardSbWw = (ProcardSbWw) ActionContext.getContext().getSession()
					.get("procardSbWw");
		}
		Object[] obj = procardTemplateGyServer.findProcardSbWwList(procardSbWw,
				Integer.parseInt(cpage), pageSize);
		procardSbWwList = (List<ProcardSbWw>) obj[0];
		if (procardSbWwList != null && procardSbWwList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_showWwSbList.action");
			errorMessage = null;
		}
		return "procardSbWw_show";
	}

	public String findwwSbforJudeg() {
		Object[] objs = procardTemplateGyServer.findwwSbforJudeg(id);
		if (objs != null) {
			procardSbWw = (ProcardSbWw) objs[0];
			if (procardSbWw.getWwSource().equals("手动外委")) {
				if (procardSbWw.getWaigouPlanId() != null) {
					waigouplan = (WaigouPlan) objs[1];
				} else {
					wwApplyDetail = (ProcessInforWWApplyDetail) objs[1];
				}
			} else {
				if (procardSbWw.getWaigouPlanId() != null) {
					waigouplan = (WaigouPlan) objs[1];
				} else {
					waigouWaiweiPlanList = (List<WaigouWaiweiPlan>) objs[1];
				}
			}
			return "procardSbWw_toJudeg";
		}
		errorMessage = "没有找到目标!";
		return "error";
	}

	/**
	 * 终止外委订单
	 */
	public String wwsbbreakwP() {
		try {
			String msg = procardTemplateGyServer.wwsbbreakwP(id, id2);
			if (msg.equals("true")) {
				errorMessage = "取消成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 终止待委外序列
	 */
	public String wwsbbreakbomwwp() {
		try {
			String msg = procardTemplateGyServer.wwsbbreakbomwwp(id, id2);
			if (msg.equals("true")) {
				errorMessage = "取消成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 终止手动外委申请
	 */
	public String wwsbbreaksdd() {
		try {
			String msg = procardTemplateGyServer.wwsbbreaksdd(id, id2);
			if (msg.equals("true")) {
				errorMessage = "取消成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 调整外委单
	 * 
	 * @return
	 */
	public String toUpdateSbwp() {
		Object[] objs = procardTemplateGyServer.toUpdateSbwp(id, id2);
		String msg = objs[0].toString();
		if (msg.equals("true1")) {
			errorMessage = objs[1].toString();
			url = "procardTemplateGyAction_findwwSbforJudeg.action?id=" + id;
		} else if (msg.equals("true")) {
			procardSbWw = (ProcardSbWw) objs[1];
			waigouplan = (WaigouPlan) objs[2];
			procard = (Procard) objs[3];
			processInforList = (List<ProcessInfor>) objs[4];
			if (waigouplan.getWwSource().equals("手动外委")) {
				return "procardSbWw_updatesdwp";
			} else {
				return "procardSbWw_updatebomwp";
			}
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 前往调整外委申请
	 * 
	 * @return
	 */
	public String toUpdatesdd() {
		Object[] objs = procardTemplateGyServer.toUpdatesdd(id, id2);
		String msg = objs[0].toString();
		if (msg.equals("true1")) {
			errorMessage = objs[1].toString();
			url = "procardTemplateGyAction_findwwSbforJudeg.action?id=" + id;
		} else if (msg.equals("true")) {
			procardSbWw = (ProcardSbWw) objs[1];
			wwApplyDetail = (ProcessInforWWApplyDetail) objs[2];
			procard = (Procard) objs[3];
			processInforList = (List<ProcessInfor>) objs[4];
			if (waigouplan.getWwSource().equals("手动外委")) {
				return "procardSbWw_updatesdd";
			}
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 前往调整外委申请
	 * 
	 * @return
	 */
	public String toUpdatebomwwp() {
		Object[] objs = procardTemplateGyServer.toUpdatebomwwp(id, id2);
		String msg = objs[0].toString();
		if (msg.equals("true1")) {
			errorMessage = objs[1].toString();
			url = "procardTemplateGyAction_findwwSbforJudeg.action?id=" + id;
		} else if (msg.equals("true")) {
			procardSbWw = (ProcardSbWw) objs[1];
			waigouWaiweiPlan = (WaigouWaiweiPlan) objs[2];
			procard = (Procard) objs[3];
			processInforList = (List<ProcessInfor>) objs[4];
			if (waigouplan.getWwSource().equals("手动外委")) {
				return "procardSbWw_updatebomwwp";
			}
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 替换手动外委生成的外委单
	 * 
	 * @return
	 */
	public String updateSbsdwp() {
		try {
			if (checkboxs == null || checkboxs.length == 0) {
				errorMessage = "请选择外委的工序!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			}
			String msg = procardTemplateGyServer.updateSbsdwp(wwApplyDetail,
					id, id2, procard, checkboxs);
			if (msg.equals("true")) {
				errorMessage = "外委成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 替换外委申请单
	 * 
	 * @return
	 */
	public String updateSbsdd() {
		try {
			if (checkboxs == null || checkboxs.length == 0) {
				errorMessage = "请选择外委的工序!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			}
			String msg = procardTemplateGyServer.updateSbsdd(wwApplyDetail, id,
					id2, procard, checkboxs);
			if (msg.equals("true")) {
				errorMessage = "外委成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 替换BOM外委生成的外委单
	 * 
	 * @return
	 */
	public String updateSbbomwp() {
		try {
			if (checkboxs == null || checkboxs.length == 0) {
				errorMessage = "请选择外委的工序!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			}
			String msg = procardTemplateGyServer.updateSbbomwp(id, id2,
					procard, checkboxs);
			if (msg.equals("true")) {
				errorMessage = "外委成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	/**
	 * 替换外委序列
	 * 
	 * @return
	 */
	public String updateSbbomwwp() {
		try {
			if (checkboxs == null || checkboxs.length == 0) {
				errorMessage = "请选择外委的工序!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			}
			String msg = procardTemplateGyServer.updateSbbomwwp(id, id2,
					procard, checkboxs);
			if (msg.equals("true")) {
				errorMessage = "外委成功!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	public String jxsbwp() {
		try {
			String msg = procardTemplateGyServer.jxsbwp(id, id2);
			if (msg.equals("true")) {
				errorMessage = "操作成功,继续此订单!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	public String jxsbsdd() {
		try {
			String msg = procardTemplateGyServer.jxsbsdd(id, id2);
			if (msg.equals("true")) {
				errorMessage = "操作成功,继续此外委申请!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	public String jxsbbomwwp() {
		try {
			String msg = procardTemplateGyServer.jxsbbomwwp(id, id2);
			if (msg.equals("true")) {
				errorMessage = "操作成功,继续此外委!";
				url = "procardTemplateGyAction_findwwSbforJudeg.action?id="
						+ id;
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
		}
		return "error";
	}

	public String findProcardSbWasterList() {
		if (procardSbWaster != null) {
			ActionContext.getContext().getSession().put("procardSbWaster",
					procardSbWaster);
		} else {
			procardSbWaster = (ProcardSbWaster) ActionContext.getContext()
					.getSession().get("procardSbWaster");
		}
		Object[] obj = procardTemplateGyServer.findProcardSbWasterList(
				procardSbWaster, Integer.parseInt(cpage), pageSize);
		procardSbWasterList = (List<ProcardSbWaster>) obj[0];
		if (procardSbWasterList != null && procardSbWasterList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("procardTemplateGyAction_findProcardSbWasterList.action");
			errorMessage = null;
		}
		return "procardSbWaster_show";
	}

	/**
	 * 前往判定设变在制品
	 * 
	 * @return
	 */
	public String toJudegWaster() {
		procardSbWaster = procardTemplateGyServer.getProcardSbWasterById(id);
		return "procardSbWaster_judeg";
	}

	/**
	 * 判断设变在制品处理方案
	 * 
	 * @return
	 */
	public String judegWaster() {
		try {
			String msg = procardTemplateGyServer.judegWaster(procardSbWaster);
			if (msg.equals("true")) {
				errorMessage = "判定成功!";
				url = "procardTemplateGyAction_findProcardSbWasterList.action";
			} else {
				errorMessage = msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
			return "error";
		}
		return "erorr";
	}

	public String findProcardsbWasterxcList() {
		if (procardSbWasterxc != null) {
			ActionContext.getContext().getSession().put("procardSbWasterxc",
					procardSbWasterxc);
		} else {
			procardSbWasterxc = (ProcardSbWasterxc) ActionContext.getContext()
					.getSession().get("procardSbWasterxc");
		}
		if (procardSbWaster != null) {
			ActionContext.getContext().getSession().put("procardSbWasterformx",
					procardSbWaster);
		} else {
			procardSbWaster = (ProcardSbWaster) ActionContext.getContext()
					.getSession().get("procardSbWasterformx");
		}
		Object[] obj = procardTemplateGyServer.finddrkProcardSbWasterxcList(
				procardSbWasterxc, procardSbWaster, Integer.parseInt(cpage),
				pageSize);
		procardSbWasterxcList = (List<ProcardSbWasterxc>) obj[0];
		if (procardSbWasterxcList != null && procardSbWasterxcList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("procardTemplateGyAction_findProcardsbWasterxcList.action");
			errorMessage = null;
		}
		return "procardSbWasterxc_drkshow";
	}

	/**
	 * 通过BOM零件Id查看对应的设变单
	 * 
	 * @return
	 */
	public String tosbjd() {
		Users user = Util.getLoginUser();
		if (user == null) {
			errorMessage = "请先登录";
			return "error";
		}

		Object[] objs = procardTemplateGyServer.getsbjdByProcardTId(id, null);
		if (objs != null) {
			if (objs[0] != null) {
				bbAply = (ProcardTemplateBanBenApply) objs[0];
			}
			if (objs[1] != null) {
				pabbList = (List<ProcardAboutBanBenApply>) objs[1];// 同总成
			}
			if (objs[2] != null) {
				pabbList2 = (List<ProcardAboutBanBenApply>) objs[2];// 非同总成
			}
		} else {
			errorMessage = "没有找到对应的设变单!";
			return "error";
		}
		return "sbApply_showjd";
	}

	/**
	 * 前往接收设变报废页面
	 * 
	 * @return
	 */
	public String sbWasterTobf() {
		procardSbWasterxc = procardTemplateGyServer
				.getProcardSbWasterxcById(id);
		return "procardSbWasterxc_tobf";
	}

	/**
	 * 前往接收设变退库页面
	 * 
	 * @return
	 */
	public String sbWasterToback() {
		procardSbWasterxc = procardTemplateGyServer
				.getProcardSbWasterxcById(id);
		return "procardSbWasterxc_toback";
	}

	/**
	 * 查看本版次修改日志
	 * 
	 * @return
	 */
	public String changeLogshow() {
		ptchangeLogList = procardTemplateGyServer.getChangeLogshow(id);
		return "ptchangeLog_show";
	}

	public String todaoruson() {
		return "procardTem_daoruson";
	}

	public String daoRusonbom() {
		try {
			errorMessage = (String) ActionContext.getContext().getApplication()
					.get("BOMDaoru");
			if (errorMessage == null) {
				Users loginUser = Util.getLoginUser();
				ActionContext.getContext().getApplication().put(
						"BOMDaoru",
						loginUser.getDept() + "的" + loginUser.getName()
								+ "正在导入BOM,请稍等片刻~~或者去他那喝杯茶吧~~");
			} else {
				return ERROR;
			}
			ProcardTemplate fatherPt = procardTemplateGyServer
					.getProcardTemplateById(id);
			String msg = null;
			if (fatherPt.getProductStyle().equals("批产")) {
				msg = procardTemplateGyServer.daoRuHwBom(bomTree,
						bomTreeFileName, id);
			} else {
				msg = procardTemplateGyServer.daoRuHwSZBom(bomTree,
						bomTreeFileName, id);
			}
			if (msg == null || msg.length() == 0 || msg.equals("true")) {
				successMessage = "导入成功";
			} else {
				successMessage = msg;
			}
			ActionContext.getContext().getApplication().put("BOMDaoru", null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			successMessage = e.getMessage();
		}
		ActionContext.getContext().getApplication().put("BOMDaoru", null);
		return "procardTem_daoruson";

	}

	/**
	 * 删除错误文件
	 */
	public void deleteerrorFile() {
		String msg = procardTemplateGyServer.deleteerrorFile();
		MKUtil.writeJSON(msg);
	}

	/**
	 * 弥补设变关联生产
	 */
	public void mibuglsc() {// procardTemplateGyAction_mibuglsc.action?id=
		String msg = procardTemplateGyServer.mibuglsc(id);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 导出设变信息
	 * 
	 * @return
	 */
	public void exprotBbAply() {
		procardTemplateGyServer.exprotBbAply(bbAply);
	}

	/**
	 * 导出设变信息
	 * 
	 * @return
	 */
	public void exprotBbAply_new() {
		procardTemplateGyServer.exprotBbAply_new(bbAply, startDate, endDate);
	}

	/**
	 * 前往添加设变台账
	 * 
	 * @return
	 */
	public String toaddTechnicalchangeLog() {
		return "tcl_add";
	}

	/**
	 * 添加EC
	 * 
	 * @return
	 */
	public String addtclog() {
		String msg = procardTemplateGyServer.addtclog(technicalchangeLog,
				checkboxs);
		if (msg.equals("true")) {
			errorMessage = "添加成功";
			url = "procardTemplateGyAction_toaddTechnicalchangeLog.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	public String tclshowListi() {
		type = "1";
		return tclshowList("in", "procardTemplateGyAction_tclshowListi.action");
	}

	public String tclshowListo() {
		type = "2";
		return tclshowList("out", "procardTemplateGyAction_tclshowListo.action");
	}

	public String tclshowLista() {
		type = "3";
		return tclshowList("all", "procardTemplateGyAction_tclshowLista.action");
	}

	/**
	 * 展示EC列表
	 * 
	 * @return
	 */
	public String tclshowList(String tag, String thisurl) {
		if (technicalchangeLog != null) {
			ActionContext.getContext().getSession().put("technicalchangeLog",
					technicalchangeLog);
		} else {// 用来保持分页时带着查询条件
			technicalchangeLog = (TechnicalchangeLog) ActionContext
					.getContext().getSession().get("technicalchangeLog");
		}
		if (start != null) {
			ActionContext.getContext().getSession().put("tclstart", start);
		} else {// 用来保持分页时带着查询条件
			start = (String) ActionContext.getContext().getSession().get(
					"tclstart");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("tclend", start);
		} else {// 用来保持分页时带着查询条件
			end = (String) ActionContext.getContext().getSession()
					.get("tclend");
		}
		Map<Integer, Object> map = procardTemplateGyServer
				.findTechnicalchangeLogByCondition(technicalchangeLog, Integer
						.parseInt(cpage), pageSize, start, end, tag);
		tclList = (List<TechnicalchangeLog>) map.get(1);// 显示页的技能系数列表
		if (tclList != null & tclList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl(thisurl);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "tcl_show";
	}

	/**
	 * 前往修改EC
	 * 
	 * @return
	 */
	public String totclupdate() {
		technicalchangeLog = procardTemplateGyServer.gettclById2(id);
		return "tcl_update";
	}

	/**
	 * 修改EC
	 * 
	 * @return
	 */
	public String updatetclog() {
		String msg = procardTemplateGyServer.updatetclog(technicalchangeLog,
				checkboxs);
		if (msg.equals("true")) {
			errorMessage = "修改成功!";
			if (type.equals("1")) {
				url = "procardTemplateGyAction_tclshowListi.action";
			} else if (type.equals("2")) {
				url = "procardTemplateGyAction_tclshowListo.action";
			}
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 删除EC
	 * 
	 * @return
	 */
	public String totcldelete() {
		String msg = procardTemplateGyServer.detetetcl(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			if (type.equals("1")) {
				url = "procardTemplateGyAction_tclshowListi.action";
			} else if (type.equals("2")) {
				url = "procardTemplateGyAction_tclshowListo.action";
			}
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 前往添加pcn
	 * 
	 * @return
	 */
	public String toaddpcn() {
		return "pcn_add";
	}

	public String addpcn() {
		List<ProcessChangeNoticeFile> pcnFileList = new ArrayList<ProcessChangeNoticeFile>();
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				ProcessChangeNoticeFile pcnFile = new ProcessChangeNoticeFile();
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS_")
						+ i + fileType;

				String realFilePath = "/upload/file/pcn";
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}

				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				pcnFile.setOldFileName(fileName);
				pcnFile.setFilename(realFileName);
				pcnFile.setAddTime(Util.getDateTime());
				pcnFile.setAddUserId(user.getId());// 添加人Id
				pcnFile.setAddUserName(user.getName());//
				pcnFile.setAddUserCode(user.getCode());//
				pcnFileList.add(pcnFile);
			}
		}
		String msg = procardTemplateGyServer.addpcn(pcn, pcnFileList);
		if (msg.equals("true")) {
			errorMessage = "添加成功";
			url = "procardTemplateGyAction_toaddpcn.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 上传pcn文件
	 * 
	 * @return
	 */
	public String updatepcnFile() {
		List<ProcessChangeNoticeFile> pcnFileList = new ArrayList<ProcessChangeNoticeFile>();
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				ProcessChangeNoticeFile pcnFile = new ProcessChangeNoticeFile();
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS_")
						+ i + fileType;

				String realFilePath = "/upload/file/pcn";
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}

				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				pcnFile.setOldFileName(fileName);
				pcnFile.setFilename(realFileName);
				pcnFile.setAddTime(Util.getDateTime());
				pcnFile.setAddUserId(user.getId());// 添加人Id
				pcnFile.setAddUserName(user.getName());//
				pcnFile.setAddUserCode(user.getCode());//
				pcnFileList.add(pcnFile);
			}
		}
		String msg = procardTemplateGyServer.addpcnFile(id, pcnFileList);
		if (msg.equals("true")) {
			errorMessage = "添加成功";
			url = "procardTemplateGyAction_pcnfileshow.action?id=" + id;
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 展示pcn
	 * 
	 * @return
	 */
	public String pcnshowList() {
		if (pcn != null) {
			ActionContext.getContext().getSession().put("pcn", pcn);
		} else {// 用来保持分页时带着查询条件
			pcn = (ProcessChangeNotice) ActionContext.getContext().getSession()
					.get("pcn");
		}
		if (start != null) {
			ActionContext.getContext().getSession().put("pcnstart", start);
		} else {// 用来保持分页时带着查询条件
			start = (String) ActionContext.getContext().getSession().get(
					"pcnstart");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("pcnend", start);
		} else {// 用来保持分页时带着查询条件
			end = (String) ActionContext.getContext().getSession()
					.get("pcnend");
		}
		if (pcn == null) {
			pcn = new ProcessChangeNotice();
		}
		pcn.setIsend("未结案");
		list = procardTemplateGyServer.findwjaPcnByCondition(pcn, start, end);
		pcn.setIsend("已结案");
		Map<Integer, Object> map = procardTemplateGyServer.findPcnByCondition(
				pcn, Integer.parseInt(cpage), pageSize, start, end);
		pcnList = (List<ProcessChangeNotice>) map.get(1);// 显示页的技能系数列表
		if (pcnList != null & pcnList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("procardTemplateGyAction_pcnshowList.action");
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "pcn_show";
	}

	/**
	 * 展示待处理pcn
	 * 
	 * @return
	 */
	public String pcndclshowList() {
		if (pcn == null) {
			pcn = new ProcessChangeNotice();
		}
		pcn.setIsend("未结案");
		list = procardTemplateGyServer.findwjaPcnByCondition(pcn, null, null);
		pcn.setIsend(null);
		pcn.setFeedbackoption("拒绝");
		list2 = procardTemplateGyServer.findwjaPcnByCondition(pcn, null, null);
		return "pcn_show2";
	}

	/**
	 * pcn文件展示
	 * 
	 * @return
	 */
	public String pcnfileshow() {
		pcn = procardTemplateGyServer.getpcnById(id);
		pcnFileList = procardTemplateGyServer.findPcnfilesBypcnId(id);
		return "pcn_showfile";
	}

	/**
	 * 前往修改pcn
	 * 
	 * @return
	 */
	public String topcnupdate() {
		pcn = procardTemplateGyServer.getpcnById(id);
		return "pcn_update";
	}

	/**
	 * 修改EC
	 * 
	 * @return
	 */
	public String updatepcn() {
		String msg = procardTemplateGyServer.updatepcn(pcn);
		if (msg.equals("true")) {
			errorMessage = "修改成功!";
			url = "procardTemplateGyAction_pcnshowList.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 删除EC
	 * 
	 * @return
	 */
	public String topcndelete() {
		String msg = procardTemplateGyServer.detetepcn(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			url = "procardTemplateGyAction_pcnshowList.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 删除pcn文件
	 * 
	 * @return
	 */
	public String deletepcnFile() {
		pcn = procardTemplateGyServer.getpcnByfileId(id);
		String msg = procardTemplateGyServer.detetepcnFile(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			url = "procardTemplateGyAction_pcnfileshow.action?id="
					+ pcn.getId();
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 下载pcn文件
	 * 
	 * @return
	 */
	public String downloadPcnFile() {
		pcnfile = procardTemplateGyServer.getpcnfileByfileId(id);
		if (pcnfile != null) {
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file/pcn")
					+ "/" + pcnfile.getFilename();
			File file = new File(fileRealPath);
			realPath = "/upload/file/pcn" + "/" + pcnfile.getFilename();
			if (file.exists()) {
				// 返回时的名字
				gygfFileName = pcnfile.getOldFileName();
				return "download";
			} else {
				errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
				return "error";
			}
		}
		errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}

	/**
	 * 前往添加e-car
	 * 
	 * @return
	 */
	public String toaddecar() {
		return "ecar_add";
	}

	/**
	 * 添加ecar
	 * 
	 * @return
	 */
	public String addecar() {
		List<DesignfeedbackNoticeFile> ecarFileList = new ArrayList<DesignfeedbackNoticeFile>();
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				DesignfeedbackNoticeFile ecarFile = new DesignfeedbackNoticeFile();
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS_")
						+ i + fileType;

				String realFilePath = "/upload/file/ecar";
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}

				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				ecarFile.setOldFileName(fileName);
				ecarFile.setFilename(realFileName);
				ecarFile.setAddTime(Util.getDateTime());
				ecarFile.setAddUserId(user.getId());// 添加人Id
				ecarFile.setAddUserName(user.getName());//
				ecarFile.setAddUserCode(user.getCode());//
				ecarFileList.add(ecarFile);
			}
		}
		String msg = procardTemplateGyServer.addecar(ecar, ecarFileList);
		if (msg.equals("true")) {
			errorMessage = "添加成功";
			url = "procardTemplateGyAction_toaddecar.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 展示ecar
	 * 
	 * @return
	 */
	public String ecarshowList() {
		if (ecar != null) {
			ActionContext.getContext().getSession().put("ecar", ecar);
		} else {// 用来保持分页时带着查询条件
			ecar = (DesignfeedbackNotice) ActionContext.getContext()
					.getSession().get("ecar");
		}
		if (start != null) {
			ActionContext.getContext().getSession().put("ecarstart", start);
		} else {// 用来保持分页时带着查询条件
			start = (String) ActionContext.getContext().getSession().get(
					"ecarstart");
		}
		if (end != null) {
			ActionContext.getContext().getSession().put("ecarend", start);
		} else {// 用来保持分页时带着查询条件
			end = (String) ActionContext.getContext().getSession().get(
					"ecarend");
		}
		if (ecar == null) {
			ecar = new DesignfeedbackNotice();
		}
		String zxStatus = ecar.getZxstatus();
		if (zxStatus == null || zxStatus.length() == 0) {
			ecar.setZxstatus("待处理");
			list = procardTemplateGyServer
					.findEcarByCondition(ecar, start, end);
		} else if (zxStatus.equals("待创建人确认") || zxStatus.equals("待处理人处理")
				|| zxStatus.equals("待处理试制报告") || zxStatus.equals("待提交试制报告")
				|| zxStatus.equals("待回归试制报告") || zxStatus.equals("待审核试制报告")) {
			ecar.setZxstatus(zxStatus);
			list = procardTemplateGyServer
					.findEcarByCondition(ecar, start, end);
		} else {
			list = new ArrayList<Object>();
		}
		if (zxStatus == null || zxStatus.length() == 0
				|| zxStatus.equals("已关闭")) {
			ecar.setZxstatus("已关闭");
			Map<Integer, Object> map = procardTemplateGyServer
					.findEcarByCondition(ecar, Integer.parseInt(cpage),
							pageSize, start, end);
			ecarList = (List<DesignfeedbackNotice>) map.get(1);// 显示页的技能系数列表
			if (ecarList != null & ecarList.size() > 0) {
				int count = (Integer) map.get(2);
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("procardTemplateGyAction_ecarshowList.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if (zxStatus == null || zxStatus.length() == 0
				|| zxStatus.equals("已驳回")) {
			ecar.setZxstatus("已驳回");
			list2 = procardTemplateGyServer.findEcarByCondition(ecar, start,
					end);
		}
		if (zxStatus == null || zxStatus.length() == 0
				|| zxStatus.equals("已取消")) {
			ecar.setZxstatus("已取消");
			list3 = procardTemplateGyServer.findEcarByCondition(ecar, start,
					end);
		}
		ecar.setZxstatus(zxStatus);
		return "ecar_show";
	}

	/**
	 * 展示待处理ecar
	 * 
	 * @return
	 */
	public String ecardclshowList() {
		if (ecar == null) {
			ecar = new DesignfeedbackNotice();
		}
		ecar.setZxstatus("待处理");
		list = procardTemplateGyServer.findEcarByCondition(ecar, null, null);
		return "ecar_show2";
	}

	/**
	 * 添加ecar文件
	 * 
	 * @return
	 */
	public String updateecarFile() {
		List<DesignfeedbackNoticeFile> ecarFileList = new ArrayList<DesignfeedbackNoticeFile>();
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				DesignfeedbackNoticeFile ecarFile = new DesignfeedbackNoticeFile();
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS_")
						+ i + fileType;

				String realFilePath = "/upload/file/ecar";
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}

				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				ecarFile.setOldFileName(fileName);
				ecarFile.setFilename(realFileName);
				ecarFile.setAddTime(Util.getDateTime());
				ecarFile.setAddUserId(user.getId());// 添加人Id
				ecarFile.setAddUserName(user.getName());//
				ecarFile.setAddUserCode(user.getCode());//
				ecarFileList.add(ecarFile);
			}
		}
		String msg = procardTemplateGyServer.addecarFile(id, ecarFileList);
		if (msg.equals("true")) {
			errorMessage = "添加成功";
			url = "procardTemplateGyAction_ecarfileshow.action?id=" + id;
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * ecar文件展示
	 * 
	 * @return
	 */
	public String ecarfileshow() {
		ecar = procardTemplateGyServer.getecarById(id);
		ecarfileList = procardTemplateGyServer.findEcarfilesByecarId(id);
		return "ecar_showfile";
	}

	/**
	 * 前往修改pcn
	 * 
	 * @return
	 */
	public String toecarupdate() {
		ecar = procardTemplateGyServer.getecarById(id);
		return "ecar_update";
	}

	/**
	 * 修改EC
	 * 
	 * @return
	 */
	public String updateecar() {
		String msg = procardTemplateGyServer.updateecar(ecar);
		if (msg.equals("true")) {
			errorMessage = "修改成功!";
			url = "procardTemplateGyAction_ecarshowList.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 删除ecar
	 * 
	 * @return
	 */
	public String toecardelete() {
		String msg = procardTemplateGyServer.deteteecar(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			url = "procardTemplateGyAction_ecarshowList.action";
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 删除ecar文件
	 * 
	 * @return
	 */
	public String deleteecarFile() {
		ecar = procardTemplateGyServer.getecarByfileId(id);
		String msg = procardTemplateGyServer.deteteecarFile(id);
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			url = "procardTemplateGyAction_ecarfileshow.action?id="
					+ ecar.getId();
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 下载ecar文件
	 * 
	 * @return
	 */
	public String downloadEcarFile() {
		ecarfile = procardTemplateGyServer.getecarfileByfileId(id);
		if (ecarfile != null) {
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file/ecar")
					+ "/" + ecarfile.getFilename();
			File file = new File(fileRealPath);
			realPath = "/upload/file/ecar" + "/" + ecarfile.getFilename();
			if (file.exists()) {
				// 返回时的名字
				gygfFileName = ecarfile.getOldFileName();
				return "download";
			} else {
				errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
				return "error";
			}
		}
		errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}

	public String uploadzzfile() {
		List<ProcardTemplateBanBenJudgesFile> ptbbjFileList = new ArrayList<ProcardTemplateBanBenJudgesFile>();
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				ProcardTemplateBanBenJudgesFile pjFile = new ProcardTemplateBanBenJudgesFile();
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName = Util.getDateTime("yyyyMMddHHmmssSSS_")
						+ i + fileType;

				String realFilePath = "/upload/file/ptbbjfile";
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}

				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				pjFile.setOldFileName(fileName);
				pjFile.setFilename(realFileName);
				pjFile.setAddTime(Util.getDateTime());
				pjFile.setAddUserId(user.getId());// 添加人Id
				pjFile.setAddUserName(user.getName());//
				pjFile.setAddUserCode(user.getCode());//
				pjFile.setPtbbjId(id);
				ptbbjFileList.add(pjFile);
			}
			Map<Integer, Object> map = procardTemplateGyServer.uploadzzfile(id,
					ptbbjFileList);
			String msg = map.get(1).toString();
			if (msg.equals("true")) {
				errorMessage = "上传成功!";
				url = "procardTemplateGyAction_showSbApplyJd.action?bbAply.id="
						+ map.get(2).toString();
			} else {
				errorMessage = msg;
			}
			return "error";

		}
		errorMessage = "请选择文件再上传!";
		return "error";
	}

	/**
	 * 评审设变佐证
	 */
	public void plzz() {
		String msg = procardTemplateGyServer.plzz(ptbbjfile);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 删除佐证
	 */
	public void deletezz() {
		String msg = procardTemplateGyServer.deletezz(id);
		MKUtil.writeJSON(msg);
	}

	/**
	 * 下载佐证文件
	 */
	public String downloadzzFile() {
		ptbbjfile = procardTemplateGyServer.getPtbbjFileByfileId(id);
		if (ptbbjfile != null) {
			String fileRealPath = ServletActionContext.getServletContext()
					.getRealPath("/upload/file/ptbbjfile")
					+ "/" + ptbbjfile.getFilename();
			File file = new File(fileRealPath);
			realPath = "/upload/file/ptbbjfile" + "/" + ptbbjfile.getFilename();
			if (file.exists()) {
				// 返回时的名字
				gygfFileName = ptbbjfile.getOldFileName();
				return "download";
			} else {
				errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
				return "error";
			}
		}
		errorMessage = "您要下载的文件已经被删除,或者不存在此文件!请重试";
		return "error";
	}

	public void addzzry() {
		try {
			String msg = procardTemplateGyServer.addzzry(id, ptbbJudges);
			MKUtil.writeJSON(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}

	}

	public void exportProcessExcel() {
		procardTemplateGyServer.exportProcessExcel(id);
	}

	//
	public String findProcessAndWgProcard() {
		if (pawp != null) {
			ActionContext.getContext().getSession().put("pawp", pawp);
		} else {// 用来保持分页时带着查询条件
			pawp = (ProcessAndWgProcardTem) ActionContext.getContext()
					.getSession().get("pawp");
		}
		Object[] obj = procardTemplateGyServer.findProcessAndWgProcard(
				pageSize, Integer.parseInt(cpage), pageStatus, pawp);
		if (obj != null && obj.length == 2) {
			pawpList = (List<ProcessAndWgProcardTem>) obj[0];
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("procardTemplateGyAction_findProcessAndWgProcard.action?pageStatus="
							+ pageStatus);
		}
		return "pawp_List";
	}
	
	public void addsonProcard(){//procardTemplateGyAction_addsonProcard.action?id=&id2=
		try {
			String msg = procardTemplateGyServer.addsonProcard(id, id2,null,null);
			MKUtil.writeJSON(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}
	public void copySonsPttoProcard(){//procardTemplateGyAction_copySonsPttoProcard.action?id=&id2=
		try {//将Id(procardTemplate)下面的东西复制到id2(procard)下面
			String msg = procardTemplateGyServer.copySonsPttoProcard(id, id2);
			MKUtil.writeJSON(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}
	
	public void copySonProcardTemplates(){//procardTemplateGyAction_copySonProcardTemplates.action?id=&id2=
		try {//将Id下面的东西复制到id2下面
			String msg = procardTemplateGyServer.copySonProcardTemplates(id, id2);
			MKUtil.writeJSON(msg);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}
	

	// 导出工序外购件关联
	public void exprotPwap() {
		procardTemplateGyServer.exprotPwap(pawp);
	}
	
	/***
	 * 设变时间分析
	 */
	public void findSbTime(){
		MKUtil.writeJSON(procardTemplateGyServer.findSbTime(id,startDate,endDate));
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

	public List<ProcardTemplate> getProcardTemplateList() {
		return procardTemplateList;
	}

	public void setProcardTemplateList(List<ProcardTemplate> procardTemplateList) {
		this.procardTemplateList = procardTemplateList;
	}

	public List<ProcessTemplate> getProcessTemplateList() {
		return processTemplateList;
	}

	public void setProcessTemplateList(List<ProcessTemplate> processTemplateList) {
		this.processTemplateList = processTemplateList;
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

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public ProcardTemplateGyServer getProcardTemplateGyServer() {
		return procardTemplateGyServer;
	}

	public void setProcardTemplateGyServer(
			ProcardTemplateGyServer procardTemplateGyServer) {
		this.procardTemplateGyServer = procardTemplateGyServer;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaxBelongLayer() {
		return maxBelongLayer;
	}

	public void setMaxBelongLayer(Integer maxBelongLayer) {
		this.maxBelongLayer = maxBelongLayer;
	}

	public File getGygf() {
		return gygf;
	}

	public void setGygf(File gygf) {
		this.gygf = gygf;
	}

	public String getGygfFileName() {
		return gygfFileName;
	}

	public void setGygfFileName(String gygfFileName) {
		this.gygfFileName = gygfFileName;
	}

	public String getGygfFileContentType() {
		return gygfFileContentType;
	}

	public void setGygfFileContentType(String gygfFileContentType) {
		this.gygfFileContentType = gygfFileContentType;
	}

	public ProcessTemplateFile getProcessTemplateFile() {
		return processTemplateFile;
	}

	public void setProcessTemplateFile(ProcessTemplateFile processTemplateFile) {
		this.processTemplateFile = processTemplateFile;
	}

	public YuanclAndWaigj getYclAndWgj() {
		return yclAndWgj;
	}

	public void setYclAndWgj(YuanclAndWaigj yclAndWgj) {
		this.yclAndWgj = yclAndWgj;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public File getBomTree() {
		return bomTree;
	}

	public void setBomTree(File bomTree) {
		this.bomTree = bomTree;
	}

	public String getBomTreeFileName() {
		return bomTreeFileName;
	}

	public void setBomTreeFileName(String bomTreeFileName) {
		this.bomTreeFileName = bomTreeFileName;
	}

	public String getBomTreeFileContentType() {
		return bomTreeFileContentType;
	}

	public void setBomTreeFileContentType(String bomTreeFileContentType) {
		this.bomTreeFileContentType = bomTreeFileContentType;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ProcardTemplateBanBen getPtbb() {
		return ptbb;
	}

	public void setPtbb(ProcardTemplateBanBen ptbb) {
		this.ptbb = ptbb;
	}

	public ProcardTemplateBanBenApply getBbAply() {
		return bbAply;
	}

	public void setBbAply(ProcardTemplateBanBenApply bbAply) {
		this.bbAply = bbAply;
	}

	public List<ProcardTemplateBanBenApply> getBbAplyList() {
		return bbAplyList;
	}

	public void setBbAplyList(List<ProcardTemplateBanBenApply> bbAplyList) {
		this.bbAplyList = bbAplyList;
	}

	public List<ProcardTemplateBanBen> getPtbbList() {
		return ptbbList;
	}

	public void setPtbbList(List<ProcardTemplateBanBen> ptbbList) {
		this.ptbbList = ptbbList;
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

	public ProcardTBanbenRelation getPtbbRelation() {
		return ptbbRelation;
	}

	public void setPtbbRelation(ProcardTBanbenRelation ptbbRelation) {
		this.ptbbRelation = ptbbRelation;
	}

	public List<ProcardTBanbenRelation> getPtbbRelationList() {
		return ptbbRelationList;
	}

	public void setPtbbRelationList(
			List<ProcardTBanbenRelation> ptbbRelationList) {
		this.ptbbRelationList = ptbbRelationList;
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

	public String getYtRadio() {
		return ytRadio;
	}

	public void setYtRadio(String ytRadio) {
		this.ytRadio = ytRadio;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int[] getCheckboxs() {
		return checkboxs;
	}

	public void setCheckboxs(int[] checkboxs) {
		this.checkboxs = checkboxs;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public List<ProcardAboutBanBenApply> getPabbList() {
		return pabbList;
	}

	public void setPabbList(List<ProcardAboutBanBenApply> pabbList) {
		this.pabbList = pabbList;
	}

	public List<ProcardAboutBanBenApply> getPabbList2() {
		return pabbList2;
	}

	public void setPabbList2(List<ProcardAboutBanBenApply> pabbList2) {
		this.pabbList2 = pabbList2;
	}

	public ProcardAboutBanBenApply getPabb() {
		return pabb;
	}

	public void setPabb(ProcardAboutBanBenApply pabb) {
		this.pabb = pabb;
	}

	public ProcardTemplateBanBenJudges getPtbbJudges() {
		return ptbbJudges;
	}

	public void setPtbbJudges(ProcardTemplateBanBenJudges ptbbJudges) {
		this.ptbbJudges = ptbbJudges;
	}

	public List<UserDept> getUserDeptList() {
		return userDeptList;
	}

	public void setUserDeptList(List<UserDept> userDeptList) {
		this.userDeptList = userDeptList;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public List<ProcardBanBenJudge> getPbbJudgeList() {
		return pbbJudgeList;
	}

	public void setPbbJudgeList(List<ProcardBanBenJudge> pbbJudgeList) {
		this.pbbJudgeList = pbbJudgeList;
	}

	public ProcardBanBenJudge getPbbJudge() {
		return pbbJudge;
	}

	public void setPbbJudge(ProcardBanBenJudge pbbJudge) {
		this.pbbJudge = pbbJudge;
	}

	public List<ProcardBanBenJudge> getPbbJudgeList2() {
		return pbbJudgeList2;
	}

	public void setPbbJudgeList2(List<ProcardBanBenJudge> pbbJudgeList2) {
		this.pbbJudgeList2 = pbbJudgeList2;
	}

	public String getBzjdCount() {
		return bzjdCount;
	}

	public void setBzjdCount(String bzjdCount) {
		this.bzjdCount = bzjdCount;
	}

	public ProcardSbWg getProcardsbwg() {
		return procardsbwg;
	}

	public void setProcardsbwg(ProcardSbWg procardsbwg) {
		this.procardsbwg = procardsbwg;
	}

	public List<ProcardSbWg> getProcardsbwgList() {
		return procardsbwgList;
	}

	public void setProcardsbwgList(List<ProcardSbWg> procardsbwgList) {
		this.procardsbwgList = procardsbwgList;
	}

	public List<ManualOrderPlan> getManyalOrderPlanList() {
		return manyalOrderPlanList;
	}

	public void setManyalOrderPlanList(List<ManualOrderPlan> manyalOrderPlanList) {
		this.manyalOrderPlanList = manyalOrderPlanList;
	}

	public WaigouPlan getWaigouplan() {
		return waigouplan;
	}

	public void setWaigouplan(WaigouPlan waigouplan) {
		this.waigouplan = waigouplan;
	}

	public List<WaigouPlan> getWaigouPlanList() {
		return waigouPlanList;
	}

	public void setWaigouPlanList(List<WaigouPlan> waigouPlanList) {
		this.waigouPlanList = waigouPlanList;
	}

	public ManualOrderPlan getMop() {
		return mop;
	}

	public void setMop(ManualOrderPlan mop) {
		this.mop = mop;
	}

	public ProcessInfor getProcessInfor() {
		return processInfor;
	}

	public void setProcessInfor(ProcessInfor processInfor) {
		this.processInfor = processInfor;
	}

	public List<ProcardReProductFile> getPrpFileList() {
		return prpFileList;
	}

	public void setPrpFileList(List<ProcardReProductFile> prpFileList) {
		this.prpFileList = prpFileList;
	}

	public ProcardReProductFile getPrpFile() {
		return prpFile;
	}

	public void setPrpFile(ProcardReProductFile prpFile) {
		this.prpFile = prpFile;
	}

	public List<ProcardReProduct> getPreProductList() {
		return preProductList;
	}

	public void setPreProductList(List<ProcardReProduct> preProductList) {
		this.preProductList = preProductList;
	}

	public ProcardReProduct getPreProduct() {
		return preProduct;
	}

	public void setPreProduct(ProcardReProduct preProduct) {
		this.preProduct = preProduct;
	}

	public ProcessInforWWApplyDetail getWwApplyDetail() {
		return wwApplyDetail;
	}

	public void setWwApplyDetail(ProcessInforWWApplyDetail wwApplyDetail) {
		this.wwApplyDetail = wwApplyDetail;
	}

	public WaigouWaiweiPlan getWaigouWaiweiPlan() {
		return waigouWaiweiPlan;
	}

	public void setWaigouWaiweiPlan(WaigouWaiweiPlan waigouWaiweiPlan) {
		this.waigouWaiweiPlan = waigouWaiweiPlan;
	}

	public List<WaigouWaiweiPlan> getWaigouWaiweiPlanList() {
		return waigouWaiweiPlanList;
	}

	public void setWaigouWaiweiPlanList(
			List<WaigouWaiweiPlan> waigouWaiweiPlanList) {
		this.waigouWaiweiPlanList = waigouWaiweiPlanList;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public List<ProcessInfor> getProcessInforList() {
		return processInforList;
	}

	public void setProcessInforList(List<ProcessInfor> processInforList) {
		this.processInforList = processInforList;
	}

	public ProcardSbWw getProcardSbWw() {
		return procardSbWw;
	}

	public void setProcardSbWw(ProcardSbWw procardSbWw) {
		this.procardSbWw = procardSbWw;
	}

	public List<ProcardSbWw> getProcardSbWwList() {
		return procardSbWwList;
	}

	public void setProcardSbWwList(List<ProcardSbWw> procardSbWwList) {
		this.procardSbWwList = procardSbWwList;
	}

	public ProcardSbWaster getProcardSbWaster() {
		return procardSbWaster;
	}

	public void setProcardSbWaster(ProcardSbWaster procardSbWaster) {
		this.procardSbWaster = procardSbWaster;
	}

	public List<ProcardSbWaster> getProcardSbWasterList() {
		return procardSbWasterList;
	}

	public void setProcardSbWasterList(List<ProcardSbWaster> procardSbWasterList) {
		this.procardSbWasterList = procardSbWasterList;
	}

	public ProcardSbWasterxc getProcardSbWasterxc() {
		return procardSbWasterxc;
	}

	public void setProcardSbWasterxc(ProcardSbWasterxc procardSbWasterxc) {
		this.procardSbWasterxc = procardSbWasterxc;
	}

	public List<ProcardSbWasterxc> getProcardSbWasterxcList() {
		return procardSbWasterxcList;
	}

	public void setProcardSbWasterxcList(
			List<ProcardSbWasterxc> procardSbWasterxcList) {
		this.procardSbWasterxcList = procardSbWasterxcList;
	}

	public List<ProcardTemplateChangeLog> getPtchangeLogList() {
		return ptchangeLogList;
	}

	public void setPtchangeLogList(
			List<ProcardTemplateChangeLog> ptchangeLogList) {
		this.ptchangeLogList = ptchangeLogList;
	}

	public String getIdname1() {
		return idname1;
	}

	public void setIdname1(String idname1) {
		this.idname1 = idname1;
	}

	public String getIdname2() {
		return idname2;
	}

	public void setIdname2(String idname2) {
		this.idname2 = idname2;
	}

	public String getIdname3() {
		return idname3;
	}

	public void setIdname3(String idname3) {
		this.idname3 = idname3;
	}

	public List<ProcessTemplate> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessTemplate> processList) {
		this.processList = processList;
	}

	public ProcardTemplateServer getProcardTemplateServer() {
		return procardTemplateServer;
	}

	public void setProcardTemplateServer(
			ProcardTemplateServer procardTemplateServer) {
		this.procardTemplateServer = procardTemplateServer;
	}

	public String getProcessListString() {
		return processListString;
	}

	public void setProcessListString(String processListString) {
		this.processListString = processListString;
	}

	public List<String> getProcessStringList() {
		return processStringList;
	}

	public void setProcessStringList(List<String> processStringList) {
		this.processStringList = processStringList;
	}

	public ProcardTemplate getPageProcardTemplate() {
		return pageProcardTemplate;
	}

	public void setPageProcardTemplate(ProcardTemplate pageProcardTemplate) {
		this.pageProcardTemplate = pageProcardTemplate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TechnicalchangeLog getTechnicalchangeLog() {
		return technicalchangeLog;
	}

	public void setTechnicalchangeLog(TechnicalchangeLog technicalchangeLog) {
		this.technicalchangeLog = technicalchangeLog;
	}

	public List<TechnicalchangeLog> getTclList() {
		return tclList;
	}

	public void setTclList(List<TechnicalchangeLog> tclList) {
		this.tclList = tclList;
	}

	public ProcessChangeNotice getPcn() {
		return pcn;
	}

	public void setPcn(ProcessChangeNotice pcn) {
		this.pcn = pcn;
	}

	public List<ProcessChangeNotice> getPcnList() {
		return pcnList;
	}

	public void setPcnList(List<ProcessChangeNotice> pcnList) {
		this.pcnList = pcnList;
	}

	public List<ProcessChangeNoticeFile> getPcnFileList() {
		return pcnFileList;
	}

	public void setPcnFileList(List<ProcessChangeNoticeFile> pcnFileList) {
		this.pcnFileList = pcnFileList;
	}

	public ProcessChangeNoticeFile getPcnfile() {
		return pcnfile;
	}

	public void setPcnfile(ProcessChangeNoticeFile pcnfile) {
		this.pcnfile = pcnfile;
	}

	public DesignfeedbackNotice getEcar() {
		return ecar;
	}

	public void setEcar(DesignfeedbackNotice ecar) {
		this.ecar = ecar;
	}

	public List<DesignfeedbackNotice> getEcarList() {
		return ecarList;
	}

	public void setEcarList(List<DesignfeedbackNotice> ecarList) {
		this.ecarList = ecarList;
	}

	public DesignfeedbackNoticeFile getEcarfile() {
		return ecarfile;
	}

	public void setEcarfile(DesignfeedbackNoticeFile ecarfile) {
		this.ecarfile = ecarfile;
	}

	public List<DesignfeedbackNoticeFile> getEcarfileList() {
		return ecarfileList;
	}

	public void setEcarfileList(List<DesignfeedbackNoticeFile> ecarfileList) {
		this.ecarfileList = ecarfileList;
	}

	public List getList2() {
		return list2;
	}

	public void setList2(List list2) {
		this.list2 = list2;
	}

	public List getList3() {
		return list3;
	}

	public void setList3(List list3) {
		this.list3 = list3;
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

	public ProcardTemplateBanBenJudgesFile getPtbbjfile() {
		return ptbbjfile;
	}

	public void setPtbbjfile(ProcardTemplateBanBenJudgesFile ptbbjfile) {
		this.ptbbjfile = ptbbjfile;
	}

	public List<ProcardTemplateBanBenJudgesFile> getPtbbjfileList() {
		return ptbbjfileList;
	}

	public void setPtbbjfileList(
			List<ProcardTemplateBanBenJudgesFile> ptbbjfileList) {
		this.ptbbjfileList = ptbbjfileList;
	}

	public ECAFeedBackBean getEcaFeedBackBean() {
		return ecaFeedBackBean;
	}

	public void setEcaFeedBackBean(ECAFeedBackBean ecaFeedBackBean) {
		this.ecaFeedBackBean = ecaFeedBackBean;
	}

	public List<ECAFeedBackBean> getEfbbList() {
		return efbbList;
	}

	public void setEfbbList(List<ECAFeedBackBean> efbbList) {
		this.efbbList = efbbList;
	}

	public List<String> getRyzbList() {
		return ryzbList;
	}

	public void setRyzbList(List<String> ryzbList) {
		this.ryzbList = ryzbList;
	}

	public ProcessAndWgProcardTem getPawp() {
		return pawp;
	}

	public void setPawp(ProcessAndWgProcardTem pawp) {
		this.pawp = pawp;
	}

	public List<ProcessAndWgProcardTem> getPawpList() {
		return pawpList;
	}

	public void setPawpList(List<ProcessAndWgProcardTem> pawpList) {
		this.pawpList = pawpList;
	}

	public List<String> getPszbList() {
		return pszbList;
	}

	public void setPszbList(List<String> pszbList) {
		this.pszbList = pszbList;
	}

	public List<String> getPszbList2() {
		return pszbList2;
	}

	public void setPszbList2(List<String> pszbList2) {
		this.pszbList2 = pszbList2;
	}

}