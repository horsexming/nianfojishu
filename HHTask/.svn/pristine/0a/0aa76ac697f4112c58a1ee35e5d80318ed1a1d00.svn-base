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
						<td colspan="7" align="center">
							编号：${leaveInform.time}	</br>
							离职工资结算单				</br>
							收文：财务 发文：人力资源								
						</td>
					</tr>
					<tr>
						<td colspan="7" align="center">事由：离职结算                                          							
						</td>
					</tr>
					<tr>
						<td align="center">
							姓名
						</td>
						<td align="center"> 
							离职原因
						</td>
						<td align="center">
							离职申请单编号
						</td>
						<td align="center">
							入职时间
						</td>
						<td align="center">
							离职时间
						</td align="center">
						<td>
							员工卡号
						</td>
						<td align="center">
							离职交接单编号
						</td>
					</tr>
					<tr>
						<td align="center">${leaveInform.username}</td>
						<td align="center">${leaveInform.reason}</td>
						<td align="center">${leaveInform.time}</td>
						<td align="center">${ leaveInform.starttime}</td>
						<td align="center">${ leaveInform.fintime}</td>
						<td align="center">${users.cardId}</td>
						<td align="center">${leaveInform.time}</td>
					</tr>
					<tr>
						<td colspan="2">工资结算截止日期	</td>
						<td align="center">    ${leaveInform.month }     月份计薪日</td>
						<td align="center">     ${leaveInform.month }     月份应发岗位工资	</td>
						<td align="center">      ${leaveInform.month }    2014年2月份应发保密津贴</td>
						<td align="center">       ${leaveInform.month }   2014年2月份绩效工资基数</td>
						<td align="center">离职补偿	</td>
					</tr>
					<tr>
						<td colspan="2">${leaveInform.endtime}</td>
						<td align="center">${leaveInform.tiantime}</td>
						<td align="center">${leaveInform.postsalary}</td>
						<td align="center">${leaveInform.secrecysalary}</td>
						<td align="center">${leaveInform.performancesalary}</td>
						<td align="center">${leaveInform.offset}</td>
					</tr>
					<tr>
						
						<td colspan="2" align="center">绩效考核得分	</td>
						<td align="center">  ${leaveInform.month }        月份绩效工资</td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center">合计应发</td>
					</tr>
					<tr>
						<td colspan="2">${leaveInform.performance } </td>
						<td align="center">${leaveInform.achievement } </td>
						<td></td>
						<td></td>
						<td></td>
						<td align="center">${leaveInform.heji }</td>
					</tr>
					<tr>
						<td colspan="2" align="center">社保统筹扣款	</td>
						<td align="center">医疗保险</td>
						<td align="center">失业保险	</td>
						<td align="center">公积金</td>
						<td></td>
						<td align="center">实际支付（实发）</td>
					</tr>
					<tr>
						<td colspan="2" align="center">${leaveInform.shebao }</td>
						<td align="center">${leaveInform.yiliao }</td>
						<td align="center">${leaveInform.shiye }</td>
						<td align="center">${leaveInform.gongji }</td>
						<td></td>
						<td align="center">${leaveInform.shiji }</td>
					</tr>
					<tr>
						<td>说明</td>
						<td colspan="7" align="center">${leaveInform.shuoming }</td>
					</tr>
					
				</table>
				</div>
				<table>
				<tr>
					<td><input type="button" onclick="pagePrint('image')" class="input" 
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
