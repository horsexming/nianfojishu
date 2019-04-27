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
				<div id="printDiv">
					<table class="table">
						<tr>
							<th colspan="6">
								<input value="扫描送货单条码" style="height: 60px;" type="button"
									onclick="getcheckList2()">
								<form
									action="WaigouwaiweiPlanAction!findDeliveryDeByBacode.action?pageStatus=${pageStatus}"
									method="post">
									或输入送货单条码:
									<input type="text" name="bacode">
									<input type="submit" value="确定">
								</form>
							</th>
						</tr>
						<tr>
							<th colspan="6">
								<form
									action="WaigouwaiweiPlanAction!findDqrDelivery.action"
									method="post">
									送货单号:
									<input type="hidden" name="pageStatus" value="${pageStatus}">
									<input type="text" name="waigouDelivery.planNumber">
									<input type="submit" value="查询">
								</form>
							</th>
						</tr>
						<tr>
							<th colspan="6">
								送货中物料明细
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								送货单号
							</th>
							<th align="center">
								供应商
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								送货人手机
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageWgww2" status="pageStatus2">
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
							<td align="left">
								${pageWgww2.planNumber}
							</td>
							<td align="left">
								${pageWgww2.userCode}
							</td>
							<td align="right">
								${pageWgww2.status}
							</td>
							<td>
								${pageWgww2.shContactsPhone}
							</td>
							<td>
								<a
								href="WaigouwaiweiPlanAction!findDeliveryNoteDetail.action?id=${pageWgww2.id}&pageStatus=ss">送货明细</a>
							</td>
						</s:iterator>
						<s:if test="list.size()<=0">
							<tr>
								<th colspan="6">
									当前用户没有待签收任务,可以休息一会儿了~
								</th>
							</tr>
						</s:if>
						<s:else>
							<tr>
							<td colspan="6" align="right">
								第
								<font size="8" color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</tr>
						</s:else>
					</table>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "WaigouwaiweiPlanAction!findDeliveryDeByBacode.action?bacode="
			+ tm+"&pageStatus=${pageStatus}";
}
</script>
	</body>
</html>
