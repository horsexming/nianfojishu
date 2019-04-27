<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DecimalFormat"%>
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
		<base href="<%=basePath%>">
		<%@include file="/util/sonHead.jsp"%>
	</head>

	<body onload="createDept('dept')">
		<%@include file="/util/sonTop.jsp"%>
		<form action="ProcardAction!findPeople.action" method="post">
			<table class="table">
				<tr>
					<th>
						部门
					</th>
					<td>
						<select name="user.dept" id="dept">
							<option></option>
						</select>
					</td>
					<th>
						姓名
					</th>
					<td>
						<input type="text" name="user.name" />
					</td>
					<th>
						工号
					</th>
					<td>
						<input type="text" name="user.code" />
					</td>
				</tr>
				<tr>
					<th colspan="8">
						<input type="hidden" value="listUser" name="tag" id="tag" />
						<input type="hidden" value="${id}" name="id" />
						<input type="button" value="查询" onclick="findPeople(this.form)"
							class="input" />
						<input type="submit" value="按条件剔除" class="input" />
					</th>
				</tr>
			</table>
		</form>
		<s:if test="pageStatus=='jhBd'">
			<form action="ProcardAction!bangAndJihuo.action" method="post"
				id="myform" name="lzyy"
				onsubmit="return  window.confirm('确认人员选择，并提交到激活序列?')">
		</s:if>
		<s:else>
			<form action="ProcardAction!bangAndad.action" method="post"
				id="myform" name="lzyy">
		</s:else>
		<table class="table">
			<tr>
				<td style="font-size: 10px; font-weight: bolder;">
					<input id="checkNotAll" type="checkbox" name="invest"
						value="checkbox" onClick="chageAllCheck(this)">
					<label for="checkNotAll">
						全选
					</label>
				</td>
				<td colspan="11">
					<input type="submit" value="绑定" class="input">
					<font color="red">注:如果选择了按条件剔除后请再次点击绑定。完成人员的解绑!<font />
				</td>
			</tr>
			<tr>
				<th colspan="7" style="background-color: #c0dcf2;">
					已绑定人员(系统推荐)
				</th>
			</tr>
			<tr bgcolor="#c0dcf2" height="50px">
				<th align="center">
					选择
				</th>
				<th align="center">
					序号
					<br />
					No.
				</th>
				<th align="center">
					工号
					<br />
					Code
				</th>
				<th align="center">
					姓名
					<br />
					Name
				</th>
				<th align="center">
					部门
					<br />
					Dept
				</th>
				<th align="center">
					状态
					<br />
					Status
				</th>
				<th>
					推荐
				</th>
			</tr>
			<s:iterator value="list" id="ProcessinforPeople0" status="pageIndex">
				<s:if test="#pageIndex.index%2==1">
					<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
						onmouseover="chageBgcolor(this)"
						onmouseout="outBgcolor(this,'#e6f3fb')">
				</s:if>
				<s:else>
					<tr align="center" onmouseover="chageBgcolor(this)"
						style="height: 50px;" onmouseout="outBgcolor(this,'')">
				</s:else>
				<td>
					<input type="checkbox" name="selected"
						value="${ProcessinforPeople0.userId}" checked="checked">
				</td>
				<td>
					<s:property value="#pageIndex.index+1" />
				</td>
				<td>
					${ProcessinforPeople0.code}
				</td>
				<td>
					${ProcessinforPeople0.name}
				</td>
				<td>
					${ProcessinforPeople0.dept}
				</td>
				<td>
					${ProcessinforPeople0.status}
				</td>
				<td>
					${ProcessinforPeople0.isTuijian}
				</td>
			</s:iterator>
		</table>


		<input name="id" value="${id}" type="hidden">
		<table class="table">
			<tr>
				<th colspan="7" style="background-color: #c0dcf2; height: 40px;">
					可绑定人员(手动调整)
				</th>
			</tr>
			<tr bgcolor="#c0dcf2" height="50px">
				<th align="center">
					选择
				</th>
				<th align="center">
					序号
				</th>
				<th align="center">
					工号
				</th>
				<th align="center">
					姓名
				</th>
				<th align="center">
					部门
				</th>
				<th></th>
				<th></th>
			</tr>
			<%--			<tr>--%>
			<%--				<td style="font-size: 10px; font-weight: bolder;">--%>
			<%--					<input id="checkNotAll" type="checkbox" name="invest"--%>
			<%--						value="checkbox" onClick="chageAllCheck(this)">--%>
			<%--					<label for="checkNotAll">--%>
			<%--						全选--%>
			<%--					</label>--%>
			<%--				</td>--%>
			<%--				<td colspan="7">--%>
			<%--					<input type="submit" value="确定" class="input">--%>
			<%--				</td>--%>
			<%--			</tr>--%>
			<s:iterator value="listAll" id="pageUserss" status="pageStatus">
				<s:if test="#pageStatus.index%2==1">
					<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
						onmouseover="chageBgcolor(this)"
						onmouseout="outBgcolor(this,'#e6f3fb')">
				</s:if>
				<s:else>
					<tr align="center" onmouseover="chageBgcolor(this)"
						style="height: 50px;" onmouseout="outBgcolor(this,'')">
				</s:else>
				<td>
					<input type="checkbox" name="selected" value="${pageUserss.id}">
				</td>
				<td>
					<s:property value="#pageStatus.index+1" />
				</td>
				<td>
					${pageUserss.code}
				</td>
				<td>
					${pageUserss.name}
				</td>
				<td>
					${pageUserss.dept}
				</td>
				<th>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
				<th>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</th>
			</s:iterator>
			<tr>
				<td colspan="11" style="font-size: 10px; font-weight: bolder;">
					<input id="checkNotAll" type="checkbox" name="invest"
						value="checkbox" onClick="chageAllCheck(this)">
					<label for="checkNotAll">
						全选
					</label>
				</td>
			</tr>
			<tr>
				<th colspan="7">
					<input type="submit" value="确定" class="input">
				</th>
			</tr>
		</table>
		</form>

		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function selectIt() {
	form = document.lzyy
	action = event.srcElement.name
	for ( var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].name == "selected") {
			e = form.elements[i]
			e.checked = (action == "selectAll") ? (form.selectAll.checked)
					: (!e.checked)
		}
	}
}

function tijiao(obj) {
	$("#" + obj).submit();
}
function findPeople(obj) {
	$("#tag").val('listwbUser');
	$(obj).attr("action", "ProcardAction!findPeople.action?pageStatus=cx")
	$(obj).submit();
	$("#tag").val('listUser');
	$(obj).attr("action", "ProcardAction!findPeople.action")
}

$(function() {
	var main = $(window.parent.document).find("#showProcess");//找到iframe对象
	if (main != null) {
		var thisheight = document.body.scrollHeight;
		var thisheight2 = parseFloat(thisheight);
		main.height(thisheight2 + 100);//为iframe高度赋值如果高度小于500，则等于500，反之不限高，自适应
	}
})
</script>
	</body>
</html>
