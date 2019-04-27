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
$(function() {
	var insScope = '${tt}';
	if (insScope != "") {
		$("#updateDiv").show();
	}
})
</script>
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
					<a href="Templatem_addInput.action" style="color: #ffffff">添加</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div id="updateDiv" style="display: none;">
				<form action="OsTemplate_UpdateOsScope.action?status=xj" method="post">
					<input type="hidden" name="tt.id" value="${tt.id}">
					<input type="hidden" name="t.id" value="${t.id}">
					<table>
						<tr>
							<td>
								结果类型
							</td>
							<td>
								<select name="tt.type" style="width: 150px">
									<option value="${tt.type}">
										${tt.type}
									</option>
									<option>
										手动填写
									</option>
									<option>
										是否合格
									</option>
									<option>
										有无
									</option>
								</select>
							</td>
							<td>
								检查条目
							</td>
							<td>
								<input name="tt.content" value="${tt.content}" />
								<input type="submit" value="修改111">
							</td>
						</tr>
					</table>
				</form>
			</div>
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>
							序号
						</th>
						<th>
							类型
						</th>
						<th>
							巡检内容
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator value="sc" status="st" id="ll">
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
							${ll.type}
						</td>
						<td>
							${ll.content}
						</td>
						<td>
							<a href="OsTemplate_toUpdateOsScope.action?tt.id=${ll.id}&status=xj&t.id=${t.id}">修改</a>
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
