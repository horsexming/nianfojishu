<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<script type="text/javascript">
if (/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
	window.location.href = "ModuleFunctionAction!findMfByUser.action?pageStatus=qx";
}
</script>
		<meta charset="UTF-8">
		<title>PEBS生产力平衡系统</title>
		<link rel="shortcut icon"
			href="<%=basePath%>/upload/file/sysImages/favicon.ico" />
		<link type="text/css" rel="styleSheet" href="css/setting.css" />
		<link type="text/css" rel="styleSheet" href="css/index3.css" />
		<link type="text/css" rel="styleSheet" href="css/fileTree.css" />
		<link type="text/css" rel="styleSheet"
			href="css/jquery.mCustomScrollbar.css" />


		<link rel="stylesheet"
			href="<%=basePath%>/javascript/typeahead.js/examples.css" />

		<script type="text/javascript" src="js/jQuery-1.7.1.js">
</script>
		<script type="text/javascript" src="js/jquery.tree.js">
</script>
		<script type="text/javascript" src="js/time.js">
</script>
		<script type="text/javascript" src="js/leftnav.js">
</script>
		<script type="text/javascript" src="js/jqPaginator.js">
</script>
		<!-- ztree -->
		<script type="text/javascript"
			src="<%=basePath%>/javascript/zTree/js/jquery.ztree.core-3.5.js">
</script>
		<!-- typeahead -->
		<script language="javascript"
			src="<%=basePath%>/javascript/typeahead.js/typeahead.bundle.js">
</script>

		<!-- 表单验证CSS -->
		<script
			src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine.js"
			type="text/javascript">
</script>
		<script
			src="<%=basePath%>javascript/calendar/js/formValidator/js/jquery.validationEngine-en.js"
			type="text/javascript">
</script>
		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/js/formValidator/css/validationEngine.jquery.css"
			type="text/css" media="screen" charset="utf-8" />


		<!-- Jquery and Jquery UI -->
		<script type="text/javascript"
			src="<%=basePath%>javascript/calendar/js/jquery-ui-1.8.6.custom.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>javascript/calendar/js/jquery-ui-timepicker-addon.js">
</script>
		<link rel="stylesheet"
			href="<%=basePath%>javascript/calendar/css/redmond/jquery-ui-1.8.1.custom.css">

		<!-- FullCalender -->
		<link
			href='<%=basePath%>javascript/fullcalendar-3.7.0/fullcalendar.min.css'
			rel='stylesheet' />
		<link
			href='<%=basePath%>javascript/fullcalendar-3.7.0/fullcalendar.print.min.css'
			rel='stylesheet' media='print' />
		<script
			src='<%=basePath%>javascript/fullcalendar-3.7.0/lib/moment.min.js'>
</script>
		<%--		<script--%>
		<%--			src='<%=basePath%>javascript/fullcalendar-3.7.0/lib/jquery.min.js'>--%>
		<%--</script>--%>
		<script
			src='<%=basePath%>javascript/fullcalendar-3.7.0/fullcalendar.js'>
</script>
		<script src='<%=basePath%>javascript/fullcalendar-3.7.0/locale-all.js'>
</script>
<script src="<%=basePath%>javascript/javascript.js" ></script>



		<!-- bootstrap -->
		<%--		<link rel="stylesheet" type="text/css"--%>
		<%--			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">--%>
		<%--		<script type="text/javascript"--%>
		<%--			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">--%>
		<%--</script>--%>

		<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

#calendar {
	width: 1000px;
	float: left;
}

#calendarDetail {
	font-size: 20px;
}

#messageTitle {
	color: red;
}

.sysheader {
	padding: 10px 5px 10px 0px;
	font-size: 14px;
	font-weight: normal;
	margin: 0;
}

.sysdesc {
	padding: 10px 5px 10px 0px;
	margin: 0;
}

.rowElem {
	height: 35px;
	clear: left;
}

.rowElem label {
	clear: left;
	float: left;
	padding: 2px 6px 2px 0px;
	text-align: left;
	width: 60px;
}

.rowElem input {
	display: block;
	float: left;
	width: 230px;
	padding: 5px;
}

.rowElem select {
	display: block;
	width: 230px;
	padding: 5px;
}

.rowElem span {
	float: left;
	width: 85px;
}

.clear {
	clear: both;
}

#headercontent a {
	text-decoration: none;
}

.dialogmask {
	display: none;
	z-index: 9999;
	text-align: center;
	padding-top: 50px;
}

