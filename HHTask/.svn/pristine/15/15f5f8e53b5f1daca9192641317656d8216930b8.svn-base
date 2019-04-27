package com.task.Server.zhuseroffer;
import java.util.List;
import java.util.Map;

import com.task.entity.zhuseroffer.NoPriceprocess;
import com.task.entity.zhuseroffer.SumProcess;
import com.task.entity.zhuseroffer.ZhuserOffer;
import com.tast.entity.zhaobiao.ZhUser;

public interface NoPriceprocessServer {
	public Map<Integer, Object> findAll(NoPriceprocess noPriceprocess,int pageNo, int pageSize,String s);
	public Map<Integer, Object> findZhuser(ZhUser zhUser,int pageNo, int pageSize);
	public Map<Integer, Object> baojiaForProcess(ZhuserOffer zhuserOffer,int pageNo, int pageSize);
	public NoPriceprocess findNoPriceprocessByid(Integer id);
	public String bandZhuser(Integer[] zhUserId,Integer noPriceprocessId);
	public String baojiaForZhuser(ZhuserOffer zhuserOffer);
	public ZhuserOffer findzhuserOffer(Integer id);
	public List<ZhuserOffer> querenYemian(Integer noPriceprocessId);
	//确认报价
	public String querenPrice(Integer[] zhOfferId,Integer noPriceprocessId);
	public String passYangpin(Integer[] zhOfferId,Integer noPriceprocessId);
	public NoPriceprocess getById(Integer id);
	public boolean addTime(Integer noPriceprocessId,String deadline);
	public String luruPrice(NoPriceprocess noPriceprocess);
	public Map<Integer, Object> findAllBys(NoPriceprocess noPriceprocess,int pageNo, int pageSize);
	public List<SumProcess> findAllSumProcess(String tag);
	/**
	 * 准备需要报价的工序for填写报价周期
	 * @param offerId
	 * @return
	 */
	public List<NoPriceprocess> zhouqiForMarkId(String offerId);
	public boolean addTimeForMarkId(String offerId,String deadline);
	public List<NoPriceprocess> findSumProcessById(Integer sumProcessId);
	public SumProcess findOneByid(Integer id);
	public String bandZhuserForMarkId(Integer[] zhUserId,Integer sumProcessId);
	public List<SumProcess> findSumProcessForZhuser();
	public List<ZhuserOffer>  zhuserOfferFor(Integer id);
	public List<NoPriceprocess>  zhuserOfferForQr(Integer id);
	public String deleteSumProcess(Integer id);
}
