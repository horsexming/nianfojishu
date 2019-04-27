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
		<style type="text/css">
.subTable {
	font-size: 1px;
	text-align: center;
	border-collapse: collapse;
	width: 100%;
	border-style: hidden;
}

.subTable td {
	padding: 0;
	margin: 0;
	border: 0;
	border-width: 0px;
}
</style>
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
			
			<div align="center" style="width: 100%;">

				<s:if test='"调职通知"==leaveInform.fuck1'>
					<div align="center" id="image" class="my_show">

						<table align="center" class="table" style="width: 100%;">
							<tr>
								<td align="center" colspan="7">
									<div align="right">
										（2014）公司字 第 2 号
									</div>
									<div align="center">
										<font size='4'> <B>${companyInfo.name}人事通知单(内部调令)</B> </font>

									</div>
								</td>
							</tr>
							<tr>

								<td style="border: 0;">
									主送
								</td>
								<td style="border: 0;">
									抄
								</td>
								<td style="border: 0;"></td>
								<td style="border: 0;"></td>
								<td style="border: 0;">
									存档
								</td>

							</tr>
							<tr>
								<td align="center" colspan="5">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 经研究决定下列人员计 &nbsp;&nbsp; 壹
									&nbsp;&nbsp; 名 &nbsp;&nbsp;&nbsp;
									，因工作需要变职，希&nbsp;&nbsp;于&nbsp;&nbsp;月&nbsp;&nbsp;日前到新
									岗位报道，并按新确定职务分配工作。
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</br>
									</br>
									</br>
									</br>
									</br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									此通知
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									公章 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									经办人：&nbsp;&nbsp;${sessionScope.Users.name}&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									姓 名
								</td>
								<td align="center">
									原 职
								</td>
								<td align="center">
									新 职
								</td>
								<td align="center">
									新单位起薪
								</td>
								<td align="center">
									试 岗 期
								</td>
							</tr>
							<tr>
								<td align="center">
									${leaveInform.username}
								</td>
								<td align="center">
									${leaveInform.fuck3}
								</td>
								<td align="center">
									${leaveInform.fuck4}
								</td>
								<td align="center">
									${leaveInform.starttime}
								</td>
								<td align="center">
									${leaveInform.shuoming}
								</td>
							</tr>
						</table>
					</div>
					<div>
						<table width="165px" height="50px">
							<tr>
								<td>
									<input type="button" onclick="pagePrint('image')" class="input"
										id="print" value="打      印" style="width: 80px; height: 50px;" />
								</td>
								<td>
									<input type="button" onclick="print1()" value="更多打印"
										style="width: 80px; height: 50px;" />
								</td>

							</tr>
						</table>
					</div>
				</s:if>
				<s:elseif test='"入职通知"==leaveInform.fuck1'>
					<div align="center" id="image" class="my_show">

						<table align="center" class="table" style="width: 100%;">
							<tr>
								<td align="center" colspan="7">
									<div align="right">
										（2014）公司字 第 2 号
									</div>
									<div align="center">
										<font size='4'> <B>${companyInfo.name}人事通知单（${leaveInform.reason})</B>
										</font>

									</div>
								</td>
							</tr>
							<tr>
								<td style="border: 0;">
									主送
								</td>
								<td style="border: 0;">
									抄
								</td>
								<td style="border: 0;"></td>
								<td style="border: 0;"></td>
								<td style="border: 0;"></td>
								<td style="border: 0;"></td>
								<td style="border: 0;">
									存档
								</td>
							</tr>
							<tr>
								<td align="center" colspan="7">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 分配下列人员计 &nbsp;&nbsp; 壹
									&nbsp;&nbsp; 名 &nbsp;&nbsp;&nbsp; ，去你部门报道，希按确定职务分配工作。
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</br>
									</br>
									</br>
									</br>
									</br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									此通知
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									公章 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									经办人：&nbsp;&nbsp;${sessionScope.Users.name}&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td align="center">
									姓 名
								</td>
								<td align="center">
									出生年月
								</td>
								<td align="center">
									分配职务
								</td>
								<td align="center">
									月标准工资
								</td>
								<td align="center">
									起薪日期
								</td>
								<td align="center">
									合同编号
								</td>
								<td align="center">
									试用期
								</td>
							</tr>
							<tr>
								<td align="center">
									${leaveInform.username}
								</td>
								<td align="center">
									${users.bothday}
								</td>
								<td align="center">
									${leaveInform.fuck3}
								</td>
								<td align="center">
									${leaveInform.shuoming}
								</td>
								<td align="center">
									${leaveInform.starttime}
								</td>
								<td align="center">
									${leaveInform.fuck5}
								</td>
								<td align="center">
									${leaveInform.fuck4}
								</td>
							</tr>
						</table>
					</div>
					<div>
						<table width="165px" height="50px">
							<tr>
								<td>
									<input type="button" onclick="pagePrint('image')" class="input"
										id="print" value="打      印" style="width: 80px; height: 50px;" />
								</td>
								<td>
									<input type="button" onclick="print2()" value="更多打印"
										style="width: 80px; height: 50px;" />
								</td>

							</tr>
						</table>
					</div>
				</s:elseif>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function print2() {
	window.location.href = "InformAction!findru.action";
}
function print1() {
	window.location.href = "InformAction!findti.action";
}
</script>
	</body>
</html>