.typeahead {
	font-size: 12px;
}
.cal ul{
	list-style: circle;
	width: 100%;
}
.cal ul li{
	cursor: pointer;
	list-style: circle;
	width: 100%;
}
.testcenter{
	float: left; 
	cursor: pointer; 
	padding-right: 18px;
}
.testcenter:hover .qs_ul {
	display: block;
	opacity:0.5;
}
.testcenter:hover h2 {
	background-color: #3333CC;
}
.qs_ul{
	display: none;
}
</style>
	</head>
	<body>
		<!-- 导航栏-->
		<!-- 功能列表-->
		<div id="bodyDiv" align="center" class="transDiv"
				onclick="chageDiv('none')">
			</div>
			<div id="contentDiv"
				style="position: absolute; z-index: 255; width: 900px; display: none;"
				align="center">
				<div id="closeDiv"
					style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
					<table style="width: 100%">
						<tr>
							<td>
								<span id="title">您正在对不合格品缺陷类型进行操作</span>
							</td>
							<td align="right">
								<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
									id="closeTcDiv" height="32" onclick="chageDiv('none')">
							</td>
						</tr>
					</table>
					<div id="operatingDiv"
						style="background-color: #ffffff; width: 100%;">
					</div>
				</div>
			</div>
	<div style="float: left; width: 20%;">
		<div class="menu custom-menu-trigger-selector" id="menu"
			style="position: absolute; z-index: 999; display: none; position: fixed;">
			<div style="position: fixed; top: 0; left: 0;">

				<ul>
					<li>
						<a href="ModuleFunctionAction!findMfByUser.action"><img
								src="img/pebs_logo.png" height="36" width="122" class="logoleft">
						</a>
					</li>
					<li>
						<a href="ModuleFunctionAction!findMfByUser.action"> <s:if
								test='#session.Users.sex =="男"'>
								<img alt="${Users.name}"
									src="images/man.jpg" height="88"
									width="89"
									style="border: solid 1px #000000; border-radius: 50%;"
									onerror="this.src='images/man.jpg'">
							</s:if> <s:else>
								<img alt="${Users.name}"
									src="images/woman.jpg" height="88"
									width="89"
									style="border: solid 1px #000000; border-radius: 50%;"
									onerror="this.src='images/woman.jpg'">
							</s:else> </a>
					</li>
				</ul>
				<ul id="list">
					<s:iterator id="mf" value="allModuleList" status="pageId">
						<li data="${mf.id}" style="background-image: url('');">
							<a href="javascript:;">${mf.functionName}</a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div id="marginDiv"
			style="background-color: #33363f; position: absolute; z-index: 999; left: 0px; padding: 0px; margin: 0px; overflow: hidden; position: fixed; width: 5px; height: 100%">
		</div>
		<div id="showOrCloseMenuDiv"
			style="position: absolute; z-index: 999; left: 5px; padding: 0px; margin: 0px; overflow: hidden; position: fixed; background-color: #252733; height: 100%">
			<div
				style="background-color: #33363f; color: #ffffff; margin-bottom: 10px;"
				id="showOrCloseMenu0" align="center">
				<SPAN id="jiantou">》</SPAN>
				<br />
				P
				<br />
				E
				<br />
				B
				<br />
				S
				<br />
			</div>
			<div style="background-color: #252733; margin-top: 40px;"
				align="center">
				<a onclick="showWork('5');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=5&pageStatus=3"
					title="项目管理" target="workMain"><img src="img/project.png"
						height="20" width="20">
					<div class="moname">
						项目
					</div> </a>
				<a onclick="showWork('8');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=8&pageStatus=3"
					title="采购管理" target="workMain"><img src="img/caigou.png"
						height="20" width="20">
					<div class="moname">
						采购
					</div> </a>
				<a onclick="showWork('6');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=6&pageStatus=3"
					title="生产管理" target="workMain"><img src="img/product.png"
						height="20" width="20">
					<div class="moname">
						生产
					</div> </a>
				<a onclick="showWork('605');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=605&pageStatus=3"
					title="物流系统" target="workMain"><img src="img/wl.png"
						height="20" width="20">
					<div class="moname">
						物流
					</div> </a>
				<a onclick="showWork('19');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=19&pageStatus=3"
					title="财务管理" target="workMain"><img src="img/finance.png"
						height="20" width="20">
					<div class="moname">
						财务
					</div> </a>
				<a onclick="showWork('20');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=20&pageStatus=3"
					title="人事管理" target="workMain"><img src="img/resource.png"
						height="20" width="20">
					<div class="moname">
						人事
					</div> </a>
				<a onclick="showWork('22');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=22&pageStatus=3"
					title="信息管理" target="workMain"><img src="img/message.png"
						height="20" width="20">
					<div class="moname">
						信息
					</div> </a>
				<a onclick="showWork('722');hidcontent();"
					href="ModuleFunctionAction!findMfByIdForJump.action?id=722&pageStatus=3"
					title="仪表盘" target="workMain"><img src="img/ybp.png"
						height="20" width="20">
					<div class="moname">
						仪表
					</div> </a>
			</div>
		</div>
		<!-- 功能明细 (先生成需要的明细div)-->
		<s:iterator id="mf" value="allModuleList" status="pageId">
			<div class="menucontainer">
				<!-- 关闭右边按钮 -->
				<div class="togglebtn">
				</div>
				<div class="content-1 left">
					<ul id="ztreeView" class="files">
					</ul>
				</div>
			</div>
		</s:iterator>
		</div>
		<!-- 功能列表over-->

		<div class="centercontainer" style="margin-left:140px ; float: left; width: 90%" >
			<div class="header">
				<div class="headrinfo">
					<ul>
						<li>
							<h1>
								<a href="ModuleFunctionAction!findMfByUser.action"> 欢迎您,
									${sessionScope.Users.name}! </a>
							</h1>
						</li>
						<li>
							<a href="ModuleFunctionAction!findMfByUser.action"><img
									src="img/icon_03.png" height="13" width="13"> </a>
						</li>
						<li>
							<a onclick="showWork();hidcontent();" href="userCenter/updatePassword.jsp"
								target="workMain"><img src="img/icon_06.png" height="13"
									width="13"> </a>
						</li>
						<li>
							<a onclick="showWork();hidcontent();"
								href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
								target="workMain"> <img src="img/icon_08.png" height="12"
									width="12"><span class="messageCount">(0)</span> </a>
						</li>
						<%--						<li>--%>
						<%--							<a href="#" id="nav-add-favorite"><img src="img/icon_10.png"--%>
						<%--									height="13" width="14"> </a>--%>
						<%--						</li>--%>
						<!-- 邮件显示开始 -->
						<li>
							<a href="#" target="_blank"><img src="img/icon_12.jpg"
									title="已读邮件" height="13" width="14"><span id="allMail"></span>
							</a>
						</li>
						<li>
							<a href="#" target="_blank"><img src="img/icon_11.jpg"
									title="未读邮件" height="13" width="14"><span id="unReadMail"></span>
							</a>
						</li>
						<!-- 邮件显示结束 -->
						<li>
							<a href="#" target="workMain" id="nav-add-favorite"><img
									src="img/icon_10.png" height="13" width="14"> </a>
						</li>
						<li>
							<a href="logOff.jsp">[退出]</a>
						</li>
					</ul>
					<div class="search" id="remote">
						<form class="searchFun"
							action="ModuleFunctionAction!searchModuleFunction.action"
							method="post" style="margin: 0px; padding-top: 10px;"
							target="workMain">
							<input type="text" name="moduleFunction.functionName"
								style="width: 180px; height: 15px"
								class="search_text form-control typeahead" accesskey="s"
								tabindex="9" autocomplete="off" x-webkit-speech=""
								x-webkit-grammar="builtin:search" />
							<input type="submit" class="search_bt search" value=""
								style="width: 16px; height: 16px; background-repeat: no-repeat; background-image: url(img/search_03.png); border: 0;"
								onclick="showWork();hidcontent();" />
						</form>
					</div>

				</div>
			</div>
			<div class="notice" >
				<div class="header_bt">
					<div class="noticeleft">
						<strong><img src="img/notice_07.png" height="22"
								width="22"> </strong>
					</div>
					<div class="marquee">
						<marquee direction="left" scrollamount="5"
							onmouseout="this.start()" onmouseover="this.stop()">
							<font color="#737373" id="show"></font>
						</marquee>
					</div>
					<div class="fr time">
						北京时间：
						<span id="hour">00</span>时
						<span id="minute">00</span>分
						<span id="second">00</span>秒
						<a onclick="showWork();hidcontent();"
							href="DingdanAction!listLoginUsers.action" target="workMain">
							当前 <span class="zxrs">${count}</span>人在线</a>
					</div>
				</div>
			</div>
			<div class="centernav" >
				<ul class="leftindexnav" >
					<li>
						<a href="ModuleFunctionAction!findMfByUser.action"><img
								src="img/navicon_06.png" height="20" width="21"> </a>
					</li>
					<li>
						<a href="ModuleFunctionAction!findMfByUser.action?pageStatus=qx"
							title="清新首页"><img src="img/navicon_09.png" height="19"
								width="19"> </a>
					</li>
					<li>
						<a href="ModuleFunctionAction!findMfByUser.action?pageStatus=xk"
							title="炫酷首页"><img src="img/navicon_03.png" height="20"
								width="18"> </a>
					</li>

				</ul>
				<div class="cenjinav">
					<p></p>
				</div>
			</div>
			<div class="tablecontent" >
				<div class="content" >
					<div class="person" id="person">
						<div class="homepage">
							<div class="infoleft">
								<s:if test='#session.Users.sex =="男"'>
									<img alt="${Users.name}"
										src="images/man.jpg" width="120px;"
										style="border: solid 1px #000000" height="130px;"
										onerror="this.src='images/man.jpg'">
								</s:if>
								<s:else>
									<img alt="${Users.name}"
										src="images/woman.jpg" width="120px;"
										style="border: solid 1px #000000" height="130px;"
										onerror="this.src='images/woman.jpg'">
								</s:else>
							</div>
							<div class="inforight">
								<h1>
									${Users.name} 提醒消息:
									<a onclick="showWork();hidcontent();"
										href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
										target="workMain"><span class="messageCount">[0]</span> </a>项
								</h1>
								<ul>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="oaAppDetailAction!preSaveOADetail.action?oadetail.appayTag=A&tag=">采购申请</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="AskForLeaveAction!preAskForLeave.action?askForLeave.appayTag=A">请假申请</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="System/renshi/hr_overtime_add.jsp">加班申请</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="userCenter/log_workLog.jsp">工作记录</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="annualLeave!gerennianxiumingxi.action">个人年休</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="annualLeave!listhuanxiumingxi.action">个人换休</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="WageAction!findPersonWage.action">个人工资</a>
									</li>
									<li>
										<a onclick="showWork();hidcontent();" target="workMain"
											href="IntegralGiftAction_giftindex.action">积分乐园</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="cal" id="caltest">
						<div style="float: none; height: 100px; margin: 20px 0px 0px 125px;">
							<div style=" " class="testcenter">
								<h2>
									协作办公平台
								</h2>
							</div>
							<div style="" class="testcenter">
								<h2>
									日常行政事务申请
								</h2>
								<ul class="qs_ul">
									<li><a onclick="showWork();hidcontent();" target="workMain"  href="./System/singleCar/addSingleCar.jsp?tag=self">用车申请</a></li>
									<li><a onclick="showWork();hidcontent();" target="workMain" href="">会议室预定</a></li>
									<li><a onclick="showWork();hidcontent();" target="workMain" href="AskForLeaveAction!preAskForLeave.action?askForLeave.appayTag=A">出差申请</a></li>
								</ul>
							</div>
							<div style="" class="testcenter">
								<h2>
									公共信息
								</h2>
								<ul class="qs_ul">
									<li><a onclick="showWork();hidcontent();" target="workMain"  href="systemFileAction_findAll.action">体系文件</a></li>
									<li><a onclick="showWork();hidcontent();" target="workMain" href="">知识库</a></li>
									<li><a onclick="showWork();hidcontent();" target="workMain" href="">标准化模板</a></li>
								</ul>
							</div>
							<div style="" class="testcenter">
								<h2>
									工会专栏
								</h2>
							</div>
							<div style="" class="testcenter">
								<h2>
									<a onclick="showWork();hidcontent();"   style="text-decoration:none; color: #707070; "
									href="rtxMsgAction_toSendRtxMsg.action">通讯录</a>
								</h2>
							</div>
						</div>
						<div style="float: left; margin: 20px 0px 0px 75px;" >
							<div style="width: 240px;height: 100px;">
								<h2>
									邮箱
								</h2>
								<ul style="">
									<li style="" >
										<a  style="text-decoration:none; color: #707070; "
										onclick="" href="javscript:;"
										>未读邮件(<span id="allMail2"></span>封)</a>
										
									</li>
									<li style="" >
										<a  style="text-decoration:none; color: #707070; "
										onclick="" href="javscript:;"
										>已读邮件(<span id="unReadMail2"></span>封)</a>
										
									</li>
								</ul>
							</div>
							<div style="width: 240px;height: 100px;">
								<h2>
									当前审批流程
								</h2>
								<ul style="">
									<li style="" >
										<a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "
										href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
										target="workMain">当前流程</a>
									</li>
									<li style="" onclick="window.location.href=''">
										<a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "
										href="AlertMessagesAction!findAlertMessages.action?alertMessages.readStatus=no"
										target="workMain">未到达流程</a>
										
									</li>
									<li style="" >
										<a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "
										href="./System/systemAbout/circuitCustomize_add.jsp"
										target="workMain">创建新流程</a>
									</li>
								</ul>
							</div>
							<div style="width: 240px;height: 100px;">
								<h2>
									工作清单
									
								</h2>
								<ul>
									<li>想开黑？先把办公室打扫了！</li>
									<li>想放假？先把办公室打扫了！</li>
									<li>想睡懒觉？先把办公室打扫了！</li>
								</ul>
							</div>
						</div>
						<div style="width: 240px; height: 100px; float: left; margin: 20px;">
							<div style="width: 240px;height: 100px; ">
								<h2>
									<a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "
										href="NoticeAction!addPage.action"
										target="workMain">公司通告</a>
								</h2>
							<div class="marquee">
								<marquee direction="left" scrollamount="5"
								onmouseout="this.start()" onmouseover="this.stop()">
								<font color="#737373" id="show0"></font>
							</marquee>
						</div>
							</div>
							<div style="width: 240px;height: 100px;">
								<h2>
									工作计划
								</h2>
								<ul>
									<li>想开黑？先把办公室打扫了！</li>
									<li>想放假？先把办公室打扫了！</li>
									<li>想睡懒觉？先把办公室打扫了！</li>
								</ul>
							</div>
							<div style="width: 240px;height:100px; cursor: pointer;">
								<h2>
									<a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "
										href="WorkLogAction!findWorkLogByCondition.action?pageStatus=single&workLog.logStatus="
										target="workMain">任务通知</a>
								</h2>
								<ul id="work_ul">
								</ul>
							</div>
						</div>
						<div style="float: right; margin: 20px 175px 0px ;">
							<div onclick="showcalendar()" style="margin:0px 25px 0px 0px;  cursor: pointer;">
								<h2 style="width:70px; 
								white-space:normal; word-break:break-all;word-wrap:break-word;
								 ">日历/日程安排</h2>
							</div>
							<div onclick="" style="margin:150px 25px 0px 0px;    cursor: pointer;">
								<h2 style="width:90px; 
								white-space:normal; word-break:break-all;word-wrap:break-word;
								 ">
								 	技术资料共享/交流区
								 </h2>
							</div>
						</div>
					</div>	
					<div  style="margin: 20px 0px 0px 75px; display: none; " id="calendar_test">
							<div id='calendar' ></div>
							<div style="cursor: pointer;" onclick="fanhui()" >
								<h2>&nbsp;&nbsp;&nbsp;&nbsp;返回</h2>
							</div>	
					</div>
					
							<DIV style="DISPLAY: none;" id="reservebox">
								<FORM action="CalendarAction!addCalendar.action"
									id="reserveformID" style="padding: 0px; margin: 0px;"
									method="post">
									<DIV class="rowElem" align="left">
										<LABEL for="title">
											标题:
										</LABEL>
										<INPUT id="title" name="calendar.title"
											class="validate[required]" />
										<font color="red">*</font>
									</DIV>
									<DIV class="rowElem" align="left" style="height: 70px;">
										<LABEL for="content">
											内容:
										</LABEL>
										<textarea rows="4" cols="40" id="content"
											style="margin: 0px; padding: 0px;"
											name="calendar.thingContent"></textarea>
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="thingType">
											所属人:
										</LABEL>
										<select id="dept" style="width: 100px; float: left;">
											<option></option>
										</select>
										<select id="users" style="width: 100px; float: left;"
											name="calendar.userId">
										</select>
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="thingType">
											事件类型:
										</LABEL>
										<select id="thingType" style="width: 100px;"
											name="calendar.thingType">
											<option></option>
											<option value="工作计划">
												工作计划
											</option>
											<option value="法定假">
												法定假
											</option>
											<option value="年休假">
												年休假
											</option>
											<option value="换休">
												换休
											</option>
											<option value="公假">
												公假
											</option>
											<option value="病假">
												病假
											</option>
											<option value="婚假">
												婚假
											</option>
											<option value="丧假">
												丧假
											</option>
											<option value="事假">
												事假
											</option>
											<option value="双休日">
												双休日
											</option>
										</select>
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="start">
											开始时间:
										</LABEL>
										<INPUT id="start"
											class="validate[required,funcCall[validate2time]]"
											name="calendar.start"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											style="width: 80px;" />
										<font color="red">*</font>
										<INPUT id="start2" name="calendar.start"
											style="width: 80px; display: none;" />

									</DIV>
									<DIV class="rowElem" align="left" />
										<LABEL for="end">
											结束时间:
										</LABEL>
										<INPUT id="end" name="calendar.endDate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"
											style="width: 80px;" />
										<INPUT id="end2" name="calendar.endDate"
											style="width: 80px; display: none;" />
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="allDay">
											是否全天:
										</LABEL>
										<input id="allDay" type="radio" value="true"
											name="calendar.allDay" checked="checked"
											style="float: left; width: 20px;" />
										<span style="float: left; width: 40px;">是</span>
										<input id="allDay2" type="radio" value="false"
											name="calendar.allDay" style="float: left; width: 40px;" />
										<span style="float: left; width: 40px;">否</span>
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="thingStatus">
											事件性质:
										</LABEL>
										<input id="thingStatus" type="radio" value="private"
											name="calendar.thingStatus" checked="checked"
											style="float: left; width: 20px;" />
										<span style="float: left; width: 40px;">私人</span>
										<input type="radio" value="public" name="calendar.thingStatus"
											style="float: left; width: 40px;" />
										<span style="float: left; width: 40px;">公开</span>
									</DIV>
									<DIV class="rowElem" align="left">
										<LABEL for="isRepeat">
											是否重复:
										</LABEL>
										<input id="isRepeat" name="calendar.isRepeat" type="checkbox"
											value="yes" style="width: 20px" />
									</DIV>
									<DIV id="thingTypeDiv" class="rowElem" align="left"
										style="display: none;">
										<LABEL for="repeatCycle">
											重复周期:
										</LABEL>
										<select id="thingType" style="width: 60px;"
											name="calendar.repeatCycle">
											<option value="day">
												每天
											</option>
											<option value="week">
												每周
											</option>
											<option value="month">
												每月
											</option>
											<option value="year">
												每年
											</option>
										</select>
									</DIV>
									<DIV id="repeatFrequencyDiv" class="rowElem" align="left"
										style="display: none;">
										<LABEL for="repeatFrequency">
											重复频率:
										</LABEL>
										<select id="repeatFrequency" name="calendar.repeatFrequency"
											style="width: 50px;">
											<option value="1">
												1
											</option>
										</select>
									</DIV>
								</FORM>
							</DIV>
							<div id='calendarDetail'>
								<div>
									<div id="messageimg">
									</div>
									<div>
										<div>
											<span id="messageSendUserName"></span>
											<br />
											<br />
										</div>
										<div>
											<span id="messageTitle"></span>
										</div>
										<div>
											<span id='messageContent'></span>
										</div>
										<div id='messageDelete'>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="showMf" style="display: none;">
						<div class="headercon">
							<ul>
								<li>
									<a href="javascript:;"><strong></strong> </a>
								</li>
							</ul>
							<dl>
								<dt>
									<a href="#" id="showAllPm" target="showQuan">全屏</a>
								</dt>
								<dd>
									<a href="javascript:;"
										onclick="document.getElementById('workMainIframe').contentWindow.history.back();">返回</a>
								</dd>
								<dd>
									<a href="#" id="shuaxin" target="workMain">刷新</a>
								</dd>
							</dl>
						</div>
						<div style="padding-top: 15px;">
							<!--主体 iframe -->
							<iframe name="workMain" target="workMain" id="workMainIframe"
								data="<%=basePath%>" src="" marginwidth="0" marginheight="0"
								hspace="0" vspace="0" frameborder="0" scrolling="auto"
								style="width: 100%; height: auto; margin: 0px; padding: 0px;"></iframe>
						</div>
					</div>
				</div>
				<div id="fix_sidebar">
					<div id="back_top">
						<a href="#"></a>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript"
			src="js/jquery.mCustomScrollbar.concat.min.js">
