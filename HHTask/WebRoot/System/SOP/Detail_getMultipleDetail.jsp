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
			style="width: 100%; border:  solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; padding-left: 35px; padding-top: 5px; padding-bottom: 5px; "
				align="left">
				<div style="float: left; width: 50%" align="left">
					
				</div>
				<div style="float: left; width: 48%" align="right">
					<a href="javascript:location.reload();" style="color: #ffffff">刷新</a>
					<a href="Detail_query.action" style="color: #ffffff">返回</a>
				</div>
			</div>
			
			<div align="center">
				<form action="Product_saveProduct.action" method="post">
					<s:iterator value="details" id="d">
						<input type="hidden" name="details.id" value="${d.id }">
					</s:iterator>
					<table class="table">
						<tr>
							<th colspan="7" align="center">
								<font color="red">${month}</font>的生产计划
							</th>
						</tr>
						<tr>
							<th>
								序号
							</th>
							<th>
								名称
							</th>
							<th>
								件号
							</th>
							<th>
								规格
							</th>
							<th>
								数量
							</th>
							<th>
								单位
							</th>
							<th>
								类别
							</th>
						</tr>
						<s:iterator value="leafs" id="leaf1" status="st">
							<tr>
							<td>${st.index + 1}</td>
							<td>${leaf1.value.name}</td>
							<td>${leaf1.value.partsNumber}</td>
							<td>${leaf1.value.specification}</td>
							<td>${leaf1.value.advPosition}</td>
							<td>${leaf1.value.unit}</td>
							<td>${leaf1.value.category}</td>
							</tr>
						</s:iterator>

					</table>
					<input type="button" value="生成请购" onclick="changeTemplet('product',this.form)" style="width: 90px; height: 40px;" />
					<input type="button" value="生成Excel" onclick="changeTemplet('excel',this.form)" style="width: 90px; height: 40px;" />
				</form>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>


<script type="text/javascript">
function changeTemplet(status, form){
	if(status == "excel"){
		form.action = "Detail_exportProductExcel.action";
		form.submit();
	} else if(status == "product"){
		form.action = "Detail_beginProduct.action";
		form.submit();
	}
}
</script>

</html>
