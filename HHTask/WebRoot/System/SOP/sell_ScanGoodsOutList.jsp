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
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
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
				<h2>
					卡片要出库记录
				</h2>
				<form action="sellAction!saveSellByCard.action" method="post"
					onkeydown="if(event.keycode==13)return false;">
					<input type="hidden" name="procard.id" value="${procard.id}">
					<s:if test="%{'barcode'==tag}">
						<input type="hidden" name="id" value="${sticker.id}">
					</s:if>
					<s:else>
						<input type="hidden" name="id" value="${runningWaterCard.id}">
					</s:else>
					<input type="hidden" name="tag" value="${tag}">
					<table width="90%" class="table">
						<tr>
							<th colspan="6">
								流水卡片信息
							</th>
						</tr>
						<tr>
							<th>
								件号
							</th>
							<th>
								${procard.markId}
							</th>
							<th>
								批次
							</th>
							<th>
								${procard.selfCard}
							</th>
							<th>
								数量
							</th>
							<th>
								${procard.filnalCount}
							</th>
						</tr>
						<tr>
							<th>
								客户
							</th>
							<th>
								${runningWaterCard.customer}
							</th>
							<th>
								车型
							</th>
							<th>
								${procard.carStyle}
							</th>
							<th>
								发卡人
							</th>
							<th>
								${procard.zhikaren}
							</th>
						</tr>
					</table>
					<table class="table" width="90%">

						<tr>
							<th colspan="9" align="center">
								发料信息
							</th>
						</tr>
						<tr bgcolor="#c0dcf2" height="30px"
							style="border-collapse: separate;">
							<th align="center">
								序号
							</th>
							<th align="center">
								<s:if test="%{'自制'==procard.procardStyle}">
							库别
						</s:if>
								<s:else>
							批次
						</s:else>
							</th>
							<th align="center">
								件号
							</th>
							<th align="center">
								名称
							</th>
							<th align="center">
								规格
							</th>
							<th align="center">
								<s:if test="%{'自制'==procard.procardStyle}">
							请领数量
						</s:if>
								<s:else>
							库位
						</s:else>
							</th>
							<th align="center">
								实发数量
							</th>
							<th align="center">
								<s:if test="%{'自制'==procard.procardStyle}">
							在制品数
						</s:if>
								<s:else>
							单位	
						</s:else>

							</th>
							<th align="center">
								操作
							</th>
						</tr>

						<s:if test="{list.size()>0}">
							<s:iterator value="list" status="se" id="goods">
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
									<s:if test="%{'自制'==procard.procardStyle}">
								${goods.goodsClass}
							</s:if>
									<s:else>
								${goods.goodsLotId}
							</s:else>
								</td>
								<td>
									${goods.goodsMarkId}
								</td>
								<td>
									${goods.goodsFullName}
								</td>
								<td>
									${goods.goodsFormat}
								</td>
								<td>
									<s:if test="%{'自制'==procard.procardStyle}">
										<input type="text" name="qingling"
											value="${goods.goodsZhishu}" size="5px;" />/${procard.yuanUnit}
							</s:if>
									<s:else>
								${goods.goodsPosition}
							</s:else>
								</td>

								<td>
									<s:if test="%{'自制'==procard.procardStyle}">
										<input type="text" name="shifa"
											value="${goods.goodsCurQuantity}" size="5px;" />/${goods.goodsUnit}
							</s:if>
									<s:else>
								${goods.goodsCurQuantity}
							</s:else>

								</td>
								<td>
									<s:if test="%{'自制'==procard.procardStyle}">
								${goods.goodsBeginQuantity}
							</s:if>
									<s:else>
								${goods.goodsUnit}
							</s:else>

								</td>
								<td>
									<div id="B<s:property value='#se.index+1' />"
										style="display: block">
										<input type="button" value="确认"
											onClick="confirmGoods(<s:property value='#se.index+1' />)">
									</div>
									<div id="H<s:property value='#se.index+1' />"
										style="display: none">
										确认
									</div>
								</td>
								</tr>

							</s:iterator>
							<tr>
								<td colspan="9" align="center">
									领料人姓名:
									<input type="text" name="procard.lingliaoren" size="100" />
								</td>
							</tr>
							<tr>
								<td colspan="9" align="center">
									<span id="butt" style="display: none;" align="top;"><input
											style="width: 120px; height: 60px;" type="submit" value="提交" />
									</span>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td colspan="9" style="font-size: 15px; color: red;">
									对不起，没有查到相关的库存信息
								</td>
							</tr>
						</s:else>

					</table>

				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function confirmGoods(obj) {
	var bid = "B" + obj;
	var hid = "H" + obj;
	var count = "<s:property value='list.size()'/>";
	document.getElementById(bid).style.display = "none";
	document.getElementById(hid).style.display = "block";
	if (obj == count) {
		document.getElementById("butt").style.display = "block";
	}
}
function gosearch() {
	if (window.event.keyCode == 13) {
		return false;
	}

}
</script>
	</body>
</html>
