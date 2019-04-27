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
		<div id="gongneng"
			style="width: 100%; border: solid 1px #0170b8; margin-top: 10px;">
			<div id="xitong"
				style="width: 100%; font-weight: bold; height: 50px; "
				align="left">
				<div
					style="float: left; width: 50%; padding-left: 30px; padding-top: 5px;"
					align="left">
					
				</div>
				<div style="float: left; width: 45%; padding-top: 5px;"
					align="right">
					<a href="zhaobiaoAction!listAll.action" style="color: #ffffff">刷新</a>
				</div>
			</div>
			
			<div align="center" id="d1">
				<form action="caoZuoAction!addshebei.action" method="post"
					theme="simple">
					<table>
						<tr>
							<td>
								工位编号
							</td>
							<td>
								<select id="gongweiId" name="zhShebei.gongweiId"
									style="width: 155px;" onchange="f2(this.value)"></select>

							</td>
						</tr>
						<tr>
							<td>
								设备编号
							</td>
							<td>
								<select id="shebeiId" name="zhShebei.shebeiId"
									style="width: 155px;" onchange="f3();f4();"></select>
							</td>
						</tr>
						<tr>
							<td>
								设备名称
							</td>
							<td>
								<select id="zhShebeiname" name="zhShebei.shebeiname"
									style="width: 155px;"></select>
							</td>
						</tr>
						<tr>
							<td>
								工序名称
							</td>
							<td>
								<select id="shebeigongxuName" name="zhShebei.shebeigongxuName"
									style="width: 155px;"></select>
							</td>
						</tr>


						<tr>
							<td>
								<s:submit value="保存" cssClass="input" />
							</td>
							<td>
								<input type="button" name="Submit2" value="取消" class="input"
									onclick="window.history.go(-1);" />
							</td>
						</tr>



					</table>
				</form>
			</div>
			<%@include file="/util/foot.jsp"%>
			</center>
			<!-- JAVASCRIPT脚本写在下面 (这样页面加载速度会快一些)-->
	</body>
	<SCRIPT type="text/javascript">
	 $(function(){
	 //下拉工位编号
	$.ajax( {
		type : "POST",
		url : "caoZuoAction!listmachinegongwei.action",
		dataType : "json",
		success : function(object) {
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='"+ obj+ "'>" + obj +"</option>";
			});
			$(bj).appendTo("#gongweiId")
		}
	  });
	})
	//设备编号
	function f2(gongweiId){
		$.ajax({
		type : "POST",
		url: "caoZuoAction!listshebeibianhao.action",
		data: {pageStatus:gongweiId},
		dataType : "json",
		success : function(object) {
			var bj = "<option></option>";
			$.each(object, function(i, obj) {
				bj += "<option value='"+obj.no+"'>" + obj.no + "</option>";
			});
			$(bj).appendTo("#shebeiId")
			}
	  });
	
	}
	//设备名称
		function f3(){
		var gongweiId=document.getElementById("gongweiId").value;
		var shebeiId=document.getElementById("shebeiId").value;
		$.ajax({
		type : "POST",
		url: "caoZuoAction!listshebeiname.action",
		data: {pageStatus:gongweiId,pageName:shebeiId},
		dataType : "json",
		success : function(object) {
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='"+obj.name+"'>" + obj.name+ "</option>";
			});
			$(bj).appendTo("#zhShebeiname")
			}
	  });
	}
	
		//工序名称
	function f4(){
		var gongweiId=document.getElementById("gongweiId").value;
		var shebeiId=document.getElementById("shebeiId").value;
		$.ajax({
		type : "POST",
		url: "caoZuoAction!listshebeigongxu.action",
		data: {pageStatus:gongweiId,pageName:shebeiId},
		dataType : "json",
		success : function(object) {
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='"+obj.processName+"'>" + obj.processName+ "</option>";
			});
			$(bj).appendTo("#shebeigongxuName")
			}
	  });
	}

//
		//工序名称
		/*
		function f4(id){
		type : "POST",
		url: "caoZuoAction!listshebeigongxu.action",
		data: {pageId:id},
		dataType : "json",
		success : function(object) {
			var bj = "";
			$.each(object, function(i, obj) {
				bj += "<option value='"+obj.id+"'>" + obj.name+ "</option>";
			});
			$(bj).appendTo("#zhShebeiname")
			}
	  });
	
	}
	*/
	</SCRIPT>

</html>
