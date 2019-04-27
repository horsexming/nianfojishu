<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>域名地址更改通知</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<div align="center" style="margin-top: 50px;">
			<h1>
				${companyInfo.shortName}作业网已启动域名
				<a href="http://task.shhhes.com/">task.shhhes.com </a>
				<br />
				当前地址<%=basePath%>已经停用,特此通知!
			</h1>
			<div id="timer" style="color: red">
				5秒后自动跳转到新的域名
			</div>
		</div>

		<script type="text/javascript">
var maxtime = 5; //一个小时，按秒计算，自己调整!   
function CountDown() {
	if (maxtime >= 0) {
		seconds = Math.floor(maxtime % 60);
		msg = seconds + "秒后自动跳转到新的域名";
		document.all["timer"].innerHTML = msg;
		if (maxtime == 5 * 60)
			alert('注意，还有5分钟!');
		--maxtime;
	} else {
		clearInterval(timer);
		window.location.href = 'http://task.shhhes.com/';
	}
}
timer = setInterval("CountDown()", 1000);
</script>


	</body>
</html>
