<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<STYLE type="text/css">

/* 带复选框的下拉框 */
ul li {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

.select_checkBox {
	border: 0px solid red;
	position: relative;
	display: inline-block;
}

.chartQuota {
	height: 23px;
	float: left;
	display: inline-block;
	border: 0px solid black;
	position: relative;
}

.chartOptionsFlowTrend {
	z-index: 300;
	background-color: white;
	border: 1px solid gray;
	display: none;
	position: absolute;
	left: 0px;
	top: 23px;
	width: 150px;
}

.chartOptionsFlowTrend ul {
	float: left;
	padding: 0px;
	margin: 5px;
}

.chartOptionsFlowTrend li { /* float:left; */
	display: block;
	position: relative;
	left: 0px;
	margin: 0px;
	clear: both;
}

.chartOptionsFlowTrend li * {
	float: left;
}

a:-webkit-any-link {
	color: -webkit-link;
	text-decoration: underline;
	cursor: auto;
}

.chartQuota p a {
	float: left;
	height: 21px;
	outline: 0 none;
	border: 1px solid #ccc;
	line-height: 22px;
	padding: 0 5px;
	overflow: hidden;
	background: #eaeaea;
	color: #313131;
	text-decoration: none;
}

.chartQuota p {
	margin: 0px;
	folat: left;
	overflow: hidden;
	height: 23px;
	line-height: 24px;
	display: inline-block;
}

.chartOptionsFlowTrend p {
	height: 23px;
	line-height: 23px;
	overflow: hidden;
	position: relative;
	z-index: 2;
	background: #fefbf7;
	padding-top: 0px;
	display: inline-block;
}

.chartOptionsFlowTrend p a {
	border: 1px solid #fff;
	margin-left: 15px;
	color: #2e91da;
}

</STYLE>
	</head>
	<body >
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
			<br/>
			<h2>
			月度生产奖金分配查询
			</h2>
			<form action="ProcardAction!findUMMoneyByCondition.action" method="POST">
				<table class="table">
					<tr>
						<th align="right">工号</th>
						<td>
							<input type="text" value="${umm.code}" name="umm.code"/>
						</td>
						<th align="right">姓名</th>
						<td>
							<input type="text" value="${umm.username}" name="umm.username"/>
						</td>
					</tr>
					<tr>
						<th align="right">部门</th>
						<td>
<%--							<input type="text" value="${umm.dept}" name="umm.dept"/>--%>
							<SELECT name="umm.dept" id="dept">
								<option></option>
							</SELECT>
						</td>
						<th align="right">月份</th>
						<td>
							<input type="text" value="${umm.month}" name="umm.month" class="Wdate"
							onClick="WdatePicker({dateFmt:'yyyy-MM月',skin:'whyGreen'})"/>
						</td>
					</tr>
				</table>
				<input type="submit" value="查询" class="input"/>
				<input class="input" onclick="window.history.back();" type="button" value="返回"/>
			</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
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
						<th align="center">
							月份
						</th>
						<th align="center">
							计件工资<br/>
							(<font color="red">
								<fmt:formatNumber value="${sum1}" pattern="###,###.##"></fmt:formatNumber>
							</font>)
						</th>
						<th align="center">
							时均工资
						</th>
						<th align="center">
							激励<br/>
							(<font color="red">
								<fmt:formatNumber value="${sum2}" pattern="###,###.##"></fmt:formatNumber>
							</font>)
						</th>
						<th align="center">
							扣款<br/>
							(<font color="red">
								<fmt:formatNumber value="${sum3}" pattern="###,###.##"></fmt:formatNumber>
							</font>)
						</th>
						<th align="center">
							超占比
						</th>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageUmm" status="pageStatus">
						<s:if test="#pageStatus.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus.index+1" />
						</td>
						<td>
							${pageUmm.code}
						</td>
						<td>
							${pageUmm.username}
						</td>
						<td>
							${pageUmm.dept}
						</td>
						<td>
							${pageUmm.month}
						</td>
						<td align="right">
							<fmt:formatNumber value="${pageUmm.money}" pattern="###,###.##"></fmt:formatNumber>
						</td>
						<td align="right">
							<fmt:formatNumber value="${pageUmm.avgHoursMoney}" pattern="###,###.##"></fmt:formatNumber>
						</td>
						<td align="right">
							<fmt:formatNumber value="${pageUmm.excitation}" pattern="###,###.##"></fmt:formatNumber>
						</td>
						<td align="right">
							<fmt:formatNumber value="${pageUmm.debit}" pattern="###,###.##"></fmt:formatNumber>
						</td>
						<td>
							<fmt:formatNumber type="number" value="${pageUmm.caozhanbi}"
								maxFractionDigits="2" />
							%
						</td>
						<td>
							<a
								href="ProcardAction!findUserMoneyDetailById.action?id=${pageUmm.id}">明细</a>
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
						</s:else>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	$.ajax( {
		type : "post",
		url : "GzstoreAction_getdept.action",
		dataType : "json",
		async : false,
		success : function(data) {
			//填充部门信息
			$(data).each(
					function() {
						$(
								"<option value='" + this.dept + "'>" + this.dept
										+ "</option>").appendTo("#dept");
						//userlist($("#deptname").val());
					});
		}
	});
	duoxuaSelect('dept');
})

</SCRIPT>
	</body>
</html>
