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
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">


				<table border="1" width="100%" class="table">
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							量具明细说明单
						</td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td>
							${measuring.matetag}
						</td>
						<th>
							仓库
						</th>
						<td>
							${measuring.storehouse}
						</td>
						<th>
							分类
						</th>
						<td>
							${measuring.parClass}
						</td>
					</tr>
					<tr>
						<th>
							位置
						</th>
						<td>
							${measuring.place}
						</td>

						<th>
							周期
						</th>
						<td>
							${measuring.period}
						</td>
						<th>
							状态
						</th>
						<td>
							${measuring.calibrationstate}
						</td>
					</tr>
					<tr>
						<th>
							校检时间
						</th>
						<td>
							${measuring.calibrationTime}
						</td>
						<th>
							上次校准时间
						</th>
						<td>
							${measuring.lastcalibrationTime}
						</td>
						<th>
								库存
						</th>
						<td>
							${measuring.curAmount}
						</td>
					</tr>
					<br/>
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							校检明细
						</td>
					</tr>
					<!-- 报废明细信息 -->
					<s:if test="msg=='报废'">
							<tr>
							<th>
								名称
							</th>
							<th>
								规格
							</th>
						<th>
							姓名
						</th>
						<th>
							部门
						</th>
						<th>
							状态
						</th>
						<th>
							时间
						</th>
					
					</tr>
						<s:iterator id="pageList" value="scrapList" >
					<tr>
					<td align="center">
							${pageList.matetag}
						</td>
					<td align="center">
							${pageList.format}
						</td>
						<td align="center">
							${pageList.username}
						</td>
						<td  align="center">
							${pageList.dept}
						</td  align="center">
						<td  align="center">
							报废
						</td  align="center">
						<td  align="center">
							${pageList.badDate}
						</td  align="center">
						</tr>
					</s:iterator>
					
					</s:if>
					<!-- 正常明细信息 -->
					<s:else>
									<tr>
								<th>
									工号
								</th>
								<th>
									校检人
								</th>
								<th>
									报检人
								</th>
								<th>
									状态
								</th>
								<th>
									校检时间
								</th>
									<th>
								校检完成时间
								</th>
									<th>
								检验报告
								</th>
							</tr>
								<s:iterator id="pageList" value="checkrecordList" >
							<tr>
								<td align="center">
									${pageList.empno}
								</td>
								<td  align="center">
									${pageList.empname}
								</td  align="center">
								<td align="center">
									${pageList.reportpop}
								</td  align="center">
								<td  align="center">
									${pageList.calibrationstate}
								</td>
								<td  align="center">
									${pageList.reportdate}
								</td>
								<td  align="center">
									${pageList.calibrationTime}
								</td>
								<td align="center">
<%--								<a href="<%=basePath%>/upload/file/Checkrecord/${pageList.fileName}">校验报告 </a>--%>
								<a href="FileViewAction.action?FilePath=/upload/file/Checkrecord/${pageList.fileName}">校验报告 </a>
								</td>
							</tr>
							</s:iterator>
					</s:else>
					
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
