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
				<div align="center"  id="image" class="my_show">
				<table class="table">
					<tr>
					<td colspan="6" align="center">
					<font size='5' color="#DD22B8"><B>${leaveInform.fuck1}</B></font></td>
					</tr>
					<tr>
						<td align="center">工号</td>
						<td align="center"> ${leaveInform.code}</td>
						<td align="center">卡号</td>
						<td align="center">${users.cardId}</td>
						<td align="center">姓名</td>
						<td align="center">
							 ${leaveInform.username}</td>
					</tr>
					<tr>
						<td align="center">部门</td>
						<td align="center">${leaveInform.dept}
						</td>
						<td align="center">岗位工资</td>
						<td align="center">${leaveInform.postsalary}</td>
						<td align="center">保密津贴</td>
						<td align="center">${leaveInform.secrecysalary}</td>
					</tr>
					<tr>
						<td align="center">补贴</td>
						<td align="center">${leaveInform.offset}</td>
						<td align="center">技能工资</td>
						<td align="center">${leaveInform.performancesalary}</td>
						<td align="center">特殊补贴</td>
						<td align="center">${leaveInform.heji}</td>
					</tr>
					<tr>
						<td align="center">绩效考核工资</td>
						<td align="center">${leaveInform.achievement}</td>
						<td align="center">公积金基数</td>
						<td align="center">${leaveInform.gongji}</td>
						<td align="center">社保基数</td>
						<td align="center">${leaveInform.shebao}</td>
					</tr>
					<tr>
						<td align="center">住房费</td>
						<td align="center">${leaveInform.yiliao}</td>
						<td align="center">是否补差</td>
						<td align="center">${leaveInform.fintime}
						</td>
						<td align="center">起薪日期</td>
						<td align="center">${leaveInform.starttime}</td>
					</tr>
					<tr>
						<td align="center">养老保险</td>
						<td align="center">${leaveInform.endtime}
						</td>
						<td align="center">医疗保险</td>
						<td align="center">${leaveInform.performance}
						</td>
						<td align="center">失业保险</td>
						<td align="center">
							${leaveInform.fuck2}
						</td>
					</tr>
					<tr>
						<td align="center">本地或外地</td>
						<td align="center">
							${leaveInform.fuck3}
						</td>
						<td align="center">户口类型</td>
						<td align="center">${leaveInform.fuck4}
						</td>
						<td align="center">保险类型</td>
						<td align="center">${leaveInform.fuck5}
						</td>
					</tr>
					<tr>
						<td align="center">说明</td>
						<td colspan="5" align="center">${leaveInform.shuoming}</td>
					</tr>
				
				</table>
				</div>
				<table>
				<tr>
					<td align="center"><input type="button" onclick="pagePrint('image')" class="input" 
							id="print"
							value="打      印"
									style="width: 80px; height: 50px;" /></td>
					<td></td>
				</tr>
				</table>
				
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		
	</body>
</html>
