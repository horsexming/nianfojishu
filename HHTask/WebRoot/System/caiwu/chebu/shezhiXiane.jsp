<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.task.entity.Users"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
body {
	text-align: center;
}
</style>

		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 980px;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">设置限额</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv" style="background-color: #fff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 600px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div id="gongneng"
			style="width: 100%;text-align:center; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
            <form action="carAllowAction!shezhiXiane.action" method="post">
            <input name="id" type="hidden" value="${carAllowSum.id}" />
          	  <table align="center">
          	  <tr>
          	  <th>车主姓名：</th><th>${carAllowSum.name}</th>
          	  </tr>
          	   <tr>
          	  <th>有卡号：</th><th>${carAllowSum.icNumber}</th>
          	  </tr>
          	  <tr>
          	  <th>月补贴额度：</th><th><input name="carAllowSum.chognzhiedu" value="${carAllowSum.chognzhiedu}" />元/月</th>
          	 </tr>
          	 <tr>
          	  <th>充值限额：</th><th><input name="carAllowSum.chongzhiMax" value="${carAllowSum.chongzhiMax}" />元/月</th></tr>
          	           	           	  
          	  </table>
          	  <input type="submit" style="width:120px;height:40px;" value="设置">
            </form>
			<br/>
			
			</div>
			<br>
		<%@include file="/util/foot.jsp"%>
	</body>
		

</html>