<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<form action="WageAction!huizongWage.action" method="post">
					选择月份:
					<input class="Wdate" type="text" name="wage.mouth"
						onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"
						value="${wage.mouth}" />
					<input type="submit" value="查询" />

				</form>
				<a href="javascript:pagePrint('hzWage','no');">预览</a>
				<a href="javascript:pagePrint('hzWage');">打印</a>
				<div id="hzWage">
					<div
						style="padding-top: 50px; padding-left: 10px; padding-right: 10px;">
						<table class="table" style="width: 100%; font-size: 14px;">
							<tr>
								<th align="center" colspan="23">
									<s:if test="wage.mouth==''">
									所有月份的工资汇总信息
								</s:if>
									<s:else>
									${wage.mouth}的工资汇总信息
								</s:else>
								</th>
							</tr>
							<tr>
								<th>
									序号
								</th>
								<th>
									部门
								</th>
								<th>
									人数
								</th>
								<th>
									岗位工资
								</th>
								<th>
									保密津贴
								</th>
								<th>
									补贴
								</th>
								<th>
									技能工资
								</th>
								<th>
									特殊补贴
								</th>
								<th>
									奖金
								</th>
								<th>
									绩效考核
								</th>
								<th>
									加班费
								</th>
								<th>
									其他
								</th>
								<th>
									应发工资
								</th>
								<th>
									病事旷等
								</th>
								<th>
									养老保险
								</th>
								<th>
									医疗保险
								</th>
								<th>
									失业保险
								</th>
								<th>
									公积金
								</th>
								<th>
									午餐费
								</th>
								<th>
									水电费
								</th>
								<th>
									房租费
								</th>
								<th>
									补发(补扣)工资
								</th>
								<th>
									应交税金
								</th>
								<th>
									实发工资
								</th>
							</tr>
							<s:iterator id="hzWage" value="hzWageList" status="pageStatus">
								<tr>
									<td align="center">
										${pageStatus.index+1}
									</td>
									<td>
										${hzWage[0]}
									</td>
									<td>
										${hzWage[1]}
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[2]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[3]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[4]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[5]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[6]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[7]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[8]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[9]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[10]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[11]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[12]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[13]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[14]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[15]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[16]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[17]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[18]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[19]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[20]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[21]}" />
									</td>
									<td>
										<fmt:formatNumber pattern="0.00" value="${hzWage[22]}" />
									</td>
								</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
