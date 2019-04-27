package com.task.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.MealService;
import com.task.entity.Mealticket;

public class MealAction extends ActionSupport {
	private MealService mealService;
	private Mealticket mealticket;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String errorMessage;
	private String pageStatus;
	private int keshu;

	private List list;
	private int shu;
	private String atime;
	private String btime;

	public Mealticket getMealticket() {
		return mealticket;
	}

	public void setMealticket(Mealticket mealticket) {
		this.mealticket = mealticket;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	// 增加
	public String save() {
		mealService.save(mealticket);
		return "saveSuccess";
	}

	// 全部查询
	public String allFind() {
		list = mealService.getAllList(Integer.parseInt(cpage), pageSize);
		this.setUrl("mealAction!allFind.action");
		int count = mealService.getcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = mealService.getshu();
		return "list";
	}

	// 审核通过时间段总览
	public String fund() {
		list = mealService.getList(atime, btime);
		keshu = 0;
		for (int i = 0; i < list.size(); i++) {
			mealticket = (Mealticket) list.get(i);
			keshu = keshu + mealticket.getNumber();
		}
		shu = mealService.getshu2(atime, btime);
		list = mealService.getKanList4(Integer.parseInt(cpage), pageSize,
				atime, btime);
		this.setUrl("mealAction!fund.action");
		int count = mealService.getcount4(atime, btime);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "reKan3";
	}

	// 审核通过总览
	public String reKan3() {
		shu = mealService.getshu();
		list = mealService.getList();
		keshu = 0;
		for (int i = 0; i < list.size(); i++) {
			mealticket = (Mealticket) list.get(i);
			keshu = keshu + mealticket.getNumber();
		}

		list = mealService.getKanList3(Integer.parseInt(cpage), pageSize);
		this.setUrl("mealAction!reKan3.action");
		int count = mealService.getcount3();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "reKan3";
	}

	// 审核总览
	public String reKan() {
		list = mealService.getKanList(Integer.parseInt(cpage), pageSize);
		this.setUrl("mealAction!reKan.action");
		int count = mealService.getcount2();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "reKan";
	}

	// 个人全部查询
	public String personFind() {
		list = mealService.getPersonList(Integer.parseInt(cpage), pageSize);
		this.setUrl("mealAction!personFind.action");
		int count = mealService.getcount1();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		shu = mealService.getshu1();
		return "personlist";
	}

	// 详细
	public String deefind() {
		try {
			list = mealService.getList(mealticket.getId());
		} catch (NullPointerException e) {
			return "saveSuccess";
		}
		this.mealticket = (Mealticket) list.get(0);
		return "look";
	}

	// 预览
	public String deefind1() {
		try {
			list = mealService.getList(mealticket.getId());
		} catch (NullPointerException e) {
			return "saveSuccess";
		}
		this.mealticket = (Mealticket) list.get(0);
		return "look1";
	}

	// 单个查询
	public String find() {
		try {
			list = mealService.getList(mealticket.getId());
		} catch (NullPointerException e) {
			return "saveSuccess";
		}
		this.mealticket = (Mealticket) list.get(0);
		return "f";
	}

	public String find1() {
		try {
			list = mealService.getList(mealticket.getId());
		} catch (NullPointerException e) {
			return "saveSuccess";
		}
		this.mealticket = (Mealticket) list.get(0);
		return "meal1";
	}

	// 修改前查询
	public String afind() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		return "h";
	}

	// 修改
	public String update() {
		mealService.update(mealticket);
		return "saveSuccess";
	}

	// 删除
	public String delete() {
		list = mealService.getList(mealticket.getId());
		if (list == null) {
			return "saveSuccess";
		} else {
			this.mealticket = (Mealticket) list.get(0);
			mealService.delete(mealticket);
			return "saveSuccess";
		}
	}

