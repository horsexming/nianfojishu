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
				style="width: 100%; font-weight: bold; height: 50px;" align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">

				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="saleBudgetAction!prepareSave.action"
						style="color: #ffffff">添加月度销售收入</a> &nbsp;&nbsp;&nbsp;
				</div>
			</div>

			<div align="center">

				<form action="saleBudgetAction!findAllBudget.action?powerTag=${powerTag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								预算月份
							</th>
							<th>
								<select name="saleBudget.planMonth" style="width: 150px;"
									id="planMonth"
									onMouseOver="createDept('planMonth','saleBudgetAction!selectStyle.action?tag=planMonth')">
									<option value="">
										选择预算月份
									</option>
									<option value="${saleBudget.planMonth}">
										${saleBudget.planMonth}
									</option>


								</select>
							</th>

							<th>
								状态
							</th>
							<th>
								<select name="saleBudget.status" style="width: 150px;"
									id="status"
									onMouseOver="createDept('status','saleBudgetAction!selectStyle.action?tag=status')">
									<option value="">
										选择状态
									</option>
									<option value="${saleBudget.status}">
										${saleBudget.status}
									</option>
								</select>
							</th>
							<th>
								条码
							</th>
							<th>
								<input type="text" name="saleBudget.barcode"
									value="${saleBudget.barcode}" />
							</th>
							<th>
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询" />
							</th>
							<th>
								<s:if test="%{tag=='manger'}">
									<input type="button" style="width: 90px; height: 30px;"
										value="数据导出" onclick="exportExcel(this.form);todisabledone(this)" data="downData"/>
								</s:if>
							</th>
						</tr>
						</form>
						<table class="table">
							<tr bgcolor="#c0dcf2" height="30px"
								style="border-collapse: separate;">
								<th align="center">
									序号
								</th>
								<th align="center">
									计划月份
								</th>
								<th align="center">
									销售总额
								</th>
								<th align="center">
									录入初始时间
								</th>
								<th align="center">
									录入人
								</th>
								<th align="center">
									条码
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									操作
								</th>
							</tr>

							<s:if test="{list.size()>0}">
								<s:iterator value="list" status="se" id="bxd">
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
										${bxd.planMonth}
									</td>
									<td>
										${bxd.saleMoney}
									</td>
									<td>
										${bxd.inputTime}
									</td>
									<td>
										${bxd.userName}
									</td>
									<td>
										${bxd.barcode}
									</td>
									<td>
										${bxd.status}
									</td>
									<td>
										<s:if test="%{'manager'==powerTag}">
											<s:if test='"审核".equals(#bxd.status)'>
												<a
													href="saleBudgetAction!updateBudgetById.action?id=${id}&tag=yes">同意</a>&nbsp;&nbsp;&nbsp;
												<a onClick="return confirm('确定要打回该月的销售收入预算吗？')"
													href="saleBudgetAction!updateBudgetById.action?id=${id}&tag=no">打回</a>&nbsp;&nbsp;&nbsp;
											</s:if>
											<a
												href="saleBudgetAction!findOneBudget.action?id=${id}&tag=find">查看</a>&nbsp;&nbsp;&nbsp;
										</s:if>
										<s:else>
											<s:if test="powerTag=='soru'">
												<a href="saleBudgetAction!subSaleBudget.action?id=${id}">添加/更新月度销售汇总</a>
											</s:if>
											<s:else>
												<s:if test='"申报".equals(#bxd.status)'>
													<a
														href="saleBudgetAction!findOneBudget.action?id=${id}&tag=update">修改</a>&nbsp;&nbsp;&nbsp;
											</s:if>
												<s:else>
													<a
														href="saleBudgetAction!findOneBudget.action?id=${id}&tag=find">查看</a>&nbsp;&nbsp;&nbsp;
											</s:else>
											</s:else>
										</s:else>

										<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
										<s:if test="#bxd.status!='申报'">
											<a href="CircuitRunAction_findAduitPage.action?id=${bxd.epId}">审批动态</a>
										</s:if>
									</td>
									</tr>
								</s:iterator>
								<tr>
									<td colspan="8" align="right">
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
										对不起，没有查到相关的预算信息
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
