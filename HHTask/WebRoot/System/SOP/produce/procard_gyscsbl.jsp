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
	<title></title>
		<%@include file="/util/sonHead.jsp"%>
	</head>
	<body>
		<%@include file="/util/sonTop.jsp"%>
		<div id="gongneng" style="width: 100%;">
			<div align="center">
				<form action="ProcardAction!gysCsbl.action" method="POST" onsubmit="return check()">
					<table class="table">
						<tr align="center" bgcolor="#c0dcf2" height="50px">
							<th>
								<input type="checkbox" onclick="chageAllCheck(this)"/>
							</th>
							<th>
								序号
							</th>
							<th>
								订单号
							</th>
							<th>
								业务件号
							</th>
							<th>
								件号
							</th>
							<th>
								名称
							</th>
							<th>
								数量
							</th>
							<th id="blNum_th">
								补料数量
							</th>
						</tr>
						<s:iterator id="pagecsbl" value="csblList"
								status="status0">
								<s:if test="#status0.first">
									<tr bgcolor="red">
										<th colspan="10">
											已补料信息
										</th>
									</tr>
								</s:if>
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
								</td>
								<td>
									<s:property value="#status0.index+1" />
								</td>
								<td>
									${pagecsbl.orderNum}
								</td>
								<td>
									${pagecsbl.ywmarkId}
								</td>
								<td>
									${pagecsbl.markId}
								</td>
								<td>
									${pagecsbl.proName}
								</td>
								<td>
								</td>
								<td>
									${pagecsbl.blNum}
								</td>
						</s:iterator>
						<s:iterator id="pageprocard" value="procardList"
								status="statussdf">
								<s:if test="#statussdf.first">
									<tr bgcolor="green">
										<th colspan="10">
											待补料信息
										</th>
									</tr>
								</s:if>
								<s:if test="#statussdf.index%2==1">
									<tr align="center" bgcolor="#e6f3fb"
										onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'#e6f3fb')">
								</s:if>
								<s:else>
									<tr align="center" onmouseover="chageBgcolor(this)"
										onmouseout="outBgcolor(this,'')">
								</s:else>
								<td>
									<input type="checkbox" value="${pageprocard.id}"  name="processIds" onclick="onclickCheckbox(this,'${pageprocard.id}')" />
								</td>
								<td>
									<s:property value="#statussdf.index+1" />
								</td>
								<td>
									${pageprocard.orderNumber}
								</td>
								<td>
									${pageprocard.ywMarkId}
								</td>
								<td>
									${pageprocard.markId}
								</td>
								<td>
									${pageprocard.proName}
								</td>
								<td>
									${pageprocard.filnalCount}
								</td>
								<td>
									<input type="text" value="" name="blNums" disabled="disabled"
									 id="blNum_${pageprocard.id}" onchange="numyanzheng(this);panduan(this,${pageprocard.filnalCount},
									 ${pageprocard.hascount},'${pageprocard.procardStyle}',${pageprocard.filnalCount})">
								</td>
						</s:iterator>
					</table>
					<input type="hidden" value="${id}" name="id"/>
					<input type="submit" value="补料申请" class="input" >
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if("${errorMessage}" == "true"){
		alert("补料申请成功!~");
		parent.chageDiv('none');
		parent.window.location.reload();
	}else if("${errorMessage}" != ""){
		alert('${errorMessage}')
	}
	
})
function panduan(obj,count,hascount,procardStyle,tjNumber){
	if(obj!=null && obj.value != ''){
		var num = parseFloat(obj.value);
		if(procardStyle == '外购'){
			if(num>(count-hascount)){
				$(obj).val('');
				$(obj).focus();
				alert("补料数量超出了该零件的已领料数量。请重新输入");
			}
		}else{
			if(num>tjNumber){
				$(obj).val('');
				$(obj).focus();
				alert("补料数量超出了该自制件的订单需求数量。请重新输入");
			}
		}
		
	}
	
}
function onclickCheckbox(obj,id){
	if(obj!=null){
		if(obj.checked){
			$("#blNum_"+id).removeAttr("disabled");
		}else{
			$("#blNum_"+id).val("");
			$("#blNum_"+id).attr("disabled","disabled")
		}
	}
}

function check(){
	var ids =	document.getElementsByName("processIds");	
	if(ids!=null && ids.length>0){
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked){
				var id = ids[i].value;
				var blnum =	$("#blNum_"+id).val();
				if(blnum==''){
					var xuhao = $("#xuhao_"+id).val();
					$("#blNum_"+id).focus();
					alert("序号:"+xuhao+"未填写补料数量。");
					$("#sub").removeAttr("disabled")
					$("#blNum_th").html("补料数量");
					return false;
				}else{
					$("#blNum_th").append('<input type="hidden" value='+blnum+' name= "processNumbers"/>');
				}
			}
		}
	}
}
</SCRIPT>
	</body>
</html>
