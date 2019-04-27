<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<!--Required libraries-->
		<script src="<%=basePath%>js/pirture/min/jquery-v1.10.2.min.js"
			type="text/javascript">
</script>
		<script
			src="<%=basePath%>js/pirture/min/modernizr-custom-v2.7.1.min.js"
			type="text/javascript">
</script>
		<script src="<%=basePath%>js/pirture/min/hammer-v2.0.3.min.js"
			type="text/javascript">
</script>

		<!--Include flickerplate-->
		<link href="<%=basePath%>css/pirture/flickerplate.css" type="text/css"
			rel="stylesheet">
		<script src="<%=basePath%>js/pirture/min/flickerplate.min.js"
			type="text/javascript">
</script>

		<!--Execute flickerplate-->
		<script>
$(function() {
	$('.flicker-example').flickerplate( {
		auto_flick : true,
		auto_flick_delay : 8,
		flick_animation : 'transform-slide'
	});
});
</script>

		<!-- Demo styles -->
		<link href="<%=basePath%>css/pirture/demo.css" type="text/css"
			rel="stylesheet">

	</head>
	<body>

		<!--Basic example-->
		<div class="flicker-example">

			<ul>

				<li data-background="<%=basePath%>img/flicker-1.jpg">
					<div class="flick-title">
						Flickerplate Is Working
					</div>
					<div class="flick-sub-text">
						Heaven forbid this package you downloaded is broken. That wouldn't
						be embarrassing at all.
					</div>
				</li>

				<li data-background="<%=basePath%>img/flicker-2.jpg">
					<div class="flick-title">
						Editable via Javascript or CSS
					</div>
					<div class="flick-sub-text">
						Take a look at the extensive documentation to see how you can
						change the settings in multiple ways.
					</div>
				</li>

				<li data-background="<%=basePath%>img/flicker-3.jpg">
					<div class="flick-title">
						Touch Enabled Through the Hammer.js Library
					</div>
					<div class="flick-sub-text">
						Hammer.js is a great touch library that has been included as part
						of the Flickerplate package.
					</div>
				</li>

			</ul>

		</div>

	</body>
</html>