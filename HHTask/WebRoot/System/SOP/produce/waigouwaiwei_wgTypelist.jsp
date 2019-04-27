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
				<table class="table" style="width: 50%;">
					<tr>
						<td colspan="3" align="center"><input type="button" value="选择" onclick="selectWgType();"> </td>
					</tr>
					<tr>
						<td><input type="checkbox" id="checkAll" onclick="chageAllCheck()">全选</td>
						<td>序号</td>
						<td>类型</td>
					</tr>
					<s:iterator value="list" id="czType" status="pageStatus">
					<tr>
						<td>
							<s:property value="#pageStatus.index+1"/>
						</td>
						<td id="boxTd<s:property value="#pageStatus.index"/>" ondblclick="changeBox(<s:property value="#pageStatus.index"/>)">
							<input id="checkboxs<s:property value="#pageStatus.index"/>" type="checkbox" name="checkboxs" value="${czType.unitname}"
								onchange="chageNum();" >
						</td>
						<td>
							${czType.unitname}<input id="czType<s:property value="#pageStatus.index"/>" type="hidden" value="${czType.unitname}">
						</td>
					</tr>
					</s:iterator>
					<tr>
						<td colspan="3" align="center"><input type="button" value="选择" onclick="selectWgType();"> </td>
					</tr>
				</table>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<script type="text/javascript">
function chageAllCheck() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	if (checkAll.checked == true) {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = true;
		}
	} else {
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = false;
		}
	}

}
function chageNum() {
	var checkAll = document.getElementById("checkAll");
	var checkboxs = document.getElementsByName("checkboxs");
	var count = 0;
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == false) {
			checkAll.checked = false;
			return;
		} else {
			count++;
		}
	}
	if (count == checkboxs.length) {
		checkAll.checked = true;
	}

}
function changeBox(index){
	var checkbox = document.getElementById("checkboxs"+index);
	if(checkbox.checked == true){
		checkbox.checked = false;
	}else{
		checkbox.checked = true
	}
	chageNum();
}

function selectWgType(){
	var wgTypes = "";
	var checkboxs = document.getElementsByName("checkboxs");
	for ( var i = 0; i < checkboxs.length; i++) {
		if (checkboxs[i].checked == true) {
			if(wgTypes==""){
			wgTypes=$("#czType"+i).val();
			}else{
			wgTypes+=","+$("#czType"+i).val();
				
			}
		}
	}
	parent.$("#showWgType").html(wgTypes);
	parent.$("#inputWgType").val(wgTypes);
	parent.chageDiv('none');
}
</script>
	</body>
</html>
