package com.task.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Dao.TotalDao;
import com.task.Server.TijingpriceServer;
import com.task.entity.Tijiang;
import com.task.entity.Tijiangprice;
import com.task.entity.Users;
import com.task.util.Util;

public class TijingpriceAction extends ActionSupport {

	private TijingpriceServer tijingpriceServer;
	private Tijiangprice tijiangprice;// 价格表
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	private String jianhao;
	private String str;
	private File[] imgpath;// 实际的上传文件
	private String[] imgpathFileName; // 上传文件名
	private String[] imgpathContentType; // 上传文件内容类型
	private ServletContext context = ServletActionContext.getServletContext();
	// 保存文件的目录路径(通过依赖注入)
	private String savePath;
	private String tijiangjianhao;// 件号
	private String tijiangxingbie;// 型别
	private String tijingguige; // 规格
	private String tijingpici; // 批次
	private Float tijingdanjia; // 单价
	private Float tijianghanshuijia; // 含税价
	private Float tijiangdie; // 定额（工时）
	private String tijingbeizhu; // 备注
	private Float kaishicount;// 开始数量
	private Float jiusucount;// 结束数量
	private String priceTjStyle;// 提奖类型

	private String errorMessage;

	@Override
	public String execute() throws Exception {
		List list = tijingpriceServer.findname(tijiangjianhao);
		if (list != null && list.size() > 0 && list.get(0) != null) {
			Float enddate = (Float) list.get(0);
			Float count = enddate + 1;
			if (kaishicount.equals(count)) {
				String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date());
				String targetFileName = "";
				if (imgpath != null) {
					String targetDirectory = ServletActionContext
							.getServletContext()
							.getRealPath(this.getSavePath());
					for (int i = 0; i < imgpath.length; i++) {
						String str = getfileName(imgpathFileName[i]);
						if (i < imgpathFileName.length - 1) {
							targetFileName += str + ",";
						} else {
							targetFileName += str;
						}
						File target = new File(targetDirectory, str);
						try {
							FileCopyUtils.copy(imgpath[i], target);
						} catch (Exception e) {
							targetFileName = "";
							break;
						}
						String excelRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/tijiang";
						File target2 = new File(excelRealPath, str);
						FileCopyUtils.copy(imgpath[i], target2);
					}
				}
				Tijiangprice tijiangprice = new Tijiangprice();
				tijiangprice.setPricemarkId(tijiangjianhao);// 件号
				tijiangprice.setPricestyle(tijiangxingbie);// 型别
				tijiangprice.setPriceformt(tijingguige);// 规格
				tijiangprice.setPricelotId(tijingpici);// 批次
				tijiangprice.setPricefactPrice(tijingdanjia);// 单价
				tijiangprice.setPricetoxprice(tijianghanshuijia);// 含税价
				tijiangprice.setPricedinge(tijiangdie);// 定额
				tijiangprice.setPricemore(tijingbeizhu);// 备注
				tijiangprice.setPricefilepath(targetFileName);// 文件
				tijiangprice.setPricedefault("正常使用");
				tijiangprice.setPricesenacount(kaishicount);// 开始数量
				tijiangprice.setPriceendcount(jiusucount);// 结束数量
				tijiangprice.setPricedate(writeDate);// 时间
				tijiangprice.setPriceTjStyle(priceTjStyle);
				tijingpriceServer.addtijingprice(tijiangprice);
				jianhao = tijiangprice.getPricemarkId();// 件号
				str = "123";
				return "execute1";
			} else {
				errorMessage = "你的结束数量应该是" + count;
				return "error";
			}
		} else {
			String writeDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(new Date());
			String targetFileName = "";
			if (imgpath != null) {
				String targetDirectory = ServletActionContext
						.getServletContext().getRealPath(this.getSavePath());
				for (int i = 0; i < imgpath.length; i++) {
					String str = getfileName(imgpathFileName[i]);
					if (i < imgpathFileName.length - 1) {
						targetFileName += str + ",";
					} else {
						targetFileName += str;
					}
					File target = new File(targetDirectory, str);
					try {
						FileCopyUtils.copy(imgpath[i], target);
					} catch (Exception e) {
						targetFileName = "";
						break;
					}
					String excelRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/tijiang";
					File target2 = new File(excelRealPath, str);
					FileCopyUtils.copy(imgpath[i], target2);
				}
			}
			Tijiangprice tijiangprice = new Tijiangprice();
			tijiangprice.setPricemarkId(tijiangjianhao);// 件号
			tijiangprice.setPricestyle(tijiangxingbie);// 型别
			tijiangprice.setPriceformt(tijingguige);// 规格
			tijiangprice.setPricelotId(tijingpici);// 批次
			tijiangprice.setPricefactPrice(tijingdanjia);// 单价
			tijiangprice.setPricetoxprice(tijianghanshuijia);// 含税价
			tijiangprice.setPricedinge(tijiangdie);// 定额
			tijiangprice.setPricemore(tijingbeizhu);// 备注
			tijiangprice.setPricefilepath(targetFileName);// 文件
			tijiangprice.setPricedefault("正常使用");
			tijiangprice.setPricesenacount(kaishicount);// 开始数量
			tijiangprice.setPriceendcount(jiusucount);// 结束数量
			tijiangprice.setPricedate(writeDate);// 时间
			tijiangprice.setPriceTjStyle(priceTjStyle);
			tijingpriceServer.addtijingprice(tijiangprice);
			jianhao = tijiangprice.getPricemarkId();// 件号
			str = "123";
			return "execute1";
		}
	}

	// 上传的文件名+当前时间+随机数
	private String getfileName(String fileFileName) {
		int position = fileFileName.lastIndexOf(".");
		String extension = fileFileName.substring(position);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String curDate = sdf.format(new Date());
		extension = curDate + (int) (Math.random() * 10000 + 1) + extension;
		return extension;
	}

	private List tijingpricelist;

	// 查询提奖价格表所有信息
	public String findtijingprice() {
		HttpServletRequest request = ServletActionContext.getRequest();
		tijingpricelist = tijingpriceServer.findShouList(Integer
				.parseInt(cpage), pageSize);
		this.setUrl("TijingpriceAction!findtijingprice.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = tijingpriceServer.getCount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findtijingprice";
	}

	private int id;

	// 删除提奖价格表数据
	public String deletetijingprice() {
		tijiangprice = tijingpriceServer.findtijingpriceById(id);
		tijingpriceServer.deleteTijingprice(tijiangprice);
		String targetDirectory = ServletActionContext.getServletContext()
				.getRealPath(this.getSavePath());
		File fileRoot = new File(targetDirectory);// 创建文件流
		File files[] = fileRoot.listFiles();// 获得图片存放路径的全部文件并且存放到files数组中
		for (int i = 0; i < files.length; i++) {// 遍历files
			File file = files[i];// 拿出files里面的只赋值给file为下面做判断用
			String fileName = file.getName();// 拿出file的用户名赋值给fileName
			if (fileName.contains(tijiangprice.getPricefilepath())) {// 拿出fileName的值做比较如果图片名称名相同删除
				file.delete();// 删除
			}
		}
		return "deletetijingprice";
	}

	// 修改前
	public String updatefind() {
		tijiangprice = tijingpriceServer.findtijingpriceById(id);
		return "updatefind";
	}

	// 修改
	public String update() {
		tijingpriceServer.updateTijingprice(tijiangprice);
		return "update";
	}

	// 条件查询
	public String conditionFind() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (tijiangprice != null) {
			ActionContext.getContext().getSession().put("tijiangprice",
					tijiangprice);
		} else {
			tijiangprice = (Tijiangprice) ActionContext.getContext()
					.getSession().get("tijiangprice");
		}
		tijingpricelist = tijingpriceServer.findByCondition(tijiangprice,
				Integer.parseInt(cpage), pageSize);
		this.setUrl("TijingpriceAction!conditionFind.action");
		this.cpage = request.getParameter("cpage");
		if ("".equals(cpage) || null == cpage) {
			cpage = 1 + "";
		}
		int count = tijingpriceServer.findByConditioncount(tijiangprice);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "findtijingprice";
	}

	// 条件查询 入库表指定的时间的数量 和单件计价表的单价 (前)
	private List<Object[]> zongsunlist;

	public String conditionAllBefore() {
		String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new Date());
		int r = Integer.parseInt(data.substring(8, 10));// 日
		int s = Integer.parseInt(data.substring(11, 13));// 时
		// 测试暂时关闭验证时间
		// if (r < 26 && r > 5) {
		// errorMessage = "对不起，你已超过了提奖的时间或者提奖时间没到";
		// return ERROR;
		// } else if (r == 26) {
		// if (s < 9) {
		// errorMessage = "对不起，你已超过了提奖的时间或者提奖时间没到";
		// return ERROR;
		// }
		// } else if (r == 5) {
		// if (s > 23) {
		// errorMessage = "对不起，你已超过了提奖的时间或者提奖时间没到";
		// return ERROR;
		// }
		// }
		zongsunlist = tijingpriceServer.findshougoodStoreBefore(setDate,
				endDate);
		if (zongsunlist.size() <= 0) {
			return "conditionAllBefore2";
		}
		return "conditionAllBefore";
	}

	// 条件查询 入库表指定的时间的数量 和单件计价表的单价 (中)
	private String setDate; // 开始时间
	private String endDate; // 结束时间
	private List<Object[]> sumList;

	public String conditionAll() {
		List lisimin = new ArrayList();
		Object[] object = tijingpriceServer
				.findShougoodsStore(setDate, endDate);
		if (object != null && object.length > 0) {
			sumList = (List<Object[]>) object[0];// 入库表
			lisimin = (List<Tijiang[]>) object[1];
		}

		ActionContext.getContext().getSession().put("sumList", sumList);
		ActionContext.getContext().getSession().put("lisimin", lisimin);
		message = message;
		return "conditionAllSuccess";
	}

	/***
	 * 计算所有的入库产品的总价
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String sumAllProduct() {
		String startDate = Util.getLastMonth("yyyy-MM") + '-' + "26 09:00:00";
		String endDate = Util.getDateTime("yyyy-MM") + '-' + "26 09:00:00";

		// // 查询所有没有单价的产品并计算产品可分配金额
		// zongsunlist = tijingpriceServer.findshougoodStoreBefore(startDate,
		// endDate);
		// if (zongsunlist != null && zongsunlist.size() > 0) {
		//			
		// }

		// 计算产品可分配金额
		List lisimin = new ArrayList();
		Object[] object = tijingpriceServer.findShougoodsStore(startDate,
				endDate);
		if (object != null && object.length > 0) {
			sumList = (List<Object[]>) object[0];// 入库表
			lisimin = (List<Tijiang[]>) object[1];
		}

		ActionContext.getContext().getSession().put("sumList", sumList);
		ActionContext.getContext().getSession().put("lisimin", lisimin);
		message = message;
		return "findAllPrice";
	}

	private String message;

	public String generateEXCEL() {
		setDate = setDate;
		endDate = endDate;
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
			WritableSheet sheet = book.createSheet("提奖计价表前 ", 0);
			Label label = new Label(0, 0, "件号");// 0列0后面是行
			Label label2 = new Label(1, 0, "型别");
			Label label3 = new Label(2, 0, "数量");
			Label label4 = new Label(3, 0, "价格");

			sheet.addCell(label);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			List<Object[]> excellist = (List) ActionContext.getContext()
					.getSession().get("sumList");
			for (int i = 0; i < excellist.size(); i++) {
				String goodsStoreMarkId = (String) excellist.get(i)[0];// 产品件号
				Float count = (Float) excellist.get(i)[1];// 实际入库数量
				Float money = (Float) excellist.get(i)[2];
				String xingbie = (String) excellist.get(i)[3];
				Label label5 = new Label(0, i + 1, goodsStoreMarkId);
				Label label6 = new Label(1, i + 1, xingbie);
				Label label7 = new Label(2, i + 1, count.toString());
				Label label8 = new Label(3, i + 1, money.toString());

				sheet.addCell(label5);
				sheet.addCell(label6);
				sheet.addCell(label7);
				sheet.addCell(label8);

			}
			// 写入数据并关闭文件
			book.write();// 写入数据
			book.close();// 关闭文件
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "generateEXCEL";
	}

	// 构造方法

	public Tijiangprice getTijiangprice() {
		return tijiangprice;
	}

	public List<Object[]> getSumList() {
		return sumList;
	}

	public void setSumList(List<Object[]> sumList) {
		this.sumList = sumList;
	}

	public void setTijiangprice(Tijiangprice tijiangprice) {
		this.tijiangprice = tijiangprice;
	}

	public TijingpriceServer getTijingpriceServer() {
		return tijingpriceServer;
	}

	public void setTijingpriceServer(TijingpriceServer tijingpriceServer) {
		this.tijingpriceServer = tijingpriceServer;
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

	public List getTijingpricelist() {
		return tijingpricelist;
	}

	public void setTijingpricelist(List tijingpricelist) {
		this.tijingpricelist = tijingpricelist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJianhao() {
		return jianhao;
	}

	public void setJianhao(String jianhao) {
		this.jianhao = jianhao;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public File[] getImgpath() {
		return imgpath;
	}

	public void setImgpath(File[] imgpath) {
		this.imgpath = imgpath;
	}

	public String[] getImgpathFileName() {
		return imgpathFileName;
	}

	public void setImgpathFileName(String[] imgpathFileName) {
		this.imgpathFileName = imgpathFileName;
	}

	public String[] getImgpathContentType() {
		return imgpathContentType;
	}

	public void setImgpathContentType(String[] imgpathContentType) {
		this.imgpathContentType = imgpathContentType;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getTijiangjianhao() {
		return tijiangjianhao;
	}

	public void setTijiangjianhao(String tijiangjianhao) {
		this.tijiangjianhao = tijiangjianhao;
	}

	public String getTijiangxingbie() {
		return tijiangxingbie;
	}

	public void setTijiangxingbie(String tijiangxingbie) {
		this.tijiangxingbie = tijiangxingbie;
	}

	public String getTijingguige() {
		return tijingguige;
	}

	public void setTijingguige(String tijingguige) {
		this.tijingguige = tijingguige;
	}

	public String getTijingpici() {
		return tijingpici;
	}

	public void setTijingpici(String tijingpici) {
		this.tijingpici = tijingpici;
	}

	public Float getTijingdanjia() {
		return tijingdanjia;
	}

	public void setTijingdanjia(Float tijingdanjia) {
		this.tijingdanjia = tijingdanjia;
	}

	public Float getTijianghanshuijia() {
		return tijianghanshuijia;
	}

	public void setTijianghanshuijia(Float tijianghanshuijia) {
		this.tijianghanshuijia = tijianghanshuijia;
	}

	public Float getTijiangdie() {
		return tijiangdie;
	}

	public void setTijiangdie(Float tijiangdie) {
		this.tijiangdie = tijiangdie;
	}

	public String getTijingbeizhu() {
		return tijingbeizhu;
	}

	public void setTijingbeizhu(String tijingbeizhu) {
		this.tijingbeizhu = tijingbeizhu;
	}

	public Float getKaishicount() {
		return kaishicount;
	}

	public void setKaishicount(Float kaishicount) {
		this.kaishicount = kaishicount;
	}

	public Float getJiusucount() {
		return jiusucount;
	}

	public void setJiusucount(Float jiusucount) {
		this.jiusucount = jiusucount;
	}

	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public List<Object[]> getZongsunlist() {
		return zongsunlist;
	}

	public void setZongsunlist(List<Object[]> zongsunlist) {
		this.zongsunlist = zongsunlist;
	}

	public String getPriceTjStyle() {
		return priceTjStyle;
	}

	public void setPriceTjStyle(String priceTjStyle) {
		this.priceTjStyle = priceTjStyle;
	}

}
