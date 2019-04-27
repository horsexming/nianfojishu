package com.task.Server.pro;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.task.entity.Price;
import com.task.entity.Users;
import com.task.entity.project.Investor;
import com.task.entity.project.InvestorEvaluation;
import com.task.entity.project.InvestorMonthJx;
import com.task.entity.project.InvestorOfQuotedPrice;
import com.task.entity.project.ProjectTime;
import com.task.entity.project.QuotedPrice;
import com.task.entity.project.QuotedPriceCost;
import com.task.entity.project.QuotedPriceFenhong;
import com.task.entity.project.QuotedPriceFile;
import com.task.entity.project.QuotedPriceLog;
import com.task.entity.project.QuotedPriceUserCost;
import com.task.entity.project.QuotedPricejy;
import com.task.entity.project.QuotedPricejyDetail;
import com.task.entity.project.QuotedPricejyDetailFile;
import com.task.entity.project.QuotedPricejyUsers;
import com.task.entity.project.QuotedProcessInfor;
import com.task.entity.project.RechargeRecord;
import com.task.entity.sop.OutSourcingApp;

/***
 * 核算Server层
 * 
 * @author 刘培
 * 
 */
@SuppressWarnings("unchecked")
public interface QuotedPriceServer {
	/***
	 * 添加核算信息
	 * 
	 * @param projectManage
	 */
	void addQuotedPrice(QuotedPrice quotedPrice, File[] attachment,
			String[] attachmentFileName);

	/***
	 * 添加工艺规范Tree
	 * 
	 * @param projectManage
	 */
	void add(QuotedPrice quotedPrice);

	/***
	 * 删除核算信息
	 * 
	 * @param QuotedPrice
	 */
	void delQuotedPrice(QuotedPrice quotedPrice);

	/***
	 * 修改核算信息
	 * 
	 * @param QuotedPrice
	 */
	void updateQuotedPrice(QuotedPrice quotedPrice);

	/***
	 * 查询核算信息
	 * 
	 * @param id
	 *            主键id
	 */
	QuotedPrice afindQuotedPrice(Integer id);

	/***
	 * 查询核算信息(条件查询、分页)
	 * 
	 * @param quotedPrice
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Object[] findPMByCondition(QuotedPrice quotedPrice, int pageNo,
			int pageSize, String pageStatus);
	
	/***
	 * 根据件号查询核算信息(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findQuotedPriceList(String markId, Integer pageNo, Integer pageSize);
	/***
	 * 外委(外采）产品申报单(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findOutSourcingAppList(String markId, Integer pageNo, Integer pageSize);
	/***
	 * 外委(外采）产品申报单(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findBargainList(String markId, Integer pageNo, Integer pageSize);
	/***
	 * 采购执行单(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findPerformsingleList(String markId, Integer pageNo, Integer pageSize);
	/***
	 * 合同(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findBarContractList(String markId, Integer pageNo, Integer pageSize);
	/***
	 * 价格(15条)
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findPricesList(String markId, Integer pageNo, Integer pageSize);

	/***
	 * 修改
	 * 
	 * @param QuotedPrice
	 */
	void update(QuotedPrice quotedPrice);

	/***
	 * 根据rootId查询这个树形数据
	 * 
	 * @param id
	 * @return
	 */
	List findAllForTree(int rootId);

	/***
	 * 查询工艺规范单条明细
	 * 
	 * @param id
	 * @return
	 */
	Object[] findQpDetailForShow(int id);

	/***
	 * 删除
	 * 
	 * @param QuotedPrice
	 */
	void del(QuotedPrice quotedPrice);

	/***
	 * 添加工序
	 * 
	 * @param qpInfor
	 */
	void addQpInfor(QuotedProcessInfor qpInfor);

	/***
	 * 查询件号对应的所有工序信息
	 * 
	 * @param fkId
	 *            产品id
	 * @return
	 */
	List findQpInforByFkid(Integer fkId);

	/***
	 * 删除工序
	 * 
	 * @param qpInfor
	 */
	void delQpInfor(QuotedProcessInfor qpInfor);

