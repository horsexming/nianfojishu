package com.task.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TijingServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.entity.GoodsStore;
import com.task.entity.Mentionrecord;
import com.task.entity.MinBalance;
import com.task.entity.Tijiang;
import com.task.entity.Users;

public class TijingAction extends ActionSupport {

	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private TijingServer tijingServer;
	private Tijiang tijing;// 提奖计价表
	private String pageStatus;
	private Integer id;

	public TijingServer getTijingServer() {
		return tijingServer;
	}

	public void setTijingServer(TijingServer tijingServer) {
		this.tijingServer = tijingServer;
	}

	private List list;
	// 增加提奖计价表和添加提奖记录表
	private String setDate;// 开始日期
	private String endDate;// 结束时间
	private String errorMessage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Override
	public String execute() throws Exception {
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		String setdatetime = setDate.substring(0, 7);// 结束时间
		String enddatetime = endDate.substring(0, 7);// 结束时间
		Float ltmoney = 0F;
		Float a3 = 0F;// 最小数
		if (setdatetime != null && enddatetime != null) {
			int startDate = Integer.parseInt(setdatetime.substring(setdatetime
					.indexOf("-") + 1));
			int endDate2 = Integer.parseInt(enddatetime.substring(setdatetime
					.indexOf("-") + 1));
			/*
			 * if (setdatetime.substring(0, setdatetime.indexOf("-")).equals(
			 * enddatetime.substring(0, setdatetime.indexOf("-")))) { if
			 * (endDate2 - startDate != 1) { errorMessage =
			 * "您选择的开始时间与结束时间相差大于一个月,请检查后重试!"; return "error"; } } else {
			 * errorMessage = "请选择同一年份!"; return "error"; }
			 */
			// 判断结束时间是否在提奖记录表当中有
			List mentionlist = tijingServer.findyuefen(enddatetime);
			if (mentionlist.size() > 0) {
				errorMessage = "您选择的时间已经在提奖记录表中存在";
				return ERROR;
			}
		}

		List<Object[]> excellist = (List) ActionContext.getContext()
				.getSession().get("sumList");
		Tijiang tijing = new Tijiang();
		Float zongmoney = 0F;
		for (int i = 0; i < excellist.size(); i++) {
			String goodsStoreMarkId = (String) excellist.get(i)[0];// 产品件号
			Float count = (Float) excellist.get(i)[1];// 实际入库数量
			Float money = (Float) excellist.get(i)[2];// 总价格
			String xingbie = (String) excellist.get(i)[3];
			tijing.setTjmarkId(goodsStoreMarkId);// 件号
			tijing.setTjcount(count);// 数量
			tijing.setTjmoney(money);// 总金额
			tijing.setTjstyle(xingbie);// 型别
			tijing.setTjtimer(enddatetime);
			zongmoney += tijing.getTjmoney();// 拿出单条件号的总价格 算出总价格
			tijingServer.savetijing(tijing); // 添加到提奖计价表当中
		}
		// 处理余额

		List<Tijiang[]> listMinBalance = (List) ActionContext.getContext()
				.getSession().get("lisimin");
		if (null != listMinBalance && listMinBalance.size() > 0) {
			for (int j = 0; j < listMinBalance.size(); j++) {
				Tijiang[] tj = listMinBalance.get(j);
				if (tj.length > 0) {
					Tijiang tijiangM = tj[tj.length - 1];
					for (int jj = 0; jj < tj.length; jj++) {
						Tijiang tijiangComp = tj[jj];// 数量最少记录对象
						MinBalance minBalance = new MinBalance();
						minBalance.setMinPartnumber(tijiangComp.getTjmarkId());
						minBalance.setMinType(tijiangComp.getTjstyle());
						minBalance.setMinBalancenumber(tijiangComp.getTjcount()
								- tijiangM.getTjcount());
						minBalance.setMinMonth(enddatetime);
						minBalance.setMindatatime(date);
						tijingServer.saveMinBalance(minBalance);
					}
				}
			}
		}

		// 添加提奖记录表
		String datetime = tijing.getTjtimer();// 终止时间
		Mentionrecord mentionrecord = new Mentionrecord();
		mentionrecord.setMentionMonth(datetime);// 月份
		mentionrecord.setMentionshallMoney(zongmoney + ltmoney);// 应提奖额
		mentionrecord.setMentionstatus("审核中");
		mentionrecord.setMentiondatetime(date);// 时间
		tijingServer.saveMentionrecord(mentionrecord); // 添加到提奖记录表当中
		pageStatus = "hr";
		// AlertMessagesServerImpl
		// .addAlertMessages("查看提奖记录（总经理审核）", "生产提奖审批", "1");
		return "savetijing";
	}

