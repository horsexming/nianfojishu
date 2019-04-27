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
		<script type="text/javascript">
function checktype() {
	var warehouse = document.getElementById("warehouse");
	var addgoodsStore = document.getElementById("addgoodsStore");
	if(addgoodsStore!=null && addgoodsStore.value!=""){
		var type = addgoodsStore.value.split(".");
	}
	
	if (type != null && type.length > 0) {
		type = type[type.length - 1]
	}
	if (warehouse != null && warehouse.value == "") {
		alert("请选择所属仓库");
		warehouse.onfocus;
		return false;
	} else if (addgoodsStore != null && addgoodsStore.value == "") {
		alert("请选择所要导入的文件");
		addgoodsStore.onfocus;
		return false;
	} else if (addgoodsStore != null && type != "xls") {
		alert("选择正确的模板");
		addgoodsStore.onfocus;
		return false;
	}
	document.getElementById("sub").disabled = "disabled";
	return true;
}

function xzfile() {
	var warehouse = document.getElementById("warehouse");
	var addgoodsStore = document.getElementById("addgoodsStore");
	if (warehouse != null && warehouse.value != "") {
		addgoodsStore.disabled = false;
	} else {
		addgoodsStore.disabled = "disabled";
	}
}
</script>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng">

			<div align="center" id="test">
				<h3>
					批量入库导入
				</h3>
				<form action="GoodsStoreAction!addSgrk.action" method="post"
					enctype="multipart/form-data" onsubmit="return checktype()">
					<a href="./upload/file/download/rukuTemplate1.xls">导入模版下载</a>
					<a href="FileViewAction.action?FilePath=/upload/file/download/rukuTemplate1.xls&Refresh=true">/预览</a>

					<table class="table" width="75%">
						<%--<tr>
							<th align="right">
								所属仓库
							</th>
							<td>
								<select name="goodsStore.goodsStoreWarehouse" id="warehouse"
									onchange="xzfile()">
									<option></option>
									<s:iterator value="l" id="it" status="st">
										<option>
											${it}
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								编码库未匹配成功是否导入:
							</th>
							<td>
								<input type="radio" value="是" name="pagestatus">
								是
								<input type="radio" value="否" name="pagestatus"
									checked="checked">
								否
							</td>
						</tr>
						--%>
						<tr>
							<th align="right">
								选择导入文件:
							</th>
							<td align="left">
								<input type="file" name="addgoodsStore" />
							</td>
						</tr>
						<tr>
							<th>
							</th>
							<td align="left">
								<input type="submit"  id="sub" value="批量导入" class="input">
							</td>
						</tr>
					</table>
				</form>
				
<%--				<form action="sellAction!daoru.action" method="post">--%>
<%--					<table class="table" width="75%">--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--							</th>--%>
<%--							<td align="left">--%>
<%--								<input type="submit" id="sub1" value="出库导入" class="input">--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</form>--%>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">

</script>
	</body>
</html>
