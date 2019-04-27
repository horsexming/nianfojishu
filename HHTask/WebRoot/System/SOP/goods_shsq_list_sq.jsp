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
				<br />
				<div style="font-weight: bolder; font-size: 30px; height: 50px;"
					align="center">
					送货申请
				</div>
				<form action="goodsAction!addDeliveryNote.action" method="post">
					<input type="hidden" name="waigouDeliveryGoods.khId"
						value="${waigouDeliveryGoods.khId}">
					<input type="hidden" name="waigouDeliveryGoods.customerCode"
						value="${waigouDeliveryGoods.customerCode}">
					<input type="hidden" name="waigouDeliveryGoods.userId"
						value="${waigouDeliveryGoods.userId}">
					<table class="table">
						<tr>
							<td align="left">
								<img
									src="barcode.action?msg=${waigouDeliveryGoods.planNumber}&type=code128"
									width="180px" height="60px" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="left">
								供应商:${waigouDeliveryGoods.gysName}
								<input type="hidden" name="waigouDeliveryGoods.gysName"
									value="${waigouDeliveryGoods.gysName}">
							</td>
							<td>
								客户名称:${waigouDeliveryGoods.customer}
								<input type="hidden" name="waigouDeliveryGoods.customer"
									value="${waigouDeliveryGoods.customer}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供应商编号:${waigouDeliveryGoods.userCode}
								<input type="hidden" name="waigouDeliveryGoods.userCode"
									value="${waigouDeliveryGoods.userCode}">
							</td>
							<td>
								地址:${waigouDeliveryGoods.daodaDizhi}
								<input type="hidden" name="waigouDeliveryGoods.daodaDizhi"
									value="${waigouDeliveryGoods.daodaDizhi}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供联系人:${waigouDeliveryGoods.gysContacts}
								<input type="hidden" name="waigouDeliveryGoods.gysContacts"
									value="${waigouDeliveryGoods.gysContacts}">
							</td>
							<td>
								客联系人:${waigouDeliveryGoods.contacts}
								<input type="hidden" name="waigouDeliveryGoods.contacts"
									value="${waigouDeliveryGoods.contacts}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供联系电话:${waigouDeliveryGoods.gysPhone}
								<input type="hidden" name="waigouDeliveryGoods.gysPhone"
									value="${waigouDeliveryGoods.gysPhone}">
							</td>
							<td>
								客联系电话:${waigouDeliveryGoods.contactsPhone}
								<input type="hidden" name="waigouDeliveryGoods.contactsPhone"
									value="${waigouDeliveryGoods.contactsPhone}">
							</td>
						</tr>
						<tr>
							<td align="left">
								出发地址:${waigouDeliveryGoods.chufaDizhi}
								<input type="hidden" name="waigouDeliveryGoods.chufaDizhi"
									value="${waigouDeliveryGoods.chufaDizhi}">
							</td>
							<td>
								到达地址:${waigouDeliveryGoods.daodaDizhi}
								<input type="hidden" name="waigouDeliveryGoods.daodaDizhi"
									value="${waigouDeliveryGoods.daodaDizhi}">
							</td>
						</tr>
						<tr>
							<td align="left">
								送货人姓名:
								<input name="waigouDeliveryGoods.shContacts"
									value="${waigouDeliveryGoods.shContacts}">
								<font color="red">*</font>
							</td>
							<td>
								送货人联系电话:
								<input name="waigouDeliveryGoods.shContactsPhone"
									value="${waigouDeliveryGoods.shContactsPhone}">
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="1">
								车牌:
								<input name="waigouDeliveryGoods.chepai"
									value="${waigouDeliveryGoods.chepai}">
								<font color="red">*</font> 注:用于门禁自动进出
							</td>
							<td align="left" colspan="1">
								送货日期:
								<input name="waigouDeliveryGoods.shsqDate"
									value="${waigouDeliveryGoods.shsqDate}" class="Wdate"
									onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})">
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td align="left">
								签收姓名:
								<input name="waigouDeliveryGoods.qsContacts"
									value="${waigouDeliveryGoods.qsContacts}">
								<font color="red">*</font>
							</td>
							<td>
								签收人联系电话:
								<input name="waigouDeliveryGoods.qsContactsPhone"
									value="${waigouDeliveryGoods.qsContactsPhone}">
								<font color="red">*</font>
							</td>
						</tr>
					</table>
					<br />
					<br />
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
								批次
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
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].goodsId"
									value="${pageWgww2.goodsId}">
							</td>
							<td>
								<input 
									name="list_wdgd[${pageStatus2.index}].cgOrderNum"
									value="${pageWgww2.cgOrderNum}">
							</td>
							<td>
								${pageWgww2.markId}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].markId"
									value="${pageWgww2.markId}">
							</td>
							<td>
								${pageWgww2.selfCard}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].selfCard"
									value="${pageWgww2.selfCard}">
							</td>
							<td>
								${pageWgww2.proName}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].proName"
									value="${pageWgww2.proName}">
							</td>
							<td>
								${pageWgww2.specification}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].specification"
									value="${pageWgww2.specification}">
							</td>
							<td>
								${pageWgww2.tuhao}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].tuhao"
									value="${pageWgww2.tuhao}">
							</td>
							<td>
								${pageWgww2.banben}
								<input type="hidden"
									name="list_wdgd[${pageStatus2.index}].banben"
									value="${pageWgww2.banben}">
							</td>
							<td>
								<input type="text"
									name="list_wdgd[${pageStatus2.index}].shNumber"
									value="${pageWgww2.shNumber}">
							</td>
							<td>
								${pageWgww2.unit}
								<input type="hidden" name="list_wdgd[${pageStatus2.index}].unit"
									value="${pageWgww2.unit}">
							</td>
							<td>
								<input type="text" name="list_wdgd[${pageStatus2.index}].ctn"
									value="${pageWgww2.ctn}">
								<br />
								预估:${pageWgww2.shNumber}/${pageWgww2.oneCtnNum}=${pageWgww2.shNumber/pageWgww2.oneCtnNum}
							</td>
							<td>
								<input type="text"
									name="list_wdgd[${pageStatus2.index}].remarks">
							</td>
						</s:iterator>
						<tr>
							<th colspan="18">
								<input type="submit" value="申请送货" class="input" />
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
		function vali() {
	var nums = document.getElementsByName("mrkIds");
	for ( var i = 0; i < nums.length; i++) {
		if (nums[i].checked) {
			return true;
		}
	}
	alert("请选择外委工序！！！");
	return false;
}
		
		</SCRIPT>
	</body>
</html>