	// 根据月份查询提奖计价表信息
	private List listtijing = new ArrayList();
	private String yuefen;

	public String showtijing() {
		HttpServletRequest request = ServletActionContext.getRequest();
		listtijing = tijingServer.showtijiang(yuefen, Integer.parseInt(cpage),
				pageSize);
		this.setUrl("TijingAction!showtijing.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}

		int count = tijingServer.findtijiangcount(yuefen);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "showtijing";
	}

	private String message;

	// 根据月份生成EXCEL
	public String dateEXCEL() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Users user = (Users) request.getSession().getAttribute(TotalDao.users);
		// 生成xls表，然后下载
		try {
			// 获得存放EXCEL的路径并且打开文件
			String path = TijingpriceAction.class.getResource("/").toString();
			path = path.substring(6, path.length() - 17);
			String excelName = user.getCode() + ""
					+ (int) (Math.random() * 10000 + 1) + ".xls";
			path = path.replace("%20", " ") + "/upload/sheet/" + excelName;
			message = excelName;// 获得path的路径截取sheet
			String root = path.substring(0, path.indexOf("/upload/sheet/"))
					+ "/upload/sheet/"; // 获得excel存放的路径只获得0到sheet
			File fileRoot = new File(root);// 创建文件流
			File files[] = fileRoot.listFiles();// 获得excel存放路径的全部文件并且存放到files数组中
			for (int i = 0; i < files.length; i++) {// 遍历files
				File file = files[i];// 拿出files里面的只赋值给file为下面做判断用
				String fileName = file.getName();// 拿出file的用户名赋值给fileName
				if (fileName.contains(user.getCode())) {// 拿出fileName的值做比较如果等于登入进入的用户名相同删除
					file.delete();// 删除
				}
			}
			File file = new File(path);// 创建文件流获得EXCEL的路径
			// 生成excel表
			WritableWorkbook book = Workbook.createWorkbook(file);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("提奖计价表详细信息 ", 0);
			Label label = new Label(0, 0, "月份");
			Label label2 = new Label(1, 0, "件号");// 0列0后面是行
			Label label3 = new Label(2, 0, "型别");
			Label label4 = new Label(3, 0, "数量");
			Label label5 = new Label(4, 0, "总金额");

			sheet.addCell(label);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);

			List tijiList = tijingServer.finddateExcel(yuefen);
			for (int i = 0; i < tijiList.size(); i++) {
				Tijiang tijiang = (Tijiang) tijiList.get(i);
				Label label6 = new Label(0, i + 1, tijiang.getTjtimer());
				Label label7 = new Label(1, i + 1, tijiang.getTjmarkId());
				Label label8 = new Label(2, i + 1, tijiang.getTjstyle());
				Label label9 = new Label(3, i + 1, tijiang.getTjcount()
						.toString());
				Label label0 = new Label(4, i + 1, tijiang.getTjmoney()
						.toString());

				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);
				sheet.addCell(label9);
				sheet.addCell(label0);
			}

