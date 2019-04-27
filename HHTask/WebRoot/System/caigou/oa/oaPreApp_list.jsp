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
					<a href="saleBudgetAction!prepareSave.action"
						style="color: #ffffff">采购申请</a> &nbsp;&nbsp;&nbsp;
					<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			<div align="center">
				<form action="oaAppDetailAction!findPreApp.action?powerTag=${powerTag}"
					method="post">
					<input type="hidden" name="tag" value="${tag}" />
					<table class="table">
						<tr>
							<th>
								部门<br>Sector 
							</th>
							<th>
								<select name="opApply.appDept" style="width: 130px;" id="detailAppDept"
									onMouseOver="createDept('detailAppDept','oaAppDetailAction!findOASelect.action?tag=detailAppDept&powerTag=${powerTag}')">
									<option value="">
										选择部门
									</option>
									<s:if test="opApply.appDept!=null">
										<option selected="selected" value="${opApply.appDept}">
											${opApply.appDept}
										</option>
									</s:if>
									<s:else>
										<option value="${opApply.appDept}">
											${opApply.appDept}
										</option>
									</s:else>
							</th>
							<th>
								计划月份<br>Plan Month
							</th>
							<th>
								<select name="opApply.appPlanMon" style="width: 130px;"
									id="detailPlanMon"
									onMouseOver="createDept('detailPlanMon','oaAppDetailAction!findOASelect.action?tag=detailPlanMon&powerTag=${powerTag}')">
									<option value="">
										选择月份
									</option>
									<s:if test="opApply.appPlanMon!=null">
										<option selected="selected" value="${opApply.appPlanMon}">
											${opApply.appPlanMon}
										</option>
									</s:if>
									<s:else>
										<option value="${opApply.appPlanMon}">
											${opApply.appPlanMon}
										</option>
									</s:else>
										
								</select>
							</th>
							<th>
								编号 <br>No
							</th>
							<th>
								<input type="text" name="opApply.appOrdnumber"
									value="${opApply.appOrdnumber}">
							</th>
							<th rowspan="3">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询(Query)" />
							</th>
						</tr>

						<tr>
							<th>
								条码<br>Barcode
							</th>
							<th>
								<input type="text" name="opApply.appBarcode"
									value="${opApply.appBarcode}">
							</th>
							<th>
								日期从<br/>Date from
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到<br/>To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号<br>No. 
								</th>
								<th align="center">
									部门<br>Sector 
								</th>
								<th align="center">
									计划月份<br>Plan Month
								</th>
								<th align="center">
									创建人 <br>Created 
								</th>
								<th align="center">
									创建时间<br>Creation time
								</th>
								<th align="center">
									编号 <br>No
								</th>

								<th align="center">
									操作<br>
									 Operation
								</th>
							</tr>

							<s:if test="{listPrint.size()>0}">
								<tr>
									<td colspan="7" align="center"
										style="font-size: 15px; color: red;">
										待打印的申报信息(Declaration information to be printed)
									</td>
								</tr>
								<s:iterator value="listPrint" status="se" id="print">
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
										${print.appDept}
									</td>
									<td>
										${print.appPlanMon}
									</td>
									<td>
										${print.appApplier}
									</td>
									<td>
										${print.appDate}
									</td>
									<td>
										${print.appOrdnumber}
									</td>

									<td>
										<a href="oaAppDetailAction!findPrintList.action?id=${print.id}&tag=print" target="form">打印(Print)</a>
										<br />
										<a href="oaAppDetailAction!findPreDetailList.action?id=${print.id}&tag=print&powerTag=buy">明细(明细)</a>
										<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
									</td>
									</tr>
								</s:iterator>
							</s:if>
							<s:if test="{list.size()>0}">
								<tr>
									<td colspan="7" align="center"
										style="font-size: 15px; color: gray;">
										申报历史记录信息(Declare history information)
									</td>
								</tr>
								<s:iterator value="list" status="se2" id="prea">
									<s:if test="#se2.index%2==1">
										<tr align="center" bgcolor="#e6f3fb"
											onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'#e6f3fb')">
									</s:if>
									<s:else>
										<tr align="center" onmouseover="chageBgcolor(this)"
											onmouseout="outBgcolor(this,'')">
									</s:else>
									<td>
										<s:property value="#se2.index+1" />
									</td>
									<td>
										${prea.appDept}
									</td>
									<td>
										${prea.appPlanMon}
									</td>
									<td>
										${prea.appApplier}
									</td>
									<td>
										${prea.appDate}
									</td>
									<td>
										${prea.appOrdnumber}
									</td>
									<td>
										<a href="oaAppDetailAction!findPrintList.action?id=${prea.id}&tag=select">补打(Up fight)</a>
										<br />
										<a href="oaAppDetailAction!findPreDetailList.action?id=${prea.id}&powerTag=${powerTag}&tag=all">明细(Details)</a>
										<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
									</td>
								</tr>
								</s:iterator>
								<tr>
									<td colspan="7" align="right">
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
									<td colspan="7" style="font-size: 15px; color: red;">
										对不起，没有查到相关的申报信息
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
	<script type="text/javascript">
function alsoAdd() {
	window.location = "oaAppDetailAction!preSaveOADetail.action";
}
</script>
</html>
