package com.task.ServerImpl.ess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.ess.GoodsStoreServer;
import com.task.Server.ess.SellNewServer;
import com.task.ServerImpl.AlertMessagesServerImpl;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.entity.Goods;
import com.task.entity.GoodsStore;
import com.task.entity.Price;
import com.task.entity.ProcardBl;
import com.task.entity.Sell;
import com.task.entity.Users;
import com.task.entity.WarehouseNumber;
import com.task.entity.menjin.WareBangGoogs;
import com.task.entity.sop.ManualOrderPlan;
import com.task.entity.sop.ManualOrderPlanDetail;
import com.task.entity.sop.Procard;
import com.task.entity.sop.ProcardProductRelation;
import com.task.entity.sop.ProcessAndWgProcardTem;
import com.task.entity.sop.ProcessInfor;
import com.task.entity.sop.RunningWaterCard;
import com.task.entity.sop.WaigouWaiweiPlan;
import com.task.entity.sop.qd.LogoStickers;
import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.util.DateUtil;
import com.task.util.Util;
import com.tast.entity.zhaobiao.ZhUser;

public class SellNewServerImpl implements SellNewServer {
	private TotalDao totalDao;
	private YuanclAndWaigj  wgj ;
	private GoodsStoreServer goodsStoreServer;
	
	public YuanclAndWaigj getWgj() {
		return wgj;
	}

	public void setWgj(YuanclAndWaigj wgj) {
		this.wgj = wgj;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	/**
	 * 上层已领数量(klNumber-hasCount)为下层配套数量（当下层有外委关联零件时将此零件的外委数量算入配套中
	 * （先配套未外委再配套被外委的数量））
	 */
	@Override
	public Map<Integer, Object> findSellOutlist(Procard procard, String ckType,
			String status) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		String errmsg = "";
		List listGoods = new ArrayList();
		Map<String, Integer> flagMap = new HashMap<String, Integer>();
		Integer maxFlag = 0;
		// Float getCount = procard.getGetCount();
		if (procard.getHascount() == null) {
			procard.setHascount(procard.getKlNumber());
		}
		if (procard.getGetCount() == null) {
			procard.setGetCount(procard.getHascount());
		}
		if (null != procard) {
			String phoneSql = "";
			if ("phone".equals(status)) {
				phoneSql = " and goodsId in (select fk_good_id from WareBangGoogs )";
			}
			// 查找到工艺卡片
			if (procard.getHascount() == null) {
				procard.setHascount(procard.getKlNumber());
			}
			// if(procard.getGetCount()==null){
			// procard.setGetCount(procard.getHascount());
			// }else if(procard.getGetCount()>procard.getHascount()){
			// procard.setGetCount(procard.getHascount());
			// }
			String procardSyle = procard.getProcardStyle();
			if ("外购".equals(procardSyle)
					&& (procard.getNeedProcess() == null || procard
							.getNeedProcess().equals("no"))) {
				map.put(3, "对不起，该外购件不可领料");
				return map;
			}
			// 查出下层外购件需领
			String hql2 = "from Procard where fatherId=? and procardStyle='外购' and (sbStatus is null or sbStatus !='删除') and (lingliaostatus is null or lingliaostatus!='否') and (needProcess !='yes' or needProcess is null)";
			String needprocesswgj = "from Procard where id=? and procardStyle='外购' and (sbStatus is null or sbStatus !='删除') and needProcess ='yes' and (lingliaostatus is null or lingliaostatus!='否')";
			List list = null;
			List listneedprocesswgj = null;
			if ("外购".equals(procardSyle)) {
				Procard wgj = (Procard) totalDao.getObjectById(Procard.class,
						procard.getId());
				list = new ArrayList();
				list.add(wgj);
			} else {
				list = totalDao.query(hql2, procard.getId());
				listneedprocesswgj = totalDao.query(needprocesswgj, procard
						.getId());
			}
			// //已配套套数量
			// Float peitaoCount = procard.getKlNumber()-procard.getHascount();
			// Float scpeitao = null;//生产配套数量
			// Float wwpeitao =null;//外委配套数量
			// 根据外购件的已领和被外委计算最大可调用在制品数量和自身外委关联数量
			float maxUserzz = procard.getHascount();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Procard wgj = (Procard) list.get(i);
					// 外委出去数量
					Float wwCount = (Float) totalDao
							.getObjectByCondition(
									"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus !='删除') and processInforWWApply.status !='打回')",
									wgj.getId());
					if (wwCount == null) {
						wwCount = 0f;
					}
					// 外委出去剩余未被领取数量
					Float wwhascount = (Float) totalDao
							.getObjectByCondition(
									"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and (dataStatus is null or dataStatus !='删除') and processInforWWApply.status !='打回')",
									wgj.getId());
					if (wwhascount == null) {
						wwhascount = 0f;
					}
					if (wgj.getWwblCount() == null) {
						wgj.setWwblCount(0f);
					}
					wgj.setWwCount(wwCount + wgj.getWwblCount());
					if (wgj.getWwblreceiveCount() == null) {
						wgj.setWwblreceiveCount(0f);
					}
					wgj.setWwhascount(wwhascount
							+ (wgj.getWwblCount() - wgj.getWwblreceiveCount()));
					if (wgj.getHascount() == null) {// 包工包料
						continue;
					}
					// 生产未领数量
					Float scHascount = wgj.getHascount() - wgj.getWwhascount();
					// 对应上层生产未领数量
					Float upscHascount = scHascount * wgj.getQuanzi1()
							/ wgj.getQuanzi2();
					if (upscHascount < maxUserzz) {
						maxUserzz = upscHascount;
					}
					// //外委已领数量
					// Float wwylCount = wwCount-wwhascount;
					// //生产已领数量
					// Float
					// scylCount=wgj.getKlNumber()-wgj.getHascount()-wwylCount;
					// if(scpeitao==null){
					// scpeitao=scylCount;
					// }else if(scpeitao>scylCount){
					// scpeitao=scylCount;
					// }
					// if(wwCount!=0){
					// if(wwpeitao==null){
					// wwpeitao=wwylCount;
					// }else if(wwpeitao>wwylCount){
					// wwpeitao=wwylCount;
					// }
					// }
					//查询该自制件下的该外购件与工序的绑定关系
				List<ProcessAndWgProcardTem> proAndWgList =	totalDao.query(" from ProcessAndWgProcardTem where procardMarkId = ? and wgprocardMardkId = ? ",procard.getMarkId(),wgj.getMarkId() );
					wgj.setProAndWgList(proAndWgList);
				}
				// if(wwpeitao==null){
				// wwpeitao=0f;
				// }
				// procard.setGetCount(procard.getKlNumber()-scpeitao-wwpeitao);//计算可伶数量先不用以后看情况使用
			}
			float hiddenCount = 0f;// 在制品抵扣数量
			float qwCount = procard.getGetCount();// 本次预领数量
			float zaizhiCount = 0f;
			// 在制品扣减，并记录扣减数量
			if (qwCount != 0 && !procard.getProcardStyle().equals("总成")) {// 不为0时计算在制品，总成不算在制品
				zaizhiCount = getZaiZhiCount(procard.getId());// 计算可用在制品
				// zaizhiCount = 0f;// 计算可用在制品
				if (zaizhiCount > 0) {
					if (zaizhiCount > maxUserzz) {
						zaizhiCount = maxUserzz;
					}
					Goods goodsZ = new Goods();
					goodsZ.setQuanzi1(1f);
					goodsZ.setQuanzi2(1f);
					goodsZ.setXqCount(zaizhiCount);
					goodsZ.setHqlCount(0f);
					goodsZ.setQlCount(0f);
					goodsZ.setIsEnough(true);
					goodsZ.setIsChangeSf(false);
					if (flagMap.get(procard.getMarkId()) != null) {
						goodsZ.setFlag(flagMap.get(procard.getMarkId()));
					} else {
						flagMap.put(procard.getMarkId(), maxFlag);
						goodsZ.setFlag(maxFlag);
						maxFlag++;
					}
					goodsZ.setGoodsId(0);// 外购件领取部分时当在制品足够时不会因为selected为空而被判断没有点击一个确定
					goodsZ.setGoodsMarkId(procard.getMarkId());
					goodsZ.setGoodsLotId("在制品");
					goodsZ.setGoodsFormat("成品");
					goodsZ.setGoodsUnit(procard.getUnit());
					goodsZ.setGoodsPosition("在制品");
					goodsZ.setGoodsFullName(procard.getProName());
					goodsZ.setGoodsBeginQuantity(zaizhiCount);
					goodsZ.setShowType("在制品充领");
					goodsZ.setQlUnit(procard.getUnit());
					if (zaizhiCount < 0) {
						map.put(3, "对不起，之前批次的在制品数不足请先生成之前批次的在制品");
					} else {
						if (qwCount <= zaizhiCount) {
							goodsZ.setGoodsZhishu(qwCount);
							goodsZ.setGoodsCurQuantity(qwCount);
							hiddenCount += qwCount;
							qwCount = 0;
						} else {
							goodsZ.setGoodsZhishu(zaizhiCount);
							goodsZ.setGoodsCurQuantity(zaizhiCount);
							hiddenCount += zaizhiCount;
							qwCount = qwCount - zaizhiCount;
						}

					}
					if (zaizhiCount > 0) {
						listGoods.add(goodsZ);
					}
				}
			}
			// 存在外购件，判断库存是否充足
			if (list != null && list.size() > 0) {
				// 存在外购件，判断库存是否充足
 				for (int i = 0; i < list.size(); i++) {
					Procard son = (Procard) list.get(i);
					String gongxuNum ="";//多个;分开
					String gongxuName ="";//多个;分开
					List<ProcessAndWgProcardTem> proAndWgList =son.getProAndWgList();
					if(proAndWgList!=null && proAndWgList.size()>0){
						for (ProcessAndWgProcardTem proAndWg : proAndWgList) {
							gongxuNum+=";"+proAndWg.getProcessNo();
							gongxuName+=";"+proAndWg.getProcessName();
						}
					}
					if(gongxuNum!=null && gongxuNum.length()>1){
						gongxuNum = gongxuNum.substring(1);
					}
					if(gongxuName!=null && gongxuName.length()>1){
						gongxuName = gongxuName.substring(1);
					}
					Float xqCount=0f;
					if (son.getHascount() == null) {// 包工包料
						Goods gd = new Goods();
						gd.setQuanzi1(son.getQuanzi1());
						gd.setQuanzi2(son.getQuanzi2());
						gd.setGoodsMarkId(son.getMarkId());
						gd.setXqCount(0f);
						gd.setGoodsFullName(son.getProName());
						gd.setGoodsFormat(son.getSpecification());
						gd.setShowType("外购件");
						gd.setGoodsCurQuantity(0f);
						gd.setHqlCount(0f);
						gd.setQlCount(son.getWwCount());
						gd.setIsEnough(true);
						gd.setGongxuName(gongxuName);
						gd.setGongxuNum(gongxuNum);
						listGoods.add(gd);
						continue;
					}
					// 外购件需领数量
					float count = 0f;
					// 本次消耗库存数量
					float sumkc = 0f;
					if (son.getNeedProcess() != null
							&& son.getNeedProcess().equals("yes")) {// 半成品
						count = qwCount;
					} else {
						count = qwCount * son.getQuanzi2() / son.getQuanzi1();
					}
					xqCount = count;
					// 生产未领=总未领数量-外委未领
					Float scwlCount = son.getHascount() - son.getWwhascount();
					// 根据占用算出最多可领
//					if (son.getMarkId().equals("1.08.00001")) {
//						System.out.println(son.getMarkId());
//					}
					Float maxklNumber = Util.Floatdelete(son.getTjNumber(), Util.Floatdelete(son.getKlNumber(), son.getHascount()));
					if (son.getMarkId().equals(procard.getMarkId())) {
						maxklNumber = son.getHascount();
					}
					if (scwlCount < count) {
						count = scwlCount;
					}
					if (maxklNumber < count) {
						count = maxklNumber;
					}
					// 生产已领数量=总已领数量-外委已领
					Float scylCount = son.getKlNumber() - son.getHascount()
							- (son.getWwCount() - son.getWwhascount());
					String banbenSql = null;
					if (son.getBanBenNumber() == null
							|| son.getBanBenNumber().length() == 0) {
						banbenSql = " and (banBenNumber is null or banBenNumber ='')";
					} else {
						banbenSql = " and banBenNumber ='"
								+ son.getBanBenNumber() + "'";
					}
					// 单零件重量（一般为原材料使用）
					Float bizhong = (Float) totalDao.getObjectByCondition(
							"select bili from YuanclAndWaigj where markId =? ",
							son.getMarkId());
					Goods googsTemp = (Goods) totalDao
							.getObjectByCondition(
									"from Goods where goodsCurQuantity>0 and kgliao=? and goodsZhishu>0 and goodsMarkId=?"
											+ banbenSql, son.getKgliao(), son
											.getMarkId());//son.getKgliao()
					if (bizhong == null) {
						if (googsTemp != null) {
							bizhong = googsTemp.getGoodsCurQuantity()
									/ googsTemp.getGoodsZhishu();
						}
					}
					boolean ylkj = false;
					// 余料查询
					if (count > 0 && bizhong != null && bizhong > 0) {// 有比重才会有余料
						Float single = null;// 单间需要重量
						if (googsTemp != null
								&& son.getUnit().equals(
										googsTemp.getGoodsUnit())) {// 单位一致
							single = son.getQuanzi2() / son.getQuanzi1();
						} else {
							single = bizhong * son.getQuanzi2()
									/ son.getQuanzi1();
						}
						List<Goods> yuliaoList = totalDao
								.query(
										" from Goods where goodsClass='余料库' and kgliao=? and goodsCurQuantity>"
												+ single
												+ " and goodsMarkId=? and llGysId is null and (yllock is null or yllock='' or yllock='no' or (yllock = 'yes' and ylMarkId=?)) and (fcStatus is null or fcStatus='可用')",
										son.getKgliao(), son.getMarkId(),
										procard.getMarkId());
						if (yuliaoList != null && yuliaoList.size() > 0) {
							for (Goods goods : yuliaoList) {
								goods.setQuanzi1(son.getQuanzi1());
								goods.setQuanzi2(son.getQuanzi2());
								goods.setXqCount(xqCount);
								// 之前已领数量
								goods.setHqlCount(scylCount);
								goods.setQlCount(son.getWwCount());
								if (flagMap.get(son.getMarkId()) != null) {
									goods.setFlag(flagMap.get(son.getMarkId()));
								} else {
									flagMap.put(son.getMarkId(), maxFlag);
									goods.setFlag(maxFlag);
									maxFlag++;
								}
								goods.setShowType("余料");
								goods.setIsEnough(true);
								goods.setIsChangeSf(false);
								goods.setGongxuName(gongxuName);
								goods.setGongxuNum(gongxuNum);
								if (googsTemp!=null && son.getUnit().equals(
										googsTemp.getGoodsUnit())) {
									if (googsTemp.getGoodsStoreZHUnit() != null
											&& googsTemp.getGoodsStoreZHUnit()
													.length() > 0) {
										goods.setQlUnit(googsTemp
												.getGoodsStoreZHUnit());
									} else {
										goods.setQlUnit("张");
									}
								} else {
									goods.setQlUnit(son.getUnit());
								}
								goods.setGoodsZhishu(1f);// 余料以单张保存
								// goods.setGoodsCurQuantity();//余料重量即领取重量
								Float singleCount = (float) Math.floor(goods
										.getGoodsCurQuantity()
										/ single);
								if (googsTemp != null
										&& son.getUnit().equals(
												googsTemp.getGoodsUnit())) {// 单位一致
									if ((singleCount * single) >= (count-0.00005)) {// 余料足够
										sumkc += count;
										goods.setGoodsBeginQuantity(Util.FomartFloat(count, 4));// 余料需要重量
										goods.setGoodsCurQuantity(Util.FomartFloat(goods.getGoodsCurQuantity(), 4));
										ylkj = true;
										listGoods.add(goods);
										count = 0;
										break;
									} else {
										sumkc += singleCount * single;
										goods.setGoodsBeginQuantity(Util.FomartFloat(singleCount
												* single, 4));// 余料需要重量
										goods.setGoodsCurQuantity(Util.FomartFloat(goods.getGoodsCurQuantity(), 4));
										ylkj = true;
										listGoods.add(goods);
										count = count - Util.FomartFloat(singleCount * single, 4);
									}
								} else {
									if (singleCount * single >= (count * bizhong-0.00005)) {// 余料足够
										sumkc += count * bizhong;
										goods.setGoodsBeginQuantity(Util.FomartFloat(count
												* bizhong, 4));// 余料需要重量
										goods.setGoodsCurQuantity(Util.FomartFloat(goods.getGoodsCurQuantity(), 4));
										ylkj = true;
										listGoods.add(goods);
										count = 0;
										break;
									} else {
										sumkc += singleCount * single;
										goods.setGoodsBeginQuantity(Util.FomartFloat(singleCount
												* single, 4));// 余料需要重量
										goods.setGoodsCurQuantity(Util.FomartFloat(goods.getGoodsCurQuantity(), 4));
										ylkj = true;
										listGoods.add(goods);
										count = count - singleCount;
									}
								}
							}
						}

					}
					if (ylkj && count == 0) {// 通过余料扣减使得数据为0
						continue;
					}
					// 请领数量
					float lsCount = count;// 张数
					String goodsClass = "外购件库','中间库";
					String goodsClassSql = null;
//					if ((bizhong == null || bizhong == 0)
//							&& son.getProductStyle() != null
//							&& son.getProductStyle().equals("试制")) {
//						goodsClass = "试制库";// 试制的外购件去试制库取
//						goodsClassSql = " and goodsClass ='" + goodsClass + "'";
//					} else {
						String kgsql = " and 1=1";
						if (son.getKgliao() != null
								&& son.getKgliao().length() > 0) {
							kgsql += " and kgliao ='" + son.getKgliao() + "'";
						}
						goodsClassSql = " and ((goodsClass in ('" + goodsClass
								+ "') " + kgsql + " ) or goodsClass = '备货库')";
//					}
					String hqlGoods = " from Goods where goodsMarkId='"
							+ son.getMarkId()
							+ "'"
							+ goodsClassSql
							+ " and  goodsCurQuantity>0  and (fcStatus is null or fcStatus='可用') and (islock is null or islock = 'NO')  "
							+ banbenSql + phoneSql + "order by goodsLotId asc";
					List<Goods> listG = totalDao.find(hqlGoods);
					// Double d2 = Math.ceil(count);
					// count = Float.parseFloat(d2.toString());
					if (listG.size() > 0) {
						boolean hasChange=false;
						int n = 0;
						for (Goods goo : listG) {
							if (goo.getGoodsPosition() != null
									&& !"".equals(goo.getGoodsPosition())) {
								String kuwei = goo.getGoodsPosition()
										.replaceAll(",", "").replace(" ", "");
								WarehouseNumber wn = (WarehouseNumber) totalDao
										.getObjectByCondition(
												"from WarehouseNumber where number = ? and wareHouseName = ?",
												kuwei, goo.getGoodsClass());
								if (wn != null && wn.getIp() != null
										&& wn.getIp().length()>7) {
									goo.setKuweiId(wn.getId());
								}
							}
							n++;
							goo.setQuanzi1(son.getQuanzi1());
							goo.setQuanzi2(son.getQuanzi2());
							goo.setXqCount(xqCount);
							goo.setShowType("外购件");
							goo.setHqlCount(scylCount);
							goo.setQlCount(son.getWwCount());
							goo.setGoodsBeginQuantity(0f);
							goo.setIsEnough(true);
							goo.setIsChangeSf(false);
							goo.setGongxuName(gongxuName);
							goo.setGongxuNum(gongxuNum);
							goo.setGoodsBeginQuantity(lsCount);
							goo.setKfCount(Util.FomartFloat(goo.getGoodsCurQuantity(),4));
							if (flagMap.get(son.getMarkId()) != null) {
								goo.setFlag(flagMap.get(son.getMarkId()));
							} else {
								flagMap.put(son.getMarkId(), maxFlag);
								goo.setFlag(maxFlag);
								maxFlag++;
							}
							if (bizhong != null && bizhong > 0) {// 有比重需要换算单位
								if (son.getUnit().equals(goo.getGoodsUnit())
										&& null != goo.getGoodsZhishu()
										&& goo.getGoodsZhishu() > 0) {// 单位一致有支数,用lsCount计数
									if (!hasChange) {
										lsCount = (float) Math.ceil(lsCount
												/ bizhong);
										hasChange=true;
									}
									if (bizhong != null && bizhong > 0) {
										goo.setSingleCount(bizhong*son.getQuanzi1()/son.getQuanzi2());
										if (goo.getGoodsZhishu() > lsCount) {
											goo.setGoodsZhishu(lsCount);
											// 计算重量
											if ((lsCount * bizhong-0.00005) <= goo
													.getGoodsCurQuantity()) {
												sumkc += Util.FomartFloat(lsCount * bizhong,4);
												goo.setGoodsCurQuantity(Util.FomartFloat(lsCount
														* bizhong,4));
											} else {
												 goo
													.setGoodsCurQuantity(Util.FomartFloat(goo
															.getGoodsCurQuantity(),4));
												sumkc += goo
														.getGoodsCurQuantity();
											}
											if (goo.getGoodsStoreZHUnit() == null
													|| goo
															.getGoodsStoreZHUnit()
															.length() == 0) {
												goo.setQlUnit("张");
											} else {
												goo.setQlUnit(goo
														.getGoodsStoreZHUnit());
											}
											goo.setIsChangeSf(true);
											lsCount = 0;
											count = 0;
											listGoods.add(goo);
											break;
										} else if (goo.getGoodsZhishu() == lsCount) {
											sumkc += lsCount * bizhong;
											goo.setGoodsZhishu(lsCount);
											goo
											.setGoodsCurQuantity(Util.FomartFloat(goo
													.getGoodsCurQuantity(),4));
											// 支数刚好情况重量，不重新计算重量
											// goo.setGoodsCurQuantity(lsCount *
											// bizhong);
											if (goo.getGoodsStoreZHUnit() == null
													|| goo
															.getGoodsStoreZHUnit()
															.length() == 0) {
												goo.setQlUnit("张");
											} else {
												goo.setQlUnit(goo
														.getGoodsStoreZHUnit());
											}
											goo.setIsChangeSf(true);
											lsCount = 0;
											count = 0;
											listGoods.add(goo);
											break;
										} else {
											sumkc += goo.getGoodsZhishu()
													* bizhong;
											lsCount = lsCount
													- goo.getGoodsZhishu();
											count -= goo.getGoodsZhishu()
													* bizhong;
											if ((goo.getGoodsZhishu() * bizhong-0.00005) <= goo
													.getGoodsCurQuantity()) {
												goo.setGoodsCurQuantity(Util.FomartFloat(goo
														.getGoodsZhishu()
														* bizhong,4));
											}
											if (goo.getGoodsStoreZHUnit() == null
													|| goo
															.getGoodsStoreZHUnit()
															.length() == 0) {
												goo.setQlUnit("张");
											} else {
												goo.setQlUnit(goo
														.getGoodsStoreZHUnit());
											}
											goo.setIsChangeSf(true);
											listGoods.add(goo);
										}
									}
								} else if (son.getUnit().equals(
										goo.getGoodsUnit())
										&& (null == goo.getGoodsZhishu() || goo
												.getGoodsZhishu() == 0)) {// 单位一致无支数
									// 此情况不需要比重,数据有误
								} else if (null != goo.getGoodsZhishu()
										&& goo.getGoodsZhishu() > 0) {// 单位不一致,
									// 有支数用count计数
									if (bizhong == null || bizhong == 0) {// 用当前库存重新计算比重
										bizhong = goo.getGoodsCurQuantity()
												/ goo.getGoodsZhishu();
									}
									if (goo.getGoodsZhishu() >= count) {
										sumkc += count * bizhong;
										goo.setGoodsZhishu(count);
										// 计算重量
										goo
												.setGoodsCurQuantity(Util.FomartFloat(count
														* bizhong,4));
										goo
												.setQlUnit(goo
														.getGoodsStoreZHUnit());
										listGoods.add(goo);
										count = 0;
										break;
									} else if (goo.getGoodsZhishu() == count) {
										sumkc += goo.getGoodsCurQuantity();
										goo.setGoodsZhishu(count);
										// 支数刚好情况重量，不重新计算重量
										// goo.setGoodsCurQuantity(lsCount *
										// bizhong);
										goo
												.setQlUnit(goo
														.getGoodsStoreZHUnit());
										listGoods.add(goo);
										count = 0;
										break;
									} else {
										sumkc += Util.FomartFloat(goo.getGoodsZhishu() * bizhong,4);
										goo.setGoodsCurQuantity(Util.FomartFloat(goo
												.getGoodsZhishu()
												* bizhong,4));
										goo
												.setQlUnit(goo
														.getGoodsStoreZHUnit());
										listGoods.add(goo);
										count = count - goo.getGoodsZhishu();
									}

								} else {// 单位不一致无支数,无法计算

								}
							} else {// 无比重
								if (goo.getGoodsCurQuantity() >= count) {
									sumkc += count;
									goo.setGoodsCurQuantity(count);
									goo.setGoodsZhishu(count);
									count = 0;
									goo.setQlUnit(son.getUnit());
									listGoods.add(goo);
									break;
								} else {
									sumkc += goo.getGoodsCurQuantity();
									goo.setCkCount(goo.getGoodsCurQuantity());
									count -= goo.getGoodsCurQuantity();
									goo.setGoodsZhishu(goo
											.getGoodsCurQuantity());
									goo.setQlUnit(son.getUnit());
									listGoods.add(goo);
								}
							}
						}
						if (count > 0&&(count*son.getQuanzi1()/son.getQuanzi2())>0.05) {// 库存不够
							Goods gd = new Goods();
							gd.setGongxuName(gongxuName);
							gd.setGongxuNum(gongxuNum);
							if(bizhong != null && bizhong > 0){
								gd.setSingleCount(bizhong*son.getQuanzi1()/son.getQuanzi2());
							}
							gd.setQuanzi1(son.getQuanzi1());
							gd.setQuanzi2(son.getQuanzi2());
							gd.setXqCount(xqCount);
							gd.setGoodsMarkId(son.getMarkId());
							gd.setGoodsFullName(son.getProName());
							gd.setGoodsFormat(son.getSpecification());
							gd.setShowType("外购件");
							gd.setGoodsCurQuantity(0f);
							gd.setHqlCount(0f);
							gd.setQlCount(son.getWwCount());
							gd.setTqlCount(count);
							gd.setIsEnough(false);
							listGoods.add(gd);
							// 已领+库存对比占用
							float dbzhanyong = son.getTjNumber()
									- (son.getKlNumber() - son.getHascount() + sumkc);
							if (dbzhanyong > 0.1) {// 允许0.1KG的差距
								// 表示激活数量过多,向系统管理员发送重要警告
								AlertMessagesServerImpl
										.addAlertMessages("系统维护异常组(严重警告!)",
												"件号:" + son.getMarkId() + "批次:"
														+ son.getSelfCard()
														+ "的占用数量超出库存"
														+ dbzhanyong + ",数据异常,"
														+ Util.getDateTime(),
												"2");
								errmsg += "严重警告!件号:" + son.getMarkId() + "批次:"
										+ son.getSelfCard() + "的占用数量超出库存"
										+ dbzhanyong + ",数据异常!";
							}
						}
					} else {
						String hqlGoods2 = " from Goods where goodsMarkId='"
								+ son.getMarkId()
								+ "'"
								+ goodsClassSql
								+ " and goodsCurQuantity=0 and (fcStatus is null or fcStatus='可用')  "
								+ phoneSql + " order by goodsLotId asc";
						List<Goods> listG2 = totalDao.find(hqlGoods2);
						if (listG2.size() > 0) {
							Goods goo = listG2.get(0);
							goo.setGongxuName(gongxuName);
							goo.setGongxuNum(gongxuNum);
							if(bizhong != null && bizhong > 0){
								goo.setSingleCount(bizhong*son.getQuanzi1()/son.getQuanzi2());
							}
							goo.setQuanzi1(son.getQuanzi1());
							goo.setQuanzi2(son.getQuanzi2());
 							goo.setXqCount(xqCount);
							goo.setShowType("外购件");
							if (flagMap.get(son.getMarkId()) != null) {
								goo.setFlag(flagMap.get(son.getMarkId()));
							} else {
								flagMap.put(son.getMarkId(), maxFlag);
								goo.setFlag(maxFlag);
								maxFlag++;
							}
							goo.setHqlCount(scylCount);
							goo.setQlCount(son.getWwCount());
							goo.setGoodsCurQuantity(0f);
							if (count == 0) {
								goo.setIsEnough(true);
							} else {
								// 已领+库存对比占用
								float dbzhanyong = son.getTjNumber()
										- (son.getKlNumber()
												- son.getHascount() + sumkc);
								if (dbzhanyong > 0.1) {// 允许0.1KG的差距
									// 表示激活数量过多,向系统管理员发送重要警告
									AlertMessagesServerImpl.addAlertMessages(
											"系统维护异常组(严重警告!)", "件号:"
													+ son.getMarkId() + "批次:"
													+ son.getSelfCard()
													+ "的占用数量超出库存" + dbzhanyong
													+ ",数据异常,"
													+ Util.getDateTime(), "2");
									errmsg += "严重警告!件号:" + son.getMarkId()
											+ "批次:" + son.getSelfCard()
											+ "的占用数量超出库存" + dbzhanyong
											+ ",数据异常!";
								}
								goo.setIsEnough(false);
							}
							goo.setQlUnit(son.getUnit());
							listGoods.add(goo);
						} else {
							Goods goo = new Goods();
							goo.setGongxuName(gongxuName);
							goo.setGongxuNum(gongxuNum);
							if(bizhong != null && bizhong > 0){
								goo.setSingleCount(bizhong*son.getQuanzi1()/son.getQuanzi2());
							}
							goo.setQuanzi1(son.getQuanzi1());
							goo.setQuanzi2(son.getQuanzi2());
							goo.setXqCount(xqCount);
							goo.setKgliao(son.getKgliao());
							goo.setShowType("外购件");
							if (flagMap.get(son.getMarkId()) != null) {
								goo.setFlag(flagMap.get(son.getMarkId()));
							} else {
								flagMap.put(son.getMarkId(), maxFlag);
								goo.setFlag(maxFlag);
								maxFlag++;
							}
							goo.setHqlCount(scylCount);
							goo.setQlCount(son.getWwCount());
							goo.setGoodsCurQuantity(0f);
							if (count == 0) {
								goo.setIsEnough(true);
							} else {
								// 已领+库存对比占用
								float dbzhanyong = son.getTjNumber()
										- (son.getKlNumber()
												- son.getHascount() + sumkc);
								if (dbzhanyong > 0.1) {// 允许0.1KG的差距
									// 表示激活数量过多,向系统管理员发送重要警告
									AlertMessagesServerImpl.addAlertMessages(
											"系统维护异常组(严重警告!)", "件号:"
													+ son.getMarkId() + "批次:"
													+ son.getSelfCard()
													+ "的占用数量超出库存" + dbzhanyong
													+ ",数据异常,"
													+ Util.getDateTime(), "2");
									errmsg += "严重警告!件号:" + son.getMarkId()
											+ "批次:" + son.getSelfCard()
											+ "的占用数量超出库存" + dbzhanyong
											+ ",数据异常!";
								}
								goo.setIsEnough(false);
							}
							goo.setGoodsFullName(son.getProName());
							goo.setGoodsMarkId(son.getMarkId());
							goo.setGoodsId(0);
							goo.setQlUnit(son.getUnit());
							goo.setGoodsCurQuantity(0f);
							goo.setGoodsUnit(son.getUnit());
							if (!"phone".equals(status)) {
								listGoods.add(goo);
							}

						}
					}
				}
			}
			// 查出下层在制品需领
			List<Procard> zaizhiSonList = totalDao
					.query(
							"from Procard where procard.id=? and ((zaizhizkCount is null and zaizhizkCount>0) or (zaizhiApplyZk is null and zaizhiApplyZk>0))",
							procard.getId());
			if (zaizhiSonList != null && zaizhiSonList.size() > 0) {
				for (Procard zaizhiSon : zaizhiSonList) {
					Float count = 0f;
					Float xqCount=0f;
					if (zaizhiSon.getProcardStyle().equals("外购")) {
						count = qwCount * zaizhiSon.getQuanzi2()
								/ zaizhiSon.getQuanzi1();
					} else {
						count = qwCount * zaizhiSon.getCorrCount();
					}
					if (zaizhiSon.getZaizhizkCount() < count) {
						count = zaizhiSon.getZaizhizkCount();
					}
					xqCount = count;
					Integer maxProcessNo = (Integer) totalDao
							.getObjectByCondition(
									"select max(processNO) from ProcessInfor where procard.id=?",
									zaizhiSon.getId());
					if (maxProcessNo == null) {
						errmsg += "对不起,零件" + zaizhiSon.getMarkId()
								+ "无工序,出现转库在制品，系统无法理解,请核查!";
					}
					// 查询已经出库数量
					Float hasck = (Float) totalDao
							.getObjectByCondition(
									"select sum(ckCount) from ProcardProductRelation and procardId=? and goodsId in(select goodsId from Goods where goodsStyle='半成品转库' and goodsMarkId=? and goodsLotId=? and processNo=? )",
									zaizhiSon.getId(), zaizhiSon.getMarkId(),
									zaizhiSon.getSelfCard(), maxProcessNo);
					if (hasck != null) {
						Float needCount = qwCount;
						Float morept = hasck - procard.getHascount();
						if (morept > 0) {
							needCount = needCount - morept;
						}
						if (needCount < 0) {
							continue;
						}
						List<Goods> zaizhiGoodList = totalDao
								.query(
										"from Goods where goodsMarkId=? and goodsLotId=? and processNo=? and goodsStyle='半成品转库'",
										zaizhiSon.getMarkId(), zaizhiSon
												.getSelfCard(), maxProcessNo);
						if (zaizhiGoodList == null
								|| zaizhiGoodList.size() == 0) {
							errmsg += "对不起,仓库中没有对应" + zaizhiSon.getMarkId()
									+ "的在职品可领";
						} else {
							for (Goods zaizhiGood : zaizhiGoodList) {
								if (zaizhiSon.getProcardStyle().equals("外购")) {
									zaizhiGood.setQuanzi1(zaizhiSon.getQuanzi1());
									zaizhiGood.setQuanzi2(zaizhiSon.getQuanzi2());
								} else {
									zaizhiGood.setQuanzi1(1f);
									zaizhiGood.setQuanzi2(zaizhiSon.getCorrCount());
								}
								zaizhiGood.setXqCount(xqCount);
								zaizhiGood.setHqlCount(hasck);
								zaizhiGood.setQlCount(0f);
								zaizhiGood.setGoodsBeginQuantity(0f);
								zaizhiGood.setIsEnough(true);
								zaizhiGood.setIsChangeSf(false);
								zaizhiGood.setShowType("中转在制品");
								if (flagMap.get(zaizhiSon.getMarkId()) != null) {
									zaizhiGood.setFlag(flagMap.get(zaizhiSon
											.getMarkId()));
								} else {
									flagMap.put(zaizhiSon.getMarkId(), maxFlag);
									zaizhiGood.setFlag(maxFlag);
									maxFlag++;
								}
								if (zaizhiGood.getGoodsCurQuantity() >= count) {
									zaizhiGood.setGoodsCurQuantity(count);
									count = 0f;
									listGoods.add(zaizhiGood);
									break;
								} else {
									count = count
											- zaizhiGood.getGoodsCurQuantity();
									listGoods.add(zaizhiGood);
								}
							}
							if (count > 0) {
								errmsg += "零件" + zaizhiSon.getMarkId() + "第"
										+ zaizhiSon.getSelfCard()
										+ "批次的在制品库存不足入库数量" + count + ",请前往核对!";
							}
						}
					}
				}
			}
		}
		map.put(1, procard);
		map.put(2, listGoods);
		return map;
	}
