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
			<div align="center">
			<div align="right" style="float: left;width: 70%;">
			 零件号:<font color="red">${quotedPrice.markId}</font>&nbsp;&nbsp;阶段:<font color="red">${quotedPrice.status}</font>&nbsp;&nbsp; 投放总额:<font color="red">${quotedPrice.tfzonge}</font>&nbsp;&nbsp; 单份金额:<font color="red">${quotedPrice.dfMoney}</font>&nbsp;&nbsp;
			 总份数:<font color="red">${zongCount}</font>&nbsp;&nbsp; 剩余份数:<font color="red">${shengyuCount}</font>&nbsp;&nbsp;
			</div>
			<div style="float: left;width: 29%;" align="right">
			 可用资金:<font color="red">${investor.kyMoney}</font>
			</div>
			<div style="clear: both;"></div>
			</div>
			<s:if test="quotedPrice.fbSatuts==null||quotedPrice.fbSatuts=='未放标'">
				<h2><font color="red">对不起,此项目还未放标!</font></h2>
			</s:if>
			<s:elseif test="quotedPrice.fbSatuts=='关闭'">
				<h2><font color="red">对不起,此项目已关闭投资!</font></h2>
			</s:elseif>
			<s:else>
			<form action="QuotedPrice_addQpUserCost.action" method="post">
			<input type="hidden" name="quotedPriceUserCost.qpId" value="${quotedPrice.id}">
			<input type="hidden" name="pageStatus" value="${pageStatus}">
			<table  class="table">
			<tr>
			 <td colspan="6" align="center"><h3>项目阶段投资</h3> </td>
			 </tr>
			 <tr>
<%--			 <td>类型 :</td>--%>
<%--			 <td align="center"> <select name="quotedPriceUserCost.costType" id="costType">--%>
<%--			  	<option></option>--%>
<%--			 </select></td>--%>
			 <td>投资份数:</td>
			 <td align="center"> <input id="tzFenshu"  name="quotedPriceUserCost.tzFenshu" onkeyup="tzfenshu()">
			 </td>
			 <td>投资金额:</td>
			 <td align="center"> <input id="costmoney"  name="quotedPriceUserCost.tzMoney" readonly="readonly">
			 </td>
			 <td>备注:</td>
			 <td align="center" colspan="3"/> 
			 <textarea name="quotedPriceUserCost.remark" rows="2" cols="80"></textarea>
			 </td>
			 </tr>
			 <tr>
			 <td colspan="6" align="center"><input type="submit" value="提交"/></td>
			 </tr>
			</table>
			</form>
			</s:else>
			<div>
			<br/>
			<br/>
			</div>
			<s:if test="pageStatus=='alltz'">
				<form action="QuotedPrice_findQpDetailForCost.action" method="post">
				<input type="hidden" value="${pageStatus}" name="pageStatus">
				<input type="hidden" value="${id}" name="id">
				 <table class="table">
				  <tr>
				  <th>姓名:</th><td><input name="quotedPriceUserCost.userName"> </td>
				  <th>工号:</th><td><input name="quotedPriceUserCost.userCode"> </td>
				  <th>项目阶段:</th>
				  <td><select>
				  		<option></option> 
				  		<option>核算完成</option> 
				  		<option>项目试制</option> 
				  		<option>项目首件</option> 
				  		<option>项目批产</option> 
				  </select> </td>
				  </tr>
				 </table>
				</form>
				<br/>
			</s:if>
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
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
		$(document).ready(function() {
			showCostType();
		});
		function showCostType(){
			$.ajax( {
		type : "post",
		url : "QuotedPrice_showCostType.action",
		data :{
			id : ${id}	
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
					function() {
						$("<option value='" + this.costType + "'>" + this.costType
									+"("+ this.tzMoney +"/"+ this.money + ")</option>").appendTo("#costType");
					});

		}
	});
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
	
	function tzfenshu(){
		var tzFenshu=$("#tzFenshu").val();
		if(isNaN(tzFenshu)){
		alert("请输入数字");
		$("#tzFenshu").val(0);
		$("#costmoney").val(0);
		return false;
		}else{
			var dfMoney="${quotedPrice.dfMoney}";
			var shengyuCount = "${shengyuCount}";
			if((shengyuCount-tzFenshu)>=0){
				$("#costmoney").val(tzFenshu*dfMoney);
			}else{
				alert("投资超额!");
				$("#tzFenshu").val(0);
				$("#costmoney").val(0);
			}
		}
	}
		</script>
	</body>
</html>
