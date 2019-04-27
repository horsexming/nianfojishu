package com.task.ServerImpl.zhaobiao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.BeanUtils;
import org.springframework.util.FileCopyUtils;

import sun.rmi.runtime.NewThreadAction;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.Server.zhaobiao.ZhaobiaoServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Category;
import com.task.entity.ClientManagement;
import com.task.entity.DeptNumber;
import com.task.entity.ModuleFunction;
import com.task.entity.OutLib;
import com.task.entity.PassReal;
import com.task.entity.Password;
import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.WorkLogClass;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.gzbj.ProcessGzstore;
import com.task.entity.hegebaobiao.MouthHege;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.renshi.Dimission_Handover;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessInforWWApplyDetail;
import com.task.entity.sop.ProcessTemplate;
import com.task.entity.sop.WaigouOrder;
import com.task.entity.sop.WaigouPlan;
import com.task.entity.system.CircuitRun;
import com.task.util.NumberToCN;
import com.task.util.RtxUtil;
import com.task.util.Upload;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ChargebackNotice;
import com.tast.entity.zhaobiao.Flowdetail;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.Hui_Xi;
import com.tast.entity.zhaobiao.Huikuang;
import com.tast.entity.zhaobiao.HuikuangXiangxi;
import com.tast.entity.zhaobiao.HuikuangXiangxis;
import com.tast.entity.zhaobiao.Nianlilv;
import com.tast.entity.zhaobiao.PrepaymentsApplication;
import com.tast.entity.zhaobiao.PrepaymentsApplicationDetails;
import com.tast.entity.zhaobiao.ZhUser; //import com.tast.entity.zhaobiao.ZhUserDelivery;
import com.tast.entity.zhaobiao.Zhaobiao;
import com.tast.entity.zhaobiao.ZhaobiaoXi;
import com.tast.entity.zhaobiao.ZhaobiaoXis;
import com.tast.entity.zhaobiao.Zhgongxu;
import com.tast.entity.zhaobiao.Zhmoban;
import com.tast.entity.zhaobiao.Zhtoubiao;

@SuppressWarnings( { "unchecked", "unused" })
public class ZhaobiaoServerImpl implements ZhaobiaoServer {
	private TotalDao totalDao;

	public void updategysjiepai(GysMarkIdjiepai g) {
		totalDao.update(g);
	}

	public GysMarkIdjiepai ByIdgysjiepai(Integer id) {
		return (GysMarkIdjiepai) totalDao.getObjectById(GysMarkIdjiepai.class,
				id);
	}

	public Object[] listtianxiejiepai(GysMarkIdjiepai p, Integer cpage,
			Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());
		if (zhUser != null) {
			if (p == null) {
				p = new GysMarkIdjiepai();
			}
			String hql = totalDao.criteriaQueries(p, null) + " and  zhuserId="
					+ zhUser.getId();
			List list = totalDao.findAllByPage(hql, cpage, PageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	public Object[] listbandingjianhao(ProcardTemplate p, Integer cpage,
			Integer PageSize) {
		if (p == null) {
			p = new ProcardTemplate();
		}
		Users user = Util.getLoginUser();
		// if ("供应商".equals(user.getDept())) {
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());
		// }

		String hql = totalDao.criteriaQueries(p, null)
				+ " and  procardStyle='外购' and id not in "
				+ " (select id from ProcardTemplate where id in (select p.id from ProcardTemplate p join p.zhUsers z where  z.id="
				+ zhUser.getId() + "))";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public void banding(int[] select, Integer zhuserid, Float cgbl) {
		/*** 清空对象 */
		ZhUser newZhUser = (ZhUser) totalDao.getObjectById(ZhUser.class,
				zhuserid);
		if (select != null && select.length >= 0) {
			Set<ProcardTemplate> ptSet = new HashSet<ProcardTemplate>();
			for (int i = 0; i < select.length; i++) {
				ProcardTemplate newp = (ProcardTemplate) totalDao
						.getObjectById(ProcardTemplate.class, select[i]);
				ptSet.add(newp);
			}
			newZhUser.setProcardTemplates(ptSet);
			totalDao.update(newZhUser);
			// -------------生成GysMarkIdjiepai
			Users user = Util.getLoginUser();
			String hql1 = "from ZhUser where userid=?";
			ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
					.getId());
			String hql2 = " from ProcardTemplate where id in (select p.id from ProcardTemplate p join p.zhUsers z where z.id=?)";
			List listp = totalDao.query(hql2, newZhUser.getId());
			for (int i = 0; i < listp.size(); i++) {
				ProcardTemplate newp = (ProcardTemplate) listp.get(i);
				GysMarkIdjiepai newGMJ = new GysMarkIdjiepai();
				// ////////////////////////
				String hql15 = " from  GysMarkIdjiepai  where markId=? and zhuserId=?";
				GysMarkIdjiepai old = (GysMarkIdjiepai) totalDao
						.getObjectByCondition(hql15, newp.getMarkId(),
								newZhUser.getId());
				// 新添件号
				if (old == null) {

					newGMJ.setZhuserId(newZhUser.getId());
					newGMJ.setGys(newZhUser.getName());
					newGMJ.setProcardTemplateId(newp.getId());
					newGMJ.setStatus("等待填充");
					newGMJ.setUserId(newZhUser.getUserid());

					newGMJ.setMarkId(newp.getMarkId());// 件号
					newGMJ.setProName(newp.getProName());// 名称
					newGMJ.setMaxCount(0F);// 数量
					newGMJ.setProcardStyle("总成");// 卡片类型
					newGMJ.setProductStyle(newp.getProductStyle());
					newGMJ.setCarStyle(newp.getCarStyle());// 车型
					// ----
					newGMJ.setFatherId(0);// 父类id设置为0
					newGMJ.setBelongLayer(1);// 设置层数为1
					newGMJ.setAddDateTime(Util.getDateTime());// 设置层数为1
					newGMJ.setDailyoutput(0F);// 日产量归零
					newGMJ.setOnePrice(0D);// 单件价格归零
					newGMJ.setUnit(newp.getUnit());// 单位
					newGMJ
							.setSingleDuration(newp.getSingleDuration() == null ? 8F
									: newp.getSingleDuration());// 单班时长
					// ------------------
					newGMJ.setKgliao(newp.getKgliao());
					newGMJ.setWgType(newp.getWgType());
					// -------------------
					totalDao.save(newGMJ);
					newGMJ.setRootId(newGMJ.getId());
					totalDao.update(newGMJ);
				}
			}
			// --------------------------------
			// 撤销的件号 删除GysMarkIdjiepai 信息
			// --------------------------------
			// 撤销的件号 删除GysMarkIdjiepai 信息
			String hql16 = " from  GysMarkIdjiepai  where  zhuserId=?";
			List listg = totalDao.query(hql16, newZhUser.getId());
			for (int i = 0; i < listg.size(); i++) {
				GysMarkIdjiepai oleg = (GysMarkIdjiepai) listg.get(i);
				// -----------------
				String hql17 = " from ProcardTemplate where id in (select p.id from ProcardTemplate p join p.zhUsers z where z.id=?) and markId=?";
				ProcardTemplate oldg = (ProcardTemplate) totalDao
						.getObjectByCondition(hql17, newZhUser.getId(), oleg
								.getMarkId());
				if (oldg == null) {
					totalDao.delete(oleg);
				}
			}
		}

		// ---------------------
	}

	public List listyibangdingjianhao(Integer id) {
		// String hql =
		// "from ProcardTemplate where id in (select p.id from ProcardTemplate p join p.zhUsers z where z.id=?)";
		List<ProcardTemplate> list = totalDao
				.query(
						"from ProcardTemplate where markId in(select markId from  GysMarkIdjiepai where zhuserId=?)",
						id);
		List<ProcardTemplate> back = new ArrayList<ProcardTemplate>();
		List<String> markIdList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			for (ProcardTemplate pt : list) {
				if (!markIdList.contains(pt.getMarkId())) {
					markIdList.add(pt.getMarkId());
					back.add(pt);
				}
			}
		}
		return back;
	}

