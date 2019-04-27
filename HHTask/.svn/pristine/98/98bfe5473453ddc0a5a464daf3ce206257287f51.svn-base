<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Virtual Keyboard Basic Demo</title>
		<%@include file="/util/inc.jsp"%>
		<style>
#nav {
	height: 39px;
	font: 12px Geneva, Arial, Helvetica, sans-serif;
	background: #2D2D2D;
	border: 1px solid #323232;
	border-radius: 3px;
	min-width: 500px;
	margin-left: 0px;
	padding-left: 0px;
}

#nav li {
	list-style: none;
	display: block;
	float: left;
	height: 40px;
	position: relative;
	border-right: 1px solid #323232;
	position: relative;
}

#nav li a {
	padding: 0px 10px 0px 30px;
	margin: 0px 0;
	line-height: 40px;
	text-decoration: none;
	border-right: 1px solid #636161;
	height: 40px;
	color: #FFF;
	text-shadow: 1px 1px 1px #66696B;
}

#nav ul {
	background: #f2f5f6;
	padding: 0px;
	border-bottom: 1px solid #DDDDDD;
	border-right: 1px solid #DDDDDD;
	border-left: 1px solid #DDDDDD;
	border-radius: 0px 0px 3px 3px;
	box-shadow: 2px 2px 3px #ECECEC;
	-webkit-box-shadow: 2px 2px 3px #ECECEC;
	-moz-box-shadow: 2px 2px 3px #ECECEC;
	width: 170px;
}

#nav li.yahoo {
	background: url(images/yahoo.png) no-repeat 9px 12px;
}

#nav li.yahoo:hover {
	background: url(images/yahoo.png) no-repeat 9px 12px #010101;
}

#nav li:hover {
	background: #010101;
}

#nav li a {
	display: block;
}

#nav ul li {
	border-right: none;
	border-bottom: 1px solid #DDDDDD;
	width: 170px;
	height: 39px;
}

#nav ul li a {
	border-right: none;
	color: #6791AD;
	text-shadow: 1px 1px 1px #FFF;
	border-bottom: 1px solid #FFFFFF;
}

#nav ul li:hover {
	background: #DFEEF0;
}

#nav ul li:last-child {
	border-bottom: none;
}

#nav ul li:last-child a {
	border-bottom: none;
}

#nav ul {
	display: none;
	visibility: hidden;
	position: absolute;
	top: 40px;
}

#nav ul ul {
	top: 0px;
	left: 170px;
	display: none;
	visibility: hidden;
	border: 1px solid #DDDDDD;
}

#nav ul ul ul {
	top: 0px;
	left: 170px;
	display: none;
	visibility: hidden;
	border: 1px solid #DDDDDD;
}

#nav ul li {
	display: block;
	visibility: visible;
}

#nav li:hover>ul {
	display: block;
	visibility: visible;
}
</style>
		<script>
$(document).ready(function() {
	$("#nav li").hover(function() {
		$(this).children('ul').hide();
		$(this).children('ul').slideDown('fast');
	}, function() {
		$('ul', this).slideUp('fast');
	});
});
</script>
	</head>
	<body>
		<ul id="nav">
			<li class="yahoo">
				<a href="http://www.freejs.net/daohangcaidan.html">导航菜单</a>
				<ul>
					<li>
						<a href="#">导航菜单二级分类 »</a>
						<ul>
							<li>
								<a href="#">1</a>
							</li>
							<li>
								<a href="#">2</a>
							</li>
							<li>
								<a href="#">3</a>
							</li>
							<li>
								<a href="#">导航菜单三 »</a>
								<ul>
									<li>
										<a href="#">导航菜单4</a>
									</li>
									<li>
										<a href="#">1</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<a href="#">1</a>
					</li>
					<li>
						<a href="#">2</a>
					</li>
				</ul>
			</li>

		</ul>
	</body>
</html>