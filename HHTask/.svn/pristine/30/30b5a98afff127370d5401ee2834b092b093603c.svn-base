<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<title>个人中心,${companyInfo.shortName}作业网</title>
		<%@include file="/util/inc.jsp"%>
	</head>
	<body bgcolor="#ffffff">
		<center>
			<div id="gongneng" style="width: 100%; clear: both;">
				<div>
					<div align="left"
						style="float: left; width: 46%; padding-left: 20px; padding-top: 10px;">
						<s:if test='#session.Users.sex =="男"'>
							<img alt="${Users.name}"
								src="upload/user/${Users.password.picture}" width="120px;"
								style="border: solid 1px #000000" height="130px;"
								onerror="this.src='images/man.jpg'">
						</s:if>
						<s:else>
							<img alt="${Users.name}"
								src="upload/user/${Users.password.picture}" width="120px;"
								style="border: solid 1px #000000" height="130px;"
								onerror="this.src='images/woman.jpg'">
						</s:else>
						<ul class="userCenter">
							<li>
								<span class="span1">姓名 :</span> ${Users.name}
							</li>
							<li></li>
							<li>
								<span class="span1">部门 :</span> ${Users.dept}
							</li>
							<li></li>
							<li>
								<span class="span1">职位 :</span> ${Users.duty}
							</li>
							<li></li>
							<li>
								<span class="span1">学历 :</span> ${Users.education}
							</li>
							<li></li>
							<li>
								<span class="span1">性别 :</span> ${Users.sex}
							</li>
							<li></li>
							<li>
								<span class="span1">家乡 :</span> ${Users.birthplace}
							</li>
							<li></li>
							<li>
								<span class="span1">生日 :</span> ${Users.bothday}
							</li>
							<li></li>
							<li>
								<span class="span1"> 手 机 号 : </span>
								${Users.password.phoneNumber}
							</li>
							<li></li>
							<li>
								<span class="span1">身份证号:</span> ${Users.uid}
							</li>
							<li></li>
						</ul>
					</div>
					<div style="width: 46%; float: left;" align="left">
						<ul class="userCenter">
							<li>
								<a href="userCenter/updatePassword.jsp">修改密码</a>
							</li>
						</ul>
						<ul class="userCenter">
							<li>
								<a href="userCenter/log_workLog.jsp">日志管理</a>
							</li>
						</ul>
						<ul class="userCenter">
							<li>
								<a href="ProjectRecordAction!findProLoginByUser.action">绑定网站管理</a>
							</li>
						</ul>
						<ul class="userCenter">
							<li>
								<a href="annualLeave!gerennianxiumingxi.action">个人年休查看</a>
							</li>
						</ul>
					</div>
					<div style="clear: both;">
					</div>
				</div>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
