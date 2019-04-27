<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
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
		<title>${board.name}</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript">
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
		"S" : this.getMilliseconds()
	//millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
var t = setTimeout(function() {
	window.location.reload(true);
}, 1000 * 500);
</script>
		<style type="text/css">
body {
	text-align: center;
	background: #ffffff;
}

#center {
	margin-right: auto;
	margin-left: auto;
}

#board {
	border-color: black;
	border-width: 1px;
	border-style: none;
}

#head {
	font-size: 20px;
	font-family: serif;
	font-style: normal;
	margin-top: 40px;
}

#head a {
	text-decoration: none;
}

#head a:hover {
	color: red;
	cursor: pointer;
}

#container {
	padding-left: 0px;
	padding-right: 300px;
}

#container>div:hover { /*background:  #d3d3d3;*/
	
}

#container>#publish:hover { /*background: #F0F7FC;*/
	
}
</style>

	</head>

	<%--<body><h1 style="text-align:center;">	<span style="line-height:1.5;">${board.name}</span></h1>${board.content }</body>
--%>
	<body style="text-align: center; background-color: #ffffff;">
		<!-- 返回页面顶部 -->
		<!-- 返回页面顶部 -->
		<!-- 主体 -->
		<div id="main" style="position: relative; width: 96%; margin: 0 auto;">
			<!-- 标题 -->
			<s:iterator id="t" value="boardList" status="bt">
				<div
					style="background-color: #DCDCDC; margin-bottom: 50px; padding: 10px;">
					<div>
						<h1 style="text-align: center;">
							<span style="line-height: 1.5;">${t.name}</span>
						</h1>
						<div style="overflow: hidden;">
							<span
								style="float: right; margin-right: 100px; font-size: 12px; color: blue;">发布日期:<s:date
									name="createDate" format="yyyy-MM-dd" /> </span>
						</div>
					</div>
					<s:iterator id="bd" value="boardList" status="bst">
						<!-- 内容 -->
						<div id="center"
							style="width: 100%; text-align: left; border-top: 1px solid #778899; margin-bottom: 10px;">
							<!-- 电子屏幕 -->
							<div>
								${bd.content }
							</div>
							<!-- 电子屏幕 -->
							<!-- 评论回复 -->
							<%--<div id="head">
					<span style="float:left;">共有<em>${count}</em>条评论&nbsp;&nbsp;<a style="font-size:15px;">最后评论时间(<s:date name="lastBoardReviewDate" format="MM-dd HH:mm"/>)</a></span>
					<span style="float:right;">&nbsp;<a href="boardReviewAction!getBoardReviewPage.action?boardReview.boardId=${board.id}">显示最新评论</a></span>
					<div style="clear: both"></div>
				</div>
				<hr style="border-bottom-style:solid;" />
				--%>
							<!-- 评论回复 -->
							<!-- 评论容器 -->
							<div id="container">
								<!-- 单条-->
								<s:iterator id="br" value="boardReviewList" status="st">
									<div name="boardReview">
										<!-- 评论 -->
										<div
											style="text-align: left; border-width: 1px; border-color: silver; border-top-style: dashed">
											<div
												style="float: left; text-align: left; width: 20%; font-size: 20px; color: red;">
												<div>
													${br.createUser.name}:
													<s:date name="createDate" format="MM-dd HH:mm" />
												</div>
											</div>
											<div style="float: right; width: 80%">
												<div>
													${br.content }
												</div>
											</div>
											<div style="clear: both;"></div>
										</div>
										<!-- 评论 -->
										<!-- 回复 -->
										<s:iterator id="subBr" value="subBoardReviewList"
											status="subSt">
											<div name="boardReview" style="">
												<!-- 评论 -->
												<div
													style="text-align: left; border-width: 1px; border-color: silver; border-top-style: dashed">
													<div
														style="margin-left: 50px; width: 25%; float: left; text-align: left; font-size: 20px; color: green;">
														<div>
															${subBr.createUser.name}
															<span style="color: #000000">回复</span>${br.createUser.name}:
															<s:date name="createDate" format="MM-dd HH:mm" />
														</div>
													</div>
													<div style="float: right; width: 65%;">
														<div>
															${subBr.content }
														</div>
													</div>
													<div style="clear: both;"></div>
												</div>
												<!-- 评论 -->
											</div>
										</s:iterator>
										<!-- 回复 -->
									</div>
								</s:iterator>

								<!-- 单条-->
							</div>
							<!-- 评论容器 -->
						</div>
						<!-- 内容-->
					</s:iterator>
				</div>
			</s:iterator>
			<!-- 标题 -->
			<div id="foot"></div>
			<div>
				<!-- 主体 -->
	</body>
</html>
<script type="text/javascript">
var marginTop = 0;
var scrollTop = 0;
function autoScroll(id) {
	$(id).animate( {
		marginTop : marginTop + "px"
	}, 200, function() {
		marginTop += -10;
		if (-marginTop > scrollTop) {
			marginTop = 0;
		}
	});
}
$(function() {
	var position = $('#foot').position();
	scrollTop = position.top;
	setInterval('autoScroll("#main")', 5000);
});
</script>
