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
					入库管理入库(Storage management storage)
				</h2>
				<form action="GoodsStoreAction!findRukuGoodsStoreQueRen.action"
					method="post">
					<input type="hidden" name="runningWaterCard.id"
						value="${runningWaterCard.id}" />
					<input type="hidden" name="procard.id" value="${procard.id}" />
					<table class="table" style="width: 95%;">
						<tr>
							<th align="right">
								批次:
								<br />
								Batch:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreLot"
									value="${goodsStore.goodsStoreLot }" />
							</td>
							<th align="right">
								件号:
								<br />
								Part No.:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreMarkId"
									value="${goodsStore.goodsStoreMarkId }" />
							</td>
							<th align="right">
								名称:
								<br />
								Name:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreGoodsName"
									value="${goodsStore.goodsStoreGoodsName }" />
							</td>
							<th rowspan="3">
								<input type="submit" value="查找"
									style="width: 80px; height: 100px; margin-top: 5px;" />
								&nbsp;
							</th>
						</tr>
						<tr>
							<th align="right">
								客户:
								<br />
								Customers:
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreCompanyName"
									value="${goodsStore.goodsStoreCompanyName }" />
							</td>
							<th align="right">
								负责人:
								<br />
								Leader :
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStorePerson"
									value="${goodsStore.goodsStorePerson}" />
							</td>
							<th align="right">
								工艺卡号:
								<br />
								Process card :
							</th>
							<td>
								<input type="text" name="goodsStore.goodsStoreArtsCard"
									value="${goodsStore.goodsStoreArtsCard }" />
							</td>
						</tr>
					</table>
				</form>
				<table class="table" style="width: 95%;">
					<tr bgcolor="#c0dcf2" height="30px"
						style="border-collapse: separate;">
						<th align="center">
							序号
							<br />
							No.
						</th>
						<th align="center">
							批次
							<br />
							Batch
						</th>
						<th align="center">
							件号
							<br />
							Part No.
						</th>
						<th align="center">
							品名
							<br />
							Name
						</th>
						<th align="center">
							数量
							<br />
							Quantity
						</th>
						<th align="center">
							客户
							<br />
							Customers
						</th>
						<th> 
							库位
							<br />
							Location
						</th>
						<th align="center">
							状态
							<br />
							State
						</th>
						<th align="center">
							操作
							<br />
							Operation
						</th>
					</tr>
					<tr>
						<th colspan="12" bgcolor="#FFB6C1"
							style="color: red；font-weight : bold;">
							入库待确认记录(Record storage to be confirmed)
						</th>
					</tr>
					<s:if test="{list.size()>0}">
						<s:iterator value="list" status="se" id="goodsIn">
							<tr align="center" 
								onmouseout="outBgcolor(this,'#FFB6C1')">
								<td>
									<s:property value="#se.index+1" />
								</td>
								<td>
									${goodsIn.goodsStoreLot}
								</td>
								<td>
									${goodsIn.goodsStoreMarkId}
								</td>
								<td>
									${goodsIn.goodsStoreGoodsName}
								</td>
								<td>
									${goodsIn.goodsStoreCount}
								</td>
								<td>
									${goodsIn.goodsStoreCompanyName}
								</td>
								<td>
									${goodsIn.goodsStorePosition}
								</td>
								<td>
									${goodsIn.status}
								</td>
								<td>
								<s:if test="#goodsIn.kuweiId!=null">
									<a onclick="getsendTow('${goodsIn.goodsStoreId}')">开门</a>&nbsp;
								</s:if>
									<a href="GoodsStoreAction!getOneGoodsStore.action?id=${goodsStoreId}&tag=ruku">确认</a>&nbsp;
								</td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="12" align="right">
								共
								<s:property value="total" />
								页 第
								<s:property value="cpage" />
								页
								<fenye:pages cpage="%{cpage}" total="%{total}" url="%{url}"
									styleClass="page" theme="number" />

							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td style="font-size: 15px; color: red;">
								对不起，没有查到相关的入库信息(Sorry, no storage-related information found)
							</td>
						</tr>
					</s:else>
				</table>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function getsendTow(id){
	$.ajax({
		url : "GoodsStoreAction!ZCsendTow.action",
		type : "POST",
		data : {
			id : id
		},
		dataType : "json",
		async : false,
		success : function(data) {
		if(data!=null){
				if(data.success){
					getcheckList2();
				}else{
					alert(data.message)
				}
			}
		},
		error : function() {
			alert("服务器异常!");
		}
	});
}
function getcheckList2() {
	if (typeof (myObj) != "undefined") {
		//打开扫描服务
		myObj.scanGongWei(1);
	} else {
		alert("无法打开扫描服务,请检查后重试!");
	}
}
function funFromjs(tm) {
	window.location.href = "GoodsStoreAction!ZcRuKuBacode.action?barCode=" + tm +"&tag=ruku";
}
</script>
	</body>
</html>
