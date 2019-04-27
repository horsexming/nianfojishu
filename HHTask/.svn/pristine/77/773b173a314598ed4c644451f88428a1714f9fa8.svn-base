package com.task.action.bp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.bp.DetailService;
import com.task.Server.bp.TempletService;
import com.task.entity.bp.Detail;
import com.task.entity.bp.Templet;

@SuppressWarnings("all")
public class DetailAction extends ActionSupport {
	private DetailService detailService;
	private TempletService templetService;

	private List<Detail> details;
	private List<Templet> templets;
	private Map<String, Templet> datas;
	private Detail detail;
	private List list;
	private String num;
	private String month;
	private List<String> models; 
	private Map<String, Templet> leafs ;
	private Map<String, Templet> disLeafs ;

	private String errorMessage;
	private String successMessage;// 成功信息
	
	
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	//列表
	public String listInput(){
		Object[] object = detailService.getList(Integer.parseInt(cpage), pageSize);
		if (object != null && object.length > 0) {
			details = (List<Detail>) object[0];
			if (details != null && details.size() > 0) {
				int sum = (Integer) object[1];
				int pageCount = (sum + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("Detail_listInput.action");
				errorMessage = null;
			} else {
				errorMessage = "抱歉!您查询的计划不存在!";
			}
		} else {
			errorMessage = "抱歉!没有您查询的计划信息!";
		}
		return INPUT;
	}
	
	//添加
	public String add(){
		if(detailService.add(templets.get(0), detail)){
			successMessage = "添加"+ detail.getTemplet().getName()+ "成功";
		} else {
			errorMessage = "失败，请检查有否有审核中的产品,或者已经添加过了";
		}
		return "chainAdd";
	}
	
	//添加之前的数据准备
	public String addInput(){
		templets = detailService.getAllRoots();
		models = templetService.getModels(null); 
		return INPUT;
	}

	//删除
	public String delete(){
		detailService.delete(detail);
		return "redirect";
	}
	
	//更新之前的数据准备
	public String updateInput(){
		detail = detailService.get(detail.getId());
		templets = detailService.getAllRoots();
		return INPUT;
	}

	//更新
	public String update(){
		detailService.update(detail, templets.get(0));
		return "redirect";
	}

	//Ajax把商品清单添加到session里
	public String addDetailItem(){
		Map session = ActionContext.getContext().getSession();
		if(session.get("detailItems") == null){
			Map<Integer,Detail> detailItems = new HashMap<Integer,Detail>();
			session.put("detailItems", detailItems);
		}
		Map<Integer,Detail> detailItems =  (Map<Integer, Detail>) session.get("detailItems");
		if(detail.getId() != null){
			detailItems.put(detail.getId(), detail);
			try {
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("1");
				response.getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	//获得一个订单的所有配件
	public String getSingleDetail(){
		datas = new HashMap<String, Templet>();
		Map<String, Templet> datas31 = new HashMap<String, Templet>();
		detailService.getTempletLeafs(detail,datas,datas31);
		return SUCCESS;
	}

	//获取到session中的商品清单
	public String listDetailItem(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<Integer,Detail> detailItems = (Map<Integer, Detail>) session.get("detailItems");

		for (Integer i : detailItems.keySet()) {
			detailItems.put(i, detailService.get(i));
		}
		return SUCCESS;
	}
	
	//删除订单清单中的某个订单
	public String deleteItem(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		Map<Integer,Detail> detailItems = (Map<Integer, Detail>) session.get("detailItems");
		detailItems.remove(detail.getId());
		return "redirectDeleteItem";
	}

	//获得清单的所有配件
	public String getMultipleDetail(){
		if(details == null){
			return null;
		}
		leafs = new HashMap<String, Templet>();//接收叶子件
		disLeafs = new HashMap<String, Templet>();//接收非叶子件
		for (Detail d : details) {
			detailService.getTempletLeafs(d,leafs, disLeafs);
		}
		return SUCCESS;
	}

	//查询列表框
	public String query(){
		if(null != month){
			details = detailService.listByMonth(month);
			if(details == null ||details.size() < 1){
				errorMessage = "没有查到此订单，请重新输入！";
			}
		}
		return SUCCESS;
	}
	
	//采购审核
	public String listVerify(){
		details = detailService.listVerify();
		return SUCCESS;
	}
	
	//生成订单Excel
	public String exportExcel(){
		if(null != details){
			List<Detail> details2= detailService.getDetailsById(details);
			details = details2;
			if(details == null ||details.size() < 1){
				errorMessage = "没有查到此订单，请重新输入！";
			}
		} else {
			return null;
		}
		
		try {
			// 取得HttpServletResponse
			HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename=" + new String("Book1".getBytes("GB2312"), "8859_1") + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook wwb = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet ws = wwb.createSheet(num+"", 10); // 创建一个工作表

			// 设置单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            ws.setRowView(1, 500);
			
			jxl.write.Label label30 = new Label(0, 0, month + "生产计划 " , wcf);
			ws.addCell(label30);
			ws.mergeCells(0, 0, 7, 0);
			wcf = new WritableCellFormat();
			ws.addCell(new Label(0, 1, "序号", wcf));
            ws.addCell(new Label(1, 1, "采购产品", wcf));
            ws.addCell(new Label(2, 1, "月份", wcf));
            ws.addCell(new Label(3, 1, "数量", wcf));
            ws.addCell(new Label(4, 1, "件号", wcf));
            ws.addCell(new Label(5, 1, "规格", wcf));
            ws.addCell(new Label(6, 1, "说明", wcf));
            
			Detail[] p = new Detail[details.size()];
            for (int i = 0; i < details.size(); i++){
                p[i] = (Detail)details.get(i);
                ws.addCell(new Label(0, i + 2, (i+1)+"", wcf));
                ws.addCell(new Label(1, i + 2, p[i].getTemplet().getName(), wcf));
                ws.addCell(new Label(2, i + 2, p[i].getMonth(), wcf));
                ws.addCell(new Label(3, i + 2, p[i].getQuantity()+"", wcf));
                ws.addCell(new Label(4, i + 2, p[i].getTemplet().getPartsNumber(), wcf));
                ws.addCell(new Label(5, i + 2, p[i].getTemplet().getSpecification(), wcf));
                ws.addCell(new Label(6, i + 2, p[i].getExplanation(), wcf));
            }
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	//生成产品Excel
	public String exportProductExcel(){
		if(null != details){
			if(details == null ||details.size() < 1){
				errorMessage = "没有查到此订单，请重新输入！";
			}
			leafs = new HashMap<String, Templet>();//接收叶子件
			disLeafs = new HashMap<String, Templet>();//接收非叶子件
			for (Detail d : details) {
				detailService.getTempletLeafs(d,leafs, disLeafs);
			}
		} else {
			return null;
		}
		
		try {
			// 取得HttpServletResponse
			HttpServletResponse response = (HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename=" + new String("Book1".getBytes("GB2312"), "8859_1") + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			WritableWorkbook wwb = Workbook.createWorkbook(os); // 建立excel文件
			WritableSheet ws = wwb.createSheet(num+"", 10); // 创建一个工作表

			// 设置单元格的文字格式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
			WritableCellFormat wcf = new WritableCellFormat(wf);
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE); 
            wcf.setAlignment(Alignment.CENTRE); 
            ws.setRowView(1, 500);
			
			jxl.write.Label label30 = new Label(0, 0, "请购征询单： " , wcf);
			ws.addCell(label30);
			ws.mergeCells(0, 0, 5, 0);
			wcf = new WritableCellFormat();
			ws.addCell(new Label(0, 1, "序号", wcf));
            ws.addCell(new Label(1, 1, "名称", wcf));
            ws.addCell(new Label(2, 1, "件号", wcf));
            ws.addCell(new Label(3, 1, "规格", wcf));
            ws.addCell(new Label(4, 1, "数量", wcf));
            ws.addCell(new Label(5, 1, "单位", wcf));
            ws.addCell(new Label(6, 1, "类别", wcf));
            
            Templet[] t = new Templet[leafs.size()];
			int k = 0;
			for (Templet temp : leafs.values()) {
				t[k] = temp;
				ws.addCell(new Label(0, k + 2, (k+1)+"", wcf));
                ws.addCell(new Label(1, k + 2, t[k].getName(), wcf));
                ws.addCell(new Label(2, k + 2, t[k].getPartsNumber(), wcf));
                ws.addCell(new Label(3, k + 2, t[k].getSpecification(), wcf));
                ws.addCell(new Label(4, k + 2, t[k].getAdvPosition()+"", wcf));
                ws.addCell(new Label(5, k + 2, t[k].getUnit()+"", wcf));
                ws.addCell(new Label(6, k + 2, t[k].getCategory(), wcf));
                k++;
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//请购征询
	public String beginProduct(){
		detailService.beginProduct(details);
		return SUCCESS;
	}
	
	public String agree(){
		if(detailService.updateArgee(details)){
			successMessage = "成功审核 " + details.size() + "条"; 
		} else {
			errorMessage = "审核失败，请重新操作";
		}
		return "chainToListVerify";
	}
	
	public String disAgree(){
		if(detailService.updateDisArgee(details)){
			successMessage = "不通过 " + details.size() + "条"; 
		} else {
			errorMessage = "审核失败，请重新操作";
		}
		return "chainToListVerify";
	}
	
	public List<Detail> getDetails() {
		return details;
	}
	public void setDetails(List<Detail> details) {
		this.details = details;
	}
	public Map<String, Templet> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Templet> datas) {
		this.datas = datas;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public DetailService getDetailService() {
		return detailService;
	}
	public void setDetailService(DetailService detailService) {
		this.detailService = detailService;
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

	public List<Templet> getTemplets() {
		return templets;
	}

	public void setTemplets(List<Templet> templets) {
		this.templets = templets;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Map<String, Templet> getLeafs() {
		return leafs;
	}

	public void setLeafs(Map<String, Templet> leafs) {
		this.leafs = leafs;
	}

	public Map<String, Templet> getDisLeafs() {
		return disLeafs;
	}

	public void setDisLeafs(Map<String, Templet> disLeafs) {
		this.disLeafs = disLeafs;
	}

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public TempletService getTempletService() {
		return templetService;
	}

	public void setTempletService(TempletService templetService) {
		this.templetService = templetService;
	}

	
}
