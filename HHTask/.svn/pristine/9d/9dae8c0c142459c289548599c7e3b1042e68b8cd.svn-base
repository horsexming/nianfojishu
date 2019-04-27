package com.task.Server;

import java.util.List;

import com.task.entity.Favorite;
import com.task.entity.payment.FundApply;

public interface FavoriteServer {

	Boolean addFavorite(Favorite favorite);

	Boolean deleteFavorite(Integer favoriteid);

	List<Favorite> findAllFavorite(Integer usersid);

	String findMFcolor(Integer id);
	
	/**
	 * 收藏夹功能是否有权限
	 * @param userid 用户id
	 * @return 无权限功能id list
	 */
	List<Integer> findFavoriteMFpermission(Integer userid);

	String updateDmlAppFileUrl(int id);

}
