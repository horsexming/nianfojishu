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
				<%--<input type="button" value="添加摄像头" onclick="add()"/>
				<a href="AccessEquipmentAction_toadd_Web.action?">添加摄像头"</a>
			--%></div> 
			<div align="center">
				<h3>
				门禁摄像头
				</h3>
						<%--<form action="AccessEquipmentAction_showList.action" method="post">
							<table class="table">
								<tr style="width: 100%">
									<th align="center" style="width: 25%">
										请输入设备编号：
									</th>
									<td align="center" style="width: 25%">
										<input type="text" name="samples.equipmentNum"
											/>
									</td>
									<td align="center" style="width: 50%">
										<input type="submit" value="查询"
											style="width: 100px; height: 25px;" />
									</td>
								</tr>
							</table>
						</form>
				--%><table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							摄像头名称
						</td>
						<td align="center">
							摄像头编号
						</td>
						<td align="center">
							摄像头应商
						</td>
						<td align="center">
							摄像头用途（进门/出门）
						</td>
						<td align="center">
							摄像头IP
						</td>
						<td align="center">
							摄像头端口
						</td>
						<td align="center">
							摄像头摆放位置
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
					<s:iterator value="accessWebcamList" id="samples"
						status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								style="height: 25px;" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td align="center">
							${samples.webcamName}
						</td>
						<td align="center">
							${samples.webcamNum}
						</td>
						<td align="center">
							${samples.webcamSupplier}
						</td>
						<td align="center">
							${samples.webcamOutIn}
						</td>
						<td align="center">
							${samples.webcamIP}
						</td>
						<td align="center">
							${samples.webcamPort}
						</td>
						<td align="center">
							${samples.webcamLocation}
						</td>
						<td align="center" colspan="2">
							<a href="AccessEquipmentAction_toupdate_Web.action?accessWebcam.id=${samples.id}&accessEquipment.id=${accessEquipment.id}">修改</a>
							<a href="AccessEquipmentAction_delete_web.action?accessWebcam.id=${samples.id}&accessEquipment.id=${accessEquipment.id}&cpage=${cpage}">删除</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="10" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
