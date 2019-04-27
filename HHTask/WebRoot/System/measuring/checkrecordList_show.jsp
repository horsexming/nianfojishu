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
	<title></title>
	<link rel="stylesheet" type="text/css"
			href="<%=basePath%>/css/button.css" />
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="MeasuringAction_findALlCheckrecord.action" method="POST">
					<table class="table">
						<tr>
							<th align="right">
								本厂编号
							</th>
							<td>
								<input type="text" value="${checkrecord.measuring.measuring_no}" name="checkrecord.measuring.measuring_no">
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="${checkrecord.measuring.matetag}" name="checkrecord.measuring.matetag">
							</td>
							<th align="right">
								规格
							</th>
							<td>
								<input type="text" value="${checkrecord.measuring.format}" name="checkrecord.measuring.format">
							</td>
						</tr>
						<tr>
							<th align="right">
								校检人
							</th>
							<td>
								<input type="text" value="${checkrecord.empname}" name="checkrecord.empname">
							</td>
							<th align="right">
								报检人
							</th>
							<td>
								<input type="text" value="${checkrecord.reportpop}" name="checkrecord.reportpop">
							</td>
							<th align="right">
								校检时间
							</th>
							<td>
								<input type="text" value="${checkrecord.calibrationTime}" name="checkrecord.calibrationTime">
							</td>
						</tr>
					</table>
					<input type="submit" class="button0 blue" value="查询" />
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px" align="center">
						<th>
							序号
						</th>
						<th>
							本厂编号
						</th>
						<th>
							名称
						</th>
						<th>
							规格
						</th>
						<th>
							分类
						</th>
						<th>
							仓库
						</th>
						<th>
							校检人
						</th>
						<th>
							报检人
						</th>
						<th>
							校检时间
						</th>
						<th>
							检验状态
						</th>
						<th>
							检验报告
						</th>
					</tr>
					<s:iterator id="pageList" value="checkrecordList"
								status="statussdf">
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
									<s:property value="#statussdf.index+1" />
								</td>
							<td>
								${pageList.measuring.measuring_no}
							</td>
							<td>
								${pageList.measuring.matetag}
							</td>
							<td>
								${pageList.measuring.format}
							</td>
							<td>
								${pageList.measuring.parClass}
							</td>
							<td>
								${pageList.measuring.storehouse}
							</td>
							<td>
								${pageList.empname}
							</td>
							<td>
								${pageList.reportpop}
							</td>
							<td>
								${pageList.calibrationTime}
							</td>
							<td>
								${pageList.calibrationstate}
							</td>
							<td>
								<a href="FileViewAction.action?FilePath=/upload/file/Checkrecord/${pageList.fileName}">校验报告 </a>
							</td>
					</s:iterator>
					<tr>
								<td colspan="30" align="right">
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
	</body>
</html>
