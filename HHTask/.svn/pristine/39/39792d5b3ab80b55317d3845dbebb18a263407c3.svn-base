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
				<h3>
					外委材料清单
					<h3 />
			</div>
			<div align="center">
				<br />
				外委单号：${order.planNumber}&nbsp;&nbsp;&nbsp;供应商:${order.gysName}
			</div>
			<form action="sellAction!outwwcl.action" method="post"
				onsubmit="valeData();">
				<input type="hidden" value="${order.id}" name="order.id">
				<input type="hidden" value="${id}" name="id">
				<input type="hidden" value="${barCode}" name="barCode">
				<table class="table">
					<tr bgcolor="#e6f3fb">
						<th align="center">
							序号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							工序号
						</th>
						<th align="center">
							供料属性
						</th>
						<th align="center">
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
							缺量
						</th>
						<th align="center">
							数量
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
						<td align="left">
							<s:if test="#pagegoods.goodsId!=null">
								<input type="hidden" value="${pagegoods.goodsId}"
									name="goodsList[<s:property value="#pageStatus.index" />].goodsId">
							</s:if>
							<input type="hidden" value="${pagegoods.order_Id}"
								name="goodsList[<s:property value="#pageStatus.index" />].order_Id">
							${pagegoods.goodsMarkId}
						</td>
						<td align="left">
							${pagegoods.banBenNumber}
						</td>
						<td align="left">
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
						<td align="left">
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
						<td align="right">
							<s:if test="!#pagegoods.isEnough">
								<font color="red">${pagegoods.tqlCount}</font>
							</s:if>
							<s:else>
							${pagegoods.tqlCount}
							</s:else>
						</td>
						<td align="right">
							<s:if test="#pagegoods.isChangeSf">
								<input value="${pagegoods.goodsCurQuantity}"
									name="goodsList[<s:property value="#pageStatus.index" />].goodsCurQuantity">
								<input type="hidden" value="${pagegoods.goodsZhishu}"
									name="goodsList[<s:property value="#pageStatus.index" />].goodsZhishu">
							</s:if>
							<s:else>
							${pagegoods.goodsCurQuantity}
							<input type="hidden" value="${pagegoods.goodsCurQuantity}"
									name="goodsList[<s:property value="#pageStatus.index" />].goodsCurQuantity">
							</s:else>
							<input type="hidden" value="${pagegoods.goodsBeginQuantity}"
								name="goodsList[<s:property value="#pageStatus.index" />].goodsBeginQuantity">
							<s:if
								test="#pagegoods.goodsZhishu!=null&&#pagegoods.goodsZhishu>0">
								<font color="red">(<s:property
										value="#pagegoods.goodsZhishu" />/<s:property
										value="#pagegoods.qlUnit" />)</font>
							</s:if>
						</td>
						<td align="left">
							${pagegoods.goodsUnit}
						</td>
						<td>
							<s:if test="#pagegoods.goodsId==null">
								<input type="hidden" disabled="disabled"
									class="gid<s:property value="#pagegoods.flag"/>"
									id="goodsId<s:property value='#pageStatus.index' />"
									name="selected" value="0">
							</s:if>
							<s:else>
								<input type="hidden" disabled="disabled"
									class="gid<s:property value="#pagegoods.flag"/>"
									id="goodsId<s:property value='#pageStatus.index' />"
									name="selected" value="${pagegoods.goodsId}">
							</s:else>
							<s:if test="#pagegoods.isEnough">
								<div id="B<s:property value='#pageStatus.index' />"
									class="btn<s:property value="#pagegoods.flag"/>"
									style="display: block">
									<input type="button" value="确认"
										onClick="confirmGoods(<s:property value='#pageStatus.index' />,'<s:property value='#pagegoods.goodsMarkId' />','<s:property value="#pagegoods.flag"/>')">
								</div>
								<div id="H<s:property value='#pageStatus.index' />"
									class="fbtn<s:property value="#pagegoods.flag"/>"
									style="display: none">
									确认
								</div>
							</s:if>
							<s:else>
								<div>
									<font color="red">库存不够</font>
								</div>
							</s:else>
						</td>
						</tr>
					</s:iterator>
					<tr>
						<td align="center" colspan="14">
							<input type="submit" id="tbn" value="领取"
								style="width: 80px; height: 40px;" />
							<%--						<s:if test="order.wlStatus=='已出库'">--%>
							<%--						已出库--%>
							<%--						</s:if>--%>
							<%--						<s:else>--%>
							<%--						<s:if test="tag=='true'">--%>
							<%--						<input type="submit" id="tbn" value="领取" style="width: 80px;height: 40px;"/>--%>
							<%--						</s:if>--%>
							<%--						<s:else>--%>
							<%--							<input type="button" value="领取" style="width: 80px;height: 40px;" disabled="disabled"/>--%>
							<%--						</s:else>--%>
							<%--						</s:else>--%>
						</td>
					</tr>
				</table>
			</form>
			<s:if test="listSell!=null&&listSell.size()>0">
				<br />
				<table class="table">
					<tr bgcolor="red">
						<td align="center" colspan="14">
							历史出库
						</td>
					</tr>
					<tr>
						<th align="center">
							序号
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							版本
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							工序号
						</th>
						<th align="center">
							供料属性
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							规格
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
							数量
						</th>
						<th align="center">
							单位
						</th>
						<th align="center">
							数量转换
						</th>
						<th align="center">
							转换单位
						</th>
					</tr>
					<s:iterator value="listSell" id="pageSell">
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
						<td align="left">
							${pageSell.sellMarkId}
						</td>
						<td align="left">
							${pageSell.banBenNumber}
						</td>
						<td align="left">
							${pageSell.sellGoods}
						</td>
						<td align="left">
							${pageSell.processNo}
						</td>
						<td align="left">
							${pageSell.kgliao}
						</td>
						<td align="left">
							${pageSell.sellLot}
						</td>
						<td align="left">
							${pageSell.sellFormat}
						</td>
						<%--					<td align="center">图号${pageSell.}--%>
						<%--					</td>--%>
						<td align="left">
							${pageSell.sellWarehouse}
						</td>
						<td align="left">
							${pageSell.goodHouseName}
						</td>
						<td align="left">
							${pageSell.kuwei}
						</td>
						<td align="right">
							${pageSell.sellCount}
						</td>
						<td align="left">
							${pageSell.sellUnit}
						</td>
						<td align="right">
							${pageSell.sellZhishu}
						</td>
						<td align="left">
							${pageSell.goodsStoreZHUnit}
						</td>
						</tr>
					</s:iterator>
				</table>
			</s:if>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
function valeData(){
	$("#tbn").attr("disabled","disabled");
}

<%--var count = $("#listSize").val();--%>
<%--var sureCount = 0;--%>
function confirmGoods(obj, goodsMarkId,btnclass) {
		$(".gid"+btnclass).removeAttr("disabled");
		$(".btn"+btnclass).hide();
		$(".fbtn"+btnclass).show();
<%--		var len=$(".btn"+btnclass).length;--%>
<%--		sureCount=sureCount+len;--%>
<%--		count=count-len;--%>
}
</SCRIPT>
	</body>
</html>
