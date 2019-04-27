package com.task.Server.menjin;

import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.List;
import java.util.Map;

import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.caiwu.pz.CwCertificate;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.AccessTime;
import com.task.entity.menjin.AccessWebcam;
import com.task.entity.menjin.ResAccess;
import com.task.entity.menjin.XungengRecord;
import com.task.entity.menjin.XungengTime;
import com.task.entity.onemark.OneLight;

public interface AccessEquipmentServer {
	/**
	 * 添加门禁设备记录
	 * 
	 * @return
	 */
	public String addAccessEquipment(AccessEquipment accessEquipment);

	/**
	 * 修改门禁设备记录
	 * 
	 * @return
	 */
	public String updateAccessEquipment(AccessEquipment accessEquipment);

	/**
	 * 官所有开的灯
	 * 
	 * @param accessEquipment
	 * @return
	 */
	public String closeDeng(AccessEquipment accessEquipment);

	/**
	 * 删除门禁设备记录
	 * 
	 * @return
	 */
	public String deleteAccessEquipment(AccessEquipment accessEquipment);

	/**
	 * 分页查询门禁设备记录
	 * 
	 * @return
	 */
	public Map<Integer, Object> findAccessEquipmentByCondition(
			AccessEquipment accessEquipment, int pageNo, int pageSize,
			String tag);

	/**
	 * 根据id获得AccessEquipment对象
	 */
	public AccessEquipment getbyIdAccessEquipment(Integer integer);

	/**
	 * 给设备生成验证码
	 * 
	 * @param accessEquipment
	 * @return
	 */
	public String addAceyanZ(AccessEquipment accessEquipment);

	/**
	 * 复位人行道闸机
	 * 
	 * @param ip
	 *            ip地址或域名
	 * @param port
	 *            端口
	 * @param openOrClose
	 *            开(1)或关(0)
	 */
	public String openZj(String ip, Integer port, int openOrClose);

	/**
	 * 添加管理员卡
	 * 
	 * @param cardId
	 * @return
	 */
	public String BindingAdmin(AccessEquipment accessEquipment);

	/**
	 * 返回所有门禁设备以字符串返回
	 * 
	 * @return
	 */
	public List<AccessEquipment> findallAce();

	/******************************* 操作摄像头对象的方法 ********************************/

	/**
	 * 添加门禁摄像头
	 * 
	 * @return
	 */
	public String addAccessWebcam(AccessWebcam AccessWebcam,
			AccessEquipment accessEquipment);

	/**
	 * 修改门禁摄像头
	 * 
	 * @return
	 */
	public String updateAccessWebcam(AccessWebcam AccessWebcam, String tag);

	/**
	 * 删除门禁摄像头
	 * 
	 * @return
	 */
	public String deleteAccessWebcam(AccessWebcam AccessWebcam);

	/**
	 * 分页查询门禁摄像头
	 * 
	 * @return
	 */
	public Map<Integer, Object> findAccessWebcamByCondition(
			AccessEquipment accessEquipment, int pageNo, int pageSize);

	/**
	 * 根据id获得AccessWebcam对象
	 */
	public AccessWebcam getbyIdAccessWebcam(Integer integer);

	/**
	 * 条件查询该门禁已绑定的用户
	 * 
	 * @param user
	 * @param parseInt
	 * @param pageSize
	 * @param string
	 * @return
	 */
	public Object[] findUsersByCondition(Users user, int parseInt,
			int pageSize, Integer id);

	/**
	 * 查询该门禁已绑定的所有用户
	 * 
	 * @param string
	 * @param id
	 * @return
	 */
	public List<Users> findAllBangUser(String string, Integer id);

	/**
	 * 查询该门禁已绑定的所有限时用户
	 * 
	 * @param id
	 *            设备id
	 * @return
	 */
	public List<Users> findAllBangTimeUser(Integer id);

	/**
	 * 查询所有未绑定用户
	 * 
	 * @param string
	 * @param id
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllUser(String string, Integer id, int parseInt,
			int pageSize);

	/**
	 * 给用户绑定权限
	 * 
	 * @param accessEquipment
	 * @param usersId
	 * @return
	 */
	public String binDingUsers(AccessEquipment accessEquipment,
			Integer[] usersId);

	/********************************* 档案柜管理 ***********************************/
	/**
	 * 添加档案柜
	 * 
	 * @return
	 */
	public String addDanganGui(AccessWebcam AccessWebcam);

	/**
	 * 修改档案柜
	 * 
	 * @return
	 */
	public String updateDanganGui(AccessWebcam AccessWebcam);

	/**
	 * 分页查看档案柜
	 * 
	 * @return
	 */
	public Map<Integer, Object> findDanganGuiByCondition(
			AccessWebcam AccessWebcam, int pageNo, int pageSize);

	/**
	 * 查询所有可用档案柜
	 * 
	 * @return
	 */
	public List<AccessWebcam> findDanganGui(String tag);

	/************************** 给档案柜绑定档案 ***************************/
	/**
	 * 条件查询所有未绑定的档案
	 * 
	 * @param price
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findPriceByCondition(Price price, int parseInt, int pageSize);

	/**
	 * 查询该档案柜已绑定的所有档案
	 * 
	 * @param id
	 *            档案柜id
	 * @return
	 */
	public List<Price> findAllBangPrice(Integer id);

	public List findAllBangXinxi(Integer id);

	public List findAllManager(Integer id);

	public List findCwCertificate(CwCertificate cwCertificate, Integer id);

	public boolean dangQuan(Integer id);

	/**
	 * 查询所有未绑定档案
	 * 
	 * @param string
	 * @param id
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Object[] findAllPrice(Integer id, int parseInt, int pageSize);

	/**
	 * 给档案柜绑定档案
	 * 
	 * @param Price
	 * @param usersId
	 * @return
	 */
	public String binDingPrice(AccessWebcam accessWebcam, Integer[] usersId);

