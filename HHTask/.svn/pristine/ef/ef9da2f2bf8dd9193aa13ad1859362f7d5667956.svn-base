<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/css/bootstrap.min.css">
		<script
			src="${pageContext.request.contextPath}/javascript/jquery/jquery-3.2.1.js">
</script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/javascript/bootstrap-3.3.7-dist/js/bootstrap.js">
</script>
		<style type="text/css">
</style>
	</head>
	<body bgcolor="#ffffff">
	<div class="container">
	<div class="row">
		<div class="span12">
		<center>
		<h2>
			退工序
		</h2>
		</center>
		</div>
	</div>
	<form action="ProcardAction!backProcessInfor.action" method="post"
						enctype="multipart/form-data" id="myform">
	<div class="row">
		<div class="span6" >
						<div class="form-group">
							<label for="">
								件号
							</label>
							<input type="text" class="form-control"
								name="procard.markId" id="markId"/>
						</div>
						<div class="form-group">
							<label for="">
								批次
							</label>
							<font color="red"> *</font>
							<input type="text" class="form-control"
								name="procard.selfCard" id="selfCard"/>
						</div>
						</br>
						
						<button type="button" class="btn btn-default" id="taskadd" onclick="findAllProcessInfor();">
							查询
						</button>
					
		</div>
		<div class="span6" id="tableDiv" style="display : none">
			<table class="table" >
				<thead>
					<tr>
						<th>
							
						</th>
						<th>
							工序号
						</th>
						<th>
							工序名称
						</th>
						<th>
							可领数量
						</th>
						<th>
							状态
						</th>
					</tr>
				</thead>
				<tbody id ="gongxu">
					
				</tbody>
			</table>
			<button type="submit" class="btn btn-default" id="taskadd">
							back
			</button>
		</div>
		</form>
	</div>
</div>
</body>
<script type="text/javascript">
function findAllProcessInfor(){
	$.ajax( {
		type : "POST",
		url:"ProcardAction!findAllProcessInfor.action",
		data : {
			'procard.markId':$("#markId").val(),
			'procard.selfCard':$("#selfCard").val()
		},
		dataType : "json",
		success : function(data) {
			$(data).each(
						function() {
							$(	
								"<tr><td><input id='processIds' name='processIds' type='checkbox' value='"+this.id+"' onclick='chageNum(this)'/></td>"+
								"<td>"+this.processNO+"</td>"+
								"<td>"+this.processName+"</td>"+
								"<td>"+this.totalCount+"</td>"+
								"<td>"+this.status+"</td></tr>")
									.appendTo("#gongxu");
						});
			document.getElementById("tableDiv").style.display='block';
		}
	});
   } 

</script>

</html>
