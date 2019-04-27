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
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="printDiv">
					<br />
					<div style="font-weight: bolder;" align="center">
						<table class="table" style="width: 70%">
							<tr>
								<th colspan="2" style="font-size: 30px;">
									${waigouPlan.markId}的送货明细
								</th>
							</tr>
							<tr>
								<td>
									件号: ${waigouPlan.markId}
								</td>
								<td>
									名称: ${waigouPlan.proName}
								</td>
							</tr>
							<tr>
								<td>
									规格: ${waigouPlan.specification}
								</td>
								<td>
									图号: ${waigouPlan.tuhao}
								</td>
							</tr>
							<tr>
								<td>
									版本: ${waigouPlan.banben}
								</td>
								<td>
									版次: ${waigouPlan.banci}
								</td>
							</tr>
							<tr>
								<td>
									供货属性: ${waigouPlan.kgliao}
								</td>
								<td>
									单位: ${waigouPlan.unit}
								</td>
							</tr>
							<tr>
								<td colspan="2">
									交付进度: ${waigouPlan.number-waigouPlan.syNumber}/
									${waigouPlan.number}
								</td>
							</tr>
<%--							<tr>--%>
<%----%>
<%--								<td>--%>
<%--									含税单价:--%>
<%--									<a--%>
<%--										href="PriceAction!findPriceById.action?id=${waigouPlan.priceId}&statue=mingxi"--%>
<%--										target="_showPrice">${waigouPlan.hsPrice}</a>--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									不含税单价: ${waigouPlan.price}--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr>--%>
<%--								<td>--%>
<%--									税率: ${waigouPlan.taxprice}%--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									总额: ${waigouPlan.money}--%>
<%--								</td>--%>
<%--							</tr>--%>
							<tr>
								<td colspan="2">
									确认 时间: ${waigouPlan.querenTime}
								</td>
							</tr>
						</table>
					</div>
					<br />
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								送货时间
							</th>
							<th align="center">
								箱(包)数
							</th>
							<th align="center">
								送货数量
							</th>
							<th align="center">
								确认数量
							</th>
							<th align="center">
								确认时间
							</th>
							<th align="center">
								合格数量
							</th>
							<th align="center">
								退货数量
							</th>
							<th align="center">
								检验时间
							</th>
							<th align="center">
								状态
							</th>
							<th align="center">
								备注
							</th>
							<th align="center">
								签收人
							</th>
							<th align="center">
								送货单
							</th>
						</tr>
						<s:iterator value="list" id="pageWgww2" status="pageStatus2">
							<s:if test="#pageStatus2.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" style="height: 50px;"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									style="height: 50px;" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#pageStatus2.index+1" />
							</td>
							<td>
								${pageWgww2.printTime}
							</td>
							<td>
								${pageWgww2.ctn}
							</td>
							<td>
								${pageWgww2.shNumber}
							</td>
							<td>
								<s:if test="#pageWgww2.qrNumber<#pageWgww2.shNumber">
									<font color="red">${pageWgww2.qrNumber}</font>
								</s:if>
								<s:else>${pageWgww2.qrNumber}</s:else>
							</td>
							<td>
								${pageWgww2.querenTime}
							</td>
							<td>
								<s:if test="#pageWgww2.hgNumber<#pageWgww2.qrNumber">
									<font color="red">${pageWgww2.hgNumber}</font>
								</s:if>
								<s:else>${pageWgww2.hgNumber} </s:else>
							</td>
							<td>
								<s:if test="#pageWgww2.tuihuoNum!=null && #pageWgww2.tuihuoNum>0">
									<font color="red">${pageWgww2.tuihuoNum}</font>
								</s:if>
							</td>
							<td>
								${pageWgww2.jianyanTime}
							</td>
							<td>
								${pageWgww2.status}
							</td>
							<td>
								${pageWgww2.remarks}
							</td>
							<td>
								${pageWgww2.qrUsersName}
							</td>
							<td>
								<a
									href="WaigouwaiweiPlanAction!findDeliveryNoteDetail.action?id=${pageWgww2.waigouDelivery.id}">送货单</a>
								<s:if test='#pageWgww2.status!="待检验"'>
									<a
										href="OsRecord_showScope.action?record.wwddId=${pageWgww2.id}">检验记录</a>
								</s:if>
							</td>
						</s:iterator>
						<tr>
							<th colspan="13">
								物料位置动态:
							</th>
						</tr>
						<tr>
							<td colspan="13">
								${waigouPlan.wlWeizhiDt}
							</td>
						</tr>
					</table>
				</div>
				<br />
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
</script>
	</body>
</html>
