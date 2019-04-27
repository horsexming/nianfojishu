package com.task.util;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.ShortMessageServiceImpl;
import com.task.ServerImpl.expresscabinet.WePayServerImpl;
import com.task.ServerImpl.sys.ShortLinkServerImpl;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.dangan.ArchiveUnarchiverAplt;
import com.task.entity.dangan.DangAn;
import com.task.entity.dangan.DangAnBank;
import com.task.entity.expresscabinet.Charging;
import com.task.entity.expresscabinet.Courier;
import com.task.entity.expresscabinet.WeiXinOrder;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessLogInfor;
import com.task.entity.menjin.ResAccess;
import com.task.entity.menjin.ToolCabine;
import com.task.entity.seal.SealLog;

public class SocketServersKDG extends Thread {

	public static final int PORT = 8891;
	public static int clientcount = 0;
	public TotalDao toalDao;

	public SocketServersKDG(TotalDao toalDao) {
		this.toalDao = toalDao;
	}
 
	// public static void startServer() {
	public void run() {
		try {
			// int clientcount = 0; // 统计客户端总数
			boolean listening = true; // 是否对客户端进行监听
			ServerSocket server = null; // 服务器端Socket对象

			try {
				// 创建一个ServerSocket在端口8870监听客户请求
				server = new ServerSocket(PORT);
				System.out.println("KDG ServerSocket starts...");
			} catch (Exception e) {
				System.out.println("Can not listen to. " + e);
			}

			while (listening) {
				// 客户端计数
				clientcount++;
				// 监听到客户请求,根据得到的Socket对象和客户计数创建服务线程,并启动之
				new ServerThreadKDG(server.accept(), clientcount, toalDao)
						.start();
			}
		} catch (Exception e) {
			System.out.println("Error. " + e);
		}
	}

	public TotalDao getToalDao() {
		return toalDao;
	}

	public void setToalDao(TotalDao toalDao) {
		this.toalDao = toalDao;
	}
}

@SuppressWarnings("unchecked")
class ServerThreadKDG extends Thread {
	private static int number = 0; // 保存本进程的客户计数
	Socket socket = null; // 保存与本线程相关的Socket对象
	static TotalDao totalDao;

	public static String seleteOne = "from AccessEquipment where equipmentIP = ?";
	public static String seleteType = " and yxType = 0 ";
	public static String seleteTow = "from ResAccess where cunCodes = ? and shixiaoTime > ? and cuseType < 2 and type = '存取'"+seleteType+" order by id desc";
	public static String seleteThe = "from ResAccess where id = ?";
	public static String seleteFou = "from ResAccess where quCodes = ? and ace_Ip = ? and daGuiposition is not null and shixiaoTime > ? and cuseType = 2 and quseType = 0"+seleteType+" order by id desc";
	public static String seleteFou_1 = "from ResAccess where ace_Ip = ? and daGuiposition is not null and shixiaoTime > ? and cuseType = 2 and quseType = 0"+seleteType+" order by id desc";
	public static String seletefiv = "from ResAccess where cunCodes = ? and ace_Ip = ? and daGuiposition is not null and shixiaoTime > ? and cuseType = 0 and type = '寄取'"+seleteType+" order by id desc";
	public static String seletefiv_1 = "from ResAccess where ace_Ip = ? and daGuiposition is not null and shixiaoTime > ? and cuseType < 2 and type = '寄取'"+seleteType+" order by id desc";
	public static String seletetool = "from ToolCabine where nowArticleFormat = ? and cabStatus = '未满' and cabAceIp = ? order by cabOpenOrder";
	public static String seletetool1 = "from ToolCabine where cabOpenOrder = ? and cabAceIp = ? order by cabOpenOrder";
	public static String seletetool2 = "from ToolCabine where cabStatus = '未满' and cabAceIp = ? order by cabOpenOrder";
	public static String seletetool3 = "from ToolCabine where id = ?";
	public static String selectsex = "from ResAccess where quCodes=? and ace_Ip=?";
	public static final String YM = "已满";
	public static final String WM = "未满";
	
	public ServerThreadKDG(Socket socket, int clientnum, TotalDao toalDao) {

		this.socket = socket;
		number = clientnum;
		this.totalDao = toalDao;
		System.out.println("当前在线的快递柜数: " + number);
	}

	/**
     * 将柜子设置为已满
     * @param access 申请
     * @param status 状态(已满/未满)
     */
	static void updateTool(ResAccess access,String status) {
		// TODO Auto-generated method stub
		ToolCabine tc = null;
		List<ToolCabine> cabine = totalDao.query(seletetool1, access.getDaGuiposition()+"",access.getAce_Ip());
		if(cabine!=null&&cabine.size()>0){
			tc = cabine.get(0);
			tc.setCabStatus(status);
			if(status.equals(WM)){
				tc.setResAccessId(null);
			}else {	
				tc.setResAccessId(access.getId());
			}
			totalDao.update2(tc);
		}
	}
	
