package com.task.Server;

import java.util.List;

import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;

public interface BonusmoneyServer {

	// 根据登入人的部门查询出奖金总额的所有信息
	public List finddept(String dept, int pageNo, int pageSize);

	// 根据登入人的部门查询出奖金总额的所有信息总数
	public Integer getcount(String dept);

	// 查询所有的奖金总额表信息
	public List findAll(int pageNo, int pageSize);

	// 查询出所有的奖金额表的总数
	public Integer bonusmoneyCount();

	// 根据月份和登入人的部门查询出奖金明细表信息
	public List findBonusAll(String data, String dept, int pageNo, int pageSize);

	// 根据月份查询出奖金明细表的总数
	public Integer countBonus(String data, String dept);

	// 条件查询
	public List conditionFindAll(Bonusmoney bonusmoney, int pageNo, int pageSize);

	// 条件查询总数
	public Integer countbonusmoney(Bonusmoney bonusmoney);

	// 根据ID查询出所对应的信息
	public Bonusmoney findByID(int id);

	// 修改奖金总额表信息
	public boolean update(Bonusmoney bonusmoney);

	// 修改奖金明细表
	public boolean updatebonus(Bonus bonus);

	// 根据月份查询出奖金分配明细 打印预览
	public List print(String yuefen, String dept);

	// 根据成员卡号和工号和月份查询出奖金明细表
	public List findghkh(String gongnumber, String kanumber, String yuefen,
			String dept);

	// 根据月份查询出奖金总额
	public List findmoney(String yuefen, String banzu);

	// 根据月份查询出提奖记录表总额
	public List findtijiang(String date);

	// 根据月份和班组查询出奖金总额表信息
	public List finddatedept(String date, String banzu);

	// 根据月份和班组查询出奖金明细表部留
	public List findddeptlu(String date, String banzu);

	// 根据月份查询出奖金分配总额 显示饼图
	public List findDate(String date, String date2);

	// 查询出班组
	public List findDept();

	// 根据班组查询出所对的信息 走势图
	public List findTeam(String dept);

	// 查询出班组 用于显示班组环比走势图
	public List findDepthbi();

	// 根据月份查询出奖金分配总额
	public List findDateMoney(String date);

	// 根据月份和班组查询出 奖金分配总金额
	public List findDateMoney(String date, String dept);

	// 根据月份查询出奖金总额表 状态为 加工经理同意
	public List findMoney(String date);

	// 根据月份查询出奖金总额表状态为 生产副总同意
	public List findDate(String date);

	boolean lpToFp(Integer[] ids);

	/***
	 * 批产奖金转为工资
	 * 
	 * @param ids
	 * @return
	 */
	boolean lpToWage(Integer[] ids);

}
