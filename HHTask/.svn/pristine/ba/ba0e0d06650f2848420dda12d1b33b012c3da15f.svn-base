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
		<div id="gongneng">
			
			<div align="center">
				<form action="SubMonthMoneyAction!findSmmByCondition.action"
					method="post">
					<table class="table">
						<tr>
							<td>
								名称：
								<input type="text" name="subMonthMoney.name" />
							</td>
							<td>
								科目月份：
								<input class="Wdate" type="text" id="yuefen"
									name="subMonthMoney.budgetMonth"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<td>
								部门月份：
								<input class="Wdate" type="text" id="yuefen1"
									name="deptMonthBudget.budgetMonth"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
									<span style="color:red">* 导出用</span>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type="submit" style="width: 100px; height: 40px;"
									value="查询" class="input" />
								<input type="button" style="width: 100px; height: 40px;"
									value="科目预算导出" class="input" onclick="daochuExec();todisabledone(this)" data="downData"/>
									<input type="button" style="width: 100px; height: 40px;"
									value="部门预算导出" class="input" onclick="daochuExec1();todisabledone(this)" data="downData" />
							</td>
						</tr>
					</table>
				</form>
				<table  class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							月份
						</th>
						<s:if test="pageStatus=='all'">
							<th align="center">
								科目所占比例
							</th>
							<th align="center">
								预算金额
							</th>
							<th align="center">
								计划内金额
							</th>
							<th align="center">
								计划外金额
							</th>
						</s:if>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="smmList" id="pageSmm" status="pageStatus">
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
							<s:property value="#pageStatus.index+1" />
							<s:if test='#pageStatus.index=="0"'>
								<input type="hidden" name="time2" id="time2"
									value="${pageSmm.budgetMonth}" />
							</s:if>
						</td>
						<td>
							${pageSmm.name}
						</td>
						<td>
							${pageSmm.budgetMonth}
						</td>
						<s:if test="pageStatus=='all'">
							<td>
								${pageSmm.subjectRate}
							</td>
							<td>
								${pageSmm.monthBudgetMoney}
							</td>
							<td>
								${pageSmm.monthRealMoney}
							</td>
							<td>
								${pageSmm.waiMonthRealMoney}
							</td>
							<td>
								<a
									href="<%=basePath%>System/caiwu/budget/subMonthMoney_all_manage.jsp?rootId=${pageSmm.rootId}&pageStatus=${pageStatus}">预算总额</a>/
						</s:if>
						<s:else>
							<td>
								<a
									href="<%=basePath%>System/caiwu/budget/subMonthMoney_manage.jsp?rootId=${pageSmm.rootId}&pageStatus=${pageStatus}">填报</a>/

							
						</s:else>
						<a
							href="SubMonthMoneyAction!findAuditDmB.action?id=${pageSmm.rootId}&pageStatus=${pageStatus}">填报明细</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
//	  var time2=document.getElementById("time2").value;
//	document.getElementById("yuefen").value= time2;
function daochuExec(){
		 var yuefen=document.getElementById("yuefen").value;
		 window.location.href="SubMonthMoneyAction!exportExcel.action?subMonthMoney.budgetMonth="+yuefen;
	}
	
	function daochuExec1(){
		 var yuefen=document.getElementById("yuefen1").value;
		 window.location.href="SubMonthMoneyAction!exportExcel1.action?deptMonthBudget.budgetMonth="+yuefen;
	}
	</SCRIPT>
</html>
