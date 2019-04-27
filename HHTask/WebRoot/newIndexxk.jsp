<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>PEBS,生产力生态平衡系统</title>
		<%@include file="/util/sonHead2.jsp"%>
		<script type="text/javascript">
window.onload = function() {
	function showTime() {
		var datetime = new Date();
		var h = datetime.getHours();
		var m = datetime.getMinutes();
		var s = datetime.getSeconds();

		if (h < 10) {
			h = "0" + h;
		}
		if (m < 10) {
			m = "0" + m;
		}
		if (s < 10) {
			s = "0" + s;
		}

		var hour = document.getElementById("hour");
		var minute = document.getElementById("minute");
		var seconds = document.getElementById("second");

		hour.innerHTML = h;
		minute.innerHTML = m;
		seconds.innerHTML = s;
		setTimeout(showTime, 1000);
	}
	showTime();

}

$(function() {
	var url = "${param.url}";
	if (url != "") {
		$("#sysModule").hide();
		$('#workMainIframe').attr("src",
				"${pageContext.request.contextPath}/" + url);
		$("#showAll").show();
	}
})

$(function() {
	var imgWid = 0;
	var imgHei = 0; //变量初始化
	var big = 1.1;//放大倍数
	$(".c_relation li").hover(function() {
		$(this).find("img").stop(true, true);
		var imgWid2 = 0;
		var imgHei2 = 0;//局部变量
			imgWid = $(this).find("img").width();
			imgHei = $(this).find("img").height();
			imgWid2 = imgWid * big;
			imgHei2 = imgHei * big;
			$(this).find("img").css( {
				"z-index" : 2
			});
			$(this).find("img").animate( {
				"width" : imgWid2,
				"height" : imgHei2,
				"margin-left" : -(imgWid2 - imgWid) / 2,
				"margin-top" : -(imgHei2 - imgHei) / 2
			});
		}, function() {
			$(this).find("img").stop().animate( {
				"width" : imgWid,
				"height" : imgHei,
				"margin-left" : -(imgWid - imgWid) / 2,
				"margin-top" : -(imgHei - imgHei) / 2,
				"z-index" : 1
			});
		});
})
</script>
	</head>
	<body
		style="background: url('images/background.jpg') no-repeat; background-size: cover; -moz-background-size: cover;">
		<%@include file="/util/newHead2.jsp"%>
		<div class="content">
			<div class="c_logo">
				<div class="fl">
					<img src="images/ss2.png" width="325px" height="65px" />
				</div>
				<div class="fr time">
					北京时间：
					<span id="hour"></span>时
					<span id="minute"></span>分
					<span id="second"></span>秒 <a href="DingdanAction!listLoginUsers.action">当前
					<span class="zxrs">${count}</span>人在线</a>
				</div>
			</div>
			<div id="sysModule" class="c_relation">
				<s:iterator id="mf" value="allModuleList" status="pageId">
					<s:if test="#pageId.index % 3 == 0">
						<ul id="rela">
					</s:if>
					<li
						style="position:absolute;z-index:1;margin-left:${pageId.index % 3 *340}px;display:inline;cursor: pointer;"
						onclick="goPageByUrl('${mf.id}','${mf.targetNewPage}',2)">
						<img src="<%=basePath%>upload/file/sysImages/${mf.imageName}"
							style="position: absolute; z-index: 1;" width="325px"
							height="100px" />
					</li>
					<s:if test="(#pageId.index+1) % 3 == 0">
						</ul>
					</s:if>
				</s:iterator>
			</div>
			<div id="showAll" style="display: none;">
				<!--主体 iframe -->
				<iframe name="workMain" target="workMain" id="workMainIframe" src=""
					marginwidth="0" marginheight="0" hspace="0" vspace="0"
					frameborder="0" scrolling="no"
					style="width: 100%; height: auto; margin: 0px; padding: 0px;"></iframe>
			</div>
		</div>
		<%@include file="/util/newFooter2.jsp"%>
	</body>
</html>