	public void run() {
		BufferedInputStream bis = null;
		BufferedInputStream bis1 = null;
		BufferedInputStream bis2 = null;
		BufferedInputStream bis3 = null;
		BufferedInputStream bis4 = null;
		
		InputStream in = null;
		StringBuilder builder = new StringBuilder();
		String accessIP = "";// 门禁IP
		String yanzheng = "";// 验证码
		String cardId = "";// 卡号
		String adminCordId = "";// 管理员卡号
		String adminStatus = "";// 卡绑定状态
		AccessEquipment accessEquipment = null;
		String nowDate = Util.getDateTime("yyyy-MM-dd");// 系统当前日期
		String nowDateTime = Util.getDateTime();// 系统当前时间
		try {
			accessIP = socket.getInetAddress().getHostAddress();
			System.out.println(accessIP + " 进入服务端了");
			// 由Socket对象得到输入流,并构造相应的BufferedReader对象
			in = socket.getInputStream();
			bis = new BufferedInputStream(in);
			// 先接收接收第一个字符 用做标识
			System.out.println("服务端开始接受标识！");
			byte[] biao_data = new byte[1];// 先接收第1个字符
			bis.read(biao_data);// 读取数据
			String mess1 = Util.byteArrayToHexString(biao_data);
			System.out.println(accessIP+"验证码刷卡类型标识: " + mess1);
			/**
			 * 第一步： 根据接收到IP或SIM标识去查找门禁设备
			 */
			String accessType = "";// 门禁类型
			List acElist = totalDao.query("from AccessEquipment where equipmentIP=? order by id desc",accessIP);
			if (acElist != null && acElist.size() > 0) {
				accessEquipment = (AccessEquipment) acElist.get(0);
				accessType = accessEquipment.getEquipmentDaoType();
				adminCordId = accessEquipment.getAdminCardId();
				adminStatus = accessEquipment.getAdminStatus();
				builder.append(mess1 + ",+" + accessType);
				if ("AA".equals(mess1) && "快递柜".equals(accessType)) {//取
					/************************ 验证码流程 **********************/
					int i = totalDao.getCount(seleteFou_1, accessIP,nowDateTime);
					if(i>0){
						getsocketNoClose(socket,7);
						bis1 = new BufferedInputStream(in);
						// 先接接收验证码或返回
						byte[] type = new byte[1];// 接收1位柜子类型
						bis1.read(type);// 读取数据
						String Mess = Util.byteArrayToHexString(type);						
						System.out.println(accessIP+"leixing: " + Mess);
						if ("DF".equals(Mess)) {//返回
							getsocketNoClose(socket, 0);//返回主页面
						}else {
							byte[] yanzhengm = new byte[7];
							bis1.read(yanzhengm);// 读取数据
							String yzmMess = Util.byteArrayToHexString(yanzhengm);							
							Integer message = 0;
							System.out.println(accessIP+"yanzhengma: " + yzmMess);
							try {
								message = Integer.parseInt(yzmMess,16);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							System.out.println("取物验证码："+message);
							List<ResAccess> ra2 = totalDao.query(seleteFou, message+"", accessIP, nowDateTime);
							if(ra2!=null&&ra2.size()>0){//发送柜号
																								
								//开始计算费用===============================================
								//1.先获取存入时间和当前时间
								List<ResAccess> resAccessChar = totalDao.query(selectsex,message+"",accessIP);
								if(resAccessChar !=null){
									Integer overTime4 = 0;
									Long overTime = Util.getWorkTime1(resAccessChar.get(0).getCopenTime(),nowDateTime);
									if(overTime !=null){
										String overTime2 = Long.toString(overTime/3600000);      //转换成String类型(小时)							
										if(overTime2 !=null){ 
											overTime4 = Integer.parseInt(overTime2);    //转换成Integer类型
										}
									}							
									//2.按照验证码表查询出的resAccessChar.getDaGuiId()进行柜子大中小查询
									List<ToolCabine> toolCabine = totalDao.query(" from ToolCabine where id=?", resAccessChar.get(0).getDaGuiId());
									//3.查询快递柜类型费用收取表(计算是否要收费,是计算费用,否直接开门)
									List<Charging> charging = new ArrayList<Charging>();
									float order_price1 = 0;
									if(toolCabine !=null&&toolCabine.size()>0&&toolCabine.get(0).getNowArticleFormat().equals("A0")){
										charging = totalDao.query(" from Charging where type=?",toolCabine.get(0).getNowArticleFormat());
										if(charging !=null&&charging.size()>0){
											order_price1 = overTime4-charging.get(0).getOverTime();
										}
									}else if(toolCabine !=null&&toolCabine.get(0).getNowArticleFormat().equals("A1")){
										charging = totalDao.query(" from Charging where type=?",toolCabine.get(0).getNowArticleFormat());
										if(charging !=null&&charging.size()>0){
											order_price1 = overTime4-charging.get(0).getOverTime();
										}
									}else if(toolCabine !=null&&toolCabine.get(0).getNowArticleFormat().equals("A2")){
										charging = totalDao.query(" from Charging where type=?",toolCabine.get(0).getNowArticleFormat());
										if(charging !=null&&charging.size()>0){
											order_price1 = overTime4-charging.get(0).getOverTime();
										}
									}
									
									Integer cabOpenOrder2 = -1;   //定义开柜指令(用于收费支付后开柜和无收费自动开柜)		
									String cabOpenOrder = toolCabine.get(0).getCabOpenOrder();  //先获取开柜指令
								    Integer cabOpenOrder1 = Integer.parseInt(cabOpenOrder);    //转换开柜指令
								    cabOpenOrder2 = cabOpenOrder1+175;
									if(order_price1 !=0&&order_price1>0&&toolCabine !=null){
										float inumPrice = charging.get(0).getCost();
										order_price1 = order_price1*inumPrice*100;
										Integer order_price2 = (int)order_price1;
										String order_price = order_price2.toString();
										//4.调用统一下单
										String product3Id = toolCabine.get(0).getCabNumber();   //商品编号
								        String userId = "user001";          //参数暂时用不到(先定死)
								        String body = toolCabine.get(0).getCaType();
								       					//转换开柜指令 开柜：B0~BF C0~CF D0~DC 45个      发送10进制			        
								        //WeiXinOrder weixinOrder = new WeiXinOrder();
								        WeiXinOrder weixinOrder = WePayServerImpl.weixinPay_1(userId,product3Id,order_price,body);
										//5.在发送支付链接之前进行数据库修改加入
										String url_Code = weixinOrder.getUrlCode();
										System.out.println("输出改变后的urlcode"+url_Code);
								        getsocketNoClose1(socket,url_Code);
										
										
								        
										
								        
										//验证支付状态是支付成功还是未支付(轮循商户后台记录)
										//int l = 0;
										//boolean b = true;
										Map<String, String> cxfhMap = new HashMap<String, String>();
										WeiXinOrder weixinorder2 = new WeiXinOrder();
										List<WeiXinOrder> weixinorder1 = new ArrayList<WeiXinOrder>();
										if(weixinOrder !=null){
											weixinorder1 = totalDao.query(" from WeiXinOrder where out_trade_no=? and startTime=?",weixinOrder.getOut_trade_no(),weixinOrder.getStartTime());
											if(weixinorder1 !=null&&weixinorder1.size()>0){
												weixinorder2 = weixinorder1.get(0);
											}
										}	
										
										
										//给线程类成员变量赋值
										RoundRobinThread.totalDao = totalDao;
										RoundRobinThread.weixinOrder = weixinOrder;
										RoundRobinThread.cxfhMap = cxfhMap;
										RoundRobinThread.ra2 = ra2;
										ResAccess access = ra2.get(0);
										RoundRobinThread.access = access;
										RoundRobinThread.socket = socket;
										RoundRobinThread.cabOpenOrder2 = cabOpenOrder2;
										RoundRobinThread.nowDateTime = nowDateTime;
										RoundRobinThread.WM = WM;
										RoundRobinThread.weixinorder2 = weixinorder2;
										RoundRobinThread.message = message;
										RoundRobinThread.accessIP = accessIP;
										RoundRobinThread.product3Id = product3Id;
										RoundRobinThread.order_price1 = order_price1;
										RoundRobinThread.body = body;
										//开启线程
										new RoundRobinThread(true).start();
										
										
										bis2 = new BufferedInputStream(in);
										byte[] fanhui = new byte[1];// 接收1位柜子类型
										bis2.read(fanhui);// 读取数据
										String fanhuiMess = Util.byteArrayToHexString(fanhui); //转换数据
										System.out.println("fanhuiMess"+fanhuiMess);											
										if ("DF".equals(fanhuiMess)) {//进行验证   接收数据为 DF和其他
											RoundRobinThread.flag= false;
											getsocketNoClose(socket, 0);//返回主页面											
										}
										
										//轮循查询 当前单号商户后台流水记录
//										while (b && l < 70) {				
//											b = true;
//											l++;																																							 																
//											
//											Thread.sleep(2000);
//											if(weixinOrder.getOut_trade_no() !=""||weixinOrder.getOut_trade_no() !=null){
//												cxfhMap = WePayServerImpl.orderQueryV(weixinOrder.getOut_trade_no());
//												for(String key : cxfhMap.keySet()) {
//											        System.out.println("key= "+ key + " and value= " + cxfhMap.get(key));
//											        if(key.equals("trade_state_desc")&&cxfhMap.get(key).equals("支付成功")){
//											        	b=false;
//											        	getsocketNoClose(socket,cabOpenOrder2);     //将开柜指令发送给前台											        	
//											        	
//											        	//取出之后进行快递柜信息的修改
//														ResAccess access = ra2.get(0);
//														access.setQopenTime(nowDateTime);//取出时间
//														access.setQuseType(2);//已完成
//														access.setYxType(1);
//														updateTool(access,WM);
//														totalDao.update2(access);											        	
//											        												        												        	
//											        	weixinorder2.setQuCodes(message.toString());
//											        	weixinorder2.setTradingStatus("支付成功");
//											        	weixinorder2.setSpbill_create_ip(accessIP);
//											        	weixinorder2.setProductId(product3Id);
//											        	weixinorder2.setOrder_price(order_price1);
//											        	weixinorder2.setBody(body);
//											        	//2.更新支付订单记录表
//											        	for(String key1 : cxfhMap.keySet()) {
//											        		if(key1.equals("transaction_id")){
//											        			weixinorder2.setTransaction_id(cxfhMap.get(key1));
//											        		}
//											        		if(key1.equals("openid")){
//											        			weixinorder2.setOpenId(cxfhMap.get(key1));
//											        		}
//											        		if(key1.equals("trade_type")){
//											        			weixinorder2.setTrade_type(cxfhMap.get(key1));
//											        		}
//											        		if(key1.equals("is_subscribe")){
//											        			weixinorder2.setIs_subscribe(cxfhMap.get(key1));
//											        		}											        		
//											        	}
//											        	boolean gxddjl = totalDao.update(weixinorder2);
//											        	System.out.println("输出更新订单记录表返回结果gxddjl"+gxddjl);
//											        }
//											    }
//											}			
//											if(l==29&&!b){
//												getsocketNoClose(socket,0);   //轮循70次后就直接发送返回主界面的指令
//											}												
//										}										
									}else{ 										
										getsocketNoClose(socket,cabOpenOrder2);	 //未产生费用自动开门		
										//取出之后进行快递柜信息的修改
										ResAccess access = ra2.get(0);
										access.setQopenTime(nowDateTime);//取出时间
										access.setQuseType(2);//已完成
										access.setYxType(1);
										updateTool(access,WM);
										totalDao.update2(access);
									}								
								}else{												
									getsocketNoClose(socket,6);     //此处为验证码和设备不存在返回结果06
								}																
							   								
								//如果是寄快递，快递员取走之后，给接收人发送消息
								//new SendThread(access).start();																
							}else {
								getsocketNoClose(socket, 4);//无效取物返回04
							}
						}
					}else {
						getsocketNoClose(socket, 6);
					}
				} else if ("AB".equals(mess1) && "快递柜".equals(accessType)) {
					/************************* 存物流程 **********************/
					int i = totalDao.getCount(seletetool2, accessIP);
					if(i==0){//柜子全满，发送01
						getsocketNoClose(socket, 1);
					}else{
						List<ToolCabine> cabines = totalDao.query(seletetool2, accessIP);
						int i1 = 0;
						int i2 = 0;
						int i3 = 0;
						for (ToolCabine toolCabine : cabines) {
							if("A0".equals(toolCabine.getNowArticleFormat())){
								i1++;
							}else if("A1".equals(toolCabine.getNowArticleFormat())){
								i2++;
							}else if("A2".equals(toolCabine.getNowArticleFormat())){
								i3++;
							}
						}
						byte[] send = new byte[6];
						send[0] = (byte) (i1/10);
						send[1] = (byte) (i1%10);
						send[2] = (byte) (i2/10);
						send[3] = (byte) (i2%10);
						send[4] = (byte) (i3/10);
						send[5] = (byte) (i3%10);
						getsocketbyte(socket, send);
						bis1 = new BufferedInputStream(in);
						// 先接柜子类型
						byte[] type = new byte[1];// 接收1位柜子类型
						bis1.read(type);// 读取数据
						String typeMess = Util.byteArrayToHexString(type);
						System.out.println(accessIP+"柜子类型: " + typeMess);
						if("A0".equals(typeMess)||"A1".equals(typeMess)||"A2".equals(typeMess)){//小、中、大
							int iy = totalDao.getCount(seletetool, typeMess,accessIP);
							if(iy>0){
								getsocketNoClose(socket, 8);
								//接收手机号流程
								bis2 = new BufferedInputStream(in);
								byte[] TEL = new byte[8];// 接收快递员手机号
								bis2.read(TEL);// 读取数据
								String telMess = Util.byteArrayToHexString(TEL);
								long tel = Long.parseLong(telMess,16);
								String pNumber = Long.toString(tel);
								System.out.println(accessIP+"手机号: " + pNumber);
								Map<String, Object> map = WePayServerImpl.selectCourier_1(pNumber);
								String couState = (String)map.get("couState");
								Integer count = (Integer) map.get("count");
								String phoneNumber = (String) map.get("phoneNumber");	
								System.out.println("输出count"+count+"和手机号phoneNumber"+phoneNumber);
								if(count==1&&couState.equals("同意")){
									//返回1接收手机号
									getsocketNoClose(socket,0x0c);  //此处返回手机号和存在指令
									//接收收件人手机号流程
									bis2 = new BufferedInputStream(in);
									byte[] uPhone = new byte[8];// 接收收件人手机号
									bis2.read(uPhone);// 读取数据
									String uPhoneMess = Util.byteArrayToHexString(uPhone);
									long uPhone2 = Long.parseLong(uPhoneMess,16);
									String uPhone3 = Long.toString(uPhone2);
								    
									//收件人手机号存在(则查询出其名称并显示)
									if(uPhone3 !=null){
										List<Users> usersList =  totalDao.query("select new  Users(u.code,u.id,u.name,u.userStatus,u.sex,p.phoneNumber)"+"from Users u join u.password p where p.phoneNumber=?",uPhone3);
										if(usersList.size()>0){
											String name = usersList.get(0).getName();
											String userStatus = usersList.get(0).getUserStatus();
											String phoneNumber4 = usersList.get(0).getPhoneNumber();
											System.out.println("输出name"+name+"和状态userStatus"+userStatus+"和手机号phoneNumber4"+phoneNumber4);
											
											/***************** 处理个人信息 *******************/
											String nameN = Util.getLimitLengthString("快递员", 8);
											String deptD = Util.getLimitLengthString(name, 8);
											try {
												int si = 8 - nameN.getBytes("gb2312").length;
												for (int j = 0; j < si; j++) {
													nameN = nameN + " ";
												}
												int sl = 8 - deptD.getBytes("gb2312").length;
												for (int k = 0; k < sl; k++) {
													deptD = deptD + " ";
												}
												System.out.println(nameN);
												System.out.println(deptD);
											} catch (UnsupportedEncodingException e) {
												e.printStackTrace();
											}
											//返回(快递员)和收件人名称
											getsocketNoClose(socket, nameN,deptD); 
																																											
											//接收确认信息和取消流程
											bis2 = new BufferedInputStream(in);
											byte[] queren = new byte[1];// 接收1位柜子类型
											bis2.read(queren);// 读取数据
											String querenMess = Util.byteArrayToHexString(queren); //转换数据
											
											if ("DF".equals(querenMess)) {//进行验证   接收数据为 DF和其他
												getsocketNoClose(socket, 0);//返回主页面
											}else {
												System.out.println(querenMess);
												getsocketNoClose(socket, 10);//扫码
												bis2 = new BufferedInputStream(in);
												//进行扫描单号
												byte[] tiaoma = new byte[100];// 接收100位单号
												bis2.read(tiaoma);// 读取数据
												String tiaomaMess = Util.byteArrayToHexString(tiaoma); //转换数据
												tiaomaMess = UtilHexString.hex2String(tiaomaMess.substring(8, tiaomaMess.indexOf("53484848")));    //53484848 为SHHH转换后内容
												System.out.println("输出一下快递单号tiaomaMess"+tiaomaMess);
//												
												List<ToolCabine> toolCabines = totalDao.query("from ToolCabine where caType = '快递柜' and cabStatus = '未满' and nowArticleFormat = ? and cabAceIp = ? order by cabNumber asc", typeMess,accessIP);
												if(toolCabines!=null&&toolCabines.size()>0){
													ToolCabine cabine = toolCabines.get(0);
													if(cabine!=null&&cabine.getCabOpenOrder()!=null)
													{
														int kai = Integer.parseInt(cabine.getCabOpenOrder());
														getsocketNoClose(socket, kai+0xAF);
																												
														//等待接收确认已存入信息。
														bis2 = new BufferedInputStream(in);
														byte[] queren1 = new byte[1];// 接收1位柜子类型
														bis2.read(queren1);// 读取数据
														String querenMess1 = Util.byteArrayToHexString(queren1); //转换数据
														String code = Util.yanNumber(6);
														if ("DE".equals(querenMess1)) {//完成
															ResAccess resAccess = new ResAccess();
															resAccess.setType("存取");
															resAccess.setDaGuiId(cabine.getId());
															resAccess.setDaGuihao(cabine.getCabNumber());
															resAccess.setDaGuiposition(kai);
															resAccess.setAce_Ip(cabine.getCabAceIp());
															resAccess.setAddTime(Util.getDateTime());//添加时间
															resAccess.setShixiaoTime(Util.getDateTime(10086));//得到7天后的时间
															resAccess.setQuCodes(code);
															resAccess.setQuseType(0);
															resAccess.setCunCodes(tiaomaMess);
															resAccess.setCuseType(2);//已完成
															resAccess.setCopenTime(nowDateTime);//存入时间
															resAccess.setYxType(0);
															resAccess.setAddCode(usersList.get(0).getCode());//收件人工号
															resAccess.setAddName(usersList.get(0).getName());//收件人姓名
															resAccess.setAddUserId(usersList.get(0).getId());//收件人id
															totalDao.save2(resAccess);
															
															//根据所开柜子判断是1号柜还是二号柜
															String equiLocation = "";
															List<AccessEquipment> accEquiList = totalDao.query(seleteOne, cabine.getCabAceIp() ); 
															if(accEquiList.size()>0){
																AccessEquipment accessEquipment2 = new AccessEquipment();
																accessEquipment2 = accEquiList.get(0);
																equiLocation = accessEquipment2.getEquipmentLocation();
															}
															//给接收人发送消息
															String Scode = "您的快递已存进"+equiLocation+"中,取货验证码为"+code+" (请勿向任何人泄露)";  
															//String Scode = "快递取货";//加上注册信息链接
															String return_code = new ShortMessageServiceImpl().send(phoneNumber4, Scode);
                                                            
															//修改快递柜中当前柜的状态(YM已满/WM未满)
															cabine.setCabStatus(YM);
															cabine.setResAccessId(resAccess.getId());
															totalDao.update2(cabine);
															getsocketNoClose(socket, 0);     //返回主界面
														}else if("DF".equals(querenMess1)){//取消
															getsocketNoClose(socket, 0);     //返回主界面
														}else if("08".equals(querenMess1)){//换柜
															//换柜逻辑
															getsocketNoClose(socket, 0);   //返回主界面
														}
													}
												}else {
													//返回选柜界面
												}												
											}																					
										}else{
											getsocketNoClose(socket,0x0f);
										}
									}					
								}else if(count==1&&couState.equals("未审批")){
									//返回提示给界面
									getsocketNoClose(socket,0x12);
								}else{
									String code = "WePayAction_tiaoZhuanAddCourier.action?phoneNumber="+pNumber;							
									String shortUrl = ShortLinkServerImpl.addStaticShortLink(AlertMessagesServerImpl.pebsUrl+"/"+code, "courier");
									String Scode = "您的注册地址为 "+shortUrl+" (请勿向任何人泄露)";  //加上注册信息链接
									String return_code = new ShortMessageServiceImpl().send(pNumber, Scode);
									if(return_code.length()>10){										
										//确认将短信发送到快递员手机上后返回提示到界面
										getsocketNoClose(socket,0x13);
										
										//接收界面返回按钮返回的DF
										bis2 = new BufferedInputStream(in);
										byte[] fanhui = new byte[1];// 接收1位返回
										bis2.read(fanhui);// 读取数据
										String fanhuiMess1 = Util.byteArrayToHexString(fanhui);
										if("DF".equals(fanhuiMess1)){
											getsocketNoClose(socket,0);
										}
										
										
//										if(phoneNumber !=null&&code==null){
//											//先将数据准备好,如何验证码返回正确就新增记录
//											Courier courier = new Courier();
//											courier.setPhoneNumber(phoneNumber);
//											courier.setCode(code);
//											
//											getsocketNoClose(socket,0x0e);  //此处返回进入输入验证码的界面指令
//												
//											//接收验证码流程
//											bis2 = new BufferedInputStream(in);
//											byte[] code1 = new byte[8];
//											bis2.read(code1);// 读取数据
//											String code1Mess = Util.byteArrayToHexString(code1);//转换数据											
//											long code2 = Long.parseLong(code1Mess,16);
//											String code3 = Long.toString(code2);
//											//根据手机号和验证码进行验证
//											if(code3.equals(code)){
//												boolean xzfh = WePayServerImpl.insertCourier_1(courier);
//												//boolean xzfh = totalDao.save(courier);
//												if(xzfh){
//												getsocketNoClose(socket,0x0c);  //返回今日输入收件人手机号流程
//																			
//												//接收收件人手机号流程
//												bis2 = new BufferedInputStream(in);
//												byte[] uPhone = new byte[8];
//												bis2.read(uPhone);// 读取数据
//												String uPhoneMess = Util.byteArrayToHexString(uPhone);//转换数据
//												long uPhone2 = Long.parseLong(uPhoneMess,16);
//												String uPhone3 = Long.toString(uPhone2);												
//												
//												//收件人手机号不为空时进入
//												if(uPhone3 !=null){
//													List<Users> usersList =  totalDao.query("select new  Users(u.name,u.userStatus,u.sex,p.phoneNumber)"+"from Users u join u.password p where p.phoneNumber=?",uPhone3);
//													if(usersList.size()>0){
//														String name = usersList.get(0).getName();
//														String userStatus = usersList.get(0).getUserStatus();
//														String phoneNumber4 = usersList.get(0).getPhoneNumber();
//														System.out.println("输出name"+name+"和状态userStatus"+userStatus+"和手机号phoneNumber4"+phoneNumber4);
//														
//														/***************** 处理个人信息 *******************/
//														String shouname = Util.getLimitLengthString(name, 8);
//														try {
//															int sl = 8 - shouname.getBytes("gb2312").length;
//															for (int k = 0; k < sl; k++) {
//																shouname = shouname + " ";
//															}
//															System.out.println(shouname);
//														} catch (UnsupportedEncodingException e) {
//															e.printStackTrace();
//														}
//														
//														//返回(快递员)和收件人名称
//														getsocketNoClose(socket, "快递员  ",shouname);														
//														
//														//接收确认信息和取消流程
//														bis2 = new BufferedInputStream(in);
//														byte[] queren = new byte[1];// 接收1位
//														bis2.read(queren);// 读取数据
//														String querenMess = Util.byteArrayToHexString(queren);  //转换数据
//														
//														if ("DF".equals(querenMess)) {//返回
//															getsocketNoClose(socket, 0);//返回主页面
//														}else {
//															System.out.println(querenMess);
//															getsocketNoClose(socket, 10);//扫码
//															bis2 = new BufferedInputStream(in);
//															//进行扫描单号
//															byte[] tiaoma = new byte[100];// 接收100位单号
//															bis2.read(tiaoma);// 读取数据
//															String tiaomaMess = Util.byteArrayToHexString(tiaoma);
//															tiaomaMess = UtilHexString.hex2String(tiaomaMess.substring(8, tiaomaMess.indexOf("53484848")));														
//															List<ToolCabine> toolCabines = totalDao.query("from ToolCabine where caType = '快递柜' and cabStatus = '未满' and nowArticleFormat = ? and cabAceIp = ? order by cabNumber asc", typeMess,accessIP);
//															if(toolCabines!=null&&toolCabines.size()>0){
//																ToolCabine cabine = toolCabines.get(0);
//																if(cabine!=null&&cabine.getCabOpenOrder()!=null)
//																{
//																	int kai = Integer.parseInt(cabine.getCabOpenOrder());
//																	getsocketNoClose(socket, kai+0xAF);																		
//																	
//																	//等待接收确认已存入信息。
//																	bis2 = new BufferedInputStream(in);
//																	// 先接柜子类型
//																	byte[] queren1 = new byte[1];// 接收1位柜子类型
//																	bis2.read(queren1);// 读取数据
//																	String querenMess1 = Util.byteArrayToHexString(queren1);
//																	if ("DE".equals(querenMess1)) {//完成
//																		ResAccess resAccess = new ResAccess();
//																		resAccess.setType("存取");
//																		resAccess.setDaGuiId(cabine.getId());
//																		resAccess.setDaGuihao(cabine.getCabNumber());
//																		resAccess.setDaGuiposition(kai);
//																		resAccess.setAce_Ip(cabine.getCabAceIp());
//																		resAccess.setAddTime(Util.getDateTime());//添加时间
//																		resAccess.setShixiaoTime(Util.getDateTime(10086));//得到7天后的时间
//																		resAccess.setQuCodes(Util.yanNumber(6));
//																		resAccess.setQuseType(0);
//																		resAccess.setCunCodes(tiaomaMess);
//																		resAccess.setCuseType(2);//已完成
//																		resAccess.setCopenTime(nowDateTime);//存入时间
//																		resAccess.setYxType(0);
//																		resAccess.setAddCode(usersList.get(0).getCode());//收件人工号
//																		resAccess.setAddName(usersList.get(0).getName());//收件人姓名
//																		totalDao.save2(resAccess);
//																		//给接收人发送消息
//																		//new SendThread(resAccess).start();
//																		cabine.setCabStatus(YM);
//																		cabine.setResAccessId(resAccess.getId());
//																		totalDao.update2(cabine);
//																		getsocketNoClose(socket, 0);
//																	}else if("DF".equals(querenMess1)){//取消
//																		getsocketNoClose(socket, 0);
//																	}else if("08".equals(querenMess1)){//换柜
//																		//换柜逻辑
//																		getsocketNoClose(socket, 0);
//																	}
//																}
//															}else {
//																//返回选柜界面
//															}																
//														}															
//													}else{
//														getsocketNoClose(socket,0x0f);
//													}
//												}
//												}
//											}
//										}																				
									}
								}	
								
								
								//getsocketNoClose(socket, 0);
								
							}else {//不够，返回2
								getsocketNoClose(socket, 2);
							}
						}else if ("DF".equals(typeMess)) {//返回
							getsocketNoClose(socket, 0);//返回主页面
						}
						
					}
				} else if ("AC".equals(mess1) && "快递柜".equals(accessType)) {
					//寄流程
					int i = totalDao.getCount(seletefiv_1, accessIP,nowDateTime);
					if(i==0){
						getsocketNoClose(socket, 6);//无可寄取的信息
					}else {
						getsocketNoClose(socket, 9);//有可寄取的信息 。等待接收6位验证码
						//接收验证码流程
						bis1 = new BufferedInputStream(in);
						byte[] df = new byte[1];
						bis1.read(df);// 读取数据
						String dfMess = Util.byteArrayToHexString(df);//转换数据		
						if("DF".equals(dfMess)){
							getsocketNoClose(socket, 0);//返回主页面
						}else {
							byte[] yanzhengm = new byte[7];
							bis1.read(yanzhengm);// 读取数据
							String yzmMess = Util.byteArrayToHexString(yanzhengm);							
							int message = 0;
							System.out.println(accessIP+"寄物品yanzhengma: " + yzmMess);
							try {
								message = Integer.parseInt(yzmMess,16);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							System.out.println("寄物验证码："+message);
							List<ResAccess> ra4 = totalDao.query(seletefiv, message, accessIP, nowDateTime);//
							if(ra4!=null&&ra4.size()>0){//发送柜号
								ResAccess access = ra4.get(0);
								access.setCuseType(1);
								totalDao.update2(access);
								getsocketNoClose(socket, access.getDaGuiposition()+0xAF);
								
								//等待接受确认存入信息
								bis1 = new BufferedInputStream(in);
								byte[] qr = new byte[1];
								bis1.read(qr);// 读取数据
								String qrMess = Util.byteArrayToHexString(qr);//转换数据		
								if ("DE".equals(qrMess)) {//完成
									access.setCopenTime(nowDateTime);//取出时间
									access.setCuseType(2);//已完成
									updateTool(access,YM);
									totalDao.save2(access);
									//如果是寄快递，在存入柜子之后，给取快递人发送短信
									new SendThread(access).start();
								}else if("DF".equals(qrMess)){//取消
									access.setCuseType(0);//返回状态
									totalDao.save2(access);
								}
								getsocketNoClose(socket, 0);
							}else {
								getsocketNoClose(socket, 4);//无效取物
								//updateStatus(ace,0);//初始状态
							}
							
						}
					}
				} else {
					// 无操作
					System.out.println("档案标识不正确。。。");
					getsocket(socket, 0);
				}
			} else {
				// 无操作
				System.out.println("无设备。。。");
				getsocket(socket, 0);
			}
			System.out.println("服务端关闭 soko");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {bis.close();}
				if (bis1 != null) {bis1.close();}
				if (bis2 != null) {bis2.close();}
				if (bis3 != null) {bis3.close();}
				if (bis4 != null) {bis4.close();}
				if (in != null) {in.close();}
				if (socket != null) {
					socket.close(); // 关闭Socket
					SocketServersKDG.clientcount--;

					System.out.println("服务端关闭,当前连接设备数量为:"
							+ SocketServersKDG.clientcount);
					AccessLogInfor accessLogInfor = new AccessLogInfor();
					accessLogInfor.setYanzheng(yanzheng);
					accessLogInfor.setInfor(builder.toString());
					accessLogInfor.setAceIp(accessIP);// IP
					accessLogInfor.setCardId(cardId);//
					accessLogInfor.setAddTime(Util.getDateTime());
					totalDao.save2(accessLogInfor);
					System.out.println("++" + builder.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // 关闭Socket输入流
		}
	}

	/**
	 * 添加再次开门记录
	 * @param accessIP
	 * @param cardId
	 * @param aplt2
	 */
	private void addDangAnBank(String accessIP, String cardId,
			ArchiveUnarchiverAplt aplt2) {
		DangAnBank anBank = new DangAnBank();
		anBank.setAddTime(Util.getDateTime());
		anBank.setCardId(cardId);
		anBank.setDaIp(accessIP);
		anBank.setDaNum(aplt2.getDaGuiposition());
		anBank.setUseStatus("未使用");
		totalDao.save2(anBank);
	}

	/**
	 * 存取档之后的操作 将price表中的状态改变
	 * @author licong
	 * @param nowDateTime
	 * @param aplt2
	 */
	private void updatePrice(String nowDateTime, ArchiveUnarchiverAplt aplt2) {
		if(aplt2.getDaId()!=null&&aplt2.getDaId()>0){
			List<Price> pricel = totalDao.query("from Price where id = ?", aplt2.getDaId()); 
			if(pricel!=null&&pricel.size()>0){
				Price price = pricel.get(0);
				price.setIsGuiDang("yes");
				price.setCunStatus("已存档");
				price.setDanganCunQuStatus("已存");
				price.setIsCunTime(nowDateTime);
				totalDao.update2(price);
				List<SealLog> seall = totalDao.query("from SealLog where documentId = ?", price.getId()); 
				if(seall!=null&&seall.size()>0){
					SealLog seal = seall.get(0);
					seal.setFujian2Status("已存档");
					seal.setIsCunTime(nowDateTime);
					totalDao.update2(seal);
				}
			}
		}
	}


	/**
	 * @author Li_Cong
	 * @param sockets
	 *            连接
	 * @param i
	 *            发送指令
	 * @throws IOException
	 *             异常抛出
	 */
	private static void getsocket(Socket sockets, Integer i) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(i);// 发送信号
		// bw.write(new char[] {01});
		bw.flush();
		bw.close();
		
	}

	/**
	 * @author Li_Cong
	 * @param sockets
	 *            连接
	 * @param i
	 *            发送指令
	 * @throws IOException
	 *             异常抛出
	 */
	public static void getsocketNoClose(Socket sockets, Integer i)
			throws IOException {
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
//				.getOutputStream()));
//		bw.write(i);// 发送信号
//		bw.flush();
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(i);
		out.flush();
		
	}

	private static void getsocketNoClose(Socket sockets, String s)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(s);
		bw.flush();

	}
	private static void getsocketNoClose1(Socket sockets, String s)
	throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(0x0d);
		bw.write(s);
		bw.flush();
		
	}
	
	private static void getsocketNoClose(Socket sockets, String s, String s1)
	throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(3);
		bw.write(s);
		bw.write(s1);
		bw.write(10);
		bw.flush();
		
	}

