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

		<title>My JSP 'divTest.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript"
			src="javascript/jquery-easyui-1.3.1/jquery-1.8.0.min.js">
</script>
		<script type="text/javascript">
$(function() {
	$($("div")[0]).hide("show");
});
</script>
	</head>

	<body>
		<div>
			test
		</div>
		<div>
			test2
		</div>
		<div>
			test3
		</div>
		<div id="litest">
			test4
		</div>

		<ul>
			<li>
				jquery(1)
			</li>
			<li>
				jquery(2)
			</li>
			<li>
				jquery(3)
			</li>
			<li>
				jquery(4)
			</li>
		</ul>

		<input type="text" value="100" />
		+
		<input type="text" value="100" />
		<input type="button" value="=" />
		<label>
		</label>
		<script type="text/javascript">
$("ul>li:first").addClass("select");
$("ul").append($("<li>new </li>"));
$(function() {
	$("ul li:first").css('color', 'red');
	$("ul li:last").css('color', 'red');
	$("#litest").css('color', 'red');
});

$("ul").bind("click",function() {
	$(this).hide("show");
});

$("input[type='button']").click(function() {
	var sum = 0;
	$("input[type='text']").each(function() {
		sum += parseInt($(this).val());
	});
	$('label').text(sum);
});
</script>

	</body>
</html>
