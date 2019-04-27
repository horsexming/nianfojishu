<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<!--Meta setup - Required for apple device-->
		<meta charset="utf-8">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!--Luara样式文件-->
		<link rel="stylesheet" href="css/left/style.css" />
		<!--渐隐样式
	    <link rel="stylesheet" href="css/left/luara.css"/>-->
		<!--左滑样式-->
		<!--上滑样式
	    <link rel="stylesheet" href="css/left/luara.top.css"/>-->
		<link rel="stylesheet" href="css/left/luara.left.css" />

		<script src="<%=basePath%>js/jquery-1.8.3.min.js">
</script>
		<!--Luara js文件-->
		<script src="<%=basePath%>js/jquery.luara.0.0.1.min.js">
</script>

	</head>
	<body>
		<!--Basic example-->
		<div class="flicker-example">
			<s:if test="errorMessage!=null">
				<div align="center">
				<img alt="" src="<%=basePath%>upload/file/download/chanpin_1.png" style="width: 1000px;height: 700px">
				</div>
			</s:if>
			<s:else>
				<!--Luara图片切换骨架begin-->
				<div class="example2" align="center">
					<ul>
						<s:iterator id="tuzhi" value="list" status="pageStatus">
							<li>
								<b style="font-size: 20px; margin: 15px;"> ${tuzhi.markId}
									第${tuzhi.processNO}工序 名称： ${tuzhi.processName} </b>
								<img alt="${pageStatus.index+1}"
									src="<%=basePath%>upload/file/processTz/${tuzhi.month}/${tuzhi.fileName}">
							</li>
						</s:iterator>
					</ul>
					<ol>
						<s:if test="list.size()>1">
							<s:iterator id="tuzhi" value="list" status="pageStatus">
								<li></li>
							</s:iterator>
						</s:if>
					</ol>
				</div>
			</s:else>
			<!--Luara图片切换骨架end-->
			<script>
//页面尺寸变化时重新加载
//window.onresize = load;
$(function() {
	$(".example2").luara( {
		width : "1200",
		height : "750",
		interval : 15000,
		selected : "seleted",
		deriction : "left"
	});
});
//重新加载页面
function reLoad() {
	window.location.reload();
}
//页面定时刷新(5分钟/次)
setTimeout(reLoad, 1000 * 60 * 5);
</script>
		</div>
	</body>
</html>