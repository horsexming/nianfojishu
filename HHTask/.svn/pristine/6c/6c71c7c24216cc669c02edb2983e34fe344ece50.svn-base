<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<body bgcolor="#ffffff" onload="chagePrint()">
		<center>
			<%@include file="/util/sonTop.jsp"%>
			<div id="gongneng"
				style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
				<div id="xitong"
					style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
					align="left">
					<div style="float: left; width: 50%" align="left">
						
					</div>
					<div style="float: left; width: 48%" align="right">
					</div>
				</div>
				
				<div id="allPrintDiv">
					<div id="printDiv" style="border: solid 1px #999999;">
						<div style="padding: 40 60 0 60px; width: 100%" align="center">
							<table width="100%" border="0">
								<tr>
									<th align="right" colspan="2"
										style="font-family: 华文行楷; font-size: 25px;">
										薪资调整通知单
									</th>
									<td align="center" colspan="2">
										<img align="middle" alt=""
											src="barcode.action?msg=${contractNumber}&type=code128"
											style="height: 40px; width: 120px;">
									</td>
								</tr>
								<tr>
									<td colspan="2" align="right">
										&nbsp;
									</td>
									<td colspan="2" align="center">
										合同编号:${contract.contractNumber}
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<th colspan="4" align="left">
										${onWorkWs.dept}部门的
										<input class="horizontalLine" style="width: 80px"
											value="&nbsp;&nbsp;&nbsp;&nbsp;${onWorkWs.userName}" />
										先生/女士
										<br />
										<br />
									</th>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;根据您的实际工作情况，经公司研究，决定对您的薪资情况做出调整:
									</td>
								</tr>
								<tr>
									<td colspan="4" align="right">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="4" style="font-weight: bold">
										一、协议内容:
									</td>
								</tr>
								<s:iterator value="provisionList" id="pageProvision"
									status="pageStatus">
									<tr>
										<td colspan="4" align="left" style="padding-left: 20px;">
											<s:property value="#pageStatus.index+1" />
											、&nbsp; ${pageProvision.content}
										</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="4" align="left">
										&nbsp;
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="4" style="font-weight: bold">
										二、薪资调整情况:
									</td>
								</tr>
								<tr>
									<td colspan="1" align="center">
										原薪资情况:
									</td>
									<td colspan="3" align="center">
										调整后薪资情况：
									</td>
								</tr>
								<tr>
									<td align="right">
										岗位基本工资:
									</td>
									<td>
										<input class="horizontalLine" id="gangweigongzi"
											name="wageStandard.gangweigongzi"
											value="${wageStandard.gangweigongzi}" style="width: 100px" />
										元
									</td>
									<td align="right">
										岗位基本工资:
									</td>
									<td>
										<input class="horizontalLine" id="gangweigongzi"
											name="onWorkWs.gangweigongzi"
											value="${onWorkWs.gangweigongzi}" style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<td align="right">
										职务保密津贴:
									</td>
									<td>
										<input class="horizontalLine" id="baomijintie"
											name="wageStandard.baomijintie"
											value="${wageStandard.baomijintie}" style="width: 100px" />
										元
									</td>
									<td align="right">
										职务保密津贴:
									</td>
									<td>
										<input class="horizontalLine" id="baomijintie"
											name="onWorkWs.baomijintie" value="${onWorkWs.baomijintie}"
											style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<td align="right">
										岗位技能工资:
									</td>
									<td>
										<input class="horizontalLine" id="jinenggongzi"
											name="wageStandard.jinenggongzi"
											value="${wageStandard.jinenggongzi}" style="width: 100px" />
										元
									</td>
									<td align="right">
										岗位技能工资:
									</td>
									<td>
										<input class="horizontalLine" id="jinenggongzi"
											name="onWorkWs.jinenggongzi" value="${onWorkWs.jinenggongzi}"
											style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<td align="right">
										绩效考核工资:
									</td>
									<td>
										0~
										<input class="horizontalLine" id="jixiaokaohegongzi"
											name="wageStandard.jixiaokaohegongzi"
											value="${wageStandard.jixiaokaohegongzi}"
											style="width: 100px" />
										元
									</td>
									<td align="right">
										绩效考核工资:
									</td>
									<td>
										0~
										<input class="horizontalLine" id="jixiaokaohegongzi"
											name="onWorkWs.jixiaokaohegongzi"
											value="${onWorkWs.jixiaokaohegongzi}" style="width: 100px" />
										元
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收到本通知单时调整后薪资开始执行
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;特此通知
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<br />
										<br />
										<br />
									</td>
								</tr>
								<tr>
									<td colspan="2"></td>
									<th align="center" colspan="2">
										本人签字:________________
										<br />
										<br />
									</th>
								</tr>
								<tr>
									<td colspan="2"></td>
									<th align="center" colspan="2">
										公司盖章:________________
										<br />
										<br />
									</th>
								</tr>
								<tr>
									<td colspan="2"></td>
									<th align="center" colspan="2">
										____________年_____月_____日
										<br />
										<br />
									</th>
								</tr>
							</table>
						</div>
					</div>
					<br />
					<br />
					<input type="button" value="打印" onclick="pagePrint('printDiv')"
						style="width: 80px; height: 50px;">
				</div>

				<br>
			</div>
			<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
