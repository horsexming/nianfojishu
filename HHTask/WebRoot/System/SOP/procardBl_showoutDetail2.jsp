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
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<h3>
					材料清单
				</h3>
			</div>
			<div align="center">
				<br />
				总成件号:${procard.markId}&nbsp;&nbsp;&nbsp;总成批次:${procard.selfCard}&nbsp;&nbsp;&nbsp;订单号:${procard.orderNumber}
				&nbsp;&nbsp;&nbsp;批次数量:${procard.filnalCount}/${procard.unit}
			</div>
			<input type="button" value="导出" id="exportbtn"
				style="height: 80px; width: 100px; float: right; display: none"
				onclick="exportAll();todisabledone(this)" data="downData">
			<input type="hidden" name="rootId" value="${procard.id}">
			<table class="table">
				<tr bgcolor="#e6f3fb">
					<th align="center">
						序号
					</th>
					<th align="center">
						件号
					</th>
					<th align="center" style="max-width: 80px;">
						名称
					</th>
					<th align="center">
						工序号
					</th>
					<th align="center">
						供料属性
					</th>
					<th align="center" style="max-width: 80px;">
						批次
					</th>
					<th align="center">
						规格
					</th>
					<th align="center">
						图号
					</th>
					<th align="center">
						仓库
					</th>
					<th align="center">
						仓区
					</th>
					<th align="center">
						库位
					</th>
					<th align="center">
							单位用量
						</th>
					<th align="center">
						工序:数量
					</th>
					<th align="center">
						外委数量
					</th>
					<th align="center">
						已领数量
					</th>
					<th align="center">
						可发数量
					</th>
					<th align="center">
						库存量
					</th>
					<th align="center">
						请领数量
					</th>
					<th align="center">
						本次实发
					</th>
					<th align="center">
						单位
					</th>
					<th align="center">
						操作
					</th>
				</tr>
				<s:iterator value="goodsList" id="pagegoods" status="pageStatus">
					<s:if test="#pageStatus.index%2==1">
						<tr align="center" bgcolor="#e6f3fb"
							onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'#e6f3fb')">
					</s:if>
					<s:else>
						<tr align="center" onmouseover="chageBgcolor(this)"
							onmouseout="outBgcolor(this,'')">
					</s:else>
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
					<td class="excludeThisClass" style="display: none">
						<s:if test="#pagegoods.goodsId!=null">
							<input type="hidden" value="${pagegoods.goodsId}"
								name="goodsList[<s:property value="#pageStatus.index" />].goodsId">
						</s:if>
						<input type="hidden" value="${pagegoods.order_Id}"
							name="goodsList[<s:property value="#pageStatus.index" />].order_Id">
					</td>
					<td align="left">
						${pagegoods.goodsMarkId}
					</td>
					<td align="left" style="max-width: 80px;">
						${pagegoods.goodsFullName}
					</td>
					<td align="left">
						${pagegoods.processNo}
					</td>
					<td align="left">
						${pagegoods.kgliao}
					</td>
					<td align="left">
						${pagegoods.goodsLotId}
					</td>
					<td align="left" style="max-width: 80px;">
						${pagegoods.goodsFormat}
					</td>
					<td align="left">
						${pagegoods.tuhao}
					</td>
					<td align="left">
						${pagegoods.goodsClass}
					</td>
					<td align="left">
						${pagegoods.goodHouseName}
					</td>
					<td align="left">
						${pagegoods.goodsPosition}
					</td>
					<td align="right" >
							${pagegoods.dwyl}
						</td>
					<td align="right">
						${pagegoods.gongxuName}
					</td>
					<s:if
						test="!#pagegoods.isEnough&&#pagegoods.zyqjCount!=null&&#pagegoods.zyqjCount>0">
						<td colspan="6" align="center">
							<font color="red"">占用缺件:${pagegoods.zyqjCount}</font>
						</td>
					</s:if>
					<s:else>
						<td align="right">
							${pagegoods.qlCount}
						</td>
						<td align="right">
							${pagegoods.hqlCount}
						</td>
						<td align="right">
							${pagegoods.kfCount}/${pagegoods.goodsUnit}
						</td>
						<td align="right">
							${pagegoods.goodsBeginQuantity}/${pagegoods.goodsUnit}
						</td>
						<td align="left">
							${pagegoods.goodsCurQuantity}
						</td>

						<s:if test="#pagegoods.isChangeSf">
							<td align="right">
								${pagegoods.goodsCurQuantity}
						</s:if>
						<s:else>
							<input type="hidden" value="${pagegoods.goodsCurQuantity}"
								name="goodsList[<s:property value="#pageStatus.index" />].goodsCurQuantity">
							<td align="right">
								${pagegoods.goodsCurQuantity}
						</s:else>
						<input type="hidden" value="${pagegoods.flushCount}"
							name="goodsList[<s:property value="#pageStatus.index" />].flushCount">
						<s:if
							test="#pagegoods.goodsZhishu!=null&&#pagegoods.goodsZhishu>0">
							<font color="red">(<s:property
									value="#pagegoods.goodsZhishu" />/<s:property
									value="#pagegoods.qlUnit" />)</font>
							</td>
						</s:if>
						<s:else>
							</td>
						</s:else>

					</s:else>
					<td align="left">
						${pagegoods.goodsUnit}
					</td>

					<s:if test="#pagegoods.goodsId==null">
						<input type="hidden" disabled="disabled"
							class="gid<s:property value="#pagegoods.flag"/>"
							id="goodsId<s:property value='#pageStatus.index' />"
							name="selected" value="0">
						<%--更改td为了不导出input--%>
						<td>
					</s:if>
					<s:else>
						<input type="hidden" disabled="disabled"
							class="samegid gid<s:property value="#pagegoods.flag"/>"
							id="goodsId<s:property value='#pageStatus.index' />"
							name="selected" value="${pagegoods.goodsId}">
						<%--更改td为了不导出input--%>
						<td>
					</s:else>
					<s:if test="#pagegoods.isEnough">足够
								</s:if>
					<s:else>
						<div>
							<s:if test="#pagegoods.tqlCount!=null&&#pagegoods.tqlCount>0">
								<font color="red">缺料${pagegoods.tqlCount}</font>
								<a
									href="procardBlAction_findLackGoodsDetail.action?goods.goodsMarkId=${pagegoods.goodsMarkId}"
									target="view_window">查看</a>
							</s:if>
							<s:else>
								<font color="red">库存不够</font>
							</s:else>
						</div>
					</s:else>
					</td>
					</tr>
				</s:iterator>
				</tr>
			</table>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function valeData(){
	$("#skinput").attr("readonly","readonly");
	$("#sbtn").attr("disabled","disabled");
	
}

