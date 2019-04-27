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
			<div align="center">
			 零件号:<font color="red">${quotedPrice.markId}</font>&nbsp;&nbsp;阶段:<font color="red">${quotedPrice.status}</font> &nbsp;&nbsp;总费用:<font color="red">${quotedPrice.allFeiyong}</font>&nbsp;&nbsp; 投资总额:<font color="red">${quotedPrice.touziFeiyong}</font>&nbsp;&nbsp; 盈亏:<font color="red">${quotedPrice.yingkui}</font>
			</div>
			<form action="QuotedPrice_addQpCost.action" method="post">
			<input type="hidden" name="quotedPriceCost.qpId" value="${quotedPrice.id}">
			<table  class="table">
			<tr>
			 <td colspan="8" align="center"><h3>申请项目阶段费用</h3> </td>
			 </tr>
			 <tr>
			 <td>项目阶段 :</td>
			 <td align="center"> <select name="quotedPriceCost.proStatus" >
			  	<option>核算完成</option>
			  	<option>项目首件</option>
			  	<option>项目试制</option>
<%--			  	<option>项目批产</option>--%>
			 </select></td>
			 <td>类型 :</td>
			 <td align="center"> <select name="quotedPriceCost.costType" >
			  	<option>餐旅费</option>
			  	<option>维修成本</option>
			  	<option>间接人工</option>
			  	<option>行政费用</option>
			  	<option>其他费用</option>
			 </select></td>
			 <td>花费金额:</td>
			 <td align="center"> <input id="costmoney"  name="quotedPriceCost.money" onkeyup="mustBeNumber('costmoney')">
			 </td>
			 <td>备注:</td>
			 <td align="center"> 
			 <textarea name="quotedPriceCost.remark" rows="2" cols="15"></textarea>
			 </td>
			 </tr>
			 <tr>
			 <td colspan="8" align="center"><input type="submit" value="提交"/></td>
			 </tr>
			</table>
			</form>
			<div>
			<br/>
			<br/>
			</div>
				<table class="table">
					<tr>
						<td colspan="11" align="center"><h3>项目阶段费用明细</h3></td>
					</tr>
					<tr>
					 <th>
					 	类型
					 </th>
					 <th>
					 	金额
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
					 	来源
					 </th>
					 <th>
					 	操作
					 </th>
					</tr>
					<s:iterator value="qpCostList" id="pageQpCost" status="pageStatus" >
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
					 	${pageQpCost.costType}
					 </td>
					 <td>
					 	${pageQpCost.money}
					 </td>
					 <td>
					 	${pageQpCost.userName}
					 </td>
					 <td>
					 	${pageQpCost.dept}
					 </td>
					 <td>
					 	${pageQpCost.proStatus}
					 </td>
					 <td>
					 	${pageQpCost.applyStatus}
					 </td>
					 <td>
					 	${pageQpCost.remark}
					 </td>
					 <td>
					 	${pageQpCost.addTime}
					 </td>
					 <td>
					 	${pageQpCost.source}
					 </td>
					 <td>
					 <s:if test="#pageQpCost.source=='报价申报'">
					 	<a href="CircuitRunAction_findAduitPage.action?id=${pageQpCost.epId}">审批动态</a>
					 </s:if>
					 </td>
					</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
