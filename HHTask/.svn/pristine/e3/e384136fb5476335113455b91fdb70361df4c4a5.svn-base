package com.task.ServerImpl.menjin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.task.Dao.TotalDao;
import com.task.Server.menjin.FingerprintMgServer;
import com.task.entity.Users;
import com.task.entity.dmltry.Zhongjian;
import com.task.entity.menjin.AccessEquipment;
import com.task.entity.menjin.DoorBangDing;
import com.task.entity.menjin.FingerprintMg;
import com.task.util.Util;

@SuppressWarnings("unchecked")
public class FingerprintMgServerImpl implements FingerprintMgServer {
	private TotalDao totalDao;

	@Override
	public String addFingerprintMg(FingerprintMg fe, Integer[] ae) {
		// TODO Auto-generated method stub
		if (fe != null) {
			Users users = null;
			if (fe.getUsers() == null) {
				users = (Users) totalDao.getObjectByCondition(
						"from Users where dept = ? and name = ? and code = ? and onWork <> '离职'", fe.getDept(),
						fe.getName(), fe.getCode());
			} else {
				users = (Users) totalDao.getObjectById(Users.class, fe.getUsers().getId());
			}
			if (users == null) {
				return "不存在该用户，添加失败！";
			} else {
				FingerprintMg fMg = (FingerprintMg) totalDao.getObjectByCondition(
						"from FingerprintMg where fingerType = ? and users.id = ? and type = '0'", fe.getFingerType(),
						users.getId());
				if (fMg != null) {
					BeanUtils.copyProperties(fMg, fe, new String[] { "id", "addTime" });
					fe.setIdentification(null);
					fe.setAddTime(Util.getDateTime());

					System.out.println(fe.getCode());
					System.out.println(fe.getFingerType());
					// 取消绑定如果传来的值是重复的话修改之前的数据将type改为1
					List<FingerprintMg> list = totalDao.query("FROM FingerprintMg WHERE code=?  and	fingerType=?",

							fe.getCode(), fe.getFingerType());
					if (list != null) {
						for (FingerprintMg fff : list) {
							Zhongjian zhonj = (Zhongjian) totalDao
									.getObjectByCondition("FROM Zhongjian WHERE fingerprintMgid=?", fff.getId());
							if (zhonj != null) {
								zhonj.setStatus("待删除");
								totalDao.update(zhonj);
							}

							fff.setType("1");
							totalDao.update(fff);
						}

					}

					if (totalDao.save(fe)) {
						List<DoorBangDing> bangDings = totalDao.query("from DoorBangDing where fk_security_id = ?",
								fe.getUsers().getId());
						boolean b = false;
						if (bangDings != null && bangDings.size() > 0) {
							b = true;
							for (DoorBangDing doorBangDing : bangDings) {
								doorBangDing.setStatus("待采集");
								doorBangDing.setFk_security_id(fe.getId());
								totalDao.update2(doorBangDing);
							}
						}
						if (b) {
							if (ae != null && ae.length > 0) {
								List<Integer> integers = new ArrayList<Integer>();
								for (Integer i : ae) {
									boolean b1 = true;
									for (DoorBangDing dbd : bangDings) {
										if (dbd.getFk_acEq_id() == i)
											b1 = false;
									}
									if (b1)
										integers.add(i);
								}
								if (integers != null && integers.size() > 0) {
									for (Integer ii : integers) {
										AccessEquipment acE = (AccessEquipment) totalDao
												.getObjectById(AccessEquipment.class, ii);
										if (acE != null) {
											// 查询考勤机最大的编号不能大于1000条
											String hql = "select max(number) from DoorBangDing where fk_acEq_id = ?";
											Integer number = (Integer) this.totalDao.getObjectByCondition(hql,
													acE.getId());
											DoorBangDing bangDing = new DoorBangDing();
											int i1 = 1;
											if (number != null) {
												if (number < 1000)
													i1 = number + 1;
												else
													continue;
											}
											bangDing.setFk_user_id(users.getId());
											bangDing.setFk_security_id(fe.getId());
											bangDing.setNumber(i1);
											bangDing.setFk_acEq_id(ii);
											bangDing.setStatus("待采集");
											totalDao.save(bangDing);
										}
									}
								}

							}
						} else {
							if (ae != null && ae.length > 0) {
								for (Integer i : ae) {
									AccessEquipment acE = (AccessEquipment) totalDao
											.getObjectById(AccessEquipment.class, i);
									if (acE != null) {
										// 查询考勤机最大的编号不能大于1000条
										String hql = "select max(number) from DoorBangDing where fk_acEq_id = ?";
										Integer number = (Integer) this.totalDao.getObjectByCondition(hql, acE.getId());
										DoorBangDing bangDing = new DoorBangDing();
										int i1 = 1;
										if (number != null) {
											if (number < 1000)
												i1 = number + 1;
											else
												continue;
										}
										bangDing.setFk_user_id(users.getId());
										bangDing.setFk_security_id(fe.getId());
										bangDing.setNumber(i1);
										bangDing.setFk_acEq_id(i);
										bangDing.setStatus("待采集");
										totalDao.save(bangDing);
									}
								}
							}
						}
						return "添加成功";
					} else
						return "添加失败！";
				} else {
					fe.setType("0");
					fe.setAddTime(Util.getDateTime());
					fe.setUsers(users);
					if (totalDao.save(fe)) {
						if (ae != null && ae.length > 0) {
							for (Integer i : ae) {
								AccessEquipment acE = (AccessEquipment) totalDao.getObjectById(AccessEquipment.class,
										i);
								if (acE != null) {
									// 查询考勤机最大的编号不能大于1000条
									String hql = "select max(number) from DoorBangDing where fk_acEq_id = ?";
									Integer number = (Integer) this.totalDao.getObjectByCondition(hql, acE.getId());
									DoorBangDing bangDing = new DoorBangDing();
									int i1 = 1;
									if (number != null) {
										if (number < 1000)
											i1 = number + 1;
										else
											continue;
									}
									bangDing.setFk_user_id(users.getId());
									bangDing.setFk_security_id(fe.getId());
									bangDing.setNumber(i1);
									bangDing.setFk_acEq_id(i);
									bangDing.setStatus("待采集");
									totalDao.save(bangDing);
								}
							}
						}
						return "添加成功";
					} else
						return "添加失败";
				}
			}
		}
		return "对象为空，添加失败！";
	}

