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
					<a
						href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}"
						style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center">
			<div align="center">${successMessage}${errorMessage}</div>
			<div align="center">
				<form action="">
					<table class="table"  >
						<tr>
							<th>选择</th>
							<th>序号</th>
							<th>采购产品</th>
							<th>月份</th>
							<th>数量</th>
							<th>件号</th>
							<th>规格</th>
							<th>说明</th>
							<th>计划单编号</th>
						</tr>
						<s:iterator value="details" id="d" status="st">
							<tr>
								<td><input type="checkbox" name="details.id" value="${d.id}"></td>
								<td>${st.index+1}</td>
								<td>${d.templet.name}</td>
								<td>
									${d.month}
								</td>
								<td>
									${d.quantity}
								</td>
								<td>
									${d.templet.partsNumber}
								</td>
								<td>
									${d.templet.specification}
								</td>
								<td>
									${d.explanation}
								</td>
								<td>
									${d.detailNumber}
								</td>
							</tr>						
						</s:iterator>
						<tr align="center">
						<td align="center" colspan="9">
						<input type="button" value="通过" onclick="changeTemplet('yes',this.form)"> 
						<input type="button" value="拒绝" onclick="changeTemplet('no',this.form)">
						</td></tr>
					</table>
				</form>
			</div>
			</div>
			<br>
		</div>
		<%@include file="/util/foot.jsp"%>
		</center>
	</body>
<script type="text/javascript">
function changeTemplet(status, form){
	if(!checkCheckBox()){
		alert("请至少选中一项");
		return false;
	}
	if(status == "yes"){
		form.action = "Detail_agree.action";
		form.submit();
	} else if(status == "no"){
		form.action = "Detail_disAgree.action";
		form.submit();
	}
}
function checkCheckBox(){
	var k = document.getElementsByName("details.id");
	var b = false;
	for(var a = 0; a<k.length; a++){
		if(k[a].checked){
			return true;
		}
	}
	return b;
}
</script>





</html>
