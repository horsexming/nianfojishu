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
						url: "board_delete.action",
						data: "board.id=" + id,
						dataType :"json",
						success: function(json){
							alert(json.message);
							if(json.success){
								var board=json.data;
								window.location = 'board_findBoardAllByparentId.action?board.parentId='+board.parentId;
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
				<a href="board_addPage.action?board.parentId=${board.parentId}" style="color: #ffffff">添加内容</a>
				</div>
			</div>
			
			<div align="center">
				<table width="100%" border="0" style="border-collapse: collapse;" class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th>序号</th>
						<th>操作</th>
					</tr>
					<s:iterator value="boards" status="st" id="ms">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
							<th>${st.index +1 }</th>
							<td>
								<%--<a href="board_addPage.action?board.scrnId=${ms.scrnId}">添加</a>
								${sessionScope.Users.name}
								--%>
								<s:if test="#session.Users.id==#ms.createUserId">
									<a href="board_updatePage.action?board.id=${ms.id}">修改</a>
									<a href="javascript:void(0);" onclick="deleteById(${ms.id});" >删除</a>
								</s:if>
								<a href="boardReviewAction!getBoardReviewPage.action?boardReview.boardId=${ms.id}">评论回复</a>
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
