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
					取物信息查询
				</h3>
				<form action="ToolCabineAction_showList_rece.action"
					method="post">
					<table class="table">
						<tr style="width: 100%">
							<th align="center" style="width: 10%">
								物品名称
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="receiveCabinet.receiveWuName" />
							</td>
							<th align="center" style="width: 10%">
								物品规格
							</th>
							<td align="center" style="width: 25%">
								<input type="text" name="receiveCabinet.receiveFormat" />
							</td>
							<td align="center" style="width: 30%">
								<input type="submit" value="查询"
									style="width: 100px; height: 25px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							取物名称
						</td>
						<td align="center">
							取物规格
						</td>
						<td align="center">
							申请取物品数量
						</td>
						<td align="center">
							实际取物品数量
						</td>
						<td align="center">
							取物验证码
						</td>
						<td align="center">
							领取人名称
						</td>
						<td align="center">
							领取人部门
						</td>
						<td align="center">
							取物状态
						</td>
						<td align="center">
							添加时间
						</td>
						<td align="center" colspan="2">
							确认/取消
						</td>
					</tr>
					<s:iterator value="receiveCabinetList" id="receiveCabinets"
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
							${receiveCabinets.receiveWuName}
						</td>
						<td align="center">
							${receiveCabinets.receiveFormat}
						</td>
						<td align="center">
							${receiveCabinets.receiveQuantity}
						</td>
						<td align="center">
							${receiveCabinets.realReceiveQuantity}
						</td>
						<td align="center">
							${receiveCabinets.receiveVerificationCode}
						</td>
						<td align="center">
							${receiveCabinets.receiveName}
						</td>
						<td align="center">
							${receiveCabinets.receiveDept}
						</td>
						<td align="center">
							${receiveCabinets.receiveStatus}
						</td>
						<td align="center">
							${receiveCabinets.addTime}
						</td>
						<td align="center" colspan="2">
							${receiveCabinets.returnZhi}
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
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

</script>
	</body>
</html>
