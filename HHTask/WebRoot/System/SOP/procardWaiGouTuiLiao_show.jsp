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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
					<h3>生产退料确认</h3>
					<form action="procardBlAction_findAllWaigouTuiLiaoQueRen.action?pagestatus=${pagestatus}"
						method="post">
						<table class="table" align="center">
							<tr>
								<td align="center">
									总成件号
									<input type="text" name="procard.rootMarkId" value="<s:property value="procard.rootMarkId"/>" />
								</td>
								<td align="center">
									总成批次
									<input type="text" name="procard.rootSelfCard" value="<s:property value="procard.rootSelfCard"/>" />
								</td>
							</tr>
							<tr>
								<td align="center">
									业务件号
									<input type="text" name="procard.ywMarkId" value="<s:property value="procard.ywMarkId"/>" />
								</td>
								<td align="center">
									订单号
									<input type="text" name="procard.orderNumber" value="<s:property value="procard.orderNumber"/>" />
								</td>
							</tr>
								<tr>
								<td align="center">
									申请人
									<input type="text" name="procard.appliUserName" value="<s:property value="procard.appliUserName"/>" />
								</td>
								<td align="center">
									仓区
									<input type="text" name="procard.cangqu" value="<s:property value="procard.cangqu"/>" />
								</td>
							</tr>
							</tr>
								<tr>
								<td align="center">
									申请时间从
									<input type="text" name="startime" value="<s:property value="procard.appliUserName"/>"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"  class="Wdate" />
								</td>
								<td align="center">
									止
									<input type="text" name="endtime" value="<s:property value="procard.cangqu"/>"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})"   class="Wdate"  />
								</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<input type="submit" style="width: 100px; height: 40px;"
										value="查询(select)" />
								</td>
							</tr>
						</table>
					</form>
					<form action=""
						method="post">
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
								全选
							</th>
							<th>
								件号
							</th>
							<th>
								批次
							</th>
							<th>
								名称
							</th>
							<th>
								仓区
							</th>
							<th>
								类型
							</th>
							<th>
								单位
							</th>
							<th>
								可领数量
							</th>
							<th>
								已发料数量
							</th>
							<th>
								申请人
							</th>
							<th>
								申请时间
							</th>
							<th>
								申请退料数量
							</th>
							<th>
								已退料数量
							</th>
							<th>
								申请状态
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator value="procardWaigouTuiLiaoList" id="procard2"
							status="blStatus2">
							<s:if test="#blStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								${blStatus2.index+1}
								<input type="checkbox" name="procardIds2" value="${procard2.id}"
									onclick="chageNum(this)">
							</td>
							<td>
								${procard2.markId}
							</td>
							<td>
								${procard2.selfCard}
							</td>
							<td style="width: 200px;" align="left">
								${procard2.proName}
							</td>
							<td>
								${procard2.cangqu}
							</td>
							<td>
								${procard2.procardStyle}
							</td>
							<td>
								${procard2.unit}
							</td>
							<td>
								${procard2.filnalCount}
							</td>
							<td>
								<fmt:formatNumber value="${procard2.filnalCount-procard2.hascount}" pattern="#.####"></fmt:formatNumber>
								
							</td>
							<td>
								${procard2.appliUserName}
							</td>
							<td>
								${procard2.appliTime}
							</td>
							<td>
								${procard2.stuiLiaoNumber}
							</td>
							<td>
								${procard2.ytuiLiaoNumber}
							</td>
							<td>
								<a href="CircuitRunAction_findAduitPage.action?id=${procard2.epId}">${procard2.tuiLiaoStatus}</a>
							</td>
							<td>
								<s:if test="#procard2.epId!=null">
									<input type="button" value="审批动态" style="height: 25px;width: 80px;" onclick="shenpi(${procard2.epId})">
								</s:if>
							</td>
							</tr>
						</s:iterator>
						<tr>
							<th colspan="1" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2" name="procardIds">
								全选
							</th>
							<td colspan="20">
								<input id="queren" class="input" style="width: 120px;" align="top"
									type="button" value="确认"
									onclick="toSubmitTlQue(this.form)"
									/>
							</td>
						</tr>
						<tr>
								<td colspan="30" align="right">
									第
									<font color="red"><s:property value="cpage" /> </font> /
									<s:property value="total" />
									页
									<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
										styleClass="page" theme="number" />

								</td>
							</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function shenpi(id){
	window.location.href="CircuitRunAction_findAduitPage.action?id="+id;
}

function toSubmitTlQue(form){
	$("#queren").attr("disabled", "disabled");
	form.action = "procardBlAction_waigouTuiLiao.action?pagestatus=${pagestatus}";
	form.submit();
}


</SCRIPT>
	</body>
</html>
