package com.task.Server.zhaobiao;

import java.io.File;
import java.util.List;

import com.task.entity.Users;
import com.task.entity.sop.ProcardTemplate;
import com.task.entity.sop.ProcessTemplate;
import com.tast.entity.zhaobiao.Baofei;
import com.tast.entity.zhaobiao.GysMarkIdjiepai;
import com.tast.entity.zhaobiao.ProcessMarkIdZijian;
import com.tast.entity.zhaobiao.Waigoujianpinci;
import com.tast.entity.zhaobiao.WaigoujianpinciZi;
import com.tast.entity.zhaobiao.ZhUser;
import com.tast.entity.zhaobiao.Zh_CaozuoDengji;
import com.tast.entity.zhaobiao.Zh_CaozuoEmp;
import com.tast.entity.zhaobiao.Zh_caozuo;
import com.tast.entity.zhaobiao.Zh_shebei;

public interface MarkIdServer {

	List<GysMarkIdjiepai> findProcardTemByRootId(int id);

	Object[] findCardTemForShow(int id);

	GysMarkIdjiepai findProcardTemById(int id);

	boolean updateProcardTemplate(GysMarkIdjiepai gysMarkIdjiepai);

	boolean delProcardTemplate(GysMarkIdjiepai oldProCard);

	boolean addProcardTemplate(GysMarkIdjiepai gysMarkIdjiepai);

	boolean addProcessTemplate(ProcessMarkIdZijian pIdZijian);

	List findProcessByFkId(Integer id);

	ProcessMarkIdZijian findProcessT(Integer id);

	String updateProcessT(ProcessMarkIdZijian pIdZijian);

	void delProcessT(ProcessMarkIdZijian pt);

	Object[] listtianxiejiepai(GysMarkIdjiepai gysMarkIdjiepai,
			Integer parseInt, Integer pageSize);

	ZhUser listByIdZhUser(Integer userid);

	ZhUser listByIdZhUserId(Integer id);

	void updateZhusers(ZhUser newzhUser);

	void jiesuan(Float gongzi, Integer id);

	Object[] listgysMarkIdjiepai(GysMarkIdjiepai gysMarkIdjiepai,
			Integer parseInt, Integer pageSize);

	Object[] listBom(ProcardTemplate procardTemplate, Integer parseInt,
			Integer pageSize);

	Object[] listgongxu(ProcessTemplate processTemplate, Integer zhuserId,
			Integer cid, Integer parseInt, Integer pageSize);

	List listYigongxu(Integer id, Integer integer);

	void bandinggongxu(int[] selected, Integer integer, Integer integer2);

	Object[] listWaiweiGongxu(ProcessMarkIdZijian pIdZijian, Integer parseInt,
			Integer pageSize, Integer pageSize2);

	void updateProcessMarkIdZijian(ProcessMarkIdZijian newp);

	Object[] listWaigoujianpinci(Waigoujianpinci waigoujianpinci,
			Integer parseInt, Integer pageSize,String status);

	void addwaigoujianpinci(Waigoujianpinci waigoujianpinci);

	Waigoujianpinci ByIdWaigoujianpinci(Integer id);

	void updatewaigoujianpinci(Waigoujianpinci waigoujianpinci);

	void deletewaigoujianpinci(Waigoujianpinci waigoujianpinci);

	Object[] listyanshoupincizi(Integer id,
			WaigoujianpinciZi waigoujianpinciZi, Integer parseInt,
			Integer pageSize);

	void addwaigoujianpinciZi(WaigoujianpinciZi waigoujianpinciZi);

	void deletewaigoujianpinciZi(WaigoujianpinciZi waigoujianpinciZi);

	WaigoujianpinciZi byIdWaigoujianpinciZi(WaigoujianpinciZi waigoujianpinciZi);

	void updatewaigoujianpinciZi(WaigoujianpinciZi waigoujianpinciZi);

	Object[] listBOMwaigou(ProcardTemplate procardTemplate, Integer parseInt,
			Integer pageSize);

	Object[] listwaigoujianyan(Waigoujianpinci waigoujianpinci,
			Integer parseInt, Integer pageSize, Integer pageSize2);

	void bandingWaigouyanshou(int[] selected, Integer id, Float jiepai);

	List bylistAllyiban(Integer id);

	Float byIdWaigoujianpinciZiZUida(Integer id);

	ProcardTemplate ByIdprocardTemplate(Integer id);

	Object[] listGysMarkIdjiepaichakan(GysMarkIdjiepai gysMarkIdjiepai,
			Integer parseInt, Integer pageSize);

	void jiesuan2(Float gongzi, Integer id);
	/**
	 * 批量导入供应供货产品信息;
	 */
	public String pladdGysMarkIdjiepai(File addfile,String status);
	public List<ZhUser> findAllZhUser();
	public Float getSumCgbl(String markId,String kgliao,String banBenNumber);
	
	public String changCgbl(Integer id,Float cgbl,String kgliao);
	
	public Integer getepId(Integer id);
	/**
	 * 添加供应商产品;
	 */
	public String addgysjiepai(GysMarkIdjiepai gysMarkIdjiepai,String gys);

	Object[] SupplyTypeCategory(GysMarkIdjiepai p);
	/**
	 * 
	 */
	 void exprot(GysMarkIdjiepai p);
	 
	 /**
	  * 查询所有巡检标准
	  */
	 List<Waigoujianpinci> findAllJyPc();
}
