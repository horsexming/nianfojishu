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
				<form action="RunningWaterCardAction!findCard.action" method="post">
					<table class="table" style="width: 85%;">
						<tr>
							<td>
								客户(Customers)：
								<select name="runningWaterCard.customer" id="customer"
									style="width: 140px;"
									onMouseOver="createDept('customer','RunningWaterCardAction!selectItem.action?tag=customer')">
									<option value=""></option>
									<option value="${runningWaterCard.customer}"
										selected="selected">
										${runningWaterCard.customer}
									</option>
								</select>

							</td>
							<td>
								车型(Models)：
								<select name="runningWaterCard.carStyle" id="carStyle"
									style="width: 140px;"
									onMouseOver="createDept('carStyle','RunningWaterCardAction!selectItem.action?tag=carStyle')">
									<option value=""></option>
									<option value="${runningWaterCard.carStyle}"
										selected="selected">
										${runningWaterCard.carStyle}
									</option>
								</select>
							</td>
							<td>
								件号(Part No.)：
								<select name="runningWaterCard.markId" id="markId"
									style="width: 140px;"
									onMouseOver="createDept('markId','RunningWaterCardAction!selectItem.action?tag=markId')">
									<option value=""></option>
									<option value="${runningWaterCard.markId}" selected="selected">
										${runningWaterCard.markId}
									</option>
								</select>
							</td>
							<td rowspan="3" align="center">
								<input type="submit" style="width: 50px; height: 70px;"
									value="查询" />
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								卡号(Card number)：
								<input type="text" name="runningWaterCard.cardNum"
									value="${runningWaterCard.cardNum }" />

							</td>

							<td>
								日期从(Date from)：
								<input class="Wdate" type="text" name="startDate"
									value="${ startDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />

							</td>
							<td>
								<span style="width: 21px;"></span>到(To)
								<span style="width: 21px;"></span>：
								<input class="Wdate" type="text" name="endDate"
									value="${ endDate}" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})" />
							</td>
						</tr>
						<tr>
							<td>
								状态(State)：
								<select name="runningWaterCard.cardStatus" id="cardStatus"
									style="width: 140px;"
									onMouseOver="createDept('cardStatus','RunningWaterCardAction!selectItem.action?tag=cardStatus')">
									<option value=""></option>
									<option value="${runningWaterCard.cardStatus}"
										selected="selected">
										${runningWaterCard.cardStatus}
									</option>
								</select>

							</td>
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
									卡号
									<br />
									Card number
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
									是否领料
									<br />
									Whether picking
								</th>
								<th align="center">
									制卡时间
									<br />
									Card time
								</th>
								<th align="center">
									操作
									<br />
									Operation
								</th>
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
										${card.cardNum}
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
										${card.receiveStatus}
									</td>
									<td>
										${card.createCardTime}
									</td>
									<td>
										<s:if test="procardId!=null">
											<a
												href="ProcardAction!findProcardView.action?id=${card.procardId}&pageStatus=history&viewStatus=${viewStatus}"
												target="showProView">生产进度</a>
										</s:if>
										<s:if test="'manager'==tag">
											/<a href="RunningWaterCardAction!getCardById.action?id=${id}">模板制卡</a>
											<br>
											/<a
												href="RunningWaterCardAction!getCardById.action?id=${id}&tag=bd">补打卡片</a>
										</s:if>

									</td>

									</tr>
								</s:iterator>
								<tr>
									<td colspan="11" align="right">
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
									<td style="font-size: 15px; color: red;">
										对不起，没有查到相关的标识贴信息
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
	</body>
</html>
