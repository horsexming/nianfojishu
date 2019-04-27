package com.task.action.sop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.UserServer;
import com.task.Server.sop.ProcardTemplateJYServer;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.jy.ProcardTemplateJY;
import com.task.entity.sop.jy.ProcessTemplateJY;
import com.task.util.MKUtil;
/**
 * 精益bom控制层
 * @author txb
 *
 */
public class ProcardTemplateJYAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ProcardTemplateJYServer procardTemplateJYServer;// Server层
	private UserServer userServer;
	private ProcardTemplateJY procardTemplateJY;// 对象
	private ProcessTemplateJY processTemplateJY;// 对象
	private List<ProcardTemplateJY> procardTemplateJYList;// 集合
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer id2;// id
	private String pageStatus;// 页面状态
	private List list;
	private Integer[] processId;// 打印选择数组
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
	private String updateContext;//改进方案
	private String jobNum;
	private Float dayOutput;// 日产量

	private int size;// list大小
	private Float laborcost;// 报价
	private Float fenpeiRate;// 可调系数

	private String markId;// 零件号
	private Integer moveId;// 移动的模板的id
	private Integer targetId;// 目标模板的id
	private List list1;
	private Boolean isAll = false;// 是否修改所有
	private String noandname;//设备、工装、量具信息  格式（  编号::名称）
	
	private File wenImg;//工艺规范图片文件
	private String  wenImgFileName;//工艺规范图片上传时文件名
	private String wenImgContentType;//工艺规范图片上传时文件类型

	/***
	 * 查询所有总成流水卡片模板(分页)
	 * 
	 * @param procardTemplate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showList() {
		if (procardTemplateJY != null) {
			ActionContext.getContext().getSession().put("procardTemplateJY",
					procardTemplateJY);
		} else {
			procardTemplateJY = (ProcardTemplateJY) ActionContext.getContext()
					.getSession().get("procardTemplateJY");
		}
		Object[] object = procardTemplateJYServer.findAllProcardTemplateJY(
				procardTemplateJY, Integer.parseInt(cpage), pageSize,"versionStatus='当前'");
		if (object != null && object.length > 0) {
			procardTemplateJYList = (List<ProcardTemplateJY>) object[0];

			if (procardTemplateJYList != null && procardTemplateJYList.size() > 0) {
				size = procardTemplateJYList.size();// 页面循环使用
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
					pageStatus = null;
					this.setUrl("ProcardTemplateAction!findAllProcardTem.action");
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "procardTemplateJY_show";
	}
	/**
	 * 显示节拍展示修改页面
	 * 
	 * @throws
	 */
	public String initTrial() {
		String jsonStr = procardTemplateJYServer.packageData(id, null);
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
	public String jinyijisuan(){
		procardTemplateJYServer.jingyiJisuan(id, null);
		procardTemplateJYServer.jingyiJisuan2(id, null);
		return "procardTemplateJY_result";
	}
	/**
	 * 显示精益计算结果页面
	 * 
	 * @throws
	 */
	public void initTrial2() {
		String jsonStr = procardTemplateJYServer.packageData2(id, null);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(jsonStr);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ;
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
			procardTemplateJY = procardTemplateJYServer.findProcardTemById(id);
			mentioningAwardPrice = procardTem.getOnePrice();
		} else {
			// mentioningAwardPrice = productPriceServer.getBonus(id);
		}
		String jsonStr = procardTemplateJYServer.packageProduct(procardTemplateJY,
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
	public String toupdatejgljwr(){
		processTemplateJY=procardTemplateJYServer.getProcessJYById(id);
		if(processTemplateJY!=null&&pageStatus!=null){
			if(pageStatus.equals("ji")){
				return "jy_updateji";
			}else if(pageStatus.equals("gong")){
				return "jy_updategong";
			}else if(pageStatus.equals("liang")){
				return "jy_updateliang";
			}else if(pageStatus.equals("jian")){
				return "jy_updatejian";
			}else if(pageStatus.equals("wen")){
				if(processTemplateJY.getProgressStatus()!=null&&processTemplateJY.getProgressStatus().equals("执行中")
						&&processTemplateJY.getMarkId()!=null){
					Map<Integer,Integer> map=procardTemplateJYServer.getGygcIdAndProcessDataId(processTemplateJY.getMarkId(),processTemplateJY.getProcessNO());
				    id=map.get(1);//工艺规程id
				    id2=map.get(2);//工艺工序id
				}
				return "jy_updatewen";
			}else if(pageStatus.equals("ren")){
				return "jy_updateren";
			}
		}
		return null;
	}
	public String updatejgljwr(){
		if(processTemplateJY!=null&&pageStatus!=null){
			boolean b=procardTemplateJYServer.updateProcessJY(processTemplateJY,pageStatus,updateContext);
			processTemplateJY=procardTemplateJYServer.getProcessJYById(processTemplateJY.getId());
			b=b&procardTemplateJYServer.setBomProgressStatusByprocessId(processTemplateJY.getId(),"分析中");
			if(b){
				successMessage="修改成功！";
			}else{
				successMessage="修改失败！";
			}
			if(pageStatus.equals("ji")){
				return "jy_updateji";
			}else if(pageStatus.equals("gong")){
				return "jy_updategong";
			}else if(pageStatus.equals("liang")){
				return "jy_updateliang";
			}else if(pageStatus.equals("jian")){
				return "jy_updatejian";
			}else if(pageStatus.equals("wen")){
				
				return "jy_updatewen";
			}else if(pageStatus.equals("ren")){
				return "jy_updateren";
			}
		}
		return null;
	}
	public void todoplan(){
		processTemplateJY=procardTemplateJYServer.getProcessJYById(id);
		if(processTemplateJY==null){
			MKUtil.writeJSON(false, "对不起您选中的精益模板不存在！", null);
			 return ;
		}
		if(processTemplateJY.getProgressStatus()==null
				||!processTemplateJY.getProgressStatus().equals("分析中")){
			MKUtil.writeJSON(false, "只有分析中的状态才可以完成分析，您现在的状态为："+processTemplateJY.getProgressStatus(), null);
			 return ;
		}
		boolean b=procardTemplateJYServer.checkisOK(id);//查看是否能精益计算通过
		if(b){
			procardTemplateJYServer.setBomProgressStatusByRootId(id,"执行中");
		}
		MKUtil.writeJSON(b, "进入执行状态失败，改进没有全部成功！", null);
	}
	public void getAllName(){
		if(noandname!=null){
			String[]sbgzljmsgs=noandname.split("::");
			String no="";
			String name="";
			if(sbgzljmsgs.length==2){
				no=sbgzljmsgs[0];
				name=sbgzljmsgs[1];
			}else{
				no=sbgzljmsgs[0];
				name=sbgzljmsgs[0];
				
			}
			List sbgzljlist=procardTemplateJYServer.findSBGZLJList(no,name,pageStatus);
			MKUtil.writeJSON(sbgzljlist);
		}
	}
	public void updateupdateSBGZLJ(){
		if(noandname!=null){
			String[]sbgzljmsgs=noandname.split("::");
			String no="";
			String name="";
			if(sbgzljmsgs.length==2){
				no=sbgzljmsgs[0];
				name=sbgzljmsgs[1];
			}else{
				no=sbgzljmsgs[0];
				name=sbgzljmsgs[0];
			}
		boolean b=procardTemplateJYServer.updateProcessSBGZLJ(no,name,id, pageStatus);
		MKUtil.writeJSON(b, null, null);
		}
	}
//	public String updatewen(){
//		processTemplateJY=procardTemplateJYServer.getProcessJYById(processTemplateJY.getId());
//		if (wenImg != null) {
////			// 判断上传文件是否为图片类型
////			if (wenImgFileName==null||(!wenImgFileName.endsWith(".jpeg")
////					& !wenImgFileName.endsWith(".jpg")
////					& !wenImgFileName.endsWith(".gif")
////					& !wenImgFileName.endsWith(".png")
////					& !wenImgFileName.endsWith(".bmp")
////					& !wenImgFileName.endsWith(".ico")
////					& !wenImgFileName.endsWith(".icon"))) {
////				successMessage="上传文件不是证书类型";
////				return "jy_updatewen";
////			}
//			// 打开存放上传文件的位置
//			String path =  ServletActionContext.getServletContext().getRealPath("/upload/file/procardTemplateJY");
//			
//			File file1 = new File(path);
//			if (!file1.exists()) {
//				file1.mkdirs();// 如果不存在文件夹就创建
//			}
//			
//			// 将文件写入文件夹中
//			InputStream is = null;
//			OutputStream os = null;
//			String[] names=wenImgFileName.split("\\.");
//			String type=names[1];
//			String savefileName=processTemplateJY.getMarkId()+"_"+processTemplateJY.getId()+"."+type;
//			try {
//				is = new FileInputStream(wenImg);
//				File file2 = new File(path + "/" +savefileName );
//				if (file2.exists()) {
//					file2.delete();// 将原文件删掉
//				}
//				os = new FileOutputStream(path + "/" + savefileName);
//				byte[] b = new byte[1024];
//				int length = 0;
//				while (-1 != (length = is.read(b, 0, b.length))) {
//					os.write(b);
//				}
//				processTemplateJY.setFileName("upload/file/procardTemplateJY" + "/"+savefileName);
//				boolean bool=procardTemplateJYServer.updateFileName(processTemplateJY);
//				if(bool){
//					successMessage="上传成功！";
//				}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				successMessage="找不到文件！";
//				return "jy_updatewen";
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				successMessage="文件输入出错！";
//				return "jy_updatewen";
//			} finally {
//				try {
//					os.close();
//					is.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return "jy_updatewen";
//		}
//		return null;
//	}
	/**
	 * 获取所有部门
	 */
	public void getdept (){
		List list = procardTemplateJYServer.getDept();
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 通过部门id获取其部门人员
	 */
	public void getusers(){
		List list=procardTemplateJYServer.getUsersByDeptId(id,processTemplateJY.getId());
		try {
			MKUtil.writeJSON(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改操作人员
	 */
	public void updateren(){
		if(id!=null&&id!=0&&processTemplateJY!=null&&processTemplateJY.getId()!=null){
			boolean b=procardTemplateJYServer.updateOperator(id,processTemplateJY.getId());
			if(b){
				MKUtil.writeJSON(b,null,null);
			}else{
				MKUtil.writeJSON(b,"修改失败！",null);
			}
		}
	}
	public void backtoProcardTemplate(){
		Map<Integer ,Object>map=procardTemplateJYServer.saveBacktoProcardTemplate(id);
		Boolean b=Boolean.parseBoolean(map.get(1).toString());
		if(b){
			procardTemplateJYServer.setBomProgressStatusByRootId(id,"完成");
		}
		MKUtil.writeJSON(b, map.get(2).toString(), null);
	}
	public String gongyiguifanView(){
		processTemplateJY=procardTemplateJYServer.getProcessJYById(id);
		return "jy_gygfview";
	}
	
	public ProcardTemplateJYServer getProcardTemplateJYServer() {
		return procardTemplateJYServer;
	}

	public void setProcardTemplateJYServer(
			ProcardTemplateJYServer procardTemplateJYServer) {
		this.procardTemplateJYServer = procardTemplateJYServer;
	}

	public ProcardTemplateJY getProcardTemplateJY() {
		return procardTemplateJY;
	}

	public void setProcardTemplateJY(ProcardTemplateJY procardTemplateJY) {
		this.procardTemplateJY = procardTemplateJY;
	}

	public List<ProcardTemplateJY> getProcardTemplateJYList() {
		return procardTemplateJYList;
	}

	public void setProcardTemplateJYList(List<ProcardTemplateJY> procardTemplateJYList) {
		this.procardTemplateJYList = procardTemplateJYList;
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

	public ProcessTemplateJY getProcessTemplateJY() {
		return processTemplateJY;
	}

	public void setProcessTemplateJY(ProcessTemplateJY processTemplateJY) {
		this.processTemplateJY = processTemplateJY;
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
	public UserServer getUserServer() {
		return userServer;
	}
	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}
	public String getUpdateContext() {
		return updateContext;
	}
	public void setUpdateContext(String updateContext) {
		this.updateContext = updateContext;
	}
	public String getNoandname() {
		return noandname;
	}
	public void setNoandname(String noandname) {
		this.noandname = noandname;
	}
	public File getWenImg() {
		return wenImg;
	}
	public void setWenImg(File wenImg) {
		this.wenImg = wenImg;
	}
	public String getWenImgFileName() {
		return wenImgFileName;
	}
	public void setWenImgFileName(String wenImgFileName) {
		this.wenImgFileName = wenImgFileName;
	}
	public String getWenImgContentType() {
		return wenImgContentType;
	}
	public void setWenImgContentType(String wenImgContentType) {
		this.wenImgContentType = wenImgContentType;
	}
	public Integer getId2() {
		return id2;
	}
	public void setId2(Integer id2) {
		this.id2 = id2;
	}
	
	

}