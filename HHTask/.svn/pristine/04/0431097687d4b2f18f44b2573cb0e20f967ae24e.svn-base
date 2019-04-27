package com.task.ServerImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.task.Dao.TotalDao;
import com.task.Server.TijingpriceServer;
import com.task.entity.MinBalance;
import com.task.entity.Tijiang;
import com.task.entity.Tijiangprice;
import com.task.util.TJCompartor;

public class TijingpriceServerImpl implements TijingpriceServer {

	private TotalDao totalDao;

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	// 添加价格表信息
	public boolean addtijingprice(Tijiangprice tijingprice) {
		if (tijingprice != null) {
			return this.totalDao.save(tijingprice);
		}
		return false;
	}

	// 查询所有价格表信息
	public List findShouList(int pageNo, int pageSize) {
		String hql = "from Tijiangprice order by pricedate desc ";
		return this.totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 获得所有数量
	public Integer getCount() {
		String hql = "from Tijiangprice";
		return totalDao.getCount(hql);
	}

	// 根据ID查询所有信息
	public Tijiangprice findtijingpriceById(int id) {
		return (Tijiangprice) this.totalDao.getObjectById(Tijiangprice.class,
				id);
	}

	// 修改
	public boolean updateTijingprice(Tijiangprice tijiangprice) {
		if (tijiangprice != null) {
			return this.totalDao.update(tijiangprice);
		}
		return false;
	}

	// 删除
	public boolean deleteTijingprice(Tijiangprice tijingprice) {
		if (tijingprice != null) {
			return this.totalDao.delete(tijingprice);
		}
		return false;
	}

	// 条件查询
	public List findByCondition(Tijiangprice tijingprice, int pageNo,
			int pageSize) {
		String hql = this.totalDao.criteriaQueries(tijingprice, null, null);
		return totalDao.findAllByPage(hql, pageNo, pageSize);
	}

	// 获得条件查询的总数量
	public int findByConditioncount(Tijiangprice tijingprice) {
		String hql = this.totalDao.criteriaQueries(tijingprice, null, null);
		return this.totalDao.getCount(hql);
	}

	// 根据件号查询相同的开始数量
	public List findname(String findname) {
		String hql = "select max(priceendcount)  from Tijiangprice where pricemarkId='"
				+ findname + "'";
		return this.totalDao.query(hql);
	}

	// 根据指定时间查询出入库表中的数量和件号
	@SuppressWarnings("unchecked")
	public Object[] findShougoodsStore(String setDate, String endDate) {
		List liMinBalance = new ArrayList();// 结余记录
		if (setDate != null && endDate != null) {
			List sumList = new ArrayList();

			String hql = "select sellMarkId,sum(sellCount),sellLot as count from Sell where sellDate between '"
					+ setDate
					+ "' and  '"
					+ endDate
					+ "' and sellWarehouse='成品库'  and sellMarkId in(select distinct(pricemarkId) from Tijiangprice where priceTjStyle in('单价计价',''))"
					+ " group by sellMarkId,sellLot order by sellMarkId";
			List<Object[]> goodList = totalDao.query(hql);
			Map<String, Float> map = new HashMap<String, Float>();
			for (int i = 0, len = goodList.size(); i < len; i++) {
				Object[] obj = goodList.get(i);
				String sellMarkId = (String) obj[0];// 产品件号
				Float sellCount = Float.valueOf(obj[1].toString());// 产品数量
				String sellLot = (String) obj[2];// 产品批次
				if (sellCount == null) {
					sellCount = 0F;
				}
				// 查询历史已入库的数量
				String hql_gs = "select sum(goodsStoreCount) from GoodsStore where  goodsStoreTime < ? and goodsStoreMarkId=? and goodsStoreLot=? and goodsStoreWarehouse='成品库'";
				Float goodsStoreCount = (Float) totalDao.getObjectByCondition(
						hql_gs, "2015-07-26 09:00:00", sellMarkId, sellLot);
				if (goodsStoreCount != null && goodsStoreCount > 0) {
					sellCount -= goodsStoreCount;
				}
				if (sellCount < 0) {
					sellCount = 0F;
				}

				Float count = map.get(sellMarkId);
				if(count==null){
					count=0F;
				}
				count += sellCount;
				map.put(sellMarkId, count);
			}
			for (String key : map.keySet()) {
				
				String goodsStoreMarkId = key;// 产品件号
				Float count = Float.valueOf(map.get(key).toString());// 实际入库数量
				if(count<=0){
					continue;
				}
				String hql2 = "from Tijiangprice  where pricemarkId=? and pricedefault='正常使用' order by pricesenacount desc";
				List priceList = totalDao.query(hql2, goodsStoreMarkId);
				if (priceList != null && priceList.size() > 0) {
					Float moneyFloat = 0F;
					String xingbie = "";
					for (int j = 0, len2 = priceList.size(); j < len2; j++) {
						Tijiangprice tjpTijiangprice = (Tijiangprice) priceList
								.get(j);// 价格对象
						Float pricesenacount = tjpTijiangprice
								.getPricesenacount();// 开始数量
						Float priceendcount = tjpTijiangprice
								.getPriceendcount();// 结束数量
						xingbie = tjpTijiangprice.getPricestyle();// 型别
						if (pricesenacount == 0.0 && priceendcount == 0.0) {
							moneyFloat += count
									* tjpTijiangprice.getPricefactPrice();
						} else {
							if (pricesenacount != null
									&& pricesenacount <= count) {// 如果实际数量大于等于开始数量
								// 说明实际数量在该区间内
								if (priceendcount != null
										&& count < priceendcount) {// 如果实际数量小于结束数量
									// 说明实际数量在该区间内
									moneyFloat += (count - pricesenacount)
											* tjpTijiangprice
													.getPricefactPrice();
								} else if (priceendcount != null
										&& priceendcount < count) {// 否则
									// 说明实际数量不在该区间内
									// 并且该区间的所有数量已经完成
									moneyFloat += (priceendcount - pricesenacount)
											* tjpTijiangprice
													.getPricefactPrice();
								} else {
									moneyFloat += (count - pricesenacount)
											* tjpTijiangprice
													.getPricefactPrice();
								}
							}
						}
					}
					Object[] sum = { goodsStoreMarkId, count, moneyFloat,
							xingbie };
					sumList.add(sum);
				}

			}

			// 查询出 大众059系列 累计记将
			String hqltyStyle = "select distinct(pricestyle) from Tijiangprice where priceTjStyle='累计计价' and pricedefault='正常使用'";
			if (null != totalDao.query(hqltyStyle)
					&& totalDao.query(hqltyStyle).size() > 0) {
				for (int i = 0; i < totalDao.query(hqltyStyle).size(); i++) {
					String priceStyle = (String) totalDao.query(hqltyStyle)
							.get(i);
					String hql2 = "select sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
							+ setDate
							+ "' and  '"
							+ endDate
							// +
							// "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId in('1KD253059AK5AX','1KD253059AR','1KD253059AT','1KD253059AS','180253059N','180253059L','1KD253059AN','1KD253059AP')";
							+ "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId in(select distinct(pricemarkId) from Tijiangprice where priceTjStyle='累计计价' and pricestyle='"
							+ priceStyle + "')";
					List otherList = totalDao.query(hql2);
					// 实际算法
					if (otherList != null && otherList.size() > 0) {
						// 计算出总数量
						// String goodsStoreMarkId = priceStyle;// 产品件号
						Float count = Float
								.valueOf(otherList.get(0).toString());// 实际入库数量（总数量）
						/*
						 * 遍历件号，查询单价，计算单个件号的总额 查询累计的件号
						 */
						String hqlMarkId = "select distinct(pricemarkId) from Tijiangprice where priceTjStyle='累计计价' and pricestyle='"
								+ priceStyle + "' ";
						if (null != totalDao.query(hqlMarkId)
								&& totalDao.query(hqlMarkId).size() > 0) {
							for (int t = 0; t < totalDao.query(hqlMarkId)
									.size(); t++) {
								// 查询各个件号
								String markId = (String) totalDao.query(
										hqlMarkId).get(t);
								// 单个件号的数量
								String hqlmarkIdCount = "select sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
										+ setDate
										+ "' and  '"
										+ endDate
										// +
										// "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId in('1KD253059AK5AX','1KD253059AR','1KD253059AT','1KD253059AS','180253059N','180253059L','1KD253059AN','1KD253059AP')";
										+ "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId ='"
										+ markId + "')";
								// 判断该件号入库情况
								if (totalDao.query(hqlmarkIdCount) != null
										&& totalDao.query(hqlmarkIdCount)
												.size() > 0) {
									// ============
									Float childCou = Float.valueOf(totalDao
											.query(hqlmarkIdCount).get(0)
											.toString());
									String hql3 = "from Tijiangprice where priceTjStyle='累计计价' and pricesenacount<? and priceendcount>? and pricedefault='正常使用' and pricemarkId='"
											+ markId + "'";
									List tijiangList = totalDao.query(hql3,
											count, count);
									if (tijiangList != null
											&& tijiangList.size() > 0) {
										Tijiangprice tijiangprice = (Tijiangprice) tijiangList
												.get(0);
										Float moneyFloat = tijiangprice
												.getNewPrice()
												* childCou;// 总价
										moneyFloat = Float.parseFloat(String
												.format("%.2f", moneyFloat));
										String xingbie = tijiangprice
												.getPricestyle();// 型别
										Object[] sum = { markId, childCou,
												moneyFloat, xingbie };
										sumList.add(sum);
									}
									// ============
								}
							}

						}

					}

				}
			}

			// 区间算法
			// for (int i = 0; i < otherList.size(); i++) {
			// String goodsStoreMarkId = "059系列";// 产品件号
			// Float count = (Float) otherList.get(i);// 实际入库数量
			// String hql3 =
			// "from Tijiangprice  where pricemarkId='1KD253059AK5AX'";
			// List priceList = totalDao.query(hql3);
			// if (priceList != null && priceList.size() > 0) {
			// Float moneyFloat = 0F;
			// String xingbie = "";
			// for (int j = 0, len2 = priceList.size(); j < len2; j++) {
			// Tijiangprice tjpTijiangprice = (Tijiangprice) priceList
			// .get(j);// 价格对象
			// Float pricesenacount = tjpTijiangprice
			// .getPricesenacount();// 开始数量
			// Float priceendcount = tjpTijiangprice
			// .getPriceendcount();// 结束数量
			// xingbie = tjpTijiangprice.getPricestyle();// 型别
			// if (pricesenacount == 0.0 && priceendcount == 0.0) {
			// moneyFloat += count
			// * tjpTijiangprice.getPricefactPrice();
			// } else {
			// if (pricesenacount != null
			// && pricesenacount <= count) {// 如果实际数量大于等于开始数量
			// // 说明实际数量在该区间内
			// if (priceendcount != null
			// && count < priceendcount) {// 如果实际数量小于结束数量
			// // 说明实际数量在该区间内
			// moneyFloat += (count - pricesenacount)
			// * tjpTijiangprice
			// .getPricefactPrice();
			// } else if (priceendcount != null
			// && priceendcount < count) {// 否则
			// // 说明实际数量不在该区间内
			// // 并且该区间的所有数量已经完成
			// moneyFloat += (priceendcount - pricesenacount)
			// * tjpTijiangprice
			// .getPricefactPrice();
			// } else {
			// moneyFloat += (count - pricesenacount)
			// * tjpTijiangprice
			// .getPricefactPrice();
			// }
			// }
			// }
			// }
			// Object[] sum = { goodsStoreMarkId, count, moneyFloat,
			// xingbie };
			// sumList.add(sum);
			// }
			// }
			// 算出MB100
			/*
			 * String hql3 =
			 * "select goodsStoreMarkId,sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
			 * + setDate + "' and  '" + endDate +
			 * "' and goodsStoreWarehouse='成品库' and goodsStoreMarkId in ('6614907001','6614907201','6614903319','6614906127','A6614906227') group by goodsStoreMarkId"
			 * ; List<Object[]> mblist1 = totalDao.query(hql3);
			 * 
			 * if (mblist1 != null && mblist1.size() > 0) { String hql10 =
			 * "from Tijiangprice where pricemarkId in ('6614907001','6614907201','6614903319','6614906127','A6614906227')"
			 * ; List tijilist = totalDao.query(hql10); for (int i = 0; i <
			 * mblist1.size(); i++) { String goodsStoreMarkId = (String)
			 * mblist1.get(i)[0];// 件号6614907001 Float count =
			 * Float.valueOf(mblist1.get(i)[1].toString());// 数量6614907001 if
			 * (tijilist != null && tijilist.size() > 0) { Float aaFloat = 0F;
			 * Tijiangprice tijiangprice = null; for (int j = 0; j <
			 * tijilist.size(); j++) { tijiangprice = (Tijiangprice)
			 * tijilist.get(j); if (tijiangprice.getPricemarkId().equals(
			 * goodsStoreMarkId)) { aaFloat = count
			 * tijiangprice.getPricefactPrice(); tijilist.remove(tijiangprice);
			 * break; } }
			 * 
			 * // if ("6614903319".equals(goodsStoreMarkId)) { // aaFloat =
			 * (count * 1.002F) / 5.31F // * tijiangprice.getPricefactPrice();
			 * // } else if ("6614906127".equals(goodsStoreMarkId)) { // aaFloat
			 * = (count * 0.66F) / 5.31F // * tijiangprice.getPricefactPrice();
			 * // } else if ("A6614906227".equals(goodsStoreMarkId)) { //
			 * aaFloat = (count * 1.48F) / 6.12F // *
			 * tijiangprice.getPricefactPrice(); // } else if
			 * ("6614907001".equals(goodsStoreMarkId)) { // aaFloat = (count *
			 * 1.34F) / 6.12F // * tijiangprice.getPricefactPrice(); // } else
			 * if ("6614907201".equals(goodsStoreMarkId)) { // aaFloat = (count
			 * * 2.27F) / 6.12F // * tijiangprice.getPricefactPrice(); // }
			 * Object[] sum1 = { goodsStoreMarkId, count, aaFloat,
			 * tijiangprice.getPricestyle() }; sumList.add(sum1); }
			 * 
			 * }
			 * 
			 * }
			 */
			// 计算配套类型的计算

			String hqltyStyleTao = "from Tijiangprice where priceTjStyle='配套计价' and pricedefault='正常使用'";
			if (null != totalDao.query(hqltyStyleTao)
					&& totalDao.query(hqltyStyleTao).size() > 0) {
				for (int ii = 0; ii < totalDao.query(hqltyStyleTao).size(); ii++) {
					Tijiangprice tjPrice = (Tijiangprice) totalDao.query(
							hqltyStyleTao).get(ii);
					String priceMarkId = tjPrice.getPricemarkId();// 件号
					Float price = tjPrice.getPricefactPrice();// 单价
					String xingbie = tjPrice.getPricestyle();
					float count = 0f;
					float moneyFloat = 0f;

					if (null != priceMarkId && !"".equals(priceMarkId)) {
						String[] markStyle = priceMarkId.split(";");
						Tijiang[] tj = new Tijiang[markStyle.length];

						if (markStyle.length > 0) {
							for (int i = 0; i < markStyle.length; i++) {// 计算配套件号数量
								String markId = markStyle[i];
								String hqlTaoCou = "";
								Tijiang tjDemo = new Tijiang();
								float cou = 0f;
								if (markId.contains(",")) {
									String[] markIdchild = markId.split(",");
									String markArr = "";
									if (markIdchild.length > 0) {
										for (int j = 0, len = markIdchild.length; j < len; j++) {
											if (j == len - 1) {
												markArr += "'"
														+ markIdchild[j]
																.toString()
														+ "'";
											} else
												markArr += "'"
														+ markIdchild[j]
																.toString()
														+ "',";
										}
									}

									hqlTaoCou = "select sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
											+ setDate
											+ "' and  '"
											+ endDate
											+ "' and goodsStoreMarkId in("
											+ markArr
											+ ") and goodsStoreLot not like '%无批次%'";
									/*
									 * Query
									 * query=totalDao.createQuery(hqlTaoCou);
									 * query
									 * .setParameterList("test",markIdchild);
									 * if(null!=query.list() &&
									 * query.list().size()>0){
									 * cou=(Float)query.list().get(0); }
									 * query.list();
									 */
								} else {
									hqlTaoCou = "select sum(goodsStoreCount) as count from GoodsStore where goodsStoreTime between '"
											+ setDate
											+ "' and  '"
											+ endDate
											+ "'  and goodsStoreMarkId ='"
											+ markId
											+ "' and goodsStoreLot not like '%无批次%'";
									/*
									 * if(null!=totalDao.query(hqlTaoCou) &&
									 * totalDao.query(hqlTaoCou).size()>0){
									 * cou=(
									 * Float)totalDao.query(hqlTaoCou).get(0); }
									 */
								}

								if (null != totalDao.query(hqlTaoCou)
										&& totalDao.query(hqlTaoCou).size() > 0) {
									String mon = setDate.substring(0, 7);
									String hqlminbalance = "from MinBalance where minMonth='"
											+ mon
											+ "' and minPartnumber='"
											+ markId + "' ";
									if (null != totalDao.query(hqlminbalance)
											&& totalDao.query(hqlminbalance)
													.size() > 0) {
										MinBalance min = (MinBalance) totalDao
												.query(hqlminbalance).get(0);
										System.out.println("件号" + markId
												+ "本月入库量=" + cou + "上月结余量="
												+ min.getMinBalancenumber());
										cou += (Float) totalDao
												.query(hqlTaoCou).get(0)
												+ min.getMinBalancenumber();
									} else {
										cou = (Float) totalDao.query(hqlTaoCou)
												.get(0);
									}

								}
								tjDemo.setTjmarkId(markId);
								tjDemo.setTjcount(cou);
								tjDemo.setTjstyle(xingbie);
								tj[i] = tjDemo;
							}
						}
						java.util.Arrays.sort(tj, new TJCompartor()); // 进行排序操作
						// for (int i = 0; i < tj.length; i++) { // 循环输出数组中的内容
						// System.out.println("=======================遍历数量"
						// + tj[i].getTjcount());
						// }
						liMinBalance.add(tj);
						Tijiang tjMin = tj[tj.length - 1];
						// System.out.println("=================zuixiao"
						// + tjMin.getTjcount());
						count = tjMin.getTjcount();
						moneyFloat = count * price;
					}

					Object[] sum = { priceMarkId, count, moneyFloat, xingbie };
					sumList.add(sum);
				}
			}
			Object[] o = { sumList, liMinBalance };
			return o;

		}
		return null;
	}

	// 条件查询 入库表指定的时间的数量 和单件计价表的单价 (前)
	public List findshougoodStoreBefore(String setDate, String endDate) {
		if (setDate != null && endDate != null) {
			String hql = "select distinct(sellMarkId),sellCompanyName from Sell where"
					+ " sellMarkId not in(select distinct(pricemarkId)  from Tijiangprice) "
					+ "and sellDate between '"
					+ setDate
					+ "' and '"
					+ endDate
					+ "' and sellWarehouse='成品库'";
			return this.totalDao.query(hql);
		}
		return null;
	}

}
