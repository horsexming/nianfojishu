<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<h3>
				检查类型表
				</h3>
					<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
							序号
						</td>
						<td align="center">
							名称
						</td>
						<td align="center">
							类型
						</td>
						<td align="center">
							分数
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="checkTypeList" id="list"
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
							${list.name}
						</td>
						<td align="center">
							${list.type}
						</td>
						<td align="center">
							${list.maxScore}
						</td>
						<td colspan="2">
						<input type="button" value="修改"
								style="width: 60px; height: 30px;"
								onclick="update(${list.id},'${cpage}')" />
							<input type="button" value="删除"
								style="width: 60px; height: 30px;"
								onclick="todelete(${list.id},'${cpage}')" />
						</td>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="12" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="12" align="center" style="color: red">
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
function update(id,cpage) {
	window.location.href = "checkTypeAction_toAdd.action?checkType.id=" + id+"&cpage="+cpage;
}
function todelete(id,cpage) {
	window.location.href = "checkTypeAction_delete.action?checkType.id="+ id+"&cpage="+cpage;
}
</script>
	</body>
</html>
