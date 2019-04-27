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
<style type="text/css">
.div_a:hover {
	webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	transform: scale(1.1);
	-webkit-border-radius: 8px;
	-moz-border-radius: 8px;
	border-radius: 8px;
	-webkit-box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
	-moz-box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
	box-shadow: 0 -1px 10px rgba(0, 0, 0, .5);
}
.button {
	display: inline-block;
	zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */ *
	display: inline;
	vertical-align: baseline;
	margin: 0 2px;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/ 100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
}
</style>
	</head>
	<body style="background-color: #f2f2f2;">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div>
				<ul style="width: 100%">
					<s:iterator value="igSetList" id="pagelist">
						<li style="float: left; width: 33.3%">
							<div class="div_a" style="width: 300px;height: 280px; ">
								<div style="position: relative; width: 260px; height: 220px;">
									<img alt="${pagelist.name}" src="<%=basePath%>upload/file/Gift/${pagelist.giftpicture}" 
								width="200" height="200" >
								<span style="position: absolute; top: 170; left: 90;">
									<a href="IntegralGiftAction_findidgiftList.action?id=${pagelist.id}&str=${pagelist.randomnum}">${pagelist.name}</a>	
								</span>
								</div>
								<div>
								<s:if test="#pagelist.status == '可报名'">
									<input type="button" class="button"  value="报名夺宝"
								onclick="window.location.href='IntegralGiftAction_addindian.action?indianaGift.igiftSet.id=${pagelist.id}'"/> 
								</s:if>
								<s:else>
									<input type="button" class="button"  value="报名夺宝" disabled="disabled"/> 
								</s:else>
								</div>
							</div>
						</li>
					</s:iterator>
				</ul>
				<div align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
				</div>
			</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
