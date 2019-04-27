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
			function deleteById(id){
				if(confirm('是否删除?')){
					$.ajax({
						type: "POST",
						url: "board_deleteTitle.action",
						data: "board.id=" + id,
						dataType :"json",
						success: function(json){
							alert(json.message);
							if(json.success){
								var board=json.data;
								window.location = 'board_findTitleAllByscrnId.action?board.scrnId='+board.scrnId;
							}
						}
					});
				}
			}
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
					<%--<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				--%>
				<a href="board_addTitlePage.action?board.scrnId=${board.scrnId}" style="color: #ffffff">添加标题add</a>
				</div>
			</div>
			
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号<br/>(Number)</th>
						<th>发布标题<br/>(Released title)</th>
						<th>屏幕名称<br/>(Screen Name)</th>
						<th>操作<br/>(Operation)</th>
					</tr>
					<s:iterator value="boards" status="st" id="t">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<th>${st.index +1 }</th>
							<td>${t.name}</td>
							<td>${t.scrnName}</td>
							<td>
								<%--<a href="board_addPage.actionboard.scrnId=${ms.scrnId}">添加</a>
								${sessionScope.Users.name}
								--%>
								<s:if test="#session.Users.id==#t.createUserId">
									<a href="board_updateTitlePage.action?board.id=${t.id}">修改add</a>
									<a href="javascript:void(0);" onclick="deleteById(${t.id});" >删除delete</a>
								<a target="_blank" href="board_addPage.action?board.parentId=${t.id}">添加发布内容add content</a>
								</s:if>
								<a target="_blank" href="board_findBoardAllByparentId.action?board.parentId=${t.id}">查看发布内容view content</a>
								<a target="_blank" href="boardReviewAction!getBoardReviewShowAllPage.action?board.scrnId=${t.scrnId}">查看屏幕view screen</a>
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
