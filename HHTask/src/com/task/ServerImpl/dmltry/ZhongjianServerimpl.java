package com.task.ServerImpl.dmltry;

import com.task.Dao.TotalDao;
import com.task.Server.dmltry.ZhongjianServer;
import com.task.entity.dmltry.Zhongjian;
import com.task.entity.menjin.FingerprintMg;

public class ZhongjianServerimpl implements ZhongjianServer {

	private TotalDao totalDao;

	/**
	 * 添加
	 */
	@Override
	public String addZhongjina(Zhongjian zhongjian) {
		if (zhongjian == null) {
			FingerprintMg fm = (FingerprintMg) totalDao
					.getObjectByCondition("from FingerprintMg  WHERE id=(SELECT MAX(id) from FingerprintMg)");
			String can = (String) totalDao.getObjectByCondition("SELECT MAX(canshu) from Zhongjian");
			if (can == null) {
				can = "0";
			}
			Integer canshu = Integer.parseInt(can) + 1;
			String gonString = canshu + "";
			if (gonString.length() == 1) {
				gonString = "000" + gonString;
			} else if (gonString.length() == 2) {
				gonString = "00" + canshu;
			} else if (gonString.length() == 3) {
				gonString = "0" + canshu;
			}
			if (canshu > 3000) {
				System.out.println("参数值大于3000");
				return "添加失败参数值超过最高上限";
			} else {

				Zhongjian zj = new Zhongjian();
				System.out.println(gonString);
				zj.setStatus("未下发");
				zj.setCanshu(gonString);
				zj.setFingerprintMgid(fm.getId());

				zj.setAccessEquipmentid(5);

				boolean b = totalDao.save(zj);
				if (b) {
					return "添加成功";
				} else {
					return "添加失败";
				}
			}
		}
		return "添加失败";
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}

}