//
//	/**
//	 * 此方法原设计上层hasCount为下层最小hasCount被废弃使用
//	 * 
//	 * @param procard
//	 * @param ckType
//	 * @param status
//	 * @return
//	 */
//	public Map<Integer, Object> findSellOutlist2(Procard procard,
//			String ckType, String status) {
//		Map<Integer, Object> map = new HashMap<Integer, Object>();
//		List listGoods = new ArrayList();
//		Map<String, Integer> flagMap = new HashMap<String, Integer>();
//		Integer maxFlag = 0;
//		if (null != procard) {
//			String phoneSql = "";
//			if ("phone".equals(status)) {
//				phoneSql = " and goodsId in (select fk_good_id from WareBangGoogs )";
//			}
//			// 查找到工艺卡片
//			if (procard.getHascount() == null) {
//				procard.setHascount(procard.getKlNumber());
//			}
//			if (procard.getGetCount() == null) {
//				procard.setGetCount(procard.getHascount());
//			} else if (procard.getGetCount() > procard.getHascount()) {
//				procard.setGetCount(procard.getHascount());
//			}
//			String procardSyle = procard.getProcardStyle();
//			if ("外购".equals(procardSyle)
//					&& (procard.getNeedProcess() == null || procard
//							.getNeedProcess().equals("no"))) {
//				map.put(3, "对不起，该外购件不可领料");
//				return map;
//			}
//			float hiddenCount = 0f;// 在制品抵扣数量
//			float qwCount = procard.getGetCount();// 本次预领数量
//			float zaizhiCount = 0f;
//			// 第一步:在制品扣减，并记录扣减数量
//			if (qwCount != 0 && !procard.getProcardStyle().equals("总成")) {// 不为0时计算在制品，总成不算在制品
//				zaizhiCount = getZaiZhiCount(procard.getId());// 计算可用在制品
//				// zaizhiCount = 0f;// 计算可用在制品
//				if (zaizhiCount > 0) {
//					Goods goodsZ = new Goods();
//					goodsZ.setHqlCount(0f);
//					goodsZ.setTqlCount(0f);
//					goodsZ.setIsEnough(true);
//					goodsZ.setIsChangeSf(false);
//					if (flagMap.get(procard.getMarkId()) != null) {
//						goodsZ.setFlag(flagMap.get(procard.getMarkId()));
//					} else {
//						flagMap.put(procard.getMarkId(), maxFlag);
//						goodsZ.setFlag(maxFlag);
//						maxFlag++;
//					}
//					goodsZ.setGoodsId(0);// 外购件领取部分时当在制品足够时不会因为selected为空而被判断没有点击一个确定
//					goodsZ.setGoodsMarkId(procard.getMarkId());
//					goodsZ.setGoodsLotId("在制品");
//					goodsZ.setGoodsFormat("成品");
//					goodsZ.setGoodsUnit(procard.getUnit());
//					goodsZ.setGoodsPosition("在制品");
//					goodsZ.setGoodsFullName(procard.getProName());
//					goodsZ.setGoodsBeginQuantity(zaizhiCount);
//					goodsZ.setShowType("在制品充领");
//					goodsZ.setQlUnit(procard.getUnit());
//					if (zaizhiCount < 0) {
//						map.put(3, "对不起，之前批次的在制品数不足请先生成之前批次的在制品");
//					} else {
//						if (qwCount <= zaizhiCount) {
//							goodsZ.setGoodsZhishu(qwCount);
//							goodsZ.setGoodsCurQuantity(qwCount);
//							hiddenCount += qwCount;
//							qwCount = 0;
//						} else {
//							goodsZ.setGoodsZhishu(zaizhiCount);
//							goodsZ.setGoodsCurQuantity(zaizhiCount);
//							hiddenCount += zaizhiCount;
//							qwCount = qwCount - zaizhiCount;
//						}
//
//					}
//					if (zaizhiCount > 0) {
//						listGoods.add(goodsZ);
//					}
//				}
//			}
//			// 存在外购件，判断库存是否充足
//			float ckCount = 0f;
//			if ((procard.getLingliaoType() == null || !procard
//					.getLingliaoType().equals("part"))) {// 配齐领料先取最大，然后根据下层数量下减
//				ckCount = qwCount;
//			}
//			// //第二步:查出下层外购件需领
//			String hql2 = "from Procard where fatherId=? and procardStyle='外购' and (lingliaostatus is null or lingliaostatus!='否') and (needProcess !='yes' or needProcess is null)";
//			String needprocesswgj = "from Procard where id=? and procardStyle='外购' and needProcess ='yes' and (lingliaostatus is null or lingliaostatus!='否')";
//			List list = null;
//			List listneedprocesswgj = null;
//			if ("外购".equals(procardSyle)) {
//				Procard wgj = (Procard) totalDao.getObjectById(Procard.class,
//						procard.getId());
//				list = new ArrayList();
//				list.add(wgj);
//			} else {
//				list = totalDao.query(hql2, procard.getId());
//				listneedprocesswgj = totalDao.query(needprocesswgj, procard
//						.getId());
//			}
//			if (list != null && list.size() > 0) {
//				// 存在外购件，判断库存是否充足
//				for (int i = 0; i < list.size(); i++) {
//					// 第三步计算外购件需领数量
//					float ckCount1 = 0f;// 组合或总成仓库可领数量，用来与ckCount比较
//					float tqlCount = 0f; // 本次缺领数量
//					float hqlCount = 0f;// 之前缺领数量
//					Procard son = (Procard) list.get(i);
//					float ckCount2 = 0f;// 此外购件仓库剩余数量（最大为此外购件此次所需数）
//					String banbenSql = null;
//					if (son.getBanBenNumber() == null
//							|| son.getBanBenNumber().length() == 0) {
//						banbenSql = " and (banBenNumber is null or banBenNumber ='')";
//					} else {
//						banbenSql = " and banBenNumber ='"
//								+ son.getBanBenNumber() + "'";
//					}
//					// 单零件重量（一般为原材料使用）
//					Float bizhong = (Float) totalDao.getObjectByCondition(
//							"select bili from YuanclAndWaigj where markId =? ",
//							son.getMarkId());
//					Goods googsTemp = (Goods) totalDao
//							.getObjectByCondition(
//									"from Goods where goodsCurQuantity>0 and kgliao=? and goodsZhishu>0 and goodsMarkId=?"
//											+ banbenSql, son.getKgliao(), son
//											.getMarkId());
//					if (bizhong == null) {
//						if (googsTemp != null) {
//							bizhong = googsTemp.getGoodsCurQuantity()
//									/ googsTemp.getGoodsZhishu();
//						}
//					}
//					// 外购件需领数量
//					float count = 0f;
//					if (son.getNeedProcess() != null
//							&& son.getNeedProcess().equals("yes")) {// 半成品
//						count = qwCount;
//					} else {
//						count = qwCount * son.getQuanzi2() / son.getQuanzi1();
//					}
//					// 外委出去数量
//					Float wwCount = (Float) totalDao
//							.getObjectByCondition(
//									"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and processInforWWApply.status !='打回')",
//									son.getId());
//					// 剩余生成领料数量
//					Float syCount = son.getKlNumber() - wwCount;
//					// 对应上层已领数量
//					float dyscyl = (procard.getKlNumber() - procard
//							.getHascount())
//							* son.getQuanzi2() / son.getQuanzi1();
//					// 对应自身已领数量
//					float selfyl = son.getKlNumber() - son.getHascount();
//					if (dyscyl > syCount) {// 如果对应上层已领数量超过剩余生产余下的数量
//						dyscyl = syCount;// 上层对应生产所需要领的数量
//					}
//					// 差领数量，之前领料时未被配套领取的数量(差领为负数时表示之前有多领，多领的情况为类似板料整张发出)
//					float chaling = dyscyl - selfyl;// 差领数量
//
//					if (wwCount == null) {
//						wwCount = 0f;
//					}
//					hqlCount = chaling;
//					float countTem = count;
//					count += chaling;// 本次需领数量=本次预领数量+之前差领数量
//					count -= wwCount;
//					if (count <= 0) {
//						continue;
//					}
//					// 余料查询
//					if (bizhong != null && bizhong > 0) {// 有比重才会有余料
//						Float single = null;// 单间需要重量
//						if (googsTemp != null
//								&& son.getUnit().equals(
//										googsTemp.getGoodsUnit())) {// 单位一致
//							single = son.getQuanzi2() / son.getQuanzi1();
//						} else {
//							single = bizhong * son.getQuanzi2()
//									/ son.getQuanzi1();
//						}
//						List<Goods> yuliaoList = totalDao
//								.query(
//										" from Goods where goodsClass='余料库' and kgliao=? and goodsCurQuantity>"
//												+ single
//												+ " and goodsMarkId=? and llGysId is null and (yllock is null or yllock='' or yllock='no' or (yllock = 'yes' and ylMarkId=?)) and (fcStatus is null or fcStatus='可用')",
//										son.getKgliao(), son.getMarkId(),
//										procard.getMarkId());
//						if (yuliaoList != null && yuliaoList.size() > 0) {
//							for (Goods goods : yuliaoList) {
//								if (flagMap.get(son.getMarkId()) != null) {
//									goods.setFlag(flagMap.get(son.getMarkId()));
//								} else {
//									flagMap.put(son.getMarkId(), maxFlag);
//									goods.setFlag(maxFlag);
//									maxFlag++;
//								}
//								goods.setShowType("余料");
//								if(son.getWwblCount()==null){
//									son.setWwblCount(0f);
//								}
//								goods.setQlCount(son.getWwCount()+son.getWwblCount());
//								goods.setHqlCount(0f);
//								goods.setTqlCount(0f);
//								goods.setIsEnough(true);
//								goods.setIsChangeSf(false);
//								if (son.getUnit().equals(
//										googsTemp.getGoodsUnit())) {
//									if (googsTemp.getGoodsStoreZHUnit() != null
//											&& googsTemp.getGoodsStoreZHUnit()
//													.length() > 0) {
//										goods.setQlUnit(googsTemp
//												.getGoodsStoreZHUnit());
//									} else {
//										goods.setQlUnit("张");
//									}
//								} else {
//									goods.setQlUnit(son.getUnit());
//								}
//								goods.setGoodsZhishu(1f);// 余料以单张保存
//								// goods.setGoodsCurQuantity();//余料重量即领取重量
//								Float singleCount = (float) Math.floor(goods
//										.getGoodsCurQuantity()
//										/ single);
//								if (googsTemp != null
//										&& son.getUnit().equals(
//												googsTemp.getGoodsUnit())) {// 单位一致
//									if ((singleCount * single) >= count) {// 余料足够
//										goods.setGoodsBeginQuantity(count);// 余料需要重量
//										listGoods.add(goods);
//										count = 0;
//										break;
//									} else {
//										goods.setGoodsBeginQuantity(singleCount
//												* single);// 余料需要重量
//										listGoods.add(goods);
//										count = count - singleCount * single;
//									}
//								} else {
//									if (singleCount * single >= count * bizhong) {// 余料足够
//										goods.setGoodsBeginQuantity(count
//												* bizhong);// 余料需要重量
//										listGoods.add(goods);
//										count = 0;
//										break;
//									} else {
//										goods.setGoodsBeginQuantity(singleCount
//												* single);// 余料需要重量
//										listGoods.add(goods);
//										count = count - singleCount;
//									}
//								}
//							}
//						}
//
//					}
//					if (count == 0) {
//						continue;
//					}
//					// 请领数量
//					float lsCount = count;// 张数
//					String goodsClass = "外购件库','中间库";
//					String goodsClassSql = null;
//					if ((bizhong == null || bizhong == 0)
//							&& son.getProductStyle() != null
//							&& son.getProductStyle().equals("试制")) {
//						goodsClass = "试制库";// 试制的外购件去试制库取
//						goodsClassSql = " and goodsClass ='" + goodsClass + "'";
//					} else {
//						String kgsql = " and 1=1";
//						if (son.getKgliao() != null
//								&& son.getKgliao().length() > 0) {
//							kgsql += " and kgliao ='" + son.getKgliao() + "'";
//						}
//						goodsClassSql = " and ((goodsClass in ('" + goodsClass
//								+ "') " + kgsql + " ) or goodsClass = '备货库')";
//					}
//
//					String hqlGoods = " from Goods where goodsMarkId='"
//							+ son.getMarkId()
//							+ "'"
//							+ goodsClassSql
//							+ " and  goodsCurQuantity>0  and (fcStatus is null or fcStatus='可用') "
//							+ banbenSql + phoneSql + "order by goodsLotId asc";
//					List<Goods> listG = totalDao.find(hqlGoods);
//					// Double d2 = Math.ceil(count);
//					// count = Float.parseFloat(d2.toString());
//					if (listG.size() > 0) {
//						int n = 0;
//						for (Goods goo : listG) {
//							n++;
//							goo.setShowType("外购件");
//							if(son.getWwblCount()==null){
//								son.setWwblCount(0f);
//							}
//							goo.setQlCount(son.getWwCount()+son.getWwblCount());
//							goo.setQlCount(wwCount);
//							goo.setHqlCount(hqlCount);
//							goo.setTqlCount(tqlCount);
//							goo.setGoodsBeginQuantity(0f);
//							goo.setIsEnough(true);
//							goo.setIsChangeSf(false);
//							if (flagMap.get(son.getMarkId()) != null) {
//								goo.setFlag(flagMap.get(son.getMarkId()));
//							} else {
//								flagMap.put(son.getMarkId(), maxFlag);
//								goo.setFlag(maxFlag);
//								maxFlag++;
//							}
//							if (bizhong != null && bizhong > 0) {// 有比重需要换算单位
//								if (son.getUnit().equals(goo.getGoodsUnit())
//										&& null != goo.getGoodsZhishu()
//										&& goo.getGoodsZhishu() > 0) {// 单位一致有支数,用lsCount计数
//									if (n == 1) {
//										lsCount = (float) Math.ceil(lsCount
//												/ bizhong);
//									}
//									if (bizhong != null && bizhong > 0) {
//										if (goo.getGoodsZhishu() > lsCount) {
//											goo.setGoodsZhishu(lsCount);
//											// 计算重量
//											if (lsCount * bizhong <= goo
//													.getGoodsCurQuantity()) {
//												goo.setGoodsCurQuantity(lsCount
//														* bizhong);
//											}
//											ckCount2 += lsCount * bizhong;
//											if (goo.getGoodsStoreZHUnit() == null
//													|| goo
//															.getGoodsStoreZHUnit()
//															.length() == 0) {
//												goo.setQlUnit("张");
//											} else {
//												goo.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//											}
//											goo.setIsChangeSf(true);
//											lsCount = 0;
//											count = 0;
//											listGoods.add(goo);
//											break;
//										} else if (goo.getGoodsZhishu() == lsCount) {
//											goo.setGoodsZhishu(lsCount);
//											// 支数刚好情况重量，不重新计算重量
//											// goo.setGoodsCurQuantity(lsCount *
//											// bizhong);
//											ckCount2 += lsCount * bizhong;
//											if (goo.getGoodsStoreZHUnit() == null
//													|| goo
//															.getGoodsStoreZHUnit()
//															.length() == 0) {
//												goo.setQlUnit("张");
//											} else {
//												goo.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//											}
//											goo.setIsChangeSf(true);
//											lsCount = 0;
//											count = 0;
//											listGoods.add(goo);
//											break;
//										} else {
//											lsCount = lsCount
//													- goo.getGoodsZhishu();
//											if (goo.getGoodsZhishu() * bizhong <= goo
//													.getGoodsCurQuantity()) {
//												goo.setGoodsCurQuantity(goo
//														.getGoodsZhishu()
//														* bizhong);
//											}
//											ckCount2 += goo.getGoodsZhishu()
//													* bizhong;
//											if (goo.getGoodsStoreZHUnit() == null
//													|| goo
//															.getGoodsStoreZHUnit()
//															.length() == 0) {
//												goo.setQlUnit("张");
//											} else {
//												goo.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//											}
//											goo.setIsChangeSf(true);
//											listGoods.add(goo);
//										}
//									}
//								} else if (son.getUnit().equals(
//										goo.getGoodsUnit())
//										&& (null == goo.getGoodsZhishu() || goo
//												.getGoodsZhishu() == 0)) {// 单位一致无支数
//									// 此情况不需要比重,数据有误
//								} else if (null != goo.getGoodsZhishu()
//										&& goo.getGoodsZhishu() > 0) {// 单位不一致,
//																		// 有支数用count计数
//									if (bizhong == null || bizhong == 0) {// 用当前库存重新计算比重
//										bizhong = goo.getGoodsCurQuantity()
//												/ goo.getGoodsZhishu();
//									}
//									if (goo.getGoodsZhishu() >= count) {
//										goo.setGoodsZhishu(count);
//										// 计算重量
//										goo
//												.setGoodsCurQuantity(count
//														* bizhong);
//										ckCount2 += count * bizhong;
//										goo
//												.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//										listGoods.add(goo);
//										count = 0;
//										break;
//									} else if (goo.getGoodsZhishu() == count) {
//										goo.setGoodsZhishu(count);
//										// 支数刚好情况重量，不重新计算重量
//										// goo.setGoodsCurQuantity(lsCount *
//										// bizhong);
//										ckCount2 += count * bizhong;
//										goo
//												.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//										listGoods.add(goo);
//										count = 0;
//										break;
//									} else {
//										goo.setGoodsCurQuantity(goo
//												.getGoodsZhishu()
//												* bizhong);
//										ckCount2 += goo.getGoodsZhishu();
//										goo
//												.setQlUnit(goo
//														.getGoodsStoreZHUnit());
//										listGoods.add(goo);
//										count = count - goo.getGoodsZhishu();
//									}
//
//								} else {// 单位不一致无支数,无法计算
//
//								}
//							} else {// 无比重
//								if (goo.getGoodsCurQuantity() >= count) {
//									goo.setGoodsCurQuantity(count);
//									ckCount2 += count;
//									goo.setGoodsZhishu(count);
//									count = 0;
//									goo.setQlUnit(son.getUnit());
//									listGoods.add(goo);
//									break;
//								} else {
//									ckCount2 += goo.getGoodsCurQuantity();
//									goo.setCkCount(goo.getGoodsCurQuantity());
//									count -= goo.getGoodsCurQuantity();
//									goo.setGoodsZhishu(goo
//											.getGoodsCurQuantity());
//									goo.setQlUnit(son.getUnit());
//									listGoods.add(goo);
//								}
//							}
//
//						}
//						if (count > 0) {// 库存不够
//							Goods gd = listG.get(0);
//							gd.setShowType("外购件");
//							gd.setGoodsCurQuantity(0f);
//							gd.setQlCount(wwCount);
//							gd.setHqlCount(hqlCount);
//							gd.setTqlCount(count);
//							gd.setIsEnough(false);
//							listGoods.add(gd);
//						}
//					} else {
//						String hqlGoods2 = " from Goods where goodsMarkId='"
//								+ son.getMarkId()
//								+ "'"
//								+ goodsClassSql
//								+ " and goodsCurQuantity=0 and (fcStatus is null or fcStatus='可用')"
//								+ phoneSql + " order by goodsLotId asc";
//						List<Goods> listG2 = totalDao.find(hqlGoods2);
//						if (listG2.size() > 0) {
//							Goods goo = listG2.get(0);
//							goo.setShowType("外购件");
//							if (flagMap.get(son.getMarkId()) != null) {
//								goo.setFlag(flagMap.get(son.getMarkId()));
//							} else {
//								flagMap.put(son.getMarkId(), maxFlag);
//								goo.setFlag(maxFlag);
//								maxFlag++;
//							}
//							goo.setQlCount(wwCount);
//							goo.setHqlCount(hqlCount);
//							goo.setTqlCount(count);
//							goo.setIsEnough(false);
//							goo.setQlUnit(son.getUnit());
//							listGoods.add(goo);
//						} else {
//							Goods goo = new Goods();
//							goo.setShowType("外购件");
//							if (flagMap.get(son.getMarkId()) != null) {
//								goo.setFlag(flagMap.get(son.getMarkId()));
//							} else {
//								flagMap.put(son.getMarkId(), maxFlag);
//								goo.setFlag(maxFlag);
//								maxFlag++;
//							}
//							goo.setQlCount(wwCount);
//							goo.setHqlCount(hqlCount);
//							goo.setTqlCount(count);
//							goo.setIsEnough(false);
//							goo.setGoodsFullName(son.getProName());
//							goo.setGoodsMarkId(son.getMarkId());
//							goo.setGoodsId(0);
//							goo.setQlUnit(son.getUnit());
//							goo.setGoodsCurQuantity(0f);
//							goo.setGoodsUnit(son.getUnit());
//							if (!"phone".equals(status)) {
//								listGoods.add(goo);
//							}
//
//						}
//					}
//					if (son.getNeedProcess() != null
//							&& son.getNeedProcess().equals("yes")) {
//						ckCount2 = (float) Math.ceil(ckCount2);
//						ckCount1 = ckCount2;
//					} else {
//						if (chaling < 0) {// 原材料之前领多了，但是可继续用在这一批次的数量
//							ckCount2 += chaling;// 补差领
//						}
//						if (ckCount2 == 0) {
//							ckCount1 = (float) Math.floor(countTem
//									* son.getQuanzi1() / son.getQuanzi2());
//						}
//						if (ckCount2 > countTem) {// 剔除差领数量
//							ckCount1 = (float) Math.floor(countTem
//									* son.getQuanzi1() / son.getQuanzi2());
//						} else {
//							ckCount1 = (float) Math.floor(ckCount2
//									* son.getQuanzi1() / son.getQuanzi2());
//						}
//					}
//					if (ckCount1 < 0) {
//						ckCount1 = 0f;
//					}
//					if (ckCount1 > ckCount && procard.getLingliaoType() != null
//							&& procard.getLingliaoType().equals("part")) {
//						ckCount = ckCount1;
//					}
//					if (ckCount1 < ckCount
//							&& (procard.getLingliaoType() == null || !procard
//									.getLingliaoType().equals("part"))) {
//						ckCount = ckCount1;
//					}
//				}
//			}
//			// 查出下层在制品需领
//			List<Procard> zaizhiSonList = totalDao
//					.query(
//							"from Procard where procard.id=? and ((zaizhizkCount is null and zaizhizkCount>0) or (zaizhiApplyZk is null and zaizhiApplyZk>0))",
//							procard.getId());
//			if (zaizhiSonList != null && zaizhiSonList.size() > 0) {
//				for (Procard zaizhiSon : zaizhiSonList) {
//					Float count = 0f;
//					if (zaizhiSon.getProcardStyle().equals("外购")) {
//						count = ckCount * zaizhiSon.getQuanzi2()
//								/ zaizhiSon.getQuanzi1();
//					} else {
//						count = ckCount * zaizhiSon.getCorrCount();
//					}
//					List<Goods> zaizhiGoodList = totalDao
//							.query(
//									"from Goods where goodsMarkId=? and goodsLotId=? and goodsStyle='半成品转库'",
//									zaizhiSon.getMarkId(), zaizhiSon
//											.getSelfCard());
//					if (zaizhiGoodList == null || zaizhiGoodList.size() == 0) {
//						map.put(3, "对不起,仓库中没有对应" + zaizhiSon.getMarkId()
//								+ "的在职品可领");
//					} else {
//						for (Goods zaizhiGood : zaizhiGoodList) {
//							zaizhiGood.setHqlCount(0f);
//							zaizhiGood.setTqlCount(0f);
//							zaizhiGood.setGoodsBeginQuantity(0f);
//							zaizhiGood.setIsEnough(true);
//							zaizhiGood.setIsChangeSf(false);
//							zaizhiGood.setShowType("中转在制品");
//							if (flagMap.get(zaizhiSon.getMarkId()) != null) {
//								zaizhiGood.setFlag(flagMap.get(zaizhiSon
//										.getMarkId()));
//							} else {
//								flagMap.put(zaizhiSon.getMarkId(), maxFlag);
//								zaizhiGood.setFlag(maxFlag);
//								maxFlag++;
//							}
//							if (zaizhiGood.getGoodsCurQuantity() >= count) {
//								zaizhiGood.setGoodsCurQuantity(count);
//								count = 0f;
//								listGoods.add(zaizhiGood);
//								break;
//							} else {
//								count = count
//										- zaizhiGood.getGoodsCurQuantity();
//								listGoods.add(zaizhiGood);
//							}
//						}
//						if (count > 0) {
//							Goods g = zaizhiGoodList.get(0);
//							g.setGoodsCurQuantity(0f);
//							g.setIsEnough(false);
//							ckCount -= count * zaizhiSon.getQuanzi1()
//									/ zaizhiSon.getQuanzi2();
//						}
//					}
//				}
//			}
//			procard.setCkCount(ckCount + hiddenCount);
//
//		}
//		map.put(1, procard);
//		map.put(2, listGoods);
//		return map;
//	}

	private float getYlCount(Procard procard) {
		// TODO Auto-generated method stub
		Float goodsCurQuantity = (Float) totalDao
				.getObjectByCondition(
						"select sum(goodsCurQuantity) from Goods where goodsClass='余料库' and goodsCurQuantity>0 and goodsMarkId=?  "
								+ "and (yllock is null or yllock='' or yllock='no' or (yllock = 'yes' and ylMarkId=?) and (fcStatus is null or fcStatus='可用'))",
						procard.getTrademark(), procard.getMarkId());
		if (goodsCurQuantity != null) {
			return goodsCurQuantity;
		}
		return 0;
	}

	/**
	 * 外购件领料
	 */
	@SuppressWarnings("unchecked")
	public List saveSellList(Integer id, Procard pro, List<Goods> li,
			String tag, float getCount, String ckType, int[] selected,
			List<Goods> goodsList, String tags) {
		Users user = (Users) Util.getLoginUser();
		String hql1 = "from Users where cardId=? and onWork not in('离职','离职中','内退','病休')";
		Users us = (Users) totalDao.getObjectByCondition(hql1, pro
				.getLingliaoren());// 领料人
		if (us == null) {
			throw new RuntimeException("领料人不存在,领料失败!");
		} else {
			Float czcount = (Float) totalDao
					.getObjectByCondition(
							"select count(id) from ProcessinforPeople where procard.id=? and userId =?",
							pro.getId(), us.getId());
			if (czcount == null || czcount == 0) {
				throw new RuntimeException("领料人对此零件无领料权限,领料失败!");
			}
		}
		String totalMarkId = (String) totalDao.getObjectByCondition(
				"select markId from Procard where id=?", pro.getRootId());
		float zaizhiCount = 0f;
		float deletehasCount = 0f;
		float ydeltehasCount = 0f;// 组合领原材料时有成品在制品减去对应的hascount
		if (!pro.getProcardStyle().equals("总成")) {// 总成无在制品
			zaizhiCount = getZaiZhiCount(pro.getId());// 计算可用在制品
			// zaizhiCount = 0f;// 计算可用在制品
		}
		if(pro.getKlNumber()==null){
			pro.setKlNumber(pro.getFilnalCount());
		}
		if(pro.getHascount()==null){
			pro.setHascount(pro.getKlNumber());
		}
		float addZaihi = 0f;
		if (zaizhiCount >= 0) {
			Float zzylCount = 0f;
			if (zaizhiCount >= getCount) {// 在制品够
				deletehasCount = getCount;
				pro.setHascount(pro.getHascount() - getCount);
				zzylCount = getCount;
			} else {
				deletehasCount = zaizhiCount;
				addZaihi = getCount - zaizhiCount;
				pro.setHascount(pro.getHascount() - zaizhiCount);
				zzylCount = zaizhiCount;
			}
		} else {
			throw new RuntimeException("对不起,之前批次的在制品不足!");
		}
		// 计算现已生成在制品数量
		// Float xyCount1 = (Float)
		// totalDao.getObjectByCondition("select sum(ylCount) from ProcardProductRelation where flagType='余额在制品' and procardId=?",
		// pro.getId());
		Float xyCount2 = (Float) totalDao
				.getObjectByCondition(
						"select sum(zyCount) from ProcardProductRelation where flagType='本批在制品' and procardId=?",
						pro.getId());
		// if(xyCount1==null){
		// xyCount1=0f;
		// }
		if (xyCount2 == null) {
			xyCount2 = 0f;
		}
		Float xyCount = xyCount2;
		// 下层最小hasCount
		Float xcMinhasCount = pro.getKlNumber();
		String lingliaoPeople = pro.getLingliaoren();
		pro = (Procard) totalDao.getObjectById(Procard.class, pro.getId());
		pro.setLingliaoren(lingliaoPeople);
		if (pro.getHascount() == null) {
			pro.setHascount(pro.getKlNumber());
		}
		// pro.setHascount(pro.getHascount() - getCount);
		// if (pro.getHascount() < 0) {
		// throw new RuntimeException("对不起,领取超额!");
		// }
		List listSell = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		List<Procard> wgprocardSonList = null;
		if(pro.getProcardStyle().equals("外购")){
			wgprocardSonList = new ArrayList<Procard>();
			wgprocardSonList.add(pro);
		}else{
			wgprocardSonList = totalDao.query(
					"from Procard where procard.id=? and procardStyle='外购' and (sbStatus is null or sbStatus !='删除') and (needProcess is null  or needProcess!='yes')", pro
							.getId());
		}
		List<Procard> zaizhiSonLIst = totalDao
				.query(
						"from Procard where procard.id=? and (sbStatus is null or sbStatus !='删除') and ((zaizhizkCount is null and zaizhizkCount>0) or (zaizhiApplyZk is null and zaizhiApplyZk>0))",
						pro.getId());
		float lasttotalCount = pro.getFilnalCount();// 最后一道工序的可领数量
		float  beforettalCount = 0;// 除最后一道工序的可领数量
		// 已配套套数量
		Float peitaoCount = pro.getKlNumber() - pro.getHascount();
		Float scpeitao = null;// 生产配套数量
		Float wwpeitao = null;// 外委配套数量
		for (Goods g : li) {
			Float qingling = 0f;
			Float shifa = 0f;
			// if (g.getGoodsPosition() != null
			// && g.getGoodsPosition().equals("在制品")) {
			// continue;
			// }
			if (!g.getShowType().equals("在制品充领")) {
				if (selected != null && selected.length > 0) {
					boolean b = false;
					for (int goodsId : selected) {
						if (g.getGoodsId()!=null&&g.getGoodsId().equals(goodsId)) {
							if (goodsList != null && goodsList.size() > 0) {
								for (Goods qlGoods : goodsList) {
									if (qlGoods.getGoodsId()!=null&&qlGoods.getGoodsId().equals(goodsId)) {
										qingling = qlGoods.getGoodsZhishu();
										shifa = qlGoods.getGoodsCurQuantity();
									}
								}
							}
							b = true;
							break;
						}
					}
					if (!b) {// 此外购件没有被选中
						continue;
					}
				} else {
					throw new RuntimeException("请至少选中一个");
				}
			}
			// if(g.getGoodsBeginQuantity()<=0){??忘记干嘛用的了
			// continue;
			// }
			Goods goodsData = (Goods) totalDao.getObjectById(Goods.class, g
					.getGoodsId());
			Sell sell = new Sell();
			sell.setSellArtsCard(pro.getSelfCard());
			sell.setSellSupplier(goodsData.getGoodsSupplier());
			sell.setSellFormat(goodsData.getGoodsFormat());
			sell.setSellLot(goodsData.getGoodsLotId());
			sell.setSellMarkId(goodsData.getGoodsMarkId());
			sell.setSellAdminName(user.getName());
			sell.setSellGoods(goodsData.getGoodsFullName());
			sell.setSellDate(sdf2.format(new Date()));
			sell.setSellTime(sdf.format(new Date()));
			sell.setSellWarehouse(goodsData.getGoodsClass());
			sell.setGoodHouseName(goodsData.getGoodHouseName());// 区名
			sell.setKuwei(goodsData.getGoodsPosition());// 库位;
			sell.setWgType(goodsData.getWgType());
			sell.setKgliao(goodsData.getKgliao());
			sell.setSellCharger(us.getName());
			sell.setSellchardept(us.getDept());
			sell.setSellcharId(us.getId());
			sell.setStyle("生产刷卡");
			sell.setYwmarkId(pro.getYwMarkId());//业务件号
			sell.setSellProMarkId(pro.getRootMarkId());//总成件号
			sell.setRootSelfCard(pro.getRootSelfCard());//总成批次
			sell.setOrderNum(pro.getOrderNumber());//内部订单号
			sell.setPrintStatus("NO");
			sell.setSellUnit(goodsData.getGoodsUnit());
			sell.setGoodsStoreZHUnit(goodsData.getGoodsStoreZHUnit());
			if (g.getShowType().equals("在制品充领")) {
				Float needzzCount = g.getGoodsCurQuantity();
				List<ProcardProductRelation> relationList = totalDao
						.query(
								"from ProcardProductRelation where zyCount>ylCount and flagType='余额在制品' and procardId=?",
								pro.getId());
				if (relationList != null && relationList.size() > 0) {
					for (ProcardProductRelation relation : relationList) {
						Goods zzGoods = (Goods) totalDao.getObjectById(
								Goods.class, relation.getGoodsId());
						if (zzGoods == null
								|| zzGoods.getGoodsCurQuantity() <= 0) {
							continue;
						}
						Float chuku = 0f;
						if (needzzCount <= zzGoods.getGoodsCurQuantity()) {
							zzGoods.setGoodsCurQuantity(zzGoods
									.getGoodsCurQuantity()
									- needzzCount);
							chuku = needzzCount;
							needzzCount = 0f;
						} else {
							needzzCount -= zzGoods.getGoodsCurQuantity();
							chuku = zzGoods.getGoodsCurQuantity();
							zzGoods.setGoodsCurQuantity(0f);
						}
						relation.setYlCount(relation.getYlCount() + chuku);
						relation.setCkCount(relation.getCkCount() + chuku);
						// 在制品出库记录
						Sell zzpSell = new Sell();
						zzpSell.setSellArtsCard(pro.getSelfCard());
						zzpSell.setSellSupplier(zzGoods.getGoodsSupplier());
						zzpSell.setSellFormat(zzGoods.getGoodsFormat());
						zzpSell.setSellLot(zzGoods.getGoodsLotId());
						zzpSell.setSellMarkId(zzGoods.getGoodsMarkId());
						zzpSell.setSellAdminName(user.getName());
						zzpSell.setSellGoods(zzGoods.getGoodsFullName());
						zzpSell.setSellDate(Util.getDateTime("yyyy-MM-dd"));
						zzpSell.setSellTime(Util.getDateTime());
						zzpSell.setSellWarehouse(zzGoods.getGoodsClass());
						zzpSell.setSellUnit(zzGoods.getGoodsUnit());
						zzpSell.setSellCount(chuku);
						zzpSell.setWgType(zzGoods.getWgType());
						zzpSell.setSellCharger(us.getName());
						sell.setSellchardept(us.getDept());
						sell.setSellcharId(us.getId());
						sell.setStyle("生产刷卡");
						zzpSell.setPrintStatus("YES");
						zzpSell.setYwmarkId(pro.getYwMarkId());//业务件号
						zzpSell.setSellProMarkId(pro.getRootMarkId());//总成件号
						zzpSell.setRootSelfCard(pro.getRootSelfCard());//总成批次
						zzpSell.setOrderNum(pro.getOrderNumber());//内部订单号
						totalDao.save(zzpSell);
						totalDao.update(zzGoods);
						if (needzzCount == 0) {
							break;
						}
					}
				}
				if (needzzCount > 0) {
					throw new RuntimeException("对不起没有足够的在制品提供出库!");
				}
				continue;
				// for (Procard swgon : wgprocardSonList) {
				// if (!swgon.getMarkId().equals(pro.getMarkId()))
				// {//下层减去对应的hasCOunt
				// if(swgon.getHascount()==null){
				// swgon.setHascount(swgon.getKlNumber());
				// }
				// swgon.setHascount(swgon.getHascount()-g.getGoodsCurQuantity()*swgon.getQuanzi2()/swgon.getQuanzi1());
				// if(swgon.getHascount()==0){
				// swgon.setStatus("完成");
				// }
				// totalDao.update(swgon);
				// }
				// }
			} else if (g.getShowType().equals("余料")) {
				if (goodsData.getGoodsCurQuantity() <g.getGoodsCurQuantity() -0.00005) {
					throw new RuntimeException("对不起余料抵发数量不足,领料失败!");
				}
				if (wgprocardSonList != null && wgprocardSonList.size() > 0) {
					for (Procard swgon : wgprocardSonList) {
						if (swgon.getMarkId()
								.equals(goodsData.getGoodsMarkId())) {
							Float thisAddZaizhi = 0f;
							if (swgon.getHascount() == null) {
								swgon.setHascount(swgon.getKlNumber());
							}
							if (swgon.getUnit()
									.equals(goodsData.getGoodsUnit())) {// 单位一致
								swgon.setHascount(swgon.getHascount()
										- g.getGoodsBeginQuantity());
								if((swgon.getHascount()*swgon.getQuanzi1()/swgon.getQuanzi2())<0.01){
									swgon.setHascount(0f);
								}
								thisAddZaizhi = g.getGoodsBeginQuantity();
								if (swgon.getHascount() == 0) {
									swgon.setStatus("完成");
									ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", swgon.getId());
									if(bl!=null){
										bl.setStatus("已领完");
										totalDao.update(bl);
									}
								}
							} else {
								// 单零件重量（一般为原材料使用）
								String banbenSql = null;
								if (swgon.getBanBenNumber() == null
										|| swgon.getBanBenNumber().length() == 0) {
									banbenSql = " and (banBenNumber is null or banBenNumber ='')";
								} else {
									banbenSql = " and banBenNumber ='"
											+ swgon.getBanBenNumber() + "'";
								}
								Float bizhong = (Float) totalDao
										.getObjectByCondition(
												"select bili from YuanclAndWaigj where markId =? ",
												swgon.getMarkId());
								Goods googsTemp = (Goods) totalDao
										.getObjectByCondition(
												"from Goods where kgliao=? and goodsCurQuantity>0 and  goodsZhishu>0 and goodsMarkId=?"
														+ banbenSql, swgon
														.getKgliao(), swgon
														.getMarkId());
								if (bizhong == null) {
									if (googsTemp != null) {
										bizhong = googsTemp
												.getGoodsCurQuantity()
												/ googsTemp.getGoodsZhishu();
									}
								}
								swgon.setHascount(swgon.getHascount()
										- Math.round(g.getGoodsBeginQuantity()
												/ bizhong));
								if((swgon.getHascount()*swgon.getQuanzi1()/swgon.getQuanzi2())<0.01){
									swgon.setHascount(0f);
								}
								thisAddZaizhi = (float) Math.round(g
										.getGoodsBeginQuantity()
										/ bizhong);
								if (swgon.getHascount() == 0) {
									swgon.setStatus("完成");
									ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", swgon.getId());
									if(bl!=null){
										bl.setStatus("已领完");
										totalDao.update(bl);
									}
								}
								totalDao.update(swgon);
							}
							String lingliaoDetail = swgon.getLingliaoDetail();
							if (lingliaoDetail == null) {
								lingliaoDetail = goodsData.getGoodsLotId()
										+ "(余):" + Util.FomartFloat(g.getGoodsCurQuantity(),4);
							} else {
								lingliaoDetail += ","
										+ goodsData.getGoodsLotId() + "(余):"
										+ Util.FomartFloat(g.getGoodsCurQuantity(),4);
							}
							swgon.setLingliaoDetail(lingliaoDetail);
							// 添加外购件在制品数量
							String hqlzaizhi = "from Goods where goodsMarkId='"
									+ g.getGoodsMarkId()
									+ "' and goodsLotId='"
									+ swgon.getSelfCard()
									+ "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
							List listzizhi = totalDao.find(hqlzaizhi);
							Integer rgoodsId = null;
							if (listzizhi != null && listzizhi.size() > 0) {
								Goods g1 = (Goods) listzizhi.get(0);
								g1.setGoodsCurQuantity(Util.FomartFloat(g1.getGoodsCurQuantity()
										+ thisAddZaizhi, 4));
								if (g1.getGoodsCurQuantity() < 0) {
									AlertMessagesServerImpl
											.addAlertMessages(
													"系统维护异常组",
													"件号:"
															+ g1
																	.getGoodsMarkId()
															+ "批次:"
															+ g1
																	.getGoodsLotId()
															+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
															+ Util
																	.getDateTime(),
													"2");
									g1.setGoodsCurQuantity(0f);
								}
								totalDao.update(g1);
								rgoodsId = g1.getGoodsId();
							} else {
								Goods gg = new Goods();
								gg.setGoodsMarkId(g.getGoodsMarkId());
								gg.setBanBenNumber(swgon.getBanBenNumber());
								gg.setGoodsFormat(swgon.getSpecification());
								gg.setTuhao(swgon.getTuhao());
								gg.setGoodsLotId(swgon.getSelfCard());
								gg.setGoodsFullName(goodsData
										.getGoodsFullName());
								gg.setGoodsClass("在制品");
								gg.setGoodsCurQuantity(Util.FomartFloat(thisAddZaizhi,4));
								gg.setGoodsBeginQuantity(Util.FomartFloat(thisAddZaizhi,4));
								gg.setGoodsChangeTime(Util.getDateTime());
								if (gg.getGoodsCurQuantity() < 0) {
									AlertMessagesServerImpl
											.addAlertMessages(
													"系统维护异常组",
													"件号:"
															+ gg
																	.getGoodsMarkId()
															+ "批次:"
															+ gg
																	.getGoodsLotId()
															+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
															+ Util
																	.getDateTime(),
													"2");
									gg.setGoodsCurQuantity(0f);
								}
								gg.setGoodsUnit(swgon.getUnit());
								totalDao.save(gg);
								rgoodsId = gg.getGoodsId();
							}
							// 添加零件与在制品关系表
							ProcardProductRelation pprelation = new ProcardProductRelation();
							pprelation.setAddTime(Util.getDateTime());
							pprelation.setProcardId(swgon.getId());
							pprelation.setGoodsId(rgoodsId);
							pprelation.setZyCount(Util.FomartFloat(thisAddZaizhi,4));
							pprelation.setFlagType("本批在制品");
							totalDao.save(pprelation);
							// 添加外购件在制品入库记录
							GoodsStore gs = new GoodsStore();
							gs.setGoodsStoreMarkId(g.getGoodsMarkId());// 件号
							gs.setGoodsStoreFormat(swgon.getSpecification());
							gs.setTuhao(swgon.getTuhao());
							gs.setGoodsStoreGoodsName(g.getGoodsFullName());// 名称
							gs.setGoodsStoreLot(pro.getSelfCard());// 批次
							gs.setGoodsStoreCount(thisAddZaizhi);// 数量
							gs.setPrintStatus("YES");
							gs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
							gs.setGoodsStoreWarehouse("在制品");// 库别
							gs.setGoodsStoreCharger(user.getName());// 经办人
							gs.setStyle("正常（成品）");// 入库类型
							if (us != null) {// 负责人
								gs.setGoodsStorePerson(us.getName());
							} else {
								gs.setGoodsStorePerson(swgon.getLingliaoren());
							}
							gs.setGoodsStoreDate(DateUtil.formatDate(
									new Date(), "yyyy-MM-dd"));
							gs.setGoodsStoreTime(Util.getDateTime());
							gs.setGoodsStoreUnit(swgon.getUnit());// 单位
							totalDao.save(gs);
							goodsData.setGoodsCurQuantity(Util.FomartFloat(goodsData
									.getGoodsCurQuantity()
									- g.getGoodsCurQuantity(),4));
							goodsData.setGoodsZhishu(0f);
							GoodsServerImpl.pushkc(null, goodsData);
							totalDao.update(goodsData);
							// 余料出库
							Sell sellYl = new Sell();
							sellYl.setSellArtsCard(pro.getSelfCard());
							sellYl
									.setSellSupplier(goodsData
											.getGoodsSupplier());
							sellYl.setSellFormat(goodsData.getGoodsFormat());
							sellYl.setSellLot(goodsData.getGoodsLotId());
							sellYl.setSellMarkId(goodsData.getGoodsMarkId());
							sellYl.setSellAdminName(user.getName());
							sellYl.setSellGoods(goodsData.getGoodsFullName());
							sellYl.setSellDate(sdf2.format(new Date()));
							sellYl.setSellTime(sdf.format(new Date()));
							sellYl.setSellWarehouse(goodsData.getGoodsClass());
							sellYl.setSellUnit(goodsData.getGoodsUnit());
							sellYl.setSellCount(Util.FomartFloat(g.getGoodsCurQuantity(),4));
							sellYl.setKgliao(goodsData.getKgliao());
							sellYl.setSellCharger(us.getName());
							sellYl.setSellchardept(us.getDept());
							sellYl.setSellcharId(us.getId());
							sellYl.setStyle("生产刷卡");
							sellYl.setYwmarkId(pro.getYwMarkId());//业务件号
							sellYl.setSellProMarkId(pro.getRootMarkId());//总成件号
							sellYl.setRootSelfCard(pro.getRootSelfCard());//总成批次
							sellYl.setOrderNum(pro.getOrderNumber());//内部订单号
							totalDao.save(sellYl);
							if (g.getGoodsCurQuantity() > g
									.getGoodsBeginQuantity()) {// 余料实发大于请领多余在做入库
								Goods ylGoods = new Goods();
								ylGoods.setGoodsClass("余料库");
								ylGoods.setGoodsBeginQuantity(Util.FomartFloat(g
										.getGoodsCurQuantity()
										- g.getGoodsBeginQuantity(),4));
								ylGoods.setGoodsCurQuantity(Util.FomartFloat(g
										.getGoodsCurQuantity()
										- g.getGoodsBeginQuantity(),4));
								ylGoods.setGoodsZhishu(1f);
								ylGoods.setYlMarkId(pro.getMarkId());
								ylGoods.setYlSelfCard(pro.getSelfCard());
								ylGoods.setGoodsMarkId(goodsData
										.getGoodsMarkId());
								ylGoods.setGoodsFormat(goodsData
										.getGoodsFormat());
								ylGoods.setGoodsUnit(g.getGoodsUnit());
								ylGoods.setGoodsChangeTime(Util
										.getDateTime("yyyy-MM-dd"));
								ylGoods.setYllock(goodsData.getYllock());
								ylGoods.setKgliao(goodsData.getKgliao());
								// ylGoods.set
								ylGoods.setWgType(goodsData.getWgType());
								totalDao.save(ylGoods);
								// 余料产品中间表
								ProcardProductRelation ppr = new ProcardProductRelation();
								ppr.setAddTime(Util.getDateTime());
								ppr.setProcardId(swgon.getId());
								ppr.setGoodsId(ylGoods.getGoodsId());
								ppr.setZyCount(Util.FomartFloat(ylGoods.getGoodsCurQuantity(),4));
								ppr.setFlagType("余料");
								totalDao.save(ppr);
								// 余料入库记录
								GoodsStore ylgs = new GoodsStore();
								ylgs.setGoodsStoreMarkId(ylGoods
										.getGoodsMarkId());// 件号
								ylgs.setGoodsStoreFormat(ylGoods
										.getGoodsFormat());// 规格
								ylgs.setGoodsStoreGoodsName(pro.getProName());// 名称
								ylgs.setGoodsStoreLot(pro.getSelfCard());// 批次
								ylgs.setGoodsStoreCount(Util.FomartFloat(g.getGoodsCurQuantity()
										- g.getGoodsBeginQuantity(),4));// 数量
								ylgs.setPrintStatus("YES");
								ylgs.setWgType(g.getWgType());
								ylgs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
								ylgs.setGoodsStoreWarehouse("余料库");// 库别
								ylgs.setKgliao(ylGoods.getKgliao());
								ylgs.setGoodsStoreCharger(user.getName());// 经办人
								ylgs.setStyle("余料");// 入库类型
								if (us != null) {// 负责人
									ylgs.setGoodsStorePerson(us.getName());
								} else {
									ylgs.setGoodsStorePerson(pro
											.getLingliaoren());
								}
								ylgs.setGoodsStoreDate(DateUtil.formatDate(
										new Date(), "yyyy-MM-dd"));
								ylgs.setGoodsStoreUnit(pro.getUnit());// 单位
								totalDao.save(ylgs);
							}
						}
					}
					continue;
				}
			} else if (g.getShowType().equals("中转在制品")) {
				if (zaizhiSonLIst != null && zaizhiSonLIst.size() > 0) {
					for (Procard zaizhisong : zaizhiSonLIst) {
						if (!zaizhisong.getMarkId().equals(
								goodsData.getGoodsMarkId())) {
							continue;
						}
						if (zaizhisong.getZaizhizkCount() < g
								.getGoodsCurQuantity()) {
							throw new RuntimeException("对不起，" + zaizhisong
									+ "的在库在制品领取超额!");
						}
						zaizhisong.setZaizhizkCount(zaizhisong
								.getZaizhizkCount()
								- g.getGoodsCurQuantity());
						totalDao.update(zaizhisong);
					}
				}
			} else {// 外购件spcc_50*1.5*6000
				sell.setWgType(g.getWgType());
				if (wgprocardSonList != null && wgprocardSonList.size() > 0) {
					for (Procard swgon : wgprocardSonList) {
						if (!swgon.getMarkId().equals(
								goodsData.getGoodsMarkId())) {
							continue;
						}
						// 领料与外购件的直接关联数量
						Float bijiaoCount = qingling;
						if (swgon.getUnit()
								.equals(goodsData.getGoodsUnit())) {// 单位一致对应实发
							bijiaoCount = shifa;
						} else {// 否则对应请领
						}
						Float addwgzz=bijiaoCount;//外购件在制品添加数量
						// 外委出去数量
						Float wwCount = (Float) totalDao
								.getObjectByCondition(
										"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and processInforWWApply.status !='打回')",
										swgon.getId());
						if (wwCount == null) {
							wwCount = 0f;
						}
						// 外委出去剩余未被领取数量
						Float wwhascount = (Float) totalDao
								.getObjectByCondition(
										"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and processInforWWApply.status !='打回')",
										swgon.getId());
						if (wwhascount == null) {
							wwhascount = 0f;
						}
						if(swgon.getWwblCount()==null){
							swgon.setWwblCount(0f);
						}
						swgon.setWwCount(wwCount+swgon.getWwblCount());
						if(swgon.getWwblreceiveCount()==null){
							swgon.setWwblreceiveCount(0f);
						}
						swgon.setWwhascount(wwhascount+(swgon.getWwblCount()-swgon.getWwblreceiveCount()));
						// 生产未领数量
						Float thisHasCount = swgon.getHascount() - swgon.getWwhascount()-swgon.getWwblCount();
						if (g.getGoodsMarkId().equals(pro.getMarkId())) {
							// 与领料件号相同，说明是半成品领料，在制品已在之前处理。
							// 处理材料批次
							String lingliaoDetail = pro.getLingliaoDetail();
							if (lingliaoDetail == null) {
								lingliaoDetail = goodsData.getGoodsLotId()
										+ ":" + g.getGoodsCurQuantity();
							} else {
								lingliaoDetail += ","
										+ goodsData.getGoodsLotId() + ":"
										+ g.getGoodsCurQuantity();
							}
							pro.setLingliaoDetail(lingliaoDetail);
							if (thisHasCount < bijiaoCount-0.00005) {// 实际领的比本外购件的总需求量要多
								AlertMessagesServerImpl
								.addAlertMessages(
										"系统维护异常组",
										"件号:"
												+ swgon.getMarkId()
												+ "批次:"
												+ swgon
														.getSelfCard()
												+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
												+ Util
														.getDateTime(),
										"2");
								swgon.setHascount(0f);
							}else{
								swgon.setHascount(swgon.getHascount()
										- bijiaoCount);
								if((swgon.getHascount()*swgon.getQuanzi1()/swgon.getQuanzi2())<0.01){
									swgon.setHascount(0f);
								}
							}
							totalDao.update(swgon);
						} else {
							// 单零件重量（一般为原材料使用）
							Float bizhong = (Float) totalDao
									.getObjectByCondition(
											"select bili from YuanclAndWaigj where markId =? ",
											g.getGoodsMarkId());
							// 更新外购件hasCount;
							if (swgon.getHascount() == null) {
								swgon.setHascount(swgon.getKlNumber());
							}
							
							if (thisHasCount < bijiaoCount-0.00005) {// 实际领的比本外购件的总需求量要多
								addwgzz = thisHasCount;
								if (bizhong == null || bizhong == 0) {// 没有比重明显数据有问题
									// 发送异常消息bgg
									AlertMessagesServerImpl
											.addAlertMessages(
													"系统维护异常组",
													"件号:"
															+ swgon.getMarkId()
															+ "批次:"
															+ swgon
																	.getSelfCard()
															+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
															+ Util
																	.getDateTime(),
													"2");
									swgon.setHascount(0f);
									swgon.setStatus("完成");
									ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", swgon.getId());
									if(bl!=null){
										bl.setStatus("已领完");
										totalDao.update(bl);
									}
									totalDao.update(swgon);
								} else {// 多余为余料,添加余料
									swgon.setHascount(Util.FomartFloat(swgon.getHascount()
											- thisHasCount,4));
									if((swgon.getHascount()*swgon.getQuanzi1()/swgon.getQuanzi2())<0.01){
										swgon.setHascount(0f);
									}
									if (swgon.getHascount() == 0) {
										swgon.setStatus("完成");
										ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", swgon.getId());
										if(bl!=null){
											bl.setStatus("已领完");
											totalDao.update(bl);
										}
									}
									totalDao.update(swgon);
									String yllock = "no";
									if (pro.getJgyl() != null
											&& pro.getJgyl().equals("yes")) {
										yllock = "yes";
									}
									Goods ylGoods = new Goods();
									ylGoods.setGoodsClass("余料库");
									ylGoods.setGoodsBeginQuantity(Util.FomartFloat(bijiaoCount
											- thisHasCount,4));
									ylGoods.setGoodsCurQuantity(Util.FomartFloat(bijiaoCount
											- thisHasCount,4));
									ylGoods.setGoodsZhishu(1f);
									ylGoods.setYlMarkId(pro.getMarkId());
									ylGoods.setYlSelfCard(pro.getSelfCard());
									ylGoods.setGoodsMarkId(swgon.getMarkId());
									ylGoods.setGoodsFormat(swgon
											.getSpecification());
									ylGoods.setGoodsUnit(g.getGoodsUnit());
									ylGoods.setGoodsChangeTime(Util
											.getDateTime("yyyy-MM-dd"));
									ylGoods.setYllock(yllock);
									// ylGoods.set
									ylGoods.setWgType(goodsData.getWgType());
									ylGoods.setKgliao(goodsData.getKgliao());
									totalDao.save(ylGoods);
									// 余料入库记录
									GoodsStore ylgs = new GoodsStore();
									ylgs.setGoodsStoreMarkId(ylGoods
											.getGoodsMarkId());// 件号
									ylgs.setGoodsStoreFormat(ylGoods
											.getGoodsFormat());// 规格
									ylgs.setGoodsStoreGoodsName(pro
											.getProName());// 名称
									ylgs.setGoodsStoreLot(pro.getSelfCard());// 批次
									ylgs.setGoodsStoreCount(bijiaoCount
											- thisHasCount);// 数量
									ylgs.setPrintStatus("YES");
									ylgs.setWgType(swgon.getWgType());
									ylgs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
									ylgs.setGoodsStoreWarehouse("余料库");// 库别
									ylgs.setKgliao(ylGoods.getKgliao());
									Users jingban = Util.getLoginUser();
									ylgs
											.setGoodsStoreCharger(jingban
													.getName());// 经办人
									ylgs.setStyle("余料");// 入库类型
									if (us != null) {// 负责人
										ylgs.setGoodsStorePerson(us.getName());
									} else {
										ylgs.setGoodsStorePerson(pro
												.getLingliaoren());
									}
									ylgs.setGoodsStoreDate(DateUtil.formatDate(
											new Date(), "yyyy-MM-dd"));
									ylgs.setGoodsStoreUnit(swgon.getUnit());// 单位
									totalDao.save(ylgs);
								}
							} else {
								swgon.setHascount(Util.FomartFloat(swgon.getHascount()
										- bijiaoCount,4));
								if((swgon.getHascount()*swgon.getQuanzi1()/swgon.getQuanzi2())<0.01){
									swgon.setHascount(0f);
								}
							}
							if (swgon.getHascount() == 0) {
								swgon.setStatus("完成");
								ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", swgon.getId());
								if(bl!=null){
									bl.setStatus("已领完");
									totalDao.update(bl);
								}
							}
							totalDao.update(swgon);
							// 处理材料批次
							String lingliaoDetail = swgon.getLingliaoDetail();
							if (lingliaoDetail == null) {
								lingliaoDetail = goodsData.getGoodsLotId()
										+ ":" + Util.FomartFloat(g.getGoodsCurQuantity(),4);
							} else {
								lingliaoDetail += ","
										+ goodsData.getGoodsLotId() + ":"
										+ Util.FomartFloat(g.getGoodsCurQuantity(),4);
							}
							swgon.setLingliaoDetail(lingliaoDetail);
							totalDao.update(swgon);
							// 添加外购件在制品数量
							String hqlzaizhi = "from Goods where goodsMarkId='"
									+ g.getGoodsMarkId()
									+ "' and goodsLotId='"
									+ pro.getSelfCard()
									+ "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
							List listzizhi = totalDao.find(hqlzaizhi);
							Integer rgoodsId = null;
							if (listzizhi != null && listzizhi.size() > 0) {
								Goods g1 = (Goods) listzizhi.get(0);
								g1.setGoodsCurQuantity(Util.FomartFloat(g1.getGoodsCurQuantity()
										+ thisHasCount,4));
								if (g1.getGoodsCurQuantity() < 0) {
									AlertMessagesServerImpl
											.addAlertMessages(
													"系统维护异常组",
													"件号:"
															+ g1
																	.getGoodsMarkId()
															+ "批次:"
															+ g1
																	.getGoodsLotId()
															+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
															+ Util
																	.getDateTime(),
													"2");
									g1.setGoodsCurQuantity(0f);
								}
								totalDao.update(g1);
								rgoodsId = g1.getGoodsId();
							} else {
								Goods gg = new Goods();
								gg.setGoodsMarkId(g.getGoodsMarkId());
								gg.setBanBenNumber(swgon.getBanBenNumber());
								gg.setGoodsFormat(swgon.getSpecification());
								gg.setTuhao(swgon.getTuhao());
								gg.setGoodsLotId(swgon.getSelfCard());
								gg.setGoodsFullName(goodsData
										.getGoodsFullName());
								gg.setGoodsClass("在制品");
								gg.setGoodsCurQuantity(Util.FomartFloat(thisHasCount,4));
								gg.setGoodsBeginQuantity(Util.FomartFloat(thisHasCount,4));
								if (gg.getGoodsCurQuantity() < 0) {
									AlertMessagesServerImpl
											.addAlertMessages(
													"系统维护异常组",
													"件号:"
															+ gg
																	.getGoodsMarkId()
															+ "批次:"
															+ gg
																	.getGoodsLotId()
															+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
															+ Util
																	.getDateTime(),
													"2");
									gg.setGoodsCurQuantity(0f);
								}
								gg.setGoodsUnit(pro.getUnit());
								totalDao.save(gg);
								rgoodsId = gg.getGoodsId();

							}
							// 添加零件与在制品关系表
							ProcardProductRelation pprelation = new ProcardProductRelation();
							pprelation.setProcardId(swgon.getId());
							pprelation.setGoodsId(rgoodsId);
							pprelation.setZyCount(Util.FomartFloat(addwgzz,4));
							pprelation.setFlagType("本批在制品");
							totalDao.save(pprelation);
							GoodsStore gs = new GoodsStore();// 添加外购件在制品入库记录
							gs.setGoodsStoreMarkId(g.getGoodsMarkId());// 件号
							gs.setGoodsStoreFormat(swgon.getSpecification());
							gs.setTuhao(swgon.getTuhao());
							gs.setBanBenNumber(swgon.getBanBenNumber());
							gs.setGoodsStoreGoodsName(g.getGoodsFullName());// 名称
							gs.setGoodsStoreLot(swgon.getSelfCard());// 批次
							// gs.setGoodsStoreCount((float) Math.floor(g
							// .getGoodsCurQuantity()));// 数量
							gs.setGoodsStoreCount(Util.FomartFloat(addwgzz,4));// 数量
							gs.setPrintStatus("YES");
							gs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
							gs.setGoodsStoreWarehouse("在制品");// 库别
							Users jingban = Util.getLoginUser();
							gs.setGoodsStoreCharger(jingban.getName());// 经办人
							gs.setStyle("正常（成品）");// 入库类型
							if (us != null) {// 负责人
								gs.setGoodsStorePerson(us.getName());
							} else {
								gs.setGoodsStorePerson(swgon.getLingliaoren());
							}
							gs.setGoodsStoreDate(DateUtil.formatDate(
									new Date(), "yyyy-MM-dd"));
							gs.setGoodsStoreUnit(swgon.getUnit());// 单位
							totalDao.save(gs);
						}
					}
				}
			}
			sell.setSellCount(Util.FomartFloat(g.getGoodsCurQuantity(),4));
			if (goodsData.getGoodsZhishu() != null
					&& goodsData.getGoodsZhishu() > 0) {
				goodsData.setGoodsZhishu(goodsData.getGoodsZhishu() - qingling);
				sell.setSellZhishu(qingling);
			}
			if(Math.abs((goodsData.getGoodsCurQuantity()- shifa))<0.00001){
				goodsData.setGoodsCurQuantity(0f);
			}else{
				goodsData.setGoodsCurQuantity(Util.FomartFloat(goodsData.getGoodsCurQuantity()
						- shifa,4));
			}
			if (goodsData.getGoodsCurQuantity() < 0) {
				throw new RuntimeException("件号:"
						+ goodsData.getGoodsMarkId() + "批次:"
						+ goodsData.getGoodsLotId()
						+ "库存数量不足!");
//				AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
//						+ goodsData.getGoodsMarkId() + "批次:"
//						+ goodsData.getGoodsLotId()
//						+ "库存数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
//						+ Util.getDateTime(), "2");
//				goodsData.setGoodsCurQuantity(0f);
			}
			totalDao.update(goodsData);
			if (null == sell.getSellFormat()) {
				sell.setSellFormat("");
			}
			totalDao.save(sell);
			
			/****************************判断剩余库存量是否小与最小库存量*********************************************************************************************/
			boolean bool = isneedcg(sell.getSellMarkId(), sell.getSellFormat(), sell.getBanBenNumber());
			if(bool){
				//添加手动下单信息;
				ManualOrderPlanDetail mod = new ManualOrderPlanDetail();
				mod.setMarkId(wgj.getMarkId());//件号
				mod.setProName(wgj.getName());//名称
				mod.setSpecification(wgj.getSpecification());//规格
				mod.setBanben(wgj.getBanbenhao());//版本号
				mod.setKgliao(wgj.getKgliao());//供料属性
				mod.setTuhao(wgj.getTuhao());//图号
				mod.setWgType(wgj.getWgType());//物料类别
				mod.setUnit(wgj.getUnit());//单位
				mod.setCgnumber(wgj.getCgcount());//采购数量
				mod.setType("安全库存");//添加方式
				mod.setRukuNum(0f);
				mod.setAddTime(Util.getDateTime());//添加时间
				mod.setRemarks("件号"+wgj.getMarkId()+"零件名称"+wgj.getName()+"总库存量低于安全库存"+wgj.getMinkc()+"系统自动下单;");
				totalDao.save(mod);
			}
			
			
			//如果是手机端添加出库记录
			if("code".equals(tags)){
				WareBangGoogs bangGoogs = (WareBangGoogs) totalDao.getObjectByCondition("from WareBangGoogs where fk_good_id = ? and fk_ware_id = ?", g.getGoodsId(),g.getKuweiId());
				if(bangGoogs!=null){
					if(bangGoogs.getNumber()>qingling){
						bangGoogs.setNumber(bangGoogs.getNumber()-qingling);
						totalDao.update(bangGoogs);
					}else {
						if(bangGoogs.getNumber()<qingling){
							AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
									+ goodsData.getGoodsMarkId() + "批次:"
									+ goodsData.getGoodsLotId() +"goosId:"+g.getGoodsId()
									+ "中间表数量小于可领数量，系统自动修复为0，中间表数量为："+bangGoogs.getNumber()+"领料数量是："+qingling+"操作是：领料(外购件),当前系统时间为"
									+ Util.getDateTime(), "2");
						}
						totalDao.delete(bangGoogs);
					}
					WarehouseNumber wn = (WarehouseNumber) totalDao.getObjectById(WarehouseNumber.class, g.getKuweiId());
					if(wn!=null)//重置屏幕
						AttendanceTowServerImpl.sendPin_1(wn);
				}
				
			}
			listSell.add(sell);
		}
		for (Procard procard : wgprocardSonList) {
			if(procard.getHascount()==null){//被包工包料外委出去了
				scpeitao=0f;
				wwpeitao=0f;
				lasttotalCount=0f;
				xcMinhasCount=0f;
			}else{
				if(procard.getWwCount()==null){//之前没有遍历到的
					// 外委出去数量
					Float wwCount = (Float) totalDao
					.getObjectByCondition(
							"select sum(applyCount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and processInforWWApply.status !='打回')",
							procard.getId());
					if (wwCount == null) {
						wwCount = 0f;
					}
					// 外委出去剩余未被领取数量
					Float wwhascount = (Float) totalDao
					.getObjectByCondition(
							"select sum(hascount) from ProcessInforWWProcard where procardId=? and applyDtailId in(select id from ProcessInforWWApplyDetail where wwType='工序外委' and processInforWWApply.status !='打回')",
							procard.getId());
					if (wwhascount == null) {
						wwhascount = 0f;
					}
					//外委包料
					if(procard.getWwblCount()==null){
						procard.setWwblCount(0f);
					}
					//外委包料道货数量
					procard.setWwCount(wwCount+procard.getWwblCount());
					if(procard.getWwblreceiveCount()==null){
						procard.setWwblreceiveCount(0f);
					}
					procard.setWwhascount(wwhascount+(procard.getWwblCount()-procard.getWwblreceiveCount()));
				}
				// 生产未领数量
				Float scHascount = procard.getHascount() - procard.getWwhascount();
//				// 对应上层生产未领数量
//				Float upscHascount = (thisHasCount - qingling)
//						* swgon.getQuanzi1() / swgon.getQuanzi2();
				// 外委已领数量
				Float wwylCount = procard.getWwCount() - procard.getWwhascount();
				// 生产已领数量
				Float scylCount = procard.getKlNumber()
						- procard.getHascount() - wwylCount;
				if (scpeitao == null) {
					scpeitao = scylCount*procard.getQuanzi1()/procard.getQuanzi2();
				} else if (scpeitao > scylCount*procard.getQuanzi1()/procard.getQuanzi2()) {
					scpeitao = scylCount*procard.getQuanzi1()/procard.getQuanzi2();
				}
				if (procard.getWwCount() != 0) {
					if (wwpeitao == null) {
						wwpeitao = wwylCount*procard.getQuanzi1()/procard.getQuanzi2();
					} else if (wwpeitao > wwylCount*procard.getQuanzi1()/procard.getQuanzi2()) {
						wwpeitao = wwylCount*procard.getQuanzi1()/procard.getQuanzi2();
					}
				}
				if(wwpeitao == null){
					wwpeitao=0f;
				}
				float totalHasCount2 = (procard.getKlNumber() - procard
						.getHascount())
						* procard.getQuanzi1() / procard.getQuanzi2();
				if(procard.getHascount()==0){
					totalHasCount2=pro.getFilnalCount();
				}
				if(procard.getKlNumber()>=procard.getFilnalCount()&&procard.getHascount()==0){//小数位数问题
					totalHasCount2 = pro.getFilnalCount();
				}
				if(totalHasCount2<0){
					totalHasCount2=0f;
				}
				if (totalHasCount2 < lasttotalCount) {
					lasttotalCount = totalHasCount2;
				}
				if(totalHasCount2>beforettalCount){
					beforettalCount = totalHasCount2;
				}
				if((procard.getHascount()
					* procard.getQuanzi1() / procard.getQuanzi2())<xcMinhasCount){
						xcMinhasCount =procard.getHascount()
						* procard.getQuanzi1() / procard.getQuanzi2();
					
				}
				totalDao.update(procard);
			}
			
			
		}
		//计算生产工序提交数量
		List<Procard> scProcardList =totalDao.query("from Procard where procard.id=? and (procardStyle='自制' or (procardStyle='外购' and  needProcess='yes') )and (sbStatus is null or sbStatus !='删除')", pro.getId());
		if(scProcardList!=null&&scProcardList.size()>0){
			for(Procard scProcard:scProcardList){
				if(scProcard.getTjNumber()==null){
					lasttotalCount=0f;
				}else if(scProcard.getProcardStyle().equals("外购")){
					float totalHasCount2 = scProcard.getTjNumber()
							* scProcard.getQuanzi1() / scProcard.getQuanzi2();
					if (totalHasCount2 < lasttotalCount) {
						lasttotalCount = totalHasCount2;
					}
				}else{
					float totalHasCount2 = scProcard.getTjNumber()/scProcard.getCorrCount();
					if (totalHasCount2 < lasttotalCount) {
						lasttotalCount = totalHasCount2;
					}
				}
			}
		}
		 if(wwpeitao==null){
			 wwpeitao=0f;
		 }
		 if(scpeitao%1<0.95){
			 scpeitao= (float)Math.floor(scpeitao);
			}else{
				scpeitao= (float)Math.ceil(scpeitao);
			}
		 if(wwpeitao%1<0.95){
			 wwpeitao= (float)Math.floor(wwpeitao);
		 }else{
			 wwpeitao= (float)Math.ceil(wwpeitao);
		 }
		pro.setHascount(pro.getKlNumber()-scpeitao-wwpeitao);
		Float addzaizhi = (float) Math.floor(pro.getKlNumber() - xcMinhasCount
				- xyCount);
		if (addzaizhi > 0) {
			zaizhiInput(pro, user, addzaizhi, "在制品入库");
		}
		if ("barcode".equals(tag)) {// 补料单
			LogoStickers sticker = (LogoStickers) totalDao.getObjectById(
					LogoStickers.class, id);
			sticker.setStatus("已发料");
			totalDao.update(sticker);
		} else {
			if ("已发卡".equals(pro.getStatus()))
				pro.setStatus("已发料");
			if (pro.getProcardStyle().equals("组合")
					|| pro.getProcardStyle().equals("总成")) {
				// 计算剩余未领数量
				Float count = pro.getFilnalCount() - pro.getKlNumber()
						+ pro.getHascount();
				// String wghql = "update Procard set "
				// + status
				// + " hascount=("
				// + count
				// +
				// "*quanzi2/quanzi1) where fatherId=? and procardStyle='外购'  and (needProcess <> 'yes' or needProcess is null)";
				// 如果有周转卡id(即有卡操作)，则更新
				if (pro.getCardId() != null) {
					RunningWaterCard rw = (RunningWaterCard) totalDao
							.getObjectById(RunningWaterCard.class, pro
									.getCardId());
					if(rw!=null){
						rw.setReceiveStatus("yes");
						rw.setCardStatus("已发料");
						rw.setOwnUsername(pro.getLingliaoren());
						totalDao.update(rw);
					}
					
				}
			}
		}
		totalDao.update(pro);
		// 处理工序的可领数量
		// String hql_update =
		// "update ProcessInfor set totalCount=? where procard.id=?";
		// totalDao.createQueryUpdate(hql_update, null, (pro.getKlNumber() - pro
		// .getHascount()), pro.getId());
		String hql_update = "from ProcessInfor where procard.id=? and (dataStatus is null or dataStatus !='删除') order by processNO";
		List<ProcessInfor> sonProcessinfor = totalDao.query(hql_update, pro
				.getId());
		Integer maxProcessNo = -1;
		String upProcesstype="";
		String upNeedSave=null;
		boolean b= true;//半成品转库之后工序限制可领数量，半成品转库或者领取之后解开限制数量
		for (int i = 0; i < sonProcessinfor.size(); i++) {
			ProcessInfor processInfor = sonProcessinfor.get(i);
			Float selectWwCount = processInfor.getSelectWwCount()==null?0:processInfor.getSelectWwCount();//已选外委数量 
			Float applyWwCount = processInfor.getApplyWwCount()==null?0:processInfor.getApplyWwCount();//外委申请中数量
			Float agreeWwCount = processInfor.getAgreeWwCount()==null?0:processInfor.getAgreeWwCount();//外委同意数量
			Float wwbackCount = processInfor.getWwbackCount()==null?0:processInfor.getWwbackCount();//外委回来数量
			//总数量-外委没有回来数量==最大激活数量
			Float thismaxCount = pro.getFilnalCount()-(selectWwCount+applyWwCount+agreeWwCount-wwbackCount);
			if(beforettalCount>thismaxCount){
				beforettalCount = thismaxCount;
			}
			if(b){
				if((upNeedSave!=null&&upNeedSave.equals("是"))&&(processInfor.getNeedSave()==null||!processInfor.getNeedSave().equals("是"))){
					b=false;
				}
				if(upProcesstype.equals("外委")
						&&processInfor.getProductStyle()!=null&&processInfor.getProductStyle().equals("自制")){
					b=false;
				}
				if(upProcesstype.equals("外委")
						&&processInfor.getProductStyle()!=null&&processInfor.getProductStyle().equals("外委")
						&&processInfor.getProcessStatus()!=null&&processInfor.getProcessStatus().equals("no")){
					b=false;
				}
			}
			upProcesstype=processInfor.getProductStyle();
			upNeedSave = processInfor.getNeedSave();
			if (i == (sonProcessinfor.size() - 1)){
//					&& ((pro.getKlNumber() - pro.getHascount()) >= lasttotalCount)) {// 部分领料最后一道工序的可领数量为最小minNumber-
				maxProcessNo = processInfor.getProcessNO();// 最大工序号
				if(!b){
					if(pro.getFilnalCount().equals(processInfor.getTotalCount())){//第一次领
						processInfor.setTotalCount(0f);
					}
				}else{ 
					processInfor.setTotalCount((float) Math.floor(lasttotalCount));
				}
			} else {
				if(!b){
					if(pro.getFilnalCount().equals(processInfor.getTotalCount())){//第一次领
						processInfor.setTotalCount(0f);
					}
				}else{
					processInfor.setTotalCount(beforettalCount);
				}
			}
			totalDao.update(processInfor);
		}
		/********************************** 如果第一道工序就是外委，则生成外委采购计划 *****************************************/

		/**** 生成外委工序计划 ***/
		String nextWwhql = "from ProcessInfor where  procard.id=? order by processNO";
		List<ProcessInfor> nextWwProcessInforList = (List<ProcessInfor>) totalDao
				.query(nextWwhql, pro.getId());
		if (nextWwProcessInforList.size() > 0) {
			int n = 0;
			WaigouWaiweiPlan wwp = new WaigouWaiweiPlan();
			// 查看是否有委外库
			Float wwckCount = (Float) totalDao
					.getObjectByCondition("select count(*) from WareHouse where name='委外库'");
			for (ProcessInfor nextWwProcessInfor : nextWwProcessInforList) {
				if (nextWwProcessInfor != null) {
					if ("外委".equals(nextWwProcessInfor.getProductStyle())
							&& (n == 0 || ("yes").equals(nextWwProcessInfor
									.getProcessStatus()))) {
						// 第一道外委工序和之后的并行外委合并外委
						if (n == 0) {
							wwp.setRootMarkId(pro.getRootMarkId());
							wwp.setRootSelfCard(pro.getRootSelfCard());
							wwp.setOrderNum(pro.getOrderNumber());
							wwp.setYwMarkId(pro.getYwMarkId());
							wwp.setMarkId(pro.getMarkId());
							wwp.setProcessNo(nextWwProcessInfor.getProcessNO()
									+ "");
							wwp.setProcessName(nextWwProcessInfor
									.getProcessName());
							wwp.setProName(pro.getProName());
							wwp.setType("外委");
							wwp.setBeginCount(getCount);
							wwp.setNumber(getCount);
							wwp.setBanben(pro.getBanBenNumber());
							wwp.setBanci(pro.getBanci());
							wwp.setUnit(pro.getUnit());
							// if (nextWwProcessInfor.getProcessNO().equals(
							// maxProcessNo)) {
							// // 最后一道外委激活数量=下层最小minNumber - 已生成过的外委数量
							// // 当beginCount=null时为改版之前数据
							// Float wwCount1 = (Float) totalDao
							// .getObjectByCondition(
							// "select sum(number) from WaigouWaiweiPlan where processNO=? and beginCount is null and procardId=?",
							// maxProcessNo, pro.getId());
							// // 当beginCount>0时以beginCount为标准
							// Float wwCount2 = (Float) totalDao
							// .getObjectByCondition(
							// "select sum(beginCount) from WaigouWaiweiPlan where processNO=? and beginCount>0 and procardId=?",
							// maxProcessNo, pro.getId());
							// Float beginCount = lasttotalCount - wwCount1
							// - wwCount2;
							// if (beginCount <= 0) {
							// beginCount = 0f;
							// }
							// wwp.setBeginCount(beginCount);
							// wwp.setNumber(beginCount);
							// } else {
							// wwp.setBeginCount(getCount);
							// wwp.setNumber(getCount);
							// }
							wwp.setAddTime(Util.getDateTime());
							wwp.setJihuoTime(Util.getDateTime());
							wwp.setShArrivalTime(pro.getNeedFinalDate());// 应到货时间在采购确认通知后计算
							wwp.setCaigouMonth(Util.getDateTime("yyyy-MM月"));// 采购月份
							String wwNumber = "";
							String before = null;
							Integer bIndex = 10;
							before = "ww" + Util.getDateTime("yyyyMMdd");
							Integer maxNo = 0;
							String maxNumber = (String) totalDao
									.getObjectByCondition("select max(planNumber) from WaigouOrder where planNumber like '"
											+ before + "%'");
							if (maxNumber != null) {
								String wwnum = maxNumber.substring(bIndex,
										maxNumber.length());
								try {
									Integer maxNum = Integer.parseInt(wwnum);
									if (maxNum > maxNo) {
										maxNo = maxNum;
									}
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
							maxNo++;
							wwNumber = before + String.format("%03d", maxNo);
							wwp.setPlanNumber(wwNumber);// 采购计划编号
							wwp.setSelfCard(pro.getSelfCard());// 批次
//							wwp.setGysId(nextWwProcessInfor.getZhuserId());// 供应商id
//							wwp.setGysName(nextWwProcessInfor.getGys());// 供应商名称
							wwp.setAllJiepai(nextWwProcessInfor.getAllJiepai());// 单件总节拍
							wwp.setDeliveryDuration(nextWwProcessInfor
									.getDeliveryDuration());// 耽误时长
							wwp.setSingleDuration(pro.getSingleDuration());// 单班时长(工作时长)
							wwp.setProcardId(pro.getId());
							wwp.setProcard(pro);
							if (wwckCount != null && wwckCount > 0) {
								Float wwCount = pro.getFilnalCount();
								wwp.setStatus("待在制入库");
								// 在制品待入库
								if (pro.getZaizhiApplyZk() == null) {
									pro.setZaizhiApplyZk(0f);
								}
								if (pro.getZaizhizkCount() == null) {
									pro.setZaizhizkCount(0f);
								}
								if (pro.getHascount() == 0) {
									pro.setHascount(pro.getKlNumber());
								}
								// procard.getKlNumber()-procard.getHascount()=已生产数量
								// 可转库数量=已生产数量-已转库数量-转库申请中数量
								pro.setZaizhikzkCount(pro.getKlNumber()
										- pro.getHascount()
										- pro.getZaizhizkCount()
										- pro.getZaizhiApplyZk());
								if (pro.getZaizhikzkCount() >= wwCount) {
									pro.setZaizhiApplyZk(pro.getZaizhiApplyZk()
											+ wwCount);
									String orderNum = (String) totalDao
											.getObjectByCondition(
													"select orderNumber from Procard where id=?",
													pro.getRootId());
									// 成品待入库
									GoodsStore goodsStore2 = new GoodsStore();
									goodsStore2.setNeiorderId(orderNum);
									goodsStore2.setGoodsStoreMarkId(pro
											.getMarkId());
									goodsStore2.setBanBenNumber(pro
											.getBanBenNumber());
									goodsStore2.setGoodsStoreLot(pro
											.getSelfCard());
									goodsStore2.setGoodsStoreGoodsName(pro
											.getProName());
									goodsStore2
											.setApplyTime(Util.getDateTime());
									goodsStore2
											.setGoodsStoreArtsCard((String) totalDao
													.getObjectByCondition(
															"select selfCard from Procard where id=?",
															pro.getRootId()));
									goodsStore2.setGoodsStorePerson(Util
											.getLoginUser().getName());
									goodsStore2.setStatus("待入库");
									goodsStore2.setStyle("半成品转库");
									goodsStore2.setGoodsStoreWarehouse("委外库");// 库别
									goodsStore2.setNeiorderId(pro.getOrderNumber());
									goodsStore2.setWaiorderId(pro.getOutOrderNum());
									goodsStore2.setProcardId(pro.getId());
									// goodsStore2.setGoodHouseName(goodsStore.getGoodHouseName());//
									// 区名
									// goodsStore2.setGoodsStorePosition(goodsStore.getGoodsStorePosition());//
									// 库位
									goodsStore2
											.setGoodsStoreUnit(pro.getUnit());
									goodsStore2.setGoodsStoreCount(wwCount);
									totalDao.update(pro);
									totalDao.save(goodsStore2);
								} else {
									throw new RuntimeException("对不起超过可申请数量("
											+ pro.getZaizhikzkCount() + ")");
								}
							} else {
								wwp.setStatus("待激活");
							}
							totalDao.save(wwp);
						} else {
							wwp.setProcessNo(wwp.getProcessNo() + ";"
									+ nextWwProcessInfor.getProcessNO());
							wwp.setProcessName(wwp.getProcessName() + ";"
									+ nextWwProcessInfor.getProcessName());
							totalDao.update(wwp);
						}
					} else {
						break;
					}
				} else {
					break;
				}
				n++;
			}
			if (wwp.getId() != null) {
				// 匹配供应商
				Price price = (Price) totalDao
						.getObjectByCondition(
								"from Price where wwType='工序外委' and partNumber=? and gongxunum=? and (pricePeriodEnd is null or pricePeriodEnd ='' or pricePeriodEnd>='"
										+ Util.getDateTime()
										+ "')  order by hsPrice", wwp
										.getMarkId(), wwp.getProcessNo());
				if (price != null) {
					wwp.setPriceId(price.getId());
					wwp.setGysId(price.getGysId());
					ZhUser zhUser = (ZhUser) totalDao.getObjectById(
							ZhUser.class, price.getGysId());
					wwp.setGysName(zhUser.getName());
					wwp.setUserCode(zhUser.getUsercode());
					wwp.setUserId(zhUser.getUserid());
					totalDao.update(wwp);
				}
			}
		}
		for (Object obj : listSell) {
			Sell sell = (Sell) obj;
			goodsStoreServer.goodshuizong0("出库", null, sell);
		}
		return listSell;
	}

	/**
	 * 外购件领料(原下层最小hascount同步上层hasCount废弃)
	 */
	// public List saveSellList2(Integer id, Procard pro, List<Goods> li,
	// String tag, float getCount,
	// String ckType, int[] selected,List<Goods> goodsList) {
	// Users user = (Users) Util.getLoginUser();
	// String hql1 =
	// "from Users where cardId=? and onWork not in('离职','离职中','内退','病休')";
	// Users us = (Users) totalDao.getObjectByCondition(hql1, pro
	// .getLingliaoren());// 领料人
	// if(us==null){
	// throw new RuntimeException("领料人不存在,领料失败!");
	// }else{
	// Float czcount = (Float) totalDao
	// .getObjectByCondition(
	// "select count(id) from ProcessinforPeople where procard.id=? and userId =?",
	// pro.getId(), us.getId());
	// if (czcount == null||czcount==0) {
	// throw new RuntimeException("领料人对此零件无领料权限,领料失败!");
	// }
	// }
	// String totalMarkId =
	// (String)totalDao.getObjectByCondition("select markId from Procard where id=?",pro.getRootId());
	// float zaizhiCount = 0f;
	// float deletehasCount = 0f;
	// float ydeltehasCount = 0f;// 组合领原材料时有成品在制品减去对应的hascount
	// if (!pro.getProcardStyle().equals("总成")) {// 总成无在制品
	// zaizhiCount = getZaiZhiCount(pro.getId());// 计算可用在制品
	// // zaizhiCount = 0f;// 计算可用在制品
	// }
	// float addZaihi = 0f;
	// if (zaizhiCount >= 0 ) {
	// if (zaizhiCount >= getCount ) {// 在制品够
	// deletehasCount = getCount;
	// } else {
	// deletehasCount = zaizhiCount ;
	// addZaihi = getCount - zaizhiCount;
	// boolean isaddZaizhi = true;
	// if (addZaihi > 0 ) {
	// // 添加在制品
	// String hqlzaizhi = "from Goods where goodsMarkId='"
	// + pro.getMarkId()
	// + "' and goodsLotId='"
	// + pro.getSelfCard()
	// +
	// "' and goodsClass='在制品'  and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
	// List listzizhi = totalDao.find(hqlzaizhi);
	// Integer rgoodsId=null;
	// if (listzizhi != null && listzizhi.size() > 0) {
	// Goods g1 = (Goods) listzizhi.get(0);
	// g1.setGoodsCurQuantity(g1.getGoodsCurQuantity()
	// + (float) Math.floor(addZaihi));
	// if (g1.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + g1.getGoodsMarkId() + "批次:"
	// + g1.getGoodsLotId()
	// +
	// "在制品数量小于零，系统自动修复为0，操作是："+pro.getMarkId()+"第"+pro.getSelfCard()+"批次领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// g1.setGoodsCurQuantity(0f);
	// }
	// totalDao.update(g1);
	// rgoodsId=g1.getGoodsId();
	// } else {
	// Goods gg = new Goods();
	// gg.setGoodsMarkId(pro.getMarkId());
	// gg.setGoodsLotId(pro.getSelfCard());
	// gg.setGoodsFullName(pro.getProName());
	// gg.setGoodsClass("在制品");
	// gg.setGoodsCurQuantity((float) Math.floor(addZaihi));
	// gg.setGoodsChangeTime(Util.getDateTime());
	// if (gg.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + gg.getGoodsMarkId() + "批次:"
	// + gg.getGoodsLotId()
	// + "在制品数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// gg.setGoodsCurQuantity(0f);
	// }
	// gg.setGoodsUnit(pro.getUnit());
	// gg.setWgType(pro.getWgType());
	// totalDao.save(gg);
	// rgoodsId=gg.getGoodsId();
	// }
	// //添加零件与在制品关系表
	// ProcardProductRelation pprelation=new ProcardProductRelation();
	// pprelation.setAddTime(Util.getDateTime());
	// pprelation.setProcardId(pro.getId());
	// pprelation.setGoodsId(rgoodsId);
	// pprelation.setZyCount((float) Math.floor(addZaihi));
	// pprelation.setFlagType("本批在制品");
	// totalDao.save(pprelation);
	// // 添加在制品入库记录
	// GoodsStore gs = new GoodsStore();
	// gs.setGoodsStoreMarkId(pro.getMarkId());// 件号
	// gs.setBanBenNumber(pro.getBanBenNumber());
	// gs.setGoodsStoreGoodsName(pro.getProName());// 名称
	// gs.setGoodsStoreLot(pro.getSelfCard());// 批次
	// gs.setGoodsStoreCount((float) Math.floor(addZaihi));// 数量
	// gs.setPrintStatus("YES");
	// gs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
	// gs.setGoodsStoreWarehouse("在制品");// 库别
	// Users jingban = Util.getLoginUser();
	// gs.setGoodsStoreCharger(jingban.getName());// 经办人
	// gs.setStyle("正常（成品）");// 入库类型
	// gs.setGoodsStorePerson(us.getName());
	// gs.setGoodsStoreDate(DateUtil.formatDate(new Date(),
	// "yyyy-MM-dd"));
	// gs.setGoodsStoreUnit(pro.getUnit());// 单位
	// gs.setWgType(pro.getWgType());
	// gs.setGoodsStoreTime(Util.getDateTime());
	// totalDao.save(gs);
	//					
	// }
	// }
	// } else {
	// throw new RuntimeException("对不起,之前批次的在制品不足!");
	// }
	// String lingliaoPeople = pro.getLingliaoren();
	// pro = (Procard) totalDao.getObjectById(Procard.class, pro.getId());
	// pro.setLingliaoren(lingliaoPeople);
	// if (pro.getHascount() == null) {
	// pro.setHascount(pro.getKlNumber());
	// }
	// pro.setHascount(pro.getHascount() - getCount);
	// if (pro.getHascount() < 0) {
	// throw new RuntimeException("对不起,领取超额!");
	// }
	//		
	// List listSell = new ArrayList();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	// List<Procard> wgprocardSonList = totalDao.query(
	// "from Procard where procard.id=? and procardStyle='外购'", pro
	// .getId());
	// List<Procard> zaizhiSonLIst = totalDao.query(
	// "from Procard where procard.id=? and ((zaizhizkCount is null and zaizhizkCount>0) or (zaizhiApplyZk is null and zaizhiApplyZk>0))",
	// pro
	// .getId());
	// float lasttotalCount = pro.getFilnalCount();// 最后一道工序的可领数量
	// for (Goods g : li) {
	// Float qingling =0f;
	// Float shifa =0f;
	// // if (g.getGoodsPosition() != null
	// // && g.getGoodsPosition().equals("在制品")) {
	// // continue;
	// // }
	// if(!g.getShowType().equals("在制品充领")){
	// if (selected != null && selected.length > 0) {
	// boolean b = false;
	// for (int goodsId : selected) {
	// if (g.getGoodsId().equals(goodsId)) {
	// if(goodsList!=null&&goodsList.size()>0){
	// for(Goods qlGoods:goodsList){
	// if(qlGoods.getGoodsId().equals(goodsId)){
	// qingling = qlGoods.getGoodsZhishu();
	// shifa = qlGoods.getGoodsCurQuantity();
	// }
	// }
	// }
	// b = true;
	// break;
	// }
	// }
	// if (!b) {// 此外购件没有被选中
	// continue;
	// }
	// } else {
	// throw new RuntimeException("请至少选中一个");
	// }
	// }
	// // if(g.getGoodsBeginQuantity()<=0){??忘记干嘛用的了
	// // continue;
	// // }
	// Goods goodsData = (Goods) totalDao.getObjectById(Goods.class, g
	// .getGoodsId());
	// Sell sell = new Sell();
	// sell.setSellArtsCard(pro.getSelfCard());
	// sell.setSellSupplier(g.getGoodsSupplier());
	// sell.setSellFormat(g.getGoodsFormat());
	// sell.setSellLot(g.getGoodsLotId());
	// sell.setSellMarkId(g.getGoodsMarkId());
	// sell.setSellAdminName(user.getName());
	// sell.setSellGoods(g.getGoodsFullName());
	// sell.setSellDate(sdf2.format(new Date()));
	// sell.setSellTime(sdf.format(new Date()));
	// sell.setSellWarehouse(g.getGoodsClass());
	// sell.setPrintStatus("NO");
	// if(g.getShowType().equals("在制品充领")){
	// Float needzzCount = g.getGoodsCurQuantity();
	// List<ProcardProductRelation> relationList =
	// totalDao.query("from ProcardProductRelation where zyCount>ylCount and flagType='余额在制品' and procardId=?",
	// pro.getId());
	// if(relationList!=null&&relationList.size()>0){
	// for(ProcardProductRelation relation:relationList){
	// Goods zzGoods= (Goods) totalDao.getObjectById(Goods.class,
	// relation.getGoodsId() );
	// if(zzGoods==null||zzGoods.getGoodsCurQuantity()<=0){
	// continue;
	// }
	// Float chuku=0f;
	// if(needzzCount<=zzGoods.getGoodsCurQuantity()){
	// zzGoods.setGoodsCurQuantity(zzGoods.getGoodsCurQuantity()-needzzCount);
	// chuku=needzzCount;
	// needzzCount=0f;
	// }else{
	// needzzCount -=zzGoods.getGoodsCurQuantity();
	// chuku=zzGoods.getGoodsCurQuantity();
	// zzGoods.setGoodsCurQuantity(0f);
	// }
	// relation.setYlCount(relation.getYlCount()+chuku);
	// //在制品出库记录
	// Sell zzpSell = new Sell();
	// zzpSell.setSellArtsCard(pro.getSelfCard());
	// zzpSell.setSellSupplier(zzGoods.getGoodsSupplier());
	// zzpSell.setSellFormat(zzGoods
	// .getGoodsFormat());
	// zzpSell.setSellLot(zzGoods
	// .getGoodsLotId());
	// zzpSell.setSellMarkId(zzGoods
	// .getGoodsMarkId());
	// zzpSell.setSellAdminName(user.getName());
	// zzpSell.setSellGoods(zzGoods
	// .getGoodsFullName());
	// zzpSell.setSellDate(Util
	// .getDateTime("yyyy-MM-dd"));
	// zzpSell.setSellTime(Util.getDateTime());
	// zzpSell.setSellWarehouse(zzGoods
	// .getGoodsClass());
	// zzpSell.setSellUnit(zzGoods
	// .getGoodsUnit());
	// zzpSell.setSellCount(chuku);
	// zzpSell.setWgType(zzGoods.getWgType());
	// zzpSell.setPrintStatus("YES");
	// totalDao.save(zzpSell);
	// totalDao.update(zzGoods);
	// if(needzzCount==0){
	// break;
	// }
	// }
	// }
	// if(needzzCount>0){
	// throw new RuntimeException("对不起没有足够的在制品提供出库!");
	// }
	// continue;
	// // for (Procard swgon : wgprocardSonList) {
	// // if (!swgon.getMarkId().equals(pro.getMarkId())) {//下层减去对应的hasCOunt
	// // if(swgon.getHascount()==null){
	// // swgon.setHascount(swgon.getKlNumber());
	// // }
	// //
	// swgon.setHascount(swgon.getHascount()-g.getGoodsCurQuantity()*swgon.getQuanzi2()/swgon.getQuanzi1());
	// // if(swgon.getHascount()==0){
	// // swgon.setStatus("完成");
	// // }
	// // totalDao.update(swgon);
	// // }
	// // }
	// }else if(g.getShowType().equals("余料")){
	// if(g.getGoodsCurQuantity()>goodsData.getGoodsCurQuantity()){
	// throw new RuntimeException("对不起余料抵发数量不足,领料失败!");
	// }
	// if (wgprocardSonList != null && wgprocardSonList.size() > 0) {
	// for (Procard swgon : wgprocardSonList) {
	// if (swgon.getMarkId().equals(
	// goodsData.getGoodsMarkId())) {
	// Float thisAddZaizhi=0f;
	// if(swgon.getHascount()==null){
	// swgon.setHascount(swgon.getKlNumber());
	// }
	// if(swgon.getUnit().equals(goodsData.getGoodsUnit())){//单位一致
	// swgon.setHascount(swgon.getHascount()-g.getGoodsBeginQuantity());
	// thisAddZaizhi=g.getGoodsBeginQuantity();
	// if(swgon.getHascount()==0){
	// swgon.setStatus("完成");
	// }
	// }else{
	// //单零件重量（一般为原材料使用）
	// String banbenSql = null;
	// if(swgon.getBanBenNumber()==null||swgon.getBanBenNumber().length()==0){
	// banbenSql = " and (banBenNumber is null or banBenNumber ='')";
	// }else{
	// banbenSql = " and banBenNumber ='"+swgon.getBanBenNumber()+"'";
	// }
	// Float bizhong= (Float) totalDao
	// .getObjectByCondition(
	// "select bili from YuanclAndWaigj where markId =? ",
	// swgon.getMarkId());
	// Goods googsTemp=
	// (Goods)totalDao.getObjectByCondition("from Goods where kgliao=? and goodsCurQuantity>0 and  goodsZhishu>0 and goodsMarkId=?"+banbenSql,
	// swgon.getKgliao(), swgon.getMarkId());
	// if(bizhong==null){
	// if(googsTemp!=null){
	// bizhong = googsTemp.getGoodsCurQuantity()/googsTemp.getGoodsZhishu();
	// }
	// }
	// swgon.setHascount(swgon.getHascount()-Math.round(g.getGoodsBeginQuantity()/bizhong));
	// thisAddZaizhi=(float)Math.round(g.getGoodsBeginQuantity()/bizhong);
	// if(swgon.getHascount()==0){
	// swgon.setStatus("完成");
	// }
	// totalDao.update(swgon);
	// }
	// // 添加外购件在制品数量
	// String hqlzaizhi = "from Goods where goodsMarkId='"
	// + g.getGoodsMarkId()
	// + "' and goodsLotId='"
	// + swgon.getSelfCard()
	// +
	// "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
	// List listzizhi = totalDao.find(hqlzaizhi);
	// Integer rgoodsId=null;
	// if (listzizhi != null && listzizhi.size() > 0) {
	// Goods g1 = (Goods) listzizhi.get(0);
	// g1.setGoodsCurQuantity(g1.getGoodsCurQuantity()
	// + thisAddZaizhi);
	// if (g1.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + g1.getGoodsMarkId() + "批次:"
	// + g1.getGoodsLotId()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// g1.setGoodsCurQuantity(0f);
	// }
	// totalDao.update(g1);
	// rgoodsId=g1.getGoodsId();
	// } else {
	// Goods gg = new Goods();
	// gg.setGoodsMarkId(g.getGoodsMarkId());
	// gg.setBanBenNumber(swgon.getBanBenNumber());
	// gg.setGoodsFormat(swgon.getSpecification());
	// gg.setTuhao(swgon.getTuhao());
	// gg.setGoodsLotId(swgon.getSelfCard());
	// gg.setGoodsFullName(goodsData.getGoodsFullName());
	// gg.setGoodsClass("在制品");
	// gg.setGoodsCurQuantity(thisAddZaizhi);
	// gg.setGoodsBeginQuantity(thisAddZaizhi);
	// gg.setGoodsChangeTime(Util.getDateTime());
	// if (gg.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + gg.getGoodsMarkId() + "批次:"
	// + gg.getGoodsLotId()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// gg.setGoodsCurQuantity(0f);
	// }
	// gg.setGoodsUnit(swgon.getUnit());
	// totalDao.save(gg);
	// rgoodsId=gg.getGoodsId();
	// }
	// //添加零件与在制品关系表
	// ProcardProductRelation pprelation=new ProcardProductRelation();
	// pprelation.setAddTime(Util.getDateTime());
	// pprelation.setProcardId(swgon.getId());
	// pprelation.setGoodsId(rgoodsId);
	// pprelation.setZyCount(thisAddZaizhi);
	// pprelation.setFlagType("本批在制品");
	// totalDao.save(pprelation);
	// // 添加外购件在制品入库记录
	// GoodsStore gs = new GoodsStore();
	// gs.setGoodsStoreMarkId(g.getGoodsMarkId());// 件号
	// gs.setGoodsStoreFormat(swgon.getSpecification());
	// gs.setTuhao(swgon.getTuhao());
	// gs.setGoodsStoreGoodsName(g.getGoodsFullName());// 名称
	// gs.setGoodsStoreLot(pro.getSelfCard());// 批次
	// gs.setGoodsStoreCount(thisAddZaizhi);// 数量
	// gs.setPrintStatus("YES");
	// gs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
	// gs.setGoodsStoreWarehouse("在制品");// 库别
	// Users jingban = Util.getLoginUser();
	// gs.setGoodsStoreCharger(jingban.getName());// 经办人
	// gs.setStyle("正常（成品）");// 入库类型
	// if (us != null) {// 负责人
	// gs.setGoodsStorePerson(us.getName());
	// } else {
	// gs.setGoodsStorePerson(swgon.getLingliaoren());
	// }
	// gs.setGoodsStoreDate(DateUtil.formatDate(new Date(),
	// "yyyy-MM-dd"));
	// gs.setGoodsStoreTime(Util.getDateTime());
	// gs.setGoodsStoreUnit(swgon.getUnit());// 单位
	// totalDao.save(gs);
	// totalDao.update(goodsData);
	// }
	// goodsData.setGoodsCurQuantity(goodsData.getGoodsCurQuantity()
	// - g.getGoodsCurQuantity());
	// goodsData.setGoodsZhishu(0f);
	// totalDao.update(goodsData);
	// // 余料出库
	// Sell sellYl = new Sell();
	// sellYl.setSellArtsCard(pro.getSelfCard());
	// sellYl.setSellSupplier(goodsData.getGoodsSupplier());
	// sellYl.setSellFormat(goodsData.getGoodsFormat());
	// sellYl.setSellLot(goodsData.getGoodsLotId());
	// sellYl.setSellMarkId(goodsData.getGoodsMarkId());
	// sellYl.setSellAdminName(user.getName());
	// sellYl.setSellGoods(goodsData.getGoodsFullName());
	// sellYl.setSellDate(sdf2.format(new Date()));
	// sellYl.setSellTime(sdf.format(new Date()));
	// sellYl.setSellWarehouse(goodsData.getGoodsClass());
	// sellYl.setSellUnit(goodsData.getGoodsUnit());
	// sellYl.setSellCount(g.getGoodsCurQuantity());
	// sellYl.setKgliao(goodsData.getKgliao());
	// totalDao.save(sellYl);
	// if(g.getGoodsCurQuantity()>g.getGoodsBeginQuantity()){//余料实发大于请领多余在做入库
	// Goods ylGoods = new Goods();
	// ylGoods.setGoodsClass("余料库");
	// ylGoods.setGoodsBeginQuantity(g.getGoodsCurQuantity()-g.getGoodsBeginQuantity());
	// ylGoods.setGoodsCurQuantity(g.getGoodsCurQuantity()-g.getGoodsBeginQuantity());
	// ylGoods.setGoodsZhishu(1f);
	// ylGoods.setYlMarkId(pro.getMarkId());
	// ylGoods
	// .setYlSelfCard(pro
	// .getSelfCard());
	// ylGoods.setGoodsMarkId(goodsData.getGoodsMarkId());
	// ylGoods.setGoodsFormat(goodsData.getGoodsFormat());
	// ylGoods.setGoodsUnit(g.getGoodsUnit());
	// ylGoods.setGoodsChangeTime(Util
	// .getDateTime("yyyy-MM-dd"));
	// ylGoods.setYllock(goodsData.getYllock());
	// ylGoods.setKgliao(goodsData.getKgliao());
	// // ylGoods.set
	// ylGoods.setWgType(goodsData.getWgType());
	// totalDao.save(ylGoods);
	// //余料产品中间表
	// ProcardProductRelation ppr = new ProcardProductRelation();
	// ppr.setAddTime(Util.getDateTime());
	// ppr.setProcardId(swgon.getId());
	// ppr.setGoodsId(ylGoods.getGoodsId());
	// ppr.setZyCount(ylGoods.getGoodsCurQuantity());
	// ppr.setFlagType("余料");
	// totalDao.save(ppr);
	// // 余料入库记录
	// GoodsStore ylgs = new GoodsStore();
	// ylgs.setGoodsStoreMarkId(ylGoods
	// .getGoodsMarkId());// 件号
	// ylgs.setGoodsStoreFormat(ylGoods
	// .getGoodsFormat());// 规格
	// ylgs.setGoodsStoreGoodsName(pro
	// .getProName());// 名称
	// ylgs.setGoodsStoreLot(pro.getSelfCard());// 批次
	// ylgs.setGoodsStoreCount(g.getGoodsCurQuantity()-g.getGoodsBeginQuantity());//
	// 数量
	// ylgs.setPrintStatus("YES");
	// ylgs.setWgType(g.getWgType());
	// ylgs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
	// ylgs.setGoodsStoreWarehouse("余料库");// 库别
	// ylgs.setKgliao(ylGoods.getKgliao());
	// Users jingban = Util.getLoginUser();
	// ylgs
	// .setGoodsStoreCharger(jingban
	// .getName());// 经办人
	// ylgs.setStyle("余料");// 入库类型
	// if (us != null) {// 负责人
	// ylgs.setGoodsStorePerson(us.getName());
	// } else {
	// ylgs.setGoodsStorePerson(pro
	// .getLingliaoren());
	// }
	// ylgs.setGoodsStoreDate(DateUtil.formatDate(
	// new Date(), "yyyy-MM-dd"));
	// ylgs.setGoodsStoreUnit(pro.getUnit());// 单位
	// totalDao.save(ylgs);
	// }
	// }
	// continue;
	// }
	// }else if(g.getShowType().equals("中转在制品")){
	// if(zaizhiSonLIst!=null&&zaizhiSonLIst.size()>0){
	// for(Procard zaizhisong:zaizhiSonLIst){
	// if (!zaizhisong.getMarkId().equals(
	// goodsData.getGoodsMarkId())) {
	// continue;
	// }
	// if(zaizhisong.getZaizhizkCount()<g.getGoodsCurQuantity()){
	// throw new RuntimeException("对不起，"+zaizhisong+"的在库在制品领取超额!");
	// }
	// zaizhisong.setZaizhizkCount(zaizhisong.getZaizhizkCount()-g.getGoodsCurQuantity());
	// totalDao.update(zaizhisong);
	// }
	// }
	// }else{//外购件
	// sell.setWgType(g.getWgType());
	// if (wgprocardSonList != null && wgprocardSonList.size() > 0) {
	// for (Procard swgon : wgprocardSonList) {
	// if (!swgon.getMarkId().equals(
	// goodsData.getGoodsMarkId())) {
	// continue;
	// }
	// if (g.getGoodsMarkId().equals(pro.getMarkId())) {
	// // 与领料件号相同，说明是半成品领料，在制品已在之前处理。
	// // 处理材料批次
	// String lingliaoDetail = pro.getLingliaoDetail();
	// if (lingliaoDetail == null) {
	// lingliaoDetail = goodsData.getGoodsLotId() + ":"
	// + g.getGoodsCurQuantity();
	// } else {
	// lingliaoDetail += "," + goodsData.getGoodsLotId() + ":"
	// + g.getGoodsCurQuantity();
	// }
	// pro.setLingliaoDetail(lingliaoDetail);
	// } else {
	// //单零件重量（一般为原材料使用）
	// Float bizhong= (Float) totalDao
	// .getObjectByCondition(
	// "select bili from YuanclAndWaigj where markId =? ",
	// g.getGoodsMarkId());
	// // 更新外购件hasCount;
	// if (swgon.getHascount() == null) {
	// swgon.setHascount(swgon.getKlNumber());
	// }
	// //对应当前上层已领数量下层应该需要领的数量
	// Float duiyinCount = swgon.getHascount() -
	// pro.getHascount()*swgon.getQuanzi2()/swgon.getQuanzi1();
	// if(duiyinCount<0){
	// throw new
	// RuntimeException("对不起数据异常，不能理解为什么要领"+swgon.getMarkId()+shifa+g.getGoodsUnit());
	// }
	// //外购件的总需求量（此时上层hasCount已更新到最新数量）
	// Float thisHasCount =
	// swgon.getHascount()-pro.getHascount()*swgon.getQuanzi2()/swgon.getQuanzi1();
	// //领料与外购件的直接关联数量
	// Float bijiaoCount = qingling;
	// if(swgon.getUnit().equals(goodsData.getGoodsUnit())){//单位一致对应实发
	// bijiaoCount = shifa;
	// }else{//否则对应请领
	// }
	// if(thisHasCount<bijiaoCount){//实际领的比本外购件的总需求量要多
	// if(bizhong==null||bizhong==0){//没有比重明显数据有问题
	// // 发送异常消息bgg
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + swgon.getMarkId() + "批次:"
	// + swgon.getSelfCard()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// swgon.setHascount(0f);
	// swgon.setStatus("完成");
	// totalDao.update(swgon);
	// }else{//多余为余料,添加余料
	// swgon.setHascount(swgon.getHascount()-thisHasCount);
	// if(swgon.getHascount()==0){
	// swgon.setStatus("完成");
	// }
	// totalDao.update(swgon);
	// String yllock = "no";
	// if (pro.getJgyl() != null
	// && pro.getJgyl().equals("yes")) {
	// yllock = "yes";
	// }
	// Goods ylGoods = new Goods();
	// ylGoods.setGoodsClass("余料库");
	// ylGoods.setGoodsBeginQuantity(bijiaoCount-thisHasCount);
	// ylGoods.setGoodsCurQuantity(bijiaoCount-thisHasCount);
	// ylGoods.setGoodsZhishu(1f);
	// ylGoods.setYlMarkId(pro.getMarkId());
	// ylGoods
	// .setYlSelfCard(pro
	// .getSelfCard());
	// ylGoods.setGoodsMarkId(swgon.getMarkId());
	// ylGoods.setGoodsFormat(swgon.getSpecification());
	// ylGoods.setGoodsUnit(g.getGoodsUnit());
	// ylGoods.setGoodsChangeTime(Util
	// .getDateTime("yyyy-MM-dd"));
	// ylGoods.setYllock(yllock);
	// // ylGoods.set
	// ylGoods.setWgType(goodsData.getWgType());
	// ylGoods.setKgliao(goodsData.getKgliao());
	// totalDao.save(ylGoods);
	// // 余料入库记录
	// GoodsStore ylgs = new GoodsStore();
	// ylgs.setGoodsStoreMarkId(ylGoods
	// .getGoodsMarkId());// 件号
	// ylgs.setGoodsStoreFormat(ylGoods
	// .getGoodsFormat());// 规格
	// ylgs.setGoodsStoreGoodsName(pro
	// .getProName());// 名称
	// ylgs.setGoodsStoreLot(pro.getSelfCard());// 批次
	// ylgs.setGoodsStoreCount(bijiaoCount-thisHasCount);// 数量
	// ylgs.setPrintStatus("YES");
	// ylgs.setWgType(swgon.getWgType());
	// ylgs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
	// ylgs.setGoodsStoreWarehouse("余料库");// 库别
	// ylgs.setKgliao(ylGoods.getKgliao());
	// Users jingban = Util.getLoginUser();
	// ylgs
	// .setGoodsStoreCharger(jingban
	// .getName());// 经办人
	// ylgs.setStyle("余料");// 入库类型
	// if (us != null) {// 负责人
	// ylgs.setGoodsStorePerson(us.getName());
	// } else {
	// ylgs.setGoodsStorePerson(pro
	// .getLingliaoren());
	// }
	// ylgs.setGoodsStoreDate(DateUtil.formatDate(
	// new Date(), "yyyy-MM-dd"));
	// ylgs.setGoodsStoreUnit(swgon.getUnit());// 单位
	// totalDao.save(ylgs);
	// }
	// }else{
	// swgon.setHascount(swgon.getHascount()-bijiaoCount);
	// }
	// if (swgon.getHascount() == 0) {
	// swgon.setStatus("完成");
	// }
	// totalDao.update(swgon);
	// //处理材料批次
	// String lingliaoDetail = swgon.getLingliaoDetail();
	// if (lingliaoDetail == null) {
	// lingliaoDetail = goodsData.getGoodsLotId()
	// + ":" + g.getGoodsCurQuantity();
	// } else {
	// lingliaoDetail += ","
	// + goodsData.getGoodsLotId() + ":"
	// + g.getGoodsCurQuantity();
	// }
	// swgon.setLingliaoDetail(lingliaoDetail);
	// totalDao.update(swgon);
	// // 添加外购件在制品数量
	// String hqlzaizhi = "from Goods where goodsMarkId='"
	// + g.getGoodsMarkId()
	// + "' and goodsLotId='"
	// + pro.getSelfCard()
	// +
	// "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
	// List listzizhi = totalDao.find(hqlzaizhi);
	// Integer rgoodsId=null;
	// if (listzizhi != null && listzizhi.size() > 0) {
	// Goods g1 = (Goods) listzizhi.get(0);
	// g1.setGoodsCurQuantity(g1.getGoodsCurQuantity()
	// + thisHasCount);
	// if (g1.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + g1.getGoodsMarkId() + "批次:"
	// + g1.getGoodsLotId()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// g1.setGoodsCurQuantity(0f);
	// }
	// totalDao.update(g1);
	// rgoodsId = g1.getGoodsId();
	// } else {
	// Goods gg = new Goods();
	// gg.setGoodsMarkId(g.getGoodsMarkId());
	// gg.setBanBenNumber(swgon.getBanBenNumber());
	// gg.setGoodsFormat(swgon.getSpecification());
	// gg.setTuhao(swgon.getTuhao());
	// gg.setGoodsLotId(swgon.getSelfCard());
	// gg.setGoodsFullName(goodsData.getGoodsFullName());
	// gg.setGoodsClass("在制品");
	// gg.setGoodsCurQuantity(thisHasCount);
	// gg.setGoodsBeginQuantity(thisHasCount);
	// if (gg.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组",
	// "件号:" + gg.getGoodsMarkId() + "批次:"
	// + gg.getGoodsLotId()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// gg.setGoodsCurQuantity(0f);
	// }
	// gg.setGoodsUnit(pro.getUnit());
	// totalDao.save(gg);
	// rgoodsId = gg.getGoodsId();
	//								
	// }
	// //添加零件与在制品关系表
	// ProcardProductRelation pprelation=new ProcardProductRelation();
	// pprelation.setProcardId(swgon.getId());
	// pprelation.setGoodsId(rgoodsId);
	// pprelation.setZyCount(thisHasCount);
	// pprelation.setFlagType("本批在制品");
	// GoodsStore gs = new GoodsStore();// 添加外购件在制品入库记录
	// gs.setGoodsStoreMarkId(g.getGoodsMarkId());// 件号
	// gs.setGoodsStoreFormat(swgon.getSpecification());
	// gs.setTuhao(swgon.getTuhao());
	// gs.setBanBenNumber(swgon.getBanBenNumber());
	// gs.setGoodsStoreGoodsName(g.getGoodsFullName());// 名称
	// gs.setGoodsStoreLot(swgon.getSelfCard());// 批次
	// // gs.setGoodsStoreCount((float) Math.floor(g
	// // .getGoodsCurQuantity()));// 数量
	// gs.setGoodsStoreCount(thisHasCount);// 数量
	// gs.setPrintStatus("YES");
	// gs.setGoodsStoreProMarkId(totalMarkId);// 总成件号
	// gs.setGoodsStoreWarehouse("在制品");// 库别
	// Users jingban = Util.getLoginUser();
	// gs.setGoodsStoreCharger(jingban.getName());// 经办人
	// gs.setStyle("正常（成品）");// 入库类型
	// if (us != null) {// 负责人
	// gs.setGoodsStorePerson(us.getName());
	// } else {
	// gs.setGoodsStorePerson(swgon.getLingliaoren());
	// }
	// gs.setGoodsStoreDate(DateUtil.formatDate(new Date(),
	// "yyyy-MM-dd"));
	// gs.setGoodsStoreUnit(swgon.getUnit());// 单位
	// totalDao.save(gs);
	// totalDao.update(goodsData);
	// }
	// }
	// for (Procard procard : wgprocardSonList) {
	// if (procard.getHascount() == null) {
	// procard.setHascount(procard.getKlNumber());
	// }
	// float totalHasCount2 = (procard.getFilnalCount() - procard
	// .getHascount())
	// * procard.getQuanzi1() / procard.getQuanzi2();
	// if (totalHasCount2 < lasttotalCount) {
	// lasttotalCount = totalHasCount2;
	// }
	// totalDao.update(procard);
	// }
	// }
	// }
	// sell.setSellCount(g.getGoodsCurQuantity());
	// if(goodsData.getGoodsZhishu()!=null&&goodsData.getGoodsZhishu()>0){
	// goodsData.setGoodsZhishu(goodsData.getGoodsZhishu()-qingling);
	// sell.setSellZhishu(qingling);
	// }
	// goodsData.setGoodsCurQuantity(goodsData.getGoodsCurQuantity()
	// - shifa);
	// if (goodsData.getGoodsCurQuantity() < 0) {
	// AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
	// + goodsData.getGoodsMarkId() + "批次:"
	// + goodsData.getGoodsLotId()
	// + "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
	// + Util.getDateTime(), "2");
	// goodsData.setGoodsCurQuantity(0f);
	// }
	// if (null == sell.getSellFormat()) {
	// sell.setSellFormat("");
	// }
	// totalDao.save(sell);
	// listSell.add(sell);
	// }
	//		
	// if ("barcode".equals(tag)) {// 补料单
	// LogoStickers sticker = (LogoStickers) totalDao.getObjectById(
	// LogoStickers.class, id);
	// sticker.setStatus("已发料");
	// totalDao.update(sticker);
	// } else {
	// if ("已发卡".equals(pro.getStatus()))
	// pro.setStatus("已发料");
	// if (pro.getProcardStyle().equals("组合")
	// || pro.getProcardStyle().equals("总成")) {
	// // 计算剩余未领数量
	// Float count = pro.getFilnalCount() - pro.getKlNumber()
	// + pro.getHascount();
	// // String wghql = "update Procard set "
	// // + status
	// // + " hascount=("
	// // + count
	// // +
	// //
	// "*quanzi2/quanzi1) where fatherId=? and procardStyle='外购'  and (needProcess <> 'yes' or needProcess is null)";
	// // 如果有周转卡id(即有卡操作)，则更新
	// if (pro.getCardId() != null) {
	// RunningWaterCard rw = (RunningWaterCard) totalDao
	// .getObjectById(RunningWaterCard.class, pro
	// .getCardId());
	// rw.setReceiveStatus("yes");
	// rw.setCardStatus("已发料");
	// rw.setOwnUsername(pro.getLingliaoren());
	// totalDao.update(rw);
	// }
	// }
	// }
	// totalDao.update(pro);
	// // 处理工序的可领数量
	// // String hql_update =
	// // "update ProcessInfor set totalCount=? where procard.id=?";
	// // totalDao.createQueryUpdate(hql_update, null, (pro.getKlNumber() - pro
	// // .getHascount()), pro.getId());
	// String hql_update =
	// "from ProcessInfor where procard.id=? order by processNO";
	// List<ProcessInfor> sonProcessinfor = totalDao.query(hql_update, pro
	// .getId());
	// Integer maxProcessNo = -1;
	// for (int i = 0; i < sonProcessinfor.size(); i++) {
	// ProcessInfor processInfor = sonProcessinfor.get(i);
	// if (i == (sonProcessinfor.size() - 1)
	// && ((pro.getKlNumber() - pro.getHascount()) > lasttotalCount)) {//
	// 部分领料最后一道工序的可领数量为最小minNumber-
	// processInfor.setTotalCount((float) Math.floor(lasttotalCount));
	// maxProcessNo = processInfor.getProcessNO();// 最大工序号
	// } else {
	// processInfor.setTotalCount(pro.getKlNumber()
	// - pro.getHascount());
	// }
	// totalDao.update(processInfor);
	// }
	// /********************************** 如果第一道工序就是外委，则生成外委采购计划
	// *****************************************/
	//		
	// /**** 生成外委工序计划 ***/
	// String nextWwhql =
	// "from ProcessInfor where  procard.id=? order by processNO";
	// List<ProcessInfor> nextWwProcessInforList = (List<ProcessInfor>) totalDao
	// .query(nextWwhql, pro.getId());
	// if (nextWwProcessInforList.size() > 0) {
	// int n=0;
	// WaigouWaiweiPlan wwp = new WaigouWaiweiPlan();
	// //查看是否有委外库
	// Float wwckCount = (Float)
	// totalDao.getObjectByCondition("select count(*) from WareHouse where name='委外库'");
	// for (ProcessInfor nextWwProcessInfor : nextWwProcessInforList) {
	// if (nextWwProcessInfor != null) {
	// if
	// ("外委".equals(nextWwProcessInfor.getProductStyle())&&(n==0||("yes").equals(nextWwProcessInfor.getProcessStatus())))
	// {
	// //第一道外委工序和之后的并行外委合并外委
	// if(n==0){
	// wwp.setRootMarkId(pro.getRootMarkId());
	// wwp.setRootSelfCard(pro.getRootSelfCard());
	// wwp.setOrderNum(pro.getOrderNumber());
	// wwp.setYwMarkId(pro.getYwMarkId());
	// wwp.setMarkId(pro.getMarkId());
	// wwp.setProcessNo(nextWwProcessInfor.getProcessNO()
	// + "");
	// wwp.setProcessName(nextWwProcessInfor.getProcessName());
	// wwp.setProName(pro.getProName());
	// wwp.setType("外委");
	// wwp.setBeginCount(getCount);
	// wwp.setNumber(getCount);
	// wwp.setBanben(pro.getBanBenNumber());
	// wwp.setBanci(pro.getBanci());
	// // if (nextWwProcessInfor.getProcessNO().equals(
	// // maxProcessNo)) {
	// // // 最后一道外委激活数量=下层最小minNumber - 已生成过的外委数量
	// // // 当beginCount=null时为改版之前数据
	// // Float wwCount1 = (Float) totalDao
	// // .getObjectByCondition(
	// //
	// "select sum(number) from WaigouWaiweiPlan where processNO=? and beginCount is null and procardId=?",
	// // maxProcessNo, pro.getId());
	// // // 当beginCount>0时以beginCount为标准
	// // Float wwCount2 = (Float) totalDao
	// // .getObjectByCondition(
	// //
	// "select sum(beginCount) from WaigouWaiweiPlan where processNO=? and beginCount>0 and procardId=?",
	// // maxProcessNo, pro.getId());
	// // Float beginCount = lasttotalCount - wwCount1
	// // - wwCount2;
	// // if (beginCount <= 0) {
	// // beginCount = 0f;
	// // }
	// // wwp.setBeginCount(beginCount);
	// // wwp.setNumber(beginCount);
	// // } else {
	// // wwp.setBeginCount(getCount);
	// // wwp.setNumber(getCount);
	// // }
	// wwp.setAddTime(Util.getDateTime());
	// wwp.setJihuoTime(Util.getDateTime());
	// wwp.setShArrivalTime(pro.getNeedFinalDate());// 应到货时间在采购确认通知后计算
	// wwp.setCaigouMonth(Util.getDateTime("yyyy-MM月"));// 采购月份
	// String wwNumber = "";
	// String before = null;
	// Integer bIndex = 10;
	// before = "ww" + Util.getDateTime("yyyyMMdd");
	// Integer maxNo = 0;
	// String maxNumber = (String) totalDao
	// .getObjectByCondition("select max(planNumber) from WaigouOrder where planNumber like '"
	// + before + "%'");
	// if (maxNumber != null) {
	// String wwnum = maxNumber.substring(bIndex,
	// maxNumber.length());
	// try {
	// Integer maxNum = Integer.parseInt(wwnum);
	// if (maxNum > maxNo) {
	// maxNo = maxNum;
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// }
	// }
	// maxNo++;
	// wwNumber = before + String.format("%03d", maxNo);
	// wwp.setPlanNumber(wwNumber);// 采购计划编号
	// wwp.setSelfCard(pro.getSelfCard());// 批次
	// wwp.setGysId(nextWwProcessInfor.getZhuserId());// 供应商id
	// wwp.setGysName(nextWwProcessInfor.getGys());// 供应商名称
	// wwp.setAllJiepai(nextWwProcessInfor.getAllJiepai());// 单件总节拍
	// wwp.setDeliveryDuration(nextWwProcessInfor
	// .getDeliveryDuration());// 耽误时长
	// wwp.setSingleDuration(pro.getSingleDuration());// 单班时长(工作时长)
	// wwp.setProcardId(pro.getId());
	// wwp.setProcard(pro);
	// if(wwckCount!=null&&wwckCount>0){
	// Float wwCount = pro.getFilnalCount();
	// wwp.setStatus("待在制入库");
	// //在制品待入库
	// if(pro.getZaizhiApplyZk()==null){
	// pro.setZaizhiApplyZk(0f);
	// }
	// if(pro.getZaizhizkCount()==null){
	// pro.setZaizhizkCount(0f);
	// }
	// if(pro.getKlNumber()==null){
	// pro.setKlNumber(pro.getFilnalCount());
	// }
	// if(pro.getHascount()==0){
	// pro.setHascount(pro.getKlNumber());
	// }
	// //procard.getKlNumber()-procard.getHascount()=已生产数量
	// //可转库数量=已生产数量-已转库数量-转库申请中数量
	// pro.setZaizhikzkCount(pro.getKlNumber()-pro.getHascount()-pro.getZaizhizkCount()-pro.getZaizhiApplyZk());
	// if(pro.getZaizhikzkCount()>=wwCount){
	// pro.setZaizhiApplyZk(pro.getZaizhiApplyZk()+wwCount);
	// String orderNum = (String)
	// totalDao.getObjectByCondition("select orderNumber from Procard where id=?",
	// pro.getRootId());
	// //成品待入库
	// GoodsStore goodsStore2=new GoodsStore();
	// goodsStore2.setNeiorderId(orderNum);
	// goodsStore2.setGoodsStoreMarkId(pro.getMarkId());
	// goodsStore2.setBanBenNumber(pro.getBanBenNumber());
	// goodsStore2.setGoodsStoreLot(pro.getSelfCard());
	// goodsStore2.setGoodsStoreGoodsName(pro.getProName());
	// goodsStore2.setApplyTime(Util.getDateTime());
	// goodsStore2.setGoodsStoreArtsCard((String)totalDao.getObjectByCondition("select selfCard from Procard where id=?",
	// pro.getRootId()));
	// goodsStore2.setGoodsStorePerson(Util.getLoginUser().getName());
	// goodsStore2.setStatus("待入库");
	// goodsStore2.setStyle("半成品转库");
	// goodsStore2.setGoodsStoreWarehouse("委外库");// 库别
	// // goodsStore2.setGoodHouseName(goodsStore.getGoodHouseName());// 区名
	// //
	// goodsStore2.setGoodsStorePosition(goodsStore.getGoodsStorePosition());//
	// 库位
	// goodsStore2.setGoodsStoreUnit(pro.getUnit());
	// goodsStore2.setGoodsStoreCount(wwCount);
	// totalDao.update(pro);
	// totalDao.save(goodsStore2);
	// }else{
	// throw new RuntimeException("对不起超过可申请数量("+pro.getZaizhikzkCount()+")");
	// }
	// }else{
	// wwp.setStatus("待激活");
	// }
	// totalDao.save(wwp);
	// }else{
	// wwp.setProcessNo(wwp.getProcessNo()+";"+nextWwProcessInfor.getProcessNO());
	// wwp.setProcessName(wwp.getProcessName()+";"+nextWwProcessInfor.getProcessName());
	// totalDao.update(wwp);
	// }
	// } else {
	// break;
	// }
	// } else {
	// break;
	// }
	// n++;
	// }
	// if(wwp.getId()!=null){
	// //匹配供应商
	// Price price = (Price) totalDao
	// .getObjectByCondition(
	// "from Price where wwType='工序外委' and partNumber=? and gongxunum=? and (pricePeriodEnd is null or pricePeriodEnd ='' or pricePeriodEnd>='"
	// + Util.getDateTime()
	// + "')  order by hsPrice", wwp.getMarkId(),wwp.getProcessNo());
	// if (price != null) {
	// wwp.setPriceId(price.getId());
	// wwp.setGysId(price.getGysId());
	// ZhUser zhUser = (ZhUser) totalDao.getObjectById(ZhUser.class,
	// price.getGysId());
	// wwp.setGysName(zhUser.getName());
	// wwp.setUserCode(zhUser.getUsercode());
	// wwp.setUserId(zhUser.getUserid());
	// totalDao.update(wwp);
	// }
	// }
	// }
	//		
	// return listSell;
	// }

	public Float getZaiZhiCount(Integer id) {
		// TODO Auto-generated method stub
		Float count = (Float) totalDao
				.getObjectByCondition(
						"select (zyCount-ylCount) from ProcardProductRelation where procardId=? and flagType='余额在制品'",
						id);
		if (count == null) {
			return 0f;
		}
		return count;

		// Procard procard = (Procard) totalDao.getObjectById(Procard.class,
		// id);
		// // 是否为需要原材料的组合(此处只查组合的成品在制品不查组合的原材料在制品)
		// boolean iszhycl = procard.isZhHasYcl();
		// String hqlZaizhi = "from Goods where goodsMarkId='"
		// + procard.getMarkId()
		// +
		// "' and goodsClass='在制品' and goodsCurQuantity>0 and (fcStatus is null or fcStatus='可用') ";
		// if (iszhycl) {// 组合分成品在制品(goodsFormat 为空)和原材料在制品(goodsFormat 为'原材料')
		// hqlZaizhi = hqlZaizhi
		// + " and (goodsFormat is null or goodsFormat='')";
		// }
		// List<Goods> listzaizhi = totalDao.query(hqlZaizhi);
		// float zaizhiCount = 0f;
		// if (null != listzaizhi && listzaizhi.size() > 0) {
		// for (Goods goodszaizhi : listzaizhi) {
		// zaizhiCount += goodszaizhi.getGoodsCurQuantity();// 在制品数量
		// }
		// }
		// // 查询所有同总成件号没有完成的所需的在制品量，用在制品数减去(同总成除外，同总成的将在下面计算)
		// String filnalCountSql =
		// "from Procard where markId =? and id !=? and hasCount is not null and klNumber>hasCount and rootId!=?  and rootId in (select rootId from Procard where markId =(select markId from Procard where id=?)  and status not in('入库'))";
		// // if (procard.getProcardStyle() != null
		// // && procard.getProcardStyle().equals("外购")) {
		// // filnalCountSql += " and needprocess='yes'";
		// // }
		// List<Procard> filnalCountList = totalDao.query(filnalCountSql,
		// procard
		// .getMarkId(), procard.getId(), procard.getRootId(), procard
		// .getRootId());
		// if (filnalCountList.size() > 0) {
		// for (Procard zaizhiProcard : filnalCountList) {
		// if (zaizhiProcard != null) {
		// Procard totalCard = (Procard) totalDao.getObjectById(
		// Procard.class, zaizhiProcard.getRootId());
		// if (totalCard.getHasRuku() == null) {
		// totalCard.setHasRuku(0f);
		// }
		// if (zaizhiProcard.getKlNumber() == null) {
		// zaizhiProcard.setKlNumber(0f);
		// }
		// if (zaizhiProcard.getHascount() == null) {
		// zaizhiProcard.setHascount(zaizhiProcard.getKlNumber());
		// }
		// // 已入库数量*该件号对总成的比例数量就是需要在制品数量
		// Float hasRuKu = totalCard.getHasRuku()
		// * zaizhiProcard.getFilnalCount()
		// / totalCard.getFilnalCount();
		// // 已领减去已入库
		// float hsaCount = (float) Math.ceil(zaizhiProcard
		// .getHascount());
		// Float needCount = (zaizhiProcard.getKlNumber() - hsaCount)
		// - hasRuKu;
		// zaizhiCount -= needCount;
		// }
		// }
		//
		// }
		// // 查询其他含有该自制件的BOM的已领却没有入库所需要的在制品数
		// String otherFilnalCountSql =
		// "from Procard where status in('已发料','领工序','完成','待入库') and markId=?  and rootId in(select id from Procard where id in (select rootId from Procard where markId=?) and markId!=(select markId from Procard where id=?) and status not in('入库'))";
		// // if (procard.getProcardStyle() != null
		// // && procard.getProcardStyle().equals("外购")) {
		// // otherFilnalCountSql += " and needprocess='yes'";
		// // }
		// List<Procard> otherProcardList = totalDao.query(otherFilnalCountSql,
		// procard.getMarkId(), procard.getMarkId(), procard.getRootId());
		// if (otherProcardList.size() > 0) {
		// for (Procard p : otherProcardList) {
		// if (p.getHascount() == null || p.getFilnalCount() == null
		// || (p.isZhHasYcl() && p.getYhascount() == null)) {
		// break;
		// } else {
		// // 已领减去已入库
		// Procard totalCard = (Procard) totalDao.getObjectById(
		// Procard.class, p.getRootId());
		// if (totalCard.getHasRuku() == null) {
		// totalCard.setHasRuku(0f);
		// }
		// // （总成总数量-已入库数量）*该件号对总成的比例数量就是需要在制品数量-未领料的
		// float hsaCount = (float) Math.ceil(p.getHascount());
		// zaizhiCount -= (totalCard.getFilnalCount() - totalCard
		// .getHasRuku())
		// * p.getFilnalCount()
		// / totalCard.getFilnalCount()
		// - hsaCount;
		// }
		// }
		// }
		// Procard totalCard = (Procard) totalDao.getObjectById(Procard.class,
		// procard.getRootId());
		// if (totalCard.getHasRuku() == null) {
		// totalCard.setHasRuku(0f);
		// }
		// // 查询本批次同件号已领的其他件号
		// Procard sameMarkId = (Procard) totalDao
		// .getObjectByCondition(
		// "from Procard where status in('已发料','领工序','完成','待入库') and rootId=? and markId=? and id!=?",
		// procard.getRootId(), procard.getMarkId(), procard
		// .getId());
		// if (sameMarkId != null) {
		// if (sameMarkId.getHascount() == null
		// || sameMarkId.getFilnalCount() == null
		// || (sameMarkId.isZhHasYcl() && sameMarkId.getYhascount() == null)) {
		// } else {
		// // 已领减去已入库
		// // （总成总数量-已入库数量）*该件号对总成的比例数量就是需要在制品数量-未领料的
		// if (sameMarkId.getHascount() != null) {
		// float hsaCount = (float) Math
		// .ceil(sameMarkId.getHascount());
		// zaizhiCount -= (sameMarkId.getKlNumber() - hsaCount);// 本批次生成的在制品
		//
		// zaizhiCount += totalCard.getHasRuku()
		// * sameMarkId.getFilnalCount()
		// / totalCard.getFilnalCount();// 入库扣去的在制品
		// }
		// }
		// }
		// // 查询本批次已占用的在制品，用在制品数减去
		// if (procard.getHascount() != null) {
		// if (procard.getKlNumber() > procard.getHascount()) {
		//
		// // 已生成的在制品-总成已入库扣去的在制品数量
		// float hsaCount = (float) Math.ceil(procard.getHascount());
		// zaizhiCount -= (procard.getKlNumber() - hsaCount);// 本批次生成的在制品
		//
		// zaizhiCount += totalCard.getHasRuku()
		// * procard.getFilnalCount() / totalCard.getFilnalCount();// 入库扣去的在制品
		// }
		// }
		//
		// return zaizhiCount;
	}

	@Override
	public void changeycl() {
		// TODO Auto-generated method stub
		List<Procard> procardList = totalDao
				.query("from Procard where rootId not in(select id from Procard where procardStyle='总成' and status='入库') "
						+ "and procardStyle not in ('组合','外购') and trademark is not null and trademark!='' and bili is not NULL ");
		if (procardList != null && procardList.size() > 0) {
			for (Procard father : procardList) {
				if (father.getTrademark() != null
						&& father.getTrademark().length() > 0) {
					Procard yclProcard = new Procard();
					yclProcard.setFatherId(father.getId());
					yclProcard.setProcard(father);
					yclProcard.setRootMarkId(father.getRootMarkId());
					yclProcard.setRootSelfCard(father.getRootSelfCard());
					yclProcard.setWgType(father.getWgType());
					yclProcard.setKgliao("TK");
					yclProcard.setMarkId(father.getTrademark());
					yclProcard.setSpecification(father.getSpecification());
					yclProcard.setProName(father.getYuanName());
					yclProcard.setSelfCard(father.getSelfCard());
					yclProcard.setTuhao(father.getYtuhao());
					yclProcard.setQuanzi1(father.getQuanzi1());
					yclProcard.setQuanzi2(father.getQuanzi2());
					yclProcard.setUnit(father.getYuanUnit());
					yclProcard.setProcardStyle("外购");
					yclProcard.setRootId(father.getRootId());
					yclProcard.setLingliaostatus(father.getLingliaostatus());
					yclProcard.setBili(father.getBili());
					yclProcard.setWgType(father.getWgType());
					yclProcard.setKgliao(father.getKgliao());
					yclProcard.setProductStyle(father.getProductStyle());
					yclProcard.setOrderNumber(father.getOrderNumber());
					yclProcard.setOrderId(father.getOrderId());
					if (father.getQuanzi2() == null || father.getQuanzi2() == 0
							|| father.getQuanzi1() == null
							|| father.getQuanzi1() == 0) {
						yclProcard.setFilnalCount(0f);
						yclProcard.setKlNumber(0f);
						yclProcard.setHascount(0f);
						yclProcard.setMinNumber(0f);
					} else {
						yclProcard.setFilnalCount(father.getFilnalCount()
								* father.getQuanzi2() / father.getQuanzi1());
						yclProcard.setKlNumber(father.getFilnalCount()
								* father.getQuanzi2() / father.getQuanzi1());
						yclProcard.setHascount(father.getFilnalCount()
								* father.getQuanzi2() / father.getQuanzi1());
					}
					if (father.getJihuoStatua() != null
							&& father.getJihuoStatua().equals("激活")) {
						yclProcard.setJihuoStatua("激活");
						if (father.getQuanzi2() != null
								&& father.getQuanzi2() > 0
								&& father.getQuanzi1() != null
								&& father.getQuanzi1() > 0) {
							yclProcard
									.setTjNumber(father.getFilnalCount()
											* father.getQuanzi2()
											/ father.getQuanzi1());
							yclProcard.setMinNumber(father.getFilnalCount());
							if (father.getKlNumber() == null) {
								father.setKlNumber(father.getFilnalCount());
							}
							if (father.getHascount() == null) {
								father.setHascount(father.getKlNumber());
							}
							// 剩余可领=可领数量-上层已领数量*权值
							yclProcard.setHascount(yclProcard.getKlNumber()
									- ((father.getKlNumber() - father
											.getHascount())
											* father.getQuanzi2() / father
											.getQuanzi1()));
						}
					} else {
						yclProcard.setMinNumber(0f);
						yclProcard.setTjNumber(0f);
					}
					if (yclProcard.getHascount() == 0) {
						yclProcard.setStatus("完成");
						ProcardBl bl = (ProcardBl) totalDao.getObjectByCondition("from ProcardBl where procardId=? and status!='已领完'", yclProcard.getId());
						if(bl!=null){
							bl.setStatus("已领完");
							totalDao.update(bl);
						}
					} else {
						yclProcard.setStatus("已发卡");
					}
					totalDao.save(yclProcard);
					 father.setTrademark(null);
					 father.setSpecification(null);
					 father.setQuanzi1(null);
					 father.setQuanzi2(null);
					 father.setYtuhao(null);
					 father.setYuanName(null);
					 father.setYuanUnit(null);
					 father.setBili(null);
					 totalDao.update(father);
				}
			}
		}
	}
	//判断是否满足最低库存采购；
	private boolean isneedcg(String markId,String specification,String banbenhao){
		if(markId!=null && markId.length()>0){
			String hql_wgj = " from YuanclAndWaigj where markId = ? and minkc is not null ";
			String hql_minkc = " select sum(goodsCurQuantity) from Goods where markId = ?"; 
			if(banbenhao!=null &&banbenhao.length()>0){
				hql_wgj += " and banbenhao = '"+banbenhao+"'";
				hql_minkc += " and banBenNumber = '"+banbenhao+"'";
			}else{
				hql_wgj +=" and (banbenhao = '' or  banbenhao is null)";
				hql_minkc += " and (banBenNumber = '' or banBenNumber is null )";
			}
			if(specification!=null && specification.length()>0){
				hql_wgj+= " and specification = '"+specification+"'";
				hql_minkc += " and goodsFormat = '"+specification+"'"; 
			}else{
				hql_wgj+= " and (specification = '' or specification is null)";
				hql_minkc += " and (goodsFormat = '' or goodsFormat is null )"; 
			}
			YuanclAndWaigj  wgj =	(YuanclAndWaigj) totalDao.getObjectByCondition(hql_wgj,markId);
			if(wgj!=null && wgj.getMinkc()!=null && wgj.getMinkc()>0){
				hql_minkc += " HAVING  sum(goods_CurQuantity)<= ?";
				Float kcCount	= (Float) totalDao.getObjectByCondition(hql_minkc,markId,wgj.getMinkc() );
				if(kcCount!=null && kcCount>0){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public void newzaizhipin() {
		// TODO Auto-generated method stub
		List<Procard> procardList = totalDao
				.query("from Procard where status in('已发卡','已发料','领工序','完成')  and procard.status not in('完成','取消') "
						+ "and rootId in(select id from Procard where procardStyle='总成' and status not in('完成','入库','取消')) ");
		// System.out.println(procardList.size());
		if (procardList != null && procardList.size() > 0) {
			for (Procard procard : procardList) {
				Float zaizhiCount = 0f;
				if (procard.getProcardStyle().equals("总成")) {
					Float tjNumber = 0f;
					if (procard.getTjNumber() != null) {
						tjNumber = procard.getTjNumber();
					}
					if (procard.getKlNumber() != null
							&& procard.getHascount() != null) {
						Float ylNumber = procard.getKlNumber()
								- procard.getHascount();
						zaizhiCount = ylNumber - tjNumber;
					}
				} else {
					Float tjNumber = 0f;
					if (procard.getProcard() != null
							&& procard.getProcard().getTjNumber() != null) {
						if (procard.getProcardStyle().equals("外购")) {
							if (procard.getQuanzi2() != null
									&& procard.getQuanzi2() > 0
									&& procard.getQuanzi1() != null
									&& procard.getQuanzi1() > 0) {
								tjNumber = procard.getProcard().getTjNumber()
										* procard.getQuanzi2()
										/ procard.getQuanzi1();
							} else {
								continue;
							}
						} else {
							tjNumber = procard.getProcard().getTjNumber()
									* procard.getCorrCount();
						}
					}
					if (procard.getKlNumber() != null
							&& procard.getHascount() != null) {
						Float ylNumber = procard.getKlNumber()
								- procard.getHascount();
						zaizhiCount = ylNumber - tjNumber;
					}
				}
				if (zaizhiCount > 0) {
					// 生成在制品
					Goods gg = new Goods();
					gg.setGoodsMarkId(procard.getMarkId());
					gg.setGoodsLotId(procard.getSelfCard());
					gg.setGoodsFullName(procard.getProName());
					gg.setGoodsClass("在制品");
					gg.setBanBenNumber(procard.getBanBenNumber());
					gg.setGoodsCurQuantity(zaizhiCount);
					gg.setGoodsUnit(procard.getUnit());
					gg.setWgType(procard.getWgType());
					gg.setGoodsFormat(procard.getSpecification());
					gg.setGoodsChangeTime(Util.getDateTime());
					totalDao.save(gg);
					// 添加零件与在制品关系表
					ProcardProductRelation pprelation = new ProcardProductRelation();
					pprelation.setAddTime(Util.getDateTime());
					pprelation.setProcardId(procard.getId());
					pprelation.setGoodsId(gg.getGoodsId());
					pprelation.setZyCount(zaizhiCount);
					pprelation.setFlagType("本批在制品");
					totalDao.save(pprelation);
					// 添加在制品入库记录
					GoodsStore gs = new GoodsStore();
					gs.setGoodsStoreMarkId(procard.getMarkId());// 件号
					gs.setGoodsStoreFormat(procard.getSpecification());
					gs.setTuhao(procard.getTuhao());
					gs.setBanBenNumber(procard.getBanBenNumber());
					gs.setGoodsStoreGoodsName(procard.getProName());// 名称
					gs.setGoodsStoreLot(procard.getSelfCard());// 批次
					gs.setGoodsStoreCount(zaizhiCount);// 数量
					gs.setPrintStatus("YES");
					gs.setGoodsStoreProMarkId(procard.getRootMarkId());// 总成件号
					gs.setGoodsStoreWarehouse("在制品");// 库别
					Users jingban = Util.getLoginUser();
					gs.setGoodsStoreCharger(jingban.getName());// 经办人
					gs.setStyle("正常（成品）");// 入库类型
					gs.setGoodsStorePerson("system");
					gs.setGoodsStoreDate(DateUtil.formatDate(new Date(),
							"yyyy-MM-dd"));
					gs.setGoodsStoreUnit(procard.getUnit());// 单位
					gs.setWgType(procard.getWgType());
					totalDao.save(gs);

				}
			}
		}
	}

	/**
	 * 对应生产批次在制品入库
	 * 
	 * @param procard
	 * @param count
	 * @param type
	 * @return 在制品库存,在制品与零件中间表,在制品入库数据
	 */
	public Object[] zaizhiInput(Procard procard, Users user, Float count,
			String type) {
		Object[] obj = new Object[3];
		String hqlzaizhi = "from Goods where goodsMarkId='"
				+ procard.getMarkId()
				+ "' and goodsLotId='"
				+ procard.getSelfCard()
				+ "' and goodsClass='在制品' and goodsStyle!='半成品转库' and (fcStatus is null or fcStatus='可用') ";
		List listzizhi = totalDao.find(hqlzaizhi);
		Integer rgoodsId = null;
		Goods gg = null;
		if (listzizhi != null && listzizhi.size() > 0) {
			gg = (Goods) listzizhi.get(0);
			gg.setGoodsCurQuantity(gg.getGoodsCurQuantity() + count);
			if (gg.getGoodsCurQuantity() < 0) {
				AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
						+ gg.getGoodsMarkId() + "批次:" + gg.getGoodsLotId()
						+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
						+ Util.getDateTime(), "2");
				gg.setGoodsCurQuantity(0f);
			}
			totalDao.update(gg);
			rgoodsId = gg.getGoodsId();
		} else {
			gg = new Goods();
			gg.setGoodsMarkId(procard.getMarkId());
			gg.setBanBenNumber(procard.getBanBenNumber());
			gg.setGoodsFormat(procard.getSpecification());
			gg.setTuhao(procard.getTuhao());
			gg.setGoodsLotId(procard.getSelfCard());
			gg.setGoodsFullName(procard.getProName());
			gg.setGoodsClass("在制品");
			gg.setGoodsCurQuantity(count);
			gg.setGoodsBeginQuantity(count);
			gg.setGoodsStyle(type);
			if (gg.getGoodsCurQuantity() < 0) {
				AlertMessagesServerImpl.addAlertMessages("系统维护异常组", "件号:"
						+ gg.getGoodsMarkId() + "批次:" + gg.getGoodsLotId()
						+ "可领数量小于零，系统自动修复为0，操作是：领料,当前系统时间为"
						+ Util.getDateTime(), "2");
				gg.setGoodsCurQuantity(0f);
			}
			gg.setGoodsUnit(procard.getUnit());
			totalDao.save(gg);
			rgoodsId = gg.getGoodsId();

		}
		// 添加零件与在制品关系表
		ProcardProductRelation pprelation = new ProcardProductRelation();
		pprelation.setAddTime(Util.getDateTime());
		pprelation.setProcardId(procard.getId());
		pprelation.setGoodsId(rgoodsId);
		pprelation.setZyCount(count);
		pprelation.setFlagType("本批在制品");
		totalDao.save(pprelation);
		GoodsStore gs = new GoodsStore();// 添加外购件在制品入库记录
		gs.setGoodsStoreMarkId(procard.getMarkId());// 件号
		gs.setGoodsStoreFormat(procard.getSpecification());
		gs.setTuhao(procard.getTuhao());
		gs.setBanBenNumber(procard.getBanBenNumber());
		gs.setGoodsStoreGoodsName(procard.getProName());// 名称
		gs.setGoodsStoreLot(procard.getSelfCard());// 批次
		// gs.setGoodsStoreCount((float) Math.floor(g
		// .getGoodsCurQuantity()));// 数量
		gs.setGoodsStoreCount(count);// 数量
		gs.setPrintStatus("YES");
		gs.setGoodsStoreProMarkId(procard.getMarkId());// 总成件号
		gs.setGoodsStoreWarehouse("在制品");// 库别
		Users jingban = Util.getLoginUser();
		gs.setGoodsStoreCharger(jingban.getName());// 经办人
		gs.setStyle(type);// 入库类型
		if (user != null) {// 负责人
			gs.setGoodsStorePerson(user.getName());
		} else {
			gs.setGoodsStorePerson(procard.getLingliaoren());
		}
		gs.setGoodsStoreDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		gs.setGoodsStoreUnit(procard.getUnit());// 单位
		totalDao.save(gs);
		obj[0] = gg;
		obj[1] = pprelation;
		obj[2] = gs;
		return obj;
	}

	public GoodsStoreServer getGoodsStoreServer() {
		return goodsStoreServer;
	}

	public void setGoodsStoreServer(GoodsStoreServer goodsStoreServer) {
		this.goodsStoreServer = goodsStoreServer;
	}
	
}
