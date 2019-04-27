package com.task.Server;

import java.util.List;
import java.util.Map;

import com.task.entity.Gift;
import com.task.entity.IgiftSet;
import com.task.entity.IndianaGift;
import com.task.entity.Integral;
import com.task.entity.IntegralGift;

public interface IntegralGiftServer {

	/**
	 * 添加礼品
	 */
	boolean addgift(Gift gift);
	/**
	 * 查看所有礼品
	 */
	Map<Integer, Object> findgiftByCondition(Gift gift,int pageNo, int pageSize);
	/**
	 * 删除礼品
	 */
	boolean delgift(Gift gift);
	/**
	 * 修改礼品
	 */
	boolean update(Gift gift);
	/**
	 * 领取礼品
	 */
	String addIntegralGift(IntegralGift integralgift,String dhnum);
	/**
	 * 查看所有礼品领取记录
	 */
	Map<Integer, Object> findIGByCondition(IntegralGift integralgift,int pageNo, int pageSize,String flag);
	/**
	 * 修改
	 */
	boolean updateIntegralGift(IntegralGift integralgift);
	/**
	 * 删除
	 */
	boolean delIntegralGift(IntegralGift integralgift);
	/**
	 * 根据Id获取gift信息
	 */
	Gift getGiftById(Integer id);
	/**
	 * 根据Id获取IntegralGift信息
	 */
	IntegralGift getIntegralGiftById(Integer id);
	/**
	 * 判断某人是否有足够的积分兑换礼品
	 */
	boolean Isenough(Integer userId,Float xaIntegral);
	/**
	 * 把礼品变为可抽奖的
	 */
	boolean Luckdraw(Integer id);
	/**
	 * 获取所有礼品
	 */
	List<Gift> findAllGift();
	/**
	 * 根据userId 获取某人积分情况;
	 */
	Integral findIntegralByUsersId(Integer userId);
	/**
	 * 
	 */
	Map<String,Object> GiftIndex();
	/**
	 * 查出所有参与抽奖的礼品名称
	 */
	String findcjgift();
	/**
	 *  抽奖扣除相应积分，减去相应库存
	 */
	String choujiang(Integer userId,String giftName);
	/**
	 * 根据名称获取礼品图片名称
	 */
	String getpicture(String giftName);
	/**
	 * 根据userId 和兑换码 兑换奖品
	 */
	public Gift dhchoujiang(Integer userId, String dhnum);
	/**
	 * 获得最近三天中奖记录 转换为字符串公告模式
	 */
	
	public String findZJIgift();
	/**
	 * 设置抽奖时间
	 */
	public String addigSet(IgiftSet igSet);
	/**
	 * 查询最近一次抽奖时设置判断当前时间是否在抽奖时间内
	 */
	public boolean findigSet();
	/**
	 *赠送红心
	 */
	 public String Giveintegral(Integral integral);
	 /**
	  * 报名参与夺宝
	  */
	 public String addindian(IndianaGift indianagift);
	 /**
	  * 计算中奖编号;计算公式为:(50个时间之和+时时彩5位开奖数字（传过来的str）)%奖品总需人次 = 幸运号码
	  */
	 public String jszjnum(String str,String qihao);
	 /**
	  * 根据中奖编号找出中奖人
	  */
	 public String zjuser(String zjnum,String qihao);
	 /**
	  * 查出所有库存大于0的礼品
	  */
	 public List<Gift> findgiftList();
	 /**
	  * 查询所有活动
	  */
	 public List<IgiftSet> AlligSetList(int pageNo, int pageSize);
	 public int getigSetcount();
	 /**
	  * 根据活动Id查出参加活动人员信息 
	  */
	 public Map<Integer, Object> findidgift(Integer igiftsetId, int pageNo,
				int pageSize);
	 /**
	  * 判断当前登录人是否有抽奖资格;
	  */
	 public boolean iscjzg();
	
		 
	 
}