	/***
	 *根据Id查询工序
	 * 
	 * @param id
	 */
	QuotedProcessInfor findQpInfor(Integer id);

	/**
	 * 获取所有的工序
	 * 
	 * @return
	 */
	List findQpInforAll();

	/**
	 * 各部门录入各部分数据
	 * 
	 * @param qpInfor
	 * @param pageStatus
	 */
	void updateDeptLuru(QuotedProcessInfor qpInfor, String pageStatus);

	/**
	 * 材料费、外购、财务风水电气费 填报
	 */
	void updateQuotedPriceForcl(QuotedPrice qp, String pageStatus);

	/***
	 * 根据项目id和类别查询时间表
	 * 
	 * @param proId
	 *            项目id
	 * @param pageStatus
	 *            类别
	 * @return
	 */
	ProjectTime findProjectTime(Integer quoId, String pageStatus);

	/***
	 * 报价汇总
	 * 
	 * @return
	 */
	List findAllPrice(Integer id);

	/***
	 * 报价总额
	 * 
	 * @return
	 */
	Float findAllMoney(Integer id);

	/***
	 * 查询所有待录入的报价信息
	 * 
	 * @param pageStatus
	 * @return
	 */
	List findNeedLuru(String pageStatus);

	/***
	 * 显示计算人工节拍页面
	 */
	String packageData(Integer id, Map map);

	String trial(Integer id);

	/****
	 * 查询工序
	 * 
	 * @param qpInforId
	 * @return
	 */
	QuotedProcessInfor showProcess(Integer qpInforId);

	/****
	 * 修改工序
	 * 
	 * @param qpInforId
	 * @return
	 */
	void updateProcess(QuotedProcessInfor qpInfor);
    /**
     * 启动项目
     * @param id
     * @return
     */
	boolean updateStartProject(int id);
    /**
     * 根据rootId提交整个项目启动中的bom,开始项目跟踪
     * @param rootId
     * @return
     */
	boolean updateSubmitProject(int rootId);
    /**
     * 根据报价系统的rootid获取其报价日志
     * @param rootId
     * @return
     */
	List<QuotedPriceLog> getQuotedPriceLog(int rootId);
    /**
     * 添加报价日志
     * @param quotedPriceLog
     * @param random 文件名后四位
     * @return
     */
	boolean addQuotedPriceLog(QuotedPriceLog quotedPriceLog);
    /**
     * 通过id删除报价日志
     * @param id
     * @return
     */
	boolean deleteQPLogByid(Integer id);
	/**
     * 通过id查找报价日志
     * @param id
     * @return
     */
	QuotedPriceLog getQpLogById(Integer id);
    /**
     * 修改报价日志
     * @param quotedPriceLog
     * @return
     */
	boolean updateQuotedPriceLog(QuotedPriceLog quotedPriceLog);
	/**
	 * ajax 获取含有少许字符的全字符
	 */
	List<QuotedPrice> getAllNames(String markId);
    /**
     * 通过markId查找一个报价零件
     * @param markId
     * @return
     */
	QuotedPrice findQuotedPriceByMarkId(String markId);
    /**
     * 将qp2下的工序复制到qp1下
     * @param qp1
     * @param qp2
     * @return
     */
	boolean saveCopyProcess(QuotedPrice qp1, QuotedPrice qp2);
    /**
     *  复制qp2及其下的所有零件和工序到qp1下
     * @param qp1
     * @param qp2
     * @return
     */
	boolean saveCopyQuotedPrice(QuotedPrice qp1, QuotedPrice qp2);
    /**
     * 获取所有MarkId
     * @return
     */
	List<String> getAllMarkId();
    /**
     * 更新件号
     * @param id
     * @param markId
     * @param isAll
     * @return
     */
	boolean updateMarkId(int id, String markId, Boolean isAll);
	/**
	  * 已选中的零件为主同步所有同件号的零件
	  */
	boolean updateQuotedPrice(int id);
	/**
	 * 移动报价零件
	 * @return
	 */
	boolean saveMoveQuotedPrice(Integer moveId, Integer targetId);
    /**
     * 根据报价bom的id获取整个bom的外委外购评审状态
     * @param id
     * @return
     */
	public List<OutSourcingApp> findOsa(int id);
    /**
     * 发送外购外委评审提醒
     * @param ids
     * @return
     */
	public String sendOsaNotify(String ids);
    /**
     * 根据报价bom的rootId将整个bom打入到试制bom
     * @param rootId
     * @return
     */
	Map<Integer, Object> buildProcardTemp(Integer rootId,String productStyle);
    /**
     * 修改报价bom的状态
     * @param rootId
     * @param string
     */
	void updateStatus(Integer rootId, String string);
	/**
	 * 根据件号，产品类别，获得产品;
	 */
	/**
	 * 各项费用填报
	 * @param quotedPrice
	 * @param parseInt
	 * @param pageSize
	 * @param pageStatus
	 * @return
	 */
	Object[] findQpForCost(QuotedPrice quotedPrice, int parseInt, int pageSize,
			String pageStatus);
	/**
	 * 项目费用填报明细
	 * @param id
	 * @return
	 */
	List<QuotedPriceCost> getQpCostDetail(int id);
	/**
	 * 添加项目费用
	 * @param quotedPriceCost
	 * @return
	 */
	boolean addQpCost(QuotedPriceCost quotedPriceCost);
	/**
	 * 
	 * @param id
	 * @param proStatus
	 * @param pageStatus
	 * @return
	 */
	Object[] getQpUserCostMsg(int id, QuotedPriceUserCost qpUserCost, String pageStatus);

