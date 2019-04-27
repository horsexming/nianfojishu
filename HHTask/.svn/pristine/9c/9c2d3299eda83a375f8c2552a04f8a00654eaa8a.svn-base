package com.task.ServerImpl.zhaobiao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.DefaultButtonModel;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.IPieceNumService;
import com.task.Server.bp.DetailService;
import com.task.Server.zhaobiao.DingdanServer;
import com.task.ServerImpl.PieceNumServiceImpl;
import com.task.ServerImpl.yw.ConvertNumber;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.InternalOrder;
import com.task.entity.InternalOrderDetail;
import com.task.entity.OrderManager;
import com.task.entity.Price;
import com.task.entity.ProductManager;
import com.task.entity.Users;
import com.task.entity.UsersLoginLog;
import com.task.entity.fin.budget.DeptMonthBudget;
import com.task.entity.sop.ProcardTemplate;
import com.task.util.SessionListener;
import com.task.util.Util;
import com.tast.entity.zhaobiao.Dingdanzhuanhua;
import com.tast.entity.zhaobiao.InternalOrderDetailzhaobiao;
import com.tast.entity.zhaobiao.InternalOrderzhaobiao;
import com.tast.entity.zhaobiao.Nianlilv;
import com.tast.entity.zhaobiao.Rukudan;
import com.tast.entity.zhaobiao.ZhUser;
import com.tast.entity.zhaobiao.Zhaobiao;
import com.tast.entity.zhaobiao.ZhaobiaoXi;
import com.tast.entity.zhaobiao.ZhaobiaoXis;
import com.tast.entity.zhaobiao.Zhmoban;
import com.tast.entity.zhaobiao.Zhtoubiao;
import com.tast.entity.zhaobiao.jihuadan;

public class DingdanActionImpl implements DingdanServer {
	private TotalDao totalDao;
	private IPieceNumService pns;
	private DetailService ds;
	private static final String AGREE = "同意";
	private static final String goBack = "打回";
	private static final String EXAMINE = "待审核";
	private static final String COMPLETE = "完成";
	private static final String PART = "采购部分";
	private static final String NOTPURCHASE = "未采购";
	private static final float ZERO = 0f;
	private static final int ONE = 1;
	private static final String YES = "是";
	private float shu;
	//
	private String[] pieceNum1;
	private String[] remerk1;
	private String title1;
	private String orderNum1;
	private Float[] num1;
	private String orderIdStr1;
	private String monthStr1;
	private Integer eid;
	
	//在职-在岗-试用-请假-内退- //实习-- 统筹外（退休)-离职中-离职
	public List listUserstatus() {
		List newlist=new ArrayList();
		
		//在职
		String sql1 = " select count(*) from Users where onWork='在职' and internal='是' ";
		List list1 = totalDao.createQuerySelect(null, sql1);
		newlist.add(list1.get(0));
		//在岗
		
		//试用
		String sql3 = " select count(*) from Users where onWork='试用'  and internal='是'";
		List list3 = totalDao.createQuerySelect(null, sql3);
		newlist.add(list3.get(0));
		//请假 Attendance
	       String time=Util.getDateTime("yyyy-MM-DD");
	       String sql4 = " select count(*) from ta_hr_Attendance where dateTime='"+time+"' ";
	       
	       Integer presentTime=Integer.parseInt(Util.getDateTime("HH"));
	       if (presentTime<12) {
	    	   sql4=sql4+" and morningStatus='请假'";
			}else {
				sql4=sql4+" and afternoonStatus='请假'";
			}
		List list4 = totalDao.createQuerySelect(null, sql4);
		newlist.add(list4.get(0));
		
		//内退   and internal='是'
		String sql5 = " select count(*) from Users where onWork='内退'  and internal='是'";
		List list5 = totalDao.createQuerySelect(null, sql5);
		newlist.add(list5.get(0));
		//实习
		String sql6 = " select count(*) from Users where onWork='实习'  and internal='是'";
		List list6 = totalDao.createQuerySelect(null, sql6);
		newlist.add(list6.get(0));
		//退休
		String sql7 = " select count(*) from Users where onWork='退休'  and internal='是'";
		List list7 = totalDao.createQuerySelect(null, sql7);
		newlist.add(list7.get(0));
		//离职中
		String sql8 = " select count(*) from Users where onWork='离职中'  and internal='是'";
		List list8 = totalDao.createQuerySelect(null, sql8);
		newlist.add(list8.get(0));
		//离职
		String sql9 = " select count(*) from Users where onWork='离职'  and internal='是'";
		List list9 = totalDao.createQuerySelect(null, sql9);
		newlist.add(list9.get(0));
		newlist.add(Util.getDateTime("yyyy-MM-dd HH:ss:mm"));
		return newlist;
//		String hql = "select onWork,count(onWork),getdate() from Users  group by onWork ";
//		List list = totalDao.query(hql);
		//return list;
	}
	public List listLoginUsers() {
		List sessions = SessionListener.getSessions();
		for (Object obj : sessions) {
			Users user = (Users) obj;
			if(user.getCode()==null) continue;
			String time = Util.getDateTime("yyyy-MM-dd");
			UsersLoginLog usersLoginLog =(UsersLoginLog) totalDao.getObjectByCondition(" from UsersLoginLog where userCode=? and dataTime =? ", user.getCode(),time);
			if(usersLoginLog !=null){
				user.setWhenOnlineLongOfDay(usersLoginLog.getWhenOnlineLong());
			}
		}
		return sessions;
	}
	public static void main(String[] args) {
		Integer presentTime=Integer.parseInt(Util.getDateTime("HH"));
		if (presentTime<12) {
			System.out.println("上午");
		}else {
			System.out.println("xi午");
		}
	}
	
