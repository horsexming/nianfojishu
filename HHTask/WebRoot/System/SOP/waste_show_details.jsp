<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	</style>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="bodyDiv" align="center" class="transDiv"
			onclick="chageDiv('none')">
		</div>
		<div id="contentDiv" style="position: absolute; z-index: 255; width: 900px; display: none;"
			align="center">
			<div id="closeDiv"
				style="position: relative; top: 165px; left: 0px; right: 200px; z-index: 255; background: url(<%=basePath%>/images/bq_bg2.gif); width: 900px;">
				<div id="operatingDiv"
					style="background-color: #ffffff; width: 100%;">
					<iframe id="xiugaiIframe" src="" marginwidth="0" marginheight="0"
						hspace="0" vspace="0" frameborder="0" scrolling="yes"
						style="width: 98%; height: 500px; margin: 0px; padding: 0px;"></iframe>
				</div>
			</div>
		</div>
		<div align="center">
			<table class="table" style="width: 95%;">
				<tr bgcolor="#c0dcf2" height="30px"
					style="border-collapse: separate;">
					<th></th>
					<th align="center">仓区</th>
					<th align="center">库位</th>
					<th align="center">批次</th>
					<th align="center">供料属性</th>
					<th align="center">物料类别</th>
					<th align="center">品名</th>
					<th align="center">规格</th>
					<th align="center">供应商 </th>
					<th align="center" >入库类型</th>
					<th align="center" >入库时间</th>
					<th align="center" >原价格(单价)</th>
					<th align="center">处理数量</th>
					<th align="center">处理价格</th>
				</tr>
				<s:if test="{wasteDisponsalList.size()>0}">
					<s:iterator value="wasteDisponsalList" status="see" id="gs">
						<s:if test="#see.index%2==1">
							<tr align="center" bgcolor="#e6f3fb" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'#e6f3fb')">
						</s:if>
						<s:else>
							<tr align="center" onmouseover="chageBgcolor(this)" onmouseout="outBgcolor(this,'')">
						</s:else>
						<td><s:property value="#see.index+1" /></td>
						<td align="left" style="color: gray;">${gs.goodssHouseName}</td>
						<td align="left" style="color: gray;">${gs.goodsPosition}</td>
						<td align="right">${gs.goodsLotId}</td>
						<td align="right">${gs.goodsWgType}</td>
						<td align="right">${gs.goodsKgliao}</td>
						<td align="right">${gs.goodsFullName}</td>
						<td align="right">${gs.goodsFormat}</td>
						<td align="right">${gs.goodsSupplier}</td>
						<td align="right">${gs.goodsStyle}</td>
						<td align="right">${gs.goodsChangeTime}</td>
						<td align="right">${gs.goodsPrice}</td>
						<td align="right">${gs.disposeNum}</td>
						<td align="right">${gs.disposePrice}</td>
					</tr>
					</s:iterator>
					<tr>
						<th colspan="6"></th>
						<th colspan="3">
							总价格：${wasteDisponsalTotal.totalMoney}
						</th>
						<th colspan="6" align="right"></th>
						<!--<th>
							<fmt:formatNumber value="${sumcount}" pattern="#,#00.0#"></fmt:formatNumber>
						</th>
						<th colspan="10"></th>
						<th ><button onclick="javascript:generateManage();">生成</button></th> 
					--></tr>
				</s:if>
				<s:else>
					<tr>
						<td colspan="21" style="font-size: 15px; color: red;">
							对不起，没有查到相关的库存信息
						</td>
					</tr>
				</s:else>
			</table>
				<br/>
				<s:if test="wasteDisponsalTotal.attachmentName!=null">
					<p align="right">
						<a href="${pageContext.request.contextPath}/upload/file/${wasteDisponsalTotal.attachmentName}">查看附件</a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					</p>
				</s:if>
		</div>
		<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		
	</body>
</html>
