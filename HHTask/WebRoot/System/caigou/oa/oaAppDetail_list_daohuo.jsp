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
				<form
					action="oaAppDetailAction!findOADetail_1.action?powerTag=${powerTag}&tag=${tag}"
					method="post">
					<table class="table">
						<tr>
							<th>
								部门
								<br>
								Department
							</th>
							<th>
								<select name="oadetail.detailAppDept" style="width: 130px;"
									id="detailAppDept"
									onMouseOver="createDept('detailAppDept','oaAppDetailAction!findOASelect.action?tag=detailAppDept&powerTag=${powerTag}')">
									<option value="">
										选择部门
									</option>
									<option value="${oadetail.detailAppDept}">
										${oadetail.detailAppDept}
									</option>
								</select>
							</th>
							<th>
								计划月份
								<br>
								Plan Month
							</th>
							<th>
								<select name="oadetail.detailPlanMon" style="width: 130px;"
									id="detailPlanMon"
									onMouseOver="createDept('detailPlanMon','oaAppDetailAction!findOASelect.action?tag=detailPlanMon&powerTag=${powerTag}')">
									<option value="">
										选择月份
									</option>
									<option value="${oadetail.detailPlanMon}">
										${oadetail.detailPlanMon}
									</option>
								</select>
							</th>
							<th>
								状态
								<br>
								State
							</th>
							<th>
								<select name="oadetail.detailStatus" style="width: 130px;"
									id="detailStatus"
									onMouseOver="createDept('detailStatus','oaAppDetailAction!findOASelect.action?tag=detailStatus&powerTag=${powerTag}')">
									<option value="">
										选择状态
									</option>
									<option value="${oadetail.detailStatus}">
										${oadetail.detailStatus}
									</option>
								</select>
							</th>
							<th rowspan="3">
								<input type="submit" style="width: 90px; height: 30px;"
									value="查询(Query)" />
							</th>
						</tr>
						<tr>
							<th>
								物品名称
								<br>
								Item Name
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailAppName"
									value="${oadetail.detailAppName }" size="80px" />
							</th>
							<th>
								规格
								<br>
								Specifications
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailFormat" value="${oadetail.detailFormat}"
									size="80px" />
							</th>
							<th>
								编号
								<br />
								Serial number
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailSeqNum" value="${oadetail.detailSeqNum}"
									size="80px" />
							</th>
						</tr>
						<tr>
							<th>
								物品类别
								<br />
								Items category
							</th>
							<th>
								<select name="oadetail.detailChildClass" style="width: 130px;"
									id="detailChildClass"
									onMouseOver="createDept('detailChildClass','oaAppDetailAction!findOASelect.action?tag=detailChildClass&powerTag=${powerTag}')">
									<option value="">
										选择类别
									</option>
									<option value="${oadetail.detailChildClass}">
										${oadetail.detailChildClass}
									</option>
								</select>
							</th>
							<th>
								日期从
								<br />
								Date from
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到
								<br>
								To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
					</table>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
							<br>
							No.
						</th>
						<th align="center">
							部门
							<br />
							departments
						</th>
						<th align="center">
							计划月份 Program in January
						</th>
						<th align="center">
							类别
							<br>
							Categories
						</th>
						<th align="center">
							编号
							<br>
							Numbers.
						</th>
						<th align="center">
							名称
							<br>
							Numbers
						</th>
						<th align="center">
							规格
							<br>
							Specifications
						</th>
						<th align="center">
							单位
							<br>
							Units
						</th>
						<th align="center">
							申报数量
							<br>
							Declare quantity
						</th>
						<th align="center">
							预算单价
							<br>
							Declare quantity
						</th>
						<th align="center">
							申购日期
							<br>
							Purchase date
						</th>
						<th align="center">
							状态
							<br>
							Status
						</th>
						<th align="center">
							计划依据
							<br>
							Planned basis
						</th>
						<th align="center">
							项目编号
							<br>
							Item number
						</th>
						<th align="center">
							到货期限
							<br>
							Arrival date
						</th>
						<th align="center" style="width: 40px;">
							操作
							<br>
							Operations
						</th>
					</tr>

					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="detail">
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
								${detail.detailAppDept}
							</td>
							<td>
								${detail.detailPlanMon}
							</td>
							<td>
								${detail.detailChildClass}
							</td>
							<td>
								${detail.detailSeqNum}
							</td>
							<td>
								${detail.detailAppName}
							</td>
							<td>
								${detail.detailFormat}
							</td>
							<td>
								${detail.detailUnit}
							</td>
							<td>
								${detail.detailCount}
							</td>
							<td>
								${detail.detailBudgetMoney}
							</td>
							<td>
								${detail.detailAppDate}
							</td>
							<td>
								${detail.detailStatus}
							</td>
							<td>
								${detail.detailPlanAcco}
							</td>
							<td>
								${detail.detailItemId}
							</td>
							<td>
								${detail.detailArrDate}
							</td>
							<td>
								<a href="oaAppDetailAction!toaddWarehouseApp.action?id=${id}">申请入库111</a>
								<a
									href="CircuitRunAction_findAduitPage.action?id=${detail.epId}">审批动态</a>
							</td>
						</s:iterator>
						<tr>
							<td colspan="16" align="right">
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
							<td colspan="16" style="font-size: 15px; color: red;">
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