	public Object[] listProcardTemplate(Integer id, ProcardTemplate p,
			Integer cpage, Integer PageSize) {
		// -----------------------

		// ---------------------------
		if (p == null) {
			p = new ProcardTemplate();
		}
		String hql = totalDao.criteriaQueries(p, null)
				+ " and  procardStyle='外购' and id not in "
				+ " (select  id from ProcardTemplate where id in (select p.id from ProcardTemplate p join p.zhUsers z where z.id="
				+ id + "))";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List ruku(Integer id) {
		String hql = "  from Zhtoubiao where tkong10 in (select id from  ZhaobiaoXi where t10=?) and tkong7 is  not 'N'";
		List list = totalDao.query(hql, id);
		return list;
	}

	public Nianlilv Bynianfenlilv(String nianfen) {
		String hql1 = " from  Nianlilv  where nianfen=?";
		Nianlilv huixi = (Nianlilv) totalDao
				.getObjectByCondition(hql1, nianfen);
		return huixi;
	}

	public void updatenianlilv(Nianlilv nianlilv) {
		totalDao.update(nianlilv);
	}

	public void addnianlilv(Nianlilv nianlilv) {
		totalDao.save(nianlilv);
	}

	public Object[] listlilv(Nianlilv nianlilv, Integer cpage, Integer PageSize) {
		if (nianlilv == null) {
			nianlilv = new Nianlilv();
		}
		String hql = totalDao.criteriaQueries(nianlilv, null);
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public DeptMonthBudget byIdDeptMonthBudget(Integer t10) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addzhaobiaoXi(ZhaobiaoXi zhaobiaoXi) {
		totalDao.save(zhaobiaoXi);
	}

	public void deletezhaobiaoXi(ZhaobiaoXi zhaobiaoXi) {
		totalDao.delete(zhaobiaoXi);
	}

	public Object[] zhtoubiaolisthesuan(Integer id, Zhtoubiao zhtoubiao,
			Integer cpage, Integer PageSize) {
		if (zhtoubiao == null) {
			zhtoubiao = new Zhtoubiao();
		}
		String hql = totalDao.criteriaQueries(zhtoubiao, null)
				+ " and tkong7='同意'  and tkong10=" + id;
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] zhaobiaoXilisthesuan(Integer id, ZhaobiaoXi zhaobiaoXi,
			Integer cpage, Integer PageSize) {
		if (zhaobiaoXi == null) {
			zhaobiaoXi = new ZhaobiaoXi();
		}
		String hql = totalDao.criteriaQueries(zhaobiaoXi, null) + " and t10="
				+ id;
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] zhtoubiaolist(Integer tid, Integer parseInt,
			Integer pageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  Zhtoubiao where  tkong10=" + tid;
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] hesuan(Zhaobiao zhaobiao, Integer cpage, Integer PageSize) {
		if (zhaobiao == null) {
			zhaobiao = new Zhaobiao();
		}
		String hql = totalDao.criteriaQueries(zhaobiao, null)
				+ " and   status='合同审批中' order by moban  desc ";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listzhongbiaoUsers(Zhaobiao zhaobiao, Integer cpage,
			Integer PageSize) {
		if (zhaobiao == null) {
			zhaobiao = new Zhaobiao();
		}
		String hql = totalDao.criteriaQueries(zhaobiao, null)
				+ " and   status in ('同意','合同审批中') order by moban  desc ";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List ByZhmobanId(Integer id) {
		String hql = "  from ZhaobiaoXi where t1=?";
		List list = totalDao.query(hql, id);
		return list;
	}

	public Integer[] listzhongbiaoY(Integer id) {
		String sql = "select t.tname from zh_zhaobiao z,zh_zhaobiaoXi x,zh_toubiao t where z.id=x.t10 and x.id=t.tkong10 and z.id="
				+ id;
		List list2 = totalDao.createQuerySelect(null, sql);
		Integer[] userId = new Integer[list2.size()];
		for (int j = 0; j < list2.size(); j++) {
			Object o = list2.get(j);
			String s = o.toString();
			String hql1 = " from  ZhUser  where name=?";
			ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, s);
			userId[j] = zhUser.getUserid();
		}
		return userId;
	}

	public void updateZhmoban(Zhmoban zhmoban) {
		totalDao.update(zhmoban);
	}

	public Zhmoban ByIdZhmoban(Integer id) {
		return (Zhmoban) totalDao.getObjectById(Zhmoban.class, id);
	}

	public Zhaobiao toUpdatezhaobiao(Integer id) {
		return (Zhaobiao) totalDao.getObjectById(Zhaobiao.class, id);
	}

	public Huikuang BuIdhuikuang(Integer id) {
		return (Huikuang) totalDao.getObjectById(Huikuang.class, id);
	}

	public void Updatehuikuang(Huikuang huikuang) {
		totalDao.update(huikuang);
	}

	public void deletehuikuang(Huikuang huikuang) {
		totalDao.delete(huikuang);
	}

	public Users ByIDUsers(Integer id) {
		return (Users) totalDao.getObjectById(Users.class, id);
	}

	// TODDo
	public void addZhgongxu(Zhgongxu zhgongxu) {
		totalDao.save(zhgongxu);
	}

	public void Updatezhgongxu(Zhgongxu zhgongxu) {
		totalDao.update(zhgongxu);
	}

	public void deletezhgongxu(Zhgongxu zhgongxu) {
		totalDao.delete(zhgongxu);
	}

	public Zhgongxu ZhgongXuByID(Integer id) {
		return (Zhgongxu) totalDao.getObjectById(Zhgongxu.class, id);
	}

	public Object[] chankanshenpijilu(Integer id, Integer cpage,
			Integer PageSize) {
		String hql = " from ta_sys_CircuitRun  where  entityName='Zhaobiao'  and  entityId =?"
				+ id;
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 与算算对接
	public void listToubiaoId(Integer xid) {
		//
		String hql1 = " from  ZhaobiaoXi  where id=?";
		ZhaobiaoXi zhaobiaoXi = (ZhaobiaoXi) totalDao.getObjectByCondition(
				hql1, xid);
		//
		String hql2 = " from  DeptMonthBudget  where id=?";
		DeptMonthBudget deptMonthBudget = (DeptMonthBudget) totalDao
				.getObjectByCondition(hql2, zhaobiaoXi.getT9());
		//
		String hql3 = "select tid from  zh_toubiao  where tkong10=?";
		List list3 = totalDao.createQuerySelect(null, hql3, xid);
		for (int i = 0; i < list3.size(); i++) {
			// HuikuangXiangxi huikuangXiangxi=new HuikuangXiangxi();
			// huikuangXiangxi.setTid(Integer.valueOf(String.valueOf(list.get(i))).intValue());
			String hql4 = " from  HuikuangXiangxi  where hname=? and hxXid=? ";
			HuikuangXiangxi huikuangXiangxi = (HuikuangXiangxi) totalDao
					.getObjectByCondition(hql4, zhaobiaoXi.getT11(), Integer
							.valueOf(String.valueOf(list3.get(i))).intValue());
			// 总额==单价乘以数量
			Float float1 = huikuangXiangxi.getHmoney()
					* Float.parseFloat(zhaobiaoXi.getT2());
			// 总额与预算对比
			if (float1 > deptMonthBudget.getAccountMoney()) {
				String hql5 = " from  Zhtoubiao  where tid=?";
				Zhtoubiao zhtoubiao = (Zhtoubiao) totalDao
						.getObjectByCondition(hql5, Integer.valueOf(
								String.valueOf(list3.get(i))).intValue());
				zhtoubiao.setTkong7("S");
				totalDao.update(zhtoubiao);
			}
		}
	}

	public void delHuiXi(Hui_Xi huiXi) {
		totalDao.delete(huiXi);
	}

	public Hui_Xi listHuiXiByXid(Integer xid) {
		String hql1 = " from  Hui_Xi  where xid=?";
		Hui_Xi huixi = (Hui_Xi) totalDao.getObjectByCondition(hql1, xid);
		return huixi;
	}

	public List listhuikuan() {
		String hql = "  from Hui_Xi ";
		List list = totalDao.query(hql);
		return list;
	}

	public List listByToubiao(Integer hxXid) {
		String hql = "  from HuikuangXiangxi where hxXid=?";
		List list = totalDao.query(hql, hxXid);
		return list;
	}

	public HuikuangXiangxi zhongbiaojine(String t11, Integer id) {
		String hql1 = "from HuikuangXiangxi where hname=? and hxXid=? ";
		HuikuangXiangxi huikuangXiangxi = (HuikuangXiangxi) totalDao
				.getObjectByCondition(hql1, t11, id);
		return huikuangXiangxi;
	}

	// 回款方式管理
	public Object[] chakantoubiaojilu(Integer id, Integer cpage,
			Integer PageSize) {
		String hql = "from Zhtoubiao where tkong10=" + id + "  order by tkong2";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public ZhUser listByUserIDZhUser(Integer userid) {
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser1 = (ZhUser) totalDao.getObjectByCondition(hql1, userid);
		return zhUser1;

	}

	// 回款方式管理
	public Object[] listhuikuanfangshi(Integer cpage, Integer PageSize) {
		String hql = "from Huikuang where 1=1";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public void addhuikuan(Huikuang huikuang) {
		totalDao.save(huikuang);
	}

	// 查看 投标金额
	public List chakanjineByX(Integer xid) {
		String hql = "  from HuikuangXiangxi where hxXid=?";
		List list = totalDao.query(hql, xid);
		return list;
	}

	// 添加回款详细
	public void addhuikuangxaingxi(Integer id, String[] kemus, float[] moneys,
			String danwei) {
		for (int i = 0; i < kemus.length; i++) {
			HuikuangXiangxi huikuangXiangxi = new HuikuangXiangxi();
			huikuangXiangxi.setHname(kemus[i]);
			huikuangXiangxi.setHxXid(id);
			huikuangXiangxi.setHmoney(moneys[i]);
			huikuangXiangxi.setDanwei(danwei);
			totalDao.save(huikuangXiangxi);
		}
	}

	// 把 Hui_xi的回款方式装进list集合中
	public List toaddhuikuangxiangxi(Integer id) {
		String hql1 = " from  Hui_Xi  where xid=?";

		Hui_Xi huiXi = (Hui_Xi) totalDao.getObjectByCondition(hql1, id);
		List list2 = new ArrayList();
		String[] indea = huiXi.getF1().split(",");
		for (int i = 0; i < indea.length; i++) {
			list2.add(indea[i]);
		}
		return list2;
	}

	// 确定回款方式
	public List quedinghuikuanfangshi(Integer id) {
		String hql1 = "select * from zh_zhaobiaoXi where id  not in ( select xid from zh_Hui_Xi)  and   t10=?";
		List list = totalDao.createQuerySelect(null, hql1, id);
		return list;
	}

	// 查询是否还存在未设置回款方式的
	public List tijiaohuikuangjieguo(Integer id) {
		String hql1 = "select * from zh_zhaobiaoXi  where t11 is null and  t10=?";
		List list = totalDao.createQuerySelect(null, hql1, id);
		return list;
	}

	// 查询回款方式
	public List listhuikuang() {
		String hql = "  from Huikuang where 1=1";
		List list = totalDao.find(hql);
		return list;
	}

	public List listzhmoban() {
		String hql = "  from Zhmoban where 1=1";
		List list = totalDao.find(hql);
		return list;
	}

	/*
	 * 财务选择回款方式列表
	 */
	public Object[] listcaiwu(Integer cpage, Integer PageSize) {
		String hql = "from Zhaobiao where status='F' or  status='G'";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/*
	 * 根据zhaobiao.id查询所有中标记录
	 */
	public List listzhtoubiaoY(Integer zhaobiaoid) {
		String hql1 = "select t.tid from zh_toubiao t,zh_zhaobiao z, zh_zhaobiaoXi x where z.id=x.t10 and x.id=t.tkong10 and t.tkong7='Y' and z.id=?";
		List list = totalDao.createQuerySelect(null, hql1, zhaobiaoid);
		return list;
	}

	public List tochakanhetong(Integer id) {
		String hql1 = "select t.* from zh_toubiao t,zh_zhaobiao z, zh_zhaobiaoXi x where z.id=x.t10 and x.id=t.tkong10 and  t.tkong7 in ('审核中','同意','Y') and z.id=?";
		List list = totalDao.createQuerySelect(null, hql1, id);
		return list;
	}

	public Zhtoubiao ByXiBY(Integer id) {
		String hql1 = "from Zhtoubiao where tkong7='Y' and tkong10=?";
		Zhtoubiao zhtoubiao = (Zhtoubiao) totalDao.getObjectByCondition(hql1,
				id);
		return zhtoubiao;
	}

	public List listBymobanName(String mobanname) {
		String hql1 = "from Zhmoban  where  name=?";
		Zhmoban zhmoban = (Zhmoban) totalDao.getObjectByCondition(hql1,
				mobanname);
		// String s;
		// s=mobanname.substring(0,2);//截取s中从begin开始至end结束时的字符串，并将其赋值给s;
		String hql = "  from ZhUser where  cclass like  '"
				+ zhmoban.getClasse() + "' and blackliststauts='正常使用'";
		List list = totalDao.find(hql);
		return list;

	}

	public List listUser() {
		String hql = "  from Users where dept ='供应商'  and id not in(select userid from ZhUser  where userid is not null) ";
		List list = totalDao.find(hql);
		return list;
	}

	public List<Category> findCategoryByType(String type) {
		String hql = "from Category where type = ?";
		return totalDao.query(hql, type);
	}

	public List chakanflowdetail(Integer id) {
		String hql = "  from Flowdetail where sqdid=?";
		List list = totalDao.query(hql, id);
		return list;
	}

	@Override
	public ZhUser listByIdZhUser(Integer id) {
		ZhUser zhuser = (ZhUser) totalDao.getObjectById(ZhUser.class, id);
		if (zhuser.getUserid() != null) {
			Users user = (Users) totalDao.get(Users.class, zhuser.getUserid());
			zhuser.setUsercode(user.getCode());
		}
		return zhuser;
	}

	public ZhUser listByUidZhUser(Integer id) {
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser1 = (ZhUser) totalDao.getObjectByCondition(hql1, id);
		return zhUser1;
	}
	public String yanzhengys(){
		String hql ="select valueCode from CodeTranslation where type = 'sys' and keyCode='供应商认证'";
		return (String) totalDao.getObjectByCondition(hql);
	}
	public Object[] findAll(ZhUser zhUser, Integer cpage, Integer PageSize) {
		if (zhUser == null) {
			zhUser = new ZhUser();
		}
		// if (zhUser.getUsercode() != null && !"".equals(zhUser.getUsercode()))
		// {
		// String hql = "select id from Users where code=?";
		// Integer id = (Integer) totalDao.getObjectByCondition(hql, zhUser
		// .getUsercode());
		// zhUser.setUsercode(null);
		// zhUser.setUserid(id);
		// }
		String hql = totalDao.criteriaQueries(zhUser, null);
		// String hql = "  from ZhUser where blackliststauts='正常使用'";

		List list = totalDao.findAllByPage(hql + "ORDER BY id DESC ", cpage,
				PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;

	}

	public Object[] findAllZhUser(ZhUser zhUser) {
		if (zhUser == null) {
			zhUser = new ZhUser();
		}
		String hql = "from ZhUser";
		List list = totalDao.query(hql, null);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;

	}

	@Override
	public Object[] serachZhUserNameAndId(ZhUser zhUser) {
		if (zhUser == null) {
			zhUser = new ZhUser();
		}
		String hql = "select id,cmp from ZhUser where cmp like \'%"
				+ zhUser.getCmp() + "%\'";
		// List list =totalDao.queryList(hql);
		List list = totalDao.query(hql, null);
		Object[] o = { list };
		return o;

	}

	@Override
	public Object[] findSupplierGoods(GysMarkIdjiepai p) {
		if (p == null) {
			p = new GysMarkIdjiepai();
		}
		String hql = totalDao.criteriaQueries(p, null);
		List list = totalDao.query(hql, null);
		Object[] o = { list };
		return o;
	}

	public Object[] listzhaobiao1(Zhaobiao zhaobiao, Integer cpage,
			Integer PageSize) {
		if (zhaobiao == null) {
			zhaobiao = new Zhaobiao();
		}
		String hql = totalDao.criteriaQueries(zhaobiao, null)
				+ "  order by moban  desc";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public ZhUser byzhUserName(String name) {
		String hql1 = "from ZhUser where name=?";
		ZhUser zhUser1 = (ZhUser) totalDao.getObjectByCondition(hql1, name);
		return zhUser1;
	}

	public String addUser(ZhUser z) {
		z.setBlackliststauts("正常使用");
		// 判断供应商编号是否存在
		if (z.getUsercode() != null && !"".equals(z.getUsercode())) {
			int i = totalDao.getCount("from ZhUser where usercode = ?", z
					.getUsercode());
			if (i > 0)
				return "供应商编号已存在，添加失败！";
			int i2 = totalDao.getCount("from Users where code = ?", z
					.getUsercode());
			if (i2 > 0)
				return "人员信息表中，该工号已存在，添加失败！请更换供应商编号。";
			int i3 = totalDao.getCount("from ZhUser where cmp = ?", z.getCmp());
			if (i3 > 0)
				return "供应商名称已存在，添加失败！";
		} else {
			return "供应商编号为空，添加失败！";
		}
		if (totalDao.save(z)) {
			String hql = "from ZhUser where userId is null";
			List<ZhUser> list = totalDao.query(hql);
			if (list != null && list.size() > 0) {
				boolean flag = false;
				for (ZhUser zhUser : list) {
					Users User = new Users();
					User.setCode(zhUser.getUsercode());
					User.setName(zhUser.getCperson());
					User.setMore(zhUser.getCmp());
					User.setSex("男");
					User.setDuty("供应商");
					User.setOnWork("在职");
					User.setInternal("否");
					zhUser.setAddTime(Util.getDateTime());// 添加时间
					String hql_dn = "from DeptNumber where deptNumber=?";
					DeptNumber deptNumber = (DeptNumber) totalDao
							.getObjectByCondition(hql_dn, "gys");// 查询部门编号
					if (deptNumber == null) {
						deptNumber = new DeptNumber();
						deptNumber.setDept("部门");
						deptNumber.setDeptNumber("gys");
						deptNumber.setFatherId(1);
						deptNumber.setBelongLayer(2);
						totalDao.save(deptNumber);
					}
					if (deptNumber != null) {
						User.setDept(deptNumber.getDept());
						User.setDeptId(deptNumber.getId());
						Password password = new Password();
						password
								.setPassword("e10adc3949ba59abbe56e057f20f883e");// 默认密码(123456)
						password.setDeptNumber("gys");
						password.setPhoneNumber(zhUser.getCmobile());
						password.setPresentAddress(zhUser.getCompanydz());
						password.setUserStatus("完成");
						password.setUser(User);
						User.setPassword(password);
						flag = totalDao.save(User);
						zhUser.setUserid(User.getId());
						flag = totalDao.update(zhUser);
					}
				}
			}
			String wgType = z.getCclass();
			Set<Category> categorySet = new HashSet<Category>();
			if (wgType != null && !"".equals(wgType)) {
				wgType = wgType.replaceAll("；", ";");
				String[] strs = wgType.split(";");
				if (strs.length > 0) {
					for (int i = 0; i < strs.length; i++) {
						String hql_wgType = " from Category where name =?";
						Category category = (Category) totalDao
								.getObjectByCondition(hql_wgType, strs[i]);
						if (category != null) {
							categorySet.add(category);
						}
					}
				}
				z.setCategoryset(categorySet);
				totalDao.update(z);
			}
			return "添加成功！";
		}
		return "添加失败！";
	}

	public String deletezhUser(ZhUser zhUser) {
		String msg = "";
		ZhUser zhu = (ZhUser) totalDao.getObjectById(ZhUser.class, zhUser
				.getId());
		if (zhu != null) {
			String time = Util.getDateTime();
			List<Price> priceList = totalDao
					.query(
							" from Price where gysId =?  and pricePeriodStart >= '"
									+ time
									+ "' and ( pricePeriodEnd<= '"
									+ time
									+ "' or pricePeriodEnd is null or pricePeriodEnd = ''  )",
							zhu.getId());
			List<GysMarkIdjiepai> gysList = totalDao.query(
					" from GysMarkIdjiepai where zhuserId =? ", zhu.getId());
			if ((priceList == null || priceList.size() == 0)
					&& (gysList == null || gysList.size() == 0)) {
				// 更新供应商用户信息
				Users users = (Users) totalDao.getObjectByCondition(
						"from Users where code = ?", zhu.getUsercode());
				if (users != null) {
					users.setWorklogClass(null);// 日志类别(一对多)
					users.setModuleFunction(null);// 模块功能(多对多)
					users.setProcessGzstore(null);// 工序（多对多）
					users.setAccessEquipments(null);// 门禁设备（多对多）
					totalDao.update(users);
					totalDao.delete(users);
				}
				totalDao.delete(zhu);
				return "删除成功！";
			} else {
				if (priceList != null && priceList.size() > 0) {
					msg += "删除失败！该供应商与下列件号的价格相关联:";
					int count = 0;
					for (Price price : priceList) {
						if (count % 5 == 0 && count != 0) {
							msg += price.getPartNumber() + "; ";
						} else {
							msg += price.getPartNumber() + "; ";
						}

						count++;
					}
				}
				if (gysList != null && gysList.size() > 0) {
					msg += "删除失败！该供应商与下列件号的供应商产品相关联:";
					int count = 0;
					for (GysMarkIdjiepai gysMarkIdjiepai : gysList) {
						if (count % 5 == 0 && count != 0) {
							msg += "" + gysMarkIdjiepai.getMarkId() + "; ";
						} else {
							msg += "" + gysMarkIdjiepai.getMarkId() + "; ";
						}
						count++;
					}
				}
			}
		} else {
			msg = "供应商不存在，删除失败！";
		}
		return msg;
	}

	public String updatezhUser(ZhUser zhUser) {
		ZhUser zhuser = (ZhUser) totalDao.getObjectById(ZhUser.class, zhUser
				.getId());
		String b1 = "";
		if (zhuser != null) {
			if (zhUser.getUsercode() != null
					&& !"".equals(zhUser.getUsercode())) {
				if (!zhuser.getUsercode().equals(zhUser.getUsercode())) {
					b1 = zhuser.getUsercode();
					int i = totalDao.getCount("from ZhUser where usercode = ?",
							zhUser.getUsercode());
					if (i > 0)
						return "供应商编号重复，修改失败！";
				}
			} else {
				return "供应商编号为空，修改失败！";
			}
		} else {
			return "供应商为空，修改失败！";
		}
		BeanUtils.copyProperties(zhUser, zhuser, new String[] { "id", "userid",
				"addTime" });
		String wgType = zhUser.getCclass();
		Set<Category> categorySet = new HashSet<Category>();
		if (wgType != null && !"".equals(wgType)) {
			String[] strs = wgType.split(";");
			if (strs.length > 0) {
				for (int i = 0; i < strs.length; i++) {
					String hql_wgType = " from Category where name =?";
					Category category = (Category) totalDao
							.getObjectByCondition(hql_wgType, strs[i]);
					if (category != null) {
						categorySet.add(category);
					}
				}
			}
			zhuser.setCategoryset(categorySet);
		}
		List<WaigouOrder> waigouOrderList = totalDao
				.query(
						"from WaigouOrder where gysId =? and status in('待核对','待审批','待通知')",
						zhUser.getId());
		if (waigouOrderList != null && waigouOrderList.size() > 0) {
			for (WaigouOrder wo : waigouOrderList) {
				wo.setGysName(zhUser.getCmp());
				wo.setGhAddress(zhUser.getCompanydz());
				wo.setLxPeople(zhUser.getCperson());
				wo.setPayType(zhUser.getFkfs());
				if (zhUser.getCmobile() != null
						&& zhUser.getCmobile().length() > 0) {
					wo.setGysPhone(zhUser.getCmobile());
				} else {
					wo.setGysPhone(zhUser.getCtel());
				}
				totalDao.update(wo);
				Set<WaigouPlan> wgPlanList = wo.getWwpSet();
				if (wgPlanList != null && wgPlanList.size() > 0) {
					for (WaigouPlan wgplan : wgPlanList) {
						if (wgplan.getGysId() != null
								&& wgplan.getGysId().equals(zhUser.getId())) {
							wgplan.setGysName(zhUser.getCmp());
							totalDao.update(wgplan);
							if (wgplan.getWwDetailId() != null) {
								ProcessInforWWApplyDetail wwd = (ProcessInforWWApplyDetail) totalDao
										.getObjectById(
												ProcessInforWWApplyDetail.class,
												wgplan.getWwDetailId());
								wwd.setGysName(zhUser.getCmp());
								totalDao.update(wwd);
							}
						}
					}
				}
			}
		}
		if (totalDao.update(zhuser)) {
			if (!"".equals(b1)) {
				// 更新供应商用户信息
				Users users = (Users) totalDao.getObjectByCondition(
						"from Users where code = ?", b1);
				if (users != null) {
					users.setCode(b1);
					totalDao.update(users);
				}
			}
			return "true";
		} else {
			return "修改失败！";
		}
	}

	public Object[] listmoban(Integer cpage, Integer PageSize) {
		String hql = "from Zhmoban where 1=1";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public void addMoban(Zhmoban zhmoban) {
		zhmoban.setEtime(Util.getDateTime());
		// String hql1 = "select top 1  *  from zh_moban order by etime  desc ";
		// Zhmoban zhmoban1 = (Zhmoban) totalDao.getObjectByCondition(hql1);
		// zhmoban.setPaihao("Moban"+zhmoban1.getId()+1+Util.getDateTime());
		// zhmoban.setXuhao((zhmoban1.getId().intValue()+1)+"");

		String sql = "select top 1  *  from zh_moban order by etime  desc ";
		List list = totalDao.createQuerySelect(null, sql);
		Object[] obj = (Object[]) list.get(0);
		zhmoban.setPaihao("Moban"
				+ (Integer.valueOf(String.valueOf(obj[0])).intValue() + 1)
				+ Util.getDateTime());
		zhmoban
				.setXuhao((Integer.valueOf(String.valueOf(obj[0])).intValue() + 1)
						+ "");
		totalDao.save(zhmoban);
	}

	public void deleteMoban(Zhmoban zhmoban) {
		totalDao.delete(zhmoban);
	}

	public List listDept() {
		String hql = "  from Zhmoban where 1=1";
		List list = totalDao.find(hql);
		return list;
	}

	public Object[] listzhaobiao(Integer cpage, Integer PageSize) {
		String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List toaddzhaobiao() {
		String hql = "  from Zhmoban where 1=1";
		List list = totalDao.find(hql);
		return list;
	}

	public Zhaobiao listById(Integer id) {
		return (Zhaobiao) totalDao.getObjectById(Zhaobiao.class, id);
	}

	public void addxi(ZhaobiaoXis ms, Integer id1) {
		Zhaobiao zb = (Zhaobiao) totalDao.getObjectById(Zhaobiao.class, id1);
		String[] yuefen = zb.getMoban().split("-");
		String hql1 = " from  Nianlilv  where nianfen like '%" + yuefen[0]
				+ "%' ";
		Nianlilv huixi = (Nianlilv) totalDao.getObjectByCondition(hql1);

		for (int i = 0; i < ms.getT1().length; i++) {
			int zhmobanId = ms.getT1()[i];
			Zhmoban zhmoban = (Zhmoban) totalDao.getObjectById(Zhmoban.class,
					zhmobanId);
			int dmbId = ms.getT9()[i];
			DeptMonthBudget dmb = (DeptMonthBudget) totalDao.getObjectById(
					DeptMonthBudget.class, dmbId);

			ZhaobiaoXi mx = new ZhaobiaoXi();
			// mx.setId(ms.getId()[i]);
			// mx.setT1(id1);
			mx.setT1(ms.getT1()[i]);
			mx.setT2(ms.getT2()[i]);
			mx.setT3(ms.getT3()[i]);
			mx.setT4(ms.getT4()[i]);
			mx.setT5(ms.getT5()[i]);
			mx.setT6(ms.getT6()[i]);
			mx.setLilv(huixi.getLilv());
			mx.setT7("N");
			mx.setT8(ms.getT8()[i]);
			/*
			 * // * //
			 */
			// String attachmentName = "";
			// if (t8 != null && t8.length > 0) {
			// for (int j = 0; i < t8.length; i++) {
			// String fileName =mx.getT8().replaceAll("/",
			// "").trim()
			// + new SimpleDateFormat("yyyyMMddHHmmss")
			// .format(new Date())
			// + (t8FileName[j]
			// .substring(t8FileName[j]
			// .lastIndexOf(".")));
			// if (i > 0) {
			// attachmentName += "|" + fileName;
			// } else {
			// attachmentName += fileName;
			// }
			// attachmentName.trim();
			// // 上传到服务器
			// String fileRealPath = ServletActionContext
			// .getServletContext().getRealPath("/zhaobiao/fujian")
			// + "/" + fileName;
			// File file = new File(fileRealPath);
			// FileCopyUtils.copy(t8[i], file);
			//					
			//
			// // 备份到项目
			// String beiFenfileRealPath =
			// "D:/WorkSpace/HHTask/WebRoot/upload/file"
			// + "/" + fileName;
			// File beiFenFile = new File(beiFenfileRealPath);
			// try {
			// FileCopyUtils.copy(t8[i], beiFenFile);
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			// }
			// } else {
			// attachmentName = findAttName(price.getContractNumber());
			// if (attachmentName == null || attachmentName.equals("")
			// || attachmentName.length() <= 0) {
			// return false;
			// }
			// }
			//			
			/*
			 * 
			 */
			mx.setT9(ms.getT9()[i]);
			mx.setT10(id1);

			mx.setZhmoban(zhmoban);
			mx.setDeptMonthBudget(dmb);

			totalDao.save(mx);
		}
	}

	public DeptMonthBudget listByIdDeptMonthBudget(Integer id) {
		return (DeptMonthBudget) totalDao.getObjectById(DeptMonthBudget.class,
				id);
	}

	public List listmoban() {
		String hql = "  from DeptMonthBudget where   1=1  ";
		List list = totalDao.find(hql);
		return list;
	}

	public void update(Zhaobiao zhaobiao) {
		totalDao.update(zhaobiao);
	}

	public void updatehuikuangXiangxi(HuikuangXiangxis huikuangXiangxis) {
		for (int i = 0; i < huikuangXiangxis.getDanwei().length; i++) {
			HuikuangXiangxi huikuangXiangxi1 = new HuikuangXiangxi();

			huikuangXiangxi1.setHxid(huikuangXiangxis.getHxid()[i]);
			huikuangXiangxi1.setHname(huikuangXiangxis.getHname()[i]);
			huikuangXiangxi1.setHxXid(huikuangXiangxis.getHxXid()[i]);
			huikuangXiangxi1.setHmoney(huikuangXiangxis.getHmoney()[i]);
			huikuangXiangxi1.setDanwei(huikuangXiangxis.getDanwei()[i]);
			totalDao.update(huikuangXiangxi1);

		}
	}

	public void updateDeptMonthBudget(DeptMonthBudget deptMonthBudget) {
		totalDao.update(deptMonthBudget);
	}

	public List listyusuan(DeptMonthBudget deptMonthBudget) {
		Users user = Util.getLoginUser();
		String hql = "select distinct userDept  from DeptMonthBudget where   1=1  ";
		List list = totalDao.find(hql);
		return list;
	}

	public List listDept(String userDept) {
		String hql = "select distinct  budgetMonth from DeptMonthBudget where userDept=?";
		List<DeptMonthBudget> list = totalDao.query(hql, userDept);
		// List newList = new ArrayList();
		// for (DeptMonthBudget deptMonthBudget : list) {
		// deptMonthBudget.setSubMonthMoney(null);
		// newList.add(deptMonthBudget);
		// }
		return list;
	}

	public List findAllDept() {
		String hql = "select distinct userDept  from DeptMonthBudget  ";
		return totalDao.query(hql);
		// List newList = new ArrayList();
		// for (DeptMonthBudget deptMonthBudget : list) {
		// deptMonthBudget.setSubMonthMoney(null);
		// newList.add(deptMonthBudget);
		// }
		// return newList;
	}

	public List listMoth(String name, String userDept) {
		String hql = "from DeptMonthBudget where budgetMonth=? and userDept=?";
		List<DeptMonthBudget> list = totalDao.query(hql, name, userDept);
		List newList = new ArrayList();
		for (DeptMonthBudget deptMonthBudget : list) {
			deptMonthBudget.setSubMonthMoney(null);
			newList.add(deptMonthBudget);
		}
		return newList;
	}

	/*
	 * 1300 (non-Javadoc)审核
	 * 
	 * @see
	 * com.task.Server.zhaobiao.ZhaobiaoServer#addzhaobiao(com.tast.entity.zhaobiao
	 * .Zhaobiao)
	 */
	public Object[] listshenhe(Integer cpage, Integer PageSize) {
		String hql = "from Zhaobiao c where c.status='W'";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List listBiIdXi(Integer id) {
		String hql = " from ZhaobiaoXi where   t10=" + id;
		List list = totalDao.find(hql);
		return list;
	}

	public List listBiIdXiliklv(Integer id) {
		String sql = "select  * from zh_zhaobiaoXi where lilv  is  not null  and   t10=?";
		List list = totalDao.createQuerySelect(null, sql, id);
		return list;
	}

	public List listBiIdXiliklvnull(Integer id) {
		String sql = "select  * from zh_zhaobiaoXi where lilv  is  null  and   t10=?";
		List list = totalDao.createQuerySelect(null, sql, id);
		return list;
	}

	public List listAllDeptMonthBudget() {
		String hql = " from DeptMonthBudget where  1=1";
		List list = totalDao.find(hql);
		return list;
	}

	public void updatezhaobiao(Zhaobiao zhaobiao) {
		totalDao.update(zhaobiao);
	}

	// ////---------------------招标发布以后的动作
	public Object[] listUserZhaobiao(Integer cpage, Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql1 = " from  ZhUser  where userid=?";
		ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());
		if (zhUser != null) {
			String hql = "from  Zhaobiao where 1=1";
			if ("拉黑".equals(zhUser.getBlackliststauts())) {
				hql = hql + " and  status='XXXXX'";
			} else {
				hql = hql + " and  status='D'";
			}

			List list = totalDao.findAllByPage(hql, cpage, PageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}

	public void addzhaobiao(Zhaobiao zhaobiao) {
		totalDao.save(zhaobiao);
	}

	public void deletezhaobiao(Zhaobiao zhaobiao) {
		totalDao.delete(zhaobiao);
	}

	// ----------------------------------------招标详细列表--------------
	public List listtotoubiao(Integer id) {
		Users user = Util.getLoginUser();//
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser1 = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());
		String str2 = zhUser1.getCclass().replaceAll(" ", "");
		String[] indea = str2.split(",");
		List lists = new ArrayList();
		for (int i = 0; i < indea.length; i++) {
			String hql = "select x from ZhaobiaoXi x,Zhmoban m "
					+ "where x.t1=m.id and m.classe  like '%" + indea[i]
					+ "%'   and  x.t10= ?";
			List list = totalDao.query(hql, id);
			// String hql="select x.*,m.classe from zh_ZhaobiaoXi x,zh_moban m "
			// +
			// "where x.t1=m.id and m.classe  like '%"+indea[i]+"%'   and  x.t10="+id;
			// List list = totalDao.createQuerySelect(null,hql);
			if (list.size() > 0) {
				lists.addAll(list);
			}
		}

		return lists;
	}

	public ZhaobiaoXi ByIdzhaobiaoXi(Integer id) {
		return (ZhaobiaoXi) totalDao.getObjectById(ZhaobiaoXi.class, id);
	}

	public Zhtoubiao getXiByName(Integer id) {
		// Users user = Util.getLoginUser();
		// String hql1 = "from Zhtoubiao where tkong9=? and  tkong10=?";
		// Zhtoubiao zhtoubiao = (Zhtoubiao) totalDao.getObjectByCondition(hql1,
		// user.getName(), id);
		// return zhtoubiao;
		Users user = Util.getLoginUser();
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());

		String hql = "from Zhtoubiao where tkong10=? and tname=?";
		Zhtoubiao zhtoubiao = (Zhtoubiao) totalDao.getObjectByCondition(hql,
				id, zhUser.getName());
		return zhtoubiao;
	}

	/*
	*
	 */
	public List listXitoubiao(Integer id) {
		Users user = Util.getLoginUser();
		String hql1 = "from ZhUser where userid=?";
		ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1, user
				.getId());

		String hql = "from Zhtoubiao where tkong10=? and tname=?";
		List list = totalDao.query(hql, id, zhUser.getName());
		return list;
	}

	public Object[] qupingxuan(Integer id, Integer cpage, Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  ZhaobiaoXi where  t10=" + id;
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] qupingxuan1(Integer id, Integer cpage, Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  ZhaobiaoXi where   t10=" + id;
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] qupingxuanToubiao(Integer id, Integer cpage,
			Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  Zhtoubiao  where tkong10=" + id + "and  tkong7='N'"
				+ "";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		// --
		for (int i = 0; i < list.size(); i++) {
			Zhtoubiao newzZhtoubiao = (Zhtoubiao) list.get(i);
			if (newzZhtoubiao.getTkong2() == null) {
				String hql1 = " from  ZhUser  where name=?";
				ZhUser zhUser = (ZhUser) totalDao.getObjectByCondition(hql1,
						newzZhtoubiao.getTname());
				if (zhUser.getBili() == null) {
					// 未中标过 评标价格计算
					// =D4+ G8*D4*G6*P4-(H8*D4*H6*P4 +I8*D4*I6*P4+ J8*D4*J6*P4)
					// D4 报价
					// G8*D4*G6*P4 款到后到货周期*报价*首笔比例*天利率
					// H8*D4*H6*P4 到货后周期I* 报价*首笔*天利率
					// I8*D4*I6*P4 第二笔 "报价（元/kg/件)" 到货后周期I 天利率
					// J8*D4*J6*P4

					float qianqihesuan = (Float.valueOf(newzZhtoubiao
							.getTkong1())
							+ newzZhtoubiao.getZhouqikuan()
							* newzZhtoubiao.getShoubikuan()
							* Float.valueOf(newzZhtoubiao.getTkong1())
							* newzZhtoubiao.getLilv() / 365F - (newzZhtoubiao
							.getZhouqihuo()
							* newzZhtoubiao.getShoubihuo()
							* Float.valueOf(newzZhtoubiao.getTkong1())
							* newzZhtoubiao.getLilv()
							/ 365F
							+ newzZhtoubiao.getZhouqier()
							* newzZhtoubiao.getErbihuo()
							* Float.valueOf(newzZhtoubiao.getTkong1())
							* newzZhtoubiao.getLilv() / 365F + newzZhtoubiao
							.getMobihuo()
							* newzZhtoubiao.getMobiuo()
							* Float.valueOf(newzZhtoubiao.getTkong1())
							* newzZhtoubiao.getLilv() / 365F));
					// newzZhtoubiao.setTkong2(Float.valueOf(newzZhtoubiao.getTkong1())+newzZhtoubiao.getZhouqikuan()*Float.valueOf(newzZhtoubiao.getTkong1

					// ())*newzZhtoubiao.getShoubikuan()*0.072F/365F
					// -newzZhtoubiao.getZhouqihuo()*Float.valueOf(newzZhtoubiao.getTkong1())*newzZhtoubiao.getShoubihuo()
					//									
					// );
					BigDecimal b = new BigDecimal(qianqihesuan);
					float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP)
							.floatValue();
					newzZhtoubiao.setTkong2(f1);
					totalDao.update(newzZhtoubiao);
				} else {
					newzZhtoubiao.setTkong2(Float.valueOf(newzZhtoubiao
							.getTkong1())
							* zhUser.getBili());
					totalDao.update(newzZhtoubiao);
				}
			}
		}
		// --
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public List listXi(Integer id) {
		String hql = "from ZhaobiaoXi where t10=" + id;
		List list = totalDao.find(hql);
		return list;
	}

	public Zhtoubiao getByIdZhtoubiao(Integer id) {
		return (Zhtoubiao) totalDao.getObjectById(Zhtoubiao.class, id);
	}

	public void updatetoubiao(Zhtoubiao zhtoubiao) {
		totalDao.update(zhtoubiao);
	}

	public void updatezhaobiaoXi(ZhaobiaoXi zhaobiaoXi) {
		totalDao.update(zhaobiaoXi);
	}

	public ZhaobiaoXi byToubiaoXI(Integer id) {
		String hql = "select  x.t10 from zh_toubiao t,zh_zhaobiaoXi x where t.tkong10=x.id and t.tid="
				+ id;
		List list = totalDao.createQuerySelect(null, hql);
		ZhaobiaoXi zhaobiaoXi = new ZhaobiaoXi();
		// Object [] obj=(Object[]) list.get(0);
		// zhaobiaoXi.setT10(Integer.valueOf(String.valueOf(obj[10])).intValue());
		zhaobiaoXi.setT10((Integer) list.get(0));
		return zhaobiaoXi;

		// List list= totalDao.createQuerySelect(null, sql);
		// Zhtoubiao zhtoubiao=new Zhtoubiao();
		// Object [] obj=(Object[]) list.get(0);
		// zhtoubiao.setTid(Integer.valueOf(String.valueOf(obj[0])).intValue());
		// zhtoubiao.setTname(obj[1].toString());
		// return zhtoubiao;
	}

	public Object[] listzhongbiao(Integer cpage, Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  Zhtoubiao where tkong7='同意' or tkong7='Y' or   tkong7='审核中'";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 排序 价格
	public Object[] listpaixujiage(Integer id, Integer cpage, Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "from  Zhtoubiao  where tkong10=" + id
				+ " order by tkong3";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 供货率
	public Object[] listpaixugonghuolv(Integer id, Integer cpage,
			Integer PageSize) {
		Users user = Util.getLoginUser();
		// String hql =
		// "select t.* from  Zhtoubiao t,ZhUser u where u.name=t.tname and  t.tkong10="+id+" order by gonghuolv ";

		String hql = "from Zhtoubiao where  tid in (select  TOP 100 t.tid from  Zhtoubiao t,ZhUser u where u.name=t.tname and  t.tkong10="
				+ id + " order by gonghuolv)";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	// 质量
	public Object[] listpaixuzhiliang(Integer id, Integer cpage,
			Integer PageSize) {
		Users user = Util.getLoginUser();
		String hql = "select t.* from  Zhtoubiao t,ZhUser u where u.name=t.tname and  t.tkong10="
				+ id + " order by zhiliang ";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Integer[] listfabuxinxi(Zhaobiao zhaobiao) {
		String sql1 = "select distinct  m.classe  from zh_moban  m,zh_ZhaobiaoXi x,zh_zhaobiao z where z.id=x.t10 and x.t1=m.id and z.id=?";
		List list1 = totalDao.createQuerySelect(null, sql1, zhaobiao.getId());
		Integer[] userId = null;
		for (int i = 0; i < list1.size(); i++) {
			if (list1.get(i) != null) {
				String sql2 = "select distinct  userid from zh_users where  userid is not  null and cclass like '%"
						+ list1.get(i).toString() + "%' ";
				List list2 = totalDao.createQuerySelect(null, sql2);

				userId = new Integer[list2.size()];
				for (int j = 0; j < list2.size(); j++) {
					Object o = list2.get(j);
					String s = o.toString();
					Integer ii = Integer.parseInt(s);
					// userId[i]=Integer.parseInt(list2.get(i).toString());
					userId[j] = ii;
				}
			}
		}
		// String sql =
		// "select userid from zh_user where Blackliststauts='正常使用' and  userid is not  null";
		// List list = totalDao.createQuerySelect(null, sql);
		return userId;
	}

	public List listfabuzhongbiao() {
		String sql = "select  userid from  Zh_user where  userid in(select u.userid from   Zh_user u,zh_toubiao t where u.name=t.tname) ";
		List list = totalDao.createQuerySelect(null, sql);
		return list;
	}

	public List listxitoubiao(Integer id) {
		// t7='Y' or t7='合同审批中' or t7='同意' and
		String hql = "from ZhaobiaoXi where     t10=?  ";
		List list = totalDao.query(hql, id);
		return list;
	}

	public List listAlltoubiao() {
		String hql = "from Zhtoubiao where 1=1";
		List list = totalDao.query(hql);
		return list;
	}

	public void addtoubiao(Zhtoubiao zhtoubiao, File t8, String t8FileName,
			String fatherPartNumber, ZhaobiaoXi zhaobiaoXi) {
		// 上传附件
		String newFileName = "";
		Upload upload = new Upload();
		if (newFileName == null || newFileName.length() <= 0) {
			if (t8FileName != null && t8FileName.length() > 0) {
				newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
						.format(new Date())
						+ t8FileName.substring(t8FileName.lastIndexOf("."),
								t8FileName.length());
			}
		}
		upload.UploadFile(t8, t8FileName, newFileName, "upload/zhaobiao/file",
				"D:/WorkSpace/HHTask/WebRoot/upload/zhaobiao/file");
		zhtoubiao.setTmoney(newFileName);

		zhtoubiao.setZhaobiaoXi(zhaobiaoXi);
		String sql = "select top 1 tid from Zh_toubiao   order by tid desc ";
		List listXiid = totalDao.createQuerySelect(null, sql);
		if (listXiid != null) {
			// cm.setNumber("KH000"+listXiid.get(0)+1);
			NumberFormat format = NumberFormat.getInstance();
			format.setMinimumIntegerDigits(6);
			String result = "RMP"
					+ String.format("%06d", Integer.parseInt(listXiid.get(0)
							.toString()) + 1);
			zhtoubiao.setHetongbiaohao(result + Util.getDateTime("yyyy") + "X");
		} else {
			zhtoubiao.setHetongbiaohao("RMP" + Util.getDateTime("yyyy")
					+ "000001");
		}
		totalDao.save(zhtoubiao);
	}

	public void chongxin(Flowdetail flowdetail, Integer id, File shenhefujian,
			String shenhefujianFileName) {
		// 上传附件
		String newFileName = "";
		Upload upload = new Upload();
		if (newFileName == null || newFileName.length() <= 0) {
			if (shenhefujianFileName != null
					&& shenhefujianFileName.length() > 0) {
				newFileName = new SimpleDateFormat("yyyyMMddHHmmss")
						.format(new Date())
						+ shenhefujianFileName
						+ shenhefujianFileName.substring(shenhefujianFileName
								.lastIndexOf("."), shenhefujianFileName
								.length());
			}
		}
		upload.UploadFile(shenhefujian, shenhefujianFileName, newFileName,
				"upload/zhaobiao/file",
				"D:/WorkSpace/HHTask/WebRoot/upload/zhaobiao/file");
		flowdetail.setSpyj(newFileName);

		totalDao.save(flowdetail);
	}

	public void savaflow(Flowdetail flowdetail) {
		totalDao.save(flowdetail);
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public List xianshizhaoshangyonghu(Integer id) {
		String sql = "from ZhaobiaoXi where  t10=?";
		List list = totalDao.query(sql, id);
		return list;
	}

	public List listAllzhongbiao() {
		String hql = "from Zhtoubiao where tkong7='Y'";
		List list = totalDao.query(hql);
		return list;
	}

	// 添加回款方式表
	public void addHuiXi(Hui_Xi huiXi) {
		totalDao.save(huiXi);
	}

	// 综合
	public Zhtoubiao listpaixuzonghe(ZhaobiaoXi zhaobiaoXi, Integer id) {
		Users user = Util.getLoginUser();
		String sql = "select top 1 t.* from  zh_toubiao t,zh_user u where u.name=t.tname and t.tkong10="
				+ id + " order by (u.gonghuolv*0.2+u.zhiliang*0.3+0.5)";
		List list = totalDao.createQuerySelect(null, sql);
		Zhtoubiao zhtoubiao = new Zhtoubiao();
		Object[] obj = (Object[]) list.get(0);
		zhtoubiao.setTid(Integer.valueOf(String.valueOf(obj[0])).intValue());
		zhtoubiao.setTname(obj[1].toString());
		return zhtoubiao;
	}

	public List listzhongbiaosize(String name) {
		String hql1 = " from  Zhtoubiao  where tkong7 <> 'N' and bili is not null and  tname=?";
		List newlist = totalDao.query(hql1, name);
		return newlist;
	}

	@Override
	public Zhtoubiao pingbiaojisuan(ZhaobiaoXi zhaobiaoXi, Integer tkong10) {
		String hql = "from Zhtoubiao where tkong10=?";
		List list = totalDao.query(hql, tkong10);
		for (int i = 0; i < list.size(); i++) {
			Zhtoubiao newzZhtoubiao = (Zhtoubiao) list.get(i);
			String hql1 = " from  Zhtoubiao  where tkong7 <> 'N' and  tname=?";
			List newlist = totalDao.query(hql1, newzZhtoubiao.getTname());
			if (newlist == null || newlist.size() <= 0) {
				// 未中标过 评标价格计算
				// =D4+ G8*D4*G6*P4-(H8*D4*H6*P4 +I8*D4*I6*P4+ J8*D4*J6*P4)
				// D4 报价
				// G8*D4*G6*P4 款到后到货周期*报价*首笔比例*天利率
				// H8*D4*H6*P4 到货后周期I* 报价*首笔*天利率
				newzZhtoubiao
						.setTkong2(Float.valueOf(newzZhtoubiao.getTkong1())
								+ newzZhtoubiao.getZhouqikuan()
								* Float.valueOf(newzZhtoubiao.getTkong1())
								* newzZhtoubiao.getShoubikuan()
								* newzZhtoubiao.getLilv()
								/ 365F
								- (newzZhtoubiao.getZhouqihuo()
										* Float.valueOf(newzZhtoubiao
												.getTkong1())
										* newzZhtoubiao.getShoubihuo()
										* newzZhtoubiao.getLilv()
										/ 365F
										+ newzZhtoubiao.getZhouqier()
										* Float.valueOf(newzZhtoubiao
												.getTkong1())
										* newzZhtoubiao.getErbihuo()
										* newzZhtoubiao.getLilv() / 365F + newzZhtoubiao
										.getMobihuo()
										* newzZhtoubiao.getMobiuo()
										* Float.valueOf(newzZhtoubiao
												.getTkong1())
										* newzZhtoubiao.getLilv() / 365F));
				// newzZhtoubiao.setTkong2(Float.valueOf(newzZhtoubiao.getTkong1())+newzZhtoubiao.getZhouqikuan()*Float.valueOf(newzZhtoubiao.getTkong1())

				// *newzZhtoubiao.getShoubikuan()*0.072F/365F
				// -newzZhtoubiao.getZhouqihuo()*Float.valueOf(newzZhtoubiao.getTkong1())*newzZhtoubiao.getShoubihuo()
				//								
				// );
				totalDao.update(newzZhtoubiao);
				// ------------
			} else {
				// =Q4/D4
				// newzZhtoubiao.setTkong2(newzZhtoubiao.getTkong2()/zhUser.getBili());
				float zongbili = 0F;
				for (int j = 0; j < newlist.size(); j++) {
					Zhtoubiao oldZhtoubiao = (Zhtoubiao) newlist.get(i);
					zongbili = zongbili + oldZhtoubiao.getBili();
				}
				float bili = zongbili / newlist.size()
						* Float.valueOf(newzZhtoubiao.getTkong1());
				newzZhtoubiao.setTkong2(Float.parseFloat(String.format("%.2f",
						bili)));
			}
		}
		// --***根据 评标价格计算 排序
		String hql12 = " from  Zhtoubiao order by tkong2 desc";
		Zhtoubiao nowzZhtoubiao = (Zhtoubiao) totalDao
				.getObjectByCondition(hql12);
		return nowzZhtoubiao;
	}

	@Override
	public void updatezhtoubiao(Zhtoubiao zhtoubiao) {
		totalDao.update(zhtoubiao);
	}

	@Override
	public Object[] listgongxu(Zhgongxu zhgongxu, Integer parseInt,
			Integer pageSize) {
		if (zhgongxu == null) {
			zhgongxu = new Zhgongxu();
		}
		String hql = totalDao.criteriaQueries(zhgongxu, null);
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	/**
	 * 选择年利率
	 */
	public Object[] listlulv(Zhaobiao zhaobiao, Integer cpage, Integer PageSize) {
		if (zhaobiao == null) {
			zhaobiao = new Zhaobiao();
		}
		String hql = totalDao.criteriaQueries(zhaobiao, null)
				+ " and   status='W'";
		// String hql = "from Zhaobiao where 1=1 order by  fabushijian desc";
		List list = totalDao.findAllByPage(hql, cpage, PageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Zhtoubiao Byhesuanjiage(Integer t10) {
		String hql12 = " from  Zhtoubiao where tkong10=? order by tkong2 ";
		Zhtoubiao nowzZhtoubiao = (Zhtoubiao) totalDao.getObjectByCondition(
				hql12, t10);
		return nowzZhtoubiao;
	}

	public void pingbiao(Integer zhaobiaoid) {
		String sql = "select  id from  Zh_zhaobiaoXi where t10=?";
		List listXiid = totalDao.createQuerySelect(null, sql, zhaobiaoid);
		if (listXiid != null && listXiid.size() > 0) {
			for (int i = 0; i < listXiid.size(); i++) {
				String hql12 = " from  Zhtoubiao where tkong10=? order by tkong2";
				List listtoubiao = totalDao.query(hql12, listXiid.get(i));
				Zhtoubiao newZhtoubiao = (Zhtoubiao) listtoubiao.get(0);
				// 修改中表的商家
				newZhtoubiao.setTkong7("Y");
				totalDao.update(newZhtoubiao);
				ZhaobiaoXi newZhaobiaoXi = (ZhaobiaoXi) totalDao.getObjectById(
						ZhaobiaoXi.class, newZhtoubiao.getTkong10());
				newZhaobiaoXi.setT7("Y");
				totalDao.update(newZhaobiaoXi);
			}
		}
		Zhaobiao newZhaobiao = (Zhaobiao) totalDao.getObjectById(
				Zhaobiao.class, zhaobiaoid);
		newZhaobiao.setStatus("自动评标结束");
		totalDao.update(newZhaobiao);
	}

	public void chongxinpingbiao(Integer zhtoubiaoId) {
		Zhtoubiao oldt = (Zhtoubiao) totalDao.getObjectById(Zhtoubiao.class,
				zhtoubiaoId);
		ZhaobiaoXi oldx = (ZhaobiaoXi) totalDao.getObjectById(ZhaobiaoXi.class,
				oldt.getTkong10());
		// -------
		String hql12 = " from  Zhtoubiao where tkong10=? and  tkong7='N'   order by tkong2 ";
		List listtoubiao = totalDao.query(hql12, oldt.getTkong10());
		if (listtoubiao != null) {
			Zhtoubiao newZhtoubiao = (Zhtoubiao) listtoubiao.get(0);
			// 修改中表的商家
			newZhtoubiao.setTkong7("Y");
			totalDao.update(newZhtoubiao);

			Zhaobiao newZhaobiao = (Zhaobiao) totalDao.getObjectById(
					Zhaobiao.class, oldx.getT10());
			newZhaobiao.setStatus("自动评标结束");
			totalDao.update(newZhaobiao);

			oldt.setTkong7("N");
			totalDao.update(oldt);
		}
	}

	public List ByToubiaohetong(String numbers, Integer zhUsers) {
		// String hql = "from  ZhaobiaoXi where  t10=? and id in (select )";

		// String hql="   from ZhaobiaoXi where  t10=? and id in " +
		// "(select tkong10 from  Zhtoubiao where  tkong7 in ('审核中','同意','Y') and zhUserId=? )";
		// List list2= totalDao.query(hql,numbers,zhUsers);
		// List list=new ArrayList(list2.size());
		// for (int i = 0; i <list2.size(); i++) {
		// ZhaobiaoXi newzX=(ZhaobiaoXi) list2.get(i);
		// String hql1 =
		// "from Zhtoubiao where  tkong7 in ('审核中','同意','Y') and tkong10=? and zhUserId=?";
		// Zhtoubiao zhtoubiao = (Zhtoubiao)
		// totalDao.getObjectByCondition(hql1,newzX.getId(),zhUsers);
		// if (zhtoubiao!=null) {
		// newzX.setChengzhong(zhtoubiao.getChengzhong());
		// newzX.setDanjia(zhtoubiao.getTkong1());
		// newzX.setZongjine(zhtoubiao.getZongjine());
		// list.add(newzX);
		// }
		// }
		String hql = " from  Zhtoubiao where  tkong7 in ('审核中','同意','Y') and number=? and   zhUserId=? ";
		List list2 = totalDao.query(hql, numbers, zhUsers);
		return list2;
	}

	@Override
	public String addzhUser(File addzhUser) {
		boolean flag = true;
		List<String> list_int = new ArrayList<String>();
		List<ZhUser> list = null;
		String msg = "true";
		String fileName = Util.getDateTime("yyyyMMddhhmmss") + ".xls";
		// 上传到服务器
		String fileRealPath = ServletActionContext.getServletContext()
				.getRealPath("/upload/file")
				+ "/" + fileName;
		File file = new File(fileRealPath);
		jxl.Workbook wk = null;
		int insize = 0;
		try {
			FileCopyUtils.copy(addzhUser, file);
			// 开始读取excle表格
			InputStream is = new FileInputStream(fileRealPath);// 创建文件流;
			if (is != null) {
				wk = Workbook.getWorkbook(is);// 创建workbook
			}
			Sheet st = wk.getSheet(0);// 获得第一张sheet表;
			int exclecolums = st.getRows();// 获得excle总行数
			if (exclecolums > 2) {
				String msg1 = "";
				for (int i = 2; i < exclecolums; i++) {
					ZhUser zh = new ZhUser();
					Cell[] cells = st.getRow(i);// 获得每i行的所有单元格（返回的是数组）
					if (cells[0].getContents() != null
							&& cells[0].getContents() != "") {
						String a = cells[1].getContents();// 获得公司简称
						String b = cells[2].getContents();// 获得公司全称
						String n = cells[3].getContents();// 编号(工号)
						if (n != null && !"".equals(n)) {
							String hql = " from ZhUser where  usercode =?";
							ZhUser user = (ZhUser) totalDao
									.getObjectByCondition(hql, n);
							if (user != null) {
								list_int.add("第" + (i + 1) + "行错误，该编号" + n
										+ "已经存在!\\n");
								continue;
							}
							String hql1 = "from Users where code =?";
							int ui = totalDao.getCount(hql, n);
							if (ui > 0) {
								list_int.add("第" + (i + 1) + "行错误，该编号" + n
										+ "在用户表中已经存在!\\n");
								continue;
							}
						} else {
							list_int.add("第" + (i + 1) + "行错误，没有供应商编号!\\n");
							continue;
						}
						String e = null;// 类别
						String g = null;// 联系人
						String c = null;// 联系电话
						String d = null;// 手机号
						String yx = null;// 邮箱*
						String fkfs = null;// 付款方式*
						String k = null;// 公司地址
						String sl = null;// 增值税率*
						String djh = null;// 税务登记号*
						String zy = null;// 营业执照*
						String j = null;// 注册时间
						String et = null;// 失效日期*
						String zdqdl = null;// 最低起定量*
						String zdzxl = null;// 最低装箱量*
						String zdqsl = null;// 最底起送量*
						String yfkb = null;// 预付款比例*
						String fkzq = null;// 付款周期*
						String kqyj = null;// 开票依据*
						try {
							e = cells[4].getContents();// 类别
							g = cells[5].getContents();// 联系人
							c = cells[6].getContents();// 联系电话
							d = cells[7].getContents();// 手机号
							yx = cells[8].getContents();// 邮箱*
							fkfs = cells[9].getContents();// 付款方式*
							k = cells[10].getContents();// 公司地址
							sl = cells[11].getContents();// 增值税率*
							djh = cells[12].getContents();// 税务登记号*
							zy = cells[13].getContents();// 营业执照*
							j = cells[14].getContents();// 注册时间
							et = cells[15].getContents();// 失效日期*
							zdqdl = cells[16].getContents();// 最低起定量*
							zdzxl = cells[17].getContents();// 最低装箱量*
							zdqsl = cells[18].getContents();// 最底起送量*
							if (cells.length >= 20) {
								yfkb = cells[19].getContents();// 预付款比例*
							}
							if (cells.length >= 21) {
								fkzq = cells[20].getContents();// 付款周期(天(票到))*
							}
							if (cells.length >= 22) {
								kqyj = cells[21].getContents();// 开票依据(合格入库/生产无不良/成品出库)*
							}
							// TODO: handle exception
						} catch (Exception ex) {
						}

						try {
							zh.setName(a);
							zh.setCmp(b);
							zh.setCfax(c);
							zh.setCtel(d);
							zh.setCclass(e);
							zh.setCperson(g);
							zh.setCtel(c);
							zh.setCmobile(d);
							zh.setTime(j);
							zh.setCompanydz(k);
							zh.setGonghuolv(1F);
							zh.setZhiliang(1F);
							zh.setUsercode(n);
							zh.setUserdept("gys");// 固定部门编号 “GYS”
							zh.setBlackliststauts("正常使用");
							zh.setYx(yx);
							zh.setFkfs(fkfs);
							zh.setZzsl(sl);
							zh.setEt(et);
							zh.setDjh(djh);
							zh.setYyzz(zy);
							zh.setAsl("大批量");
							if (zdqdl != null && zdqdl.length() > 0) {
								zh.setZdqdl(Float.parseFloat(zdqdl));
							}
							if (zdqsl != null && zdqsl.length() > 0) {
								zh.setZdqsl(Float.parseFloat(zdqsl));
							}
							if (zdzxl != null && zdzxl.length() > 0) {
								zh.setZdzxl(Float.parseFloat(zdzxl));
							}
							if (yfkb != null && yfkb.length() > 0) {
								zh.setYufuBiLi(Float.parseFloat(yfkb));
							}
							if (fkzq != null && fkzq.length() > 0) {
								zh.setFkZhouQi(Integer.parseInt(fkzq));
							}
							zh.setKpYiJu(kqyj);

							String wgType = zh.getCclass();
							Set<Category> categorySet = new HashSet<Category>();
							if (wgType != null && !"".equals(wgType)) {
								wgType = wgType.replaceAll("；", ";");
								String[] strs = wgType.split(";");
								if (strs.length > 0) {
									for (int o = 0; o < strs.length; o++) {
										String hql_wgType = " from Category where name =?";
										Category category = (Category) totalDao
												.getObjectByCondition(
														hql_wgType, strs[o]);
										if (category != null) {
											categorySet.add(category);
										}
									}
								}
								zh.setCategoryset(categorySet);
							}
							// 增加登录用户
							Users User = new Users();
							User.setCode(zh.getUsercode());
							User.setName(zh.getCperson());
							User.setMore(zh.getCmp());
							User.setSex("男");
							User.setDuty("供应商");
							User.setOnWork("在职");
							User.setInternal("否");
							String hql_dn = "from DeptNumber where deptNumber=?";
							DeptNumber deptNumber = (DeptNumber) totalDao
									.getObjectByCondition(hql_dn, "gys");// 查询部门编号
							if (deptNumber == null) {
								deptNumber = new DeptNumber();
								deptNumber.setDept("部门");
								deptNumber.setDeptNumber("gys");
								deptNumber.setFatherId(1);
								deptNumber.setBelongLayer(2);
								totalDao.save(deptNumber);
							}
							if (deptNumber != null) {
								User.setDept(deptNumber.getDept());
								User.setDeptId(deptNumber.getId());
								Password password = new Password();
								password
										.setPassword("e10adc3949ba59abbe56e057f20f883e");// 默认密码(123456)
								password.setDeptNumber("gys");
								password.setPhoneNumber(zh.getCmobile());
								password.setPresentAddress(zh.getCompanydz());
								password.setUserStatus("完成");
								password.setUser(User);
								User.setPassword(password);
								flag = totalDao.save(User);

								zh.setUserid(User.getId());
							}
							totalDao.save(zh);
							insize++;
						} catch (Exception e1) {
							list_int.add("第" + (i + 1) + "行错误数据异常\\n");
							e1.printStackTrace();
							continue;
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		// if (flag) {
		if (true) {
			// 修复程序
			String hql = "from ZhUser where userId not in (select id from Users) or usercode not in (select code from Users)";
			list = totalDao.query(hql);
			if (list != null && list.size() > 0) {
				flag = false;
				for (ZhUser zhUser : list) {

					String hql_user = " from Users where  code =?";
					Users user = (Users) totalDao.getObjectByCondition(
							hql_user, zhUser.getUsercode());
					if (user != null) {
						zhUser.setUserid(user.getId());
						totalDao.update(zhUser);
						continue;
					}
					Users user_new = new Users();
					user_new.setCode(zhUser.getUsercode());
					user_new.setName(zhUser.getCperson());
					user_new.setMore(zhUser.getCmp());
					user_new.setSex("男");
					user_new.setDuty("供应商");
					user_new.setOnWork("在职");
					user_new.setInternal("否");
					String hql_dn = "from DeptNumber where deptNumber=?";
					DeptNumber deptNumber = (DeptNumber) totalDao
							.getObjectByCondition(hql_dn, "gys");// 查询部门编号
					if (deptNumber != null) {
						user_new.setDept(deptNumber.getDept());
						user_new.setDeptId(deptNumber.getId());
						Password password = new Password();
						password
								.setPassword("e10adc3949ba59abbe56e057f20f883e");// 默认密码(123456)
						password.setDeptNumber("gys");
						password.setPhoneNumber(zhUser.getCmobile());
						password.setPresentAddress(zhUser.getCompanydz());
						password.setUserStatus("完成");
						password.setUser(user_new);
						user_new.setPassword(password);
						flag = totalDao.save(user_new);

						zhUser.setUserid(user_new.getId());
						flag = totalDao.update(zhUser);
						if (flag) {
							msg = "true";
						}
					}
				}

			}
		}
		if (list_int != null && list_int.size() > 0) {
			msg = "成功导入" + insize + "个， 失败" + list_int.size() + "个。\\n";
			for (int i = 0; i < list_int.size(); i++) {
				if (i == 0) {
					msg += list_int.get(i);
				} else {
					msg += "," + list_int.get(i);
				}
			}
			msg += "。";
		}
		return msg;
	}

	@Override
	public String addShenpi(Integer id) {
		// TODO Auto-generated method stub
		Zhaobiao zhaobiao = listById(id);
		if (zhaobiao != null) {
			zhaobiao.setStatus("Y");
			zhaobiao.setShenpiStatus("未审批");
			String processName = "招标信息发布审核";
			String name = "";
			Users users = Util.getLoginUser();
			if (users != null)
				name = users.getName();
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						Zhaobiao.class, zhaobiao.getId(), "shenpiStatus", "id",
						null, "有 " + name + " 提交的招标申请，请您审批！", true);
				if (epId != null && epId > 0) {
					zhaobiao.setEpId(epId);
					if (totalDao.update(zhaobiao))
						return "true";
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "申请失败！";
			}
		}
		return "申请失败。";
	}

	@Override
	public void exportExcel(ZhUser zhuser) {
		if (zhuser == null) {
			zhuser = new ZhUser();
		}
		String hql = totalDao.criteriaQueries(zhuser, null);
		hql += " and blackliststauts <> '拉黑'";
		List<ZhUser> zhuserList = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("供应商".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("供应商信息", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(4, 24);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 12);
			ws.setColumnView(13, 16);
			ws.setColumnView(18, 25);

			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "公司简称"));
			ws.addCell(new Label(2, 0, "供应商全称"));
			ws.addCell(new Label(3, 0, "编号"));
			ws.addCell(new Label(4, 0, "厂商类别"));
			ws.addCell(new Label(5, 0, "联系人"));
			ws.addCell(new Label(6, 0, "电话号"));
			ws.addCell(new Label(7, 0, "手机号"));
			ws.addCell(new Label(8, 0, "邮箱"));
			ws.addCell(new Label(9, 0, "付款方式"));
			ws.addCell(new Label(10, 0, "增值税率(%)"));
			ws.addCell(new Label(11, 0, "税务登记号"));
			ws.addCell(new Label(12, 0, "营业执照"));
			ws.addCell(new Label(13, 0, "注册日期"));
			ws.addCell(new Label(14, 0, "失效日期"));
			ws.addCell(new Label(15, 0, "最低起订量"));
			ws.addCell(new Label(16, 0, "最低装箱量"));
			ws.addCell(new Label(17, 0, "最低起送量"));
			ws.addCell(new Label(18, 0, "公司地址"));
			for (int i = 0; i < zhuserList.size(); i++) {
				ZhUser zhuser1 = (ZhUser) zhuserList.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, zhuser1.getName()));
				ws.addCell(new Label(2, i + 1, zhuser1.getCmp()));
				ws.addCell(new Label(3, i + 1, zhuser1.getUsercode()));
				ws.addCell(new Label(4, i + 1, zhuser1.getCclass()));
				ws.addCell(new Label(5, i + 1, zhuser1.getCperson()));
				ws.addCell(new Label(6, i + 1, zhuser1.getCfax()));
				ws.addCell(new Label(7, i + 1, zhuser1.getCtel()));
				ws.addCell(new Label(8, i + 1, zhuser1.getYx()));
				ws.addCell(new Label(9, i + 1, zhuser1.getFkfs()));
				ws.addCell(new Label(10, i + 1, zhuser1.getZzsl()));
				ws.addCell(new Label(11, i + 1, zhuser1.getDjh()));
				ws.addCell(new Label(12, i + 1, zhuser1.getYyzz()));
				ws.addCell(new Label(13, i + 1, zhuser1.getTime()));
				ws.addCell(new Label(14, i + 1, zhuser1.getEt()));
				ws.addCell(new Label(15, i + 1,
						zhuser1.getZdqdl() == null ? "0" : zhuser1.getZdqdl()
								+ ""));
				ws.addCell(new Label(16, i + 1,
						zhuser1.getZdzxl() == null ? "0" : zhuser1.getZdzxl()
								+ ""));
				ws.addCell(new Label(17, i + 1,
						zhuser1.getZdqsl() == null ? "0" : zhuser1.getZdqsl()
								+ ""));
				ws.addCell(new Label(18, i + 1, zhuser1.getCompanydz()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void XFWgType() {
		String hql = " from Price where produceType = '外购' and( wlType <> '' or wlType is not null) and ( gysId <> '' or gysId is not null)";
		List<Price> priceList = totalDao.query(hql);
		if (priceList != null && priceList.size() > 0) {
			for (Price price : priceList) {
				ZhUser zhuser = (ZhUser) totalDao.get(ZhUser.class, price
						.getGysId());
				Category category = (Category) totalDao.getObjectByCondition(
						" from Category where name =?", price.getWlType());
				Set<Category> categoryset = zhuser.getCategoryset();
				boolean bool = false;
				if (category != null) {
					if (categoryset == null || categoryset.size() == 0) {
						categoryset = new HashSet<Category>();
						categoryset.add(category);
						bool = true;
					} else if (!categoryset.contains(category)) {
						categoryset.add(category);
						bool = true;
					}
				}
				if (bool) {
					zhuser.setCategoryset(categoryset);
					totalDao.update(zhuser);
				}

			}
		}

	}

	// 自动生成扣款单编号
	@Override
	public String findCSNumber() {
		String datetime = Util.getDateTime("yyyyMMdd");
		String hql = "select number from ChargebackNotice order by id desc";
		List list = totalDao.findAllByPage(hql, 0, 1);
		String contractNumber = "";
		String sc = "CS";
		if (list != null && list.size() > 0) {
			contractNumber = (String) list.get(0);
		}
		if (contractNumber != null && contractNumber.length() > 0) {
			try {
				int num = Integer.parseInt(contractNumber.substring(10,
						contractNumber.length())) + 1;
				if (num >= 1000)
					contractNumber = sc + datetime + num;
				else if ((num >= 100))
					contractNumber = sc + datetime + "0" + num;
				else if ((num >= 10))
					contractNumber = sc + datetime + "00" + num;
				else
					contractNumber = sc + datetime + "000" + num;
			} catch (Exception e) {
				contractNumber = sc + datetime + "0001";
			}
		} else {
			contractNumber = sc + datetime + "0001";
		}
		return contractNumber;
	}

	@Override
	public String addChargebackNotice(ChargebackNotice chargebackNotice) {
		// TODO Auto-generated method stub
		Users users = Util.getLoginUser();
		if (users != null) {
			chargebackNotice.setAddName(users.getName());
			chargebackNotice.setJbName(users.getName());
		}
		chargebackNotice.setNumber(findCSNumber());
		chargebackNotice.setAddTime(Util.getDateTime());
		if (totalDao.save(chargebackNotice)) {
			return "添加成功！";
		}
		return "对象为空，添加失败！";
	}

	@Override
	public ChargebackNotice byIdChargebackNotice(Integer id) {
		// TODO Auto-generated method stub
		return (ChargebackNotice) totalDao.getObjectById(
				ChargebackNotice.class, id);
	}

	@Override
	public String deleteChargebackNotice(Integer id) {
		// TODO Auto-generated method stub
		ChargebackNotice c = byIdChargebackNotice(id);
		if (c != null) {
			if (totalDao.delete(c))
				return "删除成功！";
			else
				return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object[] findChargebackNotice(ChargebackNotice chargebackNotice,
			int parseInt, int pageSize) {
		// TODO Auto-generated method stub
		if (chargebackNotice == null) {
			chargebackNotice = new ChargebackNotice();
		}
		String hql = totalDao.criteriaQueries(chargebackNotice, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object[] o = { list, count };
		return o;
	}

	public void daochukoukuan(ChargebackNotice chargebackNotice) {
		if (chargebackNotice == null) {
			chargebackNotice = new ChargebackNotice();
		}
		String hql = totalDao.criteriaQueries(chargebackNotice, null);
		hql += " order by id desc";
		List<ChargebackNotice> cnList = totalDao.query(hql);
		try {
			HttpServletResponse response = (HttpServletResponse) ActionContext
					.getContext().get(StrutsStatics.HTTP_RESPONSE);
			OutputStream os = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("扣款单".getBytes("GB2312"), "8859_1") + ".xls");
			response.setContentType("application/msexcel");
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("扣款单", 0);
			ws.setColumnView(0, 16);
			ws.setColumnView(1, 16);
			ws.setColumnView(2, 18);
			ws.setColumnView(3, 25);
			ws.setColumnView(5, 25);
			ws.setColumnView(6, 12);

			ws.addCell(new Label(0, 0, "序号"));
			ws.addCell(new Label(1, 0, "扣款单编号"));
			ws.addCell(new Label(2, 0, "供应商"));
			ws.addCell(new Label(3, 0, "扣款事由"));
			ws.addCell(new Label(4, 0, "扣款金额"));
			ws.addCell(new Label(5, 0, "提报单位"));
			ws.addCell(new Label(6, 0, "扣款月份"));
			ws.addCell(new Label(7, 0, "经办人"));
			ws.addCell(new Label(8, 0, "添加时间"));

			for (int i = 0; i < cnList.size(); i++) {
				ChargebackNotice cbn = (ChargebackNotice) cnList.get(i);
				ws.addCell(new Label(0, i + 1, i + 1 + ""));
				ws.addCell(new Label(1, i + 1, cbn.getNumber()));
				ws.addCell(new Label(2, i + 1, cbn.getZhUser_name()));
				ws.addCell(new Label(3, i + 1, cbn.getKkCause()));
				ws.addCell(new Label(4, i + 1, cbn.getKkMoney() + ""));
				ws.addCell(new Label(5, i + 1, cbn.getReportUnit()));
				ws.addCell(new Label(6, i + 1, cbn.getKkMouth()));
				ws.addCell(new Label(7, i + 1, cbn.getJbName()));
				ws.addCell(new Label(8, i + 1, cbn.getAddTime()));
			}
			wwb.write();
			wwb.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String updateChargebackNotice(ChargebackNotice chargebackNotice) {
		// TODO Auto-generated method stub
		ChargebackNotice c = byIdChargebackNotice(chargebackNotice.getId());
		if (c != null) {
			BeanUtils.copyProperties(chargebackNotice, c, new String[] { "id",
					"addTime", "jbName", "addName", "shName", "pzName",
					"epstatus", "epId" });
			if (totalDao.update(c))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	@Override
	public PrepaymentsApplication byIdPrepayApp(Integer id) {
		// TODO Auto-generated method stub
		return (PrepaymentsApplication) totalDao.getObjectById(
				PrepaymentsApplication.class, id);
	}

	@Override
	public String deletePrepayApp(Integer id) {
		// TODO Auto-generated method stub
		PrepaymentsApplication c = byIdPrepayApp(id);
		if (c != null) {
			List<PrepaymentsApplicationDetails> paList = totalDao
					.query(
							"from PrepaymentsApplicationDetails where prepaymentsApplicationId =?",
							id);
			if (paList != null && paList.size() > 0) {
				for (PrepaymentsApplicationDetails pad : paList) {
					if (pad != null) {
						totalDao.delete(pad);
					}
				}
			}
			if (totalDao.delete(c)) {
				return "删除成功！";
			} else {
				return "删除失败！";
			}
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Object[] findPrepayApp(
			PrepaymentsApplication prepaymentsApplication, int parseInt,
			int pageSize, String tag) {
		if (prepaymentsApplication == null) {
			prepaymentsApplication = new PrepaymentsApplication();
		}
		String sql = "";
		if ("findAll".equals(tag)) {
		} else if ("findAllself".equals(tag)) {
			Users users = Util.getLoginUser();
			sql = "(jbName = '" + users.getName() + "' or addName = '"
					+ users.getName() + "')";
		} else {
			sql = "id = 0";
		}
		String hql = totalDao.criteriaQueries(prepaymentsApplication, sql);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Object[] o = { list, count };
		return o;
	}

	@Override
	public String updatePrepayApp(PrepaymentsApplication prepaymentsApplication) {
		// TODO Auto-generated method stub
		PrepaymentsApplication p = byIdPrepayApp(prepaymentsApplication.getId());
		if (p != null) {
			BeanUtils.copyProperties(prepaymentsApplication, p, new String[] {
					"id", "addTime", "addDept", "addName", "jbName", "status",
					"epId", "number", "poNumber" });
			p.setYfMoney(prepaymentsApplication.getYfbl()
					* prepaymentsApplication.getAllMoney() / 100);
			p.setYfMoneyDX(NumberToCN.NumberCN(p.getYfMoney()));
			if ("待完善".equals(p.getStatus())) {
				Users users = Util.getLoginUser();
				p.setJbName(users.getName());
				// 是否将预付款比例和付款周期同步到供应商信息
				// 调用审批流程
				String processName = "预付款申请";
				Integer epId = null;
				try {
					epId = CircuitRunServerImpl.createProcess(processName,
							PrepaymentsApplication.class, p.getId(), "status",
							"id", null// "zhaobiaoAction!toselectyufu.action?prepayApp.id=+ p.getId()"
							, p.getAddDept() + p.getAddName()
									+ " 有预付款申请单，请您审批！", true);
					if (epId != null && epId > 0) {
						p.setEpId(epId);
						CircuitRun circuitRun1 = (CircuitRun) totalDao.get(
								CircuitRun.class, epId);
						if ("同意".equals(circuitRun1.getAllStatus())
								&& "审批完成".equals(circuitRun1.getAuditStatus())) {
							p.setStatus("同意");
						} else {
							p.setStatus("未审批");
						}
						totalDao.update(p);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if ("未审批".equals(p.getStatus())) {
			}
			if (totalDao.update(p)) {
				return "修改成功！";
			} else {
				return "修改失败!";
			}
		}
		return "不存在该条数据，修改失败!";
	}

	@Override
	public WaigouOrder ByIdWaigouOrder(Integer id) {
		// TODO Auto-generated method stub
		return (WaigouOrder) totalDao.getObjectById(WaigouOrder.class, id);
	}

	@Override
	public PrepaymentsApplication ByIdpre(WaigouOrder order) {
		// TODO Auto-generated method stub
		PrepaymentsApplication application = null;
		if (order != null && order.getPlanNumber() != null) {
			application = (PrepaymentsApplication) totalDao
					.getObjectByCondition(
							"from PrepaymentsApplication where poNumber = ? order by id desc",
							order.getPlanNumber());
		}
		return application;
	}

	@Override
	public Object[] ByIdpreLisyt(WaigouOrder order) {
		// TODO Auto-generated method stub
		List list = null;
		if (order != null && order.getPlanNumber() != null) {
			list = totalDao.query(
					"from PrepaymentsApplication where poNumber = ?", order
							.getPlanNumber());
		}
		List<Float> num = totalDao
				.query(
						"select sum(yfbl) from PrepaymentsApplication where poNumber = ? and status <> '打回'",
						order.getPlanNumber());
		Float count = 100f;
		if (num != null && num.size() > 0) {
			count = 100 - num.get(0).floatValue();
		}
		Object[] o = { list, count };
		return o;
	}

	public String ppNumber() {
		String datetime = Util.getDateTime("yyyyMMdd");
		String hql = "select number from PrepaymentsApplication order by id desc";
		List list = totalDao.findAllByPage(hql, 0, 1);
		String contractNumber = "";
		String sc = "PP";
		if (list != null && list.size() > 0) {
			contractNumber = (String) list.get(0);
		}
		if (contractNumber != null && contractNumber.length() > 0) {
			try {
				int num = Integer.parseInt(contractNumber.substring(10,
						contractNumber.length())) + 1;
				if (num >= 1000)
					contractNumber = sc + datetime + num;
				else if ((num >= 100))
					contractNumber = sc + datetime + "0" + num;
				else if ((num >= 10))
					contractNumber = sc + datetime + "00" + num;
				else
					contractNumber = sc + datetime + "000" + num;
			} catch (Exception e) {
				contractNumber = sc + datetime + "0001";
			}
		} else {
			contractNumber = sc + datetime + "0001";
		}
		return contractNumber;
	}

	@Override
	public String addPrepayApp(PrepaymentsApplication pre, Integer id) {
		// TODO Auto-generated method stub
		WaigouOrder wg = ByIdWaigouOrder(id);
		if (wg != null) {
			Users loginUser = Util.getLoginUser();
			String newDate = Util.getDateTime();
			pre.setAddTime(newDate);
			pre.setAddTime(Util.getDateTime("yyyy-MM-dd"));
			pre.setAddDept(loginUser.getDept());// 添加人部门
			pre.setAddName(loginUser.getName());// 添加人
			pre.setJbName(loginUser.getName());// 申请人
			pre.setNumber(ppNumber());// 预付款单编号
			if (wg.getAllMoney() == null)
				wg.setAllMoney((double)pre.getAllMoney());
			pre.setYfMoney(wg.getAllMoney() * pre.getYfbl() / 100);
			pre.setYfMoneyDX(NumberToCN
					.NumberCN(pre.getYfMoney()));
			totalDao.save(pre);
			// 调用审批流程
			String processName = "预付款申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						PrepaymentsApplication.class, pre.getId(), "status",
						"id", null// "zhaobiaoAction!toselectyufu.action?prepayApp.id=+ p.getId()"
						, pre.getAddDept() + pre.getAddName()
								+ " 有预付款申请单，请您审批！", true);
				if (epId != null && epId > 0) {
					pre.setEpId(epId);
					CircuitRun circuitRun1 = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun1.getAllStatus())
							&& "审批完成".equals(circuitRun1.getAuditStatus())) {
						pre.setStatus("同意");
					} else {
						pre.setStatus("未审批");
					}
					if (totalDao.update(pre)) {
						return "添加成功！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "添加失败！";
	}

	@Override
	public Map<String, Object> byidZhUserDelivery(Integer id, String Mouth) {
		// TODO Auto-generated method stub
		if (Mouth == null || "".equals(Mouth)) {
			Mouth = Util.getDateTime("yyyy-MM");
		}
		ArrayList<String> mouthList = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (i > 0) {
				mouthList.add(Util.getLastMonth(mouthList.get(i - 1)));
			} else {
				mouthList.add(Mouth);
			}
		}
		Collections.reverse(mouthList);// 月份倒序排序
		ArrayList<Float> numberList = new ArrayList<Float>();
		ArrayList<Float> hegeList = new ArrayList<Float>();
		ArrayList<Float> jishiList = new ArrayList<Float>();
		for (String string : mouthList) {
			MouthHege mouth = (MouthHege) totalDao.getObjectByCondition(
					"from MouthHege where gysid = ? and mouth = ?", id, string);
			if (mouth == null) {
				numberList.add(0f);
				hegeList.add(0f);
				jishiList.add(0f);
			} else if (mouth.getBhgCount() != null) {
				numberList.add(mouth.getBhgCount());
			} else if (mouth.getQualifiedrate() != null) {
				hegeList.add(mouth.getQualifiedrate());
			} else if (mouth.getJiaofuhege() != null) {
				jishiList.add(mouth.getJiaofuhege());
			} else {
				numberList.add(0f);
				hegeList.add(0f);
				jishiList.add(0f);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("number", numberList);
		map.put("yuefen", mouthList);
		map.put("hege", hegeList);
		map.put("jishi", jishiList);
		return map;
	}

	/**
	 * 添加预付款申请及明细页面预备
	 * 
	 * @param ids
	 * @return
	 */
	public Map<String, Object> toaddxufuDatils(Integer[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		String sql_id = "";
		for (Integer id : ids) {
			if ("".equals(sql_id)) {
				sql_id += id;
			} else {
				sql_id += "," + id;
			}
		}
		String sql = "select gysName from WaigouOrder where id in(" + sql_id
				+ ") group  by gysName";
		List<String> gysNameList = totalDao.find(sql);
		if (gysNameList.size() > 1) {
			map.put("gysName", "只能选择同一家供应商进行填写");
			return map;
		} else {
			map.put("gysName", gysNameList.get(0));
		}
		String sql_list = "from PrepaymentsApplicationDetails where orderId in("
				+ sql_id + ") order by poNumber";
		List<PrepaymentsApplicationDetails> padList = totalDao.find(sql_list);
		map.put("padList", padList);
		Float zongCount = 0F;// 总金额
		String zongPlanNumber = "";
		List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList = new ArrayList<PrepaymentsApplicationDetails>();
		for (Integer id : ids) {
			WaigouOrder waigouOrder = (WaigouOrder) totalDao.get(
					WaigouOrder.class, id);
			if (waigouOrder != null) {
				Float dingCount = 0F;// 订单金额
				PrepaymentsApplicationDetails pad = new PrepaymentsApplicationDetails();
				pad.setOrderId(waigouOrder.getId());
				pad.setPoNumber(waigouOrder.getPlanNumber());
				pad.setYyName(waigouOrder.getGysName() + "_"
						+ waigouOrder.getPlanNumber());
				if (waigouOrder.getWwpSet() != null
						&& waigouOrder.getWwpSet().size() > 0) {
					Float money = 0F;
					for (WaigouPlan pl : waigouOrder.getWwpSet()) {
						if(!"取消".equals(pl.getStatus())){
						dingCount += (pl.getMoney() == null) ? 0f : Float
								.parseFloat(String
										.format("%.4f", pl.getMoney()));
						}
					}
				}
				dingCount = Float.parseFloat(String.format("%.2f", dingCount));
				pad.setAllMoney(dingCount);
				prepaymentsApplicationDetailsList.add(pad);
				zongCount += dingCount;
			}

			if (waigouOrder.getPlanNumber() != null) {
				if ("".equals(zongPlanNumber)) {
					zongPlanNumber += waigouOrder.getPlanNumber();
				} else {
					zongPlanNumber += ", " + waigouOrder.getPlanNumber();
				}
			}
		}
		PrepaymentsApplication prepayApp = new PrepaymentsApplication();
		prepayApp.setYyName(gysNameList.get(0) + " "
				+ Util.getDateTime("yyyy-MM" + "月预付款申请单"));
		zongCount = Float.parseFloat(String.format("%.2f", zongCount));
		prepayApp.setAllMoney(new BigDecimal(String.valueOf(zongCount)).doubleValue());
		prepayApp.setPoNumber(zongPlanNumber);
		map.put("prepaymentsApplicationDetailsList",
				prepaymentsApplicationDetailsList);
		map.put("prepayApp", prepayApp);
		return map;

	}

	/**
	 * 添加预付款申请及明细
	 * 
	 * @param prepayApp
	 * @param prepaymentsApplicationDetailsList
	 * @return
	 */
	public String addPrepaymentsApplicationDetail(
			PrepaymentsApplication prepayApp,
			List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList) {
		if (prepayApp != null) {
			Users loginUser = Util.getLoginUser();
			String newDate = Util.getDateTime();
			prepayApp.setAddTime(Util.getDateTime("yyyy-MM-dd"));
			prepayApp.setAddDept(loginUser.getDept());// 添加人部门
			prepayApp.setAddName(loginUser.getName());// 添加人
			prepayApp.setJbName(loginUser.getName());// 申请人
			prepayApp.setNumber(ppNumber());// 预付款单编号
			prepayApp.setAllMoneys(prepayApp.getAllMoney() + "");// 金额
			prepayApp.setYfMoneys(prepayApp.getYfMoney()+"");// 金额
			prepayApp.setNumber(ppNumber());// 预付款单编号
			prepayApp.setYfMoneyDX(NumberToCN.NumberCN(prepayApp.getYfMoney()));
			totalDao.save(prepayApp);
			for (PrepaymentsApplicationDetails prd : prepaymentsApplicationDetailsList) {
				prd.setPrepaymentsApplicationId(prepayApp.getId());
				prd.setYfMoneyDX(NumberToCN.NumberCN(prd.getYfMoney()
						.doubleValue()));
				prd.setAddDept(loginUser.getDept());// 添加人部门
				prd.setAddName(loginUser.getName());// 添加人
				prd.setJbName(loginUser.getName());// 申请人
				prepayApp.setAddTime(Util.getDateTime("yyyy-MM-dd"));
				totalDao.save(prd);
			}
			String processName = "预付款申请";
			Integer epId = null;
			try {
				epId = CircuitRunServerImpl.createProcess(processName,
						PrepaymentsApplication.class, prepayApp.getId(),
						"status", "id", null// "zhaobiaoAction!toselectyufu.action?prepayApp.id=+ p.getId()"
						, prepayApp.getAddDept() + prepayApp.getAddName()
								+ " 有预付款申请单，请您审批！", true);
				if (epId != null && epId > 0) {
					prepayApp.setEpId(epId);
					prepayApp.setJbName(loginUser.getName());
					CircuitRun circuitRun1 = (CircuitRun) totalDao.get(
							CircuitRun.class, epId);
					if ("同意".equals(circuitRun1.getAllStatus())
							&& "审批完成".equals(circuitRun1.getAuditStatus())) {
						prepayApp.setStatus("同意");
					} else {
						prepayApp.setStatus("未审批");
					}
					if (totalDao.update(prepayApp)) {
						return "添加成功！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return "系统异常！！";
		}
		return "系统异常！！";
	}

	/**
	 * 预付比例是否超
	 * 
	 * @param orderId
	 * @param yfBili
	 * @return
	 */
	public List<String> findBeyondBili(Integer[] orderId, Float yfBili) {
		try {
			String sql_id = "";
			for (Integer id : orderId) {
				String sql = "select sum(yfbl) from PrepaymentsApplicationDetails where orderId ="
						+ id;
				List<Double> yfblSum = totalDao.find(sql);
				Float cha = 100F - yfBili;
				double yfblSum1 = yfblSum.get(0);
				if (yfblSum1 > cha) {
					if ("".equals(sql_id)) {
						sql_id += id;
					} else {
						sql_id += "," + id;
					}
				}
			}
			if (!"".equals(sql_id)) {
				String sql_list = "select distinct poNumber from PrepaymentsApplicationDetails where orderId in("
						+ sql_id + ") group  by  poNumber";
				List<String> padList = totalDao.find(sql_list);
				return padList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询明细
	 * 
	 * @param prepaymentsApplicationId
	 * @return
	 */
	public Map<String, Object> findMingxi(Integer prepaymentsApplicationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (prepaymentsApplicationId != null
				&& !"".equals(prepaymentsApplicationId)) {
			PrepaymentsApplication prepayApp = (PrepaymentsApplication) totalDao
					.get(PrepaymentsApplication.class, prepaymentsApplicationId);
			map.put("prepayApp", prepayApp);
			String sql = "from PrepaymentsApplicationDetails where prepaymentsApplicationId ="
					+ prepaymentsApplicationId;
			List<PrepaymentsApplicationDetails> prepaymentsApplicationDetailsList = totalDao
					.find(sql);
			map.put("prepaymentsApplicationDetailsList",
					prepaymentsApplicationDetailsList);
		}
		return map;
	}
}