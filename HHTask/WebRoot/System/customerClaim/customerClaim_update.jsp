<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/fenye.tld" prefix="fenye"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@include file="/util/sonHead.jsp"%>
</head>
<body>
	<%@include file="/util/sonTop.jsp"%>
	<div id="gongneng" style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
		<div id="xitong" style="width: 100%; font-weight: bold; height: 50px; " align="left">
			<div style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;" align="left">	
			</div>
			<div style="float: left; width: 45%; padding-top: 5px;" align="right">
				<a href="ModuleFunctionAction!findMfByIdForJump.action?id=${moduleFunction.id}" style="color: #ffffff">刷新</a>
			</div>
		</div>		
		<div align="center">
			<form action="customerClaimAction_update.action" method="post" onsubmit="return validate()">
				<table class="table">
					<tr>
						<th colspan="6">客户索赔</th>
					</tr>
					<tr>
						<th>索赔单号</th>
						<td> <input type="hidden" name="tag" value="<s:property value="tag"/>">
					  <input type="hidden" name="customerClaim.id" value="<s:property value="customerClaim.id"/>">
						<input name="customerClaim.claimNumber" id="claimNumber"  readonly="readonly" value="<s:property value="customerClaim.claimNumber"/>"/> </td>
						<th>已方负责人</th>
						<td> <input name="customerClaim.ourPerson" id="ourPerson"  value="<s:property value="customerClaim.ourPerson"/>"/> </td>
						<th>登记时间</th>
						<td> <input name="customerClaim.addTime"  value="<s:property value="customerClaim.addTime"/>" id="addTime" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"/> </td>
					</tr>
					<tr>
						<th>对方负责人</th>
						<td> <input name="customerClaim.otherPerson"  value="<s:property value="customerClaim.otherPerson"/>" id="otherPerson" /> </td>
						<th>对方联系电话</th>
						<td> <input name="customerClaim.otherPhone"  value="<s:property value="customerClaim.otherPhone"/>" id="otherPhone"/> </td>
						<th> 对方单位</th>
						<td> <input name="customerClaim.otherCompany"  value="<s:property value="customerClaim.otherCompany"/>" id="otherCompany"/> </td>
					</tr>
					<tr>
					<th>索赔标题
					 </th>
					 <td> <input name="customerClaim.title" id="title"  value="<s:property value="customerClaim.title"/>"/> </td>
					<th>索赔内容
					 </th>
					 <td colspan="3"><textarea rows="4" cols="100" name="customerClaim.context" id="context"><s:property value="customerClaim.context"/></textarea>
					</tr>
					<tr>
						<td align="center" colspan="8">
							<input type="submit" value="提交">
						</td>
					</tr>
				</table>
			</form>
		</div>
		<br>
	</div>
	<%@include file="/util/foot.jsp"%>
	</center>
	<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	<SCRIPT type="text/javascript">
	function validate(){
		if(!validateMsg("索赔单号","claimNumber")){
			return false;
		}
		if(!validateMsg("已方负责人","ourPerson")){
			return false;
		}
		if(!validateMsg("登记时间","addTime")){
			return false;
		}
		if(!validateMsg("对方负责人","otherPerson")){
			return false;
		}
		if(!validateMsg("对方联系电话","otherPhone")){
			return false;
		}
		var filter = /[0-9][-]{0,}[0-9]+$/;
		if(!filter.test($("#otherPhone").val())){
			alert("号码格式不正确");
			return false;
		}
		if(!validateMsg("对方单位","otherCompany")){
			return false;
		}
		if(!validateMsg("索赔标题","title")){
			return false;
		}
		if(!validateMsg("索赔内容","context")){
			return false;
		}
		if(!validateMsg("索赔单号","claimNumber")){
			return false;
		}
		return true;
	}
	function validateMsg(msg,id){
	if($("#"+id).val()==null||$("#"+id).val()==""){
		alert(msg+"不能为空!");
		return false;
	}
	return true;
}
	</SCRIPT>
</body>
</html>