	/**
	 * 将验证码存入一个char数组 发送
	 * @param sockets
	 * @param yz
	 * @throws IOException
	 */
	public static void getsocketChar(Socket sockets, char[] yz)throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sockets
				.getOutputStream()));
		bw.write(0x03);// 发送信号
		bw.write(yz);// 发送信号
		bw.write(0x0A);// 发送信号
		bw.flush();
	}
	
	/**
	 * 发送三个两位十六进制的验证码
	 * @param sockets
	 * @param i
	 * @param i1
	 * @param i2
	 * @throws IOException
	 */
	public static void getsocket256wei(Socket sockets, int i, int i1, int i2)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(i);
		out.write(i1);
		out.write(i2);
		out.write(0x0A);
		out.flush();
	}
	
	/**
	 * 发送12位十六进制的开门编码
	 * @param sockets
	 * @throws IOException
	 */
	public static void getsocket12wei(Socket sockets, byte[] b)
	throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0xAF);
		out.write(b);
		out.flush();
	}
	
	/**
	 * 发送四个两位十六进制的验证码  adminCardId
	 * @param sockets
	 * @param i
	 * @param i1
	 * @param i2
	 * @param i3
	 * @throws IOException
	 */
	public static void getsocket256wei(Socket sockets, int i, int i1, int i2,
			int i3) throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(i);
		out.write(i1);
		out.write(i2);
		out.write(i3);
		out.write(0x0A);
		out.flush();
	}
	
	/**
	 * 将验证码存入一个byte数组 发送
	 * @param sockets
	 * @param by
	 * @throws IOException
	 */
	public static void getsocketbyte(Socket sockets, byte[] by)
			throws IOException {
		PrintStream out = new PrintStream(sockets.getOutputStream());
		out.write(0x03);
		out.write(by);
		out.write(0x0A);
		out.flush();
	}
	
	/***
	 * 读取数据
	 * 
	 * @param bis
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String readBis(InputStream is) throws Exception {
		// 再截取后四位
		byte[] nenghao_data = new byte[1];// 先接收第一个字符
		is.read(nenghao_data);// 读取数据
		return Util.byteArrayToHexString(nenghao_data);
	}

}




class RoundRobinThread extends Thread {
    
	static TotalDao totalDao;
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public static WeiXinOrder weixinOrder;
	public static Map<String, String> cxfhMap;
	public static ResAccess access;
	public static Socket socket;
	public static Integer cabOpenOrder2;
	public static List<ResAccess> ra2;
	public static String nowDateTime;
	public static String WM;
	public static WeiXinOrder weixinorder2;
	public static Integer message;
	public static String accessIP;
	public static String product3Id;
	public static float order_price1;
	public static String body;
	
	public static boolean flag = true;				
	
	public RoundRobinThread(boolean oneflag){
		this.flag=oneflag;
	}
	
	public RoundRobinThread(){
	}
				
	
	public void run(){      
		int l = 0;
		//轮循查询 当前单号商户后台流水记录
		while (flag && l < 45) {				
			//flag = true;
			l++;																																							 																
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(weixinOrder.getOut_trade_no() !=""||weixinOrder.getOut_trade_no() !=null){
				cxfhMap = WePayServerImpl.orderQueryV(weixinOrder.getOut_trade_no());
				for(String key : cxfhMap.keySet()) {
			        System.out.println("key= "+ key + " and value= " + cxfhMap.get(key));
			        if(key.equals("trade_state_desc")&&cxfhMap.get(key).equals("支付成功")){
			        	l=70;
			        	try {
							ServerThreadKDG.getsocketNoClose(socket,cabOpenOrder2); //将开柜指令发送给前台
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}										        	
			        	
			        	//取出之后进行快递柜信息的修改
						ResAccess access = ra2.get(0);
						access.setQopenTime(nowDateTime);//取出时间
						access.setQuseType(2);//已完成
						access.setYxType(1);
						ServerThreadKDG.updateTool(access,WM);

						totalDao.update2(access);											        				        												        												        	
			        	weixinorder2.setQuCodes(message.toString());
			        	weixinorder2.setTradingStatus("支付成功");
			        	weixinorder2.setSpbill_create_ip(accessIP);
			        	weixinorder2.setProductId(product3Id);
			        	weixinorder2.setOrder_price(order_price1);
			        	weixinorder2.setBody(body);
			        	//2.更新支付订单记录表
			        	for(String key1 : cxfhMap.keySet()) {
			        		if(key1.equals("transaction_id")){
			        			weixinorder2.setTransaction_id(cxfhMap.get(key1));
			        		}
			        		if(key1.equals("openid")){
			        			weixinorder2.setOpenId(cxfhMap.get(key1));
			        		}
			        		if(key1.equals("trade_type")){
			        			weixinorder2.setTrade_type(cxfhMap.get(key1));
			        		}
			        		if(key1.equals("is_subscribe")){
			        			weixinorder2.setIs_subscribe(cxfhMap.get(key1));
			        		}											        		
			        	}
			        	boolean gxddjl = totalDao.update(weixinorder2);
			        	System.out.println("输出更新订单记录表返回结果gxddjl"+gxddjl);
			        	try {
							ServerThreadKDG.getsocketNoClose(socket,0);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			    }
			}			
			if(l==69&&!flag){
				try {
					ServerThreadKDG.getsocketNoClose(socket,0);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   //轮循70次后就直接发送返回主界面的指令
			}												
		}
    }

}