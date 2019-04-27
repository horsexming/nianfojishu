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
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv"
			style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv" style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">您正在查看申购明细</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="showProcess" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 400px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
				<%--
					oaAppDetailAction!findOADetail.action?powerTag=${powerTag} --%>
				<form action="oaAppDetailAction!findPreDetailList.action?powerTag=${powerTag}" method="post">
					<input type="hidden" name="id" value="${id}"/>
					
					<table class="table">
						<tr>
							<th>部门</th>
							<th>
								<select name="oadetail.detailAppDept" style="width: 130px;" id="detailAppDept"
							onMouseOver="createDept('detailAppDept','oaAppDetailAction!findOASelect.action?tag=detailAppDept&powerTag=${powerTag}')">
									<option value="">
										选择部门
									</option>
									<option value="${oadetail.detailAppDept}">
										${oadetail.detailAppDept}
									</option>
							</th>
							<th>预算月份</th>
							<th>
								<select name="oadetail.detailPlanMon" style="width: 130px;" id="detailPlanMon"
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
									value="查询" />
							</th>
						</tr>
						<tr>
							<th>
								物品名称
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailAppName"
									value="${oadetail.detailAppName }" size="80px" />
							</th>
							<th>
								规格
							</th>
							<th>
								<input type="text" style="width: 130px;"
									name="oadetail.detailFormat" value="${oadetail.detailFormat}"
									size="80px" />
							</th>
							<th>
								编号
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
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到
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
									序号
								</th>
								<th align="center">
									部门
								</th>
								<th align="center">物料编码</th>
								<th align="center">
									计划月份
								</th>
								<th align="center">
									类别
								</th>
								<th align="center">
									编号
								</th>
								<th align="center">
									名称
								</th>
								<th align="center">
									规格
								</th>
								<th align="center">
									单位
								</th>
								<th align="center">
									申报数量
								</th>
								<th align="center">
									预算单价
								</th>
								<th align="center">
									到货期限
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									计划依据
								</th>
								<th align="center">
									项目编号
								</th>
								<th align="center" style="width: 40px;">
									操作
								</th>
							</tr>

							<s:if test="list.size>0">
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
									<td>${detail.wlcode}</td>
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
										${detail.detailArrDate}
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
										<input type="button" id="" value="比价" onclick="historyPrice(${detail.id})" />
										<s:if test='"预申请".equals(#detail.detailStatus) || "打回".equals(#detail.detailStatus)'>
											<a href="oaAppDetailAction!getOADetailById.action?id=${id}&tag=update&cpage=${cpage}">修改</a>
											<br />
											<a onClick="return confirm('确定要删除该条记录吗？')"
												href="oaAppDetailAction!deleteOADetailById.action?id=${id}&cpage=${cpage}">删除</a>
										</s:if>

										<s:if test="%{'buy'==powerTag}">
											<input type="button" id="" value="修改" onclick="bijia(${id},${cpage})" />
											<br />
										</s:if>
										<!--  判断状态显示
								<a onClick="return confirm('确定要删除该条记录吗？')" href="saleBudgetAction!deleteBaoXiaoDanById.action?id=${id}" >删除</a>
								-->
									</td>
									</tr>
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
									<td colspan="15" style="font-size: 15px; color: red;">
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
	function historyPrice(obj) {
	var id = obj.valueOf();
	$("#showProcess").attr("src",
			"GoodsStoreAction!findSameProductPrice.action?id=" + id+"&tag=oaApp");
	chageDiv('block');
	}
function bijia(obj, obj2) {
	var id = obj.valueOf();
	var cpage = obj2.valueOf();
	$("#showProcess").attr(
			"src",
			"oaAppDetailAction!getOADetailById.action?id=" + id + "&cpage="
					+ cpage + "&tag=buy");
	chageDiv('block');
}
</script>
</html>
