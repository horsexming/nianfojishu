<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<center>
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
					<a
						href="bonusShiZhiAction_toadd.action"
						style="color: #ffffff">刷新<br/>(reflesh)</a>
				</div>
			</div>
			
			<div align="center">
				<h3>
					添加月奖金额<br/>
					（add bonusShiZhi）
				</h3>
				<h2>
				  <br/><font color="red">${errorMessage}</font>
				</h2>
				<form action="bonusShiZhiAction_add.action" method="post"
					onsubmit="return validate()">
					<table class="table">
						<tr>
							<th align="right">
								金额<br/>（bonus）
							</th>
							<td>
								<input type="text" name="bonusShiZhi.bonus" id="bonus">
							</td>
						</tr>
						<tr>
							<th align="right">
								月份<br/>（month）
							</th>
							<td>
								<input type="text"  class="Wdate" name="bonusShiZhi.month" 
								id="month" onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="right">
								<input type="submit" value="提交(submit) "
									style="width: 80px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function validate() {
	var bonus = document.getElementById("bonus").value;
	if (bonus== "") {
		alert("请输入金额");
		return false;
	}else if(isNaN(bonus)){
		alert("金额栏请输入数字");
		return false;
	}
	var month = document.getElementById("month").value;
	if (month== "") {
		alert("请输选择时间");
		return false;
	}

}
</script>
	</body>
</html>
