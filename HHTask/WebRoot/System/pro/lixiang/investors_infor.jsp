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
	<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
	<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">项目分红</span>
						</td>
						<td align="right">
							<img id="closeimg" alt=""
								src="<%=basePath%>/images/closeImage.png" width="30" height="32"
								onclick="chageDiv('none');">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="addProIf" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 100%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div>
				 <div >
				  总资金:<font size="6">
				 <s:if test="investor.hasMoney==null">0
				 </s:if>
				 <s:else>
				 ${investor.hasMoney}
				 </s:else>
				 ￥</font>&nbsp;&nbsp;
				  冻结资金:<font size="6">
				 <s:if test="investor.djMoney==null">0
				 </s:if>
				 <s:else>
				 ${investor.djMoney}
				 </s:else>
				 ￥</font>&nbsp;&nbsp;
				 可用资金:<font size="6">
				 <s:if test="investor.kyMoney==null">0
				 </s:if>
				 <s:else>
				 ${investor.kyMoney}
				 </s:else>
				 ￥</font>&nbsp;&nbsp;
				&nbsp;&nbsp;<button onclick="toinmoney(${investor.userId})">充值</button>				 
				 </div>
				 <div >
				 <br/>
				 <table class="table">
				  <tr>
				  	<td align="center" colspan="14">投资记录</td>
				  </tr>
				  <s:if test="qpUserCostList!=null&&qpUserCostList.size()>0">
				  	<tr>
					 <th>
					 	序号
					 </th>
					 <th>
					 	投入时阶段金额
					 </th>
					 <th>件号
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
					 	${pageQpUserCost.markId}
					 </td>
					 <td>
					 	${pageQpUserCost.tzFenshu}
					 </td>
					 <td>
					 	${pageQpUserCost.tzMoney/pageQpUserCost.tzFenshu}
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
				  </s:if>
				  <s:else>
				  <tr>
				   <td colspan="14"><font>目前没有投资记录</font> </td>
				  </tr>
				  </s:else>
				 </table>
				 </div>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		function toinmoney(id){
			window.location.href="QuotedPrice_toinmoney.action?id="+id;
		}
	function showFh(id){
		$("#addProIf")
			.attr(
					"src",
					"QuotedPrice_showFh.action?id="
							+ id);
	//绑定刷新页面
	$("#bodyDiv").bind("click", function() {
		chageDiv('none');
		reload();
	});
	$("#closeimg").bind("click", function() {
		chageDiv('none');
		reload();
	});
	chageDiv('block');
	}
		</script>
	</body>
</html>
