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
				<form action="" method="post" id ="form">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${cptu.markId}" name="cptu.markId" />
							</td>
							<th align="right">
								批次
							</th>
							<td>
								<input type="text" value="${cptu.selfCard}" name="cptu.selfCard" />
							</td>
						</tr>
						<tr>
						<tr>
							<th align="right">
								申请时间从
							</th>
							<td>
								<input class="Wdate" type="text" name="startDate"
									value="${startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',skin:'whyGreen'})" />
							</td>
							<th align="right">
								到
							</th>
							<td>
								<input class="Wdate" type="text" name="endDate"
									value="${endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd 23:59:59',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${cptu.ywmarkId}" name="cptu.ywmarkId" />
							</td>
							<th align="right">
								订单号
							</th>
							<td>
								<input type="text" value="${cptu.orderNo}" name="cptu.orderNo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								申请人
							</th>
							<td>
								<input type="text" value="${cptu.sqUsers}" name="cptu.sqUsers" />
							</td>
							<th align="right"></th>
							<td>
							</td>
						</tr>
					</table>
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" class="input"  onclick="exportExcel((this.form))"/>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							订单号
						</th>
						<th>
							件号
						</th>
						<th>
							业务件号
						</th>
						<th>
							批次
						</th>
						<th>
							名称
						</th>
						<th>
							库别
						</th>
						<th>
							仓区
						</th>
						<th>
							库位
						</th>
						<th>
							申请数量
						</th>
						<th>
							申请人
						</th>
						<th>
							申请时间
						</th>
						<th>
							审批动态
						</th>
						<th>
							打印
						</th>
					</tr>
					<s:iterator value="cptuList" id="pagecpth" status="">
						<s:if test="#statussdf.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#statussdf.index+1" />
						</td>
						<td>
							${pagecpth.orderNo}
						</td>
						<td>
							${pagecpth.markId}
						</td>
						<td>
							${pagecpth.ywmarkId}
						</td>
						<td>
							${pagecpth.selfCard}
						</td>
						<td>
							${pagecpth.proname}
						</td>
						<td>
							${pagecpth.kubie}
						</td>
						<td>
							${pagecpth.cangqu}
						</td>
						<td>
							${pagecpth.kuwei}
						</td>
						<td>
							${pagecpth.sqNum}
						</td>
						<td>
							${pagecpth.sqUsers}
						</td>
						<td>
							${pagecpth.addTime}
						</td>
						<td>
							<a
								href="CircuitRunAction_findAduitPage.action?id=${pagecpth.epId}">${pagecpth.epStatus}</a>
						</td>
						<td>
							<a href="sellAction!toCpthPrint.action?id=${pagecpth.id}">打印</a>
						</td>
					</s:iterator>
					<tr>

						<td colspan="30" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />

						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
		function exportExcel(objForm) {
			objForm.action = "sellAction!exprotcpth.action";
			objForm.submit();
			objForm.action = "sellAction!findAllChengPinTuiHuo.action";
		}
		</script>
	</body>
</html>
