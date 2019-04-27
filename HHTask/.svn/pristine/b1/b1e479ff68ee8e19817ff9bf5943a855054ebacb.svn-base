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
			 <h3>总成:${procard.markId}领取辅料</h3>
			</div>
			<div align="center">
			<form action="ProcardAction!submitLingFL.action" method="post">
			 <input type="hidden" name="procard.id" value="${procard.id}">
			 <input type="hidden" name="cardNumber" value="${cardNumber}">
			 <table align="center" class="table">
			    <tr>
			    <td>名称</td>
			    <td>规格</td>
			    <td>单位</td>
			    <td>类别</td>
			    <td>总数量</td>
			    <td>已领数量</td>
			    <td>库存数量</td>
			    <td>领取数量</td>
			    </tr>
			    <s:iterator value="processinforFuLiaoList" id="pageFuliao" status="flIndex">
			     <tr>
			     <td><input type="hidden" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].id">
			     <input readonly="readonly" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].name" value="${pageFuliao.name}">
			     </td>
			     <td><input readonly="readonly" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].specification" value="${pageFuliao.specification}">
			     </td>
			     <td><input readonly="readonly" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].unit" value="${pageFuliao.unit}">
			     </td>
			     <td><input readonly="readonly" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].type"  value="${pageFuliao.type}">
			     </td>
			     <td>${pageFuliao.totalCount}
			     </td>
			     <td>${pageFuliao.outCount}
			     </td>
			     <td>${pageFuliao.ckCount}
			     </td>
			     <td>
			     <s:if test="(#pageFuliao.totalCount-#pageFuliao.outCount)<=0">
			     <input id="ckCount${flIndex.index}" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].ckCount" value="0"
			     onkeyup="mustBeNumber('ckCount${flIndex.index}')">
			     </s:if>
			      <s:elseif test="(#pageFuliao.totalCount-#pageFuliao.outCount)>#pageFuliao.ckCount">
			      <input id="ckCount${flIndex.index}" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].ckCount" value="${pageFuliao.ckCount}"
			     onkeyup="mustBeNumber('ckCount${flIndex.index}')">
			      </s:elseif>
			      <s:else>
			      <input id="ckCount${flIndex.index}" name="processinforFuLiaoList[<s:property value='#flIndex.index'/>].ckCount" value="${pageFuliao.totalCount-pageFuliao.outCount}"
			     onkeyup="mustBeNumber('ckCount${flIndex.index}')">
			      </s:else>
			     </td>
			     </tr>
			    </s:iterator>
			     <tr><td align="center" colspan="8"><input type="submit" value="提交" style="width: 70px;height: 50px;"> </td></tr>
			 </table>
			</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
</html>
