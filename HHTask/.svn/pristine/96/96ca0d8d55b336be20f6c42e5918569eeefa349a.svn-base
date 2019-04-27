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
				<form action="JiaoXiaoKaoHeAction_updateWwJgMb.action" method="POST" onsubmit="return check()">
				<table class="table" style="width: 80%;">
						<tr>
							<th align="right">
								外委目标:
							</th>
							<td>
								<input type="text" value="${wwjgmb.waiweiMuBiao}"  onchange="numyanzheng(this);maxValue(this)"
								name="wwjgmb.waiweiMuBiao" id="waiweiMuBiao"
								/>
							</td>
						</tr>
						<tr>
							<th align="right">
								结构目标:
							</th>
							<td>
								<input type="text" value="${wwjgmb.jieGouMuBiao}"  onchange="numyanzheng(this);maxValue(this)"
									name="wwjgmb.jieGouMuBiao" id="jieGouMuBiao"
									/>
							</td>
						</tr>
					</table>
					<input type="hidden" value="${wwjgmb.id}" name="wwjgmb.id"/>
					<input type="submit" value="修改" class="input" id="sub"/>
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
<SCRIPT type="text/javascript">
$(function(){
	if('${errorMessage}' == '修改成功!~'){
		alert('修改成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function check(){
	var waiweiMuBiao =$("#waiweiMuBiao").val();
	var jieGouMuBiao =$("#jieGouMuBiao").val();
	if(waiweiMuBiao == ''){
		alert('请填写外委目标');
		$("#waiweiMuBiao").focus();
		return false;
	}else if(jieGouMuBiao ==''){
		alert('请填写结构目标');
		$("#jieGouMuBiao").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}



function maxValue(obj){
	var value = $(obj).val();
	if(value>=1){
		alert('请输入0~1之间的数字');
		$(obj).val('');
	}
}
</SCRIPT>
	</body>
</html>