	List showCostType(int id);

	String addQpUserCost(QuotedPriceUserCost quotedPriceUserCost);
	/**
	 * 项目下阶段申请
	 * @param id
	 * @return
	 */
	String ApplyQP(int id);
	/**
	 * 重新计算总费用
	 * @param id
	 * @return
	 */
	QuotedPrice changeQpAllFeiYong(int id);
	/**
	 * 查看分红明细
	 * @param id
	 * @return
	 */
	List<QuotedPriceFenhong> getFhList(int id);

	Map<Integer, Object> findInvestorsByCondition(Investor investor,
			int parseInt, int pageSize);

	Object[] findAllUser(Users user,int parseInt, int pageSize);

	List<Users> findAllBangUser();

	String addTzr(Integer[] usersId);
	/**
	 * 取消恢复投资资格
	 * @param id
	 * @param op
	 * @return
	 */
	String updatezg(int id, String op);
	Investor getInvestorById(Integer id);
	/**
	 * 判断当前登录人是否有投资资格
	 * @return
	 */
	String gettzzg();
	/**
	 * 通过Id获取零件对象
	 * @param id
	 * @return
	 */
	QuotedPrice getQPById(int id);
	/**
	 * 设置投资金额
	 * @param quotedPrice
	 * @return
	 */
	String setProTz(QuotedPrice quotedPrice);
	/**
	 * 修改投标状态
	 * @param id
	 * @return
	 */
	String updatefbStatus(int id);

	Float findAllMoneysz(int id);

	Float findAllMoneysj(int id);

	Float findAllMoneyqt(int id);
	/**
	 * 通过项目获取申报明细
	 * @param id
	 * @param source
	 * @return
	 */
	List<QuotedPriceCost> findQpCostList(int id, String source);
	
	/**
	 * 获取当前登录的投资人信息
	 * @return
	 */
	Investor getInvestorByLogin();
	/**
	 * 获取个人绩效工资
	 * @param id
	 * @return
	 */
	Float getSelfJxgx(int id);
	/**
	 * 获取投资人的充值记录
	 * @param id
	 * @return
	 */
	List<RechargeRecord> getRechargeRecords(Integer id);

