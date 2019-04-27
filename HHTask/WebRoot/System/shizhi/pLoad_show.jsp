.<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="productivityLoadAction_showList.action"
						style="color: #ffffff">刷新<br />(reflesh)</a>
				</div>
			</div>

			<div align="center">
				<h3>
					产能负荷系数管理
					<br />
					(Productivity load score Management)
				</h3>
				<form action="productivityLoadAction_showList.action" method="post">
					<table class="table" align="center">
					<tr>
					       <td align="center">
								零件号（part number）：
								<input type="text" name="productivityLoad.markId" value="<s:property value="productivityLoad.markId"/>" />
							</td>
							<td align="center">
								工序号（process）：
								<input type="text" name="productivityLoad.processNO" value="<s:property value="productivityLoad.processNO"/>" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
							<input type="submit" style="width: 100px; height: 40px;"
									value="查询(select)" />
								<input type="button" style="width: 100px; height: 40px;"
									value="更新数据(update all)" onclick="updateall()"/>
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="1" style="border-collapse: collapse;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
							<br />
							(num)
						</td>
						<td align="center">
							零件号<br/>(part number)
						</td>
						<td align="center">
							零件名称<br/>(part name)
						</td>
						<td align="center">
							工序号<br/>(processNO)
						</td>
						<td align="center">
							工序名称<br/>(process name)
						</td>
						<td align="center">
							产能负荷系数
							<br />
							(Productivity load score)
						</td>
						<td align="center">
							单件工序总工时
							<br />
							（Single step of the total man-hours）
						</td>
						<td align="center">
							工作日提交数量
							<br />
							（The number of working days to submit）
						</td>
						<td align="center">
							当月客户需求数量
							<br />
							（The number of customers needs on the month）
						</td>
						<td align="center">
							当月实际入库数量
							<br />
							（The actual number of storage on the month）
						</td><td align="center">
							交付实际工作日
							<br />
							（Actual delivery days）
						</td><td align="center">
							客户需求期限
							<br />
							（Customer demand deadline）
						</td><td align="center">
							工作日交付满足率
							<br />
							（Working day delivery satisfaction rate）
						</td><td align="center">
							超工作日交付总工时
							<br />
							（Ultra-day delivery of the total man-hours）
						</td><td align="center">
							超工作日工作时限
							<br />
							（Ultra-day delivery of the work time limit）
						</td>
						<td align="center" colspan="2">
							操作
							<br />
							(Operation)
						</td>
					</tr>
					<s:iterator value="pLoadList" id="pLoad" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
								<font>
							</s:if>
							<s:else>
								<font color="#c0dcf2">
							</s:else>
							<s:property value="#pageStatus.index+1" />
							</font>
						</td>
						<td>
							${pLoad.markId}
						</td>
						<td>
							${pLoad.procardName}
						</td>
						<td>
							${pLoad.processNO}
						</td>
						<td>
							${pLoad.processName}
						</td>
						<td>
							${pLoad.proLoad}
						</td>
						<td>
							${pLoad.singleTime}
						</td>
						<td>
							${pLoad.upNumber}
						</td>
						<td>
							${pLoad.curCusNeedNum}
						</td>
						<td>
							${pLoad.curMonInputNum}
						</td>
						<td>
							${pLoad.payDays}
						</td>
						<td>
							${pLoad.cusDeadline}
						</td>
						<td>
							${pLoad.satisfactionRate}
						</td>
						<td>
							${pLoad.totalWorkTime}
						</td>
						<td>
							${pLoad.workTimeLimits}
						</td>
						

						<td colspan="2">
							<input type="button" value="修改(update)"
								style="width: 60px; height: 30px;" onclick="update(${pLoad.id},${cpage})" />

							<input type="button" value="删除(delete)"
								style="width: 60px; height: 30px;"
								onclick="todelete(${pLoad.id})" />
						</td>

					</s:iterator>
					</tr>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="15" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="15" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>

					<s:if test="successMessage!=null">
						<tr>
							<td colspan="15" align="center" style="color: red">
								${successMessage}

							</td>
						</tr>
					</s:if>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function update(id,cpage) {
	window.location.href = "productivityLoadAction_toupdate.action?productivityLoad.id="
			+ id+"&cpage"+cpage;
}
function todelete(id) {
	window.location.href = "productivityLoadAction_delete.action?productivityLoad.id="
			+ id;
}
function updateall() {
	window.location.href = "productivityLoadAction_updateAll.action"
}
</script>
	</body>
</html>
