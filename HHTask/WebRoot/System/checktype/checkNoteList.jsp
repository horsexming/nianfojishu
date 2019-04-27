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
				检查记录表
				</h3>
					<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
					<td align="center">
							序号
						</td>
						<td align="center">
							责任人
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							出错内容
						</td>
						<td align="center">
							扣除分数
						</td>
						<td align="center">
							查看图片
						</td>
						<td align="center">
							描述
						</td>
						<td align="center">
							状态
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator value="checkNoteList" id="list"
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
							${list.firstPerson}
						</td>
						<td align="center">
							${list.depert}
						</td>
						<td align="center">
							${list.checkType.type}----${list.checkType.name}
						</td>
						<td align="center">
							${list.checkType.maxScore}
						</td>
						<td align="center">
							<img alt="${list.checkType.type}----${list.checkType.name}" src="<%=basePath%>upload/file/sixJian/${list.url}" style="width:121px; height:162px">
						</td>
						<td align="center">
							${list.describe}
						</td>
						<td align="center">
							${list.status}
						</td>
						<td colspan="2">
						<input type="button" value="扣分"
								style="width: 60px; height: 30px;"
								onclick="update(${list.id},'${cpage}')" />
						<input type="button" value="否决"
								style="width: 60px; height: 30px;"
								onclick="update1(${list.id},'${cpage}')" />
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
			window.location.href = "CheckNoteAction_update.action?checkNote.id=" + id+"&cpage="+cpage+"&pass="+"yes";
		}
		function update1(id,cpage) {
			window.location.href = "CheckNoteAction_update.action?checkNote.id=" + id+"&cpage="+cpage+"&pass="+"no";
		}
		</script>
	</body>
</html>
