package com.task.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.task.Dao.TotalDao;
import com.task.entity.Goods;
import com.task.entity.Users;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardAboutBanBenApply;
import com.task.util.LedSendServer;
import com.task.util.Util;

public class Test extends Test2 {

	/**
	 * @param args
	 * @throws MessagingException
	 */
	public static void testmaplist(int n, String key, Object obj) {
		// List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();

	}

	private static boolean bool = false;

	public static void test1() {
		for (int i = 0; i < 2; i--) {
			if (i == 1) {
				bool = true;
				System.out
						.println(i + " *************************************");
			}

		}
	}

	public static JavaMailSender sender;

	public static void mytest(ProcardAboutBanBenApply p,
			List<ProcardAboutBanBenApply> pList) {
		p.setMarkId("hh");
		for (ProcardAboutBanBenApply p2 : pList) {
			if (!p2.getMarkId().equals("hh")) {
				mytest2(p2);
			}
		}
	}

	public static void mytest2(ProcardAboutBanBenApply p) {
		p.setMarkId("123");
	}

	private static final double EARTH_RADIUS = 6378137;// 赤道半径(单位m)

	/**
	 * 转化为弧度(rad)
	 * */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 基于余弦定理求两经纬度距离
	 * 
	 * @param lon1
	 *            第一点的精度
	 * @param lat1
	 *            第一点的纬度
	 * @param lon2
	 *            第二点的精度
	 * @param lat3
	 *            第二点的纬度
	 * @return 返回的距离，单位m
	 * */
	public static double LantitudeLongitudeDist(double lon1, double lat1,
			double lon2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);

		double radLon1 = rad(lon1);
		double radLon2 = rad(lon2);

		if (radLat1 < 0)
			radLat1 = Math.PI / 2 + Math.abs(radLat1);// south
		if (radLat1 > 0)
			radLat1 = Math.PI / 2 - Math.abs(radLat1);// north
		if (radLon1 < 0)
			radLon1 = Math.PI * 2 - Math.abs(radLon1);// west
		if (radLat2 < 0)
			radLat2 = Math.PI / 2 + Math.abs(radLat2);// south
		if (radLat2 > 0)
			radLat2 = Math.PI / 2 - Math.abs(radLat2);// north
		if (radLon2 < 0)
			radLon2 = Math.PI * 2 - Math.abs(radLon2);// west
		double x1 = EARTH_RADIUS * Math.cos(radLon1) * Math.sin(radLat1);
		double y1 = EARTH_RADIUS * Math.sin(radLon1) * Math.sin(radLat1);
		double z1 = EARTH_RADIUS * Math.cos(radLat1);

		double x2 = EARTH_RADIUS * Math.cos(radLon2) * Math.sin(radLat2);
		double y2 = EARTH_RADIUS * Math.sin(radLon2) * Math.sin(radLat2);
		double z2 = EARTH_RADIUS * Math.cos(radLat2);