<%--var count = $("#listSize").val();--%>
<%--var sureCount = 0;--%>
function confirmGoods(obj, goodsMarkId,btnclass) {
		$(".gid"+btnclass).removeAttr("disabled");
		$(".btn"+btnclass).hide();
		$(".fbtn"+btnclass).show();
		$("#htr1").show();
<%--		var len=$(".btn"+btnclass).length;--%>
<%--		sureCount=sureCount+len;--%>
<%--		count=count-len;--%>
}
function confirmGoods2(obj, goodsMarkId,btnclass) {
		$(".gid"+btnclass).attr("disabled","disabled");
		$(".btn"+btnclass).show();
		$(".fbtn"+btnclass).hide();
<%--		var len=$(".btn"+btnclass).length;--%>
<%--		sureCount=sureCount+len;--%>
<%--		count=count-len;--%>
}
function sureAll(){
	$(".samegid").removeAttr("disabled");
		$(".samebtn").hide();
		$(".samefbtn").show();
		$("#htr1").show();
}
function exportAll(){
	$(".table").table2excel({
    exclude: ".excludeThisClass",//BUG // hidden input移出TD // 格式css selector 
    name: "材料清单",
    filename: "${procard.orderNumber}_材料清单" //do not include extension
	});
}

$(document).ready(function(){
	$("#exportbtn").show();
});
</SCRIPT>
	</body>
</html>
