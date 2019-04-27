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
		<STYLE type="text/css">
</STYLE>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<div id="printDiv">
					<br />
					<%--<iframe id="qxmIframe"
						src="<%=basePath%>System/wuliu/WarehouseNumber_kwm.jsp?number=<%=basePath%>/loginforPhone.jsp?id=${waigouDeliveryGoods.id}&ku=${waigouDeliveryGoods.planNumber}"
						marginwidth="0" marginheight="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>

					--%>

					<div id="printDiv2">
						<div id="ercode2">
						</div>
						物流智能全息码
					</div>
					<input type="button" value="打印" class="input"
						onclick="printDelivery('printDiv2')" />
					<br />
					<br />
					<br />

					<div style="font-weight: bolder; font-size: 30px; height: 50px;"
						align="center">
						送货单
					</div>
					<s:if test="waigouDeliveryGoods!=null">
						<table
							style="border-collapse: collapse; width: 95%; line-height: 20px;">
							<tr>
								<td align="left">
									<div style="float: left;">
										<img
											src="barcode.action?msg=${waigouDeliveryGoods.planNumber}&type=code128"
											width="180px" height="60px" />
									</div>
									<div id="ercode">
									</div>
									<%--									<img alt="" src="" id="myimg">--%>
									<%--									<div id="barcode"--%>
									<%--										style="height: 60px; width: 60px; float: left;">--%>
									<%--									</div>--%>
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
					<table class="table">
						<tr bgcolor="#c0dcf2" height="50px">
							<th align="center">
								序号
							</th>
							<th align="center">
								采购订单号
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
								数量
							</th>
							<s:if
								test='waigouDeliveryGoods.status!="待打印" && waigouDeliveryGoods.status!="送货中"'>
								<th>
									签收数量
								</th>
								<s:if test='waigouDeliveryGoods.status!="待打印"'>
									<th>
										合格数量
									</th>
								</s:if>
							</s:if>
							<th align="center">
								单位
							</th>
							<th align="center">
								箱(包)数
							</th>
							<th align="center">
								备注
							</th>
						</tr>
						<!-- 
						<tr>
							<th align="center">
								No.
							</th>
							<th align="center">
								PO
							</th>
							<th align="center">
								MarkId
							</th>
							<th align="center">
								Desription
							</th>
							<th align="center">
								version
							</th>
							<th align="center">
								AP
							</th>
							<th align="center">
								Qty
							</th>
							<th align="center">
								UOM
							</th>
							<th align="center">
								Ctn
							</th>
							<th align="center">
								Remarks
							</th>
						</tr> -->
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
							<td align="right">
								${pageWgww2.shNumber}
							</td>
							<s:if
								test='waigouDeliveryGoods.status!="待打印" && waigouDeliveryGoods.status!="送货中"'>
								<th align="right">
									${pageWgww2.qrNumber}
								</th>
								<s:if test='waigouDeliveryGoods.status!="待打印"'>
									<th align="right">
										${pageWgww2.hgNumber}
									</th>
								</s:if>
							</s:if>
							<td>
								${pageWgww2.unit}
							</td>
							<td>
								${pageWgww2.ctn}
							</td>
							<td>
								${pageWgww2.remarks}
							</td>
						</s:iterator>
						<tr>
							<th colspan="7"></th>
							<th>
								${sumNum}
							</th>
							<s:if
								test='waigouDeliveryGoods.status!="待打印" && waigouDeliveryGoods.status!="送货中"'>
								<th align="right">
									${sumbhsprice}
								</th>
								<s:if test='waigouDeliveryGoods.status!="待打印"'>
									<th align="right">
										${sumMoney}
									</th>
								</s:if>
							</s:if>
							<th colspan="10"></th>
						</tr>
					</table>
					<div align="left">
						&nbsp;&nbsp;备注(异常请详细说明):
						<br />
						<br />
						<br />
						<br />
					</div>

					<table class="table">
						<tr>
							<td width="100px;">
								送货人:
								<br />
								Shipper / Agent
							</td>
							<td></td>
							<td width="100px;">
								收货人:
								<br />
								Received by
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								送货日期:
								<br />
								Delivery Date
							</td>
							<td></td>
							<td>
								收货日期:
								<br />
								Date Received
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								公司印章:
								<br />
								Company Chop
							</td>
							<td></td>
							<td>
								公司印章;
								<br />
								Company Chop
							</td>
							<td></td>
						</tr>
					</table>
				</div>
				<br />
			</div>
			<div align="center">
				<s:if
					test='waigouDeliveryGoods.status=="待打印"||waigouDeliveryGoods.status=="送货中"'>
					<input type="button" value="打印" class="input"
						onclick="printDelivery('printDiv')" />
					<s:if test='waigouDeliveryGoods.status=="送货中"'>
						<input type="button" value="确认送货" class="input"
							onchange="numyanzheng()"
							onclick="javascript:location.href='WaigouwaiweiPlanAction!updateDeliveryToPrint.action?id=${waigouDeliveryGoods.id}';" />
					</s:if>
				</s:if>
				<s:else>
					<input type="button" value="打印" class="input"
						onclick="printDelivery('printDiv')" />
				</s:else>
			</div>
		</div>
		<div style="display: none;"></div>
		<img alt="" src="" id="myimg">
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
<%--$('#barcode').qrcode( {--%>
<%--	render : "table", //table方式 --%>
<%--	width : 60, //宽度 --%>
<%--	height : 60, //高度 --%>
<%--	text : "${waigouDeliveryGoods.planNumber}" //任意内容 --%>
<%--}); //任意字符串 --%>
$(function(){
	getQRCode (60,60,'<%=basePath%>/loginforPhone.jsp?id=${waigouDeliveryGoods.id}','ercode') ;
	getQRCode (200,200,'<%=basePath%>/loginforPhone.jsp?id=${waigouDeliveryGoods.id}','ercode2') ;
})
function printDelivery(divid) {
	pagePrint(divid);
	$.ajax( {
		type : "POST",
		url : "goodsAction!updateDeliveryToPrint.action",
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
</script>
	</body>
</html>
