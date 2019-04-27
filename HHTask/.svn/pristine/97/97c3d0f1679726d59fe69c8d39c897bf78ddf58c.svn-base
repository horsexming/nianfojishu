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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<span style="color: red; font-weiht: bold;">${message}</span>
				<form
					action="RunningWaterCardAction!findOneCardInfor.action?tag=byCardNum"
					method="post">
					<table class="table" style="width: 85%;">

						<tr>
							<th align="right">
								请刷卡号，或扫描补料单条码入库：
								<br />
								Please credit card number, or scan a single barcode storage
								feeding:
							</th>
							<th>
								<input type="text" name="runningWaterCard.cardNum"
									value="${runningWaterCard.cardNum }" />
								<br />

							</th>

						</tr>
						</form>
						<table class="table" style="width: 85%;">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
									<br />
									No.
								</th>
								<th align="center">
									客户
									<br />
									Customers
								</th>
								<th align="center">
									车型
									<br />
									Models
								</th>
								<th align="center">
									件号
									<br />
									Part No.
								</th>
								<th align="center">
									跟踪批次
									<br />
									Tracking batches
								</th>
								<th align="center">
									持卡者
									<br />
									Cardholders
								</th>
								<th align="center">
									跟踪状态
									<br />
									Tracking Status
								</th>
								<th align="center">
									操作
									<br />
									Operation
								</th>
							</tr>
							<tr>
								<td colspan="8" style="font-size: 15px; color: red;">
									待入库的记录(Storage of records to be)
								</td>
							</tr>
							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="card">
									<s:if test="#se.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se.index+1" />
									</td>

									<td>
										${card.customer}
									</td>
									<td>
										${card.carStyle}
									</td>
									<td>
										${card.markId}
									</td>

									<td>
										${card.followCardId}
									</td>
									<td>
										${card.ownUsername}
									</td>
									<td>
										${card.cardStatus}
									</td>
									<td>
										<s:if test="tag =='noCard'">
											<a
												href="RunningWaterCardAction!findOneCardInfor.action?id=${id}&tag=byId">申请入库</a>
										</s:if>
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="9" align="right">
										共
										<s:property value="total" />
										页 第
										<s:property value="cpage" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />

									</td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td colspan="8" style="font-size: 15px; color: red;">

									</td>
								</tr>
							</s:else>
						</table>
						</div>
						<br>
						</div>
						<%@include file="/util/foot.jsp"%>
						</center>
						<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
						<script type="text/javascript">
//提交验证
onload = function() {
	var me = "
												${message }";
	if (me.length> 0) { alert(me); } } 
</script>
	</body>
</html>
