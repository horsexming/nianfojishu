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
			<h2>
			<s:if test="errorMessage == null || errorMessage == ''">
				工序关联外购件
			</s:if>
			<s:else>
					<font color="red" size="5">${errorMessage}</font>
			</s:else>
			</h2>
			
				<s:if test="successMessage!=null && successMessage!=''">
					<h2>
						<font color="red" size="5">关联零件成功!</font>
					</h2>
				</s:if>
				<form action="ProcardTemplateAction!findwgProcard.action" method="POST">
					<SELECT name="pageStatus">
						<option value=""></option>
						<option value="yibang">显示已绑</option>
						<option value="weibang">显示未绑</option>
						<option value="gxyibang">本工序已绑</option>
						<option value="gxweibang">本工序未绑</option>
					</SELECT>
					<input type="hidden" value="${id}" name="id"/>
					<input type="submit" value="查询"/>
				</form>
				<form action="ProcardTemplateAction!processAndwgProcard.action" method="post">
					<table style="border: 1px solid #000000; border-collapse: collapse; width: 100%;">
						<tr>
							<td colspan="4" >
								<input type="checkbox" onclick="chageAllCheck(this)">全选
							</td>
						</tr>
						<s:iterator value="procardTemplateList" id="wgProcard" status="pagestatus">
							<s:if test="#pagestatus.index % 2 == 0">
								<tr>
							</s:if>
							<th style="border: 1px solid #000000;" align="left" >
									<input type="checkbox" value="${wgProcard.markId}" name="markIds"
								 		id="markIds_${wgProcard.markId}"   >${wgProcard.markId} ${wgProcard.proName}
							</th>
							<s:if test="(#pagestatus.index+1) % 2 == 0">
								</tr>
							</s:if>
						</s:iterator>			
					</table>
					<input type="hidden" value="${id}" name="id" />
					<input type="submit" value="关联零件" style="width: 120px;height: 35px;">
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	var id = '${id}';
	if('${processTemplate.id}'!=''){
		id = '${processTemplate.id}'
	}
	$.ajax( {
		type : "POST",
		url : "ProcardTemplateAction!findProcessAndwgProcard.action",
		data : {id:id},
		dataType : "json",
		success : function(data) {
			if(data!=null && data.length>0){
		for(var i=0;i<data.length;i++){
			var processAndWgProcardTem = data[i];
			var markId = processAndWgProcardTem.wgprocardMardkId;
			var obj = document.getElementById("markIds_"+markId);
			if(obj!=null && obj.value == markId ){
				obj.checked = "checked";
			}
		}
	}
		}
	})
	
	
	
	
	
})


</SCRIPT>
	</body>
</html>
