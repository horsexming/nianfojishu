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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>

			<div align="center">
				<h3>
					选择入库
				</h3>
				<form
					action="oaAppDetailAction!findOADetail.action?tag=oaAppDetail_selectDetail"
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
									<s:if test="oadetail.detailAppDept!=null">
										<option selected="selected" value="${oadetail.detailAppDept}">
											${oadetail.detailAppDept}
										</option>
									</s:if>
									<s:else>
										<option selected="selected" value="${oadetail.detailAppDept}">
											${oadetail.detailAppDept}
										</option>
									</s:else>
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
									<s:if test="oadetail.detailPlanMon!=null">
										<option selected="selected" value="${oadetail.detailPlanMon}">
											${oadetail.detailPlanMon}
										</option>
									</s:if>
									<s:else>
										<option value="${oadetail.detailPlanMon}">
											${oadetail.detailPlanMon}
										</option>
									</s:else>
								</select>
							</th>
							<th>
								状态
								<br>
								State
							</th>
							<th>
								<select name="oadetail.detailStatus" style="width: 130px;">
									<option value="">
										选择状态
									</option>
									<s:iterator id="status" value="{'待入库','入库'}">
										<s:if test="#status==oadetail.detailStatus">
											<option selected="selected" value="${status}">
												${status}
											</option>
										</s:if>
										<s:else>
											<option value="${status}">
												${status}
											</option>
										</s:else>
									</s:iterator>
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
									<s:if test="oadetail.detailChildClass!=null">
										<option selected="selected"
											value="${oadetail.detailChildClass}">
											${oadetail.detailChildClass}
										</option>
									</s:if>
									<s:else>
										<option value="${oadetail.detailChildClass}">
											${oadetail.detailChildClass}
										</option>
									</s:else>
								</select>
							</th>
							<th>
								日期从
								<br />
								Date from
							</th>
							<th>
								<input class="Wdate" type="text" name="startDate" size="15"
									value="${startDate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
							<th>
								到
								<br>
								To
							</th>
							<th>
								<input class="Wdate" type="text" name="endDate" size="15"
									value="${endDate}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</th>
						</tr>
					</table>
				</form>
				<h3>
					入库申请详细
				</h3>
				<form action="storage_statisticalProcurement.action" method="post"
					onsubmit="return vali()">
					<table class="table">
<%--						<tr>--%>
<%--							<td colspan="17">--%>
<%--								<input type="submit" style="width: 80px; height: 50px;"--%>
<%--									value="入库" />--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr bgcolor="#c0dcf2" height="50px" align="center">
<%--							<td></td>--%>
							<td>
								序号
							</td>
							<td>
								部门
							</td>
							<td>
								物料编码
							</td>
							<td>
								类别
							</td>
							<td>
								编号
							</td>
							<td>
								名称
							</td>
							<td>
								规格
							</td>
							<td>
								单位
							</td>
							<td>
								数量
							</td>
							<td>
								已入库数量
							</td>
							<td>
								计划月份
							</td>
							<td>
								到货期限
							</td>
							<td>
								状态
							</td>
							<td>
								计划依据
							</td>
							<td>
								预算金额
							</td>
							<td>
								加急
							</td>
							<td>
								操作
							</td>
						</tr>
						<s:if test="list.size()>0">
							<s:iterator value="list" id="pageList" status="pageStatus">
								<s:if test="#pageStatus.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
<%--								<td>--%>
<%--									<input type="checkbox" name="vsto.selected"--%>
<%--										value="${pageList.id }" />--%>
<%--								</td>--%>
								<td>
									<s:if test="#pageStatus.index%2==1">
										<font>
									</s:if>
									<s:else>
										<font color="#c0dcf2">
									</s:else>
									<s:property value="#pageStatus.index+1" />
									</font>
								</td>
								<td>
									${pageList.detailAppDept}
								</td>
								<td>
									${pageList.wlcode}
								</td>
								<td>
									${pageList.detailChildClass}
								</td>
								<td>
									${pageList.detailSeqNum}
								</td>
								<td>
									${pageList.detailAppName}
								</td>
								<td>
									${pageList.detailFormat}
								</td>
								<td>
									${pageList.detailUnit}
								</td>
								<td>
									${pageList.detailCount}
								</td>
								<td>
									${pageList.rgdetailCount}
								</td>
								<td>
									${pageList.detailPlanMon}
								</td>
								<td>
									${pageList.detailArrDate}
								</td>
								<td>
									${pageList.detailStatus}
								</td>
								<td>
									${pageList.detailPlanAcco}
								</td>
								<td>
									${pageList.detailBudgetMoney}
								</td>
								<td>
									${pageList.detailIsBusy}
								</td>
								<td>
									<%--<a href="storage_getOaAppDetail.action?vsto.oaDetailId=${pageList.id}&
										vsto.applyForNum=${vsto.applyForNum}&vsto.jump=select&vsto.number=${pageList.id}">入库</a> --%>
									<s:if test="'待入库'==oadetail.detailStatus">
										<a
											href="oaAppDetailAction!getOaAppDetail.action?storageWay=single&oadetail.id=${pageList.id}">入库</a>
									</s:if>
								</td>
								</tr>
							</s:iterator>
							<tr>
								<s:if test="errorMessage==null">
									<td colspan="17" align="right">
										第
										<font color="red"><s:property value="cpage" /> </font> /
										<s:property value="total" />
										页
										<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
											styleClass="page" theme="number" />
								</s:if>
								<s:else>
									<td colspan="17" align="center" style="color: red">
										${errorMessage}
								</s:else>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="17" style="font-size: 15px; color: red;">
									对不起，没有查到相关的入库信息
								</td>
							</tr>
						</s:else>
<%--						<tr>--%>
<%--							<td colspan="17">--%>
<%--								<input type="submit" style="width: 80px; height: 50px;"--%>
<%--									value="入库" />--%>
<%--							</td>--%>
<%--						</tr>--%>
					</table>
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function vali() {
	var selectList = document.getElementsByName("vsto.selected");
	for ( var i = 0; i < selectList.length; i++) {
		if (selectList[i].checked) {
			return true;
		}
	}
	alert("请选择入库物品！谢谢");
	return false;
}
</script>
	</body>
</html>
