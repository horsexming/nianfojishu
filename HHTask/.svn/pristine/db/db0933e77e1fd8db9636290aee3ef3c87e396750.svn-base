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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<a href="attendClassAction_initaddLesson.action">添加课时</a><br/>
				<font id="ziti_font" color="red" size="5"></font>
				<form action="attendClassAction_selectAttend.action"  method="post" onsubmit="return check()">
					<table>
						<tr>
							<th>月份:</th>
							<td>
								<input type="text" name="month" class="Wdate" id="month"
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})">
							</td>
							<th>学号:</th>
							<td>
								<input type="text" name="code" id="code" >
							</td>
							<th>名字:</th>
							<td>
								<input type="text" name="name" id=""name"" >
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="查询" class="input" id="sub"/>
							</td>
						</tr>
					</table>
				</form>
				<s:if test="list.size()>0">
				<table class="table">
				
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>学生</th>
					<s:iterator id="lnumber" value="list" status="pagestatus">
						<th>第${lnumber}课时</th>
					</s:iterator>
					</tr>
				<s:iterator id="pagesavo" value="studentAttendVoList" status="pagestatus">
   					<s:if test="#pagestatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
					</s:else>
						<td>${pagesavo.userName} </td>
						<s:iterator id="attendStatus" value="#pagesavo.attendStatusList" status="pagestatus">
						<s:if test="#attendStatus=='未圆满'"><td style="cursor: hand;" onclick="attend('${pagesavo.userCode}','${pagesavo.lnumberList[pagestatus.index]}')"><font color="red">X</font></td></s:if>
							<s:elseif test="#attendStatus=='未激活'"><td><font color="gray">未激活</font></td></s:elseif>
							<s:else><td><font color="green">${attendStatus}</font></td></s:else> 
						
						</s:iterator>
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
				 </s:if>
				 <s:else>
				 <font color="red"><h3>目前没有课时</h3></font>
				 </s:else>
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
	}
	document.getElementById("sub").disabled="disabled";
}

function attend(code,Number){
	if(confirm('确定该学员的该课时已修圆满?')){
	$.ajax({
		 type : "POST",
			url : "attendClassAction_attend.action",
			data : {
				code:code,
				lnumber:Number,
			},
		dataType : "json",
		success : function(data) {
			if(data.success){
				window.location.reload();
			}
		}
		
	})
	}
}
</script>
	</body>
</html>