	InvestorMonthJx saveOrGetInvestorMonthJxsById(Integer id);
	/**
	 * 充值
	 * @param id 投资人Id
	 * @param money 充值金额
	 * @return
	 */
	String inmoney(int id, Float money);
	/**
	 * 
	 * @param quotedPriceUserCost
	 * @param investorId
	 * @param parseno
	 * @param pageSize
	 * @return
	 */
	Map<Integer, Object> findQPriceUserCostsByCondition(
			QuotedPriceUserCost quotedPriceUserCost,int investorId, int pageNo, int pageSize,String start,String end);
	/**
	 * 找到投资的零件
	 * @return
	 */
	List<QuotedPrice> findSelectLeaderList();
	/**
	 * 找到零件投资成员
	 * @param id
	 * @return
	 */
	List<Investor> getQpInvestorList(int id);
	/**
	 * 获取零件与投资成员对应表对象
	 * @param id
	 * @return
	 */
	List<InvestorOfQuotedPrice> getInvestorOfQpList(int id);
	/**
	 * 推选组长
	 * @param qpId
	 * @param investorId
	 * @return
	 */
	String saveLeader(Integer qpId, Integer investorId);
	/**
	 * 通过id获取项目对应投资人员
	 * @param id2
	 * @return
	 */
	InvestorOfQuotedPrice getInvestorOfQpById(int id);
	/**
	 * 获取对组长的评价
	 * @param id组长Id（对应零件的投资对象）
	 * @return
	 */
	List<InvestorEvaluation> findieList(int id);
	/**
	 * 评价组长
	 * @param investorEvaluation
	 * @return
	 */
	Map<Integer, Object> evaluationartner(InvestorEvaluation investorEvaluation);
	/**
	 * 获取零件实际成本
	 * @param id
	 * @return
	 */
	List<QuotedPriceCost> findQpTrueCostList(int id);
	/**
	 * 所有的外购件的已有价格
	 * @param markId   (件号)
	 * @return
	 */
	List<Price> findAllOutPrice(String markId);
	/**
	 * 导入报价BOM
	 * @param addquotedPrice
	 * @return
	 */
	public String pladdQuotedPrice(File addquotedPrice,String fileName,QuotedPrice quotedPrice
			,String productStyle);
	
	public String addQuotedPriceTree(QuotedPrice fatherqp,
			List<QuotedPrice> qpList, Integer rootId, Integer i,
			String zcMarkId, String ywmarkId, int base, int bomtype,Users user);
	/**
	 * 获取产品图纸
	 * @param id
	 * @return
	 */
	List<Object> findQuotedPriceTz(int id);
	/**
	 * 报价零件图纸上传
	 * @param quotedPriceFile
	 * @param id
	 * @param ytRadio
	 * @return
	 */
	String saveQuotedPriceFile(QuotedPriceFile quotedPriceFile, int id,
			String ytRadio);
	/**
	 * 获取工序图纸
	 * @param id
	 * @return
	 */
	List<Object> getProcessTz(int id);
	/**
	 * 上传工序图纸
	 * @param quotedPriceFile
	 * @param id
	 * @return
	 */
	String saveQuotedPriceProcessFile(QuotedPriceFile quotedPriceFile, int id);
	/**
	 * 通过Id 获取图纸对象
	 * @param id
	 * @return
	 */
	QuotedPriceFile findTzById(int id);
	/**
	 * 删除图纸
	 * @param id
	 * @return
	 */
	boolean deleteTz(Integer id);
	/**
	 * 通过零件id获取保密状态
	 * @param id
	 * @return
	 */
	String getIsbaomiByqpId(Integer id);
	/**
	 * 导入保密bom
	 * @param accessory
	 * @param accessoryFileName
	 * @param quotedPrice
	 * @return
	 */
	String daorubmQuotedPrice(File accessory, String accessoryFileName,
			QuotedPrice quotedPrice);
	/**
	 * 获取生产件下的进度纪要
	 * @param id
	 * @param name 
	 * @param code 
	 * @param jyContext 
	 * @param title 
	 * @param end 
	 * @param start 
	 * @return
	 */
	List<QuotedPricejy> findjyListByqpId(int id, String start, String end, String title, String jyContext, String code, String name);
	/**
	 * 获取生产件下的进度纪要
	 * @param id
	 * @param name 
	 * @param code 
	 * @param jyContext 
	 * @param title 
	 * @param end 
	 * @param start 
	 * @return
	 */
	List<QuotedPricejy> findjyListnoqpId( String start, String end, String title, String jyContext, String code, String name);
	/**
	 * 添加进度纪要
	 * @param qpjy
	 * @param id
	 * @return
	 */
	String addjy(QuotedPricejy qpjy, int id);
	/**
	 * 添加进度纪要
	 * @param qpjy
	 * @param id
	 * @return
	 */
	String addjy(QuotedPricejy qpjy);

