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
				<h3>委托付款月度汇总申请</h3>
				<form action="EscrowAction_applyEscrowMonth.action" method="post">
				<table class="table">
				 <tr>
				 	<th>月份</th>
				 	<td><input id="month" name="escrowMonth.month" value="${month}" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" onchange="changemonth()"></td>
				 </tr>
				 <tr>
				 	<th>付款方</th>
				 	<td>
				 		<select id="paycom" name="escrowMonth.payCom">
				 		<s:if test="list!=null&&list.size()>0">
				 			<s:iterator value="list" id="str">
				 			<option><s:property value="#str"/></option>
				 			</s:iterator>
				 		</s:if>
				 		<s:else>
				 			<option>请先选择月份</option>
				 		</s:else>
				 		</select>
				 	</td>
				 </tr>
				 <tr>
				 <td colspan="2" align="center"><input value="申请" type="submit" style="width: 60px;height: 40px;"> </td>
				 </tr>
				</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function changemonth(){
	$.ajax( {
		type : "post",
		url : "EscrowAction_getPayComByMonth.action",
		data :{
			month : $("#month").val()
		},
		dataType : "json",
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this + "'>" + this
										+ "</option>").appendTo("#paycom");
					});
			$("#paycom").tinyselect();
		}
	});
}
</SCRIPT>
	</body>
</html>
