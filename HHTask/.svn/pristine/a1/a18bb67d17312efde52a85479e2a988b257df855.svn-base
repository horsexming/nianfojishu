
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
				<form action="goodsAction!findGoodsForSh.action" method="post">
					<input name="pageStatus" value="sgsh" type="hidden">
					<table class="table">
						<tr>
							<th align="right">
								件号
							</th>
							<td>
								<input type="text" value="${goods.goodsMarkId}"
									name="goods.goodsMarkId" />
							</td>
							<th>
								订单号:
							</th>
							<td>
								<input name="goods.neiorderId" value="${goods.neiorderId}" />
							</td>
							<th>
								客户名称:
							</th>
							<td>
								<input name="goods.goodsCustomer" value="${goods.goodsCustomer}" />
							</td>
							<td>
								<input type="submit" value="查询" class="input">
							</td>
						</tr>
					</table>
				</form>
				<form action="goodsAction!orderToSonghuo.action" method="post" onsubmit="return check()">
					<h2 style="color: red;">
						${errorMessage}
					</h2>
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll">
							</th>
							<th>
								内部订单号
							</th>
							<th>
								外部订单号
							</th>
							<th>
								客户名称
							</th>
							<th>
								业务件号
							</th>
							<th>
								件号
							</th>
							<th>
								库存批次
							</th>
							<th>
								名称
							</th>
							<th>
								库别
							</th>
							<th>
								仓区
							</th>
							<th>
								库位
							</th>
							<th>
								数量
							</th>
							<th>
								操作
							</th>
						</tr>
						<s:iterator value="list" id="pageGoods" status="pageindex">
							<s:if test="#pageindex.index%2==1">
								<tr align="center" bgcolor="#e6f3fb" id="tr${pageindex.index}"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									id="tr${pageindex.index}" onmouseout="outBgcolor(this,'')">
							</s:else>
							<td align="right">
								${pageindex.index+1}
								<input type="checkbox" name="ids" value="${pageGoods.goodsId}"
									onclick="chageNum(this)" >
							</td>
							<td align="left">
								${pageGoods.neiorderId}
							</td>
							<td align="left">
								${pageGoods.waiorderId}
							</td>
							<td align="left">
								${pageGoods.goodsCustomer}
							</td>
							<td align="left">
								${pageGoods.ywmarkId}
							</td>
							<td align="left">
								${pageGoods.goodsMarkId}
							</td>
							<td align="left">
								${pageGoods.goodsLotId}
							</td>
							<td align="left">
								${pageGoods.goodsFullName}
							</td>
							<td align="right">
								${pageGoods.goodsClass}
							</td>
							<td align="right">
								${pageGoods.goodHouseName}
							</td>
							<td align="right">
								${pageGoods.goodsPosition}
							</td>
							<td align="right">
								${pageGoods.goodsCurQuantity}
							</td>
							<td align="center">
							</td>
							</tr>
						</s:iterator>
						<tr>
							<th align="right">
								<input type="checkbox" onclick="chageAllCheck(this)"
									id="checkAll2">
							</th>
							<td colspan="26" align="right">
								第
								<font color="red"><s:property value="cpage" /> </font> /
								<s:property value="total" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
						<tr>
							<th colspan="18">
								<s:if test="list!=null&&list.size()>0">
									<input type="submit" value="申请送货" class="input" />
									<br />
								(点击后进入数量确认)
								</s:if>
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
$(function() {
	$("#orderPlanNum").focus();
	$("#orderPlanNum").select();
})

function checkNum(num, obj) {
	var nownum = parseFloat($(obj).val());
	var oldnum = parseFloat(num);
	if (nownum > oldnum) {
		alert("确认数量不能超过" + oldnum);
		$(obj).val(oldnum);
		$(obj).focus();
		$(obj).select();
	} else if (nownum < 0) {
		alert("确认数量不能填写负数!");
		$(obj).val(oldnum);
		$(obj).focus();
		$(obj).select();
	}
}

function subtodis(obj) {
	$("#subto").attr("disabled", "disabled");
	obj.form.submit();
}
function check(){
	var ids =	document.getElementsByName("ids");
	var bool = false;
	if(ids!=null && ids.length>0){
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked){
				bool = true;
				break;
			}
		}
	}
	if(!bool){
		alert("请至少选择一个成品申请送货!~")
	}
	return bool;
}
</script>
	</body>
</html>
