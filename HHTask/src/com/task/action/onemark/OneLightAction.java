package com.task.action.onemark;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.onemark.OneLightServer;
import com.task.ServerImpl.onemark.OneLightServerImpl;
import com.task.entity.Users;
import com.task.entity.onemark.OneLight;
import com.task.util.MKUtil;
import com.task.util.Util;

/***
 * 灯管理
 * 
 * @author Li_Cong
 * 
 */

@SuppressWarnings("unchecked")
public class OneLightAction {
	private OneLight light;
	private OneLightServer lightServer;

	private List<OneLight> lightList;// 集合
	private List<OneLight> listLight;// 一体机上的灯
	private String errorMessage;
	private String successMessage;
	private String code;
	private Integer id;
	private String oc_id;
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 32;
	private String tag;

	private String ipAddress;// 一体机IP

	// 分页显示查询内容
	public String showList() {
		if (light != null) {
			ActionContext.getContext().getSession().put("light", light);
		} else {// 用来保持分页时带着查询条件
			light = (OneLight) ActionContext.getContext().getSession().get(
					"light");
		}
		Map<Integer, Object> map = lightServer.findOneLight(light, Integer
				.parseInt(cpage), pageSize);
		lightList = (List<OneLight>) map.get(0);// 显示面试单列表
		if (lightList != null && lightList.size() > 0) {
			int count = (Integer) map.get(1);
			int pageCount = (count + pageSize - 1) / pageSize;
			this.setTotal(pageCount + "");
			this.setUrl("OneLightAction_showList.action?tag=" + tag);
			errorMessage = null;
		} else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		if ("guan".equals(tag)) {
			return "onelight_guan";
		}
		return "onelight_show";
	}

	public String findCodeOneLight(){
		Users users = Util.getLoginUser();
		if (users!=null) {
			lightList = lightServer.findUserIdLight(users.getId());
		}else {
			errorMessage = "没有找到你要查询的内容,请检查后重试!";
		}
		return "onelight_guan";
	}
	
	//查询所有可控灯  =>发送至Android端
	public void findAllOneLight() {
		if (id!=null && id > 0) {
			lightList = lightServer.findUserIdLight(id);// 显示面试单列表
			if (lightList!=null&&lightList.size()>0)
				MKUtil.writeJSON(true, "获取成功", lightList);
			else
				MKUtil.writeJSON(false, "当前可控灯为空！", lightList);
		}
		MKUtil.writeJSON(false, "对象为空！", lightList);
	}

	public String toadd() {
		return "onelight_add";
	}

	// 添加方法
	public String add() {
		if (light != null) {
			errorMessage = lightServer.addOneLight(light);
			if ("添加成功！".equals(errorMessage))
				url = "OneLightAction_showList.action";
			return "error";
		}
		errorMessage = "数据为空，添加失败！";
		return "error";
	}

	// 跳转到修改页面的方法
	public String toupdate() {
		if (light.getId() != null && light.getId() > 0 && light != null) {
			light = lightServer.byIdOneLight(light.getId());
			if (light != null)
				return "onelight_update";
		}
		errorMessage = "数据为空!请检查";
		return "error";
	}

	// 修改方法
	public String update() {
		errorMessage = lightServer.updateOneLight(light);
		if ("修改成功！".equals(errorMessage))
			url = "OneLightAction_showList.action";
		return "error";
	}

	// 删除方法
	public String delete() {
		if (id > 0 && id != null) {
			errorMessage = lightServer.deleteOneLight(id);
			if ("删除成功！".equals(errorMessage))
				url = "OneLightAction_showList.action";
			return "error";
		}
		errorMessage = "不存在该对象！删除失败！";
		return "error";
	}

	// 开关灯接口
	public String openLight() {
		if (light != null && light.getId() != null) {
			light = lightServer.byIdOneLight(light.getId());
			if (light != null && light.getLightZhiLing() != null) {
				int num = Util.swiInstruction(light.getLightZhiLing(), "打开"
						.equals(light.getLightStatus()) ? 0 : 1);
				if (num > 0) {
					errorMessage = lightServer.updateOCLight(light, num);
					if ("true".equals(errorMessage)) {
						url = "OneLightAction_showList.action?tag="+tag;
						if ("code".equals(tag)) 
							url = "OneLightAction_findCodeOneLight.action?tag="+tag;
						errorMessage = "您选择的灯" + light.getLightNum() + "已经"
								+ light.getLightStatus() + "成功!";
						return "error";
					}
				}
				errorMessage = "开关指令不正确！";
			}
		}
		return "error";
	}

	// 开关灯接口
	public String openGongWeiLight() {
		light = lightServer.byIdOneLight(id);
		if (light != null && light.getLightZhiLing() != null) {
			int num = Util.swiInstruction(light.getLightZhiLing(), "打开"
					.equals(light.getLightStatus()) ? 0 : 1);
			if (num > 0) {
				errorMessage = lightServer.updateOCLight(light, num);
				if ("true".equals(errorMessage))
					return MKUtil.writeJSON(light);
			}
		}
		return MKUtil.writeJSON(null);
	}

	// Android端开关灯接口
	public String openAndroidLight() {
		if (light != null && light.getId() != null) {
			light = lightServer.byIdOneLight(light.getId());
			if (light != null && light.getLightZhiLing() != null) {
				int num = Util.swiInstruction(light.getLightZhiLing(), "打开"
						.equals(light.getLightStatus()) ? 0 : 1);
				if (num > 0) {
					errorMessage = OneLightServerImpl.staticOCLight(light, num, code);
					if ("true".equals(errorMessage))
						return MKUtil.writeJSON(true, "操作成功！", null);
					else
						return MKUtil.writeJSON(false, errorMessage, null);
				} else {
					return MKUtil.writeJSON(false, "开关指令不正确，操作失败！", null);
				}
			}
		}
		return MKUtil.writeJSON(false, "灯位不存在，请重试！", null);
	}

	public OneLight getLight() {
		return light;
	}

	public void setLight(OneLight light) {
		this.light = light;
	}

	public OneLightServer getLightServer() {
		return lightServer;
	}

	public void setLightServer(OneLightServer lightServer) {
		this.lightServer = lightServer;
	}

	public List<OneLight> getLightList() {
		return lightList;
	}

	public void setLightList(List<OneLight> lightList) {
		this.lightList = lightList;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOc_id() {
		return oc_id;
	}

	public void setOc_id(String ocId) {
		oc_id = ocId;
	}

	public List<OneLight> getListLight() {
		return listLight;
	}

	public void setListLight(List<OneLight> listLight) {
		this.listLight = listLight;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
