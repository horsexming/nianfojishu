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
		<div id="gongneng">
			
			<div align="center">
				<div id="printDiv">
					<s:iterator value="machineList" id="pagemachine">
						<div id="printDiv2" align="center"
							style="width: 231px; ">
							<table
								style="font-size: 11px; border-collapse: collapse; width: 230px; margin-left: 5px; margin-top: 5px;">
								<tr>

									<th colspan="2" align="center" style="font-size: 14px;">										

										报&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码
									</th>
								</tr>
								<tr>
									<td align="left">
										工区:
									</td>
									<td align="left">
										工位:
									</td>
									<td align="left">
										设备编号:
									</td>
									
								</tr>
								<tr>
									<th align="left">
										${pagemachine.workArea}
									</th>
									<th align="left">
										${pagemachine.workPosition}
									</th>
									<th align="left">
										${pagemachine.no}
									</th>
								</tr>
								<tr>
									<td align="left">
										设备类型:
									</td>
									<td align="left">
										设备名称:
									</td>
									<td align="left">
										所在班组:
									</td>
								</tr>
								<tr>

									<th align="center" colspan="2">
										${pagemachine.name}
									</th>
									<th align="left">
										${pagemachine.classGroup}
									</th>
								</tr>
								<tr>
									<td align="left" colspan="3">
										状态:
										<font style="font-weight: bolder;">${pagemachine.status}</font>
									</td>

								</tr>
								<tr>
									<td colspan="3" align="left">
										<img
											src="barcode.action?msg=${pagemachine.barcode}&type=code128"
											style="height: 35px; width: 220px;">
									</td>
								</tr>
							</table>
						</div>
					</s:iterator>
				</div>
				<input type="button" value="打印"
					onclick="pagePrint('printDiv','yes')" />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