</script>
		<script type="text/javascript" src="js/jquery.scrollTo.min.js">
</script>
		<script type="text/javascript">
// 回到顶部
$("#back_top").css("display", "none");
$(window).scroll(function() {
	var s_top = $(document).scrollTop() - $(document).height();
	if ($(document).scrollTop() > 0) {
		$("#back_top").css("display", "block");
	} else {
		$("#back_top").css("display", "none");
	}
});

$("#back_top").on('click', 'a', function() {
	$.scrollTo(0, 800);
});

$(function() {
	//公告
	$.ajax( {
		url : "NoticeAction!show.action",
		type : 'post',
		dataType : 'json',
		cache : false,//防止数据缓存
		success : function(useradsfa) {
			$("#show").empty();//清空
		var message = "";
		$(useradsfa).each(function(i, n) {
			message += (i + 1) + "、" + n.content + "&nbsp;&nbsp;&nbsp;&nbsp;";
		});
		$("#show").html(message);
		$("#show0").html(message);
	}
	});

	//系统消息提醒
	$.ajax( {
		type : "POST",
		url : "AlertMessagesAction!findAMCountByUid.action",
		dateType : "json",
		success : function(msg) {
			if (msg > 0) {
				$("#pmImg").show();
				$(".messageCount").text("[" + msg + "]");
				$(".messageCount").css( {
					color : "red"
				});
				$(".messageCount").bind("mouseover", function() {
					$("#messageCount").css("background-color", "#696969");
				})
				$(".messageCount").bind("mouseout", function() {
					$("#messageCount").css("background-color", "#ffffff");
				})
			}
		}
	});

	//获取邮箱已读未读
	$.ajax( {
		type : "POST",
		url : "JavaMailAction!getMailInfo.action",
		dataType : "json",
		success : function(data) {
		
			if (data != null) {
				$("#allMail").text(data[0]);
				$("#unReadMail").text(data[1]);
				$("#allMail").parent().attr("href", data[2]);
				$("#unReadMail").parent().attr("href", data[2]);
				$("#allMail2").text(data[0]);
				$("#unReadMail2").text(data[1]);
				$("#allMail2").parent().attr("onclick", 'window.open("'+data[2]+'")');
				$("#unReadMail2").parent().attr("onclick",'window.open("'+data[2]+'")');
			} else {
				$("#allMail").parent().parent().remove();
				$("#unReadMail").parent().parent().remove();
			}
		}
	});

});

