package com.task.action.rtx;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.task.Server.rtx.RtxMsgServer;
import com.task.entity.Users;
import com.task.entity.rtx.RtxConnect;
import com.task.entity.rtx.RtxMsg;
import com.task.util.MKUtil;

/**
 * rtx消息Action层
 * @author txb
 *
 */
public class RtxMsgAction {
	private RtxConnect rtxConnect;//RTX连接配置对象
	private Integer id;
	private List<RtxConnect> rtxConnectList;
	private RtxMsgServer rtxMsgServer;//rtx消息服务
	private RtxMsg rtxMsg;//rtx消息
	private List<RtxMsg> rtxMsgList;//rtx消息列表
	private Users sender;//发送人
	private List<Users> usersList;//用户列表
	private List<String> receiver;//接收人工号用逗号隔开
	private String title;//标题
	private String msgContext;//消息内容
	private String deptIds;//选中的部门id用逗号分开
	private String successMessage;// 成功消息
	private String errorMessage;// 错误消息
	// 分页
	private String cpage = "1";
	private String total;
	private String url;
	private int pageSize = 15;
	 public String showList(){
			if (rtxMsg != null) {
				ActionContext.getContext().getSession().put("rtxMsg",
						rtxMsg);
			    } else {//用来保持分页时带着查询条件
			    	rtxMsg = (RtxMsg) ActionContext.getContext().getSession().get("rtxMsg");
			      }
			if(rtxMsg!=null){
				if(rtxMsg.getMsgType()==null||rtxMsg.getMsgType().equals("全部")||rtxMsg.getMsgType().equals("")){
					rtxMsg.setMsgType(null);
				}
			}
			Map<Integer, Object> map = rtxMsgServer.findrtxMsgsByCondition(
					rtxMsg, Integer.parseInt(cpage), pageSize);
			rtxMsgList = (List<RtxMsg>) map.get(1);// 显示页的技能系数列表
				if (rtxMsgList != null & rtxMsgList.size() > 0) {
					int count = (Integer) map.get(2);
					int pageCount = (count + pageSize - 1) / pageSize;
					this.setTotal(pageCount + "");
					this.setUrl("rtxMsgAction_showList.action");
				} else {
					errorMessage = "没有找到你要查询的内容,请检查后重试!";
				}	
		 return "rtxMsg_show";//showrtxmsg.jsp
	 }
	/**
	 * 跳完发送rtx消息页面
	 * @return
	 */
	public String toSendRtxMsg(){
		return "sendrtxmsg";
	}
	
	/**
	 * ajax 获取带有拥有权限信息的部门Vo列表
	 */
	public void getDepts(){
		MKUtil.writeJSON(rtxMsgServer.getDeptVos());
	}
	
	public void sendAgin(){
		String msg=rtxMsgServer.sendAgin(id);
		MKUtil.writeJSON(msg);
	}
	
	
	public void getUsers(){
		usersList=rtxMsgServer.getDeptsUsers(deptIds);
		MKUtil.writeJSON(usersList);
	}
	 public void findAllRtxDept(){
		 List list=rtxMsgServer.findAllRtxDeptForJson();
		   MKUtil.writeJSON(list);
	   }
	 public void getRtxUsers(){
		   MKUtil.writeJSON(rtxMsgServer.getRtxUsersByDeptId(deptIds));
	   }
	public String sendRtxMsg(){
		sender=(Users)ActionContext.getContext().getSession()
		.get("Users");
		if(receiver!=null&&receiver.size()>0){
			boolean b=rtxMsgServer.saveSendRtxMsg(sender,receiver,msgContext,title);
			if(b){
				MKUtil.writeJSON(true, "消息发送成功！", null);
			}else{
				MKUtil.writeJSON(true, "消息发送失败！", null);
			}
		}else{
			MKUtil.writeJSON(true, "消息发送失败，请选择接收人！", null);
		}
		return null;
	}
	//添加RTX连接配置
	public String addRtxConnect(){
		errorMessage =	rtxMsgServer.addRtxConnect(rtxConnect);
		return "RtxConnect_add";
	}
	//展示RTX连接配置
	public String showRtxConnect(){
		rtxConnectList =	rtxMsgServer.showRtxConnect();
		return "RtxConnect_showList";
	}
	//根据Id获得RTX连接配置
	public String  getRtxConnectById(){
		rtxConnect =	rtxMsgServer.getRtxConnectById(id);
		return "RtxConnect_update";
	}
	//根据Id获得RTX连接配置
	public String delRtxConnect(){
		errorMessage =	rtxMsgServer.delRtxConnect(rtxConnect);
		return "showRtxConnect";
	}
	//修改RTX连接配置
	public String updateRtxConnect(){
		errorMessage = rtxMsgServer.updateRtxConnect(rtxConnect);
		return "RtxConnect_update";
	}
	
	public RtxMsgServer getRtxMsgServer() {
		return rtxMsgServer;
	}
	public void setRtxMsgServer(RtxMsgServer rtxMsgServer) {
		this.rtxMsgServer = rtxMsgServer;
	}
	public Users getSender() {
		return sender;
	}
	public void setSender(Users sender) {
		this.sender = sender;
	}
	

	public List<String> getReceiver() {
		return receiver;
	}
	public void setReceiver(List<String> receiver) {
		this.receiver = receiver;
	}
	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public String getMsgContext() {
		return msgContext;
	}

	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
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
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public RtxMsg getRtxMsg() {
		return rtxMsg;
	}
	public void setRtxMsg(RtxMsg rtxMsg) {
		this.rtxMsg = rtxMsg;
	}
	public List<RtxMsg> getRtxMsgList() {
		return rtxMsgList;
	}
	public void setRtxMsgList(List<RtxMsg> rtxMsgList) {
		this.rtxMsgList = rtxMsgList;
	}
	public RtxConnect getRtxConnect() {
		return rtxConnect;
	}
	public void setRtxConnect(RtxConnect rtxConnect) {
		this.rtxConnect = rtxConnect;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<RtxConnect> getRtxConnectList() {
		return rtxConnectList;
	}
	public void setRtxConnectList(List<RtxConnect> rtxConnectList) {
		this.rtxConnectList = rtxConnectList;
	}
	
	
	

}
