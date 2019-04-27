<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>消息页</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<STYLE type="text/css">
body {
	color: #333;
	background: #fff;
	padding: 0;
	margin: 0;
	position: relative;
	min-width: 700px;
	font-family: arial;
	font-size: 12px
}

p,form,ol,ul,li,dl,dt,dd,h3 {
	margin: 0;
	padding: 0;
	list-style: none
}

#content_left {
	width: 540px;
	padding-left: 121px;
	padding-top: 5px
}

.norsTitle {
	font-size: 22px;
	font-family: Microsoft Yahei;
	font-weight: normal;
	color: #333;
	margin: 35px 0 25px 0;
}

.norsTitle2 {
	font-family: arial;
	font-size: 13px;
	color: #666;
}

.norsSuggest ol {
	margin-left: 47px;
}

.norsSuggest li {
	margin: 13px 0;
}

#help {
	background: #f5f6f5;
	zoom: 1;
	padding: 0 0 0 50px;
	float: right
}

#help a {
	color: #777;
	padding: 0 15px;
	text-decoration: none
}
</STYLE>
	</head>
	<script type="text/javascript">
function errorMessage() {
	var errorMessage = "${fn:replace(errorMessage,'"
', '')}";
	var url = "${url}";
	if (errorMessage == "") {
		errorMessage = "您的访问出错了!";
	}
	alert(errorMessage);
	if (url == "") {
		history.go(-1);
	} else {
		window.location.href = url;
	}
}
</script>
	<body>
		<center>
			<s:if test="errorMessage==null||errorMessage==''">
				<div id="content_left" align="left">
					<div class="nors">
						<div class="norsSuggest">
							<h3 class="norsTitle">
								很抱歉，您要访问的页面不存在！
							</h3>
							<p class="norsTitle2">
								温馨提示：
							</p>
							<ol>
								<li>
									请检查您访问的网址是否正确，请点击
									<a href="javascript:history.go(-1);">返回上一页</a>。
								</li>
								<li>
									如果您不能确认访问的网址，请点击
									<a href="index.jsp">返回首页</a>。
								</li>
								<li>
									如有任何意见或建议，请及时联系系统管理员。
								</li>
							</ol>
						</div>
					</div>
				</div>
			</s:if>
			<s:else>
				<div style="margin-top: 35px;">
					<div
						style="width: 350px; border: solid 1px #000; padding: 15px; line-height: 25px; font-size: 12px;"
						align="left">
						<font color="red">提醒消息如下:</font>
						<br />
						<font color="#000"> ${fn:replace(errorMessage,'\\n','<br//>')} </font>
						<br />
						<br />
						<font style="font-size: 10px;">如您有什么其他疑惑,可以将本提醒消息截图发送给管理员!<br />谢谢您的配合!</font>
					</div>
					<s:if test="url!=null&&url!=''">
						<div id="time" style="margin-top: 10px;">
							<input value="确定"
								onclick="javascript:window.location.href = '${url}';"
								type="button" style="width: 80px; height: 50px;">
						</div>
					</s:if>
					<s:else>
						<div id="back" style="margin-top: 10px;">
							<input value="确定" onclick="javascript:history.go(-1);"
								type="button" style="width: 80px; height: 50px;">
						</div>
					</s:else>
				</div>
			</s:else>
		</center>
		
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/jquery-1.8.3.js">
</script>
<script type="text/javascript">
	//将主页iframe高度自适应
	$("#workMainIframe",window.parent.document).load(function() {//绑定事件
				var main = $("#workMainIframe",window.parent.document);//找到iframe对象
				//获取窗口高度 
				var thisheight;
				thisheight = document.body.scrollHeight;
				thisheight = parseFloat(thisheight);
				var conHeight = parseFloat($("body").css("height"));//contentDiv div的宽度
//				alert("thisheight--"+thisheight);
//				alert("conHeight--"+conHeight);
				if(conHeight>thisheight){
					thisheight=conHeight;
				}
				if(thisheight<500){
					thisheight=500;
				}
				main.height(thisheight);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
			});
</script>
	</body>
</html>
