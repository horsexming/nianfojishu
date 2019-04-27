/**
 * 
 */
package com.task.ServerImpl.expresscabinet;

import com.opensymphony.xwork2.ActionContext;
import com.task.Dao.TotalDao;
import com.task.DaoImpl.TotalDaoImpl;
import com.task.Server.expresscabinet.WePayServer;
import com.task.ServerImpl.AttendanceTowServerImpl;
import com.task.ServerImpl.ShortMessageServiceImpl;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.expresscabinet.Charging;
import com.task.entity.expresscabinet.Courier;
import com.task.entity.expresscabinet.CourierCompany;
import com.task.entity.expresscabinet.HttpUtil;
import com.task.entity.expresscabinet.PayCommonUtil;
import com.task.entity.expresscabinet.PayConfigUtil;
import com.task.entity.expresscabinet.RequestHandler;
import com.task.entity.expresscabinet.TenpayUtil;
import com.task.entity.expresscabinet.WeiXinOrder;
import com.task.entity.expresscabinet.XMLUtil;
import com.task.entity.lpanclear.Employee;
import com.task.entity.system.CircuitRun;
import com.task.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jdom.JDOMException;


@SuppressWarnings("unchecked")
public class WePayServerImpl implements WePayServer{
	
	private TotalDao totalDao;
		
	// 在静态方法调用totalDao
	private static TotalDao createTotol() {
		// 获得totalDao
		TotalDao totalDao = TotalDaoImpl.findTotalDao();
		WePayServerImpl wps = new WePayServerImpl();
		wps.setTotalDao(totalDao);
		return totalDao;
	}

	/**
	 * 调用微信统一支付接口-梁盼-2018年12月29日11:18:24
	 * @param userId  用户id
	 * @param productId  商品id
	 * @return
	 */
	public String weixinPay(String userId, String productId,String order_price,String body){
		
		// 账号信息  
        String appid = PayConfigUtil.appid;  // appid  
        String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
        String mch_id = PayConfigUtil.MCH_ID; // 商业号  
        String key = PayConfigUtil.API_KEY; // key  

        String currTime = PayCommonUtil.getCurrTime();  //获取当前时间
        String strTime = currTime.substring(0, currTime.length());  
        String strRandom = PayCommonUtil.buildRandom(11) + "";    //取出一个指定长度大小的随机正整数(方法最大传值length为11)
        String nonce_str = strTime + strRandom;  
        System.out.println("输出一下strTime"+strTime);
        System.out.println("输出一下strRandom"+strRandom);
        System.out.println("输出一下nonce_str"+nonce_str);
        //String order_price = "10"; // 价格   注意：价格的单位是分  (可变)
        //String body = "可乐";   // 商品名称 (可变)
        String out_trade_no = strTime; // 订单号  
        

        //获取发起电脑 ip  
        String spbill_create_ip = PayConfigUtil.CREATE_IP;  
        //回调接口   
        String notify_url = PayConfigUtil.NOTIFY_URL;  
        String trade_type = "NATIVE";  

        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", mch_id);  
        packageParams.put("nonce_str", nonce_str);  
        packageParams.put("body", body);  
        packageParams.put("out_trade_no", out_trade_no);  
        packageParams.put("total_fee", order_price);  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  

        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  

        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
        System.out.println(requestXML);  

        String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);  
        System.out.println(resXml);

