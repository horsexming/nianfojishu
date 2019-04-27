package com.task.ServerImpl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.TijingServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Bonusmoney;
import com.task.entity.Mentionrecord;
import com.task.entity.MinBalance;
import com.task.entity.Template;
import com.task.entity.Tijiang;
import com.task.entity.Users;
import com.task.entity.singlecar.SingleCar;

public class TijiangServerImpl implements TijingServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 增加体奖表信息
	public boolean savetijing(Tijiang tijing) {
		if (tijing != null) {
			return this.totalDao.save(tijing);
		}
		return false;
	}

	// 查询提奖所有信息
	public List showtijiang(String date, int pageNo, int pageSize) {
		if (date != null) {
			String hql = "from Tijiang where tjtimer='" + date + "' ";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 查询提奖表所有信息的总数
	public int findtijiangcount(String date) {
		String hql = "from Tijiang where tjtimer=? ";
		return totalDao.getCount(hql, date);
	}

	// 添加 提奖记录信息
	public boolean saveMentionrecord(Mentionrecord mentionrecord) {
		if (mentionrecord != null) {
			boolean bool = this.totalDao.save(mentionrecord);
			if (bool) {
				try {
					Users user = (Users) ActionContext.getContext()
							.getSession().get(totalDao.users);
					String processName = "提奖总额审核";
					Integer epId = CircuitRunServerImpl.createProcess(
							processName, Mentionrecord.class, mentionrecord
									.getId(), "mentionstatus", "id",
							mentionrecord.getMentionMonth() + "份的提奖总额请您审核!",
							true, "可提奖");
					// Integer epId = CircuitRunServerImpl.createProcess(191,
					// Mentionrecord.class, mentionrecord.getId(),
					// "mentionstatus", "id", mentionrecord
					// .getMentionMonth()
					// + "份的提奖总额请您审核!", true, "可提奖");
					if (epId != null && epId > 0) {
						mentionrecord.setEpId(epId);
						totalDao.update(mentionrecord);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return bool;
			}
		}
		return false;
	}

	// 根据开始时间和结束时间查询出朗特系列 12030100S03
	public List findltjianhao(String setDate, String endDate) {
		if (setDate != null && endDate != null) {
			String hql5 = "select goodsStoreMarkId,sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
					+ setDate
					+ "' and  '"
					+ endDate
					+ "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId ='12030100S03' group by goodsStoreMarkId ";
			return this.totalDao.query(hql5);
		}
		return null;
	}

	// 根据开始时间和结束查询出朗特系列 12030200S03
	public List findltjianhao2(String setDate, String endDate) {
		if (setDate != null && endDate != null) {
			String hql7 = "select goodsStoreMarkId,sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
					+ setDate
					+ "' and  '"
					+ endDate
					+ "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId ='12030200S03' group by goodsStoreMarkId";
			return this.totalDao.query(hql7);
		}
		return null;
	}

	// 根据开始时间和结束查询出朗特系列 2870026401/E
	public List findltjianhao3(String setDate, String endDate) {
		if (setDate != null && endDate != null) {
			String hql8 = "select goodsStoreMarkId,sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
					+ setDate
					+ "' and  '"
					+ endDate
					+ "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId ='2870026401/E' group by goodsStoreMarkId";
			return this.totalDao.query(hql8);
		}
		return null;
	}

	// 增加相对最小余额表信息
	public boolean saveMinBalance(MinBalance minBalance) {
		if (minBalance != null) {
			return this.totalDao.save(minBalance);
		}
		return false;
	}

	// 根据月份查询出相对最小的余额
	public List findMinBalance(String setDate) {
		if (setDate != null) {
			String nian = setDate.substring(0, 5);// 截取年
			int date = Integer.valueOf(setDate.substring(5, 7));// 截取月份
			int yufen = date - 1;
			String shjian = nian + '0' + yufen;
			String hql = "from MinBalance where minMonth=? ";
			return this.totalDao.query(hql, shjian);
		}
		return null;
	}

	// 根据开始时间查询出提奖记录表当中是否有相同的月份
	public List findyuefen(String setDate) {
		if (setDate != null) {
			String hql = "from Mentionrecord where mentionMonth=?";
			return this.totalDao.query(hql, setDate);
		}
		return null;
	}

	// 根据件号查询出单价计价表
	public List findjijiang() {
		String hql = "from Tijiangprice  where pricemarkId='12030100S03'";
		return this.totalDao.query(hql);
	}

	// 根据月份生成EXCEL
	public List finddateExcel(String date) {
		if (date != null) {
			String hql = "from Tijiang where tjtimer=?";
			return this.totalDao.query(hql, date);
		}
		return null;
	}

	// 根据月份查看所对应的入库记录
	public List findDateGoodStore(String setDate, String endDatetime,
			int pageNo, int pageSize) {
		if (setDate != null && endDatetime != null) {
			String hql = "from GoodsStore where  goodsStoreTime between '"
					+ setDate + " 09:00:00' and '" + endDatetime
					+ " 09:00:00' and goodsStoreWarehouse='成品库'";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 根据月份查看所对应的入库记录 总数
	public int getDateGoodStoreCount(String setDate, String endDatetime) {
		if (setDate != null && endDatetime != null) {
			String hql = "from GoodsStore where  goodsStoreTime between '"
					+ setDate + " 09:00:00' and '" + endDatetime
					+ " 09:00:00' and goodsStoreWarehouse='成品库'";
			return this.totalDao.getCount(hql);
		}
		return 0;
	}

	/****
	 * 根据月份查看所对应的入库记录 总数
	 * 
	 * @param jianhao
	 * @param pichi
	 * @param jiezhiDate
	 * @return
	 */
	@Override
	public Float getDateGSCount(String jianhao, String pichi, String jiezhiDate) {
		if (jianhao != null && pichi != null) {
			if (jiezhiDate == null || jiezhiDate.length() <= 0) {
				jiezhiDate = "2015-07-26 09:00:00";
			}
			String hql = "select sum(goodsStoreCount) from GoodsStore where  goodsStoreTime < ? and goodsStoreMarkId=? and goodsStoreLot=? and goodsStoreWarehouse='成品库'";
			return (Float) totalDao. getObjectByCondition(hql, jiezhiDate, jianhao, pichi);
		}
		return null;
	}

	// 根据件号查询出所对应的入库信息
	public List findjianhao(String jianhao, String setDate, String endDatetime,
			int pageNo, int pageSize) {
		if (jianhao != null) {
			String hql = "from GoodsStore where goodsStoreMarkId='" + jianhao
					+ "'   and goodsStoreTime between '" + setDate
					+ " 09:00:00' and '" + endDatetime
					+ " 09:00:00' and goodsStoreWarehouse='成品库'";
			return this.totalDao.findAllByPage(hql, pageNo, pageSize);
		}
		return null;
	}

	// 根据件号查询出所对应的入库信息 总数
	public int getjianhaoCout(String jianhao, String setDate, String endDatetime) {
		if (jianhao != null) {
			String hql = "from GoodsStore where goodsStoreMarkId='" + jianhao
					+ "'   and goodsStoreTime between '" + setDate
					+ " 09:00:00' and '" + endDatetime
					+ " 09:00:00' and goodsStoreWarehouse='成品库'";
			return this.totalDao.getCount(hql);
		}
		return 0;
	}

	@Override
	public boolean addShizhiJiangjin(Tijiang tijing) {
		// TODO Auto-generated method stub
		tijing.setTjmore("审核");
		tijing.setTjformat("试制奖金");
		tijing.setTjtimer(tijing.getTjmonth());
		return totalDao.save(tijing);
		// if (tijing != null) {
		// Users user = (Users) ActionContext.getContext().getSession().get(
		// TotalDao.users);
		// tijing.setTjmore("审核");
		// tijing.setTjformat("试制奖金");
		// tijing.setTjtimer(tijing.getTjmonth());
		// boolean bool = this.totalDao.save(tijing);
		// if(bool){
		// try {
		// Integer epId = CircuitRunServerImpl.createProcess(190,
		// Tijiang.class, tijing.getTjid(), "tjmore", "id",
		// user.getDept() + "部门的绩效考核模板请审核!", false);
		// if (epId != null && epId > 0) {
		// tijing.setEpId(epId);
		// totalDao.update(tijing);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// return bool;
		// }
		// return false;
	}

	@Override
	public Tijiang getShizhi(Integer id) {
		// TODO Auto-generated method stub
		return (Tijiang) totalDao.getObjectById(Tijiang.class, id);
	}

	@Override
	public Object[] queryShizhijiangjin(Tijiang tijing, Integer cpage,
			Integer Pagesize) {
		// TODO Auto-generated method stub
		String hql = "from Tijiang where tjformat='试制奖金' order by tjmonth desc";
		if (null != tijing) {
			tijing.setTjformat("试制奖金");
			hql = totalDao.criteriaQueries(tijing, "", null)
					+ " order by tjmonth desc";
		}
		Object[] productAarr = new Object[2];
		Integer count = totalDao.query(hql).size();
		List list = totalDao.findAllByPage(hql, cpage, Pagesize);
		productAarr[0] = count;
		productAarr[1] = list;
		return productAarr;

	}

	@Override
	public boolean updateShizhi(Tijiang tijing) {
		// TODO Auto-generated method stub
		// 判断状态
		// System.out.println("==============审批"+tijing.getTjmore());
		tijing.setTjtimer(tijing.getTjmonth());
		if ("同意".equals(tijing.getTjmore())) {
			// ===============================================添加到提奖总额表中
			String hql = "from Mentionrecord where mentionMonth=?";
			if (totalDao.query(hql, tijing.getTjmonth()).size() > 0) {
				Mentionrecord tijiange = (Mentionrecord) totalDao.query(hql,
						tijing.getTjtimer()).get(0);
				tijiange.setMentionshallMoney(tijiange.getMentionshallMoney()
						+ tijing.getTjmoney());
				tijing.setTjmore("提奖");
				totalDao.update(tijing);
				totalDao.update(tijiange);
				return true;
			} else {
				return false;
			}

		} else {
			// tijing.setTjmore("不同意");
			return totalDao.update(tijing);
		}

		// return false;
	}

	public boolean deleteShizhijiangjin(Tijiang tijing) {
		return totalDao.delete(tijing);
	}

}
