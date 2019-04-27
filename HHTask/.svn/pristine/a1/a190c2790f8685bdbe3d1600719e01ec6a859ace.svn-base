<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<table border="1" width="90px" class="table">
					<tr>
						<td colspan="20" align="center"
							style="font-family: 微软雅黑; font-weight: bold;">
							明细说明单
						</td>
					</tr>
					<tr>
						<th>
							工区
						</th>
						<td>
							<input type="hidden" value="${maintenance.id}"
								name="maintenance.id" />
							${maintenance.workArea}
						</td>

						<th>
							工位
						</th>
						<td>
							${maintenance.workPosition}
						</td>
						<th>
							设备编号
						</th>
						<td>
							${maintenance.no}
						</td>
					</tr>
					<tr>
						<th>
							设备类型
						</th>
						<td>
							${maintenance.type}
						</td>

						<th>
							设备名称
						</th>

						<td>
							${maintenance.name}
						</td>
						<th>
							所在班组
						</th>
						<td>
							${maintenance.classGroup}
						</td>
					</tr>
					<tr>
						<th>
							报修时间
						</th>
						<td>
							<fmt:formatDate value="${maintenance.alarmTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<th>
							故障状况
						</th>
						<td>
							${maintenance.faultDetail}
						</td>
						<th>
							修复说明
						</th>
						<td>
							${maintenance.faultReason}
						</td>
					</tr>
					<tr>
						<th colspan="1" align="center">
							维修条码：
						</th>
						<td>
							${maintenance.barcode}
						</td>

					</tr>
					<tr>
						<th colspan="6" align="center">
							更换零件信息
						</th>
					</tr>
				</table>
				<table class="table">
					<s:iterator value="partslist" id="parts" status="pagePartslist">
						<tr>
							<th>
								${pagePartslist.index+1 }
							</th>
							<th>
								零件名称:
							</th>
							<td>
								${parts.partName}
							</td>
							<th>
								零件规格:
							</th>
							<td>
								${parts.pictureNo}
							</td>
							<th>
								零件数量:
							</th>
							<td>
								${parts.num}
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
