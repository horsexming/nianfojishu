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
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/Sortable/Sortable.js">
</script>
		<style>
.list {
	margin: 2px auto;
	width: 100%;
}

.list li.columnhead {
	background: #c0dcf2;
	font-size: 14px;
	font-weight: bold;
	border: 0 solid silver;
	border-width: 0 0 1px 1px;
}

.list li,.list li.columnhead {
	cursor: Move;
	border: 0 solid silver;
	border-width: 1px 1px 0 0;
	width: 24.5%;
	line-height: 30px;
	height: 30px;
	text-align: center;
	float: left;
	margin: 0 0 1px 1px;
	list-style: none;
}
</style>
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
				</div>
			</div>
			<div align="center">

				<form action="ScreenContentAction_add.action" method="post" style="">
					<br>
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								屏幕内容名称:
							</td>
							<td>
								<input type="text" id="name" name="screencontent.name" />
							</td>
							<td align="right">
								添加屏幕内容:
							</td>
							<td>
								<input type="text" id="Url" name="screencontent.screenUrl" />
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<input type="submit" value="添加submit" onclick="reload()"
									style="width: 100px; height: 50px;" />
								<input type="reset" value="重置reset"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>

<%--			<div align="center">--%>
<%--				<h3>--%>
<%--					屏幕内容--%>
<%--					<br />--%>
<%--					(Screen Content)--%>
<%--				</h3>--%>
<%--				<form id="myform">--%>
<%--					<input type="hidden" name="screen.id" value="${screen.id}" />--%>
<%--					<table class="table" id="items2">--%>
<%--						<tr bgcolor="#c0dcf2" height="50px">--%>
<%----%>
<%--							<th align="center">--%>
<%--								序号--%>
<%--							</th>--%>
<%--							<th align="center">--%>
<%--								名称--%>
<%--							</th>--%>
<%--							<th align="center">--%>
<%--								地址--%>
<%--							</th>--%>
<%--							<th align="center">--%>
<%--								操作--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--						<s:iterator value="screencontents" id="pageList"--%>
<%--							status="pageIndex">--%>
<%----%>
<%--							<s:if test="#pageIndex.index%2==1">--%>
<%--								<tr align="center" bgcolor="#e6f3fb"--%>
<%--									onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'#e6f3fb')">--%>
<%--							</s:if>--%>
<%--							<s:else>--%>
<%--								<tr align="center" onmouseover="chageBgcolor(this)"--%>
<%--									onmouseout="outBgcolor(this,'')">--%>
<%--							</s:else>--%>
<%--							<td align="center">--%>
<%--								${pageIndex.index+1}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${pageList.name}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								${pageList.screenUrl}--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<a href="javascript:void(0);"--%>
<%--									onclick="deleteById(${pageList.id});">删除</a>--%>
<%--							</td>--%>
<%--						</s:iterator>--%>
<%--						<s:if test="successMessage!=null">--%>
<%--							<tr>--%>
<%--								<td colspan="11" align="center" style="color: red">--%>
<%--									${successMessage}--%>
<%----%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</s:if>--%>
<%--						<tr>--%>
<%--							<td align="center" colspan="3">--%>
<%--							</td>--%>
<%--							<td id="test"></td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</form>--%>
<%--			</div>--%>
<%--			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~--%>
			<div>
				<form id="myform2" action="ScreenContentAction_update.action"
					method="post">
					<ul class="list">
						<li class="columnhead">
							序号
						</li>
						<li class="columnhead">
							名称
						</li>
						<li class="columnhead">
							地址
						</li>
						<li class="columnhead">
							操作
						</li>
					</ul>
					<div id="items">
						<input id="Sequence" type="hidden" name="sortableSequence" />
						<s:iterator value="screencontents" id="pageList"
							status="pageIndex">
							<ul class="list" id="${pageIndex.index+1}">
								<div>
								</div>
								<li>
									${pageIndex.index+1}
								</li>
								<li>
									<input name="screencontents[${pageIndex.index}].name"
										value="${pageList.name}" id="inputname${pageList.id}"
										style="width: 350px; border: 0px; background-color: transparent;"
										readonly="readonly" />
								</li>
								<li>
									<input name="screencontents[${pageIndex.index}].screenUrl"
										value="${pageList.screenUrl}"
										id="inputscreenUrl${pageList.id}"
										style="width: 350px; border: 0px; background-color: transparent;"
										readonly="readonly" />
								</li>
								<li>
									<input type="hidden"
										name="screencontents[${pageIndex.index}].id"
										value="${pageList.id}" />
									<a href="javascript:void(0);"
										onclick="deleteById(${pageList.id});">删除</a>
									<a href="javascript:void(0);"
										onclick="updateById(${pageList.id});">修改</a>
							</ul>
						</s:iterator>

					</div>
				</form>
				<div align="center" colspan="3">
					<input id="submitBtn" type="button" value="保存"
						style="width: 100px; height: 50px;" />
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>

		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->

		<script type="text/javascript">
// Simple list
var el = document.getElementById('items');
Sortable.create(el);

var idstr = "";
function sortableSequence() {
	idstr = "";
	$("#items ul").each(function() {
		// 将选中的字段id拼成字符串
			idstr += $(this).attr("id") + ",";
		});
	if (idstr.length > 0) {
		idstr = idstr.substring(0, idstr.length - 1)

	}
}
$("#submitBtn").click(function() {
	sortableSequence();
<%--	alert(idstr);--%>
	document.getElementById('Sequence').value=idstr;
	$("#myform2").submit();
})

function deleteById(id) {

	if (confirm("确定删除该记录？")) {
		$.ajax( {
			type : "POST",
			url : "ScreenContentAction_delete.action?id=" + id,
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
function updateById(id) {
	var inputelname = document.getElementById("inputname" + id);
	var inputelurl = document.getElementById("inputscreenUrl" + id);
	inputelname.readOnly = false;
	inputelname.style.border = "1px solid ";
	inputelname.style.backgroundColor = "#ccc";
	inputelurl.readOnly = false;
	inputelurl.style.border = "1px solid ";
	inputelurl.style.backgroundColor = "#ccc";
}
</script>

		<script type="text/javascript">
</script>
	</body>
</html>
