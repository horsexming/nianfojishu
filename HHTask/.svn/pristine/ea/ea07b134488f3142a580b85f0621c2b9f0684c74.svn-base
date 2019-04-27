package com.task.action.sop.ycl;

import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.sop.ycl.YuanclAndWaigjServer;
import com.task.entity.sop.ycl.FenMoTzRecord;
import com.task.entity.sop.ycl.WaiGouJianLT;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.task.util.MKUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class YuanclAndWaigjAction {
	private YuanclAndWaigjServer yuanclAndWaigjServer;// 原材料和外购件服务层
	private YuanclAndWaigj yuanclAndWaigj;// 原材料和外购件对象
	private List<YuanclAndWaigj> YuanclAndWaigjList;// 原材料和外购件列表
	private List<ZhuserOffer> zhuserOfferList;// 报价列表
	private List<ZhUser> zhUserList;// 报价列表
	private String status;// (失效 ：invalid 新增 ：newly)
	private WaiGouJianLT lt1;
	private List<WaiGouJianLT> ltList;
	private FenMoTzRecord fmtzr;
	private List<FenMoTzRecord> fmtzrList;
	private Integer[] zhUserId;
	private String[] zhUserIds;
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer[] offerId;
	private String offerIds;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String tag;
	private int id;
	private Integer  yuanId;
	private File file;
	private String deadline;
	private String cycle;
	private String warehouse;
	private String markId;
	private String productStyle;
	private String nowdate;
	private ZhUser zhUser;
	private String issong;
	private String pageStatus;
	private String kgliao;
	/**
	 * 分页显示列表
	 * 
	 * @return
	 */
	public String showList() {
		if (yuanclAndWaigj != null) {
			ActionContext.getContext().getSession().put("yuanclAndWaigj",
					yuanclAndWaigj);
		} else {// 用来保持分页时带着查询条件
			yuanclAndWaigj = (YuanclAndWaigj) ActionContext.getContext()
					.getSession().get("yuanclAndWaigj");
		}
		tag = yuanclAndWaigjServer.noUpdateYuan(tag);
		yuanclAndWaigjServer.updatepriceStatus();
		Map<Integer, Object> map = yuanclAndWaigjServer
				.findYuanclAndWaigjsByCondition(yuanclAndWaigj, Integer
						.parseInt(cpage), pageSize,productStyle,pageStatus);
		YuanclAndWaigjList = (List<YuanclAndWaigj>) map.get(1);// 显示页的原材料和外购件列表
		if (YuanclAndWaigjList != null && YuanclAndWaigjList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("yuanclAndWaigjAction_showList.action?productStyle="+productStyle+"&tag="+tag+"&pageStatus="+pageStatus);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if("yltz".equals(pageStatus)){
			return "yongliangTz";
		}
		return "yuanclAndWaigj_show";
	}

	/*
	 * 根据外购件的物料类别查询全部相关供应商
	 */
	public void findZhUserByType() {
		yuanclAndWaigj = yuanclAndWaigjServer.getById(yuanId);
		
		if (yuanclAndWaigj != null) {
			boolean b = yuanclAndWaigjServer.saveZhUserByType(yuanclAndWaigj,deadline);
			if (b) {
				errorMessage= yuanclAndWaigjServer.bandZhuser(zhUserId, yuanId);
				MKUtil.writeJSON(errorMessage);
			} else{
				MKUtil.writeJSON("关联失败");
			}
		} else {
			MKUtil.writeJSON("无该外键");
		}

	}
	public void piliangbaojia() {
		String error_str ="";
		if(offerIds!=null&&!"".equals(offerIds)){
			String[] yuanIds = offerIds.split(",");
			for(String yuanId:yuanIds){
				yuanclAndWaigj = yuanclAndWaigjServer.getById(Integer.parseInt(yuanId));
				if (yuanclAndWaigj != null) {
					boolean b = yuanclAndWaigjServer.saveZhUserByType(yuanclAndWaigj,deadline);
					if (b) {
						errorMessage= yuanclAndWaigjServer.bandZhuser(zhUserId, Integer.parseInt(yuanId));
					} else{
						error_str +=yuanclAndWaigj.getMarkId()+"关联失败";
					}
				}
			}
			if(!"".equals(error_str)){
				MKUtil.writeJSON("error_str");
			}else{
				MKUtil.writeJSON("关联成功！");
			}
		}
	}
	public String findAllZhOffer() {
		yuanclAndWaigj = yuanclAndWaigjServer.getById(yuanclAndWaigj.getId());
		if (yuanclAndWaigj != null) {
			zhuserOfferList = yuanclAndWaigjServer
					.findzhOfferById(yuanclAndWaigj);
		} else {
			errorMessage = "无该外购件，请检查";
		}
		if("yangpin".equals(status)){//样品确认
			return "forwaigoujian_allZoffer";
		}else if("jiage".equals(status)){
			return "waigoujianPrice_queren";
		}else{
			return "findByYw_List";
		}
	}
	
	public String findAllZhOfferdaidayang() {
		if (yuanclAndWaigj != null) {
			zhuserOfferList = yuanclAndWaigjServer
					.findzhOfferById(yuanclAndWaigj);
		} else {
			errorMessage = "无该外购件，请检查";
		}
		return "findByYw_List";
	}
	public String findOne(){
		if (zhUser != null) {
			ActionContext.getContext().getSession().put("zhUser",
					zhUser);
		} else {// 用来保持分页时带着查询条件
			zhUser = (ZhUser) ActionContext.getContext()
					.getSession().get("zhUser");
		}
		if(yuanclAndWaigj.getId()!=null){
			yuanclAndWaigj = yuanclAndWaigjServer.getById(yuanclAndWaigj.getId());
			Map<Integer, Object> map =yuanclAndWaigjServer.checkZhUser(zhUser, Integer
					.parseInt(cpage), pageSize,yuanclAndWaigj.getWgType());
				zhUserList = (List<ZhUser>) map.get(1);// 显示页的原材料和外购件列表
				if (zhUserList != null & zhUserList.size() > 0) {
					int count = (Integer) map.get(2);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("yuanclAndWaigjAction_findOne.action?yuanclAndWaigj.id="+yuanclAndWaigj.getId());
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
			
		}
		return "deadline_add";
	}
	public String adddeadline(){
		if (zhUser != null) {
			ActionContext.getContext().getSession().put("zhUser",
					zhUser);
		} else {// 用来保持分页时带着查询条件
			zhUser = (ZhUser) ActionContext.getContext()
					.getSession().get("zhUser");
		}
		if(offerIds!=null){
			Map<Integer, Object> map  = yuanclAndWaigjServer.findyuan(offerIds);
			if("no".equals((String) map.get(1))){
				errorMessage ="选择相同的物料类别的外购件";
			}else{
				YuanclAndWaigjList = (List<YuanclAndWaigj>)map.get(2);
				Map<Integer, Object> map2 =yuanclAndWaigjServer.checkZhUser(zhUser, Integer
						.parseInt(cpage), pageSize,(String) map.get(3));
					zhUserList = (List<ZhUser>) map2.get(1);// 显示页的原材料和外购件列表
					if (zhUserList != null & zhUserList.size() > 0) {
						int count = (Integer) map2.get(2);
						int pageCount = (count + pageSize - 1) / pageSize;
						this.setTotal(pageCount + "");
						this.setUrl("yuanclAndWaigjAction_adddeadline.action?offerIds="+offerIds);
					} else {
						errorMessage = "没有找到你要查询的内容,请检查后重试!";
					}
			}
		}
		return "deadline_add_sum";
	}
	public String showWuXiaoList() {
		if (yuanclAndWaigj != null) {
			ActionContext.getContext().getSession().put("newyuanclAndWaigj",
					yuanclAndWaigj);
		} else {// 用来保持分页时带着查询条件
			yuanclAndWaigj = (YuanclAndWaigj) ActionContext.getContext()
					.getSession().get("newyuanclAndWaigj");
		}
		if (kgliao != null) {
			ActionContext.getContext().getSession().put("kgliao",
					kgliao);
		} else {// 用来保持分页时带着查询条件
			kgliao = (String) ActionContext.getContext()
					.getSession().get("kgliao");
		}
		Map<Integer, Object> map = yuanclAndWaigjServer
				.findYuanclAndWaigjsByStatus(yuanclAndWaigj, Integer
						.parseInt(cpage), pageSize, status,kgliao);
		YuanclAndWaigjList = (List<YuanclAndWaigj>) map.get(1);// 显示页的原材料和外购件列表
		if (YuanclAndWaigjList != null & YuanclAndWaigjList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("yuanclAndWaigjAction_showWuXiaoList.action?status="
					+ status);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "yuanclAndWaigjByStatus_show";
	}

	public void explorExcel() {
		yuanclAndWaigjServer.explorExcel(yuanclAndWaigj);
	}

	/**
	 * 添加外购件原材料
	 * 
	 * @return
	 */
	public String add() {
		String msg = yuanclAndWaigjServer.add(yuanclAndWaigj);
		if (msg.equals("添加成功")) {
			successMessage = "添加成功!";
		} else {
			successMessage = msg;
		}
		return "yuanclAndWaigj_add";
		// return "error";
	}

	/**
	 * 跳完修改原材料外购件信息
	 * 
	 * @return
	 */
	public String toupdate() {
		yuanclAndWaigj = yuanclAndWaigjServer.getById(yuanclAndWaigj.getId());
		if("xgwgType".equals(status)||"xgperiod".equals(status)){
			return "yuanclAndWaigj_xgWgType";
		}else if("showWgType".equals(status)){
			return "yuanclAndWaigj_showWgType";
		}else if("tzfenmo".equals(status)){
			return "fenmotz_add";
		}
		return "yuanclAndWaigj_update";
	}
	
	
	/**
	 *修改原材料外购件信息
	 * 
	 * @return
	 */
	public String update() {
		String msg = yuanclAndWaigjServer.update(yuanclAndWaigj,tag);
		if (msg.equals("修改成功")) {
			errorMessage = "修改成功!";
			url = "yuanclAndWaigjAction_showList.action?cpage=" + cpage+"&tag="+tag;
			yuanclAndWaigj = null;
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 
	 * @return
	 */
	public String updateStatus() {
		String msg = yuanclAndWaigjServer.updateStatus(yuanclAndWaigj);
		errorMessage = msg;
		url = "yuanclAndWaigjAction_showList.action?cpage=" + cpage;
		return "error";
	}
	public String dayafter(){
		Integer cycle  =Integer.parseInt(deadline);
		String a = Util.getSpecifiedDayAfter(Util.getDateTime("yyyy-MM-dd"),cycle);
		MKUtil.writeJSON(a);
		return null;
	}
	/**
	 *删除原材料外购件信息
	 * 
	 * @return
	 */
	public String delete() {
		String msg = yuanclAndWaigjServer.delete(yuanclAndWaigj);
		yuanclAndWaigj = null;
		if (msg.equals("true")) {
			errorMessage = "删除成功!";
			url="yuanclAndWaigjAction_showList.action?cpage="+cpage+"&productStyle="+productStyle+"&tag="+tag;
		} else {
			errorMessage = msg;
		}
		return "error";
	}

	/**
	 * 通过件号，名称，牌号或规格的少部分字段获取整个外购件原材信息
	 */
	public void getAllNames() {
		if (yuanclAndWaigj != null
				&& (yuanclAndWaigj.getMarkId() != null || yuanclAndWaigj
						.getName() != null)) {
			YuanclAndWaigjList = yuanclAndWaigjServer
					.getAllNames(yuanclAndWaigj);
			MKUtil.writeJSON(YuanclAndWaigjList);
		}
	}
	
	/**
	 * 更新已存在原材料和外购件
	 * 
	 * @return
	 */
	public String gethad() {
		boolean b = yuanclAndWaigjServer.updateHad();
		if (b) {
			successMessage = "更新成功!";
		} else {
			successMessage = "更新失败!";
		}
		return showList();
	}

	/**
	 * 通过原材料的件号和规格，获取原材料的比重
	 */
	public void getYWbytrademark() {
		yuanclAndWaigj = yuanclAndWaigjServer.getYWbytrademark(yuanclAndWaigj
				.getMarkId(), yuanclAndWaigj.getSpecification());
		if (yuanclAndWaigj != null) {
			try {
				MKUtil.writeJSON(yuanclAndWaigj.getBili());
			} catch (Exception e) {
				e.printStackTrace();
				MKUtil.writeJSON(e);
			}
		}
	}

	public void explorDaiLuru() {
		if (yuanclAndWaigj != null) {
			yuanclAndWaigjServer.exportNoPrice(yuanclAndWaigj);
		}
	}
	/**
	 * 根据客供属性分裂外购件
	 */
	public void KgLiaoPart() {
		yuanclAndWaigjServer.KgLiaoPart();
	}
	/**
	 * 
	 * 修改物料类别同时修改同件号同版本下BOM和Procard上的物料类别
	 */
	public String xgWgType(){
		errorMessage =	yuanclAndWaigjServer.xgWgType(yuanclAndWaigj);
		if("true".equals(errorMessage)){
			errorMessage = "申请成功!";
		}
		return "yuanclAndWaigj_xgWgType";
	}
	
	//添加外购件LT
	public String addWaiGouJianLt(){
		errorMessage =	yuanclAndWaigjServer.addWaiGouJianLt(lt1);
		return "waigoujianLt_add";
	}
	//条件查询外购件LT
	public String findltList(){
		if (lt1 != null) {
			ActionContext.getContext().getSession().put("lt1",
					lt1);
		} else {// 用来保持分页时带着查询条件
			lt1 = (WaiGouJianLT) ActionContext.getContext()
					.getSession().get("WaiGouJianLT");
		}
	Object[] obj = yuanclAndWaigjServer
				.findALlWaiGouJianLT(lt1,Integer
						.parseInt(cpage), pageSize, status);
		ltList = (List<WaiGouJianLT>) obj[0];
		if (ltList != null & ltList.size() > 0) {
			int count = (Integer) obj[1];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("yuanclAndWaigjAction_findltList.action?status="
					+ status);
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "waigoujianLt_show";
	}
	//根据Id查询外购件LT
	public String findltById(){
		lt1 =	yuanclAndWaigjServer.findWaiGouJianLTById(id);
		return "waigoujianLt_update";
	}
	//修改外购件LT
	public String updatelt(){
		errorMessage =	yuanclAndWaigjServer.updateWaiGouJianLt(lt1);
		return "waigoujianLt_update";
	}
	//删除外购件LT
	public String dellt(){
		errorMessage =	yuanclAndWaigjServer.delWaiGouJianLt(lt1);
		return "findltList";
	}
	//查询所有外购件LT
	public void findALllt(){
		ltList = yuanclAndWaigjServer.findAllltList();
		MKUtil.writeJSON(ltList);
	}
	//修改周期
	public String xgperiod(){
		errorMessage =	yuanclAndWaigjServer.xgperiod(yuanclAndWaigj);
		return "yuanclAndWaigj_xgWgType";
	}
	//导入采购周期
	public String daoruperiod(){
		yuanclAndWaigjServer.daoruperiod(file);
		return "yuanclAndWaigj_xgWgType";
	}
	public String daoruyuanclAndWaigj(){
		errorMessage=yuanclAndWaigjServer.importFile(file);
		return "error";
	}
	//根据库别和件号查询
	public void checkYuanclAndWaigjBycode(){
		List<YuanclAndWaigj> list = yuanclAndWaigjServer.checkYuanclAndWaigjBycode(markId, warehouse);
		if(null!=list && list.size()>0){
			MKUtil.writeJSON(list.get(0));
		}
	}
	
	//根据外购件表查询相应信息
	public void checkYuanclAndWaigjByinfo(){
		
		if(tag!=null&& tag.equals("getWaiGouJian")){
			YuanclAndWaigjList =yuanclAndWaigjServer.checkYuanclAndWaigjBycode(yuanclAndWaigj,tag);
		}else{
			YuanclAndWaigjList =yuanclAndWaigjServer.checkYuanclAndWaigjBycode(yuanclAndWaigj);
			//List<YuanclAndWaigj> list = yuanclAndWaigjServer.checkYuanclAndWaigjBycode(yuanclAndWaigj);
		}
		if(null!=YuanclAndWaigjList && YuanclAndWaigjList.size()>0){
			MKUtil.writeJSON(YuanclAndWaigjList.get(0));
		}
	}
	public String findAllZhusers(){
		if (zhUser != null) {
			ActionContext.getContext().getSession().put("zhUser",
					zhUser);
		} else {// 用来保持分页时带着查询条件
			zhUser = (ZhUser) ActionContext.getContext()
					.getSession().get("zhUser");
		}
		nowdate = Util.getDateTime("yyyy-MM-dd");
		yuanclAndWaigj = yuanclAndWaigjServer.getById(yuanclAndWaigj.getId());
		if(yuanclAndWaigj.getBjEndDate()!=null){
		if(nowdate.compareTo(yuanclAndWaigj.getBjEndDate())<0||nowdate.compareTo(yuanclAndWaigj.getBjEndDate())==0){
			Map<Integer, Object> map =yuanclAndWaigjServer.checkZhUser(zhUser, Integer
					.parseInt(cpage), pageSize,yuanclAndWaigj.getWgType());
				zhUserList = (List<ZhUser>) map.get(1);// 显示页的原材料和外购件列表
				if (zhUserList != null & zhUserList.size() > 0) {
					int count = (Integer) map.get(2);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("yuanclAndWaigjAction_findAllZhusers.action?yuanclAndWaigj.id="+yuanclAndWaigj.getId());
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}
				return "zhuserByType_all";
		}else{
			errorMessage="已经超过报价时间,截止日期为："+yuanclAndWaigj.getBjEndDate();
			return "error";
		}
		}else{
			errorMessage="请点击左边的选项添加截止日期";
			return "error";
		}
	}
	public String bandZhusers(){
		if(zhUserId.length>0&&zhUserId!=null){
			errorMessage= yuanclAndWaigjServer.bandZhuser(zhUserId, yuanclAndWaigj.getId());
			return "error";
		}else{
			errorMessage="请选择供应商";
			return "error";
		}
	}
	//批量审批
	public String auditYclWgl(){
		errorMessage = yuanclAndWaigjServer.auditYclWgl(zhUserId, id);
		return "showList";
	}
	
	//根据件号查询库存、在途、占用量
	public String findMarkIdByMRPCount() {
		if(yuanclAndWaigj!=null) {
			ActionContext.getContext().getSession().put("findMarkIdByMRPCount", yuanclAndWaigj);
		}else {
			yuanclAndWaigj = (YuanclAndWaigj) ActionContext.getContext().getSession().get("findMarkIdByMRPCount");
		}
		Map<String, Object> map = yuanclAndWaigjServer.findMarkIdByMRPCount(yuanclAndWaigj, pageSize, Integer.parseInt(cpage), pageStatus);
		if(map!=null) {
			YuanclAndWaigjList = (List<YuanclAndWaigj>) map.get("list");
			Integer count = (Integer) map.get("count");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("yuanclAndWaigjAction_findMarkIdByMRPCount.action?pageStatus="+pageStatus);
			
		}
		return "yuanclandwaigj_MRPCount";
	}
	
	public void explorExcelMRPCount() {
		if(yuanclAndWaigj!=null) {
			ActionContext.getContext().getSession().put("findMarkIdByMRPCount", yuanclAndWaigj);
		}else {
			yuanclAndWaigj = (YuanclAndWaigj) ActionContext.getContext().getSession().get("findMarkIdByMRPCount");
		}
		Map<String, Object> map = yuanclAndWaigjServer.findMarkIdByMRPCount(yuanclAndWaigj, 0, 0, pageStatus);
		if(map!=null) {
			YuanclAndWaigjList = (List<YuanclAndWaigj>) map.get("list");
			
			String title[] ={"序号","物料类别","件号","名称","版本","供料属性","规格","库存量","在途量","占用量","呆滞数量"};
			String fileName = "物料呆滞分析.xls";
			try {
				HttpServletResponse response = (HttpServletResponse) ActionContext
						.getContext().get(ServletActionContext.HTTP_RESPONSE);
				fileName = new String(fileName.getBytes(),"ISO8859-1");
	            response.setContentType("application/octet-stream;charset=ISO8859-1");
	            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
	            response.addHeader("Pargam", "no-cache");
	            response.addHeader("Cache-Control", "no-cache");
		        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();

		        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		        HSSFSheet sheet = wb.createSheet(fileName);

		        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		        HSSFRow row = sheet.createRow(0);

		        // 第四步，创建单元格，并设置值表头 设置表头居中
		        HSSFCellStyle style = wb.createCellStyle();
		        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		        //声明列对象
		        HSSFCell cell = null;
		        
		        //创建标题
		        for(int i=0;i<title.length;i++){
		            cell = row.createCell(i);
		            cell.setCellValue(title[i]);
		            cell.setCellStyle(style);
		        }
		        
		        HSSFCellStyle contextstyle =wb.createCellStyle();
		        HSSFDataFormat df = wb.createDataFormat(); // 此处设置数据格式
		        contextstyle.setDataFormat(df.getBuiltinFormat("#,#000"));//数据格式只显示整数
		        
		        for(int i= 0;i<YuanclAndWaigjList.size();i++) {
		        	YuanclAndWaigj wgj = YuanclAndWaigjList.get(i);
		        	row = sheet.createRow(i + 1);
		        	row.createCell(0).setCellStyle(contextstyle);
		        	row.createCell(0).setCellValue(i+1);//序号
		        	
		        	row.createCell(1).setCellValue(wgj.getWgType());//物料类别
		        	row.createCell(2).setCellValue(wgj.getMarkId());//件号
		        	row.createCell(3).setCellValue(wgj.getName());//名称
//		        	String title[] ={"序号","物料类别","件号","名称","版本","供料属性","规格","库存量","在途量","占用量","呆滞数量"};
		        	row.createCell(4).setCellValue(wgj.getBanbenhao()); //版本
		        	row.createCell(5).setCellValue(wgj.getKgliao());
		        	row.createCell(6).setCellValue(wgj.getSpecification());
		        	
		        	row.createCell(7).setCellStyle(contextstyle);
		        	row.createCell(7).setCellValue(wgj.getKcCount());
		        	
		        	row.createCell(8).setCellStyle(contextstyle);
		        	row.createCell(8).setCellValue(wgj.getZtCount());
		        	
		        	row.createCell(9).setCellStyle(contextstyle);
		        	row.createCell(9).setCellValue(wgj.getZyCount());
		        	
		        	row.createCell(10).setCellStyle(contextstyle);
		        	row.createCell(10).setCellValue(wgj.getDzCount());
		        }
				OutputStream os = response.getOutputStream();
				wb.write(os);
				os.flush();
				wb.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println();
			}
			
		}
		
	}
	/**
	 * 粉末用量调整申请
	 * @return
	 */
	public String fenMoTzSq(){
		errorMessage =	yuanclAndWaigjServer.fenMoTzSq(fmtzr);
		if("true".equals(errorMessage)){
			errorMessage ="申请成功!~";
		}
		return "findAllListfmtr";
	}
	public String findAllListfmtr(){
		if(fmtzr!=null) {
			ActionContext.getContext().getSession().put("fmtzr", fmtzr);
		}else {
			fmtzr = (FenMoTzRecord) ActionContext.getContext().getSession().get("fmtzr");
		}
		Object[] obj = yuanclAndWaigjServer.findAllListfmtr(fmtzr, pageSize, Integer.parseInt(cpage), pageStatus);
		if(obj!=null && obj.length==2) {
			fmtzrList = (List<FenMoTzRecord>) obj[1];
			Integer count = (Integer) obj[0];
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("yuanclAndWaigjAction_findAllListfmtr.action?pageStatus="+pageStatus);
		}
		return "fmtzrList_show";
	}
	
	public String tzylPL(){
 		yuanclAndWaigjServer.tzylPL(zhUserId, fmtzrList);
		if("true".equals(errorMessage)){
			errorMessage ="申请成功!~";
		}
		return "findAllListfmtr";
	}
	
	public List<ZhuserOffer> getZhuserOfferList() {
		return zhuserOfferList;
	}

	public void setZhuserOfferList(List<ZhuserOffer> zhuserOfferList) {
		this.zhuserOfferList = zhuserOfferList;
	}

	public YuanclAndWaigjServer getYuanclAndWaigjServer() {
		return yuanclAndWaigjServer;
	}

	public void setYuanclAndWaigjServer(
			YuanclAndWaigjServer yuanclAndWaigjServer) {
		this.yuanclAndWaigjServer = yuanclAndWaigjServer;
	}

	public YuanclAndWaigj getYuanclAndWaigj() {
		return yuanclAndWaigj;
	}

	public void setYuanclAndWaigj(YuanclAndWaigj yuanclAndWaigj) {
		this.yuanclAndWaigj = yuanclAndWaigj;
	}

	public List<YuanclAndWaigj> getYuanclAndWaigjList() {
		return YuanclAndWaigjList;
	}

	public void setYuanclAndWaigjList(List<YuanclAndWaigj> yuanclAndWaigjList) {
		YuanclAndWaigjList = yuanclAndWaigjList;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	
	public WaiGouJianLT getLt1() {
		return lt1;
	}

	public void setLt1(WaiGouJianLT lt1) {
		this.lt1 = lt1;
	}

	public List<WaiGouJianLT> getLtList() {
		return ltList;
	}

	public void setLtList(List<WaiGouJianLT> ltList) {
		this.ltList = ltList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public Integer getYuanId() {
		return yuanId;
	}

	public void setYuanId(Integer yuanId) {
		this.yuanId = yuanId;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getProductStyle() {
		return productStyle;
	}

	public void setProductStyle(String productStyle) {
		this.productStyle = productStyle;
	}

	public String getNowdate() {
		return nowdate;
	}

	public void setNowdate(String nowdate) {
		this.nowdate = nowdate;
	}

	public ZhUser getZhUser() {
		return zhUser;
	}

	public void setZhUser(ZhUser zhUser) {
		this.zhUser = zhUser;
	}

	public List<ZhUser> getZhUserList() {
		return zhUserList;
	}

	public void setZhUserList(List<ZhUser> zhUserList) {
		this.zhUserList = zhUserList;
	}

	public Integer[] getZhUserId() {
		return zhUserId;
	}

	public void setZhUserId(Integer[] zhUserId) {
		this.zhUserId = zhUserId;
	}

	public String getIssong() {
		return issong;
	}

	public void setIssong(String issong) {
		this.issong = issong;
	}

	public String[] getZhUserIds() {
		return zhUserIds;
	}

	public void setZhUserIds(String[] zhUserIds) {
		this.zhUserIds = zhUserIds;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public Integer[] getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer[] offerId) {
		this.offerId = offerId;
	}

	public String getOfferIds() {
		return offerIds;
	}

	public void setOfferIds(String offerIds) {
		this.offerIds = offerIds;
	}

	public FenMoTzRecord getFmtzr() {
		return fmtzr;
	}

	public void setFmtzr(FenMoTzRecord fmtzr) {
		this.fmtzr = fmtzr;
	}

	public List<FenMoTzRecord> getFmtzrList() {
		return fmtzrList;
	}

	public void setFmtzrList(List<FenMoTzRecord> fmtzrList) {
		this.fmtzrList = fmtzrList;
	}
	
	

}
