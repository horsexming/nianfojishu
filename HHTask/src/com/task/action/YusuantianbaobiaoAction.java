package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.YusuantianbaobiaoServer;
import com.task.ServerImpl.sys.CircuitRunServerImpl;
import com.task.entity.Users;
import com.task.entity.Yusuantianbaobiao;
import com.task.entity.Yusuantianbaototal;
import com.task.util.MKUtil;
import com.task.util.Util;

public class YusuantianbaobiaoAction extends ActionSupport {
	private Yusuantianbaobiao yusuantianbaobiao;
	private YusuantianbaobiaoServer yusuantianbaobiaoServer;
	private Yusuantianbaototal yusuantianbaototal;
	private List list;
	private int id;
	private int idw;
	private String statu;
	private int key;
	private String allow;
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/***
	 * 添加明细
	 */
	public String addmingxi() {
		yusuantianbaototal = yusuantianbaobiaoServer.findYusuantianbaototal(id);
		return "addyusuan";
	}

	/***
	 * 添加总表
	 */
	public String addZongBiao() {
		yusuantianbaobiaoServer.saveZBaobiao(yusuantianbaototal);
		return "getZlist";
	}

	/***
	 * 添加表单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addBiaoDan() throws Exception {
		yusuantianbaototal = yusuantianbaobiaoServer.findYusuantianbaototal(id);
		yusuantianbaobiao.setYusuantianbaototal(yusuantianbaototal);
		yusuantianbaobiaoServer.saveBaobiao(yusuantianbaobiao,statu);
		return "getAllList";
	}

	/***
	 * 表单分页查询
	 * 
	 * @return
	 */
	public String getAllList() {
		Users user = Util.getLoginUser();
		list = yusuantianbaobiaoServer.addZAllList(Integer.parseInt(cpage),
				pageSize, user.getDept());
		if(list!=null&&list.size()>0){
			yusuantianbaototal = (Yusuantianbaototal) list.get(0);
			statu = yusuantianbaototal.getShenpi();
			list = yusuantianbaobiaoServer.getAllList(Integer.parseInt(cpage),
					pageSize, user.getDept(), allow);
			this.setUrl("YusuantianbaobiaoAction!getAllList.action");
			int count = yusuantianbaobiaoServer.getcount2(user.getDept(), allow);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}else {
		}
		return "yusuantianbaobiaolist";
	}

	/***
	 * 总表分页查询
	 */
	public String getAllZlist() {
		allow = "同意";
		setList(yusuantianbaobiaoServer.getZAllList(Integer.parseInt(cpage),
				pageSize));
		this.setUrl("YusuantianbaobiaoAction!getAllZlist.action");
		int count = yusuantianbaobiaoServer.getZcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "yusuantianbaototallist";
	}

	/***
	 * 总表分页查询simple
	 */
	public String getAllListSimple() {
		yusuantianbaototal = yusuantianbaobiaoServer.findYusuantianbaototal(id);
		statu = yusuantianbaototal.getShenpi();
		list = yusuantianbaobiaoServer.getAllList(Integer.parseInt(cpage),
				pageSize, id);
		this.setUrl("YusuantianbaobiaoAction!getAllList.action");
		int count = yusuantianbaobiaoServer.getcount1(id);
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "yusuantianbaobiaolistsimple";
	}