function showWork(moduleid) {
	$("#person").hide();
	$("#showMf").show();

	if (moduleid != null && moduleid > 0) {
		$("#workMainIframe").attr(
				"src",
				"ModuleFunctionAction!findMfByIdForJump.action?pageStatus=3&id="
						+ moduleid);
		$.ajax( {
			url : 'ModuleFunctionAction!findMfByIdForJson.action',
			type : 'post',
			dataType : 'json',
			data : {
				id : moduleid,
				pageStatus : '3'
			},
			cache : true,
			success : function(mf) {
				$(".cenjinav p").html(mf[0]);
				$(".headercon strong").html(mf[1].functionName);
				$("#showAllPm").attr(
						"href",
						"ModuleFunctionAction!findMfByIdForJump.action?pageStatus=3&id="
								+ moduleid);
				$("#shuaxin").attr(
						"href",
						"ModuleFunctionAction!findMfByIdForJump.action?pageStatus=3&id="
								+ moduleid);
			},
			error : function() {
				location.href = 'ModuleFunctionAction!findMfByUser.action';
			}
		});
	}
}

var ModuleName =new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  prefetch: '',
  remote: {
	url: 'ModuleFunctionAction!searchModuleFunction2.action',
    prepare: function (query, settings) {
                     settings.url += '?moduleFunction.functionName=' + encodeURI(encodeURI(query));
<%--                     alert(encodeURI(encodeURI(query)));--%>
                      return settings;
                   }
  }
});

