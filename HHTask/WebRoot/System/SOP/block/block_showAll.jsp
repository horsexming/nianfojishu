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
<head>
	<%@include file="/util/sonHead.jsp"%>
	<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#www_ablanuxe_com {
	width: 100%;
	height: 100%;
	background: #f00;
	filter: alpha(opacity :                               
		                                                         
		                                                         
		                                                                     
		                     0);
	opacity: 0;
	z-index: 9999;
	position: absolute;
	top: 0;
	left: 0;
	display: none;
}

.ablanuxe_com_2 {
	width: 100px;
	height: 100px;
	position: absolute;
	border: 1px solid #f00;
	overflow: hidden;
	border: 1px solid #f00;
}

.tuodong {
	width: 15px;
	height: 15px;
	position: absolute;
	right: 0px;
	bottom: 0px;
	cursor: nw-resize;
	overflow: hidden;
	font-size: 12px;
	text-align: center;
	line-height: 15px;
	color: #FFFFFF;
	float: right;
	z-index: 3;
}

.rightTuo {
	width: 15px;
	height: 100%;
	background: #f00;
	float: right;
	position: absolute;
	right: 0;
	top: 0;
	cursor: e-resize;
	overflow: hidden;
	filter: alpha(opacity :         0);
	opacity: 0;
	z-index: 1;
}

.bottomTuo {
	width: 100%;
	height: 15px;
	background: #f00;
	position: absolute;
	left: 0;
	bottom: 0;
	cursor: n-resize;
	overflow: hidden;
	filter: alpha(opacity :           0);
	opacity: 0;
	z-index: 1;
}

.ablanuxe_com_2 p {
	padding: 10px;
	line-height: 24px;
	font-size: 13px;
	text-indent: 24px;
}

.ablanuxe_com_2 h2 {
	border-bottom: 1px #f00 solid;
	font-size: 10px;
	color: red;
	font-weight: bolder;
}
</style>

	<script type="text/javascript">
//现场布置图尺寸
var divWidth;
var divHeight;
//初始整体尺寸
$(function() {
	divWidth = document.documentElement.clientWidth;
	divHeight = divWidth * 1203 / 2316;
	$("#allBlock").css("height", divHeight + "px");
})
</script>
</head>
<body>
	<div id="allBlock"
		style="background: url('<%=basePath%>images/newxqqy.png') no-repeat; background-size: 100% auto; height:507px;">
		<!-- 遮罩层 -->
		<div id="www_ablanuxe_com"></div>
		<!-- 区块管理区域 -->
		<s:iterator id="pageBlock" value="blockList">
			<div id="block_${pageBlock.id}" class="ablanuxe_com_2"
				onmouseover="getDiv(this,'${pageBlock.id}')"
				style="left: ${pageBlock.leftDistance}%; top: ${pageBlock.topDistance}%; height:${pageBlock.hight}%;width:${pageBlock.width}%;">
				<div style="width: 100%; height: 100%;">
					<h2 align="center">
						${pageBlock.blockName}
					</h2>
					<p>
					</p>
					<div id="right_${pageBlock.id}" class="rightTuo"></div>
					<div id="www_ablanuxe_com_${pageBlock.id}" class="tuodong">
					</div>
					<div id="bottom_${pageBlock.id}" class="bottomTuo"></div>
				</div>
			</div>
		</s:iterator>
	</div>
</body>
</html>