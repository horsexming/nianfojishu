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
				<form action="outlib_getUserListAll.action" method="post" style="">
					<table border="0" width="100%" class="table">
						<tr>
							<td align="right">
								工号:
							</td>
							<td>
								<input id="code" type="text" name="user.code" value="" />
							</td>
							<td align="right">
								姓名:
							</td>
							<td>
								<input id="name" type="text" name="user.name" value="" />
							</td>
							<td align="right">
								部门:
							</td>
							<td>
								<select id="dept" name="user.dept">
									<option value="">
										请选择
									</option>
								</select>
							</td>
						</tr>

						<tr>
							<td align="center" colspan="8">
								<input type="submit" value="查询"
									style="width: 100px; height: 50px;" />

								<input type="reset" value="重置"
									style="width: 100px; height: 50px;" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="text-align: center;">
					<tr bgcolor="#c0dcf2" height="50px">
						<td align="center">
							序号
						</td>
						<td align="center">
							姓名
						</td>
						<td align="center">
							工号
						</td>
						<td align="center">
							卡号
						</td>
						<td align="center">
							部门
						</td>
						<td align="center">
							操作
						</td>
					</tr>
					<s:iterator id="u" value="userList" status="st">
						<s:if test="#st.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this);"
								onmouseout="outBgcolor(this,'');">
						</s:else>

						<td>
							${st.index + 1}
						</td>
						<td>
							${u.name}
						</td>
						<td>
							${u.code}
						</td>
						<td>
							${u.cardId}
						</td>
						<td>
							${u.dept}
						</td>
						<td>
							<a onclick="return true;"
								href="outlib_getFanghuOutLibListByUserId.action?fanghuOutLib.userId=${u.id}">查看劳防物品</a>
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
							</td>
						</s:if>
						<s:else>
							<td colspan="11" align="center" style="color: red">
								${errorMessage}
							</td>
						</s:else>
					</tr>
				</table>
			</div>
			<br />
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
</html>
<script type="text/javascript">
$(function() {
	$.ajax( {
		type : "get",
		dataType : "text",
		url : "DeptNumberAction!finAllDeptNumberForSetlect.action",
		async : false,
		success : function(data) {
			var dept = data.split("|");
			for ( var i = 0; i < dept.length - 1; i++) {
				var deptItem = new Option(dept[i], dept[i]);
				$("#dept").append(deptItem);
			}
		}
	});
});
</script>
