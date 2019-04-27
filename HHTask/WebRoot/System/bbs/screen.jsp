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

function deleteById(id) {
	if (confirm("确定删除该记录？")) {
		$.ajax( {
			type : "POST",
			url : "screen_delete.action?screen.id=" + id,
			dataType : "json",
			success : function(json) {
				if (!json.success) {
					alert("删除失败");
				} else {
					location.reload();
				}
			}
		});
	}

}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							屏幕名称
						</th>
						<th>
							屏幕简介
						</th>
						<th>
							创建时间
						</th>
						<th>
							修改时间
						</th>
						<th>
							视频地址
						</th>
						<th>
							工位
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="screens" status="st" id="ms">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<th>
							${st.index +1 }
						</th>
						<td>
							${ms.name}
						</td>
						<td>
							${ms.description}
						</td>
						<td>
							${ms.createDateTime}
						</td>
						<td>
							${ms.modifyDateTime}
						</td>
						<td>
							${ms.screenUrl}
						</td>
						<td>
							<a href="screen_getWorkStation.action?screen.id=${ms.id}">查看</a>
						</td>
						<td>
							<%--							<a href="screen_printScreen2.action?screen.id=${ms.id}"--%>
							<%--								target="ADSFADS">屏幕1</a>--%>
							<%--							<a href="screen_printScreen3.action?screen.id=${ms.id}"--%>
							<%--								target="ADSFADS">屏幕2</a>--%>
							<a
								href="ScreenContentAction_showScreen.action?screen.id=${ms.id}"
								target="ADSFADS">查看屏幕</a>
							<br>
							<br>
							<a href="screen_showscreencontent.action?screen.id=${ms.id}">绑定屏幕内容</a>
							<a href="screenFiles_showProcess.action?screen.id=${ms.id}">查看工序</a>
							<%--							<a href="ScreenContentAction_showContent.action?screen.id=${ms.id}">屏幕内容</a>--%>

							<a href="screen_editPage.action?screen.id=${ms.id}">修改</a>
							<a href="javascript:void(0);" onclick="deleteById(${ms.id});">删除</a>


							<%--<a href="screen_printScreen.action?screen.id=${ms.id}" target="ADSFADS">查看屏幕(view Screen)</a>
							target="ADSFADS"	--%>
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
