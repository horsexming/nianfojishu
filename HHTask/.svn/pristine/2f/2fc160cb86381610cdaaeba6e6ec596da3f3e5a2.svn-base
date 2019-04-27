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
.sss {
	font-size: 14px;
	padding: 0px;
	margin: 0px;
	border-collapse: collapse;
	/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
	border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
	border-width: 1px 0 0 1px;
	width: 980px;
}

.sss th,.sss td {
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
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">

				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="" style="color: #ffffff">添加功能</a>
				</div>
			</div>

			<div align="center">
				<form action="MentionrecordAction!conditiontFindAll.action"
					method="post">
					<input type="hidden" name="pageStatus" value="${pageStatus}">
					<table align="center" class="table">

						<tr>
							<th colspan="4">
								<font size="5">提奖记录表详细信息</font>
							</th>
						</tr>
						<tr>
							<th>
								月份
							</th>
							<td>
								<input class="Wdate" type="text"
									name="mentionrecord.mentionMonth"
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
							</td>
							<th>
								应提奖额
							</th>
							<td>
								<input type="text" name="mentionrecord.mentionshallMoney" />
							</td>
						</tr>
						<tr>
							<th>
								状态
							</th>
							<td colspan="3">
								<select name="mentionrecord.mentionstatus">
									<option value=""></option>
									<option value="审核中">
										审核中
									</option>
									<option value="可提奖">
										可提奖
									</option>
									<option value="已提奖">
										已提奖
									</option>
								</select>
								&nbsp;&nbsp;&nbsp;
								<input type="submit" value="确  定" />
								&nbsp;&nbsp;&nbsp;
								<a href="MentionrecordAction.action?pageStatus=${pageStatus}">查询所有</a>
							</td>
						</tr>
					</table>
				</form>
<%--			<form action="MentionrecordAction!updateMentionrecord1.action" method="post">--%>
				<table class="table">
					<tr>
						<th>
							月份
						</th>
						<th>
							应提奖额
						</th>
						<th>
							提奖状态
						</th>
						<th>
							实际提奖额
						</th>
						<th>
							操作
						</th>
					</tr>
					<s:iterator id="m" value="mentionrecolist" status="stauts">
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
							${m.mentionMonth}
						</td>
						<td align="center">
							${m.mentionshallMoney }
						</td>
						<td align="center">
							${m.mentionstatus}
						</td>
						<td align="center">
							${m.mentionactualMoney}
						</td>
						<td align="center">
							<s:if test="pageStatus=='hr'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
							</s:if>
							&nbsp;&nbsp;&nbsp;
							<s:if test="pageStatus=='all'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
								<s:if test="mentionstatus=='可提奖'">
									<font color="gray">可提奖</font>
								</s:if>
								<s:elseif test="mentionstatus=='审核中'">
									<a
										href="MentionrecordAction!updateMentionrecord.action?id=${m.id}">可提奖</a>
								</s:elseif>
							</s:if>
							<s:elseif test="pageStatus=='dept'">
								<a
									href="TijingAction!showtijing.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查询详细</a>
								<a
									href="TijingAction!findGoodStore.action?yuefen=${m.mentionMonth}&pageStatus=${pageStatus}">查看入库记录</a>
								<s:if test="mentionstatus=='可提奖'">
									<a href="MentionrecordAction!updateFind.action?id=${m.id}">提奖</a>
								</s:if>
								<s:elseif test="mentionstatus=='已提奖'">
									<font color="gray">提奖</font>
								</s:elseif>
							</s:elseif>
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
