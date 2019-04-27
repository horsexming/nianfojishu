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
				送货单模式 &nbsp;&nbsp;
				<a
					href="goodsAction!findDeliveryNote.action?pageStatus=${pageStatus}">送货单模式</a>
				<form
					action="goodsAction!findDeliveryNoteDetail_1.action"
					method="post">
					<table class="table">
						<tr>
							<th align="right">
								订单号:
							</th>
							<td>
								<input type="text" name="waigoudGoodsD.cgOrderNum"
									value="${waigoudGoodsD.cgOrderNum}" />
							</td>
							<th align="right">
								件号:
							</th>
							<td>
								<input type="text" name="waigoudGoodsD.markId"
									value="${waigoudGoodsD.markId}" />
							</td>
						</tr>
						<tr>
							<th align="right">
								零件名称:
							</th>
							<td>
								<input type="text" name="waigoudGoodsD.proName"
									value="${waigoudGoodsD.proName}" />
							</td>
							<th align="right">
								状态:
							</th>
							<td>
								<select name="waigoudGoodsD.status">
									<s:if
										test="waigoudGoodsD.status == null ||waigoudGoodsD.status == '' ">
										<option value="${waigoudGoodsD.status}">
											${waigoudGoodsD.status}
										</option>
									</s:if>
									<s:else>
										<option value="${waigoudGoodsD.status}">
											${waigoudGoodsD.status}
										</option>
										<option value=""></option>
									</s:else>
									<option value="待确认">
										待确认
									</option>
									<option value="待打印">
										待打印
									</option>
									<option value="待送货">
										待送货
									</option>
									<option value="已签收">
										已签收
									</option>
									<option value="已验收">
										已验收
									</option>
									<option value="退货带核对">
										退货带核对
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								添加日期从:
							</th>
							<td>
								<input type="text" name="firsttime" class="Wdate"
									value="${firsttime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
							<th align="right">
								止:
							</th>
							<td>
								<input type="text" name="endtime" class="Wdate"
									value="${endtime}"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" />
							</td>
						</tr>
					</table>
					<input type="hidden" value="${pageStatus}" name="pageStatus" />
					<input type="submit" value="查询" class="input" />
<%--					<input type="button" value="导出" class="input"--%>
<%--						onclick="exportExcel(this.form)" />--%>
				</form>
				<table class="table">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							序号
						</th>
						<th align="center">
							订单号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							零件名称
						</th>
						<th align="center">
							规格
						</th>
						<th align="center">
							图号
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							箱(包)数
						</th>
						<th align="center">
							送货数量
						</th>
						<s:if test="pageStatus=='qs'||pageStatus=='ys'">
							<th align="center">
								收货数量
							</th>
						</s:if>
						<th align="center">
							送货时间
						</th>
						<th align="center">
							签收数量
						</th>
						<th align="center">
							确认时间
						</th>
						<th align="center">
							合格数量
						</th>
						<th align="center">
							检验时间
						</th>
						<th align="center">
							入库时间
						</th>
						<th align="center">
							状态
						</th>
						<th align="center">
							备注
						</th>
						<th align="center">
							其他
							<input type="hidden" value="<s:property value="list.size()"/>"
								id="listsize" />
						</th>
					</tr>
					<s:iterator value="lists" id="pageWgww2" status="pageStatus2">
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
							<input type="hidden" value="${pageWgww2.id}"
								name="list_wdd[${pageStatus2.index}].id" />
							<input type="hidden" value="${pageWgww2.shNumber}"
								id="shNumber_${pageStatus2.index}" />
						</td>
						<td>
							${pageWgww2.cgOrderNum}
						</td>
						<td>
							${pageWgww2.markId}
						</td>
						<td>
							${pageWgww2.proName}
						</td>
						<td>
							${pageWgww2.specification}
						</td>
						<td>
							${pageWgww2.tuhao}
						</td>
						<td>
							${pageWgww2.banben}
						</td>
						<td>
							${pageWgww2.unit}
						</td>
						<td>
							${pageWgww2.ctn}
						</td>
						<s:if test="pageStatus=='qs'||pageStatus=='ys'">
							<th id="shth_${pageStatus2.index}" align="right">
								${pageWgww2.shNumber}
							</th>
							<th>
								<input type="text" style="width: 100px; height: 20px;"
									value="${pageWgww2.shNumber}">
							</th>
						</s:if>
						<s:else>
							<th id="shth_${pageStatus2.index}" align="right">
								${pageWgww2.shNumber}
							</th>
						</s:else>
						<td>
							${pageWgww2.printTime}
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
							<s:else>${pageWgww2.hgNumber}</s:else>
						</td>
						<td>
							${pageWgww2.jianyanTime}
						</td>
						<td>
							${pageWgww2.rukuTime}
						</td>
						<td>
							${pageWgww2.status}
						</td>
						<td>
							${pageWgww2.remarks}
						</td>
						<td>
							<s:if test="pageStatus!='sh'">
								<a
									href="ProcardAction!findProcardForQx.action?procard.markId=${pageWgww2.markId}&procard.selfCard=${pageWgww2.selfCard}&nums=${pageWgww2.shNumber}">产品生产记录</a>
							</s:if>
						</td>
					</s:iterator>
					<tr>
						<th colspan="9"></th>
						<th>
							${sumNum}
						</th>
						<th></th>
						<th>
							${sumbhsprice}
						</th>
						<th></th>
						<th>
							${sumMoney}
						</th>
						<th colspan="10"></th>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<SCRIPT type="text/javascript">
	
	function exportExcel(obj){//zhaobiaoAction!listAll.action
		obj.action = "WaigouwaiweiPlanAction!exportExcelWaigouDelivery.action";
	 	obj.submit();
	  	obj.action = "WaigouwaiweiPlanAction!findDeliveryNote.action";
	 	}
	</SCRIPT>
	</body>
</html>