$('#remote .typeahead').typeahead(null, {
  name: 'names',
  display: 'modulename',
  source: ModuleName,
  limit : '10',
});

$('#remote .typeahead').bind('typeahead:selected', function(obj, datum, name) { 
	showWork();
	var eValue=eval('datum.'+"moduleid");
	window.location.href='ModuleFunctionAction!findMfByIdForJump.action?id='+eValue;
});

function addmore(){
	var s="<div class='more tt-suggestion tt-selectable '>更多</div>";
	$('.tt-menu').append(s);
}
addmore();
$('.more').on("click",function(){
	 showWork();
	 $(".searchFun").submit(); 
	
});

<%--=================工作台=================--%>


//设置消息为已读
function updatemsg(id){
	if(id>0){
		$.ajax( {
		type : "POST",
		url : "AlertMessagesAction!updateMessagesStatus1.action",
		data : {
			id :id,
		},
		dataType : "json",
		success : function(data) {
			
		}
	})}
}
	
	

var caldata={};
var calCircuitData={}

$.ajax({
		url : 'AlertMessagesAction!findAlertMessages4calendar.action',
		dataType : 'json',
		data : {
		},
		async :false,
		cache : false,//防止数据缓存
		success : function(doc) {
			caldata=doc;
			}
		});

$.ajax({
		url : 'CircuitRunAction!findCircuitRun4calendar.action',
		dataType : 'json',
		data : {
		},
		async :false,
		cache : false,//防止数据缓存
		success : function(doc) {
			calCircuitData=doc;
			}
		});



