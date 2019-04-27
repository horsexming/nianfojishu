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
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
				</div>
			</div>
			
			<div align="center">
				<table class="table">
					<tr  bgcolor="#c0dcf2" height="50px">
						<td align="center">序号<br/>(Number)</td>
						<td align="center">屏幕名称<br/>(Screen Name)</td>
						<td align="center">排序号<br/>(Sort numbers)</td>
						<td align="center">创建时间<br/>(Creation time)</td>
						<td align="center">操作<br/>(Operation)</td>
					</tr>
					<s:iterator id="s" value="scrnList" status="st">
						<tr>
							<td>${st.index + 1}</td>
							<td>${s.name}</td>
							<td>${s.sort}</td>
							<td><s:date name="createDate" format="yyyy-MM-dd HH:mm"/></td>
							<td>
								<a target="_blank" href="scrnAction!getScrnUpdatePage.action?scrn.id=${s.id}">修改</a>
								<a target="_blank" onclick="return window.confirm('确认要删除该屏幕记录?')" href="scrnAction!deleteScrn.action?scrn.id=${s.id}">删除(delete)</a>
								<a target="_blank" href="board_addTitlePage.action?board.scrnId=${s.id}">添加发布标题(add)</a>
								<a target="_blank" href="board_findTitleAllByscrnId.action?board.scrnId=${s.id}">查看发布标题(view title)</a>
								<a target="_blank" href="boardReviewAction!getBoardReviewShowAllPage.action?board.scrnId=${s.id}">查看屏幕(view Screen)</a>
							</td>
						</tr>
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
							</td>
						</s:if>
						<s:else>
							<td colspan="10" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
