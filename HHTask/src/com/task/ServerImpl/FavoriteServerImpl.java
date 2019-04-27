package com.task.ServerImpl;

import java.util.List;

import com.task.Dao.TotalDao;
import com.task.Server.FavoriteServer;
import com.task.entity.Favorite;
import com.task.entity.payment.FundApply;
import com.task.util.Util;

/**
 * 收藏夹功能Server实现类
 * 
 * @author fy
 * 
 */
@SuppressWarnings("unchecked")
public class FavoriteServerImpl implements FavoriteServer {
	TotalDao totalDao;

	@Override
	public Boolean addFavorite(Favorite favorite) {
		if (favorite != null) {
			String hql = "from Favorite where mfid=? and usersid=?";
			List<Favorite> list = totalDao.query(hql, favorite.getMfid(),
					favorite.getUsersid());
			if (list.size() == 0) {
				totalDao.save(favorite);
				return true;
			} else {
				return false;
			}

		}
		return false;
	}

	@Override
	public Boolean deleteFavorite(Integer favoriteid) {
		if (favoriteid != null) {
			Favorite favorite = (Favorite) totalDao.getObjectById(
					Favorite.class, favoriteid);
			totalDao.delete(favorite);
			return true;
		}
		return false;
	}

	/**
	 * 修改时间优先显示
	 */
	@Override
	public String updateDmlAppFileUrl(int id) {
		Favorite favorite=(Favorite)totalDao.getObjectByCondition("from Favorite where id=?", id);
		favorite.setUpdateTime(Util.getDateTime());
		totalDao.update(favorite);
		return "true";
	}
	
	/**
	 * 显示收藏夹
	 */
	@Override
	public List<Favorite> findAllFavorite(Integer usersid) {
		String hql = "from Favorite where usersid=? order by updateTime desc";
		List<Favorite> list = totalDao.query(hql, usersid);
		return list;
	}

	@Override
	public String findMFcolor(Integer id) {
		String hql = "select bgColor from ModuleFunction where id=?";
		List list = totalDao.query(hql, id);
		return list.get(0).toString();
	}

	@Override
	public List<Integer> findFavoriteMFpermission(Integer userid) {
		// SELECT * from ta_sys_favorite f where usersid=1230 AND f.mfid not in(SELECT ta_mfId from ta_usersMF where ta_userId=1230
		String hql = "select id from Favorite f where usersid=? AND f.mfid NOT IN (select mf.id from ModuleFunction mf join mf.users u where u.id=?) ";
		List<Integer> list =totalDao.query(hql, userid, userid);
		return list;
	}

	public TotalDao getTotalDao() {
		return totalDao;
	}

	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}



}
