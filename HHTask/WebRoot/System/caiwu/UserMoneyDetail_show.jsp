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
/* 带复选框的下拉框 */
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ProcardAction!findUserMoneyDetailById.action"
					method="POST">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${umd.markId}" name="umd.markId" />
							</td>
							<th align="right">
								名称
							</th>
							<td>
								<input type="text" value="${umd.proName}" name="umd.proName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								工位
							</th>
							<td>
								<select name="umd.gongwei" id="gongwei">
									<s:iterator value="listAll" id="pagegongwei">
										<option value="${pagegongwei}">${pagegongwei}</option>
									</s:iterator>
								</select>
<%--								<input type="text" value="${umd.gongwei}" name="umd.gongwei" />--%>
							</td>
							<th align="right">
								工序名称
							</th>
							<td>
								<input type="text" value="${umd.processName}" name="umd.processName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								从
							</th>
							<td>
								<input type="text" value="${firstTime}" name="firstTime"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								止
							</th>
							<td>
								<input type="text" value="${endTime}" name="endTime"
									class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<th align="right">
								业务件号
							</th>
							<td>
								<input type="text" value="${umd.ywmarkId}" name="umd.ywmarkId" />
							</td>
							<th align="right">内部订单号</th>
							<td>
								<input type="text" value="${umd.orderNumber}" name="umd.orderNumber" />
							</td>
						</tr>
					</table>
					<input type="hidden" value="noPage" name="tag" />
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="hidden" value="${id}" name="id" />
					<input type="submit" value="查询" class="input" />
					<input type="button" value="导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"
						class="input" />
				</form>
				<table class="table">
					<s:if test='umm!=null'>
						<tr>
							<th colspan="18">
								${umm.username}的${umm.month}的生产奖金分配明细
							</th>
						</tr>
					</s:if>

					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							内部订单号
						</th>
						<th align="center">
							总成件号
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							总成批次
						</th>
						<th align="center">
							零件件号
						</th>
						<th align="center">
							零件名称
						</th>
						<th align="center">
							生产类型
						</th>
						<th align="center">
							工序号
						</th>
						<th align="center">
							工序名
						</th>
						<th align="center">
							工位
						</th>
						<th align="center">
							投产时间
						</th>
						<th align="center">
							领取时间
						</th>
						<th align="center">
							提交时间
						</th>
						<th align="center">
							订单数量
						</th>
						<th align="center">
							任务数量
						</th>
						<th align="center">
							提交数量<br/>
							<font color="red">
							<fmt:formatNumber type="number" value="${umd1.submmitCount}"
									maxFractionDigits="2" />	
							</font>
						</th>
						<th align="center">
							标准节拍
							<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.submmitJiepai}"
									maxFractionDigits="2" />	
							</font>
						</th>
						<th align="center">
							提交总节拍
							<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.subnmitBZJiepai}"
									maxFractionDigits="2" />	
							</font>
						</th>
						<th align="center">
							超时节拍<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.nowCsJiepai}"
									maxFractionDigits="2" />	
							</font>
						</th>
						<th align="center">
							扣除奖金<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.nowKcMoney}"
									maxFractionDigits="2" />	
							</font>
						</th>
						<s:if test='pageStatus=="gc"'>
							<th align="center">
								计件工资<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.processjjAllMoneygc}"
									maxFractionDigits="2" />	
							</font>
							</th>
							<th align="center">
								实发工资<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.nowMoney}"
									maxFractionDigits="2" />	
							</font>
							</th>
							<th>
								结余<br/>
							<font color="red">
								<fmt:formatNumber type="number" value="${umd1.processjjAllMoneygc-umd1.nowMoney}"
									maxFractionDigits="2" />
							</font>
							</th>
						</s:if>
						<s:else>
							<th align="center">
								个人奖金
							</th>
						</s:else>
						<th align="center">
							操作
						</th>
					</tr>
					<s:iterator value="list" id="pageUmD" status="pageStatus1">
						<s:if test="#pageStatus1.index%2==1">
							<tr align="center" bgcolor="#e6f3fb"
								onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)"
								onmouseout="outBgcolor(this,'')">
						</s:else>
						<td>
							<s:property value="#pageStatus1.index+1" />
						</td>
						<td>
							${pageUmD.orderNumber}
						</td>
						<td>
							${pageUmD.markIdRoot}
						</td>
						<td>
							${pageUmD.ywmarkId}
						</td>
						<td>
							${pageUmD.selfCard}
						</td>
						<td>
							${pageUmD.markId}
						</td>
						<td>
							${pageUmD.proName}
						</td>
						<td>
							${pageUmD.productStyle}
						</td>
						<td>
							${pageUmD.processNO}
						</td>
						<td>
							${pageUmD.processName}
						</td>
						<td>
							${pageUmD.gongwei}
						</td>
						<td>
							${pageUmD.tcTime}
						</td>
						<td>
							${pageUmD.firstApplyDate}
						</td>
						<td>
							${pageUmD.submitDate}
						</td>
						<td align="right">
							${pageUmD.orderNum}
						</td>
						<td align="right">
							${pageUmD.count}
						</td>
						<td align="right">
							<s:if test="submmitCount<1">
								1
							</s:if>
							<s:else>
								<fmt:formatNumber type="number" value="${pageUmD.submmitCount}"
									maxFractionDigits="0" />
							</s:else>
						</td>
						<td align="right">
							<s:if test="nowCsJiepai>0">
								<fmt:formatNumber type="number" value="${pageUmD.submmitJiepai}"
									maxFractionDigits="2" />
							</s:if>
							<s:else>
								<fmt:formatNumber type="number"
									value="${pageUmD.submmitJiepai-pageUmD.nowCsJiepai}"
									maxFractionDigits="2" />
							</s:else>
						</td>
						<td align="right">
							<s:if test="nowCsJiepai>0">
								<fmt:formatNumber type="number"
									value="${pageUmD.submmitJiepai+pageUmD.nowCsJiepai}"
									maxFractionDigits="2" />
							</s:if>
							<s:else>
								<fmt:formatNumber type="number" value="${pageUmD.submmitJiepai}"
									maxFractionDigits="2" />
							</s:else>
						</td>
						<td align="right">
							<s:if test="nowCsJiepai>0">
								<font color="red"><fmt:formatNumber type="number"
										value="${pageUmD.nowCsJiepai}" maxFractionDigits="2" /> </font>
							</s:if>
							<s:else>
								<font color="green"><fmt:formatNumber type="number"
										value="${pageUmD.nowCsJiepai}" maxFractionDigits="2" />
								</font>
							</s:else>
						</td>
						<td align="right">
							<fmt:formatNumber type="number" value="${pageUmD.nowKcMoney}"
								maxFractionDigits="2" />
						</td>
						<s:if test='pageStatus=="gc"'>
							<td align="right">
									<fmt:formatNumber type="number"
									value="${pageUmD.processjjAllMoneygc}" maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number" value="${pageUmD.nowMoney}"
									maxFractionDigits="2" />
							</td>
							<td align="right">
								<fmt:formatNumber type="number" value="${pageUmD.processjjAllMoneygc-pageUmD.nowMoney}"
									maxFractionDigits="2" />
							</td>
						</s:if>
						<s:else>
							<td align="right">
								<fmt:formatNumber type="number" value="${pageUmD.nowMoney}"
									maxFractionDigits="2" />
							</td>
						</s:else>
						<td>
							<a target="_blank"
								href="ProcardAction!findProcardView.action?id=${pageUmD.procardRootId}&pageStatus=history&viewStatus=zjl">生产进度</a>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<s:if test="errorMessage==null">
							<td colspan="35" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />
						</s:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function(){
	duoxuaSelect("gongwei");//下拉多选框
})
	
function exportExcel(obj) {//zhaobiaoAction!listAll.action
	obj.action = "ProcardAction!exportExcelumd.action";
	obj.submit();
	obj.action = "ProcardAction!findUserMoneyDetailById.action";
}
</script>
	</body>
</html>
