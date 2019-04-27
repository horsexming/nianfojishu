package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.entity.Users;
import com.task.entity.Yusuantianbaobiao;
import com.task.entity.Yusuantianbaototal;
import com.task.util.Util;
import com.task.Server.YusuantianbaobiaoServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;

@SuppressWarnings("unchecked")
public class YusuantianbaobiaoServerImpl implements YusuantianbaobiaoServer {
	private TotalDao totalDao;

	/***
	 * 保存
	 */
	public boolean saveBaobiao(Yusuantianbaobiao yusuantianbaobiao, String statu) {
		Users user = Util.getLoginUser();
		yusuantianbaobiao.setAddtime(Util.getDateTime("yyyy-MM-dd HH:mm:ss"));
		yusuantianbaobiao.setCode(user.getCode());
		yusuantianbaobiao.setName(user.getName());
		yusuantianbaobiao.setBumen(user.getDept());

		yusuantianbaobiao.setZongjine(yusuantianbaobiao.getGudingzichan()
				+ yusuantianbaobiao.getRenlichengbenzengjia()
				+ yusuantianbaobiao.getShiyanfeiyong()
				+ yusuantianbaobiao.getQitafeiyong());
		yusuantianbaobiao.setShengyu(yusuantianbaobiao.getZongjine());
		if (totalDao.save(yusuantianbaobiao)) {
			int id = yusuantianbaobiao.getId();
			int epid;
			try {
				epid = CircuitRunServerImpl.createProcess("年度预算审批",
						Yusuantianbaobiao.class, id, "shenpi", "id",
						"YusuantianbaobiaoAction!findYusuantianbaobiao.action?id="
								+ id, yusuantianbaobiao.getBumen() + "部门的"
								+ yusuantianbaobiao.getNiandu() + "年度预算请您审批!",
						true);
				yusuantianbaobiao.setShenpi("未审批");
				yusuantianbaobiao.setEpid(epid);
				return updateYusuantianbaobiao(yusuantianbaobiao, statu);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/***
	 * 分页查询
	 */
	public List getAllList(int pageNo, int pageSize, String bumen, String niandu) {

		String hql = "from Yusuantianbaobiao where";
		if (bumen != null) {
			hql = hql + "  bumen='" + bumen + "'";
		}
		if (niandu != null) {
			hql = hql + " and niandu='" + niandu + "'";
		}
		hql = hql + " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	public List getAllList(int pageNo, int pageSize, int id) {

		String hql = "from Yusuantianbaobiao where ta_yusuantianbaototal=" + id;
		hql = hql + " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	/***
	 * 获得总数
	 */
	public Integer getcount() {
		String hql = "from Yusuantianbaobiao";
		return this.totalDao.getCount(hql);
	}

	/***
	 * 获得总数
	 */
	@Override
	public Integer getcount2(String bumen, String niandu) {
		String hql = "from Yusuantianbaobiao where";
		if (bumen != null) {
			hql = hql + "  bumen='" + bumen + "'";
		}
		if (niandu != null) {
			hql = hql + " and niandu='" + niandu + "'";
		}
		hql = hql + " order by id desc";
		return this.totalDao.getCount(hql);
	}

	@Override
	public Integer getcount1(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Yusuantianbaobiao where ta_yusuantianbaototal=" + id;
		return this.totalDao.getCount(hql);
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	/**
	 * 查询
	 */
	@Override
	public Yusuantianbaobiao findYusuantianbaobiao(int id) {
		// TODO Auto-generated method stub
		return (Yusuantianbaobiao) this.totalDao.getObjectById(
				Yusuantianbaobiao.class, id);
	}

	/**
	 * 修改
	 */
	@Override
	public boolean updateYusuantianbaobiao(Yusuantianbaobiao yusuantianbaobiao,
			String status) {
		if (status == null || !"hz".equals(status)) {
			Users user = Util.getLoginUser();
			yusuantianbaobiao.setName(user.getName());
			yusuantianbaobiao.setCode(user.getCode());
		}
		yusuantianbaobiao.setZongjine(yusuantianbaobiao.getGudingzichan()
				+ yusuantianbaobiao.getRenlichengbenzengjia()
				+ yusuantianbaobiao.getShiyanfeiyong()
				+ yusuantianbaobiao.getQitafeiyong());
		yusuantianbaobiao.setShengyu(yusuantianbaobiao.getZongjine());
		// yusuantianbaobiao.setKx1(0);
		// yusuantianbaobiao.setKx2(0);
		// yusuantianbaobiao.setKx3(0);
		// yusuantianbaobiao.setKx4(0);
		// yusuantianbaobiao.setKx5(0);
		// yusuantianbaobiao.setKx6(0);
		// yusuantianbaobiao.setKx7(0);
		// yusuantianbaobiao.setKx8(0);
		// yusuantianbaobiao.setKx9(0);
		// yusuantianbaobiao.setKx10(0);
		// yusuantianbaobiao.setKx11(0);
		// yusuantianbaobiao.setKx12(0);
		if (yusuantianbaobiao.getEpid() == null) {
			Integer epid = null;
			try {
				epid = CircuitRunServerImpl.createProcess("年度预算审批",
						Yusuantianbaobiao.class, yusuantianbaobiao.getId(),
						"shenpi", "id",
						"YusuantianbaobiaoAction!findYusuantianbaobiao.action?id="
								+ yusuantianbaobiao.getId(), yusuantianbaobiao
								.getBumen()
								+ "部门的"
								+ yusuantianbaobiao.getNiandu()
								+ "年度预算请您审批!", true);
				yusuantianbaobiao.setShenpi("未审批");
				yusuantianbaobiao.setEpid(epid);
			} catch (Exception e) {
				e.printStackTrace();
				yusuantianbaobiao.setShenpi("打回");
			}
		} else if ("打回".equals(yusuantianbaobiao.getShenpi())) {
			boolean bool = CircuitRunServerImpl
					.updateCircuitRun(yusuantianbaobiao.getEpid());
			if (bool)
				yusuantianbaobiao.setShenpi("未审批");
		}
		return totalDao.update(yusuantianbaobiao);
	}

	/***
	 * 修改支出
	 */
	public boolean updatezcYusuantianbaobiao(double zhichu1, int id, String yuefen) {
		float zhichu = Float.parseFloat(zhichu1+"");
		yuefen = yuefen.substring(yuefen.length() - 2, yuefen.length());
		Yusuantianbaobiao yusuantianbaobiao = this.findYusuantianbaobiao(id);
		yusuantianbaobiao.setShengyu(yusuantianbaobiao.getShengyu() - zhichu);
		Yusuantianbaototal yusuantianbaototal = yusuantianbaobiao
				.getYusuantianbaototal();
		this.xiuGaiSengyu(zhichu, yusuantianbaototal.getId());
		if (yuefen.equals("01"))
			yusuantianbaobiao.setKx1(zhichu + yusuantianbaobiao.getKx1());
		if (yuefen.equals("02"))
			yusuantianbaobiao.setKx2(zhichu + yusuantianbaobiao.getKx2());
		if (yuefen.equals("03"))
			yusuantianbaobiao.setKx3(zhichu + yusuantianbaobiao.getKx3());
		if (yuefen.equals("04"))
			yusuantianbaobiao.setKx4(zhichu + yusuantianbaobiao.getKx4());
		if (yuefen.equals("05"))
			yusuantianbaobiao.setKx5(zhichu + yusuantianbaobiao.getKx5());
		if (yuefen.equals("06"))
			yusuantianbaobiao.setKx6(zhichu + yusuantianbaobiao.getKx6());
		if (yuefen.equals("07"))
			yusuantianbaobiao.setKx7(zhichu + yusuantianbaobiao.getKx7());
		if (yuefen.equals("08"))
			yusuantianbaobiao.setKx8(zhichu + yusuantianbaobiao.getKx8());
		if (yuefen.equals("09"))
			yusuantianbaobiao.setKx9(zhichu + yusuantianbaobiao.getKx9());
		if (yuefen.equals("10"))
			yusuantianbaobiao.setKx10(zhichu + yusuantianbaobiao.getKx10());
		if (yuefen.equals("11"))
			yusuantianbaobiao.setKx11(zhichu + yusuantianbaobiao.getKx11());
		if (yuefen.equals("12"))
			yusuantianbaobiao.setKx12(zhichu + yusuantianbaobiao.getKx12());
		return totalDao.update(yusuantianbaobiao);
	}

	/**
	 * 删除
	 */
	@Override
	public boolean deleteBaobiao(Yusuantianbaobiao yusuantianbaobiao) {
		if (yusuantianbaobiao != null) {
			if (yusuantianbaobiao.getEpid() != null) {
				CircuitRunServerImpl.deleteCircuitRun(yusuantianbaobiao
						.getEpid());
			}
			return totalDao.delete(yusuantianbaobiao);
		}
		return false;
	}

	@Override
	public boolean deleteBaobiao(Yusuantianbaototal yusuantianbaototal) {
		// TODO Auto-generated method stub
		return totalDao.delete(yusuantianbaototal);
	}

	@Override
	public List getZAllList(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Yusuantianbaototal order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		return list;
	}

	// 个人查询 总表自动生成
	public List addZAllList(int pageNo, int pageSize, String bumen) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		String sql = "from Yusuantianbaototal where bumen = ? and niandu = ? ";
		List<Yusuantianbaototal> li = totalDao.query(sql, user.getDept(), Util
				.getDateTime("yyyy"));
		if (li.size() == 0) {
			Yusuantianbaototal yusuantianbaototal = new Yusuantianbaototal();
			yusuantianbaototal.setNiandu(Util.getDateTime("yyyy"));
			yusuantianbaototal.setAddtime(Util
					.getDateTime("yyyy-MM-dd HH:mm:ss"));
			yusuantianbaototal.setXiangmumingda(user.getDept()
					+ Util.getDateTime("yyyy") + "年度预算");
			yusuantianbaototal.setBumen(user.getDept());
			yusuantianbaototal.setCode(user.getCode());
			yusuantianbaototal.setName(user.getName());
			yusuantianbaototal.setShenpi("同意");
			totalDao.save(yusuantianbaototal);
		}
		String hql = "from Yusuantianbaototal where bumen = ? order by id desc";
		List list = totalDao.query(hql, bumen);
		return list;
	}

	@Override
	public Integer getZcount() {
		// TODO Auto-generated method stub
		String hql = "from Yusuantianbaototal";
		return this.totalDao.getCount(hql);
	}

	// 总表 保存
	@Override
	public boolean saveZBaobiao(Yusuantianbaototal yusuantianbaototal) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();

		String sql = "from Yusuantianbaototal where bumen = ? and niandu = ? ";
		List<Yusuantianbaototal> li = totalDao.query(sql, user.getDept(),
				yusuantianbaototal.getNiandu());
		if (li.size() == 0) {
			yusuantianbaototal.setAddtime(Util
					.getDateTime("yyyy-MM-dd HH:mm:ss"));
			yusuantianbaototal.setBumen(user.getDept());
			yusuantianbaototal.setCode(user.getCode());
			yusuantianbaototal.setName(user.getName());
			return totalDao.save(yusuantianbaototal);
		} else {
			return false;
		}
	}

	@Override
	public boolean updateYusuantianbaototal(
			Yusuantianbaototal yusuantianbaototal) {
		// TODO Auto-generated method stub
		Users user = Util.getLoginUser();
		yusuantianbaototal.setBumen(user.getDept());
		yusuantianbaototal.setCode(user.getCode());
		yusuantianbaototal.setName(user.getName());
		return totalDao.update(yusuantianbaototal);
	}

	@Override
	public Yusuantianbaototal findYusuantianbaototal(int id) {
		// TODO Auto-generated method stub
		return (Yusuantianbaototal) this.totalDao.getObjectById(
				Yusuantianbaototal.class, id);
	}

	/***
	 * 打回修改后提交审批
	 */
	public void dahuixiugaishenpi(int epid) {
		CircuitRunServerImpl.updateCircuitRun(epid);
	}

	/***
	 * 根据部门 和 年份 查找明细
	 * 
	 * @return
	 */
	public List findMingxi() {
		Users user = Util.getLoginUser();
		String sql = "from Yusuantianbaototal where bumen = ? and niandu = ?  and shenpi='同意'";
		String hql = "from Yusuantianbaobiao where bumen = ? and niandu = ?  and shenpi='同意' ";
		Integer tcount = totalDao.getCount(sql, user.getDept(), Util
				.getDateTime("yyyy"));
		if (tcount > 0) {
			List<Yusuantianbaobiao> list = totalDao.query(hql, user.getDept(),
					Util.getDateTime("yyyy"));
			List listy = new ArrayList();
			for (Yusuantianbaobiao yusuantianbaobiao : list) {
				if (yusuantianbaobiao.getShenpi().equals("同意")) {
					yusuantianbaobiao.setYusuantianbaototal(null);
					listy.add(yusuantianbaobiao);
				}
			}
			return listy;
		} else {
			return null;
		}
	}

	/***
	 * 审批修改
	 */
	public boolean shenpi(int id, int key) {
		Yusuantianbaobiao yusuantianbaobiao = this.findYusuantianbaobiao(id);
		if (key == 0) {
			yusuantianbaobiao.setShenpi("同意");
			Yusuantianbaototal yusuantianbaototal = yusuantianbaobiao
					.getYusuantianbaototal();
			yusuantianbaototal.setZongshu(yusuantianbaototal.getZongshu()
					+ yusuantianbaobiao.getZongjine());
			yusuantianbaototal.setShengyu(yusuantianbaototal.getShengyu()
					+ yusuantianbaobiao.getZongjine());

		}
		if (key == 1)
			yusuantianbaobiao.setShenpi("打回");
		return totalDao.update(yusuantianbaobiao);
	}

	// 查看需要审批的明细
	@Override
	public Object[] findExamList(Integer cpage, Integer pageSize) {
		// 返回条件 明细ID
		/**
		 * false:未审批 true：已审批
		 * 
		 */
		Map<String, Object> map = CircuitRunServerImpl.findAuditExeNode(
				Yusuantianbaobiao.class, false);
		if (map != null) {
			String hql = "from Yusuantianbaobiao where id in (:entityId)";
			List list = totalDao.find(hql, map, cpage, pageSize);
			Object[] exam = new Object[2];
			Long countLong = totalDao.count("select count(*) " + hql, map);
			int count = Integer.parseInt(countLong.toString());
			exam[0] = count;
			exam[1] = list;
			return exam;
		}
		return null;
	}

	/***
	 * 根据子表的id 查询主表
	 */
	public Yusuantianbaototal findYusuantianbaototalByZ(
			Yusuantianbaobiao yusuantianbaobiao) {
		yusuantianbaobiao = (Yusuantianbaobiao) this.totalDao.getObjectById(
				Yusuantianbaobiao.class, yusuantianbaobiao.getId());
		int id = yusuantianbaobiao.getYusuantianbaototal().getId();
		String hql = "from Yusuantianbaototal where id=?";
		Yusuantianbaototal yusuantianbaototal = (Yusuantianbaototal) totalDao
				.getObjectByCondition(hql, id);
		return yusuantianbaototal;
	}

	/***
	 * 修改总额
	 */
	public boolean xiuGaiZongJine(int id) {
		Yusuantianbaobiao yusuantianbaobiao = this.findYusuantianbaobiao(id);
		Yusuantianbaototal yusuantianbaototal = yusuantianbaobiao
				.getYusuantianbaototal();
		yusuantianbaototal.setZongshu(yusuantianbaototal.getZongshu()
				+ yusuantianbaobiao.getZongjine());
		yusuantianbaototal.setShengyu(yusuantianbaototal.getShengyu()
				+ yusuantianbaobiao.getZongjine());
		return totalDao.update(yusuantianbaototal);
	}

	/***
	 * 修改剩余
	 */
	public boolean xiuGaiSengyu(float key, int id) {
		Yusuantianbaototal yusuantianbaototal = this.findYusuantianbaototal(id);
		yusuantianbaototal.setShengyu(yusuantianbaototal.getShengyu() - key);
		return totalDao.update(yusuantianbaototal);
	}

}
