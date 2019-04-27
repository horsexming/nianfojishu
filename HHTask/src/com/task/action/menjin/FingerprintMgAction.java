package com.task.action.menjin;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.JRadioButton;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.dmltry.ZhongjianServer;
import com.task.Server.menjin.FingerprintMgServer;
import com.task.entity.Users;
import com.task.entity.dmltry.Zhongjian;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.FingerprintMg;
import com.task.util.MKUtil;
import com.task.util.serialPort.ByteUtils;
import com.task.util.serialPort.SerialPortManager;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

/**
 * 工具柜Action层 2016-06-08
 * 
 * @author Li_Cong
 * 
 */
@SuppressWarnings("unchecked")
public class FingerprintMgAction {

	private FingerprintMgServer fingerprintMgServer;// Server层
	private FingerprintMg fingerprintMg;// 对象
	private Users uses;
	private List<FingerprintMg> fingerprintMgList;//
	private List<AccessEquipment> accessEquipmentList;// 门禁设备列表
	private List<AccessEquipment> accessEquipmentWei;// 门禁设备列表
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	private Integer id;// id
	private Integer[] aceId;// 设备id
	private String pageStatus;// 页面状态// 绑定功能
	private String p4;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	// 指纹
	private String commName; // 串口
	private Integer baudrate; // 波频
	private static JRadioButton mDataASCIIChoice = new JRadioButton("ASCII", true);
	// 发送形式
	private static JRadioButton mDataHexChoice = new JRadioButton("Hex");
	private static String data1;
	private static String type;// caiji
	private String fanhui;
	private String nextstring;
	private int dou = 01;
	private Socket socket = null;
	private String systemjiehsou;
	private String theree;
	private String p3;
	private List<String> list;
	private static String ml;
	private int i = 0;
	private String time;
	// 串口对象
	private static SerialPort mSerialport;
	private ZhongjianServer zhongjianServer;

	public String Test() {
		return "error";
	}

	public String toadd() {
		uses = fingerprintMgServer.byIdUserId(id);
		accessEquipmentWei = fingerprintMgServer.byUserIdAgrennAcE(id);
		return "fingerprintMg_add";
	}