	// 增加打印次数
	public String addCopy() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		mealService.addCopy(mealticket);
		return "rekan3";
	}

	// 打印
	public String copy() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		mealService.copy(mealticket);
		return "saveSuccess";
	}

	public String copy1() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		mealService.copy(mealticket);
		return "rekan3";
	}

	// 审核
	public String updatee() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		mealService.updatee(mealticket);
		return "rekan";
	}

	// 打回
	public String redown() {
		list = mealService.getList(mealticket.getId());
		this.mealticket = (Mealticket) list.get(0);
		mealService.redown(mealticket);
		return "rekan";
	}

	// 跳转界面
	// 导出数据
	public void export() {
		// 文件名字
		String fileName = "客饭票";
		String zbmc = fileName;
		// 获得 OutputStream 最后把生成的excel通过这个写出
		HttpServletResponse resp = (HttpServletResponse) ActionContext
				.getContext().get(StrutsStatics.HTTP_RESPONSE);
		OutputStream os;
		try {
			list = mealService.getList();
			keshu = 0;
			for (int i = 0; i < list.size(); i++) {
				mealticket = (Mealticket) list.get(i);
				keshu = keshu + mealticket.getNumber();
			}

			os = resp.getOutputStream();

			resp.setContentType("application/ms-excel;charset=gbk");
			resp.reset();
			// 设置头
			resp.setHeader("Content-Disposition", "attachment;filename="
					+ java.net.URLEncoder.encode(fileName, "UTF-8") + ".xls");

			// 标题字体
			jxl.write.WritableFont wfc = new jxl.write.WritableFont(
					WritableFont.COURIER, 18, WritableFont.BOLD, true);
			jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
					wfc);
			wcfFC.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFC.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 字段字体
			jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(
					WritableFont.COURIER, 10, WritableFont.BOLD, true);
			jxl.write.WritableCellFormat wcfFC1 = new jxl.write.WritableCellFormat(
					wfc1);
			wcfFC1.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFC1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 结果字体
			jxl.write.WritableCellFormat wcfFC2 = new jxl.write.WritableCellFormat();
			wcfFC2.setAlignment(jxl.format.Alignment.CENTRE);
			wcfFC2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 通过OutputStream对象os创建Workbook
			WritableWorkbook wbook = Workbook.createWorkbook(os);
			// 创建sheet
			WritableSheet wsheet = wbook.createSheet("sheet1", 0);
			// 列名
			String colName = "序号-来客姓名-来客单位-来客人数-申请人-职务-审批人-有效日期-";
			String[] splitColName = colName.split("-");
			int colNum = splitColName.length;
			list = mealService.getList(atime, btime);
			int resSize = list.size();
			// 加入标题
			wsheet.mergeCells(0, 0, colNum - 1, 0); // 第一行合并
			wsheet.addCell(new Label(0, 0, zbmc, wcfFC));// 写标题 看出坐标是基于 0，0
			// 第一行写入列名
			for (int i = 0; i < colNum; i++) {
				// 加入字段名
				wsheet.addCell(new jxl.write.Label(i, 1, splitColName[i],
						wcfFC1));
				// System.out.println(splistCharacterName);
			}
			// 加入打印时间
			Date aaa = new Date();
			SimpleDateFormat aSimpleDateFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String today = aSimpleDateFormat.format(aaa);
			wsheet.addCell(new jxl.write.Number(0, resSize + 2, resSize + 1));
			wsheet.addCell(new jxl.write.Number(0, resSize + 3, resSize + 2));
			wsheet.addCell(new Label(1, resSize + 2, "打印日期:"));
			wsheet.addCell(new Label(2, resSize + 2, today));
			wsheet.addCell(new Label(1, resSize + 3, "来客总数:"));
			wsheet.addCell(new jxl.write.Number(2, resSize + 3, keshu));
			// 获取结果

			// 下面开始输出结果
			for (int j = 0; j < resSize; j++) {
				// 写数据;
				mealticket = (Mealticket) list.get(j);
				wsheet.addCell(new jxl.write.Number(0, j + 2, j + 1));
				wsheet.addCell(new Label(1, j + 2, mealticket.getName()));
				wsheet.addCell(new Label(2, j + 2, mealticket.getCompany()));
				wsheet.addCell(new jxl.write.Number(3, j + 2, mealticket
						.getNumber()));
				wsheet.addCell(new Label(4, j + 2, mealticket.getManage()));
				wsheet.addCell(new Label(5, j + 2, mealticket.getJob()));
				wsheet.addCell(new Label(6, j + 2, mealticket.getCheckname()));
				wsheet.addCell(new Label(7, j + 2, mealticket.getAddDate()));

			}
			// 写入流中
			wbook.write();
			wbook.close();
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	public String fuck() {
		return "fuck";
	}

	public MealService getMealService() {
		return mealService;
	}

	public void setMealService(MealService mealService) {
		this.mealService = mealService;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTotal() {
		return total;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
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

	public int getShu() {
		return shu;
	}

	public void setShu(int shu) {
		this.shu = shu;
	}

	public String getAtime() {
		return atime;
	}

	public void setAtime(String atime) {
		this.atime = atime;
	}

	public String getBtime() {
		return btime;
	}

	public void setBtime(String btime) {
		this.btime = btime;
	}

	public int getKeshu() {
		return keshu;
	}

	public void setKeshu(int keshu) {
		this.keshu = keshu;
	}

}
