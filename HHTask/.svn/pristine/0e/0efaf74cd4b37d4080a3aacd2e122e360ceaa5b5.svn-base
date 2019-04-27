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
	<body onload="alertMessage()">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			<s:if test="%{successMessage.contains('请放行')}">
			<span style="font-size: 15;color: green;font-weight: bold">${successMessage}</span>
			</s:if>
			<s:else>
			<span style="font-size: 20;color: red;font-weight: bold">${successMessage}</span>
			</s:else>
			
				<form action="AskForLeaveAction!updateExit.action"
					method="post">
					<input name="pageStatus" value="${pageStatus}" type="hidden" />
					<table class="table">
					    <tr>
					        <th colspan="5" align="center">请假出门管理</th>
					    </tr>
						<tr>
							<td colspan="2" style="font-weight:bold;font-size: 15;">
								请刷员工卡
							</td>
							<td colspan="2">
								<input type="text" name="barcode" id="barcode"/>
							</td>
							<td>
								<input type="submit" value="提交" class="input" />
							</td>
						</tr>
					</table>
				</form>
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							请假类型
						</th>
						<th>
							请假人
						</th>
						<th>
							请假人工号
						</th>
						<th>
							请假人所在部门
						</th>
						<th>
							请假开始时间
						</th>
						<th>
							请假结束时间
						</th>
						<th>
							请假天数
						</th>
						<th>
							请假小时数
						</th>
						<th>
							假事类型
						</th>
						<th>
							请假原因
						</th>
						<th>
							提交人
						</th>
						<th>
							提交人工号
						</th>
						<th>
							提交人所在部门
						</th>
						<th>
							审批状态
						</th>
						<th>
							出门时间
						</th>
						<th>
							返回时间
						</th>
					</tr>
					<s:iterator value="list" id="pageAskForLeave"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageAskForLeave.leaveType}
						</td>
						<td>
							${pageAskForLeave.leavePerson}
						</td>
						<td>
							${pageAskForLeave.leavePersonCode}
						</td>
						<td>
							${pageAskForLeave.leavePersonDept}
						</td>
						<td>
							${pageAskForLeave.leaveStartDate}
						</td>
						<td>
							${pageAskForLeave.leaveEndDate}
						</td>
						<td>
							${pageAskForLeave.leaveDays}
						</td>
						<td>
							${pageAskForLeave.leaveHours}
						</td>
						<td>
							${pageAskForLeave.leaveTypeOf}
						</td>
						<td>
							${pageAskForLeave.leaveReason}
						</td>
						<td>
							${pageAskForLeave.submitPerson}
						</td>
						<td>
							${pageAskForLeave.submitPersonCode}
						</td>
						<td>
							${pageAskForLeave.submitPersonDept}
						</td>
						<td>
							${pageAskForLeave.approvalStatus}
						</td>
						<td>
							${pageAskForLeave.exitTime}
						</td>
						<td>
							${pageAskForLeave.returnTime}
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="17" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="17" align="center" style="color: red">
						</s:else>
						</td>
					</tr>
				</table>
				<div align="left">
				</div>
			</div>
			<div align="left">
				<br>
			</div>
		</div>
		<div align="left">
			<%@include file="/util/foot.jsp"%>
			<center></center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
			<script type="text/javascript">
			$(function(){
			//$("#barcode").select();
			getFoucus();
		})
		function getFoucus(){
			$("#barcode").getFoucus();
		}
		onload=function(){
			var tag="${pageStatus}";
				if("ok"==tag){
					var mess="${successMessage}";
					alert(mess);
				}else if(){
					var mess="${errorMessage}";
					alert(mess);
				}else{
					
				}
			
			}
			</script>
		</div>
	</body>
</html>
