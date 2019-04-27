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
				<div>
				 <div >当月满绩效:<font size="6">${investorMonthJx.monthMoney}￥</font>&nbsp;&nbsp;
				&nbsp;&nbsp;当月可充值金额:<font size="6">${investorMonthJx.syMoney}￥</font>
				<br>
				<form action="QuotedPrice_inmoney.action">
				<input id="id" name="id" type="hidden" value="${investor.id}">
				<input id="inmoney" name="money" onkeyup="checkMoney()">
				<input type="submit" value="充值">				 
				</form>
				 </div>
				 <div >
				 <br/>
				 <br/>
				 <br/>
				 <form action="QuotedPrice_showInvestorsMsg.action" method="post">
				<input type="hidden" value="${id}" name="id">
				 <table class="table">
				  <tr>
				  <th>零件:</th><td><input name="quotedPriceUserCost.userName"> </td>
				  <th>开始时间:</th><td><input name="start" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"> </td>
				  <th>结束时间:</th><td><input name="end" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"> </td>
				  </tr>
				 </table>
				</form>
				<br/>
				 <table class="table">
				<tr>
						<td colspan="13" align="center"><h3>项目投资记录</h3></td>
					</tr>
					<tr>
					 <th>
					 	序号
					 </th>
					 <th>
					 	投入时阶段金额
					 </th>
					 <th>
					 	投资份数
					 </th>
					 <th>
					 	单份金额
					 </th>
					 <th>
					 	投资金额
					 </th>
					 <th>
					 	获利
					 </th>
					 <th>
					 	申请人
					 </th>
					 <th>
					 	申请人部门
					 </th>
					 <th>
					 	项目阶段
					 </th>
					 <th>
					 	审批状态
					 </th>
					 <th>
					 	备注
					 </th>
					 <th>
					 	申请时间
					 </th>
					 <th>
					 	操作
					 </th>
					</tr>
					<s:iterator value="qpUserCostList" id="pageQpUserCost" status="pageStatus" >
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
					 	<s:property value="#pageStatus.index+1"/>
					 </td>
					 <td>
					 	${pageQpUserCost.timeMoney}
					 </td>
					 <td>
					 	${pageQpUserCost.tzFenshu}
					 </td>
					 <td>
					 	${quotedPrice.dfMoney}
					 </td>
					 <td>
					 	${pageQpUserCost.tzMoney}
					 </td>
					 <td>
					 	${pageQpUserCost.klMoney}
					 </td>
					 <td>
					 	${pageQpUserCost.userName}
					 </td>
					 <td>
					 	${pageQpUserCost.dept}
					 </td>
					 <td>
					 	${pageQpUserCost.proStatus}
					 </td>
					 <td>
					 	${pageQpUserCost.applyStatus}
					 </td>
					 <td>
					 	${pageQpUserCost.remark}
					 </td>
					 <td>
					 	${pageQpUserCost.addTime}
					 </td>
					 <td>
<%--					 <s:if test="quotedPrice.status!='项目批产'">--%>
<%--					 </s:if>--%>
					 <a onclick="showFh(${pageQpUserCost.id})">分红详情</a>
					 	<a href="CircuitRunAction_findAduitPage.action?id=${pageQpUserCost.epId}">审批动态</a>
					 </td>
					</tr>
					</s:iterator>
				</table>
				 </div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function checkMoney(){
			if(!mustBeNumber("inmoney")){
				return ;
			}
			var max ="${investorMonthJx.syMoney}"
			if(($("#inmoney").val()-max)>0){
				$("#inmoney").val(max);
				alert("对不起您输入的金额超过了您目前最大的可充值额度!");
			}
		}
		</script>
	</body>
</html>
