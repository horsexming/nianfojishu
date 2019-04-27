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
				<table class="table">
					<tr align="center" bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							实际开始时间
						</th>
						<th>
							实际结束时间
						</th>
						<th>
							销假申请人
						</th>
						<th>
							审批状态
						</th>
						<th>
							审批动态
						</th>
						<th>
							操作
						</th>
					</tr>
						<s:iterator value="list" id="qxAskForLeave" status="pagestatus1">
							<s:if test="#statussdf.index%2==1">
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
								<td>
									${qxAskForLeave.startDate}
								</td>
								<td>
									${qxAskForLeave.endDate}
								</td>
								<td>
									${qxAskForLeave.userName}
								</td>
								<td>
									${qxAskForLeave.epStatus}
								</td>
								<td>
									<a href="CircuitRunAction_findAduitPage.action?id=${qxAskForLeave.epId}">审批动态</a>
								</td>
								<td>
									<s:if test="#qxAskForLeave.epStatus == '未审批' || #qxAskForLeave.epStatus == '打回'">
										<a href="AskForLeaveAction!delqxAskForLeave.action?qxAskForLeave.id=${qxAskForLeave.id}&qxAskForLeave.leaveId =${qxAskForLeave.leaveId}">删除</a>
									</s:if>
								</td>
							</tr>
						</s:iterator>
				</table>				
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
		
		</SCRIPT>
	</body>
</html>
