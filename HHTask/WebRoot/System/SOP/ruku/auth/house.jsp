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
		<script type="text/javascript">
function deleteUser(id) {
	if (!confirm('确定将此人的权限删除?')) {
		return;
	}
	$.ajax( {
		type : "POST",
		url : "WareHouse!delete.action",
		data : "house.id=" + id,
		dataType : "json",
		success : function(msg) {
			alert(msg.message);
			if (msg.success) {
				location.reload();
			}
		}
	});
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="WareHouse_addInput.action" style="color: #ffffff">添加仓库</a>
				</div>
			</div>

			<div align="center">
				<table width="100%" border="0"
					style="border-collapse: collapse; width: 70%" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							编号
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="wareHouses" id="d" status="st">
						<tr>
							<s:if test="#st.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:if test="#st.index%2==0">
									<font color="red"> <s:property value="#st.index+1" /> </font>
								</s:if>
								<s:else>
									<s:property value="#st.index+1" />
								</s:else>
							</td>
							<td>
								${d.name}
							</td>
							<td>
								${d.code}
							</td>
							<td>
								<a href="javascript:void(0)" onclick="deleteUser(${d.id})">删除</a>
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
