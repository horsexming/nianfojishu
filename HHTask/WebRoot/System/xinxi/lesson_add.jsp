<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<script type="text/javascript"
	src="<%=basePath%>/javascript/DatePicker/WdatePicker.js">
</script>
<link rel="shortcut icon" href="/upload/file/sysImages/favicon.ico" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/index.css" />
<%@include file="/util/inc.jsp"%>
		<title>嘉定共修班2106届</title>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
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
							<span id="title"></span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								id="closeTcDiv" height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
						
				</div>
			</div>
		</div>
		
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<div style="font-size: large;">嘉定共修班2106届 版权:上海零参科技发展有限公司</div>
			<div >&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="javascript:;" onclick="tanchu('<%=basePath%>/userCenter/updatePassword.jsp','修改密码')">修改密码</a> 
			&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:;" onclick="tanchu('<%=basePath%>/System/xinxi/lesson_addstudent.jsp','添加学员')">添加学员</a>
			</div>
				<font id="ziti_font" color="red" size="5">${errorMessage}</font>
				<form action="attendClassAction_addLesson.action"  method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;期:</th>
							<td>
								<input type="text" name="lesson.ldate" class="Wdate" id="ldate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课程开始时间:</th>
							<td>
								<input type="text" name="lesson.ltimestart"class="Wdate" id="ltimestart"
								onClick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课程结束时间:</th>
							<td>
								<input type="text" name="lesson.ltimeend"class="Wdate" id="ltimeend"
								onClick="WdatePicker({dateFmt:'HH:mm:ss',skin:'whyGreen'})">
							</td>
						</tr>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;序&nbsp;&nbsp;&nbsp;号:</th>
							<td>
								<input type="text" name="lesson.lnumber" onblur="numyanzheng(this)" 
								onkeyup="numyanzheng(this)" id="lnumber">
							</td>
						</tr>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;容:</th>
							<td>
								<input type="text" name="lesson.content" id="content">
							</td>
						</tr>
						<tr>
							<th>课&nbsp;&nbsp;&nbsp;程&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;态:</th>
							<td>
								<input type="radio" value="激活" name="lesson.activation" />激活
								<input type="radio" value="未激活" name="lesson.activation" checked="checked"/>未激活
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="添加" class="input" id="sub"/>
								<a href="attendClassAction_selectAttend.action"><font size="5"></font>查询圆满情况</a>
							</td>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>课程日期</th>
						<th>课程开始时间</th>
						<th>课程结束时间</th>
						<th>课程序号</th>
						<th>课程内容</th>
						<th>课程状态</th>
						<th>操作</th>
					</tr>
				<s:iterator id="pagelist1" value="wlessonList" status="pagestatus1">
					<s:if test="#pagestatus1.first">
								<tr bgcolor="red">
									<th colspan="8" align="center">
										<font color="#ffffff"> 未激活课程<br /> </font>
									</th>
								</tr>
							</s:if>
					<s:if test="#pagestatus1.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
						<td>
							<s:property value="#pagestatus1.index+1" />
						</td>
						<td>${pagelist1.ldate}</td>
						<td>${pagelist1.ltimestart}</td>
						<td>${pagelist1.ltimeend}</td>
						<td>${pagelist1.lnumber}</td>
						<td>${pagelist1.content}</td>
						<td>
							--
						</td>
						<td>
							<a href="attendClassAction_activationLesson.action?id=${pagelist1.id}">激活</a>/
							<a href="attendClassAction_delLesson.action?lesson.id=${pagelist1.id}&cpage=${cpage}" onclick=" return confirm('确定要删除吗?')">删除</a>
						</td>
					</tr>
				</s:iterator>
				<s:iterator id="pagelist" value="lessonList" status="pagestatus">
				<s:if test="#pagestatus.first">
								<tr bgcolor="blue">
									<th colspan="8" align="center">
										<font color="#ffffff"> 已激活课程<br /> </font>
									</th>
								</tr>
					</s:if>
   					<s:if test="#pagestatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
						<td>
							<s:property value="#pagestatus.index+1" />
						</td>
						<td>${pagelist.ldate}</td>
						<td>${pagelist.ltimestart}</td>
						<td>${pagelist.ltimeend}</td>
						<td>${pagelist.lnumber}</td>
						<td>${pagelist.content}</td>
						<td>
							已激活
						</td>
						<td>
							<a href="javascript:;" onclick="tanchu('attendClassAction_getlessonByid.action?id=${pagelist.id}','修改课时')">修改</a>/
							<a href="attendClassAction_delLesson.action?lesson.id=${pagelist.id}&cpage=${cpage}" onclick=" return confirm('确定要删除吗?')">删除</a>
						</td>
					</tr>
				</s:iterator>
				 <tr>
				<td colspan="14" align="right">
								第
					<font color="red"><s:property value="cpage" /> </font> /
						<s:property value="total" />
							页
						<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
									
				</td>
			</tr>
			
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<script type="text/javascript">	
function numyanzheng(obj){
	var ty1 = /^\d*$/;
	var bChk=ty1.test(obj.value);
	if (!bChk) {
		obj.value = "";
		obj.focus();
		obj.select();
	}
}
function check(){
	var ldate = $("#ldate").val();
	var ltimestart = $("#ltimestart").val();
	var ltimeend = $("#ltimeend").val();
	var lnumber = $("#lnumber").val();
	var content = $("#content").val();
	if(ldate ==""){
		$("#ziti_font").html("请填写课程日期");
		return false;
	}else if(ltimestart == ""){
		$("#ziti_font").html("请填写课程日开始时间");
		return false;
	}else if(ltimeend == ""){
		$("#ziti_font").html("请填写课程日结束时间");
		return false;
	}else if(lnumber == ""){
		$("#ziti_font").html("请填写课程序号");
		return false;
	}else if(content == ""){
		$("#ziti_font").html("请填写课程内容");
		return false;
	}
	document.getElementById("sub").disabled="disabled";
	return true;
}
function tanchu(src,title){
	document.getElementById("xiugaiIframe").src=src;
		chageDiv('block',title);
}

</script>
	</body>
</html>

