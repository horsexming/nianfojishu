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
				<form action="JiaoXiaoKaoHeAction_addfpxs.action" method="POST"
					onsubmit="return check()">
					<table class="table" style="width: 80%;">
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<select name="dfpx.dept" id="dept">
									<option></option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">
								分配系数:
							</th>
							<td>
								<input type="text" value="" name="dfpx.fenpeiXiShu" id="fenpeiXiShu"
									 />
							</td>
						</tr>
					</table>
					<input type="submit" value="添加" class="input" id="sub" />
				</form>
			</div>
		</div>
		<%@include file="/util/foot.jsp"%>
		<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
		<SCRIPT type="text/javascript">
$(function(){
	findDudList();
	if('${errorMessage}' == '添加成功!~'){
		alert('添加成功!~');
		parent.chageDiv('none');
		parent.window.location.reload();
	}
})
function check(){
	var dept =$("#dept").val();
	var fenpeiXiShu =$("#fenpeiXiShu").val();
	if(!dept){
		alert('请选择车间');
		$("#dept").focus();
		return false;
	}else if(!fenpeiXiShu){
		alert('请填写提取金额');
		$("#fenpeiXiShu").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function findDudList(){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isdeptFenPei':1},
		dataType : "json",
		success : function(data) {
			$("#dept").empty();
			$("#dept").append('<option value=""></option>');
			if(data!=null){
				$(data).each(function(){
					$("#dept").append('<option value='+this.deptName+'>'+this.deptName+'</option>');
				})
			}
		}
	})
}
$("#fenpeiXiShu").change(()=>{
	var obj = document.getElementById("fenpeiXiShu");
	numyanzheng(obj);
})

</SCRIPT>
	</body>
</html>
