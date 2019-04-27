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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="productPriceAction!saveProductProcess.action?id=<%=request.getParameter("id") %>" method="post">
					<%=request.getParameter("id") %>
					<table border="1">
						<tr>
						<td>工序号：<input type="text" name="productProcess.processNo" /></td>
						<td>工序名称：<input type="text" name="productProcess.processName" /></td>
						<td>人工节拍：<input type="text" name="productProcess.realJIEPAI" value="0" /></td>
						</tr>
						<tr>
						<td>工位号：<input type="text" name="productProcess.gongwei" /></td>
						<td>设备号：<input type="text" name="productProcess.shebeiNo" /></td>
						<td>单件节拍：<input type="text" name="productProcess.danjianJIEPAI" value="0" /></td>
						</tr>						
						<tr>
						<td>操作人工号：<input type="text" onblur="send(this)" id="code" name="productProcess.operatorCode" /></td>
						<td>操作者：<input type="text" id="username" name="productProcess.operatorName" readonly="true"/></td>
						<td>复合利用节拍：<input type="text" name="productProcess.fuheJIEPAI" /></td>
						</tr>
						<tr>
						<td>操作技能指数：<input type="text" name="productProcess.OPtechnologyRate" value="0" /></td>
						<td>操作可替换指数：<input type="text" name="productProcess.OPCouldReplaceRate" value="0" /></td>
						<td>操作负荷指数：<input type="text" name="productProcess.OPfuheRate" value="0" /></td>
						</tr>
						<tr>
						<td>工装调试技能指数：<input type="text" name="productProcess.GZtechnologyRate" value="0" /></td>
						<td>工装调试可替换指数：<input type="text" name="productProcess.GZCouldReplaceRate" value="0" /></td>
						<td>工装调试负荷指数：<input type="text" name="productProcess.GZfuheRate" value="0" /></td>
						</tr>
						<tr>
						<td >复合工序：<input type="text" name="productProcess.fuheProcessNo" /></td>
						<td>视频连接地址：<input type="text" name="productProcess.videoFile" /></td>
						<td >备注：<input type="text" name="productProcess.processMore" /></td>
						
						</tr>
						<tr>
						<td colspan="2" align="center">
						<input type="submit" value="添加" />&nbsp;
						<input type="reset" value="取消">
						
						</td></tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- ajax处理员工号与员工姓名之间的关联关系-->
	<script type="text/javascript">
	function send(obj) {
	var value = encodeURI(obj.value);//对strValue进行编码 
	sendRequest("productPriceAction!findUserName.action?code=" + value,
			messageResponse);
}
// 联系人查询
function messageResponse() {
	if (XMLHttpReq.readyState == 4) { // 判断对象状态
		if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
			var message = XMLHttpReq.responseText;
			document.getElementById("username").value=message;

		} else { //页面不正常
			window.alert("页面异常,请重试!");
		}
	}
}
		
		</script>
		
	</body>
</html>