	// 审批s
	public String getAllSlist() {
		allow = "同意";
		list = null;
		Object[] obj = yusuantianbaobiaoServer.findExamList(Integer
				.parseInt(cpage), pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("YusuantianbaobiaoAction!getAllSlist.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "yusuantianbaobiaolistsimple";
	}

	/***
	 * 查看
	 */
	public String findYusuantianbaobiao() {
		yusuantianbaobiao = yusuantianbaobiaoServer.findYusuantianbaobiao(id);
		return "yusuantianbaobiao";
	}

	/***
	 * 修改总表
	 */
	public String findZUpdate() {
		yusuantianbaototal = yusuantianbaobiaoServer.findYusuantianbaototal(id);
		return "updateyusuantianbaototal";
	}

	public String updateYusuantianbaototal() {
		yusuantianbaobiaoServer.updateYusuantianbaototal(yusuantianbaototal);
		return "getZlist";
	}

	/***
	 * 修改
	 */
	public String findUpdate() {
		yusuantianbaobiao = yusuantianbaobiaoServer.findYusuantianbaobiao(id);
		yusuantianbaototal = yusuantianbaobiao.getYusuantianbaototal();
		return "updateyusuantianbaobiao";
	}

	public String updateYusuantianbaobiao() {
		yusuantianbaototal = yusuantianbaobiaoServer
				.findYusuantianbaototal(idw);
		yusuantianbaobiao.setYusuantianbaototal(yusuantianbaototal);
		yusuantianbaobiao.setShenpi("打回");
		yusuantianbaobiaoServer.updateYusuantianbaobiao(yusuantianbaobiao,
				statu);
		return "getAllList";
	}

	/***
	 * 删除
	 */
	public String deleteYusuantianbaobiao() {
		yusuantianbaobiao = yusuantianbaobiaoServer.findYusuantianbaobiao(id);
		yusuantianbaobiaoServer.deleteBaobiao(yusuantianbaobiao);
		return "getAllList";
	}

	/***
	 * 个人审批总表
	 */
	public String getAllgerenlist() {
		Users user = Util.getLoginUser();
		list = yusuantianbaobiaoServer.addZAllList(Integer.parseInt(cpage),
				pageSize, user.getDept());
		this.setUrl("Yusuantianbaobiao!getAllgerenlist.action");
		int count = yusuantianbaobiaoServer.getZcount();
		int pageCount = (count + pageSize - 1) / pageSize;
		this.setTotal(pageCount + "");
		return "yusuantianbaototallist";
	}

	/***
	 * 提交审批总表
	 * 
	 * @throws Exception
	 */
	public String tijiaoYusuantianbaototal() throws Exception {
		yusuantianbaobiao = yusuantianbaobiaoServer.findYusuantianbaobiao(id);
		if (yusuantianbaobiao != null) {
			if (yusuantianbaobiao.getEpid() == null) {
				int epid = CircuitRunServerImpl.createProcess("年度预算审批",
						Yusuantianbaobiao.class, id, "shenpi", "id",
						"YusuantianbaobiaoAction!getAllList.action?id=" + id,
						yusuantianbaobiao.getBumen() + "部门的"
								+ yusuantianbaobiao.getNiandu() + "年度预算请您审批!",
						true);
				yusuantianbaobiao.setShenpi("未审批");
				yusuantianbaobiao.setEpid(epid);
				yusuantianbaobiaoServer.updateYusuantianbaobiao(
						yusuantianbaobiao, statu);
			} else {
				yusuantianbaobiaoServer.dahuixiugaishenpi(yusuantianbaobiao
						.getEpid());
			}
			yusuantianbaototal = yusuantianbaobiao.getYusuantianbaototal();
			return "getAllList";
		}
		return ERROR;
	}

	/***
	 * 删除总表
	 */
	public String deleteYusuantianbaototal() {
		yusuantianbaototal = yusuantianbaobiaoServer.findYusuantianbaototal(id);
		yusuantianbaobiaoServer.deleteBaobiao(yusuantianbaototal);
		return "getZlist";
	}

	/***
	 * 根据部门 和 年份 查找明细
	 * 
	 * @return
	 */
	public void findMingxi() {
		list = yusuantianbaobiaoServer.findMingxi();
		MKUtil.writeJSON(list);
	}

	/***
	 * 审批
	 * 
	 * @return
	 */
	public String shenPi() {
		yusuantianbaobiaoServer.shenpi(id, key);
		return "getSlist";
	}

	public Yusuantianbaobiao getYusuantianbaobiao() {
		return yusuantianbaobiao;
	}

	public void setYusuantianbaobiao(Yusuantianbaobiao yusuantianbaobiao) {
		this.yusuantianbaobiao = yusuantianbaobiao;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setYusuantianbaobiaoServer(
			YusuantianbaobiaoServer yusuantianbaobiaoServer) {
		this.yusuantianbaobiaoServer = yusuantianbaobiaoServer;
	}

	public YusuantianbaobiaoServer getYusuantianbaobiaoServer() {
		return yusuantianbaobiaoServer;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getList() {
		return list;
	}

	public void setYusuantianbaototal(Yusuantianbaototal yusuantianbaototal) {
		this.yusuantianbaototal = yusuantianbaototal;
	}

	public Yusuantianbaototal getYusuantianbaototal() {
		return yusuantianbaototal;
	}

	public void setIdw(int idw) {
		this.idw = idw;
	}

	public int getIdw() {
		return idw;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getStatu() {
		return statu;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getAllow() {
		return allow;
	}

}
