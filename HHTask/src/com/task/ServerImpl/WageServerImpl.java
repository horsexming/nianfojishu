package com.task.ServerImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.ContractBonusServer;
import com.task.Server.WageServer;
import com.task.Server.WageStandardServer;
import com.task.Server.caiwu.receivePayment.ReceiptServer;
import com.task.ServerImpl.caiwu.receivePayment.ReceiptServerImpl;
import com.task.entity.AssScore;
import com.task.entity.Bonus;
import com.task.entity.Bonusmoney;
import com.task.entity.Calendar;
import com.task.entity.ContractBonus;
import com.task.entity.InsuranceGold;
import com.task.entity.Integral;
import com.task.entity.Integralsource;
import com.task.entity.Teammembers;
import com.task.entity.Users;
import com.task.entity.Wage;
import com.task.entity.WagePay;
import com.task.entity.WageStandard;
import com.task.entity.caiwu.CwUseDetail;
import com.task.entity.caiwu.CwVouchers;
import com.task.entity.caiwu.CwVouchersDetail;
import com.task.entity.caiwu.receivePayment.Receipt;
import com.task.entity.fin.budget.SubBudgetRate;
import com.task.entity.payment.FundApply;
import com.task.util.Util;

public class WageServerImpl implements WageServer {

	private TotalDao totalDao;// Dao层
	private WageStandardServer wsServer;// 工资标准
	private ContractBonusServer contractBonusServer;
	private ReceiptServer receiptServer;

	public ReceiptServer getReceiptServer() {
		return receiptServer;
	}

	public void setReceiptServer(ReceiptServer receiptServer) {
		this.receiptServer = receiptServer;
	}

	public ContractBonusServer getContractBonusServer() {
		return contractBonusServer;
	}