$(document).ready(function() {
	caldata.push.apply(caldata,calCircuitData);
	var initialLocaleCode = 'zh_cn';
		$('#calendar').fullCalendar({
			header : {
					left : 'prevYear,nextYear, prev,next, today',
					center : 'title',
					right : 'month,agendaWeek,agendaDay,listMonth'
				},
			buttonText : {
							prevYear : '去年',
							nextYear : '明年',
							today : '今天',
							month : '月',
							listMonth :'列表',
							agendaWeek : '周',
							agendaDay : '日'
						},
<%--			defaultDate: '2017-11-12',--%>
			locale: initialLocaleCode,
			buttonIcons: true, // show the prev/next text
			weekNumbers: true,
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			businessHours: true, // display business hours
			
			//内容高度
			contentHeight : 800,
			//每周开始的日期：0为周日
			firstDay : 0,
			//第几周显示的文字
			weekNumberTitle : '周',
			//数据
<%--			events: caldata,--%>
			events : function(start, end,timezone, callback) {
										$.ajax( {
													url : 'CalendarAction!findCalendar.action',
													dataType : 'json',
													data : {
														start: start.format(),
			                							end: end.format()
<%--														start : $.fullCalendar--%>
<%--																.formatDate(--%>
<%--																		start,--%>
<%--																		"yyyy-MM-dd HH:mm:ss"),--%>
<%--														end : $.fullCalendar--%>
<%--																.formatDate(--%>
<%--																		end,--%>
<%--																		"yyyy-MM-dd HH:mm:ss")--%>
													},
													cache : false,//防止数据缓存
													success : function(doc) {
														var events = [];
														var Allevents = [];
														$(doc).each(
																		function() {
																			events.push( {
																						fkId : $(
																								this)
																								.attr(
																										'id'),
																						id : $(
																								this)
																								.attr(
																										'id'),
																						title : '提醒消息\n'+$(
																								this)
																								.attr(
																										'title'),
																						allDay : $(
																								this)
																								.attr(
																										'allDay'),
																						start : $(
																								this)
																								.attr(
																										'start'),
																						end : $(
																								this)
																								.attr(
																										'endDate'),
																						url : $(
																								this)
																								.attr(
																										'url'),
																						className : $(
																								this)
																								.attr(
																										'className'),
																						editable : $(
																								this)
																								.attr(
																										'editable'),
																						source : $(
																								this)
																								.attr(
																										'source'),
																						color : $(
																								this)
																								.attr(
																										'color'),
																						backgroundColor : $(
																								this)
																								.attr(
																										'backgroundColor'),
																						borderColor : $(
																								this)
																								.attr(
																										'borderColor'),
																						textColor : $(
																								this)
																								.attr(
																										'textColor'),
																						thingType : $(
																								this)
																								.attr(
																										'thingType'),
																						thingStatus : $(
																								this)
																								.attr(
																										'thingStatus'),
																						userId : $(
																								this)
																								.attr(
																										'userId'),
																						userName : $(
																								this)
																								.attr(
																										'userName'),
																						code : $(
																								this)
																								.attr(
																										'code'),
																						dept : $(
																								this)
																								.attr(
																										'dept'),
																						addUserId : $(
																								this)
																								.attr(
																										'addUserId'),
																						addUserName : $(
																								this)
																								.attr(
																										'addUserName'),
																						addUserCode : $(
																								this)
																								.attr(
																										'addUserCode'),
																						addUserDept : $(
																								this)
																								.attr(
																										'addUserDept'),
																						addDateTime : $(
																								this)
																								.attr(
																										'addDateTime'),
																						//thingContent : $(
																						content : $(
																								this)
																								.attr(
																										'thingContent'),
																						sumDay : $(
																								this)
																								.attr(
																										'sumDay'),
																						isRepeat : $(
																								this)
																								.attr(
																										'isRepeat'),
																						repeatCycle : $(
																								this)
																								.attr(
																										'repeatCycle'),
																						repeatFrequency : $(
																								this)
																								.attr(
																										'repeatFrequency')
																					});
																		});
														Allevents=caldata.concat(events)
														callback(Allevents);
														
													},
													error : function() {
														alert('there was an error while fetching events!');
													},
													color : 'red', // a non-ajax option
													textColor : 'black' // a non-ajax option
												});
									},
									
									
			//
			eventClick: function(calEvent, jsEvent, view) {
					//处理
					if(calEvent.functionId!=0){
						updatemsg(calEvent.id);
						var dispose="<a id='disposeUrl' href='AlertMessagesAction!findAlertMessagesForUrl.action?id="+calEvent.id+"'>  前往处理         </a>"
					}else if(calEvent.functionUrl.indexOf("CircuitRunAction_findAduitPage.action")>=0){
						
						var dispose ="<a id='disposeUrl' href='"+calEvent.functionUrl+"&msgId="+calEvent.id+"'>  前往处理         </a>"
					}else{
						
						var dispose ="<a id='disposeUrl' href='"+calEvent.functionUrl+"' onclick='updatemsg("+calEvent.id+")'>  前往处理         </a>"
					}
					
					//删除
					if(calEvent.fkId!=null){
						var deletediv="<a id='deleteUrl' onclick='return window.confirm('确定要删除吗?')' href='CalendarAction!delCalendar.action?id="+ calEvent.id+"&isRepeat=delOne'>删除</a>";
						dispose="";
					}else{
						var deletediv="<a id='deleteUrl' onclick='return window.confirm('确定要删除吗?')' href='AlertMessagesAction!delAlertMessages.action?id="+ calEvent.id+"&calendar=true'>删除</a>";	
					}
						
					$("#deleteUrl").remove();
					$("#disposeUrl").remove();
					$("#messagePersonimg").remove();
					
					
					var personimg =calEvent.sendUserImg;
					if(personimg!=null && personimg.length>0){
						var imgdiv="<img alt='提醒' src='"+calEvent.sendUserImg+"' height='100px;' width='100px;' align='middle'  id='messagePersonimg' "+"onerror="+"this.src='images/man.jpg'"+""+" >";
					}
					else{
						var imgdiv="<img alt='12' src='images/man.jpg' height='100px;' width='100px;' align='middle'  id='messagePersonimg' >";
					}
					$("#messageimg").append(imgdiv);
					$("#messageDelete").append(deletediv);
					$("#messageDelete").append(dispose);
					$("#messageContent").text(calEvent.content);	
					$("#messageTitle").text(calEvent.title);
					if(calEvent.sendUserName!=null && calEvent.addTime!=null){
						$("#messageSendUserName").text(calEvent.sendUserName+'   '+calEvent.addTime+'   提醒您');	
					}

    		},
    		
    		
    		 dayClick: function(date, jsEvent, view) {
<%--    				 alert('Clicked on: ' + date.format());--%>
				 					//判断显示明细的对话框是否在打开
						if ($("#reserveinfo").dialog("isOpen")) {
							$("#reserveinfo").dialog("destroy");//销毁明细对话框
						}
						//判断添加的对话框是否在打开
						if ($("#reservebox").dialog("isOpen")) {
							var titleVal = $("#title").val();
							if (titleVal != "") {
								if (window
										.confirm("您的活动尚未保存,确定要舍弃吗?") == false) {
									return false;
								}
							}
							//重置表单
							$("#reserveformID")[0].reset();
							$("#thingTypeDiv").hide();
							$("#repeatFrequencyDiv").hide();
							$("#reservebox").dialog("destroy");//销毁添加的对话框
						};
						//选中的时间
						var selectdate = date.format();
						var x = event.clientX;//x坐标
						var y = event.clientY;//y坐标
						$("#start").val(selectdate);//为开始时间赋值
						$("#end").val(selectdate);//为开始时间赋值

						//jquery弹出对话框插件(dialog)
						$("#reservebox").dialog(
										{
											title : selectdate,
											modal : false,//模态对话框，若为是，则不可操作背景层。
											autoOpen : false,//这个属性为true的时候dialog被调用的时候自动打开dialog窗口。当属性为false的时候，一开始隐藏窗口，知道.dialog("open")的时候才弹出dialog窗口。默认为：true。
											closeOnEscape : true, //为true的时候，点击键盘ESC键关闭dialog，默认为true;(ie不支持)
											hide : 'slide',//关闭效果
											show : 'show',//打开效果
											draggable : true,//是否可拖动
											resizable : true,//是否可改变大小
											//height : 270,//高度
											//minHeight : 270,//最小高度
											width : 355,//宽度
											minWidth : 355,//最小宽度
											position : [
													x - 200,
													y - 500 ],//使窗口跟随鼠标移动

											//关闭之前的方法
											beforeClose : function(event, ui) {
												var titleVal = $("#title").val();
												if (titleVal != "") {
													if (window.confirm("您的活动尚未保存,确定要舍弃吗?") == false) {
														return false;
													}
												};
												//重置表单
												$("#reserveformID")[0].reset();
												//清空验证提示信息
												$.validationEngine.closePrompt("#title");
												$.validationEngine.closePrompt("#start");
												$.validationEngine.closePrompt("#end");
												//隐藏date2
												$("#start2").hide();
												$("#end2").hide();
											},
											buttons : {
												"关闭" : function() {
													$("#reservebox").dialog("close");
												},
												"添加" : function() {
													if ($("#reserveformID").validationEngine({
															returnIsValid : true
														})) {
														$("#reserveformID").submit();
														}
												}
											}
										});

						//是否重复处理
						$("#isRepeat").bind("click",function() {
									if ($("#isRepeat").attr('checked')) {
										$("#repeatFrequency").empty();//清空下拉框
										//添加option(生成30个重复周期)
										for ( var i = 1; i <= 30; i++) {
											$(
													"<option value='"
															+ i
															+ "'>"
															+ i
															+ "</option>")
													.appendTo(
															"#repeatFrequency");
										}
										$("#thingTypeDiv").show('slow');
										$("#repeatFrequencyDiv").show('slow');
									} else {
										$("#thingTypeDiv").hide('slow');
										$("#repeatFrequencyDiv").hide('slow');
									}
								});
						
						//查询所有的部门
						$.ajax( {
								url : 'DeptNumberAction!findAllDept.action',
								dataType : 'json',
								cache : false,//防止数据缓存
								success : function(allDdept) {
									$("#dept").empty();
									$("<option></option>").appendTo("#dept");
									$(allDdept).each(
										function() {$("<option value='"
															+ this.dept
															+ "'>"
															+ this.dept
															+ "</option>")
													.appendTo(
															"#dept");
										});
								}

							});
						//级联查询出部门所对应的所有人员
						$("#dept").bind("change",
							function() {
								if ($("#dept").val() != "") {
									$.ajax( {
											url : "UsersAction!findUsersByDept.action",
											type : 'post',
											dataType : 'json',
											contentType : "application/x-www-form-urlencoded; charset=utf-8",
											data : {
												deptName : $("#dept").val()
											},
											success : function(useradsfa) {
												$("#users").empty();//清空
												//$("<option></option>").appendTo("#users");
												$(useradsfa).each(
													function() {
														$(
																"<option value='"
																		+ this.id
																		+ "'>"
																		+ this.name
																		+ "</option>")
																.appendTo("#users")
													});
											},
											error : function() {
												alert("服务器异常!");
											}
										});
								}
							});
						
						//打开添加对话框
						$("#reservebox").dialog("open");
		    },
    		
    		eventAfterRender:function( event, element, view ) {
		    	
		    	
<%--		    	if(event.color=="#257e4a"){--%>
<%--		    		$("<span>提醒消息</span>").prependTo(element);		--%>
<%--		    	}--%>
<%--		    	else if(event.color=="#CC6699"){--%>
<%--		    		$("<span>审核消息</span>").prependTo(element);--%>
<%--		    	}else{--%>
<%--		    		$("<span>个人日程</span>").prependTo(element);--%>
<%--		    	}--%>
		    	
		    	 //$('#calendar').fullCalendar('render');
		    	
    		}
    		
		});
		//$('#calendar').fullCalendar( 'rerenderEvents' );									
			
		if(caldata.length>0){
			$(".fc-icon-left-single-arrow").css("color","red");
		}
		
		
		
		
	});

	$(function(){
				$("#menu").show();
				$(".menucontainer:eq(" + menu_index + ")").show();
				if(menu_index>0){
					$("#showOrCloseMenuDiv").css("left","431px");
					$("#marginDiv").css("left","426px");
				}else{
					$("#showOrCloseMenuDiv").css("left","131px");
					$("#marginDiv").css("left","126px");
				}
				//获取邮箱已读未读
	$.ajax( {
		type : "POST",
		url : "JavaMailAction!getMailInfo.action",
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#allMail").text(data[0]);
				$("#unReadMail").text(data[1]);
				$("#allMail").parent().attr("href", data[2]);
				$("#unReadMail").parent().attr("href", data[2]);
			} else {
				$("#allMail").parent().parent().remove();
				$("#unReadMail").parent().parent().remove();
			}
		},
		error : function() {
			$("#allMail").parent().parent().remove();
			$("#unReadMail").parent().parent().remove();
		}
	});
				
		$.ajax( {
		type : "POST",
		url : "WorkLogAction!ajax_findLogStatusDeUser.action",
		dataType : "json",
		success : function(data) {
			$("#work_ul").empty();
			if (data != null && data.length>0) {
				$(data).each(function(){
					$("#work_ul").append('<li><span>'+this.title+'<span>&nbsp;&nbsp;<span>'+this.userName+'<span>' +
					'&nbsp;&nbsp;<span>'+this.mouth+'<span> &nbsp;&nbsp;<span>'+this.dept+'<span> <li>');
				})
			}else{
				$("#work_ul").append('<li><a onclick="showWork();hidcontent()" style="text-decoration:none; color: #707070; "' +
				'href="WorkLogAction!findWorkLogByCondition.action?pageStatus=single&workLog.logStatus=" target="workMain">所有</a><li>');
			}
		}
		
	});
				
			
	})
function hidcontent(){
		$(".content").hide();
}
function showcontent(){
		$(".content").show();
}
function showcalendar(){
	$("#caltest").hide();
	$("#calendar_test").show();
	
}
function fanhui(){
	$("#caltest").show();
	$("#calendar_test").hide();
}
<%--=================工作台 over=================--%>
</script>
	</body>
</html>

