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
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<br/>
				<form action="AskForLeaveAction!addqxAskForLeave.action" method="post" onsubmit="disable('sub')">
					<table>
						<tr>
							<th>实际开始时间:</th>
							<td>
								<input type="text" value="${askForLeave.leaveStartDate}" class="Wdate"  name="qxAskForLeave.startDate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" onchange="bijiao(this,'${askForLeave.leaveStartDate}')" />
							</td>
						</tr>
						<tr>
							<th>实际结束时间:</th>
							<td>
								<input type="text" value="${askForLeave.leaveEndDate}" class="Wdate"  name="qxAskForLeave.endDate"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="hidden" value="${askForLeave.leaveId}"  name="qxAskForLeave.leaveId"/>
								<input type="submit" value="申请销假" id="sub" style="width: 100px;height: 35px;"  />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function bijiao(obj,time2){
			if(obj!=null){
				var time1 = obj.value;
				var	arrays1 =	time1.split(" ");
				var number1 = "";
				if(arrays1!=null &&  arrays1.length == 2){
					var str1 =	arrays1[0] ;
					var arrays1_1 = str1.split('-');
					if(arrays1_1!=null && arrays1_1.length == 3  ){
						number1 = arrays1_1[0]+arrays1_1[1]+arrays1_1[2]+"";
					}
					var str2 =	arrays1[1] ;
					var arrays1_2 = str2.split(":");
					if(arrays1_2!=null && arrays1_2.length == 3  ){
						number1 += arrays1_2[0]+arrays1_2[1]+arrays1_2[2]+"";
					}
					
				}
				var arrays2 = time2.split(" ");
				var number2 = "";
				if(arrays2!=null &&  arrays2.length == 2){
					var str1 =	arrays2[0] ;
					var arrays2_1 = str1.split("-");
					if(arrays2_1!=null && arrays2_1.length == 3  ){
						number2 = arrays2_1[0]+arrays2_1[1]+arrays2_1[2]+"";
					}
					var str2 =	arrays2[1] ;
					var arrays2_2 = str2.split(":");
					if(arrays2_2!=null && arrays2_2.length == 3  ){
						number2 += arrays2_2[0]+arrays2_2[1]+arrays2_2[2]+"";
					}
					
				}
				if(parseInt(number1)<parseInt(number2)){
					alert("实际开始时间不能超过之前的请假开始时间");
					obj.value = time2;  
				}
			}
		}
		function disable(obj){
			$("#"+obj).attr("disabled","disabled");
		}
		</SCRIPT>
	</body>
</html>