		double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)
				+ (z1 - z2) * (z1 - z2));
		// 余弦定理求夹角
		double theta = Math.acos((EARTH_RADIUS * EARTH_RADIUS + EARTH_RADIUS
				* EARTH_RADIUS - d * d)
				/ (2 * EARTH_RADIUS * EARTH_RADIUS));
		double dist = theta * EARTH_RADIUS;
		return dist;
	}

	public void jdbcTest() {
		Statement sql;
		ResultSet rs;
		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 加载JDBC驱动
		String dbURL = "jdbc:sqlserver://192.168.18.216:1433;databaseName=toolsmanager_170726";
		// 连接服务器和数据库sample
		String userName = "sa"; // 默认用户名
		String userPwd = "pebs_2014"; // 密码
		Connection dbConn;
		try {
			Class.forName(driverName);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			sql = dbConn.createStatement();
			int count = sql
					.executeUpdate("insert into tigger_usertest values('2','男')");
			dbConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Float minNUmber = 1.94f;
		if(minNUmber%1>0.95){
			minNUmber = (float)Math.ceil(minNUmber);
		}else{
			minNUmber = (float)Math.floor(minNUmber);
		}
		System.out.println(minNUmber);
		// Float number = 13426.0F;
		// Float pricxe = 0.0750F;
		// System.out.println(number*pricxe);

		// try {
		// String a = Util.aftertime(Util.getDateTime(), 2 * 3600 * 1000l);
		// System.out.println(a);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Properties prop = new Properties();
		// prop.put("charSet", "gb2312"); // 这里是解决中文乱码
		// prop.put("user", "");
		// prop.put("password", "");
		// String url =
		// "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=D:/Program Files (x86)/Tencent/RTXServer/db/rtxdb.mdb";
		// // 文件地址
		// PreparedStatement ps = null;
		// Statement stmt = null;
		// ResultSet rs = null;
		// try {
		// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		// Connection conn = DriverManager.getConnection(url, prop);
		// stmt = (Statement) conn.createStatement();
		//
		// rs = stmt
		// .executeQuery("select Name from SYS_User where UserName ='Dong' and (accountstate is null or accountstate=0)");
		// while (rs.next()) {
		// String columnValue = rs.getString(1);
		// System.out.println(columnValue);
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// String a="21021408-006CS";
		// String b="21021408-006CS";
		// System.out.println(a.equalsIgnoreCase(b));
		// Util.Formatnumber("YT8.900.0372");
		// System.out.println((Integer.MAX_VALUE));
		// useEnumInJava();
		// String maxNumber="DNG10000220170929";
		// String obj = "DNG10000220170929001";
		// int num2 = 0;
		// String maxNumber2 = obj.toString();
		// num2 = Integer.parseInt(maxNumber2.substring(maxNumber2.length() - 3,
		// maxNumber2.length()));
		// num2++;
		// if (num2 < 10) {
		// maxNumber += "00" + num2;
		// } else if (num2 < 100) {
		// maxNumber += "0" + num2;
		// } else {
		// maxNumber += num2 + "";
		// }

		// System.out.println(101%2);
		// System.out.println("2017-08-10".substring(0,4)+"2017-08-10".substring(5,7));
		// System.out.println(Util.StringToDate("19740617","yyyyMMdd"));
		// //
		// System.out.println("2017-08-10".substring(0,4)+"2017-08-10".substring(5,7));
		// List<Procard> list1 = new ArrayList<Procard>();
		// Procard p1 = new Procard();
		// p1.setMarkId("aa");
		// Procard p2 = new Procard();
		// p2.setMarkId("bb");
		// list1.add(p1);
		// list1.add(p2);
		// Set<Procard> list2 = new HashSet<Procard>();
		// for(int i=0;i<2;i++){
		// for(Procard p:list1){
		// p.setFilnalCount(0f+i);
		// list2.add(p);
		// }
		// }
		// for(Procard p:list2){
		// System.out.println(p.getMarkId()+":"+p.getFilnalCount());
		// }
		//		
		// try {
		// System.out.println("WWQC201801120001".substring(12,
		// "WWQC201801120001".length()));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Util.Formatnumber("YT8.900.0372");
		// System.out.println((Integer.MAX_VALUE));
		// useEnumInJava();
		// String maxNumber="DNG10000220170929";
		// String obj = "DNG10000220170929001";
		// int num2 = 0;
		// String maxNumber2 = obj.toString();
		// num2 = Integer.parseInt(maxNumber2.substring(maxNumber2.length() - 3,
		// maxNumber2.length()));
		// num2++;
		// if (num2 < 10) {
		// maxNumber += "00" + num2;
		// } else if (num2 < 100) {
		// maxNumber += "0" + num2;
		// } else {
		// maxNumber += num2 + "";
		// }

		// Date date2 = Util.StringToDate("2019-01-01", "yyyy-MM-dd");
		// boolean bool = new Date().before(date2);
		// System.out.println(bool);
		// String k="2017/12/20";
		// k=k.replaceAll("/", "-");
		// System.out.println(k);
		//		
		// String maxNumber="DNG10000220170929";
		// String obj = "DNG10000220170929001";
		// int num2 = 0;
		// String maxNumber2 = obj.toString();
		// num2 = Integer.parseInt(maxNumber2.substring(maxNumber2.length() - 3,
		// maxNumber2.length()));
		// num2++;
		// if (num2 < 10) {
		// maxNumber += "00" + num2;
		// } else if (num2 < 100) {
		// maxNumber += "0" + num2;
		// } else {
		// maxNumber += num2 + "";
		// }

		// System.out.println(101%2);
		// System.out.println("2017-08-10".substring(0,4)+"2017-08-10".substring(5,7));
		// List<Procard> list1 = new ArrayList<Procard>();
		// Procard p1 = new Procard();
		// p1.setMarkId("aa");
		// Procard p2 = new Procard();
		// p2.setMarkId("bb");
		// list1.add(p1);
		// list1.add(p2);
		// Set<Procard> list2 = new HashSet<Procard>();
		// for(int i=0;i<2;i++){
		// for(Procard p:list1){
		// p.setFilnalCount(0f+i);
		// list2.add(p);
		// }
		// }
		// for(Procard p:list2){
		// System.out.println(p.getMarkId()+":"+p.getFilnalCount());
		// }

		// String num = "F" + Util.getDateTime("yyyyMMdd");
		// String maxfkNumber = "F201710299999";
		// if (maxfkNumber != null && maxfkNumber.length() > 0) {
		// String subnum = (Integer.parseInt("9"
		// + maxfkNumber.substring(9, maxfkNumber.length())) + 1)
		// + "";
		// num += subnum.substring(1, subnum.length());
		// } else {
		// num += "0001";
		// }
		// System.out.println(num);

		// String s = "MJ2017090001";
		// System.out.println("M".indexOf(s));
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
		// System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));

		// Random random = new Random();
		// String result = "";
		// for (int i = 0; i < 6; i++) {
		// result += random.nextInt(10);
		// }
		// System.out.print(result);
		// double a=LantitudeLongitudeDist(121.165163, 31.30723, 121.164929,
		// 31.30704);
		// System.out.println(a);

		// Date t1=Util.StringToDate("2017-07-06T17:36:24.000+0800",
		// "yyyy-MM-dd'T'HH:mm:ss.000+0800");
		// System.out.println(t1);
		// System.out.println(Util.DateToString(t1, "yyyy-MM-dd HH:mm:ss"));

		// Date date = new Date();
		// long t1 = date.getTime()-10*60*1000l;
		// System.out.println(t1);

		// Users users = new Users();
		// users.setName("test");
		// users.setSex("男");
		// users.setCode("385");
		// users.setCardId("11111111111111");

		// String godss =
		// "{\"goodsBarcode\":\"20131106092644\",\"goodsBeginQuantity\":\"360.0\",\"goodsChangeTime\":\"2013-11-27\",\"goodsClass\":\"成品库\",\"goodsCurQuantity\":\"360.0\",\"goodsCustomer\":\"上海大众\",\"goodsFormat\":\"\",\"goodsFullName\":\"前排气管总成\",\"goodsId\":\"28023\",\"goodsLotId\":\"20131100005\",\"goodsMarkId\":\"1KD253059AR\",\"goodsUnit\":\"件\"}";
		// JSONObject jsongoods = new JSONObject().fromObject(godss);
		// Goods goods = (Goods) JSONObject.toBean(jsongoods, Goods.class);
		//		
		//		
		// Date t1 = new Date();
		// JSONObject json = JSONObject.fromObject(goods);// 将java对象转换为json对象
		// String str = json.toString();// 将json对象转换为字符串
		// Date t2 = new Date();
		// System.out.println(t2.getTime() - t1.getTime());
		// System.out.println(str);

		// Date t1 = new Date();
		// Map<String, Object> map = new HashMap<String, Object>();
		// Field[] fields = goods.getClass().getDeclaredFields();
		// for (Field field : fields) {
		// try {
		// Class c = field.getType();// 获得属性的类型
		// if (c.getName().equals("java.util.Set")
		// || c.getName().equals("java.util.List")) {
		// continue;
		// }
		// String name = field.getName();
		// if (name.equals("default_interceptor")
		// || name.equals("handler")
		// || name.equals("_method_filter")
		// || name.equals("_methods_")) {
		// continue;
		// }
		// PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
		// goods.getClass());
		// Method method = pd.getReadMethod();// 获得get方法
		// Object propertyObj = method.invoke(goods);// 获得get方法的值
		// String propertyName = pd.getName();// 获得属性名称
		// if (propertyObj == null) {
		// continue;
		// }
		// // if (c.getName().equals("int") && "id".equals(propertyName)) {
		// // logging.setObjectId(Integer
		// // .parseInt(propertyObj.toString()));
		// // }
		// map.put(propertyName, propertyObj.toString());
		// } catch (Exception e) {
		// String name = field.getName();
		// e.printStackTrace();
		// }
		// }
		// String a=JSON.toJSONString(map);
		// Date t2 = new Date();
		// System.out.println(t2.getTime() - t1.getTime());
		// System.out.println(JSON.toJSONString(map));

		// String a="3";
		// String c="3";
		// Float f = Float.parseFloat(a);
		// String b=f+"";
		// System.out.println(b);
		// Integer i=(int)Float.parseFloat(b);
		// System.out.println(i);
		// System.out.println(a.compareTo(c));
		// String a="沉孔（倒角）";
		// System.out.println("1."+a.indexOf("（"));
		// System.out.println("2."+a.indexOf("（"));
		// a=a.replaceAll("（", "(");
		// a=a.replaceAll("）", ")");
		// System.out.println(a);
		// Test.test1();
		// double a=01.00001d;
		// System.out.println(Float.parseFloat(a+""));
		// String maxNumber = "ww20160901001";
		// String num = maxNumber.substring(10, maxNumber.length());
		// System.out.println(String.format("%03d", 1111));
		// System.out.println(String.format("%04d", 1));
		// String markId="DKBA04800251";
		// markId = markId.substring(0, 5) + "." + markId.substring(5,
		// 8)+"."+markId.substring(8, markId.length());
		// System.out.println(markId);
		// List<String> list =new ArrayList<String>();
		// list.add("1");
		// list.add("2");
		// list.add("3");
		// list.add("4");
		// list.add("5");
		// for (int i = 0; i <list.size(); i++) {
		// if("2".equals(list.get(i))){
		// list.remove(i);
		// i--;
		// }
		// System.out.println(list.size());
		// }

		// System.out.println("QD-RP-20160810003".length());

		// String a="a.b.c.dwg";
		// String b = a.substring(0, a.lastIndexOf("."));
		// String c = a.substring( a.lastIndexOf("."),a.length());
		// System.out.println(b);
		// System.out.println(c);
		// String a ="123456";
		// String a1 = a.substring(0, 2);
		// String a2 = a.substring(2, 4);
		// String a3 = a.substring(4, 6);
		// System.out.println("a1:"+a1+"a2:"+a2+"a3:"+a3);
		// Float a=1f;
		// Float b=2f;
		// System.out.println(a/b);
		// Long a=123456789l;
		// Long b=456321879l;
		// Long c=4545090l;
		// System.out.println((b-a)%(24*3600*100));
		// System.out.println(c%(24*3600*100));
		// String a="/upload/gongyi/2015-12/100356312.jpg";
		// String []strs=a.split("/");
		// System.out.println(strs[strs.length-1]);
		// System.out.println(123);
		// AlertMessagesServerImpl.addAlertMessages("模板审核（总经理审批）",
		// "品质部门的员工级绩效考核模版定制成功,请您审核! ", "1");

		// System.out.println(System.getProperty("user.dir"));//
		// 获得文件所在文件夹(结果:D:\liupei\work\红湖作业网\HHTask)

		// SimpleDateFormat accDate = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(accDate.format(new Date()));

		// Users user = new Users();
		// user.setCode("385");
		// user.setId(1);
		//
		// Class<?> demo;
		// try {
		//
		// String name = user.getClass().getName();
		// demo = Class.forName(user.getClass().getName());
		// Object obj = demo.newInstance();
		//
		// // 处理对象
		// Field[] fields = user.getClass().getDeclaredFields();
		// for (Field field : fields) {
		// // Class c = field.getType();// 获得属性的类型
		// PropertyDescriptor pd = new PropertyDescriptor(field.getName(),
		// user.getClass());
		// Method method = pd.getReadMethod();// 获得get方法
		// Object propertyObj = method.invoke(user);// 获得get方法的值
		// String methodName = pd.getName();// 获得属性名称
		//
		// Field field2 = demo.getDeclaredField(methodName);
		// field2.setAccessible(true);
		// field2.set(obj, propertyObj);
		// }
		//
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// String date1 = "2013-05-01";
		// String date2 = "2013-05-02";
		//
		// try {
		// Date dateTime1 = Util.StringToDate(date1, "yyyy-MM-dd");
		// Date dateTime2 = Util.StringToDate(date2, "yyyy-MM-dd");
		// Long sumDay = dateTime2.getTime() - dateTime1.getTime();
		// float day = (float) Math.abs((dateTime2.getTime() - dateTime1
		// .getTime())
		// / (60 * 60 * 1000)) /24;
		// sumDay = sumDay / 1000 / 60 / 2 / 60;
		// int days = (int) Math.abs((dateTime2.getTime() - dateTime1
		// .getTime())
		// / (24 * 60 * 60 * 1000)) + 1;
		// System.out.println(sumDay);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// // GBK编码格式源码路径
		// String srcDirPath = "D:/gbk/src";
		// // 转为UTF-8编码格式源码路径
		// String utf8DirPath = "D:\\UTF8\\src";
		//
		// // 获取所有java文件
		// Collection<File> javaGbkFileCol = FileUtils.listFiles(new File(
		// srcDirPath), new String[] { "java" }, true);
		//
		// for (File javaGbkFile : javaGbkFileCol) {
		// // UTF8格式文件路径
		// String utf8FilePath = utf8DirPath
		// + javaGbkFile.getAbsolutePath().substring(
		// srcDirPath.length());
		// // 使用GBK读取数据，然后用UTF-8写入数据
		// try {
		// FileUtils.writeLines(new File(utf8FilePath), "UTF-8", FileUtils
		// .readLines(javaGbkFile, "GBK"));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// System.out.println(1 / 0);

		// String a="√fa|√sdfa|√asdfads|√asdfa|";
		// String [] b=a.split("\\|");
		// System.out.println(""+1);
		//
		// String selfCard = "20130900001";// 当前最大流水卡片
		// Long a=Long.parseLong(selfCard)+1;
		//		 
		// Integer number = Integer.parseInt(selfCard.substring(
		// selfCard.length() - 7, selfCard.length())) + 1;// 获得最后6位编号并+1生成下一个编号
		// selfCard = selfCard.substring(0,selfCard.length() - 7)+number;
		//		
		// int numLength = number.toString().length();
		// if (numLength == 1) {
		// selfCard = "0000" + number;
		// }
		// // System.out.println(selfCard);
		// String ab=" 180253 250 a  ";
		// System.out.println(ab.replace(" ", ""));
		// System.out.println(500/300);
		// System.out.println(500%300);
		// System.out.println(0%3);
		// System.out.println(1%3);
		// System.out.println(2%3);
		// System.out.println(3%3);
		// System.out.println(4%3);

		// Date date = new Date();
		// Calendar calendar = Calendar.getInstance();
		// calendar.setFirstDayOfWeek(Calendar.MONDAY);
		// calendar.setTime(date);
		// System.out.println(calendar.get(Calendar.WEEK_OF_YEAR));

		// int zeng= 72/24;
		// System.out.println(zeng);

		// Users loginUser=Util.getLoginUser();

		// Long mm=1*365*24*60*60*1000L;
		// int year=(int) (mm/(365*24*60*60*1000L));
		// System.out.println(mm);
		// System.out.println(year);
		//		
		// Date date1=new Date();
		// Date date2=new Date();
		// if(date1.getTime()>date2.getTime()){

		// }

		// Long date1 = Util.StringToDate("2014-08-23 10:00:00",
		// "yyyy-MM-dd HH:mm:ss").getTime();
		// Long date2 = new Date().getTime();
		// Long moreDate = date2 - date1;
		// Long hourTime = 1000 * 60 * 60L;// 转化为小时
		// Long day = moreDate / (hourTime * 24);// 超出天数
		// Long hour = (moreDate % (hourTime * 24)) / hourTime;

		// for (Double j = 100D; j > 0.0001D;) {
		// j = j / 10D;
		// System.out.println(j);
		// }

		// String a = "0000";
		// Integer b = Integer.parseInt(a);
		// System.out.println(b);
		//		
		// String a2 = "00.0421";
		// Float b2 = Float.parseFloat(a2);
		// System.out.println(b2);

		// String mes =
		// "AA 00 00 00 07 00 00 00 04 02 01 BB AA 00 00 00 07 00 00 00 04 02 01 BB AA 00 00 00 07 00 00 00 04 02 01 BB";
		// String mes =
		// "AA 00 00 00 07 00 00 00 04 02 01 BB CC 00 00 00 01 00 DD";
		// int ccIndex = mes.indexOf("CC");
		// System.out.println(ccIndex);
		// String[] allmes = mes.split("BB");
		// for (int i = 0; i < allmes.length; i++) {
		// String onmes = allmes[i].replace("AA ", "");
		// String[] a = onmes.split(" ");
		// System.out.println(a);
		//
		// }
		// String a="hh,gg";
		// String[]as=a.split(",");
		// if(as!=null&&as.length>0){
		// for(String b:as){
		// System.out.println(b);
		// }
		// }
		// List<String> list=new ArrayList<String>();
		// for(String s:list){
		// System.out.println(1);
		// }
		// System.out.println(2);
		// Object[]objs=new Object[]{"hh"};
		// System.out.println(objs[0].equals("hh"));
		// String a="（a（a(a";
		// a=a.replaceAll("（","(");
		// System.out.print(a);
	}

	// public static void main(String[] args) throws MessagingException {

	/*
	 * com.task.util.Filter org.apache.struts2.dispatcher.FilterDispatcher
	 */
	/*
	 * JavaMailSenderImpl senderImpl = new JavaMailSenderImpl(); // 设定mail
	 * server senderImpl.setHost("smtp.163.com");
	 * senderImpl.setUsername("liupei_yx@163.com");
	 * senderImpl.setPassword("WO1314aini"); // 建立HTML邮件消息 MimeMessage
	 * mailMessage = senderImpl.createMimeMessage(); // true表示开始附件模式
	 * MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
	 * true, "utf-8"); // 设置收件人，寄件人 messageHelper.setTo("liupei_yx@163.com");
	 * messageHelper.setFrom("liupei_yx@163.com");
	 * messageHelper.setSubject("测试邮件！"); // true 表示启动HTML格式的邮件 messageHelper
	 * .setText("<html><head></head><body><h1>你好：附件！！</h1></body></html>",
	 * true); // 发送邮件 senderImpl.send(mailMessage);
	 * System.out.println("邮件发送成功.....");
	 */
	/*
	 * int num = 0; while (1 == 1) {
	 * 
	 * ApplicationContext ctx = new FileSystemXmlApplicationContext(
	 * "WebRoot/WEB-INF/applicationContext.xml");
	 * 
	 * sender = (JavaMailSender) ctx.getBean("mailSender");
	 * sendText("87922071@qq.com", "收邮件喽", "收到了我的循环邮件没~收到吼两声~嘎嘎~"); num++;
	 * System.out.println("发送第" + num + "封邮件"); }
	 */
	/* replaceString("AabcAaB","a","G"); */
	//

	// String a = "Abc";
	// System.out.println(a.compareTo("Abc"));

	// int i = -1;
	// i >>>= 10;
	// System.out.println(i);
	// long l = -1;
	// l >>>= 10;
	// System.out.println(l);
	// short s = -1;
	// s >>>= 10;
	// System.out.println(s);
	// byte b = -1;
	// b >>>= 10;
	// System.out.println(b);
	//
	// String
	// a="HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor";
	// System.out.println(a.length());

	// String wage="-1000";
	// Float a=+1000F;
	// Float b=-2000F;
	// System.out.println(a+b);
	// Float c=76.6F;
	// float b=c;
	// int a=(int) b;
	// System.out.println(a);
	//		
	// String date =new SimpleDateFormat("yyyy-MM-")
	// .format(new Date());
	// System.out.println(date.substring(7));

	// System.out.println("asdfasdfa\nasdfads");

	// int a=1;
	// System.out.println(a==1?1:2);

	// long l = 1234567890123456l;
	// for (int i = 63; i >= 0; i--) {
	// System.out.print(l);
	// if (i % 8 == 0)
	// System.out.println();
	// }
	// System.out.println();
	// int j = (int) l;
	// for (int i = 31; i >= 0; i--) {
	// System.out.print((j & (1l << i)) != 0 ? 1 + " " : 0 + " ");
	// if (i % 8 == 0)
	// System.out.println();
	// }

	// Float a = 100.123456F;
	// System.out.println(a / 100 * 100);

	// String a="<asdfasdfasdfasdf>asdfasdfAsdf<";
	// System.out.println(a.replaceAll("<","&lt;").replaceAll(">","&gt;"));

	// PrintService service =
	// PrintServiceLookup.lookupDefaultPrintService();
	// System.out.println(service.getName());
	//
	// // PrintService[] services =
	// PrintServiceLookup.lookupPrintServices(null,
	// // null);
	// // for (int i = 0; i < services.length; i++) {
	// // System.out.println(services[i].getName());
	// // }
	// //
	// service.createPrintJob();
	// String a="asdfasdf";
	// File s = new File("/");
	// a.equals(a);
	// System.out.println(s.hashCode());
	// System.out.println(s.listFiles());
	// boolean isObject = s instanceof Object;
	// System.out.println(isObject);

	// String message="中文";
	// try {
	// message=URLEncoder.encode(message, "utf-8");
	// } catch (UnsupportedEncodingException e) {
	// e.printStackTrace();
	// }
	// System.out.println(message);
	//		
	//		
	// try {
	// message=URLDecoder.decode(message,"utf-8");
	// } catch (UnsupportedEncodingException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//		
	// System.out.println(message);
	//
	// long time1 = new Date().getTime();
	// int i = 2;
	// i <<= 2;
	// long time2 = new Date().getTime();
	// System.out.println("" + (time2 - time1));
	//
	// long time9 = new Date().getTime();
	// i = 2 * 2 * 2;
	// long time10 = new Date().getTime();
	// System.out.println("" + (time10 - time9));
	//
	// File f = new File("D:\\1.wps");
	// System.out.println(f.renameTo(new File(f.getParent() + "2.wps")));

	// System.out.println("aaaaaaaaaaaaaaaaaaaaa");

	// String a = "aa,aa，ss，ff";
	// a = a.replaceAll("，", ",");
	// System.out.println(a);
	// String[] b = a.split(",");
	// for (int i = 0; i < b.length; i++) {
	// System.out.println(b[i]);
	// System.out.println(i);
	// }
	//
	// String a = "ABC";
	//
	// System.out.println(a.indexOf("Ab"));

	// int num[] = { 1, 2, 3, 4, 5 };
	//
	// for (int i = 0; i < num.length; i++) {
	// int a=num[i];//1
	//			
	//			
	// }
	// String a="a";
	//		
	// switch (a) {
	// case "":
	//			
	// break;
	//
	// default:
	// break;
	// }

	/*
	 * String a = "中 sdfasdfas"; System.out.println(a.charAt(0)); try {
	 * System.out.println((a.charAt(0) + "").getBytes("gbk").length); } catch
	 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	// static {
	// System.out.println("ssssssssssssssssssssssss");
	//		
	// }

	public static void sendText(String recipient, String subject, String content)
			throws MessagingException {
		MimeMessage mimeMsg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, "UTF-8");
		helper.setTo(recipient);
		helper.setFrom("liupei_yx@163.com");
		helper.setSubject(subject);
		helper.setText(content);
		sender.send(mimeMsg);
	}

	public static void replaceString(String source, String oldstring,
			String newstring) {

		System.out.println("原来的字符串：" + source);

		String result1 = source.replaceAll("(?i)" + oldstring, newstring); // 大小写不敏感

		System.out.println("不区分大小写的替换结果：" + result1);

		String result2 = source.replaceAll(oldstring, newstring);// 大小写敏感

		System.out.println("区分大小写的替换结果：" + result2);

	}

	/**
	 * 在Java代码使用枚举
	 */
	private static void useEnumInJava() {
		String typeName = "f5";
		TYPE type = TYPE.fromTypeName(typeName);
		if (TYPE.BALANCE.equals(type)) {
			System.out.println("根据字符串获得的枚举类型实例跟枚举常量一致");
		} else {
			System.out.println("大师兄代码错误");
		}

	}
}

enum enumTest {
	SPRING, SUMMER, AUTUMN, WINTER
}

enum TYPE {
	FIREWALL("firewall"), SECRET("secretMac"), BALANCE("f5");

	private String typeName;

	TYPE(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 根据类型的名称，返回类型的枚举实例。
	 * 
	 * @param typeName
	 *            类型名称
	 */
	public static TYPE fromTypeName(String typeName) {
		for (TYPE type : TYPE.values()) {
			if (type.getTypeName().equals(typeName)) {
				return type;
			}
		}
		return null;
	}

	public String getTypeName() {
		return this.typeName;
	}
}
