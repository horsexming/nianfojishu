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
		<link rel="stylesheet" type="text/css" href="../css/lockTr/normalize.css">
		<link rel="stylesheet" type="text/css" href="../css/lockTr/demo.css">
		<link rel="stylesheet" type="text/css" href="../css/lockTr/component.css">
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="WaigouwaiweiPlanAction!findNOPriceWgPlanList.action"
					method="post" id="form">
					<input type="hidden" name="viewStatus" value="${viewStatus}" />
					<input type="hidden" name="pageStatus" value="${pageStatus}" />
					<table class="table">
						<tr>
							<th colspan="6">
								采购计划管理
							</th>
						</tr>
						<tr>
							<th align="right">
								订单号:
							</th>
							<td>
								<input name="procard.orderNumber" value="${procard.orderNumber}" />
							</td>
							<th align="right">
								总成件号:
							</th>
							<td>
								<input name="procard.ywMarkId" value="${procard.ywMarkId}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								件号:
							</th>
							<td>
								<input name="procard.markId" value="${procard.markId}" />
							</td>
							<th align="right">
								名称:
							</th>
							<td>
								<input name="procard.proName" value="${procard.proName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								类别:
							</th>
							<td>
								<select id="wgType" name="procard.wgType" style="width: 155px;">
								<option><s:property value="procard.wgType"/></option>
								<option></option>
								<option>原材料</option>
										</select>
							</td>
							</td>
							<th align="right">
							</th>
							<td>
							</td>
						</tr>

						<tr>
							<th colspan="6">
								<input type="submit" value="查询" class="input" />
								<input type="reset" value="清空" class="input" />
								<input type="button" value="导出所有待录入" class="input" onclick="clicks1();todisabledone(this)" data="downData"/>
							</th>
						</tr>
					</table>
				</form>
				<form action="WaigouwaiweiPlanAction!addWgOrder.action"
					method="post">
					<table class="table sticky-enabled">
						<tr>
							<th colspan="23"
								style="height: 35px; color: #ffffff; background-color: red;">
								待通知、确认的采购计划
							</th>
						</tr>
						<thead>
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								订单号
							</th>
							<th align="center">
								总成件号
							</th>
							<th align="center">
								业务件号
							</th>
							<th align="center">
								总成批次
							</th>
							<th align="center">
								类别
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								版本
							</th>
							<th align="center">
								批次
							</th>
							<th align="center">
								零件名称
							</th>
							<th align="center">
								供料属性
							</th>
							<th align="center">
								需求数量
							</th>
							<th align="center">
								采购数量
							</th>
							<th align="center">
								单位
							</th>
							<th align="center">
								操作
							</th>
						</tr>
						</thead>
						<tbody>
						<s:iterator value="procardList" id="pageWgww" status="pageStatus">
							<s:if test="#pageStatus.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<th>
								<s:property value="#pageStatus.index+1" />
							</th>
							<td>
								${pageWgww.orderNumber}
							</td>
							<td>
								<font color="green">${pageWgww.rootMarkId}</font>
							</td>
							<td>
								<s:if test="#pageWgww.ywMarkId!=null&&#pageWgww.ywMarkId!=''">
									<font color="green">${pageWgww.ywMarkId}</font>
								</s:if>
							</td>
							<td>
								<font color="green">${pageWgww.rootSelfCard}</font>
							</td>
							<s:if test="#pageWgww.trademark!=null&&#pageWgww.trademark!=''">
								<td>
									原材料
								</td>
								<th align="left" style="color: red">
									${pageWgww.trademark}
								</th>
								<th align="left">
									${pageWgww.specification}
								</th>
								<th style="color: blue">
								</th>
								<th>
									${pageWgww.selfCard}
								</th>
								<s:if test="pageStatus=='wwPlan'">
									<td>
										${pageWgww.processNo}
									</td>
								</s:if>
								<th align="left">
									${pageWgww.yuanName}
								</th>
							</s:if>
							<s:else>
								<th>
									${pageWgww.wgType}
								</th>
								<th align="left" style="color: red">
									${pageWgww.markId}
								</th>
								<th align="left">
									${pageWgww.specification}
								</th>
								<th style="color: blue">
									${pageWgww.banBenNumber}
								</th>
								<th>
									${pageWgww.selfCard}
								</th>
								<s:if test="pageStatus=='wwPlan'">
									<td>
										${pageWgww.processNo}
									</td>
								</s:if>
								<th align="left">
									${pageWgww.proName}
								</th>
							</s:else>
							<td>
								${pageWgww.kgliao}
							</td>
							<td align="right">
								${pageWgww.needCount}
							</td>
							<td align="right">
								${pageWgww.cgNumber}
							</td>
							<td align="right">
								<s:if test="#pageWgww.trademark!=null&&#pageWgww.trademark!=''">
									${pageWgww.yuanUnit}
								</s:if>
								<s:else>${pageWgww.unit}</s:else>
							</td>
						</s:iterator>
						<tr>
							<td colspan="23" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
							</td>
						</tr>
						</tbody>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(document).ready(function(){
	getCaizhi("wgType");
})
	function clicks(){
    	document.getElementById('form').action="WaigouwaiweiPlanAction!exportNoPrice.action";
  		document.getElementById('form').submit();
    	document.getElementById('form').action="WaigouwaiweiPlanAction!findNOPriceWgPlanList.action?cpage=${cpage}";
  }
</script>
	<script src="../javascript/lockTr/jquery.min(1).js"></script>
		<script src="../javascript/lockTr/jquery.ba-throttle-debounce.min.js"></script>
		<script src="../javascript/lockTr/jquery.stickyheader.js"></script>
	</body>
</html>