	/*
	    * @author fy
	　　* @date 2018/6/4 10:53
	　　* @Description: 导入会议纪要
	　　* @param [jy]
	　　* @return java.lang.String
	　　* @throws
	　　*/
	String importjy(File jy);

	/**
	 * 查询生产件下的待处理纪要
	 * @param id
	 * @param name 
	 * @param code 
	 * @param jyContext 
	 * @param title 
	 * @param end 
	 * @param start 
	 * @return
	 */
	List<QuotedPricejy> findDcljyListByqpId(int id, String start, String end, String title, String jyContext, String code, String name);
	/**
	 * 查询生产件下的待处理纪要
	 * @param id
	 * @param name 
	 * @param code 
	 * @param jyContext 
	 * @param title 
	 * @param end 
	 * @param start 
	 * @return
	 */
	List<QuotedPricejy> findDcljyListnoqpId( String start, String end, String title, String jyContext, String code, String name);
	/**
	 * 通过纪要id获取下面的指派人员
	 * @param id
	 * @return
	 */
	List<QuotedPricejyUsers> findJyUsersByJyId(int id);
	List<QuotedPricejy> findselfDcljyList(String tag);

	List<QuotedPricejy> findselfjyList(String tag);
	/**
	 * 查询待审批纪要
	 * @return
	 */
	List<QuotedPricejy> finddspjyList(String tag);
	/**
	 * 查询所有进度纪要
	 * @return
	 */
	List<QuotedPricejy> findAlljyList(String tag);
	/**
	 * 指派纪要人员
	 * @param qpjyUser
	 * @return
	 */
	Object[] jydispatche(QuotedPricejyUsers qpjyUser);
	/**
	 * 取消纪要人员
	 * @param id
	 * @return
	 */
	String jydispatcheremove(int id);
	/**
	 * 通过Id 获取纪要明细
	 * @param id
	 * @return
	 */
	QuotedPricejyDetail getQpjydById(int id);
	/**
	 * 添加纪要明细文件
	 * @param qpjyd
	 * @param qpjdFileList
	 * @return
	 */
	String saveQuotedPricejydFile(QuotedPricejyDetail qpjyd,
			List<QuotedPricejyDetailFile> qpjdFileList);
	/**
	 * 通过纪要明细查询其下文件
	 * @param id
	 * @return
	 */
	List<QuotedPricejyDetailFile> findjyfilesByjydId(int id);
	/**
	 * 同过id获取纪要文件
	 * @param id
	 * @return
	 */
	QuotedPricejyDetailFile agetjydFileById(int id);
	/**
	 * 审批进度纪要执行方案
	 * @param id
	 * @param op
	 * @return
	 */
	String spjyd(QuotedPricejyDetail qpjyd, String op);

	String saveQuotedPricejymsFile(QuotedPricejyDetailFile qpjydFile);
	/**
	 * 删除纪要文件
	 * @param id
	 * @param path
	 * @return
	 */
	Object[]  deletejyFile(int id, String path);
	/**
	 * 获取进度纪要及明细
	 * @param id
	 * @return
	 */
	QuotedPricejy getQpjyAndDetailById(int id);
	/**
	 * 编辑纪要
	 * @param qpjy
	 * @return
	 */
	String editjy(QuotedPricejy qpjy);
	/**
	 * 生成BOM采购清单
	 * @param quotedPrice
	 * @return
	 */
	String addqpBommatrial(QuotedPrice quotedPrice);
	/**
	 * 删除纪要
	 * @param id
	 * @return
	 */
	Object[] deletejy(int id);

	Object[] deletehyjy(int id);

	/**
	 * 根据必要的信息查询？？
	 */
	List<QuotedPrice> getQuotedPriceByCon(Integer id);
	
	/**
	 * 导出报价BOM
	 */
	void exportQuotedPrice(Integer rootId);

}
