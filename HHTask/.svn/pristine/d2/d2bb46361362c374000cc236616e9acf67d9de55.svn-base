package com.task.Server.zhaobiao;

import java.util.List;

import com.task.entity.GoodsStore;
import com.task.entity.OrderManager;
import com.task.entity.Users;
import com.tast.entity.zhaobiao.Dingdanzhuanhua;
import com.tast.entity.zhaobiao.InternalOrderDetailzhaobiao;
import com.tast.entity.zhaobiao.InternalOrderzhaobiao;
import com.tast.entity.zhaobiao.Rukudan;
import com.tast.entity.zhaobiao.Zhaobiao;
import com.tast.entity.zhaobiao.ZhaobiaoXis;
import com.tast.entity.zhaobiao.Zhtoubiao;
import com.tast.entity.zhaobiao.jihuadan;

public interface DingdanServer {

	Object[] listOrderManager(OrderManager oManager, Integer parseInt, Integer pageSize);


	List listAllXunahong(int[] selected);


	Object[] integrationOrderDetail(int[] selected);


	void batchConversionOrder(String[] pieceNum, Float[] selected,
			String[] remerk, String orderNum, String title);


	Object[] listInternalOrderzhaobiao(
			InternalOrderzhaobiao internalOrderzhaobiao, Integer parseInt,
			Integer pageSize);


	Object[] listInternalOrderDetail(InternalOrderDetailzhaobiao oManager, Integer parseInt,
			Integer pageSize, Integer id);


	Object[] listjihuadan(Integer id, Dingdanzhuanhua dingdanzhuanhua, Integer parseInt,
			Integer pageSize);


	List ByInternalOrderzhaobiaoId(Integer id);


	InternalOrderzhaobiao byIDOrder(Integer id);


	Object[] integrationOrderDetail1(int[] selected);


	List findAllDept();


	void addzhaobiaoAddZhabiaoXi(Zhaobiao zhaobiao, ZhaobiaoXis zhaobiaoXis);


	List listAlljihuaByinternalOrderID();


	List listxuanzhejihua(int[] selected);


	void addDingdanzhuanhua(String[] nams, Float[] zongshuliangs,
			String[] danweis, String[] guiges, Float[] caigous,
			String[] shijians, Integer[] oderids, Integer[] dateilids,
			String[] lexings, String[] dingdanIds, String[] jihuanid);


	List showcaigou(Integer id);


	InternalOrderzhaobiao byIdinternalOrderzhaobiao(Integer integer);


	void updatetoubiao(InternalOrderzhaobiao internalOrderzhaobiao);


	Object[] listInternalOrderzhaobiao1(
			InternalOrderzhaobiao internalOrderzhaobiao, Integer parseInt,
			Integer pageSize);


	Object[] listxiangxi(Integer id, Dingdanzhuanhua dingdanzhuanhua, Integer parseInt,
			Integer pageSize);


	Dingdanzhuanhua byIDdingdan(Integer id);


	void addRukudan(Rukudan rukudan);


	void updateDingdanzhuanhua(Dingdanzhuanhua dingdanzhuanhua);


	Object[] listRukudan(Rukudan rukudan, Integer parseInt, Integer pageSize);


	Object[] rukuchaxun(Integer id, Zhtoubiao zhtoubiao, Integer parseInt, Integer pageSize);


	Zhtoubiao byIdzhtoubiao(Integer tid);


	void updatezhtoubiao(Zhtoubiao zhtoubiao);


	Rukudan ByIdrukudan(Integer id);


	void updateRukudan(Rukudan rukudan);


	void saveSgrk(GoodsStore newgs);


	Object[] listLoginUsers(Users users, Integer parseInt, Integer pageSize);


	List listUserstatus();


	List listLoginUsers();


	
}
