package com.task.action.sop;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.sop.ProcardTemplateServer;
import com.task.entity.DTOProcess;
import com.task.entity.Users;
import com.task.entity.sop.ProcardSpecification;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcardTemplatePrivilege;
import com.task.entity.sop.ProcardTemplateReplace;
import com.task.entity.sop.ProcessAndWgProcardTem;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.ProcessPriceUpdateLog;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.ProcessTemplateFile;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.util.MKUtil;
import com.task.util.Upload;
import com.task.util.Util;

public class ProcardTemplateAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ProcardTemplateServer procardTemplateServer;// Server层
	private UserServer userServer;
	private ProcardTemplate procardTemplate;// 对象
	private ProcessTemplate processTemplate;// 对象
	private ProcessInfor process;// 对象
	private ProcardTemplatePrivilege procardTemplatePrivilege;//我也不知道这是啥对象
	private ProcardTemplateReplace procardTemplateReplace;
	private List<ProcardTemplateReplace> procardTemplateReplaceList;
	private List<ProcessTemplate> processList;//
	private List<ProcardTemplatePrivilege> procardTemplatePrivilegeList;
	private ProcardSpecification procardSpecification;// 管件测量记录表
	private List<ProcardSpecification> procardSpecificationList;// 管件测量记录表
	private List<ProcardTemplate> procardTemplateList;// 集合
	private List<ProcardTemplate> pt1_wg;// 集合
	private List<ProcardTemplate> pt2_wg;// 集合
	private List<ProcardTemplate> pt1_zz;// 集合
	private List<ProcardTemplate> pt2_zz;// 集合
	private List<ProcessAndWgProcardTem> processAndWgProcardList; // 
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer id2;// id
	private Integer nextNo;// 下个工序号
	private String pageStatus;// 页面状态
	private List list;
	private Integer[] processId;// 打印选择数组
	private Integer sonCount;// 子卡片数量
	private String name;
	private String jianhaoSet;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String code;
	private String messagePower;// 共用属性
	private Double OPLabourBeat; // 人工节拍
	private Double OPEquipmentBeat; // 设备节拍
	private Double PRLabourBeat; // 人工节拍
	private Double PRPrepareTIme; // 准备次数
	private String jobNum;
	private Float dayOutput;// 日产量

	private int size;// list大小
	private Float laborcost;// 报价
	private Float fenpeiRate;// 可调系数

	private String procardStyle;// 卡片类型
	private String markId;// 零件号
	private String markId2;// 零件号2
	private String markId1;// 零件号2
	private Integer moveId;// 移动的模板的id
	private Integer targetId;// 目标模板的id
	private List list1;
	private Boolean isAll = false;// 是否修改所有
	private ProcessPriceUpdateLog ppul;
	private List<ProcessPriceUpdateLog> ppulList;
	private List<ProcessInfor> processList1;
	private ProcessTemplateFile processTemplateFile;// 工序工艺规范图纸

	private File gygf;
	private String gygfFileName;
	private String gygfFileContentType;

	private File[] attachment;
	private String[] attachmentContentType;
	private String[] attachmentFileName;

	private String tag;// 查看图纸或上传缺陷图纸标识
	
	private String []	markIds;
	private String type;

	
	//更新面积设数据
	private Double procardArea;
	private Integer procardCengNum;
	private Double actualUsedProcardArea;
	private List<ProcardTemplate>   listArea;
	private String carStyle;
	
	
	
	/**
	 * @return the carStyle
	 */
	public String getCarStyle() {
		return carStyle;
	}

	/**
	 * @param carStyle the carStyle to set
	 */
	public void setCarStyle(String carStyle) {
		this.carStyle = carStyle;
	}

	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpSession session = request.getSession();
	/***
	 * 查选量具
	 */
	public void listliangju() {
		if (markId != null) {
			// procardTemplateList = procardTemplateServer.getAllNames(markId);
			list1 = procardTemplateServer.listliangju(markId);
			try {
				MKUtil.writeJSON(list1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/***
	 * 更具车型车差工装
	 */
	public void listgongzhuang() {
		// list1=procardTemplateServer.listGzstoreBycarModel(code);
		// MKUtil.writeJSON(list1);
		if (markId != null) {
			// procardTemplateList = procardTemplateServer.getAllNames(markId);
			list1 = procardTemplateServer.listGzstoreBycarModel(markId);
			try {
				MKUtil.writeJSON(list1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/***
	 * 添加总成流水卡片模板
	 * 
	 * @return
	 */
	public String addRootrProcardTem() {
		String msg = procardTemplateServer.addRootProcardTem(procardTemplate);
		if (msg.equals("true")) {
			return "addRootrProcardTem";
		}
		errorMessage = msg;
		return ERROR;
	}

	/***
	 * 添加流水卡片模板
	 * 
	 * @return
	 */
	public String addProcardTemplate() {
//		errorMessage = (String) ActionContext.getContext().getApplication()
//		.get("BOMDaoru");
//		if (errorMessage != null) {
//			return ERROR;
//		}
		// 查询父类流水卡片
		if (procardTemplate.getMarkId() == null
				|| procardTemplate.getMarkId().length() == 0) {
			MKUtil.writeJSON(false, "件号不能为空,添加失败!", null);
			return null;
		}
		procardTemplate.setMarkId(procardTemplate.getMarkId().replace(" ", ""));
//		procardTemplate.setMarkId(procardTemplate.getMarkId().replace("	", ""));
		if (procardTemplateServer.checkHasSonMarkId(procardTemplate
				.getFatherId(), procardTemplate.getId(), procardTemplate
				.getMarkId())) {
			MKUtil.writeJSON(false, "该父卡下已有此件号,添加失败!", null);
			return null;
		}
		ProcardTemplate fatherProTem = procardTemplateServer
				.findProcardTemById(procardTemplate.getFatherId());
		if (fatherProTem != null) {
			if(fatherProTem.getBzStatus().equals("已批准")){
				MKUtil.writeJSON(false, "该父卡编制状态为已批准不能添加下层零件,请先去申请上层设变!", null);
				return null;
			}
			if (procardTemplate.getProcardStyle() != null
					&& procardTemplate.getProcardStyle().equals("外购")) {
				Float maxCount = 0f;
				if (procardTemplate.getQuanzi1() != null
						&& procardTemplate.getQuanzi2() != null
						&& (procardTemplate.getQuanzi2() - 0) != 0) {
					if (fatherProTem.getMaxCount() != null) {
						double count = Math.ceil(fatherProTem.getMaxCount()
								* procardTemplate.getQuanzi2()
								/ procardTemplate.getQuanzi1());
						// maxCount = fatherProTem.getMaxCount()
						maxCount = (float) count * procardTemplate.getQuanzi2()
								/ procardTemplate.getQuanzi1();
					}
				} else {
					MKUtil.writeJSON(false, "添加失败,请填写正确的外购件比例!", null);
					return null;
				}
				procardTemplate.setMaxCount(maxCount);
			} else if (procardTemplate.getProcardStyle() != null
					&& procardTemplate.getProcardStyle().equals("自制")) {
				Float maxCount = 0f;
				if (procardTemplate.getCorrCount() != null
						&& procardTemplate.getCorrCount() != null) {
					if (fatherProTem.getMaxCount() != null) {
						double count = Math.ceil(fatherProTem.getMaxCount()
								* procardTemplate.getCorrCount());
						maxCount = (float) count;
					}
					// 总成件号
					procardTemplate.setRootMarkId(fatherProTem.getRootMarkId());
				}
				procardTemplate.setMaxCount(maxCount);
			}

			procardTemplate.setProcardTemplate(fatherProTem);
			procardTemplate.setMarkId(procardTemplate.getMarkId().replaceAll(
					" ", ""));// 去除件号中的所有空格
			// 添加
			try {
				if (procardTemplateServer.addProcardTemplate(procardTemplate)) {
					MKUtil.writeJSON(true, "添加成功!", null);
				} else {
					MKUtil.writeJSON(true, "添加失败!", null);
				}
			} catch (Exception e) {
				MKUtil.writeJSON(false, e.getMessage(), null);
			}
			
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
		ProcardTemplate proTem = procardTemplateServer.findProcardTemById(id);
		if(processTemplate.getProcessNO()==null || "".equals(processTemplate.getProcessName())){
			if(id2!=null){
				errorMessage = "添加失败!";
				url = "procardTemplateGyAction_showBOMasExlAndProcedure.action?id=" + id2;
			}
			return ERROR;
		}
		if (proTem != null) {
			// processTemplate.setProcardTemplate(proTem);
			if (processTemplate.getOpcaozuojiepai() == null) {
				processTemplate.setOpcaozuojiepai(3f);
			}
			if (processTemplate.getOpshebeijiepai() == null) {
				processTemplate.setOpshebeijiepai(1f);
			}
			if (processTemplate.getGzzhunbeijiepai() == null) {
				processTemplate.setGzzhunbeijiepai(1f);
			}
			if (processTemplate.getGzzhunbeicishu() == null) {
				processTemplate.setGzzhunbeicishu(1f);
			}
			processTemplate.setAllJiepai(processTemplate.getOpcaozuojiepai()
					+ processTemplate.getOpshebeijiepai()
					+ processTemplate.getGzzhunbeijiepai()
					* processTemplate.getGzzhunbeicishu());
			String msg = procardTemplateServer.addProcessTemplate(
					processTemplate, id);
			if (msg.equals("true")) {
				if(id2!=null){
					errorMessage = "添加成功!";
					url = "procardTemplateGyAction_showBOMasExlAndProcedure.action?id=" + id2;
				}else {
					errorMessage = "添加成功!";
					url = "ProcardTemplateAction!toAddProcess.action?id=" + id;
				}
				
			} else {
				errorMessage = msg;
			}
		}
		return ERROR;
	}
	public void exportBom(){
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		procardTemplateServer.exportBom(procardTemplate);
	}
	/***
	 * 查询所有总成流水卡片模板(分页)
	 * 
	 * @param procardTemplate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findAllProcardTem() {
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
		if (pageStatus != null && "lp".equals(pageStatus)) {
			if ("lp".equals(pageStatus) || "jy".equals(pageStatus)) {
				procardTemplate.setProductStyle("批产");
			} else if ("sop".equals(pageStatus)) {
				procardTemplate.setProductStyle("试制");
			} else {
				errorMessage = "访问参数不匹配,请您不要非法查询!谢谢!";
				return ERROR;
			}
		}
		
		
		
		

		Object[] object = procardTemplateServer.findAllProcardTemplate(
				procardTemplate, Integer.parseInt(cpage), pageSize,pageStatus);
		
		
		if(tag==null||"null".equals(tag)){
			tag = "";
		}
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];

			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				if (pageStatus != null && pageStatus.length() > 0) {
					this
							.setUrl("ProcardTemplateAction!findAllProcardTem.action?pageStatus="
									+ pageStatus+"&tag="+tag);
				} else {
					pageStatus = null;
					this
							.setUrl("ProcardTemplateAction!findAllProcardTem.action?tag="+tag);
				}
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		// 跳转到精益计算
		if (pageStatus != null && "jy".equals(pageStatus)) {
			return "jy_Template_findProcardList";
		}else if(type!=null && "lp".equals(type)){
			return "Template_findProcardList_addParts";
		}
		
		
		
		
		if (pageStatus != null && "area".equals(pageStatus)) {
			return "Template_updateProcardListArea";//跳转至修改流水单占地面积
		}else if(pageStatus != null && "carStyle".equals(pageStatus)) {
			return "Template_updateCarStyle";//
		}else{
			return "findAllProcardTem";//Template_findProcardList.jsp
		}
		
		//return "findAllProcardTem";//Template_findProcardList.jsp
	}
	
	/**
	 * 查询全部总成流水卡片模板(分页)(不止第一层)
	 * @return
	 */
	public String findAllProcardTemp(){
		if (procardTemplate != null) {
			ActionContext.getContext().getSession().put("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) ActionContext.getContext()
					.getSession().get("procardTemplate");
		}
		Object[] object = procardTemplateServer.findAllProcardTemp(
				procardTemplate, Integer.parseInt(cpage), pageSize,pageStatus);
		if (object != null && object.length > 0) {
			procardTemplateList = (List<ProcardTemplate>) object[0];
			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				size = procardTemplateList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProcardTemplateAction!findAllProcardTemp.action?pageStatus="+ pageStatus+"&type="+type);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		// 跳转到精益计算
		if(type!=null && "lp".equals(type)){
 			return "Template_findProcardList_addParts";
		}
		return null;
	}
		      
	/***
	 * 根据首层父类id查询流水卡片模板(页面生成树形结构)
	 * 
	 * @param procardTemplate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findProcardTemByRootId() {
		List<ProcardTemplate> proList = null;
		if (pageStatus == null || !pageStatus.equals("shenyue")) {
			proList = procardTemplateServer.findProcardTemByRootId(id);
		} else {
			proList = new ArrayList<ProcardTemplate>();
			procardTemplate = procardTemplateServer.findProcardTemById(id);
			proList.add(procardTemplate);
		}
		try {
			MKUtil.writeJSON(proList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String findProcardTemByRootId1() {
		String jsonStr = procardTemplateServer.findProcardTemByRootId1(id);
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

	// 通过流水卡片id(外键)查询对应工序信息
	@SuppressWarnings("unchecked")
	public String findProcessByFkId() {
		List<ProcessTemplate> processList = procardTemplateServer
				.findProcessByFkId(id);
		try {
			MKUtil.writeJSON(processList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 删除流水卡片
	 * 
	 * @return
	 */
	public String delProcard() {
		ProcardTemplate oldProCard = procardTemplateServer
				.findProcardTemById(id);
		if (id != null) {
			try {
				String msg = procardTemplateServer
				.delProcardTemplate(oldProCard);
				if(msg.equals("true")){
					msg="删除成功!";
				}else{
					errorMessage=msg;
				}
				MKUtil.writeJSON(msg);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				errorMessage = e.getMessage();
			}
			
		}
		return null;
	}
	/***
	 * 删除流水卡片
	 * 
	 * @return
	 */
	public String delProcard2() {
		List<ProcardTemplate> oldProCardList = procardTemplateServer.findProcardTemByConditionId(id);
		if (oldProCardList != null&&oldProCardList.size()>0) {
			for(ProcardTemplate oldProCard:oldProCardList){
				try {
					String msg = procardTemplateServer
					.delProcardTemplate(oldProCard);
					if(msg.equals("true")){
//					msg="删除成功!";
					}else{
						errorMessage=msg;
					}
					MKUtil.writeJSON(msg);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					errorMessage = e.getMessage();
				}
			}
			
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
		Object[] obj = procardTemplateServer.findCardTemForShow(id);
		if (obj != null) {
			procardTemplate = (ProcardTemplate) obj[0];
			procardTemplateList = (List<ProcardTemplate>) obj[1];
			list = (List) obj[2];
			if(obj[3]!=null){
				jobNum = obj[3].toString();
			}else{
				jobNum = "4";
			}
			if(obj[4]!=null){
				messagePower = obj[4].toString();
			}
			
			// 精益控制
			if (pageStatus != null && "jy".equals(pageStatus)) {
				return "jy_ProcardTemDetails";
			}
			return "ProcardTemDetails";
		}
		return ERROR;
	}

	public String showSonCard() {
		Object[] obj = procardTemplateServer.findCardTemForShow(id);
		if (obj != null) {
			procardTemplate = (ProcardTemplate) obj[0];
			procardTemplateList = (List<ProcardTemplate>) obj[1];
			list = (List) obj[2];
			return "ProcardTemSonCards";
		}
		return ERROR;
	}

	public String toAddProcess() {
		procardTemplate = procardTemplateServer.getProcardById(id);
		nextNo = procardTemplateServer.updateProcessNo(id);
		return "ProcessTemplateadd";
	}
	
	public String toAddProcessDefaultjiepai() {
		nextNo = procardTemplateServer.updateProcessNo(id);
		return "ProcessTemplateAndDefaultjiepaiadd";
	}

	/** 查找要打印的所有相关工序list **/
	public String findPrintProcessList() {
		this.pageSize = 15;
		this.setUrl("ProcardTemplateAction!findPrintProcessList.action");
		HttpServletRequest request = ServletActionContext.getRequest();
		if (null != procardTemplate) {
			request.getSession().setAttribute("procardTemplate",
					procardTemplate);
		} else {
			procardTemplate = (ProcardTemplate) request.getSession()
					.getAttribute("procardTemplate");
		}
		Object[] obj = procardTemplateServer.findPrintProcessList(
				procardTemplate, Integer.parseInt(cpage), pageSize);
		int count = (Integer) obj[0];
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		return "findPrintProcessListOK";
	}

	/** 多选的打印对象list **/
	public String selestedPrintList() {
		if (null != processId) {
			list = procardTemplateServer.findSelectedList(processId);
		}
		return "selestedPrintListOK";
	}

	/***
	 * 显示工序详细
	 * 
	 * @return
	 */
	public String showProcess() {
		processTemplate = procardTemplateServer.findProcessT(id);
		if (processTemplate != null) {
			if (successMessage != null && "true".equals(successMessage)) {
				successMessage = "修改成功";
			}
			return "ProcessTemplateDetails";
		} else {
			errorMessage = "不存在您要查询的工序信息,请核对!";
		}
		return ERROR;
	}

	/**
	 * 显示工序图纸
	 * 
	 * @return
	 */
	public String showProcessTz() {
		processTemplate = procardTemplateServer.findProcessT(id);
		list = procardTemplateServer.getProcessTz(id);
		if ("quexian".equals(tag))
			return "Process_showQXtz";
		else
			return "ProcessTemplateTzs";
	}

	/**
	 * 删除图纸
	 * 
	 * @return
	 */
	public String deleteProcessTz() {
		processTemplateFile = procardTemplateServer
				.findProcessTz(processTemplateFile.getId());
		String month = null;
		String fileName = null;
		String fileName2 = null;
		if (processTemplateFile != null) {
			fileName = processTemplateFile.getFileName();
			fileName2 = processTemplateFile.getFileName2();
			month = processTemplateFile.getMonth();
		}
		boolean b = procardTemplateServer.deleteProcessTz(processTemplateFile
				.getId(),type);
		if (b) {
			errorMessage = "删除成功!";
			if(!"sop".equals(type)){
			String path = ServletActionContext.getServletContext().getRealPath(
					"/upload/file/processTz");
			if (month != null && month.length() > 0) {
				path += "/" + month;
			}
//			File file = new File(path + "/" + fileName);
//			File file2 = new File(path + "/" + fileName2);
//			if (file.exists()) {
//				file.delete();
//			}
//			if (file2.exists()) {
//				file2.delete();
//			}
			}
		} else {
			errorMessage = "删除失败!";
		}
		if ("quexian".equals(tag)) {
			url = "ProcardTemplateAction!showProcessTz.action?id=" + id
					+ "&tag=" + tag;
		} else {
			if (pageStatus != null && pageStatus.equals("card")) {
				url = "procardTemplateGyAction_showCardTz.action?id=" + id+"&tag="+type;
			} else {
				url = "ProcardTemplateAction!showProcessTz.action?id=" + id;
			}
		}
		return ERROR;
	}

	public String updateProcessTz() {
		if (this.attachment != null && attachment.length > 0) {
			Users user = Util.getLoginUser();
			if (user == null) {
				errorMessage = "请先登录";
				return ERROR;
			}
			for (int i = 0; i < attachment.length; i++) {
				String fileName = attachmentFileName[i];
				// 文件路径
				String fileType = fileName.substring(fileName.lastIndexOf("."),
						fileName.length());
				String realFileName  = Util.getDateTime("yyyyMMddHHmmssSSS_") + i
						+ fileType;
				String realFileName2  = null;
				if(fileType.equalsIgnoreCase(".bmp")||fileType.equalsIgnoreCase(".dib")
						||fileType.equalsIgnoreCase(".gif")||fileType.equalsIgnoreCase(".jfif")
						||fileType.equalsIgnoreCase(".jpe")||fileType.equalsIgnoreCase(".jpeg")
						||fileType.equalsIgnoreCase(".jpg")||fileType.equalsIgnoreCase(".png")
						||fileType.equalsIgnoreCase(".tif")||fileType.equalsIgnoreCase(".tiff")
						||fileType.equalsIgnoreCase(".ico")){
					realFileName2="jz_"+Util.getDateTime("yyyyMMddHHmmssSSS_") + i
					+ fileType;
				}
				
				String realFilePath = "/upload/file/processTz/"
						+ Util.getDateTime("yyyy-MM");
				// 打开存放上传文件的位置
				String path = ServletActionContext.getServletContext()
						.getRealPath(realFilePath);
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();// 如果不存在文件夹就创建
				}
				processTemplateFile.setFileName(realFileName);
				processTemplateFile.setFileName2(realFileName2);
				processTemplateFile.setMonth(Util.getDateTime("yyyy-MM"));
				fileName = fileName.replaceAll("-ZKT", "-展开图");
				fileName = fileName.replaceAll("-YT", "-原图");
				fileName = fileName.replaceAll("-GYK", "-工艺卡");
				processTemplateFile.setOldfileName(fileName);
				processTemplateFile.setAddTime(Util.getDateTime());
				processTemplateFile.setUserCode(user.getCode());
				processTemplateFile.setUserName(user.getName());
				
				Upload upload = new Upload();
				upload.UploadFile(attachment[i], fileName, realFileName,
						realFilePath, null);
				 //将图纸加盖印章
				 String icon_fileRealPath = ServletActionContext
				 .getServletContext().getRealPath(
				 "/upload/file/yz/icon_ytwrq.png");
				//生成加章文件
				Util.markImageByIcon(icon_fileRealPath, path+"/"+realFileName, path+"/"+realFileName2);
				
				String msg = this.procardTemplateServer
						.saveProcessTemplateFile(this.processTemplateFile, id);
				if (!msg.equals("true")) {
					errorMessage = msg;
					return ERROR;
				}
			}
		}
		errorMessage = "添加成功!";
		url = "ProcardTemplateAction!showProcessTz.action?id=" + id + "&tag="
				+ tag;
		return ERROR;
	}

	/***
	 * 更新工序模板信息
	 * 
	 * @param processT
	 * @return
	 */
	public String updateProcessT() {
		errorMessage = procardTemplateServer.updateProcessT(processTemplate);
		if ("true".equals(errorMessage)) {
			return "updateProcessT";
		}
		return ERROR;
	}

	/**
	 * @Title: display
	 * @Description: 显示试算成品页面
	 * @return String
	 * @throws
	 */
	public String initTrial() {
		String jsonStr = procardTemplateServer.packageData(id, null);
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

	/***
	 * 查询总成信息
	 * 
	 * @return
	 */
	public String packagePro() {
		Double mentioningAwardPrice = null;
		ProcardTemplate procardTem = new ProcardTemplate();
		if (code != null && code.equals("1")) {
			procardTem = procardTemplateServer.findProcardTemById(id);
			mentioningAwardPrice = procardTem.getOnePrice();
		} else {
			// mentioningAwardPrice = productPriceServer.getBonus(id);
		}

		String jsonStr = procardTemplateServer.packageProduct(procardTem,
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
	 * @Title: saveData
	 * @Description: 保存数据
	 * @return String
	 * @throws
	 */
	public String saveData() {
		Map session = ActionContext.getContext().getSession();
		StringBuffer str = new StringBuffer();
		if ((Object) id != null) {
			String[] allJobNum = jobNum.split(";");
			for (String num : allJobNum) {
				Users user = userServer.findUserByCode(num);
				if (user != null) {
					str.append(user.getName()).append(";");
				}
			}
			DTOProcess dto = null;
			if (str != null && str.length() > 0) {
				dto = new DTOProcess(id, jobNum, OPLabourBeat, OPEquipmentBeat,
						PRLabourBeat, PRPrepareTIme, (String) str.subSequence(
								0, str.length() - 1));
			} else {
				dto = new DTOProcess(id, jobNum, OPLabourBeat, OPEquipmentBeat,
						PRLabourBeat, PRPrepareTIme, "");
			}
			session.put(id, dto);
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
		String jsonStr = procardTemplateServer.trial(id, dayOutput);
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

	/***
	 * 更新报价、可调系数
	 * 
	 * @return
	 */
	public String updatelf() {
		procardTemplate = procardTemplateServer.findProcardTemById(id);
		if (procardTemplate != null) {
			String message = "";
			procardTemplate.setLaborcost(laborcost);
			procardTemplate.setFenpeiRate(fenpeiRate);
			procardTemplateServer.updateProcardTemplate(procardTemplate, 2);
			MKUtil.writeJSON(true, message, null);
		}
		return null;
	}

	/***
	 * 修改流水单模板
	 * 
	 * @return
	 */
	public String updateProcardTem() {
		String bool =null;
		try {
			if(procardTemplate!=null){
				procardTemplate.setId(id);
			}
			 bool = procardTemplateServer.updateProcardTemplate2(procardTemplate);// 保存修改的页面直接获得的数据
			 if (bool.equals("true")) {
					errorMessage = "修改成功!";
					MKUtil.writeJSON(errorMessage);
					return null;
			}else{
				errorMessage = bool;
				MKUtil.writeJSON(errorMessage);
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			errorMessage = e.getMessage();
			MKUtil.writeJSON(errorMessage);
			return null;
		}
		
		
		
		
//		
//		if (procardTemplateServer.checkHasSonMarkId(procardTemplate
//				.getFatherId(), id, procardTemplate.getMarkId())) {
//			errorMessage = "父卡下已有此件号,修改失败!";
//			MKUtil.writeJSON(errorMessage);
//			return null;
//		}
//		ProcardTemplate oldProcardTem = procardTemplateServer
//				.findProcardTemById(id);
////		if ((oldProcardTem.getBanBenNumber() == null || oldProcardTem.getBanBenNumber().length() == 0) && procardTemplate.getBanBenNumber() != null
////				&& procardTemplate.getBanBenNumber().length() > 0) {
////			return "不允许修改版本!";
////		}
////		if ((procardTemplate.getBanBenNumber() == null || procardTemplate.getBanBenNumber().length() == 0) && oldProcardTem.getBanBenNumber() != null
////				&& oldProcardTem.getBanBenNumber().length() > 0) {
////			return "不允许修改版本!";
////		}
////		if (oldProcardTem.getBanBenNumber() != null && oldProcardTem.getBanBenNumber().length() >= 0 && procardTemplate.getBanBenNumber() != null
////				&& procardTemplate.getBanBenNumber().length() > 0 && !oldProcardTem.getBanBenNumber().equals(procardTemplate.getBanBenNumber())) {
////			return "不允许修改版本!";
////		}
//		String bzpp = "";
//		if(oldProcardTem.getBzStatus()==null||oldProcardTem.getBzStatus().equals("初始")){
//			bzpp = "bianzhiId";
//		}else if(oldProcardTem.getBzStatus().equals("待编制")){
//			bzpp = "jiaoduiId";
//		}else if(oldProcardTem.getBzStatus().equals("待校对")){
//			bzpp = "shenheId";
//		}else if(oldProcardTem.getBzStatus().equals("待审核")){
//			bzpp = "pizhunId";
//		}
//		// 判断修改的属性是否需要同步到同件号上
//		int updatelv = procardTemplateServer.isTb(procardTemplate,
//				oldProcardTem);
//		ProcardTemplate fatherProTem = oldProcardTem.getProcardTemplate();
//		if (oldProcardTem != null) {
//			String checkUpdatelimtmsg = procardTemplateServer.checkUpdatelimt(oldProcardTem,procardTemplate);
//			if(!checkUpdatelimtmsg.equals("true")){
//				errorMessage=checkUpdatelimtmsg;
//				MKUtil.writeJSON(errorMessage);
//				return null;
//			}
//			if (procardTemplate.getMarkId() == null
//					|| procardTemplate.getMarkId().length() == 0) {
//				errorMessage = "件号不能为空,添加失败!";
//				MKUtil.writeJSON(errorMessage);
//				return null;
//			}
//			procardTemplate.setMarkId(procardTemplate.getMarkId().replace(" ",
//					""));
//			procardTemplate.setMarkId(procardTemplate.getMarkId().replace("	",
//					""));
//
//			if (oldProcardTem.getProcardStyle() != null
//					&& oldProcardTem.getProcardStyle().equals("待定")) {
//				Float maxCount = 0f;
//				if (fatherProTem.getMaxCount() != null
//						&& procardTemplate.getQuanzi1() != null
//						&& procardTemplate.getQuanzi2() != null
//						&& (procardTemplate.getQuanzi2() - 0) != 0) {
//					// double count = Math.ceil(fatherProTem.getMaxCount()
//					// * procardTemplate.getQuanzi2()
//					// / procardTemplate.getQuanzi1());
//					// maxCount = (float) count;
//					maxCount = fatherProTem.getMaxCount()
//							* procardTemplate.getQuanzi2()
//							/ procardTemplate.getQuanzi1();
//				}
//				procardTemplate.setMaxCount(maxCount);
//				BeanUtils.copyProperties(oldProcardTem, procardTemplate,
//						new String[] { "markId", "proName", "carStyle",
//								"maxCount", "corrCount", "procardStyle",
//								"unit", "trademark", "specification", "luhao",
//								"number", "actualFixed", "yuanUnit",
//								"jihuoType", "singleDuration",
//								"lingliaostatus", "status", "needProcess",
//								"safeCount", "lastCount", "lingliaoType",
//								"pageTotal", "fachuDate", "banBenNumber",
//								"kgliao", "wgType", "loadMarkId", "tuhao",
//								"biaochu", "thisLength", "thisWidth",
//								"thisHight", "calculateType","importance",bzpp});
//				if (procardTemplate.getNeedProcess() == null
//						|| procardTemplate.getNeedProcess().equals("no")) {
//					procardTemplate.setSafeCount(null);
//					procardTemplate.setLastCount(null);
//				}
//				procardTemplate.setOldProcardStyle("待定");
//				 bool = procardTemplateServer.updateProcardTemplate(
//						procardTemplate, updatelv);// 保存修改的页面直接获得的数据
//				if (bool.equals("true")) {
//					errorMessage = "修改成功!";
//					MKUtil.writeJSON(errorMessage);
////					url = "ProcardTemplateAction!findCardTemForShow.action?id="
////							+ id;
//					return null;
//					// return "updateProcardT";
//				}else{
//					errorMessage = bool;
//					MKUtil.writeJSON(errorMessage);
//					return null;
//				}
//			}
//
//			if (procardTemplate.getProcardStyle() != null
//					&& (procardTemplate.getProcardStyle().equals("总成"))) {
//				BeanUtils.copyProperties(oldProcardTem, procardTemplate,
//						new String[] { "markId", "proName", "carStyle",
//								"maxCount", "corrCount", "procardStyle",
//								"unit", "danjiaojian", "trademark",
//								"specification", "luhao", "number",
//								"actualFixed", "yuanUnit", "quanzi1",
//								"quanzi2", "jihuoType", "singleDuration",
//								"lingliaostatus", "status", "needProcess",
//								"safeCount", "lastCount", "lingliaoType",
//								"pageTotal", "fachuDate", "banBenNumber",
//								"ywMarkId", "wgType", "tuhao", "biaochu",
//								"thisLength", "thisWidth", "thisHight",
//								"calculateType" ,"importance",bzpp});
//				bool = procardTemplateServer.updateProcardTemplate(
//						procardTemplate, updatelv);
//				if (bool.equals("true")) {
//					MKUtil.writeJSON(bool);
//					return null;
//				}
//			} else if (procardTemplate.getProcardStyle() != null
//					&& procardTemplate.getProcardStyle().equals("自制")) {
//				BeanUtils.copyProperties(oldProcardTem, procardTemplate,
//						new String[] { "markId", "proName", "carStyle",
//								"maxCount", "corrCount", "procardStyle",
//								"unit", "trademark", "specification", "luhao",
//								"number", "actualFixed", "yuanUnit", "quanzi1",
//								"quanzi2", "jihuoType", "singleDuration",
//								"lingliaostatus", "status", "needProcess",
//								"safeCount", "lastCount", "lingliaoType",
//								"pageTotal", "fachuDate", "procardStyle",
//								"banBenNumber", "wgType", "clType", "loadMarkId", "bili",
//								"ytuhao", "yuanName", "tuhao", "biaochu",
//								"thisLength", "thisWidth", "thisHight",
//								"calculateType","zzjihuo","importance",bzpp });
//				bool = procardTemplateServer.updateProcardTemplate(
//						procardTemplate, updatelv);// 保存修改的页面直接获得的数据
//				// procardTemplate.setProcardTemplate(null);
//				// bool = bool
//				// & procardTemplateServer.updateBomMaxCount(
//				// procardTemplate, fatherProTem.getMaxCount());//
//				// 遍历下层修改maxcount
//				if (bool.equals("true")) {
//					errorMessage = "修改成功!";
//					MKUtil.writeJSON(errorMessage);
////					url = "ProcardTemplateAction!findCardTemForShow.action?id="
////							+ id;
//					return null;
//					// return "updateProcardT";
//				}
//			} else if (procardTemplate.getProcardStyle() != null
//					&& procardTemplate.getProcardStyle().equals("外购")) {
//				Float maxCount = 0f;
//				if (fatherProTem.getMaxCount() != null
//						&& procardTemplate.getQuanzi1() != null
//						&& procardTemplate.getQuanzi2() != null
//						&& (procardTemplate.getQuanzi2() - 0) != 0) {
//					// double count = Math.ceil(fatherProTem.getMaxCount()
//					// * procardTemplate.getQuanzi2()
//					// / procardTemplate.getQuanzi1());
//					// maxCount = (float) count;
//					maxCount = fatherProTem.getMaxCount()
//							* procardTemplate.getQuanzi2()
//							/ procardTemplate.getQuanzi1();
//				}
//				procardTemplate.setMaxCount(maxCount);
//				BeanUtils.copyProperties(oldProcardTem, procardTemplate,
//						new String[] { "markId", "proName", "carStyle",
//								"maxCount", "corrCount", "procardStyle",
//								"unit", "trademark", "specification", "luhao",
//								"number", "actualFixed", "yuanUnit", "quanzi1",
//								"quanzi2", "jihuoType", "singleDuration",
//								"lingliaostatus", "status", "needProcess",
//								"safeCount", "lastCount", "lingliaoType",
//								"pageTotal", "fachuDate", "banBenNumber",
//								"kgliao", "wgType", "clType", "loadMarkId", "tuhao",
//								"biaochu", "thisLength", "thisWidth",
//								"thisHight", "calculateType","importance" });
//				if (procardTemplate.getNeedProcess() == null
//						|| procardTemplate.getNeedProcess().equals("no")) {
//					procardTemplate.setSafeCount(null);
//					procardTemplate.setLastCount(null);
//				}
//				bool = procardTemplateServer.updateProcardTemplate(
//						procardTemplate, updatelv);// 保存修改的页面直接获得的数据
//				if (bool.equals("true")) {
//					errorMessage = "修改成功!";
//					MKUtil.writeJSON(errorMessage);
////					url = "ProcardTemplateAction!findCardTemForShow.action?id="
////							+ id;
//					return null;
//					// return "updateProcardT";
//				}
//			} else {
//				Float maxCount = 0f;
//				if (fatherProTem.getMaxCount() != null
//						&& procardTemplate.getCorrCount() != null) {
//					double count = Math.ceil(fatherProTem.getMaxCount()
//							* procardTemplate.getCorrCount());
//					maxCount = (float) count;
//				}
//				procardTemplate.setMaxCount(maxCount);
//				BeanUtils.copyProperties(oldProcardTem, procardTemplate,
//						new String[] { "markId", "proName", "carStyle",
//								"maxCount", "corrCount", "procardStyle",
//								"unit", "trademark", "specification", "luhao",
//								"number", "actualFixed", "yuanUnit", "quanzi1",
//								"quanzi2", "jihuoType", "singleDuration",
//								"lingliaostatus", "status", "needProcess",
//								"safeCount", "lastCount", "lingliaoType",
//								"procardStyle", "banBenNumber", "wgType",
//								"loadMarkId", "bili", "ytuhao", "yuanName",
//								"tuhao", "biaochu", "thisLength", "thisWidth",
//								"thisHight", "calculateType","importance" });
//				bool = procardTemplateServer.updateProcardTemplate(
//						procardTemplate, updatelv);// 保存修改的页面直接获得的数据
//				if (bool.equals("true")) {
//					errorMessage = "修改成功!";
//					MKUtil.writeJSON(errorMessage);
////					url = "ProcardTemplateAction!findCardTemForShow.action?id="
////							+ id;
//					return null;
//					// return "updateProcardT";
//				}
//			}
//		} else {
//			errorMessage = "不存在您要修改的模板信息!";
//		}
//		MKUtil.writeJSON(errorMessage);
//		return ERROR;
	}

	/**
	 * ajax 获取含有少许字符的全字符
	 */
	public void getAllNames() {
		if (markId != null) {
			procardTemplateList = procardTemplateServer.getAllNames(markId,
					procardStyle);
			try {
				MKUtil.writeJSON(procardTemplateList);
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
		errorMessage = (String) ActionContext.getContext().getApplication()
		.get("BOMDaoru");
		if (errorMessage != null) {
			MKUtil.writeJSON(errorMessage);
			return ;
		}
		// 被选中的主卡模板
		ProcardTemplate pt1 = procardTemplateServer.findProcardTemById(id);
		ProcardTemplate pt2 = procardTemplateServer.findProcardTemById(id2);
		if (pt1 != null && pt2 != null) {
			if (pt1.getProcardStyle() != null && pt2.getProcardStyle() != null) {
				boolean b = true;
				if (pt1.getMarkId() != null && pt2.getMarkId() != null
						&& pt1.getMarkId().equals(pt2.getMarkId())) {
					MKUtil.writeJSON(false, "同一件号不能互相复制，复制失败！", null);
					return;
				}
				pt1.setProcardTemplate(null);
				pt2.setProcardTemplate(null);
				if (pt1.getProcardStyle().equals("外购")) {
					MKUtil.writeJSON(false, "外购件下不能复制模板，复制失败！", null);
					return;
//				} else if (pt1.getProcardStyle().equals("自制")
//						&& !pt2.getProcardStyle().equals("自制")) {
//					MKUtil.writeJSON(false, "自制件下只能复制自制件下的工序，复制失败！", null);
//					return;
//				} else if (pt1.getProcardStyle().equals("自制")
//						&& pt2.getProcardStyle().equals("自制")) {
//					try {
//						b = b & procardTemplateServer.saveCopyProcess(pt1, pt2);
//					} catch (Exception e) {
//						// TODO: handle exception
//						MKUtil.writeJSON(false, e.getMessage(), null);
//						return;
//					}

					// } else if (!pt1.getProcardStyle().equals("总成")
					// && pt2.getProcardStyle().equals("总成")) {
					// MKUtil.writeJSON(false, "非总成模板下面不能复制总成模板，复制失败！", null);
					// return;
				} else if (pt1.getProcardStyle().equals("总成")
						|| pt1.getProcardStyle().equals("自制")) {
					try {
						if(procardTemplateServer.saveCopyProcard(pt1,
								pt2, pt2.getProductStyle())==null){
							b= false;
						}
					} catch (Exception e) {
						// TODO: handle exception
						MKUtil.writeJSON(false, e.getMessage(), null);
						return;
					}
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

	public void changeTolp() {
		if (markId == null || markId.equals("")) {
			MKUtil.writeJSON(false, "对不起批产件号不能为空,转换失败!", null);
		}
		ProcardTemplate pt1 = new ProcardTemplate();
		pt1.setMarkId(markId);
		ProcardTemplate pt2 = procardTemplateServer.findProcardTemById(id);
		if (pt2 == null) {
			MKUtil.writeJSON(false, "对不起试制BOM有误,转换失败!", null);
		}
		String msg = procardTemplateServer.changeTolp(pt1, pt2);
		if (msg.equals("true")) {
			MKUtil.writeJSON(true, "转批产成功！", null);
		} else {
			MKUtil.writeJSON(false, msg, null);
		}
	}

	/**
	 * 移动模板
	 */
	public void moveProcardTemplate() {
		MKUtil.writeJSON(false, "移动功能已取消！", null);
//		if (moveId != null && targetId != null) {
//			boolean b = procardTemplateServer.saveMoveProcardTemplate(moveId,
//					targetId);
//			if (b) {
//				MKUtil.writeJSON(true, "移动成功！", null);
//			} else {
//				MKUtil.writeJSON(false, "移动失败！", null);
//			}
//		} else {
//			MKUtil.writeJSON(false, "您选中的模板不存在，移动！", null);
//		}
	}

	/**
	 * 更新卡片
	 */
	public void updateProcard() {
		if (id != null) {
			boolean b = procardTemplateServer.updateProcard(id);
			if (b) {
				int n = procardTemplateServer.updateFk();
				if (n >= 0) {
					MKUtil.writeJSON(b, "更新成功！", null);
				}
			}
			MKUtil.writeJSON(b, "更新失败！", null);

		} else {
			MKUtil.writeJSON(false, "您选中的模板不存在，更新失败！", null);
		}
	}
	
	
	/**
	 * 更新单产品占地面积  可叠放层数
	 */
	public void ajaxUpdateProcardArea(){
		boolean flag=false;
		if(procardArea !=null && procardCengNum !=null){
			this.actualUsedProcardArea=procardArea/procardCengNum;
			String  actualAreaStr=new DecimalFormat("0.00").format(this.actualUsedProcardArea);
			Double actualArea=Double.parseDouble(actualAreaStr);
			flag=procardTemplateServer.updateProcardArea(id,procardArea,procardCengNum,actualArea);
			if(flag){
				MKUtil.writeJSON("修改面积成功!"+actualArea);
			}else{
				MKUtil.writeJSON("修改面积失败!"+actualArea);
			}
		}
	}
	/**
	 * 修改车型(型号)
	 */
	public void ajaxUpdateCarStyle(){
		boolean flag=false;
		if(carStyle !=null && id !=null){

			flag=procardTemplateServer.updateCarStyle(id,carStyle);
			if(flag){
				MKUtil.writeJSON("修改车型(型号)成功!");
			}else{
				MKUtil.writeJSON("修改车型(型号)失败!");
			}
		}
	}
	
	
	
	
	public void updateMarkId() {
		if (id != null && markId != null) {
			String msg = procardTemplateServer.updateMarkId(id, markId);
			if (msg.equals("true")) {
				MKUtil.writeJSON(true, "替换成功", null);
			} else {
				MKUtil.writeJSON(false, msg, null);
			}
		}
	}

	/***
	 * 删除工序信息
	 */
	public void deleteProcess() {
		try {
			ProcessTemplate pt = procardTemplateServer.findProcessT(id);
			if (pt != null) {
				String msg = procardTemplateServer.delProcessT(pt);
				if(msg.equals("true")){
					MKUtil.writeJSON(true, "删除成功!", null);
				}else{
					MKUtil.writeJSON(false, msg, null);
				}
			} else
				MKUtil.writeJSON(false, "不存在您要删除的工序!", null);
		} catch (Exception e) {
			MKUtil.writeJSON(false, "删除失败!", null);
		}
	}
	/***
	 * 删除工序信息
	 */
	public void deleteAllProcess() {
		try {
			String msg = procardTemplateServer.deleteAllProcess(id);
			if (msg.equals("true")) {
				MKUtil.writeJSON(true, "删除成功!", null);
			} else
				MKUtil.writeJSON(false, msg, null);
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(false, "删除失败!", null);
		}
	}

	/***
	 * 精益计算
	 */
	public String jingyiJisuan() {
		try {
			errorMessage = procardTemplateServer.jingyiJisuan(id,
					procardTemplate);// 产能计算
			errorMessage += procardTemplateServer.jingyiJisuan2(id,
					procardTemplate);// 其他数据计算
			url = "jy_Template_findProcard.jsp?id=" + id;
			if (errorMessage == null || errorMessage.length() <= 0) {
				errorMessage = "计算完成!";
			}
			return ERROR;
		} catch (Exception e) {
			e.printStackTrace();
			errorMessage = "计算失败!";
			return ERROR;
		}
	}

	public void gotoJYBOM() {
		String msg = procardTemplateServer.saveCopyProcardToJY(id);
		if (msg.equals("进入精益BOM成功")) {
			MKUtil.writeJSON(true, "进入精益BOM成功!", null);
		} else {
			MKUtil.writeJSON(false, msg, null);
		}
	}

	public void applySpecial() {
		String msg = procardTemplateServer.applySpecial(id);
		MKUtil.writeJSON(msg);
	}

	public String specialProcessDetail() {
		procardTemplate = procardTemplateServer.getPtByProcessId(id);
		processTemplate = procardTemplateServer.findProcessT(id);
		return "specialProcessDetail";
	}

	public void getQuotedPricebymarkId() {
		procardTemplate = procardTemplateServer.findProcardTemByMarkId(markId,
				pageStatus);
		if (procardTemplate != null) {
			try {
				MKUtil.writeJSON(procardTemplate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 根据id查询流水卡片模板

	public void findProcardTemById() {
		procardTemplate = procardTemplateServer.findProcardTemById(id);
		if (procardTemplate != null) {
			try {
				MKUtil.writeJSON(procardTemplate);
			} catch (Exception e) {
				MKUtil.writeJSON(e);
				e.printStackTrace();
			}
		}
	}
	
	

	public String toUpdateMarkIdCard() {
		return "jianhaoShezhi";
	}

	public String updateMarkIdCard() {
		if (procardTemplate != null
				&& !"".equals(procardTemplate.getStandardSize())
				&& !"".equals(procardTemplate.getErrorRange())
				&& procardTemplate.getId() != null
				&& !"".equals(procardTemplate.getMarkId())) {
			errorMessage = procardTemplateServer.updateProcard(procardTemplate);
		} else {
			if ("yes".equals(type)) {
				errorMessage = "信息有误，修改失败！";
			} else {
				errorMessage = "信息有误，添加失败！";
			}
		}
		return "jianhaoShezhi";
	}

	@SuppressWarnings("unchecked")
	public void findUpdateMarkId() {
		procardTemplateList = procardTemplateServer.findAllMarkId_1(type);
		MKUtil.writeJSON(procardTemplateList);
	}

	public void getProcardTemplateZC() {
		try {
			procardTemplateList = procardTemplateServer.getProcardTemplateZC();
			if (procardTemplateList != null && procardTemplateList.size() > 0) {
				MKUtil.writeJSON(procardTemplateList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON(e);
		}

	}

	public String showListProcardSpecification() {
		if (procardSpecification != null) {
			ActionContext.getContext().getSession().put("attendanceTow",
					procardSpecification);
		} else {
			procardSpecification = (ProcardSpecification) ActionContext
					.getContext().getSession().get("attendanceTow");
		}
		Map<Integer, Object> map = procardTemplateServer
				.findProcardSpecification(procardSpecification, Integer
						.parseInt(cpage), pageSize);
		procardSpecificationList = (List<ProcardSpecification>) map.get(1);
		if (procardSpecificationList != null
				&& procardSpecificationList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("ProcardTemplateAction!showListProcardSpecification.action");
			errorMessage = null;
		} else {
			errorMessage = "没有对应检测记录";
		}
		return "procardSpecification_show";
	}

	public String findProcessNameGroupByMarkId() {
		procardTemplateList = procardTemplateServer
				.findProcessNameGroupByMarkId();
		return "showProcessName";
	}

	//根据件号名称查询模板;
	public void findprocardTemplateByMarkId(){
		procardTemplate = procardTemplateServer.findprocardTemplateByMarkId(markId, name,pageStatus);
		try {
			MKUtil.writeJSON(procardTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//列出系统中所有的件号;
	public void findAllMarkId(){
		try {
			List<String> markIdList = procardTemplateServer.findAllMarkId(markId);
			if(markIdList!=null && markIdList.size()>0){
				MKUtil.writeJSON(markIdList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//对比任意两个件号的下层外购件，并把不相同的外购件列出来(匹配条件:件号，物料类别，版本号，供料属性);
	public String ProcardTemduibiWgj(){
	Map<String, Object>	 map =	procardTemplateServer.ProcardTemduibiWgj(markId1, markId2);
		if(map!=null && map.size()>0){
			pt1_wg = (List<ProcardTemplate>) map.get("pt1_wg");
			pt2_wg = (List<ProcardTemplate>) map.get("pt2_wg");
			pt1_zz = (List<ProcardTemplate>) map.get("pt1_zz");
			pt2_zz = (List<ProcardTemplate>) map.get("pt2_zz");
		}
		return "ProcardTem_duibi";
	}
	public String checkIn(){
		procardTemplateList = procardTemplateServer.CheckIn(jianhaoSet);
		return "findmarkId";
	}
	public ProcardTemplateServer getProcardTemplateServer() {
		return procardTemplateServer;
	}
	
	//根据工序模板Id查询出自制件下层的所有外购件
	public String findwgProcard(){
		Object [] obj=		procardTemplateServer.findwgProcard(id,pageStatus);
		errorMessage = (String) obj[0];
		if("true".equals(errorMessage)){
			errorMessage = "";
			procardTemplateList = (List<ProcardTemplate>) obj[1];
		}
		return "ProcardTem_ProcessAndPrcard";
	}
	public String findwgProcard0(){
		processTemplate =	procardTemplateServer.findProcessTemByProcessId(id);
		Object [] obj=		procardTemplateServer.findwgProcard(processTemplate.getId(),pageStatus);
		errorMessage = (String) obj[0];
		if("true".equals(errorMessage)){
			errorMessage = "";
			procardTemplateList = (List<ProcardTemplate>) obj[1];
		}
		return "ProcardTem_ProcessAndPrcard";
	}
	//该工序所关联的所有外购件
	public void findProcessAndwgProcard(){
		try {
			processAndWgProcardList=procardTemplateServer.findProcessAndwgProcard(id);
			MKUtil.writeJSON(processAndWgProcardList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//工序关联外购件；
	public String processAndwgProcard(){
		successMessage = 	procardTemplateServer.processAndwgProcard(id, markIds);
		return "findwgProcard";
	}
	//判断某个工序是否可以修改计件单价
	public void isNeedJjPrice(){
		try {
			boolean bool = procardTemplateServer.isNeedJjPrice(id);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	//判断某个工序是否可以修改计件单价 批次
	public void isNeedJjPCPrice(){
		try {
			boolean bool = procardTemplateServer.isNeedJjPrice(id);
			MKUtil.writeJSON(bool);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	//修改工序单价并记录修日志
	public void updatProcesPrice(){
		try {
			errorMessage = procardTemplateServer.updatProcesPrice(processTemplate);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	//修改工序单价并记录修日志
	public void updatProcesPcPrice(){
		try {
			errorMessage = procardTemplateServer.updatProcesPcPrice(process);
			MKUtil.writeJSON(errorMessage);
		} catch (Exception e) {
			MKUtil.writeJSON(e);
		}
	}
	//查看修改工序日志记录
	public String showProUpdatePriceLog(){
		if (ppul != null) {
			ActionContext.getContext().getSession().put("ppul",
					ppul);
		} else {
			ppul = (ProcessPriceUpdateLog) ActionContext
					.getContext().getSession().get("ppul");
		}
		Object[] obj = procardTemplateServer.findProUpdatePriceLog(ppul, Integer.parseInt(cpage), pageSize);
		ppulList = (List<ProcessPriceUpdateLog>) obj[0];
		if (ppulList != null
				&& ppulList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this
					.setUrl("ProcardTemplateAction!showProUpdatePriceLog.action");
			errorMessage = null;
		} 
		return "ProPriceUpdateLog_show";
	}
	//导入入工序计件工资
	public String daoruProcessJJMoney(){
		
		try {
			errorMessage =	procardTemplateServer.daoruProcessJJMoney(gygf);
			MKUtil.writeJSON(errorMessage);
			removeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findAllProcardTem";//Template_findProcardList.jsp
	}
	
	
	//根据工序模板Id查询出所有在生产批次的工序单价；
	public String showpcProcess(){
		processList1 = procardTemplateServer.showpcProcess(id);
		return "PcProcess_show";
	}
	//外购绑定工序
	public void processAndWgProcard(){
		try {
			procardTemplateServer.processAndWgProcard(id);
			MKUtil.writeJSON("外购件工序一键关联成功!");
		} catch (Exception e) {
			e.printStackTrace();
			MKUtil.writeJSON("外购件工序一键关联失败!");
		}
		
	}
	//根据总成Id查询出该总成下所有未绑定工序的外购件
	public String findwbdProcessWgProcard(){
		procardTemplate =	procardTemplateServer.findProcardTemById(id);
		procardTemplateList = procardTemplateServer.findwbdProcessWgProcard(id);
		return "pawpt_showList";
	}
	/**
	 * 原图比对,1，零件上有原图工序上没有，则下发2.零件和工序都没有原图的显示出来
	 * @return
	 */
	public String ytcompare(){ 
		list =	procardTemplateServer.ytcompare();
		return "ytcompare";
	}
	public void test(){
		try {
			Integer jindu_sum = (Integer) session.getAttribute("jindu_sum_daoruProcessJJMoney");
			Integer jindu_cl = (Integer) session.getAttribute("jindu_cl_daoruProcessJJMoney");
			if(jindu_sum!=null && jindu_cl!=null){
				MKUtil.writeJSON(new Integer[]{jindu_sum,jindu_cl});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void removeSession(){
		session.removeAttribute("jindu_sum_daoruProcessJJMoney");
		session.removeAttribute("jindu_cl_daoruProcessJJMoney");
	}
	
	/**
	 * 删除重复的板材粉末
	 */
	public void deleteSamebcfm(){
		String msg = procardTemplateServer.deleteSamebcfm();
		MKUtil.writeJSON(msg);
	}
	/**
	 * 根据件号获得总成BOM模板
	 */
	public void getProcardByMarkId(){
		ProcardTemplate template = procardTemplateServer.getProcardByMarkId(markId);
		//在特殊情况 去外购件库查询
		if(template==null && pageStatus!=null && "getYuanclAndWaigj".equals(pageStatus)){
			template = new ProcardTemplate();
			YuanclAndWaigj yuanclAndWaigj = procardTemplateServer.getYuanclAndWaigjByMarkId(markId);
			template.setMarkId(yuanclAndWaigj.getMarkId());
			template.setProName(yuanclAndWaigj.getName());
			template.setUnit(yuanclAndWaigj.getUnit());
			
		}
		
		MKUtil.writeJSON(template);
	}
	/**
	 * 修复试制图纸缺失ProcardTemplateAction!xiufusztu.action
	 */
	public void xiufusztu(){
		procardTemplateServer.xiufusztusztosz(2036128);//试制
		
		// procardTemplateServer.xiufusztu(1263549);//试制
//		// procardTemplateServer.xiufusztu2(1053753);//批产
//		procardTemplateServer.xiufusztu(1705517);// 试制
//		List<Object[]> list = procardTemplateServer.getUnpaotuRootId();
//		Integer notRootId=0;
//		int cgCount=0;
//		StringBuffer sb= new StringBuffer();
//		if(list!=null&&list.size()>0){
//			int s= list.size();
//			int i=0;
//			for(Object[] pt:list){
//				i++;
//				try {
//					notRootId = Integer.parseInt(pt[0].toString());
//					String productStyle = pt[1].toString();
//					if(productStyle.equals("试制")){
////					System.out.println(notRootId);
//						procardTemplateServer.xiufusztu(notRootId);
//					}else{
////					System.out.println(notRootId);
//						procardTemplateServer.xiufusztu2(notRootId);
//					}
//					System.out.println("共"+s+"条!@#$%^&*第"+i+"条--("+notRootId+")--成功");
//					cgCount++;
//				} catch (Exception e) {
//					// TODO: handle exception
//					if(sb.length()==0){
//						sb.append(notRootId);
//					}else{
//						sb.append(","+notRootId);
//					}
//				}
//			}
//		}
//		MKUtil.writeJSON("此次遍历"+list.size()+"个BOM,成功"+cgCount+"个,失败的BOM的rootId为:"+sb.toString());
	}
	/**
	 * 修复下层零件ProcardTemplateAction!xiufuxclingjian.action
	 */
	public void xiufuxclingjian(){
		procardTemplateServer.xiufuxclingjian1(id);//试制对试制，批产对批产
//		procardTemplateServer.xiufuxclingjian2(id);//批产对试制
	}
	
	
	public void gxtbsc(){
		try {
			String msg = procardTemplateServer.gxtbsc(id);
			MKUtil.writeJSON(msg);
		} catch (Exception e) {
			// TODO: handle exception
			MKUtil.writeJSON(e.getMessage());
		}
	}
	
	public void getProcardById(){
		ProcardTemplate template = procardTemplateServer.getProcardById(id);
		MKUtil.writeJSON(template);
	}
	
	public void setProcardTemplateServer(
			ProcardTemplateServer procardTemplateServer) {
		this.procardTemplateServer = procardTemplateServer;
	}
	public String findAllprocardTemplateReplace(){
		if (procardTemplateReplace != null) {
			ActionContext.getContext().getSession().put("procardTemplateReplace",
					procardTemplateReplace);
		} else {
			procardTemplateReplace = (ProcardTemplateReplace) ActionContext.getContext()
					.getSession().get("procardTemplateReplace");
		}
		Object[] object = procardTemplateServer.findAllProcardTemplateReplace(
				procardTemplateReplace, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			procardTemplateReplaceList = (List<ProcardTemplateReplace>) object[0];

			if (procardTemplateReplaceList != null && procardTemplateReplaceList.size() > 0) {
				size = procardTemplateReplaceList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProcardTemplateAction!findAllprocardTemplateReplace.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return  "procardTemplateReplace_all";
	}
	public String findptrById(){
		if(procardTemplateReplace.getId()!=null){
			procardTemplateReplace =procardTemplateServer.findptlateReplce(procardTemplateReplace.getId());
			return "ptReplace_update";
		}else{
			errorMessage ="数据异常";
			return "error";
		}
	}
	public String findAllProcardTemplatePrivilege(){
		if (procardTemplatePrivilege != null) {
			ActionContext.getContext().getSession().put("procardTemplatePrivilege",
					procardTemplatePrivilege);
		} else {
			procardTemplatePrivilege = (ProcardTemplatePrivilege) ActionContext.getContext()
					.getSession().get("procardTemplatePrivilege");
		}
		Object[] object = procardTemplateServer.findAllProcardTemplatePrivilege(
				procardTemplatePrivilege, Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			procardTemplatePrivilegeList = (List<ProcardTemplatePrivilege>) object[0];
			if (procardTemplatePrivilegeList != null && procardTemplatePrivilegeList.size() > 0) {
				size = procardTemplatePrivilegeList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("ProcardTemplateAction!findAllProcardTemplatePrivilege.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return  "procardTemplatePrivilege_all";
	}
	public String delPTPrivilege(){
		if(procardTemplatePrivilege.getId()!=null){
			errorMessage = procardTemplateServer.delPTPrivilege(procardTemplatePrivilege.getId());
			this.setUrl("ProcardTemplateAction!findAllProcardTemplatePrivilege.action");
		}else{
			errorMessage= "数据异常";
		}
		return "error";
	}
	public String toupdatePTPrivilege(){
		if(procardTemplatePrivilege.getId()!=null){
			procardTemplatePrivilege = procardTemplateServer.toupdatePTPrivilege(procardTemplatePrivilege.getId());
			return "procardTemplatePrivilege_update";
		}else{
			errorMessage = "数据异常！";
			return "error";
		}
	}
	public String addPTPrivilege(){
		if(procardTemplatePrivilege!=null){
			errorMessage = procardTemplateServer.addPTPrivilege(procardTemplatePrivilege);
			this.setUrl("ProcardTemplateAction!findAllProcardTemplatePrivilege.action");
		}else{
			errorMessage= "数据异常";
		}
		return "error";
	}
	
	public String updatePTPrivilege(){
		if(procardTemplatePrivilege!=null){
			errorMessage = procardTemplateServer.updatePTPrivilege(procardTemplatePrivilege);
			this.setUrl("ProcardTemplateAction!findAllProcardTemplatePrivilege.action");
		}else{
			errorMessage= "数据异常";
		}
		return "error";
	}
	
	public void testSendMsg(){//ProcardTemplateAction!testSendMsg.action
		try {
			procardTemplateServer.testSendMsg();
			MKUtil.writeJSON(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MKUtil.writeJSON(e.getMessage());
		}
	}
	
	public ProcardTemplate getProcardTemplate() {
		return procardTemplate;
	}

	public void setProcardTemplate(ProcardTemplate procardTemplate) {
		this.procardTemplate = procardTemplate;
	}

	public List<ProcardTemplate> getProcardTemplateList() {
		return procardTemplateList;
	}

	public void setProcardTemplateList(List<ProcardTemplate> procardTemplateList) {
		this.procardTemplateList = procardTemplateList;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProcessTemplate getProcessTemplate() {
		return processTemplate;
	}

	public void setProcessTemplate(ProcessTemplate processTemplate) {
		this.processTemplate = processTemplate;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer[] getProcessId() {
		return processId;
	}

	public void setProcessId(Integer[] processId) {
		this.processId = processId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJobNum() {
		return jobNum;
	}

	public void setJobNum(String jobNum) {
		this.jobNum = jobNum;
	}

	public String getMessagePower() {
		return messagePower;
	}

	public void setMessagePower(String messagePower) {
		this.messagePower = messagePower;
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Float getLaborcost() {
		return laborcost;
	}

	public void setLaborcost(Float laborcost) {
		this.laborcost = laborcost;
	}

	public Float getFenpeiRate() {
		return fenpeiRate;
	}

	public void setFenpeiRate(Float fenpeiRate) {
		this.fenpeiRate = fenpeiRate;
	}

	public Float getDayOutput() {
		return dayOutput;
	}

	public void setDayOutput(Float dayOutput) {
		this.dayOutput = dayOutput;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public List getList1() {
		return list1;
	}

	public void setList1(List list1) {
		this.list1 = list1;
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

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public ProcessTemplateFile getProcessTemplateFile() {
		return processTemplateFile;
	}

	public void setProcessTemplateFile(ProcessTemplateFile processTemplateFile) {
		this.processTemplateFile = processTemplateFile;
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

	public Integer getNextNo() {
		return nextNo;
	}

	public void setNextNo(Integer nextNo) {
		this.nextNo = nextNo;
	}

	public Integer getSonCount() {
		return sonCount;
	}

	public void setSonCount(Integer sonCount) {
		this.sonCount = sonCount;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getProcardStyle() {
		return procardStyle;
	}

	public void setProcardStyle(String procardStyle) {
		this.procardStyle = procardStyle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ProcardSpecification getProcardSpecification() {
		return procardSpecification;
	}

	public void setProcardSpecification(
			ProcardSpecification procardSpecification) {
		this.procardSpecification = procardSpecification;
	}

	public List<ProcardSpecification> getProcardSpecificationList() {
		return procardSpecificationList;
	}

	public void setProcardSpecificationList(
			List<ProcardSpecification> procardSpecificationList) {
		this.procardSpecificationList = procardSpecificationList;
	}

	public List<ProcessTemplate> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessTemplate> processList) {
		this.processList = processList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarkId2() {
		return markId2;
	}

	public void setMarkId2(String markId2) {
		this.markId2 = markId2;
	}

	public List<ProcardTemplate> getPt1_wg() {
		return pt1_wg;
	}

	public void setPt1_wg(List<ProcardTemplate> pt1Wg) {
		pt1_wg = pt1Wg;
	}

	public List<ProcardTemplate> getPt2_wg() {
		return pt2_wg;
	}

	public void setPt2_wg(List<ProcardTemplate> pt2Wg) {
		pt2_wg = pt2Wg;
	}

	public List<ProcardTemplate> getPt1_zz() {
		return pt1_zz;
	}

	public void setPt1_zz(List<ProcardTemplate> pt1Zz) {
		pt1_zz = pt1Zz;
	}

	public List<ProcardTemplate> getPt2_zz() {
		return pt2_zz;
	}

	public void setPt2_zz(List<ProcardTemplate> pt2Zz) {
		pt2_zz = pt2Zz;
	}

	public String getMarkId1() {
		return markId1;
	}

	public void setMarkId1(String markId1) {
		this.markId1 = markId1;
	}

	public String getJianhaoSet() {
		return jianhaoSet;
	}

	public void setJianhaoSet(String jianhaoSet) {
		this.jianhaoSet = jianhaoSet;
	}

	public List<ProcessAndWgProcardTem> getProcessAndWgProcardList() {
		return processAndWgProcardList;
	}

	public void setProcessAndWgProcardList(
			List<ProcessAndWgProcardTem> processAndWgProcardList) {
		this.processAndWgProcardList = processAndWgProcardList;
	}

	public String[] getMarkIds() {
		return markIds;
	}

	public void setMarkIds(String[] markIds) {
		this.markIds = markIds;
	}

	public ProcessPriceUpdateLog getPpul() {
		return ppul;
	}

	public void setPpul(ProcessPriceUpdateLog ppul) {
		this.ppul = ppul;
	}

	public List<ProcessPriceUpdateLog> getPpulList() {
		return ppulList;
	}

	public void setPpulList(List<ProcessPriceUpdateLog> ppulList) {
		this.ppulList = ppulList;
	}

	public List<ProcessInfor> getProcessList1() {
		return processList1;
	}

	public void setProcessList1(List<ProcessInfor> processList1) {
		this.processList1 = processList1;
	}

	public ProcessInfor getProcess() {
		return process;
	}

	public void setProcess(ProcessInfor process) {
		this.process = process;
	}

	public void setProcardArea(Double procardArea) {
		this.procardArea = procardArea;
	}

	public Double getProcardArea() {
		return procardArea;
	}

	public void setProcardCengNum(Integer procardCengNum) {
		this.procardCengNum = procardCengNum;
	}

	public Integer getProcardCengNum() {
		return procardCengNum;
	}

	public void setActualUsedProcardArea(Double actualUsedProcardArea) {
		this.actualUsedProcardArea = actualUsedProcardArea;
	}

	public Double getActualUsedProcardArea() {
		return actualUsedProcardArea;
	}

	public void setListArea(List<ProcardTemplate> listArea) {
		this.listArea = listArea;
	}

	public List<ProcardTemplate> getListArea() {
		return listArea;
	}

	public ProcardTemplateReplace getProcardTemplateReplace() {
		return procardTemplateReplace;
	}

	public void setProcardTemplateReplace(
			ProcardTemplateReplace procardTemplateReplace) {
		this.procardTemplateReplace = procardTemplateReplace;
	}

	public List<ProcardTemplateReplace> getProcardTemplateReplaceList() {
		return procardTemplateReplaceList;
	}

	public void setProcardTemplateReplaceList(
			List<ProcardTemplateReplace> procardTemplateReplaceList) {
		this.procardTemplateReplaceList = procardTemplateReplaceList;
	}

	public ProcardTemplatePrivilege getProcardTemplatePrivilege() {
		return procardTemplatePrivilege;
	}

	public void setProcardTemplatePrivilege(
			ProcardTemplatePrivilege procardTemplatePrivilege) {
		this.procardTemplatePrivilege = procardTemplatePrivilege;
	}

	public List<ProcardTemplatePrivilege> getProcardTemplatePrivilegeList() {
		return procardTemplatePrivilegeList;
	}

	public void setProcardTemplatePrivilegeList(
			List<ProcardTemplatePrivilege> procardTemplatePrivilegeList) {
		this.procardTemplatePrivilegeList = procardTemplatePrivilegeList;
	}
	
	
}