package com.task.action.SingleCar;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.SingleCar.SingleCarServer;
import com.task.entity.Users;
import com.task.entity.approval.Signature;
import com.task.entity.kvp.KVPAssess;
import com.task.entity.singlecar.SingleCar;
import com.task.entity.singlecar.SingleCarAll;
import com.task.entity.system.ExecutionNode;
import com.task.util.MKUtil;
import com.task.util.Util;

public class SingleCarAction extends ActionSupport {
	private SingleCarServer singleCarServer;
	private SingleCar singleCar;
	private List list;
	private List<Map> maps;
	private SingleCarAll singleCarAll;

	private String successMessage;
	private String errorMessage;

	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;

	private String tag;
	private Integer[] detailSelect;// 选择补打数组,审批数组
	private String test;

	private Integer id;
	private String pilotname;
	private String firsttime;
	private String finishtime;
	private String status;// 状态
	private String car_number;

	// 查看用车单
	public String findSingleCar() {
		if (singleCar != null) {
			ActionContext.getContext().getSession().put("singleCar", singleCar);
		} else {
			singleCar = (SingleCar) ActionContext.getContext().getSession()
					.get("singleCar");
		}
		Object[] object = singleCarServer.findSingleCar(singleCar, Integer
				.parseInt(cpage), pageSize, tag);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];

				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("singleCarAction_findSingleCar.action?tag=" + tag);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findSingleCar";
	}

	// 根据编号查询用车单
	public String findSingleCarById() {
		singleCar = singleCarServer.findSingleCarById(id);
		if (test != null) {
			return "findSingleCarById1";
		} else {
			return "findSingleCarById";
		}
	}

	// 更新用车单
	public String updateSingleCar() {
		boolean bool = singleCarServer.updateSingleCar(singleCar);
		if (bool) {
			return "updateSingleCar";
		} else {
			return ERROR;
		}
	}

	// 删除用车单
	public String delSingleCarById() {
		boolean bool = singleCarServer.delSingleCarById(id);
		if (bool) {
			return "delSingleCarById";
		} else {
			return ERROR;

		}
	}

	// 总经办确认
	public String ConfirmSingleCar() {
		boolean bool = singleCarServer.updateSingleCarStatus(id);
		if (bool) {
			return "updateSingleCarStatus";
		} else {
			return ERROR;
		}
	}

	// 申请用车单
	public String addSingleCar() {
		boolean bool = this.singleCarServer.addSingleCar(singleCar);
		if (bool) {
			return "addSingleCar";
		} else {
			return ERROR;
		}
	}

	// 查询省内用车单审核
	public String findExamListA() {
		Object[] obj = singleCarServer.findExamListA(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("singleCarAction_findExamListA.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamSingleCarA";
	}

	// 查询省外用车单审核
	public String findExamListB() {
		Object[] obj = singleCarServer.findExamListB(Integer.parseInt(cpage),
				pageSize);
		if (obj != null && obj.length > 1) {
			list = (List) obj[1];
			int count = (Integer) obj[0];
			this.setUrl("singleCarAction_findExamListB.action");
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
		}
		return "findExamSingleCarB";
	}

	// 省内用车单(通过、驳回)
	public String updateExamListA() {
		try {
			if (singleCarServer.updateExamListA(detailSelect, tag)) {
				return "updateExamListA";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 省外用车单(通过、驳回)
	public String updateExamListB() {
		try {
			if (singleCarServer.updateExamListB(detailSelect, tag)) {
				return "updateExamListB";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		errorMessage = "审批失败!请检查数据有效性!";
		return ERROR;
	}

	// 查询各审批节点人
	public void findExecutionNode() {
		Map<Integer, Object> map = this.singleCarServer
				.findPay_ExecutionNode(id);
		List<Signature> sigList = (List<Signature>) map.get(1);
		List<ExecutionNode> nodeList = (List<ExecutionNode>) map.get(2);
		MKUtil.writeJSON(true, "", nodeList, sigList);// 把结果传到页面
	}

	// 根据驾驶员来统计里程数（出车前里程表，回车后里程表)
	public String getCountkilometers() {
		Users user = Util.getLoginUser();
		pilotname = user.getName();
//		pilotname = "王青育";
		singleCarAll = this.singleCarServer.getCountkilometers(pilotname,
				firsttime, finishtime, car_number);
		url = "singleCarAction_findSingleCar1.action";
		return "getCountkilometers";

	}

	// 申请审批里程数
	public String updateExamListC() {
		if (("".equals(firsttime) && firsttime != null)
				|| ("".equals(finishtime) && finishtime != null)) {
			errorMessage = "开始时间和结束时间不能为空！";
			return ERROR;
		}
		// 判断时间不能重复
		List list = singleCarServer.findSingleCarAllNN(firsttime, finishtime);
		if (list != null&&list.size()>0) {
			errorMessage = "已存在此段时间，不能重叠";
			return ERROR;
		} else {
			boolean bool = singleCarServer.updateExamListC(pilotname,
					firsttime, finishtime, car_number);
			if (bool == true) {
				successMessage = "添加" + firsttime + ":" + finishtime + "成功！";
				singleCarAll = null;
				return "updateExamListC";
			}

		}
		errorMessage = "审批失败!请检查数据有效性!";

		return ERROR;
	}

	// 里程数查询
	public String findExamListC() {
		if (singleCarAll != null) {
			ActionContext.getContext().getSession().put("singleCarAll",
					singleCarAll);
		} else {
			singleCarAll = (SingleCarAll) ActionContext.getContext()
					.getSession().get("singleCarAll");
		}
		Object[] object = singleCarServer.findExamListC(singleCarAll, Integer
				.parseInt(cpage), pageSize, status);
		if (object != null && object.length > 0) {
			maps = (List<Map>) object[0];
			if (maps != null && maps.size() > 0) {
				int count = (Integer) object[1];
				int pageCount = (count + pageSize - 1) / pageSize;
				this.setTotal(pageCount + "");
				this.setUrl("singleCarAction_findExamListC.action?status="
						+ status);
				errorMessage = null;
			} else {
				errorMessage = "没有找到你要查询的内容,请检查后重试!";
			}
		}
		return "findExamListC";
	}

	// //确认
	// public String ConfirmSingleCar1(){
	// boolean bool = singleCarServer.updateSingleCarAllStatus(id);
	// if(bool){
	// return "updateSingleCarAllStatus";
	// }else{
	// return ERROR;
	// }
	// }
	public String findSingleCarAllById() {
		singleCarAll = singleCarServer.findSingleCarAllById(singleCarAll
				.getId());
		return "findSingleCarDaYin";
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer[] getDetailSelect() {
		return detailSelect;
	}

	public void setDetailSelect(Integer[] detailSelect) {
		this.detailSelect = detailSelect;
	}

	public SingleCarServer getSingleCarServer() {
		return singleCarServer;
	}

	public void setSingleCarServer(SingleCarServer singleCarServer) {
		this.singleCarServer = singleCarServer;
	}

	public SingleCar getSingleCar() {
		return singleCar;
	}

	public void setSingleCar(SingleCar singleCar) {
		this.singleCar = singleCar;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<Map> getMaps() {
		return maps;
	}

	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getPilotname() {
		return pilotname;
	}

	public void setPilotname(String pilotname) {
		this.pilotname = pilotname;
	}

	public SingleCarAll getSingleCarAll() {
		return singleCarAll;
	}

	public void setSingleCarAll(SingleCarAll singleCarAll) {
		this.singleCarAll = singleCarAll;
	}

	public String getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(String firsttime) {
		this.firsttime = firsttime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCar_number() {
		return car_number;
	}

	public void setCar_number(String carNumber) {
		car_number = carNumber;
	}

}
