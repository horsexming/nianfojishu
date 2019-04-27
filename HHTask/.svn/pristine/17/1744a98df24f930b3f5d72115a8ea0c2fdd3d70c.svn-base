<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script type="text/javascript"></script>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body >
	<center>
	<div id="printDiv" class="my_show" align="center">
	<table class="table" style="width: 60%">
	  <tr><th colspan="9">上海红湖排气有限公司入库申请单</th> </tr>
	   <tr><th colspan="9" align="right">PD—WA-   
	        <img src="barcode.action?msg=${zhaobiao.id}&amp;type=code128" height="55px" width="200px/">
	   				</th> </tr>
	   	<tr><th colspan="3">企划部（采购）</th> <th colspan="4">技术质保部（品质）</th><th colspan="2">生产部（物流）</th></tr>
	<tr><td>项目</td><td>内容</td><td>材质、检验、报告编号</td>
	   <td>批次号</td><td>炉批号</td><td>验收结论</td><td>信息反馈单编号</td>
	   <td>单位</td><td>入库数量</td>
	   <s:iterator value="listyusuan" id="zhaobiao1" status="pageIndex">
	   			<tr><td>订单号</td><td>${zhaobiao.title}</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td>送货单号</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td>供货厂家</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td>送货时间</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td>零件号/材料规格</td><td>${zhaobiao1.t6}    ${zhaobiao1.t5}</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td>送检数量</td><td>${zhaobiao1.t2}${zhaobiao1.t3}</td><td></td><td></td><td></td><td></td></tr>
	   			<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>
	   </s:iterator>
	  	<tr align="left"><th colspan="3">采购经办人:___________________</th> <th colspan="4">品质经办人:__________________</th><th colspan="2">物流经办人:_____________</th></tr> 
	  <tr><th colspan="9">注：物流部门确认入库后需提交给采购部门入库单，入库单上该注明该批产品入库申请单单号</th> </tr>
	</tr>
	</table>
	</div>
	<table align="center">
					<tr><td colspan="10">
					
					<input type="button" id="print" value="打印" class="input" onclick="pagePrint('printDiv')" />
					
					
					</td>	</tr>
					</table>
	
	</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<%@include file="/util/foot.jsp"%>
		<script type="text/javascript">
function f1() {
	listsize = document.getElementById("listsize").value;

	alert(listsize);
	for ( var i = 1; i <= listsize; i++) {
		alert("+++++++++++i" + i);
		var Xid = document.getElementById("Xi" + i);
		var tid = document.getElementById("toubiao" + i).value;
		alert(Xid);
		alert(tid);
	}

}
</script>
	</body>
</html>
