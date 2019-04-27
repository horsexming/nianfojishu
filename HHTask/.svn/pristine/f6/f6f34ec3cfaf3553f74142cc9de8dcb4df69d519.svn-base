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
					<form action="ProcardAction!finddjyRootProcard.action" method="post">
						<table>
							<tr>
								<th align="right">
									件号
								</th>
								<td>
									<input type="text"
										style="width: 120px;" name="procard.markId" value="${procard.markId}"/>
								</td>
								<th align="right">产品编码</th>
								<td>
										<input type="text"
											style="width: 120px;" name="procard.ywMarkId" value="${procard.ywMarkId}"/>
								</td>
							</tr>
							<tr>
								<th align="right">
									批次
								</th>
								<td>
									<input type="text"
										style="width: 120px;" name="procard.selfCard" value="${procard.selfCard}"/>
								</td>
								<th align="right">订单号</th>
								<td>
										<input type="text"
											style="width: 120px;" name="procard.orderNumber" value="${procard.orderNumber}"/>
								</td>
							</tr>
						</table>
						<input type="submit" value="查询" class="input"/>
					</form>
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								订单号
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								业务件号
							</th>
							<th align="center">
								批次
							</th>
							<th align="center">
								数量
							</th>
							<th align="center">
								提交数量
							</th>
							<th align="center">
								合格数量
							</th>
							<th align="center">
								入库数量
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						<s:iterator value="procardList" id="pageProcard" status="pageStatus2">
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
								${pageProcard.orderNumber}
							</td>
							<td>
								${pageProcard.markId}
							</td>
							<td>
								${pageProcard.proName}
							</td>
							<td>
								${pageProcard.ywMarkId}
							</td>
							<td>
								${pageProcard.selfCard}
							</td>
							<td>
								${pageProcard.filnalCount}
							</td>
							<td>
								${pageProcard.tjNumber}
							</td>
							<td>
								<s:if test="#pageProcard.hgNumber == null">
									0 
								</s:if>
								<s:else>
									${pageProcard.hgNumber}
								</s:else>
							</td>
							<td>
								${pageProcard.rukuCount}
							</td>
							<td>
								<div
									style="width: 55px; height: 55px; border-radius: 50%; background-color: green; color: #ffffff; font-size: 10px;">
									<br />
									<a href="ProcardAction!toCheckwgww.action?id=${pageProcard.id}&pageStatus=${pageStatus}&cpage=${cpage}" 
										style=" font-size: 22px;color: white;"
										>检验</a>
								</div>
							</td>
						</s:iterator>
						<s:if test="procardList.size()<=0">
							<tr>
							<s:if test="pageStatus=='ww'">
							<th colspan="24">
							</s:if>
							<s:else>
							<th colspan="22">
							</s:else>
								没有待检验任务,可以休息一会儿了~</th>
							</tr>
						</s:if>
						<s:else>
							<tr>
							<s:if test="pageStatus=='ww'">
							<th colspan="24" align="right">
							</s:if>
							<s:else>
							<th colspan="22" align="right">
							</s:else>
								第
								<font size="14" color="red"><s:property value="cpage" /> </font> /
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
			+ tm;
}
</script>
	</body>
</html>