	public Object[] listLoginUsers( Users rukudan,
			Integer parseInt, Integer pageSize) {
		List sessions = SessionListener.getSessions();
		
		if (sessions != null) {
			if (rukudan == null) {
				rukudan = new Users();
			}
			Iterator it = sessions.iterator();
			
			
			// String hql = totalDao.criteriaQueries(oManager, null, null);
			//String hql = "from Rukudan  order by  songhuoshijian desc";
			String hql = totalDao.criteriaQueries(rukudan, null, null)+" order by  songhuoshijian desc";
			List list = totalDao.findAllByPage(hql, parseInt, pageSize);
			int count = totalDao.getCount(hql);
			Object[] o = { list, count };
			return o;
		}
		return null;
	}
	
	
	//-----------------
	public void saveSgrk(GoodsStore goodsStore) {
		Goods g = new Goods();
		g.setGoodsMarkId(goodsStore.getGoodsStoreMarkId());// 件号
		g.setGoodsFullName(goodsStore.getGoodsStoreGoodsName());// 名称
		g.setGoodsLotId(goodsStore.getGoodsStoreLot());// 批次
		g.setGoodsFormat(goodsStore.getGoodsStoreFormat());
		String hql = "from Goods where goodsMarkId = ? and goodsLotId = ? and goodsFormat = ?";//件号    批次    规格
		Goods s = (Goods) totalDao.getObjectByCondition(hql, new Object[] {
				g.getGoodsMarkId(), g.getGoodsLotId(), g.getGoodsFormat() });
		if (s == null) {
			g.setGoodsUnit(goodsStore.getGoodsStoreUnit());// 单位
			if (goodsStore.getBeginning_num() != null) {
				g.setGoodsBeginQuantity(goodsStore.getBeginning_num());// 起初数量
				g.setGoodsCurQuantity(goodsStore.getGoodsStoreCount()
						+ goodsStore.getBeginning_num());// 数量
			} else {
				g.setGoodsBeginQuantity(0F);// 起初数量
				g.setGoodsCurQuantity(goodsStore.getGoodsStoreCount() + 0F);// 数量
			}

			g.setGoodsArtsCard(goodsStore.getGoodsStoreArtsCard());// 工艺卡号没有
			g.setGoodsProMarkId(goodsStore.getGoodsStoreProMarkId());// 总成件号没有
			g.setGoodsClass(goodsStore.getGoodsStoreWarehouse());// 所属仓库
			g.setGoodsPosition(goodsStore.getGoodsStorePosition());// 库位
			g.setGoodsPrice(goodsStore.getGoodsStorePrice().floatValue());// 价格
			g.setGoodsSupplier(goodsStore.getGoodsStoreSupplier());// 供应.
			g.setGoodsChangeTime(goodsStore.getGoodsStoreDate());// 日期
			if (goodsStore.getGoodsStoreZhishu() != null) {
				g.setGoodsZhishu(goodsStore.getGoodsStoreZhishu() == 0 ? null
						: goodsStore.getGoodsStoreZhishu());// 支数
			}
			g.setGoodsFormat(goodsStore.getGoodsStoreFormat());// 規格
			g.setGoodsCustomer(goodsStore.getGoodsStoreCompanyName());// 客户
			g.setGoodsZhishu(goodsStore.getGoodsStoreZhishu());
			totalDao.save(g);
		} else {
			s.setGoodsUnit(goodsStore.getGoodsStoreUnit());// 单位
			s.setGoodsCurQuantity(s.getGoodsCurQuantity()
					+ goodsStore.getGoodsStoreCount());
			if (goodsStore.getGoodsStoreZhishu() != null
					&& goodsStore.getGoodsStoreZhishu() > 0F) {
				s.setGoodsZhishu((s.getGoodsZhishu() == null ? 0 : s
						.getGoodsZhishu())
						+ goodsStore.getGoodsStoreZhishu());
			}
			totalDao.update(s);
		}
		totalDao.save(goodsStore);
	}
	//-----------
	public void updateRukudan(Rukudan r) {
		totalDao.update(r);
	}
	public Rukudan  ByIdrukudan( Integer id) {
		return (Rukudan) totalDao.getObjectById(
				Rukudan.class, id);
	}
	public void updatezhtoubiao(Zhtoubiao t) {
		totalDao.update(t);
	}
	public Zhtoubiao  byIdzhtoubiao( Integer id) {
		return (Zhtoubiao) totalDao.getObjectById(
				Zhtoubiao.class, id);
	}
	public Object[] rukuchaxun(Integer id, Zhtoubiao zhtoubiao,
			Integer parseInt, Integer pageSize) {
		if (zhtoubiao == null) {
			zhtoubiao = new Zhtoubiao();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from Zhtoubiao where tkong10 in (select id from ZhaobiaoXi where tkong7 !='N' and t10="+id+")";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	public Object[] listRukudan( Rukudan rukudan,
			Integer parseInt, Integer pageSize) {
		if (rukudan == null) {
			rukudan = new Rukudan();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		//String hql = "from Rukudan  order by  songhuoshijian desc";
		String hql = totalDao.criteriaQueries(rukudan, null, null)+" order by  songhuoshijian desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	
	public void updateDingdanzhuanhua(Dingdanzhuanhua d) {
		totalDao.update(d);
	}
	public void addRukudan(Rukudan r) {
		String hql1 = "from  Rukudan  order by songhuoshijian desc";
		Rukudan oldRukudan = (Rukudan) totalDao
				.getObjectByCondition(hql1);
		if (oldRukudan!=null) {
			r.setRukubianhao("PD-WA-"+Util.getDateTime("yyyyMM")+"-"+oldRukudan.getId());
		}else {
			r.setRukubianhao("PD-WA-"+Util.getDateTime("yyyyMM")+"1");
		}
		r.setStatus("质检中");
		totalDao.save(r);
	}
	public Dingdanzhuanhua byIDdingdan(Integer eid) {
		return (Dingdanzhuanhua) totalDao.getObjectById(Dingdanzhuanhua.class, eid);
	}
	//---------------------
	public Object[] listxiangxi(Integer id, Dingdanzhuanhua dingdanzhuanhua,
			Integer parseInt, Integer pageSize) {
		if (dingdanzhuanhua == null) {
			dingdanzhuanhua = new Dingdanzhuanhua();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from Dingdanzhuanhua  where internalOrderzhaobiaoid="
				+ id;
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}
	public InternalOrderzhaobiao byIdinternalOrderzhaobiao(Integer eid) {
		return (InternalOrderzhaobiao) totalDao.getObjectById(InternalOrderzhaobiao.class, eid);
	}
	public void updatetoubiao(InternalOrderzhaobiao internalOrderzhaobiao) {
		totalDao.update(internalOrderzhaobiao);
	}

	/*
	 * 添加采购计划详细
	 */
	public void addDingdanzhuanhua(String[] nams, Float[] zongshuliangs,
			String[] danweis, String[] guiges, Float[] caigous,
			String[] shijians, Integer[] oderids, Integer[] dateilids,
			String[] lexings, String[] dingdanIds, String[] jihuanid) {
		if (nams != null && nams.length > 0) {
			for (int i = 0; i < nams.length; i++) {
				Dingdanzhuanhua dindan = new Dingdanzhuanhua();
				dindan.setMarkId(nams[i]);
				dindan.setShuliang(zongshuliangs[i]);
				dindan.setDanwei(danweis[i]);
				dindan.setGuige(guiges[i]);
				dindan.setShiji(caigous[i]);
				dindan.setGenertorDate(shijians[i]);
				dindan.setInternalOrderzhaobiaoid(oderids[i]);
				dindan.setInternalOrderDetailzhaobiaoid(dateilids[i]);
				dindan.setLeibie(lexings[i]);
				dindan.setName(jihuanid[i]);
				dindan.setLeixing(dingdanIds[i]);
				dindan.setPaihao(Util.getDateTime("yyyy-MM-dd HH:ss:mm"));
				totalDao.save(dindan);

				if (i == 0) {
					InternalOrderzhaobiao newid = (InternalOrderzhaobiao) totalDao
							.getObjectById(InternalOrderzhaobiao.class,
									oderids[i]);
					newid.setStatus("采购中");
					newid.setGenertorDate(Util
							.getDateTime("yyyy-MM-dd HH:ss:mm"));
					// --------------------------------------
					for (int j = 0; j < dingdanIds[i].length(); j++) {
						String[] strarray = dingdanIds[i].split(",");
						HashSet<OrderManager> ordermangers = new HashSet();
						for (int k = 0; k < strarray.length; k++) {
							// OrderManager or=new OrderManager();
							// or.setId(Integer.parseInt(strarray[k]));
							OrderManager oManager = (OrderManager) totalDao
									.getObjectById(OrderManager.class, Integer
											.parseInt(strarray[k]));
							ordermangers.add(oManager);
						}
						newid.setOrderManagers(ordermangers);
					}
					// -----------------------------------------------

					// -----------------------------------------
					totalDao.update(newid);
				}
			}
		}

	}

	/*
	 * 选择计划单下的需要采购的原材料
	 */
	public List listxuanzhejihua(int[] select) {
		List list = new ArrayList(select.length);
		for (int i = 0; i < select.length; i++) {
			List newlist = totalDao.query("from jihuadan  where id="
					+ select[i]);
			list.addAll(newlist);
		}
		// ------------
		/*
		 * for (int j = 0; j < list.size(); j++) { jihuadan newj=(jihuadan)
		 * list.get(j); String[] dingdanId=newj.getDingdanId().split(","); for
		 * (int k = 0; k < dingdanId.length; k++) { String matk=""; if
		 * ("外购".equals(newj.getLeixing())) { matk=newj.getMarkId(); } else {
		 * matk=newj.getPaihao(); } }
		 * 
		 * }
		 */

		// ------------------
		return list;
	}

	// ------------------
	public List showcaigou(Integer id) {
		List list = totalDao
				.query("from Dingdanzhuanhua  where internalOrderzhaobiaoid="
						+ id);
		return list;
	}

	// --------------------------------------------
	public List listAlljihuaByinternalOrderID() {
		List list = totalDao
				.query("from jihuadan  where internalOrderzhaobiaoid=" + eid);
		return list;
	}

	// ///---------------------------------------------------------------
	/*
	 * 添加采购项目
	 */
	public void addzhaobiaoAddZhabiaoXi(Zhaobiao zhaobiao, ZhaobiaoXis xs) {
		Users user = Util.getLoginUser();
		zhaobiao.setFaburen(user.getName());
		zhaobiao.setFabushijian(Util.getDateTime());
		zhaobiao.setStatus("W");
		totalDao.save(zhaobiao);
		String[] yuefen = zhaobiao.getMoban().split("-");
		String hql1 = " from  Nianlilv  where nianfen like '%" + yuefen[0]
				+ "%' ";
		Nianlilv huixi = (Nianlilv) totalDao.getObjectByCondition(hql1);
		// ------------
		if (xs != null) {
			for (int i = 0; i < xs.getT1().length; i++) {
				if (Float.parseFloat(xs.getT2()[i]) > 0) {
					int zhmobanId = xs.getT1()[i];
					Zhmoban zhmoban = (Zhmoban) totalDao.getObjectById(
							Zhmoban.class, zhmobanId);
					int dmbId = xs.getT9()[i];
					DeptMonthBudget dmb = (DeptMonthBudget) totalDao
							.getObjectById(DeptMonthBudget.class, dmbId);

					ZhaobiaoXi mx = new ZhaobiaoXi();
					// mx.setId(ms.getId()[i]);
					// mx.setT1(id1);
					mx.setT1(xs.getT1()[i]);
					mx.setT2(xs.getT2()[i]);
					mx.setT3(xs.getT3()[i]);
					mx.setT4(xs.getT4()[i]);
					if (xs.getT5()[i] != null) {
						mx.setT5(xs.getT5()[i]);
					}
					mx.setT6(xs.getT6()[i]);
					mx.setLilv(huixi.getLilv());
					mx.setT7("N");
					mx.setT8(xs.getT8()[i]);
					mx.setT9(xs.getT9()[i]);
					mx.setT10(zhaobiao.getId());
					mx.setZhmoban(zhmoban);// 模板 ID
					mx.setDeptMonthBudget(dmb);// 预算
					mx.setJihuaId(xs.getT11()[i]);// 计划Id
					totalDao.save(mx);
					// -----------修改计划单的已抓换的数量
					// jihuadan jihuadan = (jihuadan) totalDao.getObjectById(
					// jihuadan.class, xs.getT11()[i]);
					// if (xs.getT2()[i] != null) {
					// if (jihuadan.getShiji() == null) {
					// jihuadan.setShiji(Float.parseFloat(xs.getT2()[i]));
					// } else {
					// Float shiji = jihuadan.getShiji()
					// - Float.parseFloat(xs.getT2()[i]);
					// jihuadan.setShiji(shiji);
					// }
					//
					// totalDao.update(jihuadan);
					// }
				}

			}

		}

	}

	// ajax下拉模版
	public List findAllDept() {
		List list = totalDao.query("from Zhmoban");
		return list;
	}

	// -----------------------------------------
	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	public InternalOrderzhaobiao byIDOrder(Integer id) {
		return (InternalOrderzhaobiao) totalDao.getObjectById(
				InternalOrderzhaobiao.class, id);
	}

	public List ByInternalOrderzhaobiaoId(Integer id) {
		String hql = "  from Dingdanzhuanhua where internalOrderzhaobiaoid=?";
		List list = totalDao.query(hql, id);
		return list;
	}

	public Object[] listjihuadan(Integer id, Dingdanzhuanhua dingdanzhuanhua,
			Integer parseInt, Integer pageSize) {
		if (dingdanzhuanhua == null) {
			dingdanzhuanhua = new Dingdanzhuanhua();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from Dingdanzhuanhua  where internalOrderDetailzhaobiaoid="
				+ id;
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public Object[] listOrderManager(OrderManager oManager, Integer parseInt,
			Integer pageSize) {
		if (oManager == null) {
			oManager = new OrderManager();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from OrderManager  order by paymentDate desc";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	@Override
	public List listAllXunahong(int[] selected) {
		// TODO Auto-generated method stub
		List list = new ArrayList(selected.length);
		for (int i = 0; i < selected.length; i++) {
			OrderManager oManager = (OrderManager) totalDao.getObjectById(
					OrderManager.class, selected[i]);
			list.add(i, oManager);
		}
		return list;
	}

	public Object[] integrationOrderDetail(int[] selected) {
		if (selected != null && selected.length > 0) {
			String title = "采购计划(";
			Map<String, ProductManager> map = new HashMap<String, ProductManager>();
			for (int i = 0; i < selected.length; i++) {
				int orderId = selected[i];
				OrderManager om = (OrderManager) totalDao.getObjectById(
						OrderManager.class, orderId);
				if (i == 0)
					title = title + om.getCustome().getCompanyAbbreviation()
							+ ")";
				Iterator<ProductManager> in = om.getProducts().iterator();
				while (in.hasNext()) {
					ProductManager pm = in.next();
					if (map.containsKey(pm.getPieceNumber())) {
						ProductManager old = map.get(pm.getPieceNumber());
						old.setNum(old.getNum() + pm.getNum());
						map.put(old.getPieceNumber(), old);
					} else {
						ProductManager newPM = new ProductManager();
						BeanUtils.copyProperties(pm, newPM, new String[] {
								"id", "unitPrice", "unit", "orderManager" });
						map.put(pm.getPieceNumber(), newPM);
					}
				}
			}
			List list = new ArrayList(map.values());
			return new Object[] { list, title };
		}

		return null;
	}

	public Object[] integrationOrderDetail1(int[] selected) {
		if (selected != null && selected.length > 0) {
			String title = "采购计划(";
			Map<String, ProductManager> map = new HashMap<String, ProductManager>();
			for (int i = 0; i < selected.length; i++) {
				int orderId = selected[i];
				OrderManager om = (OrderManager) totalDao.getObjectById(
						OrderManager.class, orderId);
				if (i == 0)
					title = title + om.getCustome().getCompanyAbbreviation()
							+ ")";
				Iterator<ProductManager> in = om.getProducts().iterator();
				while (in.hasNext()) {
					ProductManager pm = in.next();
					if (map.containsKey(pm.getPieceNumber())) {
						ProductManager old = map.get(pm.getPieceNumber());
						old.setNum(old.getNum() + pm.getNum());
						map.put(old.getPieceNumber(), old);
					} else {
						ProductManager newPM = new ProductManager();
						BeanUtils.copyProperties(pm, newPM, new String[] {
								"id", "unitPrice", "unit", "orderManager" });
						map.put(pm.getPieceNumber(), newPM);
					}
				}
			}
			List list = new ArrayList(map.values());
			pieceNum1 = new String[list.size()];
			remerk1 = new String[list.size()];
			num1 = new Float[list.size()];
			// ---------------------------
			// --------------------------------------------------
			orderIdStr1 = "";
			for (int i = 0; i < selected.length; i++) {
				// orderIdStr1=orderIdStr1+Integer.toHexString(selected[i])+",";
				orderIdStr1 = orderIdStr1 + selected[i] + ",";
			}
			// orderIdStr1=orderIdStr1+selected;
			for (int i = 0; i < list.size(); i++) {
				ProductManager newp = (ProductManager) list.get(i);
				pieceNum1[i] = newp.getPieceNumber();
				remerk1[i] = "0";
				num1[i] = newp.getNum();
				monthStr1 = "0000-00-00";
			}

			batchConversionOrder(pieceNum1, num1, remerk1, orderIdStr1,
					monthStr1);
			// -------------------------------------
			// ---------------------------------------------------
			return new Object[] { list, title };
		}
		return null;
	}

	public void batchConversionOrder(String[] pieceNum, Float[] num,
			String[] remark, String orderIdStr, String monthStr) {
		String[] orderStr = orderIdStr.split(",");
		InternalOrderzhaobiao newIO = new InternalOrderzhaobiao();
		// newIO.setStatus(EXAMINE);
		newIO.setStatus("待审核");
		Calendar cl = Calendar.getInstance();
		int year = cl.get(Calendar.YEAR);
		int month = cl.get(Calendar.MONTH) + 1;
		Random rom = new Random();
		String yearStr = (year + "").substring(2, 4);
		String newDate = ConvertNumber.conversionDateStr();
		String newNum = "";
		newIO.setGenertorDate(monthStr);
		newIO.setNewDate(newDate);
		// newIO.setWhetherPurchase(NOTPURCHASE);
		newIO.setWhetherPurchase("未采购");

		newIO.setIsVali(0);
		for (int i = 0; i < orderStr.length; i++) {
			// OrderManager om =
			// totalDao.getOrderById(Integer.parseInt(orderStr[i]));
			OrderManager om = (OrderManager) totalDao.getObjectById(
					OrderManager.class, Integer.parseInt(orderStr[i]));
			if (i == 0) {
				newIO.setDocumentaryPeople(om.getDocumentaryPeople());
				// newIO.setFlow(om.getDocumentaryPeople());
				newNum = om.getCustome().getCompanyAbbreviation() + "-"
						+ yearStr + "-" + month + "-" + rom.nextInt(1000);
				newIO.setCustome(om.getCustome());
			}
			// om.getInnerOrders().add(newIO);
			om.getInnerOrderszhaobiao().add(newIO);
			// --------------------------------------

			// --------------------------------------
			newIO.getOuterOrderszhaobiao().add(om);
		}
		newIO.setNum(newNum);
		for (int i = 0; i < pieceNum.length; i++) {
			Float remainder = num[i];
			String hql = "from Price p where p.partNumber = ?";
			List list = totalDao.query(hql, pieceNum[i]);
			Price price = new Price();
			if (list != null && list.size() > 0) {
				price = (Price) list.get(0);
			}
			// Price price = pns.getPriceByPieceNum(pieceNum[i]);
			InternalOrderDetailzhaobiao iod = new InternalOrderDetailzhaobiao(
					price.getName(), price.getPartNumber(), num[i], 0F,
					remark[i], ZERO);
			newIO.getInterOrderDetailszhaobiao().add(iod);
			iod.setInternalOrderzhaobiao(newIO);

			// 判断计划数量是否为0
			if (num[i] != 0) {
				// -------------------------------------通过建好创建采购计划单--------------------------------------------------------------
				// 查第一层父类 --ID
				String hql1 = " from  ProcardTemplate  where markid=?";
				ProcardTemplate fatherp = (ProcardTemplate) totalDao
						.getObjectByCondition(hql1, price.getPartNumber());
				// --------------------------------------
				 //String hql2 = "from ProcardTemplate where rootid=(select id from ProcardTemplate where markid=? ) and procardstyle='自制'";
				//String hql2 = "from ProcardTemplate where rootid=(select id from ProcardTemplate where markid=? and procardStyle='自制' ) and procardstyle in ('自制','外购')";
				String hql2 = "from ProcardTemplate where rootid in (select id from ProcardTemplate where markid=? and procardstyle='总成' ) and procardstyle='自制'";
				List listjiahua = totalDao.query(hql2, price.getPartNumber());
				if (listjiahua != null && listjiahua.size() > 0) {
					for (int j = 0; j < listjiahua.size(); j++) {
						jihuadan jihuadan = new jihuadan();
						// ProcardTemplate p=new ProcardTemplate();
						ProcardTemplate newp = (ProcardTemplate) listjiahua
								.get(j);
						// ------------------------------------查询上层id是否等于父类id-------------------------------------------------
						if (newp.getFatherId().equals(fatherp.getId())) {
							// zizhiAdd(newp, iod, newIO, num[i]);
							jihuadan.setMarkId(newp.getMarkId());
							jihuadan.setPaihao(newp.getTrademark());
							jihuadan.setName(newp.getProName());
							jihuadan.setLeibie(newp.getClClass());
							jihuadan.setGuige(newp.getSpecification());
							jihuadan.setGenertorDate(newIO.getGenertorDate());
							jihuadan.setProcardTemplateid(newp.getId());
							jihuadan.setInternalOrderzhaobiaoid(newIO.getId());
							jihuadan.setInternalOrderDetailzhaobiaoid(iod
									.getId());
							jihuadan.setLeixing(newp.getProcardStyle());
							// 总成 组合 CorrCount()
							// 自制件 外购件 Quanzi2()
							// 计算数量
							String mark = "";
							if (newp.getQuanzi2() == null
									|| newp.getQuanzi1() == null) {// 判断是否有残缺数据
								continue;
							} else {
								if ("外购".equals(newp.getProcardStyle())) {

									double shuliang = Math.ceil(num[i]
											* newp.getQuanzi2()
											/ newp.getQuanzi1());
									float shuliangF = (float) shuliang;
									jihuadan.setDanwei(newp.getUnit());
									jihuadan.setShuliang(shuliangF);
									mark = jihuadan.getMarkId();
								} else {
									if (newp.getCorrCount() == null) {
										continue;
									} else {
										double shuliang = Math.ceil(num[i]
												* newp.getQuanzi2()
												/ newp.getQuanzi1()
												* newp.getCorrCount());
										float shuliangF = (float) shuliang;
										jihuadan.setShuliang(shuliangF);
										jihuadan.setDanwei(newp.getYuanUnit());
										mark = jihuadan.getPaihao();
									}

								}
							}

							// 订单编号
							jihuadan.setBiaohao(newIO.getNum());
							jihuadan.setDingdanId(orderIdStr);
							// 查采购计划表-------------------------------------------------------------
							/*
							 * String sqlt =
							 * "select sum(d.shiji)  from ta_crm_Dingdanzhuanhua d,Order_caigou oc,TA_OrderManager  o   where d.id=oc.caigouId and o.pk_id=oderId  and  markId=?  and   oc.oderId=?"
							 * ; String[] strarray = orderIdStr.split(","); for
							 * (int l = 0; l < strarray.length; l++) { if
							 * ("外购".equals(newp.getProcardStyle())) { String
							 * mark = "外购件:" + jihuadan.getMarkId(); List list2t
							 * = totalDao.createQuerySelect( null, sqlt, mark,
							 * Integer .parseInt(strarray[l]));//
							 * 更具markId和订单id查选建好的所有采购记录
							 * 
							 * // 统计已经采购的数量 if (list2t != null && list2t.size()
							 * > 0) { float caigouzong = 0F; for (int h = 0; h <
							 * list2t.size(); h++) {// for累加采购数量 Dingdanzhuanhua
							 * hua = (Dingdanzhuanhua) list2t .get(h);
							 * caigouzong = caigouzong + hua.getShiji(); } //
							 * --- jihuadan.setShuliang(jihuadan .getShuliang()
							 * - caigouzong); }
							 * 
							 * } else { // String mark = "材料:" +
							 * jihuadan.getPaihao(); List list2t =
							 * totalDao.createQuerySelect( null, sqlt, mark,
							 * Integer .parseInt(strarray[l]));//
							 * 更具markId和订单id查选建好的所有采购记录 //
							 * --------------.........
							 * .................................. // 统计已经采购的数量
							 * if (list2t != null && list2t.size() > 0) { float
							 * caigouzong = 0F; for (int l1 = 0; l1 <
							 * list2t.size(); l1++) {// for累加采购数量
							 * Dingdanzhuanhua hua = (Dingdanzhuanhua) list2t
							 * .get(l1); caigouzong = caigouzong +
							 * hua.getShiji(); } // ---
							 * jihuadan.setShuliang(jihuadan .getShuliang() -
							 * caigouzong); } //
							 * ////////---.................................. } }
							 */
							// ///--------------------------------------
							String orderIdStrs = orderIdStr.substring(0,
									orderIdStr.lastIndexOf(","));
							String sql = " select sum(shiji) from ta_crm_Dingdanzhuanhua where markId='"
									+ mark
									+ "' and internalOrderzhaobiaoid in (select distinct i.pk_id from  ta_crm_internalOrderzhaobiao i,TA_OrderManager o ,Order_caigoujihua cj "
									+ " where o.pk_id=cj.oderId  and cj.jihuaId=i.pk_id  and i.f_status='同意'   and o.pk_id in ("
									+ orderIdStrs + "))";
							List list2 = totalDao.createQuerySelect(null, sql);// 得到采购计划id
							Float yi = 0F;
							if (list2 != null && list2.size() > 0
									&& list2.get(0)!=null) {
								yi = Float.parseFloat(list2.get(0).toString());
							}
							jihuadan.setYijin(yi);
							//----------------
							if ("外购".equals(newp.getProcardStyle())) {
								String hql16 = " from  jihuadan  where markid=? and internalOrderzhaobiaoid=?";
								jihuadan oldjihuadan = (jihuadan) totalDao
										.getObjectByCondition(hql16, jihuadan.getMarkId(),jihuadan.getInternalOrderzhaobiaoid());
									if (oldjihuadan!=null) {
										oldjihuadan.setShuliang(oldjihuadan.getShuliang()+jihuadan.getShuliang());
									}else {
										//totalDao.save(jihuadan);
									}
									
							}else {
								String hql16 = " from  jihuadan  where paihao=? and internalOrderzhaobiaoid=? and guige=?";
								jihuadan oldjihuadan = (jihuadan) totalDao
										.getObjectByCondition(hql16, jihuadan.getPaihao(),jihuadan.getInternalOrderzhaobiaoid(),jihuadan.getGuige());
									if (oldjihuadan!=null) {
										oldjihuadan.setShuliang(oldjihuadan.getShuliang()+jihuadan.getShuliang());
									}else {
										totalDao.save(jihuadan);
									}
								
							}
							
							//-----------------

							//totalDao.save(jihuadan);
							/*
							 * 
							 */

						} else {
							gerFatherValue(fatherp, newp);
							// 递归循环完毕
							jihuadan.setMarkId(newp.getMarkId());
							jihuadan.setPaihao(newp.getTrademark());
							jihuadan.setName(newp.getProName());
							jihuadan.setLeixing(newp.getProcardStyle());
							String mark = "";
							if ("外购".equals(newp.getProcardStyle())) {
								jihuadan.setDanwei(newp.getUnit());
								mark = jihuadan.getMarkId();
							} else {
								jihuadan.setDanwei(newp.getYuanUnit());
								mark = jihuadan.getPaihao();
							}
							// jihuadan.setLeibie(newp.getClClass());
							jihuadan.setLeibie("管料");
							jihuadan.setGuige(newp.getSpecification());
							jihuadan.setGenertorDate(newIO.getGenertorDate());
							jihuadan.setProcardTemplateid(newp.getId());
							jihuadan.setInternalOrderzhaobiaoid(newIO.getId());
							jihuadan.setInternalOrderDetailzhaobiaoid(iod
									.getId());
							// //----
							// if ("外购".equals(newp.getProcardStyle())) {
							// double shuliang = Math.ceil(num[i]*
							// shu*newp.getCorrCount());
							// float shuliangF = (float) shuliang;
							// jihuadan.setShuliang(shuliangF);
							// }else {
							// double shuliang = Math.ceil(num[i]*
							// shu*newp.getQuanzi2()
							// / newp.getQuanzi1() );
							// float shuliangF = (float) shuliang;
							// jihuadan.setShuliang(shuliangF);
							// }
							if (newp.getQuanzi2() == null
									|| newp.getQuanzi1() == null) {// 判断是否有残缺数据
								continue;
							} else {
								double shuliang = Math
										.ceil(num[i] * shu * newp.getQuanzi2()
												* newp.getQuanzi1());
								float shuliangF = (float) shuliang;
								jihuadan.setShuliang(shuliangF);
							}

							// 订单编号
							jihuadan.setBiaohao(newIO.getNum());
							jihuadan.setDingdanId(orderIdStr);
							// -----------------------------------------------------------------------
							String orderIdStrs = orderIdStr.substring(0,
									orderIdStr.lastIndexOf(","));
							String sql = " select sum(shiji) from ta_crm_Dingdanzhuanhua where markId='"
									+ mark
									+ "' and internalOrderzhaobiaoid in (select distinct i.pk_id from  ta_crm_internalOrderzhaobiao i,TA_OrderManager o ,Order_caigoujihua cj "
									+ " where o.pk_id=cj.oderId  and cj.jihuaId=i.pk_id  and i.f_status='同意'   and o.pk_id in ("
									+ orderIdStrs + "))";
							List list2 = totalDao.createQuerySelect(null, sql);// 得到采购计划id
							Float yi = 0F;
							if (list2 != null && list2.size() > 0&& list2.get(0)!=null) {
								yi = Float.parseFloat(list2.get(0).toString());
							}
							jihuadan.setYijin(yi);
							// ---------------------------------------------------
							if ("外购".equals(newp.getProcardStyle())) {
								String hql16 = " from  jihuadan  where markid=? and internalOrderzhaobiaoid=?";
								jihuadan oldjihuadan = (jihuadan) totalDao
										.getObjectByCondition(hql16, jihuadan.getMarkId(),jihuadan.getInternalOrderzhaobiaoid());
									if (oldjihuadan!=null) {
										oldjihuadan.setShuliang(oldjihuadan.getShuliang()+jihuadan.getShuliang());
									}else {
										//totalDao.save(jihuadan);
									}
									
							}else {
								String hql16 = " from  jihuadan  where paihao=? and internalOrderzhaobiaoid=? and guige=?";
								jihuadan oldjihuadan = (jihuadan) totalDao
										.getObjectByCondition(hql16, jihuadan.getPaihao(),jihuadan.getInternalOrderzhaobiaoid(),jihuadan.getGuige());
									if (oldjihuadan!=null) {
										oldjihuadan.setShuliang(oldjihuadan.getShuliang()+jihuadan.getShuliang());
									}else {
										totalDao.save(jihuadan);
									}
								
							}
							
							//--------------------------------------------------------------
							//totalDao.save(jihuadan);
							shu = 0F;
						}

					}
				}
			}
		}
		totalDao.save(newIO);
		eid = newIO.getId();
		// List
		// list=totalDao.query("from jihuadan  where internalOrderzhaobiaoid="+newIO.getId());
		// return list;
		// String
		// sql2="select markID,sum(shuliang) shuliang from ta_crm_jihuadan   where internalOrderzhaobiaoid="+newIO.getId()+" and    leixing='外购'  group by  markId";
		// List list2 = totalDao.createQuerySelect(null, sql2);
		// for (int j = 1; j <list2.size(); j=j+2) {
		// Dingdanzhuanhua zhuanhua=new Dingdanzhuanhua();
		// zhuanhua.setMarkId(list2.get(j-1).toString());
		// zhuanhua.setShuliang((Float)list2.get(j));
		// }
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	/*
	 * 循環回調函數 查詢上層父類
	 */
	public void gerFatherValue(ProcardTemplate fatherp, ProcardTemplate newp) {
		if (!newp.getFatherId().equals(fatherp.getId())) {
			String hql12 = " from  ProcardTemplate  where rootId=? and  id=?";
			ProcardTemplate newp1 = (ProcardTemplate) totalDao
					.getObjectByCondition(hql12, fatherp.getId(), newp
							.getFatherId());
			if (shu == 0F) {
				shu = newp1.getCorrCount();
				// shu=newp.getQuanzi2()/ newp.getQuanzi1()*newp1.getQuanzi2()/
				// newp1.getQuanzi1();
				// shu=newp.getCorrCount();
			} else {
				if (newp1.getCorrCount() != null) {// 判断是否有残缺数据
					shu = shu * newp1.getCorrCount();
				}
				newp = newp1;
				gerFatherValue(fatherp, newp);

			}
		}
	}

	public float getShu() {
		return shu;
	}

	public void setShu(float shu) {
		this.shu = shu;
	}

	// 自制添加
	public void zizhiAdd(ProcardTemplate newp, InternalOrderDetailzhaobiao iod,
			InternalOrderzhaobiao newIO, int num) {
		jihuadan jihuadan = new jihuadan();
		jihuadan.setMarkId(newp.getMarkId());
		jihuadan.setPaihao(newp.getTrademark());
		jihuadan.setName(newp.getProName());
		jihuadan.setDanwei(newp.getYuanUnit());
		jihuadan.setGuige(newp.getRemark());
		jihuadan.setGenertorDate(newIO.getGenertorDate());
		jihuadan.setProcardTemplateid(newp.getId());
		jihuadan.setInternalOrderzhaobiaoid(newIO.getId());
		jihuadan.setInternalOrderDetailzhaobiaoid(iod.getId());
		// 计算数量
		double shuliang = Math.ceil(num * newp.getQuanzi2() / newp.getQuanzi1()
				* newp.getCorrCount());
		float shuliangF = (float) shuliang;
		jihuadan.setShuliang(shuliangF);
		// 订单编号
		jihuadan.setBiaohao(newIO.getNum());
		totalDao.save(jihuadan);
	}

	public Object[] listInternalOrderzhaobiao(
			InternalOrderzhaobiao internalOrderzhaobiao, Integer parseInt,
			Integer pageSize) {
		if (internalOrderzhaobiao == null) {
			internalOrderzhaobiao = new InternalOrderzhaobiao();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from InternalOrderzhaobiao where status = '同意' ";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listInternalOrderzhaobiao1(
			InternalOrderzhaobiao internalOrderzhaobiao, Integer parseInt,
			Integer pageSize) {
		if (internalOrderzhaobiao == null) {
			internalOrderzhaobiao = new InternalOrderzhaobiao();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from InternalOrderzhaobiao where status in ('同意','审核中') ";
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public Object[] listInternalOrderDetail(
			InternalOrderDetailzhaobiao internalOrderDetailzhaobiao,
			Integer parseInt, Integer pageSize, Integer id) {
		if (internalOrderDetailzhaobiao == null) {
			internalOrderDetailzhaobiao = new InternalOrderDetailzhaobiao();
		}
		// String hql = totalDao.criteriaQueries(oManager, null, null);
		String hql = "from InternalOrderDetailzhaobiao i where i.internalOrderzhaobiao.id =  "
				+ id;
		List list = totalDao.findAllByPage(hql, parseInt, pageSize);
		int count = totalDao.getCount(hql);
		Object[] o = { list, count };
		return o;
	}

	public IPieceNumService getPns() {
		return pns;
	}

	public void setPns(IPieceNumService pns) {
		this.pns = pns;
	}

	public DetailService getDs() {
		return ds;
	}

	public void setDs(DetailService ds) {
		this.ds = ds;
	}

	public static String getAgree() {
		return AGREE;
	}

	public static String getGoback() {
		return goBack;
	}

	public static String getExamine() {
		return EXAMINE;
	}

	public static String getComplete() {
		return COMPLETE;
	}

	public static String getPart() {
		return PART;
	}

	public static String getNotpurchase() {
		return NOTPURCHASE;
	}

	public static float getZero() {

		return ZERO;
	}

	public static int getOne() {
		return ONE;
	}

	public static String getYes() {
		return YES;
	}

	public String[] getPieceNum1() {
		return pieceNum1;
	}

	public void setPieceNum1(String[] pieceNum1) {
		this.pieceNum1 = pieceNum1;
	}

	public String[] getRemerk1() {
		return remerk1;
	}

	public void setRemerk1(String[] remerk1) {
		this.remerk1 = remerk1;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getOrderNum1() {
		return orderNum1;
	}

	public void setOrderNum1(String orderNum1) {
		this.orderNum1 = orderNum1;
	}

	public Float[] getNum1() {
		return num1;
	}

	public void setNum1(Float[] num1) {
		this.num1 = num1;
	}

	public String getOrderIdStr1() {
		return orderIdStr1;
	}

	public void setOrderIdStr1(String orderIdStr1) {
		this.orderIdStr1 = orderIdStr1;
	}

	public String getMonthStr1() {
		return monthStr1;
	}

	public void setMonthStr1(String monthStr1) {
		this.monthStr1 = monthStr1;
	}


}