	// 分页显示
	// 显示查询内容
	public String showList() {
		if (fingerprintMg != null) {
			ActionContext.getContext().getSession().put("FingerprintMg", fingerprintMg);
		} else {// 用来保持分页时带着查询条件
			fingerprintMg = (FingerprintMg) ActionContext.getContext().getSession().get("FingerprintMg");
		}
		Map<Integer, Object> map = fingerprintMgServer.findFingerprintMg(fingerprintMg, Integer.parseInt(cpage),
				pageSize);
		fingerprintMgList = (List<FingerprintMg>) map.get(1);// 显示面试单列表
		if (fingerprintMgList != null && fingerprintMgList.size() > 0) {
			int count = (Integer) map.get(2);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("FingerprintMgAction_showList.action");
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "fingerprintMg_show";
	}

	// 添加方法
	public String add() {
		if (fingerprintMg != null) {
			errorMessage = fingerprintMgServer.addFingerprintMg(fingerprintMg, aceId);
			if ("添加成功".equals(errorMessage)) {
				Zhongjian zhongjian = null;
				zhongjianServer.addZhongjina(zhongjian);
				SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(ml));
				url = "FingerprintMgAction_tiaojsp.action";
				// url = "FingerprintMgAction_showList.action";

			}
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	public String tiaojsp() {

		return "texiao_show";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (fingerprintMg.getId() != null && fingerprintMg.getId() > 0 && fingerprintMg != null) {
			fingerprintMg = fingerprintMgServer.byIdFingerprintMg(fingerprintMg.getId());
			if (fingerprintMg != null)
				return "fingerprintMg_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String update() {
		errorMessage = fingerprintMgServer.updateFingerprintMg(fingerprintMg);
		if ("修改成功！".equals(errorMessage))
			url = "FingerprintMgAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id != null && id > 0) {
			errorMessage = fingerprintMgServer.deleteFingerprintMg(id);
			if ("删除成功！".equals(errorMessage))
				url = "FingerprintMgAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	// 查询个人当前有效指纹信息
	public String findOneFing() {
		fingerprintMgList = fingerprintMgServer.byUserIdFing(id);
		return "fingerprintMg_Oneshow";
	}

	// 给单个指纹绑定考勤机
	public String oneBangDing() {
		accessEquipmentList = fingerprintMgServer.byUserIdAcE(id);
		accessEquipmentWei = fingerprintMgServer.byUserIdAllAcE(id);
		return "fingsbangding";
	}

	// 绑定方法
	public String fingsDoorbangding() {
		fingerprintMg = fingerprintMgServer.byIdFingerprintMg(fingerprintMg.getId());
		if (fingerprintMg != null) {
			errorMessage = fingerprintMgServer.doorbangdingFings(aceId, fingerprintMg);
			if ("添加成功".equals(errorMessage)) {
				url = "FingerprintMgAction_oneBangDing.action?id=" + fingerprintMg.getId();
			}
		}
		return "error";
	}

	public String xiafa() {
		fingerprintMg = fingerprintMgServer.seltime(time);

		// if (fingerprintMg != null) {
		Zhongjian zh = null;
		// 获取到当前特征值了
		errorMessage = zhongjianServer.addZhongjina(zh);
		if ("添加成功".equals(errorMessage)) {
			url = "FingerprintMgAction_showList.action";
		}
		return "error";
		// }

	}

	public void zhiwenopne() {
		SerialPortManager.closePort(mSerialport);
		try {
			mSerialport = SerialPortManager.openPort(commName, baudrate);
			if (mSerialport != null) {
				SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {
					// 监听
					public void dataAvailable() {
						byte[] data = null;
						try {
							if (mSerialport == null) {
								System.out.println("串口对象为空，监听失败！");
							} else {
								// 读取串口数据
								data = SerialPortManager.readFromPort(mSerialport);
								// 以十六进制的形式接收数据
								fanhui = ByteUtils.byteArrayToHexStringK(data);

								if (fanhui != null && ml.substring(2, 4).equals("01")) {
									i++;
								}
								System.out.println(i);
								if (i == 1) {

									nextstring = "F5020102010000F5";
									SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(nextstring));

								}
								if (i == 2) {
									nextstring = "F5060102010004F5";
									SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(nextstring));
									i = -2;
									System.out.println(fanhui);
								}
								if (fanhui.substring(9, 11).equals("C4") && fanhui.substring(12, 14).equals("00")) {
									String tezz = ByteUtils.byteArrayToHexString(data);
									systemjiehsou = fanhui;
									System.out.println("指纹特征是：" + fanhui.substring(36, fanhui.length() - 7));
									String Identification = fanhui.substring(36, fanhui.length() - 7);
									// 把指纹特征存入数据库
									errorMessage = fingerprintMgServer.updateIdentification(Identification);
									String delete = "F5050000010004F5";
									SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(delete));
									url = "FingerprintMgAction_showList.action";

								} else if (fanhui.substring(12, 14).equals("00")) {
									systemjiehsou = "操作成功:" + fanhui;
									System.out.println("操作成功");
								} else if (fanhui.substring(12, 14).equals(ml.substring(12, 14))
										&& ml.substring(3, 5).equals("0C")) {
									System.out.println("对比成功");
								} else if (fanhui.substring(12, 14).equals("01")) {
									systemjiehsou = "操作失败:" + fanhui;
									System.out.println("操作失败");
								} else if (fanhui.substring(12, 14).equals("04")) {
									System.out.println("指纹库数据已满");
									systemjiehsou = "指纹库数据已满:" + fanhui;
								} else if (fanhui.substring(12, 14).equals("05")) {
									System.out.println("无此用户");
									systemjiehsou = "无此用户:" + fanhui;
								} else if (fanhui.substring(12, 14).equals("07")
										|| fanhui.substring(12, 14).equals("06")) {
									System.out.println("用户已存在");
									systemjiehsou = "用户已存在:" + fanhui;
								} else if (fanhui.substring(12, 14).equals("08")) {
									System.out.println("采集超时");
									systemjiehsou = "采集超时:" + fanhui;
								}

							}

						} catch (Exception e) {
							System.out.println(e.toString());
							// 发生读取错误时显示错误信息后退出系统
							// System.exit(0);
						}
					}
				});

				MKUtil.writeJSON(true, "串口已打开", null);
				socket.getInetAddress().getHostAddress();
			} else {
				MKUtil.writeJSON(true, "串口打开失败", null);
			}
		} catch (PortInUseException e) {
			MKUtil.writeJSON(false, "" + "串口已被占用！", null);
		}

	}

