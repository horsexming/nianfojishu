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
		<div id="gongneng" style="width: 100%;">
			<dir>
				<h3>确认<font>${waigoudd.markId}</font>数量</h3>
			</dir>
			<div align="center">
				<h3>
					待开库位
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							库位编号
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							零件类型
						</td>
						<td align="center">
							操作数量
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="warehouseNumbers" id="warehouse"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${warehouse.number}
						</td>
						<td align="center">
							${warehouse.kwStatus}
						</td>
						<td align="center">
							${warehouse.markTyptName}
						</td>
						<td align="center">
							${warehouse.totalNumber}
						</td>
						<td align="center">
							<s:if test='kwStatus=="开"'>
								<a
								href="WaigouwaiweiPlanAction!sendTow.action?warehouseCertificate.id=${warehouse.id}&id=${ids}">关门</a>
							</s:if>
							<s:else>
								<a onclick="shansuo(${warehouse.id})" style="text-decoration:none;">闪烁</a>
								&nbsp;&nbsp;
								<a
								href="WaigouwaiweiPlanAction!oactoWeb.action?warehouseCertificate.id=${warehouse.id}&id=${ids}">开门</a>
							</s:else>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="8" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="8" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function shansuo(id){
	$.ajax( {
		url : "WarehouseApplicationAction_shansuo.action",
		type : 'post',
		dataType : 'json',
		cache : false,
		data : {
			"id" : id,
		},
	});
}
function sendTow(id){
	$.ajax( {
		url : "WarehouseApplicationAction_sendTow.action",
		type : 'post',
		dataType : 'json',
		cache : false,
		data : {
			"id" : id,
		},
	});
}
</script>
	</body>
</html>