	/**
	 * 给旧档案柜实际数量减1，新柜加1
	 * 
	 * @param oldaccessWebcam
	 * @param accessWebcam
	 */
	public void add1AndLess1(String oldaw, String aw, int i);

	/****************** 门禁时间段控制 ********************/
	/**
	 * 添加门禁时间段记录
	 * 
	 * @return
	 */
	public String addAccessTime(AccessTime AccessTime,
			AccessEquipment accessEquipment);

	/**
	 * 修改门禁时间段记录
	 * 
	 * @return
	 */
	public String updateAccessTime(AccessTime AccessTime);

	/**
	 * 删除门禁时间段记录
	 * 
	 * @return
	 */
	public String deleteAccessTime(Integer integer);

	/**
	 * 分页查询门禁时间段记录
	 * 
	 * @return
	 */
	public List<AccessTime> findAccessTimeByCondition(Integer accessEquipment);

	/**
	 * 根据id获得AccessTime对象
	 */
	public AccessTime getbyIdAccessTime(Integer integer);

	/**
	 * 根据设备ID和UsersId查询中间表对象
	 * 
	 * @param accessEquipment
	 *            设备ID
	 * @param usersId
	 *            用户ID
	 * @param id
	 *            1&2 1：设置时间段控制 2：取消时间段控制
	 * @return
	 */
	public boolean updateDoorBangDing(Integer accessEquipment, Integer usersId,
			Integer id);

	/*********************** 绑定灯 ***********************/
	/**
	 * 查询该门禁设备所有已绑定的灯
	 * 
	 * @param id
	 *            一体机id
	 * @return
	 */
	public List<OneLight> findAllBangLight(Integer id);

	/**
	 * 
	 * @param ace
	 *            设备id
	 * @param taOLId
	 *            选中要准备绑定的id数组
	 * @return
	 */
	public String binDingOneLight(Integer ace, Integer[] taOLId);

	/**
	 * 查询该所有未绑定一体机的灯 分页查询
	 * 
	 * @param oneLight
	 *            灯对象
	 * @param parseInt
	 *            页数
	 * @param pageSize
	 *            条数
	 * @return
	 */
	public Object[] findAllOneLight(OneLight oneLight, int parseInt,
			int pageSize);

	/**
	 * 打开库位门
	 */
	String BossOpenDoorById(Integer id, String doorIp, String doorPort);

	/**
	 * 关闭库位们
	 */
	String BossColseDoorById(Integer id, String doorIp, String doorPort);

	/**
	 * 定时关闭未关的卷帘门
	 */
	public void closeJuanlianmen();

	/**
	 * 定时重置的水阀，记录水流量
	 */
	public void closeShuiFa();

	/**
	 * 得到传入类型的门禁设备
	 * 
	 * @param s
	 * @return
	 */
	public List findAllAceShui(String s);

	/**
	 * 查询所有巡更时段
	 * 
	 * @param xungengTime
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAllXungengTime(XungengTime xungengTime,
			int pageNo, int pageSize);

	/**
	 * 查询所有巡更记录
	 * 
	 * @param xungengRecord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findAllXungengRecord(
			XungengRecord xungengRecord, int pageNo, int pageSize);

	/**
	 * 删除巡更时段
	 * 
	 * @param xungengTimeid
	 * @return
	 */
	public String delet(Integer xungengTimeid);

	/**
	 * 添加巡更时段
	 * 
	 * @param xungengTime
	 * @return
	 */
	public String addXungengTime(XungengTime xungengTime);

	/**
	 * 手动关闭水阀
	 * 
	 * @param id
	 * @param type
	 */
	public String closeShuiFa(Integer id, String type);

	void xungengTongzhi();

	/**
	 * 生成指定日期的巡更记录
	 */
	void huifuXunGeng();

	/**
	 * 给巡更记录状态赋值
	 */
	void fuzhiXunGeng();

	/**
	 * 根据类型得到描述类型
	 * @param type
	 * @return
	 */
	String findDescription(String type);

	/**
	 * 得到档案柜
	 * @param tag
	 * @param type
	 * @return
	 */
	List<AccessWebcam> findDanganGui(String tag, String type);

	/**
	 * 得到档案柜
	 * @param id
	 * @return
	 */
	public List findDescription(Integer id);
	
	/**
	 * 根据id得到ResAccess
	 * @param id
	 * @return
	 */
	public ResAccess findResAccessbyId(Integer id);
	
	/**
	 * 添加快递单号
	 * @param resAccess
	 * @return
	 */
	public String addResAccess(ResAccess resAccess);
	/**
	 * 取消快递单号
	 * @param resAccess
	 * @return
	 */
	public String updateResAccess(ResAccess resAccess);
	/**
	 * 分页查询快递柜使用记录
	 * 
	 * @return
	 */
	public Map<Integer, Object> findResAccess(
			ResAccess resAccess, int pageNo, int pageSize,
			String tag);

	public List<String> findCHuankou();

	SerialPort openSp() throws PortInUseException;

	/**
	 * 打开串口和监听
	 * @return
	 * @throws PortInUseException
	 */
	SerialPort openAndListener(String commName,Integer baudrate,final String sendtype) throws PortInUseException;

	/**
	 * 保存串口接收的数据
	 * @param data
	 * @param mSerialport
	 */
	public void addSerialSend(byte[] data, SerialPort mSerialport);

	/**
	 * 得到串口接收的数据
	 * @param receiveType 接收类型
	 * @param mSerialport 串口对象
	 * @return
	 */
	public String updateShow(String receiveType, SerialPort mSerialport);

	
}