        Map map = new Hashtable();
		try {
			map = XMLUtil.doXMLParse(resXml);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        //String return_code = (String) map.get("return_code");  
        //String prepay_id = (String) map.get("prepay_id");  
        String urlCode = (String) map.get("code_url");  
        System.out.println("看看urlCode是什么"+urlCode);
        //对数据进行添加(执行后就添加)
        
        return urlCode; 
	}
	
	
	
	
	
	
	public static WeiXinOrder weixinPay_1(String userId, String productId,String order_price,String body){
		TotalDao totalDao = createTotol();
		String nowDate  = Util.getDate();       //获取yyyy-mm-dd格式的日期
		// 账号信息  
        String appId = PayConfigUtil.appid;  // appid  
        String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
        String mch_Id = PayConfigUtil.MCH_ID; // 商业号  
        String key = PayConfigUtil.API_KEY; // key  

        String currTime = PayCommonUtil.getCurrTime();  //获取当前时间
        String nonce_str = null;
        String out_trade_no = null;
        synchronized(currTime) {
            //一次只能有一个线程进入
        	String strTime = currTime.substring(0, currTime.length());  
            String strRandom = PayCommonUtil.buildRandom(8) + "";    //取出一个指定长度大小的随机正整数
            String strRandom2 = PayCommonUtil.buildRandom(6) + "";    //取出一个指定长度大小的随机正整数
            String strRandom3 = PayCommonUtil.buildRandom(4) + "";    //取出一个指定长度大小的随机正整数
            nonce_str = strTime + strRandom;  
            String strTime2 = strTime + strRandom+strRandom2+strRandom3; 
//            System.out.println("输出一下strTime"+strTime);
//            System.out.println("输出一下strRandom"+strRandom);
//            System.out.println("输出一下nonce_str"+nonce_str);
//            System.out.println("输出strTime"+strTime);
//            System.out.println("输出strRandom"+strRandom);
//            System.out.println("输出strRandom2"+strRandom2);
//            System.out.println("输出strRandom3"+strRandom3);
//            System.out.println("输出nonce_str"+nonce_str);
            //String order_price = "10"; // 价格   注意：价格的单位是分  (可变)
            //String body = "可乐";   // 商品名称 (可变)
            out_trade_no = strTime2; // 订单号
        }
                        
       
        //获取发起电脑 ip  
        String spbill_create_ip = PayConfigUtil.CREATE_IP;  
        //回调接口   
        String notify_url = PayConfigUtil.NOTIFY_URL;  
        String trade_type = "NATIVE";  

        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
        packageParams.put("appid", appId);  
        packageParams.put("mch_id", mch_Id);  
        packageParams.put("nonce_str", nonce_str);  
        packageParams.put("body", body);  
        packageParams.put("out_trade_no", out_trade_no);  
        packageParams.put("total_fee", order_price);  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  

        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  

        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
        System.out.println(requestXML);  

        String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);  
        System.out.println(resXml);
      
