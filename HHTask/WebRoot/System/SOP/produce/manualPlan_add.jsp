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
			<h2>采购申请</h2>
			<font id="msg_font" color="red">${errorMessage}</font>
			<form action="ManualOrderPlanAction_daorumaualPlan.action" method="post"
							enctype="multipart/form-data" onsubmit="return checktype()">
							选择导入文件:
							<input type="file" name="file">
							<a href="<%=basePath%>/upload/file/download/wlxqb.xls">导入模版下载</a>
							<a href="FileViewAction.action?FilePath=/upload/file/download/wgprice.xls&Refresh=true">/预览</a>
							<s:if test="tag=='fl'">
								<input type="hidden" value="辅料"  name="mod1.category"/>
							</s:if>
							<s:else>
								<input type="hidden" value="外购"  name="mod1.category"/>
							</s:else>
							<input type="submit" value="批量导入" id="sub">
						</form>
				<form action="ManualOrderPlanAction_addmanualPlan.action" method="POST" onsubmit="return check()">
					<table class="table" >
						<tr>
							<th align="right">
								件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号
							</th>
							<td>
								<input type="text" value="" name="mod1.markId" id="markId" onchange="getwgj()" />
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th align="right">
								供料属性
							</th>
							<td>
							<select name="mod1.kgliao" id="kgliao">
									<option value="TK">
										自购(TK)
									</option>
									<option value="TK AVL">
										指定供应商(TK AVL)
									</option>
									<option value="CS">
										客供(CS)
									</option>
									<option value="TK Price">
										完全指定(TK Price)
									</option>
							</select>
							</td>
						</tr>
						<tr>
						<tr>
							<th align="right">
								名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称
							</th>
							<td>
								<input type="text" value="" name="mod1.proName" id="proName" >
							</td>
						</tr>
						<tr>
							<th align="right">
								规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格
							</th>
							<td>
								<input type="text" value="" name="mod1.specification" id="specification" >
							</td>
						</tr>
						<tr>
							<th align="right">
								物料类别
							</th>
							<td>
								<input type="text" value="" name="mod1.wgType" id="wgType">
							</td>
						</tr>
							<th align="right">
								版&nbsp;&nbsp;本&nbsp;&nbsp;号
							</th>
							<td>
								<input type="text" value="" name="mod1.banben" id="banben" >
							</td>
						</tr>
						<tr>
							<th align="right">
								单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位
							</th>
							<td>
								<input type="text" value="" name="mod1.unit" id="unit" >
							</td>
						</tr>
						<tr>
							<th align="right">
								图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号
							</th>
							<td>
								<input type="text" value="" name="mod1.tuhao" id="tuhao" >
							</td>
						</tr>
						<tr>
							<th align="right">
								采购数量
							</th>
							<td>
								<input type="text" value="" name="mod1.cgnumber" onchange="numyanzheng(this)" 
								id="number" ><font color="red">*</font>
								
							</td>
						</tr>
						<tr>
							<th align="right">
								是否紧急
							</th>
							<td>
								<input type="radio" value="YES" name="mod1.isurgent"/>是
								<input type="radio" value="NO" name="mod1.isurgent" checked="checked"/>否
							</td>
						</tr>
						<tr>
							<th align="right">
								备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注
							</th>
							<td>
								<textarea rows="2" cols="40" name="mod1.remarks"></textarea>
							</td>
						</tr>
					</table>
						<input type="hidden" value="外购" name="mod1.category"/>
						<input type="submit" value="提交" class="input" id="sub" onclick="todisabled(this)" >
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
function getwgj(){
	var markId = $("#markId").val();
	var kgliao = $("#kgliao").val();
	if(markId!=""){
		$.ajax( {
		type : "POST",
		url : "PriceAction!getAllNames.action",
		data : {
			'yuanclAndWaigj.markId' : markId,
		},
		dataType : "json",
		success : function(data) {
			if (data != null) {
				$("#proName").val(data.name);	
				$("#specification").val(data.specification);
				$("#wgType").val(data.wgType);
				$("#banben").val(data.banbenhao);
				$("#kgliao").val(data.kgliao);
				$("#type").val("外购");
				$("#tuhao").val(data.tuhao);
				$("#unit").val(data.unit);
			}
		}
	})
	}
}
function check(){
	var markId = $("#markId").val();
	var number = $("#number").val();
	if(markId==""){
		$("#msg_font").html("请输入件号");	
		$("#sub").removeAttr("disabled","disabled")
		return false;
	}else if(number==""){
		$("#number").html("请输入采购数量");	
		$("#sub").removeAttr("disabled","disabled")
		return false;
	}
}

</SCRIPT>
	</body>
</html>
