package com.task.Server;

import java.util.List;

import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.Teammembers;

public interface BonusServer {

	// 根据登录人的部门查询出所对应的班组成员
	public List finDept(String dept);

	// 根据登录人的id查询出所对应的班组成员
	public List finDept(Integer addUserId);

	// 根据奖金分配的时间查询出提奖的总金额
	public List finddate(String date);

	// 添加奖金分配明细表
	public boolean saveBonus(Bonus bonus);

	// 添加奖金总金额表
	public boolean saveBonusmoney(Bonusmoney bonusmoney);

	// 根据时间查询出所对应的奖金总额表的总金额
	public List findbonusmoney(String date);

	// 根据时间查询出奖金总额表中是否存在
	public List finddatabonusmoney(String date, String dept);

	// 根据ID查询出 所对的成员信息
	public Teammembers findByID(int id);

	// 根据月份和登入人的职位查询出奖金分配详细信息
	public List findzhiwei(String yuefen, String dept);

	// 根据月份显示 和班组显示相应的奖金分配信息
	public List findDateDept(String date, String dept);

	// 根据工号 月份查询出奖金明细
	public List fidnghdate(String chengyuangonghao, String yuefen, String dept);

	// 修改 奖金明细表
	public boolean updateBonus(Bonus bonus);

	// 根据月份和班组查询出奖金明细表部留
	public List findDpetbuliuMoney(String date, String banzu);

	// 根据月份和班组查询出奖金总额表信息
	public List findBonusmoney(String date, String banzu);

	// 修改 奖金分配总额表
	public boolean updateBonusmoney(Bonusmoney bonusmoney);

	// 根据月份查询出奖金分配的汇总
	public List findSummary(String date, int pageNo, int pageSize);

	// 根据月份查询出奖金分配的汇总 总数
	public int summaryCount(String date);

	// 跟据月份查询出奖金分配汇总信息 用于导出EXCEL
	public List findexcelsummary(String date);

	// 根据月份和班组 状态为审核中的
	public List findStatus(String date, String dept);

	// 根据月份查询出奖金总额表状态为 总经理同意或者财务同意
	public List findzongjiaji(String date);

	// 根据月份查询奖金总额表
	public List findDate(String date);

	// 根据登录人的部门查询出其所属成员并且该成员未添加过工资
	public List finDept(int addUserId, String status);

	public Object[] findExamList(int parseInt, int pageSize);

	public boolean updateExamBonus(Integer[] detailSelect, String tag);

	public Bonusmoney findbonusmoney1(String dateyuefen, String duty);

	public void updateBus(Integer bonusId);

}
