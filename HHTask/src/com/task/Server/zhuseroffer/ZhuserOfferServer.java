package com.task.Server.zhuseroffer;

import java.util.List;
import java.util.Map;

import com.task.entity.sop.ycl.YuanclAndWaigj;
import com.task.entity.zhuseroffer.Sample;
import com.task.entity.zhuseroffer.SumProcess;
import com.task.entity.zhuseroffer.ZhuserOffer;

public interface ZhuserOfferServer {
	/*
	 * 根据登陆供应商查询所有标价
	 * */
	public Map<Integer, Object> findListByZhUser(ZhuserOffer zhuseroffer,int pageNo, int pageSize);
	public ZhuserOffer findZhOfferById(Integer id);
	public boolean updateZhOffer(ZhuserOffer zhuseroffer);
	public String updateQurenOffer(Integer[] offerId,Integer id);
	public Map<Integer, Object> findQueRenListByZhUser(ZhuserOffer zhuseroffer,int pageNo, int pageSize);
	public String addSample(ZhuserOffer zhuseroffer,Sample sample,String status);
	public Map<Integer, Object> findDailuRuListByZhUser(ZhuserOffer zhuseroffer,int pageNo, int pageSize);
	public Map<Integer, Object> findQueRenList(ZhuserOffer zhuseroffer,int pageNo, int pageSize);
	public Map<Integer, Object> findListByStatus(ZhuserOffer zhuseroffer,int pageNo, int pageSize);
	public Map<Integer, Object> findAllSample(Sample sample,int pageNo, int pageSize);
	public Map<Integer, Object> findAllSampleByZhUser(Sample sample,int pageNo, int pageSize);
	public Sample findSampleById(Integer id);
	public ZhuserOffer findZhOfferBySam(Integer id);
	public Map<Integer, Object> findAllSampleByCaiGou(Sample sample,
			int pageNo, int pageSize,String status);
	public String updateSample(Sample sample,String status);
	public String inputPrice(YuanclAndWaigj yuanclAndWaigj);
	public Map<Integer, Object> findyuancailByStatus(YuanclAndWaigj yuanclAndWaigouj,int pageNo, int pageSize);
	public Map<String, String> processTemplateFileList1(Integer id);
	public Map<String, String> findbjtzList(Integer id);
	public Sample findSampleBySam(Integer id);
	public String shenpiZhoffer(Integer zhuserOfferId,String status);
	public String passYangpin(Integer[] zhOfferId,Integer yuanclAndWaigjId);
	/**
	 * 手动添加样品
	 */
	public List<Sample> addSampleByself(Sample sample);
	public Map<String, String> findprocessTemplateFileList2(Integer id);
	public Map<String, String> findpicforSumProcess(Integer id);
	public SumProcess findSumProcessByid(Integer id);
	
}
