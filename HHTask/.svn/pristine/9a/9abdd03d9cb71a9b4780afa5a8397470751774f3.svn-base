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
			<div align="left">
				<div id="printDiv">
					<br />
					<div
						style="font-weight: bolder; font-size: 30px; margin-bottom: 10px;"
						align="center">
						请确认已存入数量
					</div>
					<form action="WaigouwaiweiPlanAction!updateWNToRG.action" method="post"
					onsubmit="return validate()">
						<table class="table">
							<tr bgcolor="#c0dcf2" height="50px">
								<th align="center">
									序号
								</th>
								<th align="center">
									件号<input type="hidden" name="warehouseNumber.id" value="${warehouseNumber.id}"/>
								</th>
								<th align="center">
									零件名称
								</th>
								<th align="center">
									数量
								</th>
								<th align="center">
									物料位置
								</th>
							</tr>
							<s:iterator value="list_wdd" id="pageWgww2" status="pageStatus2">
								<s:if test="#pageStatus2.index%2==1">
									<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										style="height: 50px;" onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<s:property value="#pageStatus2.index+1" />
								</td>
								<td>
									${pageWgww2.markId}
								</td>
								<td>
									${pageWgww2.proName}
								</td>
								<td >
									<input type="hidden" name="list_wdd[${pageStatus2.index}].id"
										value="${pageWgww2.id}">
									<s:if test="#pageWgww2.ycNumber==null">
										<input type="text" style="width: 60px;"
											name="list_wdd[${pageStatus2.index}].ycNumber"
											value="${pageWgww2.qrNumber}">${pageWgww2.unit}
									</s:if>
									<s:else>
										<input type="text" style="width: 60px;"
											name="list_wdd[${pageStatus2.index}].ycNumber"
											value="${pageWgww2.qrNumber-pageWgww2.ycNumber}">${pageWgww2.unit}
									</s:else>
								</td>
								<td>
									${pageWgww2.qrWeizhi}
								</td>
							</s:iterator>
							<tr>
								<td colspan="5" align="center" >
									<input type="submit" value="确认" style="width: 60px;height: 40px;" id="queren"/>
								</td>
							</tr>
							<s:if test="list.size()<=0">
								<tr>
									<th colspan="5">确认异常</th>
								</tr>
							</s:if>
						</table>
					</form>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	$("#queren").attr("disabled", "disabled");
}

</script>
	</body>
</html>
