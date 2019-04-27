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
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<h3>
				内部员工车辆信息查询
				</h3>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							停车场名称
						</td>
						<td align="center">
							车位编号
						</td>
						<td align="center">
							车位类型
						</td>
						<td align="center">
							车位状态
						</td>
						<td align="center">
							对应设备IP
						</td>
						<td align="center">
							对应设备端口
						</td>
						<td align="center">
							当前使用人
						</td>
						<td align="center">
							当前使用人工号
						</td>
						<td align="center">
							VIP姓名
						</td>
						<td align="center" colspan="2">
							操作类型
						</td>
					</tr>
				<s:iterator id="pageParkSpace" value="parkSpaceList"
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
							${pageParkSpace.parkingLot}
						</td>
						<td align="center">
							${pageParkSpace.parkNum}
						</td>
						<td align="center">
							${pageParkSpace.parkType}
						</td>
						<td align="center">
							${pageParkSpace.parkStatus}
						</td>
						<td align="center">
							${pageParkSpace.parkIP}
						</td>
						<td align="center">
							${pageParkSpace.parkPort}
						</td>
						<td align="center">
							${pageParkSpace.usePeople}
						</td>
						<td align="center">
							${pageParkSpace.usePeopleCode}
						</td>
						<td align="center">
							${pageParkSpace.vipName}
						</td>
						<td align="center" colspan="2">
							<a href="ParkSpaceAction_toupdate.action?parkSpace.id=${pageParkSpace.id}&tag=${tag}">修改</a>
							<a href="">删除</a>
							<a href="ParkSpaceAction_showList_Infor.action?parkSpaceUseInfor.parkId=${pageParkSpace.id}">查看使用记录</a>
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="13" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="13" align="center" style="color: red">
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
function openOrClose(id, status) {
	window.location.href = "ParkSpaceAction_openPark.action?parkSpace.id=" + id
			+ "&infor=" + status;
}
</script>
	</body>
</html>
