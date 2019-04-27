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
					<span><b>退货单模式</b></span>
				&nbsp;&nbsp;&nbsp;
				<a href="WaigouwaiweiPlanAction!findReturnsDetails.action?pageStatus=${pageStatus}&tag=${tag}">退货单明细模式</a>
				<form action="WaigouwaiweiPlanAction!findReturnSingle.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								退货单号
							</th>
							<td>
								<input type="text" name="rs.planNum" value="${rs.planNum}" />
							</td>
							<th align="right">
								供应商
							</th>
							<td>
								<input type="text" name="rs.gysName" value="${rs.gysName}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								生成日期从
							</th>
							<td>
								<input type="text" name="firsttime" value="${firsttime}"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								止
							</th>
							<td>
								<input type="text" name="endtime" value="${endtime}"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${tag}" name="tag" />
					<input type="submit" value="查询" class="input" />
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							退货单号
						</th>
						<th>
							供应商
						</th>
						<th>
							供应商地址
						</th>
						<th>
							供应商联系人
						</th>
						<th>
							供应商电话
						</th>
						<th>
							退货数量(合计)
						</th>
						<th>
							处理人
						</th>
						<th>
							处理时间
						</th>
						<th>
							类型
						</th>
						<th>
							状态
						</th>
						<th>
							批准时间
						</th>
						<th>
							审批动态
						</th>
						<td>
							操作
						</td>
					</tr>
					<s:iterator value="list" id="pageList" status="statussdf">
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
							${pageList.planNum}
						</td>
						<td>
							<a href="zhaobiaoAction!listByIdZhUser.action?zhUser.id=${pageList.gysId}">${pageList.gysName}</a>
						</td>
						<td>
							${pageList.gysdizhi}
						</td>
						<td>
							${pageList.gysUser}
						</td>
						<td>
							${pageList.gysPhone}
						</td>
						<td>
							${pageList.thNumber}
						</td>
						<td>
							${pageList.printUser}
						</td>
						<td>
							${pageList.printTime}
						</td>
						<td>
							${pageList.type}
						</td>
						<td>
							${pageList.status}
						</td>
						<td>
							${pageList.approvalTime}
						</td>
						<td>
							<a href="CircuitRunAction_findAduitPage.action?id=${pageList.epId}">${pageList.epstatus}</a>
						</td>
						<td>
							<s:if test="pageStatus != 'gys'">
							<s:if test='#pageList.status == "待领"'>
								<a
									href="WaigouwaiweiPlanAction!findReturnsDetailsByrsId.action?id=${pageList.id}">退货打印</a>/
							</s:if>
							
							<a
								href="WaigouwaiweiPlanAction!findReturnsDetailsByrsId.action?id=${pageList.id}&tag=bd">补打</a>
							</s:if>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="15" align="right">
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
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