	@Override
	public FingerprintMg byIdFingerprintMg(Integer id) {
		// TODO Auto-generated method stub
		return (FingerprintMg) totalDao.getObjectById(FingerprintMg.class, id);
	}

	@Override
	public String deleteFingerprintMg(Integer id) {
		// TODO Auto-generated method stub
		FingerprintMg obje = byIdFingerprintMg(id);
		if (obje != null) {
			// if (totalDao.delete(obje)) return "删除成功！";
			// else return "删除失败！";
		}
		return "对象为空，删除失败！";
	}

	@Override
	public Map<Integer, Object> findFingerprintMg(FingerprintMg FingerprintMg, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		if (FingerprintMg == null) {
			FingerprintMg = new FingerprintMg();
		}
		String hql = totalDao.criteriaQueries(FingerprintMg, null);
		hql += " order by id desc";
		List list = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);// 总条数
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, list);
		map.put(2, count);
		return map;
	}

	@Override
	public String updateFingerprintMg(FingerprintMg FingerprintMg) {
		// TODO Auto-generated method stub
		FingerprintMg FingerprintMg2 = byIdFingerprintMg(FingerprintMg.getId());
		if (FingerprintMg2 != null) {
			BeanUtils.copyProperties(FingerprintMg, FingerprintMg2, new String[] { "id", "addTime", "addPName" });
			if (totalDao.update(FingerprintMg2))
				return "修改成功！";
			else
				return "修改失败!";
		}
		return "不存在该条数据，修改失败!";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

	@Override
	public List byUserIdFing(Integer id) {
		return totalDao.query("from FingerprintMg where users.id = ? and type = '0'", id);
	}

	@Override
	public List byUserIdAcE(Integer id) {
		// TODO Auto-generated method stub
		// List<DoorBangDing> dbds = totalDao.query("from DoorBangDing where
		// fk_security_id = ? and fk_acEq_id in (select id AccessEquipment where
		// equipmentDaoType = '指纹机')", id);
		// List<AccessEquipment> accessEquipmentList = new
		// ArrayList<AccessEquipment>();
		// for (DoorBangDing doorBangDing : dbds) {
		// AccessEquipment accessEquipment = (AccessEquipment)
		// totalDao.getObjectById(AccessEquipment.class,
		// doorBangDing.getFk_acEq_id());
		// if (accessEquipment!=null)
		// accessEquipment.setFingId(doorBangDing.getFk_security_id());//将指纹ID赋值进去
		// accessEquipmentList.add(accessEquipment);
		// }
		return totalDao.query(
				"from AccessEquipment where equipmentDaoType = '指纹机' and id in (select fk_acEq_id from DoorBangDing where fk_security_id = ?)",
				id);
	}

	@Override
	public List byUserIdAllAcE(Integer id) {
		// TODO Auto-generated method stub
		// List<DoorBangDing> dbds = totalDao.query("from DoorBangDing where
		// fk_security_id <> ? and fk_acEq_id in (select id AccessEquipment
		// where equipmentDaoType = '指纹机')", id);
		// List<AccessEquipment> accessEquipmentList = new
		// ArrayList<AccessEquipment>();
		// for (DoorBangDing doorBangDing : dbds) {
		// AccessEquipment accessEquipment = (AccessEquipment)
		// totalDao.getObjectById(AccessEquipment.class,
		// doorBangDing.getFk_acEq_id());
		// if (accessEquipment!=null)
		// accessEquipment.setFingId(doorBangDing.getFk_security_id());//将指纹ID赋值进去
		// accessEquipmentList.add(accessEquipment);
		// }
		return totalDao.query(
				"from AccessEquipment where equipmentDaoType = '指纹机' and id not in (select fk_acEq_id from DoorBangDing where fk_security_id = ?)",
				id);
	}

	@Override
	public String doorbangdingFings(Integer[] aceId, FingerprintMg fings) {
		// TODO Auto-generated method stub
		boolean b = false;
		if (fings != null) {
			if (aceId != null && aceId.length > 0) {
				for (int i = 0; i < aceId.length; i++) {
					AccessEquipment accessEquipment2 = (AccessEquipment) totalDao.getObjectById(AccessEquipment.class,
							aceId[i]);// 查询用户
					if (accessEquipment2 != null) {
						// 查询考勤机最大的编号不能大于1000条
						String hql = "select max(number) from DoorBangDing where fk_acEq_id = ?";
						Integer number = (Integer) this.totalDao.getObjectByCondition(hql, accessEquipment2.getId());
						int i1 = 1;
						if (number != null) {
							if (number < 1000)
								i1 = number + 1;
							else
								continue;
						}
						DoorBangDing bangDing = new DoorBangDing();
						bangDing.setFk_user_id(fings.getUsers().getId());
						bangDing.setFk_security_id(fings.getId());
						bangDing.setNumber(i1);
						bangDing.setFk_acEq_id(aceId[i]);
						bangDing.setStatus("待下发");
						b = totalDao.save(bangDing);
					} else {
						System.out.println("null");
					}
				}
				if (b) {
					return "添加成功";
				}

			}
		}
		return "添加失败";
	}

	@Override
	public Users byIdUserId(Integer id) {
		// TODO Auto-generated method stub
		return (Users) totalDao.getObjectById(Users.class, id);
	}

	@Override
	public List byUserIdAgrennAcE(Integer id) {
		// TODO Auto-generated method stub
		// List<DoorBangDing> dbds = totalDao.query("from DoorBangDing where
		// fk_security_id <> ? and fk_acEq_id in (select id AccessEquipment
		// where equipmentDaoType = '指纹机')", id);
		// List<AccessEquipment> accessEquipmentList = new
		// ArrayList<AccessEquipment>();
		// for (DoorBangDing doorBangDing : dbds) {
		// AccessEquipment accessEquipment = (AccessEquipment)
		// totalDao.getObjectById(AccessEquipment.class,
		// doorBangDing.getFk_acEq_id());
		// if (accessEquipment!=null)
		// accessEquipment.setFingId(doorBangDing.getFk_security_id());//将指纹ID赋值进去
		// accessEquipmentList.add(accessEquipment);
		// }
		return totalDao.query(
				"from AccessEquipment where equipmentDaoType = '指纹机' and id not in (select fk_acEq_id from DoorBangDing where fk_security_id = ?)",
				id);
	}

	@Override
	public String updateIdentification(String Identification) {
		// 根据最大id
		FingerprintMg fm = (FingerprintMg) totalDao
				.getObjectByCondition("from  FingerprintMg  order by id desc LIMIT 1");
		fm.setIdentification(Identification);
		boolean pand = totalDao.update(fm);
		if (pand) {
			return "true";
		} else {
			return "修改失败";
		}

	}

	@Override
	public FingerprintMg seltime(String addtime) {
		FingerprintMg fg = (FingerprintMg) totalDao.getObjectByQuery("from FingerprintMg where addTime=?", addtime);
		return fg;
	}

}