        Map map = new Hashtable();
		try {
			map = XMLUtil.doXMLParse(resXml);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        //String return_code = (String) map.get("return_code");  
        //String prepay_id = (String) map.get("prepay_id");  
        String urlCode = (String) map.get("code_url");  
        System.out.println("看看urlCode是什么"+urlCode);        
        
        //将预订单保存数据库
        WeiXinOrder weixinOrder = new WeiXinOrder();
        weixinOrder.setAppId(appId);
        weixinOrder.setMch_id(mch_Id);
        weixinOrder.setOut_trade_no(out_trade_no);
        weixinOrder.setNonce_str(nonce_str);
        weixinOrder.setSign(sign);
        weixinOrder.setUrlCode(urlCode);
        weixinOrder.setStartTime(nowDate);
        if(weixinOrder !=null){
			Integer count = (Integer)totalDao.getCount("from WeiXinOrder where out_trade_no=?", weixinOrder.getOut_trade_no());
			if(count !=null&&count==1){
				//获取订单号的最大数值,根据最大数值加1()
				//WeiXinOrder weixinOrder3 = new WeiXinOrder();
				//weixinOrder3 = (WeiXinOrder)totalDao.getObjectByCondition("select MAX(out_trade_no) from WeiXinOrder where ");
				//weixinOrder1.setAppId(weixinOrder.getAppId());
				//weixinOrder1.setMch_id(weixinOrder.getMch_id());
				//weixinOrder1.setTransaction_id(weixinOrder.getTransaction_id());
				//weixinOrder1.setOut_trade_no(weixinOrder.getOut_trade_no());
			}else{
				boolean fhxzjg = totalDao.save(weixinOrder);
				System.out.println("输出是否保存成功fhxzjg"+fhxzjg);
			}
		}     
        return weixinOrder; 
	}
	
	
	/**
	 * 查询订单信息返回支付状态以及其他回调参数
	 */
	public static Map orderQueryV(String out_trade_no) {
		String appid = "wxa62d21c7055ef60d";
		String appsecret = "YOkgs7JR0VEloxkwV2G_GIX0BH2l9URqmF-AXRGwmVhW_5SGCw6bpev3V_syyuzJ";
		String mch_id = "1309955901";//邮件里给的
		String pkey = "HHTaskpebs123456HHTaskpebs123456";//商户平台里自己设的密钥
		String url="https://api.mch.weixin.qq.com/pay/orderquery";
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		Map map=new HashMap();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		RequestHandler reqHandler = new RequestHandler();
		reqHandler.init(appid, appsecret, pkey);
		String sign = reqHandler.createSign(packageParams);
		String xmlParam = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "</xml>";
			map=XMLUtil.doXML(url, xmlParam);
			System.out.println("输出看一下结果xmlParam"+xmlParam);
			System.out.println("输出查看一下map"+map);
			
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 支付返回url接口
	 * @return
	 */
	
	
	
	
	
	
	/**
	 * 快递柜收费标准查询
	 */
	public Map<Integer, Object> selectCharg(Charging charging,Integer pageNo,Integer pageSize){

		if(charging ==null){
			charging= new Charging();
		}else{
			
		}
		String hql = null;
		hql = totalDao.criteriaQueries(charging, null);
		hql+= " order by id asc";
		List<Employee> employeeList = totalDao.findAllByPage(hql, pageNo, pageSize);
		int count = totalDao.getCount(hql);
		Map<Integer, Object> map = new LinkedHashMap<Integer,Object>(); 
		map.put(1, employeeList);
		map.put(2, count);
		return map;
	}
	
	/**
	 * 新增快递柜类型标准
	 */
	public boolean insertCharg(Charging charging){
	    if(charging !=null&&charging.getType() !=null&&charging.getOverTime() !=null){
	    	//首先判断类型是否已经存在
	    	Charging charging1 = (Charging)totalDao.getObjectByCondition("from Charging where type=?",charging.getType());
	    	if(charging1 !=null){
	    		return false;	    		
	    	}else{
	    		boolean fhxzjg = totalDao.save(charging);
	    		return fhxzjg;
	    	}
	    }	
	    return false;
	}
	
	
	
	/**
	 * 按id查询收费标准对象
	 */
	public Charging selectCg(Charging charging){
		if(charging !=null){
			charging =  (Charging)totalDao.getObjectByCondition(" from Charging where id=?", charging.getId());
			return charging;
		}
		return null;
	}
	
	
	/**
	 * 修改快递柜收费标准
	 */
	public boolean updateCharg(Charging charging){
		Charging charging1 = new Charging();
		if(charging !=null&&charging.getId() !=null&&charging.getOverTime() !=null){
			charging1 = (Charging)totalDao.getObjectByCondition(" from Charging where id=?", charging.getId());
			if(charging1 != null){
				charging1.setId(charging.getId());
				charging1.setOverTime(charging.getOverTime());
				charging1.setCost(charging.getCost());
				boolean upCharg = totalDao.update(charging1);
				return upCharg;
			}			
		}
		return false;
	}
	
	/**
	 * 删除指定快递柜类型标准
	 */
	public boolean deleteCharg(Charging charging){
		if(charging !=null&&charging.getId() !=null){
			Charging charging2 = (Charging)totalDao.getObjectByCondition("from Charging where id=?", charging.getId());
			if(charging2 !=null){
				boolean deCharg = totalDao.delete(charging2);
				return  deCharg;
			}
		}
		return false;
	}
	
	
	
	/**
	 * 新增预订单信息
	 */
	public boolean insertWeiXinOrder(WeiXinOrder weixinOrder){
		//WeiXinOrder weixinOrder1 = new WeiXinOrder();
		if(weixinOrder !=null){
			Integer count = (Integer)totalDao.getCount("from WeiXinOrder where out_trade_no=?", weixinOrder.getOut_trade_no());
			if(count !=null&&count==1){
				//获取订单号的最大数值,根据最大数值加1()
				//WeiXinOrder weixinOrder3 = new WeiXinOrder();
				//weixinOrder3 = (WeiXinOrder)totalDao.getObjectByCondition("select MAX(out_trade_no) from WeiXinOrder where ");
				//weixinOrder1.setAppId(weixinOrder.getAppId());
				//weixinOrder1.setMch_id(weixinOrder.getMch_id());
				//weixinOrder1.setTransaction_id(weixinOrder.getTransaction_id());
				//weixinOrder1.setOut_trade_no(weixinOrder.getOut_trade_no());
				return false;
			}else{
				boolean fhxzjg = totalDao.save(weixinOrder);
	    		return fhxzjg;
			}
		}
		return false;
	}
	
	/**
	 * 修改预订单信息（在微信返回支付结果后进行修改,或是主动进行商户后台查询记录再进行修改）
	 */
	public boolean updateWeiXinOrder(WeiXinOrder weixinOrder){
        if(weixinOrder !=null){
        	//查询修改数据是否存在
        	WeiXinOrder weixinOrder1 = (WeiXinOrder)totalDao.getObjectByCondition("from WeiXinOrder where ", weixinOrder);
        	if(weixinOrder1 !=null){
        		//如果存在记录就修改其状态
        		return false;
        	}
        	boolean xgfhjg = totalDao.update(weixinOrder);
        }
        return false;
	}
	
	/**
	 * 查询单个预订单信息(将需要的数据转成xml并通过http发送到查询订单接口链接)
	 */
	public WeiXinOrder selectWinXinOrder(WeiXinOrder weixinOrder){
		
		return null;
	}
	
	/**
	 * 查询预订单集合信息(将需要的数据转成xml并通过http发送到查询订单接口链接)(暂时用不到,场景应为公众号商城内,或是微信可登陆app及网站)
	 */
	public List<WeiXinOrder> selectWeixinOrderList(WeiXinOrder weixinOrder){
		
		return null;
	}
	
	
	
	
	
	
	/**
	 * 快递员手机号验证在数据中是否存在(不存在就发送验证码让其进行注册)
	 */
	public  boolean selectCourier(String phoneNumber){
		TotalDao totalDao = createTotol();
		Integer count=null;
		Courier courier1 = new Courier();
		if(phoneNumber !=null){
			count = totalDao.getCount(" from Courier where phoneNumber=?",phoneNumber);
			if(count==1){
				//快递员手机号存在数据库中(发送指令给前台)
				
			}else{
				//快递员手机号不存在(发送验证码)
			}
		}	
		return false;
	}
	/**
	 * 快递员手机号验证在数据中是否存在(不存在就发送验证码让其进行注册)
	 */
	public static Map selectCourier_1(String phoneNumber){
		Map<String, Object> map = new HashMap<String,Object>();
		TotalDao totalDao = createTotol();  
		//Integer count=-1;
		String couState = "";
		List<Courier> courierList = new ArrayList<Courier>();
		if(phoneNumber !=null){
			courierList = totalDao.query(" from Courier where phoneNumber=?",phoneNumber);
			if(courierList.size()>0){
				couState = courierList.get(0).getCouState();
				map.put("count",1);
				map.put("phoneNumber",phoneNumber);
				map.put("couState",couState);
			}else{
				//Integer codeInt = (int)((Math.random()*9+1)*100000);
				//String code = codeInt.toString();
				map.put("count",0);
				map.put("phoneNumber",phoneNumber);	 
				map.put("couState",couState);
			}
		}	
		return map;
	}
	
	/**
	 * 快递员记录新增
	 */
	public boolean insertCourier(Courier courier){
		String nowDate  = Util.getDateTime(); //获取yyyy-mm-dd格式的日期
		String couState = "同意";
		Courier courier1 = null;
		if(courier !=null){
			Integer id = Integer.parseInt(courier.getKdCompany());
			courier1 = (Courier)totalDao.getObjectByCondition(" from Courier where phoneNumber=? and couName=? and couState=?",courier.getPhoneNumber(),courier.getCouName(),couState);
			CourierCompany couCompany = (CourierCompany)totalDao.getObjectByCondition(" from CourierCompany where id=?",id);
			if(courier1 ==null&&couCompany !=null){
				courier1= new Courier();
				courier1.setCouName(courier.getCouName());
				courier1.setPhoneNumber(courier.getPhoneNumber());
				courier1.setKdCompany(couCompany.getCouCpanyName());
				courier1.setCouReTime(nowDate);
				courier1.setIdNumber(courier.getIdNumber());
				courier1.setIdFront(courier.getIdFront());
				//courier1.setRoute(courier.getRoute());
				courier1.setPrimaryName(courier.getPrimaryName());
				courier1.setCouState("未审批");
				boolean xzkdyfh =  totalDao.save(courier1);
				System.out.println("输出返回结果xzkdyfh"+xzkdyfh);
				//临时登陆用户设置
				Users newuser=new Users();
				newuser.setId(0);
				newuser.setName("快递员注册");
				newuser.setDept("快递员");				
				ActionContext.getContext().getSession().put(TotalDao.users, newuser);
				String durl = "";
				Integer epId = null;
				if(xzkdyfh){
					durl = "WePayAction_selectEpIdCourier.action?couId="+courier1.getId();
					try {
						epId = CircuitRunServerImpl.createProcess("快递员注册流程",Courier.class,courier1.getId(),"couState","id",durl,"有新的快递员使用快递柜,请及时审批", true);
						if (epId != null && epId > 0) {
							courier1.setEpId(epId);
							CircuitRun circuitRun = (CircuitRun) totalDao.get(
									CircuitRun.class, epId);
							if ("同意".equals(circuitRun.getAllStatus())
									&& "审批完成".equals(circuitRun.getAuditStatus())) {
								courier1.setCouState("同意");
							} else {
								courier1.setCouState("未审批");
							}
							totalDao.update(courier1);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return true;
				}
			}
		}		
		return false;
	}
	
	/**
	 * 修改快递员信息状态
	 */
	public boolean updateCourier(Courier courier){
		
		return false;
	}
	
	
	/**
	 * 快递员记录新增
	 */
	public static boolean insertCourier_1(Courier courier){
		TotalDao totalDao = createTotol();  
		String nowDate  = Util.getDate(); //获取yyyy-mm-dd格式的日期
		Courier courier1 = new Courier();
		List<Courier> courierList = new ArrayList<Courier>();
		if(courier !=null){
			
			courierList= totalDao.query("from Courier where phoneNumber=?", courier.getPhoneNumber());
			//courier1 = courierList.get(0);
			if(courierList.size()==0){
				//courier1.setcName(courier.getcName());
				courier1.setPhoneNumber(courier.getPhoneNumber());
				//courier1.setKdCompany(courier.getKdCompany());
				courier1.setCode(courier.getCode());
				courier1.setCouReTime(nowDate);
				//courier1.setcReTime(nowDate);
				//courier1.setcState("有效");
				boolean xzkdyfh =  totalDao.save2(courier1);
				System.out.println("输出返回结果xzkdyfh"+xzkdyfh);
				if(xzkdyfh){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * 验证收件人手机号是否在数据中存在
	 */
	public Users selectUsers(){
		Users users = new Users();
		
		return users;
	}
		
	
	
	/**
	 * 查询所有快递公司
	 */
	public List<CourierCompany> selectCouCompany(){
		//List<CourierCompany> couCpanyList = new ArrayList<CourierCompany>();
		List<CourierCompany> couCpanyList = totalDao.query(" from CourierCompany");	
		return couCpanyList;
	}
	
	
	/**
     * 查询快递员录入的信息
     */
	public Courier selectEpIdCourier(Integer couId){
		Courier courier = new Courier();
		if(couId !=null){
			courier = (Courier)totalDao.getObjectByCondition(" from Courier where id=?", couId);
		}	
		return courier;
	}
	
	
	
	public TotalDao getTotalDao() {
		return totalDao;
	}
	public void setTotalDao(TotalDao totalDao) {
		this.totalDao = totalDao;
	}
	
}
