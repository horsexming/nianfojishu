<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<form action="ProcardAction!jisunLpPeoPleMoney.action" method="post">
					<table class="table">
						<tr>
							<th colspan="6">
								<font size="5">批产奖金自动计算系统</font>
							</th>
						</tr>
						<tr>
							<th>
								选择日期
							</th>
							<td>
								起
								<input class="Wdate" type="text" name="startDate"
									id="kaishidate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									value="${startDate}" />
								&nbsp;&nbsp;&nbsp; 终

								<input class="Wdate" type="text" name="endDate" id="enddate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
									value="${endDate}" />
							</td>
							<th>
								所属月份
							</th>
							<td>
								<input class="Wdate" type="text" name="dateTime" id="kaishidate"
									onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"
									value="${dateTime}" />
							</td>
							<th>
								件号:
							</th>
							<td>
								<input name="markid" value="${markid}" />
								<input type="submit" value="确   定" />
								&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</form>
				<s:if test="obj!=null">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th colspan="5">
								批产系统个人奖金分配明细
							</th>
						</tr>
						<tr bgcolor="#c0dcf2">
							<th>
								序号
							</th>
							<th>
								工号
							</th>
							<th>
								姓名
							</th>
							<th>
								部门
							</th>
							<th>
								奖金
							</th>
						</tr>
						<s:iterator id="money" value="obj[0]" status="stauts">
							<tr>
								<th>
									${stauts.index+1}
								</th>
								<th>
									<s:property value="value[1]" />
								</th>
								<th>
									<s:property value="value[2]" />
								</th>
								<th>
									<s:property value="value[3]" />
								</th>
								<th>
									<s:property value="value[4]" />
								</th>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="5">
								<input class="input" value="确认分配" type="button">
							</th>
						</tr>
					</table>
					<br />
					<br />
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th colspan="8">
								产品奖金总额明细
							</th>
						</tr>
						<tr bgcolor="#c0dcf2">
							<th>
								序号
							</th>
							<th>
								件号
							</th>
							<th>
								批次
							</th>
							<th>
								出库数量
							</th>
							<th>
								总节拍
							</th>
							<th>
								单件金额
							</th>
							<th>
								总金额
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator id="markid" value="obj[1]" status="stauts2">
							<tr>
								<th>
									${stauts2.index+1}
								</th>
								<th>
									<s:property value="value[1]" />
								</th>
								<th>
									<s:property value="value[2]" />
								</th>
								<th>
									<s:property value="value[3]" />
								</th>
								<th>
									<s:property value="value[4]" />
								</th>
								<th>
									<s:property value="value[5]" />
								</th>
								<th>
									<s:property value="value[6]" />
								</th>
								<th>
									<a target="_blank"
										href="ProcardAction!findProcardView.action?id=<s:property value='value[0]'/>&pageStatus=history&viewStatus=zjl">生产进度</a>
								</th>
							</tr>
						</s:iterator>
					</table>
				</s:if>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