			// 写入数据并关闭文件
			book.write();// 写入数据
			book.close();// 关闭文件
			tijiList.clear();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "dateEXCEL";
	}

	private List<GoodsStore> goodlist = new ArrayList<GoodsStore>();
	private String date;
	private String date2;

	// 根据月份查看所对应的入库记录
	public String findGoodStore() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (yuefen != null) {
			date = yuefen + '-' + "26";
			int mm = Integer.parseInt(yuefen.substring(5, 7));
			int yy = Integer.parseInt(yuefen.substring(0, 4));
			if (mm == 1) {
				mm = 12;
				yy--;
			} else {
				mm--;
			}
			date2 = yy + "-" + mm + '-' + "26";
			if (mm < 10) {
				date2 = yuefen.substring(0, 5) + "0" + (mm) + '-' + "26";
			}
			goodlist = tijingServer.findDateGoodStore(date2, date, Integer
					.parseInt(cpage), pageSize);
			this.setUrl("TijingAction!findGoodStore.action");
			this.cpage = request.getParameter("cpage");
			if ("".equals(cpage) || null == cpage) {
				cpage = 1 + "";
			}

			int count = tijingServer.getDateGoodStoreCount(date2, date);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			return "findGoodStore";
		}
		return null;
	}

	private String jianhao;

	// 根据件号查询出所对应的入库表信息
	public String findMakID() {
		HttpServletRequest request = ServletActionContext.getRequest();
		goodlist = tijingServer.findjianhao(jianhao, date2, date, Integer
				.parseInt(cpage), pageSize);
		this.setUrl("TijingAction!findMakID.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}

		int count = tijingServer.getjianhaoCout(jianhao, date2, date);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findGoodStore";
	}

	// 添加试制奖金分配
	public String addShizhiJIangjin() {
		if (tijingServer.addShizhiJiangjin(tijing)) {
			this.setTijing(null);
			return "addShizhiok";
		} else {
			return ERROR;
		}
	}

	// 条件查询试制奖
	public String queryShizhi() {
		if (null == tijing) {
			tijing = new Tijiang();
		}
		if ("exam".equals(pageStatus)) {
			tijing = new Tijiang();
			tijing.setTjmore("审核");
		}
		this.pageSize = 15;
		this.setUrl("TijingAction!queryShizhi.action");
		Object[] obj = tijingServer.queryShizhijiangjin(tijing, Integer
				.parseInt(cpage), pageSize);

		int count = (Integer) obj[0];

		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		list = (List) obj[1];
		tijing = new Tijiang();
		return "queryShizhi";
	}

	// 获取试制
	public String getOneShizhi() {
		tijing = tijingServer.getShizhi(id);
		return "getOneShizhi";
	}

	// 审核试制奖金
	public String updateShizhi() {
		if ("update".equals(this.pageStatus)) {
			if (tijingServer.updateShizhi(tijing)) {
				tijing = new Tijiang();
				return "updateShizhijiangjinOK";
			}
			tijing = new Tijiang();
			return ERROR;
		} else if ("exam".equals(this.pageStatus)) {// 总经理审批
			tijing = tijingServer.getShizhi(id);
			if ("OK".equals(this.message)) {
				tijing.setTjmore("同意");

				tijingServer.updateShizhi(tijing);

			} else {
				tijing.setTjmore("不同意");
				tijingServer.updateShizhi(tijing);
			}
			return "examIder";
		} else if ("delete".equals(this.pageStatus)) {
			tijing = tijingServer.getShizhi(id);
			if (tijingServer.deleteShizhijiangjin(tijing)) {
				tijing = new Tijiang();
				return "deleteShizhiOK";
			}

			return ERROR;
		} else {
			return "";
		}
		// return ERROR;
	}

	// 构造方法
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

	public List getListtijing() {
		return listtijing;
	}

	public void setListtijing(List listtijing) {
		this.listtijing = listtijing;
	}

	public Tijiang getTijing() {
		return tijing;
	}

	public void setTijing(Tijiang tijing) {
		this.tijing = tijing;
	}

	public String getYuefen() {
		return yuefen;
	}

	public void setYuefen(String yuefen) {
		this.yuefen = yuefen;
	}

	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	public String getPageStatus() {
		return pageStatus;
	}

	public void setPageStatus(String pageStatus) {
		this.pageStatus = pageStatus;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GoodsStore> getGoodlist() {
		return goodlist;
	}

	public void setGoodlist(List<GoodsStore> goodlist) {
		this.goodlist = goodlist;
	}

	public String getJianhao() {
		return jianhao;
	}

	public void setJianhao(String jianhao) {
		this.jianhao = jianhao;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

}
