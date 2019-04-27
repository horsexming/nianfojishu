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
					送货单
				</div>
				<form action="WaigouwaiweiPlanAction!addDeliveryNote.action"
					method="post">
					<input type="hidden" name="waigouDelivery.gysId"
						value="${waigouDelivery.gysId}">
					<input type="hidden" name="waigouDelivery.userId"
						value="${waigouDelivery.userId}">
					<table class="table">
						<tr>
							<td align="left">
								<img
									src="barcode.action?msg=${waigouDelivery.planNumber}&type=code128"
									width="180px" height="60px" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="left">
								供应商:${waigouDelivery.gysName}
								<input type="hidden" name="waigouDelivery.gysName"
									value="${waigouDelivery.gysName}">
							</td>
							<td>
								客户名称:${waigouDelivery.customer}
								<input type="hidden" name="waigouDelivery.customer"
									value="${waigouDelivery.customer}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供应商编号:${waigouDelivery.userCode}
								<input type="hidden" name="waigouDelivery.userCode"
									value="${waigouDelivery.userCode}">
							</td>
							<td>
								地址:${companyInfo.address}
								<input type="hidden" name="companyInfo.address"
									value="${companyInfo.address}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供联系人:${waigouDelivery.gysContacts}
								<input type="hidden" name="waigouDelivery.gysContacts"
									value="${waigouDelivery.gysContacts}">
							</td>
							<td>
								客联系人:${waigouDelivery.contacts}
								<input type="hidden" name="waigouDelivery.contacts"
									value="${waigouDelivery.contacts}">
							</td>
						</tr>
						<tr>
							<td align="left">
								供联系电话:${waigouDelivery.gysPhone}
								<input type="hidden" name="waigouDelivery.gysPhone"
									value="${waigouDelivery.gysPhone}">
							</td>
							<td>
								客联系电话:${waigouDelivery.contactsPhone}
								<input type="hidden" name="waigouDelivery.contactsPhone"
									value="${waigouDelivery.contactsPhone}">
							</td>
						</tr>
						<tr>
							<td align="left">
								出发地址:${waigouDelivery.chufaDizhi}
								<input type="hidden" name="waigouDelivery.chufaDizhi"
									value="${waigouDelivery.chufaDizhi}">
							</td>
							<td>
								到达地址:${waigouDelivery.daodaDizhi}
								<input type="hidden" name="waigouDelivery.daodaDizhi"
									value="${waigouDelivery.daodaDizhi}">
							</td>
						</tr>
						<tr>
							<td align="left">
								送货人姓名:
								<input name="waigouDelivery.shContacts"
									value="${waigouDelivery.shContacts}">
							</td>
							<td>
								送货人联系电话:
								<input name="waigouDelivery.shContactsPhone"
									value="${waigouDelivery.shContactsPhone}">
							</td>
						</tr>
						<tr>
							<td align="left" colspan="2">
								车牌:
								<input name="waigouDelivery.chepai"
									value="${waigouDelivery.chepai}">
								注:用于门禁自动进入
							</td>
						</tr>
					</table>
					<br />
					<br />
					<table class="table">
						<tr>
							<th colspan="18" align="left">
								<input type="submit" value="申请送货" class="input" />
							</th>
						</tr>
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
								供货属性
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
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].waigouPlanId"
									value="${pageWgww2.waigouPlanId}">
							</td>
							<td>
								${pageWgww2.cgOrderNum}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].cgOrderNum"
									value="${pageWgww2.cgOrderNum}">
							</td>
							<td>
								${pageWgww2.markId}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].markId"
									value="${pageWgww2.markId}">
							</td>
							<td>
								${pageWgww2.proName}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].proName"
									value="${pageWgww2.proName}">
							</td>
							<td>
								${pageWgww2.specification}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].specification"
									value="${pageWgww2.specification}">
							</td>
							<td>
								${pageWgww2.tuhao}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].tuhao"
									value="${pageWgww2.tuhao}">
							</td>
							<td>
								${pageWgww2.banben}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].banben"
									value="${pageWgww2.banben}">
							</td>
							<td>
								${pageWgww2.kgliao}
								<input type="hidden"
									name="list_wdd[${pageStatus2.index}].kgliao"
									value="${pageWgww2.kgliao}">
							</td>
							<td>
								<input type="text"
									name="list_wdd[${pageStatus2.index}].shNumber"
									value="${pageWgww2.shNumber}" onchange="numyanzheng(this);bijiao(this,${pageWgww2.shNumber},${pageWgww2.rangeOfReceipt})">
								<input type="hidden" name="list_wdd[${pageStatus2.index}].rangeOfReceipt"
									value="${pageWgww2.rangeOfReceipt}"  />
							</td>
							<td>
								${pageWgww2.unit}
								<input type="hidden" name="list_wdd[${pageStatus2.index}].unit"
									value="${pageWgww2.unit}">
							</td>
							<td>
								<input type="text" name="list_wdd[${pageStatus2.index}].ctn"
									value="${pageWgww2.ctn}">
								<br />
								预估:${pageWgww2.shNumber}/${pageWgww2.oneCtnNum}=<fmt:formatNumber value="${pageWgww2.shNumber/pageWgww2.oneCtnNum}" pattern="#.0000"/>
							</td>
							<td>
								<input type="text" name="list_wdd[${pageStatus2.index}].remarks">
							</td>
						</s:iterator>
						<tr>
							<th colspan="18" align="left">
								<input type="submit" value="申请送货" class="input" id = "sub"  />
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
function bijiao(obj,synum,rangeOfReceipt){
	var shnum = obj.value;
	if(shnum!=''){
		shnum = parseFloat(shnum);
		var ysnum0 = synum+rangeOfReceipt;
		if(shnum<=0){
			alert('送货数量必须大于0')
			obj.value = synum;
		}else if(shnum>ysnum0){
			alert('送货数量:'+shnum+"大于订单未送货数量:"+synum+" 超出了："+(shnum-ysnum0)+"，不在该零件的收货浮动范围(±"+rangeOfReceipt+")内。")
			obj.value = synum;
		}
	}
}
		
		</SCRIPT>
	</body>
</html>
