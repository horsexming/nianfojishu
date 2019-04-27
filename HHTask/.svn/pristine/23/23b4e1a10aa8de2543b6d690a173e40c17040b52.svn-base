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

				<table width="100%" border="0" style="border-collapse: collapse;"
					class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							标题
						</th>
						<th align="center">
							提交时间
						</th>
						<th align="center">
							提交人员
						</th>
						<th align="center">
							工号
						</th>
						<th align="center">
							当前状态
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="notice" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout=outBgcolor(this,'');>
						</s:else>
						<td>
							<s:if test="#pageStatus.index%2==1">
							</s:if>
							<s:else>
								<font color="#c0dcf2"></font>
							</s:else>
							<s:property value="#pageStatus.index+1" />

						</td>
						<td>
							${notice.title}
						</td>
						<td>
							${notice.time}
						</td>
						<td>
							${notice.personname}
						</td>
						<td>
							${notice.pid}
						</td>
						<td>
							${notice.status}
						</td>
						<td>
							<a href="NoticeAction!alter.action?notice.id=${notice.id}"> <s:if
									test='%{"隐藏".equals(#notice.status)}'>
									<span style="color: red">显示</span>
								</s:if> <s:else>
								隐藏
							</s:else> </a>/
							<a href="NoticeAction!look.action?notice.id=${notice.id}">查看&修改</a>/
							<a href="NoticeAction!delete.action?notice.id=${notice.id}"
								onclick="return window.confirm('确定要删除该条公告信息吗?')">删除</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="11" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
						</s:else>
						</td>
					</tr>
				</table>

			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
