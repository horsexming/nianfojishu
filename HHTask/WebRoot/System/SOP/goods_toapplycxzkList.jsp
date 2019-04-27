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
				<h3>冲销待转库</h3>
				<div>
				 <table class="table">
				 	<tr>
				 		<th>序号
				 		</th>
				 		<th>件号
				 		</th>
				 		<th>业务件号
				 		</th>
				 		<th>名称
				 		</th>
				 		<th>批次
				 		</th>
				 		<th>库别
				 		</th>
				 		<th>库存
				 		</th>
				 		<th>可转数量
				 		</th>
				 		<th>申请转库
				 		</th>
				 	</tr>
				 	<s:iterator value="list" id="pageGoods" status="pageStep">
				 		<tr>
				 		<td align="center"><s:property value="#pageStep.index+1"/>
				 		</td>
				 		<td align="center">${pageGoods.goodsMarkId}
				 		</td>
				 		<td align="center">${pageGoods.ywmarkId}
				 		</td>
				 		<td align="center">${pageGoods.goodsFullName}
				 		</td>
				 		<td align="center">${pageGoods.goodsLotId}
				 		</td>
				 		<td align="center">${pageGoods.goodsClass}
				 		</td>
				 		<td align="center">${pageGoods.goodsCurQuantity}
				 		</td>
				 		<td align="center">${pageGoods.kzCount}
				 		</td>
				 		<td align="center">
				 			<input type="button" value="申请冲销" onclick="applyCxzk(${pageGoods.goodsId})" style="width: 80px;height: 20px;">
				 		</td>
				 		</tr>
				 	</s:iterator>
				 </table>
				</div>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function applyCxzk(id){
	window.location.href="goodsAction!toapplycxzk2.action?id=${id}&goods.goodsId="+id;
}
</SCRIPT>
	</body>
</html>
