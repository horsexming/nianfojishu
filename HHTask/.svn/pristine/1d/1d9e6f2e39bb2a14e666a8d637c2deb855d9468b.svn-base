<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>更改记录</h3>
				 <table class="table">
				 <tr><td align="center" colspan="6"><b>本身资料更改</b></td>
				  </tr>
				 <tr>
				 <td align="center">属性</td>
				 <td align="center">原值</td>
				 <td align="center">新值</td>
				 <td align="center">操作类型</td>
				 <td align="center">操作人</td>
				 <td align="center">操作时间</td>
				 </tr>
				 <s:if test="ptchangeLogList!=null&&ptchangeLogList.size()>0">
				 <s:iterator value="ptchangeLogList" id="pageptchangeLog" status="logstatus">
				 	<s:if test="#pageptchangeLog.entityType=='本身'">
				 		<s:iterator value="#pageptchangeLog.changeLogDetailSet" id="pgetdeatil">
				 		<tr>
				 		 <td><s:property value="#pgetdeatil.sxName"/> </td>
				 		 <td><s:property value="#pgetdeatil.oldValue"/> </td>
				 		 <td><s:property value="#pgetdeatil.newValue"/> </td>
				 		 <td><s:property value="#pgetdeatil.addUsername"/> </td>
				 		 <td>修改 </td>
				 		 <td><s:property value="#pgetdeatil.addTime"/> </td>
				 		</tr>
				 		</s:iterator>
				 	</s:if>
				 </s:iterator>
				 </s:if>
				 <tr><td align="center" colspan="6"><b>下阶层增删</b></td>
				  </tr>
				 <tr>
				 <td align="center">件号</td>
				 <td align="center">类型/版本</td>
				 <td align="center">权值</td>
				 <td align="center">操作类型</td>
				 <td align="center">操作人</td>
				 <td align="center">操作时间</td>
				 </tr>
				  <s:if test="ptchangeLogList!=null&&ptchangeLogList.size()>0">
				 <s:iterator value="ptchangeLogList" id="pageptchangeLog" status="logstatus">
				 	<s:if test="#pageptchangeLog.entityType=='子件'">
				 		<tr>
				 		 <td><s:property value="#pageptchangeLog.entityData"/> </td>
				 		 <td><s:property value="#pageptchangeLog.entityProcardStyle"/>/<s:property value="#pageptchangeLog.entityBanben"/> </td>
				 		 <td><s:property value="#pageptchangeLog.xiaohao"/> </td>
				 		 <td><s:property value="#pageptchangeLog.optype"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addUsername"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addTime"/> </td>
				 		</tr>
				 	</s:if>
				 </s:iterator>
				 </s:if>
				 <tr><td align="center" colspan="6"><b>工序增删</b></td>
				  </tr>
				 <tr>
				 <td align="center">工序号</td>
				 <td align="center" colspan="2">工序名称</td>
				 <td align="center">操作类型</td>
				 <td align="center">操作人</td>
				 <td align="center">操作时间</td>
				 </tr>
				  <s:if test="ptchangeLogList!=null&&ptchangeLogList.size()>0">
				 <s:iterator value="ptchangeLogList" id="pageptchangeLog" status="logstatus">
				 	<s:if test="#pageptchangeLog.entityType=='工序'&&#pageptchangeLog.optype!='修改'">
				 		<tr>
				 		 <td><s:property value="#pageptchangeLog.entityData"/> </td>
				 		 <td colspan="2"><s:property value="#pageptchangeLog.entityData2"/> </td>
				 		 <td><s:property value="#pageptchangeLog.optype"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addUsername"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addTime"/> </td>
				 		</tr>
				 	</s:if>
				 </s:iterator>
				 	</s:if>
				 <tr><td align="center" colspan="6"><b>工序更改</b></td>
				 </tr>
				 <tr>
				 <td align="center">属性</td>
				 <td align="center">原值</td>
				 <td align="center">新值</td>
				 <td align="center">操作类型</td>
				 <td align="center">操作人</td>
				 <td align="center">操作时间</td>
				 </tr>
				  <s:if test="ptchangeLogList!=null&&ptchangeLogList.size()>0">
				 <s:iterator value="ptchangeLogList" id="pageptchangeLog" status="logstatus">
				 	<s:if test="#pageptchangeLog.entityType=='工序'&&#pageptchangeLog.optype=='修改'">
				 		<s:iterator value="#pageptchangeLog.changeLogDetailSet" id="pgetdeatil">
				 		<tr>
				 		 <td><s:property value="#pgetdeatil.sxName"/> </td>
				 		 <td><s:property value="#pgetdeatil.oldValue"/> </td>
				 		 <td><s:property value="#pgetdeatil.newValue"/> </td>
				 		 <td><s:property value="#pgetdeatil.addUsername"/> </td>
				 		 <td>修改 </td>
				 		 <td><s:property value="#pgetdeatil.addTime"/> </td>
				 		</tr>
				 		</s:iterator>
				 	</s:if>
				 </s:iterator>
				 	</s:if>
				 <tr><td align="center" colspan="6"><b>图纸增删</b></td>
				 </tr>
				  <tr>
				 <td align="center" colspan="2">查看</td>
				 <td align="center">名称</td>
				 <td align="center">操作类型</td>
				 <td align="center">操作人</td>
				 <td align="center">操作时间</td>
				 </tr>
				  <s:if test="ptchangeLogList!=null&&ptchangeLogList.size()>0">
				 <s:iterator value="ptchangeLogList" id="pageptchangeLog" status="logstatus">
				 	<s:if test="#pageptchangeLog.entityType=='图纸'">
				 		<tr>
				 		 <td colspan="2">
				 		 <a target="_showPri"
											href="<%=path%>/FileViewAction.action?FilePath=/upload/file/processTz/<s:property value="#pageptchangeLog.month"/>/<s:property value="#pageptchangeLog.realFileName"/>">
											
											<img
												src="<%=path%>/upload/file/processTz/<s:property value="#pageptchangeLog.month"/>/<s:property value="#pageptchangeLog.realFileName"/>"
												style="width: 80px; height: 80px;" />
										</a>
				 		 </td>
				 		 <td><s:property value="#pageptchangeLog.oldFileName"/> </td>
				 		 <td><s:property value="#pageptchangeLog.optype"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addUsername"/> </td>
				 		 <td><s:property value="#pageptchangeLog.addTime"/> </td>
				 		</tr>
				 	</s:if>
				 </s:iterator>
				 	</s:if>
				 </table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
