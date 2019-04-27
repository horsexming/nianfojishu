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
		<title></title>
		<%@include file="/util/sonHead.jsp"%>
		<script
			src="${pageContext.request.contextPath}/javascript/jquery-table2excel-master/dist/jquery.table2excel.js">
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<table class="table" id="rootTable">
					<tr bgcolor="#c0dcf2" height="50px">
						<th align="center">
							订单编号
							<br />
							(内部)
						</th>
						<th align="center">
							件号
						</th>
						<th align="center">
							业务件号
						</th>
						<th align="center">
							名称
						</th>
						<th align="center">
							卡片类型
						</th>
						<th align="center">
							产品类型
						</th>
						<th align="center">
							批次
						</th>
						<th align="center">
							数量
						</th>
						<th align="center">
							交付日期
						</th>
						<th align="center">
							状态
						</th>
					</tr>
					<tr>
						<td>
							${procard.orderNumber}
						</td>
						<td>
							${procard.markId}
						</td>
						<td>
							${procard.ywMarkId}
						</td>
						<td>
							${procard.proName}
						</td>
						<td>
							${procard.procardStyle}
						</td>
						<td>
							${procard.productStyle}
						</td>
						<td>
							${procard.selfCard}
						</td>
						<td>
							${procard.filnalCount}
						</td>
						<td>
							${procard.jioafuDate}
						</td>
						<td>
							${procard.status}
						</td>
					</tr>
				</table>
				<br />
				<h2 style="font-size: x-large;">
					外购件欠料信息
				</h2>
				<br />

				<s:if test="procardList!=null && procardList.size()>0">
				<input type="button" value="导出" id="exportbtn"
				style="height: 80px; width: 100px; float: right; display: none"
				onclick="exportAll();todisabledone(this)" data="downData">
					<table class="table table_export" id="procardTable"
						style="border-collapse: collapse;">
						<tr bgcolor="#c0dcf2" height="50px">
							<th>
								序号
							</th>
							<th>
								物料类别
							</th>
							<th>
								件号
							</th>
							<th>
								版本
							</th>
							<th>
								供料属性
							</th>
							<th>
								零件名称
							</th>
							<th>
								规格
							</th>
							<th>
								单位
							</th>
							<th>
								需求数量
							</th>
							<th>
								采购数量
							</th>
							<th>
								到货数量
							</th>
							<th>
								已领数量
							</th>
							<th>
								在途量
							</th>
							<th>
								库存量
							</th>
						</tr>
						<s:iterator id="pageprocard" value="procardList"
							status="statussdf">
							<s:if test="#statussdf.index%2==1">
								<tr align="center" bgcolor="#e6f3fb"
									onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'#e6f3fb')">
							</s:if>
							<s:else>
								<tr align="center" onmouseover="chageBgcolor(this)"
									onmouseout="outBgcolor(this,'')">
							</s:else>
							<td>
								<s:property value="#statussdf.index+1" />
							</td>
							<td>
								${pageprocard.wgType}
							</td>
							<td>
								${pageprocard.markId}
							</td>
							<td>
								${pageprocard.banBenNumber}
							</td>
							<td>
								${pageprocard.kgliao}
							</td>
							<td width="300px">
								${pageprocard.proName}
							</td>
							<td width="200px">
								${pageprocard.specification}
							</td>
							<td>
								${pageprocard.unit}
							</td>
							<td>
								${pageprocard.filnalCount}
							</td>
							<td>
								${pageprocard.cgNumber}
							</td>
							<td>
								${pageprocard.dhNumber}
							</td>
							<td>
								${pageprocard.filnalCount-pageprocard.hascount}
							</td>
							<td>
								${pageprocard.ztNumber}
							</td>
							<td>
								${pageprocard.kcNumber}
							</td>
							</tr>
						</s:iterator>
					</table>
				</s:if>
				<s:else>
					<span id="errormsg" style="color: red; font-size: x-large;">总成:${procard.ywMarkId}无欠料信息!</span>
				</s:else>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
function exportAll(){
	$(".table_export").table2excel({
    exclude: ".excludeThisClass",//BUG // hidden input移出TD // 格式css selector 
    name: "外购件欠料信息",
    filename: "${procard.orderNumber}_外购件欠料信息" //do not include extension
	});
}
$(document).ready(function(){
	$("#exportbtn").show();
});
</script>
</html>