	public void setContractBonusServer(ContractBonusServer contractBonusServer) {
		this.contractBonusServer = contractBonusServer;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public WageStandardServer getWsServer() {
		return wsServer;
	}

	public void setWsServer(WageStandardServer wsServer) {
		this.wsServer = wsServer;
	}

	// 每月工资处理 添加工资(绩效考核工资)
	public boolean addWage(AssScore assScore) {
		if (assScore != null) {
			String hql = "from Users where code=?";
			Users fenUser = (Users) totalDao.getObjectByCondition(hql, assScore.getCode());
			if (fenUser.getOnWork() == null || fenUser.getOnWork().length() <= 0 || fenUser.getOnWork().equals("离职")
					|| fenUser.getOnWork().equals("离职中")) {
				return false;
			}

			WageStandard ws = wsServer.findWSByUser(assScore.getCode());// 查询员工默认的工资模板
			if (ws != null) {
				Float jixiao = assScore.getPercentageScore() * ws.getJixiaokaohegongzi() / 100;// 绩效考核=考核分数/定制总分*绩效考核总钱数
				Float yingfaWage = ws.getGangweigongzi() + ws.getBaomijintie() + jixiao;// 应发工资计算
				// 查询该月工资是否存在
				Wage wage = new Wage();
				Wage oldWage = this.findWageByCodeAndCardId(assScore.getCode(), assScore.getCardId(),
						assScore.getAsstMouth());
				Users loginUser = (Users) ActionContext.getContext().getSession().get(TotalDao.users);// 获得登录用户
				if (oldWage != null) {
					if (oldWage.getWageClass().equals("离职工资")) {
						return false;
					}
					wage = oldWage;
					yingfaWage += wage.getDianhuabutie() + wage.getJinenggongzi() + wage.getGonglinggongzi()
							+ wage.getJiabanfei() + wage.getJiangjin();

				}
				wage.setUserId(assScore.getUserId());// 工资所属人员id
				wage.setCode(ws.getCode());// 工号
				wage.setCardId(ws.getCardId());// 卡号
				wage.setUserName(ws.getUserName());// 员工名称
				wage.setDept(ws.getDept());// 所属部门
				wage.setMouth(Util.getLastMonth("yyyy-MM月"));// 发放月份
				wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
				wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
				wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
				wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
				wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
				wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
				wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
				wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
				wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
				wage.setJixiaokaohegongzi(jixiao);// 绩效考核
				wage.setYingfagongzi(yingfaWage);// 应发工资
				wage.setWageStatus("添加变动");// 工资状态
				wage.setFangzufei(ws.getFangzufei());// 房租费
				wage.setIsBucha(ws.getBucha());// 是否补差
				wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间
				wage.setAddUserName(loginUser.getName());// 添加人员名称
				wage.setAddUserId(loginUser.getId());// 添加人id
				wage.setWageClass("绩效打分");// 工资类别
				wage.setWageStatue("正常");// 人员状态
				wage.setUserId(assScore.getUserId());// 工资所属人id

				if (oldWage != null) {
					return totalDao.update(wage);
				} else {
					// 为别的项设置为0
					wage.setJiangjin(0F);// 奖金
					wage.setJiabanfei(0F);// 加班费
					wage.setOther(0F);// 其他
					wage.setBingshikangdeng(0F);// 病事旷等
					wage.setWucanfei(0F);// 午餐费
					wage.setShuidianfei(0F);// 水电费
					wage.setBfgongzi(0F);// 补发工资
					wage.setYingjiaoshuijin(0F);// 应交税金
					wage.setShifagongzi(0F);// 实发工资
					return totalDao.save(wage);
				}
			}
		}

		return false;
	}

	/***
	 * 每月工资处理 添加工资(承包奖金分配工资、内退处理、主管互评)
	 * 
	 * @param code
	 *            工号数组
	 * @param cardId
	 *            卡号数组
	 * @param money
	 *            金额数组
	 * @return boolean
	 */
	public String addWage(String[] code, String[] cardId, Float[] money, Float[] money1, String status) {
		boolean bool = false;
		String message = "";
		Users user = null;
		ContractBonus contractBonus = null;
		ContractBonus blContractBonus = null;
		Float sumMony = 0F;
		Float totalMoney = 0F;
		if (code != null && code.length > 0 && cardId != null && cardId.length > 0 && money != null
				&& money.length > 0) {

			// 如果是承包分配 判断所分配的是否大于总额
			if (status != null && status.equals("cb")) {
				for (int i = 0; i < money.length; i++) {
					if (money[i] != null)
						sumMony += money[i];
				}
				// 根据登录用户id查询出上个月的承包奖金总额
				user = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
				contractBonus = contractBonusServer.findCBByMouthAndDept(user.getId(), Util.getLastMonth("yyyy-MM月"),
						"同意");
				blContractBonus = contractBonusServer.findCBByMouthAndDept(user.getId(), Util.getLastMonth("yyyy-MM月"),
						"承包部留");
				if (contractBonus != null) {
					if (blContractBonus != null) {
						totalMoney = blContractBonus.getTotalMoney();
					} else {
						totalMoney = contractBonus.getTotalMoney();
					}
					if (sumMony > totalMoney) {
						return "对不起!您所分配的奖金总额:" + sumMony + "元,大于您所能分配的最大承包奖金总额:" + totalMoney
								+ "元!请您重新分配!谢谢!<a href='javascript:history.back();'>返回</a>";
					}
				} else {
					return "不存在上个月的承包奖金总额或正在审核中,无法分配奖金!";
				}
			}

			Users loginUser = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
			for (int i = 0; i < code.length; i++) {
				String hql = "from Users where code=?";
				Users fenUser = (Users) totalDao.getObjectByCondition(hql, code[i]);
				if (fenUser.getOnWork() == null || fenUser.getOnWork().length() <= 0 || fenUser.getOnWork().equals("离职")
						|| fenUser.getOnWork().equals("离职中")) {
					message += fenUser.getName() + fenUser.getOnWork() + "无法处理工资<br/>";
					continue;
				}
				if (money[i] != null) {
					Wage oldWage = this.findWageByCodeAndCardId(code[i], cardId[i], Util.getLastMonth("yyyy-MM月")); // 通过工号和月份查询上个月的工资信息
					if (oldWage == null) {
						WageStandard ws = wsServer.findWSByUser(code[i]);// 查询员工默认的工资模板
						if (ws != null) {
							Wage wage = new Wage();
							Float jixiao = money[i];// 绩效考核=分配金额
							if (status != null && status.equals("cb")) {// 承包人员
								Float gangAndBao = ws.getGangweigongzi() + ws.getDianhuabutie() + ws.getBaomijintie();// 岗位工资+保密津贴+电话补贴
								if (money[i] > gangAndBao) {
									jixiao = money[i] - gangAndBao;// 绩效考核=分配金额-(岗位工资+保密津贴+电话补贴)
								} else {
									ws.setGangweigongzi(0F);
									ws.setBaomijintie(0F);
									ws.setJixiaokaohegongzi(money[i]);
								}
								wage.setWageClass("承包奖金");// 工资类别
							} else if (status.equals("bing")) {
								wage.setWageClass("内退处理");// 工资类别
							} else if (status.equals("manage")) {
								wage.setWageClass("主管互评");// 工资类别
							} else {
								message += "无效参数!请检查后重试!";
								continue;
							}

							Float yingfaWage = ws.getGangweigongzi() + ws.getDianhuabutie() + ws.getBaomijintie()
									+ ws.getJinenggongzi() + ws.getGonglinggongzi() + jixiao;// 应发工资计算
							// 如果存在加班费信息则添加加班费
							if (money1 != null && money1[i] != null) {
								yingfaWage += money1[i];// 应发工资计算
							}

							wage.setCode(ws.getCode());// 工号
							wage.setCardId(ws.getCardId());// 卡号
							wage.setUserName(ws.getUserName());// 员工名称
							wage.setDept(ws.getDept());// 所属部门
							wage.setMouth(Util.getLastMonth("yyyy-MM月"));// 发放月份
							wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
							wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
							wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
							wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
							wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
							wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
							wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
							wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
							wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
							wage.setJixiaokaohegongzi(jixiao);// 绩效考核
							wage.setYingfagongzi(yingfaWage);// 应发工资
							wage.setWageStatus("添加变动");// 工资状态
							wage.setFangzufei(ws.getFangzufei());// 房租费
							wage.setIsBucha(ws.getBucha());// 是否补差

							// 为别的项设置为0
							wage.setJiangjin(0F);// 奖金
							wage.setJiabanfei(0F);// 加班费
							// 如果存在加班费信息则添加加班费
							if (money1 != null && money1[i] != null) {
								wage.setJiabanfei(money1[i]);// 加班费
							}
							wage.setOther(0F);// 其他
							wage.setBingshikangdeng(0F);// 病事旷等
							wage.setWucanfei(0F);// 午餐费
							wage.setShuidianfei(0F);// 水电费
							wage.setBfgongzi(0F);// 补发工资
							wage.setYingjiaoshuijin(0F);// 应交税金
							wage.setShifagongzi(0F);// 实发工资
							wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 填写时间
							wage.setIsBucha(ws.getBucha());// 是否工资平衡
							wage.setAddUserName(loginUser.getName());// 添加人员名称
							wage.setAddUserId(loginUser.getId());// 添加人id
							wage.setWageStatue("正常");// 人员状态
							wage.setUserId(fenUser.getId());// 工资所属人id

							bool = totalDao.save(wage);
							if (bool) {
								// 如果是承包奖金分配 则处理部留金额
								if (status != null && status.equals("cb")) {
									if (money1[i] != null) {
										updateblContractBonus(user.getId(), money[i] + money1[i]);
									} else {
										updateblContractBonus(user.getId(), money[i]);
									}
								}
							} else {
								message += "工号为 " + code[i] + " 的奖金分配失败!请检查!<br/>";
							}
						} else {
							message += "工号为" + code[i] + " 的工资模版不存在,无法分配!请检查!<br/>";
						}
					} else {
						message += "员工:" + oldWage.getUserName() + "的" + oldWage.getMouth() + "份的奖金已经分配,请勿重复分配!<br/>";
					}
				}
			}
		}
		return message;
	}

	// 工资平衡(现场奖金分配计算工资)
	@SuppressWarnings("unchecked")
	public String wageBalance(int addUserId) {
		if ((Object) addUserId == null || addUserId < 0) {
			return "无效参数";
		}
		String message = "";
		String successMessage = "";
		String hql = "";
		List teammembersList = null;
		if (addUserId == 0) {// addUserId == 0则查询所有成员
			hql = "from Teammembers";
			teammembersList = totalDao.query(hql);
		} else {
			hql = "from Teammembers where addUserId=?";
			teammembersList = totalDao.query(hql, addUserId);// 查询某班组的所有成员
		}
		String lastMouth = Util.getLastMonth("yyyy-MM");// 上个月
		String lastMouth2 = Util.getLastMonth("yyyy-MM月");
		if (teammembersList != null && teammembersList.size() > 0) {
			String hql3 = "from Bonusmoney where bonusmoneystatus=? and addUserId=? and bonusmoneymonth=?";
			String hql4 = "from Bonus where bonusmembernumber=? and bonusdata=?";
			String hql5 = "from Wage where code=?  and mouth=?";
			String hql6 = "from Users where code=?";
			for (int i = 0; i < teammembersList.size(); i++) {
				Teammembers teammembers = (Teammembers) teammembersList.get(i);// 成员信息
				// 查询该成员信息所对应的User
				Users teamUser = (Users) totalDao.getObjectByCondition(hql6, teammembers.getTeammembersmembernumber());
				if (teamUser.getOnWork() == null || teamUser.getOnWork().length() <= 0
						|| teamUser.getOnWork().equals("离职") || teamUser.getOnWork().equals("离职中")) {
					message += teammembers.getTeammembersteamname() + teamUser.getOnWork() + "无法处理工资<br/>";
					continue;
				}

				// 查询员工默认的工资模板
				String hql8 = "from WageStandard where code=? and standardStatus='默认' and processStatus='同意'";
				List list = totalDao.query(hql8, teammembers.getTeammembersmembernumber());
				WageStandard ws = null;
				if (list != null && list.size() > 0) {
					ws = (WageStandard) list.get(0);
				}
				if (ws != null) {
					Bonusmoney bonusmoney = (Bonusmoney) totalDao.getObjectByCondition(hql3, "总经理同意",
							teammembers.getAddUserId(), lastMouth);// 查询上个月
					// 该班组奖金分配总额信息是否通过审核
					if (bonusmoney != null) {
						// 根据工号、卡号、月份查询该员工的奖金信息(此操作会查询出所有该成员的奖金信息)
						List bonusList = totalDao.query(hql4, teammembers.getTeammembersmembernumber(), lastMouth);
						if (bonusList != null) {
							Float jiangjin = 0f;// 奖金
							Float jiabanfei = 0f;// 加班费
							// 计算奖金和加班费
							for (int j = 0; j < bonusList.size(); j++) {
								Bonus bonus = (Bonus) bonusList.get(j);
								jiangjin += bonus.getBonusmembermoney();
								jiabanfei += bonus.getBonusovertimemealmoney();
								// 试制奖金
								if (bonus.getShizhiMoney() != null && bonus.getShizhiMoney() > 0) {
									jiangjin += bonus.getShizhiMoney();
								}
							}
							// 根据工号查询该员工上个月的工资是否存在
							Wage wage = new Wage();
							Wage oldWage = (Wage) totalDao.getObjectByCondition(hql5,
									teammembers.getTeammembersmembernumber(), lastMouth2);
							Float yingfaWage = ws.getYingfagongzi() + 0F + jiangjin + jiabanfei;// 应发工资((岗位工资+保密津贴+电话补贴+技能工资+特殊补贴)+绩效考核+奖金+加班费)
							if (oldWage != null) {
								if (oldWage.getWageClass().equals("离职工资")) {
									continue;
								}
								wage = oldWage;
							}
							wage.setCode(ws.getCode());// 工号
							wage.setCardId(ws.getCardId());// 卡号
							wage.setUserName(ws.getUserName());// 员工名称
							wage.setDept(teamUser.getDept());// 所属部门
							wage.setMouth(lastMouth2);// 发放月份
							wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
							wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
							wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
							wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
							wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
							wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
							wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
							wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
							wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
							wage.setYingfagongzi(yingfaWage);// 应发工资
							wage.setWageStatus("添加变动");// 工资状态
							wage.setJixiaokaohegongzi(
									wage.getJixiaokaohegongzi() == null ? 0F : wage.getJixiaokaohegongzi());// 绩效考核(如果绩效工资为空则设为0，不为空则不覆盖)
							wage.setJiangjin(jiangjin);// 奖金(如果奖金为空则为当前奖金,否则则为原有奖金加当前奖金)
							wage.setJiabanfei(jiabanfei);// 加班费(同奖金一样)
							wage.setFangzufei(ws.getFangzufei());// 房租费
							wage.setIsBucha(ws.getBucha());// 是否补差

							// 为别的项设置为0
							wage.setOther(0F);// 其他
							wage.setBingshikangdeng(0F);// 病事旷等
							wage.setWucanfei(0F);// 午餐费
							wage.setShuidianfei(0F);// 水电费
							wage.setBfgongzi(0F);// 补发工资
							wage.setYingjiaoshuijin(0F);// 应交税金
							wage.setShifagongzi(0F);// 实发工资
							wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间

							wage.setAddUserName(bonusmoney.getBonusmoneyname());// 添加人员名称
							wage.setAddUserId(bonusmoney.getAddUserId());// 添加人id
							wage.setWageClass("奖金分配");// 工资类别
							wage.setWageStatue("正常");// 人员状态
							wage.setUserId(teamUser.getId());// 工资所属人id

							boolean bool = false;
							if (oldWage == null) {// 不存在工资则新添加
								bool = totalDao.save(wage);
							} else {
								bool = totalDao.update(wage);
							}

							if (bool == false) {
								message += "处理员工" + teammembers.getTeammembersteamname() + "的工资信息时出错了!,请核查!<br/>";
							} else {
								successMessage += "员工: " + teammembers.getTeammembersteamname() + " 的工资信息处理成功!<br/>";
								String hql_1 = "from Integral where integrcode=? and integralName=?";
								Integral integral = (Integral) totalDao.getObjectByCondition(hql_1,
										teammembers.getTeammembersmembernumber(), teammembers.getTeammembersteamname());
								if (integral == null) {
									integral = new Integral();
								}
								String hql_user = "from Users where code=? and name=?";
								Users user = (Users) totalDao.getObjectByCondition(hql_user,
										teammembers.getTeammembersmembernumber(), teammembers.getTeammembersteamname());
								integral.setIntegralName(teammembers.getTeammembersteamname());// 积分所属人姓名
								integral.setIntegrcode(teammembers.getTeammembersmembernumber());// 积分所属人工号
								integral.setIntegrdept(wage.getDept());// 积分所属人部门
								integral.setUserId(user.getId());
								Integralsource is = new Integralsource();
								List<Integralsource> islist = new ArrayList<Integralsource>();
								is.setAddintegral(wage.getJiangjin().intValue());// 添加的积分
								is.setAddtime(Util.getDateTime());// 添加时间
								is.setLaiyuan("奖金分配");
								islist.add(is);

								integral.setIsList(islist);
								String msg = IntegralServerDaoImpl.addIntegral1(integral);
								if ("true".equals(msg)) {
									successMessage += "员工: " + teammembers.getTeammembersteamname()
											+ " 的积分信息处理成功!<br/>";
								} else {
									successMessage += "员工: " + teammembers.getTeammembersteamname() + msg + "<br/>";
								}

							}
						} else {
							message += "不存在员工" + teammembers.getTeammembersteamname() + "的奖金分配信息,请核查!<br/>";
						}
					} else {
						message += "员工 " + teammembers.getTeammembersteamname() + "所在班组的奖金总额尚未审核完成!请等待!<br/>";
					}
				} else {
					message += "不存在员工" + teammembers.getTeammembersteamname() + "的工资模板信息,请添加!<br/>";
				}

			}
		} else {
			message = "班组中没有成员,请添加成员!";
		}
		return successMessage + message;
	}

	public String wageFenpei(int bonusId) {
		if ((Object) bonusId == null || bonusId < 0) {
			return "无效参数";
		}
		String message = "";
		String successMessage = "";
		String hql = "";
		List teammembersList = null;
		// if (addUserId == 0) {// addUserId == 0则查询所有成员
		// hql = "from Teammembers";
		// teammembersList = totalDao.query(hql);
		// } else {
		// hql = "from Teammembers where addUserId=?";
		// teammembersList = totalDao.query(hql, addUserId);// 查询某班组的所有成员
		// }
		String lastMouth = Util.getLastMonth("yyyy-MM");// 上个月
		String lastMouth2 = Util.getLastMonth("yyyy-MM月");
		if (teammembersList != null && teammembersList.size() > 0) {
			String hql3 = "from Bonusmoney where bonusmoneystatus=? and addUserId=? and bonusmoneymonth=?";
			String hql4 = "from Bonus where bonusmembernumber=? and bonuscardnumber=? and bonusdata=?";
			String hql5 = "from Wage where code=?  and mouth=?";
			String hql6 = "from Users where code=?";
			for (int i = 0; i < teammembersList.size(); i++) {
				Teammembers teammembers = (Teammembers) teammembersList.get(i);// 成员信息
				// 查询该成员信息所对应的User
				Users teamUser = (Users) totalDao.getObjectByCondition(hql6, teammembers.getTeammembersmembernumber());
				if (teamUser.getOnWork() == null || teamUser.getOnWork().length() <= 0
						|| teamUser.getOnWork().equals("离职") || teamUser.getOnWork().equals("离职中")) {
					message += teammembers.getTeammembersteamname() + teamUser.getOnWork() + "无法处理工资<br/>";
					continue;
				}

				// 查询员工默认的工资模板
				String hql8 = "from WageStandard where code=? and standardStatus='默认' and processStatus='同意'";
				List list = totalDao.query(hql8, teammembers.getTeammembersmembernumber());
				WageStandard ws = null;
				if (list != null && list.size() > 0) {
					ws = (WageStandard) list.get(0);
				}
				if (ws != null) {
					Bonusmoney bonusmoney = (Bonusmoney) totalDao.getObjectByCondition(hql3, "总经理同意",
							teammembers.getAddUserId(), lastMouth);// 查询上个月
					// 该班组奖金分配总额信息是否通过审核
					if (bonusmoney != null) {
						// 根据工号、卡号、月份查询该员工的奖金信息(此操作会查询出所有该成员的奖金信息)
						List bonusList = totalDao.query(hql4, teammembers.getTeammembersmembernumber(),
								teammembers.getTeammemberscardnumber(), lastMouth);
						if (bonusList != null && bonusList.size() > 0) {
							Float jiangjin = 0f;// 奖金
							Float jiabanfei = 0f;// 加班费
							// 计算奖金和加班费
							for (int j = 0; j < bonusList.size(); j++) {
								Bonus bonus = (Bonus) bonusList.get(j);
								jiangjin += bonus.getBonusmembermoney();
								jiabanfei += bonus.getBonusovertimemealmoney();
								// 试制奖金
								if (bonus.getShizhiMoney() != null && bonus.getShizhiMoney() > 0) {
									jiangjin += bonus.getShizhiMoney();
								}

							}
							// 根据工号查询该员工上个月的工资是否存在
							Wage wage = new Wage();
							Wage oldWage = (Wage) totalDao.getObjectByCondition(hql5,
									teammembers.getTeammembersmembernumber(), lastMouth2);
							Float yingfaWage = ws.getYingfagongzi() + 0F + jiangjin + jiabanfei;// 应发工资((岗位工资+保密津贴+电话补贴+技能工资+特殊补贴)+绩效考核+奖金+加班费)
							if (oldWage != null) {
								if (oldWage.getWageClass().equals("离职工资")) {
									continue;
								}
								wage = oldWage;
							}
							wage.setCode(ws.getCode());// 工号
							wage.setCardId(ws.getCardId());// 卡号
							wage.setUserName(ws.getUserName());// 员工名称
							wage.setDept(teamUser.getDept());// 所属部门
							wage.setMouth(lastMouth2);// 发放月份
							wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
							wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
							wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
							wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
							wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
							wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
							wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
							wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
							wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
							wage.setYingfagongzi(yingfaWage);// 应发工资
							wage.setWageStatus("添加变动");// 工资状态
							wage.setJixiaokaohegongzi(
									wage.getJixiaokaohegongzi() == null ? 0F : wage.getJixiaokaohegongzi());// 绩效考核(如果绩效工资为空则设为0，不为空则不覆盖)
							wage.setJiangjin(jiangjin);// 奖金(如果奖金为空则为当前奖金,否则则为原有奖金加当前奖金)
							wage.setJiabanfei(jiabanfei);// 加班费(同奖金一样)
							wage.setFangzufei(ws.getFangzufei());// 房租费
							wage.setIsBucha(ws.getBucha());// 是否补差

							// 为别的项设置为0
							wage.setOther(0F);// 其他
							wage.setBingshikangdeng(0F);// 病事旷等
							wage.setWucanfei(0F);// 午餐费
							wage.setShuidianfei(0F);// 水电费
							wage.setBfgongzi(0F);// 补发工资
							wage.setYingjiaoshuijin(0F);// 应交税金
							wage.setShifagongzi(0F);// 实发工资
							wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间

							wage.setAddUserName(bonusmoney.getBonusmoneyname());// 添加人员名称
							wage.setAddUserId(bonusmoney.getAddUserId());// 添加人id
							wage.setWageClass("奖金分配");// 工资类别
							wage.setWageStatue("正常");// 人员状态
							wage.setUserId(teamUser.getId());// 工资所属人id

							boolean bool = false;
							if (oldWage == null) {// 不存在工资则新添加
								bool = totalDao.save(wage);
							} else {
								bool = totalDao.update(wage);
							}

							if (bool == false) {
								message += "处理员工" + teammembers.getTeammembersteamname() + "的工资信息时出错了!,请核查!<br/>";
							} else {
								successMessage += "员工: " + teammembers.getTeammembersteamname() + " 的工资信息处理成功!<br/>";
								// String hql_1 =
								// "from Integral where integrcode=? and
								// integralName=?";
								// Integral integral = (Integral) totalDao
								// .getObjectByCondition(
								// hql_1,
								// teammembers
								// .getTeammembersmembernumber(),
								// teammembers
								// .getTeammembersteamname());
								// String hql_user =
								// "from Users where code=? and name=?";
								// Users user = (Users)
								// totalDao.getObjectByCondition(hql_user,
								// teammembers
								// .getTeammembersmembernumber(),teammembers
								// .getTeammembersteamname());
								// integral.setIntegralName(teammembers
								// .getTeammembersteamname());// 积分所属人姓名
								// integral.setIntegrcode(teammembers
								// .getTeammembersmembernumber());// 积分所属人工号
								// integral.setIntegrdept(wage.getDept());//
								// 积分所属人部门
								// integral.setUserId(user.getId());//积分所属人Id
								// Integralsource is = new Integralsource();
								// List<Integralsource> islist = new
								// ArrayList<Integralsource>();
								// is
								// .setAddintegral(wage.getJiangjin()
								// .intValue());// 添加的积分
								// is.setAddtime(Util.getDateTime());// 添加时间
								// is.setLaiyuan("奖金分配");
								// islist.add(is);
								// integral.setIsList(islist);
								// String msg = IntegralServerDaoImpl
								// .addIntegral1(integral);
								// if ("true".equals(msg)) {
								// successMessage += "员工: "
								// + teammembers
								// .getTeammembersteamname()
								// + " 的积分信息处理成功!<br/>";
								// }
								// else {
								// successMessage += "员工: "
								// + teammembers
								// .getTeammembersteamname()
								// + msg + "<br/>";
								// }

							}
						} else {
							message += "不存在员工" + teammembers.getTeammembersteamname() + "的奖金分配信息,请核查!<br/>";
						}
					} else {
						message += "员工 " + teammembers.getTeammembersteamname() + "所在班组的奖金总额尚未审核完成!请等待!<br/>";
					}
				} else {
					message += "不存在员工" + teammembers.getTeammembersteamname() + "的工资模板信息,请添加!<br/>";
				}

			}
		} else {
			message = "班组中没有成员,请添加成员!";
		}
		return successMessage + message;
	}

	/***
	 * 添加离职人员工资
	 * 
	 * @param wage
	 * @return
	 */
	@Override
	public boolean addLeaveWage(Wage wage) {
		if (wage != null) {
			Users loginUser = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
			Float yingfagongzi = wage.getGangweigongzi() + wage.getDianhuabutie() + wage.getBaomijintie()
					+ wage.getJixiaokaohegongzi() + wage.getLeaveBuchang();// 应发工资计算
			wage.setShuidianfei(0F);// 水电费
			wage.setAddDateTime(Util.getDateTime(null));// 添加时间
			wage.setWageStatus("添加变动");
			wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 添加时间
			wage.setAddUserName(loginUser.getName());// 添加人员名称
			wage.setAddUserId(loginUser.getId());// 添加人id
			wage.setWageStatue("正常");// 人员状态
			wage.setYingfagongzi(yingfagongzi);// 应发工资
			wage.setYingjiaoshuijin(0F);// 应交税金
			wage.setShifagongzi(0F);// 实发工资
			return totalDao.save(wage);
		}
		return false;
	}

	/***
	 * 部门奖金分配
	 * 
	 * @param code
	 *            [] 工号
	 * @param money
	 *            [] 奖金
	 * @param status
	 * @return
	 */
	public String addBumenBonus(String[] code, Float[] money, String status) {
		boolean bool = false;
		String message = "";
		Users user = null;
		ContractBonus contractBonus = null;
		ContractBonus blContractBonus = null;
		Float sumMony = 0F;
		Float totalMoney = 0F;
		if (code != null && code.length > 0 && money != null && money.length > 0) {
			// 根据登录用户id查询出上个月的承包奖金总额
			user = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
			contractBonus = contractBonusServer.findCBByMouthAndDept(user.getId(), "", "同意");
			blContractBonus = contractBonusServer.findCBByMouthAndDept(user.getId(), null, "部门部留");
			if (contractBonus != null) {
				// 计算分配的总金额
				for (int i = 0; i < money.length; i++) {
					if (money[i] != null)
						sumMony += money[i];
				}
				if (blContractBonus == null) {
					totalMoney = contractBonus.getTotalMoney();
				} else {
					totalMoney = blContractBonus.getTotalMoney();
				}
				// 判断分配金额是否过大
				if (sumMony > totalMoney) {
					return "对不起!您本次分配奖金总额为" + sumMony + "元,大于您所能分配的最大部门奖金总额:" + totalMoney + "元!请您重新分配!谢谢!";
				}
				// 更新工资模版的绩效工资
				for (int i = 0; i < code.length; i++) {
					if (money[i] != null) {
						WageStandard ws = wsServer.findWSByUser(code[i]);// 查询员工默认的工资模板
						if (ws != null) {
							ws.setJixiaokaohegongzi(money[i]);
							bool = totalDao.update(ws);
						} else {
							message += "工号为" + code[i] + " 的工资模版不存在,无法分配!请检查!<br/>";
						}
					}
				}
				// 处理部留金额
				if (bool && sumMony > 0) {
					if (blContractBonus != null) {
						blContractBonus.setTotalMoney(totalMoney - sumMony);
						blContractBonus.setAddDate(Util.getDateTime("yyyy-MM-dd hh:ss:mm"));// 添加时间
					} else {
						blContractBonus = new ContractBonus();
						blContractBonus.setUserId(contractBonus.getUserId());// 用户id
						blContractBonus.setCode(contractBonus.getCode());// 工号
						blContractBonus.setCardId(contractBonus.getCardId());// 卡号
						blContractBonus.setUserName(contractBonus.getUserName());// 用户名称
						blContractBonus.setDeptName(contractBonus.getDeptName());// 部门名称
						blContractBonus.setBonusMouth(Util.getLastMonth("yyyy-MM月"));// 月份
						blContractBonus.setAddDate(Util.getDateTime("yyyy-MM-dd hh:ss:mm"));// 添加时间
						blContractBonus.setBonusStatus("部门部留");
						blContractBonus.setTotalMoney(totalMoney - sumMony);// 部留=总额-分配额
						blContractBonus.setStatus(contractBonus.getStatus());// bumen/chengbao
						bool = totalDao.save(blContractBonus);// 添加部留
					}
					if (bool) {
						return "您的奖金分配成功!";
					}
				}
			} else {
				return "不存在您的部门奖金总额,无法分配奖金!";
			}
		}
		return message;
	}

	// 添加试用人员工资
	@SuppressWarnings("unchecked")
	public String addTryPeople() {
		String hql = "from WageStandard  where code in (select code from Users where onWork in('试用','实习')) and standardStatus='默认'";
		List list = totalDao.query(hql);
		if (list != null && list.size() > 0) {
			String message = "success";
			String mouth = Util.getLastMonth("yyyy-MM月");
			String hql2 = "from Users where code=?";
			for (int i = 0; i < list.size(); i++) {
				WageStandard ws = (WageStandard) list.get(i);
				try {
					// 查询该成员信息所对应的User
					Users tryUser = (Users) totalDao.getObjectByCondition(hql2, ws.getCode());
					Wage oldWage = findWageByCodeAndCardId(ws.getCode(), ws.getCardId(), mouth);// 通过工号以及月份查询工资信息
					if (oldWage != null) {
						if (oldWage.getWageClass().equals("试用工资")) {
							continue;
						}
						delWage(oldWage);
					}

					Wage wage = new Wage();
					wage.setCode(ws.getCode());// 工号
					wage.setCardId(ws.getCardId());// 卡号
					wage.setUserName(ws.getUserName());// 员工名称
					wage.setDept(ws.getDept());// 所属部门
					wage.setMouth(Util.getLastMonth("yyyy-MM月"));// 发放月份
					wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资(固)
					wage.setBaomijintie(ws.getBaomijintie());// 保密津贴(固)
					wage.setDianhuabutie(ws.getDianhuabutie());// 电话补贴(固)
					wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)
					wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)
					wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)
					wage.setGongjijin(-ws.getGongjijin());// 公积金(固)
					wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
					wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
					wage.setJixiaokaohegongzi(ws.getJixiaokaohegongzi());// 绩效考核

					wage.setYingfagongzi(ws.getYingfagongzi() + ws.getJixiaokaohegongzi());// 应发工资
					wage.setWageStatus("添加变动");// 工资状态
					wage.setAddDateTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 填写时间
					wage.setIsBucha(ws.getBucha());// 是否补差
					wage.setFangzufei(ws.getFangzufei());// 房租费

					// 为别的项设置为0
					wage.setJiangjin(0F);// 奖金
					wage.setJiabanfei(0F);// 加班费
					wage.setOther(0F);// 其他
					wage.setBingshikangdeng(0F);// 病事旷等
					wage.setWucanfei(0F);// 午餐费
					wage.setShuidianfei(0F);// 水电费
					wage.setBfgongzi(0F);// 补发工资
					wage.setYingjiaoshuijin(0F);// 应交税金
					wage.setShifagongzi(0F);// 实发工资

					Users loginUser = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
					wage.setAddUserName(loginUser.getName());// 添加人员名称
					wage.setAddUserId(loginUser.getId());// 添加人id
					wage.setWageClass("试用工资");// 工资类别
					wage.setWageStatue("正常");// 人员状态
					wage.setUserId(tryUser.getId());// 工资所属人id
					totalDao.save(wage);
				} catch (Exception e1) {
					e1.printStackTrace();
					message += "处理" + ws.getUserName() + "的工资出错!";
				}
			}
			return message;
		} else {
			return null;
		}
	}

	// 部留处理
	private boolean updateblContractBonus(int userId, Float money) {
		ContractBonus blContractBonus = contractBonusServer.findCBByMouthAndDept(userId, Util.getLastMonth("yyyy-MM月"),
				"承包部留");
		boolean bool = false;
		if (blContractBonus != null) {
			blContractBonus.setTotalMoney(blContractBonus.getTotalMoney() - money);
			blContractBonus.setAddDate(Util.getDateTime("yyyy-MM-dd hh:ss:mm"));// 添加时间
			bool = totalDao.update(blContractBonus);// 更新部留
		} else {
			ContractBonus contractBonus = contractBonusServer.findCBByMouthAndDept(userId,
					Util.getLastMonth("yyyy-MM月"), "同意");
			ContractBonus newContractBonus = new ContractBonus();
			newContractBonus.setUserId(contractBonus.getUserId());// 用户id
			newContractBonus.setCode(contractBonus.getCode());// 工号
			newContractBonus.setCardId(contractBonus.getCardId());// 卡号
			newContractBonus.setUserName(contractBonus.getUserName());// 用户名称
			newContractBonus.setDeptName(contractBonus.getDeptName());// 部门名称
			newContractBonus.setBonusMouth(contractBonus.getBonusMouth());// 月份
			newContractBonus.setAddDate(Util.getDateTime("yyyy-MM-dd hh:ss:mm"));// 添加时间
			newContractBonus.setBonusStatus("承包部留");
			newContractBonus.setTotalMoney(contractBonus.getTotalMoney() - money);// 部留=总额-分配额
			newContractBonus.setStatus("chengbao");
			bool = totalDao.save(newContractBonus);// 添加部留
		}
		return bool;
	}

	// 通过工号以及月份查询工资信息
	@SuppressWarnings("unchecked")
	public Wage findWageByCodeAndCardId(String code, String cardId, String mouth) {
		if (code != null && code.length() > 0) {
			String hql = "from Wage where code=? and mouth=?";
			List list = totalDao.query(hql, code, mouth);
			if (list != null && list.size() > 0) {
				return (Wage) list.get(0);
			}
		}
		return null;
	}

	/***
	 * 通过登录人员工号查询所属成员的工资信息
	 * 
	 * @param duty
	 *            职位
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] findWageByMouthAndDept(String code, int pageNo, int pageSize) {
		Users user = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
		String hql = "from Wage where code in (select code from AssessPersonnel where addUserId=?) order by addDateTime desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize, user.getId());
		int count = totalDao.getCount(hql, user.getId());
		return new Object[] { list, count };
	}

	// 删除工资
	public boolean delWage(Wage wage) {
		if (wage != null) {
			return totalDao.delete(wage);
		}
		return false;
	}

	// 导出工资条/工资单
	@SuppressWarnings("unchecked")
	public String exportWageArticle(Wage pageWage, String articleOrSingle, String wageStatus) {
		if (articleOrSingle != null && wageStatus != null && articleOrSingle.length() > 0 && wageStatus.length() > 0) {
			String exportMouth = "";// 导出月份
			String noDept = "";// 无需导出部门
			String username = "";// 员工名称
			if (pageWage != null) {
				exportMouth = pageWage.getMouth();
				noDept = pageWage.getDept();
				username = pageWage.getUserName();
			}
			if ("jixiao".equals(wageStatus)) {
				wageStatus = "添加变动";
				if (exportMouth != null && exportMouth.length() > 0) {
					String upDate = exportMouth;
					int yy = Integer.parseInt(upDate.substring(0, 4));
					int mm = Integer.parseInt(upDate.substring(5, 7));
					String other = upDate.substring(7);
					if (mm == 1) {
						mm = 12;
						yy--;
					} else {
						mm--;
					}
					if (mm < 10) {
						upDate = yy + "-0" + mm + other;
					} else {
						upDate = yy + "-" + mm + other;
					}

					String hql1 = "from Wage where mouth=? and wageStatus not in ('同意','审核') ";
					int count = totalDao.getCount(hql1, upDate);
					if (count > 0) {
						return upDate + " 有" + count + "条工资信息尚未处理!请先处理该月工资后才能导出!谢谢!";
					}
				} else {
					String hql1 = "from Wage where wageStatus not in ('同意','审核')";
					int count = totalDao.getCount(hql1);
					if (count > 0) {
						return "系统中存在" + count + "条工资信息未处理!请先处理该月工资后才能导出!谢谢!";
					}
				}

			} else if ("check".equals(wageStatus)) {
				wageStatus = "自查";
			} else if ("ass".equals(wageStatus)) {
				wageStatus = "同意";
			} else {
				return "非法字符!";
			}
			String hql = "";
			List list = null;
			if (noDept != null && noDept.length() > 0) {
				String[] noDepts = null;
				noDept = noDept.replaceAll("，", ",");
				if (noDept != null & noDept.length() > 0) {
					noDepts = noDept.split(",");
				}
				noDept = "";
				for (String dept : noDepts) {
					noDept += "'" + dept + "',";
				}
				noDept = noDept.substring(0, noDept.lastIndexOf(","));
			} else {
				noDept = "''";
			}
			hql = "from Wage where  wageStatus=? and  dept not in (" + noDept + ")";
			if (exportMouth != null && exportMouth.length() > 0) {
				hql += " and mouth='" + exportMouth + "'";
			}
			if (username != null && username.length() > 0) {
				hql += " and userName='" + username + "'";
			}
			list = totalDao.query(hql + " order by mouth,dept", wageStatus);
			String fileName = "error";
			if (list != null && list.size() > 0) {
				if ("wageArticle".equals(articleOrSingle)) {// 工资条
					// // 删除所有已生成的文件
					// String excelRealPath = ServletActionContext
					// .getServletContext().getRealPath("/upload/sheet")
					// + "/wage/";
					// File folder = new File(excelRealPath);
					// String filesName[] = folder.list();
					// for (int i = 0; i < filesName.length; i++) {
					// File file = new File(excelRealPath + "\\"
					// + filesName[i]);
					// if (!file.isDirectory()) {
					// file.delete();
					// }
					// }
					// 开始生成工资条
					fileName = Util.getDateTime("yyyyMMddhhmmss") + "wageArticle.xls";
					String excelRealPath2 = ServletActionContext.getServletContext().getRealPath("/upload/sheet")
							+ "/wage/" + fileName;
					File target2 = new File(excelRealPath2);
					try {
						WritableWorkbook writeBook;
						writeBook = Workbook.createWorkbook(target2);
						WritableSheet sheet = writeBook.createSheet(exportMouth + "工资条", 0);
						Label label0 = new Label(0, 0, "员工号");
						Label label1 = new Label(1, 0, "姓名");
						Label label2 = new Label(2, 0, "性别");
						Label label3 = new Label(3, 0, "部门");
						Label label4 = new Label(4, 0, "职位");
						Label label5 = new Label(5, 0, "岗位级别");
						Label label6 = new Label(6, 0, "岗位性质");
						Label label7 = new Label(7, 0, "工作领域");
						Label label8 = new Label(8, 0, "文化程度");
						Label label9 = new Label(9, 0, "职称&职级");
						Label label10 = new Label(10, 0, "岗位工资");
						Label label11 = new Label(11, 0, "保密津贴");
						Label label12 = new Label(12, 0, "补贴");
						Label label13 = new Label(13, 0, "技能工资");
						Label label14 = new Label(14, 0, "特殊补贴");
						Label label15 = new Label(15, 0, "奖金");
						Label label16 = new Label(16, 0, "绩效考核工资");
						Label label17 = new Label(17, 0, "加班费");
						Label label18 = new Label(18, 0, "应发工资");
						Label label19 = new Label(19, 0, "其他");
						Label label20 = new Label(20, 0, "病事旷等");
						Label label21 = new Label(21, 0, "养老保险");
						Label label22 = new Label(22, 0, "医疗保险");
						Label label23 = new Label(23, 0, "失业保险");
						Label label24 = new Label(24, 0, "公积金");
						Label label25 = new Label(25, 0, "午餐费");
						Label label26 = new Label(26, 0, "水电费");
						Label label27 = new Label(27, 0, "房租费");
						Label label28 = new Label(28, 0, "补发(补扣)工资");
						Label label29 = new Label(29, 0, "应交税金");
						Label label30 = new Label(30, 0, "实发工资");
						Label label31 = new Label(31, 0, "补差工资");
						Label label32 = new Label(32, 0, "离职补偿");
						Label label33 = new Label(33, 0, "月份");
						Label label34 = new Label(34, 0, "养老保险");
						Label label35 = new Label(35, 0, "医疗保险");
						Label label36 = new Label(36, 0, "失业保险");
						Label label37 = new Label(37, 0, "工伤保险");
						Label label38 = new Label(38, 0, "生育保险");
						Label label39 = new Label(39, 0, "公积金");
						Label label40 = new Label(40, 0, "身份证号");
						Label label41 = new Label(41, 0, "手机号");
						Label label42 = new Label(42, 0, "银行卡号");

						sheet.addCell(label0);
						sheet.addCell(label1);
						sheet.addCell(label2);
						sheet.addCell(label3);
						sheet.addCell(label4);
						sheet.addCell(label5);
						sheet.addCell(label6);
						sheet.addCell(label7);
						sheet.addCell(label8);
						sheet.addCell(label9);
						sheet.addCell(label10);
						sheet.addCell(label11);
						sheet.addCell(label12);
						sheet.addCell(label13);
						sheet.addCell(label14);
						sheet.addCell(label15);
						sheet.addCell(label16);
						sheet.addCell(label17);
						sheet.addCell(label18);
						sheet.addCell(label19);
						sheet.addCell(label20);
						sheet.addCell(label21);
						sheet.addCell(label22);
						sheet.addCell(label23);
						sheet.addCell(label24);
						sheet.addCell(label25);
						sheet.addCell(label26);
						sheet.addCell(label27);
						sheet.addCell(label28);
						sheet.addCell(label29);
						sheet.addCell(label30);
						sheet.addCell(label31);
						sheet.addCell(label32);
						sheet.addCell(label33);
						sheet.addCell(label34);
						sheet.addCell(label35);
						sheet.addCell(label36);
						sheet.addCell(label37);
						sheet.addCell(label38);
						sheet.addCell(label39);
						sheet.addCell(label40);
						sheet.addCell(label41);
						sheet.addCell(label42);

						for (int i = 1, len = list.size(); i <= len; i++) {
							// 保留两位小数，
							NumberFormat fivedps1 = new NumberFormat("0.00");
							WritableCellFormat fivedpsFormat1 = new WritableCellFormat(fivedps1);

							Wage wage = (Wage) list.get(i - 1);
							Users user = null;
							if (wage != null) {
								user = (Users) totalDao.getObjectByCondition("from Users where code=?", wage.getCode());
								if (user == null) {
									continue;
								}
							} else {
								continue;
							}

							Label contentLabel0 = new Label(0, i, user.getCode());
							Label contentLabel1 = new Label(1, i, user.getName());
							Label contentLabel2 = new Label(2, i, user.getSex());
							Label contentLabel3 = new Label(3, i, user.getDept());
							Label contentLabel4 = new Label(4, i, user.getDuty());
							Label contentLabel5 = new Label(5, i, "");// 岗位级别
							Label contentLabel6 = new Label(6, i, "");// 岗位性质
							Label contentLabel7 = new Label(7, i, "");// 工作领域
							Label contentLabel8 = new Label(8, i, user.getEducation());// 文化程度
							Label contentLabel9 = new Label(9, i, user.getJobtitle() == null ? ""
									: user.getJobtitle() + user.getLeveltitles() == null ? "" : user.getLeveltitles());// 职称+级别

							jxl.write.Number contentLabel10 = new jxl.write.Number(10, i,
									Float.parseFloat(String.format("%.2f", wage.getGangweigongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel11 = new jxl.write.Number(11, i,
									Float.parseFloat(String.format("%.2f", wage.getBaomijintie())), fivedpsFormat1);
							jxl.write.Number contentLabel12 = new jxl.write.Number(12, i,
									Float.parseFloat(String.format("%.2f", wage.getDianhuabutie())), fivedpsFormat1);
							jxl.write.Number contentLabel13 = new jxl.write.Number(13, i,
									Float.parseFloat(String.format("%.2f", wage.getJinenggongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel14 = new jxl.write.Number(14, i,
									Float.parseFloat(String.format("%.2f", wage.getGonglinggongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel15 = new jxl.write.Number(15, i,
									Float.parseFloat(String.format("%.2f", wage.getJiangjin())), fivedpsFormat1);
							jxl.write.Number contentLabel16 = new jxl.write.Number(16, i,
									Float.parseFloat(String.format("%.2f", wage.getJixiaokaohegongzi())),
									fivedpsFormat1);
							jxl.write.Number contentLabel17 = new jxl.write.Number(17, i,
									Float.parseFloat(String.format("%.2f", wage.getJiabanfei())), fivedpsFormat1);
							jxl.write.Number contentLabel18 = new jxl.write.Number(18, i,
									Float.parseFloat(String.format("%.2f", wage.getYingfagongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel19 = new jxl.write.Number(19, i,
									Float.parseFloat(String.format("%.2f", wage.getOther())), fivedpsFormat1);
							jxl.write.Number contentLabel20 = new jxl.write.Number(20, i,
									Float.parseFloat(String.format("%.2f", wage.getBingshikangdeng())), fivedpsFormat1);
							jxl.write.Number contentLabel21 = new jxl.write.Number(21, i,
									Float.parseFloat(String.format("%.2f", wage.getTongchoujin())), fivedpsFormat1);
							jxl.write.Number contentLabel22 = new jxl.write.Number(22, i,
									Float.parseFloat(String.format("%.2f", wage.getYiliaobaoxian())), fivedpsFormat1);
							jxl.write.Number contentLabel23 = new jxl.write.Number(23, i,
									Float.parseFloat(String.format("%.2f", wage.getShiyebaoxian())), fivedpsFormat1);
							jxl.write.Number contentLabel24 = new jxl.write.Number(24, i,
									Float.parseFloat(String.format("%.2f", wage.getGongjijin())), fivedpsFormat1);
							jxl.write.Number contentLabel25 = new jxl.write.Number(25, i,
									Float.parseFloat(String.format("%.2f", wage.getWucanfei())), fivedpsFormat1);
							jxl.write.Number contentLabel26 = new jxl.write.Number(26, i,
									Float.parseFloat(String.format("%.2f", wage.getShuidianfei())), fivedpsFormat1);
							jxl.write.Number contentLabel27 = new jxl.write.Number(27, i,
									Float.parseFloat(String.format("%.2f", wage.getFangzufei())), fivedpsFormat1);
							jxl.write.Number contentLabel28 = new jxl.write.Number(28, i,
									Float.parseFloat(String.format("%.2f", wage.getBfgongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel29 = new jxl.write.Number(29, i,
									Float.parseFloat(String.format("%.2f", wage.getYingjiaoshuijin())), fivedpsFormat1);
							jxl.write.Number contentLabel30 = new jxl.write.Number(30, i,
									Float.parseFloat(String.format("%.2f", wage.getShifagongzi())), fivedpsFormat1);
							jxl.write.Number contentLabel31 = new jxl.write.Number(31, i,
									wage.getBuchagongzi() == null ? 0F
											: Float.parseFloat(String.format("%.2f", wage.getBuchagongzi())),
									fivedpsFormat1);
							jxl.write.Number contentLabel32 = new jxl.write.Number(32, i,
									wage.getLeaveBuchang() == null ? 0F
											: Float.parseFloat(String.format("%.2f",
													wage.getLeaveBuchang() != null ? wage.getLeaveBuchang() : 0F)),
									fivedpsFormat1);
							Label contentLabel33 = new Label(33, i, wage.getMouth());
							jxl.write.Number contentLabel34 = new jxl.write.Number(34, i,
									wage.getDwtongchoujin() == null ? 0F
											: Float.parseFloat(String.format("%.2f",
													wage.getDwtongchoujin() != null ? wage.getDwtongchoujin() : 0F)),
									fivedpsFormat1);
							jxl.write.Number contentLabel35 = new jxl.write.Number(35,
									i, wage
											.getDwyiliaobaoxian() == null
													? 0F
													: Float.parseFloat(
															String.format("%.2f",
																	wage.getDwyiliaobaoxian() != null
																			? wage.getDwyiliaobaoxian() : 0F)),
									fivedpsFormat1);
							jxl.write.Number contentLabel36 = new jxl.write.Number(36, i,
									wage.getDwshiyebaoxian() == null ? 0F
											: Float.parseFloat(String.format("%.2f",
													wage.getDwshiyebaoxian() != null ? wage.getDwshiyebaoxian() : 0F)),
									fivedpsFormat1);
							jxl.write.Number contentLabel37 = new jxl.write.Number(37, i,
									wage.getDwgongshangbaoxian() == null ? 0F
											: Float.parseFloat(
													String.format("%.2f",
															wage.getDwgongshangbaoxian() != null
																	? wage.getDwgongshangbaoxian() : 0F)),
									fivedpsFormat1);
							jxl.write.Number contentLabel38 = new jxl.write.Number(38, i,
									wage.getDwshengyubaoxian() == null ? 0F
											: Float.parseFloat(
													String.format("%.2f",
															wage.getDwshengyubaoxian() != null
																	? wage.getDwshengyubaoxian() : 0F)),
									fivedpsFormat1);
							jxl.write.Number contentLabel39 = new jxl.write.Number(39, i,
									wage.getDwgongjijin() == null ? 0F
											: Float.parseFloat(String.format("%.2f",
													wage.getDwgongjijin() != null ? wage.getDwgongjijin() : 0F)),
									fivedpsFormat1);
							Label contentLabel40 = new Label(40, i, user.getUid());
							Label contentLabel41 = new Label(41, i, user.getPassword().getPhoneNumber());
							Label contentLabel42 = new Label(42, i, user.getBankCards());

							sheet.addCell(contentLabel0);
							sheet.addCell(contentLabel1);
							sheet.addCell(contentLabel2);
							sheet.addCell(contentLabel3);
							sheet.addCell(contentLabel4);
							sheet.addCell(contentLabel5);
							sheet.addCell(contentLabel6);
							sheet.addCell(contentLabel7);
							sheet.addCell(contentLabel8);
							sheet.addCell(contentLabel9);
							sheet.addCell(contentLabel10);
							sheet.addCell(contentLabel11);
							sheet.addCell(contentLabel12);
							sheet.addCell(contentLabel13);
							sheet.addCell(contentLabel14);
							sheet.addCell(contentLabel15);
							sheet.addCell(contentLabel16);
							sheet.addCell(contentLabel17);
							sheet.addCell(contentLabel18);
							sheet.addCell(contentLabel19);
							sheet.addCell(contentLabel20);
							sheet.addCell(contentLabel21);
							sheet.addCell(contentLabel22);
							sheet.addCell(contentLabel23);
							sheet.addCell(contentLabel24);
							sheet.addCell(contentLabel25);
							sheet.addCell(contentLabel26);
							sheet.addCell(contentLabel27);
							sheet.addCell(contentLabel28);
							sheet.addCell(contentLabel29);
							sheet.addCell(contentLabel30);
							sheet.addCell(contentLabel31);
							sheet.addCell(contentLabel32);
							sheet.addCell(contentLabel33);
							sheet.addCell(contentLabel34);
							sheet.addCell(contentLabel35);
							sheet.addCell(contentLabel36);
							sheet.addCell(contentLabel37);
							sheet.addCell(contentLabel38);
							sheet.addCell(contentLabel39);
							sheet.addCell(contentLabel40);
							sheet.addCell(contentLabel41);
							sheet.addCell(contentLabel42);
						}
						writeBook.write();
						writeBook.close();
					} catch (Exception e) {
						e.printStackTrace();
						fileName = "导出工资条时出错,请检查后重试!";
					}
				} else if ("wageff".equals(articleOrSingle)) {// 工资条--发放表
					// // 删除所有已生成的文件
					// String excelRealPath = ServletActionContext
					// .getServletContext().getRealPath("/upload/sheet")
					// + "/wage/";
					// File folder = new File(excelRealPath);
					// String filesName[] = folder.list();
					// for (int i = 0; i < filesName.length; i++) {
					// File file = new File(excelRealPath + "\\"
					// + filesName[i]);
					// if (!file.isDirectory()) {
					// file.delete();
					// }
					// }
					// 开始生成工资条
					fileName = Util.getDateTime("yyyyMMddhhmmss") + "wageff.xls";
					String excelRealPath2 = ServletActionContext.getServletContext().getRealPath("/upload/sheet")
							+ "/wage/" + fileName;
					File target2 = new File(excelRealPath2);
					try {
						WritableWorkbook writeBook;
						writeBook = Workbook.createWorkbook(target2);
						WritableSheet sheet = writeBook.createSheet(exportMouth + "工资发放表", 0);
						Label label0 = new Label(0, 0, "员工号");
						Label label1 = new Label(1, 0, "姓名");
						Label label4 = new Label(2, 0, "身份证号");
						Label label5 = new Label(3, 0, "银行号");
						Label label6 = new Label(4, 0, "手机号");
						Label label30 = new Label(5, 0, "实发工资");

						sheet.addCell(label0);
						sheet.addCell(label1);
						sheet.addCell(label4);
						sheet.addCell(label5);
						sheet.addCell(label6);
						sheet.addCell(label30);

						for (int i = 1, len = list.size(); i <= len; i++) {
							// 保留两位小数，
							NumberFormat fivedps1 = new NumberFormat("0.00");
							WritableCellFormat fivedpsFormat1 = new WritableCellFormat(fivedps1);

							Wage wage = (Wage) list.get(i - 1);
							Users user = null;
							if (wage != null) {
								user = (Users) totalDao.getObjectByCondition("from Users where code=?", wage.getCode());
								if (user == null) {
									continue;
								}
							} else {
								continue;
							}

							Label contentLabel0 = new Label(0, i, user.getCode());
							Label contentLabel1 = new Label(1, i, user.getName());
							Label contentLabel4 = new Label(2, i, user.getUid());
							Label contentLabel5 = new Label(3, i, user.getBankCards());// 银行卡号
							Label contentLabel6;
							try {
								contentLabel6 = new Label(4, i, user.getPassword().getPhoneNumber());
							} catch (Exception e) {
								e.printStackTrace();
								contentLabel6 = new Label(4, i, "");
							}
							jxl.write.Number contentLabel30 = new jxl.write.Number(5, i,
									Float.parseFloat(String.format("%.2f", wage.getShifagongzi())), fivedpsFormat1);

							sheet.addCell(contentLabel0);
							sheet.addCell(contentLabel1);
							sheet.addCell(contentLabel4);
							sheet.addCell(contentLabel5);
							sheet.addCell(contentLabel6);
							sheet.addCell(contentLabel30);
						}

						// int i = list.size()+1;
						// // 保留两位小数，
						// NumberFormat fivedps1 = new NumberFormat("0.00");
						// WritableCellFormat fivedpsFormat1 = new
						// WritableCellFormat(
						// fivedps1);
						// Label contentLabel0 = new Label(0, i, "");
						// Label contentLabel1 = new Label(1, i, "周小明");
						// Label contentLabel2 = new Label(2, i,
						// "320523196609043312");
						// Label contentLabel3 = new Label(3,
						// i,"6222600110040508173");
						// Label contentLabel4 = new Label(4, i, "18019438255");
						// jxl.write.Number contentLabel30 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 2300F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel0);
						// sheet.addCell(contentLabel1);
						// sheet.addCell(contentLabel2);
						// sheet.addCell(contentLabel3);
						// sheet.addCell(contentLabel4);
						// sheet.addCell(contentLabel30);
						//
						// i++;
						// Label contentLabel02 = new Label(0, i, "");
						// Label contentLabel12 = new Label(1, i, "王亚琴");
						// Label contentLabel22 = new Label(2, i,
						// "320523196904193321");
						// Label contentLabel32 = new Label(3,
						// i,"6222600110040507571");
						// Label contentLabel42 = new Label(4, i,
						// "18962655175");
						// jxl.write.Number contentLabel302 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 2300F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel02);
						// sheet.addCell(contentLabel12);
						// sheet.addCell(contentLabel22);
						// sheet.addCell(contentLabel32);
						// sheet.addCell(contentLabel42);
						// sheet.addCell(contentLabel302);
						//
						// i++;
						// Label contentLabel03 = new Label(0, i, "");
						// Label contentLabel13 = new Label(1, i, "邱萍");
						// Label contentLabel23 = new Label(2, i,
						// "31022219681119382X");
						// Label contentLabel33 = new Label(3,
						// i,"6222600110040507589");
						// Label contentLabel43 = new Label(4, i,
						// "13661751759");
						// jxl.write.Number contentLabel303 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 2300F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel03);
						// sheet.addCell(contentLabel13);
						// sheet.addCell(contentLabel23);
						// sheet.addCell(contentLabel33);
						// sheet.addCell(contentLabel43);
						// sheet.addCell(contentLabel303);
						//
						// i++;
						// Label contentLabel04 = new Label(0, i, "");
						// Label contentLabel14 = new Label(1, i, "陈菊英");
						// Label contentLabel24 = new Label(2, i,
						// "310114197711301044");
						// Label contentLabel34 = new Label(3,
						// i,"6222600110040508702");
						// Label contentLabel44 = new Label(4, i,
						// "17701876008");
						// jxl.write.Number contentLabel304 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 2300F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel04);
						// sheet.addCell(contentLabel14);
						// sheet.addCell(contentLabel24);
						// sheet.addCell(contentLabel34);
						// sheet.addCell(contentLabel44);
						// sheet.addCell(contentLabel304);
						//
						// i++;
						// Label contentLabel05 = new Label(0, i, "");
						// Label contentLabel15 = new Label(1, i, "章菁");
						// Label contentLabel25 = new Label(2, i,
						// "522526197609203622");
						// Label contentLabel35 = new Label(3,
						// i,"6222600110040508710");
						// Label contentLabel45 = new Label(4, i,
						// "18019748726");
						// jxl.write.Number contentLabel305 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 2300F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel05);
						// sheet.addCell(contentLabel15);
						// sheet.addCell(contentLabel25);
						// sheet.addCell(contentLabel35);
						// sheet.addCell(contentLabel45);
						// sheet.addCell(contentLabel305);
						//
						// i++;
						// Label contentLabel06 = new Label(0, i, "");
						// Label contentLabel16 = new Label(1, i, "才**");
						// Label contentLabel26 = new Label(2, i,
						// "522526197401207000");
						// Label contentLabel36 = new Label(3,
						// i,"6222600110040507167");
						// Label contentLabel46 = new Label(4, i,
						// "13701850429");
						// jxl.write.Number contentLabel306 = new
						// jxl.write.Number(
						// 5, i, Float.parseFloat(String.format(
						// "%.2f", 22046.08F)),
						// fivedpsFormat1);
						// sheet.addCell(contentLabel06);
						// sheet.addCell(contentLabel16);
						// sheet.addCell(contentLabel26);
						// sheet.addCell(contentLabel36);
						// sheet.addCell(contentLabel46);
						// sheet.addCell(contentLabel306);
						writeBook.write();
						writeBook.close();
					} catch (Exception e) {
						e.printStackTrace();
						fileName = "导出工资条时出错,请检查后重试!";
					}
				} else if ("wageSingle".equals(articleOrSingle)) {// 工资单
					fileName = Util.getDateTime("yyyyMMddhhmmss") + "wageSingle.xls";
					String excelRealPath2 = ServletActionContext.getServletContext().getRealPath("/upload/sheet")
							+ "/wage/" + fileName;
					File target2 = new File(excelRealPath2);

					WritableWorkbook writeBook;
					try {
						// ////////////////定义标题单元格样式1////////////////
						WritableFont wf = new WritableFont(WritableFont.ARIAL); // 定义格式
						WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
						wcf.setAlignment(jxl.format.Alignment.JUSTIFY); // 设置对齐方式
						wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);// 添加细边框样式
						// //////////////// 定义数据单元格样式2////////////////
						// 定义单元格样式 定义格式 字体 下划线 颜色 斜体 粗体
						WritableCellFormat wcf1 = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 10)); // 单元格定义

						// //////////////岗位工资到实发工资的样式(字体10 不加粗
						// 细边框)////////////////
						WritableFont wf2 = new WritableFont(WritableFont.ARIAL, 10); // 定义格式
						WritableCellFormat wcf2 = new WritableCellFormat(wf2); // 单元格定义
						wcf2.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
						wcf2.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);// 添加细边框样式
						wcf2.setWrap(true);// 是否自动换行

						// /////////////////////处理姓名、工号和部门样式(字体10，加粗)////////////////////////
						WritableFont wf3 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false,
								UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
						// 向上对齐
						WritableCellFormat wcf3 = new WritableCellFormat(wf3); // 单元格定义
						wcf3.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

						writeBook = Workbook.createWorkbook(target2);
						WritableSheet sheet = writeBook.createSheet("工资单", 0);
						for (int i = 0, len = list.size(); i < len; i++) {
							Wage wage = (Wage) list.get(i);
							int row = i * 8;// 行数

							/********************************** 上海红湖排气系统有限公司 *****************/
							WritableFont wfTitle = new WritableFont(WritableFont.ARIAL, 12);
							WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle); // 单元格定义
							wcfTitle.setAlignment(jxl.format.Alignment.CENTRE);
							sheet.addCell(new Label(0, row, "上海红湖排气系统有限公司", wcfTitle));
							sheet.mergeCells(0, row, 10, row);// 合并0到10列

							/********************************** 发放月份、姓名、工号、部门、编号 *****************/
							// 发放月份
							sheet.addCell(new Label(0, row + 1, wage.getMouth() + "工资单"));
							sheet.mergeCells(0, row + 1, 1, row + 1);// 合并单元格
							// 姓名
							sheet.addCell(new Label(2, row + 1, "姓名", wcf1));
							sheet.addCell(new Label(3, row + 1, wage.getUserName(), wcf3));
							// 工号
							sheet.addCell(new Label(4, row + 1, "工号", wcf1));
							sheet.addCell(new Label(5, row + 1, wage.getCode(), wcf3));
							// 部门
							sheet.addCell(new Label(6, row + 1, "部门", wcf1));
							sheet.addCell(new Label(7, row + 1, wage.getDept(), wcf3));
							// 编号
							String maxId = String.valueOf(i + 1);// 流水号
							if (maxId.length() == 1) {
								maxId = "00" + maxId;
							} else if (maxId.length() == 2) {
								maxId = "0" + maxId;
							}
							String deptNumber = exportMouth.substring(0, exportMouth.indexOf("月")) + "-" + maxId;
							// String deptNukmberHql =
							// "from DeptNumber where dept=?";// 根据部门名称查询部门编号
							// List deptNukmberlist = totalDao.query(
							// deptNukmberHql, wage.getDept());
							// if (deptNukmberlist != null
							// && deptNukmberlist.size() > 0) {
							// DeptNumber deptn = (DeptNumber) deptNukmberlist
							// .get(0);
							// deptNumber = deptn.getDeptNumber()
							// + exportMouth.substring(0, exportMouth
							// .indexOf("月")) + maxId;
							// } else {
							// deptNumber = "other"
							// + exportMouth.substring(0, exportMouth
							// .indexOf("月")) + maxId;
							// }
							sheet.addCell(new Label(8, row + 1, "编号", wcf1));
							sheet.addCell(new Label(9, row + 1, deptNumber, wcf3));
							sheet.mergeCells(9, row + 1, 10, row + 1);// 合并编号
							/********************************** 增加两行空白 *****************/
							sheet.addCell(new Label(0, row + 2, ""));
							sheet.addCell(new Label(0, row + 3, ""));
							/********************************** 工资信息前两行 *****************/
							sheet.addCell(new Label(0, row + 4, "岗位工资", wcf2));
							sheet.addCell(new Label(0, row + 5, String.valueOf(wage.getGangweigongzi()), wcf2));
							sheet.addCell(new Label(1, row + 4, "保密津贴", wcf2));
							sheet.addCell(new Label(1, row + 5, String.valueOf(wage.getBaomijintie()), wcf2));
							sheet.addCell(new Label(2, row + 4, "补贴", wcf2));
							sheet.addCell(new Label(2, row + 5, String.valueOf(wage.getDianhuabutie()), wcf2));
							sheet.addCell(new Label(3, row + 4, "绩效考核工资", wcf2));
							sheet.addCell(new Label(3, row + 5, String.valueOf(wage.getJixiaokaohegongzi()), wcf2));
							sheet.addCell(new Label(4, row + 4, "应发工资", wcf2));
							sheet.addCell(new Label(4, row + 5, String.valueOf(wage.getYingfagongzi()), wcf2));
							sheet.addCell(new Label(5, row + 4, "技能工资", wcf2));
							sheet.addCell(new Label(5, row + 5, String.valueOf(wage.getJinenggongzi()), wcf2));
							sheet.addCell(new Label(6, row + 4, "特殊补贴", wcf2));
							sheet.addCell(new Label(6, row + 5, String.valueOf(wage.getGonglinggongzi()), wcf2));
							sheet.addCell(new Label(7, row + 4, "奖金", wcf2));
							sheet.addCell(new Label(7, row + 5, String.valueOf(wage.getJiangjin()), wcf2));
							sheet.addCell(new Label(8, row + 4, "加班费", wcf2));
							sheet.addCell(new Label(8, row + 5, String.valueOf(wage.getJiabanfei()), wcf2));
							sheet.addCell(new Label(9, row + 4, "其他", wcf2));
							sheet.addCell(new Label(9, row + 5, String.valueOf(wage.getOther()), wcf2));
							sheet.addCell(new Label(10, row + 4, "病事旷等", wcf2));
							sheet.addCell(new Label(10, row + 5, String.valueOf(wage.getBingshikangdeng()), wcf2));

							sheet.setRowView(row + 4, 660); // 设置行的高度
							sheet.setRowView(row + 5, 660); // 设置行的高度

							/********************************** 工资信息后两行 *****************/
							sheet.addCell(new Label(0, row + 6, "养老保险", wcf2));
							sheet.addCell(new Label(0, row + 7, String.valueOf(wage.getTongchoujin()), wcf2));
							sheet.addCell(new Label(1, row + 6, "医疗保险", wcf2));
							sheet.addCell(new Label(1, row + 7, String.valueOf(wage.getYiliaobaoxian()), wcf2));
							sheet.addCell(new Label(2, row + 6, "失业保险", wcf2));
							sheet.addCell(new Label(2, row + 7, String.valueOf(wage.getShiyebaoxian()), wcf2));
							sheet.addCell(new Label(3, row + 6, "公积金", wcf2));
							sheet.addCell(new Label(3, row + 7, String.valueOf(wage.getGongjijin()), wcf2));
							sheet.addCell(new Label(4, row + 6, "午餐费", wcf2));
							sheet.addCell(new Label(4, row + 7, String.valueOf(wage.getWucanfei()), wcf2));
							sheet.addCell(new Label(5, row + 6, "补差", wcf2));
							sheet.addCell(new Label(5, row + 7,
									String.valueOf(wage.getBuchagongzi() == null ? 0 : wage.getBuchagongzi()), wcf2));
							sheet.addCell(new Label(6, row + 6, "房租费", wcf2));
							sheet.addCell(new Label(6, row + 7, String.valueOf(wage.getFangzufei()), wcf2));
							sheet.addCell(new Label(7, row + 6, "补发(补扣)工资", wcf2));
							sheet.addCell(new Label(7, row + 7, String.valueOf(wage.getBfgongzi()), wcf2));
							sheet.addCell(new Label(8, row + 6, "应交税金", wcf2));
							sheet.addCell(new Label(8, row + 7, String.valueOf(wage.getYingjiaoshuijin()), wcf2));
							sheet.addCell(new Label(9, row + 6, "实发工资", wcf2));
							sheet.addCell(new Label(9, row + 7, String.valueOf(wage.getShifagongzi()), wcf2));
							sheet.mergeCells(9, row + 6, 10, row + 6);// 合并实发工资
							sheet.mergeCells(9, row + 7, 10, row + 7);// 合并实发工资

							sheet.setRowView(row + 6, 660); // 设置行的高度
							sheet.setRowView(row + 7, 660); // 设置行的高度
						}
						writeBook.write();
						writeBook.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					fileName = "非法字符!";
				}

			} else {
				fileName = "抱歉,您所查询时间段内,没有数据!请检查后重试!";
			}
			return fileName;
		}
		return "error";
	}

	// 查询所有工资(分页)
	@SuppressWarnings("unchecked")
	public Object[] findAllWage(int pageNo, int pageSize, String status, Wage wage) {
		if ((Object) pageNo != null && (Object) pageSize != null) {
			if (wage == null) {
				wage = new Wage();
			}
			String hql = totalDao.criteriaQueries(wage, null);
			if ("chage".equals(status)) {
				hql += " and wageStatus in ('添加变动','打回','自查')  order by addDateTime desc";
			} else if ("audit".equals(status) || "findAudit".equals(status)) {
				hql += " and wageStatus in ('审核','承包审核','绩效审核') order by shifagongzi";
			} else if ("auditHistory".equals(status)) {
				hql += " and wageStatus not in ('添加变动','审核') order by addDateTime desc";
			} else {
				hql += " order by mouth desc";
			}
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 条件查询工资(分页)
	@SuppressWarnings("unchecked")
	public Object[] findWageByCondition(Wage wage, int pageNo, int pageSize, String ststus) {
		if (wage != null && (Object) pageNo != null && (Object) pageSize != null) {
			String hql = totalDao.criteriaQueries(wage, null);
			if (hql.indexOf(" where ") < 0) {
				hql += " where ";
			} else {
				hql += " and ";
			}
			if (ststus == null || "null".equals(ststus) || "print".equals(ststus)) {
				// 打印只显示本年份的工资信息
				if ("print".equals(ststus)) {
					// String shangMonth = Util.getLastMonth("YYYY-MM月");
					String shangMonth = Util.getDateTime("yyyy") + "-01月";
					String lastMonth = Util.getDateTime("yyyy") + "-12月";
					hql += " mouth between '" + shangMonth + "' and '" + lastMonth + "' and code='" + wage.getCode()
							+ "' and printDate is null and ";
				}
				hql += "wageStatus in ('同意') order by mouth desc";
			} else if ("chage".equals(ststus)) {
				hql += "wageStatus in ('添加变动','打回','自查') order by addDateTime desc";
			} else if ("audit".equals(ststus) || "findAudit".equals(ststus)) {
				hql += "wageStatus in ('审核') order by addDateTime desc";
			} else {
				return null;
			}
			List list = totalDao.findAllByPage(hql, pageNo, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 根据id查询工资
	public Wage findWageById(int id) {
		if ((Object) id != null && id != 0) {
			return (Wage) totalDao.getObjectById(Wage.class, id);
		}
		return null;
	}

	// 根据id查询工资android
	public List androidprintWage(String code, String mouth) {
		if (code != null && code.length() > 0) {
			mouth = mouth + "月";
			String hql = "from Wage where code=? and mouth =?";
			List list = totalDao.query(hql, code, mouth);
			if (list != null && list.size() > 0) {
				return list;
			}
		}
		return null;
	}

	// 通过工资模板以及打分成绩更新工资
	@SuppressWarnings("unchecked")
	public boolean updateWageByWageStandard() {
		boolean bool = false;
		String hql = "from WageStandard where standardStatus='默认' and code=?";
		String hql2 = "from Wage where mouth=?";
		String hql3 = "from AssScore where code=? and asstMouth=? and assType='员工级'";// 查询员工级打分信息
		List wageList = totalDao.query(hql2, Util.getLastMonth("yyyy-MM月"));// 查询上个月工资信息
		for (int i = 0; i < wageList.size(); i++) {
			Wage wage = (Wage) wageList.get(i);
			if (wage != null) {
				WageStandard ws = (WageStandard) totalDao.getObjectByCondition(hql, wage.getCode());// 查询工资模版信息
				if (ws == null) {
					continue;
				}

				if (!wage.getWageClass().equals("离职工资") && !wage.getWageClass().equals("处理工资")) {
					wage.setGangweigongzi(ws.getGangweigongzi());// 岗位工资
					wage.setBaomijintie(ws.getBaomijintie());// 保密津贴
					wage.setDianhuabutie(ws.getDianhuabutie());// 补贴
					wage.setTongchoujin(-ws.getTongchoujin());// 养老保险
					wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险
					wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险
					wage.setGongjijin(-ws.getGongjijin());// 公积金
					wage.setJinenggongzi(ws.getJinenggongzi());// 技能工资
					wage.setGonglinggongzi(ws.getGonglinggongzi());// 特殊补贴
					wage.setFangzufei(ws.getFangzufei());// 房租费
				}
				if (wage.getWageClass().equals("绩效打分")) {
					AssScore assScore = (AssScore) totalDao.getObjectByCondition(hql3, ws.getCode(),
							Util.getLastMonth("yyyy-MM月"));// 获得上个月绩效考核成绩
					Float jixiao = assScore.getPercentageScore() * ws.getJixiaokaohegongzi() / 100;// 绩效考核=考核分数/定制总分*绩效考核总钱数
					wage.setJixiaokaohegongzi(jixiao);
				}

				// 应发工资=岗位工资+保密津贴+补贴+技能工资+特殊补贴+绩效考核+加班费+奖金
				Float yingfaWage = wage.getGangweigongzi() + wage.getBaomijintie() + wage.getDianhuabutie()
						+ wage.getJinenggongzi() + wage.getGonglinggongzi() + wage.getJixiaokaohegongzi()
						+ wage.getJiabanfei() + wage.getJiangjin();

				// 离职补偿工资
				if (wage.getLeaveBuchang() != null && wage.getLeaveBuchang() > 0) {
					yingfaWage += wage.getLeaveBuchang();
				}
				wage.setYingfagongzi(yingfaWage);// 应发工资
				wage.setAddDateTime(wage.getAddDateTime() + "," + Util.getDateTime());
				bool = totalDao.update(wage);
			}
		}
		return bool;
	}

	// 添加变动工资
	public boolean addChageWage(Wage newWage, Wage oldWage) {
		if (newWage != null && oldWage != null) {
			String hql2 = "from Users where code=?";
			// 查询用户是否离职
			Users chageUser = (Users) totalDao.getObjectByCondition(hql2, oldWage.getCode());
			if (chageUser.getOnWork() == null || chageUser.getOnWork().length() <= 0
					|| chageUser.getOnWork().equals("离职")) {
				return false;
			}
			if (oldWage.getWageStatus().equals("添加变动") || oldWage.getWageStatus().equals("自查")) {
				// 如果是离职工资则只处理补发补扣工资
				if (oldWage.getWageClass().equals("离职工资")) {
					if (newWage.getBfgongzi() > 0) {
						oldWage.setBfgongzi(newWage.getBfgongzi());// 补发(补扣)工资
					}
				} else {
					// 如果不存在加班费信息，则添加
					if (oldWage.getJiabanfei() == null || oldWage.getJiabanfei() <= 0F) {
						if (newWage.getJiabanfei() > 0) {
							oldWage.setJiabanfei(newWage.getJiabanfei());// 加班费
						}
					}
					if (newWage.getJiangjin() != null && newWage.getJiangjin() > 0F) {
						oldWage.setJiangjin(newWage.getJiangjin());// 奖金
					}
					oldWage.setWucanfei(newWage.getWucanfei());// 午餐费
					oldWage.setShuidianfei(newWage.getShuidianfei());// 水电费
					oldWage.setBingshikangdeng(newWage.getBingshikangdeng());// 病事旷等
					oldWage.setBfgongzi(newWage.getBfgongzi());// 补发(补扣)工资
					oldWage.setOther(newWage.getOther());// 其他
				}

				// 计算特别奖金(计入绩效考核)
				if ("添加变动".equals(oldWage.getWageStatus())) {
					String month = oldWage.getMouth();
					month = month.substring(0, month.length() - 1);
					String hql_tb = "select sum(money) from RewardPunish where userId=? and convert(varchar,date,120) like '%"
							+ month + "%'";
					Object obj = totalDao.getObjectByCondition(hql_tb, oldWage.getUserId());
					if (obj != null) {
						Float money = Float.parseFloat(obj.toString());
						oldWage.setJixiaokaohegongzi(oldWage.getJixiaokaohegongzi() + money);
					}
				}

				// 应发工资=岗位工资+保密津贴+补贴+技能工资+特殊补贴+绩效考核+加班费+奖金
				Float yingfa = oldWage.getGangweigongzi() + oldWage.getBaomijintie() + oldWage.getDianhuabutie()
						+ oldWage.getJinenggongzi() + oldWage.getGonglinggongzi() + oldWage.getJixiaokaohegongzi()
						+ oldWage.getJiabanfei() + oldWage.getJiangjin();
				// 总钱数=应发工资+其他(正/负)+补发工资(正/负)-水电费-房租费-病事旷等-养老保险-失业保险-医疗保险-公积金
				Float gongzi = yingfa + oldWage.getOther() + oldWage.getBfgongzi() + oldWage.getShuidianfei()
						+ oldWage.getFangzufei() + oldWage.getBingshikangdeng() + oldWage.getTongchoujin()
						+ oldWage.getShiyebaoxian() + oldWage.getYiliaobaoxian() + oldWage.getGongjijin();

				// 如果需要补差则进行工资补差处理
				if (oldWage.getIsBucha() != null && "yes".equals(oldWage.getIsBucha())) {
					// 总工资=岗位工资+保密津贴+补贴+技能工资+绩效考核+加班费+奖金+其他(正/负)-养老保险-失业保险-医疗保险-公积金
					Float minMoney = oldWage.getGangweigongzi() + oldWage.getBaomijintie() + oldWage.getDianhuabutie()
							+ oldWage.getJinenggongzi() + oldWage.getJixiaokaohegongzi() + oldWage.getJiabanfei()
							// + oldWage.getJiangjin()
							+ oldWage.getOther() + oldWage.getTongchoujin() + oldWage.getShiyebaoxian()
							+ oldWage.getYiliaobaoxian() + oldWage.getGongjijin();
					// 查询工资模版
					WageStandard ws = wsServer.findWSByUser(chageUser.getCode());
					if (ws != null) {
						// 查询人员所属类型的最低工资标准
						String dateTime = Util.getDateTime("yyyy-MM-dd");
						String hql3 = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
						InsuranceGold ig = (InsuranceGold) totalDao.getObjectByCondition(hql3, ws.getLocalOrField(),
								ws.getCityOrCountryside(), ws.getPersonClass(), dateTime, dateTime);
						if (ig != null) {
							if (minMoney < ig.getMinimumWage()) {
								oldWage.setBuchagongzi(ig.getMinimumWage() - minMoney);// 计算共补差工资金额
								// 计算应发工资=最低工资+午餐费+水电费+房租费+病事旷等+特殊补贴+奖金
								gongzi = ig.getMinimumWage() + oldWage.getShuidianfei()// 水电费
										+ oldWage.getFangzufei()// 房租费
										+ oldWage.getBingshikangdeng()// 病事旷
										+ oldWage.getGonglinggongzi()// 补贴
										+ oldWage.getBfgongzi()// 补差补扣工资
										+ oldWage.getJiangjin();
							}
						}
					}
				}
				// 不计补差的项(午餐费)
				gongzi += oldWage.getWucanfei();
				// Float remainder = gongzi - 3500;// 余数
				// Float shuijin = 0F;// 税金
				// if (gongzi >= 3500 && gongzi < 5000) {
				// shuijin = remainder * 3 / 100;
				// } else if (gongzi >= 5000 && gongzi < 8000) {
				// shuijin = remainder * 10 / 100 - 105;
				// } else if (gongzi >= 8000 && gongzi < 12500) {
				// shuijin = remainder * 20 / 100 - 1005;
				// } else if (gongzi >= 12500 && gongzi < 38500) {
				// shuijin = remainder * 25 / 100 - 1005;
				// }
				Float remainder = gongzi - 5000;// 余数
				Float shuijin = 0F;// 税金
				if (remainder > 0 && remainder <= 3000) {
					shuijin = remainder * 3 / 100;
				} else if (remainder > 3000 && remainder <= 12000) {
					shuijin = remainder * 10 / 100 - 210;
				} else if (remainder > 12000 && remainder <= 25000) {
					shuijin = remainder * 20 / 100 - 1410;
				} else if (remainder > 25000 && remainder <= 35000) {
					shuijin = remainder * 25 / 100 - 2660;
				} else if (remainder > 35000 && remainder <= 55000) {
					shuijin = remainder * 30 / 100 - 4410;
				} else if (remainder > 55000 && remainder <= 80000) {
					shuijin = remainder * 35 / 100 - 7160;
				} else if (remainder > 80000) {
					shuijin = remainder * 45 / 100 - 15160;
				}
				oldWage.setYingfagongzi(yingfa);// 应发工资
				oldWage.setYingjiaoshuijin(-shuijin);// 应交税金(负)
				oldWage.setShifagongzi(gongzi - shuijin);// 实发工资
				oldWage.setWageStatus("自查");// 工资状态
				oldWage.setAddChageTime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));// 上传变动时间
				oldWage.setUserId(chageUser.getId());// 工资所属人员id

				if (oldWage.getWageClass().equals("离职工资")) {
					oldWage.setShifagongzi(oldWage.getShifagongzi() + oldWage.getLeaveBuchang());// 实发工资=实发工资+离职补偿
				}
				return totalDao.update(oldWage);

			}
			return false;
		}
		return false;
	}

	// 修改工资
	public boolean updateWage(Wage wage, String status) {
		if (wage != null) {
			if (status != null && status.length() > 0) {
				if ("agree".equals(status)) {
					status = "同意";
				} else if ("disagree".equals(status)) {
					status = "打回";
				}
			}
			wage.setWageStatus(status);
			return totalDao.update(wage);
		}
		return false;
	}

	// 查看工资明细
	public String showWageDetails(Wage wage) {
		if (wage != null) {
			StringBuilder sbr = new StringBuilder();
			sbr.append("<table width='100%'>");
			sbr.append("<tr>");
			sbr.append("<td>");
			sbr.append("<ul class='userCenter'>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>工号 :</span> ");
			sbr.append(wage.getCode());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>部门 :</span> ");
			sbr.append(wage.getDept());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>岗位工资 :</span> ");
			sbr.append(wage.getGangweigongzi());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>绩效工资 :</span> ");
			if (wage.getScoreId() != null) {
				sbr.append("<a href='AssScoreAction!findAssScoreByCondition.action?assScore.id=" + wage.getScoreId()
						+ "'>" + wage.getJixiaokaohegongzi() + "</a>");
			} else {
				sbr.append(wage.getJixiaokaohegongzi());
			}
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>技能工资 :</span> ");
			sbr.append(wage.getJinenggongzi());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>应发工资:</span><FONT color='red'>");
			sbr.append(wage.getYingfagongzi());
			sbr.append("</FONT>");
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>医疗保险 :</span> ");
			sbr.append(wage.getYiliaobaoxian());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>午餐费 :</span> ");
			sbr.append(wage.getWucanfei());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>补发(补扣)工资:</span> ");
			sbr.append(wage.getBfgongzi());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>工资类别:</span> ");
			sbr.append(wage.getWageClass());
			sbr.append("</li>");
			sbr.append("</ul>");
			sbr.append("</td>");
			sbr.append("<td>");
			sbr.append("<ul class='userCenter'>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>卡号 :</span> ");
			sbr.append(wage.getCardId());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>发放月份 :</span> ");
			sbr.append(wage.getMouth());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>保密津贴 :</span> ");
			sbr.append(wage.getBaomijintie());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>加班费:</span> ");
			sbr.append(wage.getJiabanfei());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>特殊补贴:</span> ");
			sbr.append(wage.getGonglinggongzi());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>病事旷等 :</span> ");
			sbr.append(wage.getBingshikangdeng());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>失业保险 :</span> ");
			sbr.append(wage.getShiyebaoxian());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>房租费:</span> ");
			sbr.append(wage.getFangzufei());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>应交税金 :</span> ");
			sbr.append(wage.getYingjiaoshuijin());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			if (wage.getWageClass() != null && wage.getWageClass().equals("离职工资")) {
				sbr.append("<span class='span1'>离职补偿 :</span> ");
				sbr.append(wage.getLeaveBuchang());
			}
			sbr.append("</li>");
			sbr.append("</ul>");
			sbr.append("</td>");
			sbr.append("<td>");
			sbr.append("<ul class='userCenter'>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>姓名 :</span> ");
			sbr.append(wage.getUserName());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>工资状态 :</span> ");
			sbr.append(wage.getWageStatus());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'></span> ");
			sbr.append("");
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>其他 :</span> ");
			sbr.append(wage.getOther());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>奖金 :</span> ");
			if (wage.getJiangjinId() != null) {
				sbr.append("<a href='ProcardAction!findUMMoneyByCondition.action?umm.id=" + wage.getJiangjinId() + "'>"
						+ wage.getJiangjin() + "</a>");
			} else {
				sbr.append(wage.getJiangjin());
			}
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>养老保险 :</span> ");
			sbr.append(wage.getTongchoujin());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>公积金 :</span> ");
			sbr.append(wage.getGongjijin());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>水电费 :</span> ");
			sbr.append(wage.getShuidianfei());
			sbr.append("</li>");
			sbr.append("<li></li>");
			sbr.append("<li>");
			sbr.append("<span class='span1'>实发工资 :</span> <FONT color='red'>");
			sbr.append(wage.getShifagongzi());
			sbr.append(" </FONT>");
			sbr.append("</li>");
			sbr.append("<li>");
			sbr.append("</li>");
			sbr.append("</ul>");
			sbr.append("</td>");
			sbr.append("</tr>");
			sbr.append("<tr>");
			sbr.append("<td align='left' colspan='3' style='padding-left:40px;'><span class='span1'>说明:</span>");
			sbr.append(wage.getDirections());
			sbr.append("</td>");
			sbr.append("</tr>");
			sbr.append("<tr>");
			sbr.append("<td align='right' colspan='3'>");
			sbr.append(wage.getAddDateTime());
			sbr.append(
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/></td>");
			sbr.append("</tr>");

			sbr.append("</table>");

			return sbr.toString();

		}
		return "";
	}

	// 上传变动工资
	public String uploadChageWage(File chageWageFile, String mouth) {
		String addMessage = "";
		String runMessage = "";
		if (mouth != null && mouth.length() > 0) {
			int number = 0;

			String filName = mouth + Util.getDateTime("yyyyMMddhhmmss") + ".xls";
			// 上传到服务器
			String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/sheet/wage/chageWage")
					+ "/" + filName;
			// 备份到项目
			String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/sheet/wage/chageWage" + "/" + filName;
			File file = new File(fileRealPath);
			File beifenFile = new File(beiFenfileRealPath);
			try {
				FileCopyUtils.copy(chageWageFile, file);
				try {
					FileCopyUtils.copy(chageWageFile, beifenFile);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 开始读取excel表格
				InputStream is = new FileInputStream(fileRealPath);// 创建文件流
				jxl.Workbook rwb = Workbook.getWorkbook(is);// 创建workBook
				Sheet rs = rwb.getSheet(0);// 获得第一张Sheet表
				int excelcolumns = rs.getRows();// 获得总行数
				String hql = "from Wage where code=? and mouth=? and wageStatus in ('添加变动','打回','自查')";
				String hql2 = "from Users where code=?";
				if (excelcolumns > 1) {
					for (int i = 1; i < excelcolumns; i++) {
						try {
							Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
							String code = cells[0].getContents();// 获得工号
							String jiabanfei = cells[3].getContents();// 获得加班费
							String wucanfei = cells[4].getContents();// 获得午餐费
							String jiangjin = cells[5].getContents();// 获得奖金
							String sdfei = cells[6].getContents();// 获得水电费
							String bskdeng = cells[7].getContents();// 获得病事旷等
							String bfbk = cells[8].getContents();// 获得补发补扣工资
							String other = cells[9].getContents();// 获得特殊补贴
							String more = cells[10].getContents();// 获得说明

							// 上传数据格式统一处理
							if (code == null || code.length() <= 0) {
								number++;
								addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>工号为空,请添加工号</font><br/>";
								continue;
							} else if (code.length() == 1) {
								code = "00" + code;
							} else if (code.length() == 2) {
								code = "0" + code;
							}
							if (jiabanfei == null || jiabanfei.length() == 0) {
								jiabanfei = "0";
							}
							try {
								Float.valueOf(jiabanfei);// 获得加班费
							} catch (Exception e1) {
								jiabanfei = "0";
							}
							if (wucanfei == null || wucanfei.length() == 0) {
								wucanfei = "0";
							}
							try {
								Float.valueOf(wucanfei);// 获得加班费
							} catch (Exception e1) {
								wucanfei = "0";
							}
							if (jiangjin == null || jiangjin.length() == 0) {
								jiangjin = "0";
							}
							try {
								Float.valueOf(jiangjin);// 获得加班费
							} catch (Exception e1) {
								jiangjin = "0";
							}
							if (sdfei == null || sdfei.length() == 0) {
								sdfei = "0";
							}
							try {
								Float.valueOf(sdfei);// 获得加班费
							} catch (Exception e1) {
								sdfei = "0";
							}
							if (bskdeng == null || bskdeng.length() == 0) {
								bskdeng = "0";
							}
							try {
								Float.valueOf(bskdeng);// 获得加班费
							} catch (Exception e1) {
								bskdeng = "0";
							}
							if (bfbk == null || bfbk.length() == 0) {
								bfbk = "0";
							}
							try {
								Float.valueOf(bfbk);// 获得加班费
							} catch (Exception e1) {
								bfbk = "0";
							}
							if (other == null || other.length() == 0) {
								other = "0";
							}
							try {
								Float.valueOf(other);// 获得特殊补贴
							} catch (Exception e1) {
								other = "0";
							}

							// 查询用户是否离职
							Users chageUser = (Users) totalDao.getObjectByCondition(hql2, code);

							if (chageUser != null) {
								if (chageUser.getOnWork() == null || chageUser.getOnWork().length() <= 0
										|| chageUser.getOnWork().equals("离职")) {
									number++;
									addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>" + chageUser.getName()
											+ chageUser.getOnWork() + ",无法处理工资</font><br/>";
									continue;
								}
							} else {
								number++;
								addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>,没有工号为 " + code
										+ " 的人员,无法处理工资</font><br/>";
								continue;
							}

							// 查询该工号上个月是否有需要添加变动项的工资信息
							Wage wage = (Wage) totalDao.getObjectByCondition(hql, code, mouth);
							if (wage != null) {
								// 查询工资模版
								WageStandard ws = wsServer.findWSByUser(chageUser.getCode());
								if (ws != null) {
									if (!wage.getWageClass().equals("处理工资")) {
										if (ws.getGongshangbaoxian() == null) {
											ws.setGongshangbaoxian(0F);
										}
										if (ws.getShengyubaoxian() == null) {
											ws.setShengyubaoxian(0F);
										}
										if (ws.getGongjijin() == null) {
											ws.setGongjijin(0F);
										}
										// 处理社保工资部分
										wage.setTongchoujin(-ws.getTongchoujin());// 养老保险(固)个人
										wage.setYiliaobaoxian(-ws.getYiliaobaoxian());// 医疗保险(固)个人
										wage.setShiyebaoxian(-ws.getShiyebaoxian());// 失业保险(固)个人
										wage.setGongshangbaoxian(-ws.getGongshangbaoxian());// 工伤保险(固)个人
										wage.setShengyubaoxian(-ws.getShengyubaoxian());// 生育保险(固)个人
										wage.setGongjijin(-ws.getGongjijin());// 公积金(固)个人

										wage.setDwtongchoujin(-ws.getDwtongchoujin());// 养老保险(固)单位
										wage.setDwyiliaobaoxian(-ws.getDwyiliaobaoxian());// 医疗保险(固)单位
										wage.setDwshiyebaoxian(-ws.getDwshiyebaoxian());// 失业保险(固)单位
										wage.setDwgongshangbaoxian(-ws.getDwgongshangbaoxian());// 工伤保险(固)单位
										wage.setDwshengyubaoxian(-ws.getDwshengyubaoxian());// 生育保险(固)单位
										wage.setDwgongjijin(-ws.getDwgongjijin());// 公积金(固)单位
										// 是否补差
										wage.setIsBucha(ws.getBucha());// 是否补差
									}
								} else {
									number++;
									addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>" + chageUser.getName()
											+ "不存在工资模版</font><br/>";
									continue;
								}
								try {
									if (wage.getWageClass().equals("离职工资")) {
										Float bfbkgongzi = Float.valueOf(bfbk);
										if (bfbkgongzi > 0) {
											wage.setBfgongzi(bfbkgongzi);// 补发(补扣)工资
										}
									} else {
										// 如果不存在加班费信息，则添加
										if (wage.getJiabanfei() == null || wage.getJiabanfei() <= 0F) {
											wage.setJiabanfei(Float.valueOf(jiabanfei));// 加班费
										}
										// 如果上传奖金不为0，则覆盖已存在奖金
										if (jiangjin != null && jiangjin.length() > 0 && !"0".equals(jiangjin)) {
											wage.setJiangjin(Float.valueOf(jiangjin));// 奖金
										}
										wage.setWucanfei(Float.valueOf(wucanfei));// 午餐费
										wage.setShuidianfei(Float.valueOf(sdfei));// 水电费
										wage.setBingshikangdeng(Float.valueOf(bskdeng));// 病事旷等
										wage.setBfgongzi(Float.valueOf(bfbk));// 补发(补扣)工资
										wage.setGonglinggongzi(Float.valueOf(other));// 特殊补贴
										wage.setDirections(more);
									}
								} catch (Exception e) {
									number++;
									addMessage += "第" + (i + 1)
											+ "行出错! 出错原因:<font color='red'>您所上传的数据格式不正确(可能某列不是数值类型)</font><br/>";
									continue;
								}
								// 计算特别奖金(计入绩效考核)
								if ("添加变动".equals(wage.getWageStatus())) {
									String month = wage.getMouth();
									month = month.substring(0, month.length() - 1);
									String hql_tb = "select sum(money) from RewardPunish where userId=? and convert(varchar,date,120) like '%"
											+ month + "%'";
									Object obj = totalDao.getObjectByCondition(hql_tb, wage.getUserId());
									if (obj != null) {
										if (wage.getBfgongzi() == null) {
											wage.setBfgongzi(0F);
										}
										Float money = Float.parseFloat(obj.toString());
										wage.setBfgongzi(wage.getBfgongzi() + money);
									}
								}

								// 应发工资=岗位工资+保密津贴+补贴+技能工资+特殊补贴+绩效考核+加班费+奖金
								Float yingfa = wage.getGangweigongzi() + wage.getBaomijintie() + wage.getDianhuabutie()
										+ wage.getJinenggongzi() + wage.getJixiaokaohegongzi() + wage.getJiabanfei()
										+ wage.getJiangjin();
								// 总钱数=应发工资+其他(正/负)+补发工资(正/负)-水电费-房租费-病事旷等-养老保险-失业保险-医疗保险-公积金
								Float gongzi = yingfa + wage.getOther() + wage.getBfgongzi() + wage.getShuidianfei()
										+ wage.getFangzufei() + wage.getBingshikangdeng() + wage.getTongchoujin()
										+ wage.getShiyebaoxian() + wage.getYiliaobaoxian() + wage.getGongjijin();
								// 如果需要补差则进行工资补差处理
								if (wage.getIsBucha() != null && "yes".equals(wage.getIsBucha())
										&& !wage.getWageClass().equals("离职工资")) {
									// 总工资=岗位工资+保密津贴+补贴+技能工资+绩效考核+加班费+奖金+其他(正/负)-养老保险-失业保险-医疗保险-公积金
									Float minMoney = wage.getGangweigongzi() + wage.getBaomijintie()
											+ wage.getDianhuabutie() + wage.getJinenggongzi()
											+ wage.getJixiaokaohegongzi()
											// + wage.getJiabanfei()
											// + wage.getJiangjin()
											+ wage.getOther() + wage.getTongchoujin() + wage.getShiyebaoxian()
											+ wage.getYiliaobaoxian() + wage.getGongjijin();
									if (ws != null) {
										// 查询人员所属类型的最低工资标准
										String dateTime = Util.getDateTime("yyyy-MM-dd");
										String hql3 = "from InsuranceGold where localOrField=? and cityOrCountryside=? and personClass=? and validityStartDate<=? and validityEndDate>=?";
										InsuranceGold ig = (InsuranceGold) totalDao.getObjectByCondition(hql3,
												ws.getLocalOrField(), ws.getCityOrCountryside(), ws.getPersonClass(),
												dateTime, dateTime);
										if (ig != null) {
											if (minMoney <= ig.getMinimumWage()) {
												wage.setBuchagongzi(ig.getMinimumWage() - minMoney);// 计算共补差工资金额
												// 计算应发工资=最低工资+午餐费+水电费+房租费+病事旷等+特殊补贴+奖金
												gongzi = ig.getMinimumWage() + wage.getShuidianfei()// 水电费
														+ wage.getFangzufei()// 房租费
														+ wage.getBingshikangdeng()// 病事旷
														+ wage.getBfgongzi()// 补差补扣工资
														+ wage.getJiangjin() + wage.getJiabanfei();
											}
										}
									}
								}
								// 不计补差的项(午餐费)
								gongzi += wage.getWucanfei();
								// Float remainder = gongzi - 3500;// 余数
								// Float shuijin = 0F;// 税金
								// if (gongzi >= 3500 && gongzi < 5000) {
								// shuijin = remainder * 3 / 100;
								// } else if (gongzi >= 5000 && gongzi < 8000) {
								// shuijin = remainder * 10 / 100 - 105;
								// } else if (gongzi >= 8000 && gongzi < 12500)
								// {
								// shuijin = remainder * 20 / 100 - 555;
								// } else if (gongzi >= 12500 && gongzi < 38500)
								// {
								// shuijin = remainder * 25 / 100 - 1005;
								// }
								Double jbkc = 5000D;
								if (wage.getZxfjkc() != null && wage.getZxfjkc() > 0) {
									jbkc += wage.getZxfjkc();
								}

								Float remainder = gongzi - jbkc.floatValue();// 余数
								Float shuijin = 0F;// 税金
								if (remainder > 0 && remainder <= 3000) {
									shuijin = remainder * 3 / 100;
								} else if (remainder > 3000 && remainder <= 12000) {
									shuijin = remainder * 10 / 100 - 210;
								} else if (remainder > 12000 && remainder <= 25000) {
									shuijin = remainder * 20 / 100 - 1410;
								} else if (remainder > 25000 && remainder <= 35000) {
									shuijin = remainder * 25 / 100 - 2660;
								} else if (remainder > 35000 && remainder <= 55000) {
									shuijin = remainder * 30 / 100 - 4410;
								} else if (remainder > 55000 && remainder <= 80000) {
									shuijin = remainder * 35 / 100 - 7160;
								} else if (remainder > 80000) {
									shuijin = remainder * 45 / 100 - 15160;
								}

								Double nojishuimoney = (wage.getGonglinggongzi() == null ? 0D
										: wage.getGonglinggongzi().doubleValue());
								wage.setYingfagongzi(yingfa + nojishuimoney.floatValue());// 应发工资
								wage.setYingjiaoshuijin(-shuijin);// 应交税金(负)
								wage.setShifagongzi(gongzi - shuijin + nojishuimoney.floatValue()
										+ (wage.getLeaveBuchang() == null ? 0F : wage.getLeaveBuchang()));// 实发工资
								wage.setWageStatus("自查");// 工资状态
								String addChageTime = wage.getAddChageTime() == null ? Util.getDateTime()
										: wage.getAddChageTime() + ",";
								wage.setAddChageTime(addChageTime + Util.getDateTime());// 上传变动时间
								wage.setUserId(chageUser.getId());// 工资所属人员id

								if (wage.getWageClass().equals("离职工资")) {
									wage.setShifagongzi(wage.getShifagongzi() + wage.getLeaveBuchang());// 实发工资=实发工资+离职补偿
								}
								boolean bool = totalDao.update(wage);
								if (bool == false) {
									addMessage += "为员工 " + wage.getUserName() + " 添加变动工资的时候出错!请联系管理员!<br>";
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							number++;
							runMessage += "读取第" + (i + 1) + "行文件或数据时信息时出错!请检查后重试!<br>";
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				number++;
				runMessage += "数据有" + number + "处问题!<br>";
			}
			return addMessage + runMessage;
		} else {
			return "请先选择上传的月份!";
		}
	}

	// 上传社保工资
	public String uploadSbWage(File chageWageFile) {
		String addMessage = "";
		String runMessage = "";
		int number = 0;

		String filName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext().getRealPath("/upload/sheet/wage/shebaoWage")
				+ "/" + filName;
		// 备份到项目
		String beiFenfileRealPath = "D:/WorkSpace/HHTask/WebRoot/upload/sheet/wage/shebaoWage" + "/" + filName;
		File file = new File(fileRealPath);
		File beifenFile = new File(beiFenfileRealPath);
		try {
			FileCopyUtils.copy(chageWageFile, file);
			try {
				FileCopyUtils.copy(chageWageFile, beifenFile);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 开始读取excel表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流
			jxl.Workbook rwb = Workbook.getWorkbook(is);// 创建workBook
			Sheet rs = rwb.getSheet(0);// 获得第一张Sheet表
			int excelcolumns = rs.getRows();// 获得总行数
			String hql = "from WageStandard where code=? and processStatus='审核' ";
			String hql3 = "from WageStandard where code=? and standardStatus='默认' and processStatus='同意'";
			String hql2 = "from Users where code=?";
			if (excelcolumns > 1) {
				Users loginUser = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
				int num = 0;
				for (int i = 1; i < excelcolumns; i++) {
					try {
						Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
						String code = cells[0].getContents();// 获得工号
						String gangweigongzi = cells[2].getContents().trim();// 基本工资
						String gjjBase = cells[3].getContents().trim();// 公积金基数
						String gongjijin = cells[4].getContents().trim();// 公积金
						String ssBase = cells[5].getContents().trim();// 社保基数
						String tongchoujin = cells[6].getContents().trim();// 养老保险
						String yiliaobaoxian = cells[7].getContents().trim();// 医疗保险
						String shiyebaoxian = cells[8].getContents().trim();// 失业保险

						// 上传数据格式统一处理
						if (code == null || code.length() <= 0) {
							addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>工号为空,请添加工号!</font><br/>";
							continue;
						} else if (code.length() == 1) {
							code = "00" + code;
						} else if (code.length() == 2) {
							code = "0" + code;
						}

						// 查询用户是否离职
						Users chageUser = (Users) totalDao.getObjectByCondition(hql2, code);
						if (chageUser.getOnWork() == null || chageUser.getOnWork().length() <= 0
								|| chageUser.getOnWork().equals("离职") || chageUser.getOnWork().equals("离职中")) {
							addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>" + chageUser.getName()
									+ "已经离职,无需处理工资模版</font><br/>";
							continue;
						}

						// 查询员工是否存在审核中工资模版
						WageStandard auditWs = (WageStandard) totalDao.getObjectByCondition(hql, code);
						WageStandard oldWs = null;
						WageStandard ws = new WageStandard();
						if (auditWs == null) {
							// 查询员工默认工资模版
							oldWs = (WageStandard) totalDao.getObjectByCondition(hql3, code);
							if (oldWs == null) {
								addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>" + chageUser.getName()
										+ "不存在工资模版!请先添加工资模版</font><br/>";
								continue;
							} else {
								ws.setCode(oldWs.getCode());
								ws.setCardId(oldWs.getCardId());
								ws.setUserName(oldWs.getUserName());
								ws.setDept(oldWs.getDept());
								ws.setGangweigongzi(oldWs.getGangweigongzi());
								ws.setBaomijintie(oldWs.getBaomijintie());
								ws.setDianhuabutie(oldWs.getDianhuabutie());
								ws.setJixiaokaohegongzi(oldWs.getJixiaokaohegongzi());
								ws.setJinenggongzi(oldWs.getJinenggongzi());
								ws.setGonglinggongzi(oldWs.getGonglinggongzi());
								ws.setYingfagongzi(oldWs.getYingfagongzi());
								ws.setTongchoujin(oldWs.getTongchoujin());
								ws.setYiliaobaoxian(oldWs.getYiliaobaoxian());
								ws.setShiyebaoxian(oldWs.getShiyebaoxian());
								ws.setGongjijin(oldWs.getGongjijin());
								ws.setGjjBase(oldWs.getGjjBase());
								ws.setSsBase(oldWs.getSsBase());
								ws.setLocalOrField(oldWs.getLocalOrField());
								ws.setCityOrCountryside(oldWs.getCityOrCountryside());
								ws.setInputPeople(oldWs.getInputPeople());
								ws.setInputDate(oldWs.getInputDate());
								ws.setStandardStatus(oldWs.getStandardStatus());
								ws.setIsOnWork(oldWs.getIsOnWork());
								ws.setProcessStatus(oldWs.getProcessStatus());
								ws.setBucha(oldWs.getBucha());
								ws.setPersonClass(oldWs.getPersonClass());
								ws.setFangzufei(oldWs.getFangzufei());

								// 更新工资模版
								oldWs.setUpdatePeople(loginUser.getName());// 修改人
								oldWs.setUpdateDate(Util.getDateTime(null));// 修改时间
								oldWs.setStandardStatus("默认");// 模板状态
								boolean bool = totalDao.update(oldWs);
								if (bool) {
									oldWs = null;
								}
							}
						} else {
							ws = auditWs;
						}
						// 岗位工资
						if (gangweigongzi != null && gangweigongzi.length() > 0) {
							ws.setGangweigongzi(Float.valueOf(gangweigongzi));
							Float yingfagongzi = ws.getGangweigongzi()// 应发工资(岗位工资+保密津贴+电话补贴+技能工资+特殊补贴)
									+ ws.getBaomijintie() + ws.getDianhuabutie() + ws.getJinenggongzi()
									+ ws.getGonglinggongzi();
							ws.setYingfagongzi(yingfagongzi);// 应发工资
						}
						// 公积金基数
						if (gjjBase != null && gjjBase.length() > 0) {
							ws.setGjjBase(Float.valueOf(gjjBase));
						}
						// 公积金
						if (gongjijin != null && gongjijin.length() > 0) {
							ws.setGongjijin(Float.valueOf(gongjijin));
						}
						// 社保基数
						if (ssBase != null && ssBase.length() > 0) {
							ws.setSsBase(Float.valueOf(ssBase));
						}
						// 养老保险
						if (tongchoujin != null && tongchoujin.length() > 0) {
							ws.setTongchoujin(Float.valueOf(tongchoujin));
						}
						// 医疗保险
						if (yiliaobaoxian != null && yiliaobaoxian.length() > 0) {
							ws.setYiliaobaoxian(Float.valueOf(yiliaobaoxian));
						}
						// 失业保险
						if (shiyebaoxian != null && shiyebaoxian.length() > 0) {
							ws.setShiyebaoxian(Float.valueOf(shiyebaoxian));
						}
						boolean bool = false;
						if (auditWs != null) {
							ws.setUpdatePeople(loginUser.getName());// 修改人
							ws.setUpdateDate(Util.getDateTime(null));// 修改时间
							bool = totalDao.update(ws);
						} else {
							ws.setInputDate(Util.getDateTime(null));// 添加时间
							ws.setInputPeople(loginUser.getName());// 添加人
							ws.setStandardStatus(null);// 模板状态
							ws.setProcessStatus("审核");// 审核状态
							bool = totalDao.save(ws);
						}
						if (bool == false) {
							addMessage += "第" + (i + 1) + "行出错! 出错原因:<font color='red'>保存修改时出错!</font><br/>";
						} else {
							num++;
						}
					} catch (Exception e) {
						e.printStackTrace();
						number++;
						runMessage += "读取第" + (i + 1) + "行文件或数据时信息时出错!请检查后重试!<br>";
					}
				}
				// 发送系统提醒消息
				AlertMessagesServerImpl.addAlertMessages("工资模版审核 ", "统一调整了" + num + "个员工的社保缴纳数据,请您审核! ", "1");

			}
		} catch (Exception e) {
			e.printStackTrace();
			number++;
			runMessage += "数据有" + number + "处问题!<br>";
		}
		return addMessage + runMessage;
	}

	// 工资批量审核(同意/打回)
	public String batchAudit(int wageId[], String status) {
		if (wageId != null && wageId.length > 0 && status != null && status.length() > 0) {
			String message = "";
			if ("ok".equals(status)) {
				status = "同意";
			} else if ("back".equals(status)) {
				status = "打回";
			} else {
				return "非法字符";
			}
			boolean allbool = false;
			String month = "";
			for (int i = 0; i < wageId.length; i++) {
				Wage wage = (Wage) totalDao.getObjectById(Wage.class, wageId[i]);
				month = wage.getMouth();
				wage.setWageStatus(status);
				wage.setAuditDateTime(Util.getDateTime());// 审核时间
				boolean bool = totalDao.update(wage);
				if (bool) {
					allbool = true;
					if ("同意".equals(wage.getWageStatus()) && wage.getJixiaokaohegongzi() > 0) {
						// 将绩效工资作为本月所得积分
						String hql_1 = "from Integral where integrcode=? and integralName=? and year=?";
						Integral integral = (Integral) totalDao.getObjectByCondition(hql_1, wage.getCode(),
								wage.getUserName(), Util.getDateTime("yyyy"));
						if (integral == null) {
							integral = new Integral();
						}
						String hql_user = "from Users where code=? and name=?";
						Users user = (Users) totalDao.getObjectByCondition(hql_user, wage.getCode(),
								wage.getUserName());
						integral.setIntegralName(wage.getUserName());// 积分所属人姓名
						integral.setIntegrcode(wage.getCode());// 积分所属人工号
						integral.setIntegrdept(wage.getDept());// 积分所属人部门
						integral.setUserId(user.getId());// 积分所属人Id;
						Integralsource is = new Integralsource();
						List<Integralsource> islist = new ArrayList<Integralsource>();
						is.setAddintegral(wage.getJixiaokaohegongzi().intValue());// 添加的积分
						is.setAddtime(Util.getDateTime());// 添加时间
						is.setLaiyuan("奖金分配");
						islist.add(is);
						integral.setIsList(islist);
						String msg = IntegralServerDaoImpl.addIntegral1(integral);
						if ("true".equals(msg)) {
							message += "员工: " + wage.getUserName() + " 的积分信息处理成功!<br/>";
						} else {
							message += "员工: " + wage.getUserName() + msg + "<br/>";
						}
					}
					message += "审核员工 " + wage.getUserName() + " 的工资完成<br>";

					if (wage.getWageClass().equals("离职工资")) {
						// 判断用户是否为"离职中"状态
						Users user = (Users) totalDao.getObjectById(Users.class, wage.getUserId());
						if (user.getOnWork() != null && user.getOnWork().equals("离职中")) {
							user.setOnWork("离职");
							user.setCardId("");// 卡号为空
							totalDao.update(user);
						}
					}

				} else {
					allbool = false;
					message += "审核员工 " + wage.getUserName() + " 的工资时出错,请检查后重试!<br>";
				}
			}
			createCvAndReg(month);
			return message;
		}
		return "false";
	}

	/***
	 * 生成凭证以及付款单
	 * 
	 * @param month
	 * @return
	 */
	@Override
	public boolean createCvAndReg(String month) {
		String wagepay_hql = "from WagePay where month=? and epStatus='同意'";
		List<WagePay> list = totalDao.query(wagepay_hql, month);
		if (list != null) {
			boolean bool = false;
			for (WagePay wagePay : list) {
				Receipt receipt = new Receipt();
				receipt.setPayee(wagePay.getPayee());
				receipt.setPayeeId(null);
				receipt.setSummary(wagePay.getMonth() + wagePay.getType() + "费用");
				receipt.setPayType(wagePay.getType());
				receipt.setAboutNum(null);
				receipt.setFk_monthlyBillsId(null);
				receipt.setAllMoney(wagePay.getAllmoney().floatValue());
				receipt.setPaymentCycle(0);
				receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
				bool = receiptServer.addReceipt(receipt);
			}
			return bool;
		}

		// 查询本月工资是否全部同意
		String hql_getmonthWage = "from Wage where mouth=? and wageStatus<>'同意'";
		Integer wageAuditcount = totalDao.getCount(hql_getmonthWage, month);
		if (wageAuditcount == 0) {
			boolean bool = false;
			/************************* 生成工资的付款账单 ***********************************/
			// // 嘉杨(乔昌、王海波、孙晓波 实发工资)
			// String hql_jygz =
			// "select sum(shifagongzi) from Wage where mouth=? and
			// wageStatus='同意' and code in ('545','549','538')";
			// Float jygz = (Float) totalDao.getObjectByCondition(hql_jygz,
			// month);
			// if (jygz != null && jygz > 0) {
			// Receipt receipt = new Receipt();
			// receipt.setPayee("上海嘉扬企业服务外包有限公司");
			// receipt.setPayeeId(null);
			// receipt.setSummary(month + "嘉杨企业服务外包有限公司" + "劳务派遣人员工资、社保及服务费");
			// receipt.setPayType("职工薪酬");
			// receipt.setAboutNum(null);
			// receipt.setFk_monthlyBillsId(null);
			// receipt.setAllMoney(jygz);
			// receipt.setPaymentCycle(0);
			// receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
			// bool = receiptServer.addReceipt(receipt);
			// }
			// // 嘉定劳务（方海清的社保）
			// String hql_fhqsb =
			// "select sum(-tongchoujin-yiliaobaoxian-shiyebaoxian"
			// +
			// "-gongshangbaoxian-shengyubaoxian-dwtongchoujin-dwyiliaobaoxian-"
			// + "dwshiyebaoxian-dwgongshangbaoxian-dwshengyubaoxian) from Wage"
			// + " where mouth=? and wageStatus='同意' and code in ('9042')";
			// Float fhq = (Float) totalDao.getObjectByCondition(hql_fhqsb,
			// month);
			// if (fhq != null && fhq > 0) {
			// fhq += 40F;
			// Receipt receipt = new Receipt();
			// receipt.setPayee("上海嘉定区职工劳务开发有限公司");
			// receipt.setPayeeId(null);
			// receipt.setSummary(month + "嘉定区职工劳务开发有限公司派遣人员方海清社保及服务费");
			// receipt.setPayType("社保");
			// receipt.setAboutNum(null);
			// receipt.setFk_monthlyBillsId(null);
			// receipt.setAllMoney(1734.7F);
			// receipt.setPaymentCycle(0);
			// receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
			// bool = receiptServer.addReceipt(receipt);
			// }
			// // 公积金（宋明军）
			// String hql_gjj = "select sum(-gongjijin-dwgongjijin) from Wage"
			// + " where mouth=? and wageStatus='同意' and code not in ('9042')";
			// Float gjj = (Float) totalDao.getObjectByCondition(hql_gjj,
			// month);
			// if (gjj != null && gjj > 0) {
			// Receipt receipt = new Receipt();
			// receipt.setPayee("上海市住房公积金管理中心");
			// receipt.setPayeeId(null);
			// receipt.setSummary(month + "公积金费用");
			// receipt.setPayType("公积金");
			// receipt.setAboutNum(null);
			// receipt.setFk_monthlyBillsId(null);
			// receipt.setAllMoney(gjj);
			// receipt.setPaymentCycle(0);
			// receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
			// bool = receiptServer.addReceipt(receipt);
			// }

			// 社保（个人+公司）
			String hql_sb = "select sum(-tongchoujin-yiliaobaoxian-shiyebaoxian"
					+ "-gongshangbaoxian-shengyubaoxian-dwtongchoujin-dwyiliaobaoxian-"
					+ "dwshiyebaoxian-dwgongshangbaoxian-dwshengyubaoxian) from Wage"
					+ " where mouth=? and wageStatus='同意' and code not in ('9042')";
			Float sb = (Float) totalDao.getObjectByCondition(hql_sb, month);
			if (sb != null && sb > 0) {
				Receipt receipt = new Receipt();
				receipt.setPayee("上海市社会保险事业管理中心");
				receipt.setPayeeId(null);
				receipt.setSummary(month + "社保费用");
				receipt.setPayType("社保");
				receipt.setAboutNum(null);
				receipt.setFk_monthlyBillsId(null);
				receipt.setAllMoney(sb);
				receipt.setPaymentCycle(0);
				receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
				bool = receiptServer.addReceipt(receipt);
			}
			// 公积金（个人+公司）
			String hql_gjj = "select sum(-gongjijin-dwgongjijin) from Wage"
					+ " where mouth=? and wageStatus='同意' and code not in ('9042')";
			Float gjj = (Float) totalDao.getObjectByCondition(hql_gjj, month);
			if (gjj != null && gjj > 0) {
				Receipt receipt = new Receipt();
				receipt.setPayee("上海市住房公积金管理中心");
				receipt.setPayeeId(null);
				receipt.setSummary(month + "公积金费用");
				receipt.setPayType("公积金");
				receipt.setAboutNum(null);
				receipt.setFk_monthlyBillsId(null);
				receipt.setAllMoney(gjj);
				receipt.setPaymentCycle(0);
				receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
				bool = receiptServer.addReceipt(receipt);
			}
			// 职工工资
			String hql_wage = "select sum(shifagongzi) from Wage" + " where mouth=? and wageStatus='同意'";
			Float wages = (Float) totalDao.getObjectByCondition(hql_wage, month);
			if (wages != null && wages > 0) {
				Receipt receipt = new Receipt();
				receipt.setPayee("职工薪酬");
				receipt.setPayeeId(null);
				receipt.setSummary(month + "员工工资");
				receipt.setPayType("职工薪酬");
				receipt.setAboutNum(null);
				receipt.setFk_monthlyBillsId(null);
				receipt.setAllMoney(wages);
				receipt.setPaymentCycle(0);
				receipt.setFukuanDate(Util.getDateTime("yyyy-MM-dd"));
				bool = receiptServer.addReceipt(receipt);
			}

			/************************ 生成工资计提凭证 ***********************************/
			String hql = "select dept,sum(yingfagongzi),"
					+ "sum(tongchoujin+dwtongchoujin),sum(yiliaobaoxian+dwyiliaobaoxian),sum(shiyebaoxian+dwshiyebaoxian)"
					+ ",sum(gongshangbaoxian+dwgongshangbaoxian),sum(shengyubaoxian+dwshengyubaoxian)"
					+ ",sum(gongjijin+dwgongjijin) from Wage where wageStatus='同意123' and mouth=? group by dept";
			List wageList = totalDao.query(hql, month);
			CwVouchers cwVouchers = new CwVouchers();
			// 生成编号
			String num = "P" + Util.getDateTime("yyyyMM");
			String hql_finMaxnum = "select max(number) from CwVouchers where number like '%" + num + "%'";
			String maxfkNumber = (String) totalDao.getObjectByCondition(hql_finMaxnum);
			if (maxfkNumber != null && maxfkNumber.length() > 0) {
				String subnum = "";
				Integer maxnum = Integer.parseInt(maxfkNumber.substring(7, maxfkNumber.length())) + 1;
				if (maxnum < 10) {
					subnum += "0000" + maxnum;// 00009
				} else if (maxnum < 100) {
					subnum += "000" + maxnum;// 00099
				} else if (maxnum < 1000) {
					subnum += "00" + maxnum;// 00999
				} else if (maxnum < 10000) {
					subnum += "0" + maxnum;// 09999
				} else {
					subnum += "" + maxnum;
				}
				num += subnum;
			} else {
				num += "00001";
			}
			cwVouchers.setNumber(num);
			cwVouchers.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers.setCreatetime(Util.getDateTime());
			cwVouchers.setUserName(Util.getLoginUser().getName());
			cwVouchers.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers.setCreateName(Util.getLoginUser().getName());
			Double jieMoney = 0d;
			Double daiMoney = 0d;

			Set<CwVouchersDetail> cwVouchersDetailSet = new HashSet<CwVouchersDetail>();
			for (int i = 0; i < wageList.size(); i++) {
				Object[] wageobj = (Object[]) wageList.get(i);
				String dept = (String) wageobj[0];
				Double yingfagongzi_d = (Double) wageobj[1];
				Double yingfagongzi = yingfagongzi_d;

				SubBudgetRate subBudgetRate_jie = null;
				if ("信息".equals(dept) || "研发".equals(dept) || "工艺".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao
							.getObjectByCondition("from SubBudgetRate where name='工资薪酬' and fatherName='费用化支出' "
									+ " and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");
				} else if ("总成班".equals(dept) || "加工".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='生产人员薪酬' and fatherName='民品成本' and rootId=(select id from SubBudgetRate where name='生产成本' and belongLayer=1)");
				} else if ("品质".equals(dept) || "物流".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='工资' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='制造费用' and belongLayer=1)");
				} else {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='薪酬' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
				}

				/******************* 冲销 凭证分录 ***********************/

				// ***************** 凭证分录 --借方*********************
				CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

				// ***************** 分录--辅助明细********************
				CwUseDetail cwUseDetail = new CwUseDetail();
				cwUseDetail.setPayee(dept);
				cwUseDetail.setUseFor("计提" + month + "工资");
				cwUseDetail.setUsemoney(yingfagongzi);
				cwUseDetail.setAboutNum("");
				cwUseDetail.setPayNum("");
				cwUseDetail.setPayType("计提");
				cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
				cwUseDetailSet.add(cwUseDetail);

				cwVouchersDetail_jie.setRemark("计提" + month + "工资");
				cwVouchersDetail_jie.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
						subBudgetRate_jie.getRootId());
				cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
				cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
				// 明细科目
				cwVouchersDetail_jie
						.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_jie.getId()) + "-" + dept);
				cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

				cwVouchersDetail_jie.setJieMoney(yingfagongzi);
				cwVouchersDetail_jie.setDaiMoney(0d);
				cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
				cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
				cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
				cwVouchersDetail_jie.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_jie);

				cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
				totalDao.save(cwVouchers);
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(), yingfagongzi.doubleValue(), 0D);
				jieMoney += yingfagongzi;
				daiMoney += 0F;

				// --*****************凭证分录-贷方********************
				CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
				cwVouchersDetail_dai.setvClass("转");
				cwVouchersDetail_dai.setRemark("计提" + month + "工资");

				// 明细科目需根据科目矩阵图查询
				SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao.getObjectByCondition(
						"from SubBudgetRate where name='工资薪酬' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='应付职工薪酬' and belongLayer=1)");

				// 查找一级科目
				SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
						subBudgetRate.getRootId());
				cwVouchersDetail_dai.setSub(oneLevelsub.getName());
				cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

				// 明细科目
				cwVouchersDetail_dai
						.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()) + "-" + dept);
				cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
				cwVouchersDetail_dai.setJieMoney(0d);
				cwVouchersDetail_dai.setDaiMoney(yingfagongzi);
				cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
				cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
				cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
				cwVouchersDetail_dai.setCwVouchers(cwVouchers);
				cwVouchersDetailSet.add(cwVouchersDetail_dai);
				// 更新贷方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D, yingfagongzi);
				jieMoney += 0F;
				daiMoney += yingfagongzi;

			}
			for (int i = 0; i < wageList.size(); i++) {
				Object[] wageobj = (Object[]) wageList.get(i);
				String dept = (String) wageobj[0];
				Double yingfagongzi_d = (Double) wageobj[1];
				Double yingfagongzi = yingfagongzi_d;
				/******************* 结转研发支出 ************************************/
				if ("信息".equals(dept) || "研发".equals(dept) || "工艺".equals(dept)) {
					SubBudgetRate subBudgetRate_jie_yf = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='研究与开发费' and rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
					CwVouchersDetail cwVouchersDetail_jie_yf = new CwVouchersDetail();
					Set<CwUseDetail> cwUseDetailSet_yf = new HashSet<CwUseDetail>();

					// ***************** 分录--辅助明细********************
					CwUseDetail cwUseDetail_yf = new CwUseDetail();
					cwUseDetail_yf.setPayee(dept);
					cwUseDetail_yf.setUseFor("结转研发工资");
					cwUseDetail_yf.setUsemoney(yingfagongzi);
					cwUseDetail_yf.setAboutNum("");
					cwUseDetail_yf.setPayNum("");
					cwUseDetail_yf.setPayType("结转");
					cwUseDetail_yf.setCwVouchersDetail(cwVouchersDetail_jie_yf);
					cwUseDetailSet_yf.add(cwUseDetail_yf);

					cwVouchersDetail_jie_yf.setRemark("结转研发工资");
					cwVouchersDetail_jie_yf.setvClass("转");
					// 查找一级科目
					SubBudgetRate oneLevelsub_jie_yf = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
							subBudgetRate_jie_yf.getRootId());
					cwVouchersDetail_jie_yf.setSub(oneLevelsub_jie_yf.getName());
					cwVouchersDetail_jie_yf.setSubId(oneLevelsub_jie_yf.getId());
					// 明细科目
					cwVouchersDetail_jie_yf
							.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_jie_yf.getId()) + "-" + dept);
					cwVouchersDetail_jie_yf.setDetailSubId(subBudgetRate_jie_yf.getId());

					cwVouchersDetail_jie_yf.setJieMoney(yingfagongzi);
					cwVouchersDetail_jie_yf.setDaiMoney(0d);
					cwVouchersDetail_jie_yf.setCreateTime(Util.getDateTime());
					cwVouchersDetail_jie_yf.setCreateName(Util.getLoginUser().getName());
					cwVouchersDetail_jie_yf.setCreateCode(Util.getLoginUser().getCode());
					cwVouchersDetail_jie_yf.setCwUseDetails(cwUseDetailSet_yf);
					cwVouchersDetail_jie_yf.setCwVouchers(cwVouchers);
					cwVouchersDetailSet.add(cwVouchersDetail_jie_yf);

					cwVouchers.setCwVouchersDetails(cwVouchersDetailSet);
					totalDao.save(cwVouchers);
					// 更新借方科目余额
					receiptServer.updatesubBudgetRate(subBudgetRate_jie_yf.getId(), yingfagongzi.doubleValue(), 0D);
					jieMoney += yingfagongzi;
					daiMoney += 0F;

					// --*****************凭证分录-贷方********************
					CwVouchersDetail cwVouchersDetail_dai_yf = new CwVouchersDetail();
					cwVouchersDetail_dai_yf.setvClass("转");
					cwVouchersDetail_dai_yf.setRemark("结转研发工资");

					// 明细科目需根据科目矩阵图查询
					SubBudgetRate subBudgetRate_yf = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='工资薪酬' and fatherName='费用化支出' and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");

					// 查找一级科目
					SubBudgetRate oneLevelsub_yf = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
							subBudgetRate_yf.getRootId());
					cwVouchersDetail_dai_yf.setSub(oneLevelsub_yf.getName());
					cwVouchersDetail_dai_yf.setSubId(oneLevelsub_yf.getId());

					// 明细科目
					cwVouchersDetail_dai_yf
							.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_yf.getId()) + "-" + dept);
					cwVouchersDetail_dai_yf.setDetailSubId(subBudgetRate_yf.getId());
					cwVouchersDetail_dai_yf.setJieMoney(0d);
					cwVouchersDetail_dai_yf.setDaiMoney(yingfagongzi);
					cwVouchersDetail_dai_yf.setCreateTime(Util.getDateTime());
					cwVouchersDetail_dai_yf.setCreateName(Util.getLoginUser().getName());
					cwVouchersDetail_dai_yf.setCreateCode(Util.getLoginUser().getCode());
					cwVouchersDetail_dai_yf.setCwVouchers(cwVouchers);
					cwVouchersDetailSet.add(cwVouchersDetail_dai_yf);
					// 更新贷方科目余额
					receiptServer.updatesubBudgetRate(subBudgetRate_yf.getId(), 0D, yingfagongzi);
					jieMoney += 0F;
					daiMoney += yingfagongzi;
				}
			}
			cwVouchers.setJieMoney(jieMoney);
			cwVouchers.setDaiMoney(daiMoney);
			totalDao.save(cwVouchers);

			/************************ 计提社会保险 **************************************/
			CwVouchers cwVouchers_sb = new CwVouchers();
			// 生成编号
			String num_sb = "P" + Util.getDateTime("yyyyMM");
			String hql_finMaxnum_sb = "select max(number) from CwVouchers where number like '%" + num_sb + "%'";
			String maxfkNumber_sb = (String) totalDao.getObjectByCondition(hql_finMaxnum_sb);
			if (maxfkNumber_sb != null && maxfkNumber_sb.length() > 0) {
				String subnum = "";
				Integer maxnum = Integer.parseInt(maxfkNumber_sb.substring(7, maxfkNumber_sb.length())) + 1;
				if (maxnum < 10) {
					subnum += "0000" + maxnum;// 00009
				} else if (maxnum < 100) {
					subnum += "000" + maxnum;// 00099
				} else if (maxnum < 1000) {
					subnum += "00" + maxnum;// 00999
				} else if (maxnum < 10000) {
					subnum += "0" + maxnum;// 09999
				} else {
					subnum += "" + maxnum;
				}
				num_sb += subnum;
			} else {
				num_sb += "00001";
			}
			cwVouchers_sb.setNumber(num_sb);
			cwVouchers_sb.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers_sb.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers_sb.setCreatetime(Util.getDateTime());
			cwVouchers_sb.setUserName(Util.getLoginUser().getName());
			cwVouchers_sb.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers_sb.setCreateName(Util.getLoginUser().getName());
			Double jieMoney_sb = 0d;
			Double daiMoney_sb = 0d;

			Set<CwVouchersDetail> cwVouchersDetailSet_sb = new HashSet<CwVouchersDetail>();
			for (int i = 0; i < wageList.size(); i++) {
				Object[] wageobj = (Object[]) wageList.get(i);
				String dept = (String) wageobj[0];

				Double tongchoujin_d = (Double) wageobj[2];
				Double tongchoujin = Math.abs(tongchoujin_d);
				jieMoney_sb = creatSub(dept, "基本养老保险", "社会保险费", month, tongchoujin, cwVouchers_sb,
						cwVouchersDetailSet_sb, jieMoney_sb, daiMoney_sb, "基本养老保险");
				daiMoney_sb = jieMoney_sb;

				Double yiliaobaoxian_d = (Double) wageobj[3];
				Double yiliaobaoxian = Math.abs(yiliaobaoxian_d);
				jieMoney_sb = creatSub(dept, "医疗保险", "社会保险费", month, yiliaobaoxian, cwVouchers_sb,
						cwVouchersDetailSet_sb, jieMoney_sb, daiMoney_sb, "基本医疗保险");
				daiMoney_sb = jieMoney_sb;

				Double shiyebaoxian_d = (Double) wageobj[4];
				Double shiyebaoxian = Math.abs(shiyebaoxian_d);
				jieMoney_sb = creatSub(dept, "失业保险", "社会保险费", month, shiyebaoxian, cwVouchers_sb,
						cwVouchersDetailSet_sb, jieMoney_sb, daiMoney_sb, "失业保险");
				daiMoney_sb = jieMoney_sb;

				Double gongshangbaoxian_d = (Double) wageobj[5];
				Double gongshangbaoxian = Math.abs(gongshangbaoxian_d);
				jieMoney_sb = creatSub(dept, "工伤保险", "社会保险费", month, gongshangbaoxian, cwVouchers_sb,
						cwVouchersDetailSet_sb, jieMoney_sb, daiMoney_sb, "工伤保险");
				daiMoney_sb = jieMoney_sb;

				Double shengyubaoxian_d = (Double) wageobj[6];
				Double shengyubaoxian = Math.abs(shengyubaoxian_d);
				jieMoney_sb = creatSub(dept, "生育保险", "社会保险费", month, shengyubaoxian, cwVouchers_sb,
						cwVouchersDetailSet_sb, jieMoney_sb, daiMoney_sb, "生育保险");
				daiMoney_sb = jieMoney_sb;

			}
			cwVouchers_sb.setJieMoney(jieMoney_sb);
			cwVouchers_sb.setDaiMoney(daiMoney_sb);
			totalDao.save(cwVouchers_sb);

			/************************ 计提公积金保险 **************************************/
			CwVouchers cwVouchers_gjj = new CwVouchers();
			// 生成编号
			String num_gjj = "P" + Util.getDateTime("yyyyMM");
			String hql_finMaxnum_gjj = "select max(number) from CwVouchers where number like '%" + num_gjj + "%'";
			String maxfkNumber_gjj = (String) totalDao.getObjectByCondition(hql_finMaxnum_gjj);
			if (maxfkNumber_gjj != null && maxfkNumber_gjj.length() > 0) {
				String subnum = "";
				Integer maxnum = Integer.parseInt(maxfkNumber_gjj.substring(7, maxfkNumber_gjj.length())) + 1;
				if (maxnum < 10) {
					subnum += "0000" + maxnum;// 00009
				} else if (maxnum < 100) {
					subnum += "000" + maxnum;// 00099
				} else if (maxnum < 1000) {
					subnum += "00" + maxnum;// 00999
				} else if (maxnum < 10000) {
					subnum += "0" + maxnum;// 09999
				} else {
					subnum += "" + maxnum;
				}
				num_gjj += subnum;
			} else {
				num_gjj += "00001";
			}
			cwVouchers_gjj.setNumber(num_gjj);
			cwVouchers_gjj.setVouchermonth(Util.getDateTime("yyyy-MM"));
			cwVouchers_gjj.setVoucherdate(Util.getDateTime("yyyy-MM-dd"));
			cwVouchers_gjj.setCreatetime(Util.getDateTime());
			cwVouchers_gjj.setUserName(Util.getLoginUser().getName());
			cwVouchers_gjj.setCreateCode(Util.getLoginUser().getCode());
			cwVouchers_gjj.setCreateName(Util.getLoginUser().getName());
			Double jieMoney_gjj = 0d;
			Double daiMoney_gjj = 0d;

			Set<CwVouchersDetail> cwVouchersDetailSet_gjj = new HashSet<CwVouchersDetail>();
			for (int i = 0; i < wageList.size(); i++) {
				Object[] wageobj = (Object[]) wageList.get(i);
				String dept = (String) wageobj[0];
				Double gjj_d = (Double) wageobj[7];
				Double gongjijin = -gjj_d;

				SubBudgetRate subBudgetRate_jie = null;
				if ("信息".equals(dept) || "研发".equals(dept) || "工艺".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao
							.getObjectByCondition("from SubBudgetRate where name='住房公积金' and fatherName='工资薪酬' "
									+ " and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");
				} else if ("总成班".equals(dept) || "加工".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金' and fatherName='民品成本' and rootId=(select id from SubBudgetRate where name='生产成本' and belongLayer=1)");
				} else if ("品质".equals(dept) || "物流".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='制造费用' and belongLayer=1)");
				} else if ("市场".equals(dept)) {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金' and rootId=(select id from SubBudgetRate where name='销售费用' and belongLayer=1)");
				} else {
					subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
				}

				/******************* 冲销 凭证分录 ***********************/

				// ***************** 凭证分录 --借方*********************
				CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
				Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

				// ***************** 分录--辅助明细********************
				CwUseDetail cwUseDetail = new CwUseDetail();
				cwUseDetail.setPayee(dept);
				cwUseDetail.setUseFor("计提" + month + "公积金");
				cwUseDetail.setUsemoney(gongjijin);
				cwUseDetail.setAboutNum("");
				cwUseDetail.setPayNum("");
				cwUseDetail.setPayType("计提");
				cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
				cwUseDetailSet.add(cwUseDetail);

				cwVouchersDetail_jie.setRemark("计提" + month + "公积金");
				cwVouchersDetail_jie.setvClass("转");
				// 查找一级科目
				SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
						subBudgetRate_jie.getRootId());
				cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
				cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
				// 明细科目
				cwVouchersDetail_jie
						.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_jie.getId()) + "-" + dept);
				cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

				cwVouchersDetail_jie.setJieMoney(gongjijin);
				cwVouchersDetail_jie.setDaiMoney(0d);
				cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
				cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
				cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
				cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
				cwVouchersDetail_jie.setCwVouchers(cwVouchers_gjj);
				cwVouchersDetailSet_gjj.add(cwVouchersDetail_jie);

				cwVouchers_gjj.setCwVouchersDetails(cwVouchersDetailSet_gjj);
				totalDao.save(cwVouchers_gjj);
				// 更新借方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(), gongjijin.doubleValue(), 0D);
				jieMoney_gjj += gongjijin;
				daiMoney_gjj += 0F;

				// --*****************凭证分录-贷方********************
				CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
				cwVouchersDetail_dai.setvClass("转");
				cwVouchersDetail_dai.setRemark("计提" + month + "公积金");

				// 明细科目需根据科目矩阵图查询
				SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao.getObjectByCondition(
						"from SubBudgetRate where name='住房公积金' and rootId=(select id from SubBudgetRate where name='应付职工薪酬' and belongLayer=1)");

				// 查找一级科目
				SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
						subBudgetRate.getRootId());
				cwVouchersDetail_dai.setSub(oneLevelsub.getName());
				cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

				// 明细科目
				cwVouchersDetail_dai
						.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()) + "-" + dept);
				cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
				cwVouchersDetail_dai.setJieMoney(0d);
				cwVouchersDetail_dai.setDaiMoney(gongjijin);
				cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
				cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
				cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
				cwVouchersDetail_dai.setCwVouchers(cwVouchers_gjj);
				cwVouchersDetailSet_gjj.add(cwVouchersDetail_dai);
				// 更新贷方科目余额
				receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D, gongjijin);
				jieMoney_gjj += 0F;
				daiMoney_gjj += gongjijin;

			}
			for (int i = 0; i < wageList.size(); i++) {
				Object[] wageobj = (Object[]) wageList.get(i);
				String dept = (String) wageobj[0];
				Double yingfagongzi_d = (Double) wageobj[1];
				Double yingfagongzi = yingfagongzi_d;
				/******************* 结转研发支出 ************************************/
				if ("信息".equals(dept) || "研发".equals(dept) || "工艺".equals(dept)) {
					SubBudgetRate subBudgetRate_jie_yf = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金'  and fatherName='工资薪酬' and  rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
					CwVouchersDetail cwVouchersDetail_jie_yf = new CwVouchersDetail();
					Set<CwUseDetail> cwUseDetailSet_yf = new HashSet<CwUseDetail>();

					// ***************** 分录--辅助明细********************
					CwUseDetail cwUseDetail_yf = new CwUseDetail();
					cwUseDetail_yf.setPayee(dept);
					cwUseDetail_yf.setUseFor("结转研发支出");
					cwUseDetail_yf.setUsemoney(yingfagongzi);
					cwUseDetail_yf.setAboutNum("");
					cwUseDetail_yf.setPayNum("");
					cwUseDetail_yf.setPayType("结转");
					cwUseDetail_yf.setCwVouchersDetail(cwVouchersDetail_jie_yf);
					cwUseDetailSet_yf.add(cwUseDetail_yf);

					cwVouchersDetail_jie_yf.setRemark("结转研发支出");
					cwVouchersDetail_jie_yf.setvClass("转");
					// 查找一级科目
					SubBudgetRate oneLevelsub_jie_yf = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
							subBudgetRate_jie_yf.getRootId());
					cwVouchersDetail_jie_yf.setSub(oneLevelsub_jie_yf.getName());
					cwVouchersDetail_jie_yf.setSubId(oneLevelsub_jie_yf.getId());
					// 明细科目
					cwVouchersDetail_jie_yf
							.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_jie_yf.getId()) + "-" + dept);
					cwVouchersDetail_jie_yf.setDetailSubId(subBudgetRate_jie_yf.getId());

					cwVouchersDetail_jie_yf.setJieMoney(yingfagongzi);
					cwVouchersDetail_jie_yf.setDaiMoney(0d);
					cwVouchersDetail_jie_yf.setCreateTime(Util.getDateTime());
					cwVouchersDetail_jie_yf.setCreateName(Util.getLoginUser().getName());
					cwVouchersDetail_jie_yf.setCreateCode(Util.getLoginUser().getCode());
					cwVouchersDetail_jie_yf.setCwUseDetails(cwUseDetailSet_yf);
					cwVouchersDetail_jie_yf.setCwVouchers(cwVouchers_gjj);
					cwVouchersDetailSet_gjj.add(cwVouchersDetail_jie_yf);

					cwVouchers_gjj.setCwVouchersDetails(cwVouchersDetailSet_gjj);
					totalDao.save(cwVouchers_gjj);
					// 更新借方科目余额
					receiptServer.updatesubBudgetRate(subBudgetRate_jie_yf.getId(), yingfagongzi.doubleValue(), 0D);
					jieMoney_gjj += yingfagongzi;
					daiMoney_gjj += 0F;

					// --*****************凭证分录-贷方********************
					CwVouchersDetail cwVouchersDetail_dai_yf = new CwVouchersDetail();
					cwVouchersDetail_dai_yf.setvClass("转");
					cwVouchersDetail_dai_yf.setRemark("结转研发支出");

					// 明细科目需根据科目矩阵图查询
					SubBudgetRate subBudgetRate_yf = (SubBudgetRate) totalDao.getObjectByCondition(
							"from SubBudgetRate where name='住房公积金' and fatherName='工资薪酬' and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");

					// 查找一级科目
					SubBudgetRate oneLevelsub_yf = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
							subBudgetRate_yf.getRootId());
					cwVouchersDetail_dai_yf.setSub(oneLevelsub_yf.getName());
					cwVouchersDetail_dai_yf.setSubId(oneLevelsub_yf.getId());

					// 明细科目
					cwVouchersDetail_dai_yf
							.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_yf.getId()) + "-" + dept);
					cwVouchersDetail_dai_yf.setDetailSubId(subBudgetRate_yf.getId());
					cwVouchersDetail_dai_yf.setJieMoney(0d);
					cwVouchersDetail_dai_yf.setDaiMoney(yingfagongzi);
					cwVouchersDetail_dai_yf.setCreateTime(Util.getDateTime());
					cwVouchersDetail_dai_yf.setCreateName(Util.getLoginUser().getName());
					cwVouchersDetail_dai_yf.setCreateCode(Util.getLoginUser().getCode());
					cwVouchersDetail_dai_yf.setCwVouchers(cwVouchers_gjj);
					cwVouchersDetailSet_gjj.add(cwVouchersDetail_dai_yf);
					// 更新贷方科目余额
					receiptServer.updatesubBudgetRate(subBudgetRate_yf.getId(), 0D, yingfagongzi);
					jieMoney_gjj += 0F;
					daiMoney_gjj += yingfagongzi;
				}
			}
			cwVouchers_gjj.setJieMoney(jieMoney_gjj);
			cwVouchers_gjj.setDaiMoney(daiMoney_gjj);
			totalDao.save(cwVouchers_gjj);

		}
		return true;
	}

	private Double creatSub(String dept, String detailname, String fatherName, String month, Double sbmoney,
			CwVouchers cwVouchers_sb, Set<CwVouchersDetail> cwVouchersDetailSet_sb, Double jieMoney_sb,
			Double daiMoney_sb, String daiSubName) {

		SubBudgetRate subBudgetRate_jie = null;
		if ("信息".equals(dept) || "研发".equals(dept) || "工艺".equals(dept)) {
			subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
					"from SubBudgetRate where name='" + detailname + "' and fatherName='" + fatherName + "' "
							+ " and rootId=(select id from SubBudgetRate where name='研发支出' and belongLayer=1)");
		} else if ("总成班".equals(dept) || "加工".equals(dept)) {
			subBudgetRate_jie = (SubBudgetRate) totalDao
					.getObjectByCondition("from SubBudgetRate where name='生产人员薪酬' and fatherName='民品成本' "
							+ "and rootId=(select id from SubBudgetRate where name='生产成本' and belongLayer=1)");
		} else if ("市场".equals(dept)) {
			subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition("from SubBudgetRate where name='社会保险'"
					+ " and rootId=(select id from SubBudgetRate where name='销售费用' and belongLayer=1)");
		} else if ("品质".equals(dept) || "物流".equals(dept)) {
			subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition("from SubBudgetRate where name='"
					+ detailname
					+ "' and fatherName='工资' and rootId=(select id from SubBudgetRate where name='制造费用' and belongLayer=1)");
		} else {
			subBudgetRate_jie = (SubBudgetRate) totalDao.getObjectByCondition(
					"from SubBudgetRate where name='" + detailname + "' and fatherName='" + fatherName
							+ "' and rootId=(select id from SubBudgetRate where name='管理费用' and belongLayer=1)");
		}

		/******************* 冲销 凭证分录 ***********************/

		// ***************** 凭证分录 --借方*********************
		CwVouchersDetail cwVouchersDetail_jie = new CwVouchersDetail();
		Set<CwUseDetail> cwUseDetailSet = new HashSet<CwUseDetail>();

		// ***************** 分录--辅助明细********************
		CwUseDetail cwUseDetail = new CwUseDetail();
		cwUseDetail.setPayee(dept);
		cwUseDetail.setUseFor("计提" + month + "社会保险");
		cwUseDetail.setUsemoney(sbmoney);
		cwUseDetail.setAboutNum("");
		cwUseDetail.setPayNum("");
		cwUseDetail.setPayType("计提");
		cwUseDetail.setCwVouchersDetail(cwVouchersDetail_jie);
		cwUseDetailSet.add(cwUseDetail);

		cwVouchersDetail_jie.setRemark("计提" + month + "社会保险");
		cwVouchersDetail_jie.setvClass("转");
		// 查找一级科目
		SubBudgetRate oneLevelsub_jie = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
				subBudgetRate_jie.getRootId());
		cwVouchersDetail_jie.setSub(oneLevelsub_jie.getName());
		cwVouchersDetail_jie.setSubId(oneLevelsub_jie.getId());
		// 明细科目
		cwVouchersDetail_jie.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate_jie.getId()) + "-" + dept);
		cwVouchersDetail_jie.setDetailSubId(subBudgetRate_jie.getId());

		cwVouchersDetail_jie.setJieMoney(sbmoney);
		cwVouchersDetail_jie.setDaiMoney(0d);
		cwVouchersDetail_jie.setCreateTime(Util.getDateTime());
		cwVouchersDetail_jie.setCreateName(Util.getLoginUser().getName());
		cwVouchersDetail_jie.setCreateCode(Util.getLoginUser().getCode());
		cwVouchersDetail_jie.setCwUseDetails(cwUseDetailSet);
		cwVouchersDetail_jie.setCwVouchers(cwVouchers_sb);
		cwVouchersDetailSet_sb.add(cwVouchersDetail_jie);

		cwVouchers_sb.setCwVouchersDetails(cwVouchersDetailSet_sb);
		totalDao.save(cwVouchers_sb);
		// 更新借方科目余额
		receiptServer.updatesubBudgetRate(subBudgetRate_jie.getId(), sbmoney.doubleValue(), 0D);
		jieMoney_sb += sbmoney;
		daiMoney_sb += 0F;

		// --*****************凭证分录-贷方********************
		CwVouchersDetail cwVouchersDetail_dai = new CwVouchersDetail();
		cwVouchersDetail_dai.setvClass("转");
		cwVouchersDetail_dai.setRemark("计提" + month + "社会保险");

		// 明细科目需根据科目矩阵图查询
		SubBudgetRate subBudgetRate = (SubBudgetRate) totalDao
				.getObjectByCondition("from SubBudgetRate where name='" + daiSubName + "' and fatherName='社会保险费' "
						+ "and rootId=(select id from SubBudgetRate where name='应付职工薪酬' and belongLayer=1)");

		// 查找一级科目
		SubBudgetRate oneLevelsub = (SubBudgetRate) totalDao.getObjectById(SubBudgetRate.class,
				subBudgetRate.getRootId());
		cwVouchersDetail_dai.setSub(oneLevelsub.getName());
		cwVouchersDetail_dai.setSubId(oneLevelsub.getId());

		// 明细科目
		cwVouchersDetail_dai.setDetailSub(receiptServer.findBudgetRateNames(subBudgetRate.getId()) + "-" + dept);
		cwVouchersDetail_dai.setDetailSubId(subBudgetRate.getId());
		cwVouchersDetail_dai.setJieMoney(0d);
		cwVouchersDetail_dai.setDaiMoney(sbmoney);
		cwVouchersDetail_dai.setCreateTime(Util.getDateTime());
		cwVouchersDetail_dai.setCreateName(Util.getLoginUser().getName());
		cwVouchersDetail_dai.setCreateCode(Util.getLoginUser().getCode());
		cwVouchersDetail_dai.setCwVouchers(cwVouchers_sb);
		cwVouchersDetailSet_sb.add(cwVouchersDetail_dai);
		// 更新贷方科目余额
		receiptServer.updatesubBudgetRate(subBudgetRate.getId(), 0D, sbmoney);
		jieMoney_sb += 0F;
		daiMoney_sb += sbmoney;
		if (jieMoney_sb.equals(daiMoney_sb)) {
			return daiMoney_sb;
		} else {
			new RuntimeException("借贷不平衡!创建凭证失败");
		}
		return null;
	}

	// 查询所有工资(无分页)
	@SuppressWarnings("unchecked")
	public Object[] findAllWageByMouth(Wage wage) {
		if (wage != null) {
			if (wage.getId() != null && wage.getId() > 0) {
				WagePay wagePay = (WagePay) totalDao.getObjectById(WagePay.class, wage.getId());
				if (wagePay != null) {
					wage.setMouth(wagePay.getMonth());
				}
			}
			String hql = totalDao.criteriaQueries(wage, null, "Id");
			List<Wage> wageList = totalDao.query(hql);
			Double sfgongzi = 0D;
			Double sbgongzi = 0D;
			Double gjjgongzi = 0D;
			Double gsgongzi = 0D;
			Double oldsfgongzi = 0D;
			Double oldsbgongzi = 0D;
			Double oldgjjgongzi = 0D;
			Double oldgsgongzi = 0D;
			String sfsj = "";
			String sbsj = "";
			String gjjsj = "";
			String gssj = "";
			// 比较实发工资、社保、公积金、个税 较上月是否变化
			for (Wage wage2 : wageList) {
				Wage oldMonthWage = (Wage) totalDao.getObjectByCondition(
						"from Wage where userId=? and id <? order by mouth desc", wage2.getUserId(), wage2.getId());
				if (oldMonthWage != null) {

					// 职工薪酬（实发工资）
					if (oldMonthWage.getShifagongzi() < wage2.getShifagongzi()) {
						wage2.setSfsj("↑");
					} else if (oldMonthWage.getShifagongzi() > wage2.getShifagongzi()) {
						wage2.setSfsj("↓");
					} else {
						wage2.setSfsj("―");
					}
					sfgongzi += Math.abs(wage2.getShifagongzi());

					// 社保
					Float oldsbmoney = oldMonthWage.getTongchoujin() + oldMonthWage.getYiliaobaoxian()
							+ oldMonthWage.getShiyebaoxian() + oldMonthWage.getGongshangbaoxian()
							+ oldMonthWage.getShengyubaoxian() + oldMonthWage.getDwtongchoujin()
							+ oldMonthWage.getDwyiliaobaoxian() + oldMonthWage.getDwshiyebaoxian()
							+ oldMonthWage.getDwgongshangbaoxian() + oldMonthWage.getDwshengyubaoxian();
					Float nowsbmoney = wage2.getTongchoujin() + wage2.getYiliaobaoxian() + wage2.getShiyebaoxian()
							+ wage2.getGongshangbaoxian() + wage2.getShengyubaoxian() + wage2.getDwtongchoujin()
							+ wage2.getDwyiliaobaoxian() + wage2.getDwshiyebaoxian() + wage2.getDwgongshangbaoxian()
							+ wage2.getDwshengyubaoxian();
					if (oldsbmoney > nowsbmoney) {
						wage2.setSbsj("↑");
					} else if (oldsbmoney < nowsbmoney) {
						wage2.setSbsj("↓");
					} else {
						wage2.setSbsj("―");
					}
					sbgongzi += Math.abs(nowsbmoney);

					// 公积金
					Float oldgjjmoney = oldMonthWage.getGongjijin() + oldMonthWage.getDwgongjijin();
					Float nowgjjmoney = wage2.getGongjijin() + wage2.getDwgongjijin();
					if (oldgjjmoney > nowgjjmoney) {
						wage2.setGjjsj("↑");
					} else if (oldgjjmoney < nowgjjmoney) {
						wage2.setGjjsj("↓");
					} else {
						wage2.setGjjsj("―");
					}
					gjjgongzi += Math.abs(nowgjjmoney);
					// 个税
					if (oldMonthWage.getYingjiaoshuijin() > wage2.getYingjiaoshuijin()) {
						wage2.setGssj("↑");
					} else if (oldMonthWage.getYingjiaoshuijin() < wage2.getYingjiaoshuijin()) {
						wage2.setGssj("↓");
					} else {
						wage2.setGssj("―");
					}
					gsgongzi += Math.abs(wage2.getYingjiaoshuijin());
				}
			}
			// 查询上月的各项工资数据
			String oldMonth = Util.getLastMonth(wage.getMouth());

			String hql_gzsc = "select sum(shifagongzi) from Wage where mouth=?";// 职工薪酬
			oldsfgongzi = Math.abs((Double) totalDao.getObjectByConditionforDouble(hql_gzsc, oldMonth));

			String hql_gjj = "select sum(gongjijin+dwgongjijin) from Wage where mouth=?";// 公积金
			oldgjjgongzi = Math.abs((Double) totalDao.getObjectByConditionforDouble(hql_gjj, oldMonth));

			String hql_sb = "select sum(tongchoujin+yiliaobaoxian+shiyebaoxian+gongshangbaoxian+shengyubaoxian+"
					+ "dwtongchoujin+dwyiliaobaoxian+dwshiyebaoxian+dwgongshangbaoxian+dwshengyubaoxian) from Wage where mouth=?";// 社保
			oldsbgongzi = Math.abs((Double) totalDao.getObjectByConditionforDouble(hql_sb, oldMonth));

			String hql_gs = "select sum(yingjiaoshuijin) from Wage where mouth=?";// 个税
			oldgsgongzi = Math.abs((Double) totalDao.getObjectByConditionforDouble(hql_gs, oldMonth));

			if (oldsfgongzi < sfgongzi) {
				sfsj = "↑";
			} else if (oldsfgongzi > sfgongzi) {
				sfsj = "↓";
			} else {
				sfsj = "―";
			}
			if (oldsbgongzi < sbgongzi) {
				sbsj = "↑";
			} else if (oldsbgongzi > sbgongzi) {
				sbsj = "↓";
			} else {
				sbsj = "―";
			}
			if (oldgjjgongzi < gjjgongzi) {
				gjjsj = "↑";
			} else if (oldgjjgongzi > gjjgongzi) {
				gjjsj = "↓";
			} else {
				gjjsj = "―";
			}
			if (oldgsgongzi < gsgongzi) {
				gssj = "↑";
			} else if (oldgsgongzi > gsgongzi) {
				gssj = "↓";
			} else {
				gssj = "―";
			}

			String[] allstr = { sfgongzi.toString(), sfsj, sbgongzi.toString(), sbsj, gjjgongzi.toString(), gjjsj,
					gsgongzi.toString(), gssj };

			Object[] obj = { wageList, allstr };
			return obj;
		}
		return null;
	}

	// 根据工号和卡号查询所有工资信息
	@SuppressWarnings("unchecked")
	public Object[] findUserAllWage(String code, String cardId, int pageNo, int pageSize) {
		if (code != null && cardId != null && code.length() > 0 && cardId.length() > 0) {
			String hql = "from Wage where code=? and cardId=?";
			List list = totalDao.findAllByPage(hql, pageNo, pageSize, code, cardId);
			int count = totalDao.getCount(hql, code, cardId);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	// 根据工号和卡号查询实发工资信息
	public Wage findUserSumWage(String code, String cardId) {
		if (code != null && cardId != null && code.length() > 0 && cardId.length() > 0) {
			String hql = "select sum(shifagongzi) from Wage where code=? and cardId=?";
			Object o = totalDao.getObjectByCondition(hql, code, cardId);
			Wage wage = null;
			if (o != null) {
				wage = new Wage();
				wage.setShifagongzi(Float.parseFloat(o.toString()));
			}
			return wage;
		}
		return null;
	}

	// 导出外包工资以及补差工资
	@SuppressWarnings("unchecked")
	public String exportOutsourcing(String exportMouth) {
		String hql = "";
		List list = null;
		if (exportMouth != null && exportMouth.length() > 0) {
			hql = "from Wage where mouth=? order by dept";
			list = totalDao.query(hql, exportMouth);
		} else {
			hql = "from Wage order by dept";
			list = totalDao.query(hql);
		}
		if (list != null && list.size() > 0) {
			// 删除所有已生成的文件
			String excelRealPath = ServletActionContext.getServletContext().getRealPath("/upload/sheet") + "/wage/";
			File folder = new File(excelRealPath);
			String filesName[] = folder.list();
			for (int j = 0; j < filesName.length; j++) {
				File file = new File(excelRealPath + "\\" + filesName[j]);
				if (!file.isDirectory()) {
					file.delete();
				}
			}
			// 开始生成工资条
			String fileName = Util.getDateTime("yyyyMMddhhmmss") + "wageBalance.xls";
			String excelRealPath2 = ServletActionContext.getServletContext().getRealPath("/upload/sheet") + "/wage/"
					+ fileName;
			File target2 = new File(excelRealPath2);
			try {
				WritableWorkbook writeBook;
				writeBook = Workbook.createWorkbook(target2);
				WritableSheet sheet = writeBook.createSheet(exportMouth + "外包工资", 0);
				Label label0 = new Label(0, 0, "员工号");
				Label label1 = new Label(1, 0, "姓名");
				Label label2 = new Label(2, 0, "部门");
				Label label3 = new Label(3, 0, "外包工资");
				Label label4 = new Label(4, 0, "补差工资");
				sheet.addCell(label0);
				sheet.addCell(label1);
				sheet.addCell(label2);
				sheet.addCell(label3);
				sheet.addCell(label4);
				for (int i = 1, len = list.size(); i <= len; i++) {
					Wage wage = (Wage) list.get(i - 1);
					Label contentLabel0 = new Label(0, i, wage.getCode());
					Label contentLabel1 = new Label(1, i, wage.getUserName());
					Label contentLabel2 = new Label(2, i, wage.getDept());
					Label contentLabel3 = new Label(3, i, String.valueOf(wage.getOutsourcing()));
					Label contentLabel4 = new Label(4, i, String.valueOf(wage.getBuchagongzi()));

					sheet.addCell(contentLabel0);
					sheet.addCell(contentLabel1);
					sheet.addCell(contentLabel2);
					sheet.addCell(contentLabel3);
					sheet.addCell(contentLabel4);
				}
				writeBook.write();
				writeBook.close();
				return fileName;
			} catch (Exception e) {
				e.printStackTrace();
				fileName = "导出工资条时出错,请检查后重试!";
			}
		}
		return "不存在查询时间段内的工资数据!";
	}

	// 补差工资
	@SuppressWarnings("unchecked")
	public String allWageBalance(String mouth) {
		String hql = "from Wage where mouth=? and isBucha='yes'";
		String hql2 = "from InsuranceGold where localOrField=? and cityOrCountryside=? and validityStartDate<=? and validityEndDate>=?";
		String message = "";
		int number = 0;
		List<Wage> list = totalDao.query(hql, Util.getLastMonth("yyyy-MM月"));// 查询该月份所有工资信息
		String dateTime = Util.getLastMonth("yyyy-MM-dd");// 上个月的时间(查找)保险缴纳比例信息
		for (Wage wage : list) {
			WageStandard ws = wsServer.findWSByUser(wage.getCode());// 查询员工默认的工资模板
			if (ws != null && ws.getBucha().equals("yes")) {// 如果补差状态为yes的进行补差操作
				InsuranceGold ig = (InsuranceGold) totalDao.getObjectByCondition(hql2, ws.getLocalOrField(),
						ws.getCityOrCountryside(), dateTime, dateTime);// 查询最低工资标准
				if (ig != null && ig.getMinimumWage() != null) {
					float shiji = wage.getGangweigongzi() + wage.getJixiaokaohegongzi() + wage.getJiangjin()
							+ wage.getBaomijintie() + wage.getDianhuabutie() - wage.getYiliaobaoxian()
							- wage.getShiyebaoxian() - wage.getGongjijin();
					if (shiji < ig.getMinimumWage()) {
						wage.setBuchagongzi(ig.getMinimumWage() - shiji);
						boolean bool = totalDao.update(wage);
						if (bool) {
							if (wage.getUserName().length() == 2) {
								message += "员工: " + wage.getUserName() + "，&nbsp;&nbsp;&nbsp;补差工资: "
										+ wage.getBuchagongzi() + "元<br/>";
							} else {
								message += "员工: " + wage.getUserName() + "，补差工资: " + wage.getBuchagongzi() + "元<br/>";
							}

							number++;
						} else {
							message += "为员工" + wage.getUserName() + "补差工资出错!请检查!<br/>";
						}
					}
				}
			}

		}
		if (number > 0) {
			message = "一共为" + number + "员工添加了补差工资!<br/><br/>" + message;
		}
		return message;
	}

	/***
	 * 根据登陆人工号和月份查询并更新承包部留
	 * 
	 * @param wage
	 *            工资对象
	 * @return
	 */
	public boolean updateBuliu(Wage wage) {
		Users user = (Users) ActionContext.getContext().getSession().get(TotalDao.users);
		String hql = "from ContractBonus where code=? and bonusMouth =? and bonusStatus='承包部留'";
		ContractBonus contractBonus = (ContractBonus) totalDao.getObjectByCondition(hql, user.getCode(),
				wage.getMouth());// 根据登陆人工号和月份查询部留信息
		if (contractBonus != null) {
			Float buliuMoney = contractBonus.getTotalMoney() + wage.getGangweigongzi() + wage.getBaomijintie()
					+ wage.getDianhuabutie() + wage.getJixiaokaohegongzi();// 部留=原部留+岗位工资+保密津贴+电话补贴+绩效考核
			contractBonus.setTotalMoney(buliuMoney);
			return totalDao.update(contractBonus);
		}
		return true;
	}

	/***
	 * 检查工资人员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkWage() {
		String fileRealPath = "D:/员工及部门.xls";
		// 开始读取excel表格
		InputStream is;
		StringBuffer noWage = new StringBuffer();// 是否存在工资
		StringBuffer noUsers = new StringBuffer();// 是否存在人员表中
		StringBuffer deptDifferent = new StringBuffer();// 部门不同
		StringBuffer dept = new StringBuffer();// 部门不同
		try {
			is = new FileInputStream(fileRealPath); // 创建文件流
			jxl.Workbook rwb = Workbook.getWorkbook(is);// 创建workBook
			Sheet rs = rwb.getSheet(0);// 获得第一张Sheet表
			int excelcolumns = rs.getRows();// 获得总列数

			for (int i = 0; i < excelcolumns; i++) {
				Cell[] cells = rs.getRow(i);// 获得每i行的所有单元格（返回的是数组）
				String hql = "from Wage where userName=? and mouth='2012-09月'";// 根据名称查询工资
				Wage wage = (Wage) totalDao.getObjectByCondition(hql, cells[0].getContents());
				if (wage == null) {
					noWage.append(cells[0].getContents() + "\n");
					String hql2 = "from Users where name=?";
					Users user = (Users) totalDao.getObjectByCondition(hql2, cells[0].getContents());
					if (user != null) {
						dept.append(user.getDept() + "\n");
						String hql3 = "from WageStandard where code=? and cardId=?";
						WageStandard ws = (WageStandard) totalDao.getObjectByCondition(hql3, user.getCode(),
								user.getCardId());
						if (ws == null) {
							noUsers.append(cells[0].getContents() + "在人员表中存在,工资模版不存在\n");
						} else
							noUsers.append(cells[0].getContents() + "在人员表中存在,工资模版存在\n");
					} else {
						dept.append("不存在\n");
						noUsers.append(cells[0].getContents() + "在人员表中不存在\n");
					}
				} else {
					if (!cells[1].getContents().equals(wage.getDept())) {
						deptDifferent.append(
								cells[1].getContents() + cells[0].getContents() + "系统部门:" + wage.getDept() + "\n");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("不存在工资员工名单:" + noWage);
		System.out.println("不存在工资员工部门:" + dept);
		System.out.println("不存在工资原因:" + noUsers);
		System.out.println("存在工资但部门与系统不匹配员工名单:" + deptDifferent);
		return "";
	}

	/***
	 * 工资汇总
	 * 
	 * @param mouth
	 *            查询月份(为空则查询全部)
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List huizongWage(String mouth) {
		String hql = "select dept,count(*),sum(gangweigongzi),sum(baomijintie),sum(dianhuabutie),sum(jinenggongzi),sum(gonglinggongzi),sum(jiangjin),"
				+ "sum(jixiaokaohegongzi),sum(jiabanfei),sum(other),sum(yingfagongzi),sum(bingshikangdeng),sum(tongchoujin),sum(yiliaobaoxian),"
				+ "sum(shiyebaoxian),sum(gongjijin),sum(wucanfei),sum(shuidianfei),sum(fangzufei),sum(bfgongzi),sum(yingjiaoshuijin),sum(shifagongzi)"
				+ " from Wage where wageStatus='同意'";
		String hql2 = "select count(*),"
				+ "sum(jixiaokaohegongzi),sum(jiabanfei),sum(other),sum(yingfagongzi),sum(bingshikangdeng),sum(tongchoujin),sum(yiliaobaoxian),"
				+ "sum(shiyebaoxian),sum(gongjijin),sum(wucanfei),sum(shuidianfei),sum(fangzufei),sum(bfgongzi),sum(yingjiaoshuijin),sum(shifagongzi)"
				+ " from Wage where wageStatus='同意'";
		if (mouth != null && mouth.length() > 0) {
			hql += " and mouth=? group by dept";
			List list1 = totalDao.query(hql, mouth);
			hql2 += " and mouth=?";
			List list2 = totalDao.query(hql2, mouth);
			Object[] o = (Object[]) list2.get(0);
			Object[] o2 = new Object[o.length + 1];
			o2[0] = "汇总";
			System.arraycopy(o, 0, o2, 1, o.length);// 从一个数组复制到另一个数组
			list1.add(o2);
			return list1;

		}
		hql += " group by dept";
		return totalDao.query(hql);
	}

	/***
	 * 提交状态为"自查"的工资信息为审核
	 * 
	 * @return
	 */
	public int submitWageAudit() {
		// 生成工资审批各种表
		String month = Util.getLastMonth("yyyy-MM月");

		String hql_gzsc = "select sum(shifagongzi) from Wage where mouth=?";// 职工薪酬
		Double shifagongzi = (Double) totalDao.getObjectByConditionforDouble(hql_gzsc, month);

		String hql_gjj = "select sum(gongjijin+dwgongjijin) from Wage where mouth=?";// 公积金
		Double gjj = (Double) totalDao.getObjectByConditionforDouble(hql_gjj, month);

		String hql_sb = "select sum(tongchoujin+yiliaobaoxian+shiyebaoxian+gongshangbaoxian+shengyubaoxian+"
				+ "dwtongchoujin+dwyiliaobaoxian+dwshiyebaoxian+dwgongshangbaoxian+dwshengyubaoxian) from Wage where mouth=?";// 社保
		Double sb = (Double) totalDao.getObjectByConditionforDouble(hql_sb, month);

		String hql_gs = "select sum(yingjiaoshuijin) from Wage where mouth=?";// 个税
		Double gs = (Double) totalDao.getObjectByConditionforDouble(hql_gs, month);

		new WagePay(month, shifagongzi, gjj, sb, gs, totalDao);

		// String hql_gzsc =
		// "select sum(shifagongzi) from Wage where mouth=?";// 职工薪酬
		// Double shifagongzi = (Double) totalDao.getObjectByConditionforDouble(
		// hql_gzsc, month);
		// new WagePay(month, "职工薪酬", shifagongzi, totalDao);
		//
		// String hql_gjj =
		// "select sum(gongjijin+dwgongjijin) from Wage where mouth=?";// 公积金
		// Double gjj = (Double) totalDao.getObjectByConditionforDouble(hql_gjj,
		// month);
		// new WagePay(month, "公积金", gjj, totalDao);
		//
		// String hql_sb =
		// "select
		// sum(tongchoujin+yiliaobaoxian+shiyebaoxian+gongshangbaoxian+shengyubaoxian+"
		// +
		// "dwtongchoujin+dwyiliaobaoxian+dwshiyebaoxian+dwgongshangbaoxian+dwshengyubaoxian)
		// from Wage where mouth=?";//
		// 社保
		// Double sb = (Double) totalDao.getObjectByConditionforDouble(hql_sb,
		// month);
		// new WagePay(month, "社保", sb, totalDao);
		//
		// String hql_gs =
		// "select sum(yingjiaoshuijin) from Wage where mouth=?";// 个税
		// Double gs = (Double) totalDao.getObjectByConditionforDouble(hql_gs,
		// month);
		// new WagePay(month, "个税", gs, totalDao);

		String sql = "update ta_fin_wage set ta_fin_wagestatus='审核' where  ta_fin_wagestatus='自查' and ta_fin_mouth=?";
		return totalDao.createQueryUpdate(null, sql, month);
	}

	/***
	 * 更新工资
	 */
	@Override
	public boolean updateWage(Wage wage) {
		if (wage != null) {
			return totalDao.update(wage);
		}
		return false;
	}

	/***
	 * 通过id查询工资明细
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Wage findWageById(Integer id) {
		if (id != null) {
			return (Wage) totalDao.getObjectById(Wage.class, id);
		} else
			return null;
	}

	@Override
	public List<Wage> getWageByUsers(Integer userId, String months) {
		if (userId != null && months != null && months.length() > 0) {
			List<Wage> wageList = totalDao.findAllByPage(
					" from Wage where userId = ?  and mouth <= '" + months + "月' order by mouth desc ", 0, 12, userId);
			Collections.reverse(wageList);
			return wageList;
		}
		return null;
	}

	@Override
	public Users getUsers(Integer id) {
		if (id != null) {
			return (Users) totalDao.get(Users.class, id);
		}
		return null;
	}

	/***
	 * 工资数据分析
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findWageMonth(Integer userId, String pageStatus) {
		if (userId != null) {
			String find = "";
			String name = "";
			if ("sf".equals(pageStatus)) {
				find = "shifagongzi";
				name = "实发工资(元)";
			} else if ("sb".equals(pageStatus)) {
				find = "(tongchoujin+yiliaobaoxian+shiyebaoxian+gongshangbaoxian+shengyubaoxian+"
						+ "dwtongchoujin+dwyiliaobaoxian+dwshiyebaoxian+dwgongshangbaoxian+dwshengyubaoxian)";
				name = "社保(元)";
			} else if ("gjj".equals(pageStatus)) {
				find = "(gongjijin+dwgongjijin)";
				name = "公积金(元)";
			} else if ("gs".equals(pageStatus)) {
				find = "yingjiaoshuijin";
				name = "个税(元)";
			} else {
				return null;
			}
			List<Object[]> list = totalDao
					.findAllByPage("select " + find + ",mouth from Wage where userId = ?  and mouth <= '"
							+ Util.getDateTime("yyyy-MM月") + "' order by mouth desc ", 0, 12, userId);
			if (list != null && list.size() > 0) {
				List datelist = new ArrayList();
				List timelonglist = new ArrayList();
				List zwslist = new ArrayList();
				float[] nums = new float[list.size()];
				int i = 0;
				for (int j = list.size(); j > 0; j--) {
					Object[] objects = list.get(j - 1);
					Float money = Math.abs(Float.parseFloat(objects[0].toString()));
					String btime = (String) objects[1];
					datelist.add(btime);
					money = Float.parseFloat(String.format("%.2f", money));
					timelonglist.add(money);
					nums[i] = money;
					i++;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				// 求中位数
				Float zws = Util.median(nums);
				for (float f : nums) {
					zwslist.add(zws);
				}
				map.put("timelong", timelonglist);
				map.put("yuefen", datelist);
				map.put("zws", zwslist);
				map.put("name", name);
				return map;
			}
		}
		return null;
	}

	/**
	 * 纤细财务个人
	 */
	@Override
	public Wage wage(String mouth) {
		Wage wages = null;
		try {
			// 当前时间
			String date = Util.getDate();
			String year = date.substring(0, 4);
			/* String mousth=year+"-"+mouth.substring(0, mouth.length()-1); */
			String yearbs = year + "-" + mouth + "-01";

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt1 = df.parse(date);
			Date dt2 = df.parse(yearbs);
			String mouths = null;
			if (dt1.getTime() > dt2.getTime()) {
				mouths = year + "-" + mouth;
			} else {

				mouths = (Integer.parseInt(year) - 1) + "-" + mouth;
			}

			wages = (Wage) totalDao.getObjectByCondition("from Wage where mouth LIKE '%" + mouths + "%'  AND  userId=?",
					Util.getLoginUser().getId());

			List<FundApply> list = totalDao.query(
					"from FundApply WHERE addTime LIKE '%" + mouths + "%' AND userId=? order by addTime desc",
					Util.getLoginUser().getId());
			double jiekuang = 0;
			if (list != null) {
				for (FundApply FundApplylist : list) {

					if (FundApplylist.getPayMoney() != null) {
						double money = FundApplylist.getPayMoney() - FundApplylist.getBackMoney();
						jiekuang = jiekuang + money;

					}
				}
			}
			wages.setZxfjkc(jiekuang);
			
			 if(wages.getBuchagongzi()==null){
				 
				 wages.setBuchagongzi((float) 0);
			 } 

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return wages;
	}

	/**
	 * 个人财务情况
	 */
	@Override
	public Map<Integer, Object> fourmouthgobzi(String code, int userid, int pageNo, int pageSize) {
		// from ta_fin_wage WHERE ta_fin_cardId='566' order by
		// ta_fin_gangweigongzi desc
		String hql = null;
		// 个人资产
		hql = "from Wage  WHERE code=?  order by  mouth desc";
		List<Wage> listInt = totalDao.findAllByPage(hql, pageNo, pageSize, code);

		for (Wage wagelist : listInt) {
			// 借款
			double jiekuang = 0;
			String mouth = wagelist.getMouth().substring(0, wagelist.getMouth().length() - 1);
			List<FundApply> list = totalDao.query(
					"from FundApply WHERE addTime LIKE '%" + mouth + "%' AND userId=? order by addTime desc", userid);
			if (list != null) {

				for (FundApply FundApplylist : list) {

					if (FundApplylist.getPayMoney() != null) {
						double money = FundApplylist.getPayMoney() - FundApplylist.getBackMoney();
						jiekuang = jiekuang + money;

					}
				}
			}
			wagelist.setZxfjkc(jiekuang);

		}

		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, listInt);
		return map;
	}

}