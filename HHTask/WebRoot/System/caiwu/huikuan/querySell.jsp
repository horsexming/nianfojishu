<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>条件查询出库记录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<%@include file="/util/sonHead.jsp"%>
		<style type="text/css">
		table {
			font-size: 14px;
			padding: 0px;
			margin: 0px;
			border-collapse: collapse;
			/* 关键属性：合并表格内外边框(其实表格边框有2px，外面1px，里面还有1px哦) */
			border: solid #999; /* 设置边框属性；样式(solid=实线)、颜色(#999=灰) */
			border-width: 1px 0 0 1px;
			
		}
		
		table th,table td {
			border: solid #999;
			border-width: 1 1px 1px 1;
			padding: 2px;
		}
		</style>
  </head>
  
  <body>
  <%@include file="/util/sonTop.jsp"%>
		<div id="gongneng"
			style="width: 100%; border: thin solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
    <h3>出库记录显示查询</h3>
    <form action="huikuanAction!querySell.action" method="post" target="querySell">      
    <table width="900px">
    <tr>
    <td>件号：<s:textfield name="sell.sellMarkId" size="20"/></td>
    <td>品名：<s:textfield name="sell.sellGoods" size="20"/></td>
    <td>批次：<s:textfield name="sell.sellLot" size="20"/></td>
    </tr>
    <tr>
    <td>客户：<s:textfield name="sell.sellCompanyName" size="20"/></td>
    <td>开始：<input class="Wdate" type="text" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
    <td>截止：<input class="Wdate" type="text" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></td>
    </tr>
    <tr>
    <td>汇款单号：<s:textfield name="sell.sellHkId" size="20"/></td>
    <td colspan="2"><s:submit value="查找" />&nbsp;<s:reset value="放弃" /></td>    
    </tr>
    </table>
    </form>
    <div style="test-align:center;"><iframe  name="querySell" src="System/caiwu/huikuan/black.jsp" width="95%" height="1000px" frameborder="0" scrolling="no"/></div>
  </div>
			<br>
		</div>
  </body>
</html>
