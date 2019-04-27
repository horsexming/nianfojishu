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

			<div align="center">
				<h3>
					入库申请查询
				</h3>
				<%-- <form action="SpecialDateAction_showList.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 10%">
								日期
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="specialDate.date" />
							</td>
							<th align="center" style="width: 10%">
								班次
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="specialDate.banciName" />
							</td>
							<td align="center" style="width: 30%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>--%>
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
							存取类型
						</td>
						<td align="center">
							操作类型
						</td>
						<td align="center">
							操作数量
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="warehouseCertificateList" id="warehouse"
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
							${warehouse.warehouseName}
						</td>
						<td align="center">
							${warehouse.useStatus}
						</td>
						<td align="center">
							${warehouse.depositTake}
						</td>
						<td align="center">
							${warehouse.type}
						</td>
						<td align="center">
							${warehouse.number}
						</td>
						<td align="center">
							<s:if test='useStatus=="待开门"'>
								<a onclick="shansuo(${warehouse.id})" style="text-decoration:none;">闪烁</a>
								&nbsp;&nbsp;
								<a
								href="WarehouseApplicationAction_openandClose.action?warehouseCertificate.id=${warehouse.id}&id=${ids}">开门</a>
								</s:if>
							<s:elseif test='useStatus=="待关门"'>
								<a
								href="WarehouseApplicationAction_openandClose.action?warehouseCertificate.id=${warehouse.id}&id=${ids}">关门</a>
							</s:elseif>
							<s:elseif test='useStatus=="待确认"'>
								<a
								href="WarehouseApplicationAction_queren.action?warehouseCertificate.id=${warehouse.id}&id=${ids}">
									确认已存入
								</a>
							</s:elseif>
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
		cache : false,//防止数据缓存
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
		cache : false,//防止数据缓存
		data : {
			"id" : id,
		},
	});
}
</script>
	</body>
</html>
