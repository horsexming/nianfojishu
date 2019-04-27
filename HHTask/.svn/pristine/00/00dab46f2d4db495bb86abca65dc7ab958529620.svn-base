package com.task.Server.rtx;

import java.util.List;
import java.util.Map;

import com.task.entity.DeptNumber;
import com.task.entity.Users;
import com.task.entity.rtx.RtxConnect;
import com.task.entity.rtx.RtxMsg;

public interface RtxMsgServer {
    /**
     * 发送Rtx消息
     * @param sender 发送人
     * @param receiver 接收人code
     * @param msgContext 消息内容
     * @return
     */
	public boolean saveSendRtxMsg(Users sender, List<String> receiver,
			String msgContext,String title);
	/**
	 * 获取所有的选中部门下的用户
	 * @param deptIds
	 * @return
	 */
	public List<Users> getDeptsUsers(String deptIds);
	/**
	 * 获得所有的部门列表用vo存储
	 * @return
	 */
	public List<DeptNumber> getDeptVos();
	/**
	 * 分页显示rtx消息
	 * @param rtxMsg
	 * @param parseInt
	 * @param pageSize
	 * @return
	 */
	public Map<Integer, Object> findrtxMsgsByCondition(RtxMsg rtxMsg,
			int parseInt, int pageSize); 
	// 查询所有Rtx部门(for json)
	public List findAllRtxDeptForJson();
	//通过rtx部门id获取rtx用户
	public List getRtxUsersByDeptId(String deptIds);
	/**
	 * 添加Rtx连接配置
	 */
	public  String addRtxConnect(RtxConnect rtxConnect);
	/**
	 * 查看Rtx连接配置
	 * @return
	 */
	public  List<RtxConnect> showRtxConnect();
	/**
	 * 修改Rtx连接配置
	 */
	public String updateRtxConnect(RtxConnect rtxConnect);
	/**
	 * 删除Rtx连接配置
	 */
	public String delRtxConnect(RtxConnect rtxConnect);
	/**
	 * 根据Id获得Rtx连接配置
	 */
	public RtxConnect getRtxConnectById(Integer id);
	/**
	 * 重新发送RTX消息
	 * @param id
	 * @return
	 */
	public String sendAgin(Integer id);
}
