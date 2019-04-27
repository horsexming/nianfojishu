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
				<form action="JiaoXiaoKaoHeAction_addBmzZlh.action" method="POST"
					onsubmit="return check()">
					<table class="table" style="width: 80%;">
						<tr>
							<th align="right">
								姓名:
							</th>
							<td>
								<select name="" id="name" onchange="changvalue(this)">
									<option value="">
										请选择
									</option>
								</select>
								<input type="hidden" value="" name="bmzzlh.leaderName"
									id="leaderName" />
							</td>
						</tr>
						<tr>
							<th align="right">
								部门:
							</th>
							<td>
								<input type="text" value="" name="bmzzlh.dept" id="dept"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th align="right">
								职务:
							</th>
							<td>
								<input type="text" value="" name="bmzzlh.rank" id="rank"
									readonly="readonly" />
								<input type="hidden" value="" name="bmzzlh.rankNo" id="rankNo" />
							</td>
						</tr>
						<tr>
							<th align="right">
								加分:
							</th>
							<td>
								<input type="text" value="" name="bmzzlh.addscore" id="addscore"
									onchange="numyanzheng(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								减分:
							</th>
							<td>
								<input type="text" value="" name="bmzzlh.reducescore"
									id="reducescore" onchange="numyanzheng(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								月份:
							</th>
							<td>
								<input type="text" value="" name="bmzzlh.months" id="months" class="Wdate" 
									onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})" />
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
	var waiweiMuBiao =$("#waiweiMuBiao").val();
	var jieGouMuBiao =$("#jieGouMuBiao").val();
	if(waiweiMuBiao == ''){
		alert('请填写外委目标');
		$("#waiweiMuBiao").focus();
		return false;
	}else if(jieGouMuBiao==''){
		alert('请填写结构目标');
		$("#jieGouMuBiao").focus();
		return false;
	}
	$("#sub").attr('disabled','disabled');
	return true;
}
function findDudList(){
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isbmzZlh':1},
		dataType : "json",
		success : function(data) {
			$("#name").empty();
			$("#name").append('<option value=""></option>');
			if(data!=null){
				$(data).each(function(){
					$("#name").append('<option value='+this.id+'>'+this.leader+'</option>');
				})
			}
		}
	})
}
function changvalue(obj){
	if(obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudById0.action",
		data:{id:obj.value},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#leaderName").val(data.leader);
				$("#dept").val(data.deptName);
				$("#rank").val(data.rank);
				$("#rankNo").val(data.rankNo);
			}
		}
	})
	}
}


</SCRIPT>
	</body>
</html>