	// 获取本地机器串口
	public String huoqu() {
		Enumeration enumeration = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portId;
		list = new ArrayList<String>();
		while (enumeration.hasMoreElements()) {
			portId = (CommPortIdentifier) enumeration.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				String com = portId.getName();
				list.add(com);
			}
		}
		return "openck_show";
	}

	public void fanghui() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	// 关闭 串口
	public void zhiwenclose() {
		if (mSerialport != null) {
			mSerialport = null;
			MKUtil.writeJSON(true, "串口已关闭", null);
		} else {
			MKUtil.writeJSON(false, "串口关闭失败", null);
		}
	}

	public static int cunchu(String p4, String zhiwen) {
		int a = 0;
		a ^= Integer.parseInt(p4.substring(0, 2));
		a ^= Integer.parseInt(p4.substring(2, 4));
		a ^= Integer.parseInt(p4.substring(5, 7));
		return a;
	}

	public int yihuo() {
		int a = 0;
		a ^= Integer.parseInt(theree, 16);
		a ^= Integer.parseInt(p3);
		MKUtil.writeJSON(a);
		return a;
	}

	// 删除
	public static int deleteORhuo(String data) {
		int a = 4;
		a ^= Integer.parseInt(data.substring(0, 2));
		a ^= Integer.parseInt(data.substring(2, 4));
		return a;
	}

	/**
	 * 
	 * @param s
	 *            员工号
	 * @return
	 */
	// 用户录入
	public static int andORhuo(String s) {
		int a = 1;
		a ^= Integer.parseInt(s.substring(0, 2), 16);
		a ^= Integer.parseInt(s.substring(2, 4), 16);
		a ^= Integer.parseInt(s.substring(4, 6), 16);

		return a;
	}

	// 构造方法
	public String getSuccessMessage() {
		return successMessage;
	}

	public FingerprintMgServer getFingerprintMgServer() {
		return fingerprintMgServer;
	}

	public void setFingerprintMgServer(FingerprintMgServer fingerprintMgServer) {
		this.fingerprintMgServer = fingerprintMgServer;
	}

	public FingerprintMg getFingerprintMg() {
		return fingerprintMg;
	}

	public void setFingerprintMg(FingerprintMg fingerprintMg) {
		this.fingerprintMg = fingerprintMg;
	}

	public List<FingerprintMg> getFingerprintMgList() {
		return fingerprintMgList;
	}

	public void setFingerprintMgList(List<FingerprintMg> fingerprintMgList) {
		this.fingerprintMgList = fingerprintMgList;
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

	public Users getUses() {
		return uses;
	}

	public void setUses(Users uses) {
		this.uses = uses;
	}

	public Integer[] getAceId() {
		return aceId;
	}

	public void setAceId(Integer[] aceId) {
		this.aceId = aceId;
	}

	public List<AccessEquipment> getAccessEquipmentList() {
		return accessEquipmentList;
	}

	public void setAccessEquipmentList(List<AccessEquipment> accessEquipmentList) {
		this.accessEquipmentList = accessEquipmentList;
	}

	public List<AccessEquipment> getAccessEquipmentWei() {
		return accessEquipmentWei;
	}

	public void setAccessEquipmentWei(List<AccessEquipment> accessEquipmentWei) {
		this.accessEquipmentWei = accessEquipmentWei;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public Integer getBaudrate() {
		return baudrate;
	}

	public void setBaudrate(Integer baudrate) {
		this.baudrate = baudrate;
	}

	public static JRadioButton getmDataASCIIChoice() {
		return mDataASCIIChoice;
	}

	public static void setmDataASCIIChoice(JRadioButton mDataASCIIChoice) {
		FingerprintMgAction.mDataASCIIChoice = mDataASCIIChoice;
	}

	public static JRadioButton getmDataHexChoice() {
		return mDataHexChoice;
	}

	public static void setmDataHexChoice(JRadioButton mDataHexChoice) {
		FingerprintMgAction.mDataHexChoice = mDataHexChoice;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public static SerialPort getmSerialport() {
		return mSerialport;
	}

	public static void setmSerialport(SerialPort mSerialport) {
		FingerprintMgAction.mSerialport = mSerialport;
	}

	public String getFanhui() {
		return fanhui;
	}

	public void setFanhui(String fanhui) {
		this.fanhui = fanhui;
	}

	public String getNextstring() {
		return nextstring;
	}

	public void setNextstring(String nextstring) {
		this.nextstring = nextstring;
	}

	public int getDou() {
		return dou;
	}

	public void setDou(int dou) {
		this.dou = dou;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public String getSystemjiehsou() {
		return systemjiehsou;
	}

	public void setSystemjiehsou(String systemjiehsou) {
		this.systemjiehsou = systemjiehsou;
	}

	public String getTheree() {
		return theree;
	}

	public void setTheree(String theree) {
		this.theree = theree;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getMl() {
		return ml;
	}

	public void setMl(String ml) {
		this.ml = ml;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ZhongjianServer getZhongjianServer() {
		return zhongjianServer;
	}

	public void setZhongjianServer(ZhongjianServer zhongjianServer) {
		this.zhongjianServer = zhongjianServer;
	}

}
