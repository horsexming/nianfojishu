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
			<div id="closeDiv"
				style="background: url(<%=basePath%>/images/bq_bg2.gif); width: 100%;">
				<table style="width: 100%">
					<tr>
						<td>
							<span id="title">工序信息</span>
						</td>
						<td align="right">
							<img alt="" src="<%=basePath%>/images/closeImage.png" width="30"
								height="32" onclick="chageDiv('none')">
						</td>
					</tr>
				</table>
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<form action="goodsAction!updateDeliveryToSh.action" method="post">
						<input name="id" value="${waigouDeliveryGoods.id}">
						<input name="pagestatus" value="${pageStatus}">
						<table class="table">
							<tr>
								<th colspan="2">
									送货单二次验收确认
								</th>
							</tr>
							<tr>
								<td align="right">
									验收人:
								</td>
								<td>
									<input name="waigouDeliveryGoods.ysContacts" />
								</td>
							</tr>
							<tr>
								<td align="right">
									手机号:
								</td>
								<td>
									<input name="waigouDeliveryGoods.ysContactsPhone" />
								</td>
							</tr>
							<tr>
								<th colspan="2">
									<input type="submit" value="确认" class="input" />
								</th>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="printDiv">
					<br />
					<div style="font-weight: bolder; font-size: 30px; height: 50px;"
						align="center">
						送货单明细
						<s:if test="pageStatus=='sh'||pageStatus=='qs'||pageStatus=='nb'"></s:if>
						<s:else>
							<s:if test='waigouDeliveryGoods.status!="待确认"'>
								<a
									href="goodsAction!findDNDetailForPrint.action?id=${waigouDeliveryGoods.id}">打印</a>
							</s:if>
							<s:else>
								<font style="color: gray;">打印</font>
							</s:else>
						</s:else>
					</div>
					<s:if test="waigouDeliveryGoods!=null">
						<table
							style="border-collapse: collapse; width: 95%; line-height: 20px;">
							<tr>
								<td align="left">
									<img
										src="barcode.action?msg=${waigouDeliveryGoods.planNumber}&type=code128"
										width="180px" height="60px" />
								</td>
								<td>
									更新时间:${waigouDeliveryGoods.addTime}
								</td>
							</tr>
							<tr>
								<td align="left">
									供应商:${waigouDeliveryGoods.gysName}
								</td>
								<td>
									客户名称:${waigouDeliveryGoods.customer}
								</td>
							</tr>
							<tr>
								<td align="left">
									供应商编号:${waigouDeliveryGoods.userCode}
								</td>
								<td>
									地址:${companyInfo.address}
								</td>
							</tr>
							<tr>
								<td align="left">
									供联系人:${waigouDeliveryGoods.gysContacts}
								</td>
								<td>
									客联系人:${waigouDeliveryGoods.contacts}
								</td>
							</tr>
							<tr>
								<td align="left">
									供联系电话:${waigouDeliveryGoods.gysPhone}
								</td>
								<td>
									客联系电话:${waigouDeliveryGoods.contactsPhone}
								</td>
							</tr>
							<tr>
								<td align="left">
									出发地址:${waigouDeliveryGoods.chufaDizhi}
								</td>
								<td>
									到达地址:${waigouDeliveryGoods.daodaDizhi}
								</td>
							</tr>
							<tr>
								<td align="left">
									送货人姓名: ${waigouDeliveryGoods.shContacts}
								</td>
								<td>
									送货人联系电话: ${waigouDeliveryGoods.shContactsPhone}
								</td>
							</tr>
							<tr>
								<td align="left" colspan="2">
									车牌: ${waigouDeliveryGoods.chepai}
								</td>
							</tr>
						</table>
					</s:if>
					<br />
					<br />
					<form action="WaigouwaiweiPlanAction!querenDelivery.action"
						method="post">
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
							<s:if test="pageStatus=='dqr'">
								<tr align="center">
									<td colspan="19">
										<div id="div_sub">
											<input type="hidden" value="${waigouDeliveryGoods.id}"
												name="id" />
											<input type="submit" value="确认" class="input"
												id="shNumbersub" />
										</div>
									</td>
								</tr>
							</s:if>
						</table>
						<div style="font-size: 22px; font-weight: bolder;">
							<s:if test="pageStatus=='sh'">
								<a
									href="goodsAction!updateDeliveryToSh.action?id=${waigouDeliveryGoods.id}&pagestatus=${pageStatus}">确认送货</a>
							</s:if>
							<s:elseif test="pageStatus=='qs'">
								<a
									href="goodsAction!updateDeliveryToSh.action?id=${waigouDeliveryGoods.id}&pagestatus=${pageStatus}">确认签收并验收</a>
								<br />
								<a href="javascript:;"
									onclick="toerciyan(${waigouDeliveryGoods.id})">只签收，指定验收人员</a>
							</s:elseif>
							<s:elseif test="pageStatus=='ys'">
								<a
									href="goodsAction!updateDeliveryToSh.action?id=${waigouDeliveryGoods.id}&pagestatus=${pageStatus}">验收</a>
							</s:elseif>
						</div>
					</form>
				</div>
				<br />
			</div>
			<%--<div align="center">
				<s:if
					test='waigouDeliveryGoods.status=="待打印"||waigouDeliveryGoods.status=="送货中"'>
					<s:if test='waigouDeliveryGoods.status=="送货中"'>
						<input type="button" value="确认送货" class="input"
							onclick="javascript:location.href='WaigouwaiweiPlanAction!updateDeliveryToPrint.action?id=${waigouDeliveryGoods.id}';" />
					</s:if>
				</s:if>
			</div>
		--%>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function printDelivery() {
	pagePrint('printDiv');
	$.ajax( {
		type : "POST",
		url : "WaigouwaiweiPlanAction!updateDeliveryToPrint.action",
		data : {
			id : "${waigouDeliveryGoods.id}"
		},
		dataType : "json",
		cache : false,//防止数据缓存
		success : function(msg) {
			alert(msg);
		}
	});
}

function check(num) {
	var size = $("#listsize").val();
	var shNumber = $("#shNumber" + num).val();
	var shNumber1 = $("#shNumber_" + num).val();
	shNumber = parseInt(shNumber);
	shNumber1 = parseInt(shNumber1);
	if (shNumber > shNumber1) {
		alert("不能大于原本的送货数量");
		$("#shNumber" + num).val("");
	}
	var bool = true;
	for ( var i = 0; i < size; i++) {
		if ($("#shNumber" + i).val() == '') {
			bool = false;
			break;
		}
	}
	if (bool) {
		$("#shNumbersub").removeAttr("disabled");
	} else {
		$("#shNumbersub").attr("disabled", "disabled");
	}
}

function toerciyan(id) {
	chageDiv("block");
}
</script>
	</body>
</html>
