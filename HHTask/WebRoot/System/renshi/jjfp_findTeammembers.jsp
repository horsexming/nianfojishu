<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<style type="text/css">
table {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

table th,table td {
	border: solid #999;
	border-width: 1 1px 1px 1;
	padding: 2px;
}
</style>
	</head>
	<body bgcolor="#ffffff">
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px;"
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
			</div>

			<div align="center">
				<%--<form action="TeammembersAction.action" method="post"
					onsubmit="return check()">
					<table align="center">
						<tr>
							<th colspan="6">
								<font size="5">添加班组成员</font>
							</th>
						</tr>
						<s:if test="str==123">
							<tr>
								<td align="center" colspan="6">
									<font color="red">工号：${name} 添加成功</font>
								</td>
							</tr>
						</s:if>
						<tr>
							<th>
								成员工号
							</th>
							<td>
								<input type="text" name="teammembers.teammembersmembernumber"
									onblur="send(this)" id="gongnumber" />
							</td>
							<th>
								成员姓名
							</th>
							<th>
								<input type="text" name="teammembers.teammembersteamname"
									readonly="readonly" id="chengyuanname" />
							</th>
							<th>
								成员卡号
							</th>
							<td>
								<input type="text" name="teammembers.teammemberscardnumber"
									readonly="readonly" id="kanumber" />
							</td>
						</tr>

						<tr>
							<th>
								备注
							</th>
							<td colspan="5">
								<textarea rows="" style="width: 350px; height: 80px;" cols=""
									name="teammembers.teammembersremarks"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="6" align="center">
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;
								<input type="reset" value="取  消" />
							</td>
						</tr>
					</table>
				</form>
				--%>
				<table align="center">
					<tr>
						<th colspan="5">
							<font size="5">班组成员信息</font>
						</th>
					</tr>
					<tr>
						<th>
							工号
						</th>
						<th>
							成员姓名
						</th>
						<th>
							卡号
						</th>
						<th>
							班组
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="t" value="list" status="stauts">
						<s:if test="#stauts.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td align="center">
							${t.teammembersmembernumber}
						</td>
						<td align="center">
							${t.teammembersteamname}
						</td>
						<td align="center">
							${t.teammemberscardnumber}
						</td>
						<td align="center">
							${t.teammembersteam}
						</td>
						<td align="center">
							<!-- -->
							<a href="TeammembersAction!delete.action?id=${t.id}"
								onClick="return window.confirm('确认要删除选中的信息吗？')">删除</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="16" align="right">
							第
							<font color="red"><s:property value="cpage" /> </font> /
							<s:property value="total" />
							页
							<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
								styleClass="page" theme="number" />
						</td>
					</tr>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
















</html>
