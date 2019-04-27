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
				<form action="JiaoXiaoKaoHeAction_addyearimprove.action" method="POST" >
					<table class="table">
						<tr>
							<th align="right">姓名</th>
							<td>
								<select  onchange="changvalue(this)">
									<option>--请选择--</option>
								</select>
								<input type="hidden" value="" name="yearimprove.name" id="name"/>
							</td>
						</tr>
						<tr>
							<th align="right">部门</th>
							<td>
								<input type="text" name="yearimprove.dept" id="dept" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">职务</th>
							<td>
								<input type="text" name="yearimprove.post" id="post" readonly="readonly"/>
							</td>
						</tr>
						<tr>
							<th align="right">月份</th>
							<td>
								<input type="text" name="yearimprove.months"  id="months"
								class="Wdate"
								onClick="WdatePicker({dateFmt:'yyyy-MM',skin:'whyGreen'})"/>
							</td>
						</tr>
						<tr>
							<th align="right">经营性</th>
							<td>
								<input type="text" name="yearimprove.jyx" onchange="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">挑战度</th>
							<td>
								<input type="text" name="yearimprove.tzd" onchange="numyanzheng(this,'zhengshu')"/>
							</td>
						</tr>
						<tr>
							<th align="right">改善项</th>
							<td>
								<textarea rows="5" cols="40" name="yearimprove.improveInfor"></textarea>
							</td>
						</tr>
					</table>
					<input type="submit" value="提交" class="input">
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
findDudList = ()=>{
	$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudList.action",
		data:{'dud.isgsx':1},
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
changvalue = (obj)=>{
	if(obj.value!=''){
		$.ajax( {
		type : "POST",
		url : "JiaoXiaoKaoHeAction_findDudById0.action",
		data:{id:obj.value},
		dataType : "json",
		success : function(data) {
			if(data!=null){
				$("#name").val(data.leader);
				$("#dept").val(data.deptName);
				$("#post").val(data.rank);
			}
		}
	})
	}
}

</SCRIPT>
	</body>
</html>
